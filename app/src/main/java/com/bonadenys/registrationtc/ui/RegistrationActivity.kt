package com.bonadenys.registrationtc.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bonadenys.registrationtc.R
import com.bonadenys.registrationtc.databinding.ActivityRegistrationBinding
import com.google.android.material.snackbar.Snackbar

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    var timeline = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialView()
    }

    private fun initialView() {
        binding = ActivityRegistrationBinding.inflate(layoutInflater)

        binding.submit.setOnClickListener {
            timeline++
            timelineView()
        }

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        timelineView()
    }

    private fun timelineView() {

        when (timeline) {
            0 -> {
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
}