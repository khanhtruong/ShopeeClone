package com.khanhtruong.shopeeclone.custom

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout

class ToolbarTransparentBehavior : CoordinatorLayout.Behavior<RecyclerView>() {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: RecyclerView,
        dependency: View
    ): Boolean {
        return dependency is AppBarLayout
    }
}