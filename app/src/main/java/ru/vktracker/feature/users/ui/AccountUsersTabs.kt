package ru.vktracker.feature.users.ui

import android.content.res.Resources
import ru.vktracker.R
import ru.vktracker.core.ui.navigation.GenericTab
import ru.vktracker.core.ui.navigation.GenericTabs
import ru.vktracker.feature.users.ui.faves.AccountFavesFragment
import ru.vktracker.feature.users.ui.friends.AccountFriendsFragment
import ru.vktracker.feature.users.ui.subscribers.AccountSubscribersFragment

/**
 * @author Danil Glazkov on 18.06.2023, 14:33
 */
class AccountUsersTabs(resources: Resources) : GenericTabs.Abstract() {
    override val tabs = arrayListOf(
        GenericTab.Base(AccountFriendsFragment(), resources.getString(R.string.friends)),
        GenericTab.Base(AccountFavesFragment(), resources.getString(R.string.faves)),
        GenericTab.Base(AccountSubscribersFragment(), resources.getString(R.string.subscribers)),
    )
}