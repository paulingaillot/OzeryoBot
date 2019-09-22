/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Default;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Utils.GuildProfil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import fr.Ybsi.OzeryoBot.Utils.Scheduler;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class Pub {
    @command(name = "pub", type = command.ExecutorType.ALL, topic = command.Topics.Util)
    private void pub(Message message, Guild guild, String[] args, User user, TextChannel channel, String arg, JDA jda,
            ProfilData data, command.Language lang) {
        if (guild.getMember(user).hasPermission(Permission.ADMINISTRATOR)) {
            String c1 = "";
            try {
                c1 = args[0];
            } catch (Exception exception) {
                // empty catch block
            }
            StringBuilder builder = new StringBuilder();
            for (String str : args) {
                if (str.equals(args[0]))
                    continue;
                builder.append(String.valueOf(str) + " ");
            }
            String c2 = builder.toString();
            if (c1.equals("")) {
                DiscordBot.getGuilddata().getGuildProfil().get(guild.getId()).setPubchannel(channel.getId());
                channel.sendMessage("Votre nouveau channel de pub est : **" + channel.getName()
                        + "**. Pour enregistrer la pub de votre guild utiliser la commande ``=pub set [ta pub]``")
                        .queue();
            } else if (c1.equals("set")) {
                if (c2.contains("@everyone") || c2.contains("@here") || message.getMentionedUsers().size() > 0
                        || message.getMentionedChannels().size() > 0) {
                    channel.sendMessage("Aucune mention possible via les pubs").queue();
                    return;
                }
                DiscordBot.getGuilddata().getGuildProfil().get(guild.getId()).setPub(c2);
                channel.sendMessage("Votre pub est désormais : " + c2
                        + "\n Faites ``=pub next`` pour voir a quel heure sera envoyé la prochaine vague de pubs")
                        .queue();
            } else if (c1.equals("next")) {
                long nextpub = Scheduler.nextPub;
                long time = nextpub - System.currentTimeMillis();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(time);
                int mYear = calendar.get(1);
                int mMonth = calendar.get(2) + 1;
                int mDay = calendar.get(5);
                int mHour = calendar.get(11) - 1;
                int mMinute = calendar.get(12);
                int mSecond = calendar.get(13);
                channel.sendMessage(
                        "La prochaine vague de pub sera envoyé dans" + mHour + " heure et " + mMinute + " minutes.")
                        .queue();
            }
        } else {
            channel.sendMessage("Vous devez etre admin du serveur pour effectuer cet commande.").queue();
            return;
        }
    }
}
