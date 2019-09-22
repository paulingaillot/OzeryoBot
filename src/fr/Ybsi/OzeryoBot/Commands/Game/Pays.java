/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.Game.habitants;
import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Utils.Premium;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import fr.Ybsi.OzeryoBot.Utils.Quest;
import fr.Ybsi.OzeryoBot.Utils.Scheduler;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import fr.Ybsi.OzeryoBot.Utils.color;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.impl.UserImpl;
import net.dv8tion.jda.core.requests.RestAction;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class Pays {
    /*
     * Enabled force condition propagation Lifted jumps to return sites
     */
    @command(name = "country", abbrev = "pays", type = command.ExecutorType.ALL, descfr = "Permet de t'allier avec d'autres joueurs et de gagner un bonus supplementaire. \n``p invite`` / ``p join`` / ``p leave`` / ``p invest`` / ``p create`` / ``p give`` / ``p add`` / ``p credits`` / ``p members`` / ``p perm`` / ``p rank`` / ``p role``", topic = command.Topics.Game)
    private void pays(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        int level;
        StringBuilder builder;
        String c1;
        User cible;
        String rank;
        String c2;
        String c3;
        int machine;
        String name;
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
        try {
            c3 = args[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            c3 = "";
        }
        String pays = data.getProfils().get(user.getId()).getCountry();
        String owner = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/owner.txt");
        if (pays.equals("") && !c1.equals("create") && !c1.equals("join")) {
            if (lang == command.Language.fr) {
                channel.sendMessage("\u26a0 Vous devez etre dans un pays pour pouvoir executer cette commande.")
                        .queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("\u26a0 You must be in a country to use this command.").queue();
            return;
        }
        HashMap<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("kick", false);
        map.put("changee", false);
        map.put("changed", false);
        map.put("invite", false);
        map.put("showm", false);
        map.put("giver", false);
        map.put("modifr", false);
        map.put("creater", false);
        map.put("creditb", false);
        map.put("pattack", false);
        map.put("prename", false);
        map.put("desc", false);
        map.put("emoji", false);
        if (owner.equals(user.getId())) {
            map.put("kick", true);
            map.put("changee", true);
            map.put("changed", true);
            map.put("invite", true);
            map.put("showm", true);
            map.put("giver", true);
            map.put("modifr", true);
            map.put("creater", true);
            map.put("creditb", true);
            map.put("pattack", true);
            map.put("prename", true);
            map.put("desc", true);
            map.put("emoji", true);
        }
        String rank1 = "member";
        try {
            for (File file1 : TextFileWriter
                    .folderlist("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles")) {
                if (!TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles/"
                        + file1.getName() + "/" + user.getId()).equals("true"))
                    continue;
                rank1 = file1.getName();
                for (File file2 : TextFileWriter.folderlist(
                        "/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles/" + file1.getName() + "/perm")) {
                    map.put(file2.getName(), true);
                }
            }
        } catch (NullPointerException file1) {
            // empty catch block
        }
        if (c1.equals("")) {
            if (pays.equals("")) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(":warning: Vous n'avez pas encore de pays").queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage(":warning: You are not in a country yet").queue();
                return;
            }
            int point = Integer.parseInt(
                    TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/points.txt"));
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/materiaux/");
            String BBois = TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/materiaux/bois.txt");
            String Bargile = TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/materiaux/argile.txt");
            String Bcuir = TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/materiaux/cuir.txt");
            String Bpierre = TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/materiaux/pierre.txt");
            String Bpaille = TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/materiaux/paille.txt");
            String Bfer = TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/materiaux/fer.txt");
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/ressources/");
            int Rbois = Integer.parseInt(
                    TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/ressources/bois.txt"));
            int Rargile = Integer.parseInt(TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/ressources/argile.txt"));
            int Rcuir = Integer.parseInt(
                    TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/ressources/cuir.txt"));
            int Rpierre = Integer.parseInt(TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/ressources/pierre.txt"));
            int Rpaille = Integer.parseInt(TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/ressources/paille.txt"));
            int Rfer = Integer.parseInt(
                    TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/ressources/fer.txt"));
            int trophées = Integer.parseInt(
                    TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/trophy.txt"));
            double level2 = point / 1000;
            double level32 = Math.sqrt(level2);
            int level4 = (int) Math.round(level32);
            String description = "aucun";
            try {
                description = TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/description.txt");
            } catch (NullPointerException e) {
                description = "aucun";
            }
            if (description == "0") {
                description = "aucun";
            }
            File repertoire = new File("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/member/");
            File[] files = repertoire.listFiles();
            int online_members = 0;
            try {
                for (File file : files) {
                    String user1 = file.getName();
                    int Nsecondes = Integer.parseInt(new SimpleDateFormat("ss", Locale.FRANCE).format(new Date()));
                    int Nminutes = Integer.parseInt(new SimpleDateFormat("mm", Locale.FRANCE).format(new Date()));
                    int Nheures = Integer.parseInt(new SimpleDateFormat("HH", Locale.FRANCE).format(new Date()));
                    int Njours = Integer.parseInt(new SimpleDateFormat("dd", Locale.FRANCE).format(new Date()));
                    int secondes = 0;
                    try {
                        secondes = Integer.parseInt(TextFileWriter.read(
                                "/home/DiscordBot/Rasberry/données/Users/" + user1 + "/LastMessage/secondes.txt"));
                    } catch (NullPointerException nullPointerException) {
                        // empty catch block
                    }
                    int minutes = 0;
                    try {
                        minutes = Integer.parseInt(TextFileWriter
                                .read("/home/DiscordBot/Rasberry/données/Users/" + user1 + "/LastMessage/minutes.txt"));
                    } catch (NullPointerException nullPointerException) {
                        // empty catch block
                    }
                    int heures = 0;
                    try {
                        heures = Integer.parseInt(TextFileWriter
                                .read("/home/DiscordBot/Rasberry/données/Users/" + user1 + "/LastMessage/heures.txt"));
                    } catch (NullPointerException nullPointerException) {
                        // empty catch block
                    }
                    int jours = 0;
                    try {
                        jours = Integer.parseInt(TextFileWriter
                                .read("/home/DiscordBot/Rasberry/données/Users/" + user1 + "/LastMessage/jours.txt"));
                    } catch (NullPointerException nullPointerException) {
                        // empty catch block
                    }
                    int Lsecondes = Nsecondes - secondes;
                    int Lminutes = Nminutes - minutes;
                    int Lheures = Nheures - heures;
                    int Ljours = Njours - jours;
                    if (Lsecondes < 0) {
                        Lsecondes += 60;
                        --Lminutes;
                    }
                    if (Lminutes < 0) {
                        Lminutes += 60;
                        --Lheures;
                    }
                    if (Lheures < 0) {
                        Lheures += 24;
                        --Ljours;
                    }
                    Lminutes = 60 * Lheures;
                    Lminutes = 1440 * Ljours;
                    System.out.println(Lminutes);
                    if (Lminutes >= 15)
                        continue;
                    ++online_members;
                }
            } catch (NullPointerException file) {
                // empty catch block
            }
            int popPays = Integer.parseInt(
                    TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/habitants.txt"));
            int bank = Integer
                    .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/bank.txt"));
            String discordurl = TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/discordURL.txt");
            if (discordurl.equals("0")) {
                discordurl = "pas encore defini";
            }
            String lettre = "";
            if (point / 1000000 >= 1) {
                point /= 1000000;
                lettre = "M";
            } else if (point / 1000 >= 1) {
                point /= 1000;
                lettre = "k";
            }
            String points = String.valueOf(point) + lettre;
            String emoji = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/emoji.txt");
            String emoji2 = "0";
            if (emoji.equals("\ud83d\udcaf")) {
                emoji2 = "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/twitter/180/hundred-points-symbol_1f4af.png";
            } else if (emoji.equals("\u2618")) {
                emoji2 = "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/twitter/180/balloon_1f388.png";
            } else if (emoji.equals("\ud83c\udf40")) {
                emoji2 = "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/twitter/180/four-leaf-clover_1f340.png";
            } else if (emoji.equals("\ud83d\udc8e")) {
                emoji2 = "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/twitter/180/gem-stone_1f48e.png";
            } else if (emoji.equals("\u2620")) {
                emoji2 = "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/twitter/180/skull-and-crossbones_2620.png";
            } else if (emoji.equals("\u26a1")) {
                emoji2 = "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/twitter/180/high-voltage-sign_26a1.png";
            } else if (emoji.equals("\ud83d\udc51")) {
                emoji2 = "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/twitter/180/crown_1f451.png";
            }
            EmbedBuilder builder2 = new EmbedBuilder();
            builder2.setAuthor(pays, null, user.getAvatarUrl());
            if (!emoji2.equals("0")) {
                builder2.setThumbnail(emoji2);
            }
            builder2.setColor(color.couleurAleatoire(user));
            if (lang == command.Language.fr) {
                builder2.setDescription(":reminder_ribbon: **Level :** " + level4 + "\n\n " + ":rosette: **Points :** "
                        + points + "\n\n" + ":clipboard: **Description :** ``" + description + "``\n\n"
                        + ":inbox_tray: **Discord :** " + discordurl + "\n\n" + ":busts_in_silhouette: **Troupes :** "
                        + popPays + "\n\n" + ":trophy: **Trophées :** " + trophées + "\n");
            }
            if (lang == command.Language.en) {
                builder2.setDescription(":reminder_ribbon: **Level :** " + level4 + "\n\n " + ":rosette: **Points :** "
                        + points + "\n\n" + ":clipboard: **Description :** ``" + description + "``\n\n"
                        + ":inbox_tray: **Discord :** " + discordurl + "\n\n" + ":busts_in_silhouette: **Troops :** "
                        + popPays + "\n\n" + ":trophy: **Trophies :** " + trophées + "\n");
            }
            if (lang == command.Language.fr) {
                builder2.addField(":scales: Ressources :", "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("wood", true).get(0).getAsMention()
                        + " - **Bois (" + BBois + "Pts)** : " + Rbois + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("clay", true).get(0).getAsMention()
                        + " - **Argile (" + Bargile + "Pts)** : " + Rargile + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("leather", true).get(0).getAsMention()
                        + " - **Cuir (" + Bcuir + "Pts)** : " + Rcuir + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("stone", true).get(0).getAsMention()
                        + " - **Pierre (" + Bpierre + "Pts)** : " + Rpierre + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("straw", true).get(0).getAsMention()
                        + " - **Paille (" + Bpaille + "Pts)** : " + Rpaille + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("iron", true).get(0).getAsMention()
                        + " - **Fer (" + Bfer + "Pts)** : " + Rfer + "\n \n" + ":money_with_wings: **Bank :** " + bank
                        + "$\n\n" + ":bust_in_silhouette: Members : " + TextFileWriter
                                .folderlength("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/member/"),
                        true);
            }
            if (lang == command.Language.en) {
                builder2.addField(":scales: Ressources :", "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("wood", true).get(0).getAsMention()
                        + " - **Wood (" + BBois + "Pts)** : " + Rbois + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("clay", true).get(0).getAsMention()
                        + " - **Clay (" + Bargile + "Pts)** : " + Rargile + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("leather", true).get(0).getAsMention()
                        + " - **Leather (" + Bcuir + "Pts)** : " + Rcuir + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("stone", true).get(0).getAsMention()
                        + " - **Stone (" + Bpierre + "Pts)** : " + Rpierre + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("straw", true).get(0).getAsMention()
                        + " - **Straw (" + Bpaille + "Pts)** : " + Rpaille + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("iron", true).get(0).getAsMention()
                        + " - **Iron (" + Bfer + "Pts)** : " + Rfer + "\n \n" + ":money_with_wings: **Bank :** " + bank
                        + "$\n\n" + ":bust_in_silhouette: Members : " + TextFileWriter
                                .folderlength("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/member/"),
                        true);
            }
            channel.sendMessage(builder2.build()).queue();
            return;
        }
        if (c1.equals("level")) {
            int point = Integer.parseInt(
                    TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/points.txt"));
            double level2 = point / 1000;
            double level3 = Math.sqrt(level2);
            level = (int) Math.round(level3);
            machine = 0;
            int levelmachine = 0;
            if (level % 10 >= 6) {
                machine = level / 10 + 1;
                levelmachine = machine * 10 + 6;
            } else {
                machine = level / 10;
                levelmachine = machine / 10 + 6;
            }
            int emoji = 0;
            emoji = level % 10 >= 2 ? level / 10 + 1 : level / 10;
            String emote = "";
            emote = emoji == 1 ? String.valueOf(emote) + ":beginner: : :white_check_mark: \n"
                    : String.valueOf(emote) + ":beginner: : débloqué au niveau 2 \n";
            emote = emoji == 2 ? String.valueOf(emote) + ":100: : :white_check_mark: \n"
                    : String.valueOf(emote) + ":100: : débloqué au niveau 12 \n";
            if (emoji == 3) {
                emote = String.valueOf(emote) + ":shamrock: : :white_check_mark: \n";
            }
            emote = String.valueOf(emote) + ":shamrock: : débloqué au niveau 22 \n";
            emote = emoji == 4 ? String.valueOf(emote) + ":four_leaf_clover: : :white_check_mark: \n"
                    : String.valueOf(emote) + ":four_leaf_clover: : débloqué au niveau 32 \n";
            emote = emoji == 5 ? String.valueOf(emote) + ":diamond_shape_with_a_dot_inside: : :white_check_mark: \n"
                    : String.valueOf(emote) + ":diamond_shape_with_a_dot_inside: : débloqué au niveau 42 \n";
            emote = emoji == 6 ? String.valueOf(emote) + ":skull: : :white_check_mark: \n"
                    : String.valueOf(emote) + ":skull: : débloqué au niveau 52 \n";
            emote = emoji == 7 ? String.valueOf(emote) + ":zap: : :white_check_mark: \n"
                    : String.valueOf(emote) + ":zap: : débloqué au niveau 62 \n";
            emote = emoji == 8 ? String.valueOf(emote) + ":crown: : :white_check_mark:\n"
                    : String.valueOf(emote) + ":crown: : débloqué au niveau 72 \n";
            if (emoji == 9) {
                emote = String.valueOf(emote) + ":shield: : :white_check_mark: \n";
            }
            emote = String.valueOf(emote) + ":shield: : débloqué au niveau 82 \n";
            emote = emoji == 10 ? String.valueOf(emote) + ":dagger: : :white_check_mark: \n"
                    : String.valueOf(emote) + ":dagger: : débloqué au niveau 92 \n";
            int membres = 10 + level / 10;
            int membresnext = level / 10 + 10;
            String bonus = "";
            bonus = level > 4 ? String.valueOf(bonus) + " +50% de bois : :white_check_mark: \n"
                    : String.valueOf(bonus) + " +50% de bois : débloqué au niveau 4 \n";
            bonus = level > 8 ? String.valueOf(bonus) + "+25% de money dans le =work : :white_check_mark: \n"
                    : String.valueOf(bonus) + " +25% de money dans le =work : débloqué au niveau 8 \n";
            bonus = level > 14 ? String.valueOf(bonus) + "+50% d'argile : :white_check_mark: \n"
                    : String.valueOf(bonus) + "+50% d'argile : débloqué au niveau 14 \n";
            bonus = level > 18 ? String.valueOf(bonus) + "+25% de pop dans le =work : :white_check_mark: \n"
                    : String.valueOf(bonus) + "+25% de pop dans le =work : débloqué au niveau 18 \n";
            bonus = level > 24 ? String.valueOf(bonus) + "+50% de cuir : :white_check_mark: \n"
                    : String.valueOf(bonus) + "+50% de cuir : débloqué au niveau 24 \n";
            bonus = level > 28 ? String.valueOf(bonus) + "+50% gain hr : :white_check_mark: \n"
                    : String.valueOf(bonus) + "+50% gain hr : débloqué au niveau 28 \n";
            bonus = level > 34 ? String.valueOf(bonus) + "+50% de pierre : :white_check_mark: \n"
                    : String.valueOf(bonus) + "+50% de pierre : débloqué au niveau 34 \n";
            bonus = level > 38 ? String.valueOf(bonus) + "+50% gain daily : :white_check_mark: \n"
                    : String.valueOf(bonus) + "+50% gain daily : débloqué au niveau 38 \n";
            bonus = level > 44 ? String.valueOf(bonus) + "+50% de paille : :white_check_mark: \n"
                    : String.valueOf(bonus) + "+50% de paille : débloqué au niveau 44 \n";
            bonus = level > 48 ? String.valueOf(bonus) + "+25% popmax : :white_check_mark: \n"
                    : String.valueOf(bonus) + "+25% popmax : débloqué au niveau 48 \n";
            bonus = level > 54 ? String.valueOf(bonus) + "+50% de fer : :white_check_mark: \n"
                    : String.valueOf(bonus) + "+50% de fer : débloqué au niveau 54 \n";
            bonus = level > 58 ? String.valueOf(bonus) + "+50% de soldats : :white_check_mark: \n"
                    : String.valueOf(bonus) + "+50% de soldats : débloqué au niveau 58 \n";
            bonus = level > 64 ? String.valueOf(bonus) + "+50% de maximum de mana : :white_check_mark: \n"
                    : String.valueOf(bonus) + "+50% de maximum de mana : débloqué au niveau 64 \n";
            bonus = level > 68 ? String.valueOf(bonus) + "+50% atk de hero : :white_check_mark:  \n"
                    : String.valueOf(bonus) + "+50% atk de hero : débloqué au niveau 68  \n";
            bonus = level > 74 ? String.valueOf(bonus)
                    + "+100% de vitesse de recuperation des ressources dans les zones de ressources : :white_check_mark: \n"
                    : String.valueOf(bonus)
                            + "+100% de vitesse de recuperation des ressources dans les zones de ressources : débloqué au niveau 74 \n";
            bonus = level > 78 ? String.valueOf(bonus) + "+50% magie de hero : :white_check_mark: \n"
                    : String.valueOf(bonus) + "+50% magie de hero : débloqué au niveau 78 \n";
            bonus = level > 84
                    ? String.valueOf(bonus) + "+50% de vitesse de deplacement sur la map : :white_check_mark: \n"
                    : String.valueOf(bonus) + "+50% de vitesse de deplacement sur la map : débloqué au niveau 84 \n";
            bonus = level > 88 ? String.valueOf(bonus) + "+50% def de hero : :white_check_mark: \n"
                    : String.valueOf(bonus) + "+50% def de hero : débloqué au niveau 88 \n";
            bonus = level > 94
                    ? String.valueOf(bonus) + "-50% de delay pour reconstruire le laboratoire : :white_check_mark: \n"
                    : String.valueOf(bonus)
                            + "-50% de delay pour reconstruire le laboratoire : débloqué au niveau 94 \n";
            bonus = level > 98 ? String.valueOf(bonus) + "+50% de vitesse de regen du hero : :white_check_mark:"
                    : String.valueOf(bonus) + "+50% de vitesse de regen du hero : débloqué au niveau 98";
            EmbedBuilder builder3 = new EmbedBuilder();
            builder3.setTitle(String.valueOf(pays));
            builder3.setAuthor(user.getName(), null, user.getAvatarUrl());
            builder3.setColor(color.couleurAleatoire(user));
            builder3.addField("Machine",
                    "Nombre de marchine : " + machine + "\n Prochaine machine débloqué au niveau " + levelmachine,
                    true);
            builder3.addField("Bonus", bonus, true);
            builder3.addBlankField(false);
            builder3.addField("Mermbres",
                    "Membres maximums : " + membres + "\n Prochain gain de membres level " + membresnext, true);
            builder3.addField("Emojis", emote, true);
            channel.sendMessage(builder3.build()).queue();
            return;
        }
        if (c1.equals("machine")) {
            if (c2.equals("") || c2.equals("machine")) {
                int point = Integer.parseInt(
                        TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/points.txt"));
                double level2 = point / 1000;
                double level3 = Math.sqrt(level2);
                level = (int) Math.round(level3);
                machine = 0;
                int levelmachine = 0;
                if (level % 10 >= 6) {
                    machine = level / 10 + 1;
                    levelmachine = machine * 10 + 6;
                } else {
                    machine = level / 10;
                    levelmachine = machine / 10 + 6;
                }
                if (level < 6) {
                    channel.sendMessage("Vous devez etre niveau 6 pour debloquer votre premi\u00e8re machine").queue();
                    return;
                }
                EmbedBuilder builder4 = new EmbedBuilder();
                builder4.setTitle("Machines du pays");
                builder4.setAuthor(user.getName(), null, user.getAvatarUrl());
                TextFileWriter.folder("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/machine/");
                for (int o = 1; o <= machine; ++o) {
                    String bonus = TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/machine/bonus" + o + ".txt");
                    String etat = TextFileWriter.read(
                            "/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/machine/machine" + o + ".txt");
                    int cout = 0;
                    try {
                        cout = Integer.parseInt(TextFileWriter.read(
                                "/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/machine/cout" + o + ".txt"));
                    } catch (Exception builder3) {
                        // empty catch block
                    }
                    if (cout < 100000) {
                        cout = 100000;
                    }
                    builder4.addField("Machine n\u00b0" + o,
                            "Etat : " + etat + "\n Bonus selectionné : " + bonus + "\n Cout Horaire : " + cout, true);
                }
                channel.sendMessage(builder4.build()).queue();
                return;
            }
            if (c2.equals("start")) {
                c3 = args[2];
                int point = Integer.parseInt(
                        TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/points.txt"));
                double level2 = point / 1000;
                double level3 = Math.sqrt(level2);
                level = (int) Math.round(level3);
                machine = 0;
                int levelmachine = 0;
                if (level % 10 >= 6) {
                    machine = level / 10 + 1;
                    levelmachine = machine * 10 + 6;
                } else {
                    machine = level / 10;
                    levelmachine = machine / 10 + 6;
                }
                Boolean test = false;
                int o = 1;
                int i = 1;
                for (o = 1; o <= machine; ++o) {
                    if (!c3.equals("machine" + o))
                        continue;
                    i = o;
                    test = true;
                }
                if (!test.booleanValue()) {
                    channel.sendMessage("Vous ne pouvez pas demarrer une machine que vous ne possedez pas").queue();
                    return;
                }
                int bonus = 0;
                try {
                    bonus = Integer.parseInt(TextFileWriter.read(
                            "/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/machine/bonus" + i + ".txt"));
                } catch (Exception cout) {
                    // empty catch block
                }
                if (bonus == 0) {
                    channel.sendMessage(
                            "Vous devez d'abord set un bonus pour activer votre machine country machine set [numero du bonus]")
                            .queue();
                    return;
                }
                String state = TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/machine/" + c3 + ".txt");
                if (state.equals("active")) {
                    channel.sendMessage("Votre machine est deja en cours d'utilisation.").queue();
                    return;
                }
                long money = Long.parseLong(
                        TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/bank.txt"));
                long cout = 0L;
                try {
                    cout = Long.parseLong(TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/machine/cout" + i + ".txt"));
                } catch (Exception level32) {
                    // empty catch block
                }
                if (cout == 0L) {
                    cout = 100000L;
                }
                if (cout > money) {
                    channel.sendMessage("Vous devez avoir " + cout + " pour activer votre machine.").queue();
                    return;
                }
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/bank.txt",
                        Long.toString(money -= cout), 1);
                TextFileWriter.write(
                        "/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/machine/lastpay" + i + ".txt",
                        Long.toString(System.currentTimeMillis()), 1);
                cout = (long) ((double) cout * 1.05);
                TextFileWriter.write(
                        "/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/machine/cout" + i + ".txt",
                        Long.toString(cout), 1);
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/machine/" + c3 + ".txt",
                        "active", 1);
                channel.sendMessage("Vous venez d'activer la machine " + i).queue();
                return;
            }
            if (c2.equals("set")) {
                c3 = args[2];
                int point = Integer.parseInt(
                        TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/points.txt"));
                double level2 = point / 1000;
                double level3 = Math.sqrt(level2);
                level = (int) Math.round(level3);
                machine = 0;
                int levelmachine = 0;
                if (level % 10 >= 6) {
                    machine = level / 10 + 1;
                    levelmachine = machine * 10 + 6;
                } else {
                    machine = level / 10;
                    levelmachine = machine / 10 + 6;
                }
                Boolean test = false;
                int o = 1;
                int i = 1;
                for (o = 1; o <= machine; ++o) {
                    if (!c3.equals("machine" + o))
                        continue;
                    i = o;
                    test = true;
                }
                if (!test.booleanValue()) {
                    channel.sendMessage("Vous ne pouvez pas modifier le bonus d'une machine que vous ne possedez pas")
                            .queue();
                    return;
                }
                int bonus = 0;
                try {
                    bonus = Integer.parseInt(args[3]);
                } catch (Exception e) {
                    channel.sendMessage(
                            "Veuillez selectionner le bonus que vous souhaitez utiliser : \n \n 1- +50% de bois : :white_check_mark: \n2- +25% de money dans le =work : :white_check_mark: \n3- +50% d'argile : :white_check_mark: \n4- +25% de pop dans le =work : :white_check_mark: \n5- +50% de cuir : :white_check_mark: \n6- +50% gain hr : :white_check_mark: \n7- +50% de pierre : :white_check_mark: \n8- +50% gain daily : :white_check_mark: \n9- +50% de paille : :white_check_mark: \n10- +25% popmax : :white_check_mark: \n11- +50% de fer : :white_check_mark: \n12- +50% de soldats : :white_check_mark: \n13- +50% de maximum de mana : :white_check_mark: \n14- +50% atk de hero : :white_check_mark:  \n15- +100% de vitesse de recuperation des ressources dans les zones de ressources : :white_check_mark: \n16- +50% magie de hero : :white_check_mark: \n17- +50% de vitesse de deplacement sur la map : :white_check_mark: \n18- +50% def de hero : :white_check_mark: \n19- -50% de delay pour reconstruire le laboratoire : :white_check_mark: \n20- +50% de vitesse de regen du hero : :white_check_mark:")
                            .queue();
                    return;
                }
                int bonusmax = 0;
                bonusmax = level % 10 >= 8 ? level / 10 * 2 + 2
                        : (level % 10 >= 4 ? level / 10 * 2 + 1 : level / 10 * 2 + 0);
                if (bonus > bonusmax) {
                    channel.sendMessage("Vous ne pouvez pas utiliser un bonus que vous ne possedez pas.").queue();
                }
                TextFileWriter.write(
                        "/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/machine/bonus" + i + ".txt",
                        Integer.toString(bonus), 1);
                channel.sendMessage(
                        "Vous venez de selectionner le bonus numero " + bonus + " sur votre la machine " + i).queue();
                return;
            }
            if (!c2.equals("stop"))
                return;
            c3 = args[2];
            int point = Integer.parseInt(
                    TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/points.txt"));
            double level2 = point / 1000;
            double level3 = Math.sqrt(level2);
            level = (int) Math.round(level3);
            machine = 0;
            int levelmachine = 0;
            if (level % 10 >= 6) {
                machine = level / 10 + 1;
                levelmachine = machine * 10 + 6;
            } else {
                machine = level / 10;
                levelmachine = machine / 10 + 6;
            }
            Boolean test = false;
            int o = 1;
            int i = 1;
            for (o = 1; o <= machine; ++o) {
                if (!c3.equals("machine" + o))
                    continue;
                i = o;
                test = true;
            }
            if (!test.booleanValue()) {
                channel.sendMessage("Vous ne pouvez pasarreter une machine que vous ne possedez pas").queue();
                return;
            }
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/machine/" + c3 + ".txt",
                    "stop", 1);
            channel.sendMessage("Vous venez d'arreter la machine " + i).queue();
            return;
        }
        if (c1.equals("emoji") && ((Boolean) map.get("emoji")).booleanValue()) {
            int point = Integer.parseInt(
                    TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/points.txt"));
            double level2 = point / 1000;
            double level3 = Math.sqrt(level2);
            level = (int) Math.round(level3);
            String emoji = c2;
            if (emoji.equals("\ud83d\udcaf") && level < 10 || emoji.equals("\ud83c\udf88") && level < 15
                    || emoji.equals("\ud83c\udf40") && level < 25 || emoji.equals("\ud83d\udc8e") && level < 35
                    || emoji.equals("\u2620") && level < 70 || emoji.equals("\u26a1") && level < 100
                    || emoji.equals("\ud83d\udc51") && level < 225) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(
                            "Le niveau de votre pays semble trop faible pour utiliser cet emoji : \n- level 10 : :100: \r\n- level 15 : :balloon: \r\n- level 25 : :four_leaf_clover: \r\n- level 35 : :gem: \r\n- level 70 : :skull_crossbones: \r\n- level 100 : :zap: \r\n- level 225 : :crown: ")
                            .queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage(
                        "Your country level is too low to use this emoji : \n- level 10 : :100: \r\n- level 15 : :balloon: \r\n- level 25 : :four_leaf_clover: \r\n- level 35 : :gem: \r\n- level 70 : :skull_crossbones: \r\n- level 100 : :zap: \r\n- level 225 : :crown: ")
                        .queue();
                return;
            }
            if (emoji.equals("\ud83d\udcaf") && level >= 10 || emoji.equals("\u2618\ufe0f") && level >= 15
                    || emoji.equals("\ud83c\udf40") && level >= 25 || emoji.equals("\ud83d\udc8e") && level >= 35
                    || emoji.equals("\u2620") && level >= 70 || emoji.equals("\u26a1") && level >= 100
                    || emoji.equals("\ud83d\udc51") && level < 225) {
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/emoji.txt", emoji, 1);
                if (lang == command.Language.fr) {
                    channel.sendMessage("Votre nouvel emoji de pays est : " + emoji).queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("Your new country's emoji is : " + emoji).queue();
                return;
            } else {
                if (lang == command.Language.fr) {
                    channel.sendMessage(
                            "Cet emoji ne peut pas etre utilisé comme recompense de level du pays. \n- level 10 : :100: \r\n- level 15 : :balloon: \r\n- level 25 : :four_leaf_clover: \r\n- level 35 : :gem: \r\n- level 70 : :skull_crossbones: \r\n- level 100 : :zap: \r\n- level 225 : :crown: ")
                            .queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage(
                        "This emojis can be used as country's rewards. \n- level 10 : :100: \r\n- level 15 : :balloon: \r\n- level 25 : :four_leaf_clover: \r\n- level 35 : :gem: \r\n- level 70 : :skull_crossbones: \r\n- level 100 : :zap: \r\n- level 225 : :crown: ")
                        .queue();
            }
            return;
        }
        if (c1.equals("role") && ((Boolean) map.get("giver")).booleanValue()) {
            User cible2;
            if (pays.equals("")) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("\u26a0 Vous devez etre dans un pays pour pouvoir executer cette commande.")
                            .queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("\u26a0 You must be in a country to use this command.").queue();
                return;
            }
            rank = c2.replaceAll(" ", "_");
            Boolean test = false;
            File[] Bpaille = TextFileWriter
                    .folderlist("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles/");
            int level3 = Bpaille.length;
            for (int Bcuir = 0; Bcuir < level3; ++Bcuir) {
                File file = Bpaille[Bcuir];
                if (!file.getName().equals(rank))
                    continue;
                test = true;
            }
            if (!test.booleanValue()) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Ce rang ne semble pas exister.").queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("This rank oes'nt exist.").queue();
                return;
            }
            try {
                cible2 = message.getMentionedUsers().get(0);
            } catch (ArrayIndexOutOfBoundsException e) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Veuillez mentionner un joueur").queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("Please mention a player").queue();
                return;
            }
            for (File file1 : TextFileWriter
                    .folderlist("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles")) {
                if (!TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles/"
                        + file1.getName() + "/" + cible2.getId()).equals("true"))
                    continue;
                TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles/" + file1.getName()
                        + "/" + cible2.getId());
            }
            TextFileWriter.write(
                    "/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles/" + rank + "/" + cible2.getId(),
                    "true", 1);
            if (lang == command.Language.fr) {
                channel.sendMessage(String.valueOf(cible2.getName()) + " a desormais le rang : " + rank).queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage(String.valueOf(cible2.getName()) + " now have rank : " + rank).queue();
            return;
        }
        if (c1.equals("perm")) {
            if (pays.equals("")) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("\u26a0 Vous devez etre dans un pays pour pouvoir executer cette commande.")
                            .queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("\u26a0 You must be in a country to use this command.").queue();
                return;
            }
            if (c2.equals("list")) {
                String mess = "";
                if (lang == command.Language.fr) {
                    mess = "Liste de vos permissions \n";
                }
                if (lang == command.Language.en) {
                    mess = "Permissions List \n";
                }
                for (String test : map.keySet()) {
                    mess = String.valueOf(mess) + test + " : " + (Boolean) map.get(test) + " \n";
                }
                channel.sendMessage(mess).queue();
            }
            if (c2.equals("add") && ((Boolean) map.get("modifr")).booleanValue()) {
                rank = c3.replaceAll(" ", "_");
                Boolean test = false;
                for (File file : TextFileWriter
                        .folderlist("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles/")) {
                    if (!file.getName().equals(rank))
                        continue;
                    test = true;
                }
                if (!test.booleanValue()) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Ce rang ne semble pas exister.").queue();
                    }
                    if (lang != command.Language.en)
                        return;
                    channel.sendMessage("This rank doesn't exist.").queue();
                    return;
                }
                String perm = args[3].toLowerCase();
                if (!(perm.equals("kick") || perm.equals("desc") || perm.equals("changee") || perm.equals("changed")
                        || perm.equals("invite") || perm.equals("showm") || perm.equals("giver")
                        || perm.equals("modifr") || perm.equals("emoji") || perm.equals("giver")
                        || perm.equals("creater") || perm.equals("creditb") || perm.equals("pattack")
                        || perm.equals("prename"))) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                " Les permissions existantes sont : \n- Kick\r\n- desc ( pouvoir modifier la description )\r\n- ChangeE ( change emote )\r\n- ChangeD ( change discord ) \r\n- Invite ( pouvoir inviter )\r\n- ShowM ( faire =p members )\r\n- GiveR ( donner un rank )\r\n- ModifR ( modifier un rank )\r\n- CreateR ( Creer un rank )\r\n- CreditB ( prendre de l argent a la bank )\r\n- PAttack ( attack avec le pays ) \r\n- Prename ( rename le pays ) \r\n- emoji (permet de gerer l'emoji du pays)")
                                .queue();
                    }
                    if (lang != command.Language.en)
                        return;
                    channel.sendMessage(
                            " Existing permissions are : \n- Kick\r\n- desc (Change the country description )\r\n- ChangeE ( change emote )\r\n- ChangeD ( change discord link ) \r\n- Invite ( be able to invite people in the country )\r\n- ShowM ( be able to do =p members )\r\n- GiveR ( be able to give a rank )\r\n- ModifR ( be able to modify a rank )\r\n- CreateR ( be able to create a rank )\r\n- CreditB ( Be able to recover money from bank )\r\n- PAttack ( Be able to attack another country ) \r\n- Prename ( be able to rename the country) \r\n- emoji ( be able to change the emoji country)")
                            .queue();
                    return;
                }
                if (!((Boolean) map.get(perm)).booleanValue()) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous ne pouvez pas donner un role que vous n'avez pas.").queue();
                    }
                    if (lang != command.Language.en)
                        return;
                    channel.sendMessage("You can't give a rank you don't have.").queue();
                    return;
                }
                TextFileWriter.write(
                        "/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles/" + rank + "/perm/" + perm,
                        "true", 1);
                if (lang == command.Language.fr) {
                    channel.sendMessage("Le role " + rank + " a desormais la permission : ``" + perm + "``").queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("The rank " + rank + " has now the permission : ``" + perm + "``").queue();
                return;
            } else {
                if (!c2.equals("remove") || !((Boolean) map.get("modifr")).booleanValue())
                    return;
                rank = c3.replaceAll(" ", "_");
                Boolean test = false;
                for (File file : TextFileWriter
                        .folderlist("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles/")) {
                    if (!file.getName().equals(rank))
                        continue;
                    test = true;
                }
                if (!test.booleanValue()) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Ce rang ne semble pas exister.").queue();
                    }
                    if (lang != command.Language.en)
                        return;
                    channel.sendMessage("The rank doesn't exist.").queue();
                    return;
                }
                String perm = args[3].toLowerCase();
                if (!(perm.equals("kick") || perm.equals("desc") || perm.equals("changee") || perm.equals("changed")
                        || perm.equals("invite") || perm.equals("showm") || perm.equals("giver")
                        || perm.equals("modifr") || perm.equals("emoji") || perm.equals("giver")
                        || perm.equals("creater") || perm.equals("creditb") || perm.equals("pattack")
                        || perm.equals("prename"))) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                " Les permissions existantes sont : \n- Kick\r\n- desc ( pouvoir modifier la description )\r\n- ChangeE ( change emote )\r\n- ChangeD ( change discord ) \r\n- Invite ( pouvoir inviter )\r\n- ShowM ( faire =p members )\r\n- GiveR ( donner un rank )\r\n- ModifR ( modifier un rank )\r\n- CreateR ( Creer un rank )\r\n- CreditB ( prendre de l argent a la bank )\r\n- PAttack ( attack avec le pays ) \r\n- Prename ( rename le pays ) \r\n- emoji (permet de gerer l'emoji du pays)")
                                .queue();
                    }
                    if (lang != command.Language.en)
                        return;
                    channel.sendMessage(
                            " Existing permissions are : \n- Kick\r\n- desc (Change the country description )\r\n- ChangeE ( change emote )\r\n- ChangeD ( change discord link ) \r\n- Invite ( be able to invite people in the country )\r\n- ShowM ( be able to do =p members )\r\n- GiveR ( be able to give a rank )\r\n- ModifR ( be able to modify a rank )\r\n- CreateR ( be able to create a rank )\r\n- CreditB ( Be able to recover money from bank )\r\n- PAttack ( Be able to attack another country ) \r\n- Prename ( be able to rename the country) \r\n- emoji ( be able to change the emoji country)")
                            .queue();
                    return;
                }
                if (!((Boolean) map.get(perm)).booleanValue()) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous ne pouvez pas retirer une permission que vous n'avez pas.").queue();
                    }
                    if (lang != command.Language.en)
                        return;
                    channel.sendMessage("You can't remove a permission on a rank you don't have.").queue();
                    return;
                }
                TextFileWriter.delete(
                        "/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles/" + rank + "/perm/" + perm);
                if (lang == command.Language.fr) {
                    channel.sendMessage("Le role " + rank + " n'a desormais plus la permission : ``" + perm + "``")
                            .queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("The rank " + rank + " now haven't longer the permission : ``" + perm + "``")
                        .queue();
            }
            return;
        }
        if (c1.equals("rank") && ((Boolean) map.get("creater")).booleanValue()) {
            if (pays.equals("")) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("\u26a0 Vous devez etre dans un pays pour pouvoir executer cette commande.")
                            .queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("\u26a0 You must be in a country to use this command.").queue();
                return;
            }
            if (c2.equals("add")) {
                rank = c3.replaceAll(" ", "_");
                TextFileWriter.folder("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles");
                if (TextFileWriter.folderlength("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles") >= 5) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous avez deja créé le maximum de roles possible.").queue();
                    }
                    if (lang != command.Language.en)
                        return;
                    channel.sendMessage("You already have the maximum role number it is possible.").queue();
                    return;
                }
                TextFileWriter.folder("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles/" + rank);
                TextFileWriter
                        .folder("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles/" + rank + "/perm");
                if (lang == command.Language.fr) {
                    channel.sendMessage("Le role " + c3
                            + " a été créé. Vous pouvez desormais selectionner ces permissions et le donner aux membres de votre pays.")
                            .queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("The rank " + c3
                        + " has created. Vous pouvez desormais selectionner ces permissions et le donner aux membres de votre pays.")
                        .queue();
                return;
            }
            if (c2.equals("remove")) {
                rank = c3.replaceAll(" ", "_");
                try {
                    TextFileWriter.recursifDelete(
                            new File("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles/" + rank));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (lang == command.Language.fr) {
                    channel.sendMessage("Le role " + c3 + " a bien été supprimé.").queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("The rank " + c3 + " has been now removed.").queue();
                return;
            }
            if (!c2.equals("rename"))
                return;
            String Ancien_Nom = c3;
            String Nouveau_Nom = args[3];
            File source = new File("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles/" + Ancien_Nom);
            File destination = new File("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles/" + Nouveau_Nom);
            source.renameTo(destination);
            if (lang == command.Language.fr) {
                channel.sendMessage("Votre role a été renommé en : " + Nouveau_Nom).queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("Your rank has been renamed to : " + Nouveau_Nom).queue();
            return;
        }
        if (c1.equals("bank")) {
            long Umoney = data.getProfils().get(user.getId()).getMoney();
            int bank = Integer
                    .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/bank.txt"));
            if (pays.equals("")) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("\u26a0 Vous devez etre dans un pays pour pouvoir executer cette commande.")
                            .queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("\u26a0 You must be in a country to use this command.").queue();
                return;
            }
            if (Integer.parseInt(c3) < 0) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Veuillez indiquer un nombre valide.").queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("Please type a valid number.").queue();
                return;
            }
            if (c2.equals("add")) {
                if ((long) Integer.parseInt(c3) > Umoney) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous ne pouvez pas donner plus d'argent que vous n'en avez").queue();
                    }
                    if (lang != command.Language.en)
                        return;
                    channel.sendMessage("You can't give more money than you have to the country").queue();
                    return;
                }
                Umoney -= (long) Integer.parseInt(c3);
                bank += Integer.parseInt(c3);
                try {
                    data.getProfils().get(user.getId()).setMoney(Umoney);
                } catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setMoney(Umoney);
                }
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/bank.txt",
                        Integer.toString(bank), 1);
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous venez d'ajouter " + c3 + " money a la banque du pays").queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("You just add " + c3 + " money to the country bank").queue();
                return;
            }
            if (!c2.equals("credit") || !((Boolean) map.get("creditb")).booleanValue())
                return;
            if (Integer.parseInt(c3) > bank) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous ne pouvez pas recuperer plus d'argent que la bank en a.").queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("You can't collect more money from the country than there is.").queue();
                return;
            }
            Umoney += (long) Integer.parseInt(c3);
            bank -= Integer.parseInt(c3);
            try {
                data.getProfils().get(user.getId()).setMoney(Umoney);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setMoney(Umoney);
            }
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/bank.txt",
                    Integer.toString(bank), 1);
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous venez de retirer " + c3 + " money a la banque du pays").queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("You just remove " + c3 + " money to the country bank").queue();
            return;
        }
        if (c1.equals("discord") && ((Boolean) map.get("changed")).booleanValue()) {
            if (pays.equals("")) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("\u26a0 Vous devez etre dans un pays pour pouvoir executer cette commande.")
                            .queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("\u26a0 You must be in a country to use this command.").queue();
                return;
            }
            builder = new StringBuilder();
            for (String str : args) {
                if (str.equals(args[0]))
                    continue;
                builder.append(String.valueOf(str) + " ");
            }
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/discordURL.txt",
                    builder.toString(), 1);
            if (lang == command.Language.fr) {
                channel.sendMessage("Le nouveau discord de votre pays est : \n ``" + builder.toString() + "``").queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("The new discord url of the country is : \n ``" + builder.toString() + "``").queue();
            return;
        }
        if (c1.equals("setdescription") || c1.equals("description") && ((Boolean) map.get("desc")).booleanValue()) {
            if (pays.equals("")) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("\u26a0 Vous devez etre dans un pays pour pouvoir executer cette commande.")
                            .queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("\u26a0 You must be in a country to use this command.").queue();
                return;
            }
            builder = new StringBuilder();
            for (String str : args) {
                if (str.equals(args[0]))
                    continue;
                builder.append(String.valueOf(str) + " ");
            }
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/description.txt",
                    builder.toString(), 1);
            if (lang == command.Language.fr) {
                channel.sendMessage("La nouvelle description de votre pays est : \n ``" + builder.toString() + "``")
                        .queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("The new country description is : \n ``" + builder.toString() + "``").queue();
            return;
        }
        if (c1.equals("give")) {
            int givePop;
            try {
                givePop = Integer.parseInt(c2);
            } catch (NumberFormatException e) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous devez donner un nombre de soldats a donner au pays").queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("Please type a number of soldier to give to the country").queue();
                return;
            }
            long soldier = habitants.atk(user);
            if (soldier >= (long) givePop) {
                soldier -= (long) givePop;
            } else {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous ne pouvez pas donner plus d'habitant que vous n'en avez.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You can't give more soldiers to the country than you have.").queue();
                }
            }
            int popPays = Integer.parseInt(
                    TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/habitants.txt"));
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/habitants.txt",
                    Integer.toString(popPays += givePop), 1);
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/soldier.txt",
                    Long.toString(soldier), 1);
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous venez de faire don de " + givePop + " a votre Pays.").queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("You just give " + givePop + " soldiers to your country.").queue();
            return;
        }
        if (c1.equals("members") && ((Boolean) map.get("showm")).booleanValue()) {
            String messages = "";
            if (lang == command.Language.fr) {
                messages = "Listes des membres de votre pays : \n";
            }
            if (lang == command.Language.en) {
                messages = "Members List : \n";
            }
            messages = String.valueOf(messages) + "---- **Owner** ---- \n"
                    + jda.getUserById(
                            TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/owner.txt"))
                            .getName()
                    + " (" + TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/owner.txt")
                    + ") \n\n";
            for (File file1 : TextFileWriter
                    .folderlist("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles")) {
                messages = String.valueOf(messages) + " ---- **" + file1.getName() + "** ----\n";
                for (File file2 : TextFileWriter.folderlist(
                        "/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles/" + file1.getName())) {
                    if (file2.getName().equals("perm"))
                        continue;
                    messages = String.valueOf(messages) + jda.getUserById(file2.getName()).getName() + " ("
                            + file2.getName() + ") \n";
                }
                messages = String.valueOf(messages) + "\n";
            }
            messages = String.valueOf(messages) + " ---- **Members** ----\n";
            for (File members : TextFileWriter
                    .folderlist("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/member")) {
                if (messages.contains(jda.getUserById(members.getName()).getName()))
                    continue;
                messages = String.valueOf(messages) + jda.getUserById(members.getName()).getName() + " ("
                        + members.getName() + "\n";
            }
            channel.sendMessage(messages).queue();
            return;
        }
        if (c1.equals("rename") && ((Boolean) map.get("prename")).booleanValue()) {
            String test;
            File[] members;
            name = c2;
            try {
                test = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + name + "/pays.txt");
            } catch (NullPointerException e) {
                test = "";
            }
            if (test.equals("true")) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("\u26a0 Il existe deja une guilde de ce nom, veuillez en choisir un autre.")
                            .queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("\u26a0There is already a country with this name, please type another name.")
                        .queue();
                return;
            }
            for (File member : members = TextFileWriter
                    .folderlist("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/member/")) {
                data.getProfils().get(member.getName()).setCountry(name);
            }
            File source = new File("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/");
            File destination = new File("/home/DiscordBot/Rasberry/données/bot/Pays/" + name + "/");
            source.renameTo(destination);
            if (lang == command.Language.fr) {
                channel.sendMessage("Votre pays se nomme desormais " + name).queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("Your country's name is now " + name).queue();
            return;
        }
        if (c1.equals("kick") && ((Boolean) map.get("kick")).booleanValue()) {
            String test;
            cible = null;
            try {
                cible = message.getMentionedUsers().get(0);
            } catch (IndexOutOfBoundsException e) {
                cible = jda.getUserById(args[1]);
            }
            if (user.getId().equals(cible.getId())) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous ne pouvez pas vous kick vous meme.").queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("You can't kick yourself from the country.").queue();
                return;
            }
            try {
                test = TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/member/" + cible.getId());
            } catch (NullPointerException e) {
                test = "";
            }
            if (!test.equals("true")) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("\u26a0  Ce joueur n'est pas dans votre pays").queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("\u26a0 This player is not in your country").queue();
                return;
            }
            for (File file1 : TextFileWriter
                    .folderlist("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles")) {
                for (File file2 : TextFileWriter.folderlist(
                        "/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles/" + file1.getName())) {
                    if (file2.getName().equals("perm") || !file2.getName().equals(cible.getId()))
                        continue;
                    TextFileWriter.delete(file2.getAbsolutePath());
                }
            }
            TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/member/" + cible.getId());
            try {
                data.getProfils().get(cible.getName()).setCountry(null);
            } catch (NullPointerException e) {
                data.getProfils().put(cible.getName(), new Profil(user.getId()));
                data.getProfils().get(cible.getName()).setCountry(null);
            }
            if (lang == command.Language.fr) {
                channel.sendMessage("Le joueur " + cible.getName() + " a été kick du pays.").queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("The player " + cible.getName() + " has been kick from your country.").queue();
            return;
        }
        if (c1.equals("lead")) {
            cible = message.getMentionedUsers().get(0);
            if (owner.equals(user.getId())) {
                String test;
                try {
                    test = TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/member/" + cible.getId());
                } catch (NullPointerException e) {
                    test = "";
                }
                if (!test.equals("true")) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("\u26a0  Ce joueur n'est pas dans votre pays").queue();
                    }
                    if (lang != command.Language.en)
                        return;
                    channel.sendMessage("\u26a0  This player is not in your country").queue();
                    return;
                }
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/owner.txt", cible.getId(),
                        1);
                if (lang == command.Language.fr) {
                    channel.sendMessage("L'owner de ce pays est desormais " + cible.getName()).queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("The country owner is now " + cible.getName()).queue();
                return;
            }
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez etre le owner du pays pour pouvoir effectuer cette action.").queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("You must be the owner of the country to perform this command.").queue();
            return;
        }
        if (c1.equals("disband")) {
            if (owner.equals(user.getId())) {
                File[] members;
                for (File member : members = TextFileWriter
                        .folderlist("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/member/")) {
                    try {
                        data.getProfils().get(member.getName()).setCountry(null);
                    } catch (NullPointerException e) {
                        data.getProfils().put(member.getName(), new Profil(user.getId()));
                        data.getProfils().get(member.getName()).setCountry(null);
                    }
                }
                TextFileWriter.folder_delete("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/");
                if (lang == command.Language.fr) {
                    channel.sendMessage("Votre pays a été detruit").queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("Your country has been destroyed").queue();
                return;
            }
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous ne pouvez pas detruire ce pays car vous n'etes pas l'owner").queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("You must be the country owner to perform this command").queue();
            return;
        }
        if (c1.equals("leave")) {
            if (owner.equals(user.getId())) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(
                            "Vous ne pouvez pas quitter votre pays car vous en etes l'owner. (tape =country disband)")
                            .queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("You can't leave the country because your are the owner (type =country disband).")
                        .queue();
                return;
            }
            try {
                data.getProfils().get(user.getId()).setCountry("");
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setCountry("");
            }
            for (File file1 : TextFileWriter
                    .folderlist("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles")) {
                for (File file2 : TextFileWriter.folderlist(
                        "/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles/" + file1.getName())) {
                    if (file2.getName().equals("perm") || !file2.getName().equals(user.getId()))
                        continue;
                    TextFileWriter.delete(file2.getAbsolutePath());
                }
            }
            TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/member/" + user.getId());
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez quitté le pays : " + pays).queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("You just leave the country : " + pays).queue();
            return;
        }
        if (c1.equals("invite") && ((Boolean) map.get("invite")).booleanValue()) {
            int membres = TextFileWriter.folderlength("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/member");
            if (membres >= 10) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(
                            "Vous avez atteind la limite de membres du pays, vous ne pouvez pas inviter plus de personne")
                            .queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("You reached the country member's limit, you can't inviter player anymore.")
                        .queue();
                return;
            }
            User user_invite = message.getMentionedUsers().get(0);
            TextFileWriter.write(
                    "/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/member/" + user_invite.getId(), "invite",
                    1);
            if (lang == command.Language.fr) {
                channel.sendMessage("Le joueur " + user_invite.getName()
                        + " a bien été inviter dans votre pays. Celui ci a 5 minutes pour accepter votre invitation.")
                        .queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("The player " + user_invite.getName()
                        + " has successfully invited in your country. He have 5 minutes to accept your invitation.")
                        .queue();
            }
            Scheduler.File_Delete(5, TimeUnit.MINUTES,
                    "/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/member/" + user_invite.getId());
            return;
        }
        if (c1.equals("attack") && ((Boolean) map.get("pattack")).booleanValue()) {
            String pays_adv = c2;
            if (TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays_adv + "/pays.txt")
                    .equals("true")) {
                int trophéesV;
                int gain;
                int troupe = 0;
                try {
                    troupe = Integer.parseInt(c3);
                } catch (NumberFormatException e) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Veuillez indiquer un nombre valide").queue();
                    }
                    if (lang != command.Language.en)
                        return;
                    channel.sendMessage("Please type a valid number").queue();
                    return;
                }
                if (troupe > Integer.parseInt(
                        TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/habitants.txt"))) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous ne pouvez pas attaquer avec plus de troupes que vous n'en avait")
                                .queue();
                    }
                    if (lang != command.Language.en)
                        return;
                    channel.sendMessage("You can't attack with more soldier than you have").queue();
                    return;
                }
                int total_troupe = Integer.parseInt(
                        TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/habitants.txt"));
                int troupe_adv = Integer.parseInt(TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays_adv + "/habitants.txt"));
                if (troupe > troupe_adv) {
                    troupe = total_troupe - troupe;
                    troupe_adv = 0;
                    int trophéesP = Integer.parseInt(TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays_adv + "/trophy.txt"));
                    trophéesV = Integer.parseInt(
                            TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/trophy.txt"));
                    int dif = trophéesV - trophéesP;
                    gain = dif > 500 ? 1
                            : (dif > 300 && dif <= 500 ? 5
                                    : (dif > 100 && dif <= 300 ? 10
                                            : (dif > 0 && dif <= 100 ? 20 : (dif <= 0 ? 30 : 1))));
                    trophéesP = trophéesP > gain ? (trophéesP -= gain) : 0;
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/habitants.txt",
                            Integer.toString(troupe), 1);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/trophy.txt",
                            Integer.toString(trophéesV += gain), 1);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays_adv + "/habtiants.txt",
                            Integer.toString(troupe_adv), 1);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays_adv + "/trophy.txt",
                            Integer.toString(trophéesP), 1);
                    User owner_adv = jda.getUserById(TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays_adv + "/owner.txt"));
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Un rapport de combat vous a été envoyer par MP.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("A combat report was sent to you by PM.").queue();
                    }
                    if (!user.hasPrivateChannel()) {
                        user.openPrivateChannel().complete();
                    }
                    if (lang == command.Language.fr) {
                        ((UserImpl) user).getPrivateChannel().sendMessage(
                                ":crossed_swords: Rapport d'Attaque de Pays :crossed_swords: \n Vous avez gagné. Vous remportez "
                                        + gain + " Trophées et perdez " + troupe + " soldats")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        ((UserImpl) user).getPrivateChannel()
                                .sendMessage(
                                        ":crossed_swords: Country Attack report :crossed_swords: \n You won. You win "
                                                + gain + " trophies and loses " + troupe + " soldiers")
                                .queue();
                    }
                    command.Language langc = DiscordBot.getData().getProfils().get(owner_adv.getId()).getLanguage();
                    if (!owner_adv.hasPrivateChannel()) {
                        owner_adv.openPrivateChannel().complete();
                    }
                    if (langc == command.Language.fr) {
                        ((UserImpl) owner_adv).getPrivateChannel().sendMessage(
                                ":crossed_swords: Rapport de defense de Pays :crossed_swords: \n Vous avez Perdu. Vous perdez "
                                        + gain + " Trophées et perdez " + troupe_adv + " soldats")
                                .queue();
                    }
                    if (langc != command.Language.en)
                        return;
                    ((UserImpl) owner_adv).getPrivateChannel()
                            .sendMessage(
                                    ":crossed_swords: Country Defense report :crossed_swords: \n You lose. You lose "
                                            + gain + " trophies and lose " + troupe_adv + " soldiers")
                            .queue();
                    return;
                } else {
                    if (troupe_adv <= troupe)
                        return;
                    troupe = total_troupe - troupe;
                    int trophéesP = Integer.parseInt(
                            TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/trophy.txt"));
                    trophéesV = Integer.parseInt(TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays_adv + "/trophy.txt"));
                    int dif = trophéesV - trophéesP;
                    gain = dif > 500 ? 1
                            : (dif > 300 && dif <= 500 ? 5
                                    : (dif > 100 && dif <= 300 ? 10
                                            : (dif > 0 && dif <= 100 ? 20 : (dif <= 0 ? 30 : 1))));
                    trophéesP = trophéesP > gain ? (trophéesP -= gain) : 0;
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/habitants.txt",
                            Integer.toString(troupe), 1);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/trophy.txt",
                            Integer.toString(trophéesP), 1);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays_adv + "/habtiants.txt",
                            Integer.toString(troupe_adv -= troupe), 1);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays_adv + "/trophy.txt",
                            Integer.toString(trophéesV += gain), 1);
                    User owner_adv = jda.getUserById(TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays_adv + "/owner.txt"));
                    if (!user.hasPrivateChannel()) {
                        user.openPrivateChannel().complete();
                    }
                    if (lang == command.Language.fr) {
                        ((UserImpl) user).getPrivateChannel().sendMessage(
                                ":crossed_swords: Rapport d'Attaque de Pays :crossed_swords: \n Vous avez Perdu. Vous perdez "
                                        + gain + " Trophées et perdez " + troupe + " soldats")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        ((UserImpl) user).getPrivateChannel()
                                .sendMessage(
                                        ":crossed_swords: Country attack report :crossed_swords: \n You lose. You lose "
                                                + gain + " trophies and " + troupe + " soldiers")
                                .queue();
                    }
                    if (!owner_adv.hasPrivateChannel()) {
                        owner_adv.openPrivateChannel().complete();
                    }
                    if (lang == command.Language.fr) {
                        ((UserImpl) owner_adv).getPrivateChannel().sendMessage(
                                ":crossed_swords: Rapport de defense de Pays :crossed_swords: \n Vous avez Gagné. Vous remportez "
                                        + gain + " Trophées et perdez " + troupe_adv + " soldats")
                                .queue();
                    }
                    if (lang != command.Language.en)
                        return;
                    ((UserImpl) owner_adv).getPrivateChannel()
                            .sendMessage(":crossed_swords: Country defense report :crossed_swords: \n You won. You won "
                                    + gain + " trophies and lose " + troupe_adv + " soldiers")
                            .queue();
                }
                return;
            }
            if (lang == command.Language.fr) {
                channel.sendMessage("Le pays que vous visez ne semble pas exister").queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("This country doesn't exist").queue();
            return;
        }
        if (c1.equals("create")) {
            long money = data.getProfils().get(user.getId()).getMoney();
            if (money >= 10000000L && !Premium.Premium(user) || money >= 1000000L && Premium.Premium(user)) {
                String test;
                if (Premium.Premium(user)) {
                    money -= 1000000L;
                } else if (!Premium.Premium(user)) {
                    money -= 10000000L;
                }
                if (c2.equals("")) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("\u26a0 Vous devez donner un nom a votre pays").queue();
                    }
                    if (lang != command.Language.en)
                        return;
                    channel.sendMessage("\u26a0Please indicate a name to your country").queue();
                    return;
                }
                String name2 = c2;
                c2 = c2.replaceAll(" ", "-");
                if (!pays.equals("")) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez quitter votre pays actuel pour en join un nouveau.").queue();
                    }
                    if (lang != command.Language.en)
                        return;
                    channel.sendMessage("You must leave your country to create a new one.").queue();
                    return;
                }
                try {
                    test = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + name2 + "/pays.txt");
                } catch (NullPointerException e) {
                    test = "";
                }
                if (test.equals("true")) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("\u26a0 Il existe deja une guilde de ce nom, veuillez en choisir un autre")
                                .queue();
                    }
                    if (lang != command.Language.en)
                        return;
                    channel.sendMessage("\u26a0 There is already a country with this name, please select another name")
                            .queue();
                    return;
                }
                TextFileWriter.folder("/home/DiscordBot/Rasberry/données/bot/Pays/" + name2 + "/");
                TextFileWriter.folder("/home/DiscordBot/Rasberry/données/bot/Pays/" + name2 + "/member/");
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + name2 + "/member/" + user.getId(),
                        "true", 1);
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + name2 + "/pays.txt", "true", 1);
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + name2 + "/owner.txt", user.getId(),
                        1);
                data.getProfils().get(user.getId()).setCountry(name2);
                TextFileWriter.folder("/home/DiscordBot/Rasberry/données/bot/Pays/" + name2 + "/materiaux/");
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + name2 + "/materiaux/bois.txt", "1",
                        1);
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + name2 + "/materiaux/argile.txt",
                        "1", 1);
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + name2 + "/materiaux/cuir.txt", "1",
                        1);
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + name2 + "/materiaux/pierre.txt",
                        "1", 1);
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + name2 + "/materiaux/paille.txt",
                        "1", 1);
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + name2 + "/materiaux/fer.txt", "1",
                        1);
                data.getProfils().get(user.getId()).setMoney(money);
                if (lang == command.Language.fr) {
                    channel.sendMessage("\ud83d\udc65 Vous avez créé un pays nommé " + name2).queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("\ud83d\udc65 You created a country named " + name2).queue();
                return;
            }
            if (lang == command.Language.fr) {
                channel.sendMessage(
                        "\ud83c\udf1f Vous devez avoir 10.000.000$ pour creer un pays (1.000.000M$ si premium).")
                        .queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("\ud83c\udf1f You must pay 10M to create a country (1M if you're premium).").queue();
            return;
        }
        if (c1.equals("join")) {
            String test;
            name = c2;
            c2 = c2.replaceAll(" ", "-");
            if (!pays.equals("")) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous devez quitter votre pays actuel pour en join un nouveau.").queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("You must leave your country to join anatoher one.").queue();
                return;
            }
            try {
                test = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + name + "/pays.txt");
            } catch (NullPointerException e) {
                test = "";
            }
            if (test.equals("true")) {
                String invit = TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + name + "/member/" + user.getId());
                if (invit.equals("invite")) {
                    data.getProfils().get(user.getId()).setCountry(name);
                    TextFileWriter.write(
                            "/home/DiscordBot/Rasberry/données/bot/Pays/" + name + "/member/" + user.getId(), "true",
                            1);
                    if (lang == command.Language.fr) {
                        channel.sendMessage("\ud83d\udc65 Vous avez rejoint le pays nommé : " + name).queue();
                    }
                    if (lang != command.Language.en)
                        return;
                    channel.sendMessage("\ud83d\udc65 You join a country named : " + name).queue();
                    return;
                } else {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous n'avez pas été invité dnas ce pays.").queue();
                    }
                    if (lang != command.Language.en)
                        return;
                    channel.sendMessage("You were not invited in this country.").queue();
                }
                return;
            } else {
                if (lang == command.Language.fr) {
                    channel.sendMessage(
                            "\u26a0 Le pays que vous recherchez ne semble pas exister. Veuillez verifier votre orthographe et les majuscules.")
                            .queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("\u26a0 This country doesn't exist, please checkyour spelling and capitalization.")
                        .queue();
            }
            return;
        }
        if (c1.equals("invest")) {
            String materiau = c2;
            int nombre = 0;
            try {
                nombre = Integer.parseInt(args[2]);
            } catch (ArrayIndexOutOfBoundsException e) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("\u26a0 Vous devez indiquer la quantité de ressources \u00e0 donner au pays.")
                            .queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("\u26a0Please type the quantity of ressources you want to give to your country.")
                        .queue();
                return;
            }
            HashMap<String, Integer> res = data.getProfils().get(user.getId()).getRes();
            String materiau1 = materiau;
            int materiau_User = res.get(materiau);
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/ressources/");
            int materiau_Pays = Integer.parseInt(TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/ressources/" + materiau1 + ".txt"));
            double pourcentage = 100.0;
            try {
                pourcentage = (double) (nombre * 100) / (double) materiau_Pays;
            } catch (ArithmeticException e) {
                pourcentage = 100.0;
            }
            if (pourcentage >= 1.0) {
                if (nombre > materiau_User) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("\u26a0 Vous ne pouvez pas donner plus de ressources que vous n'en avez.")
                                .queue();
                    }
                    if (lang != command.Language.en)
                        return;
                    channel.sendMessage("\u26a0 You can't give more ressources than you have to your country.").queue();
                    return;
                } else {
                    int bonus = Integer.parseInt(TextFileWriter.read(
                            "/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/materiaux/" + materiau1 + ".txt"));
                    int point = Integer.parseInt(
                            TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/points.txt"));
                    int point_total = nombre * bonus;
                    int point_total2 = point_total + point;
                    res.put(materiau, materiau_User -= nombre);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/points.txt",
                            Integer.toString(point_total2), 1);
                    TextFileWriter.write(
                            "/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/ressources/" + materiau1 + ".txt",
                            Integer.toString(materiau_Pays += nombre), 1);
                    Quest.Quest("invest", user, channel, nombre);
                    int Investisseur = data.getProfils().get(user.getId()).getInvestisseur();
                    data.getProfils().get(user.getId()).setInvestisseur(Investisseur += nombre);
                    if (point_total == 0) {
                        int Useless = data.getProfils().get(user.getId()).getUseless();
                        data.getProfils().get(user.getId()).setUseless(Useless += nombre);
                    }
                    if (lang == command.Language.fr) {
                        channel.sendMessage(":gift: Vous avez donné *" + nombre + " " + materiau
                                + "* \u00e0 votre pays, ce qui a rapporté **" + point_total + " points**.").queue();
                    }
                    if (lang != command.Language.en)
                        return;
                    channel.sendMessage(":gift: You gave *" + nombre + " " + materiau
                            + "* to your country, which brings **" + point_total + "** points to your country.")
                            .queue();
                }
                return;
            } else {
                if (lang == command.Language.fr) {
                    channel.sendMessage(
                            "\u26a0 Vous devez donner un minimum de 1% des ressources contenu dans les reserve du pays.")
                            .queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("\u26a0 You must gave a minimum of 1% of country ressource to the country reserve.")
                        .queue();
            }
            return;
        }
        if (lang == command.Language.fr) {
            channel.sendMessage("Vous n'avez pas les permissions necessaires pour effectuer cet action.").queue();
        }
        if (lang != command.Language.en)
            return;
        channel.sendMessage("You don't have permission to perform this action.").queue();
    }

    public static boolean Bonus(int bonus, String pays) {
        for (int i = 1; i <= 10; ++i) {
            int bonus1;
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/machine");
            String machine = TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/machine/machine" + i + ".txt");
            if (!machine.equals("active") || bonus != (bonus1 = Integer.parseInt(TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/machine/bonus" + i + ".txt"))))
                continue;
            return true;
        }
        return false;
    }
}
