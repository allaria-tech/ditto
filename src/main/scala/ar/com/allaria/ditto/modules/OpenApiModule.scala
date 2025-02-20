package ar.com.allaria.ditto.modules

import ar.com.allaria.ditto.ApplicationModule
import buildinfo.BuildInfo
import daruma.commons.api.ApiDocumentation
import sttp.apispec.openapi.Info

trait OpenApiModule { self: ApplicationModule =>

  private lazy val apiInfo: Info = Info(
    title = BuildInfo.name,
    version = "1.0.0",
    description = Some("Ditto API"),
    termsOfService = None,
    contact = None,
    license = None
  )

  lazy val apiDocumentation: ApiDocumentation = ApiDocumentation(
    apiInfo,
    apiServers,
    healthCheckEndpoints,
    versionEndpoints,
    apiEndpoints
  )
}
