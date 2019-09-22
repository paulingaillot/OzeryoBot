/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.Game.habitants;
import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import fr.Ybsi.OzeryoBot.Utils.Quest;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class Soldier {
    @command(name = "soldier", abbrev = "sol", type = command.ExecutorType.ALL, descfr = "usage : [BETA] creer une ville et developpe la au fil de temps", topic = command.Topics.Game)
    private void soldier(MessageChannel channel, User user, Guild guild, String[] args, command.Language lang) {
        ArrayList<String> list;
        int c2;
        String c1;
        int Pet_EXP;
        ProfilData data = DiscordBot.getData();
        try {
            c1 = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            c1 = "";
        }
        boolean max = false;
        try {
            if (args[1].equals("max")) {
                max = true;
            }
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            // empty catch block
        }
        try {
            c2 = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            c2 = 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            c2 = 0;
        }
        long habitant = habitants.pop(user);
        String ActivePet = TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/pet.txt");
        HashMap<String, ArrayList<String>> pet = data.getProfils().get(user.getId()).getPet();
        try {
            list = data.getProfils().get(user.getId()).getPet().get(ActivePet);
        } catch (NullPointerException e) {
            list = null;
        }
        try {
            Pet_EXP = Integer.parseInt(data.getProfils().get(user.getId()).getPet().get(ActivePet).get(1));
        } catch (NullPointerException e) {
            Pet_EXP = 0;
        }
        String Pet_Bonus = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pets/" + ActivePet);
        double operation = Pet_EXP / 10;
        double operation2 = Math.sqrt(operation);
        double Pet_Level = Math.round(operation2);
        double pet_bonus = 1.0 + 0.03 * Pet_Level;
        if (c1.equals("train")) {
            int bonus_caserne;
            String eglise;
            long soldier = data.getProfils().get(user.getId()).getSoldiers();
            long money = data.getProfils().get(user.getId()).getMoney();
            HashMap<String, Integer> building = data.getProfils().get(user.getId()).getBuilding();
            int caserne = building.get("camp d'entrainement");
            if (caserne == 0) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous devez construire votre caserne pour enroler des soldats.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You must build your caserne to enlist soldiers.").queue();
                }
                return;
            }
            switch (caserne) {
            case 1: {
                bonus_caserne = 105;
                int cout_hab = 1;
                break;
            }
            case 2: {
                bonus_caserne = 100;
                int cout_hab = 1;
                break;
            }
            case 3: {
                bonus_caserne = 95;
                int cout_hab = 2;
                break;
            }
            case 4: {
                bonus_caserne = 90;
                int cout_hab = 2;
                break;
            }
            case 5: {
                bonus_caserne = 85;
                int cout_hab = 3;
                break;
            }
            case 6: {
                bonus_caserne = 80;
                int cout_hab = 3;
                break;
            }
            case 7: {
                bonus_caserne = 75;
                int cout_hab = 4;
                break;
            }
            case 8: {
                bonus_caserne = 70;
                int cout_hab = 4;
                break;
            }
            case 9: {
                bonus_caserne = 65;
                int cout_hab = 5;
                break;
            }
            case 10: {
                bonus_caserne = 60;
                int cout_hab = 5;
                break;
            }
            case 11: {
                bonus_caserne = 55;
                int cout_hab = 6;
                break;
            }
            case 12: {
                bonus_caserne = 50;
                int cout_hab = 6;
                break;
            }
            case 13: {
                bonus_caserne = 45;
                int cout_hab = 7;
                break;
            }
            case 14: {
                bonus_caserne = 40;
                int cout_hab = 7;
                break;
            }
            case 15: {
                bonus_caserne = 35;
                int cout_hab = 8;
                break;
            }
            case 16: {
                bonus_caserne = 30;
                int cout_hab = 8;
                break;
            }
            case 17: {
                bonus_caserne = 25;
                int cout_hab = 9;
                break;
            }
            case 18: {
                bonus_caserne = 20;
                int cout_hab = 9;
                break;
            }
            case 19: {
                bonus_caserne = 15;
                int cout_hab = 10;
                break;
            }
            case 20: {
                bonus_caserne = 10;
                int cout_hab = 10;
                break;
            }
            case 21: {
                bonus_caserne = 9;
                int cout_hab = 10;
                break;
            }
            case 22: {
                bonus_caserne = 8;
                int cout_hab = 10;
                break;
            }
            case 23: {
                bonus_caserne = 7;
                int cout_hab = 10;
                break;
            }
            case 24: {
                bonus_caserne = 6;
                int cout_hab = 10;
                break;
            }
            case 25: {
                bonus_caserne = 5;
                int cout_hab = 10;
                break;
            }
            case 26: {
                bonus_caserne = 5;
                int cout_hab = 10;
                break;
            }
            case 27: {
                bonus_caserne = 5;
                int cout_hab = 10;
                break;
            }
            case 28: {
                bonus_caserne = 5;
                int cout_hab = 10;
                break;
            }
            case 29: {
                bonus_caserne = 5;
                int cout_hab = 10;
                break;
            }
            case 30: {
                bonus_caserne = 5;
                int cout_hab = 10;
                break;
            }
            default: {
                bonus_caserne = 5;
                int cout_hab = 1;
            }
            }
            int price = c2 * bonus_caserne;
            int price_hab = c2 * 1;
            int price2 = 0;
            long LastTrain = data.getProfils().get(user.getId()).getLastTrain();
            long delai = (System.currentTimeMillis() - LastTrain) / 3600000L;
            long soldiers = data.getProfils().get(user.getId()).getTrain();
            soldiers += (long) ((int) ((double) delai * (0.005 * (double) habitant)));
            int tuto = data.getProfils().get(user.getId()).getTuto();
            if ((double) soldiers > 0.1 * (double) habitant && tuto != 8) {
                soldiers = (int) (0.1 * (double) habitant);
            }
            if (max) {
                for (c2 = 0; (long) price2 < money - 10L && (long) c2 < soldiers
                        && (double) price_hab < (double) habitant * 0.25 || tuto == 8 && c2 < 100; ++c2) {
                    price = price2;
                    price_hab = c2;
                    price2 = c2 * bonus_caserne;
                }
            }
            if (soldiers == 0L && tuto != 8) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous avez atteind votre limite d'entrainement horaire.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You already reach your hourly train limit.").queue();
                }
                return;
            }
            if (price == 0 && !max && tuto != 8) {
                if (soldiers < 0L) {
                    data.getProfils().get(user.getId()).setSoldiers(1L);
                }
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous pouvez former encore " + soldiers + " soldats.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You can train " + soldiers + " soldiers.").queue();
                }
                return;
            }
            if (price == 0 && max && tuto != 8) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous n'avez plus asser d'argent pour enroler de nouveaux soldats.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You don't have enough money to train new soldiers.").queue();
                }
                return;
            }
            if ((long) price > money && tuto != 8) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Désolé mais vous n'avez pas asser d'argent pour former autant de soldats")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Sorry but you don't have enough money to train so much soldiers").queue();
                }
                return;
            }
            if ((long) price_hab > habitant && tuto != 8) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Désolé mais vous n'avez pas asser d'habitants pour former autant de soldats")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Sorry but you don't have enough people to train so much soldiers").queue();
                }
                return;
            }
            if (c2 < 0) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("veuillez indiquer un nombre valide").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Please type a vlaid nulber").queue();
                }
                return;
            }
            soldier += (long) c2;
            if (tuto == 8) {
                price = 0;
                price_hab = 0;
            }
            money -= (long) price;
            habitant -= (long) price_hab;
            String message = "";
            int soldier2 = 0;
            if (Pet_Bonus.equals("soldier")) {
                double bonus = 1.0 + Pet_Level * 3.0 / 100.0;
                soldier2 = (int) ((double) c2 * bonus) - c2;
                soldier += (long) soldier2;
                if (lang == command.Language.fr) {
                    message = " Votre pet vous apporte un bonus de " + soldier2 + " soldats en plus.";
                }
                if (lang == command.Language.en) {
                    message = "Your pet give you a bonus of " + soldier2 + " soldiers more.";
                }
            }
            if ((eglise = TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/eglise.txt")).equals("Ares")) {
                soldier += (long) (soldier2 += (int) ((double) c2 * 1.25) - c2);
                if (lang == command.Language.fr) {
                    message = String.valueOf(message) + " Vous obtenez un bonus de " + soldier2
                            + " soldats grace a Ares.";
                }
                if (lang == command.Language.en) {
                    message = String.valueOf(message) + "You obtain a bonus of " + soldier2
                            + " soldiers more thank to Ares.";
                }
            }
            if ((double) soldier > (double) habitant * 0.25 || (long) c2 > soldiers) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Désolé mais vous ne pouvez pas former autant de soldats.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Sorry but you can't train so much sodliers.").queue();
                }
                return;
            }
            try {
                data.getProfils().get(user.getId()).setSoldiers(soldier);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setSoldiers(soldier);
            }
            try {
                data.getProfils().get(user.getId()).setMoney(money);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setMoney(money);
            }
            try {
                data.getProfils().get(user.getId()).setHabitants(habitant);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setHabitants(habitant);
            }
            Quest.Quest("soldier", user, channel, c2);
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous venez de creer " + c2 + "soldats pour " + price + "$ et " + price_hab
                        + " habitants." + message + " Vous pouvez encore former " + (soldiers - (long) c2)
                        + " soldats actuelement.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage(
                        "You just train " + c2 + " soldiers for " + price + "$ and " + price_hab + " people." + message
                                + " You can train " + (soldiers - (long) c2) + " soldiers more actually.")
                        .queue();
            }
            if (tuto == 8) {
                data.getProfils().get(user.getId()).setTuto(9);
                if (lang == command.Language.fr) {
                    channel.sendMessage(
                            "Pour lancer une attaque vous pouvez consulter la map et utiliser l'id, le tag ou les coordonnés d'un joueur. Testons maintenant : =a Ozeryo 15.")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(
                            "To launch an attack you can check the map and use a player's id, tag, or coordinates. Now, let's test: =a Ozeryo 15.")
                            .queue();
                }
            }
            try {
                data.getProfils().get(user.getId()).setLastTrain(System.currentTimeMillis());
                data.getProfils().get(user.getId()).setTrain(soldiers - (long) c2);
                data.getProfils().get(user.getId()).setId(user.getId());
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setLastTrain(System.currentTimeMillis());
                data.getProfils().get(user.getId()).setTrain(soldiers - (long) c2);
            }
        }
    }
}
