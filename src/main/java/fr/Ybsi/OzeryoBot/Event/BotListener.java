/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Event;

import fr.Ybsi.OzeryoBot.Commands.CommandMap;
import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Music.MusicCommands;
import fr.Ybsi.OzeryoBot.Utils.GuildProfil;
import fr.Ybsi.OzeryoBot.Utils.GuildProfilData;
import fr.Ybsi.OzeryoBot.Utils.Scheduler;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class BotListener implements EventListener {
    public static int messages = 0;
    private final CommandMap commandMap;

    public BotListener(CommandMap commandMap) {
        this.commandMap = commandMap;
    }

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof MessageReceivedEvent) {
            this.onMessage((MessageReceivedEvent) event);
        } else if (event instanceof GuildMemberJoinEvent) {
            this.onGuildMemberJoin((GuildMemberJoinEvent) event);
        } else if (event instanceof GuildMemberLeaveEvent) {
            this.onGuildMemberLeave((GuildMemberLeaveEvent) event);
        } else if (event instanceof GuildVoiceJoinEvent) {
            this.onUserJoinVoiceChannel((GuildVoiceJoinEvent) event);
        } else if (event instanceof GuildVoiceLeaveEvent) {
            this.onUserLeaveVoiceChannel((GuildVoiceLeaveEvent) event);
        } else if (event instanceof GuildVoiceMoveEvent) {
            this.onUserMoveVoiceChannel((GuildVoiceMoveEvent) event);
        } else if (event instanceof UserUpdateOnlineStatusEvent) {
            this.Onconection((UserUpdateOnlineStatusEvent) event);
        }
    }

    private void onUserMoveVoiceChannel(GuildVoiceMoveEvent event) {
        try {
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + event.getMember().getUser().getId());
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild/" + event.getGuild().getId());
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        if (event.getChannelLeft().getName().equals(TextFileWriter
                .read("/home/DiscordBot/Rasberry/données/Guild/" + event.getGuild().getId() + "/Vmusique.txt"))) {
            List<Member> members = event.getChannelLeft().getMembers();
            int connectes = 0;
            for (Member member : members) {
                if (member.getUser().isBot())
                    continue;
                ++connectes;
            }
            if (connectes == 0) {
                MusicCommands.MusicStop(event.getGuild());
            }
        }
        if (event.getChannelJoined().getName().equals(TextFileWriter
                .read("/home/DiscordBot/Rasberry/données/Guild/" + event.getGuild().getId() + "/Vmusique.txt"))) {
            Boolean musique = true;
            for (Member member : event.getChannelJoined().getMembers()) {
                if (!member.getUser().isBot())
                    continue;
                musique = false;
            }
            if (musique.booleanValue()) {
                MusicCommands.Gplaylist(event.getChannelJoined(), event.getGuild());
            }
        }
    }

    private void onUserLeaveVoiceChannel(GuildVoiceLeaveEvent event) {
        try {
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + event.getMember().getUser().getId());
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild/" + event.getGuild().getId());
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        List<Member> members = event.getChannelLeft().getMembers();
        int connectes = 0;
        for (Member member : members) {
            if (member.getUser().isBot())
                continue;
            ++connectes;
        }
        if (connectes == 0) {
            MusicCommands.MusicStop(event.getGuild());
        }
    }

    private void onMessage(MessageReceivedEvent event) {
        String message;
        command.Language lang;
        ++messages;
        try {
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + event.getMember().getUser().getId());
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild/" + event.getGuild().getId());
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        GuildProfilData data = DiscordBot.getGuilddata();
        try {
            String string = data.getGuildProfil().get(event.getGuild().getId()).getId();
        } catch (NullPointerException e) {
            data.getGuildProfil().put(event.getGuild().getId(),
                    new GuildProfil(event.getGuild().getId(), event.getGuild().getName()));
            CommandMap.PublicLog(
                    "\ud83d\udfe2 OzeryoBot a été ajouté sur le serveur " + event.getGuild().getName() + ".",
                    event.getJDA());
        }
        if (data.getGuildProfil().get(event.getGuild().getId()).getName().equals("")) {
            data.getGuildProfil().get(event.getGuild().getId()).setName(event.getGuild().getName());
        }
        if (event.getAuthor().getId().equals("399115724926484490")) {
            if (event.getMessage().getContentDisplay().contains("Fortnite Stat's")) {
                Scheduler.Message_Delete(3, TimeUnit.SECONDS, event.getMessage());
            } else if (event.getMessage().getContentDisplay().equals(":x: CoolDown !! :x:")) {
                Scheduler.Message_Delete(3, TimeUnit.SECONDS, event.getMessage());
            }
        }
        int minutes = Integer.parseInt(new SimpleDateFormat("mm", Locale.FRANCE).format(new Date()));
        int heures = Integer.parseInt(new SimpleDateFormat("HH", Locale.FRANCE).format(new Date()));
        int jours = Integer.parseInt(new SimpleDateFormat("dd", Locale.FRANCE).format(new Date()));
        int AutoAFK = Integer.parseInt(TextFileWriter
                .read("/home/DiscordBot/Rasberry/données/Users/" + event.getAuthor().getId() + "/AutoAFK.txt"));
        if (AutoAFK != 0) {
            TextFileWriter.folder(
                    "/home/DiscordBot/Rasberry/données/Users/" + event.getAuthor().getId() + "/AutoAFK/minutes.txt");
            TextFileWriter.delete(
                    "/home/DiscordBot/Rasberry/données/Users/" + event.getAuthor().getId() + "/AutoAFK/heures.txt");
            TextFileWriter.write(
                    "/home/DiscordBot/Rasberry/données/Users/" + event.getAuthor().getId() + "/AutoAFK/heures.txt",
                    Integer.toString(heures), 1);
            TextFileWriter.delete(
                    "/home/DiscordBot/Rasberry/données/Users/" + event.getAuthor().getId() + "/AutoAFK/jours.txt");
            TextFileWriter.write(
                    "/home/DiscordBot/Rasberry/données/Users/" + event.getAuthor().getId() + "/AutoAFK/jours.txt",
                    Integer.toString(jours), 1);
            TextFileWriter.delete(
                    "/home/DiscordBot/Rasberry/données/Users/" + event.getAuthor().getId() + "/AutoAFK/minutes.txt");
            TextFileWriter.write(
                    "/home/DiscordBot/Rasberry/données/Users/" + event.getAuthor().getId() + "/AutoAFK/minutes.txt",
                    Integer.toString(minutes), 1);
        }
        try {
            lang = DiscordBot.getData().getProfils().get(event.getAuthor().getId()).getLanguage();
        } catch (Exception e) {
            lang = command.Language.en;
        }
        if (event.getMessage().getMentionedUsers().size() >= 1) {
            for (int i = 0; i < event.getMessage().getMentionedUsers().size(); ++i) {
                User user = event.getMessage().getMentionedUsers().get(i);
                AutoAFK = Integer.parseInt(TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/AutoAFK.txt"));
                if (TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/AFK.txt")
                        .equals("true")) {
                    if (lang == command.Language.fr) {
                        event.getChannel().sendMessage("Désolé mais la personne que vous tenter de mentionnez est AFK")
                                .queue();
                    }
                    if (lang != command.Language.en)
                        continue;
                    event.getChannel().sendMessage("Sorry but the person you want to mention is AFK").queue();
                    continue;
                }
                if (AutoAFK == 0)
                    continue;
                String message_minutes = TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/AutoAFK/minutes.txt");
                String message_heures = TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/AutoAFK/heures.txt");
                String message_jours = TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/AutoAFK/jours.txt");
                if (message_minutes == null) {
                    message_minutes = new SimpleDateFormat("mm", Locale.FRANCE).format(new Date());
                }
                if (message_heures == null) {
                    message_heures = new SimpleDateFormat("HH", Locale.FRANCE).format(new Date());
                }
                if (message_jours == null) {
                    message_jours = new SimpleDateFormat("dd", Locale.FRANCE).format(new Date());
                }
                int Nminutes = minutes - Integer.parseInt(message_minutes);
                int Nheures = heures - Integer.parseInt(message_heures);
                int Njours = jours - Integer.parseInt(message_jours);
                if (Nminutes < 0) {
                    Nminutes += 60;
                    --Nheures;
                }
                if (Nheures < 0) {
                    Nheures += 24;
                    --Njours;
                }
                Nminutes += Nheures * 60;
                if ((Nminutes += Njours * 1440) < AutoAFK || AutoAFK == 0)
                    continue;
                if (lang == command.Language.fr) {
                    event.getChannel().sendMessage("Désolé mais la personne que vous tenter de mentionnez est AFK")
                            .queue();
                }
                if (lang != command.Language.en)
                    continue;
                event.getChannel().sendMessage("Sorry but the person you want to mention is AFK").queue();
            }
        }
        if (TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + event.getAuthor().getId() + "/AFK.txt")
                .equals("true")) {
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + event.getAuthor().getId() + "/AFK.txt",
                    "false", 1);
            if (lang == command.Language.fr) {
                event.getChannel().sendMessage("Vous n'etes plus AFK").queue();
            }
            if (lang == command.Language.fr) {
                event.getChannel().sendMessage("You are no longer AFK").queue();
            }
        }
        if (event.getAuthor().isBot()) {
            return;
        }
        if (event.getMessage().getContentDisplay().isEmpty()) {
            return;
        }
        if (event.getMessage().getContentDisplay()
                .startsWith(this.commandMap.getPrefix(event.getGuild()) + "say")
                || event.getMessage().getContentDisplay()
                .startsWith(this.commandMap.getTag() + "say")) {
            event.getMessage().delete().queue();
        }
        if ((message = event.getMessage().getContentRaw()).startsWith(event.getJDA().getSelfUser().getAsMention())) {
            message = message.replaceFirst(event.getJDA().getSelfUser().getAsMention() + " ", "");
            CommandMap.commandUser(event.getAuthor(), message, event.getMessage(), event.getChannel(),
                    event.getGuild(), event.getJDA());
        }
        if (message.startsWith(this.commandMap.getPrefix(event.getGuild()))) {
            message = message.replaceFirst(this.commandMap.getPrefix(event.getGuild()), "");
            CommandMap.commandUser(event.getAuthor(), message, event.getMessage(), event.getChannel(),
                    event.getGuild(), event.getJDA());
        }
        if (message.toLowerCase().startsWith(this.commandMap.getTag2())) {
            message = message.toLowerCase().replaceFirst(this.commandMap.getTag2(), "");
            CommandMap.commandUser(event.getAuthor(), message, event.getMessage(), event.getChannel(),
                    event.getGuild(), event.getJDA());
        }
        if (!event.getChannel().getId().equals("541620777087402004")) {
            return;
        }
        event.getMessage().addReaction("\u274c").queue();
        event.getMessage().addReaction("\u2705").queue();
    }

    private void onGuildMemberJoin(GuildMemberJoinEvent event) {
        try {
            if (!event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                return;
            }
            if (event.getUser().isBot()) {
                return;
            }
            if (!DiscordBot.getGuilddata().getGuildProfil().get(event.getGuild().getId()).getGeneral().equals("")) {
                String mess = DiscordBot.getGuilddata().getGuildProfil().get(event.getGuild().getId())
                        .getWelcomeMessage();
                mess = mess.replace("{players}", String.valueOf(event.getGuild().getMembers().size()));
                mess = mess.replace("{guild}", event.getGuild().getName());
                mess = mess.replace("{user}", event.getUser().getName());
                EmbedBuilder builder = new EmbedBuilder();
                builder.setTitle("Bienvenue  **" + event.getUser().getName() + "** sur " + event.getGuild().getName());
                builder.setThumbnail(event.getUser().getAvatarUrl());
                builder.setColor(Color.green);
                builder.setFooter(event.getGuild().getName(), event.getGuild().getIconUrl());
                builder.setDescription(mess);
                event.getGuild()
                        .getTextChannelById(
                                DiscordBot.getGuilddata().getGuildProfil().get(event.getGuild().getId()).getGeneral())
                        .sendMessage(builder.build()).queue();
            }
        } catch (NullPointerException mess) {
            // empty catch block
        }
    }

    private void onGuildMemberLeave(GuildMemberLeaveEvent event) {
        try {
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + event.getMember().getUser().getId());
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild/" + event.getGuild().getId());
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        try {
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + event.getUser().getId() + "/");
            if (!event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                return;
            }
            if (event.getMember().getUser().isBot()) {
                return;
            }
            if (TextFileWriter.read(
                    "/home/DiscordBot/Rasberry/données/Guild/" + event.getGuild().getId() + "/general.txt") != null) {
                EmbedBuilder builder = new EmbedBuilder();
                builder.setTitle("Au revoir **" + event.getUser().getName() + "** de " + event.getGuild().getName());
                builder.setThumbnail(event.getUser().getAvatarUrl());
                builder.setColor(Color.green);
                builder.setFooter(event.getGuild().getName(), event.getGuild().getIconUrl());
                builder.setDescription("\ud83d\udc4b\ud83d\udc4b Tu vas manquer au "
                        + event.getGuild().getMembers().size() + " users du serveur. \ud83d\udc4b\ud83d\udc4b");
                event.getGuild()
                        .getTextChannelById(TextFileWriter.read(
                                "/home/DiscordBot/Rasberry/données/Guild/" + event.getGuild().getId() + "/general.txt"))
                        .sendMessage(builder.build()).queue();
            }
        } catch (NullPointerException builder) {
            // empty catch block
        }
    }

    private void onUserJoinVoiceChannel(GuildVoiceJoinEvent event) {
        try {
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + event.getMember().getUser().getId());
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild/" + event.getGuild().getId());
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        if (event.getChannelJoined().getName().equals(TextFileWriter
                .read("/home/DiscordBot/Rasberry/données/Guild/" + event.getGuild().getId() + "/Vmusique.txt"))) {
            Boolean musique = true;
            for (Member member : event.getChannelJoined().getMembers()) {
                if (!member.getUser().isBot())
                    continue;
                musique = false;
            }
            if (musique.booleanValue()) {
                MusicCommands.Gplaylist(event.getChannelJoined(), event.getGuild());
            }
        }
    }

    private void Onconection(UserUpdateOnlineStatusEvent event) {
        try {
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + event.getMember().getUser().getId());
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild/" + event.getGuild().getId());
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

    /*private void Ongame(UserUpdateGameEvent event) {
        try {
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + event.getMember().getUser().getId());
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild/" + event.getGuild().getId());
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        if (event.getGuild().getId().equals("326345972739473410")) {
            String jeu;
            try {
                jeu = event.getNewGame().toString();
            } catch (NullPointerException e) {
                jeu = "";
            }
            if (jeu.contains("Minecraft")) {
                List<Role> role = event.getGuild().getRolesByName("Minecraft", true);
                event.getGuild().getController().addRolesToMember(event.getMember(), role).queue();
            } else if (jeu.contains("Fortnite")) {
                List<Role> role1 = event.getGuild().getRolesByName("Fortnite", true);
                event.getGuild().getController().addRolesToMember(event.getMember(), role1).queue();
            } else if (jeu.contains("Overwatch")) {
                List<Role> role2 = event.getGuild().getRolesByName("Overwatch", true);
                event.getGuild().getController().addRolesToMember(event.getMember(), role2).queue();
            } else if (jeu.contains("Destiny 2")) {
                List<Role> role2 = event.getGuild().getRolesByName("Destiny 2", true);
                event.getGuild().getController().addRolesToMember(event.getMember(), role2).queue();
            } else if (jeu.contains("League Of Legends")) {
                List<Role> role2 = event.getGuild().getRolesByName("League Of Legends", true);
                event.getGuild().getController().addRolesToMember(event.getMember(), role2).queue();
            }
        }
    }*/

}
