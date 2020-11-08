/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands;

import fr.Ybsi.OzeryoBot.Commands.Default.Hypixel;
import fr.Ybsi.OzeryoBot.Commands.Default.*;
import fr.Ybsi.OzeryoBot.Commands.Game.*;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Music.MusicCommands;
import fr.Ybsi.OzeryoBot.Utils.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class CommandMap {
    private static final Map<String, SimpleCommand> commands;
    private static final Map<String, SimpleCommand> commands2;
    private static final ScheduledExecutorService scheduler1;
    public static String prefix;
    public static String tag;
    public static String tag2;
    public static int commands1;
    private static DiscordBot DiscordBot;

    static {
        commands = new HashMap<String, SimpleCommand>();
        commands2 = new HashMap<String, SimpleCommand>();
        prefix = "=";
        tag = "@OzeryoBot ";
        tag2 = "ozr ";
        commands1 = 0;
        scheduler1 = Executors.newScheduledThreadPool(4);
    }

    private final Map<Long, Integer> powers = new HashMap<Long, Integer>();

    public CommandMap(DiscordBot DiscordBot2) {
        DiscordBot = DiscordBot2;
        this.registerCommands(new CommandDefaut(DiscordBot2), new HelpCommand(this), new MusicCommands(), new Google(),
                new Hypixel(), new ChannelSet(), new NSFW(), new City(), new hourly(), new Daily(), new Build(),
                new reputation(), new Attack(), new EmojiCommand(), new WOT(), new Mana(), new Fortnite(), new Pays(),
                new Soldier(), new Shop(), new Zoo(), new Habitations(), new Trade(), new Factory(), new BlackList(),
                new Quests(), new Pray(), new Spy(), new Achivement(), new Cosmetics(), new Heroe(), new LootBox(),
                new Mail(), new Competence(), new Pub());
    }

    /*
     * Enabled aggressive block sorting Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean commandUser(User user, String command2, Message message, MessageChannel channel,
                                      Guild guild, JDA jda) {

        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/"+user.getId()+"/");

        long t1 = System.currentTimeMillis();
        ++commands1;
        ProfilData data = DiscordBot.getData();
        Object[] object = CommandMap.getCommand(command2);
        if (object[0] == null)
            return false;
        if (((SimpleCommand) object[0]).getExecutorType() == command.ExecutorType.CONSOLE) {
            return false;
        }
        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/BlackList/");
        TextFileWriter.folder(
                "/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/BlackList/" + channel.getId() + "/");
        if (TextFileWriter
                .read("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/BlackList/" + channel.getId()
                        + "/all")
                .equals("true") && !((SimpleCommand) object[0]).getMethod().getName().equals("blacklist")) {
            return false;
        }
        if (TextFileWriter.read("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/BlackList/"
                + channel.getId() + "/" + ((SimpleCommand) object[0]).getMethod().getName()).equals("true")) {
            return false;
        }
        if (((SimpleCommand) object[0]).getMethod().getName().equals("donotusethiscommand")) {
            return true;
        }
        try {
            String string = data.getProfils().get(user.getId()).getId();
        } catch (NullPointerException e) {
            data.getProfils().put(user.getId(), new Profil(user.getId()));
        }
        try {
            boolean CoolDown1;
            boolean game = false;
            try {
                game = data.getProfils().get(user.getId()).isGame();
            } catch (NullPointerException e) {
                game = false;
            }
            if (((SimpleCommand) object[0]).getTopic() == command.Topics.Game) {
                long maj = 1566165600000L;
                long time = System.currentTimeMillis();
                long dif = maj - time;
                if (dif > 0L && !user.getId().equals("431460839942389760")) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(dif);
                    int mDay = calendar.get(5);
                    int mHour = calendar.get(11);
                    int mMinute = calendar.get(12);
                    if (DiscordBot.getData().getProfils().get(user.getId()).getLanguage() == command.Language.fr) {
                        channel.sendMessage("OzeryoBot passe en version 3.0 dans " + mDay + " jours, " + mHour
                                + " heures et " + mMinute
                                + " minutes. Préinscrivez-vous pour \u00eatre averti de la sortie de la 3\u00e8me version d'OzeryoBot et ainsi obtenir 1 mois de premium gratuitement d\u00e8s \u00e0 présent gr\u00e2ce a la commande ``=register`` . Il y a actuelement "
                                + data.getRegisters() + " inscrits.").queue();
                        return true;
                    }
                    if (DiscordBot.getData().getProfils().get(user.getId()).getLanguage() != command.Language.en)
                        return true;
                    channel.sendMessage("OzeryoBot imporve itself to version 3.0 in " + mDay + " days, " + mHour
                            + " hours and " + mMinute
                            + " minutes. Pre-register to be notified of the release of the 3rd version of OzeryoBot and get 1 month of free premium now thanks to the ``=register`` command. There is actually "
                            + data.getRegisters() + "  people registered.").queue();
                    return true;
                }
                if (!game) {
                    System.out.print("New player");
                    data.getProfils().get(user.getId()).setGame(true);
                    CommandMap.NewPlayer(user);
                    int taille = TextFileWriter.folderlength("/home/DiscordBot/Rasberry/données/bot/Map/");
                    int x = 0;
                    int y = 0;
                    int places = 0;
                    int alea = 1 + (int) (Math.random() * (double) (442 - taille - 1 + 1));
                    for (x = -10; x <= 10; ++x) {
                        System.out.print(x);
                        for (y = -10; y <= 10; ++y) {
                            String name;
                            System.out.print(y);
                            try {
                                name = TextFileWriter
                                        .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt");
                            } catch (IllegalArgumentException e1) {
                                name = "0";
                            }
                            System.out.println(name);
                            if (name.equals("0")) {
                                ++places;
                            }
                            if (places >= alea)
                                break;
                        }
                        if (places >= alea)
                            break;
                    }
                    data.getProfils().get(user.getId()).setHome(String.valueOf(x) + "_" + y);
                    TextFileWriter.folder("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt",
                            user.getId(), 1);
                    CommandMap.PublicLog(":baby:  Un nouveau joueur a rejoint le monde d'OzeryoBot. Il s'agit de **"
                            + user.getName() + "**. Bonne chance \u00e0 lui.", DiscordBot.getjda());
                }
                Achivement.achivement(user, command2, channel);
                String home = "0";
                try {
                    home = data.getProfils().get(user.getId()).getHome();
                } catch (NullPointerException e) {
                    home = "0";
                }
                String name1 = "";
                String x1 = "1";
                String y1 = "";
                int taille = TextFileWriter.folderlength("/home/DiscordBot/Rasberry/données/bot/Map/");
                try {
                    String[] xy = home.split("_");
                    x1 = xy[0];
                    y1 = xy[1];
                    name1 = TextFileWriter
                            .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x1 + "_" + y1 + "/name.txt");
                } catch (ArrayIndexOutOfBoundsException xy) {
                    // empty catch block
                }
                if (home.equals("0") || !name1.equals(user.getId())) {
                    try {
                        TextFileWriter
                                .recursifDelete(new File("/home/DiscordBot/Rasberry/données/bot/Map/" + x1 + "_" + y1));
                    } catch (IOException xy) {
                        // empty catch block
                    }
                    int x = 0;
                    int y = 0;
                    int places = 0;
                    int alea = 1 + (int) (Math.random() * (double) (442 - taille - 1 + 1));
                    for (x = -10; x <= 10; ++x) {
                        System.out.print(x);
                        for (y = -10; y <= 10; ++y) {
                            String name;
                            System.out.print(y);
                            try {
                                name = TextFileWriter
                                        .read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt");
                            } catch (IllegalArgumentException e1) {
                                name = "0";
                            }
                            System.out.println(name);
                            if (name.equals("0")) {
                                ++places;
                            }
                            if (places >= alea)
                                break;
                        }
                        if (places >= alea)
                            break;
                    }
                    data.getProfils().get(user.getId()).setHome(String.valueOf(x) + "_" + y);
                    TextFileWriter.folder("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y);
                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt",
                            user.getId(), 1);
                }
                home = data.getProfils().get(user.getId()).getHome();
                home = "x=" + home.replaceAll("_", " | y=");
                int tuto = data.getProfils().get(user.getId()).getTuto();
                command.Language lang = DiscordBot.getData().getProfils().get(user.getId()).getLanguage();
                System.out.println("Tutoriel : " + tuto);
                if (tuto == 0 && ((SimpleCommand) object[0]).getMethod().getName() != "tuto") {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                ":mouse_three_button: Le tutoriel va commencer, il durera environ 2 minutes, soyez sur de pouvoir le terminer ( =tuto confirm ).")
                                .queue();
                    }
                    if (lang != command.Language.en)
                        return true;
                    channel.sendMessage(
                            ":mouse_three_button: The tutorial will start, it will last about 2 minutes, be sure to finish it ( =tuto confirm ).")
                            .queue();
                    return true;
                }
                if (tuto == 1 && ((SimpleCommand) object[0]).getMethod().getName() != "daily"
                        && ((SimpleCommand) object[0]).getMethod().getName() != "dl") {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Bien, commencez par récupérer votre récompense quotidienne avec la commande =daily.")
                                .queue();
                    }
                    if (lang != command.Language.en)
                        return true;
                    channel.sendMessage("Well, start by collecting your daily reward with the =daily command.").queue();
                    return true;
                }
                if (tuto == 2 && ((SimpleCommand) object[0]).getMethod().getName() != "hourly"
                        && ((SimpleCommand) object[0]).getMethod().getName() != "hr") {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Maintenant, obtenez votre salaire horaire avec la commande =hourly.")
                                .queue();
                    }
                    if (lang != command.Language.en)
                        return true;
                    channel.sendMessage("Now, get your hourly reward with the =hourly command.").queue();
                    return true;
                }
                if (tuto == 3 && ((SimpleCommand) object[0]).getMethod().getName() != "city"
                        && ((SimpleCommand) object[0]).getMethod().getName() != "c") {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous pouvez maintenant consulter votre ville avec la commande =city.")
                                .queue();
                    }
                    if (lang != command.Language.en)
                        return true;
                    channel.sendMessage("You can now check your city informations with the =city command..").queue();
                    return true;
                }
                if (tuto == 4 && ((SimpleCommand) object[0]).getMethod().getName() != "work"
                        && ((SimpleCommand) object[0]).getMethod().getName() != "w") {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Vous pouvez y voir votre exp, le niveau de vos constructions et vos réserves de ressources. Le mana, affiché en haut, vous permet de récolter des ressources \u00e0 l'aide de travail, essayez tout de suite avec =work all.")
                                .queue();
                    }
                    if (lang != command.Language.en)
                        return true;
                    channel.sendMessage(
                            "You can see your exp, the level of your builds, and your resource reserves. The mana, shown at the top, allows you to collect resources using work command, try now with =work all.")
                            .queue();
                    return true;
                }
                if (tuto == 5 && ((SimpleCommand) object[0]).getMethod().getName() != "build"
                        && ((SimpleCommand) object[0]).getMethod().getName() != "b") {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "\u00c0 présent, vous pouvez construire votre premier b\u00e2timent ! Commen\u00e7ons par le marché comme exemple : =b marché 1.")
                                .queue();
                    }
                    if (lang != command.Language.en)
                        return true;
                    channel.sendMessage(
                            "Now you can build your first building! Let's start with the market place as an example: = b marché 1.")
                            .queue();
                    return true;
                }
                if (tuto == 6 && ((SimpleCommand) object[0]).getMethod().getName() != "city"
                        && ((SimpleCommand) object[0]).getMethod().getName() != "c") {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Bravo, vous avez obtenu votre premi\u00e8re construction, celle-ci permet d'augmenter vos gains de money lors de vos commandes. Vous pouvez reconsulter votre ville maintenant =city.")
                                .queue();
                    }
                    if (lang != command.Language.en)
                        return true;
                    channel.sendMessage(
                            "Congratulations, you got your first build, it increases your money gains during your commands. You can consult again your city now =city.")
                            .queue();
                    return true;
                }
                if (tuto == 7 && ((SimpleCommand) object[0]).getMethod().getName() != "build"
                        && ((SimpleCommand) object[0]).getMethod().getName() != "b") {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Chaque construction poss\u00e8de une vingtaine de niveaux, vous pourrez les monter de niveaux gr\u00e2ce aux Ozecoins et aux différents matériaux. Maintenant, apprenons \u00e0 attaquer, tout d'abord construisez votre camp d'entrainement, =b camp 1.")
                                .queue();
                    }
                    if (lang != command.Language.en)
                        return true;
                    channel.sendMessage(
                            "Each building has about twenty levels, you can build levels with Ozecoins and different materials. Now, learn to attack, first build your barracks, = b camp 1.")
                            .queue();
                    return true;
                }
                if (tuto == 8 && ((SimpleCommand) object[0]).getMethod().getName() != "soldier") {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Vous pouvez maintenant entrainer des soldats, faites le avec la commande =soldier train 15.")
                                .queue();
                    }
                    if (lang != command.Language.en)
                        return true;
                    channel.sendMessage("You can now train soldiers, do it with the command =soldier train 15.")
                            .queue();
                    return true;
                }
                if (tuto == 9 && ((SimpleCommand) object[0]).getMethod().getName() != "attack"
                        && ((SimpleCommand) object[0]).getMethod().getName() != "a") {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Pour lancer une attaque vous pouvez consulter la map et utiliser l'id, le tag ou les coordonnés d'un joueur. Testons maintenant : =a Ozeryo 15.")
                                .queue();
                    }
                    if (lang != command.Language.en)
                        return true;
                    channel.sendMessage(
                            "To launch an attack you can check the map and use a player's id, tag, or coordinates. Now, let's test: =a Ozeryo 15.")
                            .queue();
                    return true;
                }
                int xp = data.getProfils().get(user.getId()).getOzPassXp();
                int lastpallier = data.getProfils().get(user.getId()).getPallier();
                int pallier = xp / 1000;
                if (Event.Summer()) {
                    do {
                        if (lastpallier > pallier || lastpallier > 100) {
                            data.getProfils().get(user.getId()).setPallier(lastpallier);
                            break;
                        }
                        if (lastpallier % 10 == 5) {
                            HashMap<String, ArrayList<Integer>> weapons;
                            try {
                                weapons = data.getProfils().get(user.getId()).getWeapons();
                            } catch (NullPointerException e) {
                                weapons = new HashMap<String, ArrayList<Integer>>();
                                weapons.put("epee", null);
                                weapons.put("spectre", null);
                                weapons.put("arc", null);
                                weapons.put("arbalete", null);
                                weapons.put("lance", null);
                                weapons.put("pelle de combat", null);
                                weapons.put("sarbacanne", null);
                                weapons.put("gourdin", null);
                                weapons.put("flechettes", null);
                                weapons.put("trident", null);
                                weapons.put("fleau", null);
                                weapons.put("fouet", null);
                                weapons.put("baton", null);
                                weapons.put("fourche", null);
                                weapons.put("dague", null);
                                weapons.put("shuriken", null);
                                weapons.put("katana", null);
                                data.getProfils().get(user.getId()).setWeapons(weapons);
                            }
                            HashMap<String, Integer> building = new HashMap();
                            building = data.getProfils().get(user.getId()).getBuilding();
                            if (LootBox.test(data.getProfils().get(user)) <= (Integer) building.get("armurerie") * 5 + 20) {
                                int aleaWeapons = 1 + (int) (Math.random() * 17.0);
                                String weapon = "epee";
                                switch (aleaWeapons) {
                                    case 1: {
                                        weapon = "epee";
                                        break;
                                    }
                                    case 2: {
                                        weapon = "spectre";
                                        break;
                                    }
                                    case 3: {
                                        weapon = "arc";
                                        break;
                                    }
                                    case 4: {
                                        weapon = "arbalete";
                                        break;
                                    }
                                    case 5: {
                                        weapon = "lance";
                                        break;
                                    }
                                    case 6: {
                                        weapon = "pelle de combat";
                                        break;
                                    }
                                    case 7: {
                                        weapon = "sarbacanne";
                                        break;
                                    }
                                    case 8: {
                                        weapon = "gourdin";
                                        break;
                                    }
                                    case 9: {
                                        weapon = "flechettes";
                                        break;
                                    }
                                    case 10: {
                                        weapon = "trident";
                                        break;
                                    }
                                    case 11: {
                                        weapon = "fleau";
                                        break;
                                    }
                                    case 12: {
                                        weapon = "fouet";
                                        break;
                                    }
                                    case 13: {
                                        weapon = "baton";
                                        break;
                                    }
                                    case 14: {
                                        weapon = "fourche";
                                        break;
                                    }
                                    case 15: {
                                        weapon = "dague";
                                        break;
                                    }
                                    case 16: {
                                        weapon = "shuriken";
                                        break;
                                    }
                                    case 17: {
                                        weapon = "katana";
                                        break;
                                    }
                                }
                                Double aleaWeapon1 = Math.random();
                                int aleaWeapon2 = 1;
                                if (lastpallier / 10 == 0 || lastpallier / 10 == 1) {
                                    aleaWeapon2 = 1;
                                } else if (lastpallier / 10 == 2 || lastpallier / 10 == 3) {
                                    aleaWeapon2 = 2;
                                } else if (lastpallier / 10 == 4 || lastpallier / 10 == 5) {
                                    aleaWeapon2 = 3;
                                } else if (lastpallier / 10 == 6 || lastpallier / 10 == 7) {
                                    aleaWeapon2 = 4;
                                } else if (lastpallier / 10 == 8 || lastpallier / 10 == 9) {
                                    aleaWeapon2 = 5;
                                }
                                ArrayList<Integer> weaponList = new ArrayList<Integer>();
                                try {
                                    weaponList = (ArrayList<Integer>) weapons.get(weapon);
                                    weaponList.add(aleaWeapon2);
                                } catch (NullPointerException e) {
                                    weaponList = new ArrayList<Integer>();
                                    weaponList.add(aleaWeapon2);
                                }
                                try {
                                    weapons.put(weapon, weaponList);
                                } catch (NullPointerException e) {
                                    weapons = new HashMap();
                                    weapons.put("epee", null);
                                    weapons.put("spectre", null);
                                    weapons.put("arc", null);
                                    weapons.put("arbalete", null);
                                    weapons.put("lance", null);
                                    weapons.put("pelle de combat", null);
                                    weapons.put("sarbacanne", null);
                                    weapons.put("gourdin", null);
                                    weapons.put("flechettes", null);
                                    weapons.put("trident", null);
                                    weapons.put("fleau", null);
                                    weapons.put("fouet", null);
                                    weapons.put("baton", null);
                                    weapons.put("fourche", null);
                                    weapons.put("dague", null);
                                    weapons.put("shuriken", null);
                                    weapons.put("katana", null);
                                    weapons.put(weapon, weaponList);
                                }
                                data.getProfils().get(user.getId()).setWeapons(weapons);
                                if (lang == command.Language.fr) {
                                    channel.sendMessage("Vous venez de franchir le pallier " + lastpallier
                                            + ". Vous avez donc obtenu une arme : " + weapon + " de niveau "
                                            + aleaWeapon2 + ".").queue();
                                }
                                if (lang == command.Language.en) {
                                    channel.sendMessage("You just croos the step " + lastpallier
                                            + ".So you won a weapon : " + weapon + " level " + aleaWeapon2 + ".")
                                            .queue();
                                }
                            } else {
                                if (lang == command.Language.fr) {
                                    channel.sendMessage("Vous venez de franchir le pallier " + lastpallier
                                            + ". Vous n'avez pas pu obtenir d'arme car votre entrepot est plein.")
                                            .queue();
                                }
                                if (lang == command.Language.en) {
                                    channel.sendMessage("You jsut cross the step " + lastpallier
                                            + ". But you didn't win any weapon because your repository is full.")
                                            .queue();
                                }
                            }
                        }
                        if (lastpallier % 10 == 1) {
                            HashMap<String, ArrayList<Integer>> armors;
                            try {
                                armors = data.getProfils().get(user.getId()).getArmor();
                            } catch (NullPointerException e) {
                                armors = new HashMap<String, ArrayList<Integer>>();
                                armors.put("armure obscure", null);
                                armors.put("armure", null);
                                armors.put("bouclier", null);
                                armors.put("armure lumineuse", null);
                                armors.put("armure royale", null);
                                armors.put("armure elfique", null);
                                armors.put("tenue en soie", null);
                                armors.put("armure magique", null);
                                armors.put("bouclier reflechissant", null);
                                armors.put("armure de vulcain", null);
                                armors.put("armure aquatique", null);
                                armors.put("armure magenta", null);
                                armors.put("armure de rubis", null);
                                armors.put("bouclier de cristal", null);
                                armors.put("bouclier de bois", null);
                                armors.put("armure de cuire", null);
                                armors.put("armure celeste", null);
                                data.getProfils().get(user.getId()).setArmor(armors);
                            }
                            HashMap<String, Integer> building = new HashMap();
                            building = data.getProfils().get(user.getId()).getBuilding();
                            if (LootBox.test(data.getProfils().get(user)) <= (Integer) building.get("armurerie") * 5 + 20) {
                                int aleaArmors = 1 + (int) (Math.random() * 17.0);
                                String armor = "armure";
                                switch (aleaArmors) {
                                    case 1: {
                                        armor = "armure";
                                        break;
                                    }
                                    case 2: {
                                        armor = "armure obscure";
                                        break;
                                    }
                                    case 3: {
                                        armor = "bouclier";
                                        break;
                                    }
                                    case 4: {
                                        armor = "armure lumineuse";
                                        break;
                                    }
                                    case 5: {
                                        armor = "armure royale";
                                        break;
                                    }
                                    case 6: {
                                        armor = "armure elfique";
                                        break;
                                    }
                                    case 7: {
                                        armor = "tenue en soie";
                                        break;
                                    }
                                    case 8: {
                                        armor = "armure magique";
                                        break;
                                    }
                                    case 9: {
                                        armor = "bouclier reflechissant";
                                        break;
                                    }
                                    case 10: {
                                        armor = "armure de vulcain";
                                        break;
                                    }
                                    case 11: {
                                        armor = "armure aquatique";
                                        break;
                                    }
                                    case 12: {
                                        armor = "armure magenta";
                                        break;
                                    }
                                    case 13: {
                                        armor = "armure de rubis";
                                        break;
                                    }
                                    case 14: {
                                        armor = "bouclier de cristal";
                                        break;
                                    }
                                    case 15: {
                                        armor = "bouclier de bois";
                                        break;
                                    }
                                    case 16: {
                                        armor = "armure de cuire";
                                        break;
                                    }
                                    case 17: {
                                        armor = "armure celeste";
                                        break;
                                    }
                                }
                                int aleaArmor2 = 1;
                                if (lastpallier / 10 == 0 || lastpallier / 10 == 1) {
                                    aleaArmor2 = 1;
                                } else if (lastpallier / 10 == 2 || lastpallier / 10 == 3) {
                                    aleaArmor2 = 2;
                                } else if (lastpallier / 10 == 4 || lastpallier / 10 == 5) {
                                    aleaArmor2 = 3;
                                } else if (lastpallier / 10 == 6 || lastpallier / 10 == 7) {
                                    aleaArmor2 = 4;
                                } else if (lastpallier / 10 == 8 || lastpallier / 10 == 9) {
                                    aleaArmor2 = 5;
                                }
                                ArrayList<Integer> armorList = new ArrayList<Integer>();
                                try {
                                    armorList = (ArrayList<Integer>) armors.get(armor);
                                    armorList.add(aleaArmor2);
                                } catch (NullPointerException e) {
                                    armorList = new ArrayList<Integer>();
                                    armorList.add(aleaArmor2);
                                }
                                try {
                                    armors.put(armor, armorList);
                                } catch (NullPointerException e) {
                                    armors = new HashMap();
                                    armors.put("armure obscure", null);
                                    armors.put("armure", null);
                                    armors.put("bouclier", null);
                                    armors.put("armure lumineuse", null);
                                    armors.put("armure royale", null);
                                    armors.put("armure elfique", null);
                                    armors.put("tenue en soie", null);
                                    armors.put("armure magique", null);
                                    armors.put("bouclier reflechissant", null);
                                    armors.put("armure de vulcain", null);
                                    armors.put("armure aquatique", null);
                                    armors.put("armure magenta", null);
                                    armors.put("armure de rubis", null);
                                    armors.put("bouclier de cristal", null);
                                    armors.put("bouclier de bois", null);
                                    armors.put("armure de cuire", null);
                                    armors.put("armure celeste", null);
                                    armors.put(armor, armorList);
                                }
                                data.getProfils().get(user.getId()).setArmor(armors);
                                if (lang == command.Language.fr) {
                                    channel.sendMessage("Vous venez de franchir le pallier " + lastpallier
                                            + ". Vous avez donc obtenu une armure : " + armor + " de niveau "
                                            + aleaArmor2 + ".").queue();
                                }
                                if (lang == command.Language.en) {
                                    channel.sendMessage("You just cross the step " + lastpallier
                                            + ". So you won an armor : " + armor + " level " + aleaArmor2 + ".")
                                            .queue();
                                }
                            } else {
                                if (lang == command.Language.fr) {
                                    channel.sendMessage("Vous venez de franchir le pallier " + lastpallier
                                            + ". Vous n'avez pas pu obtenir d'arme car votre entrepot est plein.")
                                            .queue();
                                }
                                if (lang == command.Language.en) {
                                    channel.sendMessage("You jsut cross the step " + lastpallier
                                            + ". But you didn't win any armor because your repository is full.")
                                            .queue();
                                }
                            }
                        }
                        if (lastpallier % 10 == 3) {
                            if (lang == command.Language.fr) {
                                channel.sendMessage("Vous venez de franchir le pallier " + lastpallier + ".").queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("You just cross the step " + lastpallier + ".").queue();
                            }
                        }
                        if (lastpallier % 10 == 2) {
                            int bonus = data.getProfils().get(user.getId()).getBonus();
                            data.getProfils().get(user.getId()).setBonus(bonus += 10);
                            if (lang == command.Language.fr) {
                                channel.sendMessage("Vous venez de franchir le pallier " + lastpallier
                                        + ". Vous obtenez donc +10% de bonus. Vous avez un bonus d'xp (d'event) de +"
                                        + bonus + "%.").queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("You just cross the step " + lastpallier
                                        + ". So you won +10% xp bonus. You have an xp bonus (for the event) of +"
                                        + bonus + "%.").queue();
                            }
                        }
                        if (lastpallier % 10 == 4) {
                            int level;
                            int materiau;
                            String res1;
                            HashMap<String, Integer> res = data.getProfils().get(user.getId()).getRes();
                            int bois = res.get("bois");
                            int acier = res.get("argile");
                            int beton = res.get("cuir");
                            int verre = res.get("pierre");
                            int pierre = res.get("paille");
                            int plastique = res.get("fer");
                            int gameXp = data.getProfils().get(user.getId()).getXp();
                            try {
                                double operation = 3 * gameXp / 4;
                                double math = Math.sqrt(operation);
                                level = (int) Math.round(math);
                            } catch (NullPointerException e) {
                                level = 0;
                            }
                            if (lastpallier / 10 < 6) {
                                materiau = 1 + (int) (Math.random() * 6.0);
                                res1 = "";
                                if (materiau == 1) {
                                    res1 = "bois";
                                    bois += 1 * level;
                                } else if (materiau == 2) {
                                    res1 = "argile";
                                    acier += 1 * level;
                                } else if (materiau == 3) {
                                    res1 = "cuir";
                                    beton += 1 * level;
                                } else if (materiau == 4) {
                                    res1 = "pierre";
                                    verre += 1 * level;
                                } else if (materiau == 5) {
                                    res1 = "paille";
                                    pierre += 1 * level;
                                } else if (materiau == 6) {
                                    res1 = "fer";
                                    plastique += 1 * level;
                                }
                                if (lang == command.Language.fr) {
                                    channel.sendMessage("Vous venez de franchir le pallier " + lastpallier
                                            + ". Vous avez gagné " + level + " " + res1 + ".").queue();
                                }
                                if (lang == command.Language.en) {
                                    channel.sendMessage("You just cross the step " + lastpallier + ". You won " + level
                                            + " " + res1 + ".").queue();
                                }
                            } else {
                                materiau = 1 + (int) (Math.random() * 6.0);
                                res1 = "";
                                if (materiau == 1) {
                                    res1 = "bois";
                                    bois += 1 * level;
                                } else if (materiau == 2) {
                                    res1 = "argile";
                                    acier += 1 * level;
                                } else if (materiau == 3) {
                                    res1 = "cuir";
                                    beton += 1 * level;
                                } else if (materiau == 4) {
                                    res1 = "pierre";
                                    verre += 1 * level;
                                } else if (materiau == 5) {
                                    res1 = "paille";
                                    pierre += 1 * level;
                                } else if (materiau == 6) {
                                    res1 = "fer";
                                    plastique += 1 * level;
                                }
                                materiau = 1 + (int) (Math.random() * 6.0);
                                String res2 = "";
                                if (materiau == 1) {
                                    res1 = "bois";
                                    bois += 1 * level;
                                } else if (materiau == 2) {
                                    res1 = "argile";
                                    acier += 1 * level;
                                } else if (materiau == 3) {
                                    res1 = "cuir";
                                    beton += 1 * level;
                                } else if (materiau == 4) {
                                    res1 = "pierre";
                                    verre += 1 * level;
                                } else if (materiau == 5) {
                                    res1 = "paille";
                                    pierre += 1 * level;
                                } else if (materiau == 6) {
                                    res1 = "fer";
                                    plastique += 1 * level;
                                }
                                if (lang == command.Language.fr) {
                                    channel.sendMessage(
                                            "Vous venez de franchir le pallier " + lastpallier + ". Vous avez gagné "
                                                    + level + " " + res1 + " ainsi que " + level + " " + res2 + ".")
                                            .queue();
                                }
                                if (lang == command.Language.en) {
                                    channel.sendMessage("You just cross the step " + lastpallier + ". You won " + level
                                            + " " + res1 + " and " + level + " " + res2 + ".").queue();
                                }
                            }
                        }
                        if (lastpallier % 10 == 6) {
                            if (lang == command.Language.fr) {
                                channel.sendMessage("Vous venez de franchir le pallier " + lastpallier + ".").queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("You just cross the step " + lastpallier + ".").queue();
                            }
                        }
                        if (lastpallier % 10 == 7) {
                            HashMap<String, Integer> building;
                            ArrayList<String> house;
                            ArrayList<String> list2;
                            HashMap<String, ArrayList<String>> houses;
                            if (lastpallier == 7) {
                                building = new HashMap();
                                building = data.getProfils().get(user.getId()).getBuilding();
                                int pets = 0;
                                try {
                                    pets = data.getProfils().get(user.getId()).getPet().size();
                                } catch (Exception acier) {
                                    // empty catch block
                                }
                                if ((Integer) building.get("cirque") >= pets) {
                                    list2 = new ArrayList<String>();
                                    list2.add("Aigle");
                                    list2.add("1");
                                    HashMap<String, ArrayList<String>> pet = data.getProfils().get(user.getId())
                                            .getPet();
                                    try {
                                        pet.put("Aigle", list2);
                                    } catch (Exception e1) {
                                        pet = new HashMap();
                                        pet.put("Aigle", list2);
                                    }
                                    data.getProfils().get(user.getId()).setPet(pet);
                                    if (lang == command.Language.fr) {
                                        channel.sendMessage("Vous venez de franchir le pallier " + lastpallier
                                                + ". Vous avez donc obtenu le pet Aigle.").queue();
                                    }
                                    if (lang == command.Language.en) {
                                        channel.sendMessage("You just cross the step " + lastpallier
                                                + ". So you won the pet Eagle.").queue();
                                    }
                                }
                            } else if (lastpallier == 17) {
                                houses = data.getProfils().get(user.getId()).getHouses();
                                house = new ArrayList<String>();
                                house.add("Plage");
                                house.add("10");
                                houses.put("Plage", house);
                                data.getProfils().get(user.getId()).setHouses(houses);
                                if (lang == command.Language.fr) {
                                    channel.sendMessage("Vous venez de franchir le pallier " + lastpallier
                                            + ". Vous avez donc obtenu la Plage offrant un bonus de 200.000 habitants.")
                                            .queue();
                                }
                                if (lang == command.Language.en) {
                                    channel.sendMessage("You just cross the step " + lastpallier
                                            + ". So you won the Beach which give a bonus of 200k people.").queue();
                                }
                            } else if (lastpallier == 27) {
                                TextFileWriter.write(
                                        "/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/rank.txt",
                                        "\ud83c\udfc4 Summer 2019", 1);
                                if (lang == command.Language.fr) {
                                    channel.sendMessage("Vous venez de franchir le pallier " + lastpallier
                                            + ". Vous avez donc obtenu le rank \"\ud83c\udfc4 Summer 2019\" sur le serveur Ozeryo.")
                                            .queue();
                                }
                                if (lang == command.Language.en) {
                                    channel.sendMessage("You just cross the step " + lastpallier
                                            + ". So you won the rank \"\ud83c\udfc4 Summer 2019\" on the Ozeryo official server.")
                                            .queue();
                                }
                            } else if (lastpallier == 37) {
                                building = new HashMap();
                                building = data.getProfils().get(user.getId()).getBuilding();
                                int pets = 0;
                                try {
                                    pets = data.getProfils().get(user.getId()).getPet().size();
                                } catch (Exception e) {
                                    // empty catch block
                                }
                                if ((Integer) building.get("cirque") >= pets) {
                                    list2 = new ArrayList();
                                    list2.add("Lion");
                                    list2.add("1");
                                    HashMap<String, ArrayList<String>> pet = data.getProfils().get(user.getId())
                                            .getPet();
                                    try {
                                        pet.put("Lion", list2);
                                    } catch (Exception e1) {
                                        pet = new HashMap();
                                        pet.put("Lion", list2);
                                    }
                                    data.getProfils().get(user.getId()).setPet(pet);
                                    if (lang == command.Language.fr) {
                                        channel.sendMessage("Vous venez de franchir le pallier " + lastpallier
                                                + ". Vous avez donc obtenu le pet Lion.").queue();
                                    }
                                    if (lang == command.Language.en) {
                                        channel.sendMessage(
                                                "You just cross the step " + lastpallier + ". So you won the pet Lion.")
                                                .queue();
                                    }
                                }
                            } else if (lastpallier == 47) {
                                houses = data.getProfils().get(user.getId()).getHouses();
                                house = new ArrayList();
                                house.add("Ile Paradisiaque");
                                house.add("10");
                                houses.put("Ile Paradisiaque", house);
                                data.getProfils().get(user.getId()).setHouses(houses);
                                if (lang == command.Language.fr) {
                                    channel.sendMessage("Vous venez de franchir le pallier " + lastpallier
                                            + ". Vous avez donc obtenu l'Ile paradisiaque offrant un bonus de 500k habitants.")
                                            .queue();
                                }
                                if (lang == command.Language.en) {
                                    channel.sendMessage("You just cross the step " + lastpallier
                                            + ". So you won the paradise island which give a bonus of 500k people.")
                                            .queue();
                                }
                            } else if (lastpallier == 57) {
                                User User_Premium = user;
                                if (Premium.Premium(User_Premium)) {
                                    int jours = Integer
                                            .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/"
                                                    + User_Premium.getId() + "/Premium/jours.txt"));
                                    int mois = Integer
                                            .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/"
                                                    + User_Premium.getId() + "/Premium/mois.txt"));
                                    int années = Integer
                                            .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/"
                                                    + User_Premium.getId() + "/Premium/années.txt"));
                                    if (++mois > 12) {
                                        ++années;
                                        mois -= 12;
                                    }

                                    TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/"
                                            + User_Premium.getId() + "/Premium/");
                                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/"
                                            + User_Premium.getId() + "/Premium/jours.txt", Integer.toString(jours), 1);
                                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/"
                                            + User_Premium.getId() + "/Premium/mois.txt", Integer.toString(mois), 1);
                                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/"
                                                    + User_Premium.getId() + "/Premium/années.txt", Integer.toString(années),
                                            1);
                                    if (lang == command.Language.fr) {
                                        channel.sendMessage(String.valueOf(User_Premium.getName())
                                                + " a recu un mois de premium en plus.").queue();
                                    }
                                    if (lang == command.Language.en) {
                                        channel.sendMessage(String.valueOf(User_Premium.getName())
                                                + " receive one Premium month more.").queue();
                                    }
                                } else {
                                    String jours = new SimpleDateFormat("dd", Locale.FRANCE).format(new Date());
                                    String mois = new SimpleDateFormat("MM", Locale.FRANCE).format(new Date());
                                    String années = new SimpleDateFormat("yyyy", Locale.FRANCE).format(new Date());
                                    TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/"
                                            + User_Premium.getId() + "/Premium/");
                                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/"
                                            + User_Premium.getId() + "/Premium/jours.txt", jours, 1);
                                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/"
                                            + User_Premium.getId() + "/Premium/mois.txt", mois, 1);
                                    TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/"
                                            + User_Premium.getId() + "/Premium/années.txt", années, 1);
                                    if (lang == command.Language.fr) {
                                        channel.sendMessage("Vous venez de franchir le pallier " + lastpallier
                                                + ". Vous avez donc obtenu un mois de Premium.").queue();
                                    }
                                    if (lang == command.Language.en) {
                                        channel.sendMessage("You just cross the step " + lastpallier
                                                + ". So you won one Premium month.").queue();
                                    }
                                }
                            } else if (lastpallier == 77) {
                                building = new HashMap();
                                building = data.getProfils().get(user.getId()).getBuilding();
                                int pets = 0;
                                try {
                                    pets = data.getProfils().get(user.getId()).getPet().size();
                                } catch (Exception mois) {
                                    // empty catch block
                                }
                                if ((Integer) building.get("cirque") >= pets) {
                                    list2 = new ArrayList();
                                    list2.add("Lievre");
                                    list2.add("1");
                                    HashMap<String, ArrayList<String>> pet = data.getProfils().get(user.getId())
                                            .getPet();
                                    try {
                                        pet.put("Lievre", list2);
                                    } catch (Exception e1) {
                                        pet = new HashMap();
                                        pet.put("Lievre", list2);
                                    }
                                    data.getProfils().get(user.getId()).setPet(pet);
                                    if (lang == command.Language.fr) {
                                        channel.sendMessage("Vous venez de franchir le pallier " + lastpallier
                                                + ". Vous avez donc obtenu le pet Lievre.").queue();
                                    }
                                    if (lang == command.Language.en) {
                                        channel.sendMessage(
                                                "You just cross the step " + lastpallier + ". So you won the pet Hare.")
                                                .queue();
                                    }
                                }
                            } else if (lastpallier == 87) {
                                houses = data.getProfils().get(user.getId()).getHouses();
                                house = new ArrayList();
                                house.add("Piscine");
                                house.add("10");
                                houses.put("Piscine", house);
                                data.getProfils().get(user.getId()).setHouses(houses);
                                if (lang == command.Language.fr) {
                                    channel.sendMessage("Vous venez de franchir le pallier " + lastpallier
                                            + ". Vous avez donc obtenu la Piscine offrant un bonus de 1M habitants.")
                                            .queue();
                                }
                                if (lang == command.Language.en) {
                                    channel.sendMessage("You just cross the step " + lastpallier
                                            + ". So you won the Swimming Pool wich give a bonus of 1M people.").queue();
                                }
                            } else if (lastpallier == 97) {
                                int card;
                                int level1;
                                ArrayList<String> hero1;
                                int pv;
                                HashMap<String, ArrayList<String>> heroe = data.getProfils().get(user.getId())
                                        .getHeroe();
                                String hero = "Ivoire";
                                try {
                                    hero1 = heroe.get(hero);
                                } catch (NullPointerException e) {
                                    hero1 = new ArrayList();
                                    hero1.add("1");
                                    hero1.add("0");
                                    hero1.add("false");
                                    hero1.add("0");
                                }
                                try {
                                    level1 = Integer.parseInt(hero1.get(0));
                                } catch (NullPointerException e) {
                                    level1 = 0;
                                }
                                try {
                                    card = Integer.parseInt(hero1.get(1));
                                } catch (NullPointerException e) {
                                    card = 0;
                                }
                                try {
                                    pv = Integer.parseInt(hero1.get(3));
                                } catch (NullPointerException e) {
                                    pv = 0;
                                }
                                if (level1 == 0) {
                                    level1 = 1;
                                }
                                ++card;
                                try {
                                    hero1.clear();
                                    hero1.add(Integer.toString(level1));
                                    hero1.add(Integer.toString(card));
                                    hero1.add("false");
                                    hero1.add(Integer.toString(pv));
                                    hero1.add(Long.toString(System.currentTimeMillis()));
                                } catch (NullPointerException e) {
                                    hero1 = new ArrayList();
                                    hero1.add(Integer.toString(level1));
                                    hero1.add(Integer.toString(card));
                                    hero1.add("false");
                                    hero1.add(Integer.toString(pv));
                                    hero1.add("" + System.currentTimeMillis());
                                }
                                try {
                                    heroe.put(hero, hero1);
                                } catch (NullPointerException e) {
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
                                    heroe.put(hero, hero1);
                                }
                                data.getProfils().get(user.getId()).setHeroe(heroe);
                                if (lang == command.Language.fr) {
                                    channel.sendMessage("Vous venez de franchir le pallier " + lastpallier
                                            + ". Vous obtenez donc une carte du Hero légendaire Ivoire").queue();
                                }
                                if (lang == command.Language.en) {
                                    channel.sendMessage("You just cross the step " + lastpallier
                                            + ". So you get a legendary card of the hero Ivoire").queue();
                                }
                            }
                        }
                        if (lastpallier % 10 == 8) {
                            int jetons = data.getProfils().get(user.getId()).getTokens();
                            data.getProfils().get(user.getId()).setTokens(jetons += 15);
                            if (lang == command.Language.fr) {
                                channel.sendMessage("Vous venez de franchir le pallier " + lastpallier
                                        + ". Vous obtenez donc 15 jetons").queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage(
                                        "You just cross the stepr " + lastpallier + ". So you won 15 tokens").queue();
                            }
                        }
                        if (lastpallier % 10 == 9) {
                            if (lang == command.Language.fr) {
                                channel.sendMessage("Vous venez de franchir le pallier " + lastpallier + ".").queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("You just cross the step " + lastpallier + ".").queue();
                            }
                        }
                        if (lastpallier % 10 == 0) {
                            int nblb = lastpallier / 10;
                            int lb = data.getProfils().get(user.getId()).getLootbox();
                            data.getProfils().get(user.getId()).setLootbox(lb += nblb);
                            if (lang == command.Language.fr) {
                                channel.sendMessage("Vous venez de franchir le pallier " + lastpallier
                                        + ". Vous obtenez donc " + nblb + " lootbox.").queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage(
                                        "You just cross the step " + lastpallier + ". So you won " + nblb + " lootbox.")
                                        .queue();
                            }
                        }
                        ++lastpallier;
                    } while (true);
                }
            }


            // Regen Hero

            String activeHero = data.getProfils().get(user.getId()).getActiveHeroe();
            Heroe.getPV(activeHero, data.getProfils().get(user.getId()));


            // Set Name

            data.getProfils().get(user.getId()).setName(user.getName());

            // Cooldown


            if (!(CoolDown1 = CoolDown.CoolDown(user)) && ((SimpleCommand) object[0]).getMethod().getName() != "cf") {
                channel.sendMessage(":x: CoolDown (1sec) !! :x: ").queue();
                System.out.println(" La commande " + ((SimpleCommand) object[0]).getMethod().getName()
                        + " a voulu etre utilisé par " + user.getName() + " trop rapidement");
                return true;
            }
            CommandMap.execute((SimpleCommand) object[0], command2, (String[]) object[1], message, channel, guild,
                    user, jda);
            System.out.println(" La commande " + ((SimpleCommand) object[0]).getMethod().getName()
                    + " a été utilisé par " + user.getName() + " sur le serveur " + guild.getName());
            long temps_de_jeu = data.getProfils().get(user.getId()).getTemps_de_jeu();
            long lastcommand = data.getProfils().get(user.getId()).getLastCommand();
            long delay = System.currentTimeMillis() - lastcommand;
            temps_de_jeu = delay > 60000L ? (temps_de_jeu += 60000L) : (temps_de_jeu += delay);
            data.getProfils().get(user.getId()).setTemps_de_jeu(temps_de_jeu);
            data.getProfils().get(user.getId()).setCommands(data.getProfils().get(user.getId()).getCommands() + 1);
            data.getProfils().get(user.getId()).setLastCommand(System.currentTimeMillis());
            ArrayList<ArrayList<String>> mails = data.getProfils().get(user.getId()).getListMail();
            int mail1 = 0;
            try {
                for (ArrayList<String> mail2 : mails) {
                    String objet = mail2.get(0);
                    boolean read = mail2.get(2).equals("true");
                    if (read)
                        continue;
                    ++mail1;
                }
            } catch (Exception mail2) {
                // empty catch block
            }
            command.Language lang = data.getProfils().get(user.getId()).getLanguage();
            if (mail1 > 0) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous avez des mails non lu. ``=mail list`` pour les voir.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You have unread messages. type ``=mail list`` to see them.").queue();
                }
            }
            Guild ozeryo = DiscordBot.getjda().getGuildById("326345972739473410");
            TextChannel logChannel = ozeryo.getTextChannelById("498483581052649492");
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTimestamp(Instant.now());
            builder.setAuthor("OzeryoBot");
            builder.setColor(color.couleurAleatoire(user));
            builder.addField("User", String.valueOf(user.getName()) + " (``" + user.getId() + "``)", true);
            builder.addField("Guild", String.valueOf(guild.getName()) + " (``" + guild.getId() + "``)", true);
            builder.addBlankField(true);
            builder.addField("Commande", ((SimpleCommand) object[0]).getMethod().getName(), true);
            long t2 = System.currentTimeMillis();
            builder.addField("Ping", String.valueOf(t2 - t1), true);
            command2 = command2.replace(((SimpleCommand) object[0]).getMethod().getName(), "");
            if (command2.length() < 1000) {
                builder.addField(" Param\u00e8tres ", command2, true);
            } else {
                builder.addField(" Param\u00e8tres ", "trop de param\u00e8tres", true);
            }
            logChannel.sendMessage(builder.build()).queue();
            return true;
        } catch (Exception exception) {
            if (DiscordBot.getData().getProfils().get(user.getId()).getLanguage() == command.Language.fr) {
                channel.sendMessage(
                        ":warning: Une erreur est survenue ! Signalez la \u00e0 un admin tel que Ybsi#0451 (102108573298851840) ou alors faites un rapport de bug avec le =report. Nous nous excusons de la g\u00e8ne occasionnée et vous remercions de votre aide.")
                        .queue();
            }
            if (DiscordBot.getData().getProfils().get(user.getId()).getLanguage() == command.Language.en) {
                channel.sendMessage(
                        "An error occurred! Report it to an admin such as Ybsi#0451 (102108573298851840) or make a bug report with the command =report. We apologize for the inconvenience.")
                        .queue();
            }
            System.out.println("La methode " + ((SimpleCommand) object[0]).getMethod().getName()
                    + " n'est pas correctement initialis\ufffd." + exception.getLocalizedMessage() + " "
                    + exception.toString() + " " + exception.getCause() + " " + exception.getMessage() + " ");
            /*try {
                mail.main("Error command " + ((SimpleCommand) object[0]).getMethod().getName(),
                        "La commande " + ((SimpleCommand) object[0]).getMethod().getName()
                                + " n'est pas correctement initilaisé. \n" + "User : " + user.getName() + " ("
                                + user.getId() + ") \n" + "Guild : " + guild.getName() + " (" + guild.getName() + ") \n"
                                + "Param\u00e8tres : " + command2 + "\n" + "Erreur : " + exception.getCause());
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            exception.printStackTrace();
            Guild ozeryo = DiscordBot.getjda().getGuildById("326345972739473410");
            TextChannel logChannel = ozeryo.getTextChannelById("498483581052649492");
            logChannel.sendMessage(
                    "La commande " + command2 + " utilisé par " + user.getName() + " sur le serveur " + guild.getName()
                            + " n'est pas correctement initialisé : \n\n " + exception.getStackTrace().toString())
                    .queue();
        }
        return true;
    }

    public static void Log(String topic, String error, JDA jda) {
        Guild ozeryo = jda.getGuildById("326345972739473410");
        TextChannel logChannel = ozeryo.getTextChannelById("774934424894177310");
        logChannel.sendMessage("Il y a eu une erreur dans : " + topic + "\n " + error).queue();
    }

    public static void PublicLog(String message, JDA jda) {
        Guild ozeryo = jda.getGuildById("326345972739473410");
        TextChannel logChannel = ozeryo.getTextChannelById("774934286272036885");
        logChannel.sendMessage(message).queue();
    }

    private static Object[] getCommand(String command2) {
        String[] commandSplit = command2.split(" ");
        String[] args = new String[commandSplit.length - 1];
        for (int i = 1; i < commandSplit.length; ++i) {
            args[i - 1] = commandSplit[i];
        }
        SimpleCommand simpleCommand = commands.get(commandSplit[0]);
        return new Object[]{simpleCommand, args};
    }

    private static void execute(final SimpleCommand simpleCommand, final String command2, final String[] args,
                                final Message message, final MessageChannel channel, final Guild guild, final User user, JDA jda) throws Exception {
        Runnable Uptime_Update = new Runnable() {

            @Override
            public void run() {
                Parameter[] parameters = simpleCommand.getMethod().getParameters();
                Object[] objects = new Object[parameters.length];
                for (int i = 0; i < parameters.length; ++i) {
                    if (parameters[i].getType() == String[].class) {
                        objects[i] = args;
                        continue;
                    }
                    if (parameters[i].getType() == User.class) {
                        objects[i] = message == null ? null : message.getAuthor();
                        continue;
                    }
                    if (parameters[i].getType() == TextChannel.class) {
                        objects[i] = message == null ? null : message.getTextChannel();
                        continue;
                    }
                    if (parameters[i].getType() == PrivateChannel.class) {
                        objects[i] = message == null ? null : message.getPrivateChannel();
                        continue;
                    }
                    if (parameters[i].getType() == Guild.class) {
                        objects[i] = message == null ? null : message.getGuild();
                        continue;
                    }
                    if (parameters[i].getType() == String.class) {
                        objects[i] = command2;
                        continue;
                    }
                    if (parameters[i].getType() == Message.class) {
                        objects[i] = message;
                        continue;
                    }
                    if (parameters[i].getType() == JDA.class) {
                        objects[i] = jda;
                        continue;
                    }
                    if (parameters[i].getType() == ProfilData.class) {
                        objects[i] = DiscordBot.getData();
                        continue;
                    }
                    if (parameters[i].getType() == command.Language.class) {
                        objects[i] = DiscordBot.getData().getProfils().get(user.getId()).getLanguage();
                        continue;
                    }
                    if (parameters[i].getType() != MessageChannel.class)
                        continue;
                    objects[i] = message == null ? null : message.getChannel();
                }
                try {
                    simpleCommand.getMethod().invoke(simpleCommand.getObject(), objects);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                    if (DiscordBot.getData().getProfils().get(user.getId()).getLanguage() == command.Language.fr) {
                        channel.sendMessage(
                                ":warning: Une erreur est survenue ! Signalez la \u00e0 un admin tel que Ybsi#0451 (102108573298851840) ou alors faites un rapport de bug avec le =report. Nous nous excusons de la g\u00e8ne occasionnée et vous remercions de votre aide.")
                                .queue();
                    }
                    if (DiscordBot.getData().getProfils().get(user.getId()).getLanguage() == command.Language.en) {
                        channel.sendMessage(
                                "An error occurred! Report it to an admin such as Ybsi#0451 (102108573298851840) or make a bug report with the command =report. We apologize for the inconvenience.")
                                .queue();
                    }
                    System.out.println("La methode " + command2 + " n'est pas correctement initialis\ufffd."
                            + e.getLocalizedMessage() + " " + e.toString() + " " + e.getCause() + " " + e.getMessage()
                            + " ");
                    /*try {
                        mail.main("Error command " + command2,
                                "La commande " + command2 + " n'est pas correctement initilaisé. \n" + "User : "
                                        + user.getName() + " (" + user.getId() + ") \n" + "Guild : " + guild.getName()
                                        + " (" + guild.getName() + ") \n" + "Param\u00e8tres : " + command2 + "\n"
                                        + "Erreur : " + e.getCause());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }*/
                    e.printStackTrace();
                    Guild ozeryo = DiscordBot.getjda().getGuildById("326345972739473410");
                    TextChannel logChannel = ozeryo.getTextChannelById("498483581052649492");
                    logChannel
                            .sendMessage("La commande " + command2 + " utilisé par " + user.getName()
                                    + " sur le serveur " + guild.getName()
                                    + " n'est pas correctement initialisé : \n\n " + e.getStackTrace().toString())
                            .queue();
                }
            }
        };
        scheduler1.execute(Uptime_Update);
    }

    public static void NewPlayer(User user) {
        ProfilData data = DiscordBot.getData();
        HashMap<String, Integer> building = new HashMap<String, Integer>();
        building.put("marché", 0);
        building.put("habitations", 0);
        building.put("auberge", 0);
        building.put("camp d'entrainement", 0);
        building.put("cirque", 0);
        building.put("tour de sorcier", 0);
        building.put("mine", 0);
        building.put("eglise", 0);
        building.put("muraille", 0);
        building.put("biblioth\u00e8que", 0);
        building.put("transport", 0);
        building.put("armurerie", 0);
        building.put("forge", 0);
        building.put("centrale nucleaire", 0);
        data.getProfils().get(user.getId()).setBuilding(building);
        HashMap<String, Integer> res = new HashMap<String, Integer>();
        res.put("bois", 0);
        res.put("argile", 0);
        res.put("cuir", 0);
        res.put("paille", 0);
        res.put("pierre", 0);
        res.put("fer", 0);
        res.put("cristal", 0);
        data.getProfils().get(user.getId()).setRes(res);
        HashMap<String, Long> map = new HashMap<String, Long>();
        map.put("Travailleur", 0L);
        map.put("Harvester I", 0L);
        map.put("Harvester II", 0L);
        map.put("Harvester III", 0L);
        map.put("Entrepreneur I", 0L);
        map.put("Entrepreneur II", 0L);
        map.put("Entrepreneur III", 0L);
        map.put("Conquérant I", 0L);
        map.put("Conquérant II", 0L);
        map.put("Conquérant III", 0L);
        map.put("Défenseur I", 0L);
        map.put("Défenseur II", 0L);
        map.put("Défenseur III", 0L);
        map.put("Espion I", 0L);
        map.put("Espion II", 0L);
        map.put("Espion III", 0L);
        map.put("Farmer I", 0L);
        map.put("Farmer II", 0L);
        map.put("Farmer III", 0L);
        map.put("Instructeur", 0L);
        map.put("Zoologue I", 0L);
        map.put("Zoologue II", 0L);
        map.put("Zoologue III", 0L);
        map.put("Parieur", 0L);
        map.put("Thx dude I", 0L);
        map.put("Thx dude II", 0L);
        map.put("Thx dude III", 0L);
        map.put("Braquer I", 0L);
        map.put("Braquer II", 0L);
        map.put("Braquer III", 0L);
        map.put("Crime en série", 0L);
        map.put("Parrain de la mafia", 0L);
        map.put("Toujours présent", 0L);
        map.put("Toujours debout", 0L);
        map.put("Fou des jeux", 0L);
        map.put("Croyant", 0L);
        map.put("L", 0L);
        map.put("Investisseur I", 0L);
        map.put("Investisseur II", 0L);
        map.put("Investisseur III", 0L);
        map.put("Mineur confirme", 0L);
        map.put("Avant poste", 0L);
        map.put("Pigeon", 0L);
        map.put("Famous", 0L);
        map.put("Scientifique", 0L);
        map.put("Megalopole", 0L);
        map.put("Ville touristique", 0L);
        map.put("Magicien", 0L);
        map.put("What a luck", 0L);
        map.put("A good player", 0L);
        map.put("Ville attractive", 0L);
        map.put("Useless", 0L);
        map.put("Impliqué", 0L);
        map.put("Starter", 0L);
        map.put("Grinder I", 0L);
        map.put("Grinder II", 0L);
        map.put("Grinder III", 0L);
        map.put("You'r special", 0L);
        map.put("Catch them all", 0L);
        map.put("SwordMaster", 0L);
        map.put("Armorer", 0L);
        map.put("Collector I", 0L);
        map.put("Collector II", 0L);
        map.put("Collector III", 0L);
        map.put("Melting", 0L);
        data.getProfils().get(user.getId()).setAchievement(map);
        HashMap<String, ArrayList<String>> heroe = new HashMap<String, ArrayList<String>>();
        ArrayList<String> list = new ArrayList<String>();
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
        data.getProfils().get(user.getId()).setHeroe(heroe);
        HashMap<String, ArrayList<Integer>> weapons = new HashMap<String, ArrayList<Integer>>();
        weapons.put("epee", null);
        weapons.put("spectre", null);
        weapons.put("arc", null);
        weapons.put("arbalete", null);
        weapons.put("lance", null);
        weapons.put("pelle de combat", null);
        weapons.put("sarbacanne", null);
        weapons.put("gourdin", null);
        weapons.put("flechettes", null);
        weapons.put("trident", null);
        weapons.put("fleau", null);
        weapons.put("fouet", null);
        weapons.put("baton", null);
        weapons.put("fourche", null);
        weapons.put("dague", null);
        weapons.put("shuriken", null);
        weapons.put("katana", null);
        data.getProfils().get(user.getId()).setWeapons(weapons);
        HashMap<String, ArrayList<Integer>> armor = new HashMap<String, ArrayList<Integer>>();
        armor.put("armure obscure", null);
        armor.put("armure", null);
        armor.put("bouclier", null);
        armor.put("armure lumineuse", null);
        armor.put("armure royale", null);
        armor.put("armure elfique", null);
        armor.put("tenue en soie", null);
        armor.put("armure magique", null);
        armor.put("bouclier reflechissant", null);
        armor.put("armure de vulcain", null);
        armor.put("armure aquatique", null);
        armor.put("armure magenta", null);
        armor.put("armure de rubis", null);
        armor.put("bouclier de cristal", null);
        armor.put("bouclier de bois", null);
        armor.put("armure de cuire", null);
        armor.put("armure celeste", null);
        data.getProfils().get(user.getId()).setArmor(armor);
        data.getProfils().get(user.getId()).setMoney(0L);
        data.getProfils().get(user.getId()).setSoldiers(0L);
        data.getProfils().get(user.getId()).setXp(0);
        data.getProfils().get(user.getId()).setIdh(0);
        data.getProfils().get(user.getId()).setTrophy(0);
        data.getProfils().get(user.getId()).setCf(0);
        data.getProfils().get(user.getId()).setLastTrain(0L);
        data.getProfils().get(user.getId()).setTrain(0L);
        data.getProfils().get(user.getId()).setDescription("Joueur OzeryoBot");
        data.getProfils().get(user.getId()).setVote(0);
        data.getProfils().get(user.getId()).setAp(0);
        data.getProfils().get(user.getId()).setLastDaily(0L);
        data.getProfils().get(user.getId()).setLastHourly(0L);
        data.getProfils().get(user.getId()).setHabitants(0L);
        data.getProfils().get(user.getId()).setMana(0);
        data.getProfils().get(user.getId()).setLastMana(0L);
        data.getProfils().get(user.getId()).setLastPerte(0L);
        data.getProfils().get(user.getId()).setTokens(0);
        data.getProfils().get(user.getId()).setLootbox(0);
        data.getProfils().get(user.getId()).setMail(true);
        data.getProfils().get(user.getId()).setCountry("");
        data.getProfils().get(user.getId()).setName(user.getName());
        data.getProfils().get(user.getId()).setIconURL(user.getAvatarUrl());
        data.getProfils().get(user.getId()).setInscription(System.currentTimeMillis());
    }

    static /* synthetic */ DiscordBot access$0() {
        return DiscordBot;
    }

    public int getPowerUser(Guild guild, User user) {
        if (guild.getMember(user).hasPermission(Permission.ADMINISTRATOR)) {
            return 150;
        }
        return this.powers.containsKey(user.getIdLong()) ? this.powers.get(user.getIdLong()) : 0;
    }

    public String getPrefix(Guild guild) {
        String pref = TextFileWriter.read("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/prefix.txt");
        prefix = pref.equals("0") ? "=" : pref;
        return prefix;
    }

    public String getTag() {
        return tag;
    }

    public String getTag2() {
        return tag2;
    }

    public Collection<SimpleCommand> getCommands() {
        return commands.values();
    }

    public Collection<SimpleCommand> getCommands2() {
        return commands2.values();
    }

    public void registerCommands(Object... objects) {
        for (Object object : objects) {
            this.registerCommand(object);
        }
    }

    public void registerCommand(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(command.class))
                continue;
            command command2 = method.getAnnotation(command.class);
            method.setAccessible(true);
            SimpleCommand simpleCommand = new SimpleCommand(command2.name(), command2.abbrev(), command2.topic(),
                    command2.descfr(), command2.descen(), command2.type(), object, method, command2.power(),
                    command2.use());
            commands.put(command2.name(), simpleCommand);
            commands2.put(command2.name(), simpleCommand);
            commands.put(command2.abbrev(), simpleCommand);
        }
    }

    public void commandConsole(String command2) {
        Object[] object = CommandMap.getCommand(command2);
        if (object[0] == null || ((SimpleCommand) object[0]).getExecutorType() == command.ExecutorType.USER) {
            System.out.println("Commande inconnue.");
            return;
        }
        try {
            CommandMap.execute((SimpleCommand) object[0], command2, (String[]) object[1], null, null, null, null, null);
        } catch (Exception e) {
            System.out.println("La methode " + ((SimpleCommand) object[0]).getMethod().getName()
                    + " n'est pas correctement initialis\ufffd." + e.getMessage());
            e.printStackTrace();
        }
    }

}
