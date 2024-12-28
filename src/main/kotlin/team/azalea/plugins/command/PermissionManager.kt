package team.azalea.plugins.command

import net.minestom.server.command.CommandSender

interface PermissionManager {
    fun hasPermission(sender: CommandSender, permission: String): Boolean
}

object NoPermissionsImpl : PermissionManager {
    override fun hasPermission(sender: CommandSender, permission: String): Boolean {
        return false
    }
}