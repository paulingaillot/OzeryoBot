/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Default;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class reputation {
    @command(name="rep", type=command.ExecutorType.ALL, descfr="usage : [BETA] creer une ville et developpe la au fil de temps", topic=command.Topics.Social)
    private void rep(MessageChannel channel, User user, String[] args, Message message, command.Language lang) {
        int rep;
        ProfilData data = DiscordBot.getData();
        String c1 = "";
        try {
            c1 = args[0];
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
        try {
            rep = data.getProfils().get(user.getId()).getRep();
        }
        catch (NullPointerException e) {
            rep = 0;
        }
        long lastrep = data.getProfils().get(user.getId()).getLastRep();
        long delay = System.currentTimeMillis() - lastrep;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(delay);
        int mHour = calendar.get(11);
        int mMinute = calendar.get(12);
        int mSecond = calendar.get(13);
        if (c1.equals("")) {
            if (delay < 86400000L) {
                long delay2 = delay;
                mHour = (int)(delay2 / 3600000L);
                mMinute = (int)((delay2 %= 3600000L) / 60000L);
                mSecond = (int)((delay2 %= 60000L) / 1000L);
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous avez " + rep + " de R\u00e9putation. Vous pourrez en donner un dans " + (23 - mHour) + " heures " + (60 - mMinute) + " minutes " + (60 - mSecond) + " secondes.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You have " + rep + " reputation points. You will be able to give a reputation point in " + (23 - mHour) + " hours " + (59 - mMinute) + " minutes " + (59 - mSecond) + " seconds.").queue();
                }
            } else {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous avez " + rep + " de R\u00e9putation. Vous pouvez donner un point de reputation").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You have " + rep + " reputation points. You can give a reputation point to another player.").queue();
                }
            }
            return;
        }
        if (delay >= 86400000L) {
            User cible = message.getMentionedUsers().get(0);
            if (cible.isBot()) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous ne pouvez pas donnez de la rep a un bot !").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You can't give a reputation point to a bot.").queue();
                }
                return;
            }
            if (cible.getId().equals(user.getId())) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous ne pouvez pas vous donnez de la rep a vous meme").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You can't give a reputation point to yourself").queue();
                }
                return;
            }
            int cibleRep = data.getProfils().get(cible.getId()).getRep();
            ++cibleRep;
            try {
                data.getProfils().get(cible.getId()).setRep(cibleRep);
                data.getProfils().get(cible.getId()).setId(cible.getId());
            }
            catch (NullPointerException e) {
                data.getProfils().put(cible.getId(), new Profil(cible.getId()));
                data.getProfils().get(cible.getId()).setRep(cibleRep);
            }
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous venez de donnez un point de reputation a " + cible.getAsMention() + ".").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You just give a reputation point to " + cible.getAsMention() + ".").queue();
            }
            try {
                data.getProfils().get(user.getId()).setLastRep(System.currentTimeMillis());
                data.getProfils().get(user.getId()).setId(user.getId());
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setLastRep(System.currentTimeMillis());
            }
        } else {
            mHour = (int)(delay / 3600000L);
            mMinute = (int)((delay %= 3600000L) / 60000L);
            mSecond = (int)((delay %= 60000L) / 1000L);
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous ne pouvez pas encore donner un point de reputation. Votre Prochain point de reputation sera disponible  dans" + (23 - mHour) + " heures " + (60 - mMinute) + " minutes " + (60 - mSecond) + " secondes. Vous avez " + rep + "de reputation").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You can't give a reputation point yet. Your reputation point will be available in " + (23 - mHour) + " hours " + (60 - mMinute) + " minutes " + (60 - mSecond) + " seconds. You have " + rep + " reputation points").queue();
            }
        }
    }
}

