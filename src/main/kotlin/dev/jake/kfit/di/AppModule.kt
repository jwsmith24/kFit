package dev.jake.kfit.di

import dagger.Module
import dagger.Provides
import java.time.Clock
import javax.inject.Singleton

// used to provide custom implementations of dependencies we don't control (can't add @Inject constructor() )
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideClock(): Clock = Clock.systemUTC()

    @Provides
    @Singleton
    fun provideBuildInfo(): BuildInfo = BuildInfo(version = VERSION)

    private const val VERSION = "1.0-SNAPSHOT"
}

data class BuildInfo(val version: String)