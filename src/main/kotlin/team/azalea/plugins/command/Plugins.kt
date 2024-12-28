package team.azalea.plugins.command

import net.kyori.adventure.text.Component
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.CommandExecutor
import team.azalea.plugins.PluginManager
import team.azalea.plugins.command.subcommand.Disable
import team.azalea.plugins.command.subcommand.Enable
import team.azalea.plugins.command.subcommand.Reload

class Plugins(
    private val pluginManager: PluginManager,
    permissionManager: PermissionManager = NoPermissionsImpl
) : Command("plugins"), CommandExecutor {
    init {
        defaultExecutor = this

        addSubcommand(Enable(pluginManager, permissionManager))
        addSubcommand(Disable(pluginManager, permissionManager))
        addSubcommand(Reload(pluginManager, permissionManager))
    }

    override fun apply(sender: CommandSender, context: CommandContext) {
        val plugins = pluginManager.plugins.values.map {
            Component.text(it.info.name)
                .color(if (it.isSetup) mint else coral)
        }

        var display = Component.text("  ")

        for ((index, plugin) in plugins.withIndex())
            display = display
                .append(plugin)
                .append(
                    if (index + 1 >= plugins.size - 1)
                        Component.empty()
                    else Component
                        .text(", ")
                        .color(ice))

        sender.sendMessage(Component
            .text("Plugins (")
            .color(ice)
            .append(Component
                .text(plugins.size)
                .color(mint))
            .append(Component
                .text("):"))
            .append(Component.newline())
            .append(display))
    }
}