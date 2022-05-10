package com.google.android.material.appbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.HeaderScrollingViewBehavior;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Copy of ScrollingViewBehavior.
 * We need to change max offset position.
 * <p>
 * + ACTION_UP detection
 *
 * @see [offsetChildAsNeeded]
 */
@SuppressWarnings("unused")
@Keep
public class NewsScrollingViewBehavior extends HeaderScrollingViewBehavior {

    public interface ActionUpListener {
        void onActionUp();
    }

    public interface ActionDownListener {
        void onActionDown();
    }

    public interface ActionMoveListener {
        void onActionMove();
    }

    private int minTopOffset;
    private ActionUpListener actionUpListener;
    private ActionDownListener actionDownListener;
    private ActionMoveListener actionMoveListener;

    public NewsScrollingViewBehavior() {
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed);
        System.out.println("XXXX Behavior coordinatorLayout = " + coordinatorLayout + ", child = " + child + ", target = " + target + ", dxConsumed = " + dxConsumed + ", dyConsumed = " + dyConsumed + ", dxUnconsumed = " + dxUnconsumed + ", dyUnconsumed = " + dyUnconsumed + ", type = " + type + ", consumed = " + Arrays.toString(consumed));
    }

    @SuppressLint("PrivateResource")
    public NewsScrollingViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        minTopOffset = (int) (context.getResources().getDisplayMetrics().density * 56); // TODO remove hardcoded value
        final TypedArray a = context.obtainStyledAttributes(attrs, com.google.android.material.R.styleable.ScrollingViewBehavior_Layout);
        setOverlayTop(a.getDimensionPixelSize(com.google.android.material.R.styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0));
        a.recycle();
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        // We depend on any AppBarLayouts
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child,
                                          View dependency) {
        offsetChildAsNeeded(parent, child, dependency);
        return false;
    }

    @Override
    public boolean onRequestChildRectangleOnScreen(CoordinatorLayout parent, View child,
                                                   Rect rectangle, boolean immediate) {
        final AppBarLayout header = findFirstDependency(parent.getDependencies(child));
        if (header != null) {
            // Offset the rect by the child's left/top
            rectangle.offset(child.getLeft(), child.getTop());

            final Rect parentRect = tempRect1;
            parentRect.set(0, 0, parent.getWidth(), parent.getHeight());

            if (!parentRect.contains(rectangle)) {
                // If the rectangle can not be fully seen the visible bounds, collapse
                // the AppBarLayout
                header.setExpanded(false, !immediate);
                return true;
            }
        }
        return false;
    }

    /**
     * Only this method has been changed
     */
    private void offsetChildAsNeeded(CoordinatorLayout parent, View child, View dependency) {
        final CoordinatorLayout.Behavior behavior =
                ((CoordinatorLayout.LayoutParams) dependency.getLayoutParams()).getBehavior();
        if (behavior instanceof AppBarLayout.Behavior) {
            // Offset the child, pinning it to the bottom the header-dependency, maintaining
            // any vertical gap and overlap
            final AppBarLayout.Behavior ablBehavior = (AppBarLayout.Behavior) behavior;
            try {
                Field offsetDataField = AppBarLayout.BaseBehavior.class.getDeclaredField("offsetDelta");
                offsetDataField.setAccessible(true);
                int ablBehaviorOffsetDelta = offsetDataField.getInt(ablBehavior);

                int targetOffset = dependency.getBottom() - child.getTop() + ablBehaviorOffsetDelta + this.getVerticalLayoutGap() - this.getOverlapPixelsForOffset(dependency);
                ViewCompat.offsetTopAndBottom(child, targetOffset);
                int newTop = child.getTop();

                int topPadding;

                if (newTop > minTopOffset * 2) {
                    topPadding = 0;
                } else {
                    topPadding = -newTop / 2 + minTopOffset;
                }
                child.setPadding(child.getPaddingLeft(), topPadding, child.getPaddingRight(), child.getPaddingBottom());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    float getOverlapRatioForOffset(final View header) {
        if (header instanceof AppBarLayout) {
            final AppBarLayout abl = (AppBarLayout) header;
            final int totalScrollRange = abl.getTotalScrollRange();
            final int preScrollDown = abl.getDownNestedPreScrollRange();
            final int offset = getAppBarLayoutOffset(abl);

            if (preScrollDown != 0 && (totalScrollRange + offset) <= preScrollDown) {
                // If we're in a pre-scroll down. Don't use the offset at all.
                return 0;
            } else {
                final int availScrollRange = totalScrollRange - preScrollDown;
                if (availScrollRange != 0) {
                    // Else we'll use a interpolated ratio of the overlap, depending on offset
                    return 1f + (offset / (float) availScrollRange);
                }
            }
        }
        return 0f;
    }

    private static int getAppBarLayoutOffset(AppBarLayout abl) {
        final CoordinatorLayout.Behavior behavior =
                ((CoordinatorLayout.LayoutParams) abl.getLayoutParams()).getBehavior();
        if (behavior instanceof AppBarLayout.Behavior) {
            return ((AppBarLayout.Behavior) behavior).getTopBottomOffsetForScrollingSibling();
        }
        return 0;
    }

    @Override
    AppBarLayout findFirstDependency(List<View> views) {
        for (int i = 0, z = views.size(); i < z; i++) {
            View view = views.get(i);
            if (view instanceof AppBarLayout) {
                return (AppBarLayout) view;
            }
        }
        return null;
    }

    @Override
    int getScrollRange(View v) {
        if (v instanceof AppBarLayout) {
            return ((AppBarLayout) v).getTotalScrollRange();
        } else {
            return super.getScrollRange(v);
        }
    }

    /**
     * Detect and notify about ACTION_UP event
     */
    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN && actionDownListener != null) {
            actionDownListener.onActionDown();
        }
        if (ev.getAction() == MotionEvent.ACTION_UP && actionUpListener != null) {
            actionUpListener.onActionUp();
        }
        if (ev.getAction() == MotionEvent.ACTION_MOVE && actionUpListener != null) {
            actionMoveListener.onActionMove();
        }
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    public void setActionUpListener(ActionUpListener actionUpListener) {
        this.actionUpListener = actionUpListener;
    }

    public void setActionDownListener(ActionDownListener actionDownListener) {
        this.actionDownListener = actionDownListener;
    }

    public void setActionMoveListener(ActionMoveListener actionMoveListener) {
        this.actionMoveListener = actionMoveListener;
    }
}