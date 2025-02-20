
resolvers += "Daruma Nexus" at "https://nexus.midaruma.cloud/repository/maven-public/"
externalResolvers := Resolver.combineDefaultResolvers(resolvers.value.toVector, mavenCentral = false)

publishTo := {
  val nexus = "https://nexus.midaruma.cloud/"
  if (isSnapshot.value)
    Some(("maven-snapshots" at nexus + "repository/maven-snapshots/"))
  else
    Some(("maven-releases" at nexus + "repository/maven-releases"))
}

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
