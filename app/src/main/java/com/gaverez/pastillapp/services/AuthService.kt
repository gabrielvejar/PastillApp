package com.gaverez.pastillapp.services

import com.gaverez.pastillapp.models.LoginPayloadDTO
import com.gaverez.pastillapp.models.LoginResponseDTO
import com.gaverez.pastillapp.models.RegisterPayloadDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/auth/local")
    fun login(@Body payload: LoginPayloadDTO): Call<LoginResponseDTO>

    @POST("/api/auth/local/register")
    fun register(@Body payload: RegisterPayloadDTO): Call<Void>
}