package gg.airbrush.plugins

abstract class Plugin(
    val pluginManager: PluginManager,
    val loader: PluginClassLoader,
    val info: PluginInfo
) {
    val dataFolder = pluginManager.pluginsFolder.resolve(info.name)
    var isSetup = false

    abstract fun setup()
    abstract fun teardown()
}