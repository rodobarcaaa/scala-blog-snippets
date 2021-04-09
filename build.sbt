name := "scala-blob-snippets"

version := "0.0.1"

scalaVersion := "2.13.5"

addCommandAlias("t", "test")
addCommandAlias("to", "testOnly")
addCommandAlias("tq", "testQuick")
addCommandAlias("tsf", "testShowFailed")

addCommandAlias("c", "compile")
addCommandAlias("tc", "test:compile")

addCommandAlias("f", "scalafmt")             // Format production files according to ScalaFmt
addCommandAlias("fc", "scalafmtCheck")       // Check if production files are formatted according to ScalaFmt
addCommandAlias("tf", "test:scalafmt")       // Format test files according to ScalaFmt
addCommandAlias("tfc", "test:scalafmtCheck") // Check if test files are formatted according to ScalaFmt
addCommandAlias("fmt", ";f;tf")              // Format all files according to ScalaFmt

addCommandAlias("build", ";c;tc")
