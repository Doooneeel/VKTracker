package ru.vktracker.core.ui.diffutil

import androidx.recyclerview.widget.DiffUtil

/**
 * @author Danil Glazkov on 02.06.2023, 12:03
 */
class DiffUtilCallback<T : Same<T>>(
    private val oldList: List<T>,
    private val newList: List<T>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].same(newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].sameContent(newList[newItemPosition])
}