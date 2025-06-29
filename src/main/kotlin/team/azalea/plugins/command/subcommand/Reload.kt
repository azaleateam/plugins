package team.azalea.plugins.command.subcommand

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.CommandExecutor
import team.azalea.plugins.PluginManager
import team.azalea.plugins.command.Context
import team.azalea.plugins.command.PermissionManager
import team.azalea.plugins.command.PluginArgument
import team.azalea.plugins.command.gray
import team.azalea.plugins.command.primary
import team.azalea.plugins.command.white

class Reload(
    private val pluginManager: PluginManager,
    permissionManager: PermissionManager
) : Command("reload"), CommandExecutor {
    private val pluginArgument = PluginArgument("plugin",  pluginManager, Context.GetAll)

    init {
        setCondition { sender, _ -> permissionManager.hasPermission(sender, "server.manage_plugins") }
        addSyntax(this, pluginArgument)
    }

    override fun apply(sender: CommandSender, context: CommandContext) {
        val plugin = context.get(pluginArgument)

        if (plugin.isSetup)
            pluginManager.disablePlugin(plugin)

        pluginManager.enablePlugin(plugin)

        sender.sendMessage(Component.text("» ")
            .color(gray)
            .append(Component.text(plugin.info.name)
                .color(primary))
            .append(Component.text(" has been reloaded.")
                .color(white)))
    }
}