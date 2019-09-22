/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import fr.Ybsi.OzeryoBot.Utils.color;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class Habitations {
    @command(name = "houses", type = command.ExecutorType.ALL, topic = command.Topics.Game)
    private void houses(Message message, Guild guild, String[] args, User user, MessageChannel channel, String arg,
            ProfilData data, command.Language lang) {
        String c1;
        try {
            c1 = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            c1 = "";
        }
        if (c1.equals("")) {
            EmbedBuilder builder;
            int i;
            block15: {
                builder = new EmbedBuilder();
                builder.setTitle("Habitations");
                builder.setAuthor(user.getName(), null, user.getAvatarUrl());
                builder.setColor(color.couleurAleatoire(user));
                HashMap<String, ArrayList<String>> animaux = data.getProfils().get(user.getId()).getHouses();
                i = 0;
                try {
                    for (ArrayList<String> pet : animaux.values()) {
                        String message1 = String.valueOf(pet.get(1));
                        if (lang == command.Language.fr) {
                            builder.addField(String.valueOf(pet.get(0)),
                                    "Bonus : " + message1 + "% d'habitants en plus", true);
                        }
                        if (lang == command.Language.en) {
                            builder.addField(String.valueOf(pet.get(0)), "Bonus : " + message1 + "% people more", true);
                        }
                        ++i;
                    }
                } catch (NullPointerException e) {
                    if (lang == command.Language.fr) {
                        builder.addField("Error", "Vous n'avez pour l'instant aucune maisons", true);
                    }
                    if (lang != command.Language.en)
                        break block15;
                    builder.addField("Error", "You don't have any special houses", true);
                }
            }
            if (i == 0) {
                if (lang == command.Language.fr) {
                    builder.addField("Vide",
                            "Vous n'avez actuelement aucunes maisons, commencer a en acheter des a present avec la commande ``=bid``.",
                            true);
                }
                if (lang == command.Language.en) {
                    builder.addField("Anything",
                            "You don't have any houses, you can win houses by using the command ``=bid``.", true);
                }
            } else {
                if (lang == command.Language.fr) {
                    builder.setDescription("Vous avez actuelement " + i + " maisons.");
                }
                if (lang == command.Language.en) {
                    builder.setDescription("You have actually " + i + " houses.");
                }
            }
            channel.sendMessage(builder.build()).queue();
        }
    }
}
