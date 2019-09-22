/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.Utils.Premium;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import fr.Ybsi.OzeryoBot.Utils.color;
import java.awt.Color;
import java.util.Date;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class Quests {
    @command(name="quest", abbrev="sol", type=command.ExecutorType.ALL, descfr="usage : [BETA] creer une ville et developpe la au fil de temps", topic=command.Topics.Game)
    private void quest(MessageChannel channel, User user, Guild guild, String[] args, command.Language lang) {
        int totalpoints3;
        int totalpoints2;
        int totalpoints1;
        String quest1 = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Quests/quest1.txt");
        String quest2 = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Quests/quest2.txt");
        String quest3 = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Quests/quest3.txt");
        int day = new Date().getDay();
        try {
            totalpoints1 = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Quests/" + quest1));
        }
        catch (NumberFormatException e) {
            totalpoints1 = 0;
        }
        try {
            totalpoints2 = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Quests/" + quest2));
        }
        catch (NumberFormatException e) {
            totalpoints2 = 0;
        }
        try {
            totalpoints3 = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Quests/" + quest3));
        }
        catch (NumberFormatException e) {
            totalpoints3 = 0;
        }
        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/quests/");
        int points1 = 0;
        try {
            points1 = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/quests/" + day + "|" + quest1 + ".txt"));
        }
        catch (NumberFormatException e) {
            points1 = totalpoints1;
        }
        int points2 = 0;
        try {
            points2 = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/quests/" + day + "|" + quest2 + ".txt"));
        }
        catch (NumberFormatException e) {
            points2 = totalpoints2;
        }
        int points3 = 0;
        try {
            points3 = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/quests/" + day + "|" + quest3 + ".txt"));
        }
        catch (NumberFormatException e) {
            points3 = totalpoints3;
        }
        if (lang == command.Language.fr) {
            quest1 = quest1.equals("tr") ? "Recuperer 5 cf" : (quest1.equals("hr") ? "Recuperer 5 hourly" : (quest1.equals("mana") ? "Depenser 100 mana" : (quest1.equals("atk") ? "Reussir 3 attaques" : (quest1.equals("def") ? "Reussir 3 defenses" : (quest1.equals("exp") ? "Recolter 500 EXP" : (quest1.equals("invest") ? "Investir 200 ressources dans le pays" : (quest1.equals("vote") ? "Voter pour le bot" : (quest1.equals("shop") ? "Acheter 30 items dans le Shop" : (quest1.equals("soldier") ? "Entrainer 5000 soldats" : (quest1.equals("jetons") ? "Recuperer 10 jetons" : (quest1.equals("materiau") ? "Recuperer 200 materiaux" : (quest1.equals("bois") ? "Recuperer 50 bois" : (quest1.equals("acier") ? "Recuperer 50 acier" : (quest1.equals("brique") ? "Recuperer 50 briques" : (quest1.equals("verre") ? "Recuperer 50 verre" : (quest1.equals("pierre") ? "Recuperer 50 pierre" : (quest1.equals("petrole") ? "Recuperer 50 petrole" : "aucune")))))))))))))))));
        }
        if (lang == command.Language.en) {
            quest1 = quest1.equals("tr") ? "Collect 5 cf" : (quest1.equals("hr") ? "Collect 5 hourly" : (quest1.equals("mana") ? "Spend 100 mana" : (quest1.equals("atk") ? "Achieve 3 attacks" : (quest1.equals("def") ? "Achieve 3 defenses" : (quest1.equals("exp") ? "Recolt 500 Xp" : (quest1.equals("invest") ? "Invest 200 ressources in the country" : (quest1.equals("vote") ? "Vote for the bot" : (quest1.equals("shop") ? "Buy 30 items in the Shop" : (quest1.equals("soldier") ? "Train 5000 soldats" : (quest1.equals("jetons") ? "Collect 10 jetons" : (quest1.equals("materiau") ? "Collect 200 materiaux" : (quest1.equals("bois") ? "Collect 50 bois" : (quest1.equals("acier") ? "Collect 50 acier" : (quest1.equals("brique") ? "Collect 50 briques" : (quest1.equals("verre") ? "Collect 50 verre" : (quest1.equals("pierre") ? "Collect 50 pierre" : (quest1.equals("petrole") ? "Collect 50 petrole" : "any")))))))))))))))));
        }
        if (lang == command.Language.fr) {
            quest2 = quest2.equals("tr") ? "Recuperer 5 cf" : (quest2.equals("hr") ? "Recuperer 5 hourly" : (quest2.equals("mana") ? "Depenser 100 mana" : (quest2.equals("atk") ? "Reussir 3 attaques" : (quest2.equals("def") ? "Reussir 3 defenses" : (quest2.equals("exp") ? "Recolter 500 EXP" : (quest2.equals("invest") ? "Investir 200 ressources dans le pays" : (quest2.equals("vote") ? "Voter pour le bot" : (quest2.equals("shop") ? "Acheter 30 items dans le Shop" : (quest2.equals("soldier") ? "Entrainer 5000 soldats" : (quest2.equals("jetons") ? "Recuperer 10 jetons" : (quest2.equals("materiau") ? "Recuperer 200 materiaux" : (quest2.equals("bois") ? "Recuperer 50 bois" : (quest2.equals("acier") ? "Recuperer 50 acier" : (quest2.equals("brique") ? "Recuperer 50 briques" : (quest2.equals("verre") ? "Recuperer 50 verre" : (quest2.equals("pierre") ? "Recuperer 50 pierre" : (quest2.equals("petrole") ? "Recuperer 50 petrole" : "aucune")))))))))))))))));
        }
        if (lang == command.Language.en) {
            quest2 = quest2.equals("tr") ? "Collect 5 cf" : (quest2.equals("hr") ? "Collect 5 hourly" : (quest2.equals("mana") ? "Spend 100 mana" : (quest2.equals("atk") ? "Achieve 3 attacks" : (quest2.equals("def") ? "Achieve 3 defenses" : (quest2.equals("exp") ? "Recolt 500 Xp" : (quest2.equals("invest") ? "Invest 200 ressources in the country" : (quest2.equals("vote") ? "Vote for the bot" : (quest2.equals("shop") ? "Buy 30 items in the Shop" : (quest2.equals("soldier") ? "Train 5000 soldats" : (quest2.equals("jetons") ? "Collect 10 jetons" : (quest2.equals("materiau") ? "Collect 200 materiaux" : (quest2.equals("bois") ? "Collect 50 bois" : (quest2.equals("acier") ? "Collect 50 acier" : (quest2.equals("brique") ? "Collect 50 briques" : (quest2.equals("verre") ? "Collect 50 verre" : (quest2.equals("pierre") ? "Collect 50 pierre" : (quest2.equals("petrole") ? "Collect 50 petrole" : "any")))))))))))))))));
        }
        if (lang == command.Language.fr) {
            quest3 = quest3.equals("tr") ? "Recuperer 5 cf" : (quest3.equals("hr") ? "Recuperer 5 hourly" : (quest3.equals("mana") ? "Depenser 100 mana" : (quest3.equals("atk") ? "Reussir 3 attaques" : (quest3.equals("def") ? "Reussir 3 defenses" : (quest3.equals("exp") ? "Recolter 500 EXP" : (quest3.equals("invest") ? "Investir 200 ressources dans le pays" : (quest3.equals("vote") ? "Voter pour le bot" : (quest3.equals("shop") ? "Acheter 30 items dans le Shop" : (quest3.equals("soldier") ? "Entrainer 5000 soldats" : (quest3.equals("jetons") ? "Recuperer 10 jetons" : (quest3.equals("materiau") ? "Recuperer 200 materiaux" : (quest3.equals("bois") ? "Recuperer 50 bois" : (quest3.equals("acier") ? "Recuperer 50 acier" : (quest3.equals("brique") ? "Recuperer 50 briques" : (quest3.equals("verre") ? "Recuperer 50 verre" : (quest3.equals("pierre") ? "Recuperer 50 pierre" : (quest3.equals("petrole") ? "Recuperer 50 petrole" : "aucune")))))))))))))))));
        }
        if (lang == command.Language.en) {
            quest3 = quest3.equals("tr") ? "Collect 5 cf" : (quest3.equals("hr") ? "Collect 5 hourly" : (quest3.equals("mana") ? "Spend 100 mana" : (quest3.equals("atk") ? "Achieve 3 attacks" : (quest3.equals("def") ? "Achieve 3 defenses" : (quest3.equals("exp") ? "Recolt 500 Xp" : (quest3.equals("invest") ? "Invest 200 ressources in the country" : (quest3.equals("vote") ? "Vote for the bot" : (quest3.equals("shop") ? "Buy 30 items in the Shop" : (quest3.equals("soldier") ? "Train 5000 soldats" : (quest3.equals("jetons") ? "Collect 10 jetons" : (quest3.equals("materiau") ? "Collect 200 materiaux" : (quest3.equals("bois") ? "Collect 50 bois" : (quest3.equals("acier") ? "Collect 50 acier" : (quest3.equals("brique") ? "Collect 50 briques" : (quest3.equals("verre") ? "Collect 50 verre" : (quest3.equals("pierre") ? "Collect 50 pierre" : (quest3.equals("petrole") ? "Collect 50 petrole" : "any")))))))))))))))));
        }
        String emoji1 = ":negative_squared_cross_mark: ";
        if (points1 >= totalpoints1) {
            emoji1 = ":white_check_mark: ";
        }
        String emoji2 = ":negative_squared_cross_mark: ";
        if (points2 >= totalpoints2) {
            emoji2 = ":white_check_mark: ";
        }
        String emoji3 = ":negative_squared_cross_mark: ";
        if (points3 >= totalpoints3) {
            emoji3 = ":white_check_mark: ";
        }
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(user.getName(), null, user.getAvatarUrl());
        if (lang == command.Language.fr) {
            builder.setTitle("Menu des quetes");
        }
        if (lang == command.Language.en) {
            builder.setTitle("Quest Menu");
        }
        builder.setColor(color.couleurAleatoire(user));
        builder.setFooter(guild.getName(), guild.getIconUrl());
        builder.addField(String.valueOf(emoji1) + "Quest 1", String.valueOf(quest1) + " (" + points1 + "/" + totalpoints1 + ")", true);
        builder.addField(String.valueOf(emoji2) + "Quest 2", String.valueOf(quest2) + " (" + points2 + "/" + totalpoints2 + ")", true);
        if (Premium.Premium(user)) {
            builder.addField(String.valueOf(emoji3) + "Quest 3", String.valueOf(quest3) + " (" + points3 + "/" + totalpoints3 + ")", true);
        }
        channel.sendMessage(builder.build()).queue();
    }
}

