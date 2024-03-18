package com.ifs21041.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dino(
    var icon: String,
    var famili: Int,
    var desc: String,
    var period: String,
    var characteristic: String,
    var habitat: String,
    var lingkungan: String,
    var perilaku: String,
    var klasifikasi: String,
) : Parcelable