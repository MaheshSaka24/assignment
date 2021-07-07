package com.assignment.xento.model.books

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Epub(
    @SerializedName("isAvailable") val isAvailable: Boolean
): Serializable