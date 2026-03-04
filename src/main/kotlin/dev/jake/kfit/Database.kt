package dev.jake.kfit

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import javax.sql.DataSource


fun createDataSource(): DataSource = HikariDataSource(HikariConfig().apply {
    jdbcUrl = "jdbc:postgresql://localhost:5435/kfit_db"
    username = "kfit"
    password = "kfit"
    driverClassName = "org.postgresql.Driver"
})

fun createDSLContext(dataSource: DataSource): DSLContext =
    DSL.using(dataSource, SQLDialect.POSTGRES)