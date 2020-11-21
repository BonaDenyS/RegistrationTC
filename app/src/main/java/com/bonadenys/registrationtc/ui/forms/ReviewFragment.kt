package com.bonadenys.registrationtc.ui.forms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bonadenys.registrationtc.R
import com.bonadenys.registrationtc.databinding.FragmentReviewBinding
import com.bonadenys.registrationtc.model.Review
import com.bonadenys.registrationtc.ui.SubmitCallback


class ReviewFragment: Fragment() {

    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!
    private var submit: Button? = null
    private var onSubmitCallback: SubmitCallback? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return initialView()
    }

    fun setupCallback(onSubmitCallback: SubmitCallback, submit: Button) {
        this.onSubmitCallback = onSubmitCallback
        this.submit = submit
    }

    private fun initialView(): View {
        _binding = FragmentReviewBinding.inflate(layoutInflater)

        submit?.setOnClickListener {
            onSubmitCallback?.onReviewSubmit()
        }

        return binding.root
    }

    fun setDataReview(reviewData: MutableLiveData<Review>) {
        reviewData.observe(this, Observer {
            binding.nationalId.text = it.user?.nationalId
            binding.fullname.text = it.user?.fullName
            binding.bankAccount.text = it.user?.bankAccount
            binding.education.text = it.user?.education?.name
            binding.dateOfBirth.text = it.user?.dateOfBirth

            binding.domicile.text = it.address?.domicile
            binding.housingType.text = it.address?.housingType?.name
            binding.no.text = it.address?.no
            binding.province.text = it.address?.province
        })
    }

}