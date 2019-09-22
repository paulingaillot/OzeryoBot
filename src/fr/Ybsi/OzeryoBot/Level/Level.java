/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Level;

import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Utils.GuildProfil;
import fr.Ybsi.OzeryoBot.Utils.LevelProfil;
import java.util.HashMap;

public class Level {
    public static int level(String id) {
        int EXP2 = 0;
        try {
            EXP2 = DiscordBot.getLeveldata().getLevelProfil().get(id).getXp();
        } catch (NullPointerException e) {
            DiscordBot.getLeveldata().getLevelProfil().put(id, new LevelProfil(id));
        }
        int level_test = 1;
        int EXP_test = 100;
        int EXP_Test2 = 0;
        if (EXP2 >= 100) {
            EXP_test = 100;
            while (EXP_test <= EXP2) {
                EXP_Test2 = EXP_test;
                EXP_test = EXP_Test2 + (++level_test + 1) * 50;
            }
            --level_test;
        } else {
            level_test = 0;
        }
        return level_test;
    }

    public static int Glevel(String id) {
        int EXP2 = 0;
        try {
            EXP2 = DiscordBot.getGuilddata().getGuildProfil().get(id).getXp();
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        int level_test = 1;
        int EXP_test = 100;
        int EXP_Test2 = 0;
        if (EXP2 > 1000) {
            EXP_test = 1000;
            while (EXP_test <= EXP2) {
                EXP_Test2 = EXP_test;
                EXP_test = EXP_Test2 + (++level_test + 1) * 500;
            }
            --level_test;
        } else {
            level_test = 0;
        }
        return level_test;
    }
}
