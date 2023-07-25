package ru.vktracker.core.ui.text.validate

import ru.vktracker.core.ui.text.Message

/**
 * @author Danil Glazkov on 25.07.2022, 04:21
 */
interface ValidateChain<T> : Validate<T> {

    abstract class Abstract<T>(
        private val current: Validate<T>,
        private val next: Validate<T>
    ) : ValidateChain<T> {
        protected var currentValid = false

        override fun errorMessage(): Message = if (currentValid) {
            next.errorMessage()
        } else {
            current.errorMessage()
        }
    }

    class AllValid<T>(
        private val current: Validate<T>,
        private val next: Validate<T>
    ) : Abstract<T>(current, next) {
        override fun isValid(source: T): Boolean {
            currentValid = current.isValid(source)
            return if (currentValid) next.isValid(source) else false
        }
    }

    class AnyValid<T>(
        private val current: Validate<T>,
        private val next: Validate<T>
    ) : Abstract<T>(current, next) {
        private var nextValid = false

        override fun errorMessage(): Message =
            if (nextValid || currentValid) Message.Empty else super.errorMessage()

        override fun isValid(source: T): Boolean {
            currentValid = current.isValid(source)
            nextValid = next.isValid(source)

            return currentValid || nextValid
        }
    }

}