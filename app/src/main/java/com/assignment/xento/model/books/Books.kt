package com.assignment.xento.model.books

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Books(
    @SerializedName("kind") val kind: String,
    @SerializedName("totalItems") val totalItems: Int,
    @SerializedName("items") val items: List<BooksItem>
): Serializable