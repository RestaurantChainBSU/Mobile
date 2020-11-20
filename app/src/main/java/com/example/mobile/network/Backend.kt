package com.example.mobile.network

import com.example.mobile.network.dishes.Dishes
import com.example.mobile.network.restaraunts.Restaraunts
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.*

interface Backend {
    @GET("restaurants")
    fun getRestaraunts(): Deferred<Restaraunts>

    @GET("dishes/{id}")
    fun getDishes(@Path("id") id: Int): Deferred<Dishes>

    @GET("users/{login}/{passw}")
    fun login(@Path("login") login: String, @Path("passw") passw: String): Deferred<Login>

    @POST("users/")
    fun signUp(@Body request: SignUp): Deferred<Login>
}