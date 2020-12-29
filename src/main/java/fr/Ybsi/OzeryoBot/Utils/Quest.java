/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.io.File;
import java.util.Date;

public class Quest {
    public static void Quest(String name, Profil profil, MessageChannel channel, int points) {
        File[] files;
        command.Language lang = profil.getLanguage();
        ProfilData data = DiscordBot.getData();
        int day = new Date().getDay();
        String quest1 = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Quests/quest1.txt");
        String quest2 = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Quests/quest2.txt");
        String quest3 = TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Quests/quest3.txt");
        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + profil.getId()+"/");
        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/Users/" + profil.getId() + "/quests/");
        try {
            for (File file : files = TextFileWriter
                    .folderlist("/home/DiscordBot/Rasberry/données/Users/" + profil.getId() + "/quests/")) {
                if (file.getName().equals(day + "|" + quest1 + ".txt")
                        || file.getName().equals(day + "|" + quest2 + ".txt")
                        || Premium.Premium(profil) && file.getName().equals(day + "|" + quest3 + ".txt"))
                    continue;
                TextFileWriter.delete(file.getAbsolutePath());
            }
        }catch(NullPointerException e){

        }
        if (quest1.equals(name) || quest2.equals(name) || Premium.Premium(data.getProfils().get(profil.getId())) && quest3.equals(name)) {
            int maxpoints;
            int lastpts = 0;
            try {
                lastpts = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + profil.getId()
                        + "/quests/" + day + "|" + name + ".txt"));
            } catch (NumberFormatException e) {
                return;
            }
            points = lastpts + points;
            TextFileWriter.write(
                    "/home/DiscordBot/Rasberry/données/Users/" + profil.getId() + "/quests/" + day + "|" + name + ".txt",
                    Integer.toString(points), 1);
            try {
                maxpoints = Integer
                        .parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Quests/" + name));
            } catch (NumberFormatException e) {
                maxpoints = 0;
            }
            if (points >= maxpoints) {
                String name1 = "";
                if (lang == command.Language.fr) {
                    if (name.equals("tr")) {
                        name1 = "Recuperer 5 cf";
                    } else if (name.equals("hr")) {
                        name1 = "Recuperer 5 hourly";
                    } else if (name.equals("mana")) {
                        name1 = "Depenser 100 mana";
                    } else if (name.equals("atk")) {
                        name1 = "Reussir 3 attaques";
                    } else if (name.equals("def")) {
                        name1 = "Reussir 3 defenses";
                    } else if (name.equals("exp")) {
                        name1 = "Recolter 500 EXP";
                    } else if (name.equals("invest")) {
                        name1 = "Investir 200 ressources dans le pays";
                    } else if (name.equals("vote")) {
                        name1 = "Voter pour le bot";
                    } else if (name.equals("shop")) {
                        name1 = "Acheter 30 items dans le Shop";
                    } else if (name.equals("soldier")) {
                        name1 = "Entrainer 5000 soldats";
                    } else if (name.equals("jetons")) {
                        name1 = "Recuperer 10 jetons";
                    } else if (name.equals("materiau")) {
                        name1 = "Recuperer 200 materiaux";
                    } else if (name.equals("bois")) {
                        name1 = "Recuperer 50 bois";
                    } else if (name.equals("acier")) {
                        name1 = "Recuperer 50 acier";
                    } else if (name.equals("brique")) {
                        name1 = "Recuperer 50 briques";
                    } else if (name.equals("pierre")) {
                        name1 = "Recuperer 50 pierre";
                    } else if (name.equals("pierre")) {
                        name1 = "Recuperer 50 pierre";
                    } else if (name.equals("fer")) {
                        name1 = "Recuperer 50 petrole";
                    }
                }
                if (lang == command.Language.en) {
                    if (name.equals("tr")) {
                        name1 = "Collect 5 cf";
                    } else if (name.equals("hr")) {
                        name1 = "Collect 5 hourly";
                    } else if (name.equals("mana")) {
                        name1 = "Spend 100 mana";
                    } else if (name.equals("atk")) {
                        name1 = "Achieve 3 attacks";
                    } else if (name.equals("def")) {
                        name1 = "Achieve 3 defenses";
                    } else if (name.equals("exp")) {
                        name1 = "Recolt 500 Xp";
                    } else if (name.equals("invest")) {
                        name1 = "Invest 200 ressources in the country";
                    } else if (name.equals("vote")) {
                        name1 = "Vote for the bot";
                    } else if (name.equals("shop")) {
                        name1 = "Buy 30 items in the Shop";
                    } else if (name.equals("soldier")) {
                        name1 = "Train 5000 soldats";
                    } else if (name.equals("jetons")) {
                        name1 = "Collect 10 jetons";
                    } else if (name.equals("materiau")) {
                        name1 = "Collect 200 materiaux";
                    } else if (name.equals("bois")) {
                        name1 = "Collect 50 bois";
                    } else if (name.equals("acier")) {
                        name1 = "Collect 50 acier";
                    } else if (name.equals("brique")) {
                        name1 = "Collect 50 briques";
                    } else if (name.equals("pierre")) {
                        name1 = "Collect 50 pierre";
                    } else if (name.equals("pierre")) {
                        name1 = "Collect 50 pierre";
                    } else if (name.equals("petrole")) {
                        name1 = "Collect 50 petrole";
                    }
                } else if (name.equals("tr")) {
                    name1 = "Collect 5 cf";
                } else if (name.equals("hr")) {
                    name1 = "Collect 5 hourly";
                } else if (name.equals("mana")) {
                    name1 = "Spend 100 mana";
                } else if (name.equals("atk")) {
                    name1 = "Achieve 3 attacks";
                } else if (name.equals("def")) {
                    name1 = "Achieve 3 defenses";
                } else if (name.equals("exp")) {
                    name1 = "Recolt 500 Xp";
                } else if (name.equals("invest")) {
                    name1 = "Invest 200 ressources in the country";
                } else if (name.equals("vote")) {
                    name1 = "Vote for the bot";
                } else if (name.equals("shop")) {
                    name1 = "Buy 30 items in the Shop";
                } else if (name.equals("soldier")) {
                    name1 = "Train 5000 soldats";
                } else if (name.equals("jetons")) {
                    name1 = "Collect 10 jetons";
                } else if (name.equals("materiau")) {
                    name1 = "Collect 200 materiaux";
                } else if (name.equals("bois")) {
                    name1 = "Collect 50 bois";
                } else if (name.equals("acier")) {
                    name1 = "Collect 50 acier";
                } else if (name.equals("brique")) {
                    name1 = "Collect 50 briques";
                } else if (name.equals("pierre")) {
                    name1 = "Collect 50 pierre";
                } else if (name.equals("pierre")) {
                    name1 = "Collect 50 pierre";
                } else if (name.equals("petrole")) {
                    name1 = "Collect 50 petrole";
                }
                if (lang == command.Language.fr) {
                    if(channel != null) channel.sendMessage("Vous venez de terminer la quete :``" + name1
                            + "``. Vous obtenez donc 75EXP pour l'avoir accompli.").queue();
                }
                if (lang == command.Language.en) {
                    if(channel != null) channel.sendMessage(
                            "You just finish the quest :``" + name1 + "``. So you won 75Xp for having accomplished.")
                            .queue();
                }
                int Game_EXP = profil.getXp();
                profil.setXp(Game_EXP += 75);
                TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + profil.getId() + "/quests/" + day + "|"
                        + name + ".txt", "true", 1);
            }
        } else {
            return;
        }
    }
}
