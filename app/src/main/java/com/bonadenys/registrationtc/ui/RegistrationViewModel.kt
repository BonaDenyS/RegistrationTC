package com.bonadenys.registrationtc.ui


import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bonadenys.registrationtc.data.ListProvince
import com.bonadenys.registrationtc.model.Address
import com.bonadenys.registrationtc.model.Review
import com.bonadenys.registrationtc.model.User
import com.bonadenys.registrationtc.service.APIService
import com.bonadenys.registrationtc.service.ConnectionManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegistrationViewModel : ViewModel() {

    private var provinceData: MutableLiveData<ListProvince> = MutableLiveData<ListProvince>()
    private var reviewData: MutableLiveData<Review> = MutableLiveData<Review>()

    var user: User? = null
    var address: Address? = null

    fun getReviewData(): MutableLiveData<Review> {
        val review = Review()
        review.user = user
        review.address = address
        reviewData.value = review
        return reviewData
    }
    fun getProvinceData(): MutableLiveData<ListProvince> {
        return provinceData
    }
    @SuppressLint("CheckResult")
    fun getDataProvinceList() {
        val request = ConnectionManager.setup().create(APIService::class.java)
        val observer = request.listProvince()
        try {
            observer.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    provinceData.value = result
                }, { error ->
                    Log.d("Error", error.localizedMessage!!)
                })

        } catch (e: Exception) {
            Log.d("Error", e.localizedMessage!!)
        }
    }

}