import ktor.KtorGamesDataSource
import mappers.GAMEDBToGameMapper
import models.CreateGameInfo
import models.Game
import sqldelight.SqlDelightGamesDataSource

internal class GamesRepositoryImpl(
    private val remoteDataSource: KtorGamesDataSource,
    private val localDataSource: SqlDelightGamesDataSource,
    private val gamedbToGameMapper: GAMEDBToGameMapper
) : GamesRepository {

    override suspend fun fetchAllGames(): List<Game> {
        val localGames = localDataSource.fetchLocalGames().map {
            gamedbToGameMapper.map(source = it)
        }

        return localGames.ifEmpty {
            val remote = remoteDataSource.fetchAllGames()
            remote.forEach {
                localDataSource.insertGame(game = it)
            }
            localDataSource.fetchLocalGames().map {
                gamedbToGameMapper.map(source = it)
            }
        }
    }

    override suspend fun searchGame(query: String): List<Game> {
        val localGames = localDataSource.fetchLocalQueryGames(query = query).map {
            gamedbToGameMapper.map(source = it)
        }
        return localGames.ifEmpty {
            val remote = remoteDataSource.searchGame(query = query)
            remote.forEach {
                localDataSource.insertGame(game = it)
            }
            localDataSource.fetchLocalQueryGames(query = query).map {
                gamedbToGameMapper.map(source = it)
            }
        }
    }

    override suspend fun createGame(token: String, info: CreateGameInfo) {
        return remoteDataSource.createGame(info = info, token = token)
    }
}