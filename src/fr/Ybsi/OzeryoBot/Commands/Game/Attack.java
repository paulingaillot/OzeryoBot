/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.CommandMap;
import fr.Ybsi.OzeryoBot.Commands.Game.Heroe;
import fr.Ybsi.OzeryoBot.Commands.Game.habitants;
import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import fr.Ybsi.OzeryoBot.Utils.Quest;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.impl.UserImpl;
import net.dv8tion.jda.core.requests.RestAction;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class Attack {
    @command(name = "attack", abbrev = "a", type = command.ExecutorType.ALL, topic = command.Topics.Game)
    private void attack(Message message, Guild guild, String[] args, User user, MessageChannel channel, String arg,
            JDA jda, command.Language lang) {
        int Pet_EXP;
        String c1;
        ArrayList<String> listpet;
        ProfilData data = DiscordBot.getData();
        int tuto = data.getProfils().get(user.getId()).getTuto();
        if (tuto == 9) {
            data.getProfils().get(user.getId()).setTrophy(20);
            data.getProfils().get(user.getId()).setMoney(data.getProfils().get(user.getId()).getMoney() + 10000L);
            data.getProfils().get(user.getId()).setTuto(10);
            if (lang == command.Language.fr) {
                channel.sendMessage(
                        "Bravo ! Vous venez de gagner ! Vous remportez 20 trophées ainsi que 10000 Ozecoins. Vous \u00eates dorénavant pr\u00eat pour commencer votre aventure sur le bot ! Bonne chance \u00e0 vous aventurier :grinning:")
                        .queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage(
                        "Well ! You just win ! You won 20 trophies and 10000 Ozecoins. You are now ready to start your adventure on the bot ! Good luck to you adventurer  :grinning:")
                        .queue();
            }
            return;
        }
        try {
            c1 = args[0];
        } catch (IndexOutOfBoundsException e) {
            if (lang == command.Language.fr) {
                channel.sendMessage(
                        "Afin d'attaquer veuillez mentionner ou mettre l'id de la personne que vous souhaitez attaquer.")
                        .queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage(
                        "To attack a player please mention or put the id or the location of the person you want to attack.")
                        .queue();
            }
            return;
        }
        int x = 0;
        int y = 0;
        User cible = null;
        try {
            cible = message.getMentionedUsers().get(0);
        } catch (IndexOutOfBoundsException e) {
            try {
                cible = jda.getUserById(c1);
            } catch (NumberFormatException e1) {
                cible = null;
            }
        }
        if (cible == null) {
            try {
                x = Integer.parseInt(args[0]);
                y = Integer.parseInt(args[1]);
            } catch (IndexOutOfBoundsException e) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(
                            "Afin d'attaquer veuillez mentionner ou mettre l'id de la personne que vous souhaitez attaquer.")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(
                            "To attack a player please mention or put the id or the location of the person you want to attack.")
                            .queue();
                }
                return;
            }
            try {
                cible = jda.getUserById(
                        TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt"));
            } catch (NumberFormatException e) {
                // empty catch block
            }
        }
        String ActivePet = TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/pet.txt");
        HashMap<String, ArrayList<String>> pet = data.getProfils().get(user.getId()).getPet();
        try {
            listpet = pet.get(ActivePet);
        } catch (NullPointerException e) {
            listpet = null;
        }
        try {
            Pet_EXP = Integer.parseInt(listpet.get(1));
        } catch (NullPointerException e) {
            Pet_EXP = 0;
        }
        String Pet_Bonus = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pets/" + ActivePet);
        double operationpet = Pet_EXP / 10;
        double operationpet2 = Math.sqrt(operationpet);
        double Pet_Level = Math.round(operationpet2);
        double pet_bonus = 1.0 + 0.03 * Pet_Level;
        if (cible == null) {
            long duree;
            String name;
            try {
                name = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt");
            } catch (Exception e) {
                name = "0";
            }
            if (name.equals("0")) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Il n'y a rien a cet endroit alors pourquoi l'attaquer ?").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("There is anything here so why would you like to attack on this location ?")
                            .queue();
                }
                return;
            }
            if (name.equals("dungeon")) {
                HashMap<String, ArrayList<String>> heroe;
                long duree2;
                ArrayList list;
                try {
                    heroe = data.getProfils().get(user.getId()).getHeroe();
                } catch (NullPointerException e) {
                    heroe = new HashMap();
                    list = new ArrayList();
                    list.add("1");
                    list.add("0");
                    list.add("false");
                    list.add("0");
                    heroe.put("Karl", list);
                    heroe.put("Valkyrie", null);
                    heroe.put("Ouranos", null);
                    heroe.put("Oeil", null);
                    heroe.put("Ikaryus", null);
                    heroe.put("Yegarde", null);
                    heroe.put("Angel", null);
                    heroe.put("Zhen", null);
                    heroe.put("Hearth", null);
                    heroe.put("Lixie", null);
                    heroe.put("Akashi", null);
                    heroe.put("Rose", null);
                    heroe.put("Hell", null);
                    heroe.put("Spirita", null);
                    heroe.put("Tempest", null);
                    heroe.put("Ivoire", null);
                }
                String activeHero = data.getProfils().get(user.getId()).getActiveHeroe();
                try {
                    list = (ArrayList) heroe.get(activeHero);
                } catch (NullPointerException e) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Vous n'avez actuelement aucun hero selectionner. Commencez d'abord par en selectionner un a l'aide de la commande ``=hero select`` si vous voulez attaquer avec.")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage(
                                "You don't have any hero selected. Try first to select a hero with the command ``=hero select`` if you want to attack with it.")
                                .queue();
                    }
                    return;
                }
                boolean heroAtk = ((String) list.get(2)).equals("true");
                if (heroAtk) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Votre hero est deja en attaque, vous ne pouvez pas l'utiliser actuelement.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You hero is already in attack , you can't use it actually.").queue();
                    }
                    return;
                }
                String heroAtk1 = "true";
                int pv = Integer.parseInt((String) list.get(3));
                list.set(2, heroAtk1);
                list.set(3, Integer.toString(pv));
                list.set(4, Long.toString(System.currentTimeMillis()));
                String userHome = data.getProfils().get(user.getId()).getHome();
                String[] strU = userHome.split("_");
                int xU = Integer.parseInt(strU[0]);
                int yU = Integer.parseInt(strU[1]);
                int xC = x;
                int yC = y;
                double operation = Math.pow(xC - xU, 2.0) + Math.pow(yC - yU, 2.0);
                System.out.println(operation);
                double durée = Math.sqrt(operation);
                System.out.println();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis((long) (durée * 3600000.0));
                int heure = calendar.get(11) - 1;
                int minutes = calendar.get(12);
                long bonus = 0L;
                if (Pet_Bonus.equals("speed")) {
                    bonus = (long) (60000.0 * pet_bonus);
                }
                if ((duree2 = (long) (durée * 3600000.0 - (double) bonus)) <= 0L) {
                    duree2 = 1L;
                }
                long DateFin = System.currentTimeMillis() + duree2;
                String cible2 = String.valueOf(x) + "_" + y;
                ArrayList<String> list1 = new ArrayList<String>();
                list1.add(Long.toString(DateFin));
                list1.add(cible2);
                list1.add(Integer.toString(0));
                list1.add("true");
                HashMap<Long, ArrayList<String>> map = new HashMap();
                try {
                    map = data.getProfils().get(user.getId()).getAttack();
                    map.put(DateFin, list1);
                } catch (NullPointerException e) {
                    map = new HashMap();
                    System.out.println(DateFin);
                    System.out.println(list1);
                    map.put(DateFin, list1);
                }
                data.getProfils().get(user.getId()).setAttack(map);
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous venez d'attaquer le donjon en " + x + "," + y
                            + ". Votre hero arrivera a destination dans " + heure + "h" + minutes
                            + "m. Un message privée vous sera envoyé a la fin de la bataille.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You just attack the dungeon in " + x + "," + y
                            + ". Your hero will arrive to this point in  " + heure + "h" + minutes
                            + "m. A private message will be send to you when the fight will stop.").queue();
                }
                return;
            }
            String cible2 = String.valueOf(x) + "_" + y;
            int atk = 0;
            try {
                atk = Integer.parseInt(args[2]);
            } catch (IndexOutOfBoundsException e) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Veuillez indiquer un nombre de soldats correct.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Please type a valid  soldier number.").queue();
                }
                return;
            } catch (NumberFormatException e) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Veuillez indiquer un nombre de soldats correct.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Please type a valid  soldier number.").queue();
                }
                return;
            }
            if (atk < 0) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Veuillez indiquer un nombre de soldats correct.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Please type a valid  soldier number.").queue();
                }
                return;
            }
            String c3 = "";
            try {
                c3 = args[3];
            } catch (IndexOutOfBoundsException heroAtk) {
                // empty catch block
            }
            String hero = "false";
            if (c3.equals("hero")) {
                hero = "true";
            }
            if (hero.equals("true")) {
                ArrayList list;
                HashMap<String, ArrayList<String>> heroe;
                try {
                    heroe = data.getProfils().get(user.getId()).getHeroe();
                } catch (NullPointerException e) {
                    heroe = new HashMap();
                    list = new ArrayList();
                    list.add("1");
                    list.add("0");
                    list.add("false");
                    list.add("0");
                    heroe.put("Karl", list);
                    heroe.put("Valkyrie", null);
                    heroe.put("Ouranos", null);
                    heroe.put("Oeil", null);
                    heroe.put("Ikaryus", null);
                    heroe.put("Yegarde", null);
                    heroe.put("Angel", null);
                    heroe.put("Zhen", null);
                    heroe.put("Hearth", null);
                    heroe.put("Lixie", null);
                    heroe.put("Akashi", null);
                    heroe.put("Rose", null);
                    heroe.put("Hell", null);
                    heroe.put("Spirita", null);
                    heroe.put("Tempest", null);
                    heroe.put("Ivoire", null);
                }
                String activeHero = data.getProfils().get(user.getId()).getActiveHeroe();
                try {
                    list = (ArrayList) heroe.get(activeHero);
                } catch (NullPointerException e) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Vous n'avez actuelement aucun hero selectionner. Commencez d'abord par en selectionner un a l'aide de la commande ``=hero select`` si vous voulez attaquer avec.")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage(
                                "You don't have any hero selected. Try first to select a hero with the command ``=hero select`` if you want to attack with it.")
                                .queue();
                    }
                    return;
                }
                boolean heroAtk = ((String) list.get(2)).equals("true");
                if (heroAtk) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Votre hero est deja en attaque, vous ne pouvez pas l'utiliser actuelement.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("Your hero is already in attack, you can't use it actually.").queue();
                    }
                    return;
                }
                String heroAtk1 = "true";
                int pv = Integer.parseInt((String) list.get(3));
                list.set(2, heroAtk1);
                list.set(3, Integer.toString(pv));
                list.set(4, Long.toString(System.currentTimeMillis()));
            }
            String userHome = data.getProfils().get(user.getId()).getHome();
            String[] strU = userHome.split("_");
            int xU = Integer.parseInt(strU[0]);
            int yU = Integer.parseInt(strU[1]);
            int xC = x;
            int yC = y;
            double operation = Math.pow(xC - xU, 2.0) + Math.pow(yC - yU, 2.0);
            System.out.println(operation);
            double durée = Math.sqrt(operation);
            System.out.println(durée);
            long bonus = 0L;
            if (Pet_Bonus.equals("spped")) {
                bonus = (long) (60000.0 * pet_bonus);
            }
            if ((duree = (long) (durée * 3600000.0 - (double) bonus)) <= 0L) {
                duree = 1L;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(duree);
            int heure = calendar.get(11) - 1;
            int minutes = calendar.get(12);
            long DateFin = System.currentTimeMillis() + duree;
            ArrayList<String> list = new ArrayList<String>();
            list.add(Long.toString(DateFin));
            list.add(cible2);
            list.add(Integer.toString(atk));
            list.add(hero);
            HashMap<Long, ArrayList<String>> map = new HashMap();
            try {
                map = data.getProfils().get(user.getId()).getAttack();
                System.out.println(DateFin);
                System.out.println(list);
                map.put(DateFin, list);
            } catch (NullPointerException e) {
                map = new HashMap();
                System.out.println(DateFin);
                System.out.println(list);
                map.put(DateFin, list);
            }
            long soldier = data.getProfils().get(user.getId()).getSoldiers();
            if ((long) atk > soldier) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous ne pouvez pas attaquer avec plus de soldats que vous n'en avez.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You can't attack with more soldiers you actually have.").queue();
                }
                return;
            }
            if (atk <= 0) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Veuillez indiquer un nombre valide de soldats.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Please type a valid soldiers number.").queue();
                }
            }
            soldier -= (long) atk;
            try {
                data.getProfils().get(user.getId()).setSoldiers(soldier);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setSoldiers(soldier);
            }
            try {
                data.getProfils().get(user.getId()).setAttack(map);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAttack(map);
            }
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous venez d'attaquer en " + x + "," + y + " avec " + atk
                        + " soldats. Vos soldats arriveront a destination dans " + heure + "h" + minutes
                        + "m. Un message privée vous sera envoyé a la fin de la bataille.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You just attack in " + x + "," + y + " with " + atk
                        + " soldiers. Your soldiers will arive at this point in " + heure + "h" + minutes
                        + "m. A private message will be send you when the fight will stop.").queue();
            }
        } else {
            long duree;
            int atk = 0;
            String c3 = "";
            for (String str : args) {
                try {
                    atk = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    c3 = str;
                }
            }
            String hero = "false";
            if (c3.equals("hero")) {
                hero = "true";
            }
            if (hero.equals("true")) {
                HashMap<String, ArrayList<String>> heroe;
                ArrayList list;
                try {
                    heroe = data.getProfils().get(user.getId()).getHeroe();
                } catch (NullPointerException e) {
                    heroe = new HashMap();
                    list = new ArrayList();
                    list.add("1");
                    list.add("0");
                    list.add("false");
                    list.add("0");
                    heroe.put("Karl", list);
                    heroe.put("Valkyrie", null);
                    heroe.put("Ouranos", null);
                    heroe.put("Oeil", null);
                    heroe.put("Ikaryus", null);
                    heroe.put("Yegarde", null);
                    heroe.put("Angel", null);
                    heroe.put("Zhen", null);
                    heroe.put("Hearth", null);
                    heroe.put("Lixie", null);
                    heroe.put("Akashi", null);
                    heroe.put("Rose", null);
                    heroe.put("Hell", null);
                    heroe.put("Spirita", null);
                    heroe.put("Tempest", null);
                    heroe.put("Ivoire", null);
                }
                String activeHero = data.getProfils().get(user.getId()).getActiveHeroe();
                try {
                    list = (ArrayList) heroe.get(activeHero);
                } catch (NullPointerException e) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Vous n'avez actuelement aucun hero selectionner. Commencez d'abord par enselectionner un a l'aide de la commande ``=hero select`` si vous voulez attaquer avec.")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage(
                                "You don't have any hero selected. Try first to select a hero with the command ``=hero select`` if you want to attack with it.")
                                .queue();
                    }
                    return;
                }
                boolean heroAtk = ((String) list.get(2)).equals("true");
                if (heroAtk) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Votre hero est deja en attaque, vous ne pouvez pas l'utiliser actuelement. ").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("Your hero is already in attack, You can't use it actually.").queue();
                    }
                    return;
                }
                String heroAtk1 = "true";
                int pv = Integer.parseInt((String) list.get(3));
                list.set(2, heroAtk1);
                list.set(3, Integer.toString(pv));
                list.set(4, Long.toString(System.currentTimeMillis()));
            }
            if (user == cible) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous ne pouvez pas vous auto-attaquer.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You can't attack your own city.").queue();
                }
                return;
            }
            if (atk == 0) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Serieux tu veux faire une mission suicide ?").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You can't attack with any soldiers").queue();
                }
                return;
            }
            if (atk < 0) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(
                            "Je savais pas que dnas la vraie vie on pouvais faire des attaques avec un nombre negatif de soldat ...")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Please type a positive soldiers number.").queue();
                }
                return;
            }
            HashMap<String, Integer> building = data.getProfils().get(user.getId()).getBuilding();
            int caserne = building.get("camp d'entrainement");
            if (caserne == 0) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(
                            "Vous ne pouvez pas attaquer un joueurs si vous n'avez pas construit votre caserne.")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You can't attack a player if you didn't build your barrack.").queue();
                }
                return;
            }
            HashMap<String, Integer> building1 = data.getProfils().get(cible.getId()).getBuilding();
            int Ccaserne = building1.get("camp d'entrainement");
            if (Ccaserne == 0) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous ne pouvez pas attaquer un joueurs qui n'a pas construit sa caserne.")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You can't attack a player who didn't build his barrack.").queue();
                }
                return;
            }
            long ATKmax = habitants.atk(user);
            if ((long) atk > ATKmax) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Desolé vous n'avez pas asser de soldats.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Sorry but you don't have enough soldiers.").queue();
                }
                return;
            }
            long soldier = ATKmax - (long) atk;
            try {
                data.getProfils().get(user.getId()).setSoldiers(soldier);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setSoldiers(soldier);
            }
            String userHome = data.getProfils().get(user.getId()).getHome();
            String[] strU = userHome.split("_");
            int xU = Integer.parseInt(strU[0]);
            int yU = Integer.parseInt(strU[1]);
            String cibleHome = data.getProfils().get(cible.getId()).getHome();
            String[] strC = cibleHome.split("_");
            int xC = Integer.parseInt(strC[0]);
            int yC = Integer.parseInt(strC[1]);
            double operation = Math.pow(xC - xU, 2.0) + Math.pow(yC - yU, 2.0);
            System.out.println(operation);
            double durée = Math.sqrt(operation);
            System.out.println(durée);
            long bonus = 0L;
            if (Pet_Bonus.equals("speed")) {
                bonus = (long) (60000.0 * pet_bonus);
            }
            if ((duree = (long) (durée * 3600000.0 - (double) bonus)) <= 0L) {
                duree = 1L;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(duree);
            int heure = calendar.get(11) - 1;
            int minutes = calendar.get(12);
            long DateFin = System.currentTimeMillis() + duree;
            ArrayList<String> list = new ArrayList<String>();
            list.add(Long.toString(DateFin));
            list.add(cible.getId());
            list.add(Integer.toString(atk));
            list.add(hero);
            HashMap<Long, ArrayList<String>> map = new HashMap<Long, ArrayList<String>>();
            try {
                map = data.getProfils().get(user.getId()).getAttack();
                map.put(DateFin, list);
            } catch (NullPointerException e) {
                map = new HashMap();
                map.put(DateFin, list);
            }
            try {
                data.getProfils().get(user.getId()).setAttack(map);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setAttack(map);
            }
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous venez d'attaquer " + cible.getName() + " avec " + atk
                        + " soldats. Vos soldats arriveront a destination dans " + heure + "h+" + minutes
                        + "m. Un message privée vous sera envoyé a la fin de la bataille.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You just attack " + cible.getName() + " with " + atk
                        + " soldiers. Your soldiers will arrive at this point in " + heure + "h+" + minutes
                        + "m. A private message will be send you when the fight will stop.").queue();
            }
        }
    }

    @command(name = "trophees", type = command.ExecutorType.ALL, topic = command.Topics.Game)
    private void trophees(Message message, Guild guild, String[] args, User user, MessageChannel channel,
            ProfilData data, command.Language lang) {
        int trophéesP = data.getProfils().get(user.getId()).getTrophy();
        String rank = null;
        if (trophéesP < 100) {
            rank = "Bois V";
        } else if (trophéesP >= 100 && trophéesP < 200) {
            rank = "Bois IV";
        } else if (trophéesP >= 200 && trophéesP < 300) {
            rank = "Bois III";
        } else if (trophéesP >= 300 && trophéesP < 400) {
            rank = "Bois II";
        } else if (trophéesP >= 400 && trophéesP < 500) {
            rank = "Bois I";
        } else if (trophéesP >= 500 && trophéesP < 700) {
            rank = "Bronze V";
        } else if (trophéesP >= 700 && trophéesP < 900) {
            rank = "Bronze IV";
        } else if (trophéesP >= 900 && trophéesP < 1100) {
            rank = "Bronze III";
        } else if (trophéesP >= 1100 && trophéesP < 1300) {
            rank = "Bronze II";
        } else if (trophéesP >= 1300 && trophéesP < 1500) {
            rank = "Bronze I";
        } else if (trophéesP >= 1500 && trophéesP < 1800) {
            rank = "Argent V";
        } else if (trophéesP >= 1800 && trophéesP < 2100) {
            rank = "Argent IV";
        } else if (trophéesP >= 2100 && trophéesP < 2400) {
            rank = "Argent III";
        } else if (trophéesP >= 2400 && trophéesP < 2700) {
            rank = "Argent II";
        } else if (trophéesP >= 2700 && trophéesP < 3000) {
            rank = "Argent I";
        } else if (trophéesP >= 3000 && trophéesP < 3400) {
            rank = "Or V";
        } else if (trophéesP >= 3400 && trophéesP < 3800) {
            rank = "Or IV";
        } else if (trophéesP >= 3800 && trophéesP < 4200) {
            rank = "Or III";
        } else if (trophéesP >= 4200 && trophéesP < 4600) {
            rank = "Or II";
        } else if (trophéesP >= 4600 && trophéesP < 5000) {
            rank = "Or I";
        } else if (trophéesP >= 5000 && trophéesP < 5500) {
            rank = "Diamond V";
        } else if (trophéesP >= 5500 && trophéesP < 6000) {
            rank = "Diamond IV";
        } else if (trophéesP >= 6000 && trophéesP < 6500) {
            rank = "Diamond III";
        } else if (trophéesP >= 6500 && trophéesP < 7000) {
            rank = "Diamond II";
        } else if (trophéesP >= 7000 && trophéesP < 7500) {
            rank = "Diamond I";
        } else if (trophéesP >= 7500 && trophéesP < 8100) {
            rank = "Platine V";
        } else if (trophéesP >= 8100 && trophéesP < 8700) {
            rank = "Platine IV";
        } else if (trophéesP >= 8700 && trophéesP < 9300) {
            rank = "Platine III";
        } else if (trophéesP >= 9300 && trophéesP < 9900) {
            rank = "Platine II";
        } else if (trophéesP >= 9900 && trophéesP < 10000) {
            rank = "Platine I";
        } else if (trophéesP > 10000) {
            rank = "Master";
        }
        if (lang == command.Language.fr) {
            channel.sendMessage("Vous avez " + trophéesP + " trophées. Vous etes actuelement rank " + rank + ".")
                    .queue();
        }
        if (lang == command.Language.en) {
            channel.sendMessage("You have " + trophéesP + " trophies. You are actually rank " + rank + ".").queue();
        }
    }

    public static String rank(int trophées) {
        String rank = null;
        if (trophées < 100) {
            rank = "Bois V";
        } else if (trophées >= 100 && trophées < 200) {
            rank = "Bois IV";
        } else if (trophées >= 200 && trophées < 300) {
            rank = "Bois III";
        } else if (trophées >= 300 && trophées < 400) {
            rank = "Bois II";
        } else if (trophées >= 400 && trophées < 500) {
            rank = "Bois I";
        } else if (trophées >= 500 && trophées < 700) {
            rank = "Bronze V";
        } else if (trophées >= 700 && trophées < 900) {
            rank = "Bronze IV";
        } else if (trophées >= 900 && trophées < 1100) {
            rank = "Bronze III";
        } else if (trophées >= 1100 && trophées < 1300) {
            rank = "Bronze II";
        } else if (trophées >= 1300 && trophées < 1500) {
            rank = "Bronze I";
        } else if (trophées >= 1500 && trophées < 1800) {
            rank = "Argent V";
        } else if (trophées >= 1800 && trophées < 2100) {
            rank = "Argent IV";
        } else if (trophées >= 2100 && trophées < 2400) {
            rank = "Argent III";
        } else if (trophées >= 2400 && trophées < 2700) {
            rank = "Argent II";
        } else if (trophées >= 2700 && trophées < 3000) {
            rank = "Argent I";
        } else if (trophées >= 3000 && trophées < 3400) {
            rank = "Or V";
        } else if (trophées >= 3400 && trophées < 3800) {
            rank = "Or IV";
        } else if (trophées >= 3800 && trophées < 4200) {
            rank = "Or III";
        } else if (trophées >= 4200 && trophées < 4600) {
            rank = "Or II";
        } else if (trophées >= 4600 && trophées < 5000) {
            rank = "Or I";
        } else if (trophées >= 5000 && trophées < 5500) {
            rank = "Diamond V";
        } else if (trophées >= 5500 && trophées < 6000) {
            rank = "Diamond IV";
        } else if (trophées >= 6000 && trophées < 6500) {
            rank = "Diamond III";
        } else if (trophées >= 6500 && trophées < 7000) {
            rank = "Diamond II";
        } else if (trophées >= 7000 && trophées < 7500) {
            rank = "Diamond I";
        } else if (trophées >= 7500 && trophées < 8100) {
            rank = "Platine V";
        } else if (trophées >= 8100 && trophées < 8700) {
            rank = "Platine IV";
        } else if (trophées >= 8700 && trophées < 9300) {
            rank = "Platine III";
        } else if (trophées >= 9300 && trophées < 9900) {
            rank = "Platine II";
        } else if (trophées >= 9900 && trophées < 10000) {
            rank = "Platine I";
        } else if (trophées > 10000) {
            rank = "Master";
        }
        return rank;
    }

    public static int money_bonus(int trophees) {
        int bonus = 0;
        if (trophees < 500) {
            bonus = 10000;
        } else if (trophees >= 500 && trophees < 600) {
            bonus = 14000;
        } else if (trophees >= 600 && trophees < 700) {
            bonus = 19000;
        } else if (trophees >= 700 && trophees < 800) {
            bonus = 26000;
        } else if (trophees >= 800 && trophees < 900) {
            bonus = 36000;
        } else if (trophees >= 900 && trophees < 1000) {
            bonus = 50000;
        } else if (trophees >= 1000 && trophees < 1200) {
            bonus = 68000;
        } else if (trophees >= 1200 && trophees < 1400) {
            bonus = 93000;
        } else if (trophees >= 1400 && trophees < 1600) {
            bonus = 130000;
        } else if (trophees >= 1600 && trophees < 1800) {
            bonus = 175000;
        } else if (trophees >= 1800 && trophees < 2000) {
            bonus = 242000;
        } else if (trophees >= 2000 && trophees < 2300) {
            bonus = 332000;
        } else if (trophees >= 2300 && trophees < 2600) {
            bonus = 456000;
        } else if (trophees >= 2600 && trophees < 2900) {
            bonus = 628000;
        } else if (trophees >= 2900 && trophees < 3200) {
            bonus = 863000;
        } else if (trophees >= 3200 && trophees < 3500) {
            bonus = 1190000;
        } else if (trophees >= 3500 && trophees < 3900) {
            bonus = 1630000;
        } else if (trophees >= 3900 && trophees < 4300) {
            bonus = 2240000;
        } else if (trophees >= 4300 && trophees < 4700) {
            bonus = 3090000;
        } else if (trophees >= 4700 && trophees < 5100) {
            bonus = 4240000;
        } else if (trophees >= 5100 && trophees < 5500) {
            bonus = 5840000;
        } else if (trophees >= 5500 && trophees < 6000) {
            bonus = 8020000;
        } else if (trophees >= 6000 && trophees < 6500) {
            bonus = 11000000;
        } else if (trophees >= 6500 && trophees < 7000) {
            bonus = 15200000;
        } else if (trophees >= 7000 && trophees < 7500) {
            bonus = 20900000;
        } else if (trophees >= 7500 && trophees < 8000) {
            bonus = 28700000;
        } else if (trophees >= 8000 && trophees < 8600) {
            bonus = 39400000;
        } else if (trophees >= 8600 && trophees < 9200) {
            bonus = 54200000;
        } else if (trophees >= 9200 && trophees < 10400) {
            bonus = 74600000;
        } else if (trophees >= 10400 && trophees < 11000) {
            bonus = 100000000;
        } else if (trophees > 11000) {
            bonus = 150000000;
        }
        return bonus;
    }

    public static int materiaux_bonus(int trophees) {
        int bonus = 0;
        if (trophees < 500) {
            bonus = 10;
        } else if (trophees >= 500 && trophees < 600) {
            bonus = 12;
        } else if (trophees >= 600 && trophees < 700) {
            bonus = 14;
        } else if (trophees >= 700 && trophees < 800) {
            bonus = 17;
        } else if (trophees >= 800 && trophees < 900) {
            bonus = 21;
        } else if (trophees >= 900 && trophees < 1000) {
            bonus = 25;
        } else if (trophees >= 1000 && trophees < 1200) {
            bonus = 30;
        } else if (trophees >= 1200 && trophees < 1400) {
            bonus = 36;
        } else if (trophees >= 1400 && trophees < 1600) {
            bonus = 43;
        } else if (trophees >= 1600 && trophees < 1800) {
            bonus = 52;
        } else if (trophees >= 1800 && trophees < 2000) {
            bonus = 62;
        } else if (trophees >= 2000 && trophees < 2300) {
            bonus = 74;
        } else if (trophees >= 2300 && trophees < 2600) {
            bonus = 89;
        } else if (trophees >= 2600 && trophees < 2900) {
            bonus = 107;
        } else if (trophees >= 2900 && trophees < 3200) {
            bonus = 128;
        } else if (trophees >= 3200 && trophees < 3500) {
            bonus = 154;
        } else if (trophees >= 3500 && trophees < 3900) {
            bonus = 184;
        } else if (trophees >= 3900 && trophees < 4300) {
            bonus = 221;
        } else if (trophees >= 4300 && trophees < 4700) {
            bonus = 266;
        } else if (trophees >= 4700 && trophees < 5100) {
            bonus = 319;
        } else if (trophees >= 5100 && trophees < 5500) {
            bonus = 393;
        } else if (trophees >= 5500 && trophees < 6000) {
            bonus = 460;
        } else if (trophees >= 6000 && trophees < 6500) {
            bonus = 552;
        } else if (trophees >= 6500 && trophees < 7000) {
            bonus = 662;
        } else if (trophees >= 7000 && trophees < 7500) {
            bonus = 794;
        } else if (trophees >= 7500 && trophees < 8000) {
            bonus = 953;
        } else if (trophees >= 8000 && trophees < 8600) {
            bonus = 1140;
        } else if (trophees >= 8600 && trophees < 9200) {
            bonus = 1370;
        } else if (trophees >= 9200 && trophees < 10400) {
            bonus = 1650;
        } else if (trophees >= 10400 && trophees < 11000) {
            bonus = 2000;
        } else if (trophees > 11000) {
            bonus = 2400;
        }
        return bonus;
    }

    public static void Attack(User user, User cible, int atk, String hero) {
        block162: {
            try {
                HashMap<String, ArrayList<String>> heroeA;
                boolean Umail;
                int trophéesuser;
                HashMap<String, ArrayList<String>> heroeD;
                ArrayList<ArrayList<String>> mails;
                int trophéescible;
                ArrayList<String> mail1;
                boolean Cmail;
                ArrayList<ArrayList<String>> mails2;
                command.Language lang = DiscordBot.getData().getProfils().get(user.getId()).getLanguage();
                command.Language langc = DiscordBot.getData().getProfils().get(cible.getId()).getLanguage();
                ProfilData data = DiscordBot.getData();
                try {
                    trophéescible = data.getProfils().get(cible.getId()).getTrophy();
                } catch (NullPointerException e1) {
                    trophéescible = 0;
                }
                try {
                    trophéesuser = data.getProfils().get(user.getId()).getTrophy();
                } catch (NullPointerException e1) {
                    trophéesuser = 0;
                }
                long def1 = habitants.atk(cible);
                HashMap<String, Integer> building = data.getProfils().get(user.getId()).getBuilding();
                int bunker = building.get("muraille");
                double bonus = 1.0 + (double) bunker * 0.075;
                System.out.println(bonus);
                long def = (int) ((double) def1 * bonus);
                try {
                    heroeA = data.getProfils().get(user.getId()).getHeroe();
                } catch (NullPointerException e) {
                    heroeA = new HashMap();
                    ArrayList<String> list = new ArrayList<String>();
                    list.add("1");
                    list.add("0");
                    list.add("false");
                    list.add("0");
                    heroeA.put("Karl", list);
                    heroeA.put("Valkyrie", null);
                    heroeA.put("Ouranos", null);
                    heroeA.put("Oeil", null);
                    heroeA.put("Ikaryus", null);
                    heroeA.put("Yegarde", null);
                    heroeA.put("Angel", null);
                    heroeA.put("Zhen", null);
                    heroeA.put("Hearth", null);
                    heroeA.put("Lixie", null);
                    heroeA.put("Akashi", null);
                    heroeA.put("Rose", null);
                    heroeA.put("Hell", null);
                    heroeA.put("Spirita", null);
                    heroeA.put("Tempest", null);
                    heroeA.put("Ivoire", null);
                }
                ArrayList listA = null;
                try {
                    listA = (ArrayList) heroeA.get(data.getProfils().get(user.getId()).getActiveHeroe());
                } catch (NullPointerException list) {
                    // empty catch block
                }
                int levelA = 0;
                try {
                    levelA = Integer.parseInt((String) listA.get(0));
                } catch (NullPointerException nullPointerException) {
                    // empty catch block
                }
                double Aatk = atk;
                double Adef = atk;
                double APV = atk;
                double AMagic = atk;
                if (hero.equals("true")) {
                    Aatk = atk + Heroe.getAtk(data.getProfils().get(user.getId()).getActiveHeroe(), levelA, user);
                    Adef = atk + Heroe.getDef(data.getProfils().get(user.getId()).getActiveHeroe(), levelA, user);
                    APV = atk + Heroe.getPV(data.getProfils().get(user.getId()).getActiveHeroe(), user);
                    AMagic = 0 + Heroe.getMagic(data.getProfils().get(user.getId()).getActiveHeroe(), levelA);
                }
                try {
                    heroeD = data.getProfils().get(cible.getId()).getHeroe();
                } catch (NullPointerException e) {
                    heroeD = new HashMap();
                    ArrayList<String> list = new ArrayList<String>();
                    list.add("1");
                    list.add("0");
                    list.add("false");
                    list.add("0");
                    heroeD.put("Karl", listA);
                    heroeD.put("Valkyrie", null);
                    heroeD.put("Ouranos", null);
                    heroeD.put("Oeil", null);
                    heroeD.put("Ikaryus", null);
                    heroeD.put("Yegarde", null);
                    heroeD.put("Angel", null);
                    heroeD.put("Zhen", null);
                    heroeD.put("Hearth", null);
                    heroeD.put("Lixie", null);
                    heroeD.put("Akashi", null);
                    heroeD.put("Rose", null);
                    heroeD.put("Hell", null);
                    heroeD.put("Spirita", null);
                    heroeD.put("Tempest", null);
                    heroeD.put("Ivoire", null);
                }
                ArrayList listD = null;
                try {
                    listD = (ArrayList) heroeD.get(data.getProfils().get(cible.getId()).getActiveHeroe());
                } catch (NullPointerException list) {
                    // empty catch block
                }
                int levelD = 0;
                String isAttack = "true";
                if (listD != null) {
                    levelD = Integer.parseInt((String) listD.get(0));
                }
                if (listD != null) {
                    isAttack = (String) listD.get(2);
                }
                double Datk = def;
                double DMagic = def;
                double DPV = def;
                double Ddef = def;
                if (isAttack.equals("false")) {
                    Datk = def
                            + (long) Heroe.getAtk(data.getProfils().get(cible.getId()).getActiveHeroe(), levelD, cible);
                    Ddef = def
                            + (long) Heroe.getDef(data.getProfils().get(cible.getId()).getActiveHeroe(), levelD, cible);
                    DPV = def + (long) Heroe.getPV(data.getProfils().get(cible.getId()).getActiveHeroe(), cible);
                    DMagic = 0 + Heroe.getMagic(data.getProfils().get(cible.getId()).getActiveHeroe(), levelD);
                } else {
                    Datk = def;
                    Ddef = def;
                    DPV = def;
                    DMagic = 0.0;
                }
                if (Ddef == 0.0) {
                    Ddef = 1.0;
                }
                while (DPV > 0.0 && APV > 0.0) {
                    int ABonus = 0 + (int) (Math.random() * (AMagic - 0.0 + 1.0));
                    int DBonus = 0 + (int) (Math.random() * (DMagic - 0.0 + 1.0));
                    DPV -= Aatk + (double) ABonus / Ddef;
                    APV -= Datk + (double) DBonus / Adef;
                }
                if (DPV <= 0.0) {
                    ArrayList<ArrayList<String>> mails3;
                    ArrayList<ArrayList<String>> mails4;
                    boolean Umail2;
                    ArrayList<String> mail12;
                    long Cpop;
                    boolean Cmail2;
                    if (isAttack.equals("false")) {
                        int cartesD = Integer.parseInt((String) listD.get(1));
                        String heroAtkD = "false";
                        listD.set(0, Integer.toString(levelD));
                        listD.set(1, Integer.toString(cartesD));
                        listD.set(2, heroAtkD);
                        listD.set(3, "0");
                        listD.set(4, Long.toString(System.currentTimeMillis()));
                        heroeD.put(data.getProfils().get(cible.getId()).getActiveHeroe(), listD);
                        data.getProfils().get(cible.getId()).setHeroe(heroeD);
                    }
                    if (hero.equals("true")) {
                        int cartesA = Integer.parseInt((String) listA.get(1));
                        int pvA = Integer.parseInt((String) listA.get(3));
                        double calcul1 = APV * 1.0 / (double) (atk + pvA);
                        int calcul = (int) ((double) pvA * calcul1);
                        listA.set(0, Integer.toString(levelA));
                        listA.set(1, Integer.toString(cartesA));
                        listA.set(2, "false");
                        listA.set(3, Integer.toString(calcul));
                        listA.set(4, Long.toString(System.currentTimeMillis()));
                        heroeA.put(data.getProfils().get(user.getId()).getActiveHeroe(), listA);
                        data.getProfils().get(user.getId()).setHeroe(heroeA);
                    }
                    def -= def;
                    long Umoney = data.getProfils().get(user.getId()).getMoney();
                    long Cmoney = data.getProfils().get(cible.getId()).getMoney();
                    double Mperte = 0.0;
                    Double rapport = ((double) trophéescible + 1.0) / ((double) trophéesuser + 1.0);
                    System.out.println(rapport);
                    if (rapport < 0.5) {
                        Mperte = 0.0;
                    } else if (rapport >= 0.5 && rapport < 0.75) {
                        Mperte = 0.0010000000000000009;
                    } else if (rapport >= 0.75 && rapport < 1.0) {
                        Mperte = 0.010000000000000009;
                    } else if (rapport >= 1.0 && rapport < 2.0) {
                        Mperte = 0.020000000000000018;
                    } else if (rapport >= 2.0 && rapport < 5.0) {
                        Mperte = 0.050000000000000044;
                    } else if (rapport >= 5.0) {
                        Mperte = 0.09999999999999998;
                    }
                    int perte2 = (int) ((double) Cmoney * Mperte);
                    Umoney += (long) perte2;
                    Cmoney -= (long) perte2;
                    try {
                        data.getProfils().get(user.getId()).setMoney(Umoney);
                    } catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setMoney(Umoney);
                    }
                    try {
                        data.getProfils().get(cible.getId()).setMoney(Cmoney);
                    } catch (NullPointerException e) {
                        data.getProfils().put(cible.getId(), new Profil(cible.getId()));
                        data.getProfils().get(cible.getId()).setMoney(Cmoney);
                    }
                    try {
                        Cpop = habitants.pop(cible);
                    } catch (NullPointerException e) {
                        Cpop = 0L;
                    }
                    double Pperte = 0.0;
                    rapport = ((double) trophéescible + 1.0) / ((double) trophéesuser + 1.0);
                    System.out.println(rapport);
                    if (rapport < 0.5) {
                        Pperte = 0.0;
                    } else if (rapport >= 0.5 && rapport < 0.75) {
                        Pperte = 0.0010000000000000009;
                    } else if (rapport >= 0.75 && rapport < 1.0) {
                        Pperte = 0.010000000000000009;
                    } else if (rapport >= 1.0 && rapport < 2.0) {
                        Pperte = 0.020000000000000018;
                    } else if (rapport >= 2.0 && rapport < 5.0) {
                        Pperte = 0.050000000000000044;
                    } else if (rapport >= 5.0) {
                        Pperte = 0.09999999999999998;
                    }
                    int perte3 = (int) ((double) Cpop * Pperte);
                    Cpop -= (long) perte3;
                    try {
                        data.getProfils().get(cible.getId()).setHabitants(Cpop);
                    } catch (NullPointerException e) {
                        data.getProfils().put(cible.getId(), new Profil(cible.getId()));
                        data.getProfils().get(cible.getId()).setHabitants(Cpop);
                    }
                    HashMap<String, Integer> Ures = data.getProfils().get(user.getId()).getRes();
                    HashMap<String, Integer> Cres = data.getProfils().get(cible.getId()).getRes();
                    int Ubois = Ures.get("bois");
                    int Uargile = Ures.get("argile");
                    int Ucuir = Ures.get("cuir");
                    int Upierre = Ures.get("pierre");
                    int Upaille = Ures.get("paille");
                    int Ufer = Ures.get("fer");
                    int Ucristal = Ures.get("cristal");
                    int Cbois = Cres.get("bois");
                    int Cargile = Cres.get("argile");
                    int Ccuir = Cres.get("cuir");
                    int Cpierre = Cres.get("pierre");
                    int Cpaille = Cres.get("paille");
                    int Cfer = Cres.get("fer");
                    int Ccristal = Cres.get("cristal");
                    double Rperte = 0.0;
                    rapport = ((double) trophéescible + 1.0) / ((double) trophéesuser + 1.0);
                    System.out.println(rapport);
                    if (rapport < 0.5) {
                        Rperte = 0.0;
                    } else if (rapport >= 0.5 && rapport < 0.75) {
                        Rperte = 0.010000000000000009;
                    } else if (rapport >= 0.75 && rapport < 1.0) {
                        Rperte = 0.020000000000000018;
                    } else if (rapport >= 1.0 && rapport < 2.0) {
                        Rperte = 0.050000000000000044;
                    } else if (rapport >= 2.0 && rapport < 5.0) {
                        Rperte = 0.09999999999999998;
                    } else if (rapport >= 5.0) {
                        Rperte = 0.19999999999999996;
                    }
                    int pertebois = (int) ((double) Cbois * Rperte);
                    int perteargile = (int) ((double) Cargile * Rperte);
                    int pertecuir = (int) ((double) Ccuir * Rperte);
                    int pertepierre = (int) ((double) Cpierre * Rperte);
                    int pertepaille = (int) ((double) Cpaille * Rperte);
                    int pertefer = (int) ((double) Cfer * Rperte);
                    int pertecristal = (int) ((double) Ccristal * Rperte);
                    Ures.put("bois", Ubois += pertebois);
                    Ures.put("argile", Uargile += perteargile);
                    Ures.put("cuir", Ucuir += pertepierre);
                    Ures.put("pierre", Upierre += pertepierre);
                    Ures.put("paille", Upaille += pertepaille);
                    Ures.put("fer", Ufer += pertefer);
                    Ures.put("cristal", Ucristal += pertecristal);
                    Cres.put("bois", Cbois -= pertebois);
                    Cres.put("argile", Cargile -= perteargile);
                    Cres.put("cuir", Ccuir -= pertepierre);
                    Cres.put("pierre", Cpierre -= pertepierre);
                    Cres.put("paille", Cpaille -= pertepaille);
                    Cres.put("fer", Cfer -= pertefer);
                    Cres.put("cristal", Ccristal -= pertecristal);
                    try {
                        data.getProfils().get(user.getId()).setRes(Ures);
                    } catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setRes(Ures);
                    }
                    try {
                        data.getProfils().get(cible.getId()).setRes(Cres);
                    } catch (NullPointerException e) {
                        data.getProfils().put(cible.getId(), new Profil(cible.getId()));
                        data.getProfils().get(cible.getId()).setRes(Cres);
                    }
                    int trophéesP = trophéescible;
                    int trophéesV = trophéesuser;
                    int dif = trophéesV - trophéesP;
                    int gain = dif > 500 ? 1
                            : (dif > 250 && dif <= 500 ? 4
                                    : (dif > 0 && dif <= 250 ? 9
                                            : (dif > -250 && dif <= 0 ? 14
                                                    : (dif > -250 && dif <= -500 ? 19 : (dif < -500 ? 29 : 1)))));
                    trophéesV = dif < 1000 ? trophéesV + gain + 1 : (trophéesV += 0);
                    trophéesP = trophéesP > gain - 1 ? (trophéesP -= gain - 1) : 0;
                    try {
                        data.getProfils().get(user.getId()).setTrophy(trophéesV);
                    } catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setTrophy(trophéesV);
                    }
                    try {
                        data.getProfils().get(cible.getId()).setTrophy(trophéesP);
                    } catch (NullPointerException e) {
                        data.getProfils().put(cible.getId(), new Profil(cible.getId()));
                        data.getProfils().get(cible.getId()).setTrophy(trophéesP);
                    }
                    try {
                        data.getProfils().get(cible.getId()).setSoldiers(def);
                    } catch (NullPointerException e) {
                        data.getProfils().put(cible.getId(), new Profil(cible.getId()));
                        data.getProfils().get(cible.getId()).setSoldiers(def);
                    }
                    Quest.Quest("atk", user, ((UserImpl) user).openPrivateChannel().complete(), 1);
                    String villeCible = data.getProfils().get(cible.getId()).getHome();
                    String ownerville = TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/bot/Map/" + villeCible + "/name.txt");
                    if (!ownerville.equals(cible.getId())) {
                        boolean Umail3;
                        try {
                            Umail3 = data.getProfils().get(user.getId()).isMail();
                        } catch (Exception e) {
                            Umail3 = false;
                        }
                        if (!Umail3) {
                            if (lang == command.Language.fr) {
                                ((UserImpl) user).openPrivateChannel().complete().sendMessage(
                                        "La personne que vous avez attaquez semble avoir disparue... peut etre s'est elle fait raser.")
                                        .queue();
                            }
                            if (lang == command.Language.en) {
                                ((UserImpl) user).openPrivateChannel().complete().sendMessage(
                                        "The person you attacked seem have disappear... it's possible she already have been attacked")
                                        .queue();
                            }
                        } else {
                            ArrayList<String> mail13 = new ArrayList<String>();
                            if (lang == command.Language.fr) {
                                mail13.add("Rapport d'attaque de " + cible.getName());
                            }
                            if (lang == command.Language.en) {
                                mail13.add("Attack report of " + cible.getName());
                            }
                            if (lang == command.Language.fr) {
                                mail13.add(
                                        "La personne que vous avez attaquez semble avoir disparue... peut etre s'est elle fait raser.");
                            }
                            if (lang == command.Language.en) {
                                mail13.add(
                                        "The person you attacked seem have disappear... it's possible she already have been attacked");
                            }
                            mail13.add("false");
                            mail13.add("" + System.currentTimeMillis());
                            try {
                                ArrayList<ArrayList<String>> mails5 = data.getProfils().get(user.getId()).getListMail();
                                mails5.add(0, mail13);
                                data.getProfils().get(user.getId()).setListMail(mails5);
                            } catch (NullPointerException e) {
                                ArrayList<ArrayList<String>> mails6 = new ArrayList<ArrayList<String>>();
                                mails6.add(0, mail13);
                                data.getProfils().get(user.getId()).setListMail(mails6);
                            }
                        }
                        return;
                    }
                    TextFileWriter.recursifDelete(new File("/home/DiscordBot/Rasberry/données/bot/Map/" + villeCible));
                    int Conquerant = data.getProfils().get(user.getId()).getConquerant();
                    data.getProfils().get(user.getId()).setConquerant(++Conquerant);
                    int L = data.getProfils().get(cible.getId()).getLooser();
                    data.getProfils().get(cible.getId()).setLooser(++L);
                    try {
                        Umail2 = data.getProfils().get(user.getId()).isMail();
                    } catch (Exception e) {
                        Umail2 = false;
                    }
                    try {
                        Cmail2 = data.getProfils().get(cible.getId()).isMail();
                    } catch (Exception e) {
                        Cmail2 = false;
                    }
                    int gain1 = gain + 1;
                    if (gain1 == 1) {
                        gain1 = 0;
                    }
                    if (!Umail2) {
                        if (lang == command.Language.fr) {
                            user.openPrivateChannel().complete().sendMessage(
                                    ":crossed_swords: Rapport d'Attaque :crossed_swords: \n Vous avez gagné face a "
                                            + cible.getName() + ". Vous remportez " + (gain + 1)
                                            + " Trophées. \n Vous gagnez aussi " + perte2 + "$ , " + pertebois
                                            + " bois," + perteargile + " argile, " + pertecuir + " cuir, " + pertepierre
                                            + " pierre, " + pertepaille + " paille, " + pertefer + " fer et "
                                            + pertecristal + " cristaux.")
                                    .queue();
                        }
                        if (lang == command.Language.en) {
                            user.openPrivateChannel().complete()
                                    .sendMessage(
                                            ":crossed_swords: Attack report :crossed_swords: \n **You Won** against "
                                                    + cible.getName() + ". You won " + (gain + 1)
                                                    + " trophies. \n You also won " + perte2 + "$, " + pertebois
                                                    + " wood," + perteargile + " clay, " + pertecuir + " leather, "
                                                    + pertepierre + " stone, " + pertepaille + " straw, " + pertefer
                                                    + " iron and " + pertecristal + " crystals.")
                                    .queue();
                        }
                    } else {
                        mail12 = new ArrayList<String>();
                        if (lang == command.Language.fr) {
                            mail12.add("Rapport d'attaque de " + cible.getName());
                        }
                        if (lang == command.Language.en) {
                            mail12.add("Attack report of " + cible.getName());
                        }
                        if (lang == command.Language.fr) {
                            mail12.add(":crossed_swords: Rapport d'Attaque :crossed_swords: \n Vous avez gagné face a "
                                    + cible.getName() + ". Vous remportez " + (gain + 1)
                                    + " Trophées. \n Vous gagnez aussi " + perte2 + "$ , " + pertebois + " bois,"
                                    + perteargile + " argile, " + pertecuir + " cuir, " + pertepierre + " pierre, "
                                    + pertepaille + " paille, " + pertefer + " fer et " + pertecristal + " cristaux.");
                        }
                        if (lang == command.Language.en) {
                            mail12.add(":crossed_swords: Attack report :crossed_swords: \n **You Won** against "
                                    + cible.getName() + ". You won " + (gain + 1) + " trophies. \n You also won "
                                    + perte2 + "$, " + pertebois + " wood," + perteargile + " clay, " + pertecuir
                                    + " leather, " + pertepierre + " stone, " + pertepaille + " straw, " + pertefer
                                    + " iron and " + pertecristal + " crystals.");
                        }
                        mail12.add("false");
                        mail12.add("" + System.currentTimeMillis());
                        try {
                            mails3 = data.getProfils().get(user.getId()).getListMail();
                            mails3.add(0, mail12);
                            data.getProfils().get(user.getId()).setListMail(mails3);
                        } catch (NullPointerException e) {
                            mails4 = new ArrayList<ArrayList<String>>();
                            mails4.add(0, mail12);
                            data.getProfils().get(user.getId()).setListMail(mails4);
                        }
                    }
                    if (gain1 == 0) {
                        gain1 = 1;
                    }
                    if (!Cmail2) {
                        if (langc == command.Language.fr) {
                            cible.openPrivateChannel().complete().sendMessage(
                                    ":crossed_swords:  Rapport de Defense :crossed_swords: \n Vous avez perdu face a "
                                            + user.getName() + ". Vous perdez " + (gain - 1)
                                            + " Trophées ainsi que tout vos soldats. \n Vous perdez aussi " + perte3
                                            + " habitants, " + perte2 + "$, " + pertebois + " bois," + perteargile
                                            + " argile, " + pertecuir + " cuir, " + pertepierre + " pierre, "
                                            + pertepaille + " paille et " + pertefer + " fer et " + pertecristal
                                            + " cristaux.")
                                    .queue();
                        }
                        if (langc == command.Language.en) {
                            cible.openPrivateChannel().complete().sendMessage(
                                    ":crossed_swords:  Defense report :crossed_swords: \n **You lose** against "
                                            + user.getName() + ". You lose " + (gain - 1)
                                            + " Trophies and all your soldiers. \n You also lose" + perte3 + " people, "
                                            + perte2 + "$, " + pertebois + " wood," + perteargile + " clay, "
                                            + pertecuir + " leather, " + pertepierre + " stone, " + pertepaille
                                            + " straw, " + pertefer + " iron and " + pertecristal + " crystals.")
                                    .queue();
                        }
                    } else {
                        mail12 = new ArrayList();
                        if (langc == command.Language.fr) {
                            mail12.add("Rapport de defense de " + user.getName());
                        }
                        if (langc == command.Language.en) {
                            mail12.add("Defense report from " + user.getName());
                        }
                        if (langc == command.Language.fr) {
                            mail12.add(
                                    ":crossed_swords:  Rapport de Defense :crossed_swords: \n Vous avez perdu face a "
                                            + user.getName() + ". Vous perdez " + (gain - 1)
                                            + " Trophées ainsi que tout vos soldats. \n Vous perdez aussi " + perte3
                                            + " habitants, " + perte2 + "$, " + pertebois + " bois," + perteargile
                                            + " argile, " + pertecuir + " cuir, " + pertepierre + " pierre, "
                                            + pertepaille + " paille et " + pertefer + " fer et " + pertecristal
                                            + " cristaux.");
                        }
                        if (langc == command.Language.en) {
                            mail12.add(":crossed_swords:  Defense report :crossed_swords: \n **You lose** against "
                                    + user.getName() + ". You lose " + (gain - 1)
                                    + " Trophies and all your soldiers. \n You also lose" + perte3 + " people, "
                                    + perte2 + "$, " + pertebois + " wood," + perteargile + " clay, " + pertecuir
                                    + " leather, " + pertepierre + " stone, " + pertepaille + " straw, " + pertefer
                                    + " iron and " + pertecristal + " crystals.");
                        }
                        mail12.add("false");
                        mail12.add("" + System.currentTimeMillis());
                        try {
                            mails3 = data.getProfils().get(cible.getId()).getListMail();
                            mails3.add(0, mail12);
                            data.getProfils().get(cible.getId()).setListMail(mails3);
                        } catch (NullPointerException e) {
                            mails4 = new ArrayList();
                            mails4.add(0, mail12);
                            data.getProfils().get(cible.getId()).setListMail(mails4);
                        }
                    }
                    CommandMap.PublicLog(":crossed_swords: **Rapport d'attaque** :crossed_swords: \n\nle joueur "
                            + user.getName() + " (" + user.getId() + ") a gagné face a " + cible.getName() + " ("
                            + cible.getId() + ")", user.getJDA());
                    break block162;
                }
                int perteSoldats = (int) ((double) def * (DPV * 1.0 / (double) def));
                long Dperte = def - (long) perteSoldats;
                if (isAttack.equals("false")) {
                    int cartesD = Integer.parseInt((String) listD.get(1));
                    String heroAtkD = "false";
                    int pvD = Integer.parseInt((String) listD.get(3));
                    double calcul1 = DPV * 1.0 / (double) (def + (long) pvD);
                    int calcul = (int) ((double) pvD * calcul1);
                    perteSoldats = (int) ((double) def1 * calcul1);
                    Dperte = (int) ((double) def - (double) def * calcul1);
                    listD.set(0, Integer.toString(levelD));
                    listD.set(1, Integer.toString(cartesD));
                    listD.set(2, heroAtkD);
                    listD.set(3, Integer.toString(calcul));
                    listD.set(4, Long.toString(System.currentTimeMillis()));
                    heroeD.put(data.getProfils().get(cible.getId()).getActiveHeroe(), listD);
                    data.getProfils().get(cible.getId()).setHeroe(heroeD);
                }
                if (hero.equals("true")) {
                    int cartesA = Integer.parseInt((String) listA.get(1));
                    listA.set(0, Integer.toString(levelA));
                    listA.set(1, Integer.toString(cartesA));
                    listA.set(2, "false");
                    listA.set(3, "0");
                    listA.set(4, Long.toString(System.currentTimeMillis()));
                    heroeA.put(data.getProfils().get(user.getId()).getActiveHeroe(), listA);
                    data.getProfils().get(user.getId()).setHeroe(heroeA);
                }
                def = perteSoldats;
                int trophéesV = data.getProfils().get(cible.getId()).getTrophy();
                int trophéesP = data.getProfils().get(user.getId()).getTrophy();
                int dif = trophéesP - trophéesV;
                int gain = dif > 500 ? 29
                        : (dif > 250 && dif <= 500 ? 19
                                : (dif > 0 && dif <= 250 ? 14
                                        : (dif > -250 && dif <= 0 ? 9
                                                : (dif > -250 && dif <= -500 ? 4 : (dif < -500 ? 1 : 1)))));
                trophéesV = trophéesV + gain + 1;
                trophéesP = trophéesP > gain - 1 ? (trophéesP -= gain - 1) : 0;
                try {
                    data.getProfils().get(user.getId()).setTrophy(trophéesP);
                } catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setTrophy(trophéesP);
                }
                try {
                    data.getProfils().get(cible.getId()).setTrophy(trophéesV);
                } catch (NullPointerException e) {
                    data.getProfils().put(cible.getId(), new Profil(cible.getId()));
                    data.getProfils().get(cible.getId()).setTrophy(trophéesV);
                }
                if (def > def1) {
                    def = def1;
                }
                try {
                    data.getProfils().get(cible.getId()).setSoldiers(perteSoldats);
                } catch (NullPointerException e) {
                    data.getProfils().put(cible.getId(), new Profil(cible.getId()));
                    data.getProfils().get(cible.getId()).setSoldiers(perteSoldats);
                }
                String villeCible = data.getProfils().get(cible.getId()).getHome();
                String ownerville = TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/bot/Map/" + villeCible + "/name.txt");
                if (!ownerville.equals(cible.getId())) {
                    boolean Umail4;
                    try {
                        Umail4 = data.getProfils().get(user.getId()).isMail();
                    } catch (Exception e) {
                        Umail4 = false;
                    }
                    if (!Umail4) {
                        if (lang == command.Language.fr) {
                            ((UserImpl) user).openPrivateChannel().complete().sendMessage(
                                    "La personne que vous avez attaquez semble avoir disparue... peut etre s'est elle fait raser.")
                                    .queue();
                        }
                        if (lang == command.Language.en) {
                            ((UserImpl) user).openPrivateChannel().complete().sendMessage(
                                    "The person you attacked seem have disappear... it's possible she already have been attacked")
                                    .queue();
                        }
                    } else {
                        ArrayList<String> mail14 = new ArrayList<String>();
                        if (lang == command.Language.fr) {
                            mail14.add("Rapport d'attaque de " + cible.getName());
                        }
                        if (lang == command.Language.en) {
                            mail14.add("Attack report of " + cible.getName());
                        }
                        if (lang == command.Language.fr) {
                            mail14.add(
                                    "La personne que vous avez attaquez semble avoir disparue... peut etre s'est elle fait raser.");
                        }
                        if (lang == command.Language.en) {
                            mail14.add(
                                    "The person you attacked seem have disappear... it's possible she already have been attacked");
                        }
                        mail14.add("false");
                        mail14.add("" + System.currentTimeMillis());
                        try {
                            ArrayList<ArrayList<String>> mails7 = data.getProfils().get(user.getId()).getListMail();
                            mails7.add(0, mail14);
                            data.getProfils().get(user.getId()).setListMail(mails7);
                        } catch (NullPointerException e) {
                            ArrayList<ArrayList<String>> mails8 = new ArrayList<ArrayList<String>>();
                            mails8.add(0, mail14);
                            data.getProfils().get(user.getId()).setListMail(mails8);
                        }
                    }
                    return;
                }
                int Defenseur = data.getProfils().get(cible.getId()).getDefenseur();
                data.getProfils().get(cible.getId()).setDefenseur(++Defenseur);
                int L = data.getProfils().get(user.getId()).getLooser();
                data.getProfils().get(user.getId()).setLooser(++L);
                try {
                    Umail = data.getProfils().get(user.getId()).isMail();
                } catch (Exception e) {
                    Umail = false;
                }
                try {
                    Cmail = data.getProfils().get(cible.getId()).isMail();
                } catch (Exception e) {
                    Cmail = false;
                }
                if (!Umail) {
                    if (lang == command.Language.fr) {
                        user.openPrivateChannel().complete()
                                .sendMessage(
                                        ":crossed_swords: Rapport d'Attaque :crossed_swords: \n Vous avez perdu face a "
                                                + cible.getName() + ". Vous perdez " + (gain - 1)
                                                + " Trophées ainsi que " + atk + " soldats")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        user.openPrivateChannel().complete()
                                .sendMessage(":crossed_swords: Attack report :crossed_swords: \n **You lose** aginst "
                                        + cible.getName() + ". You lose " + (gain - 1) + " trophies and " + atk
                                        + " soldiers")
                                .queue();
                    }
                } else {
                    mail1 = new ArrayList<String>();
                    if (lang == command.Language.fr) {
                        mail1.add("Rapport d'attaque de " + cible.getName());
                    }
                    if (lang == command.Language.en) {
                        mail1.add("Attack report of " + cible.getName());
                    }
                    if (lang == command.Language.fr) {
                        mail1.add(":crossed_swords: Rapport d'Attaque :crossed_swords: \n Vous avez perdu face a "
                                + cible.getName() + ". Vous perdez " + (gain - 1) + " Trophées ainsi que " + atk
                                + "soldats");
                    }
                    if (lang == command.Language.en) {
                        mail1.add(":crossed_swords: Attack report :crossed_swords: \n **You lose** aginst "
                                + cible.getName() + ". You lose " + (gain - 1) + " trophies and " + atk + " soldiers");
                    }
                    mail1.add("false");
                    mail1.add("" + System.currentTimeMillis());
                    try {
                        mails = data.getProfils().get(user.getId()).getListMail();
                        mails.add(0, mail1);
                        data.getProfils().get(user.getId()).setListMail(mails);
                    } catch (NullPointerException e) {
                        mails2 = new ArrayList<ArrayList<String>>();
                        mails2.add(0, mail1);
                        data.getProfils().get(user.getId()).setListMail(mails2);
                    }
                }
                if (!Cmail) {
                    if (langc == command.Language.fr) {
                        cible.openPrivateChannel().complete().sendMessage(
                                ":crossed_swords:  Rapport de Defense :crossed_swords: \n Vous avez gagné face a "
                                        + user.getName() + ". Vous gagnez " + (gain + 1) + " Trophées et perdez "
                                        + Dperte + " soldats")
                                .queue();
                    }
                    if (langc == command.Language.en) {
                        cible.openPrivateChannel().complete()
                                .sendMessage(":crossed_swords:  Defense report :crossed_swords: \n **You won** against "
                                        + user.getName() + ". You won " + (gain + 1) + " Trophies and you lose "
                                        + Dperte + " Soldiers")
                                .queue();
                    }
                } else {
                    mail1 = new ArrayList();
                    if (lang == command.Language.fr) {
                        mail1.add("Rapport de defense de " + user.getName());
                    }
                    if (lang == command.Language.en) {
                        mail1.add("Defense report of " + user.getName());
                    }
                    if (lang == command.Language.fr) {
                        mail1.add(":crossed_swords:  Rapport de Defense :crossed_swords: \n Vous avez gagné face a "
                                + user.getName() + ". Vous gagnez " + (gain + 1) + " Trophées et perdez " + Dperte
                                + " soldats");
                    }
                    if (lang == command.Language.en) {
                        mail1.add(":crossed_swords:  Defense report :crossed_swords: \n **You won** against "
                                + user.getName() + ". You won " + (gain + 1) + " Trophies and you lose " + Dperte
                                + " Soldiers");
                    }
                    mail1.add("false");
                    mail1.add("" + System.currentTimeMillis());
                    try {
                        mails = data.getProfils().get(cible.getId()).getListMail();
                        mails.add(0, mail1);
                        data.getProfils().get(cible.getId()).setListMail(mails);
                    } catch (NullPointerException e) {
                        mails2 = new ArrayList();
                        mails2.add(0, mail1);
                        data.getProfils().get(cible.getId()).setListMail(mails2);
                    }
                }
                CommandMap.PublicLog(":crossed_swords: **Rapport d'attaque** :crossed_swords: \n\nle joueur "
                        + cible.getName() + " (" + cible.getId() + ") a gagné face a " + user.getName() + " ("
                        + user.getId() + ")", user.getJDA());
                Quest.Quest("def", cible, ((UserImpl) user).openPrivateChannel().complete(), 1);
            } catch (Exception e) {
                e.printStackTrace();
                CommandMap.Log("Attack", e.getLocalizedMessage(), user.getJDA());
            }
        }
    }

    public static void Attack2(User user, String cible2, int atk, JDA jda, String hero) {
        block117: {
            try {
                boolean Umail;
                String owner;
                try {
                    owner = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Map/" + cible2 + "/owner.txt");
                } catch (NullPointerException e) {
                    owner = "personne";
                }
                if (owner.equals("") || owner == null || owner == "0") {
                    owner = "personne";
                }
                System.out.println(owner);
                if (!owner.equals("personne")) {
                    double Ddef;
                    int cartesA;
                    HashMap<String, ArrayList<String>> heroeD;
                    HashMap<String, ArrayList<String>> heroeA;
                    double DPV;
                    double DMagic;
                    double Datk;
                    boolean Umail2;
                    int perteSoldats;
                    command.Language lang = DiscordBot.getData().getProfils().get(user.getId()).getLanguage();
                    ProfilData data = DiscordBot.getData();
                    String defHero = TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/bot/Map/" + cible2 + "/hero.txt");
                    User cible = jda.getUserById(owner);
                    command.Language langc = DiscordBot.getData().getProfils().get(cible.getId()).getLanguage();
                    int def = 0;
                    try {
                        def = Integer.parseInt(TextFileWriter
                                .read("/home/DiscordBot/Rasberry/données/bot/Map/" + cible2 + "/soldier.txt"));
                    } catch (NumberFormatException e) {
                        def = 0;
                    }
                    try {
                        heroeA = data.getProfils().get(user.getId()).getHeroe();
                    } catch (NullPointerException e) {
                        heroeA = new HashMap();
                        ArrayList<String> list = new ArrayList<String>();
                        list.add("1");
                        list.add("0");
                        list.add("false");
                        list.add("0");
                        heroeA.put("Karl", list);
                        heroeA.put("Valkyrie", null);
                        heroeA.put("Ouranos", null);
                        heroeA.put("Oeil", null);
                        heroeA.put("Ikaryus", null);
                        heroeA.put("Yegarde", null);
                        heroeA.put("Angel", null);
                        heroeA.put("Zhen", null);
                        heroeA.put("Hearth", null);
                        heroeA.put("Lixie", null);
                        heroeA.put("Akashi", null);
                        heroeA.put("Rose", null);
                        heroeA.put("Hell", null);
                        heroeA.put("Spirita", null);
                        heroeA.put("Tempest", null);
                        heroeA.put("Ivoire", null);
                    }
                    ArrayList listA = null;
                    try {
                        listA = (ArrayList) heroeA.get(data.getProfils().get(user.getId()).getActiveHeroe());
                    } catch (NullPointerException list) {
                        // empty catch block
                    }
                    int levelA = 0;
                    try {
                        levelA = Integer.parseInt((String) listA.get(0));
                    } catch (NullPointerException nullPointerException) {
                        // empty catch block
                    }
                    double Aatk = atk;
                    double Adef = atk;
                    double APV = atk;
                    double AMagic = atk;
                    if (hero.equals("true")) {
                        Aatk = atk + Heroe.getAtk(data.getProfils().get(user.getId()).getActiveHeroe(), levelA, user);
                        Adef = atk + Heroe.getDef(data.getProfils().get(user.getId()).getActiveHeroe(), levelA, user);
                        AMagic = 0 + Heroe.getMagic(data.getProfils().get(user.getId()).getActiveHeroe(), levelA);
                    }
                    try {
                        heroeD = data.getProfils().get(cible.getId()).getHeroe();
                    } catch (NullPointerException e) {
                        heroeD = new HashMap();
                        ArrayList<String> list = new ArrayList<String>();
                        list.add("1");
                        list.add("0");
                        list.add("false");
                        list.add("0");
                        heroeD.put("Karl", listA);
                        heroeD.put("Valkyrie", null);
                        heroeD.put("Ouranos", null);
                        heroeD.put("Oeil", null);
                        heroeD.put("Ikaryus", null);
                        heroeD.put("Yegarde", null);
                        heroeD.put("Angel", null);
                        heroeD.put("Zhen", null);
                        heroeD.put("Hearth", null);
                        heroeD.put("Lixie", null);
                        heroeD.put("Akashi", null);
                        heroeD.put("Rose", null);
                        heroeD.put("Hell", null);
                        heroeD.put("Spirita", null);
                        heroeD.put("Tempest", null);
                        heroeD.put("Ivoire", null);
                    }
                    ArrayList listD = null;
                    try {
                        listD = (ArrayList) heroeD.get(data.getProfils().get(cible.getId()).getActiveHeroe());
                    } catch (NullPointerException list) {
                        // empty catch block
                    }
                    int levelD = Integer.parseInt((String) listD.get(0));
                    if (defHero.equals("true")) {
                        Datk = def + Heroe.getAtk(data.getProfils().get(cible.getId()).getActiveHeroe(), levelD, user);
                        Ddef = def + Heroe.getDef(data.getProfils().get(cible.getId()).getActiveHeroe(), levelD, user);
                        DPV = def + Heroe.getPV(data.getProfils().get(cible.getId()).getActiveHeroe(), cible);
                        DMagic = 0 + Heroe.getMagic(data.getProfils().get(cible.getId()).getActiveHeroe(), levelD);
                    } else {
                        Datk = def;
                        Ddef = def;
                        DPV = def;
                        DMagic = 0.0;
                    }
                    if (Ddef == 0.0) {
                        Ddef = 1.0;
                    }
                    while (DPV > 0.0 && APV > 0.0) {
                        int ABonus = 0 + (int) (Math.random() * (AMagic - 0.0 + 1.0));
                        int DBonus = 0 + (int) (Math.random() * (DMagic - 0.0 + 1.0));
                        DPV -= Aatk + (double) ABonus / Ddef;
                        APV -= Datk + (double) DBonus / Adef;
                    }
                    if (DPV <= 0.0) {
                        boolean Umail3;
                        if (defHero.equals("true")) {
                            int cartesD = Integer.parseInt((String) listD.get(1));
                            String heroAtkD = "false";
                            listD.set(0, Integer.toString(levelD));
                            listD.set(1, Integer.toString(cartesD));
                            listD.set(2, heroAtkD);
                            listD.set(3, "0");
                            listD.set(4, Long.toString(System.currentTimeMillis()));
                            heroeD.put(data.getProfils().get(cible.getId()).getActiveHeroe(), listD);
                            data.getProfils().get(cible.getId()).setHeroe(heroeD);
                            TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Map/" + cible2 + "/hero.txt",
                                    "false", 1);
                        }
                        perteSoldats = (int) ((double) atk * (APV * 1.0 / (double) atk));
                        if (hero.equals("true")) {
                            cartesA = Integer.parseInt((String) listA.get(1));
                            int pvA = Integer.parseInt((String) listA.get(3));
                            double calcul1 = APV * 1.0 / (double) (atk + pvA);
                            int pertePV = (int) ((double) pvA * calcul1);
                            perteSoldats = (int) ((double) atk * calcul1);
                            listA.set(0, Integer.toString(levelA));
                            listA.set(1, Integer.toString(cartesA));
                            listA.set(2, "true");
                            listA.set(3, Integer.toString(pertePV));
                            listA.set(4, Long.toString(System.currentTimeMillis()));
                            heroeA.put(data.getProfils().get(user.getId()).getActiveHeroe(), listA);
                            data.getProfils().get(user.getId()).setHeroe(heroeA);
                            TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Map/" + cible2 + "/hero.txt",
                                    "true", 1);
                        }
                        String UserPays = data.getProfils().get(user.getId()).getCountry();
                        String CiblePays = data.getProfils().get(cible.getId()).getCountry();
                        atk = user.getId().equals(owner) || UserPays.equals(CiblePays) ? (atk += def) : perteSoldats;
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Map/" + cible2 + "/soldier.txt",
                                Integer.toString(atk), 1);
                        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Map/" + cible2 + "/owner.txt",
                                user.getId(), 1);
                        int Avant_poste = data.getProfils().get(user.getId()).getAvant_poste();
                        data.getProfils().get(user.getId()).setAvant_poste(++Avant_poste);
                        if (!user.getId().equals(owner) && !UserPays.equals(CiblePays)) {
                            ArrayList<String> mail1;
                            ArrayList<ArrayList<String>> mails;
                            boolean Cmail;
                            ArrayList<ArrayList<String>> mails2;
                            boolean Umail4;
                            try {
                                Umail4 = data.getProfils().get(user.getId()).isMail();
                            } catch (Exception e) {
                                Umail4 = false;
                            }
                            try {
                                Cmail = data.getProfils().get(cible.getId()).isMail();
                            } catch (Exception e) {
                                Cmail = false;
                            }
                            if (!Umail4) {
                                if (lang == command.Language.fr && !user.hasPrivateChannel()) {
                                    user.openPrivateChannel().complete().sendMessage(
                                            ":crossed_swords: Rapport d'Attaque de ressources :crossed_swords: \n Vous avez gagnez  la zone de ressource en "
                                                    + cible2.replace("_", " ") + " qui appartenait \u00e0 "
                                                    + cible.getName() + " (" + cible.getId() + "). Vous avez desormais "
                                                    + atk + " soldats en defense sur cette zone")
                                            .queue();
                                }
                                if (lang == command.Language.en && !user.hasPrivateChannel()) {
                                    user.openPrivateChannel().complete().sendMessage(
                                            ":crossed_swords: Ressources attack report :crossed_swords: \n **You won** the ressource area in "
                                                    + cible2.replace("_", " ") + " which belonged to " + cible.getName()
                                                    + " (" + cible.getId() + "). You have now " + atk
                                                    + " soldiers on defense on this area.")
                                            .queue();
                                }
                            } else {
                                mail1 = new ArrayList<String>();
                                if (lang == command.Language.fr) {
                                    mail1.add("Rapport d'attaque de ressoource");
                                }
                                if (lang == command.Language.en) {
                                    mail1.add("Ressource attack report");
                                }
                                if (lang == command.Language.fr) {
                                    mail1.add(
                                            ":crossed_swords: Rapport d'Attaque de ressources :crossed_swords: \n Vous avez gagnez  la zone de ressource en "
                                                    + cible2.replace("_", " ") + " qui appartenait \u00e0 "
                                                    + cible.getName() + " (" + cible.getId() + "). Vous avez desormais "
                                                    + atk + " soldats en defense sur cette zone");
                                }
                                if (lang == command.Language.en) {
                                    mail1.add(
                                            ":crossed_swords: Ressources attack report :crossed_swords: \n **You won** the ressource area in "
                                                    + cible2.replace("_", " ") + " which belonged to " + cible.getName()
                                                    + " (" + cible.getId() + "). You have now " + atk
                                                    + " soldiers on defense on this area.");
                                }
                                mail1.add("false");
                                mail1.add("" + System.currentTimeMillis());
                                try {
                                    mails2 = data.getProfils().get(user.getId()).getListMail();
                                    mails2.add(0, mail1);
                                    data.getProfils().get(user.getId()).setListMail(mails2);
                                } catch (NullPointerException e) {
                                    mails = new ArrayList<ArrayList<String>>();
                                    mails.add(0, mail1);
                                    data.getProfils().get(user.getId()).setListMail(mails);
                                }
                            }
                            if (!Cmail) {
                                if (langc == command.Language.fr && !cible.hasPrivateChannel()) {
                                    cible.openPrivateChannel().complete().sendMessage(
                                            ":crossed_swords:  Rapport de Defense de ressources :crossed_swords: \n Vous avez perdu face a "
                                                    + user.getName() + ". Vous perdez " + def + " soldats")
                                            .queue();
                                }
                                if (langc == command.Language.en && !cible.hasPrivateChannel()) {
                                    cible.openPrivateChannel().complete().sendMessage(
                                            ":crossed_swords:  Ressource defense report :crossed_swords: \n **You lose** against "
                                                    + user.getName() + ". You lose " + def + " soldiers")
                                            .queue();
                                }
                            } else {
                                mail1 = new ArrayList();
                                if (langc == command.Language.fr) {
                                    mail1.add("Rapport de defense de ressource");
                                }
                                if (langc == command.Language.en) {
                                    mail1.add("Ressource defense report");
                                }
                                if (langc == command.Language.fr) {
                                    mail1.add(
                                            ":crossed_swords:  Rapport de Defense de ressources:crossed_swords: \n Vous avez perdu face a "
                                                    + user.getName() + ". Vous perdez " + def + " soldats");
                                }
                                if (langc == command.Language.en) {
                                    mail1.add(
                                            ":crossed_swords:  Ressource defense report :crossed_swords: \n **You lose** against "
                                                    + user.getName() + ". You lose " + def + " soldiers");
                                }
                                mail1.add("false");
                                mail1.add("" + System.currentTimeMillis());
                                try {
                                    mails2 = data.getProfils().get(cible.getId()).getListMail();
                                    mails2.add(0, mail1);
                                    data.getProfils().get(cible.getId()).setListMail(mails2);
                                } catch (NullPointerException e) {
                                    mails = new ArrayList();
                                    mails.add(0, mail1);
                                    data.getProfils().get(cible.getId()).setListMail(mails);
                                }
                            }
                            Quest.Quest("def", cible, ((UserImpl) cible).openPrivateChannel().complete(), 1);
                            CommandMap.PublicLog(
                                    ":crossed_swords: **Rapport d'attaque de ressources** :crossed_swords: \n\nle joueur "
                                            + user.getName() + " (" + user.getId()
                                            + ") a conquis la zone de ressource de " + cible.getName() + " ("
                                            + cible.getId() + ") en " + cible2.replace("_", " "),
                                    user.getJDA());
                            break block117;
                        }
                        try {
                            Umail3 = data.getProfils().get(user.getId()).isMail();
                        } catch (Exception e) {
                            Umail3 = false;
                        }
                        if (!Umail3) {
                            if (lang == command.Language.fr && !user.hasPrivateChannel()) {
                                user.openPrivateChannel().complete().sendMessage(
                                        "Vous venez d'ajouter " + atk + " soldats en " + cible2.replace("_", " "))
                                        .queue();
                            }
                            if (lang == command.Language.en && !user.hasPrivateChannel()) {
                                user.openPrivateChannel().complete()
                                        .sendMessage("You just add " + atk + " soldiers in " + cible2.replace("_", " "))
                                        .queue();
                            }
                        } else {
                            ArrayList<String> mail1 = new ArrayList<String>();
                            if (lang == command.Language.fr) {
                                mail1.add("Ajout de soldats sur un point de ressource");
                            }
                            if (lang == command.Language.en) {
                                mail1.add("Adding soldier to the ressource area");
                            }
                            if (lang == command.Language.fr) {
                                mail1.add("Vous venez d'ajouter " + atk + " soldats en " + cible2.replace("_", " "));
                            }
                            if (lang == command.Language.en) {
                                mail1.add("You just add " + atk + " soldier in " + cible2.replace("_", " "));
                            }
                            mail1.add("false");
                            mail1.add("" + System.currentTimeMillis());
                            try {
                                ArrayList<ArrayList<String>> mails = data.getProfils().get(user.getId()).getListMail();
                                mails.add(0, mail1);
                                data.getProfils().get(user.getId()).setListMail(mails);
                            } catch (NullPointerException e) {
                                ArrayList<ArrayList<String>> mails = new ArrayList<ArrayList<String>>();
                                mails.add(0, mail1);
                                data.getProfils().get(user.getId()).setListMail(mails);
                            }
                        }
                        CommandMap.PublicLog(
                                ":crossed_swords: **Rapport d'attaque de ressources** :crossed_swords: \n\nle joueur "
                                        + user.getName() + " (" + user.getId()
                                        + ") a ajouté des soldats sur la zone de ressource en "
                                        + cible2.replace("_", " "),
                                user.getJDA());
                        break block117;
                    }
                    perteSoldats = (int) ((double) def * (DPV * 1.0 / (double) def));
                    if (defHero.equals("true")) {
                        int cartesD = Integer.parseInt((String) listD.get(1));
                        String heroAtkD = "true";
                        int pvD = Integer.parseInt((String) listD.get(3));
                        double calcul1 = DPV * 1.0 / (double) (def + pvD);
                        int calcul = (int) ((double) pvD * calcul1);
                        perteSoldats = (int) ((double) def * calcul1);
                        listD.set(0, Integer.toString(levelD));
                        listD.set(1, Integer.toString(cartesD));
                        listD.set(2, heroAtkD);
                        listD.set(3, Integer.toString(calcul));
                        listD.set(4, Long.toString(System.currentTimeMillis()));
                        heroeD.put(data.getProfils().get(cible.getId()).getActiveHeroe(), listD);
                        data.getProfils().get(cible.getId()).setHeroe(heroeD);
                    }
                    if (hero.equals("true")) {
                        cartesA = Integer.parseInt((String) listA.get(1));
                        listA.set(0, Integer.toString(levelA));
                        listA.set(1, Integer.toString(cartesA));
                        listA.set(2, "false");
                        listA.set(3, "0");
                        listA.set(4, Long.toString(System.currentTimeMillis()));
                        heroeA.put(data.getProfils().get(user.getId()).getActiveHeroe(), listA);
                        data.getProfils().get(user.getId()).setHeroe(heroeA);
                    }
                    String UserPays = data.getProfils().get(user.getId()).getCountry();
                    String CiblePays = data.getProfils().get(cible.getId()).getCountry();
                    def = user.getId().equals(owner) || UserPays.equals(CiblePays) ? atk + def : perteSoldats;
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Map/" + cible2 + "/soldier.txt",
                            Integer.toString(def), 1);
                    if (!user.getId().equals(owner) && !UserPays.equals(CiblePays)) {
                        ArrayList<String> mail1;
                        ArrayList<ArrayList<String>> mails;
                        boolean Cmail;
                        try {
                            Umail2 = data.getProfils().get(user.getId()).isMail();
                        } catch (Exception e) {
                            Umail2 = false;
                        }
                        try {
                            Cmail = data.getProfils().get(cible.getId()).isMail();
                        } catch (Exception e) {
                            Cmail = false;
                        }
                        if (!Umail2) {
                            if (lang == command.Language.fr && !user.hasPrivateChannel()) {
                                user.openPrivateChannel().complete().sendMessage(
                                        ":crossed_swords: Rapport d'Attaque de ressources :crossed_swords: \n Vous avez perdu face a "
                                                + cible.getName() + ". Vous perdez " + atk + " soldiers")
                                        .queue();
                            }
                            if (lang == command.Language.en && !user.hasPrivateChannel()) {
                                user.openPrivateChannel().complete().sendMessage(
                                        ":crossed_swords: Ressources attack report :crossed_swords: \n **You lose** against "
                                                + cible.getName() + ". You lose " + atk + " soldiers")
                                        .queue();
                            }
                        } else {
                            mail1 = new ArrayList<String>();
                            if (lang == command.Language.fr) {
                                mail1.add("Rapport d'attaque de point de ressource");
                            }
                            if (lang == command.Language.en) {
                                mail1.add("Ressource attack report");
                            }
                            if (lang == command.Language.fr) {
                                mail1.add(
                                        ":crossed_swords: Rapport d'Attaque de ressources :crossed_swords: \n Vous avez perdu face a "
                                                + cible.getName() + ". Vous perdez " + atk + "soldats");
                            }
                            if (lang == command.Language.en) {
                                mail1.add(
                                        ":crossed_swords: Ressources attack report :crossed_swords: \n **You lose** against "
                                                + cible.getName() + ". You lose " + atk + " soldiers");
                            }
                            mail1.add("false");
                            mail1.add("" + System.currentTimeMillis());
                            try {
                                ArrayList<ArrayList<String>> mails3 = data.getProfils().get(user.getId()).getListMail();
                                mails3.add(0, mail1);
                                data.getProfils().get(user.getId()).setListMail(mails3);
                            } catch (NullPointerException e) {
                                mails = new ArrayList<ArrayList<String>>();
                                mails.add(0, mail1);
                                data.getProfils().get(user.getId()).setListMail(mails);
                            }
                        }
                        if (!Cmail) {
                            if (langc == command.Language.fr && !cible.hasPrivateChannel()) {
                                cible.openPrivateChannel().complete().sendMessage(
                                        ":crossed_swords:  Rapport de Defense de ressources:crossed_swords: \n Vous avez gagné face a "
                                                + user.getName() + ". Vous perdez " + atk + " soldats")
                                        .queue();
                            }
                            if (langc == command.Language.en && !cible.hasPrivateChannel()) {
                                cible.openPrivateChannel().complete().sendMessage(
                                        ":crossed_swords:  Ressource defense report :crossed_swords: \n **You won** against "
                                                + user.getName() + ". You lose " + atk + " soldiers")
                                        .queue();
                            }
                        } else {
                            mail1 = new ArrayList();
                            if (langc == command.Language.fr) {
                                mail1.add("Rapport de defense de point de ressource");
                            }
                            if (langc == command.Language.en) {
                                mail1.add("Ressources attack report");
                            }
                            if (langc == command.Language.fr) {
                                mail1.add(
                                        ":crossed_swords:  Rapport de Defense de ressources:crossed_swords: \n Vous avez gagné face a "
                                                + user.getName() + ". Vous perdez " + atk + " soldats");
                            }
                            if (langc == command.Language.en) {
                                mail1.add(
                                        ":crossed_swords:  Ressource defense report :crossed_swords: \n **You won** against "
                                                + user.getName() + ". You lose " + atk + " soldiers");
                            }
                            mail1.add("false");
                            mail1.add("" + System.currentTimeMillis());
                            try {
                                ArrayList<ArrayList<String>> mails4 = data.getProfils().get(cible.getId())
                                        .getListMail();
                                mails4.add(0, mail1);
                                data.getProfils().get(cible.getId()).setListMail(mails4);
                            } catch (NullPointerException e) {
                                mails = new ArrayList();
                                mails.add(0, mail1);
                                data.getProfils().get(cible.getId()).setListMail(mails);
                            }
                        }
                        Quest.Quest("def", cible, ((UserImpl) cible).getPrivateChannel(), 1);
                        CommandMap.PublicLog(
                                ":crossed_swords: **Rapport d'attaque de ressources** :crossed_swords: \n\nle joueur "
                                        + cible.getName() + " (" + cible.getId() + ") a subit une attaque de "
                                        + user.getName() + " (" + user.getId() + ") sur la zone de ressource en "
                                        + cible2.replace("_", " "),
                                user.getJDA());
                        break block117;
                    }
                    try {
                        Umail2 = data.getProfils().get(user.getId()).isMail();
                    } catch (Exception e) {
                        Umail2 = false;
                    }
                    if (!Umail2) {
                        if (!user.hasPrivateChannel()) {
                            user.openPrivateChannel().complete();
                        }
                        if (lang == command.Language.fr) {
                            ((UserImpl) user).getPrivateChannel()
                                    .sendMessage(
                                            "Vous venez d'ajouter " + atk + " soldats en " + cible2.replace("_", " "))
                                    .queue();
                        }
                        if (lang == command.Language.en) {
                            ((UserImpl) user).getPrivateChannel()
                                    .sendMessage("You just add " + atk + " soldiers in " + cible2.replace("_", " "))
                                    .queue();
                        }
                    } else {
                        ArrayList<String> mail1 = new ArrayList<String>();
                        if (lang == command.Language.fr) {
                            mail1.add("Ajout de soldats sur une zone de ressource");
                        }
                        if (lang == command.Language.en) {
                            mail1.add("Adding soldiers to a ressource point");
                        }
                        if (lang == command.Language.fr) {
                            mail1.add("Vous venez d'ajouter " + atk + " soldats en " + cible2.replace("_", " "));
                        }
                        if (lang == command.Language.en) {
                            mail1.add("You just add " + atk + " soldiers in " + cible2.replace("_", " "));
                        }
                        mail1.add("false");
                        mail1.add("" + System.currentTimeMillis());
                        try {
                            ArrayList<ArrayList<String>> mails = data.getProfils().get(user.getId()).getListMail();
                            mails.add(0, mail1);
                            data.getProfils().get(user.getId()).setListMail(mails);
                        } catch (NullPointerException e) {
                            ArrayList<ArrayList<String>> mails = new ArrayList<ArrayList<String>>();
                            mails.add(0, mail1);
                            data.getProfils().get(user.getId()).setListMail(mails);
                        }
                    }
                    CommandMap.PublicLog(
                            ":crossed_swords: **Rapport d'attaque de ressources** :crossed_swords: \n\nle joueur "
                                    + user.getName() + " (" + user.getId()
                                    + ") a ajouté des soldats sur la zone de ressource en " + cible2.replace("_", " "),
                            user.getJDA());
                    break block117;
                }
                ProfilData data = DiscordBot.getData();
                command.Language lang = command.Language.fr;
                if (hero.equals("true")) {
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Map/" + cible2 + "/hero.txt", "true",
                            1);
                }
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Map/" + cible2 + "/soldier.txt",
                        Integer.toString(atk), 1);
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Map/" + cible2 + "/owner.txt", user.getId(),
                        1);
                try {
                    Umail = data.getProfils().get(user.getId()).isMail();
                } catch (Exception e) {
                    Umail = false;
                }
                if (!Umail) {
                    if (!user.hasPrivateChannel()) {
                        user.openPrivateChannel().complete();
                    }
                    if (lang == command.Language.fr) {
                        ((UserImpl) user).getPrivateChannel()
                                .sendMessage("Vous venez d'ajouter " + atk + " soldats en " + cible2.replace("_", " "))
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        ((UserImpl) user).getPrivateChannel()
                                .sendMessage("You just add " + atk + " soldiers in " + cible2.replace("_", " "))
                                .queue();
                    }
                } else {
                    ArrayList<String> mail1 = new ArrayList<String>();
                    if (lang == command.Language.fr) {
                        mail1.add("Ajout de soldats sur une zone de ressource");
                    }
                    if (lang == command.Language.en) {
                        mail1.add("Adding soldiers to a ressource point");
                    }
                    if (lang == command.Language.fr) {
                        mail1.add("Vous venez d'ajouter " + atk + " soldats en " + cible2.replace("_", " "));
                    }
                    if (lang == command.Language.en) {
                        mail1.add("You just add " + atk + " soldiers in " + cible2.replace("_", " "));
                    }
                    mail1.add("false");
                    mail1.add("" + System.currentTimeMillis());
                    try {
                        ArrayList<ArrayList<String>> mails = data.getProfils().get(user.getId()).getListMail();
                        mails.add(0, mail1);
                        data.getProfils().get(user.getId()).setListMail(mails);
                    } catch (NullPointerException e) {
                        ArrayList<ArrayList<String>> mails = new ArrayList<ArrayList<String>>();
                        mails.add(0, mail1);
                        data.getProfils().get(user.getId()).setListMail(mails);
                    }
                }
                CommandMap.PublicLog(
                        ":crossed_swords: **Rapport d'attaque de ressources** :crossed_swords: \n\nle joueur "
                                + user.getName() + " (" + user.getId()
                                + ") a ajouté des soldats sur la zone de ressource en " + cible2.replace("_", " "),
                        user.getJDA());
            } catch (Exception e) {
                e.printStackTrace();
                CommandMap.Log("Attack point de ravitaillement", e.getLocalizedMessage(), jda);
            }
        }
    }

    public static void AttackDungeon(User user, String cibleId, JDA jda) {
        block52: {
            try {
                HashMap<String, ArrayList<String>> heroe;
                int ABonus;
                boolean Umail;
                double pvB = Double.parseDouble(
                        TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Map/" + cibleId + "/pv.txt"));
                int DBonus;
                command.Language lang = DiscordBot.getData().getProfils().get(user.getId()).getLanguage();
                ProfilData data = DiscordBot.getData();
                try {
                    heroe = data.getProfils().get(user.getId()).getHeroe();
                } catch (NullPointerException e) {
                    heroe = new HashMap();
                    ArrayList<String> list2 = new ArrayList<String>();
                    list2.add("1");
                    list2.add("0");
                    list2.add("false");
                    list2.add("0");
                    heroe.put("Karl", list2);
                    heroe.put("Valkyrie", null);
                    heroe.put("Ouranos", null);
                    heroe.put("Oeil", null);
                    heroe.put("Ikaryus", null);
                    heroe.put("Yegarde", null);
                    heroe.put("Angel", null);
                    heroe.put("Zhen", null);
                    heroe.put("Hearth", null);
                    heroe.put("Lixie", null);
                    heroe.put("Akashi", null);
                    heroe.put("Rose", null);
                    heroe.put("Hell", null);
                    heroe.put("Spirita", null);
                    heroe.put("Tempest", null);
                    heroe.put("Ivoire", null);
                }
                ArrayList list = null;
                try {
                    list = (ArrayList) heroe.get(data.getProfils().get(user.getId()).getActiveHeroe());
                } catch (NullPointerException list2) {
                    // empty catch block
                }
                int level = Integer.parseInt((String) list.get(0));
                double pvU = Heroe.getPV(data.getProfils().get(user.getId()).getActiveHeroe(), user);
                double atkU = Heroe.getAtk(data.getProfils().get(user.getId()).getActiveHeroe(), level, user);
                double defU = Heroe.getDef(data.getProfils().get(user.getId()).getActiveHeroe(), level, user);
                double magU = Heroe.getMagic(data.getProfils().get(user.getId()).getActiveHeroe(), level);
                double atkB = Integer.parseInt(
                        TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Map/" + cibleId + "/atk.txt"));
                double defB = Integer.parseInt(
                        TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Map/" + cibleId + "/atk.txt"));
                double magB = Integer.parseInt(
                        TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Map/" + cibleId + "/atk.txt"));
                double pvBSosstart = pvB;
                for (pvB = Double.parseDouble((String) TextFileWriter.read(
                        (String) new java.lang.StringBuilder((String) "/home/DiscordBot/Rasberry/données/bot/Map/")
                                .append((String) cibleId).append((String) "/pv.txt").toString())); pvU > 0.0
                                        && pvB > 0.0; pvB -= atkU + (double) ABonus / defB, pvU -= atkB
                                                + (double) DBonus / defU) {
                    ABonus = 0 + (int) (Math.random() * (magU - 0.0 + 1.0));
                    DBonus = 0 + (int) (Math.random() * (magB - 0.0 + 1.0));
                }
                if (pvU <= 0.0 && pvB > 0.0) {
                    boolean Umail2;
                    list.set(2, "false");
                    list.set(3, "0");
                    list.set(4, Long.toString(System.currentTimeMillis()));
                    heroe.put(data.getProfils().get(user.getId()).getActiveHeroe(), list);
                    data.getProfils().get(user.getId()).setHeroe(heroe);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Map/" + cibleId + "/pv.txt",
                            Integer.toString((int) pvB), 1);
                    try {
                        Umail2 = data.getProfils().get(user.getId()).isMail();
                    } catch (Exception e) {
                        Umail2 = false;
                    }
                    if (!Umail2) {
                        if (!user.hasPrivateChannel()) {
                            user.openPrivateChannel().complete();
                        }
                        if (lang == command.Language.fr) {
                            ((UserImpl) user).getPrivateChannel().sendMessage(
                                    ":crossed_swords: Rapport d'Attaque de Donjon :crossed_swords: \n Vous avez perdu face au boss du donjon. Votre hero a perdu tout ses PV, il aura besoin de temps pour recuperer.. Vous avez mit "
                                            + (pvBSosstart - pvB) + " de dégat au boss.")
                                    .queue();
                        }
                        if (lang == command.Language.en) {
                            ((UserImpl) user).getPrivateChannel().sendMessage(
                                    ":crossed_swords: Dungeon attack report :crossed_swords: \n You lose against the boss donjon. Your hero lose all his HP, he will need some time to regen. You put "
                                            + (pvBSosstart - pvB) + " damage to the boss.")
                                    .queue();
                        }
                    } else {
                        ArrayList<String> mail1 = new ArrayList<String>();
                        if (lang == command.Language.fr) {
                            mail1.add("Rapport d'attaque donjon");
                        }
                        if (lang == command.Language.en) {
                            mail1.add("Dungeon attack report");
                        }
                        if (lang == command.Language.fr) {
                            mail1.add(
                                    ":crossed_swords: Rapport d'Attaque de Donjon :crossed_swords: \n Vous avez perdu face au boss du donjon. Votre hero a perdu tout ses PV, il aura besoin de temps pour recuperer. Vous avez mit "
                                            + (pvBSosstart - pvB) + " de dégat au boss.");
                        }
                        if (lang == command.Language.en) {
                            mail1.add(
                                    ":crossed_swords: Dungeon attack report :crossed_swords: \n You lose against the boss donjon. Your hero lose all his HP, he will need some time to regen. You put "
                                            + (pvBSosstart - pvB) + " damage to the boss.");
                        }
                        mail1.add("false");
                        mail1.add("" + System.currentTimeMillis());
                        try {
                            ArrayList<ArrayList<String>> mails = data.getProfils().get(user.getId()).getListMail();
                            mails.add(0, mail1);
                            data.getProfils().get(user.getId()).setListMail(mails);
                        } catch (NullPointerException e) {
                            ArrayList<ArrayList<String>> mails = new ArrayList<ArrayList<String>>();
                            mails.add(0, mail1);
                            data.getProfils().get(user.getId()).setListMail(mails);
                        }
                    }
                    CommandMap
                            .PublicLog(
                                    ":crossed_swords: **Rapport d'attaque de donjon** :crossed_swords: \n\nle joueur "
                                            + user.getName() + " (" + user.getId()
                                            + ") a affaiblit le boss du donjon en " + cibleId.replace("_", ""),
                                    user.getJDA());
                    break block52;
                }
                list.set(2, "false");
                list.set(3, Integer.toString((int) pvU));
                list.set(4, Long.toString(System.currentTimeMillis()));
                heroe.put(data.getProfils().get(user.getId()).getActiveHeroe(), list);
                data.getProfils().get(user.getId()).setHeroe(heroe);
                int levelB = Integer.parseInt(
                        TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Map/" + cibleId + "/bosslevel.txt"));
                try {
                    TextFileWriter.recursifDelete(new File("/home/DiscordBot/Rasberry/données/bot/Map/" + cibleId));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int aleaLB = 1 + (int) (Math.random() * (double) (levelB - 1 + 1));
                int lb = data.getProfils().get(user.getId()).getLootbox();
                data.getProfils().get(user.getId()).setLootbox(lb += aleaLB);
                try {
                    Umail = data.getProfils().get(user.getId()).isMail();
                } catch (Exception e) {
                    Umail = false;
                }
                if (!Umail) {
                    if (!user.hasPrivateChannel()) {
                        user.openPrivateChannel().complete();
                    }
                    if (lang == command.Language.fr) {
                        ((UserImpl) user).getPrivateChannel().sendMessage(
                                ":crossed_swords: Rapport d'Attaque de donjon :crossed_swords: \n Vous avez bettu le boss du donjon. Votre hero n'a plus que "
                                        + pvU + " point de vie. \n Vous avez obtenu " + aleaLB + " lootbox.")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        ((UserImpl) user).getPrivateChannel().sendMessage(
                                ":crossed_swords: Dungeon attack report :crossed_swords: \n You kill the dungeon boss. Your hero has now "
                                        + pvU + " HP. \n You won " + aleaLB + " lootbox.")
                                .queue();
                    }
                } else {
                    ArrayList<String> mail1 = new ArrayList<String>();
                    if (lang == command.Language.fr) {
                        mail1.add("Rapport d'attaque donjon");
                    }
                    if (lang == command.Language.en) {
                        mail1.add("Dungeon attack report");
                    }
                    if (lang == command.Language.fr) {
                        mail1.add(
                                ":crossed_swords: Rapport d'Attaque de donjon :crossed_swords: \n Vous avez bettu le boss du donjon. Votre hero n'a plus que "
                                        + pvU + " point de vie. \n Vous avez obtenu " + aleaLB + " lootbox.");
                    }
                    if (lang == command.Language.en) {
                        mail1.add(
                                ":crossed_swords: Dungeon attack report :crossed_swords: \n You kill the dungeon boss. Your hero has now "
                                        + pvU + " HP. \n You won " + aleaLB + " lootbox.");
                    }
                    mail1.add("false");
                    mail1.add("" + System.currentTimeMillis());
                    try {
                        ArrayList<ArrayList<String>> mails = data.getProfils().get(user.getId()).getListMail();
                        mails.add(0, mail1);
                        data.getProfils().get(user.getId()).setListMail(mails);
                    } catch (NullPointerException e) {
                        ArrayList<ArrayList<String>> mails = new ArrayList<ArrayList<String>>();
                        mails.add(0, mail1);
                        data.getProfils().get(user.getId()).setListMail(mails);
                    }
                }
                CommandMap.PublicLog(":crossed_swords: **Rapport d'attaque de donjon** :crossed_swords: \n\nle joueur "
                        + user.getName() + " (" + user.getId() + ") a vaincu le boss du donjon en "
                        + cibleId.replace("_", " "), user.getJDA());
            } catch (Exception e) {
                boolean Umail;
                HashMap<String, ArrayList<String>> heroe;
                command.Language lang = DiscordBot.getData().getProfils().get(user.getId()).getLanguage();
                ProfilData data = DiscordBot.getData();
                try {
                    heroe = data.getProfils().get(user.getId()).getHeroe();
                } catch (NullPointerException e1) {
                    heroe = new HashMap();
                    ArrayList<String> list3 = new ArrayList<String>();
                    list3.add("1");
                    list3.add("0");
                    list3.add("false");
                    list3.add("0");
                    heroe.put("Karl", list3);
                    heroe.put("Valkyrie", null);
                    heroe.put("Ouranos", null);
                    heroe.put("Oeil", null);
                    heroe.put("Ikaryus", null);
                    heroe.put("Yegarde", null);
                    heroe.put("Angel", null);
                    heroe.put("Zhen", null);
                    heroe.put("Hearth", null);
                    heroe.put("Lixie", null);
                    heroe.put("Akashi", null);
                    heroe.put("Rose", null);
                    heroe.put("Hell", null);
                    heroe.put("Spirita", null);
                    heroe.put("Tempest", null);
                    heroe.put("Ivoire", null);
                }
                ArrayList list = null;
                try {
                    list = (ArrayList) heroe.get(data.getProfils().get(user.getId()).getActiveHeroe());
                } catch (NullPointerException list3) {
                    // empty catch block
                }
                double pvU = Heroe.getPV(data.getProfils().get(user.getId()).getActiveHeroe(), user);
                list.set(2, "false");
                list.set(3, Integer.toString((int) pvU));
                list.set(4, Long.toString(System.currentTimeMillis()));
                heroe.put(data.getProfils().get(user.getId()).getActiveHeroe(), list);
                data.getProfils().get(user.getId()).setHeroe(heroe);
                try {
                    Umail = data.getProfils().get(user.getId()).isMail();
                } catch (Exception e1) {
                    Umail = false;
                }
                if (!Umail) {
                    if (!user.hasPrivateChannel()) {
                        user.openPrivateChannel().complete();
                    }
                    if (lang == command.Language.fr) {
                        ((UserImpl) user).getPrivateChannel().sendMessage(
                                ":crossed_swords: Rapport d'Attaque de donjon :crossed_swords: \n Votre hero est arrivé trop tard, un joueur a deja vaincu le donjon.")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        ((UserImpl) user).getPrivateChannel().sendMessage(
                                ":crossed_swords: Dungeon attack report :crossed_swords: \n Your hero arrived too late and another person killed the boss.")
                                .queue();
                    }
                }
                ArrayList<String> mail1 = new ArrayList<String>();
                if (lang == command.Language.fr) {
                    mail1.add("Rapport d'attaque donjon");
                }
                if (lang == command.Language.en) {
                    mail1.add("Dungeon attack report");
                }
                if (lang == command.Language.fr) {
                    mail1.add(
                            ":crossed_swords: Rapport d'Attaque de donjon :crossed_swords: \\n Votre hero est arrivé trop tard, un joueur a deja vaincu le donjon.");
                }
                if (lang == command.Language.en) {
                    mail1.add(
                            ":crossed_swords: Dungeon attack report :crossed_swords: \n Your hero arrived too late and another person killed the boss.");
                }
                mail1.add("false");
                mail1.add("" + System.currentTimeMillis());
                try {
                    ArrayList<ArrayList<String>> mails = data.getProfils().get(user.getId()).getListMail();
                    mails.add(0, mail1);
                    data.getProfils().get(user.getId()).setListMail(mails);
                } catch (NullPointerException e1) {
                    ArrayList<ArrayList<String>> mails = new ArrayList<ArrayList<String>>();
                    mails.add(0, mail1);
                    data.getProfils().get(user.getId()).setListMail(mails);
                }
            }
        }
    }
}
