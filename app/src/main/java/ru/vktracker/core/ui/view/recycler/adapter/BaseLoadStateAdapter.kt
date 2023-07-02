package ru.vktracker.core.ui.view.recycler.adapter

import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import ru.vktracker.core.ui.view.recycler.viewholder.BaseLoadStateViewHolder

/**
 * @author Danil Glazkov on 13.06.2023, 13:03
 */
abstract class BaseLoadStateAdapter<WH : BaseLoadStateViewHolder> : LoadStateAdapter<WH>() {
    override fun onBindViewHolder(holder: WH, loadState: LoadState) = holder.bind(loadState)
}