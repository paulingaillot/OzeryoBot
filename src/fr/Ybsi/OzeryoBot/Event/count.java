/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Event;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import net.dv8tion.jda.core.managers.ChannelManager;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class count implements EventListener {
    @Override
    public void onEvent(Event event) {
        if (event instanceof MessageReceivedEvent) {
            this.onMessage((MessageReceivedEvent) event);
        }
    }

    private void onMessage(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        Guild guild = event.getGuild();
        MessageChannel channel = event.getChannel();
        String ChannelCible = TextFileWriter
                .read("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/count.txt");
        String lastUser = TextFileWriter
                .read("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/countuser.txt");
        if (channel.getId().equals(ChannelCible)) {
            int counter;
            command.Language lang;
            try {
                lang = DiscordBot.getData().getProfils().get(event.getAuthor().getId()).getLanguage();
            } catch (Exception e) {
                lang = command.Language.en;
            }
            String message = event.getMessage().getContentRaw();
            try {
                counter = Integer.parseInt(TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/counter.txt"));
            } catch (NumberFormatException e) {
                counter = 0;
            }
            String[] args = message.split(" ");
            int messageNumber = 0;
            try {
                messageNumber = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                if (args[0].equals("stop") || args[0].equals("remove") || args[0].equals("off")) {
                    TextFileWriter.delete("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/count.txt");
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/counter.txt",
                            "0", 1);
                    TextFileWriter
                            .delete("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/countuser.txt");
                    return;
                }
                if (lang == command.Language.fr) {
                    channel.sendMessage("Le combo a été reinitialisé ...").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("The combo has been reset ...").queue();
                }
                channel.sendMessage("0").queue();
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/counter.txt", "0",
                        1);
                return;
            }
            if (messageNumber == counter + 1 && !event.getAuthor().getId().equals(lastUser)) {
                String premier;
                int userPoint;
                String deuxieme;
                File[] files;
                String troisieme;
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/counter.txt",
                        Integer.toString(messageNumber), 1);
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/countuser.txt",
                        event.getAuthor().getId(), 1);
                try {
                    userPoint = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/Guild/"
                            + guild.getId() + "/count/" + event.getAuthor().getId()));
                } catch (NumberFormatException e) {
                    userPoint = 0;
                }
                TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/count");
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/count/"
                        + event.getAuthor().getId(), Integer.toString(++userPoint), 1);
                HashMap<String, Integer> classement = new HashMap<String, Integer>();
                for (File file : files = TextFileWriter
                        .folderlist("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/count/")) {
                    String member;
                    try {
                        member = DiscordBot.getjda().getUserById(file.getName()).getName();
                    } catch (NullPointerException e) {
                        if (lang == command.Language.fr) {
                            member = "inconnu";
                        }
                        member = lang == command.Language.en ? "unkonwn" : "unknown";
                    }
                    int EXP2 = Integer.parseInt(TextFileWriter.read(file.getAbsolutePath()));
                    classement.put(member, EXP2);
                }
                ArrayList entries = new ArrayList(classement.entrySet());
                Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

                    @Override
                    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                        return e1.getValue().compareTo(e2.getValue());
                    }
                });
                try {
                    premier = (String) ((Map.Entry) entries.get(entries.size() - 1)).getKey();
                } catch (ArrayIndexOutOfBoundsException e) {
                    premier = "inconnu";
                }
                try {
                    deuxieme = (String) ((Map.Entry) entries.get(entries.size() - 2)).getKey();
                } catch (ArrayIndexOutOfBoundsException e) {
                    deuxieme = "inconnu";
                }
                try {
                    troisieme = (String) ((Map.Entry) entries.get(entries.size() - 3)).getKey();
                } catch (ArrayIndexOutOfBoundsException e) {
                    troisieme = "inconnu";
                }
                ChannelManager channelm = new ChannelManager(guild.getTextChannelById(channel.getId()));
                if (lang == command.Language.fr) {
                    channelm.setTopic("Total de points : " + messageNumber + " |  1er : " + premier + ", 2e : "
                            + deuxieme + ", 3e : " + troisieme).queue();
                }
                if (lang == command.Language.en) {
                    channelm.setTopic("Total points : " + messageNumber + " |  1st : " + premier + ", 2nd : " + deuxieme
                            + ", 3rd : " + troisieme).queue();
                }
                return;
            }
            if (lang == command.Language.fr) {
                channel.sendMessage("Le combo a été reinitialisé ...").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("The combo has been reset ...").queue();
            }
            channel.sendMessage("0").queue();
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/counter.txt", "0", 1);
            return;
        }
    }

}
