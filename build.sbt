name         := "ditto"
organization := "ar.com.allaria.mas"
version      := "0.1.0-SNAPSHOT"
scalaVersion := "2.13.15"

lazy val ditto =
  (project in file("."))
    .enablePlugins(BuildInfoPlugin)


Compile / assembly / artifact := {
  val art = (Compile / assembly / artifact).value
  art.withClassifier(Some("assembly"))
}

addArtifact(Compile / assembly / artifact, assembly)

fork := true