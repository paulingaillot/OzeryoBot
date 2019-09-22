/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

import fr.Ybsi.OzeryoBot.Utils.Premium;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import java.awt.Color;
import net.dv8tion.jda.core.entities.User;

public class color {
    public static Color couleurAleatoire(User user) {
        if (Premium.Premium(user)) {
            int b;
            int g;
            TextFileWriter.folder("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + user.getId() + "/Premium/Color/");
            int r = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + user.getId() + "/Premium/Color/red.txt"));
            if (r == 0) {
                r = (int)(Math.random() * 255.0);
            }
            if ((g = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + user.getId() + "/Premium/Color/green.txt"))) == 0) {
                r = (int)(Math.random() * 255.0);
            }
            if ((b = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + user.getId() + "/Premium/Color/blue.txt"))) == 0) {
                r = (int)(Math.random() * 255.0);
            }
            Color couleur = new Color(r, g, b);
            return couleur;
        }
        int r = (int)(Math.random() * 255.0);
        int g = (int)(Math.random() * 255.0);
        int b = (int)(Math.random() * 255.0);
        Color couleur = new Color(r, g, b);
        return couleur;
    }
}

