/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Default;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class ChannelSet {
    @command(name = "set", type = command.ExecutorType.ALL, topic = command.Topics.Modo)
    private void set(Message message, Guild guild, String[] args, User user, MessageChannel channel,
                     command.Language lang) {
        block81:
        {
            try {
                if (guild.getMember(user).hasPermission(Permission.ADMINISTRATOR)
                        || user.getId().equals("102108573298851840")) {
                    StringBuilder builder = new StringBuilder();
                    for (String str : args) {
                        if (str.equals(args[0]))
                            continue;
                        if (builder.length() > 0) {
                            builder.append(" ");
                        }
                        builder.append(str);
                    }
                    String c1 = args[0];
                    String c2 = builder.toString();
                    if (c1.equals("welcome")) {
                        if (c2.equals("off")) {
                            DiscordBot.getGuilddata().getGuildProfil().get(guild.getId()).setGeneral("0");
                            if (lang == command.Language.fr) {
                                channel.sendMessage("Il n'y a desormais plus de channel de Bienvenue.").queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("There is no welcome channel anymore.").queue();
                            }
                        } else {
                            DiscordBot.getGuilddata().getGuildProfil().get(guild.getId()).setGeneral(channel.getId());
                            if (lang == command.Language.fr) {
                                channel.sendMessage("Le channel de bienvenue est desormais : " + channel.getId())
                                        .queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("The welcome channel is now : " + channel.getId()).queue();
                            }
                        }
                        break block81;
                    }
                    if (c1.equals("Vmusique")) {
                        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId());
                        TextFileWriter.write(
                                "/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/Vmusique.txt",
                                builder.toString(), 1);
                        if (lang == command.Language.fr) {
                            channel.sendMessage("Le channel vocal de musique est desormais : #" + builder.toString())
                                    .queue();
                        }
                        if (lang == command.Language.en) {
                            channel.sendMessage("The music vocal channel is now : #" + builder.toString()).queue();
                        }
                        break block81;
                    }
                    if (c1.equals("count")) {
                        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId());
                        if (c2.equals("off") || c2.equals("false")) {
                            if (lang == command.Language.fr) {
                                channel.sendMessage(
                                        "Il n'y a desormais plus de channel textuel o\u00f9 compter a l'infini.")
                                        .queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("There is no more textchannel to count to infinity.").queue();
                            }
                            TextFileWriter.write(
                                    "/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/count.txt", "false",
                                    1);
                        } else {
                            TextFileWriter.write(
                                    "/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/count.txt",
                                    channel.getId(), 1);
                            if (lang == command.Language.fr) {
                                channel.sendMessage(
                                        "Le channel textuel o\u00f9 compter est desormais : " + channel.getName())
                                        .queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("The textchannel where counter is enable is : " + channel.getName())
                                        .queue();
                            }
                        }
                        break block81;
                    }
                    if (c1.equals("Tmusique")) {
                        if (c2.equals("off") || c2.equals("false")) {
                            if (lang == command.Language.fr) {
                                channel.sendMessage("Il n'y a desormais plus de channel textuel pour la musique")
                                        .queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("There is no more textchannel for the music.").queue();
                            }
                            DiscordBot.getGuilddata().getGuildProfil().get(guild.getId()).setTmusique("false");
                        } else {
                            DiscordBot.getGuilddata().getGuildProfil().get(guild.getId()).setTmusique(channel.getId());
                            if (lang == command.Language.fr) {
                                channel.sendMessage(
                                        "Le channel textuel de musique est desormais : " + channel.getName()).queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("The textchannel for the music is now : " + channel.getName())
                                        .queue();
                            }
                        }
                        break block81;
                    }
                    if (c1.equals("NSFW") || c1.equals("nsfw")) {
                        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId());
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/NSFW.txt",
                                channel.getId(), 1);
                        if (lang == command.Language.fr) {
                            channel.sendMessage("Le channel NSFW est desormais : " + channel.getName()).queue();
                        }
                        if (lang == command.Language.en) {
                            channel.sendMessage("The NSFW channel is now : " + channel.getName()).queue();
                        }
                        break block81;
                    }
                    if (c1.equals("log")) {
                        if (c2.equals("")) {
                            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild" + guild.getId());
                            TextFileWriter.write(
                                    "/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/log.txt",
                                    channel.getId(), 1);
                            if (lang == command.Language.fr) {
                                channel.sendMessage("Le channel log est desormais : " + channel.getId()).queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("The log channel is now : " + channel.getId()).queue();
                            }
                        } else if (c2.equals("on") || c2.equals("true")) {
                            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild" + guild.getId());
                            TextFileWriter.write(
                                    "/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/log.txt",
                                    channel.getId(), 1);
                            if (lang == command.Language.fr) {
                                channel.sendMessage("Le channel log est desormais : " + channel.getId()).queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("The log channel is now : " + channel.getId()).queue();
                            }
                        } else if (c2.equals("off") || c2.equals("false")) {
                            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild" + guild.getId());
                            TextFileWriter.write(
                                    "/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/log.txt", "false",
                                    1);
                            if (lang == command.Language.fr) {
                                channel.sendMessage("Le channel log est desormais désactivé").queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("The log channel is now disable").queue();
                            }
                        }
                        break block81;
                    }
                    if (c1.equals("language")) {
                        if (c2.equals("on") || c2.equals("true")) {
                            TextFileWriter.write(
                                    "/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/setBadWords.txt",
                                    "true", 1);
                            if (lang == command.Language.fr) {
                                channel.sendMessage("Je vais desormais suveiller le tchat de ce serveur.").queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("I will monitor swearwords on your server. ").queue();
                            }
                        } else if (c2.equals("off") || c2.equals("false")) {
                            TextFileWriter.write(
                                    "/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/setBadWords.txt",
                                    "false", 1);
                            if (lang == command.Language.fr) {
                                channel.sendMessage("je ne vais desormais plus surveillez le tchat de ce serveur.")
                                        .queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("I will stop monitor swearwords on your server.").queue();
                            }
                        }
                        break block81;
                    }
                    if (c1.toLowerCase().equals("welcomemessage")) {
                        String mess = message.getContentRaw().replace(String.valueOf(c1) + " ", "");
                        if ((mess = mess.replace("=set ", "")).equals("")) {
                            if (lang == command.Language.fr) {
                                channel.sendMessage("Syntaxe : ``=set welcomemessage [message]").queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("Syntax : ``=set welcomemessage [message]").queue();
                            }
                            return;
                        }
                        DiscordBot.getGuilddata().getGuildProfil().get(guild.getId())
                                .setWelcomeMessage(message.getContentRaw().replace(c1, ""));
                        if (lang == command.Language.fr) {
                            channel.sendMessage("le nouveau message de bienvenue est " + mess).queue();
                        }
                        if (lang == command.Language.en) {
                            channel.sendMessage("The new welcome message is now " + mess).queue();
                        }
                        if (c2.equals("off") || c2.equals("false")) {
                            DiscordBot.getGuilddata().getGuildProfil().get(guild.getId()).setWelcomeMessage(
                                    "\ud83c\udf89\ud83c\udf89 Tu es desormais en compagnie de {players} users.\n Passe un bon moment ici. \ud83c\udf89\ud83c\udf89");
                            if (lang == command.Language.fr) {
                                channel.sendMessage("Le message de bienvenue a été remis par defaut").queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("The welcome message was reset by default").queue();
                            }
                        }
                        break block81;
                    }
                    if (c1.equals("autoAFK")) {
                        if (c2.equals("false") || c2.equals("off")) {
                            if (lang == command.Language.fr) {
                                channel.sendMessage("AutoAFK désactivé").queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("AutoAFK disable").queue();
                            }
                            return;
                        }
                        int minutes = 30;
                        try {
                            minutes = Integer.parseInt(c2);
                        } catch (NumberFormatException e) {
                            if (lang == command.Language.fr) {
                                channel.sendMessage("Syntaxe : ``=set autoAFK [durée]``").queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("Syntax : ``=set autoAFK [duration]``").queue();
                            }
                            return;
                        }
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/AutoAFK.txt",
                                Integer.toString(minutes), 1);
                        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/AutoAFK/");
                        if (lang == command.Language.fr) {
                            channel.sendMessage("Vous serez desormais mit AFK au bout de " + c2
                                    + " minutes apres votre dernier message").queue();
                        }
                        if (lang == command.Language.en) {
                            channel.sendMessage(
                                    "You will be put AFK automatically " + c2 + " minutes after your last message")
                                    .queue();
                        }
                        break block81;
                    }
                    if (c1.equals("GlobalChat")) {
                        if (c2.equals("false") || c2.equals("off")) {
                            if (lang == command.Language.fr) {
                                channel.sendMessage("Global Chat desactivé").queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("Global Chat disable").queue();
                            }
                            return;
                        }
                        TextFileWriter.write(
                                "/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/GlobalChat.txt",
                                channel.getId(), 1);
                        if (lang == command.Language.fr) {
                            channel.sendMessage("Global Chat désormais activé sur ce salon").queue();
                        }
                        if (lang == command.Language.en) {
                            channel.sendMessage("Global Chat is now enable on this channel").queue();
                        }
                    }
                    break block81;
                }
                if (lang == command.Language.fr) {
                    channel.sendMessage("Désolé mais vous n'avez pas la permission d'effectuer cette action").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Sorry but you don't have the permission to perform this command").queue();
                }
            } catch (Exception e) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(
                            "Syntaxe : ``=set [GlobalChat/autoAFK/welcomemessage/language/log/NSFW/Tmusique/Vmusique/welcome] [true/false]``.")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(
                            "Syntax : ``=set [GlobalChat/autoAFK/welcomemessage/language/log/NSFW/Tmusique/Vmusique/welcome] [true/false]``.")
                            .queue();
                }
                return;
            }
        }
    }

    @command(name = "start", type = command.ExecutorType.ALL)
    private void start(MessageChannel message, Guild guild, String[] args, User user, MessageChannel channel,
                       command.Language lang) {
        block12:
        {
            try {
                StringBuilder builder = new StringBuilder();
                for (String str : args) {
                    if (str.equals(args[0]))
                        continue;
                    if (builder.length() > 0) {
                        builder.append(" ");
                    }
                    builder.append(str);
                }
                String c1 = args[0];
                String c2 = builder.toString();
                if (c1.equals("count")) {
                    TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId());
                    if (c2.equals("off") || c2.equals("false")) {
                        if (lang == command.Language.fr) {
                            channel.sendMessage(
                                    "Il n'y a desormais plus de channel textuel o\u00f9 compter a l'infini.").queue();
                        }
                        if (lang == command.Language.en) {
                            channel.sendMessage("There is no more textchannel to count to infinity.").queue();
                        }
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/count.txt",
                                "false", 1);
                    } else {
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/count.txt",
                                channel.getId(), 1);
                        if (lang == command.Language.fr) {
                            channel.sendMessage(
                                    "Le channel textuel o\u00f9 compter est desormais : " + channel.getName()).queue();
                        }
                        if (lang == command.Language.en) {
                            channel.sendMessage("The textchannel where you can count is now : " + channel.getName())
                                    .queue();
                        }
                    }
                }
            } catch (Exception e) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Syntaxe : ``=start count``").queue();
                }
                if (lang != command.Language.en)
                    break block12;
                channel.sendMessage("Syntax : ``=start count``").queue();
            }
        }
    }
}
