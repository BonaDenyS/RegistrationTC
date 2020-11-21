package com.bonadenys.registrationtc

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bonadenys.registrationtc.dropdownlist.Education
import com.bonadenys.registrationtc.dropdownlist.HousingType
import com.bonadenys.registrationtc.ui.RegistrationActivity
import org.hamcrest.CoreMatchers

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RegistrationTCInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.bonadenys.registrationtc", appContext.packageName)
    }
    @Test
    fun fillingFormTest() {
        ActivityScenario.launch(RegistrationActivity::class.java)

        // Data Diri
        onView(withId(R.id.national_id))
            .check(ViewAssertions.matches(isCompletelyDisplayed()))
        onView(withId(R.id.national_id)).perform(
            typeTextIntoFocusedView("123456789012345"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.submit)).perform(click())
        Thread.sleep(2000)

        // It'll show the error because national_id less than 16
        onView(withId(R.id.national_id)).perform(replaceText(""))
        onView(withId(R.id.national_id)).perform(
            typeTextIntoFocusedView("12345678901234567"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.submit)).perform(click())
        Thread.sleep(2000)
        // It'll show the error because national_id more than 16

        onView(withId(R.id.national_id)).perform(replaceText(""))
        onView(withId(R.id.national_id)).perform(
            typeTextIntoFocusedView("1234567890123456"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.submit)).perform(click())
        Thread.sleep(2000)

        onView(withId(R.id.fullname))
            .check(ViewAssertions.matches(isCompletelyDisplayed()))
        onView(withId(R.id.fullname)).perform(click())
        Thread.sleep(2000)
        // It'll show the error because fullname is empty

        onView(withId(R.id.fullname)).perform(
            typeTextIntoFocusedView("John Doe"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.submit)).perform(click())
        Thread.sleep(2000)

        onView(withId(R.id.bank_account))
            .check(ViewAssertions.matches(isCompletelyDisplayed()))
        onView(withId(R.id.bank_account)).perform(click())
        onView(withId(R.id.bank_account)).perform(
            typeTextIntoFocusedView("8974268"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.submit)).perform(click())
        Thread.sleep(2000)
        // It'll show the error because bankAccount less than 8

        onView(withId(R.id.bank_account)).perform(replaceText(""))
        onView(withId(R.id.bank_account)).perform(
            typeTextIntoFocusedView("897426895"),
            closeSoftKeyboard()
        )
        Thread.sleep(2000)

        onView(withId(R.id.education))
            .check(ViewAssertions.matches(isCompletelyDisplayed()))
        onView(withId(R.id.education)).perform(click())
        Thread.sleep(1000)
        onView(withText(CoreMatchers.containsString(Education.S1.name)))
            .inRoot(RootMatchers.isPlatformPopup())
            .perform(click())
        onView(withId(R.id.education))
            .check(ViewAssertions.matches(withText("S1")))
        Thread.sleep(2000)

        onView(withId(R.id.date_of_birth))
            .perform(replaceText("20-07-1999"), closeSoftKeyboard())
        onView(withId(R.id.submit)).perform(click())
        Thread.sleep(2000)

        // Alamat KTP
        onView(withId(R.id.domicile))
            .check(ViewAssertions.matches(isCompletelyDisplayed()))
        onView(withId(R.id.domicile)).perform(click())
        Thread.sleep(2000)
        // It'll show the error because domicile is empty

        onView(withId(R.id.domicile)).perform(
            typeTextIntoFocusedView("Tapak Tuan"),
            closeSoftKeyboard()
        )
        Thread.sleep(2000)

        onView(withId(R.id.housing_type))
            .check(ViewAssertions.matches(isCompletelyDisplayed()))
        onView(withId(R.id.housing_type)).perform(click())
        Thread.sleep(1000)
        onView(withText(CoreMatchers.containsString(HousingType.Rumah.name)))
            .inRoot(RootMatchers.isPlatformPopup()).perform(
            click()
        )
        onView(withId(R.id.housing_type))
            .check(ViewAssertions.matches(withText("Rumah")))
        Thread.sleep(2000)

        onView(withId(R.id.no)).perform(click())
        onView(withId(R.id.no))
            .check(ViewAssertions.matches(isCompletelyDisplayed()))
        Thread.sleep(2000)
        // It'll show the error because domicile is empty

        onView(withId(R.id.no)).perform(
            typeTextIntoFocusedView("5 / VII"),
            closeSoftKeyboard()
        )

        onView(withId(R.id.province))
            .check(ViewAssertions.matches(isCompletelyDisplayed()))
        onView(withId(R.id.province)).perform(click())
        onView(withText(CoreMatchers.equalTo("Aceh")))
            .inRoot(RootMatchers.isPlatformPopup()).perform(click())
        onView(withId(R.id.province))
            .check(ViewAssertions.matches(withText("Aceh")))
        Thread.sleep(2000)
        onView(withId(R.id.submit)).perform(click())
        onView(withText("Province"))
            .perform(scrollTo())
            .check(ViewAssertions.matches(isDisplayed()))
        Thread.sleep(3000)
        onView(withId(R.id.submit)).perform(click())
        onView(withText("Province"))
            .perform(scrollTo())
            .check(ViewAssertions.matches(isDisplayed()))
        Thread.sleep(4000)
    }
}