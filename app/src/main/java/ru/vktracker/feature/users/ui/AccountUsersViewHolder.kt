package ru.vktracker.feature.users.ui

import android.view.View
import ru.vktracker.core.ui.BaseViewHolder
import ru.vktracker.core.ui.OnClickCallback
import ru.vktracker.databinding.LayoutMessageItemBinding
import ru.vktracker.databinding.LayoutUserItemBinding

/**
 * @author Danil Glazkov on 02.06.2023, 12:08
 */
abstract class AccountUsersViewHolder(item: View) : BaseViewHolder<AccountUserUi>(item) {

    class Base(
        private val binding: LayoutUserItemBinding,
        private val onProfileClickCallback: OnClickCallback<AccountUserUi>,
        private val onTrackButtonClick: OnClickCallback<AccountUserUi>,
    ) : AccountUsersViewHolder(binding.root) {

        private val clickListener = View.OnClickListener { view ->
            val account = view.tag as AccountUserUi

            when (view.id) {
                binding.trackButton.id -> {
                    account.changeTrackingStatus(binding.trackButton.isChecked)
                    onTrackButtonClick.onClick(account)
                }
                binding.root.id -> onProfileClickCallback.onClick(account)
            }
        }

        override fun bind(data: AccountUserUi) {
            data.apply(binding.name, binding.avatar, binding.trackButton)

            binding.root.tag = data
            binding.trackButton.tag = data

            binding.root.setOnClickListener(clickListener)
            binding.trackButton.setOnClickListener(clickListener)
        }
    }

    class Message(
        private val binding: LayoutMessageItemBinding
    ) : AccountUsersViewHolder(binding.root) {
        override fun bind(data: AccountUserUi) = data.applyMessage(binding.messageTextView)
    }

}