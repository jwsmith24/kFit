package dev.jake.kfit.di

import dagger.Component
import dev.jake.kfit.metrics.MetricsService
import dev.jake.kfit.repos.UserRepository
import dev.jake.kfit.repos.WorkoutRepository
import org.jooq.DSLContext
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun metricsService(): MetricsService
    fun dataSource(): DataSource
    fun dslContext(): DSLContext
    fun userRepository(): UserRepository
    fun workoutRepository(): WorkoutRepository
}


object DI {
    val appComponent: AppComponent = DaggerAppComponent.create()
}