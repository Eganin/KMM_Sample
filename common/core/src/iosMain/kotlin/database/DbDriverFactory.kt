package database

import com.squareup.sqldelight.db.SqlDriver
import platform.PlatformConfiguration
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DbDriverFactory actual constructor(private val platformConfiguration: PlatformConfiguration) {
    actual fun createDriver(schema: SqlDriver.Schema, name: String): SqlDriver {
        return NativeSqliteDriver(schema,name)
    }
}