/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Utils.Premium;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import fr.Ybsi.OzeryoBot.Utils.color;
import java.awt.Color;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class Achivement {
    public static void achivement(User user, String command2, MessageChannel channel) {
        ArrayList<String> hero;
        int Game_level;
        int ap;
        command.Language lang = DiscordBot.getData().getProfils().get(user.getId()).getLanguage();
        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Achievement");
        ProfilData data = DiscordBot.getData();
        HashMap<String, Long> map = data.getProfils().get(user.getId()).getAchievement();
        try {
            ap = data.getProfils().get(user.getId()).getAp();
        }
        catch (NullPointerException e) {
            ap = 0;
        }
        int collector = data.getProfils().get(user.getId()).getCollector();
        if (collector > 25 && data.getProfils().get(user.getId()).getAchievement().get("Collector I") == 0L) {
            map.put("Collector I", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Collector I``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Collector I``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (collector > 50 && data.getProfils().get(user.getId()).getAchievement().get("Collector II") == 0L) {
            map.put("Collector II", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 3;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Collector II``. Vous gagnez 3 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Collector II``. You won 3 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (collector > 100 && data.getProfils().get(user.getId()).getAchievement().get("Collector III") == 0L) {
            map.put("Collector III", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 5;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Collector III``. Vous gagnez 5 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Collector III``. You won 5 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int melting = data.getProfils().get(user.getId()).getMelting();
        if (melting >= 1 && data.getProfils().get(user.getId()).getAchievement().get("Melting") == 0L) {
            map.put("Melting", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Melting``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Melting``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int ivoire = 0;
        try {
            ivoire = Integer.parseInt(data.getProfils().get(user.getId()).getHeroe().get("Ivoire").get(0));
        }
        catch (Exception exception) {
            // empty catch block
        }
        if (ivoire >= 1 && data.getProfils().get(user.getId()).getAchievement().get("You'r special") == 0L) {
            map.put("You'r special", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 5;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``You'r special``. Vous gagnez 5 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``You'r special``. You won 5 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        try {
            hero = data.getProfils().get(user.getId()).getHeroe().get(data.getProfils().get(user.getId()).getActiveHeroe());
        }
        catch (Exception e) {
            hero = new ArrayList<String>();
            hero.add("0");
        }
        int levelh = 0;
        try {
            levelh = Integer.parseInt((String)hero.get(0));
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        if (levelh >= 5 && data.getProfils().get(user.getId()).getAchievement().get("Grinder I") == 0L) {
            map.put("Grinder I", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Grinder I``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Grinder I``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (levelh >= 10) {
            if (data.getProfils().get(user.getId()).getAchievement().get("Grinder II") == 0L) {
                map.put("Grinder II", System.currentTimeMillis());
                try {
                    data.getProfils().get(user.getId()).setAchievement(map);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setAchievement(map);
                }
                ap += 3;
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous avez obtenu le succes ``Grinder II``. Vous gagnez 3 achievement point.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You unlocked the success ``Grinder II``. You won 3 Achievement point.").queue();
                }
                try {
                    data.getProfils().get(user.getId()).setAp(ap);
                }
                catch (NullPointerException e1) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setAp(ap);
                }
                return;
            }
            if (levelh >= 20 && data.getProfils().get(user.getId()).getAchievement().get("Grinder III") == 0L) {
                map.put("Grinder III", System.currentTimeMillis());
                try {
                    data.getProfils().get(user.getId()).setAchievement(map);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setAchievement(map);
                }
                ap += 5;
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous avez obtenu le succes ``Grinder III``. Vous gagnez 5 achievement point.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You unlocked the success ``Grinder III``. You won 5 Achievement point.").queue();
                }
                try {
                    data.getProfils().get(user.getId()).setAp(ap);
                }
                catch (NullPointerException e1) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setAp(ap);
                }
                return;
            }
        }
        if ((command2.equals("work") || command2.equals("work all")) && data.getProfils().get(user.getId()).getAchievement().get("Travailleur") == 0L) {
            map.put("Travailleur", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Travailleur``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Worker``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        long money = data.getProfils().get(user.getId()).getMoney_récolté();
        if (money > 100000000L && data.getProfils().get(user.getId()).getAchievement().get("Harvester I") == 0L) {
            map.put("Harvester I", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Harvester I``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Harvester I``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (money > 1000000000L && data.getProfils().get(user.getId()).getAchievement().get("Harvester II") == 0L) {
            map.put("Harvester II", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 3;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Harvester II``. Vous gagnez 3 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Harvester II``. You won 3 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (money > 10000000000L && data.getProfils().get(user.getId()).getAchievement().get("Harvester III") == 0L) {
            map.put("Harvester III", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 5;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Harvester III``. Vous gagnez 5 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Harvester III``. You won 5 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int level = data.getProfils().get(user.getId()).getBuilding().get("marché");
        if (level >= 5 && data.getProfils().get(user.getId()).getAchievement().get("Entrepreneur I") == 0L) {
            map.put("Entrepreneur I", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Entrepreneur I``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Contractor I``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (level >= 15 && data.getProfils().get(user.getId()).getAchievement().get("Entrepreneur II") == 0L) {
            map.put("Entrepreneur II", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 3;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Entrepreneur II``. Vous gagnez 3 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Contractor II``. You won 3 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (level >= 25 && data.getProfils().get(user.getId()).getAchievement().get("Entrepreneur III") == 0L) {
            map.put("Entrepreneur III", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 5;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Entrepreneur III``. Vous gagnez 5 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Contractor III``. You won 5 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int atk = data.getProfils().get(user.getId()).getConquerant();
        if (atk >= 10 && data.getProfils().get(user.getId()).getAchievement().get("Conquérant I") == 0L) {
            map.put("Conquérant I", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Conquérant I``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Conqueror I``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (atk >= 100 && data.getProfils().get(user.getId()).getAchievement().get("Conquérant II") == 0L) {
            map.put("Conquérant II", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 3;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Conquérant II``. Vous gagnez 3 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Conqueror II``. You won 3 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (atk >= 250 && data.getProfils().get(user.getId()).getAchievement().get("Conquérant III") == 0L) {
            map.put("Conquérant III", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 5;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Conquérant III``. Vous gagnez 5 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Conqueror III``. You won 5 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int def = data.getProfils().get(user.getId()).getDefenseur();
        if (def >= 10 && data.getProfils().get(user.getId()).getAchievement().get("Défenseur I") == 0L) {
            map.put("Défenseur I", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Défenseur I``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Defender I``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (def >= 100 && data.getProfils().get(user.getId()).getAchievement().get("Défenseur II") == 0L) {
            map.put("Défenseur II", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 3;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Défenseur II``. Vous gagnez 3 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Defender II``. You won 3 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (def >= 250 && data.getProfils().get(user.getId()).getAchievement().get("Défenseur III") == 0L) {
            map.put("Défenseur III", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 5;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Défenseur I``. Vous gagnez 5 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Defender I``. You won 5 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int esp = data.getProfils().get(user.getId()).getEspion();
        if (esp >= 10 && data.getProfils().get(user.getId()).getAchievement().get("Espion I") == 0L) {
            map.put("Espion I", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Espion I``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Spy I``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (esp >= 50 && data.getProfils().get(user.getId()).getAchievement().get("Espion II") == 0L) {
            map.put("Espion II", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 3;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Espion II``. Vous gagnez 3 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Spy II``. You won 3 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (esp >= 100 && data.getProfils().get(user.getId()).getAchievement().get("Espion III") == 0L) {
            map.put("Espion III", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 5;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Espion I``. Vous gagnez 5 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Spy I``. You won 5 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int Game_EXP = data.getProfils().get(user.getId()).getXp();
        try {
            double operation = 3 * Game_EXP / 4;
            double math = Math.sqrt(operation);
            Game_level = (int)Math.round(math);
        }
        catch (NullPointerException e) {
            Game_level = 0;
        }
        if (Game_level >= 100 && data.getProfils().get(user.getId()).getAchievement().get("Farmer I") == 0L) {
            map.put("Farmer I", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Farmer I``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Farmer I``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (Game_level >= 300 && data.getProfils().get(user.getId()).getAchievement().get("Farmer II") == 0L) {
            map.put("Farmer II", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 3;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Farmer I``. Vous gagnez 3 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Farmer I``. You won 3 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (Game_level >= 500 && data.getProfils().get(user.getId()).getAchievement().get("Farmer III") == 0L) {
            map.put("Farmer III", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 5;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Farmer I``. Vous gagnez 5 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Farmer I``. You won 5 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int levelEcole = data.getProfils().get(user.getId()).getBuilding().get("biblioth\u00e8que");
        if (levelEcole >= 1 && data.getProfils().get(user.getId()).getAchievement().get("Instructeur") == 0L) {
            map.put("Instructeur", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Instructeur``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Instructor``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int animaux = 0;
        try {
            animaux = data.getProfils().get(user.getId()).getPet().size();
        }
        catch (NullPointerException math) {
            // empty catch block
        }
        if (animaux >= 1 && data.getProfils().get(user.getId()).getAchievement().get("Zoologue I") == 0L) {
            map.put("Zoologue I", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Zoologue I``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Zoologist I``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (animaux >= 5 && data.getProfils().get(user.getId()).getAchievement().get("Zoologue II") == 0L) {
            map.put("Zoologue II", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 3;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Zoologue I``. Vous gagnez 3 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Zoologist I``. You won 3 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (animaux >= 10 && data.getProfils().get(user.getId()).getAchievement().get("Zoologue III") == 0L) {
            map.put("Zoologue III", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 5;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Zoologue I``. Vous gagnez 5 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Zoologist I``. You won 5 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int Parieur = data.getProfils().get(user.getId()).getParieur();
        if (Parieur >= 1 && data.getProfils().get(user.getId()).getAchievement().get("Parieur") == 0L) {
            map.put("Parieur", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Parieur``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Bettor``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int votes = data.getProfils().get(user.getId()).getVote();
        if (votes >= 1 && data.getProfils().get(user.getId()).getAchievement().get("Thx dude I") == 0L) {
            map.put("Thx dude I", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Thx dude I``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Thx dude I``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (votes >= 50 && data.getProfils().get(user.getId()).getAchievement().get("Thx dude II") == 0L) {
            map.put("Thx dude II", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 3;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Thx dude II``. Vous gagnez 3 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Thx dude II``. You won 3 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (votes >= 100 && data.getProfils().get(user.getId()).getAchievement().get("Thx dude III") == 0L) {
            map.put("Thx dude III", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 5;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Thx dude III``. Vous gagnez 5 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Thx dude III``. You won 5 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int Combos = data.getProfils().get(user.getId()).getCf();
        if (Combos >= 50 && data.getProfils().get(user.getId()).getAchievement().get("Braquer I") == 0L) {
            map.put("Braquer I", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Braquer I``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Braquer I``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (Combos >= 500 && data.getProfils().get(user.getId()).getAchievement().get("Braquer II") == 0L) {
            map.put("Braquer II", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 3;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Braquer II``. Vous gagnez 3 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Braquer II``. You won 3 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (Combos >= 1500 && data.getProfils().get(user.getId()).getAchievement().get("Braquer III") == 0L) {
            map.put("Braquer III", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 5;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Braquer III``. Vous gagnez 5 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Braquer III``. You won 5 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int Ufilleuls = data.getProfils().get(user.getId()).getFilleuls();
        if (Ufilleuls >= 1 && data.getProfils().get(user.getId()).getAchievement().get("Parrain de la mafia") == 0L) {
            map.put("Parrain de la mafia", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Parrain de la mafia``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Mafia's Godfather``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int daily = data.getProfils().get(user.getId()).getDaily_récolté();
        if (daily >= 150 && data.getProfils().get(user.getId()).getAchievement().get("Toujours présent") == 0L) {
            map.put("Toujours présent", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 5;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Toujours présent``. Vous gagnez 5 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Always present``. You won 5 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int hourly2 = data.getProfils().get(user.getId()).getHourly_récolté();
        if (hourly2 >= 1000 && data.getProfils().get(user.getId()).getAchievement().get("Toujours debout") == 0L) {
            map.put("Toujours debout", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 5;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Toujours debout``. Vous gagnez 5 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Still standing``. You won 5 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int jetons = data.getProfils().get(user.getId()).getJetons_récolté();
        if (jetons > 2000 && data.getProfils().get(user.getId()).getAchievement().get("Fou des jeux") == 0L) {
            map.put("Fou des jeux", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 3;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Fou des jeux``. Vous gagnez 3 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Crazy of games``. You won 3 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int levelTemple = data.getProfils().get(user.getId()).getBuilding().get("eglise");
        if (levelTemple >= 1 && data.getProfils().get(user.getId()).getAchievement().get("Croyant") == 0L) {
            map.put("Croyant", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Croyant``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Believer``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int L = data.getProfils().get(user.getId()).getLooser();
        if (L >= 1 && data.getProfils().get(user.getId()).getAchievement().get("L") == 0L) {
            map.put("L", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Looser``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Looser``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int Investisseur = data.getProfils().get(user.getId()).getInvestisseur();
        if (Investisseur > 500 && data.getProfils().get(user.getId()).getAchievement().get("Investisseur I") == 0L) {
            map.put("Investisseur I", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Investisseur I``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Investor I``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (Investisseur > 5000 && data.getProfils().get(user.getId()).getAchievement().get("Investisseur II") == 0L) {
            map.put("Investisseur II", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 3;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Investisseur II``. Vous gagnez 3 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Investor II``. You won 3 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        if (Investisseur > 50000 && data.getProfils().get(user.getId()).getAchievement().get("Investisseur III") == 0L) {
            map.put("Investisseur III", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 5;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Investisseur III``. Vous gagnez 5 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Investor III``. You won 5 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int levelMine = data.getProfils().get(user.getId()).getBuilding().get("mine");
        if (levelMine >= 20 && data.getProfils().get(user.getId()).getAchievement().get("Mineur confirme") == 0L) {
            map.put("Mineur confirme", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 5;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Mineur confirmé``. Vous gagnez 5 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Confirmed minor``. You won 5 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int Avant_poste = data.getProfils().get(user.getId()).getAvant_poste();
        if (Avant_poste >= 1 && data.getProfils().get(user.getId()).getAchievement().get("Avant poste") == 0L) {
            map.put("Avant poste", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Avant poste``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``outpost``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int Pigeon = data.getProfils().get(user.getId()).getPigeon();
        if (Pigeon >= 500 && data.getProfils().get(user.getId()).getAchievement().get("Pigeon") == 0L) {
            map.put("Pigeon", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Pigeon``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Pigeon``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 100 && data.getProfils().get(user.getId()).getAchievement().get("Famous") == 0L) {
            map.put("Famous", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 3;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Famous``. Vous gagnez 3 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Famous``. You won 3 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int Scientifique = data.getProfils().get(user.getId()).getScientifique();
        if (Scientifique >= 1 && data.getProfils().get(user.getId()).getAchievement().get("Scientifique") == 0L) {
            map.put("Scientifique", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Scientifique``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Scientist``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        long hab = data.getProfils().get(user.getId()).getHabitants();
        if (hab >= 500000000L && data.getProfils().get(user.getId()).getAchievement().get("Megalopole") == 0L) {
            map.put("Megalopole", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 5;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Megalopole``. Vous gagnez 5 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Megalopolis``. You won 5 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int leveltransport = data.getProfils().get(user.getId()).getBuilding().get("transport");
        if (leveltransport >= 4 && data.getProfils().get(user.getId()).getAchievement().get("Ville touristique") == 0L) {
            map.put("Ville touristique", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 5;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Ville touristique``. Vous gagnez 5 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Tourist city``. You won 5 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int mana = data.getProfils().get(user.getId()).getMana();
        Game_EXP = data.getProfils().get(user.getId()).getXp();
        int Gamelevel = 0;
        try {
            double operation = 3 * Game_EXP / 4;
            double math = Math.sqrt(operation);
            Gamelevel = (int)Math.round(math);
        }
        catch (NullPointerException e) {
            Gamelevel = 0;
        }
        int Mana_Max = 10 + Gamelevel;
        if (mana > Mana_Max) {
            mana = Mana_Max;
        }
        if (mana >= 300 && data.getProfils().get(user.getId()).getAchievement().get("Magicien") == 0L) {
            map.put("Magicien", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 3;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Magicien``. Vous gagnez 3 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Wizard``. You won 3 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        boolean premium = Premium.Premium(user);
        if (premium && data.getProfils().get(user.getId()).getAchievement().get("What a luck") == 0L) {
            map.put("What a luck", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``What a luck``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``What a luck``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int idh = data.getProfils().get(user.getId()).getIdh();
        if (idh >= 1000 && data.getProfils().get(user.getId()).getAchievement().get("A good player") == 0L) {
            map.put("A good player", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 5;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``A good player``. Vous gagnez 5 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``A good player``. You won 5 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int houses = 0;
        try {
            houses = data.getProfils().get(user.getId()).getHouses().size();
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        if (houses >= 10 && data.getProfils().get(user.getId()).getAchievement().get("Ville attractive") == 0L) {
            map.put("Ville attractive", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ap += 5;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Ville attractive``. Vous gagnez 5 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Attractive city``. You won 5 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        int Useless = data.getProfils().get(user.getId()).getUseless();
        if (Useless >= 1 && data.getProfils().get(user.getId()).getAchievement().get("Useless") == 0L) {
            map.put("Useless", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Useless``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Useless``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
        String pays = data.getProfils().get(user.getId()).getCountry();
        if (!pays.equals("0") && !pays.equals("") && data.getProfils().get(user.getId()).getAchievement().get("Impliqué") == 0L) {
            map.put("Impliqué", System.currentTimeMillis());
            try {
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAchievement(map);
            }
            ++ap;
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous avez obtenu le succes ``Impliqué``. Vous gagnez 1 achievement point.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You unlocked the success ``Involved``. You won 1 Achievement point.").queue();
            }
            try {
                data.getProfils().get(user.getId()).setAp(ap);
            }
            catch (NullPointerException e1) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAp(ap);
            }
            return;
        }
    }

    @command(name="achievement", type=command.ExecutorType.ALL, descfr=" permet de voir votre progression dans les réalisations que vous avez mené dans le jeu", descen="lets see your progress in the achievements you have led in the game", topic=command.Topics.Game)
    private void achievement(MessageChannel message, Guild guild, String[] args, User user, JDA jda, command.Language lang, ProfilData data) {
        int c1;
        try {
            c1 = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException e) {
            c1 = 1;
        }
        catch (IndexOutOfBoundsException e) {
            c1 = 1;
        }
        HashMap<String, Long> map = data.getProfils().get(user.getId()).getAchievement();
        GregorianCalendar calendar = new GregorianCalendar();
        String travailleur = map.get("Travailleur").toString();
        if (travailleur.equals("0")) {
            travailleur = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(travailleur));
            travailleur = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String CollecteurI = map.get("Harvester I").toString();
        if (CollecteurI.equals("0")) {
            CollecteurI = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(CollecteurI));
            CollecteurI = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String CollecteurII = map.get("Harvester II").toString();
        if (CollecteurII.equals("0")) {
            CollecteurII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(CollecteurII));
            CollecteurII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String CollecteurIII = map.get("Harvester III").toString();
        if (CollecteurIII.equals("0")) {
            CollecteurIII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(CollecteurIII));
            CollecteurIII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String EntrepreneurI = map.get("Entrepreneur I").toString();
        if (EntrepreneurI.equals("0")) {
            EntrepreneurI = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(EntrepreneurI));
            EntrepreneurI = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String EntrepreneurII = map.get("Entrepreneur II").toString();
        if (EntrepreneurII.equals("0")) {
            EntrepreneurII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(EntrepreneurII));
            EntrepreneurII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String EntrepreneurIII = map.get("Entrepreneur III").toString();
        if (EntrepreneurIII.equals("0")) {
            EntrepreneurIII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(EntrepreneurIII));
            EntrepreneurIII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String ConquérantI = map.get("Conquérant I").toString();
        if (ConquérantI.equals("0")) {
            ConquérantI = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(ConquérantI));
            ConquérantI = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String ConquérantII = map.get("Conquérant II").toString();
        if (ConquérantII.equals("0")) {
            ConquérantII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(ConquérantII));
            ConquérantII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String ConquérantIII = map.get("Conquérant III").toString();
        if (ConquérantIII.equals("0")) {
            ConquérantIII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(ConquérantIII));
            ConquérantIII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String DéfenseurI = map.get("Défenseur I").toString();
        if (DéfenseurI.equals("0")) {
            DéfenseurI = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(DéfenseurI));
            DéfenseurI = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String DéfenseurII = map.get("Défenseur II").toString();
        if (DéfenseurII.equals("0")) {
            DéfenseurII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(DéfenseurII));
            DéfenseurII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String DéfenseurIII = map.get("Défenseur III").toString();
        if (DéfenseurIII.equals("0")) {
            DéfenseurIII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(DéfenseurIII));
            DéfenseurIII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String EspionI = map.get("Espion I").toString();
        if (EspionI.equals("0")) {
            EspionI = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(EspionI));
            EspionI = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String EspionII = map.get("Espion II").toString();
        if (EspionII.equals("0")) {
            EspionII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(EspionII));
            EspionII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String EspionIII = map.get("Espion III").toString();
        if (EspionIII.equals("0")) {
            EspionIII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(EspionIII));
            EspionIII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String FarmerI = map.get("Farmer I").toString();
        if (FarmerI.equals("0")) {
            FarmerI = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(FarmerI));
            FarmerI = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String FarmerII = map.get("Farmer II").toString();
        if (FarmerII.equals("0")) {
            FarmerII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(FarmerII));
            FarmerII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String FarmerIII = map.get("Farmer III").toString();
        if (FarmerIII.equals("0")) {
            FarmerIII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(FarmerIII));
            FarmerIII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String Instructeur = map.get("Instructeur").toString();
        if (Instructeur.equals("0")) {
            Instructeur = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(Instructeur));
            Instructeur = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String ZoologueI = map.get("Zoologue I").toString();
        if (ZoologueI.equals("0")) {
            ZoologueI = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(ZoologueI));
            ZoologueI = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String ZoologueII = map.get("Zoologue II").toString();
        if (ZoologueII.equals("0")) {
            ZoologueII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(ZoologueII));
            ZoologueII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String ZoologueIII = map.get("Zoologue III").toString();
        if (ZoologueIII.equals("0")) {
            ZoologueIII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(ZoologueIII));
            ZoologueIII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String Parieur = map.get("Parieur").toString();
        if (Parieur.equals("0")) {
            Parieur = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(Parieur));
            Parieur = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String thxdudeI = map.get("Thx dude I").toString();
        if (thxdudeI.equals("0")) {
            thxdudeI = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(thxdudeI));
            thxdudeI = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String thxdudeII = map.get("Thx dude II").toString();
        if (thxdudeII.equals("0")) {
            thxdudeII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(thxdudeII));
            thxdudeII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String thxdudeIII = map.get("Thx dude III").toString();
        if (thxdudeIII.equals("0")) {
            thxdudeIII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(thxdudeIII));
            thxdudeIII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String BraquerI = map.get("Braquer I").toString();
        if (BraquerI.equals("0")) {
            BraquerI = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(BraquerI));
            BraquerI = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String BraquerII = map.get("Braquer II").toString();
        if (BraquerII.equals("0")) {
            BraquerII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(BraquerII));
            BraquerII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String BraquerIII = map.get("Braquer III").toString();
        if (BraquerIII.equals("0")) {
            BraquerIII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(BraquerIII));
            BraquerIII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String ParrainDeLaMafia = map.get("Parrain de la mafia").toString();
        if (ParrainDeLaMafia.equals("0")) {
            ParrainDeLaMafia = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(ParrainDeLaMafia));
            ParrainDeLaMafia = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String ToujoursPresent = map.get("Toujours présent").toString();
        if (ToujoursPresent.equals("0")) {
            ToujoursPresent = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(ToujoursPresent));
            ToujoursPresent = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String ToujoursDebout = map.get("Toujours debout").toString();
        if (ToujoursDebout.equals("0")) {
            ToujoursDebout = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(ToujoursDebout));
            ToujoursDebout = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String FouDesJeux = map.get("Fou des jeux").toString();
        if (FouDesJeux.equals("0")) {
            FouDesJeux = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(FouDesJeux));
            FouDesJeux = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String Croyant = map.get("Croyant").toString();
        if (Croyant.equals("0")) {
            Croyant = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(Croyant));
            Croyant = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String L = map.get("L").toString();
        if (L.equals("0")) {
            L = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(L));
            L = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String InvestisseurI = map.get("Investisseur I").toString();
        if (InvestisseurI.equals("0")) {
            InvestisseurI = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(InvestisseurI));
            InvestisseurI = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String InvestisseurII = map.get("Investisseur II").toString();
        if (InvestisseurII.equals("0")) {
            InvestisseurII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(InvestisseurII));
            InvestisseurII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String InvestisseurIII = map.get("Investisseur III").toString();
        if (InvestisseurIII.equals("0")) {
            InvestisseurIII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(InvestisseurIII));
            InvestisseurIII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String MineurConfirme = map.get("Mineur confirme").toString();
        if (MineurConfirme.equals("0")) {
            MineurConfirme = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(MineurConfirme));
            MineurConfirme = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String AvantPoste = map.get("Avant poste").toString();
        if (AvantPoste.equals("0")) {
            AvantPoste = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(AvantPoste));
            AvantPoste = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String Pigeon = map.get("Pigeon").toString();
        if (Pigeon.equals("0")) {
            Pigeon = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(Pigeon));
            Pigeon = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String Famous = map.get("Famous").toString();
        if (Famous.equals("0")) {
            Famous = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(Famous));
            Famous = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String Scientifique = map.get("Scientifique").toString();
        if (Scientifique.equals("0")) {
            Scientifique = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(Scientifique));
            Scientifique = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String Megalopole = map.get("Megalopole").toString();
        if (Megalopole.equals("0")) {
            Megalopole = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(Megalopole));
            Megalopole = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String VilleTouristique = map.get("Ville touristique").toString();
        if (VilleTouristique.equals("0")) {
            VilleTouristique = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(VilleTouristique));
            VilleTouristique = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String Magicien = map.get("Magicien").toString();
        if (Magicien.equals("0")) {
            Magicien = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(Magicien));
            Magicien = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String WhatALuck = map.get("What a luck").toString();
        if (WhatALuck.equals("0")) {
            WhatALuck = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(WhatALuck));
            WhatALuck = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String AGoodPlayer = map.get("A good player").toString();
        if (AGoodPlayer.equals("0")) {
            AGoodPlayer = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(AGoodPlayer));
            AGoodPlayer = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String VilleAttractive = map.get("Ville attractive").toString();
        if (VilleAttractive.equals("0")) {
            VilleAttractive = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(VilleAttractive));
            VilleAttractive = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String Useless = map.get("Useless").toString();
        if (Useless.equals("0")) {
            Useless = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(Useless));
            Useless = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String Impliqué = map.get("Impliqué").toString();
        if (Impliqué.equals("0")) {
            Impliqué = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(Impliqué));
            Impliqué = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String Starter = map.get("Starter").toString();
        if (Starter.equals("0")) {
            Starter = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(Starter));
            Starter = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String GrinderI = map.get("Grinder I").toString();
        if (GrinderI.equals("0")) {
            GrinderI = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(GrinderI));
            GrinderI = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String GrinderII = map.get("Grinder II").toString();
        if (GrinderII.equals("0")) {
            GrinderII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(GrinderII));
            GrinderII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String GrinderIII = map.get("Grinder III").toString();
        if (GrinderIII.equals("0")) {
            GrinderIII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(GrinderIII));
            GrinderIII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String Yourspecial = map.get("You'r special").toString();
        if (Yourspecial.equals("0")) {
            Yourspecial = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(Yourspecial));
            Yourspecial = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String Catch_them_all = map.get("Catch them all").toString();
        if (Catch_them_all.equals("0")) {
            Catch_them_all = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(Catch_them_all));
            Catch_them_all = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String SwordMaster = map.get("SwordMaster").toString();
        if (SwordMaster.equals("0")) {
            SwordMaster = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(SwordMaster));
            SwordMaster = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String Armorer = map.get("Armorer").toString();
        if (Armorer.equals("0")) {
            Armorer = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(Armorer));
            Armorer = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String CollectorI = map.get("Collector I").toString();
        if (CollectorI.equals("0")) {
            CollectorI = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(CollectorI));
            CollectorI = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String CollectorII = map.get("Collector II").toString();
        if (CollectorII.equals("0")) {
            CollectorII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(CollectorII));
            CollectorII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String CollectorIII = map.get("Collector III").toString();
        if (CollectorIII.equals("0")) {
            CollectorIII = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(CollectorIII));
            CollectorIII = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        String Melting = map.get("Melting").toString();
        if (Melting.equals("0")) {
            Melting = ":negative_squared_cross_mark:";
        } else {
            calendar.setTimeInMillis(Long.parseLong(Melting));
            Melting = String.valueOf(calendar.get(5)) + "/" + (calendar.get(2) + 1) + " " + (calendar.get(10) - 1) + ":" + calendar.get(12) + ":" + calendar.get(13);
        }
        if (c1 > 6) {
            message.sendMessage(":warning:  Il n'y a que 6 pages d'achievement.").queue();
            return;
        }
        EmbedBuilder builder = new EmbedBuilder();
        if (lang == command.Language.fr) {
            if (c1 == 1) {
                builder.setTitle("Achievement");
                builder.setColor(color.couleurAleatoire(user));
                builder.addField("Page 1", "Travailleur : " + travailleur + "\n" + "Collecteur I : " + CollecteurI + "\n" + "Collecteur II : " + CollecteurII + "\n" + "Collecteur III : " + CollecteurIII + "\n" + "Entrepreneur I : " + EntrepreneurI + "\n" + "Entrepreneur II : " + EntrepreneurII + "\n" + "Entrepreneur III : " + EntrepreneurIII + "\n" + "Conquerant I : " + ConquérantI + "\n" + "Conquerant II : " + ConquérantII + "\n" + "Conquerant III : " + ConquérantIII + "\n", true);
            } else if (c1 == 2) {
                builder.setTitle("Achievement");
                builder.setColor(color.couleurAleatoire(user));
                builder.addField("Page 2", "Defenseur I : " + DéfenseurI + "\n" + "Defenseur II : " + DéfenseurII + "\n" + "Defenseur III : " + DéfenseurIII + "\n" + "Espion I : " + EspionI + "\n" + "Espion II : " + EspionII + "\n" + "Espion III : " + EspionIII + "\n" + "Farmer I : " + FarmerI + "\n" + "Farmer II : " + FarmerII + "\n" + "Farmer III : " + FarmerIII + "\n" + "Instructeur : " + Instructeur + "\n", true);
            } else if (c1 == 3) {
                builder.setTitle("Achievement");
                builder.setColor(color.couleurAleatoire(user));
                builder.addField("Page 3", "Zoologue I : " + ZoologueI + "\n" + "Zoologue II : " + ZoologueII + "\n" + "Zoologue III : " + ZoologueIII + "\n" + "Parieur : " + Parieur + "\n" + "Thx dude I : " + thxdudeI + "\n" + "Thx dude II : " + thxdudeII + "\n" + "Thx dude III : " + thxdudeIII + "\n" + "Braquer I : " + BraquerI + "\n" + "Braquer II : " + BraquerII + "\n" + "Braquer III : " + BraquerIII + "\n", true);
            } else if (c1 == 4) {
                builder.setTitle("Achievement");
                builder.setColor(color.couleurAleatoire(user));
                builder.addField("Page 4", "Parrain de la mafia : " + ParrainDeLaMafia + "\n" + "Toujours present : " + ToujoursPresent + "\n" + "Toujours debout : " + ToujoursDebout + "\n" + "Fou des jeux : " + FouDesJeux + "\n" + "Croyant : " + Croyant + "\n" + "Loser : " + L + "\n" + "Investisseur I" + InvestisseurI + "\n" + "Investisseur II" + InvestisseurII + "\n" + "Investisseur III" + InvestisseurIII + "\n" + "Mineur confirmé : " + MineurConfirme + "\n", true);
            } else if (c1 == 5) {
                builder.setTitle("Achievement");
                builder.setColor(color.couleurAleatoire(user));
                builder.addField("Page 5", "Avant poste : " + AvantPoste + "\n" + "Pigeon : " + Pigeon + "\n" + "Famous : " + Famous + "\n" + "Scientifique : " + Scientifique + "\n" + "Megalopole : " + Megalopole + "\n" + "Ville touristique : " + VilleTouristique + "\n" + "Ville attractive : " + VilleAttractive + "\n" + "Magicien : " + Magicien + "\n" + "What a luck : " + WhatALuck + "\n" + "A good player : " + AGoodPlayer + "\n", true);
            } else if (c1 == 6) {
                builder.setTitle("Achievement");
                builder.setColor(color.couleurAleatoire(user));
                builder.addField("Page 6", "Useless : " + Useless + "\n" + "Impliqué : " + Impliqué + "\n" + "Starter : " + Starter + "\n" + "Grinder I : " + GrinderI + "\n" + "Grinder II : " + GrinderII + "\n" + "Grinder III : " + GrinderIII + "\n" + "You'r special : " + Yourspecial + "\n" + "Catch them all : " + Catch_them_all + "\n" + "SwordMaster : " + SwordMaster + "\n" + "Armorer : " + Armorer + "\n" + "Collector I : " + CollectorI + "\n" + "Collector II : " + CollectorII + "\n" + "Collector III : " + CollectorIII + "\n" + "Melting : " + Melting, true);
            }
            builder.setFooter("Page " + c1 + " / 6", guild.getIconUrl());
        }
        if (lang == command.Language.en) {
            if (c1 == 1) {
                builder.setTitle("Achievement");
                builder.setColor(color.couleurAleatoire(user));
                builder.addField("Page 1", "Worker : " + travailleur + "\n" + "Collector I : " + CollecteurI + "\n" + "Collector II : " + CollecteurII + "\n" + "Collector III : " + CollecteurIII + "\n" + "Contractor I : " + EntrepreneurI + "\n" + "Contractor II : " + EntrepreneurII + "\n" + "Contractor III : " + EntrepreneurIII + "\n" + "Conqueror I : " + ConquérantI + "\n" + "Conqueror II : " + ConquérantII + "\n" + "Conqueror III : " + ConquérantIII + "\n", true);
            } else if (c1 == 2) {
                builder.setTitle("Achievement");
                builder.setColor(color.couleurAleatoire(user));
                builder.addField("Page 2", "Defender I : " + DéfenseurI + "\n" + "Defender II : " + DéfenseurII + "\n" + "Defender III : " + DéfenseurIII + "\n" + "Spy I : " + EspionI + "\n" + "Spy II : " + EspionII + "\n" + "Spy III : " + EspionIII + "\n" + "Farmer I : " + FarmerI + "\n" + "Farmer II : " + FarmerII + "\n" + "Farmer III : " + FarmerIII + "\n" + "Instructor : " + Instructeur + "\n", true);
            } else if (c1 == 3) {
                builder.setTitle("Achievement");
                builder.setColor(color.couleurAleatoire(user));
                builder.addField("Page 3", "Zoologist I : " + ZoologueI + "\n" + "Zoologist II : " + ZoologueII + "\n" + "Zoologist III : " + ZoologueIII + "\n" + "Bettor : " + Parieur + "\n" + "Thx dude I : " + thxdudeI + "\n" + "Thx dude II : " + thxdudeII + "\n" + "Thx dude III : " + thxdudeIII + "\n" + "Braquer I : " + BraquerI + "\n" + "Braquer II : " + BraquerII + "\n" + "Braquer III : " + BraquerIII + "\n", true);
            } else if (c1 == 4) {
                builder.setTitle("Achievement");
                builder.setColor(color.couleurAleatoire(user));
                builder.addField("Page 4", "Mafia's Godfather : " + ParrainDeLaMafia + "\n" + "Always present : " + ToujoursPresent + "\n" + "Still standing : " + ToujoursDebout + "\n" + "Crazy of games : " + FouDesJeux + "\n" + "Believer : " + Croyant + "\n" + "Looser : " + L + "\n" + "Investor I" + InvestisseurI + "\n" + "Investor II" + InvestisseurII + "\n" + "Investor III" + InvestisseurIII + "\n" + "Confirmed minor : " + MineurConfirme + "\n", true);
            } else if (c1 == 5) {
                builder.setTitle("Achievement");
                builder.setColor(color.couleurAleatoire(user));
                builder.addField("Page 5", "Outpost : " + AvantPoste + "\n" + "Pigeon : " + Pigeon + "\n" + "Famous : " + Famous + "\n" + "Scientist : " + Scientifique + "\n" + "Megalopolis : " + Megalopole + "\n" + "Tourist city : " + VilleTouristique + "\n" + "Attractive city : " + VilleAttractive + "\n" + "Wizard : " + Magicien + "\n" + "What a luck : " + WhatALuck + "\n" + "A good player : " + AGoodPlayer + "\n", true);
            } else if (c1 == 6) {
                builder.setTitle("Achievement");
                builder.setColor(color.couleurAleatoire(user));
                builder.addField("Page 6", "Useless : " + Useless + "\n" + "Involved : " + Impliqué + "\n" + "Starter : " + Starter + "\n" + "Grinder I : " + GrinderI + "\n" + "Grinder II : " + GrinderII + "\n" + "Grinder III : " + GrinderIII + "\n" + "You'r special : " + Yourspecial + "\n" + "Catch them all : " + Catch_them_all + "\n" + "SwordMaster : " + SwordMaster + "\n" + "Armorer : " + Armorer + "\n" + "Collector I : " + CollectorI + "\n" + "Collector II : " + CollectorII + "\n" + "Collector III : " + CollectorIII + "\n" + "Melting : " + Melting, true);
            }
            builder.setFooter("Page " + c1 + " / 6", guild.getIconUrl());
        }
        message.sendMessage(builder.build()).queue();
    }
}

