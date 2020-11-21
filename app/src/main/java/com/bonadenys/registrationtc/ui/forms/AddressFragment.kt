package com.bonadenys.registrationtc.ui.forms

import android.R
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bonadenys.registrationtc.data.ListProvince
import com.bonadenys.registrationtc.databinding.FragmentAddressBinding
import com.bonadenys.registrationtc.dropdownlist.HousingType
import com.bonadenys.registrationtc.model.Address
import com.bonadenys.registrationtc.model.ResponseField
import com.bonadenys.registrationtc.ui.SubmitCallback
import com.bonadenys.registrationtc.util.FieldChecker


class AddressFragment() : Fragment() {

    private var _binding: FragmentAddressBinding? = null
    private val binding get() = _binding!!
    private var address = Address()
    private var submit: Button? = null
    private var onSubmitCallback: SubmitCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initialView()
    }

    fun setupCallback(onSubmitCallback: SubmitCallback, submit: Button) {
        this.onSubmitCallback = onSubmitCallback
        this.submit = submit
    }

    fun setProvinceData(provinceData: MutableLiveData<ListProvince>) {
        provinceData.observe(this, Observer {
            val listProvince: ArrayList<String> = ArrayList()
            it.provinces?.forEach {
                listProvince.add(it.nama)
            }
            val adapter = ArrayAdapter<Any?>(
                context!!,
                R.layout.simple_spinner_dropdown_item,
                listProvince as List<Any?>
            )
            binding.province.keyListener = null
            binding.province.setAdapter(adapter)
            binding.province.setOnItemClickListener { _, _, position, _ ->
                address.province = listProvince[position]
            }

        })
    }

    private fun initialView(): View {
        _binding = FragmentAddressBinding.inflate(layoutInflater)

        binding.domicile.filters = arrayOf(InputFilter.LengthFilter(100))

        val adapter = ArrayAdapter<Any?>(
            context!!,
            R.layout.simple_spinner_dropdown_item,
            HousingType.values()
        )
        binding.housingType.keyListener = null
        binding.housingType.setAdapter(adapter)
        binding.housingType.setOnItemClickListener { _, _, position, _ ->
            address.housingType = HousingType.values()[position]
        }

        binding.no.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
            if (source == "") { // for backspace
                return@InputFilter source
            }

            if (source.toString().matches(Regex("[a-zA-Z 1234567890/,.]+"))) {
                source
            } else ""
        })

        submit?.setOnClickListener {
            onSubmitCallback?.onAlamatKTPSubmit(binding, isFullField(), address)
        }

        return binding.root
    }

    private fun isFullField(): ResponseField {

        binding.domicile.let {
            FieldChecker.domicile(it.text.toString()).let { fc ->
                if (!fc.status) {
                    it.error = fc.message
                    return ResponseField(false, fc.message)
                }
            }
            address.domicile = it.text.toString()
        }

        binding.housingType.let {
            FieldChecker.housingType(it.text.toString()).let { fc ->
                if (!fc.status) {
                    it.error = fc.message
                    return ResponseField(false, fc.message)
                }
            }
        }

        binding.no.let {
            FieldChecker.no(it.text.toString()).let { fc ->
                if (!fc.status) {
                    it.error = fc.message
                    return ResponseField(false, fc.message)
                }
            }
            address.no = it.text.toString()
        }

        binding.province.let {
            FieldChecker.dateOfBirth(it.text.toString()).let { fc ->
                if (!fc.status) {
                    it.error = fc.message
                    return ResponseField(false, fc.message)
                }
            }
        }

        return ResponseField(true, "")
    }
}