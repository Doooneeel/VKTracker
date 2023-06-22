package ru.vktracker.core.common.exception

import java.lang.IllegalStateException

/**
 * @author Danil Glazkov on 20.06.2023, 19:01
 */
class EmptyCacheException : IllegalStateException("Cache is empty")