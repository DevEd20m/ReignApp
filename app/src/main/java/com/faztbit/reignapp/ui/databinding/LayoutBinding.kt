package com.faztbit.reignapp.ui.databinding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("onSwipeRefreshLayout")
fun onSwipeRefreshLayout(swipeRefreshLayout: SwipeRefreshLayout, function: () -> Unit) {
    swipeRefreshLayout.setOnRefreshListener {
        function.invoke()
    }
}

@BindingAdapter("setSwipeRefreshing")
fun <T> setSwipeRefreshing(swipeRefreshLayout: SwipeRefreshLayout, isRefreshing: Boolean) {
    if (swipeRefreshLayout.isRefreshing && !isRefreshing) {
        swipeRefreshLayout.isRefreshing = isRefreshing
    }
}
