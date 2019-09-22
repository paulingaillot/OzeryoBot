/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Level;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Level.EXP;
import fr.Ybsi.OzeryoBot.Level.Level;
import fr.Ybsi.OzeryoBot.Utils.GuildProfil;
import fr.Ybsi.OzeryoBot.Utils.LevelProfil;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import java.util.HashMap;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.PermissionException;
import net.dv8tion.jda.core.hooks.EventListener;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class levelup implements EventListener {
    @Override
    public void onEvent(Event event) {
        try {
            if (event instanceof MessageReceivedEvent) {
                this.levelup((MessageReceivedEvent) event);
            }
        } catch (PermissionException permissionException) {
            // empty catch block
        }
    }

    public void levelup(MessageReceivedEvent event) {
        try {
            command.Language lang;
            int EXP1;
            try {
                lang = DiscordBot.getData().getProfils().get(event.getAuthor().getId()).getLanguage();
            } catch (Exception e) {
                lang = command.Language.en;
            }
            if (DiscordBot.getGuilddata().getGuildProfil().get(event.getGuild().getId()).isSilence()) {
                return;
            }
            if (event.getAuthor().isBot()) {
                return;
            }
            if (!event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                return;
            }
            int level = Level.level(event.getAuthor().getId());
            if (level == 0) {
                return;
            }
            int levelUp = EXP.LevelUp(event.getAuthor(), -1);
            try {
                EXP1 = DiscordBot.getLeveldata().getLevelProfil().get(event.getAuthor().getId()).getXp();
            } catch (NullPointerException e) {
                EXP1 = 0;
            }
            if (EXP1 >= levelUp && EXP1 <= levelUp + 4) {
                if (lang == command.Language.fr) {
                    event.getChannel().sendMessage(":up: Level Up !!!:up: Tu es désormais au niveau " + level).queue();
                }
                if (lang == command.Language.en) {
                    event.getChannel().sendMessage(":up: Level Up !!!:up: You are now level " + level).queue();
                }
                DiscordBot.getLeveldata().getLevelProfil().get(event.getAuthor().getId()).setXp(EXP1 + 5);
            }
            int GEXP = DiscordBot.getGuilddata().getGuildProfil().get(event.getGuild().getId()).getXp();
            int Glevel = Level.Glevel(event.getGuild().getId());
            if (Glevel == 0) {
                return;
            }
            int GlevelUp = EXP.GLevelUp(event.getGuild(), -1);
            if (DiscordBot.getGuilddata().getGuildProfil().get(event.getGuild().getId()).isSilence()) {
                return;
            }
            if (event.getAuthor().isBot()) {
                return;
            }
            if (!event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                return;
            }
            try {
                if (GEXP >= GlevelUp && GEXP <= GlevelUp + 4) {
                    if (lang == command.Language.fr) {
                        event.getChannel()
                                .sendMessage(":up: Level Up !!!:up: Votre guilde est désormais au niveau " + Glevel)
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        event.getChannel().sendMessage(":up: Level Up !!!:up: Your Guild is now level " + Glevel)
                                .queue();
                    }
                    DiscordBot.getGuilddata().getGuildProfil().get(event.getGuild().getId()).setXp(EXP1 + 5);
                }
            } catch (NumberFormatException numberFormatException) {
            }
        } catch (NullPointerException lang) {
        } catch (NumberFormatException lang) {
            // empty catch block
        }
    }
}
