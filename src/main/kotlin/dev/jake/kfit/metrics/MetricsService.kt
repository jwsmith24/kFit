package dev.jake.kfit.metrics

import dev.jake.kfit.di.BuildInfo
import java.time.Clock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MetricsService @Inject constructor(
    private val clock: Clock,
    private val buildInfo: BuildInfo
) {
    fun healthCheck(): HealthResponse =
        HealthResponse(
            status = "doing ok",
            time = clock.instant().toString(),
            version = buildInfo.version
        )
}