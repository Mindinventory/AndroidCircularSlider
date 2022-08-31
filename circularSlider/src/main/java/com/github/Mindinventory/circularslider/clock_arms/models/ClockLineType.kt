package com.github.Mindinventory.circularslider.clock_arms.models

sealed class ClockLineType {
    object Minutes : ClockLineType()
    object Hours : ClockLineType()
}