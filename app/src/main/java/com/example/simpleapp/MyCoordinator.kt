package com.example.simpleapp

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout

class MyCoordinator @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CoordinatorLayout(context, attributeSet, defStyleAttr) {

    override fun onNestedScroll(target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int, consumed: IntArray) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed)
//        println("onNestedScroll ${this::class.simpleName}   target = [${target!!::class.simpleName}], dyConsumed = [${dyConsumed}], dyUnconsumed = [${dyUnconsumed}], type = [${type}], consumed = [${consumed}]")
    }

    override fun dispatchNestedScroll(dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, offsetInWindow: IntArray?): Boolean {
//        println("dispatchNestedScroll ${this::class.simpleName} dispatchNestedScroll dyConsumed = [${dyConsumed}], dyUnconsumed = [${dyUnconsumed}]")
        return super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow)
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
//        println("onScrollChanged l = [${l}], t = [${t}], oldl = [${oldl}], oldt = [${oldt}]")
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        super.onNestedPreScroll(target, dx, dy, consumed, type)
//        println("onNestedPreScroll dx = [${dx}], dy = [${dy}], consumed = [${consumed}], type = [${type}]")
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
        super.onNestedScrollAccepted(child, target, axes, type)
//        println(" onNestedScrollAccepted axes = [${axes}], type = [${type}]")
    }

    override fun dispatchNestedPreScroll(dx: Int, dy: Int, consumed: IntArray?, offsetInWindow: IntArray?): Boolean {
//        println("dispatchNestedPreScroll dx = [${dx}], dy = [${dy}], consumed = [${consumed}], offsetInWindow = [${offsetInWindow}]")
        return super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
    }
}

class MyAppBarLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppBarLayout(context, attributeSet, defStyleAttr) {
    override fun onNestedScroll(target: View?, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
        println("${this::class.simpleName}   target = [${target!!::class.simpleName}], dyConsumed = [${dyConsumed}], dyUnconsumed = [${dyUnconsumed}]")
    }

    override fun dispatchNestedScroll(dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, offsetInWindow: IntArray?): Boolean {
        println("${this::class.simpleName} dispatchNestedScroll dyConsumed = [${dyConsumed}], dyUnconsumed = [${dyUnconsumed}]")
        return super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow)
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        println("l = [${l}], t = [${t}], oldl = [${oldl}], oldt = [${oldt}]")
    }

    override fun onNestedPreScroll(target: View?, dx: Int, dy: Int, consumed: IntArray?) {
        super.onNestedPreScroll(target, dx, dy, consumed)
        println("target = [${target}], dx = [${dx}], dy = [${dy}], consumed = [${consumed}]")
    }
}

class MyToolbar @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Toolbar(context, attributeSet, defStyleAttr) {
    override fun onNestedScroll(target: View?, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
        println("${this::class.simpleName}   target = [${target!!::class.simpleName}], dyConsumed = [${dyConsumed}], dyUnconsumed = [${dyUnconsumed}]")
    }

    override fun dispatchNestedScroll(dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, offsetInWindow: IntArray?): Boolean {
        println("${this::class.simpleName} dispatchNestedScroll dyConsumed = [${dyConsumed}], dyUnconsumed = [${dyUnconsumed}]")
        return super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow)
    }
}

class MyRecycler @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attributeSet, defStyleAttr) {

    override fun onNestedScroll(target: View?, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
        println("${this::class.simpleName}   target = [${target!!::class.simpleName}], dyConsumed = [${dyConsumed}], dyUnconsumed = [${dyUnconsumed}]")
    }

    override fun dispatchNestedScroll(dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, offsetInWindow: IntArray?): Boolean {
        println("${this::class.simpleName} dispatchNestedScroll dyConsumed = [${dyConsumed}], dyUnconsumed = [${dyUnconsumed}]")
        return super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow)
    }
}