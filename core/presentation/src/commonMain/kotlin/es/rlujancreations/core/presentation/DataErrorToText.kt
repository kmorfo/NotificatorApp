package es.rlujancreations.core.presentation

import es.rlujancreations.core.domain.util.DataError
import notificatorapp.core.presentation.generated.resources.Res
import notificatorapp.core.presentation.generated.resources.all_data_synced
import notificatorapp.core.presentation.generated.resources.data_not_found
import notificatorapp.core.presentation.generated.resources.error_disk_full
import notificatorapp.core.presentation.generated.resources.error_email_password_incorrect
import notificatorapp.core.presentation.generated.resources.error_no_internet
import notificatorapp.core.presentation.generated.resources.error_payload_too_large
import notificatorapp.core.presentation.generated.resources.error_request_timeout
import notificatorapp.core.presentation.generated.resources.error_serialization
import notificatorapp.core.presentation.generated.resources.error_server_error
import notificatorapp.core.presentation.generated.resources.error_too_many_requests
import notificatorapp.core.presentation.generated.resources.error_unknown
import notificatorapp.core.presentation.generated.resources.invalid_token
import notificatorapp.core.presentation.generated.resources.unprocessable_entity
import notificatorapp.core.presentation.generated.resources.unsupported_media_type

/**
 * Created by Raúl L.C. on 4/4/25.
 */
fun DataError.asUiText(): UiText {
    return when (this) {
        DataError.Local.DISK_FULL ->
            UiText.StringResource(
                Res.string.error_disk_full,
            )

        DataError.Local.ALL_DATA_SYNCED ->
            UiText.StringResource(
                Res.string.all_data_synced,
            )

        DataError.Local.DATA_NOT_FOUND ->
            UiText.StringResource(
                Res.string.data_not_found,
            )

        DataError.Network.REQUEST_TIMEOUT ->
            UiText.StringResource(
                Res.string.error_request_timeout,
            )

        DataError.Network.TOO_MANY_REQUESTS ->
            UiText.StringResource(
                Res.string.error_too_many_requests,
            )

        DataError.Network.UNSUPPORTED_MEDIA_TYPE ->
            UiText.StringResource(
                Res.string.unsupported_media_type,
            )

        DataError.Network.NO_INTERNET ->
            UiText.StringResource(
                Res.string.error_no_internet,
            )

        DataError.Network.PAYLOAD_TOO_LARGE ->
            UiText.StringResource(
                Res.string.error_payload_too_large,
            )

        DataError.Network.SERVER_ERROR ->
            UiText.StringResource(
                Res.string.error_server_error,
            )

        DataError.Network.SERIALIZATION ->
            UiText.StringResource(
                Res.string.error_serialization,
            )

        DataError.Network.UNPROCESSABLE_ENTITY ->
            UiText.StringResource(
                Res.string.unprocessable_entity,
            )
        DataError.Network.UNAUTHORIZED->
            UiText.StringResource(
                Res.string.error_email_password_incorrect,
            )

        DataError.Network.TOKEN_EXPIRED -> UiText.StringResource(Res.string.invalid_token)

        else -> UiText.StringResource(Res.string.error_unknown)
    }
}
