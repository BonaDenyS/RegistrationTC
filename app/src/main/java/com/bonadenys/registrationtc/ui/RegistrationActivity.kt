package com.bonadenys.registrationtc.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bonadenys.registrationtc.R
import com.bonadenys.registrationtc.databinding.ActivityRegistrationBinding
import com.bonadenys.registrationtc.databinding.FragmentIdentityBinding
import com.bonadenys.registrationtc.model.ResponseField
import com.bonadenys.registrationtc.model.User
import com.bonadenys.registrationtc.ui.forms.IdentityFragment
import com.google.android.material.snackbar.Snackbar

class RegistrationActivity : AppCompatActivity(), SubmitCallback {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var fragment: Fragment
    private lateinit var viewModel: RegistrationViewModel
    var timeline = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialView()
    }

    private fun initialView() {
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = RegistrationViewModel()
    }

    override fun onResume() {
        super.onResume()
        timelineView()
    }

    private fun timelineView() {
        fragment = Fragment()
        when (timeline) {
            0 -> {
                val identityFragment = IdentityFragment()
                identityFragment.setupCallback(this, binding.submit)
                fragment = identityFragment
                switchTimelineView(0)
            }
            1 -> {
                switchTimelineView(1)
            }
            else -> {
                switchTimelineView(2)
                binding.submit.visibility = View.GONE
            }
        }

        supportFragmentManager.beginTransaction().replace(binding.container.id, fragment).commit()
    }

    private fun switchTimelineView(timeline: Int) {
        when (timeline) {
            0 -> {
                timelineViewOff()
                binding.layoutTimeline.timelineBallLeft.background =
                    getDrawable(R.drawable.round_ball_blue)
                binding.layoutTimeline.timelineLabelLeft.setTextColor(getColor(R.color.colorBlue))
            }
            1 -> {
                timelineViewOff()
                binding.layoutTimeline.timelineBallMid.background =
                    getDrawable(R.drawable.round_ball_blue)
                binding.layoutTimeline.timelineLabelMid.setTextColor(getColor(R.color.colorBlue))
            }
            2 -> {
                timelineViewOff()
                binding.layoutTimeline.timelineBallRight.background =
                    getDrawable(R.drawable.round_ball_blue)
                binding.layoutTimeline.timelineLabelRight.setTextColor(getColor(R.color.colorBlue))
            }
        }
    }

    private fun timelineViewOff() {
        binding.layoutTimeline.timelineBallLeft.background = getDrawable(R.drawable.round_ball_gray)
        binding.layoutTimeline.timelineLabelLeft.setTextColor(getColor(R.color.colorGray))
        binding.layoutTimeline.timelineBallMid.background = getDrawable(R.drawable.round_ball_gray)
        binding.layoutTimeline.timelineLabelMid.setTextColor(getColor(R.color.colorGray))
        binding.layoutTimeline.timelineBallRight.background =
            getDrawable(R.drawable.round_ball_gray)
        binding.layoutTimeline.timelineLabelRight.setTextColor(getColor(R.color.colorGray))
    }

    override fun onDataDiriSubmit(
        binding: FragmentIdentityBinding,
        responseField: ResponseField,
        user: User
    ) {

        viewModel.user = user

        if (responseField.isFullField) {
            timeline++
            timelineView()
        } else {
            Snackbar.make(this.binding.container, responseField.message, Snackbar.LENGTH_SHORT)
                .show()
        }

    }
}