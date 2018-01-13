import scalariform.formatter.preferences._

lazy val introToMl = (project in file(".")).settings(
  scalaVersion := "2.12.4",
  organization := "mlguider",
  scalariformPreferences := scalariformPreferences.value.setPreference(DanglingCloseParenthesis, Force),
  libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.4",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"
)
