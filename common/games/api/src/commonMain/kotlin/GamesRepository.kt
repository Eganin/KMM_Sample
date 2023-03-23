import models.CreateGameInfo
import models.Game

interface GamesRepository {
    suspend fun fetchAllGames(): List<Game>
    suspend fun searchGame(query: String): List<Game>
    suspend fun createGame(token: String,info: CreateGameInfo)
}