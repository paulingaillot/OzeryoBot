/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Utils.*;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.HashMap;

public class hourly {
    @command(name = "hourly", abbrev = "hr", type = command.ExecutorType.ALL, descfr = "usage : [BETA] creer une ville et developpe la au fil de temps", topic = command.Topics.Game)
    private void hourly(MessageChannel channel, User user, command.Language lang) {
        ProfilData data = DiscordBot.getData();
        long lastHourly = data.getProfils().get(user.getId()).getLastHourly();
        long delay = System.currentTimeMillis() - lastHourly;
        long money = data.getProfils().get(user.getId()).getMoney();
        int tuto = data.getProfils().get(user.getId()).getTuto();
        if (tuto == 2) {
            delay = 4000000L;
        }
        if (delay >= 3600000L) {
            ArrayList<String> list;
            int level;
            int Pet_EXP;
            try {
                data.getProfils().get(user.getId()).setLastHourly(System.currentTimeMillis());
                data.getProfils().get(user.getId()).setId(user.getId());
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setLastHourly(System.currentTimeMillis());
            }
            int Game_EXP = data.getProfils().get(user.getId()).getXp();
            try {
                double math = Math.sqrt(Game_EXP);
                level = (int) Math.round(math);
            } catch (NullPointerException e) {
                level = 0;
            }
            HashMap<String, Integer> building = data.getProfils().get(user.getId()).getBuilding();
            int struct = building.get("marché");
            String Halloween1 = "";
            if (Event.Summer()) {
                int nbalea = 50 + (int) (Math.random() * 51.0);
                int gain = 0;
                int xp = data.getProfils().get(user.getId()).getOzPassXp();
                double bonus1 = (double) data.getProfils().get(user.getId()).getBonus() / 100.0;
                gain = (int) ((double) nbalea * (1.0 + bonus1));
                data.getProfils().get(user.getId()).setOzPassXp(xp += gain);
                if (lang == command.Language.fr) {
                    Halloween1 = " Vous avez aussi gagné " + gain + " OzXp";
                }
                if (lang == command.Language.en) {
                    Halloween1 = " You also won " + gain + " OzXp";
                }
            }
            double bonus = Structure.entreprise(struct);
            long pop = data.getProfils().get(user.getId()).getHabitants();
            int EXP_win = 0;
            int money_win = 0;
            int pop_win = 0;
            int Alea_money = 3 + (int) (Math.random() * 4.0);
            int money_win2 = (int) ((double) Alea_money * (100.0 + (double) (10 * level) * bonus));
            money_win = Math.round(money_win2) + money_win;
            int Alea_pop = 3 + (int) (Math.random() * 4.0);
            int pop_win2 = (int) ((double) Alea_pop * (10.0 + (double) (15 * level) * bonus));
            pop_win = Math.round(pop_win2) + pop_win;
            EXP_win = 10 + (int) (Math.random() * 21.0);
            String ActivePet = TextFileWriter
                    .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/pet.txt");
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
            double operationpet = Pet_EXP / 10;
            double operationpet2 = Math.sqrt(operationpet);
            double Pet_Level = Math.round(operationpet2);
            double pet_bonus = 1.0 + 0.1 * Pet_Level;
            if (!ActivePet.equals("0")) {
                Pet_EXP += EXP_win / 10;
            }
            if (list != null) {
                list.remove(1);
            }
            if (list != null) {
                list.add(1, String.valueOf(Pet_EXP));
            }
            if (list != null) {
                pet.put(ActivePet, list);
            }
            if (Pet_Bonus.equals("resHr")) {
                money_win = (int) ((double) money_win * pet_bonus);
                pop_win = (int) ((double) pop_win * pet_bonus);
            }
            try {
                data.getProfils().get(user.getId()).setPet(pet);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setPet(pet);
            }
            Quest.Quest("exp", data.getProfils().get(user.getId()), channel, EXP_win);
            Quest.Quest("hr", data.getProfils().get(user.getId()), channel, 1);
            int jetons1 = data.getProfils().get(user.getId()).getTokens();
            ++jetons1;
            try {
                data.getProfils().get(user.getId()).setTokens(jetons1);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setTokens(jetons1);
            }
            pop += pop_win;
            money += money_win;
            Game_EXP += EXP_win;
            Quest.Quest("jetons", data.getProfils().get(user.getId()), channel, 1);
            try {
                data.getProfils().get(user.getId()).setXp(Game_EXP);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setXp(Game_EXP);
            }
            try {
                data.getProfils().get(user.getId()).setMoney(money);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setMoney(money);
            }
            try {
                data.getProfils().get(user.getId()).setHabitants(pop);
            } catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setHabitants(pop);
            }
            int hourly2 = data.getProfils().get(user.getId()).getHourly_récolté();
            data.getProfils().get(user.getId()).setHourly_récolté(++hourly2);
            int jetons = data.getProfils().get(user.getId()).getJetons_récolté();
            data.getProfils().get(user.getId()).setJetons_récolté(++jetons);
            if (lang == command.Language.fr) {
                channel.sendMessage("\ud83d\udd51 Votre hourly vous a rapporté **" + pop_win + "** habitants, **"
                        + money_win + "** money, **1** jetons et **" + EXP_win + "** EXP. " + Halloween1).queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("\ud83d\udd51 Your hourly give you **" + pop_win + "** people, **" + money_win
                        + "** money, **1** tokens et **" + EXP_win + "** Xp. " + Halloween1).queue();
            }
            if (tuto == 2) {
                data.getProfils().get(user.getId()).setTuto(3);
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous pouvez maintenant consulter votre ville avec la commande =city.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You can now check your city informations with the =city command.").queue();
                }
            }
        } else {
            int mMinute = (int) (delay / 60000L);
            int mSecond = (int) ((delay %= 60000L) / 1000L);
            if (lang == command.Language.fr) {
                channel.sendMessage(
                        "\ud83d\udd59 Votre hourly pourra \u00eatre récupéré dans **" + (59 - mMinute) + "** minutes. ")
                        .queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage(
                        "\ud83d\udd59 Your hourly will be recoverable in **" + (59 - mMinute) + "** minutes. ").queue();
            }
        }
    }
}
