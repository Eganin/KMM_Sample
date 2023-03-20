package settings

import com.russhwolf.settings.Settings

class SettingsAuthDataSource(
    private val settings: Settings
) {

    fun saveToken(token: String) {
        settings.putString(key = tokenKey, value = token)
    }

    fun fetchToken(): String =
        settings.getString(key = tokenKey, defaultValue = "")

    private companion object {
        val tokenKey = "tokenKey"
    }
}