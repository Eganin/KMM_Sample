package sqldelight

import com.example.kmm_sample.Database
import databases.GAMEDB
import ktor.models.KtorSearchGame
import models.Game

internal class SqlDelightGamesDataSource(
    private val database: Database
) {

    fun fetchLocalGames(): List<GAMEDB> {
        return database.gameQueries.getAllGames().executeAsList()
    }

    fun fetchLocalQueryGames(query: String): List<GAMEDB>{
        return database.gameQueries.getAllGamesOnRequest(query = "%$query%").executeAsList()
    }

    fun insertGame(game: KtorSearchGame) {
        database.gameQueries.insertGame(
            game_id = game.gameId,
            game_title = game.title,
            game_size = game.size,
            game_version = game.version
        )
    }
}