/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import fr.Ybsi.OzeryoBot.Utils.Quest;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Factory {
    @command(name = "tower", abbrev = "labo", type = command.ExecutorType.ALL, topic = command.Topics.Game)
    private void usine(Message message, Guild guild, String[] args, User user, MessageChannel channel, String arg,
                       ProfilData data, command.Language lang) {
        String c1;
        String c2;
        HashMap<String, Integer> building = data.getProfils().get(user.getId()).getBuilding();
        int level = building.get("tour de sorcier");
        if (level == 0) {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez construire votre laboratoire pour pouvoir acceder a cette commande.")
                        .queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must build you laboratoire to use this command.").queue();
            }
            return;
        }
        int Bonus_factory = 75;
        switch (level) {
            case 1: {
                Bonus_factory = 98;
                break;
            }
            case 2: {
                Bonus_factory = 120;
                break;
            }
            case 3: {
                Bonus_factory = 142;
                break;
            }
            case 4: {
                Bonus_factory = 165;
                break;
            }
            case 5: {
                Bonus_factory = 188;
                break;
            }
            case 6: {
                Bonus_factory = 225;
                break;
            }
            case 7: {
                Bonus_factory = 263;
                break;
            }
            case 8: {
                Bonus_factory = 300;
                break;
            }
            case 9: {
                Bonus_factory = 338;
                break;
            }
            case 10: {
                Bonus_factory = 375;
                break;
            }
            case 11: {
                Bonus_factory = 413;
                break;
            }
            case 12: {
                Bonus_factory = 450;
                break;
            }
            case 13: {
                Bonus_factory = 488;
                break;
            }
            case 14: {
                Bonus_factory = 525;
                break;
            }
            case 15: {
                Bonus_factory = 563;
                break;
            }
            case 16: {
                Bonus_factory = 600;
                break;
            }
            case 17: {
                Bonus_factory = 638;
                break;
            }
            case 18: {
                Bonus_factory = 676;
                break;
            }
            case 19: {
                Bonus_factory = 713;
                break;
            }
            case 20: {
                Bonus_factory = 750;
                break;
            }
            default: {
                Bonus_factory = 750;
                for (int i = 20; i < level; ++i) {
                    Bonus_factory = (int) ((double) Bonus_factory * 1.205);
                }
            }
        }
        String etat = TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory.txt");
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
        if (c1.equals("start")) {
            if (etat.equals("broken")) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous ne pouvez pas demarrer votre laboratoire car celui ci est detruit")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You can't start your laboratoire because is destroyed").queue();
                }
            } else if (etat.equals("active")) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(":telescope: Vous \u00eates déj\u00e0 en train de faire des recherches.")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(":telescope: You are already doing researches.").queue();
                }
            } else {
                TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory/");
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory.txt",
                        "active", 1);
                int secondes = Integer.parseInt(new SimpleDateFormat("ss", Locale.FRANCE).format(new Date()));
                int minutes = Integer.parseInt(new SimpleDateFormat("mm", Locale.FRANCE).format(new Date()));
                int heures = Integer.parseInt(new SimpleDateFormat("HH", Locale.FRANCE).format(new Date()));
                int jours = Integer.parseInt(new SimpleDateFormat("dd", Locale.FRANCE).format(new Date()));
                TextFileWriter.write(
                        "/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory/secondes.txt",
                        Integer.toString(secondes), 1);
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory/minutes.txt",
                        Integer.toString(minutes), 1);
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory/heures.txt",
                        Integer.toString(heures), 1);
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory/jours.txt",
                        Integer.toString(jours), 1);
                if (lang == command.Language.fr) {
                    channel.sendMessage(":alembic: Vous venez de débuter vos recherches.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(":alembic: You just start your researches.").queue();
                }
            }
        } else if (c1.equals("claim")) {
            TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory/");
            String Lsecondes = TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory/secondes.txt");
            String Lminutes = TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory/minutes.txt");
            String Lheures = TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory/heures.txt");
            String Ljours = TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory/jours.txt");
            if (Lsecondes == null) {
                Lsecondes = Integer.toString(0);
            }
            if (Lminutes == null) {
                Lminutes = Integer.toString(0);
            }
            if (Lheures == null) {
                Lheures = Integer.toString(0);
            }
            if (Ljours == null) {
                Ljours = Integer.toString(0);
            }
            int secondes = Integer.parseInt(new SimpleDateFormat("ss", Locale.FRANCE).format(new Date()));
            int minutes = Integer.parseInt(new SimpleDateFormat("mm", Locale.FRANCE).format(new Date()));
            int heures = Integer.parseInt(new SimpleDateFormat("HH", Locale.FRANCE).format(new Date()));
            int jours = Integer.parseInt(new SimpleDateFormat("dd", Locale.FRANCE).format(new Date()));
            int Nsecondes = secondes - Integer.parseInt(Lsecondes);
            int Nminutes = minutes - Integer.parseInt(Lminutes);
            int Nheures = heures - Integer.parseInt(Lheures);
            int Njours = jours - Integer.parseInt(Ljours);
            if (Nsecondes < 0) {
                Nsecondes += 60;
                --Nminutes;
            }
            if (Nminutes < 0) {
                Nminutes += 60;
                --Nheures;
            }
            if (Nheures < 0) {
                Nheures += 24;
                --Njours;
            }
            if ((Nheures += 24 * Njours) < 0) {
                Nheures = 4;
            }
            if (etat.equals("active") && Nheures == 4) {
                int Pet_EXP;
                ArrayList<String> list;
                int Scientifique = data.getProfils().get(user.getId()).getScientifique();
                data.getProfils().get(user.getId()).setScientifique(++Scientifique);
                Quest.Quest("exp", data.getProfils().get(user.getId()), channel, Bonus_factory);
                String pet = TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/pet.txt");
                HashMap<String, ArrayList<String>> activePet = data.getProfils().get(user.getId()).getPet();
                try {
                    list = data.getProfils().get(user.getId()).getPet().get(pet);
                } catch (NullPointerException e) {
                    list = null;
                }
                try {
                    Pet_EXP = Integer.parseInt(data.getProfils().get(user.getId()).getPet().get(pet).get(1));
                } catch (NullPointerException e) {
                    Pet_EXP = 0;
                }
                if (!pet.equals("0")) {
                    Pet_EXP += Bonus_factory / 10;
                }
                if (list != null) {
                    list.remove(1);
                }
                if (list != null) {
                    list.add(1, String.valueOf(Pet_EXP));
                }
                if (list != null) {
                    activePet.put(pet, list);
                }
                try {
                    data.getProfils().get(user.getId()).setPet(activePet);
                } catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setPet(activePet);
                }
                int mana = data.getProfils().get(user.getId()).getMana();
                int gain = (int) ((double) level * 2.5);
                mana += gain;
                try {
                    data.getProfils().get(user.getId()).setMana(mana);
                } catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setMana(mana);
                }
                if (lang == command.Language.fr) {
                    channel.sendMessage(":microscope: Apr\u00e8s des heures de recherche, vous venez de découvrir **"
                            + Bonus_factory + "** EXP ainsi que **" + gain + "** mana.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(":microscope: After hours of researches, you just discover **" + Bonus_factory
                            + "** Xp and **" + gain + "** mana.").queue();
                }
                int Game_EXP = data.getProfils().get(user.getId()).getXp();
                Game_EXP += Bonus_factory;
                try {
                    data.getProfils().get(user.getId()).setXp(Game_EXP);
                } catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setXp(Game_EXP);
                }
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory.txt", "idle",
                        1);
            } else if (etat.equals("active") && Nheures > 4) {
                channel.sendMessage(
                        ":boom: Votre laboratoire vient d'exploser ! Il sera de nouveau utilisable dans 24 heures.")
                        .queue();
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory.txt",
                        "broken", 1);
            } else if (etat.equals("active") && Nheures < 4) {
                Nheures = 3 - Nheures;
                Nminutes = 60 - Nminutes;
                Nsecondes = 60 - Nsecondes;
                if (lang == command.Language.fr) {
                    channel.sendMessage(":telescope: Vous pourrez récupérer le résultat de vos expériences dans **"
                            + Nheures + "** heures et **" + Nminutes + "** minutes. ").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(":telescope: You will be able to discover the result of your researches in **"
                            + Nheures + "** hours and **" + Nminutes + "** minutes. ").queue();
                }
            } else {
                if (lang == command.Language.fr) {
                    channel.sendMessage(":telescope: Votre laboratoire n'est pas actif.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(":telescope: Your laboratory is not active").queue();
                }
            }
        } else if (c1.equals("repair")) {
            if (etat.equals("broken")) {
                TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/structure/");
                TextFileWriter.CreateFile(
                        "/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/structure/industrie.txt");
                System.out.println("1");
                long money = data.getProfils().get(user.getId()).getMoney();
                System.out.println("2");
                int level_up = level + 1;
                System.out.println("3");
                int need_mat = 0;
                long need_money = 0L;
                switch (level_up) {
                    case 1: {
                        need_money = 25000L;
                        need_mat = 5;
                        break;
                    }
                    case 2: {
                        need_money = 37500L;
                        need_mat = 6;
                        break;
                    }
                    case 3: {
                        need_money = 56000L;
                        need_mat = 8;
                        break;
                    }
                    case 4: {
                        need_money = 85000L;
                        need_mat = 10;
                        break;
                    }
                    case 5: {
                        need_money = 125000L;
                        need_mat = 12;
                        break;
                    }
                    case 6: {
                        need_money = 190000L;
                        need_mat = 15;
                        break;
                    }
                    case 7: {
                        need_money = 285000L;
                        need_mat = 20;
                        break;
                    }
                    case 8: {
                        need_money = 420000L;
                        need_mat = 24;
                        break;
                    }
                    case 9: {
                        need_money = 640000L;
                        need_mat = 30;
                        break;
                    }
                    case 10: {
                        need_money = 960000L;
                        need_mat = 37;
                        break;
                    }
                    case 11: {
                        need_money = 1450000L;
                        need_mat = 46;
                        break;
                    }
                    case 12: {
                        need_money = 2150000L;
                        need_mat = 58;
                        break;
                    }
                    case 13: {
                        need_money = 3250000L;
                        need_mat = 73;
                        break;
                    }
                    case 14: {
                        need_money = 5000000L;
                        need_mat = 91;
                        break;
                    }
                    case 15: {
                        need_money = 7300000L;
                        need_mat = 114;
                        break;
                    }
                    case 16: {
                        need_money = 11000000L;
                        need_mat = 142;
                        break;
                    }
                    case 17: {
                        need_money = 16500000L;
                        need_mat = 177;
                        break;
                    }
                    case 18: {
                        need_money = 25000000L;
                        need_mat = 222;
                        break;
                    }
                    case 19: {
                        need_money = 37000000L;
                        need_mat = 277;
                        break;
                    }
                    case 20: {
                        need_money = 55000000L;
                        need_mat = 350;
                        break;
                    }
                    default: {
                        need_money = 550000000L;
                        need_mat = 3500;
                        for (int i = 20; i < level_up; ++i) {
                            need_money = (long) ((double) need_money * 1.5);
                            need_mat = (int) ((double) need_mat * 1.25);
                        }
                        need_money /= 10L;
                        need_mat /= 10;
                    }
                }
                if (c2.equals("confirm")) {
                    HashMap<String, Integer> res = data.getProfils().get(user.getId()).getRes();
                    int pierre = res.get("pierre");
                    int paille = res.get("paille");
                    int acier = res.get("argile");
                    if (money >= need_money && acier >= need_mat && pierre >= need_mat && paille >= need_mat) {
                        money -= need_money;
                        pierre -= need_mat;
                        acier -= need_mat;
                        paille -= need_mat;
                        try {
                            data.getProfils().get(user.getId()).setMoney(money);
                        } catch (NullPointerException e) {
                            data.getProfils().put(user.getId(), new Profil(user.getId()));
                            data.getProfils().get(user.getId()).setMoney(money);
                        }
                        res.put("argile", acier);
                        res.put("pierre", pierre);
                        res.put("paille", paille);
                        try {
                            data.getProfils().get(user.getId()).setRes(res);
                        } catch (NullPointerException e) {
                            data.getProfils().put(user.getId(), new Profil(user.getId()));
                            data.getProfils().get(user.getId()).setRes(res);
                        }
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory.txt",
                                "idle", 1);
                        if (lang == command.Language.fr) {
                            channel.sendMessage(":microscope: Votre laboratoire a bien été reparé.").queue();
                        }
                        if (lang == command.Language.en) {
                            channel.sendMessage(":microscope: Your laboratory has been repaired.").queue();
                        }
                    } else {
                        if (lang == command.Language.fr) {
                            channel.sendMessage(
                                    ":tools: Vous n'avez pas les matériaux nécessaires pour réparer votre laboratoire.")
                                    .queue();
                        }
                        if (lang == command.Language.en) {
                            channel.sendMessage(
                                    ":tools: You don't have the necessary materials to repair your laboratory.")
                                    .queue();
                        }
                    }
                } else {
                    TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory/");
                    String Lsecondes = TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory/secondes.txt");
                    String Lminutes = TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory/minutes.txt");
                    String Lheures = TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory/heures.txt");
                    String Ljours = TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory/jours.txt");
                    if (Lsecondes == null) {
                        Lsecondes = Integer.toString(0);
                    }
                    if (Lminutes == null) {
                        Lminutes = Integer.toString(0);
                    }
                    if (Lheures == null) {
                        Lheures = Integer.toString(0);
                    }
                    if (Ljours == null) {
                        Ljours = Integer.toString(0);
                    }
                    int secondes = Integer.parseInt(new SimpleDateFormat("ss", Locale.FRANCE).format(new Date()));
                    int minutes = Integer.parseInt(new SimpleDateFormat("mm", Locale.FRANCE).format(new Date()));
                    int heures = Integer.parseInt(new SimpleDateFormat("HH", Locale.FRANCE).format(new Date()));
                    int jours = Integer.parseInt(new SimpleDateFormat("dd", Locale.FRANCE).format(new Date()));
                    int Nsecondes = secondes - Integer.parseInt(Lsecondes);
                    int Nminutes = minutes - Integer.parseInt(Lminutes);
                    int Nheures = heures - Integer.parseInt(Lheures);
                    int Njours = jours - Integer.parseInt(Ljours);
                    if (Nsecondes < 0) {
                        Nsecondes += 60;
                        --Nminutes;
                    }
                    if (Nminutes < 0) {
                        Nminutes += 60;
                        --Nheures;
                    }
                    if (Nheures < 0) {
                        Nheures += 24;
                        --Njours;
                    }
                    if (etat.equals("broken") && (Nheures += 24 * Njours) >= 28
                            || etat.equals("broken") && Nheures < 0) {
                        if (lang == command.Language.fr) {
                            channel.sendMessage(":microscope: Votre laboratoire a été reparé.").queue();
                        }
                        if (lang == command.Language.en) {
                            channel.sendMessage(":microscope: Your laboratoire has been repaired.").queue();
                        }
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/factory.txt",
                                "idle", 1);
                    } else {
                        Nheures = 27 - Nheures;
                        Nminutes = 60 - Nminutes;
                        Nsecondes = 60 - Nsecondes;
                        if (lang == command.Language.fr) {
                            channel.sendMessage(":hammer_pick: Votre laboratoire sera réparé dans **" + Nheures
                                    + "** heures et **" + Nminutes + "** minutes.\r\n"
                                    + ":tools: Vous pouvez tout de m\u00eame appeler une équipe de spécialistes pour **"
                                    + need_money + "** money ainsi que **" + need_mat + "** pierre, **" + need_mat
                                    + "** argile et **" + need_mat + "** paille.").queue();
                        }
                        if (lang == command.Language.en) {
                            channel.sendMessage(":hammer_pick: You laboratory will be repair in **" + Nheures
                                    + "** hours and **" + Nminutes + "** minutes.\r\n"
                                    + ":tools: You can call a team of specialists for **" + need_money
                                    + "** money and **" + need_mat + "** stone, **" + need_mat + "** clay et **"
                                    + need_mat + "** straw.").queue();
                        }
                    }
                }
            } else {
                if (lang == command.Language.fr) {
                    channel.sendMessage(":telescope: Votre laboratoire est déj\u00e0 en parfait état.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(":telescope: You laboratory is already in good condition.").queue();
                }
            }
        } else {
            channel.sendMessage("=labo ``claim``, ``start``, ``repair``").queue();
        }
    }
}
