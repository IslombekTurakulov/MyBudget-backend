package ru.iuturakulov.mybudgetbackend.models.user.body

import org.valiktor.functions.hasSize
import org.valiktor.functions.isEmail
import org.valiktor.functions.isNotNull
import org.valiktor.validate

data class ConfirmPassword(
    val email: String,
    val verificationCode: String,
    val newPassword: String
) {
    fun validation() {
        validate(this) {
            validate(ConfirmPassword::email).isNotNull().isEmail()
            validate(ConfirmPassword::verificationCode).isNotNull().hasSize(4, 6)
            validate(ConfirmPassword::newPassword).isNotNull().hasSize(8, 32)
        }
    }
}