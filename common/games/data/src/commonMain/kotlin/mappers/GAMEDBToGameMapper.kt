package mappers

import databases.GAMEDB
import models.Game
import other.Mapper

internal class GAMEDBToGameMapper : Mapper<GAMEDB,Game> {
    override fun map(source: GAMEDB): Game {
        return Game(
            gameId = source.game_id,
            title = source.game_title
        )
    }
}