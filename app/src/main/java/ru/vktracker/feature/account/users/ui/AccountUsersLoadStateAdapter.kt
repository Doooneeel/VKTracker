package ru.vktracker.feature.account.users.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import ru.vktracker.core.ui.BaseLoadStateAdapter
import ru.vktracker.core.ui.BaseLoadStateViewHolder
import ru.vktracker.databinding.ErrorMessageLayoutBinding
import ru.vktracker.feature.account.users.tabs.UsersLoadStateViewHolder

/**
 * @author Danil Glazkov on 13.06.2023, 20:45
 */
class AccountUsersLoadStateAdapter(
    private val onRefreshButtonClickListener: View.OnClickListener,
    private val recyclerView: RecyclerView
) : BaseLoadStateAdapter<BaseLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState,
    ) : BaseLoadStateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ErrorMessageLayoutBinding.inflate(inflater, parent, false)
        return UsersLoadStateViewHolder(binding, recyclerView, onRefreshButtonClickListener)
    }
}