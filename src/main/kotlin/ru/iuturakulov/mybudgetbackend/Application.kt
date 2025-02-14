package ru.iuturakulov.mybudgetbackend

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.iuturakulov.mybudgetbackend.database.configureDataBase
import ru.iuturakulov.mybudgetbackend.plugins.configureAuth
import ru.iuturakulov.mybudgetbackend.plugins.configureBasic
import ru.iuturakulov.mybudgetbackend.plugins.configureKoin
import ru.iuturakulov.mybudgetbackend.plugins.configureRequestValidation
import ru.iuturakulov.mybudgetbackend.plugins.configureRoute
import ru.iuturakulov.mybudgetbackend.plugins.configureStatusPage
import ru.iuturakulov.mybudgetbackend.plugins.configureSwagger

fun main() {
    val config = HoconApplicationConfig(ConfigFactory.load("application.conf"))
    val port = config.property("ktor.deployment.port").getString().toInt()
    val host = config.property("ktor.deployment.host").getString()
    embeddedServer(Netty, port = port, host = host) {
        configureDataBase()
        configureBasic()
        configureKoin()
        configureRequestValidation()
        configureAuth()
        configureSwagger()
        configureStatusPage()
        configureRoute()
    }.start(wait = true)
}
