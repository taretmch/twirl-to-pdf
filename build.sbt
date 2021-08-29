import Dependencies._

ThisBuild / scalaVersion     := "2.13.6"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.criceta"
ThisBuild / organizationName := "taretmch"

val openHtmlVersion = "1.0.9"

lazy val core = (project in file("core"))
  .settings(name := "twirl-to-pdf")
  .settings(libraryDependencies ++= Seq(
    "com.openhtmltopdf" % "openhtmltopdf-core"   % openHtmlVersion,
    "com.openhtmltopdf" % "openhtmltopdf-pdfbox" % openHtmlVersion,
    scalaTest % Test
  ))
  enablePlugins(SbtTwirl)

lazy val example = (project in file("example"))
  .settings(name := "pdf-example")
  .settings(libraryDependencies ++= Seq(
    "com.openhtmltopdf" % "openhtmltopdf-core"   % openHtmlVersion,
    "com.openhtmltopdf" % "openhtmltopdf-pdfbox" % openHtmlVersion,
    scalaTest % Test
  ))
  enablePlugins(SbtTwirl)
  .dependsOn(core)

TwirlKeys.templateImports = Seq.empty
