package dev.jake.kfit.repos

import dev.jake.kfit.generated.Tables.EXERCISES
import dev.jake.kfit.generated.Tables.USER_LIFT_HISTORY
import dev.jake.kfit.models.Exercise
import dev.jake.kfit.models.LiftHistoryDTO
import org.jooq.DSLContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WorkoutRepository @Inject constructor(private val dsl: DSLContext) {

    fun findAllExercises(): List<Exercise> =
        dsl.selectFrom(EXERCISES)
            .fetch { Exercise(id = it[EXERCISES.ID], name = it[EXERCISES.NAME]) }


    fun findLiftHistoryByUserId(userId: Int): List<LiftHistoryDTO> =
        dsl.selectFrom(USER_LIFT_HISTORY)
            .where(USER_LIFT_HISTORY.USER_ID.eq(userId))
            .fetch {
                println("$it")
                LiftHistoryDTO(
                    exerciseName = it.exercise,
                    reps = it.reps,
                    weight = it.weight
                )
            }

}