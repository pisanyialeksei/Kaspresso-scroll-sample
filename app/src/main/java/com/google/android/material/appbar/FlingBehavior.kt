package com.google.android.material.appbar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.OverScroller
import androidx.coordinatorlayout.widget.CoordinatorLayout

class FlingBehavior @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : AppBarLayout.Behavior(context, attributeSet) {

    private var isAppbarFlinging: Boolean = false
    private var originalScroller: OverScroller? = null
    private val fakeScroller: OverScroller = object : OverScroller(context) {
        override fun computeScrollOffset(): Boolean {
            scroller = originalScroller
            return false
        }
    }

    override fun setHeaderTopBottomOffset(coordinatorLayout: CoordinatorLayout, appBarLayout: AppBarLayout, newOffset: Int, minOffset: Int, maxOffset: Int): Int {
        isAppbarFlinging = minOffset == Int.MIN_VALUE && maxOffset == Int.MAX_VALUE
        if (scroller === fakeScroller) {
            scroller = originalScroller
        }
        return super.setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, newOffset, minOffset, maxOffset)
    }

    override fun onFlingFinished(parent: CoordinatorLayout, layout: AppBarLayout) {
        super.onFlingFinished(parent, layout)
        isAppbarFlinging = false
    }

    override fun onStartNestedScroll(parent: CoordinatorLayout, child: AppBarLayout, directTargetChild: View, target: View, nestedScrollAxes: Int, type: Int): Boolean {
        if (isAppbarFlinging && scroller !== fakeScroller) {
            originalScroller = scroller
            scroller = fakeScroller
        }
        return super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes, type)
    }
}