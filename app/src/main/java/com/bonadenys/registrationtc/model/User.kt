package com.bonadenys.registrationtc.model

import com.bonadenys.registrationtc.dropdownlist.Education

class User {
    var nationalId: String = ""
    var fullName: String = ""
    var bankAccount: String = ""
    var education: Education? = null
    var dateOfBirth: String = ""
}