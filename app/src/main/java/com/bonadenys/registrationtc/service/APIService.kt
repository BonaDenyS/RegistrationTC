package com.bonadenys.registrationtc.service

import com.bonadenys.registrationtc.data.ListProvince
import io.reactivex.Observable
import retrofit2.http.GET

interface APIService {
    //ListProvince
    @GET("daerahindonesia/provinsi")
    fun listProvince(): Observable<ListProvince>
}