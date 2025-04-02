package es.rlujancreations.auth.domain.validator

/**
 * Created by Raúl L.C. on 31/3/25.
 */

class UserDataValidator {
    fun isValidEmail(email: String): Boolean {
        val emailRegex =
            Regex(
                """^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\.)+[A-Za-z]{2,6}$""",
            )
        return emailRegex.matches(email)
    }

    fun isValidUsername(username: String): Boolean {
        val usernameRegex = Regex("""^[a-zA-Z0-9_.-]+$""")
        return usernameRegex.matches(username)
    }

    fun validatePassword(password: String): PasswordValidationState {
        val hasMinLength = password.length >= MIN_PASSWORD_LENGTH
        val hasDigit = password.any { it.isDigit() }
        val hasLowerCaseCharacter = password.any { it.isLowerCase() }
        val hasUpperCaseCharacter = password.any { it.isUpperCase() }

        return PasswordValidationState(
            hasMinLength = hasMinLength,
            hasNumber = hasDigit,
            hasLowerCaseCharacter = hasLowerCaseCharacter,
            hasUpperCaseCharacter = hasUpperCaseCharacter,
        )
    }

    companion object {
        const val MIN_PASSWORD_LENGTH = 8
    }
}
