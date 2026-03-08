package dev.jake.kfit.repos

import dev.jake.generated.Tables.USERS
import kotlinx.serialization.Serializable
import org.jooq.DSLContext
import javax.inject.Inject
import javax.inject.Singleton

@Serializable
data class User(val id: Int, val name: String)

@Singleton
class UserRepository @Inject constructor(private val dsl: DSLContext) {

    fun findAll(): List<User> =
        dsl.select(USERS.ID, USERS.NAME)
            .from(USERS)
            // can pass the transform function directly into fetch as lambda
            .fetch { User(it[USERS.ID], it[USERS.NAME]) }


    fun findById(id: Int): User? =
        dsl.selectFrom(USERS)
            .where(USERS.ID.eq(id))
            .fetchOne { User(it[USERS.ID], it[USERS.NAME])}
}