package com.bonadenys.registrationtc.util

class FieldChecker {
    companion object {
        fun nationalIdChecker(it: String): ResponseFieldChecker {
            if (it.length != 16 || it.isEmpty()) {
                return ResponseFieldChecker(false, "National ID should be 16 Characters")
            }
            return ResponseFieldChecker(true, "")
        }

        fun fullname(it: String): ResponseFieldChecker {
            if (it.isEmpty() || it.length > 10) {
                return ResponseFieldChecker(false, "Fullname should not be empty")
            }
            return ResponseFieldChecker(true, "")
        }

        fun bankAccount(it: String): ResponseFieldChecker {
            if (it.isEmpty() || it.length < 8) {
                return ResponseFieldChecker(false, "Bank Account should be at least 8 Characters")
            }
            return ResponseFieldChecker(true, "")
        }

        fun education(it: String): ResponseFieldChecker {
            if (it.isEmpty()) {
                return ResponseFieldChecker(false, "Education should not be empty")
            }
            return ResponseFieldChecker(true, "")
        }

        fun dateOfBirth(it: String): ResponseFieldChecker {
            if (it.isEmpty()) {
                return ResponseFieldChecker(false, "Date of birth should not be empty")
            }
            return ResponseFieldChecker(true, "")
        }

        fun domicile(it: String): ResponseFieldChecker {
            if (it.isEmpty()) {
                return ResponseFieldChecker(false, "Domicile Address should not be empty")
            }
            return ResponseFieldChecker(true, "")
        }

        fun housingType(it: String): ResponseFieldChecker {
            if (it.isEmpty()) {
                return ResponseFieldChecker(false, "Housing Type should not be empty")
            }
            return ResponseFieldChecker(true, "")
        }

        fun no(it: String): ResponseFieldChecker {
            if (it.isEmpty()) {
                return ResponseFieldChecker(false, "No should not be empty")
            }
            return ResponseFieldChecker(true, "")
        }

        fun province(it: String): ResponseFieldChecker {
            if (it.isEmpty()) {
                return ResponseFieldChecker(false, "Province should not be empty")
            }
            return ResponseFieldChecker(true, "")
        }
    }
}

class ResponseFieldChecker(val status: Boolean, val message: String) {
}