package com.orpuwupetup.numberslight.utils.devicestate

import android.content.Context
import android.content.res.Configuration
import com.orpuwupetup.numberslight.R

class DeviceStateChecker(private val context: Context) {

    fun isLandscape(): Boolean {
        val orientation = context.resources.configuration.orientation
        return orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    fun isTablet(): Boolean =
        context.resources.getBoolean(R.bool.isTablet)

    fun isTabletInPortraitMode() = !isTablet() || (isTablet() && !isLandscape())
}