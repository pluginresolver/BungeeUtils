/*Copyright (C) Harry5573 2013-14

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.*/

package co.uk.harry5573.bungee.utils.commands;

import co.uk.harry5573.bungee.utils.BungeeUtils;
import co.uk.harry5573.bungee.utils.enumerations.EnumMessage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;

/**
 *
 * @author Harry5573
 */
public class CommandRemoveServer extends Command {

    BungeeUtils plugin;

    public CommandRemoveServer(BungeeUtils instance, String name, String permission, String[] aliases) {
        super(name, permission, aliases);
        this.plugin = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("bungeeutils.admin")) {
            sender.sendMessage(new ComponentBuilder("").append(plugin.prefix + plugin.messages.get(EnumMessage.NOPERM)).create());
            return;
        }

        if (args.length != 1) {
            sender.sendMessage(new ComponentBuilder("").append(plugin.prefix + ChatColor.RED + "Usage: " + ChatColor.GOLD + "/removeserver [name]").create());
            return;
        }

        String name = args[0];
        if (ProxyServer.getInstance().getServers().remove(name) == null) {
            sender.sendMessage(new ComponentBuilder("").append(plugin.prefix + ChatColor.RED + "Could not find server " + ChatColor.GOLD + name).create());
        } else {
            sender.sendMessage(new ComponentBuilder("").append(plugin.prefix + ChatColor.GREEN + "Removed server " + ChatColor.GOLD + name).create());
        }
    }
}