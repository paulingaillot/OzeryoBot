/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Utils.Premium;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.HashMap;

public class LootBox {
    public static int test(Profil user) {
        HashMap<String, ArrayList<Integer>> armor;
        HashMap<String, ArrayList<Integer>> weapons;
        try {
            weapons = user.getWeapons();
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
            user.setWeapons(weapons);
        }
        int quantité = 0;
        try {
            quantité += weapons.get("epee").size();
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        try {
            quantité += weapons.get("spectre").size();
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        try {
            quantité += weapons.get("arc").size();
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        try {
            quantité += weapons.get("arbalete").size();
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        try {
            quantité += weapons.get("lance").size();
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        try {
            quantité += weapons.get("pelle de combat").size();
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        try {
            quantité += weapons.get("sarbacanne").size();
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        try {
            quantité += weapons.get("gourdin").size();
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        try {
            quantité += weapons.get("flechettes").size();
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        try {
            quantité += weapons.get("trident").size();
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        try {
            quantité += weapons.get("fleau").size();
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        try {
            quantité += weapons.get("fouet").size();
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        try {
            quantité += weapons.get("baton").size();
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        try {
            quantité += weapons.get("fourche").size();
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        try {
            quantité += weapons.get("dague").size();
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        try {
            quantité += weapons.get("shuriken").size();
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        try {
            quantité += weapons.get("katana").size();
        } catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        try {
            armor = user.getArmor();
        } catch (NullPointerException e) {
            armor = new HashMap<String, ArrayList<Integer>>();
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
            user.setArmor(armor);
        }
        try {
            quantité += armor.get("armure obscure").size();
        } catch (NullPointerException e) {
            // empty catch block
        }
        try {
            quantité += armor.get("armure").size();
        } catch (NullPointerException e) {
            // empty catch block
        }
        try {
            quantité += armor.get("bouclier").size();
        } catch (NullPointerException e) {
            // empty catch block
        }
        try {
            quantité += armor.get("armure lumineuse").size();
        } catch (NullPointerException e) {
            // empty catch block
        }
        try {
            quantité += armor.get("armure royale").size();
        } catch (NullPointerException e) {
            // empty catch block
        }
        try {
            quantité += armor.get("armure elfique").size();
        } catch (NullPointerException e) {
            // empty catch block
        }
        try {
            quantité += armor.get("tenue en soie").size();
        } catch (NullPointerException e) {
            // empty catch block
        }
        try {
            quantité += armor.get("armure magique").size();
        } catch (NullPointerException e) {
            // empty catch block
        }
        try {
            quantité += armor.get("bouclier reflechissant").size();
        } catch (NullPointerException e) {
            // empty catch block
        }
        try {
            quantité += armor.get("armure de vulcain").size();
        } catch (NullPointerException e) {
            // empty catch block
        }
        try {
            quantité += armor.get("armure aquatique").size();
        } catch (NullPointerException e) {
            // empty catch block
        }
        try {
            quantité += armor.get("armure magenta").size();
        } catch (NullPointerException e) {
            // empty catch block
        }
        try {
            quantité += armor.get("armure de rubis").size();
        } catch (NullPointerException e) {
            // empty catch block
        }
        try {
            quantité += armor.get("bouclier de cristal").size();
        } catch (NullPointerException e) {
            // empty catch block
        }
        try {
            quantité += armor.get("bouclier de bois").size();
        } catch (NullPointerException e) {
            // empty catch block
        }
        try {
            quantité += armor.get("armure de cuire").size();
        } catch (NullPointerException e) {
            // empty catch block
        }
        try {
            quantité += armor.get("armure celeste").size();
        } catch (NullPointerException e) {
            // empty catch block
        }
        return quantité;
    }

    /*
     * Enabled force condition propagation Lifted jumps to return sites
     */
    @command(name = "lootbox", abbrev = "lb", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Game)
    private void lootbox(Message message, User user, String[] args, MessageChannel channel, Guild guild,
                         ProfilData data, command.Language lang) {
        String c2;
        String c1;
        try {
            c1 = args[0];
        } catch (IndexOutOfBoundsException e) {
            c1 = "";
        }
        if (c1.equals("")) {
            int lb = data.getProfils().get(user.getId()).getLootbox();
            if (lang == command.Language.fr) {
                channel.sendMessage(
                        "Vous avez actuellement " + lb + " lootbox. Faites ``=lb open`` pour ouvrir une lootbox.")
                        .queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("You actually have " + lb + " lootbox. Type ``=lb open`` to open a lootbox.").queue();
            return;
        }
        if (c1.equals("open")) {
            int alea5;
            int alea1;
            int alea13;
            HashMap<String, ArrayList<String>> heroe;
            int alea9;
            int level;
            int alea11;
            int alea6;
            int alea3;
            int alea7;
            int alea8;
            int alea12;
            int lb = data.getProfils().get(user.getId()).getLootbox();
            if (lb <= 0) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Désolé mais vous n'avez plus de lootbox a ouvrir").queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("Sorry but you don't have any lootbox to open").queue();
                return;
            }
            data.getProfils().get(user.getId()).setLootbox(--lb);
            int Game_EXP = data.getProfils().get(user.getId()).getXp();
            HashMap<String, Integer> building = data.getProfils().get(user.getId()).getBuilding();
            int struct = building.get("marché");
            int levelEcole = building.get("biblioth\u00e8que");
            int levelMine = building.get("mine");
            double bonus = Structure.entreprise(struct);
            try {
                double math = Math.sqrt(Game_EXP);
                level = (int) Math.round(math);
            } catch (NullPointerException e) {
                level = 0;
            }
            String mess = "";
            if ((alea1 = 1 + (int) (Math.random() * 3.0)) == 1) {
                long money = data.getProfils().get(user.getId()).getMoney();
                int Alea_money = 25 + (int) (Math.random() * 76.0);
                int money_win2 = (int) ((double) Alea_money * (100.0 + (double) (10 * level) * bonus));
                data.getProfils().get(user.getId()).setMoney(money += money_win2);
                mess = mess + "- " + money_win2 + " money \n";
            }
            if ((alea11 = 1 + (int) (Math.random() * 3.0)) == 1) {
                long pop = data.getProfils().get(user.getId()).getHabitants();
                int Alea_pop = 25 + (int) (Math.random() * 76.0);
                int popWin = (int) ((double) Alea_pop * (10.0 + 2.5 * (double) (10 * (levelEcole + 1) * level)));
                data.getProfils().get(user.getId()).setHabitants(pop += popWin);
                if (lang == command.Language.fr) {
                    mess = mess + "- " + popWin + " habitants \n";
                }
                if (lang == command.Language.en) {
                    mess = mess + "- " + popWin + " people \n";
                }
            }
            HashMap<String, Integer> res = data.getProfils().get(user.getId()).getRes();
            int min = 25 + (int) (2.5 * (double) levelMine);
            int max = 100 + 10 * levelMine;
            int Alea_bois = 25 + (int) (Math.random() * (double) (max - min + 1));
            int Alea_acier = 25 + (int) (Math.random() * (double) (max - min + 1));
            int Alea_brique = 25 + (int) (Math.random() * (double) (max - min + 1));
            int Alea_pierre = 25 + (int) (Math.random() * (double) (max - min + 1));
            int Alea_paille = 25 + (int) (Math.random() * (double) (max - min + 1));
            int Alea_fer = 25 + (int) (Math.random() * (double) (max - min + 1));
            int bois = res.get("bois");
            int acier = res.get("argile");
            int brique = res.get("cuir");
            int pierre = res.get("pierre");
            int paille = res.get("paille");
            int fer = res.get("fer");
            int alea4 = 1 + (int) (Math.random() * 3.0);
            if (alea4 == 1) {
                bois += Alea_bois;
                if (lang == command.Language.fr) {
                    mess = mess + "- " + Alea_bois + " bois \n";
                }
                if (lang == command.Language.en) {
                    mess = mess + "- " + Alea_bois + " wood \n";
                }
            }
            if ((alea5 = 1 + (int) (Math.random() * 3.0)) == 1) {
                acier += Alea_acier;
                if (lang == command.Language.fr) {
                    mess = mess + "- " + Alea_acier + " argile \n";
                }
                if (lang == command.Language.en) {
                    mess = mess + "- " + Alea_acier + " clay \n";
                }
            }
            if ((alea6 = 1 + (int) (Math.random() * 3.0)) == 1) {
                brique += Alea_brique;
                if (lang == command.Language.fr) {
                    mess = mess + "- " + Alea_brique + " cuir \n";
                }
                if (lang == command.Language.en) {
                    mess = mess + "- " + Alea_brique + " leather \n";
                }
            }
            if ((alea7 = 1 + (int) (Math.random() * 3.0)) == 1) {
                pierre += Alea_pierre;
                if (lang == command.Language.fr) {
                    mess = mess + "- " + Alea_pierre + " pierre \n";
                }
                if (lang == command.Language.en) {
                    mess = mess + "- " + Alea_pierre + " stone \n";
                }
            }
            if ((alea8 = 1 + (int) (Math.random() * 3.0)) == 1) {
                pierre += Alea_pierre;
                if (lang == command.Language.fr) {
                    mess = mess + "- " + Alea_pierre + " paille \n";
                }
                if (lang == command.Language.en) {
                    mess = mess + "- " + Alea_pierre + " straw \n";
                }
            }
            if ((alea9 = 1 + (int) (Math.random() * 3.0)) == 1) {
                fer += Alea_fer;
                if (lang == command.Language.fr) {
                    mess = mess + "- " + Alea_fer + " fer \n";
                }
                if (lang == command.Language.en) {
                    mess = mess + "- " + Alea_fer + " iron \n";
                }
            }
            res.put("bois", bois);
            res.put("argile", acier);
            res.put("cuir", brique);
            res.put("pierre", pierre);
            res.put("paille", paille);
            res.put("fer", fer);
            data.getProfils().get(user.getId()).setRes(res);
            try {
                heroe = data.getProfils().get(user.getId()).getHeroe();
            } catch (NullPointerException e) {
                heroe = new HashMap();
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
            }
            int alea10 = 1 + (int) (Math.random() * 3.0);
            if (alea10 == 1) {
                int level1;
                ArrayList<String> hero1;
                int alea2;
                int card;
                int pv;
                String hero = null;
                double alea_hero = Math.random();
                if (alea_hero <= 0.5) {
                    hero = "Karl";
                } else if (alea_hero > 0.5 && alea_hero <= 0.75) {
                    alea2 = 1 + (int) (Math.random() * 4.0);
                    if (alea2 == 1) {
                        hero = "Valkyrie";
                    }
                    if (alea2 == 2) {
                        hero = "Ouranos";
                    }
                    if (alea2 == 3) {
                        hero = "Oeil";
                    }
                    if (alea2 == 4) {
                        hero = "Ikaryus";
                    }
                } else if (alea_hero > 0.75 && alea_hero <= 0.9) {
                    alea2 = 1 + (int) (Math.random() * 6.0);
                    if (alea2 == 1) {
                        hero = "Yegarde";
                    }
                    if (alea2 == 2) {
                        hero = "Angel";
                    }
                    if (alea2 == 3) {
                        hero = "Zhen";
                    }
                    if (alea2 == 4) {
                        hero = "Hearth";
                    }
                    if (alea2 == 5) {
                        hero = "Lixie";
                    }
                    if (alea2 == 6) {
                        hero = "Akashi";
                    }
                } else if (alea_hero > 0.9 && alea_hero <= 0.99) {
                    alea2 = 1 + (int) (Math.random() * 4.0);
                    if (alea2 == 1) {
                        hero = "Rose";
                    }
                    if (alea2 == 2) {
                        hero = "Hell";
                    }
                    if (alea2 == 3) {
                        hero = "Spirita";
                    }
                    if (alea2 == 4) {
                        hero = "Tempest";
                    }
                } else if (alea_hero > 0.99) {
                    hero = "Ivoire";
                }
                try {
                    hero1 = heroe.get(hero);
                } catch (NullPointerException e) {
                    hero1 = new ArrayList<String>();
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
                    pv = Heroe.getPV(hero, data.getProfils().get(user.getId()));
                } catch (NullPointerException e) {
                    pv = 0;
                }
                if (level1 == 0) {
                    level1 = 1;
                }
                ++card;
                try {
                    hero1.set(0, Integer.toString(level1));
                    hero1.set(1, Integer.toString(card));
                    hero1.set(2, "false");
                    hero1.set(3, Integer.toString(pv));
                    hero1.set(4, Long.toString(System.currentTimeMillis()));
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
                    heroe.put(hero, hero1);
                }
                data.getProfils().get(user.getId())
                        .setCollector(data.getProfils().get(user.getId()).getCollector() + 1);
                data.getProfils().get(user.getId()).setHeroe(heroe);
                if (lang == command.Language.fr) {
                    mess = mess + "- une carte du hero " + hero + " (" + Heroe.getRarity(hero) + ") \n";
                }
                if (lang == command.Language.en) {
                    mess = mess + "- An hero card " + hero + " (" + Heroe.getRarity(hero) + ") \n";
                }
            }
            if ((alea12 = 1 + (int) (Math.random() * 3.0)) == 1) {
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
                if (LootBox.test(data.getProfils().get(user.getId())) <= building.get("marché") * 5 + 20) {
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
                    if (aleaWeapon1 <= 0.5) {
                        aleaWeapon2 = 1;
                    } else if (aleaWeapon1 > 0.5 && aleaWeapon1 <= 0.75) {
                        aleaWeapon2 = 2;
                    } else if (aleaWeapon1 > 0.75 && aleaWeapon1 <= 0.88) {
                        aleaWeapon2 = 3;
                    } else if (aleaWeapon1 > 0.88 && aleaWeapon1 <= 0.96) {
                        aleaWeapon2 = 4;
                    } else if (aleaWeapon1 > 0.96 && aleaWeapon1 <= 1.0) {
                        aleaWeapon2 = 5;
                    }
                    ArrayList<Integer> weaponList = new ArrayList<Integer>();
                    try {
                        weaponList = weapons.get(weapon);
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
                        mess = mess + "- " + weapon + " offrant un bonus de " + aleaWeapon2 * 50
                                + " d'attaque \n";
                    }
                    if (lang == command.Language.en) {
                        mess = mess + "- " + weapon + " which give a bonus of " + aleaWeapon2 * 50
                                + " of attack \n";
                    }
                } else {
                    if (lang == command.Language.fr) {
                        mess = mess
                                + "- Oh non votre ville n'a plus l'espace necessaire pour stocker une arme de plus \n";
                    }
                    if (lang == command.Language.en) {
                        mess = mess
                                + "- Oh no your city doesn't have the necessary space to store a weapon more \n";
                    }
                }
            }
            if ((alea13 = 1 + (int) (Math.random() * 3.0)) == 1) {
                int entrepot;
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
                try {
                    entrepot = building.get("marché");
                } catch (NullPointerException e) {
                    entrepot = 0;
                }
                if (LootBox.test(data.getProfils().get(user.getId())) <= entrepot * 5 + 20) {
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
                    Double aleaWeapon1 = Math.random();
                    int aleaArmor2 = 1;
                    if (aleaWeapon1 <= 0.5) {
                        aleaArmor2 = 1;
                    } else if (aleaWeapon1 > 0.5 && aleaWeapon1 <= 0.75) {
                        aleaArmor2 = 2;
                    } else if (aleaWeapon1 > 0.75 && aleaWeapon1 <= 0.88) {
                        aleaArmor2 = 3;
                    } else if (aleaWeapon1 > 0.88 && aleaWeapon1 <= 0.96) {
                        aleaArmor2 = 4;
                    } else if (aleaWeapon1 > 0.96 && aleaWeapon1 <= 1.0) {
                        aleaArmor2 = 5;
                    }
                    ArrayList<Integer> armorList = new ArrayList<Integer>();
                    try {
                        armorList = armors.get(armor);
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
                        mess = mess + "- " + armor + " offrant un bonus de " + aleaArmor2 * 50
                                + " de defense \n";
                    }
                    if (lang == command.Language.en) {
                        mess = mess + "- " + armor + " which give a bonus of " + aleaArmor2 * 50
                                + " of defense \n";
                    }
                } else {
                    if (lang == command.Language.fr) {
                        mess = mess
                                + "- Oh non on dirai que votre ville n'a plus l'espace necessaire pour stocker un bouclier de plus.";
                    }
                    if (lang == command.Language.en) {
                        mess = mess
                                + "-Oh no your city doesn't have the necessary space to store mroe armor.";
                    }
                }
            }
            if (lang == command.Language.fr) {
                channel.sendMessage("Votre lootbox a été ouvert avec succes : Vous y avez gagné : \n" + mess).queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("Your lootbox oppened with success : You won : \n" + mess).queue();
            return;
        }
        if (!c1.equals("premium"))
            return;
        if (!Premium.Premium(data.getProfils().get(user.getId()))) {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez etre premium pour reclamer votre lootbox habdomadaire").queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("You must be premium to open your weekly lootbox").queue();
            return;
        }
        boolean premiumlb = false;
        try {
            premiumlb = data.getProfils().get(user.getId()).isLootboxPremium();
        } catch (NullPointerException e) {
            premiumlb = false;
        }
        try {
            c2 = args[1];
        } catch (IndexOutOfBoundsException e) {
            c2 = "";
        }
        if (!premiumlb) {
            if (c2.equals("open")) {
                int alea9;
                int level;
                int alea1;
                int alea11;
                int alea8;
                int alea12;
                HashMap<String, ArrayList<String>> heroe;
                int alea7;
                int alea13;
                int alea3;
                int alea5;
                int alea6;
                data.getProfils().get(user.getId()).setLootboxPremium(true);
                int Game_EXP = data.getProfils().get(user.getId()).getXp();
                HashMap<String, Integer> building = data.getProfils().get(user.getId()).getBuilding();
                int struct = building.get("marché");
                int levelEcole = building.get("biblioth\u00e8que");
                int levelMine = building.get("mine");
                double bonus = Structure.entreprise(struct);
                try {
                    double math = Math.sqrt(Game_EXP);
                    level = (int) Math.round(math);
                } catch (NullPointerException e) {
                    level = 0;
                }
                String mess = "";
                if ((alea1 = 1 + (int) (Math.random() * 3.0)) == 1) {
                    long money = data.getProfils().get(user.getId()).getMoney();
                    int Alea_money = 25 + (int) (Math.random() * 76.0);
                    int money_win2 = (int) ((double) Alea_money * (100.0 + (double) (10 * level) * bonus));
                    data.getProfils().get(user.getId()).setMoney(money += money_win2);
                    mess = mess + "- " + money_win2 + " money \n";
                }
                if ((alea11 = 1 + (int) (Math.random() * 3.0)) == 1) {
                    long pop = data.getProfils().get(user.getId()).getHabitants();
                    int Alea_pop = 25 + (int) (Math.random() * 76.0);
                    int popWin = (int) ((double) Alea_pop * (10.0 + 2.5 * (double) (10 * (levelEcole + 1) * level)));
                    data.getProfils().get(user.getId()).setHabitants(pop += popWin);
                    if (lang == command.Language.fr) {
                        mess = mess + "- " + popWin + " habitants \n";
                    }
                    if (lang == command.Language.en) {
                        mess = mess + "- " + popWin + " people \n";
                    }
                }
                HashMap<String, Integer> res = data.getProfils().get(user.getId()).getRes();
                int min = 25 + (int) (2.5 * (double) levelMine);
                int max = 100 + 10 * levelMine;
                int Alea_bois = 25 + (int) (Math.random() * (double) (max - min + 1));
                int Alea_acier = 25 + (int) (Math.random() * (double) (max - min + 1));
                int Alea_brique = 25 + (int) (Math.random() * (double) (max - min + 1));
                int Alea_pierre = 25 + (int) (Math.random() * (double) (max - min + 1));
                int Alea_paille = 25 + (int) (Math.random() * (double) (max - min + 1));
                int Alea_fer = 25 + (int) (Math.random() * (double) (max - min + 1));
                int bois = res.get("bois");
                int acier = res.get("argile");
                int brique = res.get("cuir");
                int pierre = res.get("pierre");
                int paille = res.get("paille");
                int fer = res.get("fer");
                int alea4 = 1 + (int) (Math.random() * 3.0);
                if (alea4 == 1) {
                    bois += Alea_bois;
                    if (lang == command.Language.fr) {
                        mess = mess + "- " + Alea_bois + " bois \n";
                    }
                    if (lang == command.Language.en) {
                        mess = mess + "- " + Alea_bois + " wood \n";
                    }
                }
                if ((alea5 = 1 + (int) (Math.random() * 3.0)) == 1) {
                    acier += Alea_acier;
                    if (lang == command.Language.fr) {
                        mess = mess + "- " + Alea_acier + " argile \n";
                    }
                    if (lang == command.Language.en) {
                        mess = mess + "- " + Alea_acier + " clay \n";
                    }
                }
                if ((alea6 = 1 + (int) (Math.random() * 3.0)) == 1) {
                    brique += Alea_brique;
                    if (lang == command.Language.fr) {
                        mess = mess + "- " + Alea_brique + " cuir \n";
                    }
                    if (lang == command.Language.en) {
                        mess = mess + "- " + Alea_brique + " leather \n";
                    }
                }
                if ((alea7 = 1 + (int) (Math.random() * 3.0)) == 1) {
                    pierre += Alea_pierre;
                    if (lang == command.Language.fr) {
                        mess = mess + "- " + Alea_pierre + " pierre \n";
                    }
                    if (lang == command.Language.en) {
                        mess = mess + "- " + Alea_pierre + " stone \n";
                    }
                }
                if ((alea8 = 1 + (int) (Math.random() * 3.0)) == 1) {
                    paille += Alea_paille;
                    if (lang == command.Language.fr) {
                        mess = mess + "- " + Alea_paille + " paille \n";
                    }
                    if (lang == command.Language.en) {
                        mess = mess + "- " + Alea_paille + " straw \n";
                    }
                }
                if ((alea9 = 1 + (int) (Math.random() * 3.0)) == 1) {
                    fer += Alea_fer;
                    if (lang == command.Language.fr) {
                        mess = mess + "- " + Alea_fer + " fer \n";
                    }
                    if (lang == command.Language.en) {
                        mess = mess + "- " + Alea_fer + " iron \n";
                    }
                }
                res.put("bois", bois);
                res.put("argile", acier);
                res.put("cuir", brique);
                res.put("pierre", pierre);
                res.put("paille", paille);
                res.put("fer", fer);
                data.getProfils().get(user.getId()).setRes(res);
                try {
                    heroe = data.getProfils().get(user.getId()).getHeroe();
                } catch (NullPointerException e) {
                    heroe = new HashMap();
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
                }
                int alea10 = 1 + (int) (Math.random() * 3.0);
                if (alea10 == 1) {
                    int card;
                    int alea2;
                    int pv;
                    int level1;
                    ArrayList<String> hero1;
                    String hero = null;
                    double alea_hero = Math.random();
                    if (alea_hero <= 0.5) {
                        hero = "Karl";
                    } else if (alea_hero > 0.5 && alea_hero <= 0.75) {
                        alea2 = 1 + (int) (Math.random() * 4.0);
                        if (alea2 == 1) {
                            hero = "Valkyrie";
                        }
                        if (alea2 == 2) {
                            hero = "Ouranos";
                        }
                        if (alea2 == 3) {
                            hero = "Oeil";
                        }
                        if (alea2 == 4) {
                            hero = "Ikaryus";
                        }
                    } else if (alea_hero > 0.75 && alea_hero <= 0.9) {
                        alea2 = 1 + (int) (Math.random() * 6.0);
                        if (alea2 == 1) {
                            hero = "Yegarde";
                        }
                        if (alea2 == 2) {
                            hero = "Angel";
                        }
                        if (alea2 == 3) {
                            hero = "Zhen";
                        }
                        if (alea2 == 4) {
                            hero = "Hearth";
                        }
                        if (alea2 == 5) {
                            hero = "Lixie";
                        }
                        if (alea2 == 6) {
                            hero = "Akashi";
                        }
                    } else if (alea_hero > 0.9 && alea_hero <= 0.99) {
                        alea2 = 1 + (int) (Math.random() * 4.0);
                        if (alea2 == 1) {
                            hero = "Rose";
                        }
                        if (alea2 == 2) {
                            hero = "Hell";
                        }
                        if (alea2 == 3) {
                            hero = "Spirita";
                        }
                        if (alea2 == 4) {
                            hero = "Tempest";
                        }
                    } else if (alea_hero > 0.99) {
                        hero = "Ivoire";
                    }
                    try {
                        hero1 = heroe.get(hero);
                    } catch (NullPointerException e) {
                        hero1 = new ArrayList<String>();
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
                        pv = Heroe.getPV(hero, data.getProfils().get(user.getId()));
                    } catch (NullPointerException e) {
                        pv = 0;
                    }
                    if (level1 == 0) {
                        level1 = 1;
                    }
                    ++card;
                    try {
                        hero1.set(0, Integer.toString(level1));
                        hero1.set(1, Integer.toString(card));
                        hero1.set(2, "false");
                        hero1.set(3, Integer.toString(pv));
                        hero1.set(4, Long.toString(System.currentTimeMillis()));
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
                        heroe.put(hero, hero1);
                    }
                    data.getProfils().get(user.getId())
                            .setCollector(data.getProfils().get(user.getId()).getCollector() + 1);
                    data.getProfils().get(user.getId()).setHeroe(heroe);
                    if (lang == command.Language.fr) {
                        mess = mess + "- une carte du hero " + hero + " (" + Heroe.getRarity(hero)
                                + ") \n";
                    }
                    if (lang == command.Language.en) {
                        mess = mess + "- An hero card " + hero + " (" + Heroe.getRarity(hero) + ") \n";
                    }
                }
                if ((alea12 = 1 + (int) (Math.random() * 3.0)) == 1) {
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
                    if (LootBox.test(data.getProfils().get(user.getId())) <= building.get("marché") * 5 + 20) {
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
                        if (aleaWeapon1 <= 0.5) {
                            aleaWeapon2 = 1;
                        } else if (aleaWeapon1 > 0.5 && aleaWeapon1 <= 0.75) {
                            aleaWeapon2 = 2;
                        } else if (aleaWeapon1 > 0.75 && aleaWeapon1 <= 0.88) {
                            aleaWeapon2 = 3;
                        } else if (aleaWeapon1 > 0.88 && aleaWeapon1 <= 0.96) {
                            aleaWeapon2 = 4;
                        } else if (aleaWeapon1 > 0.96 && aleaWeapon1 <= 1.0) {
                            aleaWeapon2 = 5;
                        }
                        ArrayList<Integer> weaponList = new ArrayList<Integer>();
                        try {
                            weaponList = weapons.get(weapon);
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
                            mess = mess + "- " + weapon + " offrant un bonus de " + aleaWeapon2 * 50
                                    + " d'attaque \n";
                        }
                        if (lang == command.Language.en) {
                            mess = mess + "- " + weapon + " which give a bonus of " + aleaWeapon2 * 50
                                    + " of attack \n";
                        }
                    } else {
                        if (lang == command.Language.fr) {
                            mess = mess
                                    + "- Oh non votre ville n'a plus l'espace necessaire pour stocker une arme de plus \n";
                        }
                        if (lang == command.Language.en) {
                            mess = mess
                                    + "- Oh no your city doesn't have the necessary space to store a weapon more \n";
                        }
                    }
                }
                if ((alea13 = 1 + (int) (Math.random() * 3.0)) == 1) {
                    int entrepot;
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
                    try {
                        entrepot = building.get("marché");
                    } catch (NullPointerException e) {
                        entrepot = 0;
                    }
                    if (LootBox.test(data.getProfils().get(user.getId())) <= entrepot * 5 + 20) {
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
                        Double aleaWeapon1 = Math.random();
                        int aleaArmor2 = 1;
                        if (aleaWeapon1 <= 0.5) {
                            aleaArmor2 = 1;
                        } else if (aleaWeapon1 > 0.5 && aleaWeapon1 <= 0.75) {
                            aleaArmor2 = 2;
                        } else if (aleaWeapon1 > 0.75 && aleaWeapon1 <= 0.88) {
                            aleaArmor2 = 3;
                        } else if (aleaWeapon1 > 0.88 && aleaWeapon1 <= 0.96) {
                            aleaArmor2 = 4;
                        } else if (aleaWeapon1 > 0.96 && aleaWeapon1 <= 1.0) {
                            aleaArmor2 = 5;
                        }
                        ArrayList<Integer> armorList = new ArrayList<Integer>();
                        try {
                            armorList = armors.get(armor);
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
                            mess = mess + "- " + armor + " offrant un bonus de " + aleaArmor2 * 50
                                    + " de defense \n";
                        }
                        if (lang == command.Language.en) {
                            mess = mess + "- " + armor + " which give a bonus of " + aleaArmor2 * 50
                                    + " of defense \n";
                        }
                    } else {
                        if (lang == command.Language.fr) {
                            mess = mess
                                    + "- Oh non on dirai que votre ville n'a plus l'espace necessaire pour stocker un bouclier de plus.";
                        }
                        if (lang == command.Language.en) {
                            mess = mess
                                    + "-Oh no your city doesn't have the necessary space to store mroe armor.";
                        }
                    }
                }
                if (lang == command.Language.fr) {
                    channel.sendMessage("Votre lootbox a été ouvert avec succes : Vous y avez gagné : \n" + mess)
                            .queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("Your lootbox oppened with success : You won : \n" + mess).queue();
                return;
            }
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous pouvez actuellement ouvrir votre box premium.").queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("You can open your premium lootbox.").queue();
            return;
        }
        if (lang == command.Language.fr) {
            channel.sendMessage("Vous avez deja ouvert votre lootbox speciale pour les premium cette semaine.").queue();
        }
        if (lang != command.Language.en)
            return;
        channel.sendMessage("You already open you premium lootbox this week.").queue();
    }
}
