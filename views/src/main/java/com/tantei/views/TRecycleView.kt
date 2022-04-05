package com.tantei.views

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TRecycleView : RecyclerView {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attributeSet: AttributeSet) :super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int): super(context, attributeSet, defStyleAttr)

    private var isSlidingToLast = false

    interface OnScrollBottomListener {
        fun onScrollBottom() {}
    }

    fun setOnScrollBottomListener(listener: OnScrollBottomListener) {
        this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                if (newState == SCROLL_STATE_IDLE) {
                    val lastItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()
                    val totalItemCount = layoutManager.itemCount
                    if (lastItemPosition == (totalItemCount - 1) && isSlidingToLast) {
                        listener.onScrollBottom()
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                isSlidingToLast = if (dy > 0)  true else false
            }
        })
    }
}