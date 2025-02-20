val AWS: String            = "com.amazonaws"
val APACHE: String         = "org.apache"
val APACHE_COMMONS: String = s"$APACHE.commons"
val APACHE_POI: String     = s"$APACHE.poi"
val CIRCE: String          = "io.circe"
val DARUMA: String         = "daruma"
val HTTP4S: String         = "org.http4s"
val NRINAUDO: String       = "com.nrinaudo"
val LOGBACK: String        = "ch.qos.logback"
val TIMEPIT: String        = "eu.timepit"
val TYPE_LEVEL: String     = "org.typelevel"
val TYPE_SAFE: String      = "com.typesafe"
val SLF4J: String          = "org.slf4j"
val SOFTWAREMILL: String   = "com.softwaremill"
val OPTICS: String         = "dev.optics"
val PURECONFIG: String     = "com.github.pureconfig"
val RUIPPEIXOTOG: String   = "net.ruippeixotog"
val SENTRY: String         = "io.sentry"

val APACHE_COMMONS_EMAIL_VERSION: String = "1.5"
val APACHE_POI_VERSION: String           = "5.2.4"
val AWS_SNS_VERSION: String              = "1.12.765"
val DARUMA_COMMONS_VERSION: String       = "1.0.2"
val CATS_EFFECT_VERSION: String          = "3.5.1"
val CIRCE_VERSION: String                = "0.15.0-M1"
val CIRCE_GENERIC_EXTRAS_VERSION: String = "0.14.2"
val HTTP4S_VERSION: String               = "0.23.18"
val HTTP4S_BLAZE_VERSION: String         = "0.23.13"
val KANTAN_VERSION: String               = "0.7.0"
val MACWIRE_VERSION: String              = "2.5.8"
val MONOCLE_VERSION: String              = "3.1.0"
val LOGBACK_VERSION: String              = "1.2.6"
val OPEN_API_CIRCE_YAML_VERSION: String  = "0.2.1"
val POSTGRESQL_VERSION: String           = "42.5.0"
val PURECONFIG_VERSION: String           = "0.17.7"
val TAPIR_VERSION: String                = "1.1.0"
val SCALA_SCRAPPER_VERSION: String       = "3.0.0"
val SENTRY_VERSION: String               = "6.4.1"
val SLICK_VERSION: String                = "3.3.3"
val SLF4J_VERSION: String                = "1.7.36"
val STTP_CLIENT3_VERSION: String         = "3.8.9"
val SYNCHRO_VERSION: String              = "6.0.0"
val MONGO_DRIVER_VERSION                 = "5.3.1"

val AWS_SNS_DEPENDENCIES: Seq[ModuleID] = Seq[ModuleID](
  AWS % "aws-java-sdk-sns" % AWS_SNS_VERSION
)

val APACHE_COMMONS_DEPENDENCIES: Seq[ModuleID] = Seq[ModuleID](
  APACHE_COMMONS % "commons-email" % APACHE_COMMONS_EMAIL_VERSION excludeAll (ExclusionRule(
    "com.sun.activation"
  ), ExclusionRule(
    "javax.activation"
  ))
)

val APACHE_POI_DEPENDENCIES: Seq[ModuleID] = Seq[ModuleID](
  APACHE_POI % "poi"       % APACHE_POI_VERSION,
  APACHE_POI % "poi-ooxml" % APACHE_POI_VERSION
)

val CATS_EFFECT_DEPEDENCIES: Seq[ModuleID] = Seq[ModuleID](
  TYPE_LEVEL %% "cats-effect"                % CATS_EFFECT_VERSION,
  TYPE_LEVEL %% "cats-effect-kernel"         % CATS_EFFECT_VERSION,
  TYPE_LEVEL %% "cats-effect-std"            % CATS_EFFECT_VERSION,
  TYPE_LEVEL %% "cats-effect-testing-specs2" % "1.4.0" % Test,
  TYPE_LEVEL %% "munit-cats-effect-3"        % "1.0.7" % Test
)

val CIRCE_DEPENDENCIES: Seq[ModuleID] = Seq[ModuleID](
  s"${CIRCE}" %% "circe-core"           % CIRCE_VERSION,
  s"${CIRCE}" %% "circe-generic"        % CIRCE_VERSION,
  s"${CIRCE}" %% "circe-generic-extras" % CIRCE_GENERIC_EXTRAS_VERSION,
  s"${CIRCE}" %% "circe-parser"         % CIRCE_VERSION
)

val COMMONS_DEPEDENCIES: Seq[ModuleID] = Seq[ModuleID](
  DARUMA %% "daruma-commons" % DARUMA_COMMONS_VERSION
    excludeAll (
      ExclusionRule(organization = LOGBACK),                         // use specific version in this project
      ExclusionRule(organization = s"${SOFTWAREMILL}.macwire"),      // use specific version in this project
      ExclusionRule(organization = s"${SOFTWAREMILL}.sttp.apispec"), // use specific version in this project
      ExclusionRule(organization = s"${SOFTWAREMILL}.sttp.client3"), // use specific version in this project
      ExclusionRule(organization = s"${SOFTWAREMILL}.sttp.tapir"),   // use specific version in this project
      ExclusionRule(organization = PURECONFIG),                      // use specific version in this project
      ExclusionRule(organization = SLF4J),                           // use specific version in this project,
      ExclusionRule(organization = "com.google.firebase"),           // use specific version in this project
      ExclusionRule(organization = "com.google.cloud"),              // use specific version in this project
      ExclusionRule(organization = "com.google.inject"),             // use specific version in this project
      ExclusionRule(organization = "com.dripower"),                  // use specific version in this project
    )
)

val DATABASE_DEPENDENCIES: Seq[ModuleID] = Seq[ModuleID](
  s"${TYPE_SAFE}.slick" %% "slick"          % SLICK_VERSION,
  s"${TYPE_SAFE}.slick" %% "slick-hikaricp" % SLICK_VERSION,
  "org.postgresql"       % "postgresql"     % POSTGRESQL_VERSION
)

val MONGO_DRIVER = Seq[ModuleID](
  "org.mongodb.scala" %% "mongo-scala-driver" % MONGO_DRIVER_VERSION
)

val HEDWIG_DEPENDENCIES: Seq[ModuleID] = Seq(
  "daruma" %% "sdk-hedwig" % "0.1.5" excludeAll (ExclusionRule(organization =
    LOGBACK
  ),                                                             // use specific version in this project
  ExclusionRule(organization = s"${SOFTWAREMILL}.macwire"),      // use specific version in this project
  ExclusionRule(organization = s"${SOFTWAREMILL}.sttp.apispec"), // use specific version in this project
  ExclusionRule(organization = s"${SOFTWAREMILL}.sttp.client3"), // use specific version in this project
  ExclusionRule(organization = s"${SOFTWAREMILL}.sttp.tapir"),   // use specific version in this project
  ExclusionRule(organization = PURECONFIG),                      // use specific version in this project
  ExclusionRule(organization = SLF4J),                           // use specific version in this project,
  ExclusionRule(organization = "com.google.firebase"),           // use specific version in this project
  ExclusionRule(organization = "com.google.cloud"),              // use specific version in this project
  ExclusionRule(organization = "com.google.inject"),             // use specific version in this project
  ExclusionRule(organization = "com.dripower"),
  ExclusionRule(organization = "com.typesafe.play"))
)

val HTTP4S_DEPENDENCIES: Seq[ModuleID] = Seq[ModuleID](
  HTTP4S %% "http4s-dsl"          % HTTP4S_VERSION,
  HTTP4S %% "http4s-blaze-server" % HTTP4S_BLAZE_VERSION,
  HTTP4S %% "http4s-blaze-client" % HTTP4S_BLAZE_VERSION
)

val KANTAN_DEPENDENCIES: Seq[ModuleID] = Seq[ModuleID](
  NRINAUDO %% "kantan.csv"         % KANTAN_VERSION,
  NRINAUDO %% "kantan.csv-generic" % KANTAN_VERSION
)

val MACWIRE_DEPENDENCIES: Seq[ModuleID] = Seq[ModuleID](
  s"${SOFTWAREMILL}.macwire" %% "macros"     % MACWIRE_VERSION,
  s"${SOFTWAREMILL}.macwire" %% "macrosakka" % MACWIRE_VERSION,
  s"${SOFTWAREMILL}.macwire" %% "util"       % MACWIRE_VERSION,
  s"${SOFTWAREMILL}.macwire" %% "proxy"      % MACWIRE_VERSION
)

val MONOCLE_DEPENDENCIES: Seq[ModuleID] = Seq[ModuleID](
  s"${OPTICS}" %% "monocle-core"  % MONOCLE_VERSION,
  s"${OPTICS}" %% "monocle-law"   % MONOCLE_VERSION,
  s"${OPTICS}" %% "monocle-macro" % MONOCLE_VERSION
)

val PURECONFIG_DEPENDENCIES: Seq[ModuleID] = Seq[ModuleID](
  PURECONFIG %% "pureconfig" % PURECONFIG_VERSION
)

val SCALA_SCRAPPER_DEPENDENCIES: Seq[ModuleID] = Seq[ModuleID](
  s"$RUIPPEIXOTOG" %% "scala-scraper" % SCALA_SCRAPPER_VERSION
)

val SENTRY_DEPENDENCIES: Seq[ModuleID] = Seq[ModuleID](
  s"${SENTRY}" % "sentry"         % SENTRY_VERSION,
  s"${SENTRY}" % "sentry-logback" % SENTRY_VERSION
)

val SLF4J_DEPENDENCIES: Seq[ModuleID] = Seq[ModuleID](
  SLF4J   % "slf4j-api"       % SLF4J_VERSION,
  LOGBACK % "logback-classic" % LOGBACK_VERSION
)

val SYNCHRO_DEPENDENCIES: Seq[ModuleID] = Seq[ModuleID](
  DARUMA %% "synchro" % SYNCHRO_VERSION
)

val TAPIR_DEPENDENCIES: Seq[ModuleID] = Seq[ModuleID](
  s"${SOFTWAREMILL}.sttp.client3" %% "core"                % STTP_CLIENT3_VERSION,
  s"${SOFTWAREMILL}.sttp.client3" %% "circe"               % STTP_CLIENT3_VERSION,
  s"${SOFTWAREMILL}.sttp.apispec" %% "openapi-circe-yaml"  % OPEN_API_CIRCE_YAML_VERSION,
  s"${SOFTWAREMILL}.sttp.tapir"   %% "tapir-core"          % TAPIR_VERSION,
  s"${SOFTWAREMILL}.sttp.tapir"   %% "tapir-play-server"   % TAPIR_VERSION,
  s"${SOFTWAREMILL}.sttp.tapir"   %% "tapir-openapi-docs"  % TAPIR_VERSION,
  s"${SOFTWAREMILL}.sttp.tapir"   %% "tapir-http4s-server" % TAPIR_VERSION,
  s"${SOFTWAREMILL}.sttp.tapir"   %% "tapir-json-circe"    % TAPIR_VERSION,
  s"${SOFTWAREMILL}.sttp.tapir"   %% "tapir-swagger-ui"    % TAPIR_VERSION
)

// Dependency injection.
libraryDependencies ++= AWS_SNS_DEPENDENCIES
libraryDependencies ++= APACHE_COMMONS_DEPENDENCIES
libraryDependencies ++= APACHE_POI_DEPENDENCIES
libraryDependencies ++= CATS_EFFECT_DEPEDENCIES
libraryDependencies ++= CIRCE_DEPENDENCIES
libraryDependencies ++= COMMONS_DEPEDENCIES
libraryDependencies ++= DATABASE_DEPENDENCIES
libraryDependencies ++= HEDWIG_DEPENDENCIES
libraryDependencies ++= HTTP4S_DEPENDENCIES
libraryDependencies ++= KANTAN_DEPENDENCIES
libraryDependencies ++= MACWIRE_DEPENDENCIES
libraryDependencies ++= MONOCLE_DEPENDENCIES
libraryDependencies ++= PURECONFIG_DEPENDENCIES
libraryDependencies ++= TAPIR_DEPENDENCIES
libraryDependencies ++= SCALA_SCRAPPER_DEPENDENCIES
libraryDependencies ++= SENTRY_DEPENDENCIES
libraryDependencies ++= SLF4J_DEPENDENCIES
libraryDependencies ++= SYNCHRO_DEPENDENCIES
libraryDependencies ++= MONGO_DRIVER
