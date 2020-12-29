/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Default;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import fr.Ybsi.OzeryoBot.Utils.color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class Fortnite {
    public String arondi(double arg) {
        DecimalFormat arondi = new DecimalFormat();
        arondi.setMaximumFractionDigits(2);
        arondi.setMinimumFractionDigits(2);
        String arg2 = arondi.format(arg);
        return arg2;
    }

    public String separateur(String arg) {
        DecimalFormat arondi = new DecimalFormat();
        arondi.setGroupingSize(3);
        String arg2 = arondi.format(arg);
        return arg2;
    }

    @command(name = "fortnite", abbrev = "ftn", type = command.ExecutorType.ALL, descfr = "usage : =fortnite [plateforme] [user]\n permet d'afficher les stats fortnite d'un joueurs")
    private void fortnite(MessageChannel message, Guild guild, String[] args, User user, command.Language lang) {
        block60:
        {
            String username;
            String plateforme;
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/fortnite/");
            StringBuilder builder = new StringBuilder();
            for (String str : args) {
                if (str.equals(args[0]) || str.equals(args[1]))
                    continue;
                if (builder.length() > 0) {
                    builder.append(" ");
                }
                builder.append(str);
            }
            try {
                plateforme = args[0];
            } catch (ArrayIndexOutOfBoundsException e) {
                plateforme = "";
            }
            try {
                username = args[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                username = "";
            }
            String c2 = builder.toString();
            System.out.println(username);
            System.out.println(plateforme);
            System.out.println(c2);
            if (username.equals("") && plateforme.equals("") && c2.equals("")) {
                username = TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/fortnite/username.txt");
                plateforme = TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/fortnite/plateforme.txt");
                c2 = "";
            } else if (username.equals("") && c2.equals("")) {
                if (plateforme.equals("last") || plateforme.equals("solo") || plateforme.equals("duo")
                        || plateforme.equals("section")) {
                    c2 = plateforme;
                    plateforme = TextFileWriter.read(
                            "/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/fortnite/plateforme.txt");
                    username = TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/fortnite/username.txt");
                } else {
                    username = plateforme;
                    plateforme = "pc";
                    c2 = "";
                }
            } else if (c2.equals("") && !plateforme.equals("pc") && !plateforme.equals("ps4")) {
                c2 = username;
                username = plateforme;
                plateforme = "pc";
            }
            URL url = null;
            HttpURLConnection con = null;
            try {
                String inputLine;
                url = new URL("https://api.fortnitetracker.com/v1/profile/" + plateforme + "/" + username);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", "Java client");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("TRN-Api-Key",
                        TextFileWriter.read("/home/DiscordBot/Rasberry/key/TrackerNetworkKey.txt"));
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                if (c2.equals("")) {
                    EmbedBuilder buider = new EmbedBuilder();
                    JsonObject jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();
                    buider.setColor(color.couleurAleatoire(user));
                    buider.setAuthor(user.getName(), null, "http://ozeryo.sytes.net/images/Logo_Fortnite.png");
                    buider.setTitle("LifeTime Stats");
                    if (lang == command.Language.fr) {
                        buider.addField("\ud83d\udd79 | Plateforme | \ud83d\udd79\ufffd?",
                                jsonObject.get("platformName").toString().replace("\"", ""), true);
                    }
                    if (lang == command.Language.en) {
                        buider.addField("\ud83d\udd79 | Platform | \ud83d\udd79\ufffd?",
                                jsonObject.get("platformName").toString().replace("\"", ""), true);
                    }
                    buider.addField("\ud83d\udc64 | Username | \ud83d\udc64",
                            jsonObject.get("epicUserHandle").toString().replace("\"", ""), true);
                    buider.addBlankField(false);
                    buider.addField("\ud83c\udfc6 | Win | \ud83c\udfc6",
                            ((JsonObject) jsonObject.getAsJsonArray("lifeTimeStats").get(8)).get("value").toString()
                                    .replace("\"", ""),
                            true);
                    buider.addField("\ud83c\udfc6 | Win % | \ud83c\udfc6",
                            ((JsonObject) jsonObject.getAsJsonArray("lifeTimeStats").get(9)).get("value").toString()
                                    .replace("\"", ""),
                            true);
                    buider.addField("\ud83d\udcaf | Score | \ud83d\udcaf",
                            ((JsonObject) jsonObject.getAsJsonArray("lifeTimeStats").get(6)).get("value").toString()
                                    .replace("\"", ""),
                            true);
                    if (lang == command.Language.fr) {
                        buider.addField("\ud83c\udfae | Matchs Joués | \ud83c\udfae",
                                ((JsonObject) jsonObject.getAsJsonArray("lifeTimeStats").get(7)).get("value").toString()
                                        .replace("\"", ""),
                                true);
                    }
                    if (lang == command.Language.fr) {
                        buider.addField("\ud83c\udfae | Played Matches | \ud83c\udfae",
                                ((JsonObject) jsonObject.getAsJsonArray("lifeTimeStats").get(7)).get("value").toString()
                                        .replace("\"", ""),
                                true);
                    }
                    buider.addField("\u2620 | Kills | \u2620",
                            ((JsonObject) jsonObject.getAsJsonArray("lifeTimeStats").get(10)).get("value").toString()
                                    .replace("\"", ""),
                            true);
                    buider.addField("\u2620 | K/D | \u2620",
                            ((JsonObject) jsonObject.getAsJsonArray("lifeTimeStats").get(11)).get("value").toString()
                                    .replace("\"", ""),
                            true);
                    buider.addBlankField(false);
                    buider.addField("\ud83d\udc64  | __**Solo**__ | \ud83d\udc64",
                            "**K/D** : " + jsonObject.getAsJsonObject("stats").getAsJsonObject("p2")
                                    .getAsJsonObject("kd").get("value").toString().replace("\"", "") + " \n"
                                    + "**Matches** : "
                                    + jsonObject.getAsJsonObject("stats").getAsJsonObject("p2")
                                    .getAsJsonObject("matches").get("value").toString().replace("\"", "")
                                    + "\n" + "**Kills** : " + jsonObject.getAsJsonObject("stats").getAsJsonObject("p2")
                                    .getAsJsonObject("kills").get("value").toString().replace("\"", "")
                                    + "\n" + "**Wins** : " + jsonObject.getAsJsonObject("stats").getAsJsonObject("p2")
                                    .getAsJsonObject("top1").get("value").toString().replace("\"", "")
                                    + "\n" + "**Wins %** : "
                                    + jsonObject.getAsJsonObject("stats").getAsJsonObject("p2")
                                    .getAsJsonObject("winRatio").get("value").toString().replace("\"", ""),
                            true);
                    buider.addField("\ud83d\udc65 | __**Duo**__ | \ud83d\udc65",
                            "**K/D** : " + jsonObject.getAsJsonObject("stats").getAsJsonObject("p10")
                                    .getAsJsonObject("kd").get("value").toString().replace("\"", "") + " \n"
                                    + "**Matches** : "
                                    + jsonObject.getAsJsonObject("stats").getAsJsonObject("p10")
                                    .getAsJsonObject("matches").get("value").toString().replace("\"", "")
                                    + "\n" + "**Kills** : "
                                    + jsonObject.getAsJsonObject("stats").getAsJsonObject("p10")
                                    .getAsJsonObject("kills").get("value").toString().replace("\"", "")
                                    + "\n" + "**Wins** : " + jsonObject.getAsJsonObject("stats").getAsJsonObject("p10")
                                    .getAsJsonObject("top1").get("value").toString().replace("\"", "")
                                    + "\n" + "**Wins %** : "
                                    + jsonObject.getAsJsonObject("stats").getAsJsonObject("p10")
                                    .getAsJsonObject("winRatio").get("value").toString().replace("\"", ""),
                            true);
                    buider.addField("\ud83d\udc65\ud83d\udc65 | __**Section**__ | \ud83d\udc65\ud83d\udc65",
                            "**K/D** : " + jsonObject.getAsJsonObject("stats").getAsJsonObject("p9")
                                    .getAsJsonObject("kd").get("value").toString().replace("\"", "") + " \n"
                                    + "**Matches** : "
                                    + jsonObject.getAsJsonObject("stats").getAsJsonObject("p9")
                                    .getAsJsonObject("matches").get("value").toString().replace("\"", "")
                                    + "\n" + "**Kills** : " + jsonObject.getAsJsonObject("stats").getAsJsonObject("p9")
                                    .getAsJsonObject("kills").get("value").toString().replace("\"", "")
                                    + "\n" + "**Wins** : " + jsonObject.getAsJsonObject("stats").getAsJsonObject("p9")
                                    .getAsJsonObject("top1").get("value").toString().replace("\"", "")
                                    + "\n" + "**Wins %** : "
                                    + jsonObject.getAsJsonObject("stats").getAsJsonObject("p9")
                                    .getAsJsonObject("winRatio").get("value").toString().replace("\"", ""),
                            true);
                    buider.setFooter(guild.getName(), guild.getIconUrl());
                    message.sendMessage(buider.build()).queue();
                } else if (c2.equals("last")) {
                    EmbedBuilder buider = new EmbedBuilder();
                    JsonObject jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();
                    String test = "\"";
                    System.out.println(test);
                    buider.setColor(color.couleurAleatoire(user));
                    buider.setAuthor(user.getName(), null, "http://ozeryo.sytes.net/images/Logo_Fortnite.png");
                    buider.setTitle("Last Match Stats");
                    if (lang == command.Language.fr) {
                        buider.addField("\ud83d\udd79 | Plateforme | \ud83d\udd79\ufffd?",
                                jsonObject.get("platformName").toString().replace("\"", ""), true);
                    }
                    if (lang == command.Language.en) {
                        buider.addField("\ud83d\udd79 | Platform | \ud83d\udd79\ufffd?",
                                jsonObject.get("platformName").toString().replace("\"", ""), true);
                    }
                    buider.addField("\ud83d\udc64 | Username | \ud83d\udc64",
                            jsonObject.get("epicUserHandle").toString().replace("\"", ""), true);
                    buider.addBlankField(false);
                    for (int i = 0; i < jsonObject.getAsJsonArray("recentMatches").size(); ++i) {
                        String Top6;
                        String Top12;
                        String Top3;
                        String Top10;
                        String Top5;
                        String Top25;
                        String matches = ((JsonObject) jsonObject.getAsJsonArray("recentMatches").get(i)).get("matches")
                                .toString().replace("\"", "");
                        String kills = ((JsonObject) jsonObject.getAsJsonArray("recentMatches").get(i)).get("kills")
                                .toString().replace("\"", "");
                        String score = ((JsonObject) jsonObject.getAsJsonArray("recentMatches").get(i)).get("score")
                                .toString().replace("\"", "");
                        String Rating2 = ((JsonObject) jsonObject.getAsJsonArray("recentMatches").get(i))
                                .get("trnRating").toString().replace("\"", "");
                        String RatingChange = ((JsonObject) jsonObject.getAsJsonArray("recentMatches").get(i))
                                .get("trnRatingChange").toString().replace("\"", "");
                        String Date2 = ((JsonObject) jsonObject.getAsJsonArray("recentMatches").get(i))
                                .get("dateCollected").toString().replace("\"", "").replace("-", "/").replace("T", "  ");
                        String mode_de_jeu = ((JsonObject) jsonObject.getAsJsonArray("recentMatches").get(i))
                                .get("playlist").toString().replace("\"", "");
                        mode_de_jeu = mode_de_jeu.equals("p2") ? "Solo"
                                : (mode_de_jeu.equals("p10") ? "Duo" : (mode_de_jeu.equals("p9") ? "Section" : "Solo"));
                        double kills2 = 1.1234;
                        kills2 = Integer.parseInt(kills);
                        double matches2 = 1.1234;
                        matches2 = Integer.parseInt(matches);
                        double KD = 1.234;
                        KD = kills2 / matches2;
                        String KD2 = this.arondi(KD);
                        if (!RatingChange.contains("-")) {
                            RatingChange = "+" + RatingChange;
                        }
                        StringBuilder builder1 = new StringBuilder(RatingChange);
                        System.out.println(RatingChange.length());
                        for (int i2 = builder1.length(); i2 > 6; --i2) {
                            System.out.println(RatingChange + " / " + builder1);
                            builder1.deleteCharAt(builder.length() + 1);
                        }
                        RatingChange = builder1.toString();
                        String top = "";
                        String Top1 = ((JsonObject) jsonObject.getAsJsonArray("recentMatches").get(i)).get("top1")
                                .toString().replace("\"", "");
                        if (!Top1.equals("0")) {
                            top = top + "- *Top 1* : " + Top1 + " \n";
                        }
                        if (!(Top3 = ((JsonObject) jsonObject.getAsJsonArray("recentMatches").get(i)).get("top3")
                                .toString().replace("\"", "")).equals("0")) {
                            top = top + "- *Top 3* : " + Top3 + " \n";
                        }
                        if (!(Top5 = ((JsonObject) jsonObject.getAsJsonArray("recentMatches").get(i)).get("top5")
                                .toString().replace("\"", "")).equals("0")) {
                            top = top + "- *Top 5* : " + Top5 + " \n";
                        }
                        if (!(Top6 = ((JsonObject) jsonObject.getAsJsonArray("recentMatches").get(i)).get("top6")
                                .toString().replace("\"", "")).equals("0")) {
                            top = top + "- *Top 6* : " + Top6 + " \n";
                        }
                        if (!(Top10 = ((JsonObject) jsonObject.getAsJsonArray("recentMatches").get(i)).get("top10")
                                .toString().replace("\"", "")).equals("0")) {
                            top = top + "- *Top 10* : " + Top10 + " \n";
                        }
                        if (!(Top12 = ((JsonObject) jsonObject.getAsJsonArray("recentMatches").get(i)).get("top12")
                                .toString().replace("\"", "")).equals("0")) {
                            top = top + "- *Top 12* : " + Top12 + " \n";
                        }
                        if (!(Top25 = ((JsonObject) jsonObject.getAsJsonArray("recentMatches").get(i)).get("top25")
                                .toString().replace("\"", "")).equals("0")) {
                            top = top + "- *Top 25* : " + Top25 + " \n";
                        }
                        if (top.equals("")) {
                            if (lang == command.Language.fr) {
                                top = "Aucun Top";
                            }
                            if (lang == command.Language.en) {
                                top = "Any Top";
                            }
                        }
                        if (lang == command.Language.fr) {
                            buider.addField("\ud83d\udcc6 | **" + Date2 + "** | ", "\ud83d\udc7e | **Mode de jeu**  : "
                                    + mode_de_jeu + "\n" + "\u2620\ufffd? | **Kills** : " + kills + "\n"
                                    + "\u2620\ufffd? | **K/D**  : " + KD2 + " \n" + "\ud83c\udfae | **Matches**  : "
                                    + matches + "\n" + "\ud83d\udcaf | **Score**  : " + score + "\n"
                                    + "\ud83e\udd47 | **Rating** : " + Rating2 + " (" + RatingChange + ") \n"
                                    + "\ufffd?\ufffd | **Top**  : \n" + top, true);
                        }
                        if (lang != command.Language.en)
                            continue;
                        buider.addField("\ud83d\udcc6 | **" + Date2 + "** | ",
                                "\ud83d\udc7e | **Gamemode**  : " + mode_de_jeu + "\n" + "\u2620\ufffd? | **Kills** : "
                                        + kills + "\n" + "\u2620\ufffd? | **K/D**  : " + KD2 + " \n"
                                        + "\ud83c\udfae | **Matches**  : " + matches + "\n"
                                        + "\ud83d\udcaf | **Score**  : " + score + "\n" + "\ud83e\udd47 | **Rating** : "
                                        + Rating2 + " (" + RatingChange + ") \n" + "\ufffd?\ufffd | **Top**  : \n"
                                        + top,
                                true);
                    }
                    message.sendMessage(buider.build()).queue();
                } else if (c2.equals("register")) {
                    TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/fortnite/");
                    TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/fortnite/");
                    TextFileWriter.write(
                            "/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/fortnite/plateforme.txt",
                            plateforme, 1);
                    TextFileWriter.write(
                            "/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/fortnite/username.txt",
                            username, 1);
                    if (lang == command.Language.fr) {
                        message.sendMessage("Vous etes désormais enregistré sous le nom de **" + username
                                + "** sur la platerofme **" + plateforme + "**.").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage("You are now registered on the username : **" + username
                                + "** and on the platform : **" + plateforme + "**.").queue();
                    }
                } else {
                    String mode_de_jeu = "";
                    if (c2.equals("solo")) {
                        mode_de_jeu = "p2";
                    }
                    if (c2.equals("duo")) {
                        mode_de_jeu = "p10";
                    }
                    if (c2.equals("section")) {
                        mode_de_jeu = "p9";
                    }
                    EmbedBuilder buider = new EmbedBuilder();
                    JsonObject jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();
                    String test = "\"";
                    System.out.println(test);
                    buider.setColor(color.couleurAleatoire(user));
                    buider.setAuthor(user.getName(), null, "http://ozeryo.sytes.net/images/Logo_Fortnite.png");
                    buider.setTitle(c2 + " Stats");
                    if (lang == command.Language.fr) {
                        buider.addField("\ud83d\udd79 | Plateforme | \ud83d\udd79\ufffd?",
                                jsonObject.get("platformName").toString().replace("\"", ""), true);
                    }
                    if (lang == command.Language.en) {
                        buider.addField("\ud83d\udd79 | Platform | \ud83d\udd79\ufffd?",
                                jsonObject.get("platformName").toString().replace("\"", ""), true);
                    }
                    buider.addBlankField(true);
                    buider.addField("\ud83d\udc64 | Username | \ud83d\udc64",
                            jsonObject.get("epicUserHandle").toString().replace("\"", ""), true);
                    String win = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                            .getAsJsonObject("top1").get("value").toString().replace("\"", "");
                    String winRank = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                            .getAsJsonObject("top1").get("rank").toString().replace("\"", "");
                    String winRatio = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                            .getAsJsonObject("winRatio").get("value").toString().replace("\"", "");
                    String winRatioRank = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                            .getAsJsonObject("winRatio").get("rank").toString().replace("\"", "");
                    String score = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                            .getAsJsonObject("score").get("value").toString().replace("\"", "");
                    String scoreRank = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                            .getAsJsonObject("score").get("rank").toString().replace("\"", "");
                    String matchs = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                            .getAsJsonObject("matches").get("value").toString().replace("\"", "");
                    String matchsRank = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                            .getAsJsonObject("matches").get("rank").toString().replace("\"", "");
                    String kills = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                            .getAsJsonObject("kills").get("value").toString().replace("\"", "");
                    String killsRank = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                            .getAsJsonObject("kills").get("rank").toString().replace("\"", "");
                    String KD = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu).getAsJsonObject("kd")
                            .get("value").toString().replace("\"", "");
                    String KDRank = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                            .getAsJsonObject("kd").get("rank").toString().replace("\"", "");
                    String trnRating = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                            .getAsJsonObject("trnRating").get("value").toString().replace("\"", "");
                    String trnRatingRank = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                            .getAsJsonObject("trnRating").get("rank").toString().replace("\"", "");
                    String scoreParMatch = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                            .getAsJsonObject("scorePerMatch").get("value").toString().replace("\"", "");
                    String top = "";
                    if (mode_de_jeu.equals("p2")) {
                        String top10 = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                                .getAsJsonObject("top10").get("value").toString().replace("\"", "");
                        String top10Rank = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                                .getAsJsonObject("top10").get("rank").toString().replace("\"", "");
                        top = top + "Top 10 = " + top10 + " (" + top10Rank + "\ud83e\udd47) \n";
                        String top25 = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                                .getAsJsonObject("top25").get("value").toString().replace("\"", "");
                        String top25Rank = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                                .getAsJsonObject("top25").get("rank").toString().replace("\"", "");
                        top = top + "Top 25 = " + top25 + " (" + top25Rank + "\ud83e\udd47) \n";
                    } else if (mode_de_jeu.equals("p10")) {
                        String top6 = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                                .getAsJsonObject("top6").get("value").toString().replace("\"", "");
                        String top6Rank = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                                .getAsJsonObject("top6").get("rank").toString().replace("\"", "");
                        top = top + "Top 6 = " + top6 + " (" + top6Rank + "\ud83e\udd47) \n";
                        String top12 = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                                .getAsJsonObject("top12").get("value").toString().replace("\"", "");
                        String top12Rank = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                                .getAsJsonObject("top12").get("rank").toString().replace("\"", "");
                        top = top + "Top 12 = " + top12 + " (" + top12Rank + "\ud83e\udd47) \n";
                    } else if (mode_de_jeu.equals("p9")) {
                        String top3 = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                                .getAsJsonObject("top3").get("value").toString().replace("\"", "");
                        String top3Rank = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                                .getAsJsonObject("top3").get("rank").toString().replace("\"", "");
                        top = top + "Top 3 = " + top3 + " (" + top3Rank + "\ud83e\udd47) \n";
                        String top5 = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                                .getAsJsonObject("top5").get("value").toString().replace("\"", "");
                        String top5Rank = jsonObject.getAsJsonObject("stats").getAsJsonObject(mode_de_jeu)
                                .getAsJsonObject("top5").get("rank").toString().replace("\"", "");
                        top = top + "Top 5 = " + top5 + " (" + top5Rank + "\ud83e\udd47) \n";
                    }
                    buider.addBlankField(true);
                    buider.addField("\ud83c\udfb2 | TRN Rating | \ud83c\udfb2",
                            trnRating + "(" + trnRatingRank + "\ud83e\udd47)", true);
                    buider.addBlankField(true);
                    buider.addField("\ufffd?\ufffd | **Win** | \ufffd?\ufffd",
                            win + " (" + winRank + "\ud83e\udd47)", true);
                    buider.addField("\ufffd?\ufffd| **Win %** | \ufffd?\ufffd",
                            winRatio + " (" + winRatioRank + "\ud83e\udd47)", true);
                    buider.addField("\ud83d\udcaf | **Score** | \ud83d\udcaf",
                            score + " (" + scoreRank + "\ud83e\udd47)", true);
                    buider.addField("\ud83c\udfae | **Matchs** | \ud83c\udfae",
                            matchs + " (" + matchsRank + "\ud83e\udd47)", true);
                    buider.addField("\u2620\ufffd? | **Kills** | \u2620\ufffd?",
                            kills + " (" + killsRank + "\ud83e\udd47)", true);
                    buider.addField("\u2620\ufffd? | **K/D** | \u2620\ufffd?",
                            KD + " (" + KDRank + "\ud83e\udd47)", true);
                    buider.addBlankField(true);
                    if (lang == command.Language.fr) {
                        buider.addField("\ud83d\udcaf | Score par match | \ud83d\udcaf", scoreParMatch, true);
                    }
                    if (lang == command.Language.en) {
                        buider.addField("\ud83d\udcaf | Score per match | \ud83d\udcaf", scoreParMatch, true);
                    }
                    buider.addBlankField(true);
                    buider.addField("\ufffd?\ufffd | **Top** | \ufffd?\ufffd : ", top, false);
                    message.sendMessage(buider.build()).queue();
                }
            } catch (IOException e) {
                if (lang == command.Language.fr) {
                    message.sendMessage("Désolé mais le joueur que vous recherchez ne semble pas exister").queue();
                }
                if (lang == command.Language.en) {
                    message.sendMessage("Sorry but this player doesn't exist").queue();
                }
                e.printStackTrace();
            } catch (NullPointerException ei) {
                if (lang == command.Language.fr) {
                    message.sendMessage("Désolé mais le joueur que vous recherchez ne semble pas exister").queue();
                }
                if (lang != command.Language.en)
                    break block60;
                message.sendMessage("Sorry but this player doesn't exist").queue();
            }
        }
    }
}
