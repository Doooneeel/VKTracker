package ru.vktracker.feature.login.twofactor.ui.view.timerbutton

import android.os.Parcelable
import android.view.View
import kotlinx.parcelize.Parcelize

/**
 * @author Danil Glazkov on 09.07.2023, 11:43
 */
@Parcelize
class TimerViewSavedState(
    private val parcelable: Parcelable?,
    val millisUntilFinished: Long,
    val currentTime: Long
) : View.BaseSavedState(parcelable)