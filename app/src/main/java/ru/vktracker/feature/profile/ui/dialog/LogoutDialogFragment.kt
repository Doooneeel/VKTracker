package ru.vktracker.feature.profile.ui.dialog

import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import ru.vktracker.R
import ru.vktracker.core.ui.dialog.AbstractDialogFragment
import ru.vktracker.core.ui.dialog.DialogEvent
import ru.vktracker.feature.profile.ui.ProfileViewModel

/**
 * @author Danil Glazkov on 30.07.2023, 9:50
 */
@AndroidEntryPoint
class LogoutDialogFragment : AbstractDialogFragment() {

    private val viewModel: ProfileViewModel.Dialog by viewModels<ProfileViewModel.Base>(
        ::requireParentFragment
    )

    override fun MaterialAlertDialogBuilder.setup() {
        setTitle(R.string.question_logout)
        setMessage(R.string.explanation_logout)
        setIcon(R.drawable.ic_logout)
        setNegativeButton(R.string.cancel) { dialog, _ -> dialog.cancel() }
        setPositiveButton(R.string.logout) { dialog, _ ->
            viewModel.logoutDialogEvent(DialogEvent.Confirm)
            dialog.cancel()
        }
    }
}