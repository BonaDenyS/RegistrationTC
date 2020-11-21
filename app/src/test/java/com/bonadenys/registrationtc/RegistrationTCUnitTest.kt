package com.bonadenys.registrationtc

import com.bonadenys.registrationtc.dropdownlist.Education
import com.bonadenys.registrationtc.dropdownlist.HousingType
import com.bonadenys.registrationtc.model.Address
import com.bonadenys.registrationtc.model.User
import com.bonadenys.registrationtc.util.DateFormat
import com.bonadenys.registrationtc.util.FieldChecker
import org.junit.Test

import org.junit.Assert.*
import java.text.ParseException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@Suppress("TestFunctionName")
class RegistrationTCUnitTest {
    @Test
    fun DropdownlistTest() {
        val education = arrayOf("SD", "SMP", "SMA", "S1", "S2", "S3")
        Education.values().forEach {
            assertSame("Nice it's same", education[it.ordinal], it.name)
        }

        val housingType = arrayOf("Rumah", "Kantor")
        HousingType.values().forEach {
            assertSame("Nice it's same", housingType[it.ordinal], it.name)
        }
    }

    @Test
    fun ModelsTest() {
        val user = User()
        user.education = Education.S1

        assertEquals(user.nationalId, String())
        assertEquals(user.fullName, String())
        assertEquals(user.education.toString(), "S1")
        assertEquals(user.dateOfBirth, String())

        val address = Address()
        address.housingType = HousingType.Rumah

        assertEquals(address.domicile, String())
        assertEquals(address.housingType.toString(), "Rumah")
        assertEquals(address.no, String())
        assertEquals(address.province, String())
    }

    @Test
    fun dateFormatConverTest() {
        try {
            val date = "20 November 2012"
            val converted = DateFormat.convert(date)
            assertEquals("20-11-2012", converted)
        } catch (e: ParseException) {
            assertEquals("Unparseable date: \"20 November 2012\"", e.message)
        }


        val date = "Nov 20, 2012"
        val converted = DateFormat.convert(date)
        assertEquals("20-11-2012", converted)


    }

    @Test
    fun FieldNationalIDCheckerTest() {
        // Positive Cases
        var field = "1234567890123456"
        var fc = FieldChecker.nationalIdChecker(field)
        assertTrue(fc.status)
        assertEquals("", fc.message)

        // Negative Cases
        field = "12345678901234562" // More than 16 digfields
        fc = FieldChecker.nationalIdChecker(field)
        assertFalse(fc.status)
        assertEquals("National ID should be 16 Characters", fc.message)

        field = "123456789012345" // Less than 16 digfields
        fc = FieldChecker.nationalIdChecker(field)
        assertFalse(fc.status)
        assertEquals("National ID should be 16 Characters", fc.message)
    }

    @Test
    fun FieldFullnameCheckerTest() {
        // Positive Cases
        var field = "Stuart"
        var fc = FieldChecker.fullname(field)
        assertTrue(fc.status)
        assertEquals("", fc.message)

        // Negative Cases
        field = "" // Empty Field
        fc = FieldChecker.fullname(field)
        assertFalse(fc.status)
        assertEquals("Fullname should not be empty", fc.message)
    }

    @Test
    fun FieldBankAccountCheckerTest() {
        // Positive Cases
        var field = "12345678"
        var fc = FieldChecker.bankAccount(field)
        assertTrue(fc.status)
        assertEquals("", fc.message)

        // Negative Cases
        field = "123" // Less than 8 digits
        fc = FieldChecker.bankAccount(field)
        assertFalse(fc.status)
        assertEquals("Bank Account should be at least 8 Characters", fc.message)
    }

    @Test
    fun FieldEducationCheckerTest() {
        // Positive Cases
        var field = "12345678"
        var fc = FieldChecker.education(field)
        assertTrue(fc.status)
        assertEquals("", fc.message)

        // Negative Cases
        field = "" // Empty Field
        fc = FieldChecker.education(field)
        assertFalse(fc.status)
        assertEquals("Education should not be empty", fc.message)
    }

    @Test
    fun FieldDateOfBirthCheckerTest() {
        // Positive Cases
        var field = "12345678"
        var fc = FieldChecker.dateOfBirth(field)
        assertTrue(fc.status)
        assertEquals("", fc.message)

        // Negative Cases
        field = "" // Empty Field
        fc = FieldChecker.dateOfBirth(field)
        assertFalse(fc.status)
        assertEquals("Date of birth should not be empty", fc.message)
    }

    @Test
    fun FieldDomicileAddressCheckerTest() {
        // Positive Cases
        var field = "12345678"
        var fc = FieldChecker.domicile(field)
        assertTrue(fc.status)
        assertEquals("", fc.message)

        // Negative Cases
        field = "" // Empty Field
        fc = FieldChecker.domicile(field)
        assertFalse(fc.status)
        assertEquals("Domicile Address should not be empty", fc.message)
    }

    @Test
    fun FieldHousingTypeCheckerTest() {
        // Positive Cases
        var field = "12345678"
        var fc = FieldChecker.housingType(field)
        assertTrue(fc.status)
        assertEquals("", fc.message)

        // Negative Cases
        field = "" // Empty Field
        fc = FieldChecker.housingType(field)
        assertFalse(fc.status)
        assertEquals("Housing Type should not be empty", fc.message)
    }

    @Test
    fun FieldNoCheckerTest() {
        // Positive Cases
        var field = "IV"
        var fc = FieldChecker.no(field)
        assertTrue(fc.status)
        assertEquals("", fc.message)

        // Negative Cases
        field = "" // Empty Field
        fc = FieldChecker.no(field)
        assertFalse(fc.status)
        assertEquals("No should not be empty", fc.message)
    }

    @Test
    fun FieldProvinceCheckerTest() {
        // Positive Cases
        var field = "Jakarta"
        var fc = FieldChecker.province(field)
        assertTrue(fc.status)
        assertEquals("", fc.message)

        // Negative Cases
        field = "" // Empty Field
        fc = FieldChecker.province(field)
        assertFalse(fc.status)
        assertEquals("Province should not be empty", fc.message)
    }
}