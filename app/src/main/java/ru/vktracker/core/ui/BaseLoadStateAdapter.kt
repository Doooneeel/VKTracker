package ru.vktracker.core.ui

import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

/**
 * @author Danil Glazkov on 13.06.2023, 13:03
 */
abstract class BaseLoadStateAdapter<WH : BaseLoadStateViewHolder> : LoadStateAdapter<WH>() {
    override fun onBindViewHolder(holder: WH, loadState: LoadState) = holder.bind(loadState)
}