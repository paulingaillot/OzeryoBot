/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import fr.Ybsi.OzeryoBot.Utils.Quest;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import fr.Ybsi.OzeryoBot.Utils.color;
import java.awt.Color;
import java.util.HashMap;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class Shop {
    @command(name="shop", type=command.ExecutorType.ALL, descfr="Affiche le level d'un joueur", topic=command.Topics.Game)
    private void shop(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda, command.Language lang) {
        int Pstock;
        int pailleStock;
        int Bstock;
        int Astock;
        int cuirStock;
        int Aleastock;
        int Vstock;
        try {
            Bstock = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/bois.txt"));
        }
        catch (NumberFormatException e) {
            Bstock = 0;
        }
        try {
            Astock = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/argile.txt"));
        }
        catch (NumberFormatException e) {
            Astock = 0;
        }
        try {
            cuirStock = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/cuir.txt"));
        }
        catch (NumberFormatException e) {
            cuirStock = 0;
        }
        try {
            Vstock = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/pierre.txt"));
        }
        catch (NumberFormatException e) {
            Vstock = 0;
        }
        try {
            pailleStock = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/paille.txt"));
        }
        catch (NumberFormatException e) {
            pailleStock = 0;
        }
        try {
            Pstock = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/fer.txt"));
        }
        catch (NumberFormatException e) {
            Pstock = 0;
        }
        try {
            Aleastock = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/alea.txt"));
        }
        catch (NumberFormatException e) {
            Aleastock = 0;
        }
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(color.couleurAleatoire(user));
        builder.setTitle(":shopping_cart:   | Shop | :shopping_cart: ");
        builder.addBlankField(false);
        if (lang == command.Language.fr) {
            builder.addField(":one: Bois", "**prix :** 5.000$ \n **Stock :** " + Bstock, true);
        }
        if (lang == command.Language.en) {
            builder.addField(":one: Wood", "**price :** 5.000$ \n **Stock :** " + Bstock, true);
        }
        if (lang == command.Language.fr) {
            builder.addField(":two: Argile ", "**prix :** 5.000$  \n **Stock :** " + Astock, true);
        }
        if (lang == command.Language.en) {
            builder.addField(":two: Clay ", "**price :** 5.000$  \n **Stock :** " + Astock, true);
        }
        if (lang == command.Language.fr) {
            builder.addField(":three: Cuir", "**prix :** 5.000$ \n **Stock :** " + cuirStock, true);
        }
        if (lang == command.Language.en) {
            builder.addField(":three: Leather", "**price :** 5.000$ \n **Stock :** " + cuirStock, true);
        }
        if (lang == command.Language.fr) {
            builder.addField(":four: Pierre", "**prix :** 5.000$ \n **Stock :** " + Vstock, true);
        }
        if (lang == command.Language.en) {
            builder.addField(":four: Stone", "**price :** 5.000$ \n **Stock :** " + Vstock, true);
        }
        builder.addBlankField(true);
        if (lang == command.Language.fr) {
            builder.addField(":five: Paille", "**prix :** 5.000$ \n **Stock :** " + pailleStock, true);
        }
        if (lang == command.Language.en) {
            builder.addField(":five: Straw", "**price :** 5.000$ \n **Stock :** " + pailleStock, true);
        }
        if (lang == command.Language.fr) {
            builder.addField(":six: Pétrole", "**prix :** 5.000$ \n **Stock :** " + Pstock, true);
        }
        if (lang == command.Language.en) {
            builder.addField(":six: Pétrole", "**price :** 5.000$ \n **Stock :** " + Pstock, true);
        }
        if (lang == command.Language.fr) {
            builder.addField(":seven: Materiaux aleatoire", "**prix :** 2.500$ \n **Stock :** " + Aleastock, true);
        }
        if (lang == command.Language.en) {
            builder.addField(":seven: Materiaux aleatoire", "**price :** 2.500$ \n **Stock :** " + Aleastock, true);
        }
        builder.setFooter(guild.getName(), guild.getIconUrl());
        channel.sendMessage(builder.build()).queue();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @command(name="buy", type=command.ExecutorType.ALL, descfr="Affiche le level d'un joueur", topic=command.Topics.Game)
    private void buy(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda, ProfilData data, command.Language lang) {
        int c1;
        int c2;
        int Bstock;
        long money = data.getProfils().get(user.getId()).getMoney();
        HashMap<String, Integer> res = data.getProfils().get(user.getId()).getRes();
        try {
            c1 = Integer.parseInt(args[0]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            c1 = 0;
        }
        catch (NumberFormatException e) {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez donner un nombre valide.").queue();
            }
            if (lang != command.Language.en) return;
            channel.sendMessage("Please type a valid number.").queue();
            return;
        }
        try {
            c2 = Integer.parseInt(args[1]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            c2 = 0;
        }
        catch (NumberFormatException e) {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez donner un nombre valide.").queue();
            }
            if (lang != command.Language.en) return;
            channel.sendMessage("Please type a valid number.").queue();
            return;
        }
        if (c2 <= 0) {
            if (lang == command.Language.fr) {
                channel.sendMessage("Syntaxe : ``=buy [numero] [quantité]``.").queue();
            }
            if (lang != command.Language.en) return;
            channel.sendMessage("Syntax : ``=buy [number] [amount]``.").queue();
            return;
        }
        if (c1 == 1) {
            int Bstock2;
            int bois = res.get("bois");
            try {
                Bstock2 = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/bois.txt"));
            }
            catch (NumberFormatException e) {
                Bstock2 = 0;
            }
            if (c2 > Bstock2) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Il n'y a plus asser de stock de bois").queue();
                }
                if (lang != command.Language.en) return;
                channel.sendMessage("There is not enough wood").queue();
                return;
            }
            if (money >= (long)(5000 * c2)) {
                int Pigeon;
                Bstock2 -= c2;
                money -= (long)(5000 * c2);
                Quest.Quest("shop", user, channel, c2);
                res.put("bois", bois += c2);
                try {
                    data.getProfils().get(user.getId()).setRes(res);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setRes(res);
                }
                try {
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Shop/bois.txt", Integer.toString(Bstock2), 1);
                try {
                    Pigeon = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Achievement/Pigeon.txt"));
                }
                catch (NumberFormatException e) {
                    Pigeon = 0;
                }
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Achievement/Pigeon.txt", Integer.toString(Pigeon += c2), 1);
                if (lang == command.Language.fr) {
                    channel.sendMessage("Bravo, Vous venez d'acheter " + c2 + " bois.").queue();
                }
                if (lang != command.Language.en) return;
                channel.sendMessage("Well, you just buy " + c2 + " wood.").queue();
                return;
            }
            if (lang == command.Language.fr) {
                channel.sendMessage("Tu n'as pas asser d'argent pour acheter ceci.").queue();
            }
            if (lang != command.Language.en) return;
            channel.sendMessage("You don't have enough money to buy this.").queue();
            return;
        }
        if (c1 == 2) {
            int Astock;
            int argile = res.get("argile");
            try {
                Astock = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/argile.txt"));
            }
            catch (NumberFormatException e) {
                Astock = 0;
            }
            if (c2 > Astock) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Il n'y a plus asser de stock de argile").queue();
                }
                if (lang != command.Language.en) return;
                channel.sendMessage("There is not enough clay").queue();
                return;
            }
            if (money >= (long)(5000 * c2)) {
                int Pigeon;
                Astock -= c2;
                money -= (long)(5000 * c2);
                Quest.Quest("shop", user, channel, c2);
                res.put("argile", argile += c2);
                try {
                    data.getProfils().get(user.getId()).setRes(res);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setRes(res);
                }
                try {
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Shop/argile.txt", Integer.toString(Astock), 1);
                try {
                    Pigeon = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Achievement/Pigeon.txt"));
                }
                catch (NumberFormatException e) {
                    Pigeon = 0;
                }
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Achievement/Pigeon.txt", Integer.toString(Pigeon += c2), 1);
                if (lang == command.Language.fr) {
                    channel.sendMessage("Bravo, Vous venez d'acheter " + c2 + " argile.").queue();
                }
                if (lang != command.Language.en) return;
                channel.sendMessage("Well, you just buy " + c2 + " clay.").queue();
                return;
            }
            if (lang == command.Language.fr) {
                channel.sendMessage("Tu n'as pas asser d'argent pour acheter ceci.").queue();
            }
            if (lang != command.Language.en) return;
            channel.sendMessage("You don't have enough money to buy this.").queue();
            return;
        }
        if (c1 == 3) {
            int Bstock3;
            int cuir = res.get("cuir");
            try {
                Bstock3 = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/cuir.txt"));
            }
            catch (NumberFormatException e) {
                Bstock3 = 0;
            }
            if (c2 > Bstock3) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Il n'y a plus asser de stock de cuir").queue();
                }
                if (lang != command.Language.en) return;
                channel.sendMessage("There is not enough leather").queue();
                return;
            }
            if (money >= (long)(5000 * c2)) {
                int Pigeon;
                Bstock3 -= c2;
                money -= (long)(5000 * c2);
                Quest.Quest("shop", user, channel, c2);
                res.put("cuir", cuir += c2);
                try {
                    data.getProfils().get(user.getId()).setRes(res);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setRes(res);
                }
                try {
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Shop/cuir.txt", Integer.toString(Bstock3), 1);
                try {
                    Pigeon = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Achievement/Pigeon.txt"));
                }
                catch (NumberFormatException e) {
                    Pigeon = 0;
                }
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Achievement/Pigeon.txt", Integer.toString(Pigeon += c2), 1);
                if (lang == command.Language.fr) {
                    channel.sendMessage("Bravo, Vous venez d'acheter " + c2 + " cuir.").queue();
                }
                if (lang != command.Language.en) return;
                channel.sendMessage("Well, you just buy " + c2 + " leather.").queue();
                return;
            }
            if (lang == command.Language.fr) {
                channel.sendMessage("Tu n'as pas asser d'argent pour acheter ceci.").queue();
            }
            if (lang != command.Language.en) return;
            channel.sendMessage("You don't have enough money to buy this.").queue();
            return;
        }
        if (c1 == 4) {
            int Vstock;
            int pierre = res.get("pierre");
            try {
                Vstock = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/pierre.txt"));
            }
            catch (NumberFormatException e) {
                Vstock = 0;
            }
            if (c2 > Vstock) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Il n'y a plus asser de stock de pierre").queue();
                }
                if (lang != command.Language.en) return;
                channel.sendMessage("There is not enough stone").queue();
                return;
            }
            if (money >= (long)(5000 * c2)) {
                int Pigeon;
                Vstock -= c2;
                money -= (long)(5000 * c2);
                Quest.Quest("shop", user, channel, c2);
                res.put("pierre", pierre += c2);
                try {
                    data.getProfils().get(user.getId()).setRes(res);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setRes(res);
                }
                try {
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Shop/pierre.txt", Integer.toString(Vstock), 1);
                try {
                    Pigeon = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Achievement/Pigeon.txt"));
                }
                catch (NumberFormatException e) {
                    Pigeon = 0;
                }
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Achievement/Pigeon.txt", Integer.toString(Pigeon += c2), 1);
                if (lang == command.Language.fr) {
                    channel.sendMessage("Bravo, Vous venez d'acheter " + c2 + " pierre.").queue();
                }
                if (lang != command.Language.en) return;
                channel.sendMessage("Well, you just buy " + c2 + " stone.").queue();
                return;
            }
            if (lang == command.Language.fr) {
                channel.sendMessage("Tu n'as pas asser d'argent pour acheter ceci.").queue();
            }
            if (lang != command.Language.en) return;
            channel.sendMessage("You don't have enough money to buy this.").queue();
            return;
        }
        if (c1 == 5) {
            int Pstock;
            int paille = res.get("paille");
            try {
                Pstock = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/paille.txt"));
            }
            catch (NumberFormatException e) {
                Pstock = 0;
            }
            if (c2 > Pstock) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Il n'y a plus asser de stock de paille").queue();
                }
                if (lang != command.Language.en) return;
                channel.sendMessage("There is not enough straw").queue();
                return;
            }
            if (money >= (long)(5000 * c2)) {
                int Pigeon;
                Pstock -= c2;
                money -= (long)(5000 * c2);
                Quest.Quest("shop", user, channel, c2);
                res.put("paille", paille += c2);
                try {
                    data.getProfils().get(user.getId()).setRes(res);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setRes(res);
                }
                try {
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Shop/paille.txt", Integer.toString(Pstock), 1);
                try {
                    Pigeon = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Achievement/Pigeon.txt"));
                }
                catch (NumberFormatException e) {
                    Pigeon = 0;
                }
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Achievement/Pigeon.txt", Integer.toString(Pigeon += c2), 1);
                if (lang == command.Language.fr) {
                    channel.sendMessage("Bravo, Vous venez d'acheter " + c2 + " paille.").queue();
                }
                if (lang != command.Language.en) return;
                channel.sendMessage("Well, you just buy " + c2 + " straw.").queue();
                return;
            }
            if (lang == command.Language.fr) {
                channel.sendMessage("Tu n'as pas asser d'argent pour acheter ceci.").queue();
            }
            if (lang != command.Language.en) return;
            channel.sendMessage("You don't have enough money to buy this.").queue();
            return;
        }
        if (c1 == 6) {
            int Bstock4;
            int fer = res.get("petrole");
            try {
                Bstock4 = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/fer.txt"));
            }
            catch (NumberFormatException e) {
                Bstock4 = 0;
            }
            if (c2 > Bstock4) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Il n'y a plus asser de stock de fer").queue();
                }
                if (lang != command.Language.en) return;
                channel.sendMessage("There is not enough iron").queue();
                return;
            }
            if (money >= (long)(5000 * c2)) {
                Bstock4 -= c2;
                money -= (long)(5000 * c2);
                Quest.Quest("shop", user, channel, c2);
                res.put("petrole", fer += c2);
                try {
                    data.getProfils().get(user.getId()).setRes(res);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setRes(res);
                }
                try {
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Shop/fer.txt", Integer.toString(Bstock4), 1);
                int Pigeon = data.getProfils().get(user.getId()).getPigeon();
                data.getProfils().get(user.getId()).setPigeon(Pigeon += c2);
                if (lang == command.Language.fr) {
                    channel.sendMessage("Bravo, Vous venez d'acheter " + c2 + " fer.").queue();
                }
                if (lang != command.Language.en) return;
                channel.sendMessage("Well, you just buy " + c2 + " iron.").queue();
                return;
            }
            if (lang == command.Language.fr) {
                channel.sendMessage("Tu n'as pas asser d'argent pour acheter ceci.").queue();
            }
            if (lang != command.Language.en) return;
            channel.sendMessage("You don't have enough money to buy this.").queue();
            return;
        }
        if (c1 != 7) return;
        try {
            Bstock = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Shop/alea.txt"));
        }
        catch (NumberFormatException e) {
            Bstock = 0;
        }
        if (c2 > Bstock) {
            if (lang == command.Language.fr) {
                channel.sendMessage("Il n'y a plus asser de stock.").queue();
            }
            if (lang != command.Language.en) return;
            channel.sendMessage("There is not enough stock.").queue();
            return;
        }
        String ressource = "";
        int bois = 0;
        int argile = 0;
        int cuir = 0;
        int paille = 0;
        int pierre = 0;
        int fer = 0;
        if (money >= (long)(2500 * c2)) {
            int Pigeon;
            for (int i = 0; i < c2; ++i) {
                int alea = 1 + (int)(Math.random() * 6.0);
                if (alea == 1) {
                    ressource = "bois";
                    ++bois;
                }
                if (alea == 2) {
                    ++argile;
                    ressource = "argile";
                }
                if (alea == 3) {
                    ++cuir;
                    ressource = "cuir";
                }
                if (alea == 4) {
                    ++pierre;
                    ressource = "pierre";
                }
                if (alea == 5) {
                    ++paille;
                    ressource = "paille";
                }
                if (alea == 6) {
                    ++fer;
                    ressource = "petrole";
                }
                int res1 = res.get(ressource);
                money -= 2500L;
                --Bstock;
                res.put(ressource, ++res1);
                try {
                    data.getProfils().get(user.getId()).setRes(res);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setRes(res);
                }
                try {
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setMoney(money);
                }
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/Shop/alea.txt", Integer.toString(Bstock), 1);
            }
            try {
                Pigeon = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Achievement/Pigeon.txt"));
            }
            catch (NumberFormatException e) {
                Pigeon = 0;
            }
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/Achievement/Pigeon.txt", Integer.toString(Pigeon += c2), 1);
            if (lang == command.Language.fr) {
                channel.sendMessage("Bravo, Vous venez d'acheter " + bois + " bois, " + argile + " argile, " + cuir + " cuir, " + pierre + " pierre, " + paille + " paille, " + fer + " fer.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("Well, you just buy " + bois + " wood, " + argile + " clay, " + cuir + " leather, " + pierre + " stone, " + paille + " straw, " + fer + " iron.").queue();
            }
            Quest.Quest("shop", user, channel, c2);
            return;
        }
        if (lang == command.Language.fr) {
            channel.sendMessage("Tu n'as pas asser d'argent pour acheter ceci.").queue();
        }
        if (lang != command.Language.en) return;
        channel.sendMessage("You don't have enough money to buy this.").queue();
    }
}

