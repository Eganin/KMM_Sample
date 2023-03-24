package database

import com.example.kmm_sample.Database
import io.ktor.http.content.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

internal val databaseModule = DI.Module("databaseModule") {
    bind<DbDriverFactory>() with singleton {
        DbDriverFactory(instance())
    }

    bind<Database>() with singleton {
        val driverFactory = instance<DbDriverFactory>()
        val driver = driverFactory.createDriver(Database.Schema, "playzone_db")

        //Database.Schema.create(driver)
        Database(driver)
    }
}