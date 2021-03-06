/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.Utils.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class City {
    @command(name = "city", abbrev = "c", type = command.ExecutorType.ALL, descfr = " permet de voir votre ville et ses b\u00e2timents.", descen = " lets see your city and its buildings.", topic = command.Topics.Game)
    private void city(MessageChannel channel, User user, Guild guild, String[] args, JDA jda, ProfilData data,
                      command.Language lang) {
        String c1;
        String c2;
        try {
            c1 = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            c1 = "";
        }
        try {
            c2 = args[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            c2 = "";
        }
        StringBuilder buider = new StringBuilder();
        for (String str : args) {
            if (str.equals(args[0]))
                continue;
            if (buider.length() > 0) {
                buider.append(" ");
            }
            buider.append(str);
        }
        String c3 = buider.toString();
        if (c1.equals("")) {
            int level;
            int Pet_EXP;
            double cc;
            int total;
            int habitations;
            String home = data.getProfils().get(user.getId()).getHome();
            home = "x=" + home.replaceAll("_", " | y=");
            long habitant = data.getProfils().get(user.getId()).getHabitants();
            HashMap<String, Integer> building = new HashMap();
            building = data.getProfils().get(user.getId()).getBuilding();
            HashMap<String, Integer> res = new HashMap();
            res = data.getProfils().get(user.getId()).getRes();
            int camp_entrainement = building.get("camp d'entrainement");
            int cc2 = building.get("habitations");
            switch (cc2) {
                case 0: {
                    cc = 0.0;
                    break;
                }
                case 1: {
                    cc = 1.125;
                    break;
                }
                case 2: {
                    cc = 1.25;
                    break;
                }
                case 3: {
                    cc = 1.5;
                    break;
                }
                case 4: {
                    cc = 2.0;
                    break;
                }
                case 5: {
                    cc = 2.5;
                    break;
                }
                case 6: {
                    cc = 3.0;
                    break;
                }
                case 7: {
                    cc = 3.5;
                    break;
                }
                case 8: {
                    cc = 4.0;
                    break;
                }
                case 9: {
                    cc = 4.5;
                    break;
                }
                case 10: {
                    cc = 5.0;
                    break;
                }
                case 11: {
                    cc = 5.5;
                    break;
                }
                case 12: {
                    cc = 6.0;
                    break;
                }
                case 13: {
                    cc = 6.5;
                    break;
                }
                case 14: {
                    cc = 7.0;
                    break;
                }
                case 15: {
                    cc = 7.5;
                    break;
                }
                case 16: {
                    cc = 8.0;
                    break;
                }
                case 17: {
                    cc = 8.5;
                    break;
                }
                case 18: {
                    cc = 9.0;
                    break;
                }
                case 19: {
                    cc = 9.5;
                    break;
                }
                case 20: {
                    cc = 10.0;
                    break;
                }
                case 21: {
                    cc = 11.0;
                    break;
                }
                case 22: {
                    cc = 12.0;
                    break;
                }
                case 23: {
                    cc = 13.0;
                    break;
                }
                case 24: {
                    cc = 14.0;
                    break;
                }
                case 25: {
                    cc = 15.0;
                    break;
                }
                default: {
                    cc = 15 + (cc2 - 25);
                }
            }
            int Game_EXP = data.getProfils().get(user.getId()).getXp();
            String pet = TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/pet.txt");
            try {
                Pet_EXP = Integer.parseInt(data.getProfils().get(user.getId()).getPet().get(pet).get(1));
            } catch (NullPointerException e) {
                Pet_EXP = 0;
            }
            String Pet_Bonus = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pets/" + pet);
            double operation1 = Pet_EXP / 10;
            double operation2 = Math.sqrt(operation1);
            double Pet_Level = Math.round(operation2);
            double pet_bonus = 1.0 + 0.03 * Pet_Level;
            try {
                double operation = 3 * Game_EXP / 4;
                double math = Math.sqrt(operation);
                level = (int) Math.round(math);
            } catch (NullPointerException e) {
                level = 0;
            }
            String pays = data.getProfils().get(user.getId()).getCountry();
            if (pays.equals("")) {
                if (lang == command.Language.fr) {
                    pays = "aucun";
                }
                if (lang == command.Language.fr) {
                    pays = "any";
                }
            }
            int point = Integer.parseInt(
                    TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pays/" + pays + "/points.txt"));
            double Glevel2 = point / 1000;
            double Glevel3 = Math.sqrt(Glevel2);
            int Glevel = (int) Math.round(Glevel3);
            String eglise = data.getProfils().get(user.getId()).getDeus();
            cc = 500.0 + Math.pow(cc + 1, 2.0) * (double) (40 * level);
            long popmax = (int) Math.round(cc);
            if (eglise.equals("Hades")) {
                popmax = (int) ((double) popmax * 1.5);
            }
            int mana = 10;
            mana = data.getProfils().get(user.getId()).getMana();
            long lastHourly = data.getProfils().get(user.getId()).getLastMana();
            long delay = System.currentTimeMillis() - lastHourly;
            delay /= 1000L;
            int regen = Pet_Bonus.equals("regen") ? 300 - (int) (Pet_Level * 3.0) : 300;
            if (eglise.equals("Hermes")) {
                regen -= 60;
            }
            if (regen < 150) {
                regen = 150;
            }
            if (delay > (long) regen) {
                while (delay > (long) regen) {
                    ++mana;
                    delay -= regen;
                }
                try {
                    data.getProfils().get(user.getId()).setLastMana(System.currentTimeMillis() - delay);
                } catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setLastMana(System.currentTimeMillis());
                }
            }
            int Mana_Max = 10 + level;
            if (level > 90) {
                Mana_Max = 100;
            }
            if (Pet_Bonus.equals("mana")) {
                Mana_Max += (int) (Pet_Level * 3.0);
            }
            if (eglise.equals("Athena")) {
                Mana_Max = (int) ((double) Mana_Max * 1.25);
            }
            if (mana > Mana_Max) {
                mana = Mana_Max;
            }
            if (mana < 0) {
                mana = Mana_Max;
            }
            try {
                data.getProfils().get(user.getId()).setMana(mana);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setMana(mana);
            }
            try {
                habitations = data.getProfils().get(user.getId()).getHouses().size();
            } catch (Exception e1) {
                habitations = 0;
            }
            try {
                HashMap<String, ArrayList<String>> houses = data.getProfils().get(user.getId()).getHouses();
                for (ArrayList<String> bonus1 : houses.values()) {
                    int bonus = Integer.parseInt(bonus1.get(1));
                    double bonus2 = bonus * 20000;
                    popmax = (int) ((double) popmax + bonus2);
                }
            } catch (Exception houses) {
                // empty catch block
            }
            long soldier = data.getProfils().get(user.getId()).getSoldiers();
            String hopmess = "";
            if (habitant > popmax || (double) soldier > 0.25 * (double) habitant) {
                double perte;
                int hopital = building.get("auberge");
                switch (hopital) {
                    case 1: {
                        perte = 0.9025;
                        break;
                    }
                    case 2: {
                        perte = 0.905;
                        break;
                    }
                    case 3: {
                        perte = 0.9075;
                        break;
                    }
                    case 4: {
                        perte = 0.91;
                        break;
                    }
                    case 5: {
                        perte = 0.9125;
                        break;
                    }
                    case 6: {
                        perte = 0.915;
                        break;
                    }
                    case 7: {
                        perte = 0.9175;
                        break;
                    }
                    case 8: {
                        perte = 0.92;
                        break;
                    }
                    case 9: {
                        perte = 0.9225;
                        break;
                    }
                    case 10: {
                        perte = 0.925;
                        break;
                    }
                    case 11: {
                        perte = 0.9275;
                        break;
                    }
                    case 12: {
                        perte = 0.93;
                        break;
                    }
                    case 13: {
                        perte = 0.9325;
                        break;
                    }
                    case 14: {
                        perte = 0.935;
                        break;
                    }
                    case 15: {
                        perte = 0.9375;
                        break;
                    }
                    case 16: {
                        perte = 0.94;
                        break;
                    }
                    case 17: {
                        perte = 0.9425;
                        break;
                    }
                    case 18: {
                        perte = 0.945;
                        break;
                    }
                    case 19: {
                        perte = 0.9475;
                        break;
                    }
                    case 20: {
                        perte = 0.95;
                        break;
                    }
                    case 21: {
                        perte = 0.955;
                        break;
                    }
                    case 22: {
                        perte = 0.96;
                        break;
                    }
                    case 23: {
                        perte = 0.965;
                        break;
                    }
                    case 24: {
                        perte = 0.97;
                        break;
                    }
                    case 25: {
                        perte = 0.975;
                        break;
                    }
                    default: {
                        perte = 0.9;
                    }
                }
                long lastHourly1 = data.getProfils().get(user.getId()).getLastPerte();
                long delay1 = System.currentTimeMillis() - lastHourly1;
                delay1 /= 3600000L;
                if (habitant > popmax) {
                    int a = 0;
                    habitant -= popmax;
                    while ((long) a < delay1) {
                        habitant = (int) ((double) habitant * perte);
                        ++a;
                    }
                    habitant += popmax;
                }
                if (habitant < popmax) {
                    habitant = popmax;
                }
                if ((double) soldier > 0.25 * (double) habitant) {
                    int i = 0;
                    while ((long) i < delay1) {
                        soldier = (int) ((double) soldier * perte);
                        ++i;
                    }
                    if ((double) soldier < 0.25 * (double) habitant) {
                        soldier = (long) (0.25 * (double) habitant);
                    }
                }
                if (habitant > popmax) {
                    if (lang == command.Language.fr) {
                        hopmess = "(-" + Utils.format((int) ((double) (habitant-popmax) - (double) (habitant-popmax) * perte))
                                + " hab/heures)";
                    }
                    if (lang == command.Language.en) {
                        hopmess = "(-" + Utils.format((int) ((double) (habitant-popmax) - (double) (habitant-popmax)  * perte))
                                + " hab/hours)";
                    }
                } else {
                    if (lang == command.Language.fr) {
                        hopmess = " / " + Utils.format(popmax) + " habitants";
                    }
                    if (lang == command.Language.en) {
                        hopmess = " / " + Utils.format(popmax) + " people";
                    }
                }
                try {
                    data.getProfils().get(user.getId()).setHabitants(habitant);
                } catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setHabitants(habitant);
                }
                try {
                    data.getProfils().get(user.getId()).setSoldiers(soldier);
                } catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setSoldiers(soldier);
                }
                try {
                    data.getProfils().get(user.getId()).setLastPerte(System.currentTimeMillis());
                } catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setLastPerte(System.currentTimeMillis());
                }
            }
            int auberge = building.get("auberge");
            int marché = building.get("marché");
            int habitation = building.get("habitations");
            long money = data.getProfils().get(user.getId()).getMoney();
            int A_bois = res.get("bois");
            int A_argile = res.get("argile");
            int A_cuir = res.get("cuir");
            int A_pierre = res.get("pierre");
            int A_paille = res.get("paille");
            int A_fer = res.get("fer");
            int A_Cristal = res.get("cristal");
            int cirque = building.get("cirque");
            EmbedBuilder builder = new EmbedBuilder();
            int leveleglise = building.get("eglise");
            int levelMine = building.get("mine");
            int sorcier = building.get("tour de sorcier");
            int bibliothèque = building.get("biblioth\u00e8que");
            int muraille = building.get("muraille");
            int armurerie = building.get("armurerie");
            int forge = building.get("forge");
            int centrale = building.get("centrale nucleaire");
            int leveltransport = building.get("transport");
            int trophées = data.getProfils().get(user.getId()).getTrophy();
            data.getProfils().get(user.getId()).setTrophy(trophées);
            try {
                total = marché + habitation + camp_entrainement + auberge + levelMine + sorcier + cirque + leveleglise
                        + muraille + leveltransport + bibliothèque * 5;
            } catch (NumberFormatException e) {
                total = 0;
            }
            int IDH = trophées / 25 + total + level + data.getProfils().get(user.getId()).getAp();
            try {
                data.getProfils().get(user.getId()).setIdh(IDH);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setIdh(IDH);
            }
            String thumbail = TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/logo.txt");

            if (!thumbail.equals("0")) {
                builder.setThumbnail("http://ozeryo.sytes.net/logos/" + thumbail + ".png");
            }
            String emoji = "";
            String nom = "";
            String nextupgrade = "";
            if (leveltransport == 0) {
                nom = "Autoroute";
                emoji = ":red_car:";
                nextupgrade = "Gare";
            } else if (leveltransport == 1) {
                nom = "Gare";
                emoji = ":train2:";
                nextupgrade = "Port";
            } else if (leveltransport == 2) {
                nom = "Port";
                emoji = ":ship:";
                nextupgrade = "Aeroport";
            } else if (leveltransport == 3) {
                nom = "Aeroport";
                emoji = ":airplane_departure:";
                nextupgrade = "Zone de Lancement";
            } else if (leveltransport == 4) {
                nom = "Zone de Lancement";
                emoji = ":rocket:";
                nextupgrade = "";
            }
            String titre = TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/city.txt");
            if (titre.equals("0")) {
                titre = user.getName() + "'s City";
            }
            builder.setAuthor(user.getName(), user.getAvatarUrl());
            builder.setColor(color.couleurAleatoire(user));
            builder.setFooter(guild.getName(), guild.getIconUrl());
            builder.setTitle(titre);
            builder.addField("Mana :heart:\ufe0f",
                    mana + " / " + Mana_Max + " mana (+1 mana / " + regen + " secondes)", true);
            builder.addField("Xp :star:", Utils.format(Game_EXP) + " EXP **(Level " + level + ")**",
                    true);
            if (lang == command.Language.fr) {
                builder.addField("Population \ud83d\udc65", Utils.format(habitant) + hopmess, true);
            }
            if (lang == command.Language.en) {
                builder.addField("People \ud83d\udc65", Utils.format(habitant) + hopmess, true);
            }
            builder.addField("IDH", "" + IDH, true);
            builder.addBlankField(false);
            if (lang == command.Language.fr) {
                builder.addField(":construction_site: | Construction ", ":convenience_store: **Habitations** : Tier "
                        + habitation + " \n" + ":hospital: **Auberge** : Tier " + auberge + " \n"
                        + ":office: **Marché** : Tier " + marché + " \n"
                        + ":department_store: **Camp d'entrainement** : Tier " + camp_entrainement + " \n"
                        + ":bear: **Cirque** : Tier " + cirque + " \n" + ":pick: **Mine** : Tier " + levelMine + " \n"
                        + ":microscope: **Tour de Sorcier** : Tier " + sorcier + "\n"
                        + ":classical_building: **Eglise** : Tier  " + eglise + " \n"
                        + ":japanese_castle:  **Muraille** : Tier  " + muraille + " \n"
                        + ":school: **Biblioth\u00e8que** : Tier  " + bibliothèque + "\n"
                        + "\ud83c\udfea **Armurerie** : Tier " + armurerie + " \n" + "\ud83d\udd28 **Forge** : Tier "
                        + forge + " \n" + emoji + " **" + nom + "** : Tier " + leveltransport + " (Next :" + nextupgrade
                        + ") \n" + "**Centrale Nuclaire** : Tier " + centrale, false);
            }
            if (lang == command.Language.en) {
                builder.addField(":construction_site: | Buildings ",
                        ":convenience_store: **Houses** : Tier " + habitation + " \n" + ":hospital: **Hostel** : Tier "
                                + auberge + " \n" + ":office: **Market Place** : Tier " + marché + " \n"
                                + ":department_store: **training camp** : Tier " + camp_entrainement + " \n"
                                + ":bear: **Circus** : Tier " + cirque + " \n" + ":pick: **Mine** : Tier " + levelMine
                                + " \n" + ":microscope: **Wizard Tower** : Tier " + sorcier + "\n"
                                + ":classical_building: **Church** : Tier  " + eglise + " \n"
                                + ":japanese_castle:  **Wall** : Tier  " + muraille + " \n"
                                + ":school: **Library** : Tier  " + bibliothèque + "\n"
                                + "\ud83c\udfea **Armory** : Tier " + armurerie + " \n"
                                + "\ud83d\udd28 **Wrought** : Tier " + forge + " \n" + emoji + " **" + nom
                                + "** : Tier " + leveltransport + " (Next :" + nextupgrade + ")\n"
                                + "**Centrale Nuclaire** : Tier " + centrale,
                        false);
            }
            builder.addBlankField(false);
            if (lang == command.Language.fr) {
                builder.addField(":factory: | Materiaux ", "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("wood", true).get(0).getAsMention()
                        + "** Bois** : " + Utils.format(A_bois) + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("clay", true).get(0).getAsMention()
                        + "** Argile :** " + Utils.format(A_argile) + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("leather", true).get(0).getAsMention()
                        + "** Cuir :** " + Utils.format(A_cuir) + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("stone", true).get(0).getAsMention()
                        + "** Pierre :** " + Utils.format(A_pierre) + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("straw", true).get(0).getAsMention()
                        + "** Paille :** " + Utils.format(A_paille) + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("iron", true).get(0).getAsMention()
                        + "** Fer :** " + Utils.format(A_fer) + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("iron", true).get(0).getAsMention()
                        + "** Cristal :** " + Utils.format(A_Cristal), false);
            }
            if (lang == command.Language.en) {
                builder.addField(":factory: | Materials ", "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("wood", true).get(0).getAsMention()
                        + "** Wood** : " + Utils.format(A_bois) + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("clay", true).get(0).getAsMention()
                        + "** Clay :** " + Utils.format(A_argile) + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("leather", true).get(0).getAsMention()
                        + "** Leather :** " + Utils.format(A_cuir) + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("stone", true).get(0).getAsMention()
                        + "** Stone :** " + Utils.format(A_pierre) + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("straw", true).get(0).getAsMention()
                        + "** Staw :** " + Utils.format(A_paille) + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("iron", true).get(0).getAsMention()
                        + "** Iron :** " + Utils.format(A_fer) + "\n"
                        + jda.getGuildById("326345972739473410").getEmotesByName("iron", true).get(0).getAsMention()
                        + "** Crystal :** " + Utils.format(A_Cristal), false);
            }
            builder.addBlankField(false);
            if (lang == command.Language.fr) {
                builder.addField("\u2139 | Info ",
                        ":house: **Habitations** = " + habitations + "\n" + "\u2694 **Force militaire** = "
                                + Utils.format(soldier) + " \n\ud83d\udcb0  **Money** = " + Utils.format(money)
                                + " $ \n\ud83d\uddfa  **Pays** = " + pays + " (*Level* " + Glevel
                                + ") \n:trophy: **Trophées** = " + Utils.format(trophées)
                                + " \n:house_with_garden: **Home** = " + home,
                        false);
            }
            if (lang == command.Language.en) {
                builder.addField("\u2139 | Info ",
                        ":house: **Residentials** = " + habitations + "\n" + "\u2694 **Military force** = "
                                + Utils.format(soldier) + " \n\ud83d\udcb0  **Money** = " + Utils.format(money)
                                + " $ \n\ud83d\uddfa  **Country** = " + pays + " (*Level* " + Glevel
                                + ") \n:trophy: **Trophies** = " + Utils.format(trophées)
                                + " \n:house_with_garden: **Home** = " + home,
                        false);
            }
            channel.sendMessage(builder.build()).queue();
            int tuto = data.getProfils().get(user.getId()).getTuto();
            if (tuto == 3) {
                data.getProfils().get(user.getId()).setTuto(4);
                if (lang == command.Language.fr) {
                    channel.sendMessage(
                            "Vous pouvez y voir votre exp, le niveau de vos constructions et vos réserves de ressources. Le mana, affiché en haut, vous permet de récolter des ressources \u00e0 l'aide de travail, essayez tout de suite avec =work all.")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(
                            "You can see your exp, the level of your builds, and your resource reserves. The mana, shown at the top, allows you to collect resources using work command, try now with =work all.")
                            .queue();
                }
            } else if (tuto == 6) {
                data.getProfils().get(user.getId()).setTuto(7);
                if (lang == command.Language.fr) {
                    channel.sendMessage(
                            "Chaque construction poss\u00e8de une vingtaine de niveaux, vous pourrez les monter de niveaux gr\u00e2ce aux Ozecoins et aux différents matériaux. Maintenant, apprenons \u00e0 attaquer, tout d'abord construisez votre camp d'entrainement, =b camp 1.")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(
                            "Each building has about twenty levels, you can build levels with Ozecoins and different materials. Now, learn to attack, first build your barracks, =b camp 1.")
                            .queue();
                }
            }
        } else if (c1.equals("logo")) {
            if (c2.equals("list")) {
                TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/logo/");
                File[] files = TextFileWriter
                        .folderlist("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/logo/");
                EmbedBuilder builder = new EmbedBuilder();
                for (File file : files) {
                    builder.addField(file.getName(), "=city logo " + file.getName(), true);
                }
                if (lang == command.Language.fr) {
                    builder.setTitle("Logo De ville");
                }
                if (lang == command.Language.en) {
                    builder.setTitle("City's logo");
                }
                builder.setColor(color.couleurAleatoire(user));
                builder.setFooter(guild.getName(), guild.getIconUrl());
                channel.sendMessage(builder.build()).queue();
            } else {
                String logo = TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/logo/" + c2);
                if (!logo.equals("0")) {
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/logo.txt", c2,
                            1);
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Votre logo est desormais le logo nommé " + c2).queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("Your logo is now " + c2).queue();
                    }
                } else {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Désolé mais vous n'avez pas debloqué ce logo.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("Sorry but you don't unlocked this logo").queue();
                    }
                }
            }
        } else if (c1.equals("rename")) {
            String name = c3;
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/city.txt", name, 1);
            if (lang == command.Language.fr) {
                channel.sendMessage("Le nouveau nom de votre ville est : " + name).queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("Your new city's name is : " + name).queue();
            }
        }
    }
}
