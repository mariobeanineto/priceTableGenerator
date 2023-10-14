package com.mbn.calculator.integration

import com.mbn.calculator.domain.integration.SelicResponse
import retrofit2.Call
import retrofit2.http.GET

interface BacenIntegration {

    @GET("/dados/serie/bcdata.sgs.4189/dados/ultimos/1?formato=json")
    fun getSelicRate(): Call<List<SelicResponse>>
}