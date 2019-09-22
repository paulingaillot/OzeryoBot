/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import java.util.HashMap;
import net.dv8tion.jda.core.entities.User;

public class habitants {
    public static long pop(User user) {
        long Habitants = DiscordBot.getData().getProfils().get(user.getId()).getHabitants();
        return Habitants;
    }

    public static long atk(User user) {
        long bonus_caserne2;
        try {
            bonus_caserne2 = DiscordBot.getData().getProfils().get(user.getId()).getSoldiers();
        }
        catch (NullPointerException e) {
            bonus_caserne2 = 0L;
        }
        return bonus_caserne2;
    }
}

