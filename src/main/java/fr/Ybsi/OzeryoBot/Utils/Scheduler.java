/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.Ybsi.OzeryoBot.Commands.CommandMap;
import fr.Ybsi.OzeryoBot.Commands.Game.Attack;
import fr.Ybsi.OzeryoBot.Commands.Game.Heroe;
import fr.Ybsi.OzeryoBot.Commands.Game.LootBox;
import fr.Ybsi.OzeryoBot.Commands.Game.Trade;
import fr.Ybsi.OzeryoBot.Commands.SimpleCommand;
import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Level.Level;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.managers.ChannelManager;
import net.dv8tion.jda.internal.entities.UserImpl;
import org.discordbots.api.client.DiscordBotListAPI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.util.List;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private static final ScheduledExecutorService scheduler1 = Executors.newScheduledThreadPool(2);
    private static final ScheduledExecutorService scheduler2 = Executors.newScheduledThreadPool(3);
    public static int messages = 0;
    public static long nextPub = System.currentTimeMillis() + 7200000L;
    public static boolean messcf = false;
    public static boolean debug = false;
    public static boolean inQueue = false;
    static int LastQuestDate = 0;
    static int LastShopDate = 0;
    static int LastBidDate = 0;
    static int LastPaysDate = 0;
    static int LastRessourcesDate = 0;
    static Long week = DiscordBot.getData().getLastLbPremium();

    public static void Guildtest(final JDA jda) {
        Runnable Uptime_Update = new Runnable() {

            @Override
            public void run() {
                try {
                    if (jda.getStatus() == JDA.Status.CONNECTED) {
                        ProfilData data = DiscordBot.getData();
                        ArrayList<String> list = new ArrayList<String>();
                        for (GuildProfil profil : DiscordBot.getGuilddata().getGuildProfil().values()) {
                            try {
                                Boolean me = jda.getGuildById(profil.getId()).isAvailable();
                            } catch (NullPointerException e) {
                                CommandMap.PublicLog(
                                        "\ud83d\udfe0 OzeryoBot a été retiré du serveur " + profil.getName() + ".",
                                        jda);
                                list.add(profil.getId());
                            }
                        }
                        if (list.size() > 0) {
                            for (String id : list) {
                                DiscordBot.getGuilddata().getGuildProfil().remove(id);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ScheduledFuture<?> UptimeHandle = scheduler1.scheduleAtFixedRate(Uptime_Update, 600L, 180L, TimeUnit.SECONDS);
    }

    public static void Pub(final JDA jda) {
        Runnable Uptime_Update = new Runnable() {

            @Override
            public void run() {
                try {
                    ProfilData data = DiscordBot.getData();
                    ArrayList<String> list = new ArrayList<String>();
                    for (GuildProfil profil : DiscordBot.getGuilddata().getGuildProfil().values()) {
                        try {
                            if (DiscordBot.getGuilddata().getGuildProfil().get(profil.getId()).getPubchannel()
                                    .equals(""))
                                continue;
                            for (int i = 0; i < jda
                                    .getGuildById(profil.getId()).getTextChannelById(DiscordBot.getGuilddata()
                                            .getGuildProfil().get(profil.getId()).getPubchannel())
                                    .getMembers().size(); ++i) {
                                list.add(profil.getId());
                            }
                        } catch (Exception i) {
                            // empty catch block
                        }
                    }
                    for (GuildProfil profil : DiscordBot.getGuilddata().getGuildProfil().values()) {
                        try {
                            if (DiscordBot.getGuilddata().getGuildProfil().get(profil.getId()).getPubchannel()
                                    .equals(""))
                                continue;
                            int alea = 1 + (int) (Math.random() * (double) (list.size() - 1 + 1));
                            String id = list.get(alea);
                            while (id.equals(profil.getId())) {
                                alea = 1 + (int) (Math.random() * (double) (list.size() - 1 + 1));
                                id = list.get(alea);
                            }
                            String pub = DiscordBot.getGuilddata().getGuildProfil().get(id).getPub();
                            DiscordBot.getGuilddata().getGuildProfil().get(id)
                                    .setPub_sent(DiscordBot.getGuilddata().getGuildProfil().get(id).getPub_sent() + 1);
                            ChannelManager channel = jda
                                    .getGuildById(DiscordBot.getGuilddata().getGuildProfil().get(id).getId())
                                    .getTextChannelById(
                                            DiscordBot.getGuilddata().getGuildProfil().get(id).getPubchannel())
                                    .getManager();
                            if (jda.getGuildById(id).getSelfMember().hasPermission(Permission.MANAGE_CHANNEL)) {
                                channel.setTopic("Puclicité : Pub envoyé : "
                                        + DiscordBot.getGuilddata().getGuildProfil().get(id).getPub_sent()
                                        + " | Pub liké : 0 | Pub rapporté : 0").queue();
                            }
                            jda.getGuildById(DiscordBot.getGuilddata().getGuildProfil().get(profil.getId()).getId())
                                    .getTextChannelById(DiscordBot.getGuilddata().getGuildProfil().get(profil.getId())
                                            .getPubchannel())
                                    .sendMessage(pub).queue();
                        } catch (Exception alea) {
                            // empty catch block
                        }
                    }
                    nextPub = System.currentTimeMillis() + 7200000L;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ScheduledFuture<?> UptimeHandle = scheduler1.scheduleAtFixedRate(Uptime_Update, 2L, 2L, TimeUnit.HOURS);
    }

    public static void WebLeaderBoard(final JDA jda) {
        Runnable Uptime_Update = new Runnable() {

            @Override
            public void run() {
                try {
                    ProfilData data = DiscordBot.getData();
                    HashMap<String, Integer> classement = new HashMap<String, Integer>();
                    for (Profil profil : data.getProfils().values()) {
                        int point = profil.getIdh();
                        String id2 = profil.getId();
                        classement.put(id2, point);
                    }
                    ArrayList<Entry<String, Integer>> entries = new ArrayList(classement.entrySet());
                    Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

                        @Override
                        public int compare(Map.Entry<String, Integer> e2, Map.Entry<String, Integer> e1) {
                            return e1.getValue().compareTo(e2.getValue());
                        }
                    });
                    String messages = "";
                    int o = 1;
                    messages = "\ud83c\udfc6 Leaderboard \ud83c\udfc6 :<br />";
                    for (Map.Entry entry : entries) {

                        data.getProfils().get(entry.getKey()).setTopIdh(o);
                        String rank = o == 1 ? "\ud83e\udd47"
                                : (o == 2 ? "\ud83e\udd48"
                                : (o == 3 ? "\ud83e\udd49" : o + "\u00e8me"));
                        String user3;
                        try {
                            user3 = DiscordBot.getData().getProfils().get(entry.getKey()).getName();
                        } catch (NullPointerException e) {
                            user3 = "Une personne discr\u00e8te";
                        }
                        messages = messages + "<span style=\"font-weight : bold\">" + rank
                                + "</span> : " + user3 + " : " + entry.getValue() + "\n";
                        ++o;
                    }
                    TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/top.txt");
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/top.txt",
                            String.valueOf(classement.size()), 0);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/top.txt", messages, 1);
                    classement = new HashMap();
                    for (Profil profil : data.getProfils().values()) {
                        int level;
                        int Game_EXP = profil.getXp();
                        try {
                            double operation = 3 * Game_EXP / 4;
                            double math = Math.sqrt(operation);
                            level = (int) Math.round(math);
                        } catch (NullPointerException e) {
                            level = 0;
                        }
                        String emoji = "";
                        if (level >= 200 && level < 300) {
                            emoji = "\ud83d\udc9b";
                        } else if (level >= 300 && level < 400) {
                            emoji = "\ud83d\udc9a";
                        } else if (level >= 400 && level < 500) {
                            emoji = "\ud83d\udc99";
                        } else if (level >= 500 && level < 600) {
                            emoji = "\ud83d\udc9c";
                        } else if (level >= 600) {
                            emoji = "\ud83d\udda4";
                        }
                        String id3 = profil.getId();
                        classement.put(id3, Game_EXP);
                    }
                    entries = new ArrayList(classement.entrySet());
                    Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

                        @Override
                        public int compare(Map.Entry<String, Integer> e2, Map.Entry<String, Integer> e1) {
                            return e1.getValue().compareTo(e2.getValue());
                        }
                    });
                    messages = "";
                    o = 1;
                    messages = "\ud83c\udfc6 Leaderboard \ud83c\udfc6 :<br />";
                    for (Map.Entry entry : entries) {
                        int level;
                        data.getProfils().get(entry.getKey()).setTopXp(o);
                        String rank = o == 1 ? "\ud83e\udd47"
                                : (o == 2 ? "\ud83e\udd48"
                                : (o == 3 ? "\ud83e\udd49" : o + "\u00e8me"));
                        int Game_EXP = DiscordBot.getData().getProfils().get(entry.getKey()).getXp();
                        try {
                            double operation = 3 * Game_EXP / 4;
                            double math = Math.sqrt(operation);
                            level = (int) Math.round(math);
                        } catch (NullPointerException e) {
                            level = 0;
                        }
                        String emoji = "";
                        if (level >= 200 && level < 300) {
                            emoji = "\ud83d\udc9b";
                        } else if (level >= 300 && level < 400) {
                            emoji = "\ud83d\udc9a";
                        } else if (level >= 400 && level < 500) {
                            emoji = "\ud83d\udc99";
                        } else if (level >= 500 && level < 600) {
                            emoji = "\ud83d\udc9c";
                        } else if (level >= 600) {
                            emoji = "\ud83d\udda4";
                        }
                        String member2 = "Une personne discr\u00e8te | <span style=\"font-weight : bold\">Level</span> : "
                                + level + emoji + " | <span style=\"font-weight : bold\">Level</span>EXP</span> ";
                        try {
                            member2 = DiscordBot.getData().getProfils().get(entry.getKey()).getName()
                                    + " | <span style=\"font-weight : bold\">Level</span> : " + level + emoji
                                    + " | <span style=\"font-weight : bold\">EXP</span> ";
                        } catch (NullPointerException math) {
                            // empty catch block
                        }
                        messages = messages + "<span style=\"font-weight : bold\">" + rank
                                + "</span> : " + member2 + " : " + entry.getValue() + "\n";
                        ++o;
                    }
                    TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/gamelevel.txt");
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/gamelevel.txt",
                            String.valueOf(classement.size()), 0);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/gamelevel.txt", messages,
                            1);
                    classement = new HashMap();
                    for (Profil profil : data.getProfils().values()) {
                        int rep = profil.getRep();
                        String id = profil.getId();
                        classement.put(id, rep);
                    }
                    entries = new ArrayList(classement.entrySet());
                    Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

                        @Override
                        public int compare(Map.Entry<String, Integer> e2, Map.Entry<String, Integer> e1) {
                            return e1.getValue().compareTo(e2.getValue());
                        }
                    });
                    messages = "";
                    o = 1;
                    messages = "\ud83c\udfc6 Leaderboard \ud83c\udfc6 :<br />";
                    for (Map.Entry entry : entries) {
                        data.getProfils().get(entry.getKey()).setToprRep(o);
                        String rank = o == 1 ? "\ud83e\udd47"
                                : (o == 2 ? "\ud83e\udd48"
                                : (o == 3 ? "\ud83e\udd49" : o + "\u00e8me"));
                        String member = "Une personne discr\u00e8te | <span style=\"font-weight : bold\">réputation</span> ";
                        try {
                            member = DiscordBot.getData().getProfils().get(entry.getKey()).getName()
                                    + " | <span style=\"font-weight : bold\">réputation</span>";
                        } catch (NullPointerException level) {
                            // empty catch block
                        }
                        messages = messages + "<span style=\"font-weight : bold\">" + rank
                                + "</span> : " + member + " : " + entry.getValue() + "\n";
                        ++o;
                    }
                    TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/rep.txt");
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/rep.txt",
                            String.valueOf(classement.size()), 0);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/rep.txt", messages, 1);
                    classement = new HashMap();
                    for (Profil profil : data.getProfils().values()) {
                        int combo = profil.getCf();
                        String id = profil.getId();
                        classement.put(id, combo);
                    }
                    entries = new ArrayList(classement.entrySet());
                    Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

                        @Override
                        public int compare(Map.Entry<String, Integer> e2, Map.Entry<String, Integer> e1) {
                            return e1.getValue().compareTo(e2.getValue());
                        }
                    });
                    messages = "";
                    o = 1;
                    messages = "\ud83c\udfc6 Leaderboard \ud83c\udfc6 :<br />";
                    for (Map.Entry entry : entries) {
                        data.getProfils().get(entry.getKey()).setTopCf(o);
                        String rank = o == 1 ? "\ud83e\udd47"
                                : (o == 2 ? "\ud83e\udd48"
                                : (o == 3 ? "\ud83e\udd49" : o + "\u00e8me"));
                        String member = "Une personne discr\u00e8te | <span style=\"font-weight : bold\">Combo</span>  ";
                        try {
                            member = DiscordBot.getData().getProfils().get(entry.getKey()).getName()
                                    + " | <span style=\"font-weight : bold\">Combo</span>  ";
                        } catch (NullPointerException level) {
                            // empty catch block
                        }
                        messages = messages + "<span style=\"font-weight : bold\">" + rank
                                + "</span> : " + member + " : " + entry.getValue() + "\n";
                        ++o;
                    }
                    TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/cf.txt");
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/cf.txt",
                            String.valueOf(classement.size()), 0);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/cf.txt", messages, 1);
                    classement = new HashMap();
                    for (Profil profil : data.getProfils().values()) {
                        int trophees = profil.getTrophy();
                        String id = profil.getId();
                        classement.put(id, trophees);
                    }
                    entries = new ArrayList(classement.entrySet());
                    Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

                        @Override
                        public int compare(Map.Entry<String, Integer> e2, Map.Entry<String, Integer> e1) {
                            return e1.getValue().compareTo(e2.getValue());
                        }
                    });
                    messages = "";
                    o = 1;
                    messages = "\ud83c\udfc6 Leaderboard \ud83c\udfc6 :<br />";
                    for (Map.Entry entry : entries) {
                        data.getProfils().get(entry.getKey()).setTopTrophy(o);
                        String rank = o == 1 ? "\ud83e\udd47"
                                : (o == 2 ? "\ud83e\udd48"
                                : (o == 3 ? "\ud83e\udd49" : o + "\u00e8me"));
                        String member = " inconnu";
                        try {
                            member = DiscordBot.getData().getProfils().get(entry.getKey()).getName();
                        } catch (Exception level) {
                            // empty catch block
                        }
                        int trophees = DiscordBot.getData().getProfils().get(entry.getKey()).getTrophy();
                        String rank1 = Attack.rank(trophees);
                        member = "Une personne discr\u00e8te | <span style=\"font-weight : bold\">Rank</span> : "
                                + rank1 + " | <span style=\"font-weight : bold\">Trophées</span>  ";
                        try {
                            member = DiscordBot.getData().getProfils().get(entry.getKey()).getName()
                                    + " | <span style=\"font-weight : bold\">Rank</span> : " + rank
                                    + " | <span style=\"font-weight : bold\">Trophées</span>  ";
                        } catch (NullPointerException member2) {
                            // empty catch block
                        }
                        messages = messages + "<span style=\"font-weight : bold\">" + rank
                                + "</span> : " + member + " : " + entry.getValue() + "\n";
                        ++o;
                    }
                    TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/trophées.txt");
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/trophées.txt",
                            String.valueOf(classement.size()), 0);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/trophées.txt", messages, 1);
                    classement = new HashMap();
                    for (GuildProfil guildProfil : DiscordBot.getGuilddata().getGuildProfil().values()) {
                        try {
                            int EXP2 = DiscordBot.getGuilddata().getGuildProfil().get(guildProfil.getId()).getXp();
                            int level = Level.Glevel(guildProfil.getId());
                            String Guildname = "inconnu";
                            try {
                                Guildname = jda.getGuildById(guildProfil.getId()).getName();
                            } catch (NullPointerException rank1) {
                                // empty catch block
                            }
                            String member3 = Guildname
                                    + " | <span style=\"font-weight : bold\">Level</span> : " + level;
                            classement.put(member3, EXP2);
                        } catch (NumberFormatException EXP2) {
                            // empty catch block
                        }
                    }
                    entries = new ArrayList(classement.entrySet());
                    Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

                        @Override
                        public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                            return e2.getValue().compareTo(e1.getValue());
                        }
                    });
                    messages = "";
                    o = 1;
                    messages = "\ud83c\udfc6 Leaderboard \ud83c\udfc6 :<br />";
                    for (Map.Entry entry : entries) {
                        String rank = o == 1 ? "\ud83e\udd47"
                                : (o == 2 ? "\ud83e\udd48"
                                : (o == 3 ? "\ud83e\udd49" : o + "\u00e8me"));
                        messages = messages + "<span style=\"font-weight : bold\">" + rank
                                + "</span> : " + entry.getKey() + " : " + entry.getValue() + "\n";
                        ++o;
                    }
                    TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/guild.txt");
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/guild.txt",
                            String.valueOf(classement.size()), 0);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/guild.txt", messages, 1);
                    classement = new HashMap();
                    LevelProfilData leveldata = DiscordBot.getLeveldata();
                    for (LevelProfil levelProfil : leveldata.getLevelProfil().values()) {
                        int EXP3 = levelProfil.getXp();
                        String id4 = levelProfil.getId();
                        classement.put(id4, EXP3);
                        entries = new ArrayList(classement.entrySet());
                    }
                    Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

                        @Override
                        public int compare(Map.Entry<String, Integer> e2, Map.Entry<String, Integer> e1) {
                            return e1.getValue().compareTo(e2.getValue());
                        }
                    });
                    messages = "";
                    o = 1;
                    messages = "\ud83c\udfc6 Leaderboard \ud83c\udfc6 :<br />";
                    for (Map.Entry entry : entries) {
                        try {
                            leveldata.getLevelProfil().get(entry.getKey()).setToplevel(o);
                        } catch (NullPointerException EXP3) {
                            // empty catch block
                        }
                        String rank5;
                        if (o == 1) {
                            rank5 = "\ud83e\udd47";
                        } else if (o == 2) {
                            rank5 = "\ud83e\udd48";
                        } else if (o == 3) {
                            rank5 = "\ud83e\udd49";
                        } else {
                            rank5 = o + "\u00e8me";
                        }
                        String member4 = " inconnu";
                        try {
                            member4 = DiscordBot.getLeveldata().getLevelProfil().get(entry.getKey()).getName();
                        } catch (Exception member3) {
                            // empty catch block
                        }
                        member4 = member4 + "| <span style=\"font-weight : bold\">Level</span> : "
                                + Level.level((String) entry.getKey())
                                + " | <span style=\"font-weight : bold\">EXP</span> : ";
                        messages = messages + "<span style=\"font-weight : bold\">" + rank5
                                + "</span> : " + member4 + " : " + entry.getValue() + "\n";
                        ++o;
                    }
                    TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/level.txt");
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/level.txt",
                            String.valueOf(classement.size()), 0);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/level.txt", messages, 1);
                    File repertoire = new File("/home/DiscordBot/Rasberry/données/bot/Pays/");
                    File[] files = repertoire.listFiles();
                    classement = new HashMap();
                    for (File file : files) {
                        try {
                            int Game_EXP = 0;
                            Game_EXP = Integer.parseInt(TextFileWriter.read(
                                    "/home/DiscordBot/Rasberry/données/bot/Pays/" + file.getName() + "/points.txt"));
                            double level2 = Game_EXP / 1000;
                            double level3 = Math.sqrt(level2);
                            int level = (int) Math.round(level3);
                            String member5 = file.getName()
                                    + " | <span style=\"font-weight : bold\">Level</span> : " + level;
                            classement.put(member5, Game_EXP);
                        } catch (IndexOutOfBoundsException Game_EXP) {
                            // empty catch block
                        }
                    }
                    entries = new ArrayList(classement.entrySet());
                    Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

                        @Override
                        public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                            return e2.getValue().compareTo(e1.getValue());
                        }
                    });
                    messages = "";
                    o = 1;
                    messages = "\ud83c\udfc6 Leaderboard \ud83c\udfc6 :<br />";
                    for (Map.Entry entry : entries) {
                        String rank = o == 1 ? "\ud83e\udd47"
                                : (o == 2 ? "\ud83e\udd48"
                                : (o == 3 ? "\ud83e\udd49" : o + "\u00e8me"));
                        messages = messages + "<span style=\"font-weight : bold\">" + rank
                                + "</span> : " + entry.getKey() + " : " + entry.getValue() + "\n";
                        ++o;
                    }
                    TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/pays.txt");
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/pays.txt",
                            String.valueOf(classement.size()), 0);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/pays.txt", messages, 1);
                    classement = new HashMap();
                    for (Profil profil : data.getProfils().values()) {
                        int rep = profil.getAp();
                        String id5 = profil.getId();
                        classement.put(id5, rep);
                    }
                    entries = new ArrayList(classement.entrySet());
                    Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

                        @Override
                        public int compare(Map.Entry<String, Integer> e2, Map.Entry<String, Integer> e1) {
                            return e1.getValue().compareTo(e2.getValue());
                        }
                    });
                    messages = "";
                    o = 1;
                    messages = "\ud83c\udfc6 Leaderboard \ud83c\udfc6 :<br />";
                    for (Map.Entry entry : entries) {
                        data.getProfils().get(entry.getKey()).setTopAp(o);
                        String rank = o == 1 ? "\ud83e\udd47"
                                : (o == 2 ? "\ud83e\udd48"
                                : (o == 3 ? "\ud83e\udd49" : o + "\u00e8me"));
                        String member = " inconnu";
                        try {
                            member = DiscordBot.getData().getProfils().get(entry.getKey()).getName();
                        } catch (Exception Game_EXP) {
                            // empty catch block
                        }
                        messages = messages + "<span style=\"font-weight : bold\">" + rank
                                + "</span> : " + member + " : " + entry.getValue() + "\n";
                        ++o;
                    }
                    TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/ap.txt");
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/ap.txt",
                            String.valueOf(classement.size()), 0);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/LeaderBoard/ap.txt", messages, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
        ScheduledFuture<?> UptimeHandle = scheduler1.scheduleAtFixedRate(Uptime_Update, 30L, 30L, TimeUnit.MINUTES);
    }

    public static void WebMap(final JDA jda) {
        Runnable Uptime_Update = new Runnable() {

            @Override
            public void run() {
                try {

                    int xb = -10;
                    int yb = -10;
                    int x;
                    int y;
                    BufferedImage image = null;
                    for (xb = -10; xb <= 10; xb = xb + 5) {
                        for (yb = -10; yb <= 10; yb = yb + 5) {


                            try {
                                image = ImageIO.read(new File("/home/DiscordBot/Rasberry/données/bot/map1.png"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Graphics2D g1 = image.createGraphics();
                            g1.create();
                            g1.setColor(Color.black);

                            int xi = 0;
                            int yi = 6;
                            int xmax = xb + 2;
                            int ymax = yb + 2;
                            int minx = xb - 2;
                            int miny = yb - 2;
                            for (x = minx; x <= xmax; x++) {
                                xi++;
                                yi = 6;
                                for (y = miny; y <= ymax; y++) {
                                    yi--;
                                    String Case = "";
                                    String Soldier = "";
                                    String Owner = "";
                                    String Level = "";

                                    if(x>=-10 && x<=10 && y>=-10 && y<=10) {
                                        try {
                                            Case = jda
                                                    .getUserById(TextFileWriter
                                                            .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt"))
                                                    .getName();
                                        } catch (NullPointerException e) {
                                            try {
                                                Case = DiscordBot.getData().getProfils().get(TextFileWriter
                                                        .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt")).getName();
                                            } catch (Exception e1) {
                                                Case = "personne";
                                            }
                                            if (Case.equals("")) {
                                                Case = "personne";
                                            }
                                        } catch (NumberFormatException e) {
                                            Case = TextFileWriter
                                                    .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt");
                                            Soldier = TextFileWriter
                                                    .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/soldier.txt");
                                            Owner = "";
                                            try {
                                                Owner = DiscordBot.getData().getProfils().get(TextFileWriter.read(
                                                        "/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/owner.txt"))
                                                        .getName();
                                            } catch (NullPointerException e1) {
                                                Owner = "personne";
                                            }
                                            int res = Integer.parseInt(TextFileWriter
                                                    .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/res.txt"));
                                            Level = res + "";
                                        } catch (IllegalArgumentException e) {
                                            Case = "personne";
                                        }
                                    }

                                    Color couleur = null;

                                    String operation = Case;
                                    if (x > 10 || y > 10 || x < -10 || y < -10) {
                                        couleur = Color.white;
                                    } else if (operation.equals("personne")) {
                                        couleur = Color.lightGray;
                                    } else if (operation.equals("dungeon")) {
                                        couleur = Color.DARK_GRAY;

                                        Owner = "Level : " + TextFileWriter
                                                .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/bosslevel.txt");
                                        Soldier = "";
                                        Level = TextFileWriter
                                                .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/pv.txt");

                                    } else if (!Case.equals("Foret") && !Case.equals("Grotte") && !Case.equals("Dépot d'Argile")
                                            && !Case.equals("Bétail") && !Case.equals("Carrière") && !Case.equals("Champs")
                                            && !Case.equals("Mine") && !Case.equals("personne") && !Case.equals("dungeon")) {
                                        couleur = Color.white;
                                    } else couleur = Color.orange;


                                    g1.setColor(couleur);
                                    g1.fillRect(1 + 160 * (xi - 1), 1 + 160 * (yi - 1), 155, 155);
                                    g1.setColor(Color.black);

                                    if (x < -10 || x > 10 || y < -10 || y > 10) {
                                    } else {
                                        g1.drawString("(" + x + "," + y + ")", 90 + (135 * (xi - 1)), 140 + (150 * (yi - 1)));

                                        Font font = new Font("Dialog", 1, 18);
                                        g1.setFont(font);
                                        g1.drawString(Case, 50 + (150 * (xi - 1)), 50 + (150 * (yi - 1)));

                                        g1.drawString(Soldier, 50 + 135 * (xi - 1), 100 + 150 * (yi - 1));
                                        g1.drawString(Owner, 50 + 135 * (xi - 1), 80 + 150 * (yi - 1));
                                        g1.drawString(Level, 50 + 135 * (xi - 1), 120 + 150 * (yi - 1));
                                    }
                                }
                            }

                            g1.setBackground(Color.white);
                            g1.dispose();
                            try {
                                ImageIO.write(image, "png", new File(
                                        "/home/DiscordBot/Rasberry/données/bot/WebMap/" + xb + "_" + yb + ".png"));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ScheduledFuture<?> UptimeHandle = scheduler1.scheduleAtFixedRate(Uptime_Update, 5, 5, TimeUnit.MINUTES);
    }

    public static void hypixelStats(JDA jda) {
        Runnable Uptime_Update = new Runnable() {

            @Override
            public void run() {
                try {
                    String key;
                    BufferedReader in;
                    int legacyRank;
                    String Network_EXP;
                    String player;
                    JsonObject jsonObject;
                    StringBuffer response;
                    ArrayList<String> HypixelQueue;
                    int legacyXP;
                    HttpURLConnection con;
                    String inputLine;
                    URL url;
                    int pitXP;
                    int pitRank;
                    block26:
                    {
                        HypixelQueue = DiscordBot.getHypixeldata().getHypixelQueue();
                        try {
                            player = HypixelQueue.get(0);
                        } catch (Exception e1) {
                            player = "d140036beb824390a01be4995da5f6a5";
                        }
                        try {
                            String id = DiscordBot.getHypixeldata().getHypixelStats().get(player).getID();
                        } catch (NullPointerException e) {
                            DiscordBot.getHypixeldata().getHypixelStats().put(player, new Hypixel(player));
                        }
                        pitXP = DiscordBot.getHypixeldata().getHypixelStats().get(player).getPitStats();
                        pitRank = DiscordBot.getHypixeldata().getHypixelStats().get(player).getPitRank();
                        legacyXP = DiscordBot.getHypixeldata().getHypixelStats().get(player).getLegacyStats();
                        legacyRank = DiscordBot.getHypixeldata().getHypixelStats().get(player).getLegacyRank();
                        url = null;
                        con = null;
                        key = TextFileWriter.read("/home/DiscordBot/Rasberry/key/HypixelKey.txt");
                        url = new URL("https://api.hypixel.net/friends?key=" + key + "&uuid=" + player);
                        con = (HttpURLConnection) url.openConnection();
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
                        try {
                            HypixelQueue.remove(0);
                        } catch (IndexOutOfBoundsException e1) {
                            if (player == "d140036beb824390a01be4995da5f6a5")
                                break block26;
                            e1.printStackTrace();
                        }
                    }
                    try {
                        for (int i = 0; i < jsonObject.getAsJsonArray("records").size() - 1; ++i) {
                            if (HypixelQueue.size() > 50000) {
                                inQueue = false;
                            }
                            if (HypixelQueue.size() < 10000) {
                                inQueue = true;
                            }
                            if (!jsonObject.getAsJsonArray("records").get(i).getAsJsonObject().get("uuidReceiver")
                                    .toString().replaceAll("\"", "").equals(player) && inQueue) {
                                HypixelQueue.add(jsonObject.getAsJsonArray("records").get(i).getAsJsonObject()
                                        .get("uuidReceiver").toString().replaceAll("\"", ""));
                            }
                            if (jsonObject.getAsJsonArray("records").get(i).getAsJsonObject().get("uuidSender")
                                    .toString().replaceAll("\"", "").equals(player) || !inQueue)
                                continue;
                            HypixelQueue.add(jsonObject.getAsJsonArray("records").get(i).getAsJsonObject()
                                    .get("uuidSender").toString().replaceAll("\"", ""));
                        }
                    } catch (NullPointerException i) {
                        // empty catch block
                    }
                    url = new URL("https://api.hypixel.net/player?key=" + key + "&uuid=" + player);
                    con = (HttpURLConnection) url.openConnection();
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
                    try {
                        Network_EXP = jsonObject.getAsJsonObject("player").get("networkExp").toString();
                    } catch (Exception e) {
                        Network_EXP = "0";
                    }
                    Network_EXP = Network_EXP.replace(".0", "");
                    Network_EXP = Network_EXP.replace(".", "");
                    Network_EXP = Network_EXP.replace("E7", "");
                    Network_EXP = Network_EXP.replace("E8", "");
                    Network_EXP = Network_EXP.replace("E9", "");
                    legacyXP = Integer.parseInt(Network_EXP);
                    DiscordBot.getHypixeldata().getHypixelStats().get(player).setLegacyStats(legacyXP);
                    HashMap<String, Integer> legacyStats = new HashMap<String, Integer>();
                    for (Hypixel profil : DiscordBot.getHypixeldata().getHypixelStats().values()) {
                        legacyStats.put(profil.getID(), profil.getLegacyStats());
                    }
                    ArrayList<Entry<String, Integer>> entries = new ArrayList(legacyStats.entrySet());
                    Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

                        @Override
                        public int compare(Map.Entry<String, Integer> e2, Map.Entry<String, Integer> e1) {
                            return e1.getValue().compareTo(e2.getValue());
                        }
                    });
                    int o = 0;
                    boolean bool = false;
                    for (Map.Entry entry : entries) {
                        if (!bool) {
                            ++o;
                        }
                        if (!entry.getKey().equals(player))
                            continue;
                        legacyRank = o;
                        bool = true;
                    }
                    DiscordBot.getHypixeldata().getHypixelStats().get(player).setLegacyRank(legacyRank);
                    String xp3;
                    try {
                        xp3 = jsonObject.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("Pit")
                                .getAsJsonObject("profile").get("xp").toString().replace("\"", "");
                    } catch (Exception e) {
                        xp3 = "0";
                    }
                    pitXP = Integer.parseInt(xp3);
                    DiscordBot.getHypixeldata().getHypixelStats().get(player).setPitStats(pitXP);
                    HashMap<String, Integer> pitStats = new HashMap<String, Integer>();
                    for (Hypixel profil : DiscordBot.getHypixeldata().getHypixelStats().values()) {
                        pitStats.put(profil.getID(), profil.getPitStats());
                    }
                    entries = new ArrayList(pitStats.entrySet());
                    Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

                        @Override
                        public int compare(Map.Entry<String, Integer> e2, Map.Entry<String, Integer> e1) {
                            return e1.getValue().compareTo(e2.getValue());
                        }
                    });
                    o = 0;
                    bool = false;
                    for (Map.Entry entry : entries) {
                        if (!bool) {
                            ++o;
                        }
                        if (!entry.getKey().equals(player))
                            continue;
                        pitRank = o;
                        bool = true;
                    }
                    DiscordBot.getHypixeldata().setHypixelQueue(HypixelQueue);
                    DiscordBot.getHypixeldata().getHypixelStats().get(player).setPitRank(pitRank);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
        ScheduledFuture<?> UptimeHandle = scheduler1.scheduleAtFixedRate(Uptime_Update, 60L, 60L, TimeUnit.SECONDS);
    }

    public static void Uptime(final JDA jda) {
        Runnable Uptime_Update = new Runnable() {

            @Override
            public void run() {
                if (jda.getGatewayPing() < 1000L) {
                    long uptime = Long
                            .parseLong(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/uptime.txt"));
                    TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/uptime.txt");
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/uptime.txt",
                            Long.toString(uptime += 60000L), 1);
                    double total = System.currentTimeMillis() - 1558450050583L;
                    double operation = 100.0 / total;
                    double pourcent = operation * (double) uptime;
                    double pourcent2 = Utils.arrondi(2, pourcent);
                    jda.getGuildById("326345972739473410").getVoiceChannelById("549589565405528084").getManager()
                            .setName("Uptime : " + pourcent2 + "%").queue();
                } else {
                    long uptime = Long
                            .parseLong(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/uptime.txt"));
                    double total = System.currentTimeMillis() - 1558450050583L;
                    double operation = 100.0 / total;
                    double pourcent = operation * (double) uptime;
                    double pourcent2 = Utils.arrondi(2, pourcent);
                    jda.getGuildById("326345972739473410").getVoiceChannelById("549589565405528084").getManager()
                            .setName("Uptime : " + pourcent2 + "%").queue();
                }
            }
        };
        ScheduledFuture<?> UptimeHandle = scheduler1.scheduleAtFixedRate(Uptime_Update, 5L, 5L, TimeUnit.MINUTES);
    }

    public static void Dungeon() {
        Runnable Help_Update = new Runnable() {

            @Override
            public void run() {
                    try {
                        String name;
                        int taille = TextFileWriter.folderlength("/home/DiscordBot/Rasberry/données/bot/Map/");
                        String message2 = taille + " joueurs  (" + taille * 100 / 441 + ")\n";
                        int x = 0;
                        int y = 0;
                        int res = 0;
                        for (x = -10; x <= 10; ++x) {
                            for (y = -10; y <= 10; ++y) {
                                try {
                                    name = TextFileWriter.read(
                                            "/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt");
                                    if (!name.equals("Foret") && !name.equals("Grotte")
                                            && !name.equals("Dépot d'Argile") && !name.equals("Bétail")
                                            && !name.equals("Carrière") && !name.equals("Champs")
                                            && !name.equals("Mine"))
                                        continue;
                                    res++;
                                    continue;
                                } catch (IllegalArgumentException e1) {
                                    name = "0";
                                }
                            }
                        }
                        if (res < 25) {
                            int places = 0;
                            int alea = 1 + (int)(Math.random() * ((441- taille - 1) + 1));
                            for (x = -10; x <= 10; x++) {
                                for (y = -10; y <= 10; y++) {
                                    try {
                                        name = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Map/" + x
                                                + "_" + y + "/name.txt");
                                    } catch (Exception e1) {
                                        name = "0";
                                    }
                                    if (name.equals("0")) {
                                        places++;
                                    }
                                    if (places >= alea)
                                        break;
                                }
                                if (places >= alea)
                                    break;
                            }
                            int alea1 = 1 + (int) (Math.random() * 7.0);
                            String type = "";
                            if (alea1 == 1) {
                                type = "Foret";
                            }
                            if (alea1 == 2) {
                                type = "Dépot d'Argile";
                            }
                            if (alea1 == 3) {
                                type = "Bétail";
                            }
                            if (alea1 == 4) {
                                type = "Carrière";
                            }
                            if (alea1 == 5) {
                                type = "Champs";
                            }
                            if (alea1 == 6) {
                                type = "Mine";
                            }
                            if (alea1 == 7) {
                                type = "Grotte";
                            }
                            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y);
                            TextFileWriter.write(
                                    "/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt", type, 1);
                            int alea2 = 0;
                            alea2 = !type.equals("Grotte") ? 1000 + (int) (Math.random() * 6001.0)
                                    : 100 + (int) (Math.random() * 601.0);
                            TextFileWriter.write(
                                    "/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/res.txt",
                                    String.valueOf(alea2), 1);
                            CommandMap.PublicLog(
                                    ":camping: Un nouveau point de ressource est apparu en " + x + " " + y + ".",
                                    DiscordBot.getjda());
                        }
                        taille = TextFileWriter.folderlength("/home/DiscordBot/Rasberry/données/bot/Map/");
                        message2 = taille + " joueurs  (" + taille * 100 / 441 + ")\n";
                        x = 0;
                        y = 0;
                        int donjon = 0;
                        for (x = -10; x <= 10; ++x) {
                            for (y = -10; y <= 10; ++y) {
                                try {
                                    name = TextFileWriter.read(
                                            "/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt");
                                    if (!name.equals("dungeon"))
                                        continue;
                                    ++donjon;
                                    continue;
                                } catch (IllegalArgumentException e1) {
                                    name = "0";
                                }
                            }
                        }
                        if (donjon >= 10)
                            return;
                        int places = 0;
                        int alea = 1 + (int) (Math.random() * (double) (441 - taille - 1 + 1));
                        for (x = -10; x <= 10; ++x) {
                            for (y = -10; y <= 10; ++y) {
                                try {
                                    name = TextFileWriter.read(
                                            "/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt");
                                } catch (IllegalArgumentException e1) {
                                    name = "0";
                                }
                                if (name.equals("0")) {
                                    ++places;
                                }
                                if (places >= alea)
                                    break;
                            }
                            if (places >= alea)
                                break;
                        }
                        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y);
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt",
                                "dungeon", 1);
                        double alea1 = Math.random();
                        int pv = 0;
                        int level=1 ;
                        int atk = 0;
                        if (alea1 <= 0.28) {
                            atk = 50;
                            pv = 1000;
                            level =1;
                        } else if (alea1 <=0.51) {
                            atk = 100;
                            pv = 20000;
                            level =2;
                        } else if (alea1 <= 0.685) {
                            atk = 150;
                            pv = 3000;
                            level =3;
                        } else if (alea1 <=  0.785) {
                            atk = 200;
                            pv = 40000;
                            level =4;
                        } else if (alea1 <= 0.865) {
                            atk = 250;
                            pv = 50000;
                            level =5;
                        } else if (alea1 <= 0.925) {
                            atk = 300;
                            pv = 60000;
                            level =6;
                        } else if (alea1 <= 0.965) {
                            atk = 350;
                            pv = 70000;
                            level =7;
                        } else if (alea1 <= 0.985) {
                            atk = 400;
                            pv = 80000;
                            level =8;
                        } else if (alea1 <=  0.995) {
                            atk = 450;
                            pv = 90000;
                            level =9;
                        } else if (alea1 <= 1) {
                            atk = 500;
                            pv = 100000;
                            level =10;
                        }
                        TextFileWriter.write(
                                "/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/bosslevel.txt",
                                String.valueOf(level), 1);
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/atk.txt",
                                String.valueOf(atk), 1);
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/pv.txt",
                                String.valueOf(pv), 1);
                        CommandMap.PublicLog(":skull_crossbones: Un nouveau donjon est apparu en " + x + " " + y + ".",
                                DiscordBot.getjda());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        };
        ScheduledFuture<?> HelpHandle = scheduler1.scheduleAtFixedRate(Help_Update, 2L, 2L, TimeUnit.HOURS);
    }

    public static void Map(JDA jda) {
        Runnable Map_Update = new Runnable() {

            @Override
            public void run() {
                int x = 0;
                int y = 0;
                for (x = -10; x <= 10; ++x) {
                    for (y = -10; y <= 10; ++y) {
                        if (x % 5 != 0 || y % 5 != 0)
                            continue;
                        int soldiers = Integer.parseInt(TextFileWriter
                                .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/soldier.txt"));
                        String user = TextFileWriter
                                .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/owner.txt");
                        int xp = DiscordBot.getData().getProfils().get(user).getXp();
                        int perte = xp * 1000;
                        if ((soldiers = (int) ((double) soldiers * 0.99)) < 1) {
                            soldiers = 1;
                        }
                        TextFileWriter.write(
                                "/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/soldier.txt",
                                Integer.toString(soldiers), 1);
                    }
                }
            }
        };
        ScheduledFuture<?> MapHandle = scheduler1.scheduleAtFixedRate(Map_Update, 1L, 1L, TimeUnit.HOURS);
    }

    public static void Save(JDA jda) {
        Runnable Save_Update = new Runnable() {

            @Override
            public void run() {
                try {
                    String URL2 = "/home/DiscordBot/Rasberry/données/bot";
                    DiscordBot.getData().saveData(URL2);
                    DiscordBot.getGuilddata().saveData(URL2);
                    DiscordBot.getHypixeldata().saveData(URL2);
                    DiscordBot.getLeveldata().saveData(URL2);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    int day = calendar.get(5);
                    int mois = calendar.get(2) + 1;
                    int année = calendar.get(1);
                    String URL22 = "/home/DiscordBot/Rasberry/données/backup/" + day + "-" + mois + "-" + année;
                    if (calendar.get(11) == 1) {
                        DiscordBot.getData().saveData(URL22);
                        DiscordBot.getGuilddata().saveData(URL22);
                        DiscordBot.getHypixeldata().saveData(URL22);
                        DiscordBot.getLeveldata().saveData(URL22);
                    }
                        if ((day -= 7) <= 0) {
                            day += 30;
                            --mois;
                        }
                        if (mois <= 0) {
                            mois += 30;
                            --année;
                        }
                    File f = new File("/home/DiscordBot/Rasberry/données/backup/" + day + "-" + mois + "-" + année);
                    if(f.isFile()) TextFileWriter.recursifDelete(f);
                    System.out.println("Données enregistrées");
                } catch (Exception e) {
                    System.out.println("Les données n'ont pas pu etre enregistrés : "+e.getMessage());
                }
            }
        };
        ScheduledFuture<?> SaveHandle = scheduler1.scheduleAtFixedRate(Save_Update, 15L, 15L, TimeUnit.MINUTES);
    }

    public static void Give(final JDA jda) {
        Runnable Give_Update = new Runnable() {

            /*
             * Could not resolve type clashes
             */
            @Override
            public void run() {
                ProfilData data = DiscordBot.getData();
                try {
                    long lastcf;
                    if (System.currentTimeMillis() > week + 604800000L || debug) {
                        for (Profil profil : data.getProfils().values()) {
                            data.getProfils().get(profil.getId()).setLootboxPremium(false);
                        }
                        data.setLastLbPremium(System.currentTimeMillis());
                        debug = false;
                    }
                    if ((lastcf = data.getNextcf()) < System.currentTimeMillis() && !messcf) {
                        messcf = true;
                        CommandMap.PublicLog(":key: le coffre fort peut etre recupéré.", jda);
                    } else if (lastcf > System.currentTimeMillis() && messcf) {
                        messcf = false;
                    }
                    for (File file : TextFileWriter.folderlist("/home/DiscordBot/Rasberry/données/bot/Pays")) {
                        for (int i = 1; i <= 10; ++i) {
                            long lastpay;
                            TextFileWriter.folder(file.getAbsolutePath() + "/machine");
                            String machine = TextFileWriter
                                    .read(file.getAbsolutePath() + "/machine/machine" + i + ".txt");
                            if (machine.equals("active")) {
                                lastpay = Long.parseLong(TextFileWriter.read(
                                        file.getAbsolutePath() + "/machine/lastpay" + i + ".txt"));
                                if (System.currentTimeMillis() - lastpay <= 3600000L)
                                    continue;
                                long money = Long.parseLong(
                                        TextFileWriter.read(file.getAbsolutePath() + "/bank.txt"));
                                long cout = Long.parseLong(TextFileWriter
                                        .read(file.getAbsolutePath() + "/machine/cout" + i + ".txt"));
                                if (cout < 100000L) {
                                    cout = 100000L;
                                }
                                if (cout > money) {
                                    TextFileWriter.write(
                                            file.getAbsolutePath() + "/machine/machine" + i + ".txt",
                                            "stop", 1);
                                    continue;
                                }
                                TextFileWriter.write(file.getAbsolutePath() + "/bank.txt",
                                        Long.toString(money -= cout), 1);
                                TextFileWriter.write(
                                        file.getAbsolutePath() + "/machine/lastpay" + i + ".txt",
                                        Long.toString(System.currentTimeMillis()), 1);
                                cout = (long) ((double) cout * 1.1);
                                TextFileWriter.write(
                                        file.getAbsolutePath() + "/machine/cout" + i + ".txt",
                                        Long.toString(cout), 1);
                                continue;
                            }
                            if (!machine.equals("stop"))
                                continue;
                            lastpay = Long.parseLong(TextFileWriter
                                    .read(file.getAbsolutePath() + "/machine/lastpay" + i + ".txt"));
                            if (System.currentTimeMillis() - lastpay <= 3600000L)
                                continue;
                            long cout = Long.parseLong(TextFileWriter
                                    .read(file.getAbsolutePath() + "/machine/cout" + i + ".txt"));
                            cout = (long) ((double) cout / 1.05);
                            TextFileWriter.write(
                                    file.getAbsolutePath() + "/machine/lastpay" + i + ".txt",
                                    Long.toString(System.currentTimeMillis()), 1);
                            TextFileWriter.write(file.getAbsolutePath() + "/machine/cout" + i + ".txt",
                                    Long.toString(cout), 1);
                        }
                    }
                    for (Profil profil : data.getProfils().values()) {

                        HashMap<Long, ArrayList<String>> map = new HashMap();
                        try {
                            map = profil.getAttack();
                        } catch (NullPointerException e) {
                            map = new HashMap();
                        }
                        HashMap<Long, ArrayList<String>> map2 = map;
                        try {
                            try {
                                for (ArrayList test : map.values()) {
                                    long heure = Long.parseLong((String) test.get(0));
                                    long delay = heure - System.currentTimeMillis();
                                    if (delay >= 0L || heure == 0L)
                                        continue;
                                        int atk;
                                        if (!test.get(1).toString().contains("_")) {
                                            Profil cible = data.getProfils().get(test.get(1));
                                            try {
                                                atk = Integer.parseInt((String) test.get(2));
                                            } catch (IndexOutOfBoundsException e) {
                                                atk = 1;
                                            }
                                            String hero = (String) test.get(3);
                                            Attack.Attack(profil, cible, atk, hero);
                                            map2.remove(heure);
                                        } else {
                                            String cibleId = test.get(1).toString();
                                            String name = TextFileWriter.read(
                                                    "/home/DiscordBot/Rasberry/données/bot/Map/" + cibleId + "/name.txt");
                                            if (name.equals("dungeon")) {
                                                Attack.AttackDungeon(profil, cibleId, jda);
                                                map2.remove(heure);
                                                return;
                                            }
                                            else if (name.equals("personne")) {
                                                HashMap<String, ArrayList<String>> heroe;
                                                boolean Umail;
                                                command.Language lang = profil
                                                        .getLanguage();
                                                try {
                                                    heroe = profil.getHeroe();
                                                } catch (NullPointerException e1) {
                                                    heroe = new HashMap();
                                                    ArrayList<String> list2 = new ArrayList<String>();
                                                    list2.add("1");
                                                    list2.add("0");
                                                    list2.add("false");
                                                    list2.add("0");
                                                    heroe.put("Karl", list2);
                                                    heroe.put("Valkyrie", null);
                                                    heroe.put("Ouranos", null);
                                                    heroe.put("Oeil", null);
                                                    heroe.put("Ikaryus", null);
                                                    heroe.put("Yegarde", null);
                                                    heroe.put("Angel", null);
                                                    heroe.put("Zhen", null);
                                                    heroe.put("Hearth", null);
                                                    heroe.put("Lixie", null);
                                                    heroe.put("Akashi", null);
                                                    heroe.put("Rose", null);
                                                    heroe.put("Hell", null);
                                                    heroe.put("Spirita", null);
                                                    heroe.put("Tempest", null);
                                                    heroe.put("Ivoire", null);
                                                }
                                                ArrayList list = null;
                                                try {
                                                    list = heroe
                                                            .get(profil.getActiveHeroe());
                                                } catch (NullPointerException list2) {
                                                    // empty catch block
                                                }
                                                double pvU = Heroe
                                                        .getPV(profil.getActiveHeroe(), profil);
                                                list.set(2, "false");
                                                list.set(3, Integer.toString((int) pvU));
                                                list.set(4, Long.toString(System.currentTimeMillis()));
                                                heroe.put(profil.getActiveHeroe(), list);
                                                profil.setHeroe(heroe);
                                                try {
                                                    Umail = profil.isMail();
                                                } catch (Exception e1) {
                                                    Umail = false;
                                                }
                                                if (!Umail) {
                                              /*  if (!user.hasPrivateChannel()) {
                                                    user.openPrivateChannel().complete();
                                                }
                                                if (lang == command.Language.fr) {
                                                    ((UserImpl) user).getPrivateChannel().sendMessage(
                                                            ":crossed_swords: Rapport d'Attaque de donjon :crossed_swords: \n Votre hero est arrivé trop tard, un joueur a deja vaincu le donjon.")
                                                            .queue();
                                                }
                                                if (lang != command.Language.en)
                                                    continue;
                                                ((UserImpl) user).getPrivateChannel().sendMessage(
                                                        ":crossed_swords: Dungeon attack report :crossed_swords: \n Your hero arrived too late and another person killed the boss.")
                                                        .queue();
                                                continue;*/
                                                }
                                                ArrayList<String> mail1 = new ArrayList<String>();
                                                if (lang == command.Language.fr) {
                                                    mail1.add("Rapport d'attaque donjon");
                                                }
                                                if (lang == command.Language.en) {
                                                    mail1.add("Dungeon attack report");
                                                }
                                                if (lang == command.Language.fr) {
                                                    mail1.add(
                                                            ":crossed_swords: Rapport d'Attaque de donjon :crossed_swords: \\n Votre hero est arrivé trop tard, un joueur a deja vaincu le donjon.");
                                                }
                                                if (lang == command.Language.en) {
                                                    mail1.add(
                                                            ":crossed_swords: Dungeon attack report :crossed_swords: \n Your hero arrived too late and another person killed the boss.");
                                                }
                                                mail1.add("false");
                                                mail1.add("" + System.currentTimeMillis());
                                                try {
                                                    ArrayList<ArrayList<String>> mails = profil
                                                            .getListMail();
                                                    mails.add(0, mail1);
                                                    profil.setListMail(mails);
                                                } catch (NullPointerException e1) {
                                                    ArrayList<ArrayList<String>> mails = new ArrayList<ArrayList<String>>();
                                                    mails.add(0, mail1);
                                                    profil.setListMail(mails);
                                                }
                                                continue;
                                            }
                                            try {
                                                atk = Integer.parseInt((String) test.get(2));
                                            } catch (IndexOutOfBoundsException e1) {
                                                atk = 1;
                                            }
                                            String hero = (String) test.get(3);
                                            Attack.Attack2(profil, cibleId, atk, DiscordBot.getData(), hero);
                                            map2.remove(heure);
                                        }
                                }
                            } catch (ConcurrentModificationException test) {
                            }
                        } catch (NullPointerException test) {
                            // empty catch block
                        }
                        HashMap<Long, ArrayList<String>> map1 = new HashMap();
                        try {
                            map1 = profil.getRMD();
                        } catch (NullPointerException e) {
                            map1 = new HashMap();
                        }
                        HashMap<Long, ArrayList<String>> map21 = map1;
                        try {
                            try {
                                for (ArrayList test : map1.values()) {
                                    String messages;
                                    long heure = Long.parseLong((String) test.get(0));
                                    long delay = heure - System.currentTimeMillis();
                                    if (delay >= 0L || heure == 0L)
                                        continue;
                                    try {
                                        messages = (String) test.get(1);
                                    } catch (IndexOutOfBoundsException e) {
                                        messages = " j'ai oublié ce que c'etait";
                                    }
                                    map21.remove(heure);
                                    /*if (!user.hasPrivateChannel()) {
                                        user.openPrivateChannel().complete();
                                    }
                                    ((UserImpl) user).getPrivateChannel()
                                            .sendMessage(
                                                    String.valueOf(user.getAsMention()) + " | Remind : " + messages)
                                            .queue();*/
                                }
                            } catch (ConcurrentModificationException test) {
                                // empty catch block
                            }
                                profil.setRMD(map21);

                        } catch (NullPointerException e) {
                            // empty catch block
                        }
                        HashMap<Long, ArrayList<String>> map11 = new HashMap();
                        try {
                            map11 = profil.getGive();
                        } catch (NullPointerException e) {
                            map11 = new HashMap();
                        }
                        HashMap<Long, ArrayList<String>> map211 = map11;
                        try {
                            try {
                                for (ArrayList test : map11.values()) {
                                    int c2;
                                    String c1;
                                    String cibleId;
                                    long heure = Long.parseLong((String) test.get(0));
                                    long delay = heure - System.currentTimeMillis();
                                    if (delay >= 0L || heure == 0L)
                                        continue;
                                    try {
                                        cibleId = (String) test.get(1);
                                    } catch (IndexOutOfBoundsException e) {
                                        cibleId = "102108573298851840";
                                    }
                                    try {
                                        c1 = (String) test.get(2);
                                    } catch (IndexOutOfBoundsException e) {
                                        c1 = "bois";
                                    }
                                    try {
                                        c2 = Integer.parseInt((String) test.get(3));
                                    } catch (IndexOutOfBoundsException e) {
                                        c2 = 1;
                                    }
                                    Trade.give(c1, profil, data.getProfils().get(cibleId), c2);
                                    map211.remove(heure);
                                }
                            } catch (ConcurrentModificationException test) {
                                // empty catch block
                            }
                            profil.setGive(map211);

                        } catch (NullPointerException e) {
                            // empty catch block
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    CommandMap.Log("Give / Attack / RMD", e.getLocalizedMessage(), jda);
                }
            }
        };
        ScheduledFuture<?> GiveHandle = scheduler1.scheduleAtFixedRate(Give_Update, 30L, 10L, TimeUnit.SECONDS);
    }

    public static void Ressources(final JDA jda) {
        Runnable Ressources_Update = new Runnable() {

            @Override
            public void run() {
                    try {
                        GregorianCalendar calendar = new GregorianCalendar();
                        int minutes = calendar.get(Calendar.MINUTE);
                        int hour = calendar.get(Calendar.HOUR);
                        if (hour == LastRessourcesDate || minutes <= 0 || minutes >= 5) return;
                        ProfilData data = DiscordBot.getData();
                        LastRessourcesDate = hour;
                        int x = -5;
                        int y = -5;
                        for (x = -10; x <= 10; ++x) {
                            for (y = -10; y <= 10; ++y) {
                                int res1;
                                String owner = TextFileWriter.read(
                                        "/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/owner.txt");
                                String name = TextFileWriter
                                        .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt");
                                if (!name.equals("Foret") && !name.equals("Dépot d'Argile")
                                        && !name.equals("Bétail") && !name.equals("Carrière")
                                        && !name.equals("Champs") && !name.equals("Mine") && !name.equals("Grotte")
                                        || owner.equals("personne"))
                                    continue;
                                try {
                                    res1 = Integer.parseInt(TextFileWriter.read(
                                            "/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/res.txt"));
                                } catch (NumberFormatException e) {
                                    res1 = 0;
                                }
                                String ressources = "bois";
                                if (name.equals("Foret")) {
                                    ressources = "bois";
                                } else if (name.equals("Dépot d'Argile")) {
                                    ressources = "argile";
                                } else if (name.equals("Bétail")) {
                                    ressources = "cuir";
                                } else if (name.equals("Carrière")) {
                                    ressources = "pierre";
                                } else if (name.equals("Champs")) {
                                    ressources = "paille";
                                } else if (name.equals("Mine")) {
                                    ressources = "fer";
                                } else if (name.equals("Grotte")) {
                                    ressources = "cristal";
                                }
                                HashMap<String, Integer> res = new HashMap();
                                try {
                                    res = data.getProfils().get(owner).getRes();
                                } catch (NullPointerException e) {
                                    res = new HashMap();
                                    res.put("bois", 0);
                                    res.put("argile", 0);
                                    res.put("cuir", 0);
                                    res.put("pierre", 0);
                                    res.put("paille", 0);
                                    res.put("fer", 0);
                                    res.put("cristal", 0);
                                }
                                int mat = res.get(ressources);
                                int gain = 20;
                                if (!name.equals("Grotte")) {
                                    if (res1 > 20) {
                                        mat += 20;
                                        res1 -= 20;
                                    } else {
                                        mat += res1;
                                        res1 = 0;
                                    }
                                } else {
                                    ++mat;
                                    --res1;
                                }
                                if (res1 > 0) {
                                    TextFileWriter.write(
                                            "/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/res.txt",
                                            Integer.toString(res1), 1);
                                }
                                if (res1 <= 0) {
                                    TextFileWriter.recursifDelete(
                                            new File("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y));
                                }
                                res.put(ressources, mat);
                                try {
                                    data.getProfils().get(owner).setRes(res);
                                    continue;
                                } catch (NullPointerException e) {
                                    data.getProfils().put(owner, new Profil(owner));
                                    data.getProfils().get(owner).setRes(res);
                                }
                            }
                        }
                        Emote emoji = jda.getGuildById("326345972739473410").getEmotesByName("wood", true).get(0);
                        int alea = 1 + (int) (Math.random() * 6.0);
                        if (alea == 1) {
                            emoji = jda.getGuildById("326345972739473410").getEmotesByName("wood", true).get(0);
                        } else if (alea == 2) {
                            emoji = jda.getGuildById("326345972739473410").getEmotesByName("clay", true).get(0);
                        } else if (alea == 3) {
                            emoji = jda.getGuildById("326345972739473410").getEmotesByName("leather", true).get(0);
                        } else if (alea == 4) {
                            emoji = jda.getGuildById("326345972739473410").getEmotesByName("stone", true).get(0);
                        } else if (alea == 5) {
                            emoji = jda.getGuildById("326345972739473410").getEmotesByName("straw", true).get(0);
                        } else if (alea == 6) {
                            emoji = jda.getGuildById("326345972739473410").getEmotesByName("iron", true).get(0);
                        }
                        CommandMap.PublicLog(
                                emoji.getAsMention()
                                        + " Les joueurs present sur les zones de ressources ont recus leur butin.",
                                jda);
                    } catch (Exception e) {
                        e.printStackTrace();
                        CommandMap.Log("Point de ravitaillement", e.getLocalizedMessage(), jda);
                    }
                }
        };
        ScheduledFuture<?> RessourcesHandle = scheduler1.scheduleAtFixedRate(Ressources_Update, 2L, 1L,
                TimeUnit.MINUTES);
    }

    public static void Bid(final JDA jda) {
        Runnable Bid_Update = new Runnable() {

            @Override
            public void run() {
                GregorianCalendar calendar = new GregorianCalendar();
                int hour = calendar.get(11);
                int day = calendar.get(5);
                if (day != LastBidDate && hour == 0) {
                    String weapon;
                    String armor;
                    Double aleaWeapon1;
                    int aleaWeapon2;
                    ProfilData data = DiscordBot.getData();
                    LastBidDate = day;
                    String userid = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Bid/biduser.txt");
                    try {
                        int bidjetons = Integer.parseInt(
                                TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Bid/bidjetons.txt"));
                    } catch (NumberFormatException e) {
                        boolean bidjetons = false;
                    }
                    String object = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Bid/bid.txt");
                    String object2 = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Bid/object.txt");
                    if (object2.equals("pet")) {
                        HashMap<String, Integer> building = new HashMap();
                        building = data.getProfils().get(userid).getBuilding();
                        int pets = 0;
                        try {
                            pets = data.getProfils().get(userid).getPet().size();
                        } catch (Exception exception) {
                            // empty catch block
                        }
                        if (building.get("cirque") >= pets) {
                            HashMap<String, ArrayList<String>> pet;
                            int Parieur;
                            ArrayList<String> list = new ArrayList<String>();
                            list.add(object);
                            list.add("1");
                            try {
                                pet = data.getProfils().get(userid).getPet();
                                pet.put(object, list);
                            } catch (Exception e1) {
                                pet = new HashMap<String, ArrayList<String>>();
                                pet.put(object, list);
                            }
                            data.getProfils().get(userid).setPet(pet);
                            try {
                                Parieur = Integer
                                        .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/"
                                                + userid + "/Achievement/Parieur.txt"));
                            } catch (NumberFormatException e) {
                                Parieur = 0;
                            }
                            TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + userid
                                    + "/Achievement/Parieur.txt", Integer.toString(++Parieur), 1);
                           /* if (!user.hasPrivateChannel()) {
                                user.openPrivateChannel().complete();
                            }
                            ((UserImpl) user).getPrivateChannel()
                                    .sendMessage("Vous venez de gagner un pet nommé " + object + " dans les encheres.")
                                    .queue();
                        } else {
                            if (!user.hasPrivateChannel()) {
                                user.openPrivateChannel().complete();
                            }
                            ((UserImpl) user).getPrivateChannel().sendMessage(
                                    "Vous venez de gagner les encehres mais votre zoo est pleins ... vous avez donc perdu votre lot.")
                                    .queue();
                        }*/
                        } else if (object2.equals("building")) {
                            int Parieur;
                            HashMap<String, ArrayList<String>> house;
                            try {
                                Parieur = Integer.parseInt(DiscordBot.getData().getProfils().get(userid).getAchievement().get("Parieur").toString());
                            } catch (NumberFormatException e) {
                                Parieur = 0;
                            }
                            Parieur++;
                            try {
                                DiscordBot.getData().getProfils().get(userid).getAchievement().put("Parieur", Long.parseLong(""+Parieur));
                            }catch(Exception e){

                            }
                            ArrayList<String> list = new ArrayList<String>();
                            list.add(object);
                            list.add("10");
                            try {
                                house = data.getProfils().get(userid).getHouses();
                                house.put(object, list);
                            } catch (Exception e1) {
                                house = new HashMap<String, ArrayList<String>>();
                                house.put(object, list);
                            }
                            data.getProfils().get(userid).setHouses(house);
                        /*if (!user.hasPrivateChannel()) {
                            user.openPrivateChannel().complete();
                        }
                        ((UserImpl) user).getPrivateChannel()
                                .sendMessage(
                                        "Vous venez de gagner une habitation nommé " + object + " dans les encheres.")
                                .queue();*/
                        }
                        } else if (object2.equals("weapon")) {
                        HashMap<String, Integer> building = data.getProfils().get(userid).getBuilding();
                            HashMap<String, ArrayList<Integer>> weapons = data.getProfils().get(userid).getWeapons();
                            weapon = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Bid/bid.txt");
                            int level = Integer
                                    .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Bid/level.txt"));
                            if (LootBox.test(data.getProfils().get(userid)) <= building.get("armurerie") * 5 + 20) {
                                ArrayList<Integer> weaponList = new ArrayList();
                                try {
                                    weaponList = weapons.get(weapon);
                                    weaponList.add(level);
                                } catch (NullPointerException e) {
                                    weaponList = new ArrayList();
                                    weaponList.add(level);
                                }
                                weapons.put(weapon, weaponList);
                                TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/Bid/level.txt");
                                data.getProfils().get(userid).setWeapons(weapons);
                            /*if (!user.hasPrivateChannel()) {
                                user.openPrivateChannel().complete();
                            }
                            ((UserImpl) user).getPrivateChannel().sendMessage("Vous venez de gagner une arme nommé "
                                    + object + " de niveau " + level + " dans les encheres.").queue();
                        } else {
                            if (!user.hasPrivateChannel()) {
                                user.openPrivateChannel().complete();
                            }
                            ((UserImpl) user).getPrivateChannel()
                                    .sendMessage(
                                            "Oh non, Vous auriez pu gagner une arme nommé " + object + " de niveau "
                                                    + level + " dans les encheres mais votre entrepot est plein.")
                                    .queue();*/
                            }
                        } else if (object2.equals("armor")) {
                        HashMap<String, Integer> building = data.getProfils().get(userid).getBuilding();
                            HashMap<String, ArrayList<Integer>> armors = data.getProfils().get(userid).getArmor();
                            armor = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Bid/bid.txt");
                            int level = Integer
                                    .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Bid/level.txt"));
                            if (LootBox.test(data.getProfils().get(userid)) <= building.get("armurerie") * 5 + 20) {
                                ArrayList<Integer> armorList = new ArrayList();
                                try {
                                    armorList = armors.get(armor);
                                    armorList.add(level);
                                } catch (NullPointerException e) {
                                    armorList = new ArrayList();
                                    armorList.add(level);
                                }
                                armors.put(armor, armorList);
                                TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/Bid/level.txt");
                                data.getProfils().get(userid).setArmor(armors);
                            /*if (!user.hasPrivateChannel()) {
                                user.openPrivateChannel().complete();
                            }
                            ((UserImpl) user).getPrivateChannel().sendMessage("Vous venez de gagner une armure nommé "
                                    + object + " de niveau " + level + " dans les encheres.").queue();*/
                            } else {
                           /* if (!user.hasPrivateChannel()) {
                                user.openPrivateChannel().complete();
                            }
                            ((UserImpl) user).getPrivateChannel()
                                    .sendMessage(
                                            "Oh non, Vous auriez pu gagner une armure nommé " + object + " de niveau "
                                                    + level + " dans les encheres mais votre entrepot est plein.")
                                    .queue();
                        }*/
                            }

                    }
                    CommandMap.PublicLog(":scales: L'objet " + object + " a été remporté par " + data.getProfils().get(userid).getName()
                            + " dans l'ench\u00e8re aujourd'hui", jda);
                    TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/Bid/bid.txt");
                    TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/Bid/biduser.txt");
                    TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/Bid/bidjetons.txt");
                    int aleabid = 1 + (int) (Math.random() * 4.0);
                    object2 = null;
                    if (aleabid == 1) {
                        object2 = "pet";
                    }
                    if (aleabid == 2) {
                        object2 = "building";
                    }
                    if (aleabid == 3) {
                        object2 = "weapon";
                    }
                    if (aleabid == 4) {
                        object2 = "armor";
                    }
                    if (aleabid == 1 || aleabid == 2) {
                        int objects = TextFileWriter
                                .folderlength("/home/DiscordBot/Rasberry/données/bot/Bid/" + object2);
                        aleabid = 0 + (int) (Math.random() * (double) (objects - 0 + 1));
                        File[] file = TextFileWriter.folderlist("/home/DiscordBot/Rasberry/données/bot/Bid/" + object2);
                        File objet = file[aleabid];
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Bid/bid.txt", objet.getName(), 1);
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Bid/object.txt", object2, 1);
                    } else if (aleabid == 3) {
                        int aleaWeapons = 1 + (int) (Math.random() * 17.0);
                        weapon = "epee";
                        switch (aleaWeapons) {
                            case 1: {
                                weapon = "epee";
                                break;
                            }
                            case 2: {
                                weapon = "spectre";
                                break;
                            }
                            case 3: {
                                weapon = "arc";
                                break;
                            }
                            case 4: {
                                weapon = "arbalete";
                                break;
                            }
                            case 5: {
                                weapon = "lance";
                                break;
                            }
                            case 6: {
                                weapon = "pelle de combat";
                                break;
                            }
                            case 7: {
                                weapon = "sarbacanne";
                                break;
                            }
                            case 8: {
                                weapon = "gourdin";
                                break;
                            }
                            case 9: {
                                weapon = "flechettes";
                                break;
                            }
                            case 10: {
                                weapon = "trident";
                                break;
                            }
                            case 11: {
                                weapon = "fleau";
                                break;
                            }
                            case 12: {
                                weapon = "fouet";
                                break;
                            }
                            case 13: {
                                weapon = "baton";
                                break;
                            }
                            case 14: {
                                weapon = "fourche";
                                break;
                            }
                            case 15: {
                                weapon = "dague";
                                break;
                            }
                            case 16: {
                                weapon = "shuriken";
                                break;
                            }
                            case 17: {
                                weapon = "katana";
                                break;
                            }
                        }
                        aleaWeapon1 = Math.random();
                        aleaWeapon2 = 1;
                        if (aleaWeapon1 <= 0.5) {
                            aleaWeapon2 = 1;
                        } else if (aleaWeapon1 > 0.5 && aleaWeapon1 <= 0.75) {
                            aleaWeapon2 = 2;
                        } else if (aleaWeapon1 > 0.75 && aleaWeapon1 <= 0.88) {
                            aleaWeapon2 = 3;
                        } else if (aleaWeapon1 > 0.88 && aleaWeapon1 <= 0.96) {
                            aleaWeapon2 = 4;
                        } else if (aleaWeapon1 > 0.96 && aleaWeapon1 <= 1.0) {
                            aleaWeapon2 = 5;
                        }
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Bid/level.txt", "" + aleaWeapon2,
                                1);
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Bid/bid.txt", weapon, 1);
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Bid/object.txt", object2, 1);
                    } else if (aleabid == 4) {
                        int aleaArmors = 1 + (int) (Math.random() * 17.0);
                        armor = "armure";
                        switch (aleaArmors) {
                            case 1: {
                                armor = "armure";
                                break;
                            }
                            case 2: {
                                armor = "armure obscure";
                                break;
                            }
                            case 3: {
                                armor = "bouclier";
                                break;
                            }
                            case 4: {
                                armor = "armure lumineuse";
                                break;
                            }
                            case 5: {
                                armor = "armure royale";
                                break;
                            }
                            case 6: {
                                armor = "armure elfique";
                                break;
                            }
                            case 7: {
                                armor = "tenue en soie";
                                break;
                            }
                            case 8: {
                                armor = "armure magique";
                                break;
                            }
                            case 9: {
                                armor = "bouclier reflechissant";
                                break;
                            }
                            case 10: {
                                armor = "armure de vulcain";
                                break;
                            }
                            case 11: {
                                armor = "armure aquatique";
                                break;
                            }
                            case 12: {
                                armor = "armure magenta";
                                break;
                            }
                            case 13: {
                                armor = "armure de rubis";
                                break;
                            }
                            case 14: {
                                armor = "bouclier de cristal";
                                break;
                            }
                            case 15: {
                                armor = "bouclier de bois";
                                break;
                            }
                            case 16: {
                                armor = "armure de cuire";
                                break;
                            }
                            case 17: {
                                armor = "armure celeste";
                                break;
                            }
                        }
                        aleaWeapon1 = Math.random();
                        aleaWeapon2 = 1;
                        if (aleaWeapon1 <= 0.5) {
                            aleaWeapon2 = 1;
                        } else if (aleaWeapon1 > 0.5 && aleaWeapon1 <= 0.75) {
                            aleaWeapon2 = 2;
                        } else if (aleaWeapon1 > 0.75 && aleaWeapon1 <= 0.88) {
                            aleaWeapon2 = 3;
                        } else if (aleaWeapon1 > 0.88 && aleaWeapon1 <= 0.96) {
                            aleaWeapon2 = 4;
                        } else if (aleaWeapon1 > 0.96 && aleaWeapon1 <= 1.0) {
                            aleaWeapon2 = 5;
                        }
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Bid/level.txt", "" + aleaWeapon2,
                                1);
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Bid/bid.txt", armor, 1);
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Bid/object.txt", object2, 1);
                    } else {
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Bid/bid.txt", "Aucun", 1);
                    }
                }
            }
        };
        ScheduledFuture<?> BidHandle = scheduler1.scheduleAtFixedRate(Bid_Update, 5L, 5L, TimeUnit.MINUTES);
    }

    public static void Shop() {
        Runnable Shop_Update = new Runnable() {

            @Override
            public void run() {
                try {
                    int bois = Integer
                            .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/bois.txt"));
                    int argile = Integer
                            .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/argile.txt"));
                    int cuir = Integer
                            .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/cuir.txt"));
                    int pierre = Integer
                            .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/pierre.txt"));
                    int paille = Integer
                            .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/paille.txt"));
                    int fer = Integer
                            .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/fer.txt"));
                    int alea = Integer
                            .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/alea.txt"));
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Shop/bois.txt",
                            Integer.toString(++bois), 1);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Shop/argile.txt",
                            Integer.toString(++argile), 1);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Shop/cuir.txt",
                            Integer.toString(++cuir), 1);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Shop/pierre.txt",
                            Integer.toString(++pierre), 1);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Shop/paille.txt",
                            Integer.toString(++paille), 1);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Shop/fer.txt", Integer.toString(++fer),
                            1);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Shop/alea.txt",
                            Integer.toString(++alea), 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ScheduledFuture<?> ShopHandle = scheduler1.scheduleAtFixedRate(Shop_Update, 2L, 2L, TimeUnit.MINUTES);
    }

    public static void File_Delete(int durée, TimeUnit timeunit, final String file) {
        Runnable File_Delete_Update = new Runnable() {

            @Override
            public void run() {
                String invit = TextFileWriter.read(file);
                if (invit.equals("invite")) {
                    TextFileWriter.delete(file);
                }
            }
        };
        final ScheduledFuture<?> File_DeleteHandle = scheduler1.scheduleAtFixedRate(File_Delete_Update, durée, durée,
                timeunit);
        scheduler1.schedule(new Runnable() {

            @Override
            public void run() {
                File_DeleteHandle.cancel(true);
            }
        }, durée, timeunit);
    }

    public static void Quests(JDA jda) {
        Runnable Guild_Bonus_Update = new Runnable() {

            @Override
            public void run() {
                GregorianCalendar calendar = new GregorianCalendar();
                int hour = calendar.get(11);
                int day = calendar.get(5);
                if (day != LastQuestDate && hour == 0) {
                    String lastquest1;
                    LastQuestDate = day;
                    String quest1 = lastquest1 = TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/bot/Quests/quest1.txt");
                    while (lastquest1.equals(quest1)) {
                        int alea1 = 1 + (int) (Math.random() * 18.0);
                        quest1 = alea1 == 1 ? "tr"
                                : (alea1 == 2 ? "hr"
                                : (alea1 == 3 ? "mana"
                                : (alea1 == 4 ? "atk"
                                : (alea1 == 5 ? "def"
                                : (alea1 == 6 ? "exp"
                                : (alea1 == 7 ? "invest"
                                : (alea1 == 8 ? "vote"
                                : (alea1 == 9 ? "shop"
                                : (alea1 == 10
                                ? "soldier"
                                : (alea1 == 11
                                ? "jetons"
                                : (alea1 == 12
                                ? "materiau"
                                : (alea1 == 13
                                ? "bois"
                                : (alea1 == 14
                                ? "acier"
                                : (alea1 == 15
                                ? "brique"
                                : (alea1 == 16
                                ? "pier"
                                : (alea1 == 17
                                ? "paille"
                                : (alea1 == 18
                                ? "fer"
                                : "tr")))))))))))))))));
                    }
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Quests/quest1.txt", quest1, 1);
                    String lastquest2 = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Quests/quest2.txt");
                    String quest2 = quest1;
                    while (quest2.equals(quest1) || quest2.equals(lastquest2)) {
                        int alea2 = 1 + (int) (Math.random() * 18.0);
                        quest2 = alea2 == 1 ? "tr"
                                : (alea2 == 2 ? "hr"
                                : (alea2 == 3 ? "mana"
                                : (alea2 == 4 ? "atk"
                                : (alea2 == 5 ? "def"
                                : (alea2 == 6 ? "exp"
                                : (alea2 == 7 ? "invest"
                                : (alea2 == 8 ? "vote"
                                : (alea2 == 9 ? "shop"
                                : (alea2 == 10
                                ? "soldier"
                                : (alea2 == 11
                                ? "jetons"
                                : (alea2 == 12
                                ? "materiau"
                                : (alea2 == 13
                                ? "bois"
                                : (alea2 == 14
                                ? "acier"
                                : (alea2 == 15
                                ? "brique"
                                : (alea2 == 16
                                ? "pierre"
                                : (alea2 == 17
                                ? "paille"
                                : (alea2 == 18
                                ? "petrole"
                                : "tr")))))))))))))))));
                    }
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Quests/quest2.txt", quest2, 1);
                    String lastquest3 = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Quests/quest3.txt");
                    String quest3 = quest1;
                    while (quest3.equals(quest1) || quest3.equals(quest2) || quest3.equals(lastquest3)) {
                        int alea3 = 1 + (int) (Math.random() * 18.0);
                        quest3 = alea3 == 1 ? "tr"
                                : (alea3 == 2 ? "hr"
                                : (alea3 == 3 ? "mana"
                                : (alea3 == 4 ? "atk"
                                : (alea3 == 5 ? "def"
                                : (alea3 == 6 ? "exp"
                                : (alea3 == 7 ? "invest"
                                : (alea3 == 8 ? "vote"
                                : (alea3 == 9 ? "shop"
                                : (alea3 == 10
                                ? "soldier"
                                : (alea3 == 11
                                ? "jetons"
                                : (alea3 == 12
                                ? "materiau"
                                : (alea3 == 13
                                ? "bois"
                                : (alea3 == 14
                                ? "acier"
                                : (alea3 == 15
                                ? "brique"
                                : (alea3 == 16
                                ? "pierre"
                                : (alea3 == 17
                                ? "paille"
                                : (alea3 == 18
                                ? "petrole"
                                : "tr")))))))))))))))));
                    }
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Quests/quest3.txt", quest3, 1);
                }
            }
        };
        ScheduledFuture<?> Guild_BonusHandle = scheduler1.scheduleAtFixedRate(Guild_Bonus_Update, 5L, 5L,
                TimeUnit.MINUTES);
    }

    public static void Guild_Bonus(JDA jda) {
        Runnable Guild_Bonus_Update = new Runnable() {

            @Override
            public void run() {
                GregorianCalendar calendar = new GregorianCalendar();
                int hour = calendar.get(11);
                int day = calendar.get(5);
                if (day != LastPaysDate && hour == 0) {
                    File[] files;
                    LastPaysDate = day;
                    File repertoire = new File("/home/DiscordBot/Rasberry/données/bot/Pays/");
                    for (File file : files = repertoire.listFiles()) {
                        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/bot/Pays/" + file.getName() + "/");
                        TextFileWriter
                                .folder("/home/DiscordBot/Rasberry/données/bot/Pays/" + file.getName() + "/materiaux/");
                        TextFileWriter.delete(
                                "/home/DiscordBot/Rasberry/données/bot/Pays/" + file.getName() + "/materiaux/bois.txt");
                        TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/Pays/" + file.getName()
                                + "/materiaux/argile.txt");
                        TextFileWriter.delete(
                                "/home/DiscordBot/Rasberry/données/bot/Pays/" + file.getName() + "/materiaux/cuir.txt");
                        TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/Pays/" + file.getName()
                                + "/materiaux/pierre.txt");
                        TextFileWriter.delete("/home/DiscordBot/Rasberry/données/bot/Pays/" + file.getName()
                                + "/materiaux/paille.txt");
                        TextFileWriter.delete(
                                "/home/DiscordBot/Rasberry/données/bot/Pays/" + file.getName() + "/materiaux/fer.txt");
                        int alea3 = 1 + (int) (Math.random() * 6.0);
                        String materiau3 = "bois";
                        if (alea3 == 1) {
                            materiau3 = "bois";
                        } else if (alea3 == 2) {
                            materiau3 = "argile";
                        } else if (alea3 == 3) {
                            materiau3 = "cuir";
                        } else if (alea3 == 4) {
                            materiau3 = "pierre";
                        } else if (alea3 == 5) {
                            materiau3 = "paille";
                        } else if (alea3 == 6) {
                            materiau3 = "fer";
                        }
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + file.getName()
                                + "/materiaux/" + materiau3 + ".txt", "1", 1);
                        int alea2 = 1 + (int) (Math.random() * 6.0);
                        String materiau2 = "bois";
                        if (alea2 == 1) {
                            materiau2 = "bois";
                        } else if (alea2 == 2) {
                            materiau2 = "argile";
                        } else if (alea2 == 3) {
                            materiau2 = "cuir";
                        } else if (alea2 == 4) {
                            materiau2 = "pierre";
                        } else if (alea2 == 5) {
                            materiau2 = "paille";
                        } else if (alea2 == 6) {
                            materiau2 = "fer";
                        }
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + file.getName()
                                + "/materiaux/" + materiau2 + ".txt", "2", 1);
                        int alea21 = 1 + (int) (Math.random() * 6.0);
                        String materiau21 = "bois";
                        if (alea21 == 1) {
                            materiau21 = "bois";
                        } else if (alea21 == 2) {
                            materiau21 = "argile";
                        } else if (alea21 == 3) {
                            materiau21 = "cuir";
                        } else if (alea21 == 4) {
                            materiau21 = "pierre";
                        } else if (alea21 == 5) {
                            materiau21 = "paille";
                        } else if (alea21 == 6) {
                            materiau21 = "fer";
                        }
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + file.getName()
                                + "/materiaux/" + materiau21 + ".txt", "3", 1);
                        int alea1 = 1 + (int) (Math.random() * 6.0);
                        String materiau1 = "bois";
                        if (alea1 == 1) {
                            materiau1 = "bois";
                        } else if (alea1 == 2) {
                            materiau1 = "argile";
                        } else if (alea1 == 3) {
                            materiau1 = "cuir";
                        } else if (alea1 == 4) {
                            materiau1 = "pierre";
                        } else if (alea1 == 5) {
                            materiau1 = "paille";
                        } else if (alea1 == 6) {
                            materiau1 = "fer";
                        }
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Pays/" + file.getName()
                                + "/materiaux/" + materiau1 + ".txt", "5", 1);
                    }
                }
            }
        };
        ScheduledFuture<?> Guild_BonusHandle = scheduler1.scheduleAtFixedRate(Guild_Bonus_Update, 5L, 5L,
                TimeUnit.MINUTES);
    }

    public static void Message_Delete(int minutes, TimeUnit timeUnit, final Message message) {
        Runnable Message_Delete_Update = new Runnable() {

            @Override
            public void run() {
                message.delete().queue();
            }
        };
        final ScheduledFuture<?> Message_DeleteHandle = scheduler1.scheduleAtFixedRate(Message_Delete_Update, minutes,
                minutes, timeUnit);
        scheduler1.schedule(new Runnable() {

            @Override
            public void run() {
                Message_DeleteHandle.cancel(true);
            }
        }, minutes, timeUnit);
    }

    public static void Mute(final User user, int minutes, MessageChannel channel, final Guild guild) {
        Runnable Mute_Update = new Runnable() {

            @Override
            public void run() {
                List<Role> role = guild.getRolesByName("Muted", true);
                Role role2 = role.get(0);
                guild.removeRoleFromMember(guild.getMember(user), role2).queue();
            }
        };
        final ScheduledFuture<?> MuteHandle = scheduler1.scheduleAtFixedRate(Mute_Update, minutes, minutes,
                TimeUnit.MINUTES);
        scheduler1.schedule(new Runnable() {

            @Override
            public void run() {
                MuteHandle.cancel(true);
            }
        }, minutes, TimeUnit.MINUTES);
    }

    public static void GameUpdate(final JDA jda) {
        Runnable Game_Update = new Runnable() {

            @Override
            public void run() {
                long ping = jda.getGatewayPing();
                if (ping <= 200L) {
                    jda.getPresence().setStatus(OnlineStatus.ONLINE);
                } else if (ping > 200L && ping <= 500L) {
                    jda.getPresence().setStatus(OnlineStatus.IDLE);
                } else if (ping > 500L && ping <= 1000L) {
                    jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
                }
                jda.getPresence().setActivity(Activity.playing(
                        "try =help | " + jda.getGuilds().size() + " guilds " + jda.getUsers().size() + " users"));
                if (ping > 1000L) {
                    jda.getPresence().setActivity(Activity.playing("Probl\u00e8mes de connexion ?"));
                }
                String DiscordBotKey = TextFileWriter.read("/home/DiscordBot/Rasberry/key/DiscordBotKey.txt");
                DiscordBotListAPI api = new DiscordBotListAPI.Builder().token(DiscordBotKey).botId("399115724926484490")
                        .build();
                int serverCount = jda.getGuilds().size();
                api.setStats(serverCount);
            }
        };
        ScheduledFuture<?> GameHandle = scheduler1.scheduleAtFixedRate(Game_Update, 10L, 10L, TimeUnit.MINUTES);
    }

}
