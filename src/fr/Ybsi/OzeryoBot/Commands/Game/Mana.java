/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.Game.Pays;
import fr.Ybsi.OzeryoBot.Commands.Game.Structure;
import fr.Ybsi.OzeryoBot.Commands.Game.habitants;
import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.Utils.Event;
import fr.Ybsi.OzeryoBot.Utils.Premium;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import fr.Ybsi.OzeryoBot.Utils.Quest;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import fr.Ybsi.OzeryoBot.Utils.color;
import java.awt.Color;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class Mana {
    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @command(name="work", abbrev="w", type=command.ExecutorType.ALL, descfr="usage : [BETA] creer une ville et developpe la au fil de temps", topic=command.Topics.Game)
    private void work(MessageChannel channel, User user, Guild guild, String[] args, JDA jda, ProfilData data, command.Language lang) {
            StringBuilder buder = new StringBuilder();
            for (String str : args) {
                if (!str.equals(args[0])) break;
                if (buder.length() > 0) {
                    buder.append(" ");
                }
                buder.append(str);
            }
            HashMap<String, Integer> building = data.getProfils().get(user.getId()).getBuilding();
            Integer struct = building.get("march\u00e9");
            double bonus = Structure.entreprise(struct);
            String ActivePet = TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + user.getId() + "/pet.txt");
            HashMap<String, ArrayList<String>> pet = data.getProfils().get(user.getId()).getPet();
            ArrayList<String> list;
			try {
                list = data.getProfils().get(user.getId()).getPet().get(ActivePet);
            }
            catch (NullPointerException e) {
                list = null;
            }
            int Pet_EXP;
			try {
                Pet_EXP = Integer.parseInt(data.getProfils().get(user.getId()).getPet().get(ActivePet).get(1));
            }
            catch (NullPointerException e) {
                Pet_EXP = 0;
            }
            String Pet_Bonus = TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/bot/Pets/" + ActivePet);
            int operation = Pet_EXP / 10;
            double operation2 = Math.sqrt(operation);
            long Pet_Level = Math.round(operation2);
            double pet_bonus = 1.0 + 0.03 * Pet_Level;
            Integer levelEcole = building.get("biblioth\u00e8que");
            String eglise = TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + user.getId() + "/eglise.txt");
            int Game_EXP = data.getProfils().get(user.getId()).getXp();
            int level;
			try {
                int operation3 = 3 * Game_EXP / 4;
                double math = Math.sqrt(operation3);
                level = (int)Math.round(math);
            }
            catch (NullPointerException e) {
                level = 0;
            }
            int mana = 10;
            mana = data.getProfils().get(user.getId()).getMana();
            long lastHourly = data.getProfils().get(user.getId()).getLastMana();
            long delay = System.currentTimeMillis() - lastHourly;
            delay /= 1000L;
            int regen = Pet_Bonus.equals("regen") != false ? 300 - (int)(Pet_Level * 3.0) : 300;
            if (eglise.equals("Hermes")) {
                regen -= 60;
            }
            if (regen < 150) {
                regen = 150;
            }
            if (delay > (long)regen) {
                while (delay > (long)regen) {
                    ++mana;
                    delay -= (long)regen;
                }
                try {
                    data.getProfils().get(user.getId()).setLastMana(System.currentTimeMillis() - delay);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setLastMana(System.currentTimeMillis());
                }
            }
            int Mana_Max = 10 + level;
            if (level > 190) {
                Mana_Max = 200;
            }
            if (Pet_Bonus.equals("mana")) {
                Mana_Max += (int)(Pet_Level * 3.0);
            }
            if (eglise.equals("Athena")) {
                Mana_Max = (int)((double)Mana_Max * 1.25);
            }
            if (mana > Mana_Max) {
                mana = Mana_Max;
            }
            if (mana < 0) {
                mana = Mana_Max;
            }
            try {
                data.getProfils().get(user.getId()).setMana(mana);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setMana(mana);
            }
            Integer cc2 = building.get("habitations");
            long habitant = habitants.pop(user);
            double cc;
            switch (cc2) {
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
            }
            cc = 15 + (cc2 - 25);

            cc = 500.0 + Math.pow(cc2 + 1, 2.0) * (double)(40 * level);
            int popmax = (int)Math.round(cc);
            if (eglise.equals("Hades")) {
                popmax = (int)((double)popmax * 1.5);
            }
            try {
                HashMap<String, ArrayList<String>> houses = data.getProfils().get(user.getId()).getHouses();
                for (ArrayList<String> maison : houses.values()) {
                    int bonus1 = Integer.parseInt(maison.get(1));
                    double bonus2 = bonus * 20000.0;
                    popmax = (int)((double)popmax + bonus2);
                }
            }
            catch (Exception houses) {
                // empty catch block
            }
            if (habitant > (long)popmax) {
                Integer hopital = building.get("auberge");
                double perte;
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
                }
                
                long soldier = data.getProfils().get(user.getId()).getSoldiers();
                long lastHourly1 = data.getProfils().get(user.getId()).getLastPerte();
                long delay1 = System.currentTimeMillis() - lastHourly1;
                delay1 /= 3600000L;
                int a;
				if (habitant > (long)popmax) {
                    habitant -= (long)popmax;
                    a = 0;
                    while ((long)a < delay1) {
                        habitant = (int)((double)habitant * perte);
                        ++a;
                    }
                    habitant += (long)popmax;
                }
                if (habitant < (long)popmax) {
                    habitant = popmax;
                }
                if ((double)soldier > 0.25 * (double)habitant) {
                    int i = 0;
                    while ((long)i < delay1) {
                        for (int b = 0; b < 24; ++b) {
                            soldier = (int)((double)soldier * perte);
                        }
                        ++i;
                    }
                    a = 0;
                    while ((long)a < delay1) {
                        soldier = (int)((double)soldier * perte);
                        ++a;
                    }
                }
                try {
                    data.getProfils().get(user.getId()).setHabitants(habitant);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setHabitants(habitant);
                }
                try {
                    data.getProfils().get(user.getId()).setSoldiers(soldier);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setSoldiers(soldier);
                }
                try {
                    data.getProfils().get(user.getId()).setLastPerte(System.currentTimeMillis());
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setLastPerte(System.currentTimeMillis());
                }
            }
            boolean premium = Premium.Premium(user);
            HashMap<String, Integer> res = new HashMap<String, Integer>();
            try {
                res = data.getProfils().get(user.getId()).getRes();
            }
            catch (NullPointerException e) {
                res = new HashMap<String, Integer>();
                res.put("bois", 0);
                res.put("argile", 0);
                res.put("cuir", 0);
                res.put("pierre", 0);
                res.put("paille", 0);
                res.put("fer", 0);
                data.getProfils().get(user.getId()).setRes(res);
            }
            Integer A_bois = (Integer)res.get("bois");
            Integer A_argile = (Integer)res.get("argile");
            Integer A_cuir = (Integer)res.get("cuir");
            Integer A_pierre = (Integer)res.get("pierre");
            Integer A_paille = (Integer)res.get("paille");
            Integer A_fer = (Integer)res.get("fer");
           
            String c1 = buder.toString();
            String pays = data.getProfils().get(user.getId()).getCountry();
            int point = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/bot/Pays/" + pays + "/points.txt"));
            int level2 = point / 1000;
            double level3 = Math.sqrt(level2);
            long Plevel = Math.round(level3);
            Integer mineLevel = building.get("mine");
            int min = 1;
            if (mineLevel < 10) {
                min = 1;
            } else if (mineLevel > 10 && mineLevel < 20) {
                min = 5;
            } else if (mineLevel >= 20) {
                min = 10;
            }
            int max = mineLevel + 1;
            int Max_mana = 10 + level;
            if (mana < 0) {
                mana = Max_mana;
            }
            if (!c1.equals("all")) break block293;
            if (Pet_Bonus.equals("mana")) {
                Max_mana += (int)(Pet_Level * 3.0);
            }
            if (eglise.equals("Athena")) {
                Max_mana = (int)((double)Max_mana * 1.25);
            }
            if (mana > Max_mana) {
                mana = Max_mana;
            }
            int Used_mana = 0;
            int EXP_win = 0;
            double money_win = 0.0;
            double pop_win = 0.0;
            double bois = 0.0;
            double argile = 0.0;
            double cuir = 0.0;
            double pierre = 0.0;
            double paille = 0.0;
            double fer = 0.0;
            if (mana == 0) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous n'avez plus de mana RIP.").queue();
                }
                if (lang != command.Language.en) return;
                channel.sendMessage("You don't have mana.").queue();
                return;
            }

        int alea1;
		int gift;
		double money_win2;
		double pop_win2;
		int materiau_alea;
		int nombre_alea;
		int bois_Total;
		int argile_Total;
		int cuir_Total;
		int pierre_Total;
		int fer_Total;
		int paille_Total;
		long money;
		long pop;
		int petGain;
		int gain;
		int xp;
		double bonus1;
		double op;
		if (c1.equals("")) {
            if (Pet_Bonus.equals("mana")) {
                Max_mana += (int)(Pet_Level * 3.0);
            }
            if (eglise.equals("Athena")) {
                Max_mana = (int)((double)Max_mana * 1.25);
            }
            if (mana == 0) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous n'avez plus de mana RIP.").queue();
                }
                if (lang != command.Language.en) return;
                channel.sendMessage("You don't have mana.").queue();
                return;
            }
            if (mana > Max_mana) {
                mana = Max_mana;
            }
            Used_mana = 1;
            EXP_win = 0;
            money_win = 0.0;
            pop_win = 0.0;
            bois = 0.0;
            argile = 0.0;
            cuir = 0.0;
            pierre = 0.0;
            paille = 0.0;
            fer = 0.0;
            alea1 = (int)(2.0 + Math.random() * 4.0);
            EXP_win += alea1;
            gift += 2;
            money_win2 = 100.0 + (double)(10 * level) * bonus;
            money_win = (double)Math.round(money_win2) + money_win;
            pop_win2 = 10.0 + 2.5 * (double)(10 * (levelEcole + 1) * level);
            pop_win = (double)Math.round(pop_win2) + pop_win;
            nombre_alea = (int)((double)min + Math.random() * (double)(max - min + 1));
            materiau_alea = (int)(1.0 + Math.random() * 6.0);
            if (materiau_alea == 1) {
                bois += (double)nombre_alea;
            }
            if (materiau_alea == 2) {
                argile += (double)nombre_alea;
            }
            if (materiau_alea == 3) {
                cuir += (double)nombre_alea;
            }
            if (materiau_alea == 4) {
                pierre += (double)nombre_alea;
            }
            if (materiau_alea == 5) {
                paille += (double)nombre_alea;
            }
            if (materiau_alea == 6) {
                fer += (double)nombre_alea;
            }
            if (Pet_Bonus.equals("bois")) {
                bois = (int)(bois * (1.0 + 0.1 * Pet_Level));
            }
            if (eglise.equals("Poseidon")) {
                bois = (int)(bois * 1.5);
            }
            if (Pet_Bonus.equals("argile")) {
                argile = (int)(argile * (1.0 + 0.1 * Pet_Level));
            }
            if (eglise.equals("Poseidon")) {
                argile = (int)(argile * 1.5);
            }
            if (Pet_Bonus.equals("cuir")) {
                cuir = (int)(cuir * (1.0 + 0.1 * Pet_Level));
            }
            if (eglise.equals("Poseidon")) {
                cuir = (int)(cuir * 1.5);
            }
            if (Pet_Bonus.equals("pierre")) {
                pierre = (int)(pierre * (1.0 + 0.1 * Pet_Level));
            }
            if (eglise.equals("Poseidon")) {
                pierre = (int)(pierre * 1.5);
            }
            if (Pet_Bonus.equals("paille")) {
                paille = (int)(paille * (1.0 + 0.1 * Pet_Level));
            }
            if (eglise.equals("Poseidon")) {
                paille = (int)(paille * 1.5);
            }
            if (Pet_Bonus.equals("fer")) {
                fer = (int)(fer * (1.0 + 0.1 * Pet_Level));
            }
            if (eglise.equals("Poseidon")) {
                fer = (int)(fer * 1.5);
            }
            if (Pet_Bonus.equals("res")) {
                bois = (int)(bois * (1.0 + 0.05 * Pet_Level));
                argile = (int)(argile * (1.0 + 0.05 * Pet_Level));
                cuir = (int)(cuir * (1.0 + 0.05 * Pet_Level));
                pierre = (int)(pierre * (1.0 + 0.05 * Pet_Level));
                paille = (int)(paille * (1.0 + 0.05 * Pet_Level));
                fer = (int)(fer * (1.0 + 0.05 * Pet_Level));
            }
            if (Pays.Bonus(1, pays)) {
                bois = (int)(bois * 1.5);
            }
            bois_Total = (int)(bois + (double)A_bois);
            argile_Total = (int)(argile + (double)A_argile);
            cuir_Total = (int)(cuir + (double)A_cuir);
            pierre_Total = (int)(pierre + (double)A_pierre);
            paille_Total = (int)(paille + (double)A_paille);
            fer_Total = (int)(fer + (double)A_fer);
            System.out.println(pet_bonus);
            System.out.println(money_win);
            if (Pet_Bonus.equals("pop")) {
                pop_win *= pet_bonus;
            }
            if (eglise.equals("Hera")) {
                pop_win = (int)(pop_win * 1.5);
            }
            if (Pet_Bonus.equals("money")) {
                money_win *= pet_bonus;
            }
            if (eglise.equals("Zeus")) {
                money_win = (int)(money_win * 1.5);
            }
            System.out.println(money_win);
            if (Pays.Bonus(2, pays)) {
                money_win = (int)(money_win * 1.25);
            }
            res.put("bois", bois_Total);
            res.put("argile", argile_Total);
            res.put("cuir", cuir_Total);
            res.put("pierre", pierre_Total);
            res.put("paille", paille_Total);
            res.put("fer", fer_Total);
            try {
                data.getProfils().get(user.getId()).setRes(res);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setRes(res);
            }
            pop = data.getProfils().get(user.getId()).getHabitants();
            money = data.getProfils().get(user.getId()).getMoney();
            mana -= Used_mana;
            pop = (long)(pop+ pop_win);
            money = (long)(money + money_win);
            Game_EXP += EXP_win;
            petGain = EXP_win / 10;
            if (list != null) {
                Pet_EXP += EXP_win / 10;
            } else {
                petGain = 0;
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
            gain = 0;
            if (Event.Summer()) {
                xp = data.getProfils().get(user.getId()).getOzPassXp();
                bonus1 = (double)data.getProfils().get(user.getId()).getBonus() / 100.0;
                gain = (int)((double)Used_mana * (1.0 + bonus1));
                op = (100.0 - Pet_Level) / 100.0;
                if (Pet_Bonus.equals("regen")) {
                    gain = (int)((double)gain * op);
                }
                data.getProfils().get(user.getId()).setOzPassXp(xp += gain);
            }
            try {
                data.getProfils().get(user.getId()).setPet(pet);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setPet(pet);
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
            try {
                data.getProfils().get(user.getId()).setXp(Game_EXP);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setXp(Game_EXP);
            }
            try {
                data.getProfils().get(user.getId()).setMana(mana);
            }
            catch (NullPointerException e) {
                data.getProfils().put(user.getId(), new Profil(user.getId()));
                data.getProfils().get(user.getId()).setMana(mana);
            }
            Quest.Quest("exp", user, channel, EXP_win);
            Quest.Quest("mana", user, channel, Used_mana);
            Quest.Quest("materiau", user, channel, (int)(bois + argile + cuir + pierre + paille + fer));
            if (bois != 0.0) {
                Quest.Quest("bois", user, channel, (int)bois);
            }
            if (argile != 0.0) {
                Quest.Quest("argile", user, channel, (int)argile);
            }
            if (cuir != 0.0) {
                Quest.Quest("cuir", user, channel, (int)cuir);
            }
            if (pierre != 0.0) {
                Quest.Quest("pierre", user, channel, (int)pierre);
            }
            if (paille != 0.0) {
                Quest.Quest("paille", user, channel, (int)paille);
            }
            if (fer != 0.0) {
                Quest.Quest("fer", user, channel, (int)fer);
            }
            money2 = data.getProfils().get(user.getId()).getMoney_r\u00e9colt\u00e9();
            money2 = (long)((double)money2 + money_win);
            data.getProfils().get(user.getId()).setMoney_r\u00e9colt\u00e9(money2);
            premium2 = premium != false ? "[Premium]" : "";
            builder = new EmbedBuilder();
            builder.setAuthor(user.getName(), null, user.getAvatarUrl());
            builder.setFooter(guild.getName(), guild.getIconUrl());
            builder.setDescription("Votre travail vous a apport\u00e9 :");
            builder.setColor(color.couleurAleatoire(user));
            builder.addField(":star: | **Xp** ", String.valueOf(EXP_win), true);
            if (lang == command.Language.fr) {
                builder.addField("\ud83d\udc65 | **Habitants** ", "+" + (int)pop_win, true);
            }
            if (lang == command.Language.en) {
                builder.addField("\ud83d\udc65 | **People** ", "+" + (int)pop_win, true);
            }
            builder.addField(String.valueOf(jda.getGuildById("326345972739473410").getEmotesByName("gold", true).get(0).getAsMention()) + " | **Money** ", "+" + (int)money_win, true);
            builder.addBlankField(true);
            if (lang == command.Language.fr) {
                builder.addField("\ud83d\udc8e | **Mat\u00e9riaux obtenus** :", "\n" + jda.getGuildById("326345972739473410").getEmotesByName("wood", true).get(0).getAsMention() + " **Bois :** " + "+" + (int)bois + "\n " + jda.getGuildById("326345972739473410").getEmotesByName("clay", true).get(0).getAsMention() + " **argile :** " + "+" + (int)argile + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("leather", true).get(0).getAsMention() + " **cuir :** " + "+" + (int)cuir + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("stone", true).get(0).getAsMention() + " **pierre :** " + "+" + (int)pierre + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("straw", true).get(0).getAsMention() + " **paille :** " + "+" + (int)paille + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("iron", true).get(0).getAsMention() + " **fer :** " + "+" + (int)fer, false);
            }
            if (lang == command.Language.en) {
                builder.addField("\ud83d\udc8e | **Mat\u00e9riaux obtenus** :", "\n" + jda.getGuildById("326345972739473410").getEmotesByName("wood", true).get(0).getAsMention() + " **wood :** " + "+" + (int)bois + "\n " + jda.getGuildById("326345972739473410").getEmotesByName("clay", true).get(0).getAsMention() + " **clay :** " + "+" + (int)argile + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("leather", true).get(0).getAsMention() + " **leather :** " + "+" + (int)cuir + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("stone", true).get(0).getAsMention() + " **stone :** " + "+" + (int)pierre + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("straw", true).get(0).getAsMention() + " **straw :** " + "+" + (int)paille + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("iron", true).get(0).getAsMention() + " **iron :** " + "+" + (int)fer, false);
            }
            builder.addBlankField(false);
            if (lang == command.Language.fr) {
                builder.addField(":heart:  **Mana D\u00e9pens\u00e9** ", String.valueOf(Used_mana) + " " + premium2, true);
            }
            if (lang == command.Language.en) {
                builder.addField(":heart:  **Mana Spent** ", String.valueOf(Used_mana) + " " + premium2, true);
            }
            builder.addField(":sparkles: **Pet EXP**", "+" + petGain, true);
            if (Event.Summer()) {
                builder.addField("OzPass Xp", String.valueOf(gain) + " :beach: ", true);
            }
            channel.sendMessage(builder.build()).queue();
            return;
        }
        nombre = 1;
        try {
            nombre = Integer.parseInt(c1);
        }
        catch (NumberFormatException e) {
            nombre = 1;
        }
        if (Pet_Bonus.equals("mana")) {
            Max_mana += (int)(Pet_Level * 3.0);
        }
        if (eglise.equals("Athena")) {
            Max_mana = (int)((double)Max_mana * 1.25);
        }
        if (mana > Max_mana) {
            mana = Max_mana;
        }
        if (mana < nombre) {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous n'avez plus de mana RIP.").queue();
            }
            if (lang != command.Language.en) return;
            channel.sendMessage("You don't have mana.").queue();
            return;
        }
        Used_mana = 0;
        EXP_win = 0;
        money_win = 0;
        pop_win = 0;
        bois = 0;
        argile = 0;
        cuir = 0;
        pierre = 0;
        paille = 0;
        fer = 0;
        i = 0;
        i = 0;


         while (mana > 0) {
        
            --mana;
        	
            ++Used_mana;
            alea1 = (int)(2.0 + Math.random() * 4.0);
            EXP_win += alea1;
            gift += 2;
            money_win2 = 100.0 + 10.0 * (bonus + 1.0) * (double)level;
            money_win = (double)((int)Math.round(money_win2)) + money_win;
            pop_win2 = 10.0 + 2.5 * (double)(10 * (levelEcole + 1) * level);
            pop_win = (double)((int)Math.round(pop_win2)) + pop_win;
            nombre_alea = (int)((double)min + Math.random() * (double)(max - min + 1));
            materiau_alea = (int)(1.0 + Math.random() * 6.0);
            if (materiau_alea == 1) {
                bois += (double)nombre_alea;
            }
            if (materiau_alea == 2) {
                argile += (double)nombre_alea;
            }
            if (materiau_alea == 3) {
                cuir += (double)nombre_alea;
            }
            if (materiau_alea == 4) {
                pierre += (double)nombre_alea;
            }
            if (materiau_alea == 5) {
                paille += (double)nombre_alea;
            }
            if (materiau_alea == 6) {
                fer += (double)nombre_alea;
            }

        if (Pet_Bonus.equals("bois")) {
            bois = (int)(bois * (1.0 + 0.1 * Pet_Level));
        }
        if (eglise.equals("Poseidon")) {
            bois = (int)(bois * 1.5);
        }
        if (Pet_Bonus.equals("argile")) {
            argile = (int)(argile * (1.0 + 0.1 * Pet_Level));
        }
        if (eglise.equals("Poseidon")) {
            argile = (int)(argile * 1.5);
        }
        if (Pet_Bonus.equals("cuir")) {
            cuir = (int)(cuir * (1.0 + 0.1 * Pet_Level));
        }
        if (eglise.equals("Poseidon")) {
            cuir = (int)(cuir * 1.5);
        }
        if (Pet_Bonus.equals("pierre")) {
            pierre = (int)(pierre * (1.0 + 0.1 * Pet_Level));
        }
        if (eglise.equals("Poseidon")) {
            pierre = (int)(pierre * 1.5);
        }
        if (Pet_Bonus.equals("paille")) {
            paille = (int)(paille * (1.0 + 0.1 * Pet_Level));
        }
        if (eglise.equals("Poseidon")) {
            paille = (int)(paille * 1.5);
        }
        if (Pet_Bonus.equals("fer")) {
            fer = (int)(fer * (1.0 + 0.1 * Pet_Level));
        }
        if (eglise.equals("Poseidon")) {
            fer = (int)(fer * 1.5);
        }
        if (Pet_Bonus.equals("res")) {
            bois = (int)(bois * (1.0 + 0.05 * Pet_Level));
            argile = (int)(argile * (1.0 + 0.05 * Pet_Level));
            cuir = (int)(cuir * (1.0 + 0.05 * Pet_Level));
            pierre = (int)(pierre * (1.0 + 0.05 * Pet_Level));
            paille = (int)(paille * (1.0 + 0.05 * Pet_Level));
            fer = (int)(fer * (1.0 + 0.05 * Pet_Level));
        }
        if (Pays.Bonus(1, pays)) {
            bois = (int)(bois * 1.5);
        }
        bois_Total = (int)(bois + (double)A_bois);
        argile_Total = (int)(argile + (double)A_argile);
        cuir_Total = (int)(cuir + (double)A_cuir);
        pierre_Total = (int)(pierre + (double)A_pierre);
        paille_Total = (int)(paille + (double)A_paille);
        fer_Total = (int)(fer + (double)A_fer);
        res.put("bois", bois_Total);
        res.put("argile", argile_Total);
        res.put("cuir", cuir_Total);
        res.put("pierre", pierre_Total);
        res.put("paille", paille_Total);
        res.put("fer", fer_Total);
        try {
            data.getProfils().get(user.getId()).setRes(res);
        }
        catch (NullPointerException e) {
            data.getProfils().put(user.getId(), new Profil(user.getId()));
            data.getProfils().get(user.getId()).setRes(res);
        }
        System.out.println(pet_bonus);
        System.out.println(money_win);
        if (Pet_Bonus.equals("pop")) {
            pop_win *= pet_bonus;
        }
        if (eglise.equals("Hera")) {
            pop_win = (int)(pop_win * 1.5);
        }
        if (Pet_Bonus.equals("money")) {
            money_win *= pet_bonus;
        }
        if (eglise.equals("Zeus")) {
            money_win = (int)(money_win * 1.5);
        }
        System.out.println(money_win);
        if (Pays.Bonus(2, pays)) {
            money_win = (int)(money_win * 1.25);
        }
        gain = 0;
        if (Event.Summer()) {
            xp = data.getProfils().get(user.getId()).getOzPassXp();
            bonus1 = (double)data.getProfils().get(user.getId()).getBonus() / 100.0;
            gain = (int)((double)Used_mana * (1.0 + bonus1));
            op = (100.0 - Pet_Level) / 100.0;
            if (Pet_Bonus.equals("regen")) {
                gain = (int)((double)gain * op);
            }
            data.getProfils().get(user.getId()).setOzPassXp(xp += gain);
        }
        pop = data.getProfils().get(user.getId()).getHabitants();
        money = data.getProfils().get(user.getId()).getMoney();
        pop = (long)((double)pop.longValue() + pop_win);
        money = (long)((double)money.longValue() + money_win);
        Game_EXP += EXP_win;
        petGain = EXP_win / 10;
        if (list != null) {
            Pet_EXP += EXP_win / 10;
        } else {
            petGain = 0;
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
        try {
            data.getProfils().get(user.getId()).setPet(pet);
        }
        catch (NullPointerException e) {
            data.getProfils().put(user.getId(), new Profil(user.getId()));
            data.getProfils().get(user.getId()).setPet(pet);
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
        try {
            data.getProfils().get(user.getId()).setXp(Game_EXP);
        }
        catch (NullPointerException e) {
            data.getProfils().put(user.getId(), new Profil(user.getId()));
            data.getProfils().get(user.getId()).setXp(Game_EXP);
        }
        try {
            data.getProfils().get(user.getId()).setMana(mana);
        }
        catch (NullPointerException e) {
            data.getProfils().put(user.getId(), new Profil(user.getId()));
            data.getProfils().get(user.getId()).setMana(mana);
        }
        Quest.Quest("exp", user, channel, EXP_win);
        Quest.Quest("mana", user, channel, Used_mana);
        Quest.Quest("materiau", user, channel, (int)(bois + argile + cuir + pierre + paille + fer));
        if (bois != 0.0) {
            Quest.Quest("bois", user, channel, (int)bois);
        }
        if (argile != 0.0) {
            Quest.Quest("argile", user, channel, (int)argile);
        }
        if (cuir != 0.0) {
            Quest.Quest("cuir", user, channel, (int)cuir);
        }
        if (pierre != 0.0) {
            Quest.Quest("pierre", user, channel, (int)pierre);
        }
        if (paille != 0.0) {
            Quest.Quest("paille", user, channel, (int)paille);
        }
        if (fer != 0.0) {
            Quest.Quest("fer", user, channel, (int)fer);
        }
        money2 = data.getProfils().get(user.getId()).getMoney_r\u00e9colt\u00e9();
        money2 = (long)((double)money2 + money_win);
        data.getProfils().get(user.getId()).setMoney_r\u00e9colt\u00e9(money2);
        premium2 = premium != false ? "[Premium]" : "";
        builder = new EmbedBuilder();
        builder.setAuthor(user.getName(), null, user.getAvatarUrl());
        builder.setFooter(guild.getName(), guild.getIconUrl());
        builder.setDescription("Votre travail vous a apport\u00e9 :");
        builder.setColor(color.couleurAleatoire(user));
        builder.addField(":star: | **Xp** ", String.valueOf(EXP_win), true);
        if (lang == command.Language.fr) {
            builder.addField("\ud83d\udc65 | **Habitants** ", "+" + (int)pop_win, true);
        }
        if (lang == command.Language.en) {
            builder.addField("\ud83d\udc65 | **People** ", "+" + (int)pop_win, true);
        }
        builder.addField(String.valueOf(jda.getGuildById("326345972739473410").getEmotesByName("gold", true).get(0).getAsMention()) + " | **Money** ", "+" + (int)money_win, true);
        builder.addBlankField(true);
        if (lang == command.Language.fr) {
            builder.addField("\ud83d\udc8e | **Mat\u00e9riaux obtenus** :", "\n" + jda.getGuildById("326345972739473410").getEmotesByName("wood", true).get(0).getAsMention() + " **Bois :** " + "+" + (int)bois + "\n " + jda.getGuildById("326345972739473410").getEmotesByName("clay", true).get(0).getAsMention() + " **argile :** " + "+" + (int)argile + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("leather", true).get(0).getAsMention() + " **cuir :** " + "+" + (int)cuir + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("stone", true).get(0).getAsMention() + " **pierre :** " + "+" + (int)pierre + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("straw", true).get(0).getAsMention() + " **paille :** " + "+" + (int)paille + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("iron", true).get(0).getAsMention() + " **fer :** " + "+" + (int)fer, false);
        }
        if (lang == command.Language.en) {
            builder.addField("\ud83d\udc8e | **Mat\u00e9riaux obtenus** :", "\n" + jda.getGuildById("326345972739473410").getEmotesByName("wood", true).get(0).getAsMention() + " **wood :** " + "+" + (int)bois + "\n " + jda.getGuildById("326345972739473410").getEmotesByName("clay", true).get(0).getAsMention() + " **clay :** " + "+" + (int)argile + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("leather", true).get(0).getAsMention() + " **leather :** " + "+" + (int)cuir + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("stone", true).get(0).getAsMention() + " **stone :** " + "+" + (int)pierre + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("straw", true).get(0).getAsMention() + " **straw :** " + "+" + (int)paille + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("iron", true).get(0).getAsMention() + " **iron :** " + "+" + (int)fer, false);
        }
        builder.addBlankField(false);
        if (lang == command.Language.fr) {
            builder.addField(":heart:  **Mana D\u00e9pens\u00e9** ", String.valueOf(Used_mana) + " " + premium2, true);
        }
        if (lang == command.Language.en) {
            builder.addField(":heart:  **Mana Spent** ", String.valueOf(Used_mana) + " " + premium2, true);
        }
        builder.addField(":sparkles: **Pet EXP**", "+" + petGain, true);
        if (Event.Summer()) {
            builder.addField("OzPass Xp", String.valueOf(gain) + " :beach: ", true);
        }
        channel.sendMessage(builder.build()).queue();
        tuto = data.getProfils().get(user.getId()).getTuto();
        if (tuto != 4) return;
        data.getProfils().get(user.getId()).setTuto(5);
        if (lang == command.Language.fr) {
            channel.sendMessage("\u00c0 pr\u00e9sent, vous pouvez construire votre premier b\u00e2timent ! Commen\u00e7ons par le march\u00e9 comme exemple : =b march\u00e9 1.").queue();
        }
        if (lang != command.Language.en) return;
        channel.sendMessage("Now you can build your first building! Let's start with the market place as an example: = b march\u00e9 1.").queue();
        return;
lbl-1000: // 1 sources:
        {
            ++Used_mana;
            alea1 = (int)(2.0 + Math.random() * 4.0);
            EXP_win += alea1;
            gift += 2;
            money_win2 = 100.0 + (double)(10 * level) * bonus;
            money_win = (int)Math.round(money_win2) + money_win;
            pop_win2 = 10.0 + 2.5 * (double)(10 * (levelEcole + 1) * level);
            pop_win = (int)Math.round(pop_win2) + pop_win;
            nombre_alea = min + (int)(Math.random() * (double)(max - min + 1));
            materiau_alea = (int)(1.0 + Math.random() * 6.0);
            if (materiau_alea == 1) {
                bois += nombre_alea;
            }
            if (materiau_alea == 2) {
                argile += nombre_alea;
            }
            if (materiau_alea == 3) {
                cuir += nombre_alea;
            }
            if (materiau_alea == 4) {
                pierre += nombre_alea;
            }
            if (materiau_alea == 5) {
                paille += nombre_alea;
            }
            if (materiau_alea == 6) {
                fer += nombre_alea;
            }
            ++i;
lbl890: // 2 sources:
            ** while (i < nombre)
        }
lbl891: // 1 sources:
        if (Pet_Bonus.equals("bois")) {
            bois = (int)((double)bois * (1.0 + 0.1 * Pet_Level));
        }
        if (eglise.equals("Poseidon")) {
            bois = (int)((double)bois * 1.5);
        }
        if (Pet_Bonus.equals("argile")) {
            argile = (int)((double)argile * (1.0 + 0.1 * Pet_Level));
        }
        if (eglise.equals("Poseidon")) {
            argile = (int)((double)argile * 1.5);
        }
        if (Pet_Bonus.equals("cuir")) {
            cuir = (int)((double)cuir * (1.0 + 0.1 * Pet_Level));
        }
        if (eglise.equals("Poseidon")) {
            cuir = (int)((double)cuir * 1.5);
        }
        if (Pet_Bonus.equals("pierre")) {
            pierre = (int)((double)pierre * (1.0 + 0.1 * Pet_Level));
        }
        if (eglise.equals("Poseidon")) {
            pierre = (int)((double)pierre * 1.5);
        }
        if (Pet_Bonus.equals("paille")) {
            paille = (int)((double)paille * (1.0 + 0.1 * Pet_Level));
        }
        if (eglise.equals("Poseidon")) {
            paille = (int)((double)paille * 1.5);
        }
        if (Pet_Bonus.equals("fer")) {
            fer = (int)((double)fer * (1.0 + 0.1 * Pet_Level));
        }
        if (eglise.equals("Poseidon")) {
            fer = (int)((double)fer * 1.5);
        }
        if (Pays.Bonus(1, pays)) {
            bois = (int)((double)bois * 1.5);
        }
        if (Pet_Bonus.equals("res")) {
            bois = (int)((double)bois * (1.0 + 0.05 * Pet_Level));
            argile = (int)((double)argile * (1.0 + 0.05 * Pet_Level));
            cuir = (int)((double)cuir * (1.0 + 0.05 * Pet_Level));
            pierre = (int)((double)pierre * (1.0 + 0.05 * Pet_Level));
            paille = (int)((double)paille * (1.0 + 0.05 * Pet_Level));
            fer = (int)((double)fer * (1.0 + 0.05 * Pet_Level));
        }
        bois_Total = bois + A_bois;
        argile_Total = argile + A_argile;
        cuir_Total = cuir + A_cuir;
        pierre_Total = pierre + A_pierre;
        paille_Total = paille + A_paille;
        fer_Total = fer + A_fer;
        if (Pays.Bonus(2, pays)) {
            money_win = (int)((double)money_win * 1.25);
        }
        if (Pet_Bonus.equals("pop")) {
            pop_win = (int)((double)pop_win * pet_bonus);
        }
        if (eglise.equals("Hera")) {
            pop_win = (int)((double)pop_win * 1.5);
        }
        if (Pet_Bonus.equals("money")) {
            money_win = (int)((double)money_win * pet_bonus);
        }
        if (eglise.equals("Zeus")) {
            money_win = (int)((double)money_win * 1.5);
        }
        mana -= Used_mana;
        gain = 0;
        if (Event.Summer()) {
            xp = data.getProfils().get(user.getId()).getOzPassXp();
            bonus1 = (double)data.getProfils().get(user.getId()).getBonus() / 100.0;
            gain = (int)((double)Used_mana * (1.0 + bonus1));
            op = (100.0 - Pet_Level) / 100.0;
            if (Pet_Bonus.equals("regen")) {
                gain = (int)((double)gain * op);
            }
            data.getProfils().get(user.getId()).setOzPassXp(xp += gain);
        }
        res.put("bois", bois_Total);
        res.put("argile", argile_Total);
        res.put("cuir", cuir_Total);
        res.put("pierre", pierre_Total);
        res.put("paille", paille_Total);
        res.put("fer", fer_Total);
        try {
            data.getProfils().get(user.getId()).setRes(res);
        }
        catch (NullPointerException e) {
            data.getProfils().put(user.getId(), new Profil(user.getId()));
            data.getProfils().get(user.getId()).setRes(res);
        }
        pop = data.getProfils().get(user.getId()).getHabitants();
        money = data.getProfils().get(user.getId()).getMoney();
        pop = pop + (long)pop_win;
        money = money + (long)money_win;
        Game_EXP += EXP_win;
        petGain = EXP_win / 10;
        if (list != null) {
            Pet_EXP += EXP_win / 10;
        } else {
            petGain = 0;
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
        try {
            data.getProfils().get(user.getId()).setPet(pet);
        }
        catch (NullPointerException e) {
            data.getProfils().put(user.getId(), new Profil(user.getId()));
            data.getProfils().get(user.getId()).setPet(pet);
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
        try {
            data.getProfils().get(user.getId()).setXp(Game_EXP);
        }
        catch (NullPointerException e) {
            data.getProfils().put(user.getId(), new Profil(user.getId()));
            data.getProfils().get(user.getId()).setXp(Game_EXP);
        }
        try {
            data.getProfils().get(user.getId()).setMana(mana);
        }
        catch (NullPointerException e) {
            data.getProfils().put(user.getId(), new Profil(user.getId()));
            data.getProfils().get(user.getId()).setMana(mana);
        }
        Quest.Quest("exp", user, channel, EXP_win);
        Quest.Quest("mana", user, channel, Used_mana);
        Quest.Quest("materiau", user, channel, bois + argile + cuir + pierre + paille + fer);
        if (bois != 0) {
            Quest.Quest("bois", user, channel, bois);
        }
        if (argile != 0) {
            Quest.Quest("argile", user, channel, argile);
        }
        if (cuir != 0) {
            Quest.Quest("cuir", user, channel, cuir);
        }
        if (pierre != 0) {
            Quest.Quest("pierre", user, channel, pierre);
        }
        if (paille != 0) {
            Quest.Quest("paille", user, channel, paille);
        }
        if (fer != 0) {
            Quest.Quest("fer", user, channel, fer);
        }
        money2 = data.getProfils().get(user.getId()).getMoney_r\u00e9colt\u00e9();
        data.getProfils().get(user.getId()).setMoney_r\u00e9colt\u00e9(money2 += (long)money_win);
        premium2 = premium != false ? "[Premium]" : "";
        builder = new EmbedBuilder();
        builder.setAuthor(user.getName(), null, user.getAvatarUrl());
        builder.setFooter(guild.getName(), guild.getIconUrl());
        builder.setDescription("Votre travail vous a apport\u00e9 :");
        builder.setColor(color.couleurAleatoire(user));
        builder.addField(":star: | **Xp** ", String.valueOf(EXP_win), true);
        if (lang == command.Language.fr) {
            builder.addField("\ud83d\udc65 | **Habitants** ", "+" + pop_win, true);
        }
        if (lang == command.Language.en) {
            builder.addField("\ud83d\udc65 | **People** ", "+" + pop_win, true);
        }
        builder.addField(String.valueOf(jda.getGuildById("326345972739473410").getEmotesByName("gold", true).get(0).getAsMention()) + " | **Money** ", "+" + money_win, true);
        builder.addBlankField(true);
        if (lang == command.Language.fr) {
            builder.addField("\ud83d\udc8e | **Mat\u00e9riaux obtenus** :", "\n" + jda.getGuildById("326345972739473410").getEmotesByName("wood", true).get(0).getAsMention() + " **Bois :** " + "+" + bois + "\n " + jda.getGuildById("326345972739473410").getEmotesByName("clay", true).get(0).getAsMention() + " **argile :** " + "+" + argile + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("leather", true).get(0).getAsMention() + " **cuir :** " + "+" + cuir + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("stone", true).get(0).getAsMention() + " **pierre :** " + "+" + pierre + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("straw", true).get(0).getAsMention() + " **paille :** " + "+" + paille + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("iron", true).get(0).getAsMention() + " **fer :** " + "+" + fer, false);
        }
        if (lang == command.Language.en) {
            builder.addField("\ud83d\udc8e | **Mat\u00e9riaux obtenus** :", "\n" + jda.getGuildById("326345972739473410").getEmotesByName("wood", true).get(0).getAsMention() + " **wood :** " + "+" + bois + "\n " + jda.getGuildById("326345972739473410").getEmotesByName("clay", true).get(0).getAsMention() + " **clay :** " + "+" + argile + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("leather", true).get(0).getAsMention() + " **leather :** " + "+" + cuir + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("stone", true).get(0).getAsMention() + " **stone :** " + "+" + pierre + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("straw", true).get(0).getAsMention() + " **straw :** " + "+" + paille + "\n" + jda.getGuildById("326345972739473410").getEmotesByName("iron", true).get(0).getAsMention() + " **iron :** " + "+" + fer, false);
        }
        builder.addBlankField(false);
        if (lang == command.Language.fr) {
            builder.addField(":heart:  **Mana D\u00e9pens\u00e9** ", String.valueOf(Used_mana) + " " + premium2, true);
        }
        if (lang == command.Language.en) {
            builder.addField(":heart:  **Mana Spent** ", String.valueOf(Used_mana) + " " + premium2, true);
        }
        builder.addField(":sparkles: **Pet EXP**", "+" + petGain, true);
        if (Event.Summer()) {
            builder.addField("OzPass Xp", String.valueOf(gain) + " :beach: ", true);
        }
        channel.sendMessage(builder.build()).queue();
    }
}
}

