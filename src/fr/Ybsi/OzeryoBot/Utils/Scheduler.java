/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.Ybsi.OzeryoBot.Commands.CommandMap;
import fr.Ybsi.OzeryoBot.Commands.Game.Attack;
import fr.Ybsi.OzeryoBot.Commands.Game.Heroe;
import fr.Ybsi.OzeryoBot.Commands.Game.LootBox;
import fr.Ybsi.OzeryoBot.Commands.Game.Trade;
import fr.Ybsi.OzeryoBot.Commands.command.Language;
import fr.Ybsi.OzeryoBot.Commands.SimpleCommand;
import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Level.Level;
import fr.Ybsi.OzeryoBot.Utils.GuildProfil;
import fr.Ybsi.OzeryoBot.Utils.Hypixel;
import fr.Ybsi.OzeryoBot.Utils.LevelProfil;
import fr.Ybsi.OzeryoBot.Utils.LevelProfilData;
import fr.Ybsi.OzeryoBot.Utils.Premium;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import fr.Ybsi.OzeryoBot.Utils.Utils;
import fr.Ybsi.OzeryoBot.Utils.color;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.MessageReaction;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.entities.impl.UserImpl;
import net.dv8tion.jda.core.managers.ChannelManager;
import net.dv8tion.jda.core.managers.GuildController;
import net.dv8tion.jda.core.managers.Presence;
import net.dv8tion.jda.core.requests.RestAction;
import net.dv8tion.jda.core.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.core.requests.restaction.MessageAction;
import net.dv8tion.jda.core.requests.restaction.pagination.ReactionPaginationAction;
import org.discordbots.api.client.DiscordBotListAPI;

public class Scheduler {
    public static int messages = 0;
    static int LastQuestDate = 0;
    static int LastShopDate = 0;
    static int LastBidDate = 0;
    static int LastPaysDate = 0;
    static int LastRessourcesDate = 0;
    static Long week = DiscordBot.getData().getLastLbPremium();
    public static long nextPub = System.currentTimeMillis() + 7200000L;
    public static boolean messcf = false;
    public static boolean debug = false;
    public static boolean inQueue = false;
    private static final ScheduledExecutorService scheduler1 = Executors.newScheduledThreadPool(2);
    private static final ScheduledExecutorService scheduler2 = Executors.newScheduledThreadPool(3);

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
        ScheduledFuture<?> UptimeHandle = scheduler1.scheduleAtFixedRate(Uptime_Update, 600L, 10L, TimeUnit.SECONDS);
    }

    public static void Pub(final JDA jda) {
        Runnable Uptime_Update = new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("Test 1 ");
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
                            String id = (String) list.get(alea);
                            while (id.equals(profil.getId())) {
                                alea = 1 + (int) (Math.random() * (double) (list.size() - 1 + 1));
                                id = (String) list.get(alea);
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
                            System.out.println("Test 2 ");
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

            /*
             * WARNING - void declaration
             */
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
                                        : (o == 3 ? "\ud83e\udd49" : String.valueOf(o) + "\u00e8me"));
                        String user3;
                        try {
                            user3 = jda.getUserById((String) entry.getKey()).getName();
                        } catch (NullPointerException e) {
                            user3 = "Une personne discr\u00e8te";
                        }
                        messages = String.valueOf(messages) + "<span style=\"font-weight : bold\">" + rank
                                + "</span> : " + (String) user3 + " : " + entry.getValue() + "\n";
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
                                        : (o == 3 ? "\ud83e\udd49" : String.valueOf(o) + "\u00e8me"));
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
                            member2 = String.valueOf(jda.getUserById((String) entry.getKey()).getName())
                                    + " | <span style=\"font-weight : bold\">Level</span> : " + level + emoji
                                    + " | <span style=\"font-weight : bold\">EXP</span> ";
                        } catch (NullPointerException math) {
                            // empty catch block
                        }
                        messages = String.valueOf(messages) + "<span style=\"font-weight : bold\">" + rank
                                + "</span> : " + (String) member2 + " : " + entry.getValue() + "\n";
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
                                        : (o == 3 ? "\ud83e\udd49" : String.valueOf(o) + "\u00e8me"));
                        String member = "Une personne discr\u00e8te | <span style=\"font-weight : bold\">réputation</span> ";
                        try {
                            member = String.valueOf(jda.getUserById((String) entry.getKey()).getName())
                                    + " | <span style=\"font-weight : bold\">réputation</span>";
                        } catch (NullPointerException level) {
                            // empty catch block
                        }
                        messages = String.valueOf(messages) + "<span style=\"font-weight : bold\">" + rank
                                + "</span> : " + (String) member + " : " + entry.getValue() + "\n";
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
                                        : (o == 3 ? "\ud83e\udd49" : String.valueOf(o) + "\u00e8me"));
                        String member = "Une personne discr\u00e8te | <span style=\"font-weight : bold\">Combo</span>  ";
                        try {
                            member = String.valueOf(jda.getUserById((String) entry.getKey()).getName())
                                    + " | <span style=\"font-weight : bold\">Combo</span>  ";
                        } catch (NullPointerException level) {
                            // empty catch block
                        }
                        messages = String.valueOf(messages) + "<span style=\"font-weight : bold\">" + rank
                                + "</span> : " + (String) member + " : " + entry.getValue() + "\n";
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
                                        : (o == 3 ? "\ud83e\udd49" : String.valueOf(o) + "\u00e8me"));
                        String member = " inconnu";
                        try {
                            member = jda.getUserById((String) entry.getKey()).getName();
                        } catch (Exception level) {
                            // empty catch block
                        }
                        int trophees = DiscordBot.getData().getProfils().get(entry.getKey()).getTrophy();
                        String rank1 = Attack.rank(trophees);
                        member = "Une personne discr\u00e8te | <span style=\"font-weight : bold\">Rank</span> : "
                                + rank1 + " | <span style=\"font-weight : bold\">Trophées</span>  ";
                        try {
                            member = String.valueOf(jda.getUserById((String) entry.getKey()).getName())
                                    + " | <span style=\"font-weight : bold\">Rank</span> : " + rank
                                    + " | <span style=\"font-weight : bold\">Trophées</span>  ";
                        } catch (NullPointerException member2) {
                            // empty catch block
                        }
                        messages = String.valueOf(messages) + "<span style=\"font-weight : bold\">" + rank
                                + "</span> : " + (String) member + " : " + entry.getValue() + "\n";
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
                            String member3 = String.valueOf(Guildname)
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
                                        : (o == 3 ? "\ud83e\udd49" : String.valueOf(o) + "\u00e8me"));
                        messages = String.valueOf(messages) + "<span style=\"font-weight : bold\">" + rank
                                + "</span> : " + (String) entry.getKey() + " : " + entry.getValue() + "\n";
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
                            rank5 = String.valueOf(o) + "\u00e8me";
                        }
                        String member4 = " inconnu";
                        try {
                            member4 = jda.getUserById((String) entry.getKey()).getName();
                        } catch (Exception member3) {
                            // empty catch block
                        }
                        member4 = String.valueOf(member4) + "| <span style=\"font-weight : bold\">Level</span> : "
                                + Level.level((String) entry.getKey())
                                + " | <span style=\"font-weight : bold\">EXP</span> : ";
                        messages = String.valueOf(messages) + "<span style=\"font-weight : bold\">" + (String) rank5
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
                            String member5 = String.valueOf(file.getName())
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
                                        : (o == 3 ? "\ud83e\udd49" : String.valueOf(o) + "\u00e8me"));
                        messages = String.valueOf(messages) + "<span style=\"font-weight : bold\">" + rank
                                + "</span> : " + (String) entry.getKey() + " : " + entry.getValue() + "\n";
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
                                        : (o == 3 ? "\ud83e\udd49" : String.valueOf(o) + "\u00e8me"));
                        String member = " inconnu";
                        try {
                            member = jda.getUserById((String) entry.getKey()).getName();
                        } catch (Exception Game_EXP) {
                            // empty catch block
                        }
                        messages = String.valueOf(messages) + "<span style=\"font-weight : bold\">" + rank
                                + "</span> : " + (String) member + " : " + entry.getValue() + "\n";
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
        ScheduledFuture<?> UptimeHandle = scheduler1.scheduleAtFixedRate(Uptime_Update, 1L, 1L, TimeUnit.MINUTES);
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
                            int xmax = xb+2;
                            int ymax = yb+2;
                            int minx = xb-2;
                            int miny = yb-2;
                            for (x = minx; x <= xmax; x++) {
                                xi++;
                                yi = 6;
                                for (y = miny; y <= ymax; y++) {
                                    yi--;
                                    String Case = "";
                                    String Soldier = "";
                                    String Owner = "";
                                    String Level = "";

                                    try {
                                        Case = jda
                                                .getUserById(TextFileWriter
                                                        .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt"))
                                                .getName();
                                    } catch (NullPointerException e) {
                                        Case = TextFileWriter
                                                .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt");
                                        if (Case.equals("0")) {
                                            Case = "personne";
                                        }
                                    } catch (NumberFormatException e) {
                                        Case = TextFileWriter
                                                .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt");
                                        Soldier = TextFileWriter
                                                .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/soldier.txt");
                                        Owner = "";
                                        try {
                                            Owner = jda
                                                    .getUserById(TextFileWriter.read(
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

                                    Color couleur = null;

                                        String operation = Case;
                                        if(x>10 || y>10 || x<-10 || y<-10) {
                                        	couleur = Color.white;
                                        }
                                        else if (operation.equals("personne") ) {
                                            couleur = Color.lightGray;
                                        }

                                        else if (operation.equals("dungeon")) {
                                            couleur = Color.DARK_GRAY;

                                                Owner = "Level : " + TextFileWriter
                                                        .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/bosslevel.txt");
                                                Soldier = "";
                                                Level = TextFileWriter
                                                        .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/pv.txt");
                                            
                                        }

                                        else if(!Case.equals("Foret") && !Case.equals("Grotte") && !Case.equals("Dép\u00f4t d'Argile")
                                        && !Case.equals("Bétail") && !Case.equals("Carri\u00e8re") && !Case.equals("Champs")
                                        && !Case.equals("Mine") && !Case.equals("personne") && !Case.equals("dungeon")) {
                                    couleur = Color.white;
                                        }
                                    else  couleur = Color.orange;

                                    


                    				g1.setColor(couleur);
                                    g1.fillRect(1 + 160 * (xi - 1), 1 + 160 * (yi - 1), 155, 155);
                                    g1.setColor(Color.black);

                                    if (x<-10 || x>10 || y<-10 || y>10) {
                                    }else {
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
                                ImageIO.write((RenderedImage) image, "png", new File(
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
        ScheduledFuture<?> UptimeHandle = scheduler1.scheduleAtFixedRate(Uptime_Update, 1, 1, TimeUnit.MINUTES);
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
                    block26: {
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
                        if (!((String) entry.getKey()).equals(player))
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
                    pitXP = Integer.parseInt((String) xp3);
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
                        if (!((String) entry.getKey()).equals(player))
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
        ScheduledFuture<?> UptimeHandle = scheduler1.scheduleAtFixedRate(Uptime_Update, 60L, 2L, TimeUnit.SECONDS);
    }

    public static void Uptime(final JDA jda) {
        Runnable Uptime_Update = new Runnable() {

            @Override
            public void run() {
                if (jda.getPing() < 1000L) {
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
        ScheduledFuture<?> UptimeHandle = scheduler1.scheduleAtFixedRate(Uptime_Update, 1L, 1L, TimeUnit.MINUTES);
    }

    public static void Dungeon() {
        Runnable Help_Update = new Runnable() {

            @Override
            public void run() {
                block47: {
                    try {
                        String name;
                        int taille = TextFileWriter.folderlength("/home/DiscordBot/Rasberry/données/bot/Map/");
                        String message2 = String.valueOf(taille) + " joueurs  (" + taille * 100 / 441 + ")\n";
                        int x = 0;
                        int y = 0;
                        int res = 0;
                        int alea = 1 + (int) (Math.random() * (double) (441 - taille - 1 + 1));
                        for (x = -10; x <= 10; ++x) {
                            for (y = -10; y <= 10; ++y) {
                                try {
                                    name = TextFileWriter.read(
                                            "/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt");
                                    if (!name.equals("Foret") && !name.equals("Grotte")
                                            && !name.equals("Dép\u00f4t d'Argile") && !name.equals("Bétail")
                                            && !name.equals("Carri\u00e8re") && !name.equals("Champs")
                                            && !name.equals("Mine"))
                                        continue;
                                    ++res;
                                    continue;
                                } catch (IllegalArgumentException e1) {
                                    name = "0";
                                }
                            }
                        }
                        if (res < 25) {
                            int places = 0;
                            alea = 1 + (int) (Math.random() * (double) (441 - taille - 1 + 1));
                            for (x = -10; x <= 10; ++x) {
                                for (y = -10; y <= 10; ++y) {
                                    try {
                                        name = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Map/" + x
                                                + "_" + y + "/name.txt");
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
                            int alea1 = 1 + (int) (Math.random() * 7.0);
                            String type = "";
                            if (alea1 == 1) {
                                type = "Foret";
                            }
                            if (alea1 == 2) {
                                type = "Dép\u00f4t d'Argile";
                            }
                            if (alea1 == 3) {
                                type = "Bétail";
                            }
                            if (alea1 == 4) {
                                type = "Carri\u00e8re";
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
                                    ":camping: Un nouveau point de ressourece est apparu en " + x + " " + y + ".",
                                    DiscordBot.getjda());
                        }
                        taille = TextFileWriter.folderlength("/home/DiscordBot/Rasberry/données/bot/Map/");
                        message2 = String.valueOf(taille) + " joueurs  (" + taille * 100 / 441 + ")\n";
                        x = 0;
                        y = 0;
                        int donjon = 0;
                        alea = 1 + (int) (Math.random() * (double) (441 - taille - 1 + 1));
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
                            break block47;
                        int places = 0;
                        alea = 1 + (int) (Math.random() * (double) (441 - taille - 1 + 1));
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
                        int alea1 = 1 + (int) (Math.random() * 10.0);
                        int pv = 0;
                        int level = alea1;
                        int atk = 0;
                        if (alea1 == 1) {
                            atk = 25;
                            pv = 2500;
                        } else if (alea1 == 2) {
                            atk = 50;
                            pv = 5000;
                        } else if (alea1 == 3) {
                            atk = 100;
                            pv = 7500;
                        } else if (alea1 == 4) {
                            atk = 150;
                            pv = 15000;
                        } else if (alea1 == 5) {
                            atk = 200;
                            pv = 20000;
                        } else if (alea1 == 6) {
                            atk = 200;
                            pv = 30000;
                        } else if (alea1 == 7) {
                            atk = 200;
                            pv = 40000;
                        } else if (alea1 == 8) {
                            atk = 200;
                            pv = 50000;
                        } else if (alea1 == 9) {
                            atk = 200;
                            pv = 75000;
                        } else if (alea1 == 10) {
                            atk = 200;
                            pv = 100000;
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
            }
        };
        ScheduledFuture<?> HelpHandle = scheduler1.scheduleAtFixedRate(Help_Update, 1L, 2L, TimeUnit.HOURS);
    }

    public static void Help(final String id, final TextChannel channel, final Guild guild,
            final CommandMap commandMap) {
        Runnable Help_Update = new Runnable() {

            @Override
            public void run() {
                try {
                    JDA jda = DiscordBot.getjda();
                    Message message = jda.getTextChannelById(channel.getId()).getMessageById(id).complete();
                    for (MessageReaction emoji : message.getReactions()) {
                        for (User user : emoji.getUsers()) {
                            if (user.isBot())
                                continue;
                            String emote = emoji.getReactionEmote().getName();
                            if (emote.equals("\ud83d\udc51")) {
                                String admin = "";
                                for (SimpleCommand command2 : commandMap.getCommands()) {
                                    if (command2.getExecutorType() == command.ExecutorType.CONSOLE
                                            || command2.getTopic() != command.Topics.Admin)
                                        continue;
                                    admin = String.valueOf(admin) + "``" + command2.getName() + "``, ";
                                }
                                EmbedBuilder builder1 = new EmbedBuilder();
                                builder1.setTitle("\u2139 | Commands | \u2139");
                                builder1.setColor(color.couleurAleatoire(user));
                                builder1.setAuthor(user.getName());
                                builder1.setFooter(guild.getName(), guild.getIconUrl());
                                builder1.setTimestamp(Instant.now());
                                builder1.addField("\ud83d\udc6e **Moderation** \ud83d\udc6e",
                                        new StringBuilder(admin).deleteCharAt(admin.length() - 2).toString(), false);
                                if (!user.hasPrivateChannel()) {
                                    user.openPrivateChannel().complete();
                                }
                                ((UserImpl) user).getPrivateChannel().sendMessage(builder1.build()).queue();
                                emoji.removeReaction(user).queue();
                                return;
                            }
                            if (emote.equals("\ud83c\udfae")) {
                                String game = "";
                                for (SimpleCommand command3 : commandMap.getCommands()) {
                                    if (command3.getExecutorType() == command.ExecutorType.CONSOLE
                                            || game.contains(command3.getName())
                                            || command3.getTopic() != command.Topics.Game)
                                        continue;
                                    game = String.valueOf(game) + "``" + command3.getName() + "``, ";
                                }
                                EmbedBuilder builder1 = new EmbedBuilder();
                                builder1.setTitle("\u2139 | Commands | \u2139");
                                builder1.setColor(color.couleurAleatoire(user));
                                builder1.setAuthor(user.getName());
                                builder1.setFooter(guild.getName(), guild.getIconUrl());
                                builder1.setTimestamp(Instant.now());
                                builder1.addField("\ud83c\udfae **Jeu** \ud83c\udfae",
                                        new StringBuilder(game).deleteCharAt(game.length() - 2).toString(), true);
                                if (!user.hasPrivateChannel()) {
                                    user.openPrivateChannel().complete();
                                }
                                ((UserImpl) user).getPrivateChannel().sendMessage(builder1.build()).queue();
                                emoji.removeReaction(user).queue();
                                return;
                            }
                            if (emote.equals("\ud83c\udfb5")) {
                                String music = "";
                                for (SimpleCommand command4 : commandMap.getCommands()) {
                                    if (command4.getExecutorType() == command.ExecutorType.CONSOLE
                                            || command4.getTopic() != command.Topics.Music)
                                        continue;
                                    music = String.valueOf(music) + "``" + command4.getName() + "``, ";
                                }
                                EmbedBuilder builder1 = new EmbedBuilder();
                                builder1.setTitle("\u2139 | Commands | \u2139");
                                builder1.setColor(color.couleurAleatoire(user));
                                builder1.setAuthor(user.getName());
                                builder1.setFooter(guild.getName(), guild.getIconUrl());
                                builder1.setTimestamp(Instant.now());
                                builder1.addField("\ud83c\udfb5 **Musique** \ud83c\udfb5",
                                        new StringBuilder(music).deleteCharAt(music.length() - 2).toString(), false);
                                if (!user.hasPrivateChannel()) {
                                    user.openPrivateChannel().complete();
                                }
                                ((UserImpl) user).getPrivateChannel().sendMessage(builder1.build()).queue();
                                emoji.removeReaction(user).queue();
                                return;
                            }
                            if (emote.equals("\ud83d\udd25")) {
                                String social = "";
                                for (SimpleCommand command5 : commandMap.getCommands()) {
                                    if (command5.getExecutorType() == command.ExecutorType.CONSOLE
                                            || command5.getTopic() != command.Topics.Social)
                                        continue;
                                    social = String.valueOf(social) + "``" + command5.getName() + "``, ";
                                }
                                EmbedBuilder builder1 = new EmbedBuilder();
                                builder1.setTitle("\u2139 | Commands | \u2139");
                                builder1.setColor(color.couleurAleatoire(user));
                                builder1.setAuthor(user.getName());
                                builder1.setFooter(guild.getName(), guild.getIconUrl());
                                builder1.setTimestamp(Instant.now());
                                builder1.addField("\ud83d\udd25 **Social** \ud83d\udd25",
                                        new StringBuilder(social).deleteCharAt(social.length() - 2).toString(), true);
                                if (!user.hasPrivateChannel()) {
                                    user.openPrivateChannel().complete();
                                }
                                ((UserImpl) user).getPrivateChannel().sendMessage(builder1.build()).queue();
                                emoji.removeReaction(user).queue();
                                return;
                            }
                            if (emote.equals("\u26a1")) {
                                String util = "";
                                for (SimpleCommand command6 : commandMap.getCommands()) {
                                    if (command6.getExecutorType() == command.ExecutorType.CONSOLE
                                            || command6.getTopic() != command.Topics.Util)
                                        continue;
                                    util = String.valueOf(util) + "``" + command6.getName() + "``, ";
                                }
                                EmbedBuilder builder1 = new EmbedBuilder();
                                builder1.setTitle("\u2139 | Commands | \u2139");
                                builder1.setColor(color.couleurAleatoire(user));
                                builder1.setAuthor(user.getName());
                                builder1.setFooter(guild.getName(), guild.getIconUrl());
                                builder1.setTimestamp(Instant.now());
                                builder1.addField("\u26a1  **Utilitaire** \u26a1",
                                        new StringBuilder(util).deleteCharAt(util.length() - 2).toString(), false);
                                if (!user.hasPrivateChannel()) {
                                    user.openPrivateChannel().complete();
                                }
                                ((UserImpl) user).getPrivateChannel().sendMessage(builder1.build()).queue();
                                emoji.removeReaction(user).queue();
                                return;
                            }
                            if (emote.equals("\ud83e\udd84")) {
                                String stories = "";
                                for (SimpleCommand command7 : commandMap.getCommands()) {
                                    if (command7.getExecutorType() == command.ExecutorType.CONSOLE
                                            || command7.getTopic() != command.Topics.Stories)
                                        continue;
                                    stories = String.valueOf(stories) + "``" + command7.getName() + "``, ";
                                }
                                EmbedBuilder builder1 = new EmbedBuilder();
                                builder1.setTitle("\u2139 | Commands | \u2139");
                                builder1.setColor(color.couleurAleatoire(user));
                                builder1.setAuthor(user.getName());
                                builder1.setFooter(guild.getName(), guild.getIconUrl());
                                builder1.setTimestamp(Instant.now());
                                builder1.addField("\ud83e\udd84 **Stories** \ud83e\udd84",
                                        new StringBuilder(stories).deleteCharAt(stories.length() - 2).toString(), true);
                                if (!user.hasPrivateChannel()) {
                                    user.openPrivateChannel().complete();
                                }
                                ((UserImpl) user).getPrivateChannel().sendMessage(builder1.build()).queue();
                                emoji.removeReaction(user).queue();
                                return;
                            }
                            if (!emote.equals("\ud83c\udf0e"))
                                continue;
                            String game = "";
                            String admin = "";
                            String util = "";
                            String music = "";
                            String social = "";
                            String stories = "";
                            for (SimpleCommand command8 : commandMap.getCommands()) {
                                if (command8.getExecutorType() == command.ExecutorType.CONSOLE)
                                    continue;
                                if (command8.getTopic() == command.Topics.Game) {
                                    game = String.valueOf(game) + "``" + command8.getName() + "``, ";
                                    continue;
                                }
                                if (command8.getTopic() == command.Topics.Admin) {
                                    admin = String.valueOf(admin) + "``" + command8.getName() + "``, ";
                                    continue;
                                }
                                if (command8.getTopic() == command.Topics.Util) {
                                    util = String.valueOf(util) + "``" + command8.getName() + "``, ";
                                    continue;
                                }
                                if (command8.getTopic() == command.Topics.Music) {
                                    music = String.valueOf(music) + "``" + command8.getName() + "``, ";
                                    continue;
                                }
                                if (command8.getTopic() == command.Topics.Social) {
                                    social = String.valueOf(social) + "``" + command8.getName() + "``, ";
                                    continue;
                                }
                                if (command8.getTopic() != command.Topics.Stories)
                                    continue;
                                stories = String.valueOf(stories) + "``" + command8.getName() + "``, ";
                            }
                            EmbedBuilder builder1 = new EmbedBuilder();
                            builder1.setTitle("\u2139 | Commands | \u2139");
                            builder1.setColor(color.couleurAleatoire(user));
                            builder1.setAuthor(user.getName());
                            builder1.setFooter(guild.getName(), guild.getIconUrl());
                            builder1.setTimestamp(Instant.now());
                            builder1.addField("\ud83c\udfae **Jeu** \ud83c\udfae",
                                    new StringBuilder(game).deleteCharAt(game.length() - 2).toString(), true);
                            builder1.addField("\ud83d\udc6e **Moderation** \ud83d\udc6e",
                                    new StringBuilder(admin).deleteCharAt(admin.length() - 2).toString(), false);
                            builder1.addField("\ud83c\udfb5 **Musique** \ud83c\udfb5",
                                    new StringBuilder(music).deleteCharAt(music.length() - 2).toString(), false);
                            builder1.addField("\u26a1  **Utilitaire** \u26a1",
                                    new StringBuilder(util).deleteCharAt(util.length() - 2).toString(), false);
                            builder1.addField("\ud83e\udd84 **Stories** \ud83e\udd84",
                                    new StringBuilder(stories).deleteCharAt(stories.length() - 2).toString(), true);
                            builder1.addField("\ud83d\udd25 **Social** \ud83d\udd25",
                                    new StringBuilder(social).deleteCharAt(social.length() - 2).toString(), true);
                            if (!user.hasPrivateChannel()) {
                                user.openPrivateChannel().complete();
                            }
                            ((UserImpl) user).getPrivateChannel().sendMessage(builder1.build()).queue();
                            emoji.removeReaction(user).queue();
                            return;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        final ScheduledFuture<?> HelpHandle = scheduler2.scheduleAtFixedRate(Help_Update, 5L, 1L, TimeUnit.SECONDS);
        scheduler2.schedule(new Runnable() {

            @Override
            public void run() {
                HelpHandle.cancel(true);
            }
        }, 5L, TimeUnit.MINUTES);
    }

    public static void Map(JDA jda, final ProfilData data) {
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
                        int xp = data.getProfils().get(user).getXp();
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
                    try {
                        if ((day -= 7) <= 0) {
                            day += 30;
                            --mois;
                        }
                        if (mois <= 0) {
                            mois += 30;
                            --année;
                        }
                        TextFileWriter.recursifDelete(
                                new File("/home/DiscordBot/Rasberry/données/backup/" + day + "-" + mois + "-" + année));
                    } catch (Exception exception) {
                        // empty catch block
                    }
                    System.out.println("Données enregistrées");
                } catch (Exception e) {
                    System.out.println("Les données n'ont pas pu etre enregistrés");
                }
            }
        };
        ScheduledFuture<?> SaveHandle = scheduler1.scheduleAtFixedRate(Save_Update, 1L, 1L, TimeUnit.HOURS);
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
                            TextFileWriter.folder(String.valueOf(file.getAbsolutePath()) + "/machine");
                            String machine = TextFileWriter
                                    .read(String.valueOf(file.getAbsolutePath()) + "/machine/machine" + i + ".txt");
                            if (machine.equals("active")) {
                                lastpay = Long.parseLong(TextFileWriter.read(
                                        String.valueOf(file.getAbsolutePath()) + "/machine/lastpay" + i + ".txt"));
                                if (System.currentTimeMillis() - lastpay <= 3600000L)
                                    continue;
                                long money = Long.parseLong(
                                        TextFileWriter.read(String.valueOf(file.getAbsolutePath()) + "/bank.txt"));
                                long cout = Long.parseLong(TextFileWriter
                                        .read(String.valueOf(file.getAbsolutePath()) + "/machine/cout" + i + ".txt"));
                                if (cout < 100000L) {
                                    cout = 100000L;
                                }
                                if (cout > money) {
                                    TextFileWriter.write(
                                            String.valueOf(file.getAbsolutePath()) + "/machine/machine" + i + ".txt",
                                            "stop", 1);
                                    continue;
                                }
                                TextFileWriter.write(String.valueOf(file.getAbsolutePath()) + "/bank.txt",
                                        Long.toString(money -= cout), 1);
                                TextFileWriter.write(
                                        String.valueOf(file.getAbsolutePath()) + "/machine/lastpay" + i + ".txt",
                                        Long.toString(System.currentTimeMillis()), 1);
                                cout = (long) ((double) cout * 1.1);
                                TextFileWriter.write(
                                        String.valueOf(file.getAbsolutePath()) + "/machine/cout" + i + ".txt",
                                        Long.toString(cout), 1);
                                continue;
                            }
                            if (!machine.equals("stop"))
                                continue;
                            lastpay = Long.parseLong(TextFileWriter
                                    .read(String.valueOf(file.getAbsolutePath()) + "/machine/lastpay" + i + ".txt"));
                            if (System.currentTimeMillis() - lastpay <= 3600000L)
                                continue;
                            long cout = Long.parseLong(TextFileWriter
                                    .read(String.valueOf(file.getAbsolutePath()) + "/machine/cout" + i + ".txt"));
                            cout = (long) ((double) cout / 1.05);
                            TextFileWriter.write(
                                    String.valueOf(file.getAbsolutePath()) + "/machine/lastpay" + i + ".txt",
                                    Long.toString(System.currentTimeMillis()), 1);
                            TextFileWriter.write(String.valueOf(file.getAbsolutePath()) + "/machine/cout" + i + ".txt",
                                    Long.toString(cout), 1);
                        }
                    }
                    for (Profil profil : data.getProfils().values()) {
                        User user = jda.getUserById("102108573298851840");
                        try {
                            user = jda.getUserById(profil.getId());
                        } catch (Exception e) {
                            user = jda.getUserById("102108573298851840");
                        }
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
                                    try {
                                        int atk;
                                        User cible = jda.getUserById((String) test.get(1));
                                        try {
                                            atk = Integer.parseInt((String) test.get(2));
                                        } catch (IndexOutOfBoundsException e) {
                                            atk = 1;
                                        }
                                        String hero = (String) test.get(3);
                                        Attack.Attack(user, cible, atk, hero);
                                        map2.remove(heure);
                                    } catch (NumberFormatException e) {
                                        int atk;
                                        String cibleId = (String) test.get(1);
                                        String name = TextFileWriter.read(
                                                "/home/DiscordBot/Rasberry/données/bot/Map/" + cibleId + "/name.txt");
                                        if (name.equals("dungeon")) {
                                            Attack.AttackDungeon(user, cibleId, jda);
                                            map2.remove(heure);
                                            continue;
                                        }
                                        if (name.equals("personne")) {
                                            HashMap<String, ArrayList<String>> heroe;
                                            boolean Umail;
                                            command.Language lang = DiscordBot.getData().getProfils().get(user.getId())
                                                    .getLanguage();
                                            try {
                                                heroe = data.getProfils().get(user.getId()).getHeroe();
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
                                                list = (ArrayList) heroe
                                                        .get(data.getProfils().get(user.getId()).getActiveHeroe());
                                            } catch (NullPointerException list2) {
                                                // empty catch block
                                            }
                                            double pvU = Heroe
                                                    .getPV(data.getProfils().get(user.getId()).getActiveHeroe(), user);
                                            list.set(2, "false");
                                            list.set(3, Integer.toString((int) pvU));
                                            list.set(4, Long.toString(System.currentTimeMillis()));
                                            heroe.put(data.getProfils().get(user.getId()).getActiveHeroe(), list);
                                            data.getProfils().get(user.getId()).setHeroe(heroe);
                                            try {
                                                Umail = data.getProfils().get(user.getId()).isMail();
                                            } catch (Exception e1) {
                                                Umail = false;
                                            }
                                            if (!Umail) {
                                                if (!user.hasPrivateChannel()) {
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
                                                continue;
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
                                                ArrayList<ArrayList<String>> mails = data.getProfils().get(user.getId())
                                                        .getListMail();
                                                mails.add(0, mail1);
                                                data.getProfils().get(user.getId()).setListMail(mails);
                                            } catch (NullPointerException e1) {
                                                ArrayList<ArrayList<String>> mails = new ArrayList<ArrayList<String>>();
                                                mails.add(0, mail1);
                                                data.getProfils().get(user.getId()).setListMail(mails);
                                            }
                                            continue;
                                        }
                                        try {
                                            atk = Integer.parseInt((String) test.get(2));
                                        } catch (IndexOutOfBoundsException e1) {
                                            atk = 1;
                                        }
                                        String hero = (String) test.get(3);
                                        Attack.Attack2(user, cibleId, atk, jda, hero);
                                        map2.remove(heure);
                                    }
                                }
                            } catch (ConcurrentModificationException test) {
                            }
                        } catch (NullPointerException test) {
                            // empty catch block
                        }
                        user = jda.getUserById("102108573298851840");
                        try {
                            user = jda.getUserById(profil.getId());
                        } catch (Exception e) {
                            user = jda.getUserById("102108573298851840");
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
                                    if (!user.hasPrivateChannel()) {
                                        user.openPrivateChannel().complete();
                                    }
                                    ((UserImpl) user).getPrivateChannel()
                                            .sendMessage(
                                                    String.valueOf(user.getAsMention()) + " | Remind : " + messages)
                                            .queue();
                                }
                            } catch (ConcurrentModificationException test) {
                                // empty catch block
                            }
                            try {
                                data.getProfils().get(user.getId()).setRMD(map21);
                            } catch (NullPointerException e) {
                                data.getProfils().put(user.getId(), new Profil(user.getId()));
                                data.getProfils().get(user.getId()).setRMD(map21);
                            }
                        } catch (NullPointerException e) {
                            // empty catch block
                        }
                        try {
                            user = jda.getUserById(profil.getId());
                        } catch (Exception e) {
                            user = jda.getUserById("102108573298851840");
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
                                    System.out.println(cibleId);
                                    System.out.println(c1);
                                    System.out.println(c2);
                                    Trade.give(c1, user, jda.getUserById(cibleId), c2);
                                    map211.remove(heure);
                                }
                            } catch (ConcurrentModificationException test) {
                                // empty catch block
                            }
                            try {
                                data.getProfils().get(user.getId()).setGive(map211);
                            } catch (NullPointerException e) {
                                data.getProfils().put(user.getId(), new Profil(user.getId()));
                                data.getProfils().get(user.getId()).setGive(map211);
                            }
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
        ScheduledFuture<?> GiveHandle = scheduler1.scheduleAtFixedRate(Give_Update, 2L, 1L, TimeUnit.SECONDS);
    }

    public static void Ressources(final JDA jda) {
        Runnable Ressources_Update = new Runnable() {

            @Override
            public void run() {
                block40: {
                    try {
                        GregorianCalendar calendar = new GregorianCalendar();
                        int minutes = calendar.get(12);
                        int hour = calendar.get(11);
                        if (hour == LastRessourcesDate || minutes <= 0 || minutes >= 5)
                            break block40;
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
                                if (!name.equals("Foret") && !name.equals("Dép\u00f4t d'Argile")
                                        && !name.equals("Bétail") && !name.equals("Carri\u00e8re")
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
                                } else if (name.equals("Dép\u00f4t d'Argile")) {
                                    ressources = "argile";
                                } else if (name.equals("Bétail")) {
                                    ressources = "cuir";
                                } else if (name.equals("Carri\u00e8re")) {
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
                                int mat = (Integer) res.get(ressources);
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
                                String.valueOf(emoji.getAsMention())
                                        + " Les joueurs present sur les zones de ressources ont recus leur butin.",
                                jda);
                    } catch (Exception e) {
                        e.printStackTrace();
                        CommandMap.Log("Point de ravitaillement", e.getLocalizedMessage(), jda);
                    }
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
                    User user = jda.getUserById(userid);
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
                        building = data.getProfils().get(user.getId()).getBuilding();
                        int pets = 0;
                        try {
                            pets = data.getProfils().get(user.getId()).getPet().size();
                        } catch (Exception exception) {
                            // empty catch block
                        }
                        if ((Integer) building.get("cirque") >= pets) {
                            HashMap<String, ArrayList<String>> pet;
                            int Parieur;
                            ArrayList<String> list = new ArrayList<String>();
                            list.add(object);
                            list.add("1");
                            try {
                                pet = data.getProfils().get(user.getId()).getPet();
                                pet.put(object, list);
                            } catch (Exception e1) {
                                pet = new HashMap<String, ArrayList<String>>();
                                pet.put(object, list);
                            }
                            data.getProfils().get(user.getId()).setPet(pet);
                            try {
                                Parieur = Integer
                                        .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/"
                                                + user.getId() + "/Achievement/Parieur.txt"));
                            } catch (NumberFormatException e) {
                                Parieur = 0;
                            }
                            TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId()
                                    + "/Achievement/Parieur.txt", Integer.toString(++Parieur), 1);
                            if (!user.hasPrivateChannel()) {
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
                        }
                    } else if (object2.equals("building")) {
                        int Parieur;
                        HashMap<String, ArrayList<String>> house;
                        try {
                            Parieur = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/"
                                    + user.getId() + "/Achievement/Parieur.txt"));
                        } catch (NumberFormatException e) {
                            Parieur = 0;
                        }
                        TextFileWriter.write(
                                "/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Achievement/Parieur.txt",
                                Integer.toString(++Parieur), 1);
                        ArrayList<String> list = new ArrayList<String>();
                        list.add(object);
                        list.add("10");
                        try {
                            house = data.getProfils().get(user.getId()).getHouses();
                            house.put(object, list);
                        } catch (Exception e1) {
                            house = new HashMap<String, ArrayList<String>>();
                            house.put(object, list);
                        }
                        data.getProfils().get(user.getId()).setHouses(house);
                        if (!user.hasPrivateChannel()) {
                            user.openPrivateChannel().complete();
                        }
                        ((UserImpl) user).getPrivateChannel()
                                .sendMessage(
                                        "Vous venez de gagner une habitation nommé " + object + " dans les encheres.")
                                .queue();
                    } else if (object2.equals("weapon")) {
                        HashMap<String, Integer> building = data.getProfils().get(user.getId()).getBuilding();
                        HashMap<String, ArrayList<Integer>> weapons = data.getProfils().get(user.getId()).getWeapons();
                        weapon = TextFileWriter
                                .read(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Bid/bid.txt"));
                        int level = Integer
                                .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Bid/level.txt"));
                        if (LootBox.test(user) <= building.get("armurerie") * 5 + 20) {
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
                            data.getProfils().get(user.getId()).setWeapons(weapons);
                            if (!user.hasPrivateChannel()) {
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
                                    .queue();
                        }
                    } else if (object2.equals("armor")) {
                        HashMap<String, Integer> building = data.getProfils().get(user.getId()).getBuilding();
                        HashMap<String, ArrayList<Integer>> armors = data.getProfils().get(user.getId()).getArmor();
                        armor = TextFileWriter
                                .read(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Bid/bid.txt"));
                        int level = Integer
                                .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Bid/level.txt"));
                        if (LootBox.test(user) <= building.get("armurerie") * 5 + 20) {
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
                            data.getProfils().get(user.getId()).setArmor(armors);
                            if (!user.hasPrivateChannel()) {
                                user.openPrivateChannel().complete();
                            }
                            ((UserImpl) user).getPrivateChannel().sendMessage("Vous venez de gagner une armure nommé "
                                    + object + " de niveau " + level + " dans les encheres.").queue();
                        } else {
                            if (!user.hasPrivateChannel()) {
                                user.openPrivateChannel().complete();
                            }
                            ((UserImpl) user).getPrivateChannel()
                                    .sendMessage(
                                            "Oh non, Vous auriez pu gagner une armure nommé " + object + " de niveau "
                                                    + level + " dans les encheres mais votre entrepot est plein.")
                                    .queue();
                        }
                    }
                    CommandMap.PublicLog(":scales: L'objet " + object + " a été remporté par " + user.getName()
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
        }, (long) durée, timeunit);
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
                                                                                                                                                        ? "verre"
                                                                                                                                                        : (alea1 == 17
                                                                                                                                                                ? "pierre"
                                                                                                                                                                : (alea1 == 18
                                                                                                                                                                        ? "petrole"
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
                                                                                                                                                        ? "verre"
                                                                                                                                                        : (alea2 == 17
                                                                                                                                                                ? "pierre"
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
                                                                                                                                                        ? "verre"
                                                                                                                                                        : (alea3 == 17
                                                                                                                                                                ? "pierre"
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
        }, (long) minutes, timeUnit);
    }

    public static void Mute(final User user, int minutes, MessageChannel channel, final Guild guild) {
        Runnable Mute_Update = new Runnable() {

            @Override
            public void run() {
                List<Role> role = guild.getRolesByName("Muted", true);
                Role role2 = role.get(0);
                guild.getController().removeSingleRoleFromMember(guild.getMember(user), role2).queue();
            }
        };
        final ScheduledFuture<?> MuteHandle = scheduler1.scheduleAtFixedRate(Mute_Update, minutes, minutes,
                TimeUnit.MINUTES);
        scheduler1.schedule(new Runnable() {

            @Override
            public void run() {
                MuteHandle.cancel(true);
            }
        }, (long) minutes, TimeUnit.MINUTES);
    }

    public static void GameUpdate(final JDA jda) {
        Runnable Game_Update = new Runnable() {

            @Override
            public void run() {
                long ping = jda.getPing();
                if (ping <= 200L) {
                    jda.getPresence().setStatus(OnlineStatus.ONLINE);
                } else if (ping > 200L && ping <= 500L) {
                    jda.getPresence().setStatus(OnlineStatus.IDLE);
                } else if (ping > 500L && ping <= 1000L) {
                    jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
                }
                jda.getPresence().setGame(Game.playing(
                        "try =help | " + jda.getGuilds().size() + " guilds " + jda.getUsers().size() + " users"));
                if (ping > 1000L) {
                    jda.getPresence().setGame(Game.playing("Probl\u00e8mes de connexion ?"));
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

    public static void OzeryoRoleUpdate(final JDA jda) {
        Runnable beeper = new Runnable() {

            @Override
            public void run() {
                try {
                    Guild guild = jda.getGuildById("326345972739473410");
                    for (int i = 0; i < guild.getMembers().size(); ++i) {
                        Member member;
                        int Ulevel;
                        int Game_EXP;
                        int level = Level.level(guild.getMembers().get(i).getUser().getId());
                        User user = guild.getMembers().get(i).getUser();
                        if (user.isBot())
                            continue;
                        ArrayList<Role> add_role = new ArrayList<Role>();
                        ArrayList<Role> rem_role = new ArrayList<Role>();
                        block65: for (int j = 1; j <= level + 50; ++j) {
                            String role_name = "";
                            switch (j) {
                            case 1: {
                                role_name = "[lvl1] Soldat";
                                break;
                            }
                            case 2: {
                                role_name = "[lvl2] Soldat";
                                break;
                            }
                            case 3: {
                                role_name = "[lvl3] Brigadier";
                                break;
                            }
                            case 4: {
                                role_name = "[lvl4] Brigadier";
                                break;
                            }
                            case 5: {
                                role_name = "[lvl5] Chef-Brigadier";
                                break;
                            }
                            case 6: {
                                role_name = "[lvl6] Chef-Brigadier";
                                break;
                            }
                            case 7: {
                                role_name = "[lvl7] Caporal";
                                break;
                            }
                            case 8: {
                                role_name = "[lvl8] Caporal";
                                break;
                            }
                            case 9: {
                                role_name = "[lvl9] Caporal-Chef";
                                break;
                            }
                            case 10: {
                                role_name = "[lvl10] Caporal-Chef";
                                break;
                            }
                            case 11: {
                                role_name = "[lvl11] Sergent";
                                break;
                            }
                            case 12: {
                                role_name = "[lvl12] Sergent";
                                break;
                            }
                            case 13: {
                                role_name = "[lvl13] Sergent-Chef";
                                break;
                            }
                            case 14: {
                                role_name = "[lvl14] Sergent-Chef";
                                break;
                            }
                            case 15: {
                                role_name = "[lvl15] Gendarme";
                                break;
                            }
                            case 16: {
                                role_name = "[lvl16] Gendarme";
                                break;
                            }
                            case 17: {
                                role_name = "[lvl17] Marechal";
                                break;
                            }
                            case 18: {
                                role_name = "[lvl18] Marechal";
                                break;
                            }
                            case 19: {
                                role_name = "[lvl19] Marechal-Chef";
                                break;
                            }
                            case 20: {
                                role_name = "[lvl20] Marechal-Chef";
                                break;
                            }
                            case 21: {
                                role_name = "[lvl21] Adjudant";
                                break;
                            }
                            case 22: {
                                role_name = "[lvl22] Adjudant";
                                break;
                            }
                            case 23: {
                                role_name = "[lvl23] Adjudant-Chef";
                                break;
                            }
                            case 24: {
                                role_name = "[lvl24] Adjudant-Chef";
                                break;
                            }
                            case 25: {
                                role_name = "[lvl25] Aspirant";
                                break;
                            }
                            case 26: {
                                role_name = "[lvl26] Aspirant";
                                break;
                            }
                            case 27: {
                                role_name = "[lvl27] Sous-Lieutenant";
                                break;
                            }
                            case 28: {
                                role_name = "[lvl28] Sous-Lieutenant";
                                break;
                            }
                            case 29: {
                                role_name = "[lvl29] Lieutenant";
                                break;
                            }
                            case 30: {
                                role_name = "[lvl30] Lieutenant";
                                break;
                            }
                            case 31: {
                                role_name = "[lvl31] Capitaine";
                                break;
                            }
                            case 32: {
                                role_name = "[lvl32] Capitaine";
                                break;
                            }
                            case 33: {
                                role_name = "[lvl33] Vice-Commandant";
                                break;
                            }
                            case 34: {
                                role_name = "[lvl34] Vice-Commandant";
                                break;
                            }
                            case 35: {
                                role_name = "[lvl35] Commandant";
                                break;
                            }
                            case 36: {
                                role_name = "[lvl36] Commandant";
                                break;
                            }
                            case 37: {
                                role_name = "[lvl37] Vice-Colonel";
                                break;
                            }
                            case 38: {
                                role_name = "[lvl38] Vice-Colonel";
                                break;
                            }
                            case 39: {
                                role_name = "[lvl39] Colonel";
                                break;
                            }
                            case 40: {
                                role_name = "[lvl40] Colonel";
                                break;
                            }
                            case 41: {
                                role_name = "[lvl41] Lieutenant-Colonel";
                                break;
                            }
                            case 42: {
                                role_name = "[lvl42] Lieutenant-Colonel";
                                break;
                            }
                            case 43: {
                                role_name = "[lvl43] Vice-Amiral";
                                break;
                            }
                            case 44: {
                                role_name = "[lvl44] Vice-Amiral";
                                break;
                            }
                            case 45: {
                                role_name = "[lvl45] Amiral";
                                break;
                            }
                            case 46: {
                                role_name = "[lvl46] Amiral";
                                break;
                            }
                            case 47: {
                                role_name = "[lvl47] Vice-Général";
                                break;
                            }
                            case 48: {
                                role_name = "[lvl48] Vice-Général";
                                break;
                            }
                            case 49: {
                                role_name = "[lvl49] Général";
                                break;
                            }
                            case 50: {
                                role_name = "[lvl50] Général";
                                break;
                            }
                            default: {
                                role_name = "[lvl??] Dieu";
                            }
                            }
                            if (j < level) {
                                rem_role.addAll(guild.getRolesByName(role_name, true));
                            } else if (j > level) {
                                rem_role.addAll(guild.getRolesByName(role_name, true));
                            } else if (j == level) {
                                add_role.addAll(guild.getRolesByName(role_name, true));
                            }
                            guild.getController().addRolesToMember(guild.getMembers().get(i),
                                    guild.getRolesByName("\u1801\u1801\u1801 Social levels \u1801\u1801\u1801", true))
                                    .queue();
                            int boucle = 2;
                            String rank = null;
                            try {
                                rank = TextFileWriter.read1(
                                        "/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/rank.txt",
                                        boucle);
                                while (rank != null) {
                                    if (rank.equals("0") || rank == null || rank.equals(""))
                                        continue block65;
                                    guild.getController()
                                            .addRolesToMember(guild.getMembers().get(i),
                                                    guild.getRolesByName(
                                                            "\u1801\u1801\u1801 Events ranks \u1801\u1801\u1801", true))
                                            .queue();
                                    add_role.addAll(guild.getRolesByName(rank, true));
                                    rank = TextFileWriter.read1(
                                            "/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/rank.txt",
                                            ++boucle);
                                }
                                continue;
                            } catch (NullPointerException nullPointerException) {
                                // empty catch block
                            }
                        }
                        if (Premium.Premium(guild.getMembers().get(i).getUser())) {
                            add_role.addAll(guild.getRolesByName("\ud83c\udf66 Premium", true));
                        }
                        if (!Premium.Premium(guild.getMembers().get(i).getUser())) {
                            rem_role.addAll(guild.getRolesByName("\ud83c\udf66 Premium", true));
                        }
                        if (level >= 1) {
                            rem_role.addAll(guild.getRolesByName("Néophyte", true));
                        } else {
                            add_role.addAll(guild.getRolesByName("Néophyte", true));
                        }
                        guild.getController().removeRolesFromMember(guild.getMembers().get(i), rem_role).queue();
                        guild.getController().addRolesToMember(guild.getMembers().get(i), add_role).queue();
                        try {
                            Game_EXP = DiscordBot.getData().getProfils().get(user.getId()).getXp();
                        } catch (NullPointerException e) {
                            Game_EXP = 0;
                        }
                        try {
                            double operation = 3 * Game_EXP / 4;
                            double math = Math.sqrt(operation);
                            Ulevel = (int) Math.round(math);
                        } catch (NullPointerException e) {
                            Ulevel = 0;
                        }
                        ArrayList<Role> add_role1 = new ArrayList<Role>();
                        ArrayList<Role> rem_role1 = new ArrayList<Role>();
                        for (int j = -1; j <= 1100; j += 50) {
                            String role = "";
                            if (j < 50) {
                                role = "NOMADE | 0 - 50 \u272f";
                            } else if (j >= 50 && j < 100) {
                                role = "NOMADE | 50 - 100 \u272f";
                            } else if (j >= 100 && j < 150) {
                                role = "CAMPEMENT | 100 - 150 \u2606";
                            } else if (j >= 150 && j < 200) {
                                role = "CAMPEMENT | 150 - 200 \u2606";
                            } else if (j >= 200 && j < 250) {
                                role = "CAMPEMENT FORTIFIE | 200 - 250 \u2605";
                            } else if (j >= 250 && j < 300) {
                                role = "CAMPEMENT FORTIFIE | 250 - 300 \u2605";
                            } else if (j >= 300 && j < 350) {
                                role = "HAMEAU | 300 - 350 \u2729";
                            } else if (j >= 350 && j < 400) {
                                role = "HAMEAU | 350 - 400 \u2729";
                            } else if (j >= 400 && j < 450) {
                                role = "VILLAGE | 400 - 450 \u272d";
                            } else if (j >= 450 && j < 500) {
                                role = "VILLAGE | 450 - 500 \u272d";
                            } else if (j >= 500 && j < 550) {
                                role = "BOURG | 500 - 550 \u2730";
                            } else if (j >= 550 && j < 600) {
                                role = "BOURG | 550 - 600 \u2730";
                            } else if (j >= 600 && j < 650) {
                                role = "VILLE | 600 - 650 \u272c";
                            } else if (j >= 650 && j < 700) {
                                role = "VILLE | 650 - 700 \u272c";
                            } else if (j >= 700 && j < 750) {
                                role = "CITE | 700 - 750 \u272a";
                            } else if (j >= 750 && j < 800) {
                                role = "CITE | 750 - 800 \u272a";
                            } else if (j >= 800 && j < 850) {
                                role = "CITADELLE | 800 - 850 \u2726";
                            } else if (j >= 850 && j < 900) {
                                role = "CITADELLE | 850 - 900 \u2726";
                            } else if (j >= 900 && j < 950) {
                                role = "CAPITALE | 900 - 950 \u2727";
                            } else if (j >= 950 && j < 1000) {
                                role = "CAPITALE | 950 - 1000 \u2727";
                            } else if (j >= 1000) {
                                role = "UTOPIE | 1000 + \ud83c\udf11";
                            }
                            if (j < Ulevel + 51 && j > Ulevel - 51) {
                                add_role1.addAll(guild.getRolesByName(role, true));
                                continue;
                            }
                            rem_role1.addAll(guild.getRolesByName(role, true));
                        }
                        guild.getController().removeRolesFromMember(guild.getMembers().get(i), rem_role1).queue();
                        guild.getController()
                                .addRolesToMember(guild.getMembers().get(i),
                                        guild.getRolesByName("\u1801\u1801\u1801 Game levels \u1801\u1801\u1801", true))
                                .queue();
                        guild.getController().addRolesToMember(guild.getMembers().get(i), add_role1).queue();
                        if (guild.getMembers().get(i).getUser().getId().equals("102108573298851840")
                                || guild.getMembers().get(i).getUser().getId().equals("284730935331782657")
                                || guild.getMembers().get(i).getUser().getId().equals("502535486691082279")
                                || guild.getMembers().get(i).getUser().getId().equals("249987060365000704")) {
                            guild.getController()
                                    .addRolesToMember(guild.getMembers().get(i), guild
                                            .getRolesByName("\u1801\u1801\u1801 Server ranks \u1801\u1801\u1801", true))
                                    .queue();
                        } else {
                            guild.getController()
                                    .removeRolesFromMember(guild.getMembers().get(i), guild
                                            .getRolesByName("\u1801\u1801\u1801 Server ranks \u1801\u1801\u1801", true))
                                    .queue();
                        }
                        String emoji = "";
                        if (Ulevel >= 200 && Ulevel < 300) {
                            emoji = "\ud83d\udc9b";
                        } else if (Ulevel >= 300 && Ulevel < 400) {
                            emoji = "\ud83d\udc9a";
                        } else if (Ulevel >= 400 && Ulevel < 500) {
                            emoji = "\ud83d\udc99";
                        } else if (Ulevel >= 500 && Ulevel < 600) {
                            emoji = "\ud83d\udc9c";
                        } else if (Ulevel >= 600) {
                            emoji = "\ud83d\udda4";
                        }
                        Message mess = jda.getGuildById("326345972739473410").getTextChannelById("392366630912655372")
                                .getMessageById("542026527953649664").complete();
                        List list = null;
                        List list2 = null;
                        List list3 = null;
                        List list4 = null;
                        for (MessageReaction mess1 : mess.getReactions()) {
                            if (mess1.getReactionEmote().getName().equals("broken_heart")) {
                                list = (List) mess1.getUsers().complete();
                                continue;
                            }
                            if (mess1.getReactionEmote().getName().equals(jda.getGuildById("326345972739473410")
                                    .getEmotesByName("levelupbadge", true).get(0).getName())) {
                                list2 = (List) mess1.getUsers().complete();
                                continue;
                            }
                            if (mess1.getReactionEmote().getName().equals(":white_check_mark:")) {
                                list4 = (List) mess1.getUsers().complete();
                                continue;
                            }
                            if (!mess1.getReactionEmote().getName().equals("\ud83d\udcf2"))
                                continue;
                            list3 = (List) mess1.getUsers().complete();
                        }
                        boolean emoji1 = true;
                        for (User user1 : mess.getReactions().get(0).getUsers()) {
                            if (!user1.getId().equals(user.getId()))
                                continue;
                            emoji1 = false;
                        }
                        boolean level1 = true;
                        for (User user1 : mess.getReactions().get(1).getUsers()) {
                            if (!user1.getId().equals(user.getId()))
                                continue;
                            level1 = false;
                        }
                        String pseudo = guild.getMembers().get(i).getEffectiveName();
                        String[] args = pseudo.split(" ");
                        if (args[0].contains("[") && args[0].contains("]")) {
                            pseudo = pseudo.replace(String.valueOf(args[0]) + " ", "");
                        }
                        if (emoji1 && level1) {
                            if (Ulevel != 0) {
                                if (guild.getSelfMember().hasPermission(Permission.NICKNAME_MANAGE)
                                        && user.getName().length() <= 27) {
                                    if (!guild.getMembers().get(i).hasPermission(Permission.ADMINISTRATOR)) {
                                        guild.getController().setNickname(guild.getMembers().get(i),
                                                "[" + Ulevel + emoji + "] " + pseudo).queue();
                                    }
                                }
                            }
                        } else if (!emoji1 && level1) {
                            if (Ulevel != 0) {
                                if (guild.getSelfMember().hasPermission(Permission.NICKNAME_MANAGE)
                                        && user.getName().length() <= 27) {
                                    if (!guild.getMembers().get(i).hasPermission(Permission.ADMINISTRATOR)) {
                                        guild.getController()
                                                .setNickname(guild.getMembers().get(i), "[" + Ulevel + "] " + pseudo)
                                                .queue();
                                    }
                                }
                            }
                        } else if (emoji1 && !level1) {
                            if (Ulevel != 0) {
                                if (guild.getSelfMember().hasPermission(Permission.NICKNAME_MANAGE)
                                        && user.getName().length() <= 27) {
                                    if (!guild.getMembers().get(i).hasPermission(Permission.ADMINISTRATOR)) {
                                        guild.getController()
                                                .setNickname(guild.getMembers().get(i), "[" + emoji + "] " + pseudo)
                                                .queue();
                                    }
                                }
                            }
                        } else if (Ulevel != 0) {
                            if (guild.getSelfMember().hasPermission(Permission.NICKNAME_MANAGE)
                                    && user.getName().length() <= 27) {
                                if (!guild.getMembers().get(i).hasPermission(Permission.ADMINISTRATOR)) {
                                    guild.getController().setNickname(guild.getMembers().get(i), pseudo).queue();
                                }
                            }
                        }
                        for (User user1 : mess.getReactions().get(2).getUsers()) {
                            member = null;
                            try {
                                member = guild.getMember(user1);
                            } catch (Exception exception) {
                                // empty catch block
                            }
                            if (member == null)
                                continue;
                            if (user1.getId().equals(user.getId())) {
                                guild.getController()
                                        .addRolesToMember(member, guild.getRolesByName(
                                                "᠁᠁᠁ Notifications settings ᠁᠁᠁", true))
                                        .queue();
                                guild.getController().addRolesToMember(member,
                                        guild.getRolesByName("🔔 notifications", true));
                                continue;
                            }
                            guild.getController().removeRolesFromMember(member,
                                    guild.getRolesByName("🔔 notifications", true));
                        }
                        for (User user1 : mess.getReactions().get(3).getUsers()) {
                            member = null;
                            try {
                                member = guild.getMember(user1);
                            } catch (Exception exception) {
                                // empty catch block
                            }
                            if (member == null)
                                continue;
                            if (user1.getId().equals(user.getId())) {
                                guild.getController()
                                        .addRolesToMember(guild.getMember(user1), guild.getRolesByName(
                                                "᠁᠁᠁ Notifications settings ᠁᠁᠁", true))
                                        .queue();
                                guild.getController().addRolesToMember(guild.getMember(user1),
                                        guild.getRolesByName("🔔 notif-events", true));
                                continue;
                            }
                            guild.getController().removeRolesFromMember(guild.getMember(user1),
                                    guild.getRolesByName("🔔 notif-events", true));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ScheduledFuture<?> beeperHandle = scheduler1.scheduleAtFixedRate(beeper, 1L, 1L, TimeUnit.MINUTES);
    }

}
