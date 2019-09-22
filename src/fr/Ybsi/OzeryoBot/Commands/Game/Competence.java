/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import fr.Ybsi.OzeryoBot.Utils.color;
import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class Competence {
    @command(name = "competence", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Game)
    private void competence(Message message, String[] args, MessageChannel channel, Guild guild, JDA jda,
            ProfilData data, User user) {
        HashMap<String, Integer> competence;
        int level;
        try {
            competence = data.getProfils().get(user.getId()).getCompetence();
        } catch (NullPointerException e) {
            competence = new HashMap<String, Integer>();
            competence.put("guerre", 0);
            competence.put("ville", 0);
            competence.put("divinités", 0);
            competence.put("héros", 0);
            competence.put("intelligence", 0);
            competence.put("pays", 0);
            data.getProfils().get(user.getId()).setCompetence(competence);
        }
        int Game_EXP = data.getProfils().get(user.getId()).getXp();
        try {
            double operation = 3 * Game_EXP / 4;
            double math = Math.sqrt(operation);
            level = (int) Math.round(math);
        } catch (NullPointerException e) {
            level = 0;
        }
        int points = (int) Math.floor(level / 100);
        Iterator<Integer> math = competence.values().iterator();
        while (math.hasNext()) {
            int used_point = (Integer) math.next();
            points -= used_point;
        }
        String c1 = "";
        try {
            c1 = args[0];
        } catch (Exception e) {
            c1 = "";
        }
        if (c1.equals("")) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle(":deciduous_tree: Arbre de compétences");
            builder.setAuthor(user.getName(), user.getAvatarUrl());
            builder.setColor(color.couleurAleatoire(user));
            builder.setFooter(guild.getName(), guild.getIconUrl());
            builder.addField("Branche de la guerre :crossed_swords: :", "", true);
            builder.addField("Branches de la ville :hotel: :", "", true);
            builder.addField("Branche des divinités :classical_building: :  :", "", true);
            builder.addField("Branche des héros :statue_of_liberty: : :", "", true);
            builder.addField("Branche de l'intelligence :dove: : :", "", true);
            builder.addField("Branche du pays :map: : :", "", true);
        }
    }
}
