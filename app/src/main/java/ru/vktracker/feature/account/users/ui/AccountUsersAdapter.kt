package ru.vktracker.feature.account.users.ui

import android.view.ViewGroup
import ru.vktracker.core.ui.BasePagingDataAdapter
import ru.vktracker.core.ui.OnClickCallback
import ru.vktracker.databinding.UserItemBinding

/**
 * @author Danil Glazkov on 02.06.2023, 12:07
 */
class AccountUsersAdapter(
    private val onProfileClickCallback: OnClickCallback<AccountUserUi>,
    private val onTrackButtonClickCallback: OnClickCallback<AccountUserUi>,
) : BasePagingDataAdapter<AccountUsersViewHolder, AccountUserUi>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountUsersViewHolder {
        return AccountUsersViewHolder.Base(
            UserItemBinding.inflate(layoutInflater, parent, false),
            onProfileClickCallback,
            onTrackButtonClickCallback
        )
    }

}