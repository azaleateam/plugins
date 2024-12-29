package team.azalea.plugins

abstract class Plugin {
    lateinit var pluginManager: PluginManager
    lateinit var loader: PluginClassLoader
    lateinit var info: PluginInfo
    val dataFolder
        get() = pluginManager.pluginsFolder.resolve(info.name)
    var isSetup = false

    abstract fun setup()
    abstract fun teardown()
}