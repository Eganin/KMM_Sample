package database

import com.squareup.sqldelight.db.SqlDriver
import platform.PlatformConfiguration

expect class DbDriverFactory(platformConfiguration: PlatformConfiguration) {
    fun createDriver(schema: SqlDriver.Schema, name: String): SqlDriver
}