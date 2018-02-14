name := "shapeless-circe"

organization := "danslapman"

version := "1.0"

scalaVersion := "2.12.4"

crossScalaVersions := Seq("2.11.12", "2.12.4")

scalacOptions ++= {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, y)) if y == 11 => Seq("-Xexperimental", "-Yrecursion", "1")
    case _ => Seq.empty[String]
  }
}

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core" % Versions.circe,
  "io.circe" %% "circe-generic" % Versions.circe,
  "io.circe" %% "circe-parser" % Versions.circe,
  "com.chuusai" %% "shapeless" % "2.3.3",
  "org.scalatest" %% "scalatest" % "3.0.4" % Test
)

lazy val shapeless_circe = project in file(".")

licenses += ("WTFPL", url("http://www.wtfpl.net"))

bintrayOrganization := Some("danslapman")

bintrayReleaseOnPublish in ThisBuild := false