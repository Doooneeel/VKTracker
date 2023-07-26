package ru.vktracker.core.ui.dialog

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import ru.vktracker.R
import ru.vktracker.core.ui.SingleLifecycleEventObserver

/**
 * @author Danil Glazkov on 20.06.2023, 14:47
 */
interface Dialog {

    fun show(view: View)


    abstract class Abstract<B> : Dialog {
        protected abstract fun B.setup (view: View)
    }

    abstract class AbstractAlert : Abstract<MaterialAlertDialogBuilder>() {
        protected open val themeOverlay =
            R.style.ThemeOverlay_Material3_MaterialAlertDialog_IconPrimaryColor

        override fun show(view: View) {
            val dialog = MaterialAlertDialogBuilder(view.context, themeOverlay).run {
                setup(view)
                create()
            }

            view.findViewTreeLifecycleOwner()?.let { owner: LifecycleOwner ->
                owner.lifecycle.addObserver(
                    SingleLifecycleEventObserver.Destroy { dialog.dismiss() }
                )
            }

            dialog.show()
        }
    }

    abstract class AbstractToast : Abstract<Toast>() {
        override fun show(view: View) {
            val toast = Toast.makeText(view.context, "", Toast.LENGTH_SHORT)
            toast.setup(view)
            toast.show()
        }
    }

    abstract class AbstractSnackbar : Abstract<Snackbar>() {
        override fun show(view: View) {
            val snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
            snackbar.setup(view)
            snackbar.show()
        }
    }

}