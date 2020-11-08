/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import net.dv8tion.jda.api.entities.User;

public class habitants {
    public static long pop(Profil user) {
        long Habitants =user.getHabitants();
        return Habitants;
    }

    public static long atk(Profil profil) {
        long bonus_caserne2;
        try {
            bonus_caserne2 =profil.getSoldiers();
        } catch (NullPointerException e) {
            bonus_caserne2 = 0L;
        }
        return bonus_caserne2;
    }
}
