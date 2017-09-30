
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/dsathe/Documents/Soc/LocationTrackerServer/conf/routes
// @DATE:Sun Aug 27 23:00:29 EDT 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
