package ru.vktracker.feature.account.users.ui

import android.view.View
import ru.vktracker.core.ui.BaseViewHolder
import ru.vktracker.core.ui.OnClickCallback
import ru.vktracker.databinding.UserItemBinding

/**
 * @author Danil Glazkov on 02.06.2023, 12:08
 */
abstract class AccountUsersViewHolder(item: View) : BaseViewHolder<AccountUserUi>(item) {

    class Base(
        private val binding: UserItemBinding,
        private val onProfileClickCallback: OnClickCallback<AccountUserUi>,
        private val onTrackButtonClick: OnClickCallback<AccountUserUi>,
    ) : AccountUsersViewHolder(binding.root) {
        override fun bind(data: AccountUserUi) {
            data.apply(binding.name, binding.avatar, binding.trackButton)

            binding.root.setOnClickListener { onProfileClickCallback.onClick(data) }
            binding.trackButton.setOnClickListener {
                data.changeTracked(binding.trackButton.isChecked)
                onTrackButtonClick.onClick(data)
            }
        }
    }

}