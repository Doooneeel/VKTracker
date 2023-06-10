package ru.vktracker.feature.account.users.ui

import android.view.ViewGroup
import ru.vktracker.core.ui.BaseAdapter
import ru.vktracker.core.ui.OnClickCallback
import ru.vktracker.databinding.HeaderItemBinding
import ru.vktracker.databinding.MessageItemBinding
import ru.vktracker.databinding.UserItemBinding

/**
 * @author Danil Glazkov on 02.06.2023, 12:07
 */
class AccountUsersAdapter(
    private val onProfileClickCallback: OnClickCallback<AccountUserUi>,
    private val onTrackButtonClickCallback: OnClickCallback<AccountUserUi>,
) : BaseAdapter<AccountUsersViewHolder, AccountUserUi>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        BIRTHDAY -> AccountUsersViewHolder.Base(
            UserItemBinding.inflate(layoutInflater, parent, false),
            onProfileClickCallback,
            onTrackButtonClickCallback
        )
        HEADER -> AccountUsersViewHolder.Header(
            HeaderItemBinding.inflate(layoutInflater, parent, false)
        )
        MESSAGE -> AccountUsersViewHolder.Message(
            MessageItemBinding.inflate(layoutInflater, parent, false)
        )
        else -> throw IllegalArgumentException("unknown viewType: $viewType")
    }

    override fun getViewType(data: AccountUserUi) = when (data) {
        is AccountUserUi.Base -> BIRTHDAY
        is AccountUserUi.Header -> HEADER
        is AccountUserUi.Message -> MESSAGE
        else -> -1
    }

    companion object {
        private const val BIRTHDAY: Int = 0
        private const val HEADER: Int = 1
        private const val MESSAGE: Int = 2
    }

}