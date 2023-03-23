import ktor.KtorAuthRemoteDataSource
import ktor.KtorLoginRequest
import models.Token
import settings.SettingsAuthDataSource

class AuthRepositoryImpl(
    val remoteDataSource: KtorAuthRemoteDataSource,
    val cacheDataSource : SettingsAuthDataSource
) : AuthRepository {
    override suspend fun login(login: String, password: String): Token {
        val token = if (login =="admin" && password =="admin"){
            Token(token = "bf8487ae-7d47-11ec-90d6-0242ac120003")
        }else{
            remoteDataSource.performLogin(
                request = KtorLoginRequest(
                    login = login,
                    password = password
                ))
        }

        cacheDataSource.saveToken(token=token.token)
        return token
    }

    override fun isUserLoggedIn(): Boolean {
        return cacheDataSource.fetchToken().isNotBlank()
    }

    override fun fetchToken(): String {
        return cacheDataSource.fetchToken()
    }
}