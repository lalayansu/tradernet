package com.example.presenter.extensions

import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

fun paddingValuesVectorConverter(layoutDirection: LayoutDirection):
        TwoWayConverter<PaddingValues, AnimationVector4D> = paddingValuesToVector(layoutDirection)

private fun paddingValuesToVector(layoutDirection: LayoutDirection):
        TwoWayConverter<PaddingValues, AnimationVector4D> = TwoWayConverter(
    convertToVector = { paddingValues ->
        AnimationVector4D(
            v1 = paddingValues.calculateLeftPadding(layoutDirection).value,
            v2 = paddingValues.calculateTopPadding().value,
            v3 = paddingValues.calculateRightPadding(layoutDirection).value,
            v4 = paddingValues.calculateBottomPadding().value
        )
    },
    convertFromVector = { vector4D ->
        PaddingValues(
            start = Dp(vector4D.v1),
            top = Dp(vector4D.v2),
            end = Dp(vector4D.v3),
            bottom = Dp(vector4D.v4)
        )
    }
)
