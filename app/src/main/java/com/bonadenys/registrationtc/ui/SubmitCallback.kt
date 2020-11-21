package com.bonadenys.registrationtc.ui

import com.bonadenys.registrationtc.databinding.FragmentAddressBinding
import com.bonadenys.registrationtc.databinding.FragmentIdentityBinding
import com.bonadenys.registrationtc.model.Address
import com.bonadenys.registrationtc.model.ResponseField
import com.bonadenys.registrationtc.model.User

interface SubmitCallback {
    fun onDataDiriSubmit(binding: FragmentIdentityBinding, responseField: ResponseField, user: User)
    fun onAlamatKTPSubmit(
        binding: FragmentAddressBinding,
        responseField: ResponseField,
        address: Address
    )
}