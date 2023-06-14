package ru.vktracker.feature.account.users.ui

import android.content.res.Resources
import ru.vktracker.R
import ru.vktracker.core.ui.AbstractTab
import ru.vktracker.core.ui.AbstractTabs
import ru.vktracker.feature.account.users.tabs.faves.AccountFavesFragment
import ru.vktracker.feature.account.users.tabs.friends.AccountFriendsFragment
import ru.vktracker.feature.account.users.tabs.subscribers.AccountSubscribersFragment

/**
 * @author Danil Glazkov on 10.06.2023, 07:49
 */
class AccountUsersTabs(resources: Resources) : AbstractTabs {

    private val tabs = listOf<AbstractTab>(
        AbstractTab.Base(AccountFriendsFragment(), resources.getString(R.string.friends)),
        AbstractTab.Base(AccountFavesFragment(), resources.getString(R.string.faves)),
        AbstractTab.Base(AccountSubscribersFragment(), resources.getString(R.string.subscribers)),
    )

    override fun tabs(): List<AbstractTab> = tabs

}