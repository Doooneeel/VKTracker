package ru.vktracker.core.ui

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Danil Glazkov on 13.06.2023, 12:12
 */
class BaseLinearLayoutManager(context: Context) : LinearLayoutManager(context) {

    private val scroller = BaseLinearSmoothScroller(context, 100F)

    override fun smoothScrollToPosition(
        recyclerView: RecyclerView,
        state: RecyclerView.State,
        position: Int
    ) {
        scroller.targetPosition = position
        startSmoothScroll(scroller)
    }
}