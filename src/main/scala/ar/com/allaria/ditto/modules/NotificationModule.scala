package ar.com.allaria.ditto.modules


import ar.com.allaria.ditto.ApplicationModule
import com.softwaremill.macwire.wire
import daruma.commons.notifications.config.entities.DiscordConfig
import daruma.commons.notifications.{Discord => CommonsDiscordNotifier}

trait NotificationModule { self: ApplicationModule =>

  lazy val discordConfig: DiscordConfig =
    env.externalServices.notifications.discord

  lazy val discord: CommonsDiscordNotifier           = wire[CommonsDiscordNotifier]

}