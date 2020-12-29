/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

import fr.Ybsi.OzeryoBot.DiscordBot;
import net.dv8tion.jda.api.entities.User;

public class Premium {
    public static boolean Premium(Profil user) {
        long prem;
        try {
            prem = user.getPremium();
        } catch (Exception e) {
            prem = 0L;
        }
        long delay = prem - System.currentTimeMillis();
        return delay > 0L;
    }
}
