package `in`.ac.themuviedb.model

import com.google.gson.annotations.SerializedName
import java.util.ArrayList


data class MuvieDetailModel(
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val results: List<Result>
)