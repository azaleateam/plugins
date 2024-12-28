package team.azalea.plugins.command

import net.minestom.server.command.ArgumentParserType
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.arguments.Argument
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import net.minestom.server.command.builder.suggestion.SuggestionEntry
import team.azalea.plugins.PLUGIN_REGEX
import team.azalea.plugins.Plugin
import team.azalea.plugins.PluginManager

class PluginArgument(
    id: String,
    private val pluginManager: PluginManager,
    private val context: Context = Context.GetAll
) : Argument<Plugin>(id, true, false) {
    init {
        setSuggestionCallback { _, _, suggestion ->
            for (plugin in pluginManager.plugins.values) {
                if (when (this.context) {
                    Context.GetAll -> false
                    Context.GetEnabled -> !plugin.isSetup
                    Context.GetDisabled -> plugin.isSetup
                }) continue

                suggestion.addEntry(SuggestionEntry(plugin.info.id))
            }
        }
    }

    override fun parse(sender: CommandSender, input: String): Plugin {
        val lower = input.lowercase()

        if (PLUGIN_REGEX.matchEntire(lower) == null)
            throw ArgumentSyntaxException("Plugin ID doesn't match character set [a-z0-9-]", input, 1)

        return pluginManager.plugins[lower]
            ?: throw ArgumentSyntaxException("Failed to find a plugin with that ID.", input, 2)
    }

    override fun parser(): ArgumentParserType {
        return ArgumentParserType.STRING
    }

}

enum class Context {
    GetEnabled,
    GetDisabled,
    GetAll
}