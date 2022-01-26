package com.msd117.cryptocompose.theme.ui.shared

data class SharedElementInfo(val tag: String, val type: SharedElementType) {

    enum class SharedElementType { From, To }
}