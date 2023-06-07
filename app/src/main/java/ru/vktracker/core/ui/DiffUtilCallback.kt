package ru.vktracker.core.ui

import androidx.recyclerview.widget.DiffUtil

/**
 * @author Danil Glazkov on 02.06.2023, 12:03
 */
class DiffUtilCallback<T : DiffUtilCallback.Same<T>>(
    private val oldList: List<T>,
    private val newList: List<T>
) : DiffUtil.Callback() {

    interface Same<T> {

        fun same(data: T): Boolean

        fun sameContent(data: T): Boolean

    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].same(newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].sameContent(newList[newItemPosition])
}