/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import fr.Ybsi.OzeryoBot.Utils.color;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class Mail {
    @command(name = "mail", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Social)
    private void mail(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        String c1;
        try {
            c1 = args[0];
        } catch (IndexOutOfBoundsException e) {
            c1 = "";
        }
        if (c1.equals("on")) {
            data.getProfils().get(user.getId()).setMail(true);
            if (lang == command.Language.fr) {
                channel.sendMessage(
                        ":mailbox: Vous recevrez desormais des mails au lieux de mp. Pour lire vos mail faites ``=mail list``")
                        .queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage(
                        ":mailbox:You will receive mails on the bot instead of private messages. To read your mails type ``=mail list``")
                        .queue();
            }
        } else if (c1.equals("off")) {
            data.getProfils().get(user.getId()).setMail(false);
            if (lang == command.Language.fr) {
                channel.sendMessage(
                        ":mailbox: Vous ne recevrez desormais des mails au lieux de mp. Pour lire vos mail faites ``=mail list``")
                        .queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage(
                        ":mailbox: You will not reveive mail on the bot instead of private message anymore. To read your mails type ``=mail list``")
                        .queue();
            }
        } else if (c1.equals("list")) {
            int c2;
            try {
                c2 = Integer.parseInt(args[1]);
            } catch (Exception e) {
                c2 = 1;
            }
            ArrayList<ArrayList<String>> mails = data.getProfils().get(user.getId()).getListMail();
            String mess = "";
            int i = 0;
            int min = 15 * (c2 - 1);
            int max = 15 * (c2 - 1) + 15;
            int read1 = 0;
            int unread = 0;
            try {
                for (ArrayList<String> mail2 : mails) {
                    String emoji;
                    ++i;
                    String objet = mail2.get(0);
                    boolean read = mail2.get(2).equals("true");
                    if (read) {
                        ++read1;
                        emoji = jda.getGuildById("326345972739473410").getEmoteById("552558087152730226")
                                .getAsMention();
                    } else {
                        ++unread;
                        emoji = "\ud83d\udd34";
                    }
                    if (i < min || i > max)
                        continue;
                    mess = String.valueOf(mess) + "**[" + emoji + i + "]** : " + objet + " \n";
                }
            } catch (NullPointerException mail2) {
                // empty catch block
            }
            if (mess.equals("")) {
                if (lang == command.Language.fr) {
                    mess = " Vous n'avez aucun mails dans votre messagerie.";
                }
                if (lang == command.Language.en) {
                    mess = " You don't have any mails in your mailbox.";
                }
            }
            EmbedBuilder builder = new EmbedBuilder();
            if (lang == command.Language.fr) {
                builder.setTitle(":e_mail: Bo\u00eete mail de " + user.getName() + "\n");
            }
            if (lang == command.Language.en) {
                builder.setTitle(":e_mail: MailBox of " + user.getName() + "\n");
            }
            builder.setColor(color.couleurAleatoire(user));
            builder.setAuthor(user.getName(), null, user.getAvatarUrl());
            if (lang == command.Language.fr) {
                builder.setDescription(":inbox_tray: E-mail non lus : " + unread + "\n" + ":outbox_tray: E-mail lus : "
                        + read1 + "\n\n" + mess);
            }
            if (lang == command.Language.en) {
                builder.setDescription(":inbox_tray: Unread mails : " + unread + "\n" + ":outbox_tray: Mails read : "
                        + read1 + "\n\n" + mess);
            }
            channel.sendMessage(builder.build()).queue();
        } else if (c1.equals("send")) {
            User cible;
            try {
                cible = message.getMentionedUsers().get(0);
            } catch (Exception e) {
                cible = jda.getUserById(args[0]);
            }
            String pref = TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/prefix.txt");
            if (pref.equals("0")) {
                pref = "=";
            }
            String mess = message.getContentRaw();
            mess = mess.replaceAll(String.valueOf(pref) + "mail ", "");
            mess = mess.replace(String.valueOf(cible.getAsMention()) + " ", "");
            mess = mess.replace("<@!" + cible.getId() + "> ", "");
            mess = mess.replace("<@" + cible.getId() + "> ", "");
            mess = mess.replace(String.valueOf(cible.getId()) + " ", "");
            mess = mess.replace("send ", "");
            ArrayList<String> list = new ArrayList<String>();
            if (lang == command.Language.fr) {
                list.add("Nouveau mail de " + user.getName());
            }
            if (lang == command.Language.en) {
                list.add("New mail from " + user.getName());
            }
            list.add(mess);
            list.add("false");
            list.add(String.valueOf(System.currentTimeMillis()));
            ArrayList<ArrayList<String>> mails = data.getProfils().get(cible.getId()).getListMail();
            mails.add(list);
            data.getProfils().get(cible.getId()).setListMail(mails);
            if (lang == command.Language.fr) {
                channel.sendMessage("Votre mail a bien été envoyé a " + cible.getName() + ".").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("Your mail has been sent to " + cible.getName() + ".").queue();
            }
        } else if (c1.equals("read") || c1.equals("open")) {
            ArrayList<String> mail3;
            int c2;
            ArrayList<ArrayList<String>> mails;
            String mess;
            String contenu;
            String objet;
            block65: {
                try {
                    c2 = Integer.parseInt(args[1]) - 1;
                } catch (Exception e) {
                    c2 = 0;
                }
                mails = data.getProfils().get(user.getId()).getListMail();
                mail3 = mails.get(c2);
                objet = mail3.get(0);
                contenu = mail3.get(1);
                boolean read = mail3.get(2).equals("true");
                mess = "";
                try {
                    long date = Long.parseLong(mail3.get(3));
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(date);
                    int mday = calendar.get(5);
                    int mmonth = calendar.get(2) + 1;
                    int myear = calendar.get(1);
                    int mHour = calendar.get(11);
                    int mMinute = calendar.get(12);
                    int mSecond = calendar.get(13);
                    if (lang == command.Language.fr) {
                        mess = "**Re\u00e7u le : ** " + mday + "/" + mmonth + "/" + myear + " a " + mHour + ":"
                                + mMinute + ":" + mSecond;
                    }
                    if (lang == command.Language.en) {
                        mess = "**Received on : ** " + mday + "/" + mmonth + "/" + myear + " a " + mHour + ":" + mMinute
                                + ":" + mSecond;
                    }
                } catch (Exception e) {
                    if (lang == command.Language.fr) {
                        mess = "Date de reception inconnu";
                    }
                    if (lang != command.Language.en)
                        break block65;
                    mess = "Unknown received date";
                }
            }
            mail3.set(2, "true");
            mails.set(c2, mail3);
            data.getProfils().get(user.getId()).setListMail(mails);
            EmbedBuilder builder = new EmbedBuilder();
            if (lang == command.Language.fr) {
                builder.setTitle(":e_mail: Lecture de mail");
            }
            if (lang == command.Language.en) {
                builder.setTitle(":e_mail: Mail reading");
            }
            builder.setColor(color.couleurAleatoire(user));
            builder.setAuthor(user.getName(), null, user.getAvatarUrl());
            if (lang == command.Language.fr) {
                builder.setDescription(" \n " + mess + "\n :inbox_tray: **Objet :** " + objet + "\n\n" + contenu);
            }
            if (lang == command.Language.en) {
                builder.setDescription(" \n " + mess + "\n :inbox_tray: **Object :** " + objet + "\n\n" + contenu);
            }
            channel.sendMessage(builder.build()).queue();
        } else if (c1.equals("remove") || c1.equals("delete")) {
            int c2;
            try {
                c2 = Integer.parseInt(args[1]) - 1;
            } catch (Exception e) {
                c2 = 0;
            }
            ArrayList<ArrayList<String>> mails = data.getProfils().get(user.getId()).getListMail();
            mails.remove(c2);
            data.getProfils().get(user.getId()).setListMail(mails);
            if (lang == command.Language.fr) {
                channel.sendMessage(":mailbox: Le mail numéro " + (c2 + 1) + " a bien été supprimé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage(":mailbox: The mail number " + (c2 + 1) + " has been deleted").queue();
            }
        } else if (c1.equals("deleteall")) {
            ArrayList<ArrayList<String>> mails = data.getProfils().get(user.getId()).getListMail();
            for (int i = 0; i < mails.size(); ++i) {
                mails.remove(i);
            }
            data.getProfils().get(user.getId()).setListMail(mails);
            if (lang == command.Language.fr) {
                channel.sendMessage(":mailbox: Tout vos mails ont été supprimés").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage(":mailbox: All your mails has been deleted").queue();
            }
        } else {
            boolean mail4 = data.getProfils().get(user.getId()).isMail();
            if (mail4) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(":mailbox: Les notifications via mails sont activés").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(":mailbox: Mails notifications are enable").queue();
                }
            } else {
                if (lang == command.Language.fr) {
                    channel.sendMessage(":mailbox: Les notifications via mails sont désactivés").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(":mailbox: Mails notifications are disable").queue();
                }
            }
        }
    }
}
