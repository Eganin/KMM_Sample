package mappers

import ktor.models.KtorSearchGame
import models.Game
import other.Mapper

internal class KtorSearchGameToGameMapper :Mapper<KtorSearchGame,Game> {
    override fun map(source: KtorSearchGame): Game {
        return Game(
            gameId = source.gameId,
            title = source.title
        )
    }
}