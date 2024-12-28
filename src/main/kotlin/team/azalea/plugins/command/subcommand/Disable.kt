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

class Disable(
    private val pluginManager: PluginManager,
    permissionManager: PermissionManager
) : Command("disable"), CommandExecutor {
    private val pluginArgument = PluginArgument("plugin",  pluginManager, Context.GetEnabled)

    init {
        setCondition { sender, _ -> permissionManager.hasPermission(sender, "server.manage_plugins") }
        addSyntax(this, pluginArgument)
    }

    override fun apply(sender: CommandSender, context: CommandContext) {
        val plugin = context.get(pluginArgument)
        pluginManager.disablePlugin(plugin)
        sender.sendMessage(Component.text("${plugin.info.name} ")
            .append(Component
                .text("has been disabled.")
                .color(NamedTextColor.GRAY)))
    }
}