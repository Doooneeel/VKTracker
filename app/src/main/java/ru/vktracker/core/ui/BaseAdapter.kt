package ru.vktracker.core.ui

import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.vktracker.core.ui.view.AbstractView

/**
 * @author Danil Glazkov on 02.06.2023, 12:06
 */
abstract class BaseAdapter<VH : BaseViewHolder<VT>, VT : DiffUtilCallback.Same<VT>> :
    RecyclerView.Adapter<VH>(), AbstractView.List<VT> {

    protected lateinit var layoutInflater: LayoutInflater

    protected abstract fun getViewType(data: VT): Int

    private val mutableList: MutableList<VT> = mutableListOf()


    override fun getItemViewType(position: Int): Int = getViewType(mutableList[position])

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        layoutInflater = LayoutInflater.from(recyclerView.context)
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(mutableList[position])

    override fun getItemCount(): Int = mutableList.size

    open fun diffUtilCallback(oldList: MutableList<VT>, newList: List<VT>): DiffUtil.Callback =
        DiffUtilCallback(oldList, newList)

    override fun apply(items: List<VT>) {
        val result = DiffUtil.calculateDiff(diffUtilCallback(mutableList, items))
        mutableList.clear()
        mutableList.addAll(items)
        result.dispatchUpdatesTo(this)
    }

}