package com.bonadenys.registrationtc.ui.forms

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.fragment.app.Fragment
import com.bonadenys.registrationtc.databinding.FragmentIdentityBinding
import com.bonadenys.registrationtc.model.ResponseField
import com.bonadenys.registrationtc.model.User
import com.bonadenys.registrationtc.ui.SubmitCallback
import com.bonadenys.registrationtc.dropdownlist.Education
import com.bonadenys.registrationtc.util.DateFormat
import com.bonadenys.registrationtc.util.FieldChecker
import com.google.android.material.datepicker.MaterialDatePicker

@Suppress("DEPRECATION")
class IdentityFragment : Fragment() {

    private var _binding: FragmentIdentityBinding? = null
    private val binding get() = _binding!!
    private var user = User()
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

    @SuppressLint("SimpleDateFormat")
    private fun initialView(): View {
        _binding = FragmentIdentityBinding.inflate(layoutInflater)

        binding.fullname.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
            if (source == "") { // for backspace
                return@InputFilter source
            }

            if (source.toString().matches(Regex("[a-zA-Z' ]+"))) {
                source
            } else ""
        }, LengthFilter(10))

        val adapter = ArrayAdapter<Any?>(context!!, R.layout.simple_spinner_dropdown_item, Education.values())
        binding.education.setAdapter(adapter)
        binding.education.setOnItemClickListener { _, _, position, _ ->
            user.education = Education.values()[position]
        }

        binding.dateOfBirth.setOnClickListener {
            val builder = MaterialDatePicker.Builder.datePicker()
            val picker = builder.build()
            picker.show(fragmentManager!!, picker.toString())
            picker.addOnPositiveButtonClickListener {
                user.dateOfBirth = DateFormat.convert(picker.headerText)
                binding.dateOfBirth.setText(user.dateOfBirth)
            }
        }

        submit?.setOnClickListener {
            onSubmitCallback?.onDataDiriSubmit(binding, isFullField(), user)
        }

        return binding.root
    }

    private fun isFullField(): ResponseField {

        binding.nationalId.let {
            FieldChecker.nationalIdChecker(it.text.toString()).let { fc ->
                if (!fc.status) {
                    it.error = fc.message
                    return ResponseField(false, it.error.toString())
                }
            }
            user.nationalId = it.text.toString()
        }

        binding.fullname.let {
            FieldChecker.fullname(it.text.toString()).let { fc ->
                if (!fc.status) {
                    it.error = fc.message
                    return ResponseField(false, fc.message)
                }
            }
            user.fullName = it.text.toString()
        }

        binding.bankAccount.let {
            FieldChecker.bankAccount(it.text.toString()).let { fc ->
                if (!fc.status) {
                    it.error = fc.message
                    return ResponseField(false, it.error.toString())
                }
            }
            user.bankAccount = it.text.toString()
        }

        binding.education.let {
            FieldChecker.education(it.text.toString()).let { fc ->
                if (!fc.status) {
                    it.error = fc.message
                    return ResponseField(false, it.error.toString())
                }
            }
        }

        binding.dateOfBirth.let {
            FieldChecker.dateOfBirth(it.text.toString()).let { fc ->
                if (!fc.status) {
                    it.error = fc.message
                    return ResponseField(false, it.error.toString())
                }
            }
            user.dateOfBirth = it.text.toString()
        }

        return ResponseField(true, "")
    }
}