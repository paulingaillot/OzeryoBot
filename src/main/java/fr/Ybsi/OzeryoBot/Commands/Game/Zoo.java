/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import fr.Ybsi.OzeryoBot.Utils.color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Zoo {
    /*
     * Enabled aggressive block sorting Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @command(name = "zoo", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Game)
    private void zoo(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
                     ProfilData data, command.Language lang) {
        String c1;
        try {
            c1 = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            c1 = "";
        }
        StringBuilder builder1 = new StringBuilder();
        for (String str : args) {
            if (str.equals(args[0]))
                continue;
            if (builder1.length() > 0) {
                builder1.append(" ");
            }
            builder1.append(str);
        }
        String c2 = builder1.toString();
        if (c1.equals("")) {
            EmbedBuilder builder;
            block29:
            {
                builder = new EmbedBuilder();
                builder.setTitle("Zoo");
                builder.setAuthor(user.getName(), null, user.getAvatarUrl());
                builder.setColor(color.couleurAleatoire(user));
                HashMap<String, Integer> building = data.getProfils().get(user.getId()).getBuilding();
                HashMap<String, ArrayList<String>> animaux = data.getProfils().get(user.getId()).getPet();
                String Active_Pet = TextFileWriter
                        .read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/pet.txt");
                Integer levelZoo = building.get("cirque");
                int i = 0;
                try {
                    Iterator<ArrayList<String>> iterator = animaux.values().iterator();
                    do {
                        int level;
                        ArrayList<String> list;
                        int EXP2;
                        if (!iterator.hasNext()) {
                            if (lang == command.Language.fr) {
                                builder.setDescription("Faites ``=zoo select`` pour selectionner un pet. " + i + " / "
                                        + levelZoo + " places utilisés.");
                            }
                            if (lang == command.Language.en) {
                                builder.setDescription("Type ``=zoo select`` to select a pet. " + i + " / " + levelZoo
                                        + " used slot.");
                            }
                            break;
                        }
                        ArrayList<String> pet = iterator.next();
                        String message1 = pet.get(1);
                        String nom = pet.get(0);
                        HashMap<String, ArrayList<String>> activePet = data.getProfils().get(user.getId()).getPet();
                        String bonus = TextFileWriter
                                .read("/home/DiscordBot/Rasberry/données/bot/Pets/" + nom.replace(".txt", ""));
                        try {
                            list = data.getProfils().get(user.getId()).getPet().get(nom);
                        } catch (NullPointerException e) {
                            list = null;
                        }
                        try {
                            EXP2 = Integer.parseInt(data.getProfils().get(user.getId()).getPet().get(nom).get(1));
                        } catch (NullPointerException e) {
                            EXP2 = 0;
                        }
                        try {
                            double operation = 1 * EXP2 / 10;
                            double math = Math.sqrt(operation);
                            level = (int) Math.round(math);
                        } catch (NullPointerException e) {
                            level = 0;
                        }
                        if (lang == command.Language.fr) {
                            message1 = Active_Pet.equals(nom) ? "Pet Actif" : "En reserve";
                        }
                        if (lang == command.Language.en) {
                            message1 = Active_Pet.equals(nom) ? "Active Pet" : "Inactive";
                        }
                        builder.addField(String.valueOf(nom) + " | Level " + level, message1, true);
                        ++i;
                    } while (true);
                } catch (NullPointerException e) {
                    if (lang == command.Language.fr) {
                        builder.addField("Error",
                                "Vous n'avez pour l'instant aucun pet. vous pouvez tenter d'en gagner avec la commande ``=bid``",
                                true);
                    }
                    if (lang != command.Language.en)
                        break block29;
                    builder.addField("Error",
                            "You don't have any pet yet. You can try to win pet by usin the command ``=bid``", true);
                }
            }
            channel.sendMessage(builder.build()).queue();
            return;
        }
        if (!c1.equals("select"))
            return;
        String nom = c2;
        int pet1 = 0;
        try {
            pet1 = Integer.parseInt(data.getProfils().get(user.getId()).getPet().get(c2).get(1));
        } catch (NullPointerException e) {
            if (lang == command.Language.fr) {
                channel.sendMessage("Désolé mais vous ne pouvez selectionner un pet que vous ne possedez pas.").queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("You can't select a pet you don't have.").queue();
            return;
        }
        long last = Long.parseLong(
                TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/lastselect.txt"));
        if (pet1 != 0) {
            if (last + 1800000L < System.currentTimeMillis()) {
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/pet.txt", nom, 1);
                if (lang == command.Language.fr) {
                    channel.sendMessage("Votre pet actif est desormais : **" + nom + "**").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("Your active pet is now : **" + nom + "**").queue();
                }
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/lastselect.txt",
                        "" + System.currentTimeMillis(), 1);
                return;
            }
            long delay = last + 1800000L - System.currentTimeMillis();
            int min = (int) (delay / 60000L);
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous pourrez changer de pet dans " + min + " minutes.").queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("You will be able to change your pet in " + min + " minutes.").queue();
            return;
        }
        if (lang == command.Language.fr) {
            channel.sendMessage("Désolé mais vous ne pouvez selectionner un pet que vous ne possedez pas.").queue();
        }
        if (lang != command.Language.en)
            return;
        channel.sendMessage("You can't select a pet you don't have.").queue();
    }

    /*
     * Enabled aggressive block sorting Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @command(name = "pet", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Game)
    private void pet(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
                     ProfilData data, command.Language lang) {
        String c1;
        int EXP2;
        try {
            c1 = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            c1 = "";
        }
        StringBuilder builder1 = new StringBuilder();
        for (String str : args) {
            if (str.equals(args[0]))
                continue;
            if (builder1.length() > 0) {
                builder1.append(" ");
            }
            builder1.append(str);
        }
        String c2 = builder1.toString();
        String pet = TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/pet.txt");
        HashMap<String, ArrayList<String>> activePet = data.getProfils().get(user.getId()).getPet();
        String bonus = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Pets/" + pet.replace(".txt", ""));
        try {
            ArrayList<String> list = data.getProfils().get(user.getId()).getPet().get(pet);
        } catch (NullPointerException e) {
            Object list = null;
        }
        try {
            EXP2 = Integer.parseInt(data.getProfils().get(user.getId()).getPet().get(pet).get(1));
        } catch (NullPointerException e) {
            EXP2 = 0;
        }
        if (c1.equals("")) {
            int level;
            if (activePet.equals("0")) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous n'avez aucun pet selectionné.").queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("You don't have any pet selected.").queue();
                return;
            }
            try {
                double operation = 1 * EXP2 / 10;
                double math = Math.sqrt(operation);
                level = (int) Math.round(math);
            } catch (NullPointerException e) {
                level = 0;
            }
            int bonus2 = bonus.equals("bois") || bonus.equals("beton") || bonus.equals("acier") || bonus.equals("verre")
                    || bonus.equals("pierre") || bonus.equals("plastique") ? 10 * level
                    : (bonus.equals("res") ? 5 * level : 3 * level);
            String bonus3 = "+" + bonus2 + "%";
            String messages = "";
            if (lang == command.Language.fr) {
                messages = "gain de ";
            }
            if (lang == command.Language.en) {
                messages = "gain of";
            }
            if (bonus.equals("acier")) {
                if (lang == command.Language.fr) {
                    messages = "gain d'";
                }
                if (lang == command.Language.en) {
                    messages = "gain of";
                }
            }
            if (bonus.equals("regen")) {
                bonus = "";
                if (lang == command.Language.fr) {
                    messages = "régénération de mana";
                }
                if (lang == command.Language.en) {
                    messages = "mana regeneration";
                }
                bonus2 = 300 - bonus2;
                bonus3 = "1 mana toute les " + bonus2 + " secondes";
            }
            if (bonus.equals("mana")) {
                bonus = "";
                if (lang == command.Language.fr) {
                    messages = "maximum de mana";
                }
                if (lang == command.Language.en) {
                    messages = "mana limit";
                }
            }
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle(pet);
            builder.setColor(color.couleurAleatoire(user));
            builder.setFooter(guild.getId(), guild.getIconUrl());
            if (lang == command.Language.fr) {
                builder.setDescription("En ameliorant ce pet vous boosterez votre " + messages + bonus);
            }
            if (lang == command.Language.en) {
                builder.setDescription("By upgrade this pet you will increate your " + messages + bonus);
            }
            builder.addBlankField(false);
            builder.addField("Xp", String.valueOf(EXP2) + "Xp", true);
            builder.addField("Level", "" + level, true);
            builder.addBlankField(false);
            builder.addField("Bonus", bonus3, true);
            channel.sendMessage(builder.build()).queue();
            return;
        }
        if (!c1.equals("select"))
            return;
        String nom = c2;
        int pet1 = 0;
        try {
            pet1 = Integer.parseInt(data.getProfils().get(user.getId()).getPet().get(c2).get(1));
        } catch (NullPointerException e) {
            if (lang == command.Language.fr) {
                channel.sendMessage("Désolé mais vous ne pouvez selectionner un pet que vous ne possedez pas.").queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("Sorry but you can't select a pet you don't have.").queue();
            return;
        }
        long last = Long.parseLong(
                TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/lastselect.txt"));
        if (pet1 != 0) {
            if (last + 1800000L < System.currentTimeMillis()) {
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/pet.txt", nom, 1);
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/lastselect.txt",
                        Long.toString(System.currentTimeMillis()), 1);
                if (lang == command.Language.fr) {
                    channel.sendMessage("Votre pet actif est desormais : **" + nom + "**").queue();
                }
                if (lang != command.Language.en)
                    return;
                channel.sendMessage("Your active pet is now : **" + nom + "**").queue();
                return;
            }
            long delay = last + 1800000L - System.currentTimeMillis();
            int min = (int) (delay / 60000L);
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous pourrez changer de pet dans " + min + " minutes.").queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("You will be able to change your pet in " + min + " minutes.").queue();
            return;
        }
        if (lang == command.Language.fr) {
            channel.sendMessage("Désolé mais vous ne pouvez selectionner un pet que vous ne possedez pas.").queue();
        }
        if (lang != command.Language.en)
            return;
        channel.sendMessage("Sorry but you can't select a pet you don't have.").queue();
    }
}
