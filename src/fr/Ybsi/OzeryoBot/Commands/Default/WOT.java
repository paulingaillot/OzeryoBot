/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Default;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import fr.Ybsi.OzeryoBot.Utils.Utils;
import fr.Ybsi.OzeryoBot.Utils.color;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.List;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class WOT {
    @command(name="wot", type=command.ExecutorType.ALL)
    private void wot(User user, MessageChannel channel, String[] args, Guild guild, JDA jda, command.Language lang) {
        try {
            JsonObject jsonObject;
            StringBuilder builder = new StringBuilder();
            for (String str : args) {
                if (str.equals(args[0])) continue;
                builder.append(String.valueOf(str));
            }
            String c1 = args[0];
            String c2 = builder.toString();
            String id = null;
            URL url = null;
            HttpURLConnection con = null;
            try {
                String inputLine;
                String key = TextFileWriter.read("/home/DiscordBot/Rasberry/key/WotKey.txt");
                url = new URL("https://api.worldoftanks.eu/wot/account/list/?application_id=" + key + "&search=" + c1);
                con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", "Java client");
                con.setRequestProperty("Content-Type", "application/json");
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();
                try {
                    id = jsonObject.getAsJsonArray("data").get(0).getAsJsonObject().get("account_id").toString();
                    System.out.println(id);
                }
                catch (IndexOutOfBoundsException e) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Joueur introuvable.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("Player not found.").queue();
                    }
                    return;
                }
                url = new URL("https://api.worldoftanks.eu/wot/account/info/?application_id=" + key + "&account_id=" + id);
                con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", "Java client");
                con.setRequestProperty("Content-Type", "application/json");
                in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();
            }
            catch (IOException e) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Une erreur est survenu veuillez reessayer plus tard.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("An error occured, retry in few minutes.").queue();
                }
                return;
            }
            EmbedBuilder builder1 = new EmbedBuilder();
            builder1.setTitle(String.valueOf(c1) + " WOT Stats");
            builder1.setColor(color.couleurAleatoire(user));
            builder1.setAuthor(user.getName(), null, user.getAvatarUrl());
            builder1.setThumbnail("https://logos-download.com/wp-content/uploads/2016/02/world-of-tanks.png");
            builder1.setFooter(guild.getName(), guild.getIconUrl());
            if (lang == command.Language.fr) {
                builder1.addField(String.valueOf(jda.getGuildById("326345972739473410").getEmotesByName("wot", true).get(0).getAsMention()) + " Cote Personnel", jsonObject.getAsJsonObject("data").getAsJsonObject(id).get("global_rating").toString(), true);
            }
            if (lang == command.Language.fr) {
                builder1.addField(String.valueOf(jda.getGuildById("326345972739473410").getEmotesByName("wot", true).get(0).getAsMention()) + " Personal Rating", jsonObject.getAsJsonObject("data").getAsJsonObject(id).get("global_rating").toString(), true);
            }
            Long lastbataille = new Long(String.valueOf(jsonObject.getAsJsonObject("data").getAsJsonObject(id).get("last_battle_time").toString()) + "000");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(lastbataille);
            int mYear = calendar.get(1);
            int mMonth = calendar.get(2) + 1;
            int mDay = calendar.get(5);
            int mHour = calendar.get(11);
            int mMinute = calendar.get(12);
            int mSecond = calendar.get(13);
            if (lang == command.Language.fr) {
                builder1.addField("\ud83d\udd5b Derni\u00e8re bataille", String.valueOf(mDay) + "/" + mMonth + "/" + mYear + " " + mHour + ":" + mMinute + ":" + mSecond, true);
            }
            if (lang == command.Language.en) {
                builder1.addField("\ud83d\udd5b Last battle", String.valueOf(mDay) + "/" + mMonth + "/" + mYear + " " + mHour + ":" + mMinute + ":" + mSecond, true);
            }
            Long lastUpdate = new Long(String.valueOf(jsonObject.getAsJsonObject("data").getAsJsonObject(id).get("updated_at").toString()) + "000");
            calendar = Calendar.getInstance();
            calendar.setTimeInMillis(lastUpdate);
            mYear = calendar.get(1);
            mMonth = calendar.get(2) + 1;
            mDay = calendar.get(5);
            mHour = calendar.get(11);
            mMinute = calendar.get(12);
            mSecond = calendar.get(13);
            if (lang == command.Language.fr) {
                builder1.addField("\ud83d\udd5b Derniere mise a jour des stats", String.valueOf(mDay) + "/" + mMonth + "/" + mYear + " " + mHour + ":" + mMinute + ":" + mSecond, true);
            }
            if (lang == command.Language.en) {
                builder1.addField("\ud83d\udd5bLast update of your stats", String.valueOf(mDay) + "/" + mMonth + "/" + mYear + " " + mHour + ":" + mMinute + ":" + mSecond, true);
            }
            builder1.addBlankField(false);
            if (lang == command.Language.fr) {
                builder1.addField("\ud83d\udcaf EXP par partie", jsonObject.getAsJsonObject("data").getAsJsonObject(id).getAsJsonObject("statistics").getAsJsonObject("all").get("battle_avg_xp").toString(), true);
            }
            if (lang == command.Language.en) {
                builder1.addField("\ud83d\udcaf Xp per game", jsonObject.getAsJsonObject("data").getAsJsonObject(id).getAsJsonObject("statistics").getAsJsonObject("all").get("battle_avg_xp").toString(), true);
            }
            if (lang == command.Language.fr) {
                builder1.addField("\ud83d\udca5 Degat par partie", jsonObject.getAsJsonObject("data").getAsJsonObject(id).getAsJsonObject("statistics").getAsJsonObject("all").get("avg_damage_assisted").toString(), true);
            }
            if (lang == command.Language.en) {
                builder1.addField("\ud83d\udca5 Damage per game", jsonObject.getAsJsonObject("data").getAsJsonObject(id).getAsJsonObject("statistics").getAsJsonObject("all").get("avg_damage_assisted").toString(), true);
            }
            if (lang == command.Language.fr) {
                builder1.addField("\ud83d\udca5 Degat moyen bloqués par partie", jsonObject.getAsJsonObject("data").getAsJsonObject(id).getAsJsonObject("statistics").getAsJsonObject("all").get("avg_damage_blocked").toString(), true);
            }
            if (lang == command.Language.en) {
                builder1.addField("\ud83d\udca5 Average damage blocked per game", jsonObject.getAsJsonObject("data").getAsJsonObject(id).getAsJsonObject("statistics").getAsJsonObject("all").get("avg_damage_blocked").toString(), true);
            }
            if (lang == command.Language.fr) {
                builder1.addField("\ud83c\udfae Parties joués", jsonObject.getAsJsonObject("data").getAsJsonObject(id).getAsJsonObject("statistics").getAsJsonObject("all").get("battles").toString(), true);
            }
            if (lang == command.Language.en) {
                builder1.addField("\ud83c\udfae Games played", jsonObject.getAsJsonObject("data").getAsJsonObject(id).getAsJsonObject("statistics").getAsJsonObject("all").get("battles").toString(), true);
            }
            if (lang == command.Language.fr) {
                builder1.addField("% de coup au but", String.valueOf(jsonObject.getAsJsonObject("data").getAsJsonObject(id).getAsJsonObject("statistics").getAsJsonObject("all").get("hits_percents").toString()) + "%", true);
            }
            if (lang == command.Language.en) {
                builder1.addField("% of shot on goal", String.valueOf(jsonObject.getAsJsonObject("data").getAsJsonObject(id).getAsJsonObject("statistics").getAsJsonObject("all").get("hits_percents").toString()) + "%", true);
            }
            if (lang == command.Language.fr) {
                builder1.addField("\ud83c\udfc6 Parties gagnés", jsonObject.getAsJsonObject("data").getAsJsonObject(id).getAsJsonObject("statistics").getAsJsonObject("all").get("wins").toString(), true);
            }
            if (lang == command.Language.en) {
                builder1.addField("\ud83c\udfc6 Games win", jsonObject.getAsJsonObject("data").getAsJsonObject(id).getAsJsonObject("statistics").getAsJsonObject("all").get("wins").toString(), true);
            }
            Double win = Double.parseDouble(jsonObject.getAsJsonObject("data").getAsJsonObject(id).getAsJsonObject("statistics").getAsJsonObject("all").get("wins").toString());
            Double losses = Double.parseDouble(jsonObject.getAsJsonObject("data").getAsJsonObject(id).getAsJsonObject("statistics").getAsJsonObject("all").get("losses").toString());
            Double ratio = win / losses;
            ratio = Utils.arrondi(2, ratio);
            builder1.addField("\ud83d\udd30 Ratio Wins/Losses", ratio + "%", true);
            if (lang == command.Language.fr) {
                builder1.addField("\ud83d\udc64 Parties survecues", jsonObject.getAsJsonObject("data").getAsJsonObject(id).getAsJsonObject("statistics").getAsJsonObject("all").get("survived_battles").toString(), true);
            }
            if (lang == command.Language.en) {
                builder1.addField("\ud83d\udc64 Survived Games", jsonObject.getAsJsonObject("data").getAsJsonObject(id).getAsJsonObject("statistics").getAsJsonObject("all").get("survived_battles").toString(), true);
            }
            Double games = Double.parseDouble(jsonObject.getAsJsonObject("data").getAsJsonObject(id).getAsJsonObject("statistics").getAsJsonObject("all").get("battles").toString());
            Double partiesSurvived = Double.parseDouble(jsonObject.getAsJsonObject("data").getAsJsonObject(id).getAsJsonObject("statistics").getAsJsonObject("all").get("survived_battles").toString());
            ratio = partiesSurvived / games;
            ratio = Utils.arrondi(2, ratio);
            if (lang == command.Language.fr) {
                builder1.addField("\ufffd?\ufffd Ratio Parties survecues/ Parties joués", ratio + "%", true);
            }
            if (lang == command.Language.en) {
                builder1.addField("\ufffd?\ufffd Ratio Survived games/ Games played", ratio + "%", true);
            }
            channel.sendMessage(builder1.build()).queue();
        }
        catch (Exception e) {
            if (lang == command.Language.fr) {
                channel.sendMessage("Syntaxe : ``=wot [joueur]``").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("Syntaxe : ``=wot [player]``").queue();
            }
            return;
        }
    }
}

