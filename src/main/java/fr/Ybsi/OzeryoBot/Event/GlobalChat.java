/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Event;

import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Level.Level;
import fr.Ybsi.OzeryoBot.Utils.Premium;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import fr.Ybsi.OzeryoBot.Utils.color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import java.time.Instant;
import java.util.List;

public class GlobalChat implements EventListener {
    @Override
    public void onEvent(GenericEvent event) {
        if (event instanceof MessageReceivedEvent) {
            this.onMessage((MessageReceivedEvent) event);
        }
    }

    private void onMessage(MessageReceivedEvent event) {
        try {
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + event.getMember().getUser().getId());
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild/" + event.getGuild().getId());
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        if (event.getAuthor().isBot()) {
            return;
        }
        String globalChat = TextFileWriter
                .read("/home/DiscordBot/Rasberry/données/Guild/" + event.getGuild().getId() + "/GlobalChat.txt");
        String channel = event.getChannel().getId();
        if (channel.equals(globalChat)) {
            if (event.getMessage().getContentRaw().contains("https://")
                    || event.getMessage().getContentRaw().contains("http://")) {
                event.getMessage().delete().queue();
                return;
            }
            String message = event.getMessage().getContentRaw();
            Guild guild1 = event.getGuild();
            List<Message.Attachment> fichier = event.getMessage().getAttachments();
            int Guilds2 = DiscordBot.getjda().getGuilds().size();
            for (int i = 0; i < Guilds2; ++i) {
                try {
                    int Levels;
                    Guild guild = DiscordBot.getjda().getGuilds().get(i);
                    String ChannelId2 = TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/GlobalChat.txt");
                    String content = message;
                    String Role2 = "LvL" + Level.level(event.getAuthor().getId());
                    if (Premium.Premium(DiscordBot.getData().getProfils().get(event.getAuthor().getId())) && !TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/Users/" + event.getAuthor().getId() + "/grade.txt")
                            .equals("0")) {
                        Role2 = TextFileWriter.read(
                                "/home/DiscordBot/Rasberry/données/Users/" + event.getAuthor().getId() + "/grade.txt");
                    }
                    try {
                        Levels = Level.Glevel(guild1.getId());
                    } catch (NumberFormatException e) {
                        Levels = 0;
                    }
                    EmbedBuilder builder = new EmbedBuilder();
                    builder.setTitle("[" + Role2 + "] " + event.getAuthor().getName());
                    builder.setThumbnail(event.getAuthor().getAvatarUrl());
                    builder.setDescription(content);
                    if (fichier.size() > 0) {
                        builder.setImage(fichier.get(0).getUrl());
                    }
                    builder.setFooter(event.getGuild().getName() + " | Level : " + Levels,
                            event.getGuild().getIconUrl());
                    builder.setTimestamp(Instant.now());
                    builder.setColor(color.couleurAleatoire(event.getAuthor()));
                    guild.getTextChannelById(ChannelId2).sendMessage(builder.build()).queue();
                    continue;
                } catch (NullPointerException guild) {
                    // empty catch block
                }
            }
        } else {
            return;
        }
        event.getMessage().delete().queue();
    }
}
