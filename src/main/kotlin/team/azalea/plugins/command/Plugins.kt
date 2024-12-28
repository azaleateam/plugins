package team.azalea.plugins.command

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.CommandExecutor
import team.azalea.plugins.PluginManager
import team.azalea.plugins.command.subcommand.Disable
import team.azalea.plugins.command.subcommand.Enable
import team.azalea.plugins.command.subcommand.Reload

class Plugins(
    pluginManager: PluginManager,
    permissionManager: PermissionManager = NoPermissionsImpl
) : Command("plugins"), CommandExecutor {
    init {
        defaultExecutor = this

        addSubcommand(Enable(pluginManager, permissionManager))
        addSubcommand(Disable(pluginManager, permissionManager))
        addSubcommand(Reload(pluginManager, permissionManager))
    }

    override fun apply(sender: CommandSender, context: CommandContext) {

    }
}