/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Event;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import java.util.HashMap;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.SelfUser;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import net.dv8tion.jda.core.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class BadWords
implements EventListener {
    int tg = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Tg.txt"));
    int ntm = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Ntm.txt"));
    int fdp = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Fdp.txt"));
    int bruh = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Bruh.txt"));
    int ez = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Ez.txt"));
    int salope = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Salope.txt"));
    int pute = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Pute.txt"));
    int condor = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Condor.txt"));
    int suce = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Suce.txt"));
    int blc = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Blc.txt"));
    int ptn = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Ptn.txt"));
    int pd = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Pd.txt"));

    @Override
    public void onEvent(Event event) {
        if (event instanceof MessageReceivedEvent) {
            this.onMessage((MessageReceivedEvent)event);
        }
    }

    private void onMessage(MessageReceivedEvent event) {
        command.Language lang;
        try {
            TextFileWriter.folder("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + event.getMember().getUser().getId());
            TextFileWriter.folder("/home/DiscordBot/Rasberry/donn\u00e9es/Guild/" + event.getGuild().getId());
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        if (event.getMessage().getContentDisplay().contains("Surveille") && event.getAuthor().equals(event.getJDA().getSelfUser())) {
            try {
                Thread.sleep(3000L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            event.getMessage().delete().queue();
        }
        try {
            lang = DiscordBot.getData().getProfils().get(event.getAuthor().getId()).getLanguage();
        }
        catch (Exception e) {
            lang = command.Language.en;
        }
        if (event.getAuthor().equals(event.getJDA().getSelfUser())) {
            return;
        }
        if (TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/Guild/" + event.getGuild().getId() + "/setBadWords.txt").equals("true")) {
            if (event.getMessage().getContentDisplay().toLowerCase().contains(" blc") || event.getMessage().getContentDisplay().toLowerCase().startsWith("blc")) {
                ++this.blc;
                TextFileWriter.write("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Blc.txt", Integer.toString(this.blc), 1);
                if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                    if (lang == command.Language.fr) {
                        event.getChannel().sendMessage(" Surveille ton langage !! " + event.getAuthor().getAsMention() + "\n j'ai effac\u00e9 " + this.blc + " blc").queue();
                    }
                    if (lang == command.Language.en) {
                        event.getChannel().sendMessage(" Watch your words !! " + event.getAuthor().getAsMention() + "\n I deleted " + this.blc + " blc").queue();
                    }
                }
                event.getMessage().delete().queue();
            }
            if (event.getMessage().getContentDisplay().toLowerCase().contains(" tg") || event.getMessage().getContentDisplay().toLowerCase().startsWith("tg")) {
                TextFileWriter.write("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Tg.txt", Integer.toString(this.tg), 1);
                ++this.tg;
                if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                    if (lang == command.Language.fr) {
                        event.getChannel().sendMessage(" Surveille ton langage !! " + event.getAuthor().getAsMention() + "\n j'ai effac\u00e9 " + this.tg + " tg").queue();
                    }
                    if (lang == command.Language.en) {
                        event.getChannel().sendMessage(" Watch your words !! " + event.getAuthor().getAsMention() + "\n I deleted " + this.tg + " tg").queue();
                    }
                }
                event.getMessage().delete().queue();
            }
            if (event.getMessage().getContentDisplay().toLowerCase().contains(" ntm") || event.getMessage().getContentDisplay().toLowerCase().startsWith("ntm")) {
                TextFileWriter.write("/home/DiscordBot/Rasberry/donn\u00e9es//BadWords/Ntm.txt", Integer.toString(this.ntm), 1);
                ++this.ntm;
                if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                    if (lang == command.Language.fr) {
                        event.getChannel().sendMessage(" Surveille ton langage !! " + event.getAuthor().getAsMention() + "\n j'ai effac\u00e9 " + this.ntm + " ntm").queue();
                    }
                    if (lang == command.Language.en) {
                        event.getChannel().sendMessage(" Watch your words !! " + event.getAuthor().getAsMention() + "\n I deleted " + this.ntm + " ntm").queue();
                    }
                }
                event.getMessage().delete().queue();
            }
            if (event.getMessage().getContentDisplay().toLowerCase().contains(" fdp") || event.getMessage().getContentDisplay().toLowerCase().startsWith("fdp")) {
                TextFileWriter.write("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Fdp.txt", Integer.toString(this.fdp), 1);
                ++this.fdp;
                if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                    if (lang == command.Language.fr) {
                        event.getChannel().sendMessage(" Surveille ton langage !! " + event.getAuthor().getAsMention() + "\n j'ai effac\u00e9 " + this.fdp + " fdp").queue();
                    }
                    if (lang == command.Language.en) {
                        event.getChannel().sendMessage(" Watch your words !! " + event.getAuthor().getAsMention() + "\n I deleted " + this.fdp + " fdp").queue();
                    }
                }
                event.getMessage().delete().queue();
            }
            if (event.getMessage().getContentDisplay().toLowerCase().contains(" ez") || event.getMessage().getContentDisplay().toLowerCase().startsWith("ez")) {
                TextFileWriter.write("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Ez.txt", Integer.toString(this.ez), 1);
                ++this.ez;
                if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                    if (lang == command.Language.fr) {
                        event.getChannel().sendMessage(" Surveille ton langage !! " + event.getAuthor().getAsMention() + "\n j'ai effac\u00e9 " + this.ez + " ez").queue();
                    }
                    if (lang == command.Language.en) {
                        event.getChannel().sendMessage(" Watch your words !! " + event.getAuthor().getAsMention() + "\n I deleted " + this.ez + " ez").queue();
                    }
                }
                event.getMessage().delete().queue();
            }
            if (event.getMessage().getContentDisplay().toLowerCase().contains(" salope") || event.getMessage().getContentDisplay().toLowerCase().startsWith("salope")) {
                TextFileWriter.write("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Salope.txt", Integer.toString(this.salope), 6);
                ++this.salope;
                if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                    if (lang == command.Language.fr) {
                        event.getChannel().sendMessage(" Surveille ton langage !! " + event.getAuthor().getAsMention() + "\n j'ai effac\u00e9 " + this.salope + " salope").queue();
                    }
                    if (lang == command.Language.en) {
                        event.getChannel().sendMessage(" Watch your words !! " + event.getAuthor().getAsMention() + "\n I deleted " + this.salope + " salope").queue();
                    }
                }
                event.getMessage().delete().queue();
            }
            if (event.getMessage().getContentDisplay().toLowerCase().contains(" pute") || event.getMessage().getContentDisplay().toLowerCase().startsWith("pute")) {
                TextFileWriter.write("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Pute.txt", Integer.toString(this.pute), 1);
                ++this.pute;
                if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                    if (lang == command.Language.fr) {
                        event.getChannel().sendMessage(" Surveille ton langage !! " + event.getAuthor().getAsMention() + "\n j'ai effac\u00e9 " + this.pute + " pute").queue();
                    }
                    if (lang == command.Language.en) {
                        event.getChannel().sendMessage(" Watch your words !! " + event.getAuthor().getAsMention() + "\n I deleted " + this.pute + " pute").queue();
                    }
                }
                event.getMessage().delete().queue();
            }
            if (event.getMessage().getContentDisplay().toLowerCase().contains(" suce") || event.getMessage().getContentDisplay().toLowerCase().startsWith("suce")) {
                TextFileWriter.write("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Suce.txt", Integer.toString(this.suce), 1);
                ++this.suce;
                if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                    if (lang == command.Language.fr) {
                        event.getChannel().sendMessage(" Surveille ton langage !! " + event.getAuthor().getAsMention() + "\n j'ai effac\u00e9 " + this.suce + " suce").queue();
                    }
                    if (lang == command.Language.en) {
                        event.getChannel().sendMessage(" Watch your words !! " + event.getAuthor().getAsMention() + "\n I deleted " + this.suce + " suce").queue();
                    }
                }
                event.getMessage().delete().queue();
            }
            if (event.getMessage().getContentDisplay().toLowerCase().contains(" ptn") || event.getMessage().getContentDisplay().toLowerCase().startsWith("ptn")) {
                ++this.ptn;
                TextFileWriter.write("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Ptn.txt", Integer.toString(this.ptn), 1);
                if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                    if (lang == command.Language.fr) {
                        event.getChannel().sendMessage(" Surveille ton langage !! " + event.getAuthor().getAsMention() + "\n j'ai effac\u00e9 " + this.ptn + " ptn").queue();
                    }
                    if (lang == command.Language.en) {
                        event.getChannel().sendMessage(" Watch your words !! " + event.getAuthor().getAsMention() + "\n I deleted " + this.ptn + " ptn").queue();
                    }
                }
                event.getMessage().delete().queue();
            }
            if (event.getMessage().getContentDisplay().toLowerCase().contains(" pd") || event.getMessage().getContentDisplay().toLowerCase().startsWith("pd")) {
                ++this.pd;
                TextFileWriter.write("/home/DiscordBot/Rasberry/donn\u00e9es/BadWords/Pd.txt", Integer.toString(this.pd), 1);
                if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                    if (lang == command.Language.fr) {
                        event.getChannel().sendMessage(" Surveille ton langage !! " + event.getAuthor().getAsMention() + "\n j'ai effac\u00e9 " + this.pd + " pd").queue();
                    }
                    if (lang == command.Language.en) {
                        event.getChannel().sendMessage(" Watch your words !! " + event.getAuthor().getAsMention() + "\n I deleted " + this.pd + " pd").queue();
                    }
                }
                event.getMessage().delete().queue();
            }
        }
    }
}

