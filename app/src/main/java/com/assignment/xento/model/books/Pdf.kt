package com.assignment.xento.model.books

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pdf(
    @SerializedName("isAvailable") val isAvailable: Boolean,
    @SerializedName("acsTokenLink") val acsTokenLink: String
): Serializable