package es.rlujancreations.auth.domain.validator

/**
 * Created by Raúl L.C. on 31/3/25.
 */
data class PasswordValidationState(
    val hasMinLength: Boolean = false,
    val hasNumber: Boolean = false,
    val hasLowerCaseCharacter: Boolean = false,
    val hasUpperCaseCharacter: Boolean = false,
) {
    val isValidPassword: Boolean
        get() = hasMinLength && hasNumber && hasLowerCaseCharacter && hasUpperCaseCharacter
}
