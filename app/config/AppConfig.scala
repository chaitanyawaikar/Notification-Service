package config

import com.typesafe.config.ConfigFactory

object AppConfig {

  private lazy val configFile = "application.conf"
  private lazy val config = ConfigFactory.load(configFile)

  lazy val templateServiceUrl = config.getString("templateService.url")
  lazy val userServiceUrl = config.getString("userService.url")

}
