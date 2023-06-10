package ru.vktracker.feature.account.users.ui

import android.content.res.Resources
import ru.vktracker.R
import ru.vktracker.core.ui.AbstractTab
import ru.vktracker.core.ui.AbstractTabs
import ru.vktracker.feature.account.users.tabs.faves.ui.AccountFavesTabFragment
import ru.vktracker.feature.account.users.tabs.friends.ui.AccountFriendsTabFragment
import ru.vktracker.feature.account.users.tabs.subscribers.ui.AccountSubscribersTabFragment

/**
 * @author Danil Glazkov on 10.06.2023, 07:49
 */
class AccountUsersTabs(resources: Resources) : AbstractTabs {

    private val tabs = listOf<AbstractTab>(
        AbstractTab.Base(AccountFriendsTabFragment(), resources.getString(R.string.friends)),
        AbstractTab.Base(AccountFavesTabFragment(), resources.getString(R.string.faves)),
        AbstractTab.Base(AccountSubscribersTabFragment(), resources.getString(R.string.subscribers)),
    )

    override fun tabs(): List<AbstractTab> = tabs

}