/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Level;

import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Level.Level;
import fr.Ybsi.OzeryoBot.Utils.GuildProfil;
import fr.Ybsi.OzeryoBot.Utils.LevelProfil;
import java.io.PrintStream;
import java.util.HashMap;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class EXP implements EventListener {
    @Override
    public void onEvent(Event event) {
        if (event instanceof MessageReceivedEvent) {
            this.EXP((MessageReceivedEvent) event);
        }
    }

    public void EXP(MessageReceivedEvent event) {
        long lastmessage;
        if (event.getMessage().getContentRaw().startsWith("=")) {
            return;
        }
        try {
            lastmessage = DiscordBot.getLeveldata().getLevelProfil().get(event.getAuthor().getId()).getLastmessage();
        } catch (NullPointerException e) {
            DiscordBot.getLeveldata().getLevelProfil().put(event.getAuthor().getId(),
                    new LevelProfil(event.getAuthor().getId()));
            lastmessage = 0L;
        }
        long dif = System.currentTimeMillis() - lastmessage;
        if (dif >= 60000L) {
            DiscordBot.getLeveldata().getLevelProfil().get(event.getAuthor().getId())
                    .setLastmessage(System.currentTimeMillis());
            if (event.getAuthor().isBot()) {
                return;
            }
            int xp = 0;
            try {
                xp = DiscordBot.getGuilddata().getGuildProfil().get(event.getGuild().getId()).getXp();
            } catch (Exception exception) {
                // empty catch block
            }
            int alea = 1 + (int) (Math.random() * 5.0);
            DiscordBot.getGuilddata().getGuildProfil().get(event.getGuild().getId()).setXp(xp += alea);
            if (event.getAuthor().isBot()) {
                return;
            }
            xp = 0;
            try {
                xp = DiscordBot.getLeveldata().getLevelProfil().get(event.getAuthor().getId()).getXp();
            } catch (NullPointerException e) {
                DiscordBot.getLeveldata().getLevelProfil().put(event.getAuthor().getId(),
                        new LevelProfil(event.getAuthor().getId()));
            }
            alea = 1 + (int) (Math.random() * 5.0);
            DiscordBot.getLeveldata().getLevelProfil().get(event.getAuthor().getId()).setXp(xp += alea);
        }
    }

    public static int LevelUp(User user, int lvl) {
        int level = Level.level(user.getId());
        int EXP2 = DiscordBot.getLeveldata().getLevelProfil().get(user.getId()).getXp();
        int level_test = 0;
        int EXP_test = 0;
        int EXP_Test2 = 0;
        EXP_test = 0;
        while (EXP_test <= EXP2) {
            EXP_Test2 = EXP_test;
            EXP_test = EXP_Test2 + (++level_test + 1) * 50;
        }
        if (lvl == -1) {
            EXP_test -= (level_test + 1) * 50;
        }
        if (level == 0) {
            EXP_test = 100;
        }
        if (level == 0 && lvl == -1) {
            EXP_test = 0;
        }
        int EXPUp = EXP_test;
        return EXPUp;
    }

    public static int LevelUp2(User user) {
        int level = Level.level(user.getId());
        System.out.println(level);
        System.out.println(level);
        int level_test = level;
        int EXP_test = 0;
        EXP_test = (level_test + 1) * 50;
        int EXPUp = EXP_test + 50;
        if (level == 0) {
            EXPUp = 100;
        }
        return EXPUp;
    }

    public static int GLevelUp2(Guild guild) {
        int level = Level.Glevel(guild.getId());
        int level_test = level + 1;
        int EXP_test = 0;
        int EXPUp = EXP_test = (level_test + 1) * 500;
        return EXPUp;
    }

    public static int GLevelUp(Guild guild, int lvl) {
        int level = Level.Glevel(guild.getId());
        level += lvl;
        int EXP2 = DiscordBot.getGuilddata().getGuildProfil().get(guild.getId()).getXp();
        int level_test = 0;
        int EXP_test = 0;
        int EXP_Test2 = 0;
        EXP_test = 0;
        while (EXP_test <= EXP2) {
            EXP_Test2 = EXP_test;
            EXP_test = EXP_Test2 + (++level_test + 1) * 500;
        }
        if (lvl == -1) {
            EXP_test -= (level_test + 1) * 500;
        }
        if (level == 0) {
            EXP_test = 1000;
        }
        int EXPUp = EXP_test;
        return EXPUp;
    }
}
