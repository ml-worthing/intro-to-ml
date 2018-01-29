import scalariform.formatter.preferences._

resolvers += Resolver.sonatypeRepo("snapshots")

val scalaV = "2.11.11" //must be the same as version of scala in jupyter notebooks

lazy val introToMl = (project in file(".")).settings(
  scalaVersion := scalaV,
  organization := "ml-worthing",
  scalariformPreferences := scalariformPreferences.value.setPreference(DanglingCloseParenthesis, Force),
  libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.4",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4",
  libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.13.5",
  libraryDependencies += "org.jupyter-scala" % s"scala-api_$scalaV" % "0.4.2" % "provided", //wasn't able to use '%%'
  libraryDependencies += "org.plotly-scala" %% "plotly-render" % "0.3.1" % "provided",
  libraryDependencies += "org.plotly-scala" %% "plotly-jupyter-scala" % "0.3.1" % "provided",

  // https://oss.sonatype.org/index.html#nexus-search;quick~platanios
  //Last Modified: Mon Jan 22 2018 06:10:48 GMT+0000 (GMT)

  libraryDependencies += "org.platanios" %% "tensorflow" % tensorFlowVersion classifier "linux-cpu-x86_64",
  libraryDependencies += "org.platanios" %% "tensorflow-data" % tensorFlowVersion,
  libraryDependencies += "org.platanios" %% "tensorflow-examples" % tensorFlowVersion

)

lazy val tensorFlowVersion = "0.1.1-SNAPSHOT"