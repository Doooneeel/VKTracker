package ru.vktracker.core.ui.diffutil

import androidx.recyclerview.widget.DiffUtil

/**
 * @author Danil Glazkov on 11.06.2023, 05:13
 */
class DiffUtilItemCallback<T : Same<T>> : DiffUtil.ItemCallback<T>() {

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem.sameContent(newItem)

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem.same(newItem)
}