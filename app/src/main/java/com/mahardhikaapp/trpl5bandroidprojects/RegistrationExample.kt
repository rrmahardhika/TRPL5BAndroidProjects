package com.mahardhikaapp.trpl5bandroidprojects

object RegistrationExample {
    private val existingUsers = listOf("Peter", "Mathew")
    fun validateRegistrationInput(
        userName: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (userName.isEmpty() || password.isEmpty()) {
            return false
        }
        if (userName in existingUsers) {
            return false
        }
        if (password != confirmPassword) {
            return false
        }
        if (password.count { it.isDigit() } < 2) {
            return false
        }

        return true
    }
}