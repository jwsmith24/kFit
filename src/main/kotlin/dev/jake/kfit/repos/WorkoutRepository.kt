package dev.jake.kfit.repos

import dev.jake.generated.Tables.EXERCISES
import dev.jake.kfit.models.Exercise
import org.jooq.DSLContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WorkoutRepository @Inject constructor(private val dsl: DSLContext) {

    fun findAllExercises(): List<Exercise> =
        dsl.selectFrom(EXERCISES)
            .fetch { Exercise(id = it[EXERCISES.ID], name = it[EXERCISES.NAME]) }

}