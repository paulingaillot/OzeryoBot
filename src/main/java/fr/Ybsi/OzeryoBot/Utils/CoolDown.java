/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

import net.dv8tion.jda.api.entities.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CoolDown {
    public static boolean CoolDown(User user) {
      /*  int minutes;
        int secondes;
        int heures;
        boolean Cooldown;
        boolean premium = Premium.Premium(user);
        if (premium) {
            boolean Cooldown2 = true;
            return Cooldown2;
        }
        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId());
        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Cooldown/");
        try {
            secondes = Integer.parseInt(TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Cooldown/secondes.txt"));
        } catch (NumberFormatException e) {
            secondes = 0;
        }
        try {
            minutes = Integer.parseInt(TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Cooldown/minutes.txt"));
        } catch (NumberFormatException e) {
            minutes = 0;
        }
        try {
            heures = Integer.parseInt(TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Cooldown/heures.txt"));
        } catch (NumberFormatException e) {
            heures = 0;
        }
        int Nsecondes = Integer.parseInt(new SimpleDateFormat("ss", Locale.FRANCE).format(new Date()));
        int Nminutes = Integer.parseInt(new SimpleDateFormat("mm", Locale.FRANCE).format(new Date()));
        int Nheures = Integer.parseInt(new SimpleDateFormat("HH", Locale.FRANCE).format(new Date()));
        int Lsecondes = Nsecondes - secondes;
        int Lminutes = Nminutes - minutes;
        int Lheures = Nheures - heures;
        if (Lsecondes < 0) {
            Lsecondes += 60;
            --Lminutes;
        }
        if (Lminutes < 0) {
            Lminutes += 60;
            --Lheures;
        }
        Lsecondes += 60 * Lminutes;
        System.out.println(Lsecondes += 1440 * Lheures);
        if (Lsecondes > 1 || Lsecondes < 0) {
            Cooldown = true;
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Cooldown/secondes.txt",
                    Integer.toString(Nsecondes), 1);
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Cooldown/minutes.txt",
                    Integer.toString(Nminutes), 1);
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Cooldown/heures.txt",
                    Integer.toString(Nheures), 1);
        } else {
            Cooldown = false;
        }
        return Cooldown;*/
        return true;
    }
}
