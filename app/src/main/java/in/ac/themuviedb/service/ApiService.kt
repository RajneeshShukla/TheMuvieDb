package `in`.ac.themuviedb.service

import `in`.ac.themuviedb.model.MuvieDetailModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/3/discover/movie")
    fun getMovies(
        @Query("api_key") apiKey :String = "d3dfa3a49d0d5a69e854d59a5c120173",
        @Query("language") language : String = "en-US",
        @Query("sort_by") sortBy : String = "popularity.desc",
        @Query ("include_adult") inCludeAdault : Boolean = false,
        @Query ("include_video") includeVideo : Boolean = false,
        @Query ("page") page : Int = 1
    ) : Call<MuvieDetailModel>

    @GET("/3/discover/tv")
    fun getTvShows(
        @Query("api_key") apiKey :String = "d3dfa3a49d0d5a69e854d59a5c120173",
        @Query("language") language : String = "en-US",
        @Query("sort_by") sortBy : String = "popularity.desc",
        @Query ("page") page : Int = 1
    ) : Call<MuvieDetailModel>

}