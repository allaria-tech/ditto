assembly / mainClass       := Some("ar.com.allaria.ditto.Application")
assembly / assemblyJarName := "application.jar"

val protobuf: Seq[String] = Seq(
  "any.proto",
  "api.proto",
  "descriptor.proto",
  "duration.proto",
  "empty.proto",
  "field_mask.proto",
  "timestamp.proto",
  "type.proto",
  "plugin.proto",
  "source_context.proto",
  "struct.proto",
  "wrappers.proto"
)

val blacklistedFiles: Seq[String] = Seq()

val onceFiles: Seq[String] = Seq()

val concatenatedFiles = Seq(
  "reference-overrides.conf",
  "io.netty.versions.properties",
  "native-image.properties",
  "reflection-config.json"
) ++ protobuf

assembly / assemblyMergeStrategy := {
  case manifest: String if manifest.contains("swagger-ui")        => MergeStrategy.first
  case manifest: String if manifest.contains("module-info.class") => MergeStrategy.first
  case manifest: String if manifest.contains("MANIFEST.MF")       => MergeStrategy.discard
  case PathList("org", "apache", "commons", "logging", _*)        => MergeStrategy.first
  case PathList(ps @ _*) if blacklistedFiles.contains(ps.last)    => MergeStrategy.discard
  case PathList(ps @ _*) if onceFiles.contains(ps.last)           => MergeStrategy.first
  case PathList(ps @ _*) if concatenatedFiles.contains(ps.last)   => MergeStrategy.concat
  case x: String                                                  =>
    val oldStrategy = (assembly / assemblyMergeStrategy).value
    oldStrategy(x)
}

assembly / test                  := {}
