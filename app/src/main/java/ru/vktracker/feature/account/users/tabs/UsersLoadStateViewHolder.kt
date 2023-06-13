package ru.vktracker.feature.account.users.tabs

import android.view.View
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import ru.vktracker.core.ui.BaseLoadStateViewHolder
import ru.vktracker.databinding.ErrorMessageLayoutBinding

/**
 * @author Danil Glazkov on 13.06.2023, 05:55
 */
class UsersLoadStateViewHolder(
    private val binding: ErrorMessageLayoutBinding,
    private val recyclerView: RecyclerView,
    onRefreshButtonClickListener: View.OnClickListener
) : BaseLoadStateViewHolder(binding.root) {

    init {
        binding.tryAgainButton.setOnClickListener(onRefreshButtonClickListener)
    }

    override fun bind(data: LoadState) = with(binding) {
        val isError = data is LoadState.Error

        if (isError) {
            recyclerView.adapter?.itemCount?.let { count ->
                recyclerView.smoothScrollToPosition(count)
            }
        }
        tryAgainButton.isVisible = isError
        messageTextView.isVisible = isError

        progressBar.isVisible = data is LoadState.Loading
    }
}