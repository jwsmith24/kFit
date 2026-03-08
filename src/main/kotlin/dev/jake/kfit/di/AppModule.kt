package dev.jake.kfit.di

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dagger.Module
import dagger.Provides
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import java.time.Clock
import javax.inject.Singleton
import javax.sql.DataSource

// used to provide custom implementations of dependencies we don't control (can't add @Inject constructor() )
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideClock(): Clock = Clock.systemUTC()

    @Provides
    @Singleton
    fun provideBuildInfo(): BuildInfo = BuildInfo(version = VERSION)

    @Provides
    @Singleton
    fun provideDataSource(): DataSource = HikariDataSource(HikariConfig().apply {
        jdbcUrl = "jdbc:postgresql://localhost:5435/kfit_db"
        username = "kfit"
        password = "kfit"
        driverClassName = "org.postgresql.Driver"
    })

    @Provides
    @Singleton
    fun provideDSLContext(dataSource: DataSource): DSLContext =
        DSL.using(dataSource, SQLDialect.POSTGRES)

    private const val VERSION = "1.0-SNAPSHOT"
}

data class BuildInfo(val version: String)