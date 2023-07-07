package ru.vktracker.feature.login.twofactor.ui

import ru.vktracker.R
import ru.vktracker.core.common.text.TimeTextFormat
import ru.vktracker.core.ui.resources.ManageResources
import ru.vktracker.core.ui.timer.SecondCountDownTimer
import ru.vktracker.feature.login.twofactor.ui.view.ResendCodeViewState
import ru.vktracker.feature.login.twofactor.ui.view.ResendCodeViewStateCommunication
import java.util.concurrent.TimeUnit

/**
 * @author Danil Glazkov on 07.07.2023, 13:57
 */
interface ResendCodeTimer {

    fun start()

    fun cancel()


    class Base(
        private val resendCodeViewStateCommunication: ResendCodeViewStateCommunication,
        private val timeTextFormat: TimeTextFormat,
        resources: ManageResources,
    ) : ResendCodeTimer {

        private val duration = resources.number(R.integer.vk_resend_code_duration_millis)

        private val timer = SecondCountDownTimer(duration.toLong(), { millis ->
            val seconds = TimeUnit.MILLISECONDS.toSeconds(millis)

            resendCodeViewStateCommunication.put(
                when (seconds == 0L) {
                    true -> ResendCodeViewState.EmptyTimeEnable
                    else -> ResendCodeViewState.TimeDisable(timeTextFormat.format(millis))
                }
            )
        })

        override fun start() {
            timer.cancel()
            timer.start()
        }

        override fun cancel() { timer.cancel() }
    }
}