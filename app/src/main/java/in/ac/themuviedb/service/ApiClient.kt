package www.mindfire.thenews.service

import `in`.ac.themuviedb.service.ApiConstant
import `in`.ac.themuviedb.service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    fun create(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConstant().BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}