import ktor.KtorGamesDataSource
import ktor.models.mapToGame
import models.CreateGameInfo
import models.Game
import sqldelight.SqlDelightGamesDataSource

class GamesRepositoryImpl(
    private val remoteDataSource: KtorGamesDataSource,
    private val localDataSource: SqlDelightGamesDataSource
) : GamesRepository {

    override suspend fun fetchAllGames(): List<Game> {
        val localGames = localDataSource.fetchLocalGames().map {
            Game(
                gameId = it.game_id,
                title = it.game_title
            )
        }

        return if (localGames.isNotEmpty()) {
            localGames
        } else {
            val remote = remoteDataSource.fetchAllGames()
            remote.forEach {
                localDataSource.insertGame(game = it)
            }
            remote.map { it.mapToGame() }
        }
    }

    override suspend fun searchGame(query: String): List<Game> {
        return remoteDataSource.searchGame(query).map { it.mapToGame() }
    }

    override suspend fun createGame(token: String, info: CreateGameInfo) {
        return remoteDataSource.createGame(info = info, token = token)
    }
}