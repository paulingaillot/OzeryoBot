/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import fr.Ybsi.OzeryoBot.Utils.color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Heroe {
    public static String getRarity(String hero) {
        String rarity = "Common";
        if (hero.equals("Karl")) {
            rarity = "Common";
        } else if (hero.equals("Valkyrie") || hero.equals("Ouranos") || hero.equals("Oeil") || hero.equals("Ikaryus")) {
            rarity = "Rare";
        } else if (hero.equals("Yegarde") || hero.equals("Angel") || hero.equals("Zhen") || hero.equals("Hearth")
                || hero.equals("Lixie") || hero.equals("Akashi")) {
            rarity = "Epic";
        } else if (hero.equals("Rose") || hero.equals("Hell") || hero.equals("Spirita") || hero.equals("Tempest")) {
            rarity = "Legend";
        } else if (hero.equals("Ivoire")) {
            rarity = "Mythic";
        }
        return rarity;
    }

    public static int getAtk(String hero, int level, Profil profil) {
        HashMap<String, ArrayList<String>> heroe;
        ArrayList<String> list;
        int atk = 100 + (level - 1) * 100;
        ProfilData data = DiscordBot.getData();
        try {
            heroe = profil.getHeroe();
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
        try {
            list = (ArrayList) heroe.get(hero);
        } catch (NullPointerException e) {
            return atk;
        }
        int levelarmor = 0;
        try {
            levelarmor = Integer.parseInt((String) list.get(5));
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
        atk += levelarmor * 50;
        if (hero.equals("Valkyrie") || hero.equals("Yegarde") || hero.equals("Akashi") || hero.equals("Zhen")
                || hero.equals("Rose") || hero.equals("Hell") || hero.equals("Spirita") || hero.equals("Ivoire")) {
            atk *= 2;
        }
        return atk;
    }

    public static int getDef(String hero, int level, Profil user) {
        HashMap<String, ArrayList<String>> heroe;
        ArrayList<String> list;
        int def = 100 + (level - 1) * 100;
        ProfilData data = DiscordBot.getData();
        try {
            heroe = user.getHeroe();
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
        try {
            list = (ArrayList) heroe.get(data.getProfils().get(user.getId()).getActiveHeroe());
        } catch (NullPointerException e) {
            return def;
        }
        int levelarmor = 0;
        try {
            levelarmor = Integer.parseInt((String) list.get(6));
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // empty catch block
        }
        def += levelarmor * 50;
        if (hero.equals("Ouranos") || hero.equals("Zhen") || hero.equals("Hearth") || hero.equals("Lixie")
                || hero.equals("Hell") || hero.equals("Rose") || hero.equals("Tempest") || hero.equals("Ivoire")) {
            def *= 2;
        }
        return def;
    }

    public static int getPV(String hero, Profil user) {
        HashMap<String, ArrayList<String>> heroe;
        ArrayList<String> list;
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
        try {
            list = (ArrayList) heroe.get(hero);
        } catch (NullPointerException e) {
            return 0;
        }
        if (list == null) {
            return 0;
        }
        int level = Integer.parseInt((String) list.get(0));
        int cartes = Integer.parseInt((String) list.get(1));
        String atk = (String) list.get(2);
        int PV = Integer.parseInt((String) list.get(3));
        if (atk.equals("false")) {
            long lastPV;
            try {
                lastPV = Long.parseLong((String) list.get(4));
            } catch (IndexOutOfBoundsException e) {
                lastPV = 0L;
            }
            long delay = System.currentTimeMillis() - lastPV;
            delay /= 1000L;
            PV = (int) ((double) PV + (double) (delay /= 60L) * ((double) Heroe.getmaxPV(hero, level) * 0.001));
            if (PV > Heroe.getmaxPV(hero, level)) {
                PV = Heroe.getmaxPV(hero, level);
            }
            list.set(3, Integer.toString(PV));
            try {
                list.set(4, "" + System.currentTimeMillis());
            } catch (IndexOutOfBoundsException e) {
                list.add(4, "" + System.currentTimeMillis());
            }
            heroe.put(hero, list);
            data.getProfils().get(user.getId()).setHeroe(heroe);
        }
        return PV;
    }

    public static int getmaxPV(String heroe, int level) {
        int PV = 10000 + (level - 1) * 1000;
        if (heroe.equals("Oeil") || heroe.equals("Angel") || heroe.equals("Hearth") || heroe.equals("Yegarde")
                || heroe.equals("Hell") || heroe.equals("Spirita") || heroe.equals("Tempest")
                || heroe.equals("Ivoire")) {
            PV = (int) ((double) PV * 1.5);
        }
        return PV;
    }

    public static int getMagic(String heroe, int level) {
        int magic = 100 + (level - 1) * 100;
        if (heroe.equals("Ikaryus") || heroe.equals("Angel") || heroe.equals("Lixie") || heroe.equals("Akashi")
                || heroe.equals("Rose") || heroe.equals("Spirita") || heroe.equals("Tempest")
                || heroe.equals("Ivoire")) {
            magic *= 2;
        }
        return magic;
    }

    @command(name = "hero", abbrev = "h", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Game)
    private void hero(Message message, User user, String[] args, MessageChannel channel, Guild guild, ProfilData data,
                      command.Language lang) {
        HashMap<String, ArrayList<Integer>> weapons;
        HashMap<String, Integer> building;

        String c1;
        try {
            c1 = args[0];
        } catch (Exception e) {
            c1 = "";
        }
        if (c1.equals("info") || c1.equals("")) {
            HashMap<String, ArrayList<String>> heroe = data.getProfils().get(user.getId()).getHeroe();
            String activeHero = data.getProfils().get(user.getId()).getActiveHeroe();
            ArrayList<String> list2;
            try {
                list2 = heroe.get(activeHero);
            } catch (NullPointerException e) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(
                            "Vous n'avez actuelement aucun hero selectionner. Commencez d'abord par enselectionner un a l'aide de la commande ``=hero select``")
                            .queue();
                    return;
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(
                            "You don't have any hero selected. Start by select a hero using the command ``=hero select``")
                            .queue();
                    return;
                }
                return;
            }
            if (list2 == null) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(
                            "Vous n'avez actuelement aucun hero selectionner. Commencez d'abord par enselectionner un a l'aide de la commande ``=hero select``")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(
                            "You don't have any hero selected. Start by select a hero using the command ``=hero select``")
                            .queue();
                }
                return;
            }
            EmbedBuilder builder2 = new EmbedBuilder();
            int level2 = Integer.parseInt(list2.get(0));
            int PV = Heroe.getPV(activeHero, data.getProfils().get(user.getId()));
            int pvtotal = Heroe.getmaxPV(activeHero, level2);
            int ATK = Heroe.getAtk(activeHero, level2, data.getProfils().get(user.getId()));
            int DEF = Heroe.getDef(activeHero, level2, data.getProfils().get(user.getId()));
            int MAG = Heroe.getMagic(activeHero, level2);
            builder2.setAuthor(user.getName(), null, user.getAvatarUrl());
            builder2.setColor(color.couleurAleatoire(user));
            builder2.setTitle(activeHero);
            builder2.addField("Level", list2.get(0), true);
            if (lang == command.Language.fr) {
                builder2.addField("Cartes", list2.get(1), true);
            }
            if (lang == command.Language.en) {
                builder2.addField("Cards", list2.get(1), true);
            }
            if (lang == command.Language.fr) {
                builder2.addField("En attaque", list2.get(2), true);
            }
            if (lang == command.Language.en) {
                builder2.addField("In attack", list2.get(2), true);
            }
            if (lang == command.Language.fr) {
                builder2.addField(" :heart:  PV", String.valueOf(PV) + "/" + pvtotal, true);
            }
            if (lang == command.Language.en) {
                builder2.addField(" :heart:  HP", String.valueOf(PV) + "/" + pvtotal, true);
            }
            builder2.addBlankField(false);
            builder2.addField(":crossed_swords:  Atk", String.valueOf(ATK), true);
            builder2.addField(":shield: Def", String.valueOf(DEF), true);
            if (lang == command.Language.fr) {
                builder2.addField(":boom: Magie", String.valueOf(MAG), true);
            }
            if (lang == command.Language.en) {
                builder2.addField(":boom: Magic", String.valueOf(MAG), true);
            }
            channel.sendMessage(builder2.build()).queue();
        }
        int i;
        if (c1.equals("destroy")) {
            int c3 = 0;
            String c2 = "";
            for (String str : args) {
                if (str.equals(args[0]))
                    continue;
                try {
                    c3 = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    if (c2 != "") c2 = c2 + " " + str;
                    else c2 = c2 + str;
                }
            }
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
            HashMap<String, ArrayList<Integer>> armor;
            try {
                armor = data.getProfils().get(user.getId()).getArmor();
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
                data.getProfils().get(user.getId()).setArmor(armor);
            }
            ArrayList<Integer> list = null;
            int weapon2 = 0;
            int armor1 = 0;
            try {
                list = weapons.get(c2);
                weapon2 = 1;
            } catch (Exception ATK) {
                // empty catch block
            }
            System.out.println(list);
            if (list == null) {
                weapon2 = 0;
                armor1 = 1;
                try {
                    list = armor.get(c2);
                } catch (NullPointerException e) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Veuillez indiquer un nom valide d'armure ou d'épée").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("Please type a valid weapon or armor name.").queue();
                    }
                    return;
                }
                armor1 = 1;
            }
            if (list == null) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Veuillez indiquer un nom valide d'armure ou d'épée").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Please type a valid weapon or armor name.").queue();
                }
                return;
            }

            if (c3 == 0) {
                String mess = "";
                if (lang == command.Language.fr) {
                    mess = "Veuillez indiquez le numero du " + c2 + " que vous voulez detruire : \n";
                }
                if (lang == command.Language.en) {
                    mess = "Please type the number of the " + c2 + " you want to destroy : \n";
                }
                i = 1;
                try {
                    for (int o : list) {
                        mess = String.valueOf(mess) + " **[" + i + "]** - Level " + o + "\n";
                        ++i;
                    }
                } catch (NullPointerException e) {
                    // empty catch block
                }
                channel.sendMessage(mess).queue();
            } else if (weapon2 != 0) {
                int level4 = (Integer) ((ArrayList) weapons.get(c2)).get(c3 - 1);
                ArrayList weapon1 = (ArrayList) weapons.get(c2);
                weapon1.remove(c3 - 1);
                weapons.put(c2, weapon1);
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous venez de détruire : " + c2 + " de niveau " + level4).queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You just destroy : " + c2 + " level " + level4).queue();
                }
            } else if (armor1 != 0) {
                int level5 = (Integer) ((ArrayList) armor.get(c2)).get(c3 - 1);
                ArrayList armor2 = (ArrayList) armor.get(c2);
                armor2.remove(c3 - 1);
                armor.put(c2, armor2);
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous venez de détruire : " + c2 + " de niveau " + level5).queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You just destroy : " + c2 + " level " + level5).queue();
                }
            }
        }
        String gourdin;
        if (c1.equals("weapons")) {
            HashMap<String, ArrayList<Integer>> weapons2;
            try {
                weapons2 = data.getProfils().get(user.getId()).getWeapons();
            } catch (NullPointerException e) {
                weapons2 = new HashMap<String, ArrayList<Integer>>();
                weapons2.put("epee", null);
                weapons2.put("spectre", null);
                weapons2.put("arc", null);
                weapons2.put("arbalete", null);
                weapons2.put("lance", null);
                weapons2.put("pelle de combat", null);
                weapons2.put("sarbacanne", null);
                weapons2.put("gourdin", null);
                weapons2.put("flechettes", null);
                weapons2.put("trident", null);
                weapons2.put("fleau", null);
                weapons2.put("fouet", null);
                weapons2.put("baton", null);
                weapons2.put("fourche", null);
                weapons2.put("dague", null);
                weapons2.put("shuriken", null);
                weapons2.put("katana", null);
                data.getProfils().get(user.getId()).setWeapons(weapons2);
            }
            building = data.getProfils().get(user.getId()).getBuilding();
            EmbedBuilder builder = new EmbedBuilder();
            if (lang == command.Language.fr) {
                builder.setTitle(":crossed_swords: Forge ");
            }
            if (lang == command.Language.en) {
                builder.setTitle(":crossed_swords: Forge ");
            }
            builder.setColor(color.couleurAleatoire(user));
            builder.setAuthor(user.getName(), null, user.getAvatarUrl());
            builder.setFooter(guild.getName(), guild.getIconUrl());
            if (lang == command.Language.fr) {
                builder.setDescription(String.valueOf(LootBox.test(data.getProfils().get(user))) + " / " + (building.get("armurerie") * 5 + 20)
                        + " places d'entrepot utilisés.");
            }
            if (lang == command.Language.en) {
                builder.setDescription(String.valueOf(LootBox.test(data.getProfils().get(user))) + " / " + (building.get("armurerie") * 5 + 20)
                        + " store spaces used.");
            }
            String epee = "";
            int level = 0;
            try {
                Iterator weapon21 = ((ArrayList) weapons2.get("epee")).iterator();
                while (weapon21.hasNext()) {
                    level = (Integer) weapon21.next();
                    if (lang == command.Language.fr) {
                        epee = String.valueOf(epee) + "niveau : " + level + " \n";
                    }
                    if (lang != command.Language.en)
                        continue;
                    epee = String.valueOf(epee) + "level : " + level + " \n";
                }
            } catch (NullPointerException e) {
                // empty catch block
            }
            if (epee != "") {
                builder.addField("epee", epee, false);
            }
            String spectre = "";
            try {
                Iterator armor12 = ((ArrayList) weapons2.get("spectre")).iterator();
                while (armor12.hasNext()) {
                    level = (Integer) armor12.next();
                    if (lang == command.Language.fr) {
                        spectre = String.valueOf(spectre) + "niveau : " + level + " \n";
                    }
                    if (lang != command.Language.en)
                        continue;
                    spectre = String.valueOf(spectre) + "level : " + level + " \n";
                }
            } catch (NullPointerException e) {
                // empty catch block
            }
            if (spectre != "") {
                builder.addField("spectre", spectre, false);
            }
            String arc = "";
            try {
                Iterator level5 = ((ArrayList) weapons2.get("arc")).iterator();
                while (level5.hasNext()) {
                    level = (Integer) level5.next();
                    if (lang == command.Language.fr) {
                        arc = String.valueOf(arc) + "niveau : " + level + " \n";
                    }
                    if (lang != command.Language.en)
                        continue;
                    arc = String.valueOf(arc) + "level : " + level + " \n";
                }
            } catch (NullPointerException e) {
                // empty catch block
            }
            if (arc != "") {
                builder.addField("arc", arc, false);
            }
            String arbalete = "";
            try {
                Iterator armor2 = ((ArrayList) weapons2.get("arbalete")).iterator();
                while (armor2.hasNext()) {
                    level = (Integer) armor2.next();
                    if (lang == command.Language.fr) {
                        arbalete = String.valueOf(arbalete) + "niveau : " + level + " \n";
                    }
                    if (lang != command.Language.en)
                        continue;
                    arbalete = String.valueOf(arbalete) + "level : " + level + " \n";
                }
            } catch (NullPointerException i18) {
                // empty catch block
            }
            if (arbalete != "") {
                builder.addField("arbalete", arbalete, false);
            }
            String lance = "";
            try {
                Iterator level31 = ((ArrayList) weapons2.get("lance")).iterator();
                while (level31.hasNext()) {
                    i = (Integer) level31.next();
                    if (lang == command.Language.fr) {
                        lance = String.valueOf(lance) + "niveau : " + i + " \n";
                    }
                    if (lang != command.Language.en)
                        continue;
                    lance = String.valueOf(lance) + "level : " + i + " \n";
                }
            } catch (NullPointerException i19) {
                // empty catch block
            }
            if (lance != "") {
                builder.addField("lance", lance, false);
            }
            String pelle_de_combat = "";
            try {
                Iterator iterator = ((ArrayList) weapons2.get("pelle de combat")).iterator();
                while (iterator.hasNext()) {
                    level = (Integer) iterator.next();
                    if (lang == command.Language.fr) {
                        pelle_de_combat = String.valueOf(pelle_de_combat) + "niveau : " + level + " \n";
                    }
                    if (lang != command.Language.en)
                        continue;
                    pelle_de_combat = String.valueOf(pelle_de_combat) + "level : " + level + " \n";
                }
            } catch (NullPointerException e) {
                // empty catch block
            }
            if (pelle_de_combat != "") {
                builder.addField("pelle de combat", pelle_de_combat, false);
            }
            String sarbacanne = "";
            try {
                Iterator iterator3 = ((ArrayList) weapons2.get("sarbacanne")).iterator();
                while (iterator3.hasNext()) {
                    level = (Integer) iterator3.next();
                    if (lang == command.Language.fr) {
                        sarbacanne = String.valueOf(sarbacanne) + "niveau : " + level + " \n";
                    }
                    if (lang != command.Language.en)
                        continue;
                    sarbacanne = String.valueOf(sarbacanne) + "level : " + level + " \n";
                }
            } catch (NullPointerException e) {
                // empty catch block
            }
            if (sarbacanne != "") {
                builder.addField("sarbacanne", sarbacanne, false);
            }
            gourdin = "";
            try {
                Iterator iterator4 = ((ArrayList) weapons2.get("gourdin")).iterator();
                while (iterator4.hasNext()) {
                    level = (Integer) iterator4.next();
                    if (lang == command.Language.fr) {
                        gourdin = String.valueOf(gourdin) + "niveau : " + level + " \n";
                    }
                    if (lang != command.Language.en)
                        continue;
                    gourdin = String.valueOf(gourdin) + "level : " + level + " \n";
                }
            } catch (NullPointerException e) {
                // empty catch block
            }
            if (gourdin != "") {
                builder.addField("gourdin", (String) gourdin, false);
            }
            String flechettes = "";
            try {
                Iterator iterator5 = ((ArrayList) weapons2.get("flechettes")).iterator();
                while (iterator5.hasNext()) {
                    level = (Integer) iterator5.next();
                    if (lang == command.Language.fr) {
                        flechettes = String.valueOf(flechettes) + "niveau : " + level + " \n";
                    }
                    if (lang != command.Language.en)
                        continue;
                    flechettes = String.valueOf(flechettes) + "level : " + level + " \n";
                }
            } catch (NullPointerException e) {
                // empty catch block
            }
            if (flechettes != "") {
                builder.addField("flechettes", flechettes, false);
            }
            String trident = "";
            try {
                Iterator iterator6 = ((ArrayList) weapons2.get("trident")).iterator();
                while (iterator6.hasNext()) {
                    level = (Integer) iterator6.next();
                    if (lang == command.Language.fr) {
                        trident = String.valueOf(trident) + "niveau : " + level + " \n";
                    }
                    if (lang != command.Language.en)
                        continue;
                    trident = String.valueOf(trident) + "level : " + level + " \n";
                }
            } catch (NullPointerException e) {
                // empty catch block
            }
            if (trident != "") {
                builder.addField("trident", trident, false);
            }
            String fleau = "";
            try {
                Iterator iterator7 = ((ArrayList) weapons2.get("fleau")).iterator();
                while (iterator7.hasNext()) {
                    level = (Integer) iterator7.next();
                    if (lang == command.Language.fr) {
                        fleau = String.valueOf(fleau) + "niveau : " + level + " \n";
                    }
                    if (lang != command.Language.en)
                        continue;
                    fleau = String.valueOf(fleau) + "level : " + level + " \n";
                }
            } catch (NullPointerException e) {
                // empty catch block
            }
            if (fleau != "") {
                builder.addField("fleau", fleau, false);
            }
            String fouet = "";
            try {
                Iterator iterator8 = ((ArrayList) weapons2.get("fouet")).iterator();
                while (iterator8.hasNext()) {
                    level = (Integer) iterator8.next();
                    if (lang == command.Language.fr) {
                        fouet = String.valueOf(fouet) + "niveau : " + level + " \n";
                    }
                    if (lang != command.Language.en)
                        continue;
                    fouet = String.valueOf(fouet) + "level : " + level + " \n";
                }
            } catch (NullPointerException e) {
                // empty catch block
            }
            if (fouet != "") {
                builder.addField("fouet", (String) fouet, false);
            }
            String baton = "";
            try {
                Iterator iterator9 = ((ArrayList) weapons2.get("baton")).iterator();
                while (iterator9.hasNext()) {
                    level = (Integer) iterator9.next();
                    if (lang == command.Language.fr) {
                        baton = String.valueOf(baton) + "niveau : " + level + " \n";
                    }
                    if (lang != command.Language.en)
                        continue;
                    baton = String.valueOf(baton) + "level : " + level + " \n";
                }
            } catch (NullPointerException e) {
                // empty catch block
            }
            if (baton != "") {
                builder.addField("baton", (String) baton, false);
            }
            String fourche = "";
            try {
                Iterator iterator10 = ((ArrayList) weapons2.get("fourche")).iterator();
                while (iterator10.hasNext()) {
                    level = (Integer) iterator10.next();
                    if (lang == command.Language.fr) {
                        fourche = String.valueOf(fourche) + "niveau : " + level + " \n";
                    }
                    if (lang != command.Language.en)
                        continue;
                    fourche = String.valueOf(fourche) + "level : " + level + " \n";
                }
            } catch (NullPointerException e) {
                // empty catch block
            }
            if (fourche != "") {
                builder.addField("fourche", (String) fourche, false);
            }
            String dague = "";
            try {
                Iterator iterator11 = ((ArrayList) weapons2.get("dague")).iterator();
                while (iterator11.hasNext()) {
                    level = (Integer) iterator11.next();
                    if (lang == command.Language.fr) {
                        dague = String.valueOf(dague) + "niveau : " + level + " \n";
                    }
                    if (lang != command.Language.en)
                        continue;
                    dague = String.valueOf(dague) + "level : " + level + " \n";
                }
            } catch (NullPointerException e) {
                // empty catch block
            }
            if (dague != "") {
                builder.addField("dague", (String) dague, false);
            }
            String shuriken = "";
            try {
                Iterator iterator12 = ((ArrayList) weapons2.get("shuriken")).iterator();
                while (iterator12.hasNext()) {
                    level = (Integer) iterator12.next();
                    if (lang == command.Language.fr) {
                        shuriken = String.valueOf(shuriken) + "niveau : " + level + " \n";
                    }
                    if (lang != command.Language.en)
                        continue;
                    shuriken = String.valueOf(shuriken) + "level : " + level + " \n";
                }
            } catch (NullPointerException e) {
                // empty catch block
            }
            if (shuriken != "") {
                builder.addField("shuriken", (String) shuriken, false);
            }
            String katana = "";
            try {
                Iterator iterator2 = ((ArrayList) weapons2.get("katana")).iterator();
                while (iterator2.hasNext()) {
                    level = (Integer) iterator2.next();
                    if (lang == command.Language.fr) {
                        katana = String.valueOf(katana) + "niveau : " + level + " \n";
                    }
                    if (lang != command.Language.en)
                        continue;
                    katana = String.valueOf(katana) + "level : " + level + " \n";
                }
            } catch (NullPointerException e) {
                // empty catch block
            }
            if (katana != "") {
                builder.addField("katana", (String) katana, false);
            }
            if (builder.isEmpty()) {
                if (lang == command.Language.fr) {
                    builder.addField("Error", "Vous n'avez actuelement aucune armures", true);
                }
                if (lang == command.Language.en) {
                    builder.addField("Error", "Vous n'avez actuelement aucune armures", true);
                }
            }
            channel.sendMessage(builder.build()).queue();
        }
        HashMap<String, ArrayList<Integer>> armor;
        if (c1.equals("equip")) {
            int c3 = 0;

            String c2 = "";
            for (String str : args) {
                if (str.equals(args[0]))
                    continue;
                try {
                    c3 = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    if (c2 != "") c2 = c2 + " " + str;
                    else c2 = c2 + str;
                }
            }
            try {
                weapons = data.getProfils().get(user.getId()).getWeapons();
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
                data.getProfils().get(user.getId()).setWeapons(weapons);
            }
            try {
                armor = data.getProfils().get(user.getId()).getArmor();
            } catch (NullPointerException e) {
                armor = new HashMap();
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
            }
            ArrayList<Integer> list = null;
            boolean weapon3 = false;
            int armor1 = 0;
            try {
                list = (ArrayList) weapons.get(c2);
                weapon3 = true;
            } catch (Exception lance) {
                // empty catch block
            }
            System.out.println(list);
            if (list == null) {
                weapon3 = false;
                armor1 = 1;
                try {
                    list = (ArrayList) armor.get(c2);
                } catch (NullPointerException e) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Veuillez indiquer un nom valide d'armure ou d'épée").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("Please type a valid weapon name.").queue();
                    }
                    return;
                }
                armor1 = 1;
            }
            if (list == null) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Veuillez indiquer un nom valide d'armure ou d'épée").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Please type a valid weapon name.").queue();
                }
                return;
            }
            System.out.println(c2);
            System.out.println(list);
            String mess;
            if (c3 == 0) {
                mess = "";
                if (lang == command.Language.fr) {
                    mess = "Veuillez indiquez le numero du " + c2 + " que vous voulez equiper : \n";
                }
                if (lang == command.Language.en) {
                    mess = "Please type the number of the " + c2 + " you want to equip : \n";
                }
                i = 1;

                channel.sendMessage(mess).queue();
            } else {
                ArrayList hero;
                HashMap<String, ArrayList<String>> heroe3;
                try {
                    heroe3 = data.getProfils().get(user.getId()).getHeroe();
                } catch (NullPointerException e) {
                    heroe3 = new HashMap();
                    ArrayList<String> list22 = new ArrayList<String>();
                    list22.add("1");
                    list22.add("0");
                    list22.add("false");
                    list22.add("0");
                    heroe3.put("Karl", list22);
                    heroe3.put("Valkyrie", null);
                    heroe3.put("Ouranos", null);
                    heroe3.put("Oeil", null);
                    heroe3.put("Ikaryus", null);
                    heroe3.put("Yegarde", null);
                    heroe3.put("Angel", null);
                    heroe3.put("Zhen", null);
                    heroe3.put("Hearth", null);
                    heroe3.put("Lixie", null);
                    heroe3.put("Akashi", null);
                    heroe3.put("Rose", null);
                    heroe3.put("Hell", null);
                    heroe3.put("Spirita", null);
                    heroe3.put("Tempest", null);
                    heroe3.put("Ivoire", null);
                    data.getProfils().get(user.getId()).setHeroe(heroe3);
                }
                String activeHero = data.getProfils().get(user.getId()).getActiveHeroe();
                try {
                    hero = (ArrayList) heroe3.get(activeHero);
                } catch (NullPointerException e) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Vous n'avez actuelement aucun hero selectionner. Commencez d'abord par enselectionner un a l'aide de la commande ``=hero select``")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage(
                                "You don't have any hero selected. Please select a hero with the command ``=hero select``")
                                .queue();
                    }
                    return;
                }
                if (weapon3 == true) {
                    int level7 = (Integer) ((ArrayList) weapons.get(c2)).get(c3 - 1);
                    try {
                        hero.add(5, "" + level7);
                    } catch (IndexOutOfBoundsException e) {
                        hero.add(5, "" + level7);
                    }
                    heroe3.put(activeHero, hero);
                    data.getProfils().get(user.getId()).setHeroe(heroe3);
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous venez de vous equiper de : " + c2 + " de niveau " + level7).queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You jsut equip : " + c2 + " level " + level7).queue();
                    }
                } else if (armor1 != 0) {
                    int level8 = (Integer) ((ArrayList) armor.get(c2)).get(c3 - 1);
                    try {
                        hero.add(6, "" + level8);
                    } catch (IndexOutOfBoundsException e) {
                        hero.add(5, "0");
                        hero.add(6, "" + level8);
                    }
                    heroe3.put(activeHero, hero);
                    data.getProfils().get(user.getId()).setHeroe(heroe3);
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous venez de vous equiper de : " + c2 + " de niveau " + level8).queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You just equip : " + c2 + " level " + level8).queue();
                    }
                }
            }
        }

        HashMap<String, ArrayList<String>> heroe2;
        if (c1.equals("weaponupgrade") || c1.equals("wupgrade") || c1.equals("wup")) {
            HashMap<String, ArrayList<Integer>> weapons3;
            HashMap<String, ArrayList<Integer>> armor2;
            HashMap<String, Integer> building2 = data.getProfils().get(user.getId()).getBuilding();
            int level9 = building2.get("forge");
            if (level9 != 1) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous devez totu d'abord construire le garage pour pouvoir amliorer vos armes")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You must build your garage to upgrade your weapons").queue();
                }
                return;
            }
            String c23 = "";
            int c4 = 0;
            String[] heroe3 = args;
            int armor1 = heroe3.length;
            int weapon2 = 0;
            for (weapon2 = 0; weapon2 < armor1; ++weapon2) {
                String str = heroe3[weapon2];
                if (str.equals(args[0]))
                    continue;
                try {
                    c4 = Integer.parseInt(str);
                    continue;
                } catch (NumberFormatException e) {
                    c23 = !c23.equals("") ? String.valueOf(c23) + " " + str : str;
                }
            }
            int c3 = 0;
            for (String str : args) {
                if (str.equals(args[0]) || str.equals(Integer.toString(c4)))
                    continue;
                try {
                    c3 = Integer.parseInt(str);
                } catch (NumberFormatException hero) {
                    // empty catch block
                }
            }
            try {
                weapons3 = data.getProfils().get(user.getId()).getWeapons();
            } catch (NullPointerException e) {
                weapons3 = new HashMap<String, ArrayList<Integer>>();
                weapons3.put("epee", null);
                weapons3.put("spectre", null);
                weapons3.put("arc", null);
                weapons3.put("arbalete", null);
                weapons3.put("lance", null);
                weapons3.put("pelle de combat", null);
                weapons3.put("sarbacanne", null);
                weapons3.put("gourdin", null);
                weapons3.put("flechettes", null);
                weapons3.put("trident", null);
                weapons3.put("fleau", null);
                weapons3.put("fouet", null);
                weapons3.put("baton", null);
                weapons3.put("fourche", null);
                weapons3.put("dague", null);
                weapons3.put("shuriken", null);
                weapons3.put("katana", null);
                data.getProfils().get(user.getId()).setWeapons(weapons3);
            }
            try {
                armor2 = data.getProfils().get(user.getId()).getArmor();
            } catch (NullPointerException e) {
                armor2 = new HashMap<String, ArrayList<Integer>>();
                armor2.put("armure obscure", null);
                armor2.put("armure", null);
                armor2.put("bouclier", null);
                armor2.put("armure lumineuse", null);
                armor2.put("armure royale", null);
                armor2.put("armure elfique", null);
                armor2.put("tenue en soie", null);
                armor2.put("armure magique", null);
                armor2.put("bouclier reflechissant", null);
                armor2.put("armure de vulcain", null);
                armor2.put("armure aquatique", null);
                armor2.put("armure magenta", null);
                armor2.put("armure de rubis", null);
                armor2.put("bouclier de cristal", null);
                armor2.put("bouclier de bois", null);
                armor2.put("armure de cuire", null);
                armor2.put("armure celeste", null);
                data.getProfils().get(user.getId()).setArmor(armor2);
            }
            ArrayList list3 = null;
            boolean weapon4 = false;
            boolean armor13 = false;
            try {
                list3 = (ArrayList) weapons3.get(c23);
                weapon4 = true;
            } catch (Exception level8) {
                // empty catch block
            }
            System.out.println(list3);
            if (list3 == null) {
                weapon4 = false;
                armor13 = true;
                try {
                    list3 = (ArrayList) armor2.get(c23);
                } catch (NullPointerException e) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Veuillez un nom valide d'armure ou d'épée").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("Please type a valid name of weapons or armor").queue();
                    }
                    return;
                }
                armor13 = true;
            }
            if (list3 == null) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Veuillez un nom valide d'armure ou d'épée").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Please type a valid name of weapons or armor").queue();
                }
                return;
            }
            System.out.println(c23);
            System.out.println(list3);
            try {
                heroe2 = data.getProfils().get(user.getId()).getHeroe();
            } catch (NullPointerException e) {
                heroe2 = new HashMap();
                ArrayList<String> list23 = new ArrayList<String>();
                list23.add("1");
                list23.add("0");
                list23.add("false");
                list23.add("0");
                ((HashMap) heroe2).put("Karl", list23);
                ((HashMap) heroe2).put("Valkyrie", null);
                ((HashMap) heroe2).put("Ouranos", null);
                ((HashMap) heroe2).put("Oeil", null);
                ((HashMap) heroe2).put("Ikaryus", null);
                ((HashMap) heroe2).put("Yegarde", null);
                ((HashMap) heroe2).put("Angel", null);
                ((HashMap) heroe2).put("Zhen", null);
                ((HashMap) heroe2).put("Hearth", null);
                ((HashMap) heroe2).put("Lixie", null);
                ((HashMap) heroe2).put("Akashi", null);
                ((HashMap) heroe2).put("Rose", null);
                ((HashMap) heroe2).put("Hell", null);
                ((HashMap) heroe2).put("Spirita", null);
                ((HashMap) heroe2).put("Tempest", null);
                ((HashMap) heroe2).put("Ivoire", null);
                data.getProfils().get(user.getId()).setHeroe((HashMap<String, ArrayList<String>>) heroe2);
            }
            if (c3 == 0 || c4 == 0) {
                String mess2 = "";
                if (lang == command.Language.fr) {
                    mess2 = "Veuillez indiquez les numeros du " + c23 + " que vous voulez ameliorer : \n";
                }
                if (lang == command.Language.en) {
                    mess2 = "Please type the number of the " + c23 + " you want to upgrade : \n";
                }
                int i20 = 1;
                try {
                    Iterator fouet1 = list3.iterator();
                    while (fouet1.hasNext()) {
                        int level1 = (Integer) fouet1.next();
                        mess2 = String.valueOf(mess2) + " **[" + i20 + "]** - Level " + level1 + "\n";
                        ++i20;
                    }
                } catch (NullPointerException level1) {
                    // empty catch block
                }
                channel.sendMessage(mess2).queue();
                return;
            }
            if (weapon4) {
                int level2;
                int level1 = (Integer) ((ArrayList) weapons3.get(c23)).get(c3 - 1);
                if (level1 != (level2 = ((Integer) ((ArrayList) weapons3.get(c23)).get(c4 - 1)).intValue())) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez fusionner deux armes de meme niveau.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must merge two weapons of the same level.").queue();
                    }
                    return;
                }
                if (c3 < c4) {
                    ((ArrayList) weapons3.get(c23)).remove(c3 - 1);
                    ((ArrayList) weapons3.get(c23)).remove(c4 - 2);
                } else {
                    ((ArrayList) weapons3.get(c23)).remove(c3 - 1);
                    ((ArrayList) weapons3.get(c23)).remove(c4 - 1);
                }
                int levelup2 = level1 + 1;
                ((ArrayList) weapons3.get(c23)).add(0, levelup2);
                data.getProfils().get(user.getId()).setWeapons(weapons3);
                data.getProfils().get(user.getId()).setMelting(data.getProfils().get(user.getId()).getMelting() + 1);
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous venez de creer une arme de niveau " + (level1 + 1)
                            + " en fusionnant deux armes de niveau " + level1 + ".").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You just createa weapon level " + (level1 + 1)
                            + " by merging two weapons level " + level1 + ".").queue();
                }
            } else if (armor13) {
                int level2;
                int level1 = (Integer) ((ArrayList) armor2.get(c23)).get(c3 - 1);
                if (level1 != (level2 = ((Integer) ((ArrayList) armor2.get(c23)).get(c4 - 1)).intValue())) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous devez fusionner deux armures de meme niveau.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must merge two armors of the same level.").queue();
                    }
                    return;
                }
                if (c3 < c4) {
                    ((ArrayList) armor2.get(c23)).remove(c3 - 1);
                    ((ArrayList) armor2.get(c23)).remove(c4 - 2);
                } else {
                    ((ArrayList) armor2.get(c23)).remove(c3 - 1);
                    ((ArrayList) armor2.get(c23)).remove(c4 - 1);
                }
                int levelup3 = level1 + 1;
                ((ArrayList) armor2.get(c23)).add(0, levelup3);
                data.getProfils().get(user.getId()).setArmor(armor2);
                data.getProfils().get(user.getId()).setMelting(data.getProfils().get(user.getId()).getMelting() + 1);
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous venez de creer une armure de niveau " + (level1 + 1)
                            + " en fusionnant deux armures de niveau " + level1 + ".").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You just create an armor level " + (level1 + 1)
                            + " by merging two armors level " + level1 + ".").queue();
                }
            }
        }
        if (c1.equals("armor")) {
            HashMap<String, ArrayList<Integer>> armor3;
            try {
                armor3 = data.getProfils().get(user.getId()).getArmor();
            } catch (NullPointerException e) {
                armor3 = new HashMap<String, ArrayList<Integer>>();
                armor3.put("armure obscure", null);
                armor3.put("armure", null);
                armor3.put("bouclier", null);
                armor3.put("armure lumineuse", null);
                armor3.put("armure royale", null);
                armor3.put("armure elfique", null);
                armor3.put("tenue en soie", null);
                armor3.put("armure magique", null);
                armor3.put("bouclier reflechissant", null);
                armor3.put("armure de vulcain", null);
                armor3.put("armure aquatique", null);
                armor3.put("armure magenta", null);
                armor3.put("armure de rubis", null);
                armor3.put("bouclier de cristal", null);
                armor3.put("bouclier de bois", null);
                armor3.put("armure de cuire", null);
                armor3.put("armure celeste", null);
                data.getProfils().get(user.getId()).setArmor(armor3);
            }
            building = data.getProfils().get(user.getId()).getBuilding();
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle(":crossed_swords: Forge ");
            builder.setColor(color.couleurAleatoire(user));
            builder.setAuthor(user.getName(), null, user.getAvatarUrl());
            builder.setFooter(guild.getName(), guild.getIconUrl());
            if (lang == command.Language.fr) {
                builder.setDescription(String.valueOf(LootBox.test(data.getProfils().get(user))) + " / " + (building.get("armurerie") * 5 + 20)
                        + " places d'entrepot utilisés.");
            }
            if (lang == command.Language.en) {
                builder.setDescription(String.valueOf(LootBox.test(data.getProfils().get(user))) + " / " + (building.get("armurerie") * 5 + 20)
                        + " spaces used.");
            }
            String epee = "";
            Integer level;
            try {
                Iterator weapons3 = ((ArrayList) armor3.get("armure obscure")).iterator();
                while (weapons3.hasNext()) {
                    level = (Integer) weapons3.next();
                    epee = String.valueOf(epee) + "level : " + level + " \n";
                }
            } catch (NullPointerException i21) {
                // empty catch block
            }
            if (epee != "") {
                builder.addField("armure obscure", epee, false);
            }
            String spectre = "";
            try {
                Iterator armor2 = ((ArrayList) armor3.get("armure")).iterator();
                while (armor2.hasNext()) {
                    level = (Integer) armor2.next();
                    spectre = String.valueOf(spectre) + "level : " + level + " \n";
                }
            } catch (NullPointerException i22) {
                // empty catch block
            }
            if (spectre != "") {
                builder.addField("armure", spectre, false);
            }
            String arc = "";
            try {
                Iterator list3 = ((ArrayList) armor3.get("bouclier")).iterator();
                while (list3.hasNext()) {
                    level = (Integer) list3.next();
                    arc = String.valueOf(arc) + "level : " + level + " \n";
                }
            } catch (NullPointerException i23) {
                // empty catch block
            }
            if (arc != "") {
                builder.addField("bouclier", arc, false);
            }
            String arbalete = "";
            try {
                Iterator weapon4 = ((ArrayList) armor3.get("armure lumineuse")).iterator();
                while (weapon4.hasNext()) {
                    level = (Integer) weapon4.next();
                    arbalete = String.valueOf(arbalete) + "level : " + level + " \n";
                }
            } catch (NullPointerException i24) {
                // empty catch block
            }
            if (arbalete != "") {
                builder.addField("armure lumineuse", arbalete, false);
            }
            String lance = "";
            try {
                Iterator armor13 = ((ArrayList) armor3.get("armure royale")).iterator();
                while (armor13.hasNext()) {
                    i = (Integer) armor13.next();
                    lance = String.valueOf(lance) + "level : " + i + " \n";
                }
            } catch (NullPointerException i25) {
                // empty catch block
            }
            if (lance != "") {
                builder.addField("armure royale", lance, false);
            }
            String pelle_de_combat = "";
            try {
                Iterator heroe22 = ((ArrayList) armor3.get("armure elfique")).iterator();
                while (heroe22.hasNext()) {
                    level = (Integer) heroe22.next();
                    pelle_de_combat = String.valueOf(pelle_de_combat) + "level : " + level + " \n";
                }
            } catch (NullPointerException i26) {
                // empty catch block
            }
            if (pelle_de_combat != "") {
                builder.addField("armure elfique", pelle_de_combat, false);
            }
            String sarbacanne = "";
            try {
                Iterator level1 = ((ArrayList) armor3.get("tenue en soie")).iterator();
                while (level1.hasNext()) {
                    level = (Integer) level1.next();
                    sarbacanne = String.valueOf(sarbacanne) + "level : " + level + " \n";
                }
            } catch (NullPointerException i27) {
                // empty catch block
            }
            if (sarbacanne != "") {
                builder.addField("tenue en soie", sarbacanne, false);
            }
            String gourdin2 = "";
            try {
                Iterator level2 = ((ArrayList) armor3.get("armure magique")).iterator();
                while (level2.hasNext()) {
                    level = (Integer) level2.next();
                    gourdin2 = String.valueOf(gourdin2) + "level : " + level + " \n";
                }
            } catch (NullPointerException i28) {
                // empty catch block
            }
            if (gourdin2 != "") {
                builder.addField("armure magique", gourdin2, false);
            }
            String flechettes = "";
            try {
                Iterator levelup3 = ((ArrayList) armor3.get("bouclier reflechissant")).iterator();
                while (levelup3.hasNext()) {
                    level = (Integer) levelup3.next();
                    flechettes = String.valueOf(flechettes) + "level : " + level + " \n";
                }
            } catch (NullPointerException i29) {
                // empty catch block
            }
            if (flechettes != "") {
                builder.addField("bouclier reflechissant", flechettes, false);
            }
            String trident = "";
            try {
                Iterator test52 = ((ArrayList) armor3.get("armure de vulcain")).iterator();
                while (test52.hasNext()) {
                    level = (Integer) test52.next();
                    trident = String.valueOf(trident) + "level : " + level + " \n";
                }
            } catch (NullPointerException i30) {
                // empty catch block
            }
            if (trident != "") {
                builder.addField("armure de vulcain", trident, false);
            }
            String fleau = "";
            Iterator baton1;
            try {
                baton1 = ((ArrayList) armor3.get("armure aquatique")).iterator();
                while (baton1.hasNext()) {
                    level = (Integer) baton1.next();
                    fleau = String.valueOf(fleau) + "level : " + level + " \n";
                }
            } catch (NullPointerException i31) {
                // empty catch block
            }
            if (fleau != "") {
                builder.addField("armure aquatique", fleau, false);
            }
            String fourche = "";
            try {
                Iterator test25 = ((ArrayList) armor3.get("armure magenta")).iterator();
                while (test25.hasNext()) {
                    level = (Integer) test25.next();
                    fourche = String.valueOf(fourche) + "level : " + level + " \n";
                }
            } catch (NullPointerException i32) {
                // empty catch block
            }

            if (fourche != "") {
                builder.addField("armure magenta", (String) fourche, false);
            }
            String baton = "";
            Iterator dague1;
            try {
                dague1 = ((ArrayList) armor3.get("armure de rubis")).iterator();
                while (dague1.hasNext()) {
                    level = (Integer) dague1.next();
                    baton = String.valueOf(baton) + "level : " + level + " \n";
                }
            } catch (NullPointerException i33) {
                // empty catch block
            }
            if (baton != "") {
                builder.addField("armure de rubis", (String) baton, false);
            }
            fourche = "";
            Iterator shuriken1;
            try {
                shuriken1 = ((ArrayList) armor3.get("bouclier de cristal")).iterator();
                while (shuriken1.hasNext()) {
                    level = (Integer) shuriken1.next();
                    fourche = String.valueOf(fourche) + "level : " + level + " \n";
                }
            } catch (NullPointerException i34) {
                // empty catch block
            }
            if (fourche != "") {
                builder.addField("bouclier de cristal", (String) fourche, false);
            }
            String dague = "";
            Iterator katana1;
            try {
                katana1 = ((ArrayList) armor3.get("bouclier de bois")).iterator();
                while (katana1.hasNext()) {
                    level = (Integer) katana1.next();
                    dague = String.valueOf(dague) + "level : " + level + " \n";
                }
            } catch (NullPointerException i35) {
                // empty catch block
            }
            if (dague != "") {
                builder.addField("bouclier de bois", (String) dague, false);
            }
            String shuriken = "";
            try {
                Iterator i112 = ((ArrayList) armor3.get("armure de cuire")).iterator();
                while (i112.hasNext()) {
                    level = (Integer) i112.next();
                    shuriken = String.valueOf(shuriken) + "level : " + level + " \n";
                }
            } catch (NullPointerException i36) {
                // empty catch block
            }
            if (shuriken != "") {
                builder.addField("armure de cuire", (String) shuriken, false);
            }
            String katana = "";
            try {
                Iterator iterator2 = ((ArrayList) armor3.get("armure celeste")).iterator();
                while (iterator2.hasNext()) {
                    level = (Integer) iterator2.next();
                    katana = String.valueOf(katana) + "level : " + level + " \n";
                }
            } catch (NullPointerException i37) {
                // empty catch block
            }
            if (katana != "") {
                builder.addField("armure celeste", (String) katana, false);
            }
            if (builder.isEmpty()) {
                if (lang == command.Language.fr) {
                    builder.addField("Error", "Vous n'avez actuelement aucune armures", true);
                }
                if (lang == command.Language.en) {
                    builder.addField("Error", "You don't have any armor", true);
                }
            }
            channel.sendMessage(builder.build()).queue();
        }
        HashMap<String, ArrayList<String>> heroe;
        ArrayList<String> list2;
        String c2;
        if (c1.equals("select")) {
            try {
                heroe = data.getProfils().get(user.getId()).getHeroe();
            } catch (NullPointerException e) {
                heroe = new HashMap();
                list2 = new ArrayList();
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
                data.getProfils().get(user.getId()).setHeroe(heroe);
            }
            try {
                c2 = args[1];
            } catch (IndexOutOfBoundsException e) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Veuillez indiquer le hero que vous voulez selectionner.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Please type the name of the hero you want to select.").queue();
                }
                return;
            }
            try {
                list2 = heroe.get(c2);
            } catch (NullPointerException e) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous ne pouvez pas selectionner un hero que vous ne possedez pas.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You can't select a hero you don't have.").queue();
                }
                return;
            }
            if (list2 == null) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous ne pouvez pas selectionner un hero que vous ne possedez pas.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You can't select a hero you don't have.").queue();
                }
                return;
            }
            ArrayList<String> list24 = null;
            try {
                list24 = heroe.get(data.getProfils().get(user.getId()).getActiveHeroe());
            } catch (NullPointerException spectre) {
                // empty catch block
            }
            try {
                if (((String) list24.get(2)).equals("true")) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Veuillez attendre que votre hero actif soit au repos avant dans selectionner un autre.")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You must wait your active hero be at rest to select another hero.")
                                .queue();
                    }
                    return;
                }
            } catch (Exception spectre) {
                // empty catch block
            }
            try {
                list2.set(4, Long.toString(System.currentTimeMillis()));
            } catch (IndexOutOfBoundsException e) {
                list2.add(4, Long.toString(System.currentTimeMillis()));
            }
            heroe.put(c2, list2);
            data.getProfils().get(user.getId()).setActiveHeroe(c2);
            if (lang == command.Language.fr) {
                channel.sendMessage("Votre nouveau hero est désormais : ``" + c2 + "``").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("Your new hero is now : ``" + c2 + "``").queue();
            }
        }
        if (c1.equals("list")) {
            String LixieName;
            ArrayList<String> Ikaryus;
            ArrayList<String> Spirita;
            ArrayList<String> karl;
            int OuranosLevel;
            String SpiritaName;
            int karlLevel;
            int TempestLevel;
            int IvoireLevel;
            int AngelLevel;
            String IvoireName;
            int YegardeLevel;
            String AngelName;
            int HellLevel;
            int HearthLevel;
            String HearthName;
            ArrayList<String> Ivoire;
            ArrayList<String> Zhen;
            ArrayList<String> Ouranos;
            String HellName;
            ArrayList<String> Lixie;
            ArrayList<String> Hearth;
            String YegardeName;
            int ValkyrieLevel;
            int LixieLevel;
            HashMap<String, ArrayList<String>> heroe4;
            int SpiritaLevel;
            String OeilName;
            String IkaryusName;
            int AkashiLevel;
            String karlName;
            int IkaryusLevel;
            String ValkyrieName;
            int ZhenLevel;
            String AkashiName;
            int RoseLevel;
            String ZhenName;
            ArrayList<String> Valkyrie;
            ArrayList<String> Hell;
            ArrayList<String> Yegarde;
            ArrayList<String> Angel;
            ArrayList<String> Akashi;
            ArrayList<String> Tempest;
            ArrayList<String> Rose;
            String TempestName;
            String RoseName;
            ArrayList<String> Oeil;
            String OuranosName;
            int OeilLevel;
            EmbedBuilder builder3 = new EmbedBuilder();
            builder3.setAuthor(user.getName(), null, user.getAvatarUrl());
            builder3.setColor(color.couleurAleatoire(user));
            builder3.setTitle("Heroe List");
            try {
                heroe4 = data.getProfils().get(user.getId()).getHeroe();
            } catch (NullPointerException e) {
                heroe4 = new HashMap();
                ArrayList<String> list4 = new ArrayList<String>();
                list4.add("1");
                list4.add("0");
                list4.add("false");
                list4.add("0");
                heroe4.put("Karl", list4);
                heroe4.put("Valkyrie", null);
                heroe4.put("Ouranos", null);
                heroe4.put("Oeil", null);
                heroe4.put("Ikaryus", null);
                heroe4.put("Yegarde", null);
                heroe4.put("Angel", null);
                heroe4.put("Zhen", null);
                heroe4.put("Hearth", null);
                heroe4.put("Lixie", null);
                heroe4.put("Akashi", null);
                heroe4.put("Rose", null);
                heroe4.put("Hell", null);
                heroe4.put("Spirita", null);
                heroe4.put("Tempest", null);
                heroe4.put("Ivoire", null);
            }
            try {
                karl = data.getProfils().get(user.getId()).getHeroe().get("Karl");
            } catch (NullPointerException e) {
                heroe4 = new HashMap();
                ArrayList<String> list5 = new ArrayList<String>();
                list5.add("1");
                list5.add("0");
                list5.add("false");
                list5.add("0");
                heroe4.put("Karl", list5);
                heroe4.put("Valkyrie", null);
                heroe4.put("Ouranos", null);
                heroe4.put("Oeil", null);
                heroe4.put("Ikaryus", null);
                heroe4.put("Yegarde", null);
                heroe4.put("Angel", null);
                heroe4.put("Zhen", null);
                heroe4.put("Hearth", null);
                heroe4.put("Lixie", null);
                heroe4.put("Akashi", null);
                heroe4.put("Rose", null);
                heroe4.put("Hell", null);
                heroe4.put("Spirita", null);
                heroe4.put("Tempest", null);
                heroe4.put("Ivoire", null);
                data.getProfils().get(user.getId()).setHeroe(heroe4);
                karl = list5;
            }
            if (karl == null) {
                karlName = "Unknown";
                karlLevel = 0;
            } else {
                karlName = "Karl";
                karlLevel = Integer.parseInt((String) ((ArrayList) heroe4.get("Karl")).get(0));
            }
            builder3.addField(karlName, "level : " + karlLevel, true);
            try {
                Valkyrie = data.getProfils().get(user.getId()).getHeroe().get("Valkyrie");
            } catch (NullPointerException e) {
                Valkyrie = null;
            }
            if (Valkyrie == null) {
                ValkyrieName = "Unknown";
                ValkyrieLevel = 0;
            } else {
                ValkyrieName = "Valkyrie";
                ValkyrieLevel = Integer.parseInt((String) ((ArrayList) heroe4.get("Valkyrie")).get(0));
            }
            builder3.addField(ValkyrieName, "level : " + ValkyrieLevel, true);
            try {
                Ouranos = data.getProfils().get(user.getId()).getHeroe().get("Ouranos");
            } catch (NullPointerException e) {
                Ouranos = null;
            }
            if (Ouranos == null) {
                OuranosName = "Unknown";
                OuranosLevel = 0;
            } else {
                OuranosName = "Ouranos";
                OuranosLevel = Integer.parseInt((String) ((ArrayList) heroe4.get("Ouranos")).get(0));
            }
            builder3.addField(OuranosName, "level : " + OuranosLevel, true);
            try {
                Oeil = data.getProfils().get(user.getId()).getHeroe().get("Oeil");
            } catch (Exception e) {
                Oeil = null;
            }
            if (Oeil == null) {
                OeilName = "Unknown";
                OeilLevel = 0;
            } else {
                OeilName = "Oeil";
                OeilLevel = Integer.parseInt((String) ((ArrayList) heroe4.get("Oeil")).get(0));
            }
            builder3.addField(OeilName, "level : " + OeilLevel, true);
            try {
                Ikaryus = data.getProfils().get(user.getId()).getHeroe().get("Ikaryus");
            } catch (Exception e) {
                Ikaryus = null;
            }
            if (Ikaryus == null) {
                IkaryusName = "Unknown";
                IkaryusLevel = 0;
            } else {
                IkaryusName = "Ikaryus";
                IkaryusLevel = Integer.parseInt((String) ((ArrayList) heroe4.get("Ikaryus")).get(0));
            }
            builder3.addField(IkaryusName, "level : " + IkaryusLevel, true);
            try {
                Yegarde = data.getProfils().get(user.getId()).getHeroe().get("Yegarde");
            } catch (Exception e) {
                Yegarde = null;
            }
            if (Yegarde == null) {
                YegardeName = "Unknown";
                YegardeLevel = 0;
            } else {
                YegardeName = "Yegarde";
                YegardeLevel = Integer.parseInt((String) ((ArrayList) heroe4.get("Yegarde")).get(0));
            }
            builder3.addField(YegardeName, "level : " + YegardeLevel, true);
            try {
                Angel = data.getProfils().get(user.getId()).getHeroe().get("Angel");
            } catch (Exception e) {
                Angel = null;
            }
            if (Angel == null) {
                AngelName = "Unknown";
                AngelLevel = 0;
            } else {
                AngelName = "Angel";
                AngelLevel = Integer.parseInt((String) ((ArrayList) heroe4.get("Angel")).get(0));
            }
            builder3.addField(AngelName, "level : " + AngelLevel, true);
            try {
                Zhen = data.getProfils().get(user.getId()).getHeroe().get("Zhen");
            } catch (Exception e) {
                Zhen = null;
            }
            if (Zhen == null) {
                ZhenName = "Unknown";
                ZhenLevel = 0;
            } else {
                ZhenName = "Zhen";
                ZhenLevel = Integer.parseInt((String) ((ArrayList) heroe4.get("Zhen")).get(0));
            }
            builder3.addField(ZhenName, "level : " + ZhenLevel, true);
            try {
                Hearth = data.getProfils().get(user.getId()).getHeroe().get("Hearth");
            } catch (Exception e) {
                Hearth = null;
            }
            if (Hearth == null) {
                HearthName = "Unknown";
                HearthLevel = 0;
            } else {
                HearthName = "Hearth";
                HearthLevel = Integer.parseInt((String) ((ArrayList) heroe4.get("Hearth")).get(0));
            }
            builder3.addField(HearthName, "level : " + HearthLevel, true);
            try {
                Lixie = data.getProfils().get(user.getId()).getHeroe().get("Lixie");
            } catch (Exception e) {
                Lixie = null;
            }
            if (Lixie == null) {
                LixieName = "Unknown";
                LixieLevel = 0;
            } else {
                LixieName = "Lixie";
                LixieLevel = Integer.parseInt((String) ((ArrayList) heroe4.get("Lixie")).get(0));
            }
            builder3.addField(LixieName, "level : " + LixieLevel, true);
            try {
                Akashi = data.getProfils().get(user.getId()).getHeroe().get("Akashi");
            } catch (Exception e) {
                Akashi = null;
            }
            if (Akashi == null) {
                AkashiName = "Unknown";
                AkashiLevel = 0;
            } else {
                AkashiName = "Akashi";
                AkashiLevel = Integer.parseInt((String) ((ArrayList) heroe4.get("Akashi")).get(0));
            }
            builder3.addField(AkashiName, "level : " + AkashiLevel, true);
            try {
                Rose = data.getProfils().get(user.getId()).getHeroe().get("Rose");
            } catch (Exception e) {
                Rose = null;
            }
            if (Rose == null) {
                RoseName = "Unknown";
                RoseLevel = 0;
            } else {
                RoseName = "Rose";
                RoseLevel = Integer.parseInt((String) ((ArrayList) heroe4.get("Rose")).get(0));
            }
            builder3.addField(RoseName, "level : " + RoseLevel, true);
            try {
                Hell = data.getProfils().get(user.getId()).getHeroe().get("Hell");
            } catch (Exception e) {
                Hell = null;
            }
            if (Hell == null) {
                HellName = "Unknown";
                HellLevel = 0;
            } else {
                HellName = "Hell";
                HellLevel = Integer.parseInt((String) ((ArrayList) heroe4.get("Hell")).get(0));
            }
            builder3.addField(HellName, "level : " + HellLevel, true);
            try {
                Spirita = data.getProfils().get(user.getId()).getHeroe().get("Spirita");
            } catch (Exception e) {
                Spirita = null;
            }
            if (Spirita == null) {
                SpiritaName = "Unknown";
                SpiritaLevel = 0;
            } else {
                SpiritaName = "Spirita";
                SpiritaLevel = Integer.parseInt((String) ((ArrayList) heroe4.get("Spirita")).get(0));
            }
            builder3.addField(SpiritaName, "level : " + SpiritaLevel, true);
            try {
                Tempest = data.getProfils().get(user.getId()).getHeroe().get("Tempest");
            } catch (Exception e) {
                Tempest = null;
            }
            if (Tempest == null) {
                TempestName = "Unknown";
                TempestLevel = 0;
            } else {
                TempestName = "Tempest";
                TempestLevel = Integer.parseInt((String) ((ArrayList) heroe4.get("Tempest")).get(0));
            }
            builder3.addField(TempestName, "level : " + TempestLevel, true);
            try {
                Ivoire = data.getProfils().get(user.getId()).getHeroe().get("Ivoire");
            } catch (Exception e) {
                Ivoire = null;
            }
            if (Ivoire == null) {
                IvoireName = "Unknown";
                IvoireLevel = 0;
            } else {
                IvoireName = "Ivoire";
                IvoireLevel = Integer.parseInt((String) ((ArrayList) heroe4.get("Ivoire")).get(0));
            }
            builder3.addField(IvoireName, "level : " + IvoireLevel, true);
            channel.sendMessage(builder3.build()).queue();
        } else if (c1.equals("upgrade") || c1.equals("up")) {
            ArrayList<String> hero;
            try {
                heroe = data.getProfils().get(user.getId()).getHeroe();
            } catch (NullPointerException e) {
                heroe = new HashMap();
                list2 = new ArrayList();
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
            try {
                c2 = args[1];
            } catch (IndexOutOfBoundsException e) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Veuillez indiquez le hero que vous souhaitez ameliorer.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Please type the hero you want to upgrade.").queue();
                }
                return;
            }
            try {
                hero = heroe.get(c2);
            } catch (NullPointerException e) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous ne pouvez pas ameliorer un hero que vous n'avez pas").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You can't upgrade a hero you don't have").queue();
                }
                return;
            }
            if (hero == null) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous ne pouvez pas ameliorer un hero que vous n'avez pas").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You can't upgrade a hero you don't have").queue();
                }
                return;
            }
            int level10 = Integer.parseInt(hero.get(0));
            int card = Integer.parseInt(hero.get(1));
            if (card < 10) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vosu devez posseder 10 cartes afin de pouvoir ameliorer votre hero").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You must have 10 hero card to upgrade your hero.").queue();
                }
                return;
            }
            long cout = 10000000L;
            for (int i12 = 1; i12 < level10 + 1; ++i12) {
                cout = (long) ((double) cout * 1.5);
            }
            long money = data.getProfils().get(user.getId()).getMoney();
            if (money < cout) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(
                            "Vous n'avez pas asser d'argent pour ameliorer votre hero. L'amelioration de votre hero au niveau "
                                    + (level10 + 1) + " coute " + cout + "$")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You don't have enough money to upgrade your hero. This hero upgrade to level "
                            + (level10 + 1) + " cost " + cout + "$").queue();
                }
                return;
            }
            boolean atk = hero.get(2).equals("true");
            if (atk) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous ne pouvez pas ameliorer votre hero tant qu'il est en attaque").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You can't upgrade your hero while your hero is in attack").queue();
                }
                return;
            }
            int pv = Integer.parseInt(hero.get(3));
            hero.clear();
            hero.add(Integer.toString(++level10));
            hero.add(Integer.toString(card -= 10));
            hero.add("false");
            hero.add(Integer.toString(pv));
            hero.add(Long.toString(System.currentTimeMillis()));
            heroe.put(c2, hero);
            data.getProfils().get(user.getId()).setMoney(money -= cout);
            data.getProfils().get(user.getId()).setHeroe(heroe);
            if (lang == command.Language.fr) {
                channel.sendMessage("Votre hero " + c2 + " a été amelioré au niveau " + level10 + " pour " + cout + "$")
                        .queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("Your hero " + c2 + " upgraded to level " + level10 + " for " + cout + "$").queue();
            }
        }
    }
}
