/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

import fr.Ybsi.OzeryoBot.DiscordBot;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class color {
    public static Color couleurAleatoire(User user) {
        if (Premium.Premium(DiscordBot.getData().getProfils().get(user.getId()))) {
            int b;
            int g;
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Premium/Color/");
            int r = Integer.parseInt(TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Premium/Color/red.txt"));
            if (r == 0) {
                r = (int) (Math.random() * 255.0);
            }
            if ((g = Integer.parseInt(TextFileWriter.read(
                    "/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Premium/Color/green.txt"))) == 0) {
                r = (int) (Math.random() * 255.0);
            }
            if ((b = Integer.parseInt(TextFileWriter.read(
                    "/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Premium/Color/blue.txt"))) == 0) {
                r = (int) (Math.random() * 255.0);
            }
            Color couleur = new Color(r, g, b);
            return couleur;
        }
        int r = (int) (Math.random() * 255.0);
        int g = (int) (Math.random() * 255.0);
        int b = (int) (Math.random() * 255.0);
        Color couleur = new Color(r, g, b);
        return couleur;
    }
}
