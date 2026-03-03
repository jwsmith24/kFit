package dev.jake.kfit.di

import dagger.Component
import dev.jake.kfit.metrics.MetricsService
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun metricsService(): MetricsService
}