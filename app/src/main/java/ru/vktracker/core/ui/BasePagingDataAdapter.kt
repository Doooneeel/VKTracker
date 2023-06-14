package ru.vktracker.core.ui

import android.view.LayoutInflater
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.vktracker.core.ui.diffutil.DiffUtilItemCallback
import ru.vktracker.core.ui.diffutil.Same
import ru.vktracker.core.ui.view.AbstractView

/**
 * @author Danil Glazkov on 11.06.2023, 04:58
 */
abstract class BasePagingDataAdapter<VH : BaseViewHolder<T>, T : Same<T>> : PagingDataAdapter<T, VH>(
    DiffUtilItemCallback()
) , AbstractView.PagingList<T> {

    protected lateinit var layoutInflater: LayoutInflater

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        layoutInflater = LayoutInflater.from(recyclerView.context)
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
        }
    }

    override fun apply(scope: LifecycleCoroutineScope, data: PagingData<T>) {
        scope.launch(Dispatchers.IO) { submitData(data) }
    }
}