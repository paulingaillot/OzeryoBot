/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.Game.Structure;
import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Utils.Event;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import fr.Ybsi.OzeryoBot.Utils.Quest;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class Daily {
    @command(name="daily", abbrev="dl", type=command.ExecutorType.ALL, descfr="usage : [BETA] creer une ville et developpe la au fil de temps", topic=command.Topics.Game)
    private void daily(MessageChannel channel, User user, command.Language lang) {
        ProfilData data = DiscordBot.getData();
        long lastHourly = data.getProfils().get(user.getId()).getLastDaily();
        long delay = System.currentTimeMillis() - lastHourly;
        long money = data.getProfils().get(user.getId()).getMoney();
        int tuto = data.getProfils().get(user.getId()).getTuto();
        if (tuto == 1) {
            delay = 100000000L;
        }
        if (delay >= 86400000L) {
            int Pet_EXP;
            ArrayList<String> list;
            int level;
            try {
                data.getProfils().get(user.getId()).setLastDaily(System.currentTimeMillis());
                data.getProfils().get(user.getId()).setId(user.getId());
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setLastDaily(System.currentTimeMillis());
            }
            int Game_EXP = data.getProfils().get(user.getId()).getXp();
            long pop = data.getProfils().get(user.getId()).getHabitants();
            HashMap<String, Integer> building = data.getProfils().get(user.getId()).getBuilding();
            int struct = building.get("marché");
            double bonus = Structure.entreprise(struct);
            try {
                double math = Math.sqrt(Game_EXP);
                level = (int)Math.round(math);
            }
            catch (NullPointerException e) {
                level = 0;
            }
            int EXP_win = 0;
            int money_win = 0;
            int pop_win = 0;
            int Alea_money = 30 + (int)(Math.random() * 31.0);
            int money_win2 = (int)((double)Alea_money * (100.0 + (double)(10 * level) * bonus));
            money_win = Math.round(money_win2) + money_win;
            int Alea_pop = 30 + (int)(Math.random() * 31.0);
            int pop_win2 = (int)((double)Alea_pop * (10.0 + (double)(15 * level) * bonus));
            pop_win = Math.round(pop_win2) + pop_win;
            EXP_win = 100 + (int)(Math.random() * 101.0);
            int jetons1 = data.getProfils().get(user.getId()).getTokens();
            jetons1 += 5;
            try {
                data.getProfils().get(user.getId()).setTokens(jetons1);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setTokens(jetons1);
            }
            String pet = TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/pet.txt");
            HashMap<String, ArrayList<String>> activePet = data.getProfils().get(user.getId()).getPet();
            try {
                list = data.getProfils().get(user.getId()).getPet().get(pet);
            }
            catch (NullPointerException e) {
                list = null;
            }
            try {
                Pet_EXP = Integer.parseInt(data.getProfils().get(user.getId()).getPet().get(pet).get(1));
            }
            catch (NullPointerException e) {
                Pet_EXP = 0;
            }
            if (!pet.equals("0")) {
                Pet_EXP += EXP_win / 10;
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
            if (list != null) {
                Pet_EXP += EXP_win / 10;
            }
            if (list != null) {
                list.add(1, String.valueOf(Pet_EXP));
            }
            if (list != null) {
                activePet.put(pet, list);
            }
            String Pet_Bonus = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pets/" + pet);
            double operation = Pet_EXP / 10;
            double operation2 = Math.sqrt(operation);
            double Pet_Level = Math.round(operation2);
            double pet_bonus = 1.0 + 0.1 * Pet_Level;
            if (Pet_Bonus.equals("resHr")) {
                money_win = (int)((double)money_win * pet_bonus);
                EXP_win = (int)((double)EXP_win * pet_bonus);
                pop_win = (int)((double)pop_win * pet_bonus);
            }
            try {
                data.getProfils().get(user.getId()).setPet(activePet);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setPet(activePet);
            }
            pop += (long)pop_win;
            money += (long)money_win;
            Game_EXP += EXP_win;
            Quest.Quest("exp", user, channel, EXP_win);
            String Halloween1 = "";
            if (Event.Summer()) {
                int nbalea = 500 + (int)(Math.random() * 501.0);
                int gain = 0;
                int xp = data.getProfils().get(user.getId()).getOzPassXp();
                double bonus1 = (double)data.getProfils().get(user.getId()).getBonus() / 100.0;
                gain = (int)((double)nbalea * (1.0 + bonus1));
                data.getProfils().get(user.getId()).setOzPassXp(xp += gain);
                if (lang == command.Language.fr) {
                    Halloween1 = " Vous avez aussi gagné " + gain + " OzXp";
                }
                if (lang == command.Language.en) {
                    Halloween1 = "You also won " + gain + " OzXp";
                }
            }
            Quest.Quest("jetons", user, channel, 5);
            try {
                data.getProfils().get(user.getId()).setXp(Game_EXP);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setXp(Game_EXP);
            }
            try {
                data.getProfils().get(user.getId()).setMoney(money);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setMoney(money);
            }
            try {
                data.getProfils().get(user.getId()).setHabitants(pop);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setHabitants(pop);
            }
            int daily = data.getProfils().get(user.getId()).getDaily_récolté();
            data.getProfils().get(user.getId()).setDaily_récolté(++daily);
            int jetons = data.getProfils().get(user.getId()).getJetons_récolté();
            data.getProfils().get(user.getId()).setJetons_récolté(jetons += 5);
            if (lang == command.Language.fr) {
                channel.sendMessage("\u23f0 Votre daily vous a rapporté **" + pop_win + "** habitants, **" + money_win + "** money, **5** jetons et **" + EXP_win + "** EXP." + Halloween1).queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("\u23f0 Thank to your daily you won **" + pop_win + "** people, **" + money_win + "** $, **5** tokens and **" + EXP_win + "** Xp." + Halloween1).queue();
            }
            if (tuto == 1) {
                data.getProfils().get(user.getId()).setTuto(2);
                if (lang == command.Language.fr) {
                    channel.sendMessage("Maintenant, obtenez votre salaire horaire avec la commande =hourly.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Now, get your hourly reward with the =hourly command.").queue();
                }
            }
        } else {
            int mHour = (int)(delay / 3600000L);
            int mMinute = (int)((delay %= 3600000L) / 60000L);
            if (lang == command.Language.fr) {
                channel.sendMessage("\u23f0 Votre daily sera récupérable dans **" + (23 - mHour) + "** heures **" + (59 - mMinute) + "** minutes.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("\u23f0 You daily will be recoverable in **" + (23 - mHour) + "** hours and **" + (59 - mMinute) + "** minutes.").queue();
            }
        }
    }
}

