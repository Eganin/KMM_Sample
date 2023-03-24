import ktor.KtorGamesDataSource
import mappers.GAMEDBToGameMapper
import mappers.KtorSearchGameToGameMapper
import org.kodein.di.*
import sqldelight.SqlDelightGamesDataSource

val gamesModule = DI.Module("gamesModule"){
    bind<GamesRepository>() with singleton {
        GamesRepositoryImpl(instance(),instance(),instance())
    }

    bind<KtorGamesDataSource>() with singleton {
        KtorGamesDataSource(instance())
    }

    bind<SqlDelightGamesDataSource>() with singleton{
        SqlDelightGamesDataSource(instance())
    }

    bind<GAMEDBToGameMapper>() with singleton {
        GAMEDBToGameMapper()
    }

    bind<KtorSearchGameToGameMapper>() with singleton {
        KtorSearchGameToGameMapper()
    }
}