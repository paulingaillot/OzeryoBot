/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import fr.Ybsi.OzeryoBot.Utils.Utils;
import java.io.PrintStream;
import java.util.HashMap;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class Build {
    @command(name="build", abbrev="b", type=command.ExecutorType.ALL, topic=command.Topics.Game)
    private void build(MessageChannel message, Guild guild, String[] args, User user, MessageChannel channel, ProfilData data, command.Language lang) {
        int Glevel;
        StringBuilder builder = new StringBuilder();
        for (String str : args) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(str);
        }
        String c3 = "";
        try {
            c3 = builder.toString();
        }
        catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            // empty catch block
        }
        System.out.println(c3);
        String c1 = "";
        try {
            c1 = args[0];
        }
        catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            // empty catch block
        }
        int c2 = 0;
        try {
            c2 = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException numberFormatException) {
        }
        catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            // empty catch block
        }
        HashMap<String, Integer> building = data.getProfils().get(user.getId()).getBuilding();
        HashMap<String, Integer> res = data.getProfils().get(user.getId()).getRes();
        int bois = res.get("bois");
        int argile = res.get("argile");
        int cuir = res.get("cuir");
        int pierre = res.get("pierre");
        int paille = res.get("paille");
        int iron = res.get("fer");
        int cristal = res.get("cristal");
        int Game_EXP = data.getProfils().get(user.getId()).getXp();
        try {
            double operation = 3 * Game_EXP / 4;
            double math = Math.sqrt(operation);
            Glevel = (int)Math.round(math);
        }
        catch (NullPointerException e) {
            Glevel = 0;
        }
        if (c1.equals("march\u00e9") || c1.equals("mar")) {
            int level = building.get("march\u00e9");
            long money = data.getProfils().get(user.getId()).getMoney();
            int level_up = level + 1;
            long need_money = 5000L;
            int need_cuir = 0;
            int need_paille = 0;
            switch (level_up) {
                case 1: {
                    need_money = 5000L;
                    break;
                }
                case 2: {
                    need_money = 7000L;
                    break;
                }
                case 3: {
                    need_money = 10000L;
                    break;
                }
                case 4: {
                    need_money = 14000L;
                    break;
                }
                case 5: {
                    need_money = 19000L;
                    break;
                }
                case 6: {
                    need_money = 25000L;
                    break;
                }
                case 7: {
                    need_money = 35000L;
                    break;
                }
                case 8: {
                    need_money = 50000L;
                    break;
                }
                case 9: {
                    need_money = 70000L;
                    break;
                }
                case 10: {
                    need_money = 100000L;
                    break;
                }
                case 11: {
                    need_money = 135000L;
                    break;
                }
                case 12: {
                    need_money = 185000L;
                    break;
                }
                case 13: {
                    need_money = 250000L;
                    break;
                }
                case 14: {
                    need_money = 350000L;
                    break;
                }
                case 15: {
                    need_money = 500000L;
                    break;
                }
                case 16: {
                    need_money = 675000L;
                    break;
                }
                case 17: {
                    need_money = 950000L;
                    break;
                }
                case 18: {
                    need_money = 1300000L;
                    break;
                }
                case 19: {
                    need_money = 1800000L;
                    break;
                }
                case 20: {
                    need_money = 2500000L;
                    break;
                }
                case 21: {
                    need_money = 3750000L;
                    need_paille = 200;
                    need_cuir = 200;
                    break;
                }
                case 22: {
                    need_money = 5600000L;
                    need_paille = 300;
                    need_cuir = 300;
                    break;
                }
                case 23: {
                    need_money = 8500000L;
                    need_paille = 450;
                    need_cuir = 450;
                    break;
                }
                case 24: {
                    need_money = 12500000L;
                    need_paille = 675;
                    need_cuir = 675;
                    break;
                }
                case 25: {
                    need_money = 20000000L;
                    need_paille = 1000;
                    need_cuir = 1000;
                    break;
                }
                default: {
                    need_money = 20000000L;
                    need_paille = 1000;
                    need_cuir = 1000;
                    for (int i = 25; i < level_up; ++i) {
                        need_money = (long)((double)need_money * 1.5);
                        need_paille = (int)((double)need_paille * 1.25);
                        need_cuir = (int)((double)need_cuir * 1.25);
                    }
                }
            }
            if (c2 == level + 1) {
                if (level_up > 20 && level_up <= 25 && Glevel < 200) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre level 200 pour pouvoir ameliorer le march\u00e9.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 200 to upgrade the market").queue();
                    }
                    return;
                }
                if (Glevel < 200 + (level_up - 25) * 50) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau " + (200 + (level_up - 25) * 50) + " pour ameliorer le march\u00e9 .").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level " + (200 + (level_up - 25) * 50) + " to upgrade the market .").queue();
                    }
                    return;
                }
                int tuto = data.getProfils().get(user.getId()).getTuto();
                if (tuto == 5) {
                    need_cuir = 0;
                    need_money = 0L;
                    need_paille = 0;
                }
                if (money >= need_money && paille >= need_paille && cuir >= need_cuir) {
                    money -= need_money;
                    cuir -= need_cuir;
                    paille -= need_paille;
                    if (lang == command.Language.fr) {
                        message.sendMessage(":up: Votre *march\u00e9* vient d'\u00eatre am\u00e9lior\u00e9e au niveau **" + level_up + "**").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage(":up: Your *market* is now level **" + level_up + "**").queue();
                    }
                    building.put("march\u00e9", level_up);
                    try {
                        data.getProfils().get(user.getId()).setBuilding(building);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setBuilding(building);
                    }
                    try {
                        data.getProfils().get(user.getId()).setMoney(money);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setMoney(money);
                    }
                    res.put("cuir", cuir);
                    res.put("paille", paille);
                    try {
                        data.getProfils().get(user.getId()).setRes(res);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setRes(res);
                    }
                    if (tuto == 5) {
                        data.getProfils().get(user.getId()).setTuto(6);
                        if (lang == command.Language.fr) {
                            channel.sendMessage("Bravo, vous avez obtenu votre premi\u00e8re construction, celle-ci permet d'augmenter vos gains de money lors de vos commandes. Vous pouvez reconsulter votre ville maintenant =city.").queue();
                        }
                        if (lang == command.Language.en) {
                            channel.sendMessage("Congratulations, you got your first build, it increases your money gains during your commands. You can consult again your city now =city.").queue();
                        }
                    }
                } else {
                    if (lang == command.Language.fr) {
                        message.sendMessage("\ud83d\udcb8 Vous n'avez pas assez d'argent. Pour cette amelioration, il vous faudrait **" + Utils.format(need_money) + "$** ainsi que " + Utils.format(need_cuir) + " cuir et " + Utils.format(need_paille) + " paille").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage("\ud83d\udcb8 You don't have enough money. For this upgrade, You need **" + Utils.format(need_money) + "$**, " + Utils.format(need_cuir) + " leather and " + Utils.format(need_paille) + " straw").queue();
                    }
                }
            } else {
                if (lang == command.Language.fr) {
                    message.sendMessage("Vous pouvez am\u00e9liorer votre *march\u00e9* au niveau **" + level_up + "** pour **" + Utils.format(need_money) + "$** ainsi que " + Utils.format(need_cuir) + " cuir et " + Utils.format(need_paille) + " paille").queue();
                }
                if (lang == command.Language.en) {
                    message.sendMessage("Vous pouvez am\u00e9liorer votre *market* au niveau **" + level_up + "** pour **" + Utils.format(need_money) + "$** ainsi que " + Utils.format(need_cuir) + " leather et " + Utils.format(need_paille) + " straw").queue();
                }
            }
        } else if (c1.equals("cirque")) {
            long need_money;
            int level = building.get("cirque");
            long money = data.getProfils().get(user.getId()).getMoney();
            int level_up = level + 1;
            switch (level_up) {
                case 1: {
                    need_money = 15000L;
                    break;
                }
                case 2: {
                    need_money = 22500L;
                    break;
                }
                case 3: {
                    need_money = 35000L;
                    break;
                }
                case 4: {
                    need_money = 50000L;
                    break;
                }
                case 5: {
                    need_money = 75000L;
                    break;
                }
                case 6: {
                    need_money = 110000L;
                    break;
                }
                case 7: {
                    need_money = 170000L;
                    break;
                }
                case 8: {
                    need_money = 250000L;
                    break;
                }
                case 9: {
                    need_money = 380000L;
                    break;
                }
                case 10: {
                    need_money = 575000L;
                    break;
                }
                case 11: {
                    need_money = 860000L;
                    break;
                }
                case 12: {
                    need_money = 1300000L;
                    break;
                }
                case 13: {
                    need_money = 2000000L;
                    break;
                }
                case 14: {
                    need_money = 3000000L;
                    break;
                }
                case 15: {
                    need_money = 4300000L;
                    break;
                }
                case 16: {
                    need_money = 6500000L;
                    break;
                }
                case 17: {
                    need_money = 10000000L;
                    break;
                }
                case 18: {
                    need_money = 15000000L;
                    break;
                }
                case 19: {
                    need_money = 22000000L;
                    break;
                }
                case 20: {
                    need_money = 33000000L;
                    break;
                }
                default: {
                    need_money = 33000000L;
                    for (int i = 20; i < level_up; ++i) {
                        need_money = (long)((double)need_money * 1.5);
                    }
                }
            }
            if (c2 == level + 1) {
                if (level_up > 5 && level_up <= 10 && Glevel < 50) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau 50 pour ameliorer le cirque.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 50 to upgrade the circus.").queue();
                    }
                    return;
                }
                if (level_up > 10 && level_up <= 15 && Glevel < 100) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre au niveau 100 pour ameliorer le cirque").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 100 to upgrade the circus").queue();
                    }
                    return;
                }
                if (level_up > 15 && level_up <= 20 && Glevel < 150) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre au niveau 150 pour ameliorer le cirque").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 150 to upgrade the circus").queue();
                    }
                    return;
                }
                if (Glevel < 150 + (level_up - 20) * 50) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau " + (150 + (level_up - 20) * 50) + " pour ameliorer le cirque .").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level " + (150 + (level_up - 20) * 50) + " to upgrade the circus.").queue();
                    }
                    return;
                }
                if (money >= need_money) {
                    money -= need_money;
                    if (lang == command.Language.fr) {
                        message.sendMessage("Votre *cirque* vient d'\u00eatre amelior\u00e9 au niveau **" + level_up + "**").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage("Your *Circus* just upgraded to level **" + level_up + "**").queue();
                    }
                    building.put("cirque", level_up);
                    try {
                        data.getProfils().get(user.getId()).setBuilding(building);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setBuilding(building);
                    }
                    try {
                        data.getProfils().get(user.getId()).setMoney(money);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setMoney(money);
                    }
                } else {
                    if (lang == command.Language.fr) {
                        message.sendMessage("\ud83d\udcb8 Vous n'avez pas assez d'argent. Pour cette amelioration, il vous faudrait **" + Utils.format(need_money) + "$**").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage("\ud83d\udcb8 You don't have enough money. For this upgrade, you need **" + Utils.format(need_money) + "$**").queue();
                    }
                }
            } else {
                if (lang == command.Language.fr) {
                    message.sendMessage("Vous pouvez am\u00e9liorer votre *Cirque* au niveau **" + level_up + "** pour **" + Utils.format(need_money) + "$**").queue();
                }
                if (lang == command.Language.en) {
                    message.sendMessage("You can upgrade your *Circus* to level **" + level_up + "** for **" + Utils.format(need_money) + "$**").queue();
                }
            }
        } else if (c1.equals("armurerie")) {
            long need_money;
            int level;
            try {
                level = building.get("armurerie");
            }
            catch (NullPointerException e) {
                building.put("armurerie", 0);
                data.getProfils().get(user.getId()).setBuilding(building);
                level = 0;
            }
            long money = data.getProfils().get(user.getId()).getMoney();
            int level_up = level + 1;
            switch (level_up) {
                case 1: {
                    need_money = 12500000L;
                    break;
                }
                case 2: {
                    need_money = 18700000L;
                    break;
                }
                case 3: {
                    need_money = 28100000L;
                    break;
                }
                case 4: {
                    need_money = 42100000L;
                    break;
                }
                case 5: {
                    need_money = 63200000L;
                    break;
                }
                case 6: {
                    need_money = 94900000L;
                    break;
                }
                case 7: {
                    need_money = 142000000L;
                    break;
                }
                case 8: {
                    need_money = 213000000L;
                    break;
                }
                case 9: {
                    need_money = 320000000L;
                    break;
                }
                case 10: {
                    need_money = 480000000L;
                    break;
                }
                case 11: {
                    need_money = 720000000L;
                    break;
                }
                case 12: {
                    need_money = 1080000000L;
                    break;
                }
                case 13: {
                    need_money = 1620000000L;
                    break;
                }
                case 14: {
                    need_money = 2430000000L;
                    break;
                }
                case 15: {
                    need_money = 3640000000L;
                    break;
                }
                case 16: {
                    need_money = 5470000000L;
                    break;
                }
                case 17: {
                    need_money = 8210000000L;
                    break;
                }
                case 18: {
                    need_money = 12000000000L;
                    break;
                }
                case 19: {
                    need_money = 18000000000L;
                    break;
                }
                case 20: {
                    need_money = 27000000000L;
                    break;
                }
                default: {
                    need_money = 41000000000L;
                    for (int i = 20; i < level_up; ++i) {
                        need_money = (long)((double)need_money * 1.5);
                    }
                }
            }
            if (c2 == level + 1) {
                if (level_up > 5 && level_up <= 10 && Glevel < 200) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau 200 pour ameliorer l'armurerie.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 200 to upgrade your armory.").queue();
                    }
                    return;
                }
                if (level_up > 10 && level_up <= 15 && Glevel < 250) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre au niveau 250 pour ameliorer l'armurerie").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 250 to upgrade you armory").queue();
                    }
                    return;
                }
                if (level_up > 15 && level_up <= 20 && Glevel < 300) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre au niveau 300 pour ameliorer l'armurerie").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 300 to upgrade you armory").queue();
                    }
                    return;
                }
                if (Glevel < 300 + (level_up - 20) * 50) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau " + (300 + (level_up - 20) * 50) + " pour ameliorer l'armurerie.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level " + (300 + (level_up - 20) * 50) + " to upgrade your armory .").queue();
                    }
                    return;
                }
                if (money >= need_money) {
                    money -= need_money;
                    if (lang == command.Language.fr) {
                        message.sendMessage("Votre *armurerie* vient d'\u00eatre amelior\u00e9 au niveau **" + level_up + "**").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage("Your *armory* upgraded to level **" + level_up + "**").queue();
                    }
                    building.put("armurerie", level_up);
                    try {
                        data.getProfils().get(user.getId()).setBuilding(building);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setBuilding(building);
                    }
                    try {
                        data.getProfils().get(user.getId()).setMoney(money);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setMoney(money);
                    }
                } else {
                    if (lang == command.Language.fr) {
                        message.sendMessage("\ud83d\udcb8 Vous n'avez pas assez d'argent. Pour cette amelioration, il vous faudrait **" + Utils.format(need_money) + "$**").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage("\ud83d\udcb8 You don't have enough money. For this upgrade, you need **" + Utils.format(need_money) + "$**").queue();
                    }
                }
            } else {
                if (lang == command.Language.fr) {
                    message.sendMessage("Vous pouvez am\u00e9liorer votre *armurerie* au niveau **" + level_up + "** pour **" + Utils.format(need_money) + "$**").queue();
                }
                if (lang == command.Language.en) {
                    message.sendMessage("You can upgrade your *armory* to level **" + level_up + "** for **" + Utils.format(need_money) + "$**").queue();
                }
            }
        } else if (c1.equals("habitations")) {
            long need_money;
            int level = building.get("habitations");
            long money = data.getProfils().get(user.getId()).getMoney();
            int level_up = level + 1;
            int need_bois = 0;
            int need_iron = 0;
            switch (level_up) {
                case 1: {
                    need_money = 1000L;
                    break;
                }
                case 2: {
                    need_money = 1500L;
                    break;
                }
                case 3: {
                    need_money = 2000L;
                    break;
                }
                case 4: {
                    need_money = 3000L;
                    break;
                }
                case 5: {
                    need_money = 4250L;
                    break;
                }
                case 6: {
                    need_money = 6000L;
                    break;
                }
                case 7: {
                    need_money = 9000L;
                    break;
                }
                case 8: {
                    need_money = 13000L;
                    break;
                }
                case 9: {
                    need_money = 18500L;
                    break;
                }
                case 10: {
                    need_money = 26500L;
                    break;
                }
                case 11: {
                    need_money = 40000L;
                    break;
                }
                case 12: {
                    need_money = 55000L;
                    break;
                }
                case 13: {
                    need_money = 80000L;
                    break;
                }
                case 14: {
                    need_money = 115000L;
                    break;
                }
                case 15: {
                    need_money = 165000L;
                    break;
                }
                case 16: {
                    need_money = 235000L;
                    break;
                }
                case 17: {
                    need_money = 340000L;
                    break;
                }
                case 18: {
                    need_money = 500000L;
                    break;
                }
                case 19: {
                    need_money = 700000L;
                    break;
                }
                case 20: {
                    need_money = 1000000L;
                    break;
                }
                case 21: {
                    need_money = 1500000L;
                    need_bois = 100;
                    need_iron = 100;
                    break;
                }
                case 22: {
                    need_money = 2250000L;
                    need_bois = 150;
                    need_iron = 150;
                    break;
                }
                case 23: {
                    need_money = 3375000L;
                    need_bois = 225;
                    need_iron = 225;
                    break;
                }
                case 24: {
                    need_money = 5000000L;
                    need_bois = 350;
                    need_iron = 350;
                    break;
                }
                case 25: {
                    need_money = 7600000L;
                    need_bois = 500;
                    need_iron = 500;
                    break;
                }
                default: {
                    need_money = 7600000L;
                    need_iron = 500;
                    need_bois = 500;
                    for (int i = 25; i < level_up; ++i) {
                        need_money = (long)((double)need_money * 1.5);
                        need_iron = (int)((double)need_iron * 1.25);
                        need_bois = (int)((double)need_bois * 1.25);
                    }
                }
            }
            if (c2 == level + 1) {
                if (level_up > 20 && level_up <= 25 && Glevel < 150) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau 150 pour ameliorer vos habitations.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 150 to upgrade your residentials.").queue();
                    }
                    return;
                }
                if (Glevel < 150 + (level_up - 25) * 50) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau " + (150 + (level_up - 25) * 50) + " pour ameliorer vos habitations .").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level " + (150 + (level_up - 25) * 50) + " to upgrade your residentials.").queue();
                    }
                    return;
                }
                System.out.println("4");
                if (money >= need_money && iron >= need_iron && bois >= need_bois) {
                    money -= need_money;
                    bois -= need_bois;
                    iron -= need_iron;
                    if (lang == command.Language.fr) {
                        message.sendMessage("Votre *centre commercial* vient d'\u00eatre amelior\u00e9 au niveau **" + level_up + "**").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage("Your *residentials* just upgraded to level **" + level_up + "**").queue();
                    }
                    building.put("habitations", level_up);
                    try {
                        data.getProfils().get(user.getId()).setBuilding(building);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setBuilding(building);
                    }
                    try {
                        data.getProfils().get(user.getId()).setMoney(money);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setMoney(money);
                    }
                    res.put("bois", bois);
                    res.put("iron", iron);
                    try {
                        data.getProfils().get(user.getId()).setRes(res);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setRes(res);
                    }
                } else {
                    if (lang == command.Language.fr) {
                        message.sendMessage("\ud83d\udcb8 Vous n'avez pas assez d'argent. Pour cette amelioration, il vous faudrait **" + Utils.format(need_money) + "$** ainsi que " + Utils.format(need_bois) + " bois et " + need_iron + " iron.").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage("\ud83d\udcb8 You don't have enough money. For this upgrade, you need **" + Utils.format(need_money) + "$**, " + Utils.format(need_bois) + " wood and " + need_iron + " iron.").queue();
                    }
                }
            } else {
                if (lang == command.Language.fr) {
                    message.sendMessage("Vous pouvez am\u00e9liorer votre *centre commercial* au niveau **" + level_up + "** pour **" + Utils.format(need_money) + "$** ainsi que " + Utils.format(need_bois) + " bois et " + need_iron + " p\u00e9trole.").queue();
                }
                if (lang == command.Language.en) {
                    message.sendMessage("You can upgrade your *residetials* to level **" + level_up + "** for **" + Utils.format(need_money) + "$**, " + Utils.format(need_bois) + " wood and " + need_iron + " iron.").queue();
                }
            }
        } else if (c1.equals("auberge") || c1.equals("aub")) {
            int need_money;
            int level = building.get("auberge");
            long money = data.getProfils().get(user.getId()).getMoney();
            int level_up = level + 1;
            if (level_up > 25) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Le niveau maximal de l'auberge est le niveau 25.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("The maximal level for the inn is the level 25.").queue();
                }
                return;
            }
            int need_argile = 0;
            int need_pierre = 0;
            switch (level_up) {
                case 1: {
                    need_money = 10000;
                    break;
                }
                case 2: {
                    need_money = 14000;
                    break;
                }
                case 3: {
                    need_money = 20000;
                    break;
                }
                case 4: {
                    need_money = 26000;
                    break;
                }
                case 5: {
                    need_money = 37000;
                    break;
                }
                case 6: {
                    need_money = 50000;
                    break;
                }
                case 7: {
                    need_money = 70000;
                    break;
                }
                case 8: {
                    need_money = 100000;
                    break;
                }
                case 9: {
                    need_money = 140000;
                    break;
                }
                case 10: {
                    need_money = 190000;
                    break;
                }
                case 11: {
                    need_money = 260000;
                    break;
                }
                case 12: {
                    need_money = 360000;
                    break;
                }
                case 13: {
                    need_money = 500000;
                    break;
                }
                case 14: {
                    need_money = 700000;
                    break;
                }
                case 15: {
                    need_money = 1000000;
                    break;
                }
                case 16: {
                    need_money = 1350000;
                    break;
                }
                case 17: {
                    need_money = 1900000;
                    break;
                }
                case 18: {
                    need_money = 2600000;
                    break;
                }
                case 19: {
                    need_money = 3600000;
                    break;
                }
                case 20: {
                    need_money = 5000000;
                    break;
                }
                case 21: {
                    need_money = 7500000;
                    need_argile = 300;
                    need_pierre = 300;
                    break;
                }
                case 22: {
                    need_money = 11250000;
                    need_argile = 450;
                    need_pierre = 450;
                    break;
                }
                case 23: {
                    need_money = 17000000;
                    need_argile = 675;
                    need_pierre = 675;
                    break;
                }
                case 24: {
                    need_money = 25000000;
                    need_argile = 1000;
                    need_pierre = 1000;
                    break;
                }
                case 25: {
                    need_money = 38000000;
                    need_argile = 1500;
                    need_pierre = 1500;
                    break;
                }
                default: {
                    need_money = 0;
                }
            }
            if (c2 == level + 1) {
                if (level_up > 20 && level_up <= 25 && Glevel < 250) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau 250 pour ameliorer l'auberge.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 250 to upgrade the inn.").queue();
                    }
                    return;
                }
                if (money >= (long)need_money && argile >= need_argile && pierre >= need_pierre) {
                    money -= (long)need_money;
                    argile -= need_argile;
                    pierre -= need_pierre;
                    if (lang == command.Language.fr) {
                        message.sendMessage("Votre auberge vient d'\u00eatre au niveau **" + level_up + "**").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage("Your inn just upgraded to level **" + level_up + "**").queue();
                    }
                    building.put("auberge", level_up);
                    try {
                        data.getProfils().get(user.getId()).setBuilding(building);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setBuilding(building);
                    }
                    try {
                        data.getProfils().get(user.getId()).setMoney(money);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setMoney(money);
                    }
                    res.put("argile", argile);
                    res.put("pierre", pierre);
                    try {
                        data.getProfils().get(user.getId()).setRes(res);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setRes(res);
                    }
                } else {
                    if (lang == command.Language.fr) {
                        message.sendMessage("\ud83d\udcb8 Vous n'avez pas assez d'argent. Pour cette amelioration, il vous faudrait **" + Utils.format(need_money) + "$** ainsi que " + Utils.format(need_argile) + " argile et " + Utils.format(need_pierre) + " paille.").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage("\ud83d\udcb8 You don't have enough money. For this upgrade, you need **" + Utils.format(need_money) + "$**, " + Utils.format(need_argile) + " clay and " + Utils.format(need_pierre) + " stone.").queue();
                    }
                }
            } else {
                if (lang == command.Language.fr) {
                    message.sendMessage("Vous pouvez am\u00e9liorer votre auberge au niveau **" + level_up + "** pour **" + Utils.format(need_money) + "$** ainsi que " + Utils.format(need_argile) + " argile et " + Utils.format(need_pierre) + " paille.").queue();
                }
                if (lang == command.Language.en) {
                    message.sendMessage("You can upgrade your inn to level **" + level_up + "** for **" + Utils.format(need_money) + "$**, " + Utils.format(need_argile) + " clay and " + Utils.format(need_pierre) + " stone.").queue();
                }
            }
        } else if (c1.equals("eglise") || c1.equals("egl")) {
            int level = building.get("eglise");
            long money = data.getProfils().get(user.getId()).getMoney();
            int level_up = level + 1;
            if (level_up != 1) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Ce batiment ne poss\u00e8de qu'un niveau.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("This building have only one level.").queue();
                }
                return;
            }
            if (Glevel < 100) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous devez etre level 100 pour construire l'eglise").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You must be level 100 to build the church").queue();
                }
                return;
            }
            int need_money = 2000000;
            int need_mat = 500;
            if (money >= (long)need_money && bois >= need_mat && cuir >= need_mat && iron >= need_mat && argile >= need_mat && paille >= need_mat && pierre >= need_mat) {
                money -= (long)need_money;
                bois -= need_mat;
                iron -= need_mat;
                argile -= need_mat;
                pierre -= need_mat;
                cuir -= need_mat;
                paille -= need_mat;
                if (lang == command.Language.fr) {
                    message.sendMessage("Votre eglise vient d'\u00eatre amelior\u00e9 au niveau **" + level_up + "**").queue();
                }
                if (lang == command.Language.en) {
                    message.sendMessage("Your church just upgraded to level **" + level_up + "**").queue();
                }
                building.put("eglise", level_up);
                try {
                    data.getProfils().get(user.getId()).setBuilding(building);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setBuilding(building);
                }
                try {
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                res.put("bois", bois);
                res.put("argile", argile);
                res.put("cuir", cuir);
                res.put("pierre", pierre);
                res.put("paille", paille);
                res.put("iron", iron);
                try {
                    data.getProfils().get(user.getId()).setRes(res);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setRes(res);
                }
            } else {
                if (lang == command.Language.fr) {
                    message.sendMessage("\ud83d\udcb8 Vous n'avez pas assez d'argent. Pour cette amelioration, il vous faudrait **" + Utils.format(need_money) + "$** ainsi que " + Utils.format(need_mat) + "de chaque materiaux").queue();
                }
                if (lang == command.Language.en) {
                    message.sendMessage("\ud83d\udcb8 You don't have enough money. For this upgrade, you need **" + Utils.format(need_money) + "$** and " + Utils.format(need_mat) + " of every materials").queue();
                }
            }
        } else if (c1.equals("golem") || c1.equals("gol")) {
            int level = building.get("golem");
            long money = data.getProfils().get(user.getId()).getMoney();
            int level_up = level + 1;
            if (level_up != 1) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Ce batiment ne poss\u00e8de qu'un niveau.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("This building have only one level.").queue();
                }
                return;
            }
            if (Glevel < 400) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous devez etre level 400 pour construire le golem").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You must be level 400 to build the golem").queue();
                }
                return;
            }
            long need_money = 5000000000L;
            int need_mat = 50000;
            if (money >= need_money && bois >= need_mat && cuir >= need_mat && iron >= need_mat && argile >= need_mat && paille >= need_mat && pierre >= need_mat) {
                money -= need_money;
                bois -= need_mat;
                iron -= need_mat;
                cuir -= need_mat;
                if (lang == command.Language.fr) {
                    message.sendMessage("Votre golem vient d'\u00eatre amelior\u00e9 au niveau **" + level_up + "**").queue();
                }
                if (lang == command.Language.en) {
                    message.sendMessage("Your golem just upgraded to level **" + level_up + "**").queue();
                }
                building.put("golem", level_up);
                try {
                    data.getProfils().get(user.getId()).setBuilding(building);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setBuilding(building);
                }
                try {
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                res.put("bois", bois);
                res.put("cuir", cuir);
                res.put("iron", iron);
                try {
                    data.getProfils().get(user.getId()).setRes(res);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setRes(res);
                }
            } else {
                if (lang == command.Language.fr) {
                    message.sendMessage("\ud83d\udcb8 Vous n'avez pas assez d'argent. Pour cette amelioration, il vous faudrait **" + Utils.format(need_money) + "$** ainsi que " + Utils.format(need_mat) + "de bois, cuir et fer").queue();
                }
                if (lang == command.Language.en) {
                    message.sendMessage("\ud83d\udcb8 You don't have enough money. For this upgrade, you need **" + Utils.format(need_money) + "$** and " + Utils.format(need_mat) + " of wood, leather and iron").queue();
                }
            }
        } else if (c1.equals("forge")) {
            int level;
            try {
                level = building.get("forge");
            }
            catch (NullPointerException e) {
                building.put("forge", 0);
                data.getProfils().get(user.getId()).setBuilding(building);
                level = 0;
            }
            long money = data.getProfils().get(user.getId()).getMoney();
            int level_up = level + 1;
            if (level_up != 1) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Ce batiment ne poss\u00e8de qu'un niveau.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("This building have only one level.").queue();
                }
                return;
            }
            if (Glevel < 300) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous devez etre level 300 pour construire la forge").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You must be level 300 to build the forge").queue();
                }
                return;
            }
            int need_money = 500000000;
            int need_mat = 2500;
            if (money >= (long)need_money && bois >= need_mat && cuir >= need_mat && iron >= need_mat && argile >= need_mat && paille >= need_mat && pierre >= need_mat) {
                money -= (long)need_money;
                bois -= need_mat;
                iron -= need_mat;
                argile -= need_mat;
                pierre -= need_mat;
                cuir -= need_mat;
                paille -= need_mat;
                if (lang == command.Language.fr) {
                    message.sendMessage("Votre forge vient d'\u00eatre amelior\u00e9 au niveau **" + level_up + "**").queue();
                }
                if (lang == command.Language.en) {
                    message.sendMessage("Your wrought just upgraded to level **" + level_up + "**").queue();
                }
                building.put("forge", level_up);
                try {
                    data.getProfils().get(user.getId()).setBuilding(building);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setBuilding(building);
                }
                try {
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                res.put("bois", bois);
                res.put("argile", argile);
                res.put("cuir", cuir);
                res.put("pierre", pierre);
                res.put("paille", paille);
                res.put("iron", iron);
                try {
                    data.getProfils().get(user.getId()).setRes(res);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setRes(res);
                }
            } else {
                if (lang == command.Language.fr) {
                    message.sendMessage("\ud83d\udcb8 Vous n'avez pas assez d'argent. Pour cette amelioration, il vous faudrait **" + Utils.format(need_money) + "$** ainsi que " + Utils.format(need_mat) + "de chaque materiaux").queue();
                }
                if (lang == command.Language.en) {
                    message.sendMessage("\ud83d\udcb8You don't have enough money. For this upgrade, you need **" + Utils.format(need_money) + "$** and " + Utils.format(need_mat) + "de chaque materiaux").queue();
                }
            }
        } else if (c1.equals("biblioth\u00e8que")) {
            int level = building.get("biblioth\u00e8que");
            long money = data.getProfils().get(user.getId()).getMoney();
            int level_up = level + 1;
            int need_money = 50000;
            int need_mat = 50;
            for (int i = 1; i < level + 1; ++i) {
                need_money = (int)((double)need_money * 1.5);
                need_mat = (int)((double)need_mat * 1.25);
            }
            if (c2 == level + 1) {
                if (money >= (long)need_money && bois >= need_mat && cuir >= need_mat && iron >= need_mat && argile >= need_mat && paille >= need_mat && pierre >= need_mat) {
                    money -= (long)need_money;
                    bois -= need_mat;
                    iron -= need_mat;
                    argile -= need_mat;
                    pierre -= need_mat;
                    cuir -= need_mat;
                    paille -= need_mat;
                    if (lang == command.Language.fr) {
                        message.sendMessage("Votre biblioth\u00e8que vient d'\u00eatre amelior\u00e9 au niveau **" + level_up + "**").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage("Your library just upgraded to level **" + level_up + "**").queue();
                    }
                    building.put("biblioth\u00e8que", level_up);
                    try {
                        data.getProfils().get(user.getId()).setBuilding(building);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setBuilding(building);
                    }
                    try {
                        data.getProfils().get(user.getId()).setMoney(money);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setMoney(money);
                    }
                    res.put("bois", bois);
                    res.put("argile", argile);
                    res.put("cuir", cuir);
                    res.put("pierre", pierre);
                    res.put("paille", paille);
                    res.put("iron", iron);
                    try {
                        data.getProfils().get(user.getId()).setRes(res);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setRes(res);
                    }
                } else {
                    if (lang == command.Language.fr) {
                        message.sendMessage("\ud83d\udcb8 Vous n'avez pas assez d'argent. Pour cette amelioration, il vous faudrait **" + Utils.format(need_money) + "$** ainsi que " + Utils.format(need_mat) + "de chaque materiaux").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage("\ud83d\udcb8You don't have enough money. For this upgrade, you need **" + Utils.format(need_money) + "$** and " + Utils.format(need_mat) + " of every materials").queue();
                    }
                }
            } else {
                if (lang == command.Language.fr) {
                    message.sendMessage("Vous pouvez am\u00e9liorer votre biblioth\u00e8que au niveau **" + level_up + "** pour **" + Utils.format(need_money) + "$** ainsi que " + Utils.format(need_mat) + "de chaque materiaux").queue();
                }
                if (lang == command.Language.en) {
                    message.sendMessage("You can upgrade you library to level **" + level_up + "** for **" + Utils.format(need_money) + "$** and " + Utils.format(need_mat) + " of every materials").queue();
                }
            }
        } else if (c1.equals("camp")) {
            int need_money;
            int level = building.get("camp d'entrainement");
            long money = data.getProfils().get(user.getId()).getMoney();
            int level_up = level + 1;
            if (level_up > 30) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Le niveau maximal de la camp est le niveau 30.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("The maximal level of the training camp is the level 30.").queue();
                }
                return;
            }
            int need_bois = 0;
            int need_iron = 0;
            int need_cuir = 0;
            switch (level_up) {
                case 1: {
                    need_money = 25000;
                    break;
                }
                case 2: {
                    need_money = 35000;
                    break;
                }
                case 3: {
                    need_money = 50000;
                    break;
                }
                case 4: {
                    need_money = 65000;
                    break;
                }
                case 5: {
                    need_money = 90000;
                    break;
                }
                case 6: {
                    need_money = 121000;
                    break;
                }
                case 7: {
                    need_money = 170000;
                    break;
                }
                case 8: {
                    need_money = 230000;
                    break;
                }
                case 9: {
                    need_money = 320000;
                    break;
                }
                case 10: {
                    need_money = 430000;
                    break;
                }
                case 11: {
                    need_money = 600000;
                    break;
                }
                case 12: {
                    need_money = 800000;
                    break;
                }
                case 13: {
                    need_money = 1100000;
                    break;
                }
                case 14: {
                    need_money = 1500000;
                    break;
                }
                case 15: {
                    need_money = 2100000;
                    break;
                }
                case 16: {
                    need_money = 2900000;
                    break;
                }
                case 17: {
                    need_money = 3900000;
                    break;
                }
                case 18: {
                    need_money = 5400000;
                    break;
                }
                case 19: {
                    need_money = 7400000;
                    break;
                }
                case 20: {
                    need_money = 10000000;
                    break;
                }
                case 21: {
                    need_money = 15000000;
                    need_bois = 400;
                    need_iron = 400;
                    need_cuir = 400;
                    break;
                }
                case 22: {
                    need_money = 22500000;
                    need_bois = 600;
                    need_iron = 600;
                    need_cuir = 600;
                    break;
                }
                case 23: {
                    need_money = 33750000;
                    need_bois = 900;
                    need_iron = 900;
                    need_cuir = 900;
                    break;
                }
                case 24: {
                    need_money = 50000000;
                    need_bois = 1350;
                    need_iron = 1350;
                    need_cuir = 1350;
                    break;
                }
                case 25: {
                    need_money = 75000000;
                    need_bois = 2000;
                    need_iron = 2000;
                    need_cuir = 2000;
                    break;
                }
                case 26: {
                    need_money = 110000000;
                    need_bois = 2500;
                    need_iron = 2500;
                    need_cuir = 2500;
                    break;
                }
                case 27: {
                    need_money = 170000000;
                    need_bois = 3100;
                    need_iron = 3100;
                    need_cuir = 3100;
                    break;
                }
                case 28: {
                    need_money = 250000000;
                    need_bois = 4000;
                    need_iron = 4000;
                    need_cuir = 4000;
                    break;
                }
                case 29: {
                    need_money = 380000000;
                    need_bois = 4900;
                    need_iron = 4900;
                    need_cuir = 4900;
                    break;
                }
                case 30: {
                    need_money = 570000000;
                    need_bois = 6100;
                    need_iron = 6100;
                    need_cuir = 6100;
                    break;
                }
                default: {
                    need_money = 0;
                }
            }
            if (c2 == level + 1) {
                if (level_up > 20 && level_up <= 25 && Glevel < 250) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau 250 pour ameliorer votre camp.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 250 to upgrade your training camp.").queue();
                    }
                    return;
                }
                if (level_up > 25 && level_up <= 30 && Glevel < 300) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau 300 pour ameliorer votre camp.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 300 to upgrade your training camp.").queue();
                    }
                    return;
                }
                int tuto = data.getProfils().get(user.getId()).getTuto();
                if (tuto == 7) {
                    need_money = 0;
                    need_bois = 0;
                    need_cuir = 0;
                    need_iron = 0;
                }
                if (money >= (long)need_money && bois >= need_bois && cuir >= need_cuir && iron >= need_iron) {
                    money -= (long)need_money;
                    bois -= need_bois;
                    iron -= need_iron;
                    cuir -= need_cuir;
                    if (lang == command.Language.fr) {
                        message.sendMessage("Votre camp vient d'\u00eatre amelior\u00e9 au niveau **" + level_up + "**").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage("Your training camp just upgraded to level **" + level_up + "**").queue();
                    }
                    building.put("camp d'entrainement", level_up);
                    try {
                        data.getProfils().get(user.getId()).setBuilding(building);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setBuilding(building);
                    }
                    try {
                        data.getProfils().get(user.getId()).setMoney(money);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setMoney(money);
                    }
                    res.put("bois", bois);
                    res.put("argile", argile);
                    res.put("cuir", cuir);
                    res.put("pierre", pierre);
                    res.put("paille", paille);
                    res.put("iron", iron);
                    try {
                        data.getProfils().get(user.getId()).setRes(res);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setRes(res);
                    }
                    if (tuto == 7) {
                        data.getProfils().get(user.getId()).setTuto(8);
                        if (lang == command.Language.fr) {
                            channel.sendMessage("Vous pouvez maintenant entrainer des soldats, faites le avec la commande =soldier train 15.").queue();
                        }
                        if (lang == command.Language.en) {
                            channel.sendMessage("You can now train soldiers, do it with the command =soldier train 15.").queue();
                        }
                    }
                } else {
                    if (lang == command.Language.fr) {
                        message.sendMessage("\ud83d\udcb8 Vous n'avez pas assez d'argent. Pour cette amelioration, il vous faudrait **" + Utils.format(need_money) + "$** ainsi que " + Utils.format(need_bois) + " bois, " + Utils.format(need_cuir) + " cuir et " + need_iron + " p\u00e9trole.").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage("\ud83d\udcb8 You don't have enough money. For this upgrade, you need **" + Utils.format(need_money) + "$**, " + Utils.format(need_bois) + " wood, " + Utils.format(need_cuir) + " leather and " + need_iron + " iron.").queue();
                    }
                }
            } else {
                if (lang == command.Language.fr) {
                    message.sendMessage("Vous pouvez am\u00e9liorer votre camp au niveau **" + level_up + "** pour **" + Utils.format(need_money) + "$** ainsi que " + Utils.format(need_bois) + " bois, " + Utils.format(need_cuir) + " cuir et " + need_iron + " p\u00e9trole.").queue();
                }
                if (lang == command.Language.en) {
                    message.sendMessage("You can upgrade your training camp to level**" + level_up + "** for **" + Utils.format(need_money) + "$**, " + Utils.format(need_bois) + " wood, " + Utils.format(need_cuir) + " leather and " + need_iron + " iron.").queue();
                }
            }
        } else if (c1.equals("mine")) {
            int level = building.get("mine");
            long money = data.getProfils().get(user.getId()).getMoney();
            int level_up = level + 1;
            int need_mat = 0;
            long need_money = 0L;
            switch (level_up) {
                case 1: {
                    need_money = 1000000L;
                    need_mat = 150;
                    break;
                }
                case 2: {
                    need_money = 1500000L;
                    need_mat = 190;
                    break;
                }
                case 3: {
                    need_money = 2250000L;
                    need_mat = 230;
                    break;
                }
                case 4: {
                    need_money = 3375000L;
                    need_mat = 300;
                    break;
                }
                case 5: {
                    need_money = 5000000L;
                    need_mat = 370;
                    break;
                }
                case 6: {
                    need_money = 7500000L;
                    need_mat = 450;
                    break;
                }
                case 7: {
                    need_money = 11250000L;
                    need_mat = 575;
                    break;
                }
                case 8: {
                    need_money = 17000000L;
                    need_mat = 715;
                    break;
                }
                case 9: {
                    need_money = 25000000L;
                    need_mat = 900;
                    break;
                }
                case 10: {
                    need_money = 38500000L;
                    need_mat = 1100;
                    break;
                }
                case 11: {
                    need_money = 58000000L;
                    need_mat = 1400;
                    break;
                }
                case 12: {
                    need_money = 86500000L;
                    need_mat = 1750;
                    break;
                }
                case 13: {
                    need_money = 130000000L;
                    need_mat = 2182;
                    break;
                }
                case 14: {
                    need_money = 195000000L;
                    need_mat = 2728;
                    break;
                }
                case 15: {
                    need_money = 300000000L;
                    need_mat = 3410;
                    break;
                }
                case 16: {
                    need_money = 440000000L;
                    need_mat = 4260;
                    break;
                }
                case 17: {
                    need_money = 650000000L;
                    need_mat = 5330;
                    break;
                }
                case 18: {
                    need_money = 1000000000L;
                    need_mat = 6660;
                    break;
                }
                case 19: {
                    need_money = 1500000000L;
                    need_mat = 8320;
                    break;
                }
                case 20: {
                    need_money = new Long("2200000000");
                    need_mat = 10500;
                    break;
                }
                default: {
                    need_money = 2200000000L;
                    need_mat = 10500;
                    for (int i = 20; i < level_up; ++i) {
                        need_money = (long)((double)need_money * 1.5);
                        need_mat = (int)((double)need_mat * 1.25);
                    }
                }
            }
            if (c2 == level + 1) {
                if (level_up <= 5 && Glevel < 150) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau 150 pour construire votre mine.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 150 to build your mine.").queue();
                    }
                    return;
                }
                if (level_up > 5 && level_up <= 10 && Glevel < 200) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau 200 pour ameliorer votre mine.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 200 to upgrade your mine.").queue();
                    }
                    return;
                }
                if (level_up > 10 && level_up <= 15 && Glevel < 250) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau 250 pour ameliorer votre mine.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 250 to upgrade your mine.").queue();
                    }
                    return;
                }
                if (level_up > 15 && level_up <= 20 && Glevel < 300) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau 300 pour ameliorer votre mine.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 300 to upgrade your mine.").queue();
                    }
                    return;
                }
                if (Glevel < 300 + (level_up - 20) * 50) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau " + (300 + (level_up - 20) * 50) + " pour ameliorer la mine .").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level " + (300 + (level_up - 20) * 50) + " to upgrade your mine .").queue();
                    }
                    return;
                }
                System.out.println("4");
                if (money >= need_money && bois >= need_mat && argile >= need_mat && pierre >= need_mat && cuir >= need_mat && paille >= need_mat && iron >= need_mat) {
                    money -= need_money;
                    bois -= need_mat;
                    cuir -= need_mat;
                    pierre -= need_mat;
                    argile -= need_mat;
                    paille -= need_mat;
                    iron -= need_mat;
                    if (lang == command.Language.fr) {
                        message.sendMessage("Votre *mine* vient d'\u00eatre amelior\u00e9 au niveau **" + level_up + "**").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage("Your *mine* just upgraded to level **" + level_up + "**").queue();
                    }
                    building.put("mine", level_up);
                    try {
                        data.getProfils().get(user.getId()).setBuilding(building);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setBuilding(building);
                    }
                    try {
                        data.getProfils().get(user.getId()).setMoney(money);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setMoney(money);
                    }
                    res.put("bois", bois);
                    res.put("argile", argile);
                    res.put("cuir", cuir);
                    res.put("pierre", pierre);
                    res.put("paille", paille);
                    res.put("iron", iron);
                    try {
                        data.getProfils().get(user.getId()).setRes(res);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setRes(res);
                    }
                } else {
                    if (lang == command.Language.fr) {
                        message.sendMessage("\ud83d\udcb8 Vous n'avez pas assez d'argent. Pour cette amelioration, il vous faudrait **" + Utils.format(need_money) + "$** ainsi que " + Utils.format(need_mat) + " de chaque materiau.").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage("\ud83d\udcb8You don't have enough money. For this upgrade, you need **" + Utils.format(need_money) + "$** and " + Utils.format(need_mat) + " of every materials.").queue();
                    }
                }
            } else {
                if (lang == command.Language.fr) {
                    message.sendMessage("Vous pouvez am\u00e9liorer votre *mine* au niveau **" + level_up + "** pour **" + Utils.format(need_money) + "$**ainsi que " + Utils.format(need_mat) + " de chaque materiau.").queue();
                }
                if (lang == command.Language.en) {
                    message.sendMessage("You can upgrade your *mine* to level **" + level_up + "** for **" + Utils.format(need_money) + "$** and " + Utils.format(need_mat) + " of every materials.").queue();
                }
            }
        } else if (c1.equals("muraille")) {
            int level = building.get("muraille");
            long money = data.getProfils().get(user.getId()).getMoney();
            int level_up = level + 1;
            if (level_up > 20) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Le niveau maximal du muraille est le niveau 20.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("The maximal level of this building is level 20.").queue();
                }
                return;
            }
            int need_mat = 0;
            long need_money = 0L;
            switch (level_up) {
                case 1: {
                    need_money = 250000L;
                    need_mat = 75;
                    break;
                }
                case 2: {
                    need_money = 375000L;
                    need_mat = 90;
                    break;
                }
                case 3: {
                    need_money = 562500L;
                    need_mat = 115;
                    break;
                }
                case 4: {
                    need_money = 850000L;
                    need_mat = 150;
                    break;
                }
                case 5: {
                    need_money = 1250000L;
                    need_mat = 180;
                    break;
                }
                case 6: {
                    need_money = 1900000L;
                    need_mat = 230;
                    break;
                }
                case 7: {
                    need_money = 2900000L;
                    need_mat = 290;
                    break;
                }
                case 8: {
                    need_money = 4300000L;
                    need_mat = 350;
                    break;
                }
                case 9: {
                    need_money = 6500000L;
                    need_mat = 450;
                    break;
                }
                case 10: {
                    need_money = 9600000L;
                    need_mat = 560;
                    break;
                }
                case 11: {
                    need_money = 14500000L;
                    need_mat = 700;
                    break;
                }
                case 12: {
                    need_money = 21600000L;
                    need_mat = 880;
                    break;
                }
                case 13: {
                    need_money = 32500000L;
                    need_mat = 1100;
                    break;
                }
                case 14: {
                    need_money = 50000000L;
                    need_mat = 1350;
                    break;
                }
                case 15: {
                    need_money = 73000000L;
                    need_mat = 1700;
                    break;
                }
                case 16: {
                    need_money = 110000000L;
                    need_mat = 2130;
                    break;
                }
                case 17: {
                    need_money = 164000000L;
                    need_mat = 2660;
                    break;
                }
                case 18: {
                    need_money = 250000000L;
                    need_mat = 3330;
                    break;
                }
                case 19: {
                    need_money = 370000000L;
                    need_mat = 4150;
                    break;
                }
                case 20: {
                    need_money = 550000000L;
                    need_mat = 5200;
                    break;
                }
                default: {
                    need_money = 0L;
                }
            }
            if (c2 == level + 1) {
                if (level_up <= 5 && Glevel < 100) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau 100 pour construire le muraille.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 100 to build the wall.").queue();
                    }
                    return;
                }
                if (level_up > 5 && level_up <= 10 && Glevel < 150) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau 150 pour construire le muraille.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 150 to build the wall.").queue();
                    }
                    return;
                }
                if (level_up > 10 && level_up <= 15 && Glevel < 200) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau 200 pour construire le muraille.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 200 to build the wall.").queue();
                    }
                    return;
                }
                if (level_up > 15 && level_up <= 20 && Glevel < 250) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau 250 pour construire le muraille.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 250 to build the wall.").queue();
                    }
                    return;
                }
                System.out.println("4");
                if (money >= need_money && argile >= need_mat && pierre >= need_mat && paille >= need_mat) {
                    money -= need_money;
                    pierre -= need_mat;
                    argile -= need_mat;
                    paille -= need_mat;
                    if (lang == command.Language.fr) {
                        message.sendMessage("Votre *muraille* vient d'\u00eatre amelior\u00e9 au niveau **" + level_up + "**").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage("Your *wall* just upgraded to level **" + level_up + "**").queue();
                    }
                    building.put("muraille", level_up);
                    try {
                        data.getProfils().get(user.getId()).setBuilding(building);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setBuilding(building);
                    }
                    try {
                        data.getProfils().get(user.getId()).setMoney(money);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setMoney(money);
                    }
                    res.put("bois", bois);
                    res.put("argile", argile);
                    res.put("cuir", cuir);
                    res.put("pierre", pierre);
                    res.put("paille", paille);
                    res.put("iron", iron);
                    try {
                        data.getProfils().get(user.getId()).setRes(res);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setRes(res);
                    }
                } else {
                    if (lang == command.Language.fr) {
                        message.sendMessage("\ud83d\udcb8 Vous n'avez pas assez d'argent. Pour cette amelioration, il vous faudrait **" + Utils.format(need_money) + "$** ainsi que " + Utils.format(need_mat) + "  de  paille, argile et paille.").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage("\ud83d\udcb8 You don't have enough money. For this upgrade, you need **" + Utils.format(need_money) + "$** and " + Utils.format(need_mat) + " of  paille, argile and pierre.").queue();
                    }
                }
            } else {
                if (lang == command.Language.fr) {
                    message.sendMessage("Vous pouvez am\u00e9liorer votre *muraille* au niveau **" + level_up + "** pour **" + Utils.format(need_money) + "$**ainsi que " + Utils.format(need_mat) + " de  paille, argile et paille.").queue();
                }
                if (lang == command.Language.en) {
                    message.sendMessage("You can upgrade your *wall* to level **" + level_up + "** for **" + Utils.format(need_money) + "$** and " + Utils.format(need_mat) + " of  staw, clay et stone.").queue();
                }
            }
        } else if (c1.equals("tower") || c1.equals("wizard")) {
            int level = building.get("tour de sorcier");
            long money = data.getProfils().get(user.getId()).getMoney();
            int level_up = level + 1;
            int need_mat = 0;
            long need_money = 0L;
            switch (level_up) {
                case 1: {
                    need_money = 250000L;
                    need_mat = 50;
                    break;
                }
                case 2: {
                    need_money = 375000L;
                    need_mat = 65;
                    break;
                }
                case 3: {
                    need_money = 560000L;
                    need_mat = 80;
                    break;
                }
                case 4: {
                    need_money = 850000L;
                    need_mat = 100;
                    break;
                }
                case 5: {
                    need_money = 1250000L;
                    need_mat = 125;
                    break;
                }
                case 6: {
                    need_money = 1900000L;
                    need_mat = 150;
                    break;
                }
                case 7: {
                    need_money = 2850000L;
                    need_mat = 200;
                    break;
                }
                case 8: {
                    need_money = 4200000L;
                    need_mat = 240;
                    break;
                }
                case 9: {
                    need_money = 6400000L;
                    need_mat = 300;
                    break;
                }
                case 10: {
                    need_money = 9600000L;
                    need_mat = 375;
                    break;
                }
                case 11: {
                    need_money = 14500000L;
                    need_mat = 465;
                    break;
                }
                case 12: {
                    need_money = 21500000L;
                    need_mat = 580;
                    break;
                }
                case 13: {
                    need_money = 32500000L;
                    need_mat = 730;
                    break;
                }
                case 14: {
                    need_money = 50000000L;
                    need_mat = 910;
                    break;
                }
                case 15: {
                    need_money = 73000000L;
                    need_mat = 1140;
                    break;
                }
                case 16: {
                    need_money = 110000000L;
                    need_mat = 1420;
                    break;
                }
                case 17: {
                    need_money = 165000000L;
                    need_mat = 1775;
                    break;
                }
                case 18: {
                    need_money = 250000000L;
                    need_mat = 2220;
                    break;
                }
                case 19: {
                    need_money = 370000000L;
                    need_mat = 2775;
                    break;
                }
                case 20: {
                    need_money = 550000000L;
                    need_mat = 3500;
                    break;
                }
                default: {
                    need_money = 550000000L;
                    need_mat = 3500;
                    for (int i = 20; i < level_up; ++i) {
                        need_money = (long)((double)need_money * 1.5);
                        need_mat = (int)((double)need_mat * 1.25);
                    }
                }
            }
            if (c2 == level + 1) {
                if (level_up == 1 && Glevel < 50) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau 50 pour construire le tour de sorcier .").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 50 to build the wizard tower .").queue();
                    }
                    return;
                }
                if (level_up > 1 && level_up <= 5 && Glevel < 100) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau 100 pour ameliorer le tour de sorcier .").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 100 to upgrade the wizard tower .").queue();
                    }
                    return;
                }
                if (level_up > 5 && level_up <= 10 && Glevel < 150) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau 150 pour ameliorer le tour de sorcier .").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 150 to upgrade the wizard tower .").queue();
                    }
                    return;
                }
                if (level_up > 10 && level_up <= 15 && Glevel < 200) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau 200 pour ameliorer le tour de sorcier .").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 200 to upgrade the wizard tower .").queue();
                    }
                    return;
                }
                if (level_up > 15 && level_up <= 20 && Glevel < 250) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau 250 pour ameliorer le tour de sorcier .").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level 250 to upgrade the wizard tower .").queue();
                    }
                    return;
                }
                if (Glevel < 250 + (level_up - 20) * 50) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez etre niveau " + (250 + (level_up - 20) * 50) + " pour ameliorer le tour de sorcier .").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must be level " + (250 + (level_up - 20) * 50) + " to upgrade your wizard tower.").queue();
                    }
                    return;
                }
                System.out.println("4");
                if (money >= need_money && argile >= need_mat && pierre >= need_mat && paille >= need_mat) {
                    money -= need_money;
                    pierre -= need_mat;
                    argile -= need_mat;
                    paille -= need_mat;
                    if (lang == command.Language.fr) {
                        message.sendMessage("Votre *tour de sorcier* vient d'\u00eatre amelior\u00e9 au niveau **" + level_up + "**").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage("your *wizard tower* just upgraded to level **" + level_up + "**").queue();
                    }
                    building.put("tour de sorcier", level_up);
                    try {
                        data.getProfils().get(user.getId()).setBuilding(building);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setBuilding(building);
                    }
                    try {
                        data.getProfils().get(user.getId()).setMoney(money);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setMoney(money);
                    }
                    res.put("bois", bois);
                    res.put("argile", argile);
                    res.put("cuir", cuir);
                    res.put("pierre", pierre);
                    res.put("paille", paille);
                    res.put("iron", iron);
                    try {
                        data.getProfils().get(user.getId()).setRes(res);
                    }
                    catch (NullPointerException e) {
                        data.getProfils().put(user.getId(), new Profil(user.getId()));
                        data.getProfils().get(user.getId()).setRes(res);
                    }
                } else {
                    if (lang == command.Language.fr) {
                        message.sendMessage("\ud83d\udcb8 Vous n'avez pas assez d'argent. Pour cette amelioration, il vous faudrait **" + Utils.format(need_money) + "$** ainsi que " + Utils.format(need_mat) + " de pierre, paille et argile.").queue();
                    }
                    if (lang == command.Language.en) {
                        message.sendMessage("\ud83d\udcb8 You don't have enough money. For this upgrade, you need **" + Utils.format(need_money) + "$** and " + Utils.format(need_mat) + " de pierre, paille et argile.").queue();
                    }
                }
            } else {
                if (lang == command.Language.fr) {
                    message.sendMessage("Vous pouvez am\u00e9liorer votre *tour de sorcier* au niveau **" + level_up + "** pour **" + Utils.format(need_money) + "$**ainsi que " + Utils.format(need_mat) + " de pierre, paille et argile.").queue();
                }
                if (lang == command.Language.en) {
                    message.sendMessage("You can upgrade your *wizard tower* to level **" + level_up + "** for **" + Utils.format(need_money) + "$** and " + Utils.format(need_mat) + " de pierre, paille et argile.").queue();
                }
            }
        } else if (c3.equals("gare") || c3.equals("port") || c3.equals("aeroport") || c3.equals("zone de lancement")) {
            int level = building.get("transport");
            long money = data.getProfils().get(user.getId()).getMoney();
            if (level + 1 == 1 && !c3.equals("gare")) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous pouvez actuelement construire la gare pour 50.000.000$ et 500 materiaux.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You can actually build your gare for  50.000.000$ and 500 of every materials.").queue();
                }
                return;
            }
            if (level + 1 == 2 && !c3.equals("port")) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous pouvez actuelement construire le port pour 200.000.000$ et 1000 materiaux.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You cna actually buid your port fotr 200.000.000$ and 1000 of every materials.").queue();
                }
                return;
            }
            if (level + 1 == 3 && !c3.equals("aeroport")) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous pouvez actuelement construire l'aeroport pour 400.000.000$ et 2500 materiaux.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You can actually build your aeroport for 400.000.000$ and 2500 of every materials.").queue();
                }
                return;
            }
            if (level + 1 == 4 && !c3.equals("zone de lancement")) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous pouvez actuelement construire la fus\u00e9e pour 800.000.000$ et 5000 materiaux.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You can actually build la fus\u00e9e for 800.000.000$ and 5000 of every materials.").queue();
                }
                return;
            }
            if (level + 1 > 4) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous avez atteint le niveau maximal").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You have reach the maximal level for this building").queue();
                }
                return;
            }
            int need_money = 0;
            int need_mat = 0;
            if (level + 1 == 1) {
                need_money = 50000000;
                need_mat = 500;
            } else if (level + 1 == 2) {
                need_money = 200000000;
                need_mat = 1000;
            } else if (level + 1 == 3) {
                need_money = 400000000;
                need_mat = 2500;
            } else if (level + 1 == 4) {
                need_money = 800000000;
                need_mat = 5000;
            }
            if (level + 1 == 1 && Glevel < 200) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous devez etre niveau 200 pour construire la gare .").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You must be level 200 to build the gare .").queue();
                }
                return;
            }
            if (level + 1 == 2 && Glevel < 250) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous devez etre niveau 250 pour construire le port .").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You must be level 250 yo build the port .").queue();
                }
                return;
            }
            if (level + 1 == 3 && Glevel < 300) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous devez etre niveau 300 pour construire l'aeroport .").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You must be level 300 to build the aeroport .").queue();
                }
                return;
            }
            if (level + 1 == 4 && Glevel < 350) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous devez etre niveau 350 pour construire la zone de lancement .").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You must be level 350 to build the zone de lancement.").queue();
                }
                return;
            }
            if (money >= (long)need_money && bois >= need_mat && cuir >= need_mat && argile >= need_mat && pierre >= need_mat && paille >= need_mat && iron >= need_mat) {
                money -= (long)need_money;
                bois -= need_mat;
                cuir -= need_mat;
                pierre -= need_mat;
                argile -= need_mat;
                paille -= need_mat;
                iron -= need_mat;
                if (lang == command.Language.fr) {
                    message.sendMessage("Vous venez de construire : " + c3).queue();
                }
                if (lang == command.Language.en) {
                    message.sendMessage("You just build : " + c3).queue();
                }
                building.put("transport", level + 1);
                try {
                    data.getProfils().get(user.getId()).setBuilding(building);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setBuilding(building);
                }
                try {
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                res.put("bois", bois);
                res.put("argile", argile);
                res.put("cuir", cuir);
                res.put("pierre", pierre);
                res.put("paille", paille);
                res.put("iron", iron);
                try {
                    data.getProfils().get(user.getId()).setRes(res);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setRes(res);
                }
            } else {
                if (lang == command.Language.fr) {
                    message.sendMessage("\ud83d\udcb8 Vous n'avez pas assez d'argent. Pour cette amelioration, il vous faudrait **" + Utils.format(need_money) + "$** ainsi que " + Utils.format(need_mat) + " de chaque materiau").queue();
                }
                if (lang == command.Language.en) {
                    message.sendMessage("\ud83d\udcb8 You don't have enough money. For this upgrade , you need **" + Utils.format(need_money) + "$** and " + Utils.format(need_mat) + " of every materials").queue();
                }
            }
        } else {
            if (lang == command.Language.fr) {
                message.sendMessage("=build ``habitation``, ``auberge``, ``march\u00e9``, ``camp``, ``cirque``, ``labo``, ``mine``,``biblioth\u00e8que``, ``muraille``, ``gare``, ``port``, ``aeroport``, ``zone de lancement``").queue();
            }
            if (lang == command.Language.en) {
                message.sendMessage("=build ``habitation``, ``auberge``, ``march\u00e9``, ``camp``, ``cirque``, ``labo``, ``mine``,``biblioth\u00e8que``, ``muraille``, ``gare``, ``port``, ``aeroport``, ``zone de lancement``").queue();
            }
        }
    }
}

