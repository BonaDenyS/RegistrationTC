package com.bonadenys.registrationtc.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListProvince {
    @SerializedName("provinsi")
    @Expose
    var provinces: List<Province>? = null
}

class Province {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("nama")
    @Expose
    var nama: String = ""
}