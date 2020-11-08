/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Default;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class EmojiCommand {
    @command(name = "emoji", type = command.ExecutorType.USER, descfr = "affiche la liste des commandes")
    private void emoji(User user, MessageChannel channel, Guild guild, String[] args, Message message,
                       command.Language lang) {
        EmbedBuilder builder;
        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/emojis/common/");
        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/emojis/uncommon/");
        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/emojis/rare/");
        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/emojis/epic/");
        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/emojis/legendary/");
        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/emojis/mythic/");
        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/emojis/");
        int NbEmoji = TextFileWriter
                .folderlength("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/emojis/common/")
                + TextFileWriter
                .folderlength("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/emojis/uncommon/");
        StringBuilder buider = new StringBuilder();
        String[] arrstring = args;
        int n = arrstring.length;
        for (int i = 0; i < n; ++i) {
            String str = arrstring[i];
            if (str.equals(args[0]))
                continue;
            if (buider.length() > 0) {
                buider.append(" ");
            }
            buider.append(str);
        }
        StringBuilder buder = new StringBuilder();
        for (String str : args) {
            if (!str.equals(args[0]))
                break;
            if (buder.length() > 0) {
                buder.append(" ");
            }
            buder.append(str);
        }
        String c1 = buder.toString();
        int c2 = 1;
        String build = buider.toString();
        System.out.println(c1);
        System.out.println(build);
        try {
            c2 = build.equals("") || build.equals("owned") ? 1 : Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            c2 = 1;
        }
        System.out.println(c2);
        if (c1.equals("")) {
            builder = new EmbedBuilder();
            builder.setTitle("Emoji List");
            builder.setDescription("Vous avez " + NbEmoji + " Emoji Sur 118");
            int page = c2;
            for (int i = 1; i <= 15; ++i) {
                if (TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/emojis/" + i + ".txt")
                        .equals("true")) {
                    builder.addField("**Emoji " + i + "**", "\u2705 Owned \u2705", true);
                    continue;
                }
                builder.addField("**Emoji " + i + "**", "\ufffd?\ufffd Not Owned \ufffd?\ufffd", true);
            }
            builder.setFooter("Page " + page + " sur 8", null);
            channel.sendMessage(builder.build()).queue();
        } else if (c1.equals("list")) {
            if (build.equals("owned")) {
                int i;
                builder = new EmbedBuilder();
                builder.setTitle("Emoji List");
                builder.setDescription("Vous avez " + NbEmoji + " Emoji Sur 118");
                for (i = 1; i <= 118; ++i) {
                    if (!TextFileWriter.read(
                            "/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/emojis/common/" + i + ".txt")
                            .equals("true"))
                        continue;
                    builder.addField("**Emoji " + i + "** ( common )", "\u2705 Owned \u2705", true);
                }
                for (i = 1; i <= 69; ++i) {
                    if (!TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId()
                            + "/emojis/uncommon/" + i + ".txt").equals("true"))
                        continue;
                    builder.addField("**Emoji " + i + "** ( uncommon )", "\u2705 Owned \u2705", true);
                }
                builder.setFooter("Page 1 sur 1 | tape =emoji list 1 pour acceder a la page suivante", null);
                channel.sendMessage(builder.build()).queue();
            } else {
                builder = new EmbedBuilder();
                builder.setTitle("Emoji List");
                builder.setDescription("Vous avez " + NbEmoji + " Emoji Sur 118");
                int page = c2;
                int i = 1;
                int imax = 15;
                int NextPage = 2;
                String rarity = "common";
                switch (page) {
                    case 1: {
                        i = 1;
                        imax = 15;
                        NextPage = 2;
                        rarity = "common";
                        break;
                    }
                    case 2: {
                        i = 16;
                        imax = 30;
                        NextPage = 2;
                        rarity = "common";
                        break;
                    }
                    case 3: {
                        i = 31;
                        imax = 45;
                        NextPage = 2;
                        rarity = "common";
                        break;
                    }
                    case 4: {
                        i = 46;
                        imax = 60;
                        NextPage = 2;
                        rarity = "common";
                        break;
                    }
                    case 5: {
                        i = 61;
                        imax = 75;
                        NextPage = 2;
                        rarity = "common";
                        break;
                    }
                    case 6: {
                        i = 76;
                        imax = 90;
                        NextPage = 2;
                        rarity = "common";
                        break;
                    }
                    case 7: {
                        i = 91;
                        imax = 105;
                        NextPage = 2;
                        rarity = "common";
                        break;
                    }
                    case 8: {
                        i = 106;
                        imax = 118;
                        NextPage = 2;
                        rarity = "common";
                        break;
                    }
                    case 9: {
                        i = 1;
                        imax = 15;
                        NextPage = 2;
                        rarity = "uncommon";
                        break;
                    }
                    case 10: {
                        i = 16;
                        imax = 30;
                        NextPage = 2;
                        rarity = "uncommon";
                        break;
                    }
                    case 11: {
                        i = 31;
                        imax = 45;
                        NextPage = 2;
                        rarity = "uncommon";
                        break;
                    }
                    case 12: {
                        i = 46;
                        imax = 60;
                        NextPage = 2;
                        rarity = "uncommon";
                        break;
                    }
                    case 13: {
                        i = 61;
                        imax = 69;
                        NextPage = 2;
                        rarity = "uncommon";
                        break;
                    }
                    default: {
                        i = 1;
                        imax = 15;
                        NextPage = 2;
                        rarity = "common";
                        break;
                    }
                }
                while (i <= imax) {
                    if (TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/emojis/"
                            + rarity + "/" + i + ".txt").equals("true")) {
                        builder.addField("**Emoji " + i + "** ( " + rarity + " )", "\u2705 Owned \u2705", true);
                    } else {
                        builder.addField("**Emoji " + i + "**", "\ufffd?\ufffd Not Owned \ufffd?\ufffd", true);
                    }
                    ++i;
                }
                builder.setFooter(
                        "Page " + page + " sur 14 | tape =emoji list " + NextPage + " pour acceder a la page suivante",
                        null);
                channel.sendMessage(builder.build()).queue();
            }
        } else if (c1.equals("claim")) {
            int jetons = Integer.parseInt(
                    TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/jeton.txt"));
            if (jetons == 0) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Desolé mais vous n'avez pas assez de jeton pour debloquer un emoji !!")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Sorry but you don't avec enough tokens to unlock an emoji.").queue();
                }
            } else {
                --jetons;
                double alea1 = Math.random();
                if (alea1 <= 0.39) {
                    int alea2 = 1 + (int) (Math.random() * 118.0);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/emojis/common/"
                            + alea2 + ".txt", "true", 1);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/jeton.txt",
                            Integer.toString(jetons), 1);
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous avez debloquerun nouvel Emoji (commun). Faites =emoji common " + alea2
                                + " pour l'afficher").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You unlock a new emoji (commun). Do =emoji common " + alea2 + " to see it")
                                .queue();
                    }
                } else if (alea1 > 0.39 && alea1 <= 0.69) {
                    int alea2 = 1 + (int) (Math.random() * 69.0);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/emojis/uncommon/"
                            + alea2 + ".txt", "true", 1);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/jeton.txt",
                            Integer.toString(jetons), 1);
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous avez debloquerun nouvel Emoji (peu commun). Faites =emoji uncommon "
                                + alea2 + " pour l'afficher").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage(
                                "You unlock a new emoji (uncommun). Do =emoji uncommon " + alea2 + " to see it.")
                                .queue();
                    }
                } else if (alea1 > 0.69 && alea1 <= 0.87) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Au non ce n'est pas de chance il n'y a pas encore d'objet rare a gagner.")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("Oh no this is bad luck, there is not rare object to win yet.").queue();
                    }
                } else if (alea1 > 0.87 && alea1 <= 0.95) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Au non ce n'est pas de chance il n'y a pas encore d'objet epique a gagner.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("Oh no this is bad luck, there is not epic object to win yet.").queue();
                    }
                } else if (alea1 > 0.95 && alea1 <= 0.99) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Au non ce n'est pas de chance il n'y a pas encore d'objet legendaire a gagner.")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("Oh no this is bad luck, there is not legendary object to win yet.")
                                .queue();
                    }
                } else if (alea1 > 0.99) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Au non ce n'est pas de chance il n'y a pas encore d'objet mythic a gagner.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("Oh no this is bad luck, there is not mythic object to win yet.").queue();
                    }
                }
            }
        } else {
            if (c1.equals("c")) {
                c1 = "common";
            }
            if (c1.equals("uc")) {
                c1 = "uncommon";
            }
            if (c1.equals("r")) {
                c1 = "rare";
            }
            if (c1.equals("e")) {
                c1 = "epic";
            }
            if (c1.equals("l")) {
                c1 = "legendary";
            }
            if (c1.equals("m")) {
                c1 = "mythic";
            }
            String rarity = c1;
            int emojis = c2;
            if (rarity.equals("common") || rarity.equals("uncommon")) {
                String verif = TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId()
                        + "/emojis/" + rarity + "/" + emojis + ".txt");
                if (verif.equals("true")) {
                    if (guild.getSelfMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                        message.delete().queue();
                    }
                    EmbedBuilder emoji = new EmbedBuilder();
                    emoji.setImage("http://ozeryo.sytes.net/images/emojis/" + rarity + "/" + emojis + ".png");
                    channel.sendMessage(emoji.build()).queue();
                } else {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Désolé mais vous n'avez pas débloqué cet emoji !!").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("Sorry but you didn't unlock this emoji").queue();
                    }
                }
            } else {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Syntaxe incorecte --> =emoji [rareté] [numero]").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Incorrect syntax --> =emoji [rarity] [number]").queue();
                }
            }
        }
    }
}
