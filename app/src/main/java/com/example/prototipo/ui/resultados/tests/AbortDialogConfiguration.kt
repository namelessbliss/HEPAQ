package com.example.prototipo.ui.resultados.tests

import androidx.annotation.StringRes

data class AbortDialogConfiguration(
        @StringRes val title: Int,
        @StringRes val message: Int,
        @StringRes val neutralMessage: Int,
        @StringRes val negativeMessage: Int
)