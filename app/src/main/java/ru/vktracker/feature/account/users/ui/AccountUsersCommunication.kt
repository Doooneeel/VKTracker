package ru.vktracker.feature.account.users.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import ru.vktracker.core.ui.paging.PagingCommunication
import ru.vktracker.core.ui.paging.ProvidePagingConfig
import ru.vktracker.core.ui.paging.ProvidePagingSource

/**
 * @author Danil Glazkov on 02.06.2023, 13:22
 */
interface AccountUsersCommunication : PagingCommunication<AccountUserUi> {

    interface Observe {
        fun observeAccountUsers(owner: LifecycleOwner, observer: Observer<PagingData<AccountUserUi>>)
    }

    class Base(
        config: ProvidePagingConfig,
        source: ProvidePagingSource<AccountUserUi>
    ) : PagingCommunication.Abstract<AccountUserUi>(config, source),
        AccountUsersCommunication
}