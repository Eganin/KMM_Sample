package ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import ktor.models.KtorSearchGame
import ktor.models.KtorSearchRequest
import models.CreateGameInfo
import models.Game

class KtorGamesDataSource(private val httpClient: HttpClient) {

    suspend fun fetchAllGames(): List<KtorSearchGame> {
        return httpClient.post {
            header("Bearer-Authorization", "bf8487ae-7d47-11ec-90d6-0242ac120003")

            url {
                path("games/search")
                setBody(KtorSearchRequest(searchQuery = ""))
            }
        }.body()
    }

    suspend fun searchGame(query: String): List<KtorSearchGame> {
        return httpClient.post {
            header("Bearer-Authorization", "bf8487ae-7d47-11ec-90d6-0242ac120003")

            url {
                path("games/search")
                setBody(KtorSearchRequest(searchQuery = query))
            }
        }.body()
    }

    suspend fun createGame(token:String,info: CreateGameInfo){
        httpClient.post {
            header("Bearer-Authorization", token)

            url {
                path("games/create")
                setBody(info)
            }
        }
    }
}