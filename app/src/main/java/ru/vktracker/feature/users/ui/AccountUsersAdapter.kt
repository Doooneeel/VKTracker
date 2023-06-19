package ru.vktracker.feature.users.ui

import android.view.ViewGroup
import ru.vktracker.core.ui.BasePagingDataAdapter
import ru.vktracker.core.ui.OnClickCallback
import ru.vktracker.databinding.LayoutMessageItemBinding
import ru.vktracker.databinding.LayoutUserItemBinding

/**
 * @author Danil Glazkov on 02.06.2023, 12:07
 */
class AccountUsersAdapter(
    private val onProfileClickCallback: OnClickCallback<AccountUserUi>,
    private val onTrackButtonClickCallback: OnClickCallback<AccountUserUi>,
) : BasePagingDataAdapter<AccountUsersViewHolder, AccountUserUi>() {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is AccountUserUi.Base -> BASE
            else -> MESSAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountUsersViewHolder {
        return when (viewType) {
            BASE -> AccountUsersViewHolder.Base(
                LayoutUserItemBinding.inflate(layoutInflater, parent, false),
                onProfileClickCallback,
                onTrackButtonClickCallback
            )
            MESSAGE -> AccountUsersViewHolder.Message(
                LayoutMessageItemBinding.inflate(layoutInflater, parent, false)
            )
            else -> throw IllegalStateException("Unknown viewType: $viewType")
        }
    }

    companion object {
        private const val BASE = 1
        private const val MESSAGE = 2
    }

}