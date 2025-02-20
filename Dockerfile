## JAVA = 21.0.5
## SCALA_VERSION = 2.13.15
## SBT_VERSION = 1.10.3
FROM sbtscala/scala-sbt:eclipse-temurin-alpine-21.0.2_13_1.10.3_2.13.15 AS build

WORKDIR /app

COPY build.sbt buildInfo.sbt dependencies.sbt compilerPlugins.sbt nexus.sbt nexusCredentials.sbt ./
COPY project/ project/
RUN sbt -warn update

COPY . .

RUN echo "==> Running sbt assembly..." && \
    sbt -Dsbt.offline=true assembly

FROM eclipse-temurin:21.0.2_13-jre-alpine
WORKDIR /app

COPY --from=build --chown=185 /app/target/scala-*/application.jar application.jar

RUN ln -sf /usr/share/zoneinfo/America/Argentina/Buenos_Aires /etc/localtime

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"] 