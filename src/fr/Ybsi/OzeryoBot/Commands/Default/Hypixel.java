/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Default;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import fr.Ybsi.OzeryoBot.Utils.Utils;
import fr.Ybsi.OzeryoBot.Utils.color;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class Hypixel {
    @command(name = "hypixel", type = command.ExecutorType.ALL)
    private void hypixel(User user, MessageChannel channel, String[] args, JDA jda, command.Language lang) {
        block281: {
            try {
                StringBuilder builder = new StringBuilder();
                for (String str : args) {
                    if (str.equals(args[0]))
                        continue;
                    builder.append(String.valueOf(str));
                }
                String c2 = args[0];
                String c1 = builder.toString();
                URL url = null;
                HttpURLConnection con = null;
                try {
                    Object response;
                    EmbedBuilder hypixel;
                    String inputLine;
                    BufferedReader in;
                    String key;
                    JsonObject jsonObject;
                    if (c1.equals("")) {
                        c1 = c2;
                    }
                    if (!c2.equals("guild")) {
                        key = TextFileWriter.read("/home/DiscordBot/Rasberry/key/HypixelKey.txt");
                        url = new URL("https://api.hypixel.net/player?key=" + key + "&name=" + c1);
                        con = (HttpURLConnection) url.openConnection();
                        con.setRequestMethod("GET");
                        con.setRequestProperty("User-Agent", "Java client");
                        con.setRequestProperty("Content-Type", "application/json");
                        in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        response = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                            ((StringBuffer) response).append(inputLine);
                        }
                        in.close();
                        jsonObject = new JsonParser().parse(((StringBuffer) response).toString()).getAsJsonObject();
                        if (jsonObject.get("success").toString().replace("\"", "").equals("false")) {
                            String cause = jsonObject.get("cause").toString().replace("\"", "");
                            channel.sendMessage("**ERROR** \n __Cause : __" + cause).queue();
                            return;
                        }
                        if (jsonObject.get("player").toString() == "null") {
                            if (lang == command.Language.fr) {
                                channel.sendMessage("Le joueur que vous recherchez ne semble pas exister.").queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("T.").queue();
                            }
                            return;
                        }
                        ArrayList<String> queue = DiscordBot.getHypixeldata().getHypixelQueue();
                        String uuid = jsonObject.getAsJsonObject("player").get("uuid").toString().replace("\"", "");
                        queue.add(0, uuid);
                        DiscordBot.getHypixeldata().setHypixelQueue(queue);
                    } else {
                        key = TextFileWriter.read("/home/DiscordBot/Rasberry/key/HypixelKey.txt");
                        url = new URL("https://api.hypixel.net/guild?key=" + key + "&name=" + c1);
                        con = (HttpURLConnection) url.openConnection();
                        con.setRequestMethod("GET");
                        con.setRequestProperty("User-Agent", "Java client");
                        con.setRequestProperty("Content-Type", "application/json");
                        in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        response = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                            ((StringBuffer) response).append(inputLine);
                        }
                        in.close();
                        jsonObject = new JsonParser().parse(((StringBuffer) response).toString()).getAsJsonObject();
                        if (jsonObject.get("success").toString().replace("\"", "").equals("false")) {
                            String cause = jsonObject.get("cause").toString().replace("\"", "");
                            channel.sendMessage("**ERROR** \n __Cause : __" + cause).queue();
                            return;
                        }
                        if (jsonObject.get("player").toString() == "null") {
                            if (lang == command.Language.fr) {
                                channel.sendMessage("Le joueur que vous recherchez ne semble pas exister.").queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("This player doesn't exist.").queue();
                            }
                            return;
                        }
                    }
                    if (c2.equals("guild")) {
                        hypixel = new EmbedBuilder();
                        hypixel.setTitle("Profile");
                        hypixel.setColor(color.couleurAleatoire(user));
                        hypixel.addField("\ud83d\udc64 | Guild | \ud83d\udc64 : ",
                                jsonObject.getAsJsonObject("guild").get("name").toString().replace("\"", ""), true);
                        hypixel.addField("Coins  : ",
                                jsonObject.getAsJsonObject("guild").get("coins").toString().replace("\"", ""), true);
                        hypixel.addBlankField(false);
                        hypixel.addField("Coins Ever : ",
                                jsonObject.getAsJsonObject("guild").get("coinsEver").toString().replace("\"", ""),
                                true);
                        if (lang == command.Language.fr) {
                            hypixel.addField("Membres  : ",
                                    String.valueOf(
                                            jsonObject.getAsJsonObject("guild").getAsJsonArray("members").size()),
                                    true);
                        }
                        if (lang == command.Language.en) {
                            hypixel.addField("Members  : ",
                                    String.valueOf(
                                            jsonObject.getAsJsonObject("guild").getAsJsonArray("members").size()),
                                    true);
                        }
                        hypixel.addField("legacyRanking  : ",
                                jsonObject.getAsJsonObject("guild").get("legacyRanking").toString().replace("\"", ""),
                                true);
                        hypixel.addField("Status  : ", String.valueOf(
                                jsonObject.getAsJsonObject("guild").get("joinable").toString().replace("\"", "")) + "("
                                + jsonObject.getAsJsonObject("guild").get("publiclyListed").toString().replace("\"", "")
                                + ")", true);
                        hypixel.addField("Xp  : ",
                                jsonObject.getAsJsonObject("guild").get("exp").toString().replace("\"", ""), true);
                        String jeux = "";
                        for (JsonElement i : jsonObject.getAsJsonObject("guild").getAsJsonArray("preferredGames")) {
                            jeux = String.valueOf(jeux) + "- " + i.toString() + " \n";
                        }
                        if (lang == command.Language.fr) {
                            hypixel.addField("Jeux preférés  : ", jeux, true);
                        }
                        if (lang == command.Language.en) {
                            hypixel.addField("favorite games  : ", jeux, true);
                        }
                        hypixel.addField("Description  : ",
                                jsonObject.getAsJsonObject("guild").get("description").toString().replace("\"", ""),
                                true);
                        long date = new Long(
                                jsonObject.getAsJsonObject("guild").get("created").toString().replace("\"", ""));
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(date);
                        int mYear = calendar.get(1);
                        int mMonth = calendar.get(2) + 1;
                        int mDay = calendar.get(5);
                        int mHour = calendar.get(11);
                        int mMinute = calendar.get(12);
                        int mSecond = calendar.get(13);
                        if (lang == command.Language.fr) {
                            hypixel.addField("Date de creation  : ", String.valueOf(mDay) + "/" + mMonth + "/" + mYear
                                    + " " + mHour + ":" + mMinute + ":" + mSecond, true);
                        }
                        if (lang == command.Language.en) {
                            hypixel.addField("Creation date  : ", String.valueOf(mDay) + "/" + mMonth + "/" + mYear
                                    + " " + mHour + ":" + mMinute + ":" + mSecond, true);
                        }
                        String message = "";
                        message = String.valueOf(message) + "QUAKECRAFT : " + jsonObject.getAsJsonObject("guild")
                                .getAsJsonObject("guildExpByGameType").get("QUAKECRAFT").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n WALLS : " + jsonObject.getAsJsonObject("guild")
                                .getAsJsonObject("guildExpByGameType").get("WALLS").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n PAINTBALL : " + jsonObject.getAsJsonObject("guild")
                                .getAsJsonObject("guildExpByGameType").get("PAINTBALL").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n SURVIVAL_GAMES : "
                                + jsonObject.getAsJsonObject("guild").getAsJsonObject("guildExpByGameType")
                                        .get("SURVIVAL_GAMES").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n TNTGAMES : " + jsonObject.getAsJsonObject("guild")
                                .getAsJsonObject("guildExpByGameType").get("TNTGAMES").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n VAMPIREZ : " + jsonObject.getAsJsonObject("guild")
                                .getAsJsonObject("guildExpByGameType").get("VAMPIREZ").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n WALLS3 : " + jsonObject.getAsJsonObject("guild")
                                .getAsJsonObject("guildExpByGameType").get("WALLS3").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n ARCADE : " + jsonObject.getAsJsonObject("guild")
                                .getAsJsonObject("guildExpByGameType").get("ARCADE").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n ARENA : " + jsonObject.getAsJsonObject("guild")
                                .getAsJsonObject("guildExpByGameType").get("ARENA").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n MCGO : " + jsonObject.getAsJsonObject("guild")
                                .getAsJsonObject("guildExpByGameType").get("MCGO").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n UHC : " + jsonObject.getAsJsonObject("guild")
                                .getAsJsonObject("guildExpByGameType").get("UHC").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n BATTLEGROUND : " + jsonObject.getAsJsonObject("guild")
                                .getAsJsonObject("guildExpByGameType").get("BATTLEGROUND").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n SUPER_SMASH : " + jsonObject.getAsJsonObject("guild")
                                .getAsJsonObject("guildExpByGameType").get("SUPER_SMASH").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n GINGERBREAD : " + jsonObject.getAsJsonObject("guild")
                                .getAsJsonObject("guildExpByGameType").get("GINGERBREAD").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n SKYWARS : " + jsonObject.getAsJsonObject("guild")
                                .getAsJsonObject("guildExpByGameType").get("SKYWARS").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n TRUE_COMBAT : " + jsonObject.getAsJsonObject("guild")
                                .getAsJsonObject("guildExpByGameType").get("TRUE_COMBAT").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n SPEED_UHC : " + jsonObject.getAsJsonObject("guild")
                                .getAsJsonObject("guildExpByGameType").get("SPEED_UHC").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n PROTOTYPE : " + jsonObject.getAsJsonObject("guild")
                                .getAsJsonObject("guildExpByGameType").get("PROTOTYPE").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n BEDWARS : " + jsonObject.getAsJsonObject("guild")
                                .getAsJsonObject("guildExpByGameType").get("BEDWARS").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n MURDER_MYSTERY : "
                                + jsonObject.getAsJsonObject("guild").getAsJsonObject("guildExpByGameType")
                                        .get("MURDER_MYSTERY").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n BUILD_BATTLE : " + jsonObject.getAsJsonObject("guild")
                                .getAsJsonObject("guildExpByGameType").get("BUILD_BATTLE").toString().replace("\"", "");
                        message = String.valueOf(message) + "/n Duels : " + jsonObject.getAsJsonObject("guild")
                                .getAsJsonObject("guildExpByGameType").get("DUELS").toString().replace("\"", "");
                        if (lang == command.Language.fr) {
                            hypixel.addField("Xp par jeu ", message, true);
                        }
                        if (lang == command.Language.en) {
                            hypixel.addField("Xp by game ", message, true);
                        }
                        channel.sendMessage(hypixel.build()).queue();
                    } else if (c2.equals("Murder Mystery") || c2.toLowerCase().equals("mm") || c2.equals("murder")) {
                        String kills;
                        hypixel = new EmbedBuilder();
                        hypixel.setTitle("Profile");
                        hypixel.setColor(color.couleurAleatoire(user));
                        hypixel.setThumbnail("https://cravatar.eu/head/"
                                + jsonObject.getAsJsonObject("player").get("uuid").toString().replace("\"", "")
                                + "?254");
                        hypixel.addField("\ud83d\udc64 | Username | \ud83d\udc64 : ",
                                jsonObject.getAsJsonObject("player").get("playername").toString().replace("\"", ""),
                                true);
                        hypixel.addField("\ud83c\udd94 | UUID | \ud83c\udd94 : ",
                                jsonObject.getAsJsonObject("player").get("uuid").toString().replace("\"", ""), true);
                        hypixel.addBlankField(false);
                        String wins = "0";
                        try {
                            wins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("wins").toString().replace("\"", "");
                        } catch (NullPointerException date) {
                            // empty catch block
                        }
                        String coins = "0";
                        try {
                            coins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("coins").toString().replace("\"", "");
                        } catch (NullPointerException response2) {
                            // empty catch block
                        }
                        String games = "0";
                        try {
                            games = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("games").toString().replace("\"", "");
                        } catch (NullPointerException calendar) {
                            // empty catch block
                        }
                        try {
                            kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("kills").toString().replace("\"", "");
                        } catch (NullPointerException e) {
                            kills = "0";
                        }
                        String total_time_survived_seconds = "0";
                        try {
                            total_time_survived_seconds = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("total_time_survived_seconds").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException mMonth) {
                            // empty catch block
                        }
                        hypixel.addField("\ud83d\udcb0 | coins", coins, true);
                        hypixel.addField(":trophy: | Wins", wins, true);
                        hypixel.addField("\u2694| Kills", kills, true);
                        hypixel.addField("\ud83c\udfae | Games", games, true);
                        hypixel.addField("\ud83d\udd51  | Time Survived", total_time_survived_seconds, true);
                        hypixel.addBlankField(false);
                        String Classic_games = "0";
                        try {
                            Classic_games = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("games_MURDER_CLASSIC").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException mDay) {
                            // empty catch block
                        }
                        String Classic_wins = "0";
                        try {
                            Classic_wins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("wins_MURDER_CLASSIC").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException mHour) {
                            // empty catch block
                        }
                        String Classic_kills = "0";
                        try {
                            Classic_kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("kills_MURDER_CLASSIC").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException mMinute) {
                            // empty catch block
                        }
                        String Classic_Knife_kills = "0";
                        try {
                            Classic_Knife_kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("knife_kills_MURDER_CLASSIC").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException mSecond) {
                            // empty catch block
                        }
                        String Classic_Bow_Kills = "0";
                        try {
                            Classic_Bow_Kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("bow_kills_MURDER_CLASSIC").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException message) {
                            // empty catch block
                        }
                        String Classic_Coins_Picked_Up = "0";
                        try {
                            Classic_Coins_Picked_Up = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("coins_pickedup_MURDER_CLASSIC").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException nullPointerException) {
                            // empty catch block
                        }
                        hypixel.addField("\ud83d\udde1 | Classic Game | \ud83d\udde1",
                                "\n **games** : " + Classic_games + "\n **Wins** : " + Classic_wins + "\n **Kills** : "
                                        + Classic_kills + "\n **Knife Kills** : " + Classic_Knife_kills
                                        + "\n **Bow Kills** : " + Classic_Bow_Kills + "\n **Coins picked up** : "
                                        + Classic_Coins_Picked_Up,
                                true);
                        String Infected_games = "0";
                        try {
                            Infected_games = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("games_MURDER_INFECTION").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException nullPointerException) {
                            // empty catch block
                        }
                        String Infected_wins = "0";
                        try {
                            Infected_wins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("survivor_wins_MURDER_INFECTION").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException nullPointerException) {
                            // empty catch block
                        }
                        String Last_One_Alive = "0";
                        try {
                            Last_One_Alive = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("last_one_alive_MURDER_INFECTION").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException nullPointerException) {
                            // empty catch block
                        }
                        String Infected_Survivor_kills = "0";
                        try {
                            Infected_Survivor_kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("kills_as_survivor_MURDER_INFECTION")
                                    .toString().replace("\"", "");
                        } catch (NullPointerException nullPointerException) {
                            // empty catch block
                        }
                        String Infected_Infected_kills = "0";
                        try {
                            Infected_Infected_kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("kills_as_infected_MURDER_INFECTION")
                                    .toString().replace("\"", "");
                        } catch (NullPointerException nullPointerException) {
                            // empty catch block
                        }
                        hypixel.addField("\ud83e\udddf  | Infected Game | \ud83e\udddf",
                                "\n **Games** : " + Infected_games + "\n **Wins** : " + Infected_wins
                                        + "\n **Last Alive** : " + Last_One_Alive + "\n **Survivor Kills** : "
                                        + Infected_Survivor_kills + "\n **Infected Kills** : "
                                        + Infected_Infected_kills,
                                true);
                        String Assassin_games = "0";
                        try {
                            Assassin_games = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("games_MURDER_ASSASSINS").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException nullPointerException) {
                            // empty catch block
                        }
                        String Assassin_wins = "0";
                        try {
                            Assassin_wins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("wins_MURDER_ASSASSINS").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException nullPointerException) {
                            // empty catch block
                        }
                        String Assassin_kills = "0";
                        try {
                            Assassin_kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("kills_MURDER_ASSASSINS").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException nullPointerException) {
                            // empty catch block
                        }
                        String Assassin_Knife_kills = "0";
                        try {
                            Assassin_Knife_kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("knife_kills_MURDER_ASSASSINS").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException nullPointerException) {
                            // empty catch block
                        }
                        String Assassin_Deaths = "0";
                        try {
                            Assassin_Deaths = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("deaths_MURDER_ASSASSINS").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException nullPointerException) {
                            // empty catch block
                        }
                        String Assassin_Coins_Picked_Up = "0";
                        try {
                            Assassin_Coins_Picked_Up = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("coins_pickedup_MURDER_ASSASSINS").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException nullPointerException) {
                            // empty catch block
                        }
                        hypixel.addField("\ud83d\udcdc | Assassin Game | \ud83d\udcdc",
                                "\n **games** : " + Assassin_games + "\n **Wins** : " + Assassin_wins
                                        + "\n **Kills** : " + Assassin_kills + "\n **Deaths** : " + Assassin_Deaths
                                        + "\n **Knife Kills** : " + Assassin_Knife_kills + "\n **Coins picked up** : "
                                        + Assassin_Coins_Picked_Up,
                                true);
                        channel.sendMessage(hypixel.build()).queue();
                    } else if (c1 == c2) {
                        long firstLogin;
                        int coins3;
                        int coins21;
                        int coins18;
                        int coins2;
                        int coins20;
                        long lastLogin;
                        int coins17;
                        String Karma;
                        int coins5;
                        int coins16;
                        int coins4;
                        int coins15;
                        int coins14;
                        int coins7;
                        int coins13;
                        int coins6;
                        int coins12;
                        int coins23;
                        int coins9;
                        int coins1;
                        String achievementPoints;
                        int coins11;
                        String Network_EXP;
                        int coins10;
                        int coins8;
                        int coins22;
                        int coins19;
                        hypixel = new EmbedBuilder();
                        hypixel.setTitle("Profile");
                        hypixel.setColor(color.couleurAleatoire(user));
                        hypixel.setThumbnail("https://cravatar.eu/head/"
                                + jsonObject.getAsJsonObject("player").get("uuid").toString().replace("\"", "")
                                + "?254");
                        hypixel.addField("\ud83d\udc64 | Username | \ud83d\udc64 : ",
                                jsonObject.getAsJsonObject("player").get("playername").toString().replace("\"", ""),
                                true);
                        hypixel.addField("\ud83c\udd94 | UUID | \ud83c\udd94 : ",
                                jsonObject.getAsJsonObject("player").get("uuid").toString().replace("\"", ""), true);
                        try {
                            Network_EXP = jsonObject.getAsJsonObject("player").get("networkExp").toString();
                        } catch (NumberFormatException e) {
                            Network_EXP = "0";
                        }
                        Network_EXP = Network_EXP.replace(".0", "");
                        Network_EXP = Network_EXP.replace(".", "");
                        Network_EXP = Network_EXP.replace("E7", "");
                        Network_EXP = Network_EXP.replace("E8", "");
                        int EXP2 = Integer.parseInt(Network_EXP);
                        int EXP22 = 10000;
                        int EXP_last = 0;
                        int EXP3 = 0;
                        int level = 1;
                        level = 1;
                        while (EXP3 < EXP2) {
                            EXP_last = EXP3 = EXP_last + (EXP22 += 2500);
                            ++level;
                        }
                        try {
                            Karma = jsonObject.getAsJsonObject("player").get("karma").toString();
                        } catch (Exception e) {
                            Karma = "0";
                        }
                        try {
                            achievementPoints = jsonObject.getAsJsonObject("player").get("achievementPoints")
                                    .toString();
                        } catch (Exception e) {
                            achievementPoints = "0";
                        }
                        try {
                            lastLogin = Long
                                    .parseLong(jsonObject.getAsJsonObject("player").get("lastLogin").toString());
                        } catch (Exception e) {
                            lastLogin = 0L;
                        }
                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.setTimeInMillis(lastLogin);
                        int mYear1 = calendar1.get(1);
                        int mMonth1 = calendar1.get(2) + 1;
                        int mDay1 = calendar1.get(5);
                        int mHour1 = calendar1.get(11);
                        int mMinute1 = calendar1.get(12);
                        int mSecond1 = calendar1.get(13);
                        try {
                            firstLogin = Long
                                    .parseLong(jsonObject.getAsJsonObject("player").get("firstLogin").toString());
                        } catch (Exception e) {
                            firstLogin = 0L;
                        }
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(firstLogin);
                        int mYear = calendar.get(1);
                        int mMonth = calendar.get(2) + 1;
                        int mDay = calendar.get(5);
                        int mHour = calendar.get(11);
                        int mMinute = calendar.get(12);
                        int mSecond = calendar.get(13);
                        try {
                            coins1 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("SkyWars").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins1 = 0;
                        }
                        try {
                            coins2 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Paintball").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins2 = 0;
                        }
                        try {
                            coins3 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Quake").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins3 = 0;
                        }
                        try {
                            coins4 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Battleground").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins4 = 0;
                        }
                        try {
                            coins5 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("GingerbreadPart").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins5 = 0;
                        }
                        try {
                            coins6 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins6 = 0;
                        }
                        try {
                            coins7 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Arcade").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins7 = 0;
                        }
                        try {
                            coins8 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("UHC").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins8 = 0;
                        }
                        try {
                            coins9 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("TrueCombat").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins9 = 0;
                        }
                        try {
                            coins10 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("TNTGames").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins10 = 0;
                        }
                        try {
                            coins11 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Walls").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins11 = 0;
                        }
                        try {
                            coins12 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("VampireZ").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins12 = 0;
                        }
                        try {
                            coins13 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Arena").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins13 = 0;
                        }
                        try {
                            coins14 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Walls3").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins14 = 0;
                        }
                        try {
                            coins15 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("HungerGames").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins15 = 0;
                        }
                        try {
                            coins16 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("SuperSmash").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins16 = 0;
                        }
                        try {
                            coins17 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("SkyClash").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins17 = 0;
                        }
                        try {
                            coins18 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MCGO").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins18 = 0;
                        }
                        try {
                            coins19 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("SpeedUHC").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins19 = 0;
                        }
                        try {
                            coins20 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("MurderMystery").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins20 = 0;
                        }
                        try {
                            coins21 = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("BuildBattle").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins21 = 0;
                        }
                        try {
                            coins22 = Integer
                                    .parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("achievements")
                                            .getAsJsonObject("blitz_coins").get("coins").toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins22 = 0;
                        }
                        try {
                            coins23 = Integer.parseInt(jsonObject.getAsJsonObject("player")
                                    .getAsJsonObject("achievements").getAsJsonObject("warlords_coins").get("coins")
                                    .toString().replace("\"", ""));
                        } catch (Exception e) {
                            coins23 = 0;
                        }
                        int totalCoins = coins1 + coins2 + coins3 + coins4 + coins5 + coins6 + coins7 + coins8 + coins9
                                + coins10 + coins11 + coins12 + coins13 + coins14 + coins15 + coins16 + coins17
                                + coins18 + coins19 + coins20 + coins21 + coins22 + coins23;
                        hypixel.addBlankField(false);
                        hypixel.addField("**\ud83c\udf40 | Karma :** ", Karma, true);
                        hypixel.addField("**\ud83c\udfae | EXP :** ", Network_EXP, true);
                        hypixel.addBlankField(false);
                        hypixel.addField("**\ud83d\udcaf | Level :** ", String.valueOf(level), true);
                        hypixel.addField("**\ud83d\udd6e | AchievementPoints :** ", String.valueOf(achievementPoints),
                                true);
                        hypixel.addField("**\ud83d\udcb0 | Global Coins :** ", String.valueOf(totalCoins), true);
                        hypixel.addBlankField(true);
                        hypixel.addField("**LastLogin :** ", String.valueOf(mDay1) + "/" + mMonth1 + "/" + mYear1 + " "
                                + mHour1 + ":" + mMinute1 + ":" + mSecond1, true);
                        hypixel.addField("**FirstLogin :** ", String.valueOf(mDay) + "/" + mMonth + "/" + mYear + " "
                                + mHour + ":" + mMinute + ":" + mSecond, true);
                        hypixel.setFooter(
                                "ranking : "
                                        + DiscordBot.getHypixeldata().getHypixelStats()
                                                .get(jsonObject.getAsJsonObject("player").get("uuid").toString()
                                                        .replace("\"", ""))
                                                .getLegacyRank()
                                        + "e / " + DiscordBot.getHypixeldata().getHypixelStats().size(),
                                "https://banner2.kisspng.com/20180501/oye/kisspng-minecraft-playstation-3-computer-servers-hypixel-v-vip-logo-5ae868aceded31.6203425515251805889746.jpg");
                        channel.sendMessage(hypixel.build()).queue();
                    }
                    if (c2.toLowerCase().equals("skywars")) {
                        String lucky;
                        String slime;
                        String tnt;
                        String rush;
                        String Temps_de_jeu = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("SkyWars").get("time_played").toString().replace("\"", "");
                        double Temps_De_Jeu = Integer.parseInt(Temps_de_jeu) / 60 / 60;
                        int Heure = new Double(Temps_De_Jeu).intValue();
                        EmbedBuilder hypixel2 = new EmbedBuilder();
                        hypixel2.setTitle("SkyWars Stats");
                        hypixel2.setColor(color.couleurAleatoire(user));
                        hypixel2.setThumbnail("https://cravatar.eu/head/"
                                + jsonObject.getAsJsonObject("player").get("uuid").toString().replace("\"", "")
                                + "?254");
                        hypixel2.addField("\ud83d\udc64 | Username | \ud83d\udc64 : ",
                                jsonObject.getAsJsonObject("player").get("playername").toString().replace("\"", ""),
                                true);
                        hypixel2.addField("\ud83c\udd94 | UUID | \ud83c\udd94 : ",
                                jsonObject.getAsJsonObject("player").get("uuid").toString().replace("\"", ""), true);
                        hypixel2.addBlankField(false);
                        hypixel2.addField("\u2694 | Kills | \u2694 : ",
                                jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("SkyWars")
                                        .get("kills").toString().replace("\"", ""),
                                true);
                        hypixel2.addField("\ud83c\udfae | Wins | \ud83c\udfae : ",
                                jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("SkyWars")
                                        .get("wins").toString().replace("\"", ""),
                                true);
                        hypixel2.addField(
                                String.valueOf(jda.getGuildById("326345972739473410")
                                        .getEmotesByName("minecraft_head", true).get(0).getAsMention())
                                        + " | Heads | "
                                        + jda.getGuildById("326345972739473410").getEmotesByName("minecraft_head", true)
                                                .get(0).getAsMention()
                                        + " : ",
                                jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("SkyWars")
                                        .get("heads").toString().replace("\"", ""),
                                true);
                        hypixel2.addField("\ud83d\udc7b | Souls | \ud83d\udc7b : ",
                                jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("SkyWars")
                                        .get("souls").toString().replace("\"", ""),
                                true);
                        hypixel2.addField("\ud83d\udcb0 | Coins | \ud83d\udcb0 : ",
                                jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("SkyWars")
                                        .get("coins").toString().replace("\"", ""),
                                true);
                        hypixel2.addField("\u23f2\ufe0f | Time to play | \u23f2\ufe0f : ", String.valueOf(Heure) + "H",
                                true);
                        hypixel2.addBlankField(false);
                        int kills_solo = Integer
                                .parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("achievements")
                                        .get("skywars_kills_solo").toString().replace("\"", ""));
                        int losses_solo = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("SkyWars").get("deaths_solo").toString().replace("\"", ""));
                        double KD_solo = (double) kills_solo / (double) losses_solo;
                        hypixel2.addField("\ud83d\udc64 | Solo | \ud83d\udc64 :", "\n**:trophy: | Kills** : "
                                + jsonObject.getAsJsonObject("player").getAsJsonObject("achievements")
                                        .get("skywars_kills_solo").toString().replace("\"", "")
                                + "\n**\u2694 | Win** : "
                                + jsonObject.getAsJsonObject("player").getAsJsonObject("achievements")
                                        .get("skywars_wins_solo").toString().replace("\"", "")
                                + "\n**\u2694 | K/D** : " + Utils.arrondi(2, KD_solo) + "\n**\ud83c\udf92 | Kits** : "
                                + jsonObject.getAsJsonObject("player").getAsJsonObject("achievements")
                                        .get("skywars_kits_solo").toString().replace("\"", ""),
                                true);
                        int kills_team = Integer
                                .parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("achievements")
                                        .get("skywars_kills_team").toString().replace("\"", ""));
                        int losses_team = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("SkyWars").get("deaths_team").toString().replace("\"", ""));
                        double KD_team = (double) kills_team / (double) losses_team;
                        hypixel2.addField("\ud83d\udc65 | Duo | \ud83d\udc65 :", "\n**:trophy: | Kills** : "
                                + jsonObject.getAsJsonObject("player").getAsJsonObject("achievements")
                                        .get("skywars_kills_team").toString().replace("\"", "")
                                + "\n**\u2694 | Win** : "
                                + jsonObject.getAsJsonObject("player").getAsJsonObject("achievements")
                                        .get("skywars_wins_team").toString().replace("\"", "")
                                + "\n**\u2694 | K/D** : " + Utils.arrondi(2, KD_team) + "\n**\ud83c\udf92 | Kits** : "
                                + jsonObject.getAsJsonObject("player").getAsJsonObject("achievements")
                                        .get("skywars_kits_team").toString().replace("\"", ""),
                                true);
                        int kills_mega = Integer
                                .parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("achievements")
                                        .get("skywars_kills_mega").toString().replace("\"", ""));
                        int losses_mega = Integer.parseInt(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("SkyWars").get("deaths_mega").toString().replace("\"", ""));
                        double KD_mega = (double) kills_mega / (double) losses_mega;
                        hypixel2.addField("\ud83c\udf10 | Mega | \ud83c\udf10 :", "\n**:trophy: | Kills** : "
                                + jsonObject.getAsJsonObject("player").getAsJsonObject("achievements")
                                        .get("skywars_kills_mega").toString().replace("\"", "")
                                + "\n**\u2694 | Win** : "
                                + jsonObject.getAsJsonObject("player").getAsJsonObject("achievements")
                                        .get("skywars_wins_mega").toString().replace("\"", "")
                                + "\n**\u2694 | K/D** : " + Utils.arrondi(2, KD_mega) + "\n**\ud83c\udf92 | Kits** : "
                                + jsonObject.getAsJsonObject("player").getAsJsonObject("achievements")
                                        .get("skywars_kits_mega").toString().replace("\"", ""),
                                true);
                        try {
                            lucky = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("SkyWars").get("lab_win_lucky_blocks_lab").toString()
                                    .replace("\"", "");
                        } catch (Exception e) {
                            lucky = "0";
                        }
                        try {
                            slime = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("SkyWars").get("lab_win_rush_lab").toString().replace("\"", "");
                        } catch (Exception e) {
                            slime = "0";
                        }
                        try {
                            tnt = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("SkyWars").get("lab_win_tnt_madness_lab").toString()
                                    .replace("\"", "");
                        } catch (Exception e) {
                            tnt = "0";
                        }
                        try {
                            rush = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("SkyWars").get("lab_win_rush_lab").toString().replace("\"", "");
                        } catch (Exception e) {
                            rush = "0";
                        }
                        hypixel2.addField(
                                String.valueOf(jda.getGuildById("326345972739473410").getEmotesByName("lab", true)
                                        .get(0).getAsMention())
                                        + " | Lab | "
                                        + jda.getGuildById("326345972739473410").getEmotesByName("lab", true).get(0)
                                                .getAsMention()
                                        + " :",
                                "\n**:trophy: | Wins Lucky** : " + lucky + "\n**:trophy: | Wins TNT** : " + tnt
                                        + "\n**:trophy: | Wins Rush** : " + rush + "\n**:trophy: | Wins Slime** : "
                                        + slime,
                                true);
                        hypixel2.setFooter("**ranking** : soon",
                                "https://banner2.kisspng.com/20180501/oye/kisspng-minecraft-playstation-3-computer-servers-hypixel-v-vip-logo-5ae868aceded31.6203425515251805889746.jpg");
                        channel.sendMessage(hypixel2.build()).queue();
                    }
                    if (c2.equals("pit")) {
                        String prestige;
                        Long connexion;
                        String cash;
                        String xp;
                        String mess;
                        String renown;
                        block280: {
                            hypixel = new EmbedBuilder();
                            hypixel.setTitle("SkyWars Stats");
                            hypixel.setColor(color.couleurAleatoire(user));
                            hypixel.setThumbnail("https://cravatar.eu/head/"
                                    + jsonObject.getAsJsonObject("player").get("uuid").toString().replace("\"", "")
                                    + "?254");
                            hypixel.addField("\ud83d\udc64 | Username | \ud83d\udc64 : ",
                                    jsonObject.getAsJsonObject("player").get("playername").toString().replace("\"", ""),
                                    true);
                            hypixel.addField("\ud83c\udd94 | UUID | \ud83c\udd94 : ",
                                    jsonObject.getAsJsonObject("player").get("uuid").toString().replace("\"", ""),
                                    true);
                            hypixel.addBlankField(false);
                            try {
                                prestige = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                        .getAsJsonObject("Pit").getAsJsonObject("profile").getAsJsonArray("prestiges")
                                        .get(jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                                .getAsJsonObject("Pit").getAsJsonObject("profile")
                                                .getAsJsonArray("prestiges").size() - 1)
                                        .getAsJsonObject().get("index").toString().replace("\"", "");
                            } catch (Exception e) {
                                prestige = "0";
                            }
                            try {
                                xp = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                        .getAsJsonObject("Pit").getAsJsonObject("profile").get("xp").toString()
                                        .replace("\"", "");
                            } catch (Exception e) {
                                xp = "0";
                            }
                            try {
                                renown = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                        .getAsJsonObject("Pit").getAsJsonObject("profile").get("renown").toString()
                                        .replace("\"", "");
                            } catch (Exception e) {
                                renown = "0";
                            }
                            try {
                                cash = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                        .getAsJsonObject("Pit").getAsJsonObject("profile").get("cash").toString()
                                        .replace("\"", "");
                            } catch (Exception e) {
                                cash = "0";
                            }
                            HashMap<String, HashMap<String, String>> renown_unlocks = new HashMap();
                            try {
                                for (int i = 0; i < jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                        .getAsJsonObject("Pit").getAsJsonObject("profile")
                                        .getAsJsonArray("renown_unlocks").size(); ++i) {
                                    HashMap<String, String> test = new HashMap<String, String>();
                                    test.put("key",
                                            jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                                    .getAsJsonObject("Pit").getAsJsonObject("profile")
                                                    .getAsJsonArray("renown_unlocks").get(i).getAsJsonObject()
                                                    .get("key").toString());
                                    test.put("tier",
                                            jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                                    .getAsJsonObject("Pit").getAsJsonObject("profile")
                                                    .getAsJsonArray("renown_unlocks").get(i).getAsJsonObject()
                                                    .get("tier").toString());
                                    String key2 = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                            .getAsJsonObject("Pit").getAsJsonObject("profile")
                                            .getAsJsonArray("renown_unlocks").get(i).getAsJsonObject().get("key")
                                            .toString();
                                    renown_unlocks.put(key2, test);
                                }
                            } catch (NullPointerException i) {
                                // empty catch block
                            }
                            mess = "";
                            try {
                                for (HashMap i : renown_unlocks.values()) {
                                    mess = String.valueOf(mess) + ((String) i.get("key")).replaceAll("\"", "")
                                            + " : **Tier** " + (Integer.parseInt((String) i.get("tier")) + 1) + " \n";
                                }
                            } catch (NullPointerException e) {
                                if (lang == command.Language.fr) {
                                    mess = "vous n'avez pas encore débloqué de d'amelioration grace aux renown.";
                                }
                                if (lang != command.Language.en)
                                    break block280;
                                mess = "You doesn't unlock any renown upgrade for the moment.";
                            }
                        }
                        try {
                            connexion = Long.parseLong(
                                    jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("Pit")
                                            .getAsJsonObject("profile").get("last_save").toString().replace("\"", ""));
                        } catch (Exception e) {
                            connexion = 1L;
                        }
                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.setTimeInMillis(connexion);
                        int mYear1 = calendar1.get(1);
                        int mMonth1 = calendar1.get(2) + 1;
                        int mDay1 = calendar1.get(5);
                        int mHour1 = calendar1.get(11);
                        int mMinute1 = calendar1.get(12);
                        int mSecond1 = calendar1.get(13);
                        hypixel.addField("Prestige", prestige, true);
                        hypixel.addField("Xp", xp, true);
                        hypixel.addField("Renown", renown, true);
                        hypixel.addField("Cash", cash, true);
                        hypixel.addField("Renown Upgrade", mess, true);
                        hypixel.addField("Last connexion", String.valueOf(mDay1) + "/" + mMonth1 + "/" + mYear1 + " "
                                + mHour1 + ":" + mMinute1 + ":" + mSecond1, true);
                        hypixel.setFooter(
                                "ranking : "
                                        + DiscordBot.getHypixeldata().getHypixelStats()
                                                .get(jsonObject.getAsJsonObject("player").get("uuid").toString()
                                                        .replace("\"", ""))
                                                .getPitRank()
                                        + "e / " + DiscordBot.getHypixeldata().getHypixelStats().size(),
                                "https://banner2.kisspng.com/20180501/oye/kisspng-minecraft-playstation-3-computer-servers-hypixel-v-vip-logo-5ae868aceded31.6203425515251805889746.jpg");
                        channel.sendMessage(hypixel.build()).queue();
                    }
                    if (c2.equals("bedwars")) {
                        String three_wins_bedwars;
                        String solo_wins_bedwars;
                        String three_kills_bedwars;
                        String Squad_Final_Kills;
                        String beds_broken_bedwars;
                        String t2_Death;
                        String solo_losses_bedwars;
                        String t1_Death;
                        String deaths;
                        String t4_Kills;
                        String wins2;
                        String t2_Kills;
                        String t4_Death;
                        String Solo_Final_Kills;
                        String duo_kills_bedwars;
                        String t3_Death;
                        String kills;
                        String t1_Kills;
                        String t3_Kills;
                        String three_beds_broken_bedwars;
                        String three_losses_bedwars;
                        String duo_death_bedwars;
                        String four_wins_bedwars;
                        String losses;
                        String four_losses_bedwars;
                        String three_death_bedwars;
                        String four_beds_broken_bedwars;
                        String duo_losses_bedwars;
                        String Three_Final_Kills;
                        String Duo_Final_Kills;
                        String level;
                        String solo_beds_broken_bedwars;
                        String solo_kills_bedwars;
                        String solo_death_bedwars;
                        String four_death_bedwars;
                        String duo_beds_broken_bedwars;
                        String coins;
                        String parties_jouées;
                        String duo_wins_bedwars;
                        String four_kills_bedwars;
                        hypixel = new EmbedBuilder();
                        hypixel.setTitle("BedWars Stats");
                        hypixel.setColor(color.couleurAleatoire(user));
                        hypixel.setThumbnail("https://cravatar.eu/head/"
                                + jsonObject.getAsJsonObject("player").get("uuid").toString().replace("\"", "")
                                + "?254");
                        hypixel.addField("\ud83d\udc64 | Username | \ud83d\udc64 : ",
                                jsonObject.getAsJsonObject("player").get("playername").toString().replace("\"", ""),
                                true);
                        hypixel.addField("\ud83c\udd94 | UUID | \ud83c\udd94 : ",
                                jsonObject.getAsJsonObject("player").get("uuid").toString().replace("\"", ""), true);
                        hypixel.addBlankField(false);
                        try {
                            coins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("coins").toString().replace("\"", "");
                        } catch (NullPointerException e) {
                            coins = "0";
                        }
                        try {
                            level = jsonObject.getAsJsonObject("player").getAsJsonObject("achievements")
                                    .get("bedwars_level").toString().replace("\"", "");
                        } catch (NullPointerException e) {
                            level = "0";
                        }
                        try {
                            kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("kills_bedwars").toString().replace("\"", "");
                        } catch (NullPointerException e) {
                            kills = "0";
                        }
                        try {
                            deaths = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("deaths_bedwars").toString().replace("\"", "");
                        } catch (NullPointerException e) {
                            deaths = "0";
                        }
                        try {
                            parties_jouées = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("games_played_bedwars_1").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            parties_jouées = "0";
                        }
                        hypixel.addField("\ud83d\udcb0 | Coins | \ud83d\udcb0 : ", coins, true);
                        hypixel.addField("\ud83d\udcaf | Level | \ud83d\udcaf : ", level, true);
                        hypixel.addField("\u2694 | Kills | \u2694  : ", kills, true);
                        hypixel.addField("\u2620  | Deaths | \u2620  : ", deaths, true);
                        hypixel.addField("\ud83c\udfae | Game played | \ud83c\udfae : ", parties_jouées, true);
                        try {
                            beds_broken_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("beds_broken_bedwars").toString().replace("\"", "");
                        } catch (NullPointerException e) {
                            beds_broken_bedwars = "0";
                        }
                        try {
                            wins2 = jsonObject.getAsJsonObject("player").getAsJsonObject("achievements")
                                    .get("bedwars_wins").toString().replace("\"", "");
                        } catch (NullPointerException e) {
                            wins2 = "0";
                        }
                        try {
                            losses = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("losses_bedwars").toString().replace("\"", "");
                        } catch (NullPointerException e) {
                            losses = "0";
                        }
                        hypixel.addField("\ufffd?\ufffd? | Bed Destroyed | \ufffd?\ufffd? :  ", beds_broken_bedwars,
                                true);
                        hypixel.addField("\ud83d\udc7b | Losses | \ud83d\udc7b  : ", losses, true);
                        hypixel.addField(":trophy:  | Wins | :trophy:  : ", wins2, true);
                        hypixel.addBlankField(false);
                        DecimalFormat arondi = new DecimalFormat();
                        arondi.setMaximumFractionDigits(2);
                        arondi.setMinimumFractionDigits(2);
                        try {
                            t1_Kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("eight_one_kills_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            t1_Kills = "0";
                        }
                        try {
                            t1_Death = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("eight_one_deaths_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            t1_Death = "0";
                        }
                        try {
                            Solo_Final_Kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("eight_one_final_kills_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            Solo_Final_Kills = "0";
                        }
                        try {
                            solo_beds_broken_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("eight_one_beds_broken_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            solo_beds_broken_bedwars = "0";
                        }
                        try {
                            solo_wins_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("eight_one_wins_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            solo_wins_bedwars = "0";
                        }
                        try {
                            solo_kills_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("eight_one_kills_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            solo_kills_bedwars = "0";
                        }
                        try {
                            solo_death_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("eight_one_deaths_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            solo_death_bedwars = "0";
                        }
                        try {
                            solo_losses_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("eight_one_losses_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            solo_losses_bedwars = "0";
                        }
                        Double t1_Kills2 = Double.parseDouble(t1_Kills);
                        Double t1_Death2 = Double.parseDouble(t1_Death);
                        Double t1_kd = t1_Kills2 / t1_Death2;
                        String t1_kd_bedwars = arondi.format(t1_kd);
                        hypixel.addField("\ud83d\udc64 | Solo | \ud83d\udc64 : ",
                                "\n**\u2694 | Kills** : " + solo_kills_bedwars + "\n**\u2620 | Deaths** :"
                                        + solo_death_bedwars + "\n**:trophy:  | Win** :" + solo_wins_bedwars
                                        + "\n**\ud83d\udc7b | Losses** :" + solo_losses_bedwars
                                        + "\n**\ud83d\udecf\ufe0f | Bed Broken** : " + solo_beds_broken_bedwars
                                        + "\n**\u2694 | Final Kills** : " + Solo_Final_Kills + "\n**\u2694 | K/D** : "
                                        + t1_kd_bedwars,
                                true);
                        try {
                            t2_Kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("eight_two_kills_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            t2_Kills = "0";
                        }
                        try {
                            t2_Death = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("eight_two_deaths_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            t2_Death = "0";
                        }
                        try {
                            Duo_Final_Kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("eight_two_final_kills_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            Duo_Final_Kills = "0";
                        }
                        try {
                            duo_beds_broken_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("eight_two_beds_broken_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            duo_beds_broken_bedwars = "0";
                        }
                        try {
                            duo_kills_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("eight_two_kills_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            duo_kills_bedwars = "0";
                        }
                        try {
                            duo_death_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("eight_two_deaths_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            duo_death_bedwars = "0";
                        }
                        try {
                            duo_wins_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("eight_two_wins_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            duo_wins_bedwars = "0";
                        }
                        try {
                            duo_losses_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("eight_two_losses_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            duo_losses_bedwars = "0";
                        }
                        Double t2_Kills2 = Double.parseDouble(t2_Kills);
                        Double t2_Death2 = Double.parseDouble(t2_Death);
                        Double t2_kd = t2_Kills2 / t2_Death2;
                        String t2_kd_bedwars = arondi.format(t2_kd);
                        hypixel.addField("\ud83d\udc65 | Duo | \ud83d\udc65 : ",
                                "\n**\u2694 | Kills** : " + duo_kills_bedwars + "\n**\u2620 | Deaths** :"
                                        + duo_death_bedwars + "\n**:trophy: | Win** :" + duo_wins_bedwars
                                        + "\n**\ud83d\udc7b | Losses** :" + duo_losses_bedwars
                                        + "\n**\ud83d\udecf\ufe0f | Bed Broken** : " + duo_beds_broken_bedwars
                                        + "\n**\u2694 | Final Kills** : " + Duo_Final_Kills + "\n**\u2694 | K/D** : "
                                        + t2_kd_bedwars,
                                true);
                        try {
                            t3_Kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("four_three_kills_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            t3_Kills = "0";
                        }
                        try {
                            t3_Death = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("four_three_deaths_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            t3_Death = "0";
                        }
                        try {
                            Three_Final_Kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("four_three_final_kills_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            Three_Final_Kills = "0";
                        }
                        try {
                            three_beds_broken_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("four_three_beds_broken_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            three_beds_broken_bedwars = "0";
                        }
                        try {
                            three_kills_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("four_three_kills_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            three_kills_bedwars = "0";
                        }
                        try {
                            three_death_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("four_three_deaths_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            three_death_bedwars = "0";
                        }
                        try {
                            three_wins_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("four_three_wins_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            three_wins_bedwars = "0";
                        }
                        try {
                            three_losses_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("four_three_losses_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            three_losses_bedwars = "0";
                        }
                        Double t3_Kills2 = Double.parseDouble(t3_Kills);
                        Double t3_Death2 = Double.parseDouble(t3_Death);
                        Double t3_kd = t3_Kills2 / t3_Death2;
                        String t3_kd_bedwars = arondi.format(t3_kd);
                        hypixel.addField("\ud83d\udc65 \ud83d\udc64 | 3v3v3v3 | \ud83d\udc65 \ud83d\udc64 : ",
                                "\n**\u2694 | Kills** : " + three_kills_bedwars + "\n**\u2620 | Deaths** :"
                                        + three_death_bedwars + "\n**:trophy: | Win** :" + three_wins_bedwars
                                        + "\n**\ud83d\udc7b | Losses** :" + three_losses_bedwars
                                        + "\n**\ud83d\udecf\ufe0f | Bed Broken** : " + three_beds_broken_bedwars
                                        + "\n**\u2694 | Final Kills** : " + Three_Final_Kills + "\n**\u2694 | K/D** : "
                                        + t3_kd_bedwars,
                                true);
                        try {
                            t4_Kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("four_four_kills_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            t4_Kills = "0";
                        }
                        try {
                            t4_Death = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("four_four_deaths_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            t4_Death = "0";
                        }
                        try {
                            Squad_Final_Kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("four_four_final_kills_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            Squad_Final_Kills = "0";
                        }
                        try {
                            four_beds_broken_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("four_four_beds_broken_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            four_beds_broken_bedwars = "0";
                        }
                        try {
                            four_kills_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("four_four_kills_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            four_kills_bedwars = "0";
                        }
                        try {
                            four_death_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("four_four_deaths_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            four_death_bedwars = "0";
                        }
                        try {
                            four_wins_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("four_four_wins_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            four_wins_bedwars = "0";
                        }
                        try {
                            four_losses_bedwars = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                    .getAsJsonObject("Bedwars").get("four_four_losses_bedwars").toString()
                                    .replace("\"", "");
                        } catch (NullPointerException e) {
                            four_losses_bedwars = "0";
                        }
                        Double t4_Kills2 = Double.parseDouble(t4_Kills);
                        Double t4_Death2 = Double.parseDouble(t4_Death);
                        Double t4_kd = t4_Kills2 / t4_Death2;
                        String t4_kd_bedwars = arondi.format(t4_kd);
                        hypixel.addField("\ud83d\udc65\ud83d\udc65 | 4v4v4v4 | \ud83d\udc65\ud83d\udc65 : ",
                                "\n**\u2694 | Kills** : " + four_kills_bedwars + "\n**\u2620 | Deaths** :"
                                        + four_death_bedwars + "\n**:trophy: | Win** :" + four_wins_bedwars
                                        + "\n**\ud83d\udc7b | Losses** :" + four_losses_bedwars
                                        + "\n**\ud83d\udecf\ufe0f | Bed Broken** : " + four_beds_broken_bedwars
                                        + "\n**\u2694 | Final Kills** : " + Squad_Final_Kills + "\n**\u2694 | K/D** : "
                                        + t4_kd_bedwars,
                                true);
                        channel.sendMessage(hypixel.build()).queue();
                        break block281;
                    }
                    if (!c2.equals("TnTGames"))
                        break block281;
                    hypixel = new EmbedBuilder();
                    hypixel.setTitle("TnT Games Stats");
                    hypixel.setColor(color.couleurAleatoire(user));
                    hypixel.setThumbnail("https://cravatar.eu/head/"
                            + jsonObject.getAsJsonObject("player").get("uuid").toString().replace("\"", "") + "?254");
                    hypixel.addField("\ud83d\udc64 | Username | \ud83d\udc64 : ",
                            jsonObject.getAsJsonObject("player").get("playername").toString().replace("\"", ""), true);
                    hypixel.addField("\ud83c\udd94 | UUID | \ud83c\udd94 : ",
                            jsonObject.getAsJsonObject("player").get("uuid").toString().replace("\"", ""), true);
                    hypixel.addBlankField(false);
                    String money = "0";
                    try {
                        money = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("TNTGames").get("coins").toString().replace("\"", "");
                    } catch (NullPointerException level) {
                        // empty catch block
                    }
                    String wins = "0";
                    try {
                        wins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("TNTGames")
                                .get("wins").toString().replace("\"", "");
                    } catch (NullPointerException kills) {
                        // empty catch block
                    }
                    hypixel.addField("\ud83d\udcb0 | money", money, true);
                    hypixel.addField(":trophy: | Wins", wins, true);
                    hypixel.addBlankField(false);
                    String TnTRun_wins = "0";
                    try {
                        TnTRun_wins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("TNTGames").get("wins_tntrun").toString().replace("\"", "");
                    } catch (NullPointerException deaths) {
                        // empty catch block
                    }
                    String TnTRun_Deaths = "0";
                    try {
                        TnTRun_Deaths = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("TNTGames").get("deaths_tntrun").toString().replace("\"", "");
                    } catch (NullPointerException parties_jouées) {
                        // empty catch block
                    }
                    int TnTRunDoubleJump = 0;
                    try {
                        TnTRunDoubleJump = Integer.parseInt(jsonObject.getAsJsonObject("player")
                                .getAsJsonObject("stats").getAsJsonObject("TNTGames")
                                .get("new_tntrun_double_jumps_legacy").toString().replace("\"", "")) + 1;
                    } catch (NullPointerException beds_broken_bedwars) {
                    } catch (NumberFormatException beds_broken_bedwars) {
                        // empty catch block
                    }
                    String TnTRun_SpeedPotion = "0";
                    try {
                        TnTRun_SpeedPotion = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("TNTGames").get("new_tntrun_speed_potions").toString()
                                .replace("\"", "");
                    } catch (NullPointerException wins2) {
                        // empty catch block
                    }
                    String TnTRun_SlowPotion = "0";
                    try {
                        TnTRun_SlowPotion = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("TNTGames").get("new_tntrun_slowness_potions").toString()
                                .replace("\"", "");
                    } catch (NullPointerException losses) {
                        // empty catch block
                    }
                    hypixel.addField("\ud83d\udca5 | TnTRun : ",
                            "\n **Wins** : " + TnTRun_wins + "\n" + " **Death** : " + TnTRun_Deaths + "\n"
                                    + " **Double Jump** : " + TnTRunDoubleJump + "\n" + " **Speed potion** : "
                                    + TnTRun_SpeedPotion + "\n" + " **Slowness potion** : " + TnTRun_SlowPotion,
                            true);
                    String PvPRun_wins = "0";
                    try {
                        PvPRun_wins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("TNTGames").get("wins_pvprun").toString().replace("\"", "");
                    } catch (NullPointerException arondi) {
                        // empty catch block
                    }
                    String PvPRun_kills = "0";
                    try {
                        PvPRun_kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("TNTGames").get("kills_pvprun").toString().replace("\"", "");
                    } catch (NullPointerException t1_Kills) {
                        // empty catch block
                    }
                    String PvPRun_Deaths = "0";
                    try {
                        PvPRun_Deaths = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("TNTGames").get("deaths_pvprun").toString().replace("\"", "");
                    } catch (NullPointerException t1_Death) {
                        // empty catch block
                    }
                    String PvPRun_Record = "0";
                    try {
                        PvPRun_Record = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("TNTGames").get("record_pvprun").toString().replace("\"", "");
                    } catch (NullPointerException Solo_Final_Kills) {
                        // empty catch block
                    }
                    String PvPRun_DoubleJump = "0";
                    try {
                        PvPRun_DoubleJump = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("TNTGames").get("new_pvprun_double_jumps").toString()
                                .replace("\"", "");
                    } catch (NullPointerException solo_beds_broken_bedwars) {
                        // empty catch block
                    }
                    hypixel.addField("\u2694 | PvPRun : ",
                            "\n **Wins** : " + PvPRun_wins + "\n **Kills** : " + PvPRun_kills + "\n **Death** : "
                                    + PvPRun_Deaths + "\n **Double Jump** : " + PvPRun_DoubleJump
                                    + "\n **Survival record** : " + PvPRun_Record,
                            true);
                    String BowSpleef_Deaths = "0";
                    try {
                        BowSpleef_Deaths = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("TNTGames").get("deaths_bowspleef").toString().replace("\"", "");
                    } catch (NullPointerException solo_wins_bedwars) {
                        // empty catch block
                    }
                    String BowSpleef_wins = "0";
                    try {
                        BowSpleef_wins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("TNTGames").get("wins_bowspleef").toString().replace("\"", "");
                    } catch (NullPointerException solo_kills_bedwars) {
                        // empty catch block
                    }
                    String BowSpleef_tag = "0";
                    try {
                        BowSpleef_tag = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("TNTGames").get("tags_bowspleef").toString().replace("\"", "");
                    } catch (NullPointerException solo_death_bedwars) {
                        // empty catch block
                    }
                    hypixel.addField("\ud83c\udff9 | BowSpleef : ", "\n **Wins** : " + BowSpleef_wins
                            + "\n **Death** : " + BowSpleef_Deaths + "\n **Tag** : " + BowSpleef_tag, true);
                    String TnTag_Kills = "0";
                    try {
                        TnTag_Kills = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("TNTGames").get("kills_tntag").toString().replace("\"", "");
                    } catch (NullPointerException solo_losses_bedwars) {
                        // empty catch block
                    }
                    String TnTag_Wins = "0";
                    try {
                        TnTag_Wins = jsonObject.getAsJsonObject("player").getAsJsonObject("stats")
                                .getAsJsonObject("TNTGames").get("kills_tntag").toString().replace("\"", "");
                    } catch (NullPointerException t1_kd) {
                        // empty catch block
                    }
                    hypixel.addField("\ud83d\udca5 | TnTag : ",
                            "\n **Wins** : " + TnTag_Wins + "\n **Kills** : " + TnTag_Kills, true);
                    channel.sendMessage(hypixel.build()).queue();
                } catch (IOException e) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("**ERROR** \n Cause : Maintenance Hypixel").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("**ERROR** \n Cause : Hypixel Maintenance").queue();
                    }
                    return;
                }
            } catch (Exception e) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Syntaxe : ``=hypixel [TnTGames/bedwars/skywars/pit/murder] [joueur]``")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Syntax : ``=hypixel [TnTGames/bedwars/skywars/pit/murder] [player]``").queue();
                }
                return;
            }
        }
    }
}
