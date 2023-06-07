package ru.vktracker.main.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vktracker.core.common.CoroutineDispatchers
import ru.vktracker.core.ui.BaseViewModel
import javax.inject.Inject

/**
 * @author Danil Glazkov on 01.06.2023, 22:29
 */
interface MainNavigationViewModel : BaseViewModel {

    @HiltViewModel
    class Base @Inject constructor(
        dispatchers: CoroutineDispatchers
    ) : BaseViewModel.Abstract(dispatchers), MainNavigationViewModel
}