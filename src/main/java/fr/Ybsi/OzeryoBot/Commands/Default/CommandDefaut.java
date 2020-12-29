/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Default;

import fr.Ybsi.OzeryoBot.Commands.CommandMap;
import fr.Ybsi.OzeryoBot.Commands.Game.Attack;
import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.Commands.command.ExecutorType;
import fr.Ybsi.OzeryoBot.Commands.command.Language;
import fr.Ybsi.OzeryoBot.Commands.command.Topics;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Event.BotListener;
import fr.Ybsi.OzeryoBot.Level.EXP;
import fr.Ybsi.OzeryoBot.Level.Level;
import fr.Ybsi.OzeryoBot.Utils.Event;
import fr.Ybsi.OzeryoBot.Utils.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.internal.entities.UserImpl;
import org.discordbots.api.client.DiscordBotListAPI;

import javax.imageio.ImageIO;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.util.Map.Entry;


public class CommandDefaut {

    private final DiscordBot DiscordBot;

    public CommandDefaut(DiscordBot DiscordBot2) {
        this.DiscordBot = DiscordBot2;
    }


    @command(name = "Bstop", type = ExecutorType.CONSOLE)
    private void Bstop() {
        this.DiscordBot.setrunning(false);
    }

    @command(name = "ram", type = ExecutorType.ALL, descfr = "Permet de voir toutes les informations sur l'utilisation de la ram par le bot", descen = "Lets see all the information on the use of the ram by the bot.", rank = 1, topic = Topics.Admin)
    private void ram(User user, MessageChannel Channel2, Guild guild, JDA jda) {
        long totalram = Runtime.getRuntime().totalMemory();
        long maxram = Runtime.getRuntime().maxMemory();
        long freeram = Runtime.getRuntime().freeMemory();
        Channel2.sendMessage("TotalRam : " + (totalram /= 1000000L) + "\n" + "MaxRam : " + (maxram /= 1000000L) + "\n"
                + "FreeRam : " + (freeram /= 1000000L) + "\n" + "TotalRam - FreeRam : " + (totalram - freeram) + "\n"
                + "MaxRam - FreeRam : " + (maxram - freeram) + "\n").queue();
    }

    @command(name = "info", type = ExecutorType.ALL, descfr = "Permet de voir toute les informations sur le bot.", descen = "Lets see all the information about the bot.", rank = 1)
    private void info(User user, MessageChannel Channel2, Guild guild, JDA jda, Language lang) {
        long ping;
        long ram1 = Runtime.getRuntime().totalMemory();
        long ram2 = Runtime.getRuntime().freeMemory();
        ram1 /= 1000000L;
        ram1 -= (ram2 /= 1000000L);
        int shard = user.getJDA().getShardInfo().getShardId() + 1;
        int secondes = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/secondes.txt"));
        int minutes = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/minutes.txt"));
        int heures = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/heures.txt"));
        int jours = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/jours.txt"));
        int mois = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/mois.txt"));
        String Ssecondes = new SimpleDateFormat("ss", Locale.FRANCE).format(new Date());
        String Sminutes = new SimpleDateFormat("mm", Locale.FRANCE).format(new Date());
        String Sheures = new SimpleDateFormat("HH", Locale.FRANCE).format(new Date());
        String Sjours = new SimpleDateFormat("dd", Locale.FRANCE).format(new Date());
        String Smois = new SimpleDateFormat("MM", Locale.FRANCE).format(new Date());
        int Usecondes = Integer.parseInt(Ssecondes) - secondes;
        int Uminutes = Integer.parseInt(Sminutes) - minutes;
        int Uheures = Integer.parseInt(Sheures) - heures;
        int Ujours = Integer.parseInt(Sjours) - jours;
        int Umois = Integer.parseInt(Smois) - mois;
        if (Usecondes < 0) {
            Usecondes += 60;
            --Uminutes;
        }
        if (Uminutes < 0) {
            Uminutes += 60;
            --Uheures;
        }
        if (Uheures < 0) {
            Uheures += 24;
            --Ujours;
        }
        if (Ujours < 0) {
            Ujours += 30;
            --Umois;
        }
        String logo = (ping = jda.getGatewayPing()) <= 150L ? "\ud83d\udcd8"
                : (ping > 150L && ping <= 200L ? "\ud83d\udcd7"
                : (ping > 200L && ping <= 500L ? "\ud83d\udcd9" : "\ud83d\udcd5"));
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(user.getName(), String.valueOf(user.getAvatarUrl()) + "?size=256");
        builder.setTitle("Information");
        builder.setFooter(guild.getName(), guild.getIconUrl());
        builder.setColor(color.couleurAleatoire(user));
        String dev = "";
        if (lang == Language.en) {
            dev = "developer";
        } else if (lang == Language.fr) {
            dev = "developpeur";
        }
        builder.addField("\ud83e\udd34 " + dev + " \ud83e\udd34", "Ybsi#0451", true);
        builder.addBlankField(true);
        String website = "";
        if (lang == Language.en) {
            website = "Website";
        } else if (lang == Language.fr) {
            website = "Site Web";
        }
        builder.addField("\ud83c\udf10 " + website + " \ud83c\udf10", "http://ozeryo.xyz", true);
        builder.addField("\u23f0 ping \u23f0", String.valueOf(user.getJDA().getGatewayPing()) + "ms " + logo, true);
        builder.addField("\ud83d\udcbb RAM \ud83d\udcbb", String.valueOf(ram1) + "Mo", true);
        builder.addField("\ud83d\udd05 Uptime \ud83d\udd05",
                String.valueOf(Ujours) + "j" + Uheures + "h" + Uminutes + "m" + Usecondes + "s", true);
        builder.addField("\ud83d\udd37 Guilds \ud83d\udd37",
                String.valueOf(user.getJDA().getGuilds().size()) + " guilds", true);
        builder.addField("Shard", "Shard " + shard + " / " + user.getJDA().getShardInfo().getShardTotal(), true);
        builder.addField("\ud83d\udd36 Users \ud83d\udd36", String.valueOf(user.getJDA().getUsers().size()) + " users",
                true);
        builder.addField("Messages", String.valueOf(BotListener.messages), true);
        builder.addField("Commands", String.valueOf(CommandMap.commands1), true);
        Channel2.sendMessage(builder.build()).queue();
    }

    @command(name = "invite", type = ExecutorType.ALL, descfr = "invite OzeryoBot sur ton serveur !!!", descen = " lets invite the bot on your server")
    private void invite(User user, MessageChannel Channel2, Language lang) {
        if (lang == Language.fr) {
            Channel2.sendMessage(
                    "Pour obtenir OzeryoBot sur ton serveur : \n\n https://discord.com/oauth2/authorize?client_id=768122696210186292&scope=bot&permissions=2146958847 \n\n:top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: ")
                    .complete();
        } else if (lang == Language.en) {
            Channel2.sendMessage(
                    " Click here to get OzeryoBot on your server : \n\n https://discord.com/oauth2/authorize?client_id=768122696210186292&scope=bot&permissions=2146958847 \n\n:top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: :top: ")
                    .complete();
        }
    }

    @command(name = "serveur", type = ExecutorType.ALL, descfr = "Permet de rejoindre le serveur officiel d'OzeryoBot", descen = "allows to join the official OzeryoBot server")
    private void serveur(User user, MessageChannel Channel2, Language lang) {
        if (lang == Language.fr) {
            Channel2.sendMessage(
                    ":boom: :boom: :boom:   Rejoint vite notre serveur !!! :boom: :boom: :boom: :boom: \n\n                https://discord.gg/6c8NXk2")
                    .complete();
        } else if (lang == Language.en) {
            Channel2.sendMessage(
                    ":boom: :boom: :boom: Join our Main Server !!! :boom: :boom: :boom: :boom: \n\n                https://discord.gg/6c8NXk2")
                    .complete();
        }
    }

    @command(name = "say", type = ExecutorType.ALL, descfr = " usage : =say [message]\n Le bot repete ton message", descen = "use : =say [message]\n The bot will repeat your message")
    private void say(String[] args, MessageChannel channel, User user, Message messages) {
        String message = messages.getContentRaw().replace("=say ", "");
        if (message.contains("@everyone")) {
            channel.sendMessage("non mais tu reve la ??").queue();
        } else if (message.contains("@here")) {
            channel.sendMessage("non mais tu reve la ??").queue();
        } else {
            channel.sendMessage(messages.getContentRaw().replace("=say ", "")).complete();
        }
    }

    @command(name = "prefix", power = 100, type = ExecutorType.ALL, descfr = "change le prefix du bot", descen = "allows you to edit the bot prefix", topic = Topics.Modo)
    private void prefix(String[] args, MessageChannel channel, User user, Guild guild, Language lang) {
        if (guild.getMember(user).hasPermission(Permission.ADMINISTRATOR)) {
            StringBuilder builder = new StringBuilder();
            for (String str : args) {
                if (builder.length() > 0) {
                    builder.append(" ");
                }
                builder.append(str);
            }
            if (builder.toString().equals("") || builder.toString() == null) {
                if (lang == Language.fr) {
                    channel.sendMessage("Syntaxe : ``=prefix [prefix]").queue();
                } else if (lang == Language.fr) {
                    channel.sendMessage("Syntax : ``=prefix [prefix]").queue();
                }
                return;
            }
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/prefix.txt",
                    builder.toString(), 1);
            if (lang == Language.fr) {
                channel.sendMessage("Le prefix est désormais : " + builder.toString()).queue();
            } else if (lang == Language.en) {
                channel.sendMessage("The prefix is now : " + builder.toString()).queue();
            }
        } else {
            if (lang == Language.fr) {
                channel.sendMessage("Vous n'avez pas les permissions nécessaire pour executer cette action.").queue();
            } else if (lang == Language.en) {
                channel.sendMessage("You don't have the required permissions to perform this action.").queue();
            }
            return;
        }
    }

    @command(name = "spam", type = ExecutorType.ALL, descfr = "Te permet de spam un message ", descen = "Allow you to spam a message", topic = Topics.Admin)
    private void spam(String[] args, MessageChannel channel, User user, int[] arg, Guild guild, Language lang) {
        if (user.getId().equals("102108573298851840")) {
            String string = "";
            StringBuilder builder = new StringBuilder();
            for (String str : args) {
                if (str.equals(args[0]))
                    continue;
                builder.append(String.valueOf(str) + " ");
            }
            int c1 = Integer.parseInt(args[0]) + 1;
            String c2 = builder.toString();
            if (builder.toString().contains("@everyone")) {
                channel.sendMessage("non mais tu reve la ??").queue();
            } else if (builder.toString().contains("@here")) {
                channel.sendMessage("non mais tu reve la ??").queue();
            } else {
                for (int i = 1; i < c1; ++i) {
                    channel.sendMessage(c2).queue();
                }
            }
        } else if (lang == Language.fr) {
            channel.sendMessage("Cette commande a déja fait trop de dommage donc reve pour avoir l'acces !!").queue();
        } else if (lang == Language.en) {
            channel.sendMessage("This command make has already done too much demage so she is no longer available")
                    .queue();
        }
    }

    @command(name = "choix", type = ExecutorType.ALL, descfr = "usage : =choix [choix 1] / [choix 2]\n Je choisit a ta place", descen = " use ``=choix [choix 1] / [choix 2] \n The bot will choose for you")
    public void choix(String[] args, MessageChannel channel, Message message, Language lang, String mess) {
        try {
            mess.replace("choix", "");
            String[] newArgs = mess.split("/");
            String c1 = newArgs[0];
            String c2 = newArgs[1];
            if (c1.equals("") || c2.equals("")) {
                if (lang == Language.fr) {
                    channel.sendMessage("``Syntaxe`` : =choix <CHOIX_1> / <CHOIX_2> ").queue();
                } else if (lang == Language.en) {
                    channel.sendMessage("``Syntax`` : =choix <CHOIX_1> / <CHOIX_2>").queue();
                }
            } else if (Math.random() < 0.5) {
                if (lang == Language.fr) {
                    channel.sendMessage("Je choisis : " + c1).queue();
                } else if (lang == Language.en) {
                    channel.sendMessage("I choose : " + c1).queue();
                }
            } else if (lang == Language.fr) {
                channel.sendMessage("Je choisis : " + c2).queue();
            } else if (lang == Language.en) {
                channel.sendMessage("I choose : " + c2).queue();
            }
        } catch (Exception e) {
            channel.sendMessage("Usage incorrect, Il faut faire : =choix <CHOIX_1> / <CHOIX_2> ").queue();
            return;
        }
    }

    @command(name = "teest", type = ExecutorType.ALL, descfr = "Affiche le level d'un joueur", descen = "Shows the level of a player", topic = Topics.Social)
    private void teest(MessageChannel channel, Message message, String[] args, User user, Guild guild, ProfilData data,
                       Language lang, JDA jda) {

        for(User user1 : jda.getUsers()) {
            channel.sendMessage(user1.getName()).queue();
        }

    }

    @command(name = "stats", type = ExecutorType.ALL, descfr = "Affiche le level d'un joueur", descen = "Shows the level of a player", topic = Topics.Social)
    private void stats(MessageChannel channel, Message message, String[] args, User user, Guild guild, ProfilData data,
                       Language lang, JDA jda) {
        User cible;
        try {
            cible = message.getMentionedUsers().get(0);
        } catch (IndexOutOfBoundsException e) {
            try {
                cible = jda.getUserById(args[0]);
            } catch (Exception e1) {
                cible = user;
            }
        }
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Player Stats");
        builder.setAuthor(cible.getName(), null, cible.getAvatarUrl());
        builder.setColor(color.couleurAleatoire(cible));
        builder.setFooter(guild.getName(), guild.getIconUrl());
        if (lang == Language.fr) {
            builder.setDescription("To access to your stats click on the link : [Stats de " + cible.getName()
                    + "](https://ozeryo.xyz/profile.php?id=" + cible.getId() + ")");
        }
        if (lang == Language.en) {
            builder.setDescription("To access to your stats click on the link : [Stats de " + cible.getName()
                    + "](https://ozeryo.xyz/profile.php?id=" + cible.getId() + ")");
        }
        channel.sendMessage(builder.build()).queue();
    }

    @command(name = "level", type = ExecutorType.ALL, descfr = "Affiche le level d'un joueur", descen = "Shows the level of a player", topic = Topics.Social)
    private void level(MessageChannel message, User user, ProfilData data, Language lang) {
        int level = Level.level(user.getId());
        int EXPUp = EXP.LevelUp(user, -1);
        int EXPUp2 = EXP.LevelUp2(user);
        int EXPMax = EXP.LevelUp(user, 0);
        int EXP2 = DiscordBot.getLeveldata().getLevelProfil().get(user.getId()).getXp();
        int avancement = (EXP2 - EXPUp) * 100 / EXPUp2;
        String mess = avancement == 0 ? "\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement != 0 && avancement <= 10 ? "\u2705\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement > 10 && avancement <= 20
                ? "\u2b1c\u2705\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement > 20 && avancement <= 30
                ? "\u2b1c\u2b1c\u2705\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement > 30 && avancement <= 40
                ? "\u2b1c\u2b1c\u2b1c\u2705\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement > 40 && avancement <= 50
                ? "\u2b1c\u2b1c\u2b1c\u2b1c\u2705\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement > 50 && avancement <= 60
                ? "\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2705\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement > 60 && avancement <= 70
                ? "\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2705\u2b1b\u2b1b\u2b1b"
                : (avancement > 70 && avancement <= 80
                ? "\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2705\u2b1b\u2b1b"
                : (avancement > 80 && avancement <= 90
                ? "\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2705\u2b1b"
                : (avancement > 90
                && avancement <= 100
                ? "\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2705"
                : " "))))))))));
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(color.couleurAleatoire(user));
        String levelS = "";
        if (lang == Language.fr) {
            levelS = "Niveau";
        } else if (lang == Language.en) {
            levelS = "Level";
        }
        builder.addField(":level_slider: " + levelS + " ", String.valueOf(level), true);
        builder.addField("\ud83c\udf21\ufe0f XP ", String.valueOf(EXP2) + " / " + EXPMax, true);
        builder.addField("\ud83d\udcca Progression", String.valueOf(mess) + " " + avancement + "%", false);
        builder.setTitle(String.valueOf(user.getName()) + "'s level");
        builder.setFooter(user.getName(), user.getAvatarUrl());
        message.sendMessage(builder.build()).queue();
    }

    @command(name = "rank", type = ExecutorType.ALL, descfr = "Affiche le level d'un joueur.", descen = "Show the level of a player.", topic = Topics.Social)
    private void rank(MessageChannel message, User user, Language lang, ProfilData data) {
        int level = Level.level(user.getId());
        int EXPUp = EXP.LevelUp(user, -1);
        int EXPUp2 = EXP.LevelUp2(user);
        int EXPMax = EXP.LevelUp(user, 0);
        int EXP2 = DiscordBot.getLeveldata().getLevelProfil().get(user.getId()).getXp();
        int avancement = (EXP2 - EXPUp) * 100 / EXPUp2;
        String mess = avancement == 0 ? "\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement != 0 && avancement <= 10 ? "\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement > 10 && avancement <= 20
                ? "\u2705\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement > 20 && avancement <= 30
                ? "\u2b1c\u2705\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement > 30 && avancement <= 40
                ? "\u2b1c\u2b1c\u2705\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement > 40 && avancement <= 50
                ? "\u2b1c\u2b1c\u2b1c\u2705\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement > 50 && avancement <= 60
                ? "\u2b1c\u2b1c\u2b1c\u2b1c\u2705\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement > 60 && avancement <= 70
                ? "\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2705\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement > 70 && avancement <= 80
                ? "\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2705\u2b1b\u2b1b\u2b1b"
                : (avancement > 80 && avancement <= 90
                ? "\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2705\u2b1b\u2b1b"
                : (avancement > 90
                && avancement <= 100
                ? "\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2705\u2b1b"
                : " "))))))))));
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(color.couleurAleatoire(user));
        String levelS = "";
        if (lang == Language.fr) {
            levelS = "Niveau";
        } else if (lang == Language.en) {
            levelS = "Level";
        }
        builder.addField(":level_slider: " + levelS + " ", String.valueOf(level), true);
        builder.addField("\ud83c\udf21\ufe0f XP ", String.valueOf(EXP2) + " / " + EXPMax, true);
        builder.addField("\ud83d\udcca Progression", String.valueOf(mess) + " " + avancement + "%", false);
        builder.setTitle(String.valueOf(user.getName()) + "'s level");
        builder.setFooter(user.getName(), user.getAvatarUrl());
        message.sendMessage(builder.build()).queue();
    }

    @command(name = "top", type = ExecutorType.ALL, descfr = "Affiche le classement des joueurs en fonction de leur IDH !", descen = "Show the IDH players leaderboard.", topic = Topics.Game)
    private void top(MessageChannel channel, Guild guild, String[] args, JDA jda, Language lang) {
        int c1;
        ProfilData data = DiscordBot.getData();
        HashMap<String, Integer> classement = new HashMap<String, Integer>();
        try {
            c1 = Integer.parseInt(args[0]);
        } catch (IndexOutOfBoundsException e) {
            c1 = 10;
        } catch (NumberFormatException e) {
            c1 = 10;
        }
        for (Profil profil : data.getProfils().values()) {
            String user;
            try {
                user = profil.getName();
            } catch (NullPointerException e) {
                user = lang == Language.fr ? "Une personne invisible"
                        : (lang == Language.en ? "An invisible person" : "An invisible person");
            }
            int point = profil.getIdh();
            classement.put(user, point);
        }
        ArrayList<Entry<String, Integer>> entries = new ArrayList(classement.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> e2, Map.Entry<String, Integer> e1) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        int max = (Integer) ((Map.Entry) entries.get(0)).getValue();
        if (c1 > entries.size()) {
            c1 = entries.size();
        }
        String messages = "";
        int o = 1;
        messages = ":trophy:  Leaderboard :trophy: :\n";
        for (Entry entry : entries) {
            String rank = o == 1 ? ":first_place:"
                    : (o == 2 ? ":second_place:"
                    : (o == 3 ? ":third_place:"
                    : (lang == Language.fr ? String.valueOf(o) + "ème"
                    : (lang == Language.en ? String.valueOf(o) + "e" : "e"))));
            if ((messages = String.valueOf(messages) + "**" + rank + "** : " + (String) entry.getKey() + " : "
                    + entry.getValue() + " points (" + (Integer) entry.getValue() * 100 / (max + max / 10) + "%)\n")
                    .length() > 1900
                    || ++o > c1)
                break;
        }
        channel.sendMessage(messages).queue();
    }

    @command(name = "Glevel", type = ExecutorType.ALL, descfr = "Affiche le level d'une guilde !", descen = " Show the level of a server.", topic = Topics.Social)
    private void Glevel(MessageChannel message, Guild guild, User user, ProfilData data, Language lang) {
        int level = Level.Glevel(guild.getId());
        int EXPUp = EXP.GLevelUp(guild, -1);
        int EXPMax = EXP.GLevelUp(guild, 0);
        int EXPUp2 = EXP.GLevelUp2(guild);
        int GEXP = DiscordBot.getGuilddata().getGuildProfil().get(guild.getId()).getXp();
        int avancement = (GEXP - EXPUp) * 100 / EXPUp2;
        String mess = avancement == 0 ? "\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement != 0 && avancement <= 10 ? "\u2705\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement > 10 && avancement <= 20
                ? "\u2b1c\u2705\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement > 20 && avancement <= 30
                ? "\u2b1c\u2b1c\u2705\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement > 30 && avancement <= 40
                ? "\u2b1c\u2b1c\u2b1c\u2705\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement > 40 && avancement <= 50
                ? "\u2b1c\u2b1c\u2b1c\u2b1c\u2705\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement > 50 && avancement <= 60
                ? "\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2705\u2b1b\u2b1b\u2b1b\u2b1b"
                : (avancement > 60 && avancement <= 70
                ? "\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2705\u2b1b\u2b1b\u2b1b"
                : (avancement > 70 && avancement <= 80
                ? "\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2705\u2b1b\u2b1b"
                : (avancement > 80 && avancement <= 90
                ? "\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2705\u2b1b"
                : (avancement > 90
                && avancement <= 100
                ? "\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2b1c\u2705"
                : " "))))))))));
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(color.couleurAleatoire(user));
        String levelS = "";
        if (lang == Language.fr) {
            levelS = "Niveau";
        } else if (lang == Language.en) {
            levelS = "Level";
        }
        builder.addField(":level_slider: " + levelS + " ", String.valueOf(level), true);
        builder.addField("\ud83c\udf21\ufe0f XP ", String.valueOf(GEXP) + " / " + EXPMax, true);
        builder.addField("\ud83d\udcca Progression", String.valueOf(mess) + " " + avancement + "%", false);
        builder.setTitle(String.valueOf(user.getName()) + "'s level");
        builder.setFooter(user.getName(), user.getAvatarUrl());
        message.sendMessage(builder.build()).queue();
    }

    @command(name = "report", type = ExecutorType.ALL, descfr = "usage : =report [command] [raison] . \n donnes des idées pour les futures maj du bot grace a cette commande !!!", descen = "User: =report [command] [reason]. \n give ideas for the future maj of the bot thanks to this command !!!")
    private void report(MessageChannel message, Guild guild, String[] args, User user, Language lang) {
        StringBuilder builder = new StringBuilder();
        for (String str : args) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(str);
        }
        String txtDate = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ssaaaa : ", Locale.FRANCE).format(new Date());
        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/bot/");
        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/report.txt",
                String.valueOf(txtDate) + " " + user.getName() + " : " + builder.toString(), 1);
        try {
            mail.main("report", builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (lang == Language.fr) {
            message.sendMessage("Votre rapport \u00e0 bien été envoyé \u00e0 un admin.").queue();
        } else if (lang == Language.en) {
            message.sendMessage("Your report has been sent to an admin.").queue();
        }
    }

    /*
     * WARNING - void declaration
     */
    @command(name = "profile", type = ExecutorType.ALL, descfr = "Affiche le profil d'un joueur", descen = "Show the player profile", topic = Topics.Social)
    private void profile(Message message, Guild guild, String[] args, User user, MessageChannel channel, JDA jda,
                         Language lang) {
        String rank;
        int LevelOzr;
        ProfilData data;
        String desc;
        String pays;
        int vote;
        int level;
        User cible;
        block50:
        {
            data = DiscordBot.getData();
            try {
                cible = message.getMentionedUsers().get(0);
            } catch (IndexOutOfBoundsException e) {
                try {
                    cible = jda.getUserById(args[0]);
                } catch (Exception e1) {
                    cible = user;
                }
            }
            level = Level.level(cible.getId());
            vote = 0;
            try {
                vote = data.getProfils().get(cible.getId()).getVote();
            } catch (NullPointerException e4) {
                e4.printStackTrace();
            }
            rank = "Default";
            if (Premium.Premium(data.getProfils().get(cible.getId()))) {
                rank = "Premium";
            }
            if (cible.getId().equals("102108573298851840") || cible.getId().equals("249987060365000704")
                    || cible.getId().equals("502535486691082279")) {
                rank = "Admin";
            }
            int XpOzr = 0;
            try {
                XpOzr = data.getProfils().get(cible.getId()).getXp();
            } catch (NullPointerException e3) {
                e3.printStackTrace();
            }
            try {
                double operation = 3 * XpOzr / 4;
                double math = Math.sqrt(operation);
                LevelOzr = (int) Math.round(math);
            } catch (NullPointerException e) {
                LevelOzr = 0;
            }
            pays = "None";
            try {
                pays = data.getProfils().get(cible.getId()).getCountry();
            } catch (NullPointerException e) {
                if (lang == Language.fr) {
                    pays = "Aucun";
                }
                if (lang != Language.en)
                    break block50;
                pays = "None";
            }
        }
        String rankPays = "member";
        try {
            for (File file1 : TextFileWriter
                    .folderlist("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles")) {
                if (!TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/roles/"
                        + file1.getName() + "/" + cible.getId()).equals("true"))
                    continue;
                rankPays = file1.getName().replaceAll("_", " ");
            }
        } catch (NullPointerException file1) {
            // empty catch block
        }
        if (TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/owner.txt")
                .equals(cible.getId())) {
            rankPays = "Owner";
        }
        int reputation2 = 0;
        try {
            reputation2 = data.getProfils().get(cible.getId()).getRep();
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        try {
            desc = data.getProfils().get(cible.getId()).getDescription();
        } catch (NullPointerException e) {
            desc = null;
        }
        if (desc == null) {
            if (lang == Language.fr) {
                desc = "Joueur OzeryoBot";
            } else if (lang == Language.en) {
                desc = "OzeryoBot Player";
            }
        }
        HashMap<String, Integer> classement = new HashMap<String, Integer>();
        for (Profil profil : data.getProfils().values()) {
            String user15;
            try {
                user15 = jda.getUserById(profil.getId()).getName();
            } catch (NullPointerException e) {
                if (lang == Language.fr) {
                    user15 = "Une personne invisible";
                }
                if (lang == Language.en) {
                    user15 = "An invisible person";
                }
                user15 = "An invisible person";
            }
            int point = profil.getIdh();
            classement.put(user15, point);
        }
        ArrayList<Entry<String, Integer>> entries = new ArrayList(classement.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> e2, Map.Entry<String, Integer> e1) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        int topOzr = 0;
        for (Map.Entry entry : entries) {
            ++topOzr;
            if (((String) entry.getKey()).equals(cible.getName()))
                break;
        }
        HashMap<String, Integer> classement1 = new HashMap<String, Integer>();
        File repertoire = new File("/home/DiscordBot/Rasberry/données/bot/Pays/");
        File[] files = repertoire.listFiles();
        for (File file : files) {
            try {
                int Game_EXP = 0;
                try {
                    Game_EXP = Integer.parseInt(TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + file.getName() + "/points.txt"));
                } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    // empty catch block
                }
                String member = file.getName();
                classement1.put(member, Game_EXP);
            } catch (IndexOutOfBoundsException Game_EXP) {
                // empty catch block
            }
        }
        ArrayList<Entry<String, Integer>> entries1 = new ArrayList(classement1.entrySet());
        Collections.sort(entries1, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> e3, Map.Entry<String, Integer> e4) {
                return e4.getValue().compareTo(e3.getValue());
            }
        });
        int topPays = 0;
        for (Map.Entry entry1 : entries1) {
            ++topPays;
            if (((String) entry1.getKey()).equals(pays))
                break;
        }
        String emoji = "";
        if (LevelOzr >= 200 && LevelOzr < 300) {
            emoji = "\ud83d\udc9b";
        } else if (LevelOzr >= 300 && LevelOzr < 400) {
            emoji = "\ud83d\udc9a";
        } else if (LevelOzr >= 400 && LevelOzr < 500) {
            emoji = "\ud83d\udc99";
        } else if (LevelOzr >= 500 && LevelOzr < 600) {
            emoji = "\ud83d\udc9c";
        } else if (LevelOzr >= 600) {
            emoji = "\ud83d\udda4";
        }
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(user.getName(), null, user.getAvatarUrl());
        builder.setTitle(String.valueOf(cible.getName()) + "'s Profile");
        builder.setColor(color.couleurAleatoire(cible));
        builder.setThumbnail(cible.getAvatarUrl());
        String country = "";
        if (lang == Language.fr) {
            country = "Pays";
        }
        if (lang == Language.en) {
            country = "Country";
        }
        builder.setDescription(":mortar_board: Level : " + level + "\t\t      |      :incoming_envelope: Vote : " + vote
                + "\n\n" + ":beginner: Rank : " + rank + "\n\n" + ":star: Level Ozeryo : " + LevelOzr + (String) emoji
                + "\t | :medal: Top Ozeryo : " + topOzr + "\n\n" + ":earth_americas: " + country + " Ozeryo : " + pays
                + "\t | :medal: Top " + country + " : " + topPays + "\n" + ":tickets: Rank " + country + " : "
                + rankPays + "\n\n" + ":100: Reputation : " + reputation2 + "\n\n" + ":pen_ballpoint: Description : ``"
                + desc + "``");
        channel.sendMessage(builder.build()).queue();
    }

    @command(name = "vote", type = ExecutorType.ALL, descfr = "Permet de voter pour le bot. Pour recuperer votre gain veuillez refaire (delay de 2 minutes possible)", descen = "Allow you to vote for the bot. To claim your price please redo the commands (delay of 2 minutes possible)", topic = Topics.Social)
    private void vote(MessageChannel message, Guild guild, String[] args, User user, JDA jda, Language lang) {
        ProfilData data = DiscordBot.getData();
        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/vote/");
        DiscordBotListAPI api = new DiscordBotListAPI.Builder().token(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjM5OTExNTcyNDkyNjQ4NDQ5MCIsImJvdCI6dHJ1ZSwiaWF0IjoxNTIyMTY1OTQ2fQ.9ZdP4Mk5yxZdvN2eZE7aLYyh6pVaofUI5t3sgQHMizg")
                .botId("399115724926484490").build();
        String userIds = user.getId();
        api.hasVoted(userIds).whenComplete((hasVoted, e) -> {
            String reponse = "";
            if (hasVoted.booleanValue()) {
                TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/vote/");
                int sec = Integer.parseInt(TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/vote/sec.txt"));
                int min = Integer.parseInt(TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/vote/min.txt"));
                int hr = Integer.parseInt(TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/vote/hr.txt"));
                int day = Integer.parseInt(TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/vote/day.txt"));
                String secondes = new SimpleDateFormat("ss", Locale.FRANCE).format(new Date());
                String minutes = new SimpleDateFormat("mm", Locale.FRANCE).format(new Date());
                String heures = new SimpleDateFormat("HH", Locale.FRANCE).format(new Date());
                String jours = new SimpleDateFormat("dd", Locale.FRANCE).format(new Date());
                int Usecondes = Integer.parseInt(secondes) - sec;
                int Uminutes = Integer.parseInt(minutes) - min;
                int Uheures = Integer.parseInt(heures) - hr;
                int Ujours = Integer.parseInt(jours) - day;
                if (Usecondes < 0) {
                    Usecondes += 60;
                    --Uminutes;
                }
                if (Uminutes < 0) {
                    Uminutes += 60;
                    --Uheures;
                }
                if (Uheures < 0) {
                    Uheures += 24;
                    --Ujours;
                }
                if (Uheures >= 12 || Ujours > 1) {
                    int Pet_EXP;
                    ArrayList<String> list;
                    int level;
                    String reponse2 = "";
                    if (Event.Summer()) {
                        int nbalea = 250 + (int) (Math.random() * 251.0);
                        int gain = 0;
                        int xp = data.getProfils().get(user.getId()).getOzPassXp();
                        int bonus1 = data.getProfils().get(user.getId()).getBonus() / 100;
                        gain = nbalea * (1 + bonus1);
                        data.getProfils().get(user.getId()).setOzPassXp(xp += gain);
                        if (lang == Language.fr) {
                            reponse2 = " Vous gangez aussi " + gain + " OzXp";
                        } else if (lang == Language.en) {
                            reponse2 = " You also won " + gain + " OzXp";
                        }
                    }
                    int jetons = data.getProfils().get(user.getId()).getTokens();
                    data.getProfils().get(user.getId()).setTokens(jetons += 5);
                    String ActivePet = TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/pet.txt");
                    HashMap<String, ArrayList<String>> pet = data.getProfils().get(user.getId()).getPet();
                    try {
                        list = data.getProfils().get(user.getId()).getPet().get(ActivePet);
                    } catch (NullPointerException e1) {
                        list = null;
                    }
                    try {
                        Pet_EXP = Integer.parseInt(data.getProfils().get(user.getId()).getPet().get(ActivePet).get(1));
                    } catch (NullPointerException e1) {
                        Pet_EXP = 0;
                    }
                    String Pet_Bonus = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pets/" + ActivePet);
                    double operationpet = Pet_EXP / 10;
                    double operationpet2 = Math.sqrt(operationpet);
                    double Pet_Level = Math.round(operationpet2);
                    double pet_bonus = 1.0 + 0.1 * Pet_Level;
                    int Game_EXP = data.getProfils().get(user.getId()).getXp();
                    int Alea_EXP = 50 + (int) (Math.random() * 51.0);
                    if (Pet_Bonus.equals("resHr")) {
                        Alea_EXP = (int) ((double) Alea_EXP * pet_bonus);
                    }
                    data.getProfils().get(user.getId()).setXp(Game_EXP += Alea_EXP);
                    if (!ActivePet.equals("0")) {
                        Pet_EXP += Alea_EXP / 10;
                    }
                    if (list != null) {
                        list.remove(1);
                    }
                    if (list != null) {
                        list.add(1, String.valueOf(Pet_EXP));
                    }
                    if (list != null) {
                        pet.put(ActivePet, list);
                    }
                    try {
                        data.getProfils().get(user.getId()).setPet(pet);
                    } catch (NullPointerException e1) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setPet(pet);
                    }
                    try {
                        double operation = 3 * Game_EXP / 4;
                        double math = Math.sqrt(operation);
                        level = (int) Math.round(math);
                    } catch (NullPointerException np) {
                        level = 0;
                    }
                    HashMap<String, Integer> building = new HashMap();
                    building = data.getProfils().get(user.getId()).getBuilding();
                    int bonus = (Integer) building.get("marché");
                    long money = data.getProfils().get(user.getId()).getMoney();
                    int Alea_money = 20 + (int) (Math.random() * 31.0);
                    int money_win2 = Alea_money * (100 + 10 * level * bonus);
                    if (Pet_Bonus.equals("resHr")) {
                        money_win2 = (int) ((double) money_win2 * pet_bonus);
                    }
                    data.getProfils().get(user.getId()).setMoney(money += (long) money_win2);
                    long pop = data.getProfils().get(user.getId()).getHabitants();
                    int Alea_pop = 20 + (int) (Math.random() * 31.0);
                    int pop_win2 = Alea_pop * (10 + 15 * level * bonus);
                    if (Pet_Bonus.equals("resHr")) {
                        pop_win2 = (int) ((double) pop_win2 * pet_bonus);
                    }
                    data.getProfils().get(data.getProfils().get(user.getId()).getId()).setHabitants(pop += (long) pop_win2);
                    Quest.Quest("vote", data.getProfils().get(user.getId()), message, 1);
                    Quest.Quest("jetons", data.getProfils().get(user.getId()), message, 5);
                    int jeton = data.getProfils().get(user.getId()).getJetons_récolté();
                    jeton += 5;
                    data.getProfils().get(user.getId()).getJetons_récolté();
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/vote/sec.txt",
                            secondes, 1);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/vote/min.txt",
                            minutes, 1);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/vote/hr.txt",
                            heures, 1);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/vote/day.txt",
                            jours, 1);
                    int vote = data.getProfils().get(user.getId()).getVote() + 1;
                    try {
                        data.getProfils().get(user.getId()).setVote(vote);
                    } catch (NullPointerException e1) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setVote(vote);
                    }
                    if (lang == Language.fr) {
                        reponse = "Vous venez de gagner  " + Alea_EXP + "EXP, " + money_win2 + "$, " + pop_win2
                                + " habitants ainsi que 5 jetons. " + reponse2;
                    } else if (lang == Language.en) {
                        reponse = "You won " + Alea_EXP + "Xp, " + money_win2 + "$, " + pop_win2
                                + " people and 5 tokens. " + reponse2;
                    }
                    CommandMap.PublicLog("\ud83d\udce8 " + user.getName() + " a voté pour OzeryoBot. Merci a lui.",
                            jda);
                } else {
                    Uheures = 11 - Uheures;
                    Uminutes = 60 - Uminutes;
                    Usecondes = 60 - Usecondes;
                    if (lang == Language.fr) {
                        reponse = "Vous devez attendre " + Uheures + " heures " + Uminutes + " minutes " + Usecondes
                                + " secondes avant de pouvoir de nouveau voter";
                    } else if (lang == Language.fr) {
                        reponse = "You must wait " + Uheures + " hours " + Uminutes + " minutes " + Usecondes
                                + " seconds to be able to vote for OzeryoBot again";
                    }
                }
            } else {
                TextFileWriter.folder_delete("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/vote/");
                if (lang == Language.fr) {
                    reponse = "Veuillez voter pour reclamer votre prix";
                } else if (lang == Language.en) {
                    reponse = "You must vote to claim your price";
                }
            }
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("Vote");
            if (lang == Language.fr) {
                builder.setDescription(
                        "Clique sur ce lien afin d'acceder a la page de vote : https://discordbots.org/bot/399115724926484490/vote");
            } else if (lang == Language.en) {
                builder.setDescription(
                        "Click on the link to go to the voting page : https://discordbots.org/bot/399115724926484490/vote");
            }
            builder.setColor(color.couleurAleatoire(user));
            if (lang == Language.fr) {
                builder.addField("**Etat**", reponse, false);
            } else if (lang == Language.en) {
                builder.addField("**Progress**", reponse, false);
            }
            builder.setAuthor(user.getName(), user.getAvatarUrl());
            builder.setFooter(guild.getName(), guild.getIconUrl());
            message.sendMessage(builder.build()).queue();
        });
    }

    @command(name = "game", type = ExecutorType.ALL, descfr = "Met a jour le jeu du bot", descen = "Update the bot game", topic = Topics.Admin)
    private void game(MessageChannel message, Guild guild, String[] args, User user, JDA jda, Language lang) {
        if (user.getId().equals("102108573298851840")) {
            jda.getPresence().setActivity(Activity
                    .playing("try =help | " + jda.getGuilds().size() + " guilds " + jda.getUsers().size() + " users"));
            String DiscordBotKey = TextFileWriter.read("/home/DiscordBot/Rasberry/key/DiscordBotKey.txt");
            DiscordBotListAPI api = new DiscordBotListAPI.Builder().token(DiscordBotKey).botId("399115724926484490")
                    .build();
            int serverCount = jda.getGuilds().size();
            api.setStats(serverCount);
            message.sendMessage("Le jeu a bien été mis a jours").queue();
        } else {
            if (lang == Language.fr) {
                message.sendMessage("désolé mais vous n'avez pas la permission necessaire pour faire cela").queue();
            }
            if (lang == Language.en) {
                message.sendMessage("Sorry but you don't have the necessary permission to perform this ")
                        .queue();
            }
        }
    }

    @command(name = "bruh", type = ExecutorType.ALL, descfr = "BRUHHH", descen = " BRUHHHHHHH")
    private void bruh(MessageChannel message, Guild guild, String[] args, User user, JDA jda) {
        EmbedBuilder bruh = new EmbedBuilder();
        bruh.setImage("https://media.giphy.com/media/NrqabhEpXWsGA/giphy.gif");
        bruh.setColor(color.couleurAleatoire(user));
        bruh.setFooter(user.getName(), user.getAvatarUrl());
        message.sendMessage(bruh.build()).queue();
    }

    @command(name = "kick", type = ExecutorType.ALL, descfr = "Permet de kick un joueur de votre serveur ", descen = " allow you to kick a player from your server", topic = Topics.Modo)
    private void kick(Message message, Guild guild, String[] args, User user, MessageChannel channel, ProfilData data,
                      Language lang) {
        if (guild.getMember(user).hasPermission(Permission.KICK_MEMBERS)) {
            StringBuilder builder = new StringBuilder();
            for (String str : args) {
                if (str.equals(args[0]))
                    continue;
                if (builder.length() > 0) {
                    builder.append(" ");
                }
                builder.append(str);
            }
            String c1 = builder.toString();
            User userk = message.getMentionedUsers().get(0);
            guild.kick(userk.getId(), c1).queue();
            Language langk = data.getProfils().get(userk.getId()).getLanguage();
            userk.openPrivateChannel().complete();
            if (langk == Language.fr) {
                ((UserImpl) userk).getPrivateChannel()
                        .sendMessage("Vous avez été kick du serveur " + guild.getName() + "\n ***Raison*** :" + c1)
                        .queue();
            }
            if (langk == Language.en) {
                ((UserImpl) userk).getPrivateChannel()
                        .sendMessage("You have been kicked from " + guild.getName() + "\n ***Reason*** :" + c1).queue();
            }
            if (lang == Language.fr) {
                channel.sendMessage(String.valueOf(userk.getName()) + " a bien été kick.").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage(String.valueOf(userk.getName()) + " has successfully been kick.").queue();
            }
        } else {
            if (lang == Language.fr) {
                channel.sendMessage("Desolé mias vous n'avez pas la permission de kick un joueur.").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("Sorry but you don't ave the necessary permission to kick a player.").queue();
            }
        }
    }

    @command(name = "ban", type = ExecutorType.ALL, descfr = "Permet de bannir un joueur de votre serveur", descen = " Allow you to ban a player from your server", topic = Topics.Modo)
    private void ban(Message message, Guild guild, String[] args, User user, MessageChannel channel,
                     Language lang) {
        try {
            if (guild.getMember(user).hasPermission(Permission.BAN_MEMBERS)) {
                String c1 = args[1];
                User userk = message.getMentionedUsers().get(0);
                channel.sendMessage("" + userk).queue();
                guild.ban(user.getId(), Integer.parseInt(c1)).queue();
                if (lang == Language.fr) {
                    channel.sendMessage(String.valueOf(userk.getName()) + " a bien été ban .").queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage(String.valueOf(userk.getName()) + " has been successfully banned.").queue();
                }
            } else {
                if (lang == Language.fr) {
                    channel.sendMessage("Desolé mais vous n'avez pas la permission de ban un joueur.").queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage("Sorry but you don't have the necessary permissions to ban a player.").queue();
                }
            }
        } catch (Exception e) {
            if (lang == Language.fr) {
                channel.sendMessage("Syntaxe : ``=ban [jours] [mention]``").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("Syntax : ``=ban [days] [mention]``").queue();
            }
            return;
        }
    }

    @command(name = "unban", type = ExecutorType.ALL, descfr = "Permet de debannir un joueur de votre serveur", descen = "Allow you to unban a player from your server", topic = Topics.Modo)
    private void unban(Message message, Guild guild, String[] args, User user, MessageChannel channel,
                       Language lang) {
        try {
            if (guild.getMember(user).hasPermission(Permission.BAN_MEMBERS)) {
                String c1 = args[0];
                guild.unban(c1).queue();
                if (lang == Language.fr) {
                    channel.sendMessage(String.valueOf(c1) + " a bien été **unban** .").queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage(String.valueOf(c1) + "has been successfully unban.").queue();
                }
            } else {
                if (lang == Language.fr) {
                    channel.sendMessage("Desolé mias vous n'avez pas la permission de ban un joueur.").queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage("Sorry but you don't have the necessary permissions to unban a player.")
                            .queue();
                }
            }
        } catch (Exception e) {
            if (lang == Language.fr) {
                channel.sendMessage("Syntaxe : ``=unban [id]``").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("Syntax : ``=unban [id]``").queue();
            }
            return;
        }
    }

    @command(name = "warn", type = ExecutorType.ALL, descfr = "Permet de warn un joueur", descen = " Allow you to warn a player", topic = Topics.Modo)
    private void warn(Message message, Guild guild, String[] args, User user, MessageChannel channel, ProfilData data,
                      Language lang) {
        try {
            if (guild.getMember(user).hasPermission(Permission.MANAGE_CHANNEL)) {
                User userk = message.getMentionedUsers().get(0);
                StringBuilder builder = new StringBuilder();
                for (String str : args) {
                    if (str.equals(args[0]))
                        continue;
                    if (builder.length() > 0) {
                        builder.append(" ");
                    }
                    builder.append(str);
                }
                String c1 = builder.toString();
                Language langk = data.getProfils().get(userk.getId()).getLanguage();
                userk.openPrivateChannel().complete();
                if (langk == Language.fr) {
                    ((UserImpl) userk).getPrivateChannel().sendMessage(
                            "Vous avez été warn sur le serveur " + guild.getName() + "\n __***Raison***__ : " + c1)
                            .queue();
                }
                if (langk == Language.en) {
                    ((UserImpl) userk).getPrivateChannel()
                            .sendMessage("You have been warn on " + guild.getName() + "\n __***Reason***__ : " + c1)
                            .queue();
                }
                if (lang == Language.fr) {
                    channel.sendMessage(String.valueOf(userk.getAsMention()) + " a bien été warn !!").queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage(String.valueOf(userk.getAsMention()) + " has been successfully warn.").queue();
                }
            } else {
                if (lang == Language.fr) {
                    channel.sendMessage("Vous n'avez pas les perm requises pour effectué cette action.").queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage("You don't have the necessarry permissions to perform this ").queue();
                }
            }
        } catch (Exception e) {
            if (lang == Language.fr) {
                channel.sendMessage("Syntaxe : ``=warn [mention]``").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("Syntax : ``=warn [mention]``").queue();
            }
            return;
        }
    }

    @command(name = "nick", type = ExecutorType.ALL, descfr = "Permet de nick le bot", descen = "Allow you to nick the bot")
    private void nick(Message message, Guild guild, String[] args, User user, MessageChannel channel,
                      Language lang) {
        if (guild.getMember(user).hasPermission(Permission.NICKNAME_CHANGE)) {
            StringBuilder builder = new StringBuilder();
            for (String str : args) {
                if (builder.length() > 0) {
                    builder.append(" ");
                }
                builder.append(str);
            }
            String c1 = builder.toString();
            if (c1.equals("")) {
                c1 = "OzeryoBot";
            }
            if (lang == Language.fr) {
                channel.sendMessage("Mon pseudo est désormais " + c1).queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("My username is now " + c1).queue();
            }
        } else {
            if (lang == Language.fr) {
                channel.sendMessage("Vous n'avez pas les perms resquises pour effectuer cette action.").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("You don't have the necessay permission to perform this action.").queue();
            }
        }
    }

    @command(name = "mute", type = ExecutorType.ALL, descfr = "Permet de rendre muet un joueur", descen = " Allow you to mute a player", topic = Topics.Admin)
    private void mute(Message message, Guild guild, String[] args, User user, MessageChannel channel, TextChannel chanel,
                      Language lang) {
        block21:
        {
            try {
                if (guild.getMember(user).hasPermission(Permission.ADMINISTRATOR)) {
                    int mute_time;
                    try {
                        mute_time = Integer.parseInt(args[args.length - 1]);
                    } catch (NullPointerException e) {
                        mute_time = 5;
                    }

                    User userk = message.getMentionedUsers().get(0);
                    if (guild.getRolesByName("Muted", true).isEmpty()) {

                        guild.createRole().setName("Muted").setColor(color.couleurAleatoire(user))
                                .setHoisted(false).queue();

                        List<Role> role = guild.getRolesByName("Muted", true);
                        Role role2 = role.get(0);

                        for (int i = 0; i < guild.getTextChannels().size(); ++i) {
                            TextChannel chanel2 = guild.getTextChannels().get(i);
            ;
                            EnumSet<Permission> deny = EnumSet.of(Permission.MESSAGE_WRITE);

                            try {
                                chanel2.createPermissionOverride(role2).setPermissions(null, deny).queue();
                                continue;
                            } catch (IllegalStateException illegalStateException) {
                                // empty catch block
                            }
                        }

                        guild.addRoleToMember(guild.getMember(userk), role2).queue();
                        if (lang == Language.fr) {
                            channel.sendMessage(String.valueOf(userk.getAsMention()) + " a bien été muet pour "
                                    + mute_time + " minutes").queue();
                        }
                        if (lang == Language.en) {
                            channel.sendMessage(String.valueOf(userk.getAsMention()) + " has been muted for "
                                    + mute_time + " minutes").queue();
                        }
                    } else {

                        List<Role> role = guild.getRolesByName("Muted", false);
                        Role role2 = role.get(0);

                        for (int i = 0; i < guild.getTextChannels().size(); ++i) {

                            TextChannel chanel2 = guild.getTextChannels().get(i);

                            EnumSet<Permission> deny = EnumSet.of(Permission.MESSAGE_WRITE);

                            try {
                                chanel2.createPermissionOverride(role2).setPermissions(null, deny).queue();
                                continue;
                            } catch (IllegalStateException illegalStateException) {
                                // empty catch block
                            }
                        }

                        guild.addRoleToMember(guild.getMember(userk), role2).queue();
                        if (lang == Language.fr) {
                            channel.sendMessage(String.valueOf(userk.getAsMention()) + " a bien été muet pour "
                                    + mute_time + " minutes").queue();
                        }
                        if (lang == Language.en) {
                            channel.sendMessage(String.valueOf(userk.getAsMention()) + " has been muted for "
                                    + mute_time + " minutes").queue();
                        }
                    }
                    Scheduler.Mute(userk, mute_time, channel, guild);
                    break block21;
                }
                if (lang == Language.fr) {
                    channel.sendMessage("Vous n'avez pas les perms requises pour effectuer cette action.").queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage("You don't have the necessay permission to perform this action.").queue();
                }
            } catch (Exception e) {
                if (lang == Language.fr) {
                    channel.sendMessage("Syntaxe : ``=mute [mention] [durée]``.").queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage("Syntax : ``=mute [mention] [duration]``.").queue();
                }
                return;
            }
        }
    }

    /*
     * Unable to fully structure code Enabled aggressive block sorting Enabled
     * unnecessary exception pruning Enabled aggressive exception aggregation Lifted
     * jumps to return sites
     */
    @command(name = "sethome", type = ExecutorType.ALL, descfr = "Permet de set son home", descen = "Allow you to set your home", topic = Topics.Game)
    private void sethome(Guild guild, String[] args, User user, MessageChannel channel, JDA jda, ProfilData data,
                         Language lang) {
        int x = 1;
        int y = 1;
        String message = "";
        int places = 0;
        try {
            x = Integer.parseInt(args[0]);
            y = Integer.parseInt(args[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            message = "";
            places = 0;
            x = -10;
            return;
        } catch (NumberFormatException e) {
            message = "";
            places = 0;
            x = -10;
            return;
        }
        if (x > 10 || x < -10 || y > 10 || y < -10) {
            if (lang == Language.fr) {
                channel.sendMessage("x et y doivent etre compris entre -10 et 10").queue();
            }
            if (lang != Language.en)
                return;
            channel.sendMessage("x and y must be between -10 and 10").queue();
            return;
        }
        long lastsetHome = Long.parseLong(
                TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/lasthome.txt"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(lastsetHome);
        int dif = (int) ((System.currentTimeMillis() - lastsetHome) / 86400000L);
        if (dif >= 1) {
            String home;
            try {
                home = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt");
            } catch (IllegalArgumentException e) {
                home = "0";
            }
            if (!home.equals("0")) {
                if (lang == Language.fr) {
                    channel.sendMessage(
                            "Désolé mais cet emplacement est deja occupé veuillez utilisez de nouvelles coordonnées")
                            .queue();
                }
                if (lang != Language.en)
                    return;
                channel.sendMessage("Sorry but this location is already taken, please use another coordinates.")
                        .queue();
                return;
            }
            String lastHome = data.getProfils().get(user.getId()).getHome();
            String[] arg = lastHome.split("_");
            int last_x = Integer.parseInt(arg[0]);
            int last_y = Integer.parseInt(arg[1]);
            if (x > last_x + dif || x < last_x - dif && y > last_y + dif || y < last_y - dif) {
                if (lang == Language.fr) {
                    channel.sendMessage("Vous ne pouvez pas vous deplacer aussi loin pour le moment.").queue();
                }
                if (lang != Language.en)
                    return;
                channel.sendMessage(
                        "You can't go so far for the moment, you can only move from 1 block per days (combinable).")
                        .queue();
                return;
            }
            try {
                TextFileWriter.recursifDelete(new File("/home/DiscordBot/Rasberry/données/bot/Map/" + lastHome));
            } catch (IOException e) {
                e.printStackTrace();
            }

            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y);

            TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt", user.getId(),
                    1);

            data.getProfils().get(user.getId()).setHome(String.valueOf(x) + "_" + y);
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/lasthome.txt",
                    Long.toString(System.currentTimeMillis()), 1);
            if (lang == Language.fr) {
                channel.sendMessage("Votre nouveau home est désormais en " + x + "," + y + ".").queue();
            }
            if (lang != Language.en)
                return;
            channel.sendMessage("Your new home is now on " + x + "," + y + ".").queue();
            return;
        }
        if (lang == Language.fr) {
            channel.sendMessage("Vous ne pouvez changer de home qu'une fois par jour.").queue();
        }
        if (lang != Language.en) {
            channel.sendMessage("You can only move from 1 block per day.").queue();
            return;
        }
        for (x = -10; x <= 10; ++x) {
            for (y = -10; y <= 10; ++y) {
                String name;
                try {
                    name = TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt");
                } catch (IllegalArgumentException e1) {
                    name = "0";
                }

                if (name.equals("0")) {
                    message = places == 4 ? String.valueOf(message) + " ``(" + x + "," + y + ")``"
                            : String.valueOf(message) + " ``(" + x + "," + y + ")`` | ";
                    ++places;
                }
                if (places >= 5)
                    break;
            }
            if (places >= 5)
                break;
            ++x;
        }

        if (lang == Language.fr) {
            channel.sendMessage(
                    "Vous devez indiquez les coordonnées x et y de votre nouveau home. Vous pouvez vous placer par exemple sur ces coordonnées : "
                            + message)
                    .queue();
        }
        if (lang != Language.en) {
            channel.sendMessage(
                    "You must indicate the coordinate x and y of your new home. You can, for exemple, go on this coordinates : "
                            + message)
                    .queue();
            return;
        }

    }

    @command(name = "map", type = ExecutorType.ALL, descfr = "permet d'afficher la carte du jeu ", descen = " Allow you to see the game map", topic = Topics.Game)
    private void map(Message message, Guild guild, String[] args, User user, MessageChannel channel, JDA jda, ProfilData data, Language lang) {
        int x = 1;
        int y = 1;
        String[] coord;
        try {
            x = Integer.parseInt(args[0]);
            y = Integer.parseInt(args[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            try {
                coord = data.getProfils().get(user.getId()).getHome().split("_");
                x = Integer.parseInt(coord[0]);
                y = Integer.parseInt(coord[1]);
            } catch (Exception e2) {
                if (lang == Language.fr) {
                    channel.sendMessage("Vous devez indiquez les coordonnées x et y de la map a afficher.").queue();
                }
                if (lang != Language.en)
                    return;
                channel.sendMessage("You must indicate the coordinate x and y of the map to show.").queue();
                return;
            }
        } catch (NumberFormatException e) {
            try {
                User cible = null;
                try {
                    cible = message.getMentionedUsers().get(0);
                } catch (IndexOutOfBoundsException e1) {
                    cible = jda.getUserById(args[0]);
                }
                coord = data.getProfils().get(cible.getId()).getHome().split("_");
                x = Integer.parseInt(coord[0]);
                y = Integer.parseInt(coord[1]);
            } catch (Exception e2) {
                if (lang == Language.fr) {
                    channel.sendMessage("Vous devez indiquez les coordonnées x et y de la map a afficher.").queue();
                }
                if (lang != Language.en)
                    return;
                channel.sendMessage("You must indicate the coordinate x and y of the map to show.").queue();
                return;
            }
        }
        int max = 10;
        if (x > max || x < -max || y > max || y < -max) {
            if (lang == Language.fr) {
                channel.sendMessage("x et y doivent etre compris entre -10 et 10.").queue();
            }
            if (lang != Language.en)
                return;
            channel.sendMessage("The coordinates x and y must be between -10 and 10.").queue();
            return;
        }

        BufferedImage image = null;
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
        int xmax = x + 2;
        int ymax = y + 2;
        int minx = x - 2;
        int miny = y - 2;

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
                            Case = data.getProfils().get(TextFileWriter
                                    .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt")).getName();
                        }catch(Exception e1) {
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
                            Owner = data.getProfils().get(TextFileWriter.read(
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
            ImageIO.write((RenderedImage) image, "png", new File("/home/DiscordBot/Rasberry/données/bot/map.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        channel.sendFile(new File("/home/DiscordBot/Rasberry/données/bot/map.png")).queue();

    }


    @command(name = "Gtop", type = ExecutorType.ALL, descfr = "permet d'afficher le classement des serveur ", descen = "Show the Server Leaderboard", topic = Topics.Social)
    private void Gtop(Message message, Guild guild, String[] args, User user, MessageChannel channel, ProfilData data,
                      Language lang) {
        String c1;
        HashMap<String, Integer> classement = new HashMap<String, Integer>();
        try {
            c1 = args[0];
        } catch (IndexOutOfBoundsException e) {
            c1 = "10";
        }
        for (int i = 0; i < guild.getMembers().size(); ++i) {
            try {
                int EXP2 = DiscordBot.getLeveldata().getLevelProfil().get(guild.getMembers().get(i).getUser().getId())
                        .getXp();
                String member = guild.getMembers().get(i).getUser().getName();
                member = String.valueOf(member) + "| **Level** : "
                        + Level.level(guild.getMembers().get(i).getUser().getId()) + " | **Xp** : ";
                classement.put(member, EXP2);
                continue;
            } catch (NumberFormatException EXP2) {
                // empty catch block
            }
        }
        ArrayList entries = new ArrayList(classement.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        if (c1 != "0" && Integer.parseInt(c1) < entries.size() - 1) {
            String messages = ":trophy:  LeaderBoard :trophy: : /n";
            for (int i = 1; i <= Integer.parseInt(c1); ++i) {
                messages = String.valueOf(messages) + "__***" + i + "e***__ " + entries.get(entries.size() - i) + "\n";
            }
            channel.sendMessage(messages).queue();
        } else {
            channel.sendMessage(":trophy:  Leaderboard :trophy: : \n __***1er***__ : " + entries.get(entries.size() - 1)
                    + "\n **2e** : " + entries.get(entries.size() - 2) + "\n *3e* : " + entries.get(entries.size() - 3)
                    + "\n 4e : " + entries.get(entries.size() - 4) + "\n 5e : " + entries.get(entries.size() - 5)
                    + "\n 6e : " + entries.get(entries.size() - 6) + "\n 7e : " + entries.get(entries.size() - 7)
                    + "\n 8e : " + entries.get(entries.size() - 8) + "\n 9e : " + entries.get(entries.size() - 9)
                    + "\n 10e : " + entries.get(entries.size() - 10)).queue();
        }
    }

    @command(name = "toplevel", type = ExecutorType.ALL, descfr = "Affiche le classement en fonction du niveau social ", descen = "Show the Social Level Leaderboard", topic = Topics.Social)
    private void toplevel(Message message, Guild guild, String[] args, User user, MessageChannel channel,
                          ProfilData data, JDA jda, Language lang) {
        String c1;
        HashMap<String, Integer> classement = new HashMap<String, Integer>();
        try {
            c1 = args[0];
        } catch (IndexOutOfBoundsException e) {
            c1 = "10";
        }
        for (LevelProfil levelProfil : DiscordBot.getLeveldata().getLevelProfil().values()) {
            try {
                int EXP2;
                String member;
                block11:
                {
                    EXP2 = DiscordBot.getLeveldata().getLevelProfil().get(levelProfil.getId()).getXp();
                    member = "unknwon";
                    try {
                        member = jda.getUserById(levelProfil.getId()).getName();
                    } catch (NullPointerException e) {
                        if (lang == Language.fr) {
                            member = "inconnu";
                        }
                        if (lang != Language.en)
                            break block11;
                        member = "unknown";
                    }
                }
                member = String.valueOf(member) + "| **Level** : " + Level.level(levelProfil.getId()) + " | **Xp** : ";
                classement.put(member, EXP2);
            } catch (NumberFormatException EXP2) {
                // empty catch block
            }
        }
        ArrayList entries = new ArrayList(classement.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        if (c1 != "0" && Integer.parseInt(c1) < entries.size() - 1) {
            String messages = ":trophy:  LeaderBoard :trophy: :/n";
            for (int i = 1; i <= Integer.parseInt(c1); ++i) {
                messages = String.valueOf(messages) + "__***" + i + "e***__ " + entries.get(entries.size() - i) + "\n";
            }
            channel.sendMessage(messages).queue();
        } else {
            channel.sendMessage(":trophy:  LeaderBoard :trophy: : \n __***1er***__ : " + entries.get(entries.size() - 1)
                    + "\n **2e** : " + entries.get(entries.size() - 2) + "\n *3e* : " + entries.get(entries.size() - 3)
                    + "\n 4e : " + entries.get(entries.size() - 4) + "\n 5e : " + entries.get(entries.size() - 5)
                    + "\n 6e : " + entries.get(entries.size() - 6) + "\n 7e : " + entries.get(entries.size() - 7)
                    + "\n 8e : " + entries.get(entries.size() - 8) + "\n 9e : " + entries.get(entries.size() - 9)
                    + "\n 10e : " + entries.get(entries.size() - 10)).queue();
        }
    }

    @command(name = "toprep", type = ExecutorType.ALL, descfr = "Affiche le classement des joueurs en fonction de la reputation ", descen = " Show the Reputation Players Leaderboard", topic = Topics.Social)
    private void toprep(Message message, Guild guild, String[] args, User user, MessageChannel channel, JDA jda,
                        Language lang) {
        int c1;
        ProfilData data = DiscordBot.getData();
        HashMap<String, Integer> classement = new HashMap<String, Integer>();
        try {
            c1 = Integer.parseInt(args[0]);
        } catch (IndexOutOfBoundsException e) {
            c1 = 10;
        }
        for (Profil profil : data.getProfils().values()) {
            int rep = profil.getRep();
            String member = "Une personne discr\u00e8te | **reputation** ";
            if (lang == Language.en) {
                member = "an invisible person | **réputation** ";
            }
            if (lang == Language.fr) {
                member = "Une personne discrete | **reputation** ";
            }
            try {
                if (lang == Language.fr) {
                    member = String.valueOf(profil.getName()) + " | **réputation** ";
                }
                if (lang == Language.en) {
                    member = String.valueOf(profil.getName()) + " | **reputation** ";
                }
            } catch (NullPointerException nullPointerException) {
                // empty catch block
            }
            classement.put(member, rep);
        }
        ArrayList<Entry<String, Integer>> entries = new ArrayList(classement.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> e2, Map.Entry<String, Integer> e1) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        if (c1 > entries.size()) {
            c1 = entries.size();
        }
        String messages = "";
        int o = 1;
        messages = ":trophy:  Leaderboard :trophy: :\n";
        for (Entry entry : entries) {
            String rank;
            if (o == 1) {
                rank = ":first_place:";
            } else if (o == 2) {
                rank = ":second_place:";
            } else if (o == 3) {
                rank = ":third_place:";
            } else {
                if (lang == Language.fr) {
                    rank = String.valueOf(o) + "\u00e8me";
                }
                rank = lang == Language.en ? String.valueOf(o) + "e" : String.valueOf(o) + "e";
            }
            messages = String.valueOf(messages) + "**" + rank + "** : " + (String) entry.getKey() + " : "
                    + entry.getValue() + "\n";
            if (messages.length() > 1900 || ++o > c1)
                break;
        }
        channel.sendMessage(messages).queue();
    }

    @command(name = "badword", type = ExecutorType.ALL, descfr = "Affiche les statistiques des gros mots supprimés par le bot", descen = "Show statistics about badwords tha bot have deleted")
    private void badword(Message message, Guild guild, String[] args, User user, MessageChannel channel, JDA jda,
                         Language lang) {
        int tg = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/BadWords/Tg.txt"));
        int ntm = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/BadWords/Ntm.txt"));
        int fdp = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/BadWords/Fdp.txt"));
        int ez = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/BadWords/Ez.txt"));
        int salope = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/BadWords/Salope.txt"));
        int pute = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/BadWords/Pute.txt"));
        int suce = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/BadWords/Suce.txt"));
        int blc = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/BadWords/Blc.txt"));
        int ptn = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/BadWords/Ptn.txt"));
        int pd = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/BadWords/Pd.txt"));
        if (lang == Language.fr) {
            channel.sendMessage("\ud83d\udcdbSuppression :\ud83d\udcdb \ntg = **" + tg + "**\n" + "ntm = **" + ntm
                    + "**\n" + "fdp = **" + fdp + "**\n" + "ez = **" + ez + "**\n" + "salope = **" + salope + "**\n"
                    + "pute = **" + pute + "**\n" + "suce = **" + suce + "**\n" + "blc = **" + blc + "**\n" + "ptn = **"
                    + ptn + "**\n" + "pd = **" + pd + "**\n" + "\n Vous deriez faire attention a votre langage !!!")
                    .queue();
        }
        if (lang == Language.en) {
            channel.sendMessage("\ud83d\udcdbRemoval :\ud83d\udcdb \ntg = **" + tg + "**\n" + "ntm = **" + ntm + "**\n"
                    + "fdp = **" + fdp + "**\n" + "ez = **" + ez + "**\n" + "salope = **" + salope + "**\n"
                    + "pute = **" + pute + "**\n" + "suce = **" + suce + "**\n" + "blc = **" + blc + "**\n" + "ptn = **"
                    + ptn + "**\n" + "pd = **" + pd + "**\n" + "\n You should take attention to your language !!!")
                    .queue();
        }
    }

    @command(name = "topG", type = ExecutorType.ALL, descfr = "Affiche le classement des joueurs du serveur ", descen = " Show the Guild Players Leaderboard", topic = Topics.Social)
    private void topG(Message message, Guild guild, String[] args, User user, MessageChannel channel, JDA jda,
                      ProfilData data, Language lang) {
        String c1;
        HashMap<String, Integer> classement = new HashMap<String, Integer>();
        try {
            c1 = args[0];
        } catch (IndexOutOfBoundsException e) {
            c1 = "10";
        }
        for (GuildProfil guildProfil : DiscordBot.getGuilddata().getGuildProfil().values()) {
            try {
                int EXP2 = DiscordBot.getGuilddata().getGuildProfil().get(guildProfil.getId()).getXp();
                int level = Level.Glevel(guildProfil.getId());
                String Guildname = "";
                if (lang == Language.fr) {
                    Guildname = "inconnu";
                }
                if (lang == Language.en) {
                    Guildname = "inconnu";
                }
                try {
                    Guildname = jda.getGuildById(guildProfil.getId()).getName();
                } catch (NullPointerException nullPointerException) {
                    // empty catch block
                }
                String member = String.valueOf(Guildname) + " | **Level** : " + level;
                classement.put(member, EXP2);
            } catch (NumberFormatException EXP2) {
                // empty catch block
            }
        }
        ArrayList entries = new ArrayList(classement.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        if (c1 != "0" && Integer.parseInt(c1) < entries.size() - 1) {
            String messages = ":trophy: LeaderBoard :trophy: :\n";
            for (int i = 1; i <= Integer.parseInt(c1); ++i) {
                messages = String.valueOf(messages) + "__***" + i + "er***__ : " + entries.get(entries.size() - i)
                        + "\n";
            }
            channel.sendMessage(messages).queue();
        } else {
            channel.sendMessage(":trophy:  LeaderBoard :trophy: : \n __***1er***__ : " + entries.get(entries.size() - 1)
                    + "\n **2e** : " + entries.get(entries.size() - 2) + "\n *3e* : " + entries.get(entries.size() - 3)
                    + "\n 4e : " + entries.get(entries.size() - 4) + "\n 5e : " + entries.get(entries.size() - 5)
                    + "\n 6e : " + entries.get(entries.size() - 6) + "\n 7e : " + entries.get(entries.size() - 7)
                    + "\n 8e : " + entries.get(entries.size() - 8) + "\n 9e : " + entries.get(entries.size() - 9)
                    + "\n 10e : " + entries.get(entries.size() - 10)).queue();
        }
    }

    @command(name = "silence", type = ExecutorType.ALL, descfr = "Permet d'activer/desactiver les messages de level up sur votre serveur.", descen = "Allow you to enable/disable level up message on your server", topic = Topics.Modo)
    private void silence(Message message, Guild guild, String[] args, User user, MessageChannel channel,
                         ProfilData data, Language lang) {
        GuildProfilData guilddata = DiscordBot.getGuilddata();
        Boolean silence = guilddata.getGuildProfil().get(guild.getId()).isSilence();
        if (!silence.booleanValue()) {
            if (lang == Language.fr) {
                channel.sendMessage("Le bot a été mit en sourdine. (il ne previendra plus des level up)").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("The bot has been muted. (you will be not inform anymore when you level up)")
                        .queue();
            }
            guilddata.getGuildProfil().get(guild.getId()).setSilence(true);
        } else if (silence.booleanValue()) {
            guilddata.getGuildProfil().get(guild.getId()).setSilence(false);
            if (lang == Language.fr) {
                channel.sendMessage("Le bot peut de nouveau parler (il previendra des level up").queue();
            }
            if (lang == Language.fr) {
                channel.sendMessage("The bot can speak again (you will be inform when you level up)").queue();
            }
        } else {
            if (lang == Language.fr) {
                channel.sendMessage("Le bot a été mit en sourdine. (il ne previendra plus des level up)").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("The bot has been muted. (you will be not inform anymore when you level up)")
                        .queue();
            }
            guilddata.getGuildProfil().get(guild.getId()).setSilence(true);
        }
    }

    @command(name = "AFK", type = ExecutorType.ALL, descfr = "Permet de se definir comme AFK ", descen = "Allow you to set yourself AFK", topic = Topics.Util)
    private void AFK(Message message, Guild guild, String[] args, User user, MessageChannel channel, JDA jda,
                     Language lang) {
        TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/AFK.txt", "true", 1);
        if (lang == Language.fr) {
            channel.sendMessage("Vous etes desormais AFK. ").queue();
        }
        if (lang == Language.en) {
            channel.sendMessage("You are now AFK. ").queue();
        }
    }

    @command(name = "premium", type = ExecutorType.ALL, descfr = "Affiche la durée restante de votre Premium", descen = "Show you the remaining duration of your Premium", topic = Topics.Util)
    private void premium(MessageChannel channel, User user, String[] args, Message message, Language lang,
                         ProfilData data) {
        if (user.getId().equals("102108573298851840")) {
            if (message.getMentionedUsers().size() <= 0) {
                return;
            }
            User User_Premium = message.getMentionedUsers().get(0);
            if (Premium.Premium(data.getProfils().get(User_Premium.getId()))) {
                long premium = data.getProfils().get(User_Premium.getId()).getPremium();
                data.getProfils().get(User_Premium.getId()).setPremium(premium += 2592000000L);
            } else {
                long premium = System.currentTimeMillis() + 2592000000L;
                data.getProfils().get(User_Premium.getId()).setPremium(premium);
            }
        } else {
            long premium = data.getProfils().get(user.getId()).getPremium();
            long delay = premium - System.currentTimeMillis();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(delay);
            int mDay = calendar.get(5);
            if (delay > 0L) {
                if (lang == Language.fr) {
                    channel.sendMessage("Votre premium est actif pour encore " + mDay + " jours.").queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage("Your premium is still active for " + mDay + " days.").queue();
                }
            } else {
                if (lang == Language.fr) {
                    channel.sendMessage("Vous n'avez pas le Premium.").queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage("You don't have the Premium").queue();
                }
            }
        }
    }

    @command(name = "color", type = ExecutorType.ALL, descfr = "Permet de definir une couleur pour tout vos commandes (Premium Uniquement)", descen = "Allow you to set a color for your commands (Only for Premium users)")
    private void color(MessageChannel channel, User user, String[] args, Message message, Language lang, ProfilData data) {
        if (Premium.Premium(data.getProfils().get(user.getId()))) {
            String g;
            String b;
            String r;
            try {
                r = args[0];
            } catch (NullPointerException e) {
                r = "0";
            }
            try {
                g = args[1];
            } catch (NullPointerException e) {
                g = "0";
            }
            try {
                b = args[2];
            } catch (NullPointerException e) {
                b = "0";
            }
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Premium/Color/");
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Premium/Color/red.txt",
                    r, 1);
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Premium/Color/green.txt",
                    g, 1);
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Premium/Color/blue.txt",
                    b, 1);
            if (lang == Language.fr) {
                channel.sendMessage("Votre couleur est desormais RGB(" + r + "," + g + "," + b + ")").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("Your color message is now RGB(" + r + "," + g + "," + b + ")").queue();
            }
        } else {
            if (lang == Language.fr) {
                channel.sendMessage("Vous devez etre Premium pour effectuer cette action.").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("You must be Premium to perform this ").queue();
            }
        }
    }

    @command(name = "rmd", type = ExecutorType.ALL, descfr = "Permet de vous alerter au bout d'un certain temps", descen = "Alert you after a while")
    private void rmd(MessageChannel channel, User user, String[] args, Message message, Language lang) {
        try {
            int rmd;
            String arg;
            ProfilData data = DiscordBot.getData();
            try {
                arg = args[0];
            } catch (NullPointerException e) {
                arg = "5m";
            }
            StringBuilder builder = new StringBuilder();
            for (String str : args) {
                if (str.equals(args[0]))
                    continue;
                builder.append(String.valueOf(str) + " ");
            }
            String messages = "minutes";
            if (arg.contains("s")) {
                arg = arg.replace("s", "");
                if (lang == Language.fr) {
                    messages = "secondes";
                }
                if (lang == Language.en) {
                    messages = "seconds";
                }
                try {
                    rmd = Integer.parseInt(arg);
                } catch (NumberFormatException e) {
                    rmd = 5;
                }
                rmd *= 1000;
            } else if (arg.contains("m")) {
                arg = arg.replace("m", "");
                if (lang == Language.fr) {
                    messages = "minutes";
                }
                if (lang == Language.en) {
                    messages = "minutes";
                }
                try {
                    rmd = Integer.parseInt(arg);
                } catch (NumberFormatException e) {
                    rmd = 5;
                }
                rmd *= 60000;
            } else if (arg.contains("h")) {
                arg = arg.replace("h", "");
                if (lang == Language.fr) {
                    messages = "heures";
                }
                if (lang == Language.en) {
                    messages = "hours";
                }
                try {
                    rmd = Integer.parseInt(arg);
                } catch (NumberFormatException e) {
                    rmd = 5;
                }
                rmd *= 3600000;
            } else if (arg.contains("d")) {
                arg = arg.replace("d", "");
                if (lang == Language.fr) {
                    messages = "jours";
                }
                if (lang == Language.en) {
                    messages = "days";
                }
                try {
                    rmd = Integer.parseInt(arg);
                } catch (NumberFormatException e) {
                    rmd = 5;
                }
                rmd *= 86400000;
            } else {
                if (lang == Language.fr) {
                    messages = "minutes";
                }
                if (lang == Language.en) {
                    messages = "minutes";
                }
                try {
                    rmd = Integer.parseInt(arg);
                } catch (NumberFormatException e) {
                    rmd = 5;
                }
                rmd *= 60000;
            }
            long DateFin = System.currentTimeMillis() + (long) rmd;
            ArrayList<String> list = new ArrayList<String>();
            list.add(Long.toString(DateFin));
            list.add(builder.toString());
            HashMap<Long, ArrayList<String>> map = new HashMap<Long, ArrayList<String>>();
            try {
                map = data.getProfils().get(user.getId()).getRMD();
                map.put(DateFin, list);
            } catch (NullPointerException e) {
                map = new HashMap();
                map.put(DateFin, list);
            }
            try {
                data.getProfils().get(user.getId()).setRMD(map);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setRMD(map);
            }
            if (lang == Language.fr) {
                channel.sendMessage("Je vous previendrai dans " + Integer.parseInt(arg) + " " + messages
                        + "\n Raison : " + builder.toString()).queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("I will remind you in " + Integer.parseInt(arg) + " " + messages + "\n Reason : "
                        + builder.toString()).queue();
            }
        } catch (Exception e) {
            if (lang == Language.fr) {
                channel.sendMessage("Syntaxe : ``=rmd [30s/10m/1h/2d/...] [raison]``").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("Syntax : ``=rmd [30s/10m/1h/2d/...] [raison]``").queue();
            }
            return;
        }
    }

    @command(name = "CoffreFort", abbrev = "cf", type = ExecutorType.ALL, descfr = "permet de récupérer un coffre fort. Ceux-ci apparaissent au bout d'un certain temps mais attention les autres joueurs les réclameront aussi !", descen = " Allow you to recolt a treasure. Treasure appear after a while but be carefull because other users will also try to recolt it.", topic = Topics.Game)
    private void cf(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
                    ProfilData data, Language lang) {
        ArrayList<String> list;
        int Pet_EXP;
        String ActivePet = TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/pet.txt");
        HashMap<String, ArrayList<String>> pet = data.getProfils().get(user.getId()).getPet();
        try {
            list = data.getProfils().get(user.getId()).getPet().get(ActivePet);
        } catch (NullPointerException e) {
            list = null;
        }
        try {
            Pet_EXP = Integer.parseInt(data.getProfils().get(user.getId()).getPet().get(ActivePet).get(1));
        } catch (NullPointerException e) {
            Pet_EXP = 0;
        }
        String Pet_Bonus = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pets/" + ActivePet);
        double operationpet = Pet_EXP / 10;
        double operationpet2 = Math.sqrt(operationpet);
        double Pet_Level = Math.round(operationpet2);
        double pet_bonus = 1.0 + 0.03 * Pet_Level;
        String rarity = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Treasure/rarity.txt");
        String lastUser = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Treasure/user.txt");
        String lastServer = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Treasure/server.txt");
        long lastcf = data.getNextcf();
        if (lastcf < System.currentTimeMillis()) {
            int materiau;
            int i;
            int materiau2;
            int i2;
            int tr = 300 + (int) (Math.random() * 1501.0);
            long nextCf = System.currentTimeMillis() + (long) (tr * 1000);
            data.setNextcf(nextCf);
            double alea = Math.random();
            if (alea <= 0.75) {
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Treasure/rarity.txt", "common", 1);
            }
            if (alea > 0.75 && alea <= 0.9) {
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Treasure/rarity.txt", "rare", 1);
            }
            if (alea > 0.9 && alea <= 0.99) {
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Treasure/rarity.txt", "epic", 1);
            }
            if (alea > 0.99 && alea <= 1.0) {
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Treasure/rarity.txt", "legendary", 1);
            }
            CommandMap.PublicLog(":package: le coffre fort a été recupéré par " + user.getName() + ".", jda);
            int Combos = data.getProfils().get(user.getId()).getCf();
            String Halloween1 = "";
            if (Event.Summer()) {
                int nbalea = 30 + (int) (Math.random() * 31.0);
                int gain = 0;
                int xp = data.getProfils().get(user.getId()).getOzPassXp();
                double bonus1 = (double) data.getProfils().get(user.getId()).getBonus() / 100.0;
                gain = (int) ((double) nbalea * (1.0 + bonus1));
                data.getProfils().get(user.getId()).setOzPassXp(xp += gain);
                Halloween1 = " Vous avez aussi gagné " + gain + " OzXp";
            }
            HashMap<String, Integer> res = data.getProfils().get(user.getId()).getRes();
            int A_bois = res.get("bois");
            int A_acier = res.get("argile");
            int A_cuir = res.get("cuir");
            int A_pierre = res.get("pierre");
            int A_paille = res.get("paille");
            int A_fer = res.get("fer");
            int rarity2 = 1;
            if (rarity.equals("common")) {
                rarity2 = 1;
            } else if (rarity.equals("rare")) {
                rarity2 = 2;
            } else if (rarity.equals("epic")) {
                rarity2 = 3;
            } else if (rarity.equals("legendary")) {
                rarity2 = 4;
            }
            double bonus = 1.0;
            if (Pet_Bonus.equals("resCf")) {
                bonus = pet_bonus;
            }
            Double combos2 = new Double(Combos);
            Double operation1 = combos2 / 100.0;
            Double operation2 = operation1 * (double) rarity2;
            Double operation3 = operation2 + 1.0;
            Double operation = operation3 * 5.0 * bonus;
            int gain = (int) Math.round(operation);

            int bois = 0;
            int acier = 0;
            int cuir = 0;
            int pierre = 0;
            int paille = 0;
            int fer = 0;
            if (rarity.equals("common")) {
                materiau = 1 + (int) (Math.random() * 6.0);
                if (materiau == 1) {
                    bois += gain;
                } else if (materiau == 2) {
                    acier += gain;
                } else if (materiau == 3) {
                    cuir += gain;
                } else if (materiau == 4) {
                    pierre += gain;
                } else if (materiau == 5) {
                    paille += gain;
                } else if (materiau == 6) {
                    fer += gain;
                }
            } else if (rarity.equals("rare")) {
                for (i2 = 0; i2 < 2; ++i2) {
                    materiau2 = 1 + (int) (Math.random() * 6.0);
                    if (materiau2 == 1) {
                        bois += gain;
                        continue;
                    }
                    if (materiau2 == 2) {
                        acier += gain;
                        continue;
                    }
                    if (materiau2 == 3) {
                        cuir += gain;
                        continue;
                    }
                    if (materiau2 == 4) {
                        pierre += gain;
                        continue;
                    }
                    if (materiau2 == 5) {
                        paille += gain;
                        continue;
                    }
                    if (materiau2 != 6)
                        continue;
                    fer += gain;
                }
            } else if (rarity.equals("epic")) {
                for (i2 = 0; i2 < 3; ++i2) {
                    materiau2 = 1 + (int) (Math.random() * 6.0);
                    if (materiau2 == 1) {
                        bois += gain;
                        continue;
                    }
                    if (materiau2 == 2) {
                        acier += gain;
                        continue;
                    }
                    if (materiau2 == 3) {
                        cuir += gain;
                        continue;
                    }
                    if (materiau2 == 4) {
                        pierre += gain;
                        continue;
                    }
                    if (materiau2 == 5) {
                        paille += gain;
                        continue;
                    }
                    if (materiau2 != 6)
                        continue;
                    fer += gain;
                }
            } else if (rarity.equals("legendary")) {
                for (i2 = 0; i2 < 4; ++i2) {
                    materiau2 = 1 + (int) (Math.random() * 6.0);
                    if (materiau2 == 1) {
                        bois += gain;
                        continue;
                    }
                    if (materiau2 == 2) {
                        acier += gain;
                        continue;
                    }
                    if (materiau2 == 3) {
                        cuir += gain;
                        continue;
                    }
                    if (materiau2 == 4) {
                        pierre += gain;
                        continue;
                    }
                    if (materiau2 == 5) {
                        paille += gain;
                        continue;
                    }
                    if (materiau2 != 6)
                        continue;
                    fer += gain;
                }
            } else {
                materiau = 1 + (int) (Math.random() * 6.0);
                if (materiau == 1) {
                    bois += gain;
                } else if (materiau == 2) {
                    acier += gain;
                } else if (materiau == 3) {
                    cuir += gain;
                } else if (materiau == 4) {
                    pierre += gain;
                } else if (materiau == 5) {
                    paille += gain;
                } else if (materiau == 6) {
                    fer += gain;
                }
            }
            String gains = "";
            if (lang == Language.fr) {
                for (i = 1; i <= 6; ++i) {
                    if (i == 1 && bois != 0) {
                        gains = String.valueOf(gains) + "\n" + jda.getGuildById("326345972739473410")
                                .getEmotesByName("wood", true).get(0).getAsMention() + " **Bois :** " + bois;
                        continue;
                    }
                    if (i == 2 && acier != 0) {
                        gains = String.valueOf(gains) + "\n" + jda.getGuildById("326345972739473410")
                                .getEmotesByName("clay", true).get(0).getAsMention() + " **Argile :** " + acier;
                        continue;
                    }
                    if (i == 3 && cuir != 0) {
                        gains = String.valueOf(gains) + "\n" + jda.getGuildById("326345972739473410")
                                .getEmotesByName("leather", true).get(0).getAsMention() + " **Cuir :** " + cuir;
                        continue;
                    }
                    if (i == 4 && pierre != 0) {
                        gains = String.valueOf(gains) + "\n" + jda.getGuildById("326345972739473410")
                                .getEmotesByName("stone", true).get(0).getAsMention() + " **Pierre :** " + pierre;
                        continue;
                    }
                    if (i == 5 && paille != 0) {
                        gains = String.valueOf(gains) + "\n" + jda.getGuildById("326345972739473410")
                                .getEmotesByName("straw", true).get(0).getAsMention() + " **Paille :** " + paille;
                        continue;
                    }
                    if (i != 6 || fer == 0)
                        continue;
                    gains = String.valueOf(gains) + "\n"
                            + jda.getGuildById("326345972739473410").getEmotesByName("iron", true).get(0).getAsMention()
                            + " **Fer :** " + fer;
                }
            }
            if (lang == Language.en) {
                for (i = 1; i <= 6; ++i) {
                    if (i == 1 && bois != 0) {
                        gains = String.valueOf(gains) + "\n" + jda.getGuildById("326345972739473410")
                                .getEmotesByName("wood", true).get(0).getAsMention() + " **Wood :** " + bois;
                        continue;
                    }
                    if (i == 2 && acier != 0) {
                        gains = String.valueOf(gains) + "\n" + jda.getGuildById("326345972739473410")
                                .getEmotesByName("clay", true).get(0).getAsMention() + " **Clay :** " + acier;
                        continue;
                    }
                    if (i == 3 && cuir != 0) {
                        gains = String.valueOf(gains) + "\n" + jda.getGuildById("326345972739473410")
                                .getEmotesByName("leather", true).get(0).getAsMention() + " **Leather :** " + cuir;
                        continue;
                    }
                    if (i == 4 && pierre != 0) {
                        gains = String.valueOf(gains) + "\n" + jda.getGuildById("326345972739473410")
                                .getEmotesByName("stone", true).get(0).getAsMention() + " **Stone :** " + pierre;
                        continue;
                    }
                    if (i == 5 && paille != 0) {
                        gains = String.valueOf(gains) + "\n" + jda.getGuildById("326345972739473410")
                                .getEmotesByName("straw", true).get(0).getAsMention() + " **Straw :** " + paille;
                        continue;
                    }
                    if (i != 6 || fer == 0)
                        continue;
                    gains = String.valueOf(gains) + "\n"
                            + jda.getGuildById("326345972739473410").getEmotesByName("iron", true).get(0).getAsMention()
                            + " **Iron :** " + fer;
                }
            }
            int bois_Total = bois + A_bois;
            int acier_Total = acier + A_acier;
            int cuir_Total = cuir + A_cuir;
            int pierre_Total = pierre + A_pierre;
            int paille_Total = paille + A_paille;
            int fer_Total = fer + A_fer;
            int rarity3 = 7;
            if (rarity.equals("common")) {
                rarity3 = 7;
            } else if (rarity.equals("rare")) {
                rarity3 = 9;
            } else if (rarity.equals("epic")) {
                rarity3 = 11;
            } else if (rarity.equals("legendary")) {
                rarity3 = 13;
            }
            int Game_EXP = data.getProfils().get(user.getId()).getXp();
            Double operation4 = 5.0 + (double) rarity3 * combos2 / 100.0;
            Game_EXP = (int) ((long) Game_EXP + Math.round(operation4));
            try {
                data.getProfils().get(user.getId()).setXp(Game_EXP);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setXp(Game_EXP);
            }
            Pet_EXP = (int) ((long) Pet_EXP + Math.round(operation4 / 10.0));
            if (list != null) {
                list.remove(1);
            }
            if (list != null) {
                list.add(1, String.valueOf(Pet_EXP));
            }
            if (list != null) {
                pet.put(ActivePet, list);
            }
            try {
                data.getProfils().get(user.getId()).setPet(pet);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setPet(pet);
            }
            Quest.Quest("exp", data.getProfils().get(user.getId()), channel, (int) Math.round(operation4));
            Quest.Quest("tr", data.getProfils().get(user.getId()), channel, 1);
            Quest.Quest("materiau", data.getProfils().get(user.getId()), channel, bois + acier + cuir + pierre + paille + fer);
            if (bois != 0) {
                Quest.Quest("bois", data.getProfils().get(user.getId()), channel, bois);
            }
            if (acier != 0) {
                Quest.Quest("acier", data.getProfils().get(user.getId()), channel, acier);
            }
            if (cuir != 0) {
                Quest.Quest("brique", data.getProfils().get(user.getId()), channel, cuir);
            }
            if (pierre != 0) {
                Quest.Quest("pierre", data.getProfils().get(user.getId()), channel, pierre);
            }
            if (paille != 0) {
                Quest.Quest("paille", data.getProfils().get(user.getId()), channel, paille);
            }
            if (fer != 0) {
                Quest.Quest("petrole", data.getProfils().get(user.getId()), channel, fer);
            }
            res.put("bois", bois_Total);
            res.put("argile", acier_Total);
            res.put("cuir", cuir_Total);
            res.put("pierre", pierre_Total);
            res.put("paille", paille_Total);
            res.put("fer", fer_Total);
            try {
                data.getProfils().get(user.getId()).setRes(res);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setRes(res);
            }
            ++Combos;
            try {
                data.getProfils().get(user.getId()).setCf(Combos);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setCf(Combos);
            }
            if (lang == Language.fr) {
                channel.sendMessage(
                        "\ud83d\udd13 Bravo " + user.getAsMention() + ".Vous avez remporté un Coffre Fort **" + rarity
                                + "** (*Combo : " + Combos + "*). \n Il y avait \u00e0 l'intérieur "
                                + Math.round(operation4) + " EXP et : \n" + gains + " \n" + Halloween1)
                        .queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("\ud83d\udd13 Great " + user.getAsMention() + ".You won a strongbox **" + rarity
                        + "** (*Combo : " + Combos + "*). \n You found in it " + Math.round(operation4) + " Xp and : \n"
                        + gains + " \n" + Halloween1).queue();
            }
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Treasure/user.txt", user.getId(), 1);
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Treasure/server.txt", guild.getId(), 1);
        } else {
            long delay = lastcf - System.currentTimeMillis();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(delay);
            int mMinute = calendar.get(12);
            int mSecond = calendar.get(13);
            String Ucible;
            try {
                 Ucible = data.getProfils().get(lastUser).getName();
            }catch(NullPointerException e){
                Ucible = "???";
            }
            String SCible;
                    try {
                        SCible = jda.getGuildById(lastServer).getName();
                    }catch(NullPointerException e){
                        SCible = "???";
                    }
            if (lang == Language.fr) {
                channel.sendMessage("\ud83d\udd12   Le dernier coffre fort a été récupéré par **" + Ucible
                        + "** sur le serveur **" + SCible + "**. \r\n"
                        + "Il sera \u00e0 nouveau récupérable dans : " + mMinute + " minutes et " + mSecond
                        + " secondes.").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("\ud83d\udd12  The last StrongBox was collected by  **" + Ucible
                        + "** on **" + SCible + "**. \r\n" + "The next StrongBox will be available in  "
                        + mMinute + " minutes and " + mSecond + " seconds.").queue();
            }
        }
    }

    @command(name = "clean", type = ExecutorType.ALL, descfr = "Permet de supprimer un certain nombre de message sur le channel", descen = "Allow you to delete some messages on the channel", topic = Topics.Modo)
    private void clean(MessageChannel channel, User user, String[] args, Message message, Guild guild,
                       Language lang) {
        block13:
        {
            try {
                if (guild.getMember(user).hasPermission(Permission.MESSAGE_MANAGE)) {
                    int message2;
                    try {
                        message2 = Integer.parseInt(args[0]);
                    } catch (NumberFormatException e) {
                        boolean message22 = false;
                        if (lang == Language.fr) {
                            channel.sendMessage("Vous devez ecrire un nombre valide").queue();
                        }
                        if (lang == Language.en) {
                            channel.sendMessage("You must write a valid number").queue();
                        }
                        return;
                    }
                    channel.getIterableHistory().takeAsync(message2 + 1).thenAccept(channel::purgeMessages);
                    if (lang == Language.fr) {
                        channel.sendMessage("J'ai supprimé " + message2 + " messages.").queue();
                    }
                    if (lang == Language.en) {
                        channel.sendMessage("I deleted " + (message2 + 1) + " messages.").queue();
                    }
                    break block13;
                }
                if (lang == Language.fr) {
                    channel.sendMessage("Vous n'avez pas les permissions pour effectuer cette action.").queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage("You don't have the necessary permission to perform this ").queue();
                }
            } catch (Exception e) {
                if (lang == Language.fr) {
                    channel.sendMessage("Syntaxe : ``=clean [nombre]``.").queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage("Syntax : ``=clean [nombre]``.").queue();
                }
                return;
            }
        }
    }

    @command(name = "topEXP", type = ExecutorType.ALL, descfr = "Affiche le classement des joueurs en fonctions de leur Xp", descen = " Show the Xp Players LeaderBoard", topic = Topics.Game)
    private void topEXP(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
                        Language lang) {
        int c1;
        ProfilData data = DiscordBot.getData();
        HashMap<String, Integer> classement = new HashMap<String, Integer>();
        try {
            c1 = Integer.parseInt(args[0]);
        } catch (IndexOutOfBoundsException e) {
            c1 = 10;
        }
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
            Object emoji = "";
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
            String member = "";
            if (lang == Language.fr) {
                member = "Une personne discr\u00e8te | **Level** : " + level + (String) emoji + " | **Xp** ";
            }
            if (lang == Language.en) {
                member = "An invisible person | **Level** : " + level + (String) emoji + " | **Xp** ";
            }
            try {
                member = profil.getName() + " | **Level** : " + level
                        + (String) emoji + " | **Xp** ";
            } catch (NullPointerException math) {
                // empty catch block
            }
            classement.put(member, Game_EXP);
        }
        ArrayList<Entry<String, Integer>> entries = new ArrayList(classement.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> e2, Map.Entry<String, Integer> e1) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        if (c1 > entries.size()) {
            c1 = entries.size();
        }
        String messages = "";
        int o = 1;
        messages = ":trophy:  Leaderboard :trophy: :\n";
        for (Map.Entry entry : entries) {
            String rank;
            if (o == 1) {
                rank = ":first_place:";
            } else if (o == 2) {
                rank = ":second_place:";
            } else if (o == 3) {
                rank = ":third_place:";
            } else {
                if (lang == Language.fr) {
                    rank = String.valueOf(o) + "\u00e8me";
                }
                rank = lang == Language.en ? String.valueOf(o) + "e" : String.valueOf(o) + "e";
            }
            messages = String.valueOf(messages) + "**" + rank + "** : " + (String) entry.getKey() + " : "
                    + entry.getValue() + "\n";
            if (messages.length() > 1900 || ++o > c1)
                break;
        }
        channel.sendMessage(messages).queue();
    }

    @command(name = "topPays", type = ExecutorType.ALL, descfr = "Affiche le classement des Pays", descen = "Show the Country's LeaderBoard", topic = Topics.Game)
    private void topPays(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
                         Language lang) {
        String c1;
        int i;
        File[] files;
        HashMap<String, Integer> classement = new HashMap<String, Integer>();
        try {
            c1 = args[0];
        } catch (IndexOutOfBoundsException e) {
            c1 = "10";
        }
        File repertoire = new File("/home/DiscordBot/Rasberry/données/bot/Pays/");
        for (File file : files = repertoire.listFiles()) {
            try {
                int Game_EXP = 0;
                try {
                    Game_EXP = Integer.parseInt(TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/bot/Pays/" + file.getName() + "/points.txt"));
                } catch (IndexOutOfBoundsException e) {
                    c1 = "10";
                }
                double level2 = Game_EXP / 1000;
                double level3 = Math.sqrt(level2);
                int level = (int) Math.round(level3);
                String member = String.valueOf(file.getName()) + " | **Level** : " + level;
                classement.put(member, Game_EXP);
            } catch (IndexOutOfBoundsException Game_EXP) {
                // empty catch block
            }
        }
        ArrayList entries = new ArrayList(classement.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        if (c1 != "0" && Integer.parseInt(c1) < entries.size() - 1) {
            String messages = ":trophy:  Classement :trophy: :\n";
            for (i = 1; i <= Integer.parseInt(c1); ++i) {
                messages = String.valueOf(messages) + "__***" + i + "e***__ : " + entries.get(entries.size() - i)
                        + "\n";
            }
            channel.sendMessage(messages).queue();
        } else {
            String messages = ":trophy:  Classement :trophy: :\n";
            for (i = 1; i <= 10 && i < entries.size(); ++i) {
                messages = String.valueOf(messages) + "__***" + i + "e***__ : " + entries.get(entries.size() - i)
                        + "\n";
            }
            channel.sendMessage(messages).queue();
        }
    }

    @command(name = "server", type = ExecutorType.CONSOLE)
    private void server() {
        JDA jda = DiscordBot.getjda();
        List<Guild> guilds = jda.getGuilds();
        String message = "";
        for (Guild guild : guilds) {
            message = String.valueOf(message) + " " + guild.getName() + " | " + guild.getMembers().size();
        }


    }

    @command(name = "region", type = ExecutorType.CONSOLE)
    private void region() {
        JDA jda = DiscordBot.getjda();
        int amsterdam = 0;
        int brazil = 0;
        int eu_central = 0;
        int eu_west = 0;
        int frankfurt = 0;
        int hong_kong = 0;
        int japan = 0;
        int london = 0;
        int russia = 0;
        int singapore = 0;
        int south_africa = 0;
        int sydney = 0;
        int us_central = 0;
        int us_south = 0;
        int us_west = 0;
        int us_east = 0;
        int unknow = 0;

        System.out.println("Voici la liste des regions des serveur : \nAmsterdam : " + amsterdam + "\n" + "Brazil : "
                + brazil + "\n" + "EU Central : " + eu_central + "\n" + "EU West : " + eu_west + "\n" + "Franfurt : "
                + frankfurt + "\n" + "Hong Kong : " + hong_kong + "\n" + "Japan : " + japan + "\n" + "London : "
                + london + "\n" + "Russia : " + russia + "\n" + "Singapore : " + singapore + "\n" + "South Africa : "
                + south_africa + "\n" + "Sydney : " + sydney + "\n" + "US Central : " + us_central + "\n" + "US East : "
                + us_east + "\n" + "US South : " + us_south + "\n" + "US West : " + us_west + "\n" + "Unknow : "
                + unknow);
    }

    @command(name = "topCf", type = ExecutorType.ALL, descfr = "Affiche le classement des joueurs en fonction du nombre de cf recupérés", descen = " Show the Cf players LeaderBoard", topic = Topics.Game)
    private void topCf(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
                       ProfilData data, Language lang) {
        int c1;
        HashMap<String, Integer> classement = new HashMap<String, Integer>();
        try {
            c1 = Integer.parseInt(args[0]);
        } catch (IndexOutOfBoundsException e) {
            c1 = 10;
        }
        for (Profil profil : data.getProfils().values()) {
            int combo = profil.getCf();
            String member = "";
            if (lang == Language.fr) {
                member = "Une personne discr\u00e8te | **Combo**  ";
            }
            if (lang == Language.en) {
                member = "An invisible person | **Combo**  ";
            }
            try {
                member = profil.getName() + " | **Combo**  ";
            } catch (NullPointerException nullPointerException) {
                // empty catch block
            }
            classement.put(member, combo);
        }
        ArrayList<Entry<String, Integer>> entries = new ArrayList(classement.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> e2, Map.Entry<String, Integer> e1) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        if (c1 > entries.size()) {
            c1 = entries.size();
        }
        String messages = "";
        int o = 1;
        messages = ":trophy:  Leaderboard :trophy: :\n";
        for (Map.Entry entry : entries) {
            String rank;
            if (o == 1) {
                rank = ":first_place:";
            } else if (o == 2) {
                rank = ":second_place:";
            } else if (o == 3) {
                rank = ":third_place:";
            } else {
                if (lang == Language.fr) {
                    rank = String.valueOf(o) + "\u00e8me";
                }
                rank = lang == Language.en ? String.valueOf(o) + "e" : String.valueOf(o) + "e";
            }
            messages = String.valueOf(messages) + "**" + rank + "** : " + (String) entry.getKey() + " : "
                    + entry.getValue() + "\n";
            if (messages.length() > 1900 || ++o > c1)
                break;
        }
        channel.sendMessage(messages).queue();
    }

    /*
     * Enabled force condition propagation Lifted jumps to return sites
     */
    @command(name = "setgrade", type = ExecutorType.ALL, descfr = "Permet de modifier son grade (premium uniquement)", descen = "Allow you to modifiy your rank (Premium only)")
    private void setgrade(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
                          Language lang, ProfilData data) {
        if (Premium.Premium(data.getProfils().get(user.getId()))) {
            try {
                String grade = args[0];
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/grade.txt", grade,
                        1);
                if (lang == Language.fr) {
                    channel.sendMessage("Votre grade est desormais " + grade).queue();
                }
                if (lang != Language.en)
                    return;
                channel.sendMessage("Your personal rank is now " + grade).queue();
                return;
            } catch (IndexOutOfBoundsException e) {
                if (lang == Language.fr) {
                    channel.sendMessage("Vous devez indiquer le grade que vous souhaitez avoir.").queue();
                }
                if (lang != Language.en)
                    return;
                channel.sendMessage("You must indicate the personal rank you would have").queue();
                return;
            }
        } else {
            if (lang == Language.fr) {
                channel.sendMessage("Désolé mais vous devez etre premium pour acceder a cette fonctionalité.").queue();
            }
            if (lang != Language.en)
                return;
            channel.sendMessage("Sorry but you must be Premium to access to this ").queue();
        }
    }

    @command(name = "unmute", type = ExecutorType.ALL, descfr = "Permet d'unmute un joueur", descen = "Allow you to unmute a player", topic = Topics.Modo)
    private void unmute(MessageChannel channel, User user, String[] args, Message message, Guild guild,
                        Language lang) {
        try {
            List<Role> role = guild.getRolesByName("Muted", true);
            Role role2 = role.get(0);
            User cible = message.getMentionedUsers().get(0);
            guild.removeRoleFromMember(guild.getMember(cible), role2).queue();
            if (lang == Language.fr) {
                channel.sendMessage(String.valueOf(cible.getName()) + " a bien été unmute.").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage(String.valueOf(cible.getName()) + " has been unmute.").queue();
            }
        } catch (Exception e) {
            if (lang == Language.fr) {
                channel.sendMessage("Syntaxe : ``=unmute [mention]``.").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("Syntax : ``=unmute [mention]``.").queue();
            }
            return;
        }
    }

    @command(name = "slowmode", type = ExecutorType.ALL, descfr = "Permet de definir un delai entre deux messages consecutif sur un channel", descen = "Define a delay between two consecutive messages on a channel", topic = Topics.Modo)
    private void slowmode(TextChannel channel, User user, String[] args, MessageChannel message, Guild guild,
                          Language lang) {
        if (guild.getMember(user).hasPermission(Permission.MANAGE_CHANNEL)) {
            int c1;
            try {
                c1 = Integer.parseInt(args[0]);
            } catch (ArrayIndexOutOfBoundsException e) {
                if (lang == Language.fr) {
                    channel.sendMessage("Syntaxe : ``=slowmode [durée]``.").queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage("Syntax : ``=slowmode [duration]``.").queue();
                }
                return;
            } catch (NumberFormatException e) {
                if (lang == Language.fr) {
                    channel.sendMessage("Syntaxe : ``=slowmode [durée]``.").queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage("Syntax : ``=slowmode [duration]``.").queue();
                }
                return;
            }
            if (c1 > 120) {
                if (lang == Language.fr) {
                    message.sendMessage("Les cooldown doit etre compris entre 1 et 120 secondes").queue();
                }
                if (lang == Language.en) {
                    message.sendMessage("The cooldown must be between 1 and 120 seconds").queue();
                }
                return;
            }
            channel.getManager().setSlowmode(c1).queue();
            if (lang == Language.fr) {
                message.sendMessage("Le slowmode est désormais de " + c1 + " secondes").queue();
            }
            if (lang == Language.en) {
                message.sendMessage("The slowmode is now set on " + c1 + " seconds on this channel").queue();
            }
        } else {
            if (lang == Language.fr) {
                channel.sendMessage("Vous n'avez pas les permissions pour effectuer cet action.").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("You don't have the necessary permissions to perform this ").queue();
            }
            return;
        }
    }

    @command(name = "partenaires", abbrev = "partners", type = ExecutorType.ALL, descfr = "Affiche la liste des partnaires OzeryoBot", descen = "Show the OzeryoBot Partner List")
    private void partenaire(Message message, User user, String[] args, MessageChannel channel, Guild guild,
                            Language lang) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(user.getName(), null, user.getAvatarUrl());
        builder.setColor(color.couleurAleatoire(user));
        if (lang == Language.fr) {
            builder.setDescription(":busts_in_silhouette: Listes des Partenaires d'OzeryoBot :heart: ");
        }
        if (lang == Language.en) {
            builder.setDescription(":busts_in_silhouette: OzeryoBot Partners List :heart: ");
        }
        channel.sendMessage(builder.build()).queue();
    }

    @command(name = "CPU", type = ExecutorType.ALL, descfr = "Affiche les information processeur du bot", descen = " Allow you to see the CPU informations", topic = Topics.Admin)
    private void CPU(Message message, User user, String[] args, MessageChannel channel, Guild guild) {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        String message1 = "";
        for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
            Object value;
            method.setAccessible(true);
            if (!method.getName().startsWith("get") || !Modifier.isPublic(method.getModifiers()))
                continue;
            try {
                value = method.invoke(operatingSystemMXBean, new Object[0]);
            } catch (Exception e) {
                value = e;
            }
            message1 = String.valueOf(method.getName()) + " = " + value + "\n";
        }
        channel.sendMessage(String.valueOf(message1)).queue();
    }

    @command(name = "parrain", type = ExecutorType.ALL, descfr = "Permet de parrainer un joueur", descen = "Allow you to choose your sponsor")
    private void parrain(Message message, User user, String[] args, MessageChannel channel, Guild guild,
                         ProfilData data, Language lang) {
        String c1;
        int level;
        try {
            c1 = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            c1 = "";
        }
        int Game_EXP = data.getProfils().get(user.getId()).getXp();
        try {
            double operation = 3 * Game_EXP / 4;
            double math = Math.sqrt(operation);
            level = (int) Math.round(math);
        } catch (NullPointerException e) {
            level = 0;
        }
        int Ufilleuls = data.getProfils().get(user.getId()).getFilleuls();
        if (c1.equals("")) {
            Boolean parrain = data.getProfils().get(user.getId()).isParrain();
            String emoji = ":white_check_mark: ";
            if (parrain.booleanValue()) {
                emoji = ":x:";
            }
            if (lang == Language.fr) {
                channel.sendMessage(String.valueOf(emoji) + " | Vous avez actuellement " + Ufilleuls + " filleuls.")
                        .queue();
            }
            if (lang == Language.en) {
                channel.sendMessage(String.valueOf(emoji) + " | You actualy have " + Ufilleuls + " godson.").queue();
            }
        } else {
            if (level < 30) {
                if (lang == Language.fr) {
                    channel.sendMessage("Vous devez atteindre le niveau 30 du jeu pour pouvoir choisir votre parrain.")
                            .queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage("You must reach level 30 of the game to be able to choose your sponsor.")
                            .queue();
                }
                return;
            }
            User cible = null;
            try {
                cible = message.getMentionedUsers().get(0);
            } catch (ArrayIndexOutOfBoundsException e) {
                if (lang == Language.fr) {
                    channel.sendMessage("Veuillez mentionner le joueur que vous voulez designer comme votre parrain.")
                            .queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage("Please mention the player you want to designate as your sponsor.").queue();
                }
                return;
            }
            if (cible.getId().equals(user.getId())) {
                if (lang == Language.fr) {
                    channel.sendMessage("Vous ne pouvez pas vous parrainer vous meme.").queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage("You can't sponsor yourself.").queue();
                }
                return;
            }
            if (cible.isBot()) {
                if (lang == Language.fr) {
                    channel.sendMessage("Vous ne pouvez pas parrainer un bot.").queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage("You can't sponsor a bot.").queue();
                }
                return;
            }
            Boolean parrain = data.getProfils().get(user.getId()).isParrain();
            if (parrain.booleanValue()) {
                if (lang == Language.fr) {
                    channel.sendMessage("Désolé mais vous avez deja choisit votre parrain.").queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage("Sorry but you already choose your sponsor.").queue();
                }
                return;
            }
            data.getProfils().get(user.getId()).setParrain(true);
            int Cfilleuls = data.getProfils().get(cible.getId()).getFilleuls();
            data.getProfils().get(cible.getId()).setFilleuls(++Cfilleuls);
            if (lang == Language.fr) {
                channel.sendMessage("Vous avez choisit " + cible.getName() + " comme parrain.").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("You chosse " + cible.getName() + " as sponsor.").queue();
            }
            Language langcible = data.getProfils().get(cible.getId()).getLanguage();
            if (!cible.hasPrivateChannel()) {
                cible.openPrivateChannel().complete();
            }
            if (langcible == Language.fr) {
                ((UserImpl) cible).getPrivateChannel().sendMessage(
                        "Vous venez de gagner un nouveau filleul. Vous avez actuellement " + Cfilleuls + " filleuls.")
                        .queue();
            }
            if (langcible == Language.en) {
                ((UserImpl) cible).getPrivateChannel()
                        .sendMessage("You won a new godson. You have actually " + Cfilleuls + " godson.").queue();
            }
            if (Cfilleuls % 3 == 0 && Cfilleuls != 0) {
                if (!cible.hasPrivateChannel()) {
                    cible.openPrivateChannel().complete();
                }
                if (langcible == Language.fr) {
                    ((UserImpl) cible).getPrivateChannel().sendMessage(
                            "Bravo vous avez " + Cfilleuls + " filleuls, vous remportez donc un premium d'un mois.")
                            .queue();
                }
                if (langcible == Language.en) {
                    ((UserImpl) cible).getPrivateChannel()
                            .sendMessage(
                                    "Great you have " + Cfilleuls + " godson, So you win one month of Ozeryo Premium.")
                            .queue();
                }
                if (Premium.Premium(data.getProfils().get(cible.getId()))) {
                    long premium = data.getProfils().get(cible.getId()).getPremium();
                    data.getProfils().get(cible.getId()).setPremium(premium += 2592000000L);
                } else {
                    long premium = System.currentTimeMillis() + 2592000000L;
                    data.getProfils().get(cible.getId()).setPremium(premium);
                }
            }
        }
    }

    @command(name = "math", type = ExecutorType.ALL, descfr = "Permet d'effectuer un calcul mathematique, c'est fantastique !", descen = " Allow you to execute a mathematical calculation, it's fantastic !")
    private void math(Message message, User user, String arg, MessageChannel channel, Guild guild, JDA jda,
                      Language lang) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        String foo = arg.replace("math", "");

        try {

            if (foo.equals("")) {
                if (lang == Language.fr) {
                    channel.sendMessage("Syntaxe : ``=math [calcul]``.").queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage("Syntax : ``=math [calculation]``.").queue();
                }
            }
            channel.sendMessage("``" + foo + "`` = " + engine.eval(foo)).queue();
        } catch (ScriptException e) {
            if (lang == Language.fr) {
                channel.sendMessage("Erreur Math : " + arg).queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("Error Math : " + arg).queue();
            }
            e.printStackTrace();
        }
    }

    @command(name = "bid", type = ExecutorType.ALL, descfr = "Permet d'encherir sur l'objet du jour en vente", descen = "Allow you to bet on the daily object on sale", topic = Topics.Game)
    private void bid(Message message, User user, String[] args, MessageChannel channel, Guild guild, JDA jda,
                     ProfilData data, Language lang) {
        int c1;
        try {
            c1 = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            c1 = 0;
        } catch (NumberFormatException e) {
            c1 = 0;
        }
        if (c1 == 0) {
            String cible;
            int jetons;
            String object2 = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Bid/bid.txt");
            String object1 = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Bid/object.txt");
            String cible1 = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Bid/biduser.txt");
            try {
                cible = data.getProfils().get(cible1).getName();
            } catch (NullPointerException e) {
                cible = "personne";
            }
            try {
                jetons = Integer
                        .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Bid/bidjetons.txt"));
            } catch (NumberFormatException e) {
                jetons = 0;
            }
            int jetons1 = data.getProfils().get(user.getId()).getTokens();
            String mess = "";
            int level = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Bid/level.txt"));
            if (object1.equals("weapon") || object1.equals("armor")) {
                mess = " de niveau **" + level + "**";
            }
            if (lang == Language.fr) {
                channel.sendMessage(
                        "\u2696 Vous avez **" + jetons1 + "** jetons\r\n" + "L'ench\u00e8re du jour est **" + object2
                                + "**" + mess + ". **" + cible + "** a enchérit dessus pour **" + jetons + "** jetons.")
                        .queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("\u2696 You have **" + jetons1 + "** tokens\r\n" + "The bid of the day is **"
                        + object2 + "**" + mess + ". **" + cible + "** who has bid on for **" + jetons + "** tokens.")
                        .queue();
            }
        } else {
            int Ljetons;
            try {
                Ljetons = Integer
                        .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Bid/bidjetons.txt"));
            } catch (NumberFormatException e) {
                Ljetons = 0;
            }
            int jetons = data.getProfils().get(user.getId()).getTokens();
            if (c1 > jetons) {
                if (lang == Language.fr) {
                    channel.sendMessage("Vous ne pouvez pas miser plus de jetons que vous n'en avez.").queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage("You can't bet more tokens than you have.").queue();
                }
                return;
            }
            if (c1 <= Ljetons) {
                if (lang == Language.fr) {
                    channel.sendMessage("Vous ne pouvez pas misez un nombre inferieur de jetons a celui actuel.")
                            .queue();
                }
                if (lang == Language.en) {
                    channel.sendMessage("You can't bet a lower number of tokens than the current one .").queue();
                }
                return;
            }
            String object2 = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Bid/bid.txt");
            String object1 = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Bid/object.txt");
            data.getProfils().get(user.getId()).setTokens(jetons -= c1);
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Bid/bidjetons.txt", Integer.toString(c1), 1);
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Bid/biduser.txt", user.getId(), 1);
            if (lang == Language.fr) {
                channel.sendMessage("\u2696 Vous venez de miser **" + c1 + "** jetons pour **" + object2 + "**.\r\n"
                        + "Si vous remportez celle-ci, vous serez informé aux alentours de minuit (UTC+2).").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("\u2696 you have just bet **" + c1 + "** tokens for **" + object2 + "**.\r\n"
                        + "If you win the bid, you will be inform around midnight (UTC+2).").queue();
            }
        }
    }

    @command(name = "topTrophy", type = ExecutorType.ALL, descfr = "Affiche le top des joueurs en fonction de leurs trophées", descen = " Show the Trophy player top", topic = Topics.Game)
    private void topTrophy(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
                           Language lang) {
        int c1;
        ProfilData data = DiscordBot.getData();
        HashMap<String, Integer> classement = new HashMap<String, Integer>();
        try {
            c1 = Integer.parseInt(args[0]);
        } catch (IndexOutOfBoundsException e) {
            c1 = 10;
        }
        for (Profil profil : data.getProfils().values()) {
            int trophees = profil.getTrophy();
            String rank = Attack.rank(trophees);
            Object member = "";
            if (lang == Language.fr) {
                member = "Une personne discrète | **Rank** : " + rank + " | **Trophées**  ";
            }
            if (lang == Language.en) {
                member = "An invisible Person | **Rank** : " + rank + " | **Trophies**  ";
            }
            try {
                if (lang == Language.fr) {
                    member = profil.getName() + " | **Rank** : " + rank
                            + " | **Trophées**  ";
                }
                if (lang == Language.en) {
                    member = profil.getName() + " | **Rank** : " + rank
                            + " | **Trophies**  ";
                }
            } catch (NullPointerException nullPointerException) {
                // empty catch block
            }
            classement.put((String) member, trophees);
        }
        ArrayList<Entry<String, Integer>> entries = new ArrayList(classement.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> e2, Map.Entry<String, Integer> e1) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        if (c1 > entries.size()) {
            c1 = entries.size();
        }
        String messages = "";
        int o = 1;
        messages = ":trophy:  Leaderboard :trophy: :\n";
        for (Map.Entry entry : entries) {
            String rank;
            if (o == 1) {
                rank = ":first_place:";
            } else if (o == 2) {
                rank = ":second_place:";
            } else if (o == 3) {
                rank = ":third_place:";
            } else {
                if (lang == Language.fr) {
                    rank = String.valueOf(o) + "\u00e8me";
                }
                rank = lang == Language.en ? String.valueOf(o) + "e" : String.valueOf(o) + "e";
            }
            messages = String.valueOf(messages) + "**" + rank + "** : " + (String) entry.getKey() + " : "
                    + entry.getValue() + "\n";
            if (messages.length() > 1900 || ++o > c1)
                break;
        }
        channel.sendMessage(messages).queue();
    }

    @command(name = "setdescription", type = ExecutorType.ALL, descfr = "Permet de definir une description de profil (Premium only)", descen = " Allow you to set your profile description ( Premium only)")
    private void setdescription(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
                                Language lang, ProfilData data) {
        if (Premium.Premium(data.getProfils().get(user.getId()))) {
            String desc = message.getContentRaw();
            desc = desc.replaceAll("=setdescription ", "");
            try {
                data.getProfils().get(user.getId()).setDescription(desc);
                data.getProfils().get(user.getId()).setId(user.getId());
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setDescription(desc);
            }
            if (lang == Language.fr) {
                channel.sendMessage("Votre description est désormais : \n \n **" + desc + "**").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("Your description is now : \n \n **" + desc + "**").queue();
            }
        } else {
            if (lang == Language.fr) {
                channel.sendMessage("Vous devez etre premium pour changer votre description.").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("You must be Premium to change your description.").queue();
            }
        }
    }

    @command(name = "timerpanel", type = ExecutorType.ALL, descfr = "Affiche la liste de vos operations en cours", descen = " Show you the list of all your operation in progress")
    private void timerpanel(MessageChannel channel, User user, String[] args, Message message, Guild guild,JDA jda,
                            Language lang) {
        String rep;
        int mMinute;
        int Pet_EXP;
        int mSecond;
        String vote;
        String daily;
        int level;
        String hourly2;
        ProfilData data = DiscordBot.getData();
        long lastHourly = data.getProfils().get(user.getId()).getLastDaily();
        long delay = System.currentTimeMillis() - lastHourly;
        if (delay > 86400000L) {
            daily = ":white_check_mark:";
        } else {
            int mHour = (int) (delay / 3600000L);
            int mMinute2 = (int) ((delay %= 3600000L) / 60000L);
            int mSecond2 = (int) ((delay %= 60000L) / 1000L);
            daily = String.valueOf(23 - mHour) + "h" + (59 - mMinute2) + "m" + (59 - mSecond2) + "s";
        }
        long lastrep = data.getProfils().get(user.getId()).getLastRep();
        delay = System.currentTimeMillis() - lastrep;
        if (delay >= 86400000L) {
            rep = ":white_check_mark: ";
        } else {
            int mHour = (int) (delay / 3600000L);
            mMinute = (int) ((delay %= 3600000L) / 60000L);
            mSecond = (int) ((delay %= 60000L) / 1000L);
            rep = String.valueOf(23 - mHour) + "h" + (59 - mMinute) + "m" + (59 - mSecond) + "s";
        }
        lastHourly = data.getProfils().get(user.getId()).getLastHourly();
        delay = System.currentTimeMillis() - lastHourly;
        if (delay >= 3600000L) {
            hourly2 = ":white_check_mark: ";
        } else {
            mMinute = (int) (delay / 60000L);
            mSecond = (int) ((delay %= 60000L) / 1000L);
            hourly2 = String.valueOf(59 - mMinute) + "m" + (59 - mSecond) + "s";
        }
        String RMD = "";
        try {
            for (ArrayList<String> rmd : data.getProfils().get(user.getId()).getRMD().values()) {
                Object timer = Long.parseLong(rmd.get(0));
                timer = (Long) timer - System.currentTimeMillis();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis((Long) timer);
                int mHour = calendar.get(11) - 1;
                int mMinute3 = calendar.get(12);
                int mSecond3 = calendar.get(13);
                RMD = String.valueOf(RMD) + "**" + mHour + "h" + mMinute3 + "m" + mSecond3 + "s** : " + rmd.get(1)
                        + "\n";
            }
        } catch (NullPointerException rmd) {
            // empty catch block
        }
        String Attack2 = "";
        try {
            HashMap<Long, ArrayList<String>> test = data.getProfils().get(user.getId()).getAttack();

            TreeMap<Long, ArrayList<String>> sorted = new TreeMap<>();
            sorted.putAll(test);


            for (ArrayList<String> atk : sorted.values()) {
                String cible;
                Object timer = Long.parseLong(atk.get(0));
                timer = (Long) timer - System.currentTimeMillis();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis((Long) timer);
                int mHour = calendar.get(11) - 1;
                int mMinute4 = calendar.get(12);
                int mSecond4 = calendar.get(13);
                if(!atk.get(1).toString().contains("_")) {
                    cible = data.getProfils().get(atk.get(1)).getName();
                }else {
                    String[] at = atk.get(1).split("_");
                    String x = at[0];
                    String y = at[1];
                    cible = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Map/" + atk.get(1) + "/name.txt")
                            .equals("dungeon") ? "attaque de donjon ("+x+" "+y+")" : "zone de ressource ("+x+" "+y+")";
                }
                Attack2 = String.valueOf(Attack2) + "**" + mHour + "h" + mMinute4 + "m" + mSecond4 + "s** : " + cible
                        + " \n";
            }
        } catch (NullPointerException atk) {
            // empty catch block
        }
        String Give = "";
        try {
            for (ArrayList<String> give : data.getProfils().get(user.getId()).getGive().values()) {
                Long timer = Long.parseLong(give.get(0));
                timer = timer - System.currentTimeMillis();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timer);
                int mHour = calendar.get(11) - 1;
                int mMinute5 = calendar.get(12);
                int mSecond5 = calendar.get(13);
                Give = String.valueOf(Give) + "**" + mHour + "h" + mMinute5 + "m" + mSecond5 + "s** : "
                        + data.getProfils().get(give.get(1)).getName() + " \n";
            }
        } catch (NullPointerException give) {
            // empty catch block
        }
        String temple = TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/temple.txt");
        String pet = TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/pet.txt");
        try {
            Pet_EXP = Integer.parseInt(data.getProfils().get(user.getId()).getPet().get(pet).get(1));
        } catch (NullPointerException e) {
            Pet_EXP = 0;
        }
        String Pet_Bonus = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pets/" + pet);
        double operation1 = Pet_EXP / 10;
        double operation2 = Math.sqrt(operation1);
        double Pet_Level = Math.round(operation2);
        int Game_EXP = data.getProfils().get(user.getId()).getXp();
        try {
            double operation = 3 * Game_EXP / 4;
            double math = Math.sqrt(operation);
            level = (int) Math.round(math);
        } catch (NullPointerException e) {
            level = 0;
        }
        int mana = 10;
        mana = data.getProfils().get(user.getId()).getMana();
        long lastmana = data.getProfils().get(user.getId()).getLastMana();
        delay = System.currentTimeMillis() - lastmana;
        delay /= 1000L;
        int regen = Pet_Bonus.equals("regen") ? 300 - (int) (Pet_Level * 3.0) : 300;
        if (temple.equals("Hermes")) {
            regen -= 60;
        }
        if (regen < 150) {
            regen = 150;
        }
        if (delay > (long) regen) {
            while (delay > (long) regen) {
                ++mana;
                delay -= (long) regen;
            }
            try {
                data.getProfils().get(user.getId()).setLastMana(System.currentTimeMillis() - delay);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setLastMana(System.currentTimeMillis());
            }
        }
        int Mana_Max = 10 + level;
        if (level > 190) {
            Mana_Max = 200;
        }
        if (Pet_Bonus.equals("mana")) {
            Mana_Max += (int) (Pet_Level * 3.0);
        }
        if (temple.equals("Athena")) {
            Mana_Max = (int) ((double) Mana_Max * 1.25);
        }
        if (mana > Mana_Max) {
            mana = Mana_Max;
        }
        if (mana < 0) {
            mana = Mana_Max;
        }
        try {
            data.getProfils().get(user.getId()).setMana(mana);
        } catch (NullPointerException e) {
            data.getProfils().put(user.getId(), new Profil(user.getId()));
            data.getProfils().get(user.getId()).setMana(mana);
        }
        int manarestant = Mana_Max - mana;
        int timerestant = manarestant * regen;
        String mana1 = "";
        if (timerestant < 0) {
            mana1 = ":white_check_mark:";
        } else {
            int heures = timerestant / 60 / 60;
            int minutes = timerestant / 60 - heures * 60;
            int secondes = timerestant - minutes * 60 - heures * 60 * 60;
            mana1 = String.valueOf(heures) + "h" + minutes + "m" + secondes + "s";
        }
        int sec = Integer.parseInt(
                TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/vote/sec.txt"));
        int min = Integer.parseInt(
                TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/vote/min.txt"));
        int hr = Integer.parseInt(
                TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/vote/hr.txt"));
        int day = Integer.parseInt(
                TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/vote/day.txt"));
        String secondes11 = new SimpleDateFormat("ss", Locale.FRANCE).format(new Date());
        String minutes11 = new SimpleDateFormat("mm", Locale.FRANCE).format(new Date());
        String heures11 = new SimpleDateFormat("HH", Locale.FRANCE).format(new Date());
        String jours1 = new SimpleDateFormat("dd", Locale.FRANCE).format(new Date());
        int Usecondes = Integer.parseInt(secondes11) - sec;
        int Uminutes = Integer.parseInt(minutes11) - min;
        int Uheures = Integer.parseInt(heures11) - hr;
        int Ujours = Integer.parseInt(jours1) - day;
        if (Usecondes < 0) {
            Usecondes += 60;
            --Uminutes;
        }
        if (Uminutes < 0) {
            Uminutes += 60;
            --Uheures;
        }
        if (Uheures < 0) {
            Uheures += 24;
            --Ujours;
        }
        if (Uheures >= 12 || Ujours > 1) {
            vote = ":white_check_mark:";
        } else {
            Uheures = 11 - Uheures;
            Uminutes = 60 - Uminutes;
            Usecondes = 60 - Usecondes;
            vote = String.valueOf(Uheures) + "h" + Uminutes + "m" + Usecondes + "s";
        }
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Timerpanel");
        builder.setAuthor(user.getName(), null, user.getAvatarUrl());
        builder.setColor(color.couleurAleatoire(user));
        builder.setThumbnail(user.getAvatarUrl());
        builder.addField(":clock1: Hourly", hourly2, true);
        builder.addField(":sunny: Daily", daily, true);
        builder.addField(":e_mail: Rep", rep, true);
        builder.addField(":incoming_envelope: Vote ", vote, true);
        builder.addField(String
                .valueOf(jda.getGuildById("326345972739473410").getEmotesByName("lab", true).get(0).getAsMention())
                + " Work All ", mana1, true);
        builder.addField(":crossed_swords: Attack", Attack2, true);
        builder.addField(":notepad_spiral: RMD", RMD, true);
        builder.addField(":currency_exchange:  Give", Give, true);
        channel.sendMessage(builder.build()).queue();
    }

    @command(name = "test", type = ExecutorType.ALL, descfr = "Affiche la proportion de la carte utilisé", descen = "Show the map statistic", topic = Topics.Admin)
    private void test(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda) {
        int taille = TextFileWriter.folderlength("/home/DiscordBot/Rasberry/données/bot/Map/");
        String message2 = String.valueOf(taille) + " joueurs  (" + taille * 100 / 441 + ")\n";
        for (int i = 0; i < 20; ++i) {
            int x = 0;
            int y = 0;
            int places = 0;
            int alea = 1 + (int) (Math.random() * (double) (441 - taille - 1 + 1));
            for (x = -10; x <= 10; ++x) {
                for (y = -10; y <= 10; ++y) {
                    String name;
                    try {
                        name = TextFileWriter
                                .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt");
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
            message2 = String.valueOf(message2) + "\n **alea :** " + alea + " | " + x + " | " + y;
        }
        channel.sendMessage(message2).queue();
    }

    @command(name = "topap", type = ExecutorType.ALL, descfr = "Affiche le classement des joueurs en fonction de leur nombre d'Achievement Point", descen = "Show the Achievement Point Player's LeaderBoard", topic = Topics.Game)
    private void topap(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
                       Language lang) {
        int c1;
        ProfilData data = DiscordBot.getData();
        HashMap<String, Integer> classement = new HashMap<String, Integer>();
        try {
            c1 = Integer.parseInt(args[0]);
        } catch (IndexOutOfBoundsException e) {
            c1 = 10;
        }
        for (Profil profil : data.getProfils().values()) {
            int rep = profil.getAp();
            String member = "";
            if (lang == Language.fr) {
                member = "Une personne discr\u00e8te | **Achievement Points** ";
            }
            if (lang == Language.en) {
                member = "An invisible person | **Achievement Points** ";
            }
            try {
                member = profil.getName() + " | **Achievement Points** ";
            } catch (NullPointerException nullPointerException) {
                // empty catch block
            }
            classement.put(member, rep);
        }
        ArrayList<Entry<String, Integer>> entries = new ArrayList(classement.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> e2, Map.Entry<String, Integer> e1) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        if (c1 > entries.size()) {
            c1 = entries.size();
        }
        String messages = "";
        int o = 1;
        messages = ":trophy:  Leaderboard :trophy: :\n";
        for (Map.Entry entry : entries) {
            String rank = "";
            if (o == 1) {
                rank = ":first_place:";
            } else if (o == 2) {
                rank = ":second_place:";
            } else if (o == 3) {
                rank = ":third_place:";
            } else {
                if (lang == Language.fr) {
                    rank = String.valueOf(o) + "\u00e8me";
                }
                if (lang == Language.en) {
                    rank = String.valueOf(o) + "e";
                }
            }
            messages = String.valueOf(messages) + "**" + rank + "** : " + (String) entry.getKey() + " : "
                    + entry.getValue() + "\n";
            if (messages.length() > 1900 || ++o > c1)
                break;
        }
        channel.sendMessage(messages).queue();
    }

    @command(name = "helpstories", type = ExecutorType.ALL, descfr = "Affiche la liste des Stories", descen = "Show the list of all sotries")
    private void helpstories(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda) {
        channel.sendMessage(
                "**Stories** \ud83c\udf93 \r\n\r\n**=vulcain** : 10reps\r\n**=cry** :  20reps\r\n**=rage** :  30reps\r\n**=grow** : 40reps\r\n**=nop** : 50reps\r\n**=lol** : 60reps\r\n**=hearts** :  70reps\r\n**=travel** : 80reps\r\n**=rain** : 90reps\r\n**=lf** :  100reps\r\n**=surf** : 120reps\r\n**=travelplane** : 140reps\r\n**=thug** : 160reps\r\n**=ghost** : 180 reps\r\n**=singe** : 200reps\r\n**=moon** : 240reps\r\n**=fight** : 280reps\r\n**=soundover** : 300reps\r\n**=hiver** : 350reps\r\n**=women** : 400reps\r\n**=timeislong** : 500reps\r\n**=sick** : 550reps\r\n**=fp** : 600reps\r\n**=nm** : 650reps\r\n**=hey** : 700reps\r\n**=pic** : 750reps\r\n\r\n**Spéciaux** \ud83d\udc51 \r\n\r\n**=topreprec** : top 5 reps\r\n**=cfez** : top 5cfs\r\n")
                .queue();
    }

    @command(name = "herodebug", type = ExecutorType.ALL, descfr = "debug le hero d'un joueur", topic = Topics.Admin)
    private void herodebug(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
                           ProfilData data, Language lang) {
        if (!(user.getId().equals("102108573298851840") || user.getId().equals("502535486691082279")
                || user.getId().equals("249987060365000704"))) {
            if (lang == Language.fr) {
                channel.sendMessage("Vous n'avez pas la permission d'executer cette action").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("You don't have the necessary permission to perform this ").queue();
            }
            return;
        }
        User cible = message.getMentionedUsers().get(0);
        HashMap<String, ArrayList<String>> hero = data.getProfils().get(cible.getId()).getHeroe();
        String activehero = data.getProfils().get(cible.getId()).getActiveHeroe();
        ArrayList<String> actualhero = hero.get(activehero);
        actualhero.set(2, "false");
        hero.put(activehero, actualhero);
        data.getProfils().get(cible.getId()).setHeroe(hero);
        channel.sendMessage("Le hero de " + cible.getName() + " a été réparé.").queue();
    }

    @command(name = "mapdebug", type = ExecutorType.ALL, descfr = "Clean la map", topic = Topics.Admin)
    private void mapdebug(Message message, User user, String[] args, MessageChannel channel, Guild guild, JDA jda,
                          ProfilData data, Language lang) {
        if (user.getId().equals("102108573298851840")) {
            int y = -10;
            int x = -10;
            for (x = -10; x <= 10; ++x) {
                for (y = -10; y <= 10; ++y) {
                    TextFileWriter.folder("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/");
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt", "", 1);

                }
            }
        } else {
            if (lang == Language.fr) {
                channel.sendMessage("Vous n'avez pas la permission d'executer cette action").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("You don't have the necessary permission to perform this ").queue();
            }
            return;
        }
        channel.sendMessage("La map a été clear.").queue();
    }

    @command(name = "Ozpass", type = ExecutorType.ALL, descfr = "Affiche votre progression dans la pass OzeryoBot", descen = " Show your progression in the OzeryoBot Pass", topic = Topics.Game)
    private void Ozpass(Message message, User user, String[] args, MessageChannel channel, Guild guild, JDA jda,
                        ProfilData data, Language lang) {
        if (Event.Summer() || user.getId().equals("102108573298851840")) {
            int xp = data.getProfils().get(user.getId()).getOzPassXp();
            int pallier = xp / 1000;
            if (pallier >= 100) {
                pallier = 100;
            }
            int digit = pallier % 10;
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("/home/DiscordBot/Rasberry/données/bot/SummerUpdate/OzPass.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Graphics2D g1 = image.createGraphics();
            g1.create();
            g1.setColor(Color.black);
            if (pallier - 2 >= 0) {
                g1.drawString("" + (pallier - 2), 40, 15);
            }
            if (pallier - 1 >= 0) {
                g1.drawString("" + (pallier - 1), 200, 15);
            }
            g1.drawString("" + pallier, 360, 15);
            if (pallier + 1 <= 100) {
                g1.drawString("" + (pallier + 1), 520, 15);
            }
            if (pallier - 2 <= 100) {
                g1.drawString("" + (pallier + 2), 680, 15);
            }
            int o = 0;
            for (int i = pallier - 2; i <= pallier + 2; ++i) {
                if (i <= 0 || i > 100) {
                    g1.setColor(Color.DARK_GRAY);
                    g1.fillRect(1 + 160 * o, 35, 157, 141);
                    g1.setColor(Color.black);
                    ++o;
                    continue;
                }
                String imagelink = "";
                if (i % 10 == 1) {
                    imagelink = "bouclier.png";
                }
                if (i % 10 == 2) {
                    imagelink = "xp.png";
                }
                if (i % 10 == 3) {
                    imagelink = "cross.png";
                }
                if (i % 10 == 4) {
                    imagelink = "res.png";
                }
                if (i % 10 == 5) {
                    imagelink = "epee.png";
                }
                if (i % 10 == 6) {
                    imagelink = "cross.png";
                }
                if (i % 10 == 7) {
                    imagelink = "interogation.png";
                }
                if (i % 10 == 8) {
                    imagelink = "jeton.png";
                }
                if (i % 10 == 9) {
                    imagelink = "cross.png";
                }
                if (i % 10 == 0) {
                    imagelink = "lootbox.png";
                }
                BufferedImage img = null;
                try {
                    img = ImageIO.read(new File("/home/DiscordBot/Rasberry/données/bot/SummerUpdate/" + imagelink));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (i <= pallier) {
                    g1.setColor(Color.GREEN);
                    g1.fillRect(1 + 160 * o, 35, 157, 141);
                    g1.setColor(Color.black);
                }
                ImageObserver observer = null;
                g1.drawImage(img, 10 + 160 * o, 50, 140 + 160 * o, 160, 0, 0, img.getHeight(), img.getWidth(),
                        observer);
                ++o;
            }
            try {
                ImageIO.write((RenderedImage) image, "png",
                        new File("/home/DiscordBot/Rasberry/données/bot/SummerUpdate/test.png"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (lang == Language.fr) {
                channel.sendMessage("``Vous avez actuellement " + (xp - pallier * 1000) + " / 1000 OzXp``")
                        .addFile(new File("/home/DiscordBot/Rasberry/données/bot/SummerUpdate/test.png")).queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("``You have actually " + (xp - pallier * 1000) + " / 1000 OzXp``")
                        .addFile(new File("/home/DiscordBot/Rasberry/données/bot/SummerUpdate/test.png")).queue();
            }
        }
    }

    @command(name = "apDebug", type = ExecutorType.ALL, descfr = "Debug des achievement points des joueurs", topic = Topics.Admin)
    private void apDebug(Message message, String[] args, MessageChannel channel, Guild guild, JDA jda, ProfilData data,
                         User user, Language lang) {
        int o;
        if (user.getId().equals("102108573298851840")) {
            o = 0;
            for (Profil profil : data.getProfils().values()) {
                int ap = 0;
                HashMap<String, Long> map = new HashMap<String, Long>();
                try {
                    map = data.getProfils().get(profil.getId()).getAchievement();
                } catch (NullPointerException e) {
                    map = new HashMap();
                    map.put("Travailleur", 0L);
                    map.put("Collecteur I", 0L);
                    map.put("Collecteur II", 0L);
                    map.put("Collecteur III", 0L);
                    map.put("Entrepreneur I", 0L);
                    map.put("Entrepreneur II", 0L);
                    map.put("Entrepreneur III", 0L);
                    map.put("Conquérant I", 0L);
                    map.put("Conquérant II", 0L);
                    map.put("Conquérant III", 0L);
                    map.put("Défenseur I", 0L);
                    map.put("Défenseur II", 0L);
                    map.put("Défenseur III", 0L);
                    map.put("Espion I", 0L);
                    map.put("Espion II", 0L);
                    map.put("Espion III", 0L);
                    map.put("Farmer I", 0L);
                    map.put("Farmer II", 0L);
                    map.put("Farmer III", 0L);
                    map.put("Instructeur", 0L);
                    map.put("Zoologue I", 0L);
                    map.put("Zoologue II", 0L);
                    map.put("Zoologue III", 0L);
                    map.put("Parieur", 0L);
                    map.put("Thx dude I", 0L);
                    map.put("Thx dude II", 0L);
                    map.put("Thx dude III", 0L);
                    map.put("Braquer I", 0L);
                    map.put("Braquer II", 0L);
                    map.put("Braquer III", 0L);
                    map.put("Crime en série", 0L);
                    map.put("Parrain de la mafia", 0L);
                    map.put("Toujours présent", 0L);
                    map.put("Toujours debout", 0L);
                    map.put("Fou des jeux", 0L);
                    map.put("Croyant", 0L);
                    map.put("L", 0L);
                    map.put("Investisseur I", 0L);
                    map.put("Investisseur II", 0L);
                    map.put("Investisseur III", 0L);
                    map.put("Mineur confirme", 0L);
                    map.put("Avant poste", 0L);
                    map.put("Pigeon", 0L);
                    map.put("Famous", 0L);
                    map.put("Scientifique", 0L);
                    map.put("Megalopole", 0L);
                    map.put("Ville touristique", 0L);
                    map.put("Magicien", 0L);
                    map.put("What a luck", 0L);
                    map.put("A good player", 0L);
                    map.put("Ville attractive", 0L);
                    map.put("Useless", 0L);
                    map.put("Impliqué", 0L);
                    map.put("Starter", 0L);
                    map.put("Grinder I", 0L);
                    map.put("Grinder II", 0L);
                    map.put("Grinder III", 0L);
                    map.put("You'r special", 0L);
                    map.put("Catch them all", 0L);
                    map.put("SwordMaster", 0L);
                    map.put("Armorer", 0L);
                    map.put("Collector I", 0L);
                    map.put("Collector II", 0L);
                    map.put("Collector III", 0L);
                    map.put("Melting", 0L);
                    try {
                        data.getProfils().get(profil.getId()).setAchievement(map);
                    } catch (NullPointerException e1) {
                        data.getProfils().put(profil.getId(), new Profil(profil.getId()));
                        data.getProfils().get(profil.getId()).setAchievement(map);
                    }
                }
                try {
                    String travailleur;
                    try {
                        travailleur = ((Long) map.get("Travailleur")).toString();
                    } catch (NullPointerException e) {
                        travailleur = "0";
                        map = data.getProfils().get(profil.getId()).getAchievement();
                    }
                    if (travailleur.equals("0")) {
                        travailleur = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String CollecteurI = ((Long) map.get("Collecteur I")).toString();
                    if (CollecteurI.equals("0")) {
                        CollecteurI = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String CollecteurII = ((Long) map.get("Collecteur II")).toString();
                    if (CollecteurII.equals("0")) {
                        CollecteurII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 3;
                    }
                    String CollecteurIII = ((Long) map.get("Collecteur III")).toString();
                    if (CollecteurIII.equals("0")) {
                        CollecteurIII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 5;
                    }
                    String EntrepreneurI = ((Long) map.get("Entrepreneur I")).toString();
                    if (EntrepreneurI.equals("0")) {
                        EntrepreneurI = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String EntrepreneurII = ((Long) map.get("Entrepreneur II")).toString();
                    if (EntrepreneurII.equals("0")) {
                        EntrepreneurII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 3;
                    }
                    String EntrepreneurIII = ((Long) map.get("Entrepreneur III")).toString();
                    if (EntrepreneurIII.equals("0")) {
                        EntrepreneurIII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 5;
                    }
                    String ConquérantI = ((Long) map.get("Conquérant I")).toString();
                    if (ConquérantI.equals("0")) {
                        ConquérantI = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String ConquérantII = ((Long) map.get("Conquérant II")).toString();
                    if (ConquérantII.equals("0")) {
                        ConquérantII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 3;
                    }
                    String ConquérantIII = ((Long) map.get("Conquérant III")).toString();
                    if (ConquérantIII.equals("0")) {
                        ConquérantIII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 5;
                    }
                    String DéfenseurI = ((Long) map.get("Défenseur I")).toString();
                    if (DéfenseurI.equals("0")) {
                        DéfenseurI = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String DéfenseurII = ((Long) map.get("Défenseur II")).toString();
                    if (DéfenseurII.equals("0")) {
                        DéfenseurII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 3;
                    }
                    String DéfenseurIII = ((Long) map.get("Défenseur III")).toString();
                    if (DéfenseurIII.equals("0")) {
                        DéfenseurIII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 5;
                    }
                    String EspionI = ((Long) map.get("Espion I")).toString();
                    if (EspionI.equals("0")) {
                        EspionI = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String EspionII = ((Long) map.get("Espion II")).toString();
                    if (EspionII.equals("0")) {
                        EspionII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 3;
                    }
                    String EspionIII = ((Long) map.get("Espion III")).toString();
                    if (EspionIII.equals("0")) {
                        EspionIII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 5;
                    }
                    String FarmerI = ((Long) map.get("Farmer I")).toString();
                    if (FarmerI.equals("0")) {
                        FarmerI = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String FarmerII = ((Long) map.get("Farmer II")).toString();
                    if (FarmerII.equals("0")) {
                        FarmerII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 3;
                    }
                    String FarmerIII = ((Long) map.get("Farmer III")).toString();
                    if (FarmerIII.equals("0")) {
                        FarmerIII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 5;
                    }
                    String Instructeur = ((Long) map.get("Instructeur")).toString();
                    if (Instructeur.equals("0")) {
                        Instructeur = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String ZoologueI = ((Long) map.get("Zoologue I")).toString();
                    if (ZoologueI.equals("0")) {
                        ZoologueI = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String ZoologueII = ((Long) map.get("Zoologue II")).toString();
                    if (ZoologueII.equals("0")) {
                        ZoologueII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 3;
                    }
                    String ZoologueIII = ((Long) map.get("Zoologue III")).toString();
                    if (ZoologueIII.equals("0")) {
                        ZoologueIII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 5;
                    }
                    String Parieur = ((Long) map.get("Parieur")).toString();
                    if (Parieur.equals("0")) {
                        Parieur = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String thxdudeI = ((Long) map.get("Thx dude I")).toString();
                    if (thxdudeI.equals("0")) {
                        thxdudeI = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String thxdudeII = ((Long) map.get("Thx dude II")).toString();
                    if (thxdudeII.equals("0")) {
                        thxdudeII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 3;
                    }
                    String thxdudeIII = ((Long) map.get("Thx dude III")).toString();
                    if (thxdudeIII.equals("0")) {
                        thxdudeIII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 5;
                    }
                    String BraquerI = ((Long) map.get("Braquer I")).toString();
                    if (BraquerI.equals("0")) {
                        BraquerI = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String BraquerII = ((Long) map.get("Braquer II")).toString();
                    if (BraquerII.equals("0")) {
                        BraquerII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 3;
                    }
                    String BraquerIII = ((Long) map.get("Braquer III")).toString();
                    if (BraquerIII.equals("0")) {
                        BraquerIII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 5;
                    }
                    String ParrainDeLaMafia = ((Long) map.get("Parrain de la mafia")).toString();
                    if (ParrainDeLaMafia.equals("0")) {
                        ParrainDeLaMafia = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String ToujoursPresent = ((Long) map.get("Toujours présent")).toString();
                    if (ToujoursPresent.equals("0")) {
                        ToujoursPresent = ":negative_squared_cross_mark:";
                    } else {
                        ap += 5;
                    }
                    String ToujoursDebout = ((Long) map.get("Toujours debout")).toString();
                    if (ToujoursDebout.equals("0")) {
                        ToujoursDebout = ":negative_squared_cross_mark:";
                    } else {
                        ap += 5;
                    }
                    String FouDesJeux = ((Long) map.get("Fou des jeux")).toString();
                    if (FouDesJeux.equals("0")) {
                        FouDesJeux = ":negative_squared_cross_mark:";
                    } else {
                        ap += 3;
                    }
                    String Croyant = ((Long) map.get("Croyant")).toString();
                    if (Croyant.equals("0")) {
                        Croyant = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String L = ((Long) map.get("L")).toString();
                    if (L.equals("0")) {
                        L = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String InvestisseurI = ((Long) map.get("Investisseur I")).toString();
                    if (InvestisseurI.equals("0")) {
                        InvestisseurI = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String InvestisseurII = ((Long) map.get("Investisseur II")).toString();
                    if (InvestisseurII.equals("0")) {
                        InvestisseurII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 3;
                    }
                    String InvestisseurIII = ((Long) map.get("Investisseur III")).toString();
                    if (InvestisseurIII.equals("0")) {
                        InvestisseurIII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 5;
                    }
                    String MineurConfirme = ((Long) map.get("Mineur confirme")).toString();
                    if (MineurConfirme.equals("0")) {
                        MineurConfirme = ":negative_squared_cross_mark:";
                    } else {
                        ap += 5;
                    }
                    String AvantPoste = ((Long) map.get("Avant poste")).toString();
                    if (AvantPoste.equals("0")) {
                        AvantPoste = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String Pigeon = ((Long) map.get("Pigeon")).toString();
                    if (Pigeon.equals("0")) {
                        Pigeon = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String Famous = ((Long) map.get("Famous")).toString();
                    if (Famous.equals("0")) {
                        Famous = ":negative_squared_cross_mark:";
                    } else {
                        ap += 3;
                    }
                    String Scientifique = ((Long) map.get("Scientifique")).toString();
                    if (Scientifique.equals("0")) {
                        Scientifique = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String Megalopole = ((Long) map.get("Megalopole")).toString();
                    if (Megalopole.equals("0")) {
                        Megalopole = ":negative_squared_cross_mark:";
                    } else {
                        ap += 5;
                    }
                    String VilleTouristique = ((Long) map.get("Ville touristique")).toString();
                    if (VilleTouristique.equals("0")) {
                        VilleTouristique = ":negative_squared_cross_mark:";
                    } else {
                        ap += 5;
                    }
                    String Magicien = ((Long) map.get("Magicien")).toString();
                    if (Magicien.equals("0")) {
                        Magicien = ":negative_squared_cross_mark:";
                    } else {
                        ap += 3;
                    }
                    String WhatALuck = ((Long) map.get("What a luck")).toString();
                    if (WhatALuck.equals("0")) {
                        WhatALuck = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String AGoodPlayer = ((Long) map.get("A good player")).toString();
                    if (AGoodPlayer.equals("0")) {
                        AGoodPlayer = ":negative_squared_cross_mark:";
                    } else {
                        ap += 5;
                    }
                    String VilleAttractive = ((Long) map.get("Ville attractive")).toString();
                    if (VilleAttractive.equals("0")) {
                        VilleAttractive = ":negative_squared_cross_mark:";
                    } else {
                        ap += 5;
                    }
                    String Useless = ((Long) map.get("Useless")).toString();
                    if (Useless.equals("0")) {
                        Useless = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String Impliqué = ((Long) map.get("Impliqué")).toString();
                    if (Impliqué.equals("0")) {
                        Impliqué = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String Starter = ((Long) map.get("Starter")).toString();
                    if (Starter.equals("0")) {
                        Starter = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String GrinderI = ((Long) map.get("Grinder I")).toString();
                    if (GrinderI.equals("0")) {
                        GrinderI = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String GrinderII = ((Long) map.get("Grinder II")).toString();
                    if (GrinderII.equals("0")) {
                        GrinderII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 3;
                    }
                    String GrinderIII = ((Long) map.get("Grinder III")).toString();
                    if (GrinderIII.equals("0")) {
                        GrinderIII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 5;
                    }
                    String Yourspecial = ((Long) map.get("You'r special")).toString();
                    if (Yourspecial.equals("0")) {
                        Yourspecial = ":negative_squared_cross_mark:";
                    } else {
                        ap += 5;
                    }
                    String Catch_them_all = ((Long) map.get("Catch them all")).toString();
                    if (Catch_them_all.equals("0")) {
                        Catch_them_all = ":negative_squared_cross_mark:";
                    } else {
                        ap += 3;
                    }
                    String SwordMaster = ((Long) map.get("SwordMaster")).toString();
                    if (SwordMaster.equals("0")) {
                        SwordMaster = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String Armorer = ((Long) map.get("Armorer")).toString();
                    if (Armorer.equals("0")) {
                        Armorer = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String CollectorI = ((Long) map.get("Collector I")).toString();
                    if (CollectorI.equals("0")) {
                        CollectorI = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                    String CollectorII = ((Long) map.get("Collector II")).toString();
                    if (CollectorII.equals("0")) {
                        CollectorII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 3;
                    }
                    String CollectorIII = ((Long) map.get("Collector III")).toString();
                    if (CollectorIII.equals("0")) {
                        CollectorIII = ":negative_squared_cross_mark:";
                    } else {
                        ap += 5;
                    }
                    String Melting = ((Long) map.get("Melting")).toString();
                    if (Melting.equals("0")) {
                        Melting = ":negative_squared_cross_mark:";
                    } else {
                        ++ap;
                    }
                } catch (NullPointerException travailleur) {
                    // empty catch block
                }
                data.getProfils().get(profil.getId()).setAp(ap);
                ++o;
            }
        } else {
            if (lang == Language.fr) {
                channel.sendMessage("Vous n'avez pas la permission d'executer cette action").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("You don't have the necessary permission to perform this ").queue();
            }
            return;
        }
        channel.sendMessage("Les ap des joueurs ont été mis a jours. (" + o + " joueurs)").queue();
    }

    @command(name = "site", type = ExecutorType.ALL, descfr = "Lien vers le site web", descen = "Website URL ", topic = Topics.Util)
    private void site(Message message, String[] args, MessageChannel channel, Guild guild, JDA jda, ProfilData data,
                      User user, Language lang) {
        if (lang == Language.fr) {
            channel.sendMessage("**Site OzeryoBot** : http://ozeryo.xyz").queue();
        }
        if (lang == Language.en) {
            channel.sendMessage("**WebSite OzeryoBot** : http://ozeryo.xyz").queue();
        }
    }

    @command(name = "debug", type = ExecutorType.ALL, descfr = "Lien vers le site web", descen = "Website URL ", topic = Topics.Admin)
    private void debug(Message message, String[] args, MessageChannel channel, Guild guild, JDA jda, ProfilData data,
                       User user, Language lang) {
        for (Profil profil : data.getProfils().values()) {
            try {
                HashMap<String, Long> achievement = profil.getAchievement();
                long h1 = 0L;
                try {
                    h1 = achievement.get("Harvester I");
                } catch (Exception exception) {
                    // empty catch block
                }
                long h2 = 0L;
                try {
                    h2 = achievement.get("Harvester II");
                } catch (Exception exception) {
                    // empty catch block
                }
                long h3 = 0L;
                try {
                    h3 = achievement.get("Harvester III");
                } catch (Exception exception) {
                    // empty catch block
                }
                achievement.remove("Harvester I");
                achievement.remove("Harvester II");
                achievement.remove("Harvester III");
                achievement.put("Collector I", h1);
                achievement.put("Collector II", h2);
                achievement.put("Collector III", h3);
                long c1 = 0L;
                try {
                    h1 = achievement.get("Collecteur I");
                } catch (Exception exception) {
                    // empty catch block
                }
                long c2 = 0L;
                try {
                    h2 = achievement.get("Collecteur II");
                } catch (Exception exception) {
                    // empty catch block
                }
                long c3 = 0L;
                try {
                    h3 = achievement.get("Collecteur III");
                } catch (Exception exception) {
                    // empty catch block
                }
                achievement.remove("Collecteur I");
                achievement.remove("Collecteur II");
                achievement.remove("Collecteur III");
                achievement.put("Harvester I", c1);
                achievement.put("Harvester II", c2);
                achievement.put("Harvester III", c3);
                data.getProfils().get(profil.getId()).setAchievement(achievement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        channel.sendMessage("Update des achievement des joueurs").queue();
    }

    @command(name = "tuto", type = ExecutorType.ALL, descfr = "Permet de vous aider a debuter le jeu", descen = "Help you to start the game", topic = Topics.Game)
    private void tuto(Message message, String[] args, MessageChannel channel, Guild guild, JDA jda, ProfilData data,
                      User user, Language lang) {
        int tuto = data.getProfils().get(user.getId()).getTuto();
        String c1 = args[0];
        if (c1.equals("confirm") && tuto == 0) {
            int i = 1;
            DiscordBot.getData().getProfils().get(user.getId()).setTuto(i);
            tuto = data.getProfils().get(user.getId()).getTuto();
            channel.sendMessage("Bien, commencez par récupérer votre récompense quotidienne avec la commande =daily.")
                    .queue();
        } else if (tuto == 0) {
            if (lang == Language.fr) {
                channel.sendMessage(
                        ":mouse_three_button: Le tutoriel va commencer, il durera environ 2 minutes, soyez sur de pouvoir le terminer ( =tuto confirm ).")
                        .queue();
            }
            if (lang == Language.en) {
                channel.sendMessage(
                        ":mouse_three_button: The tutorial will start, it will last about 2 minutes, be sure to finish it ( =tuto confirm ).")
                        .queue();
            }
        } else if (tuto == 1) {
            if (lang == Language.fr) {
                channel.sendMessage(
                        "Bien, commencez par récupérer votre récompense quotidienne avec la commande =daily.").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("Well, start by collecting your daily reward with the =daily ").queue();
            }
        } else if (tuto == 2) {
            if (lang == Language.fr) {
                channel.sendMessage("Maintenant, obtenez votre salaire horaire avec la commande =hourly.").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("Now, get your hourly reward with the =hourly ").queue();
            }
        } else if (tuto == 3) {
            if (lang == Language.fr) {
                channel.sendMessage("Vous pouvez maintenant consulter votre ville avec la commande =city.").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("You can now check your city informations with the =city ").queue();
            }
        } else if (tuto == 4) {
            if (lang == Language.fr) {
                channel.sendMessage(
                        "Vous pouvez y voir votre exp, le niveau de vos constructions et vos réserves de ressources. Le mana, affiché en haut, vous permet de récolter des ressources \u00e0 l'aide de travail, essayez tout de suite avec =work all.")
                        .queue();
            }
            if (lang == Language.en) {
                channel.sendMessage(
                        "You can see your exp, the level of your builds, and your resource reserves. The mana, shown at the top, allows you to collect resources using work command, try now with =work all.")
                        .queue();
            }
        } else if (tuto == 5) {
            if (lang == Language.fr) {
                channel.sendMessage(
                        "\u00c0 présent, vous pouvez construire votre premier b\u00e2timent ! Commen\u00e7ons par le marché comme exemple : =b marché 1.")
                        .queue();
            }
            if (lang == Language.en) {
                channel.sendMessage(
                        "Now you can build your first building! Let's start with the company as an example: = b marché 1.")
                        .queue();
            }
        } else if (tuto == 6) {
            if (lang == Language.fr) {
                channel.sendMessage(
                        "Bravo, vous avez obtenu votre premi\u00e8re construction, celle-ci permet d'augmenter vos gains de money lors de vos commandes. Vous pouvez reconsulter votre ville maintenant =city.")
                        .queue();
            }
            if (lang == Language.en) {
                channel.sendMessage(
                        "Congratulations, you got your first build, it increases your money gains during your commands. You can consult again your city now =city.")
                        .queue();
            }
        } else if (tuto == 7) {
            if (lang == Language.fr) {
                channel.sendMessage(
                        "Chaque construction poss\u00e8de une vingtaine de niveaux, vous pourrez les monter de niveaux gr\u00e2ce aux Ozecoins et aux différents matériaux. Maintenant, apprenons \u00e0 attaquer, tout d'abord construisez votre camp d'entrainement, =b camp 1.")
                        .queue();
            }
            if (lang == Language.en) {
                channel.sendMessage(
                        "Each building has about twenty levels, you can build levels with Ozecoins and different materials. Now, learn to attack, first build your barracks, = b camp 1.")
                        .queue();
            }
        } else if (tuto == 8) {
            if (lang == Language.fr) {
                channel.sendMessage(
                        "Vous pouvez maintenant entrainer des soldats, faites le avec la commande =soldier train 15.")
                        .queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("You can now train soldiers, do it with the command =soldier train 15.").queue();
            }
        } else if (tuto == 9) {
            if (lang == Language.fr) {
                channel.sendMessage(
                        "Pour lancer une attaque vous pouvez consulter la map et utiliser l'id, le tag ou les coordonnés d'un joueur. Testons maintenant : =a Ozeryo 15.")
                        .queue();
            }
            if (lang == Language.en) {
                channel.sendMessage(
                        "To launch an attack you can check the map and use a player's id, tag, or coordinates. Now, let's test: =a Ozeryo 15.")
                        .queue();
            }
        } else if (tuto == 10) {
            if (lang == Language.fr) {
                channel.sendMessage("Vous avez deja complété le tutoriel.").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("You already complete the tutorial.").queue();
            }
        } else {
            if (lang == Language.fr) {
                channel.sendMessage("Vous avez deja complété le tutoriel.").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("You already complete the tutorial.").queue();
            }
        }
    }

    @command(name = "save", type = ExecutorType.CONSOLE, descfr = "Permet de s'enregistrer pour la v3 d'OzeryoBot", descen = " Allow you to register yourself to the V3 of OzeryoBot", topic = Topics.Util)
    private void save() {
        long debut = System.currentTimeMillis();
        System.out.println("Eregistrement en cours");
        String URL2 = "/home/DiscordBot/Rasberry/données/bot";
        DiscordBot.getData().saveData(URL2);
        DiscordBot.getGuilddata().saveData(URL2);
        DiscordBot.getHypixeldata().saveData(URL2);
        DiscordBot.getLeveldata().saveData(URL2);
        long fin = System.currentTimeMillis();
        double dif = (double) (fin - debut) / 1000.0;
        System.out.println("Données enregistrés en " + dif + " secondes");
    }

    @command(name = "attackDebug", type = ExecutorType.ALL, descfr = "Permet de s'enregistrer pour la v3 d'OzeryoBot", descen = " Allow you to register yourself to the V3 of OzeryoBot", topic = Topics.Util)
    private void attackDebug(Message message, String[] args, MessageChannel channel, Guild guild, JDA jda,
                             ProfilData data, User user, Language lang) {
        if (user.getId().equals("102108573298851840")) {
            HashMap<Long, ArrayList<String>> attack = data.getProfils().get(user.getId()).getAttack();
            ArrayList<String> atk2 = null;
            Iterator<ArrayList<String>> iterator = attack.values().iterator();
            while (iterator.hasNext()) {
                ArrayList<String> atk;
                atk2 = atk = iterator.next();
            }
            atk2.set(0, "" + System.currentTimeMillis());
            attack.put(System.currentTimeMillis(), atk2);
            data.getProfils().get(user.getId()).setAttack(attack);
            channel.sendMessage("Attack debug !!").queue();
        }
    }

    @command(name = "UpdateV3", type = ExecutorType.ALL, descfr = "Permet de s'enregistrer pour la v3 d'OzeryoBot", descen = " Allow you to register yourself to the V3 of OzeryoBot", topic = Topics.Util)
    private void UpdateV3(Message message, String[] args, MessageChannel channel, Guild guild, JDA jda, ProfilData data,
                          User user, Language lang) {
        if (user.getId().equals("102108573298851840")) {
            for (Profil profil : data.getProfils().values()) {
                try {
                    long premium;
                    try {
                        TextFileWriter
                                .recursifDelete(new File("/home/DiscordBot/Rasberry/données/Users/" + profil.getId()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (!data.getProfils().get(profil.getId()).isRegister())
                        continue;
                    Profil User_Premium = profil;
                    TextFileWriter.folder("/home/DiscordBot/données/Users/" + User_Premium);
                    if (Premium.Premium(User_Premium)) {
                        premium = profil.getPremium();
                        profil.setPremium(premium += 2592000000L);
                    } else {
                        premium = System.currentTimeMillis() + 2592000000L;
                        profil.setPremium(premium);
                    }
                    ArrayList<String> list = new ArrayList<String>();
                    list.add("\ud83c\udf89 Premium");
                    list.add("You just received 1 Premium Month on OzeryoBot as pre-register reward");
                    list.add("false");
                    list.add("" + System.currentTimeMillis());
                    ArrayList<ArrayList<String>> mails = profil.getListMail();
                    mails.add(0, list);
                    profil.setListMail(mails);
                } catch (Exception User_Premium) {
                    // empty catch block
                }
            }
            channel.sendMessage("Tout les joueurs OzeryoBiot ont recu un mois de premium").queue();
        }
    }

    @command(name = "language", type = ExecutorType.ALL, descfr = "Permet de selectionner la langue du bot", descen = "Allow you to select the bot language", topic = Topics.Admin)
    private void language(Message message, String[] args, MessageChannel channel, Guild guild, JDA jda, ProfilData data,
                          User user, Language lang) {
        String c1;
        block9:
        {
            c1 = "";
            try {
                c1 = args[0];
            } catch (Exception e) {
                if (lang == Language.fr) {
                    channel.sendMessage("``Syntaxe :`` =language [fr/en].").queue();
                }
                if (lang != Language.en)
                    break block9;
                channel.sendMessage("``Syntax :`` =language [fr/en].").queue();
            }
        }
        if (c1.equals("fr")) {
            channel.sendMessage("Le langage du bot est désormais réglé sur : Français").queue();
            data.getProfils().get(user.getId()).setLanguage(Language.fr);
        } else if (c1.equals("en")) {
            channel.sendMessage("the bot's language is now set on : English").queue();
            data.getProfils().get(user.getId()).setLanguage(Language.en);
        } else {
            if (lang == Language.fr) {
                channel.sendMessage("``Syntaxe :`` =language [fr/en].").queue();
            }
            if (lang == Language.en) {
                channel.sendMessage("``Syntax :`` =language [fr/en].").queue();
            }
        }
    }


}
