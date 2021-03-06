lazy val root = (project in file(".")).enablePlugins(SbtWeb)

libraryDependencies += "org.webjars" % "bootstrap" % "3.0.2"

LessKeys.relativeImports := false

val checkMapFileContents = taskKey[Unit]("check that map contents are correct")

checkMapFileContents := {
  var re = """^.+,"sources":\["main\.less","[^"]+/relative-imports-off/target/web/web-modules/main/webjars/lib/bootstrap/less/mixins\.less"\],.+$""".r
  val contents = IO.read((WebKeys.public in Assets).value / "main.css.map")
  if (!re.pattern.matcher(contents).matches) {
    sys.error(s"Unexpected contents: $contents")
  }
}
