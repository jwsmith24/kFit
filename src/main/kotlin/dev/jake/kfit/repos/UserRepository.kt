package dev.jake.kfit.repos

import dev.jake.generated.tables.Users.USERS
import org.jooq.DSLContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val dsl: DSLContext) {

    fun findAll(): List<String> =
        dsl.select(USERS.ID, USERS.NAME)
            .from(USERS)
            // can pass the transform function directly into fetch as lambda
            .fetch { "${it[USERS.ID]}: ${it[USERS.NAME]}" }
}