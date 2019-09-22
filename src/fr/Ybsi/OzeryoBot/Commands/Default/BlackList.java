/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Default;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class BlackList {
    @command(name = "blacklist", type = command.ExecutorType.ALL, topic = command.Topics.Modo)
    private void blacklist(Message message, Guild guild, String[] args, User user, MessageChannel channel, String arg,
            command.Language lang) {
        if (guild.getMember(user).hasPermission(Permission.MANAGE_CHANNEL)) {
            String c2;
            String c1;
            try {
                c1 = args[0];
            } catch (ArrayIndexOutOfBoundsException e) {
                c1 = "";
            }
            try {
                c2 = args[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                c2 = "";
            }
            if (c2.toLowerCase().equals("blacklist")) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous ne pouvez pas désactiver cette commande.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You can't disable this command.").queue();
                }
                return;
            }
            if (c1.equals("add")) {
                String channel_cible = channel.getId();
                String command_cible = c2;
                TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/BlackList/");
                TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/BlackList/"
                        + channel_cible + "/");
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/BlackList/"
                        + channel_cible + "/" + command_cible, "true", 1);
                if (!c2.equals("all")) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Il n'est plus possible d'utiliser la commande " + command_cible + " sur ce channel")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage(
                                "It's not possible to use the command " + command_cible + " on this channel anymore")
                                .queue();
                    }
                } else {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Il n'est plus possible d'utiliser aucune commande sur ce channel.")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("It's not possible to use any command on this channel anymore.").queue();
                    }
                }
            } else if (c1.equals("remove")) {
                String channel_cible = channel.getId();
                String command_cible = c2;
                TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/BlackList/");
                TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/BlackList/"
                        + channel_cible + "/");
                TextFileWriter.delete("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/BlackList/"
                        + channel_cible + "/" + command_cible);
                if (!c2.equals("all")) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Il est desormais possible d'utiliser la commande " + command_cible + " sur ce channel")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage(
                                "It is now possible to use the command " + command_cible + " son this channel").queue();
                    }
                } else {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Il est desormais possible d'utiliser les commandes sur ce channel.")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("It is now possible to use all command on this channel.").queue();
                    }
                }
            } else {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Syntaxe ``=blacklist [add/remove] [commande]``.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Syntax ``=blacklist [add/remove] [command]``.").queue();
                }
            }
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous n'avez pas les permissions necessaire pour effectuer cette action.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You don't have the necessary permission to perform this command.").queue();
            }
        }
    }
}
