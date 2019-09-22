/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.Game.habitants;
import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.impl.UserImpl;
import net.dv8tion.jda.core.requests.RestAction;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class Spy {
    @command(name="spy", type=command.ExecutorType.ALL, topic=command.Topics.Game)
    private void spy(Message message, Guild guild, String[] args, User user, MessageChannel channel, String arg, JDA jda, ProfilData data, command.Language lang) {
        User cible;
        int point;
        String cible2 = "";
        try {
            cible2 = args[0];
        }
        catch (IndexOutOfBoundsException e) {
            if (lang == command.Language.fr) {
                channel.sendMessage("Veuillez mentionnez ou indiquez l'id de la personne a espionner.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("Please mention the user you want to spy.").queue();
            }
            return;
        }
        try {
            cible = message.getMentionedUsers().get(0);
        }
        catch (IndexOutOfBoundsException e) {
            cible = jda.getUserById(cible2);
        }
        HashMap<String, Integer> building = data.getProfils().get(user.getId()).getBuilding();
        int caserne = building.get("camp d'entrainement");
        if (caserne <= 25) {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez ameliorer votre camp d'entrainement au niveau 26 afin de debloquer les espions.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must upgrade your  camp d'entrainement  to level 26 to unlock spying.").queue();
            }
            return;
        }
        int Lday = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/spyday.txt"));
        GregorianCalendar calendar = new GregorianCalendar();
        int day = calendar.get(5);
        if (day != Lday) {
            point = (caserne - 25) * 2;
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/spy.txt", Integer.toString(point), 1);
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/spyday.txt", Integer.toString(day), 1);
        }
        if ((point = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/spy.txt"))) == 0) {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous ne pouvez plus espionner personne aujourd'hui.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You can't spy anymore today.").queue();
            }
            return;
        }
        long soldier = habitants.atk(cible);
        int alea = 0;
        int alea2 = 0;
        if (caserne == 26) {
            alea = 0 + (int)(Math.random() * 8000001.0);
            alea2 = 0 + (int)(Math.random() * 8000001.0);
        } else if (caserne == 27) {
            alea = 0 + (int)(Math.random() * 4000001.0);
            alea2 = 0 + (int)(Math.random() * 4000001.0);
        } else if (caserne == 28) {
            alea = 0 + (int)(Math.random() * 2000001.0);
            alea2 = 0 + (int)(Math.random() * 2000001.0);
        } else if (caserne == 29) {
            alea = 0 + (int)(Math.random() * 1000001.0);
            alea2 = 0 + (int)(Math.random() * 1000001.0);
        } else if (caserne == 30) {
            alea = 0;
            alea2 = 0;
        }
        long soldierMin = soldier - (long)alea;
        if (soldierMin < 0L) {
            soldierMin = 0L;
        }
        long soldierMax = soldier + (long)alea2;
        alea = 1 + (int)(Math.random() * 9.0);
        HashMap<String, Integer> res = data.getProfils().get(cible.getId()).getRes();
        String messages = "";
        if (alea == 1) {
            long money = data.getProfils().get(cible.getId()).getMoney();
            if (lang == command.Language.fr) {
                messages = String.valueOf(messages) + "\n Votre espion a decouvert " + money + "$.";
            }
            if (lang == command.Language.en) {
                messages = String.valueOf(messages) + "\n Your spy discovered " + money + "$.";
            }
        }
        if (alea == 2) {
            int mana = data.getProfils().get(cible.getId()).getMana();
            if (lang == command.Language.fr) {
                messages = String.valueOf(messages) + "\n Votre espion a decouvert que l'ennemi a " + mana + " mana.";
            }
            if (lang == command.Language.en) {
                messages = String.valueOf(messages) + "\n Your spy discovered that your opponent have " + mana + " mana.";
            }
        }
        if (alea == 3) {
            int bois = res.get("bois");
            if (lang == command.Language.fr) {
                messages = String.valueOf(messages) + "\n Votre espion a decouvert " + bois + " bois dans la ville ennemie.";
            }
            if (lang == command.Language.en) {
                messages = String.valueOf(messages) + "\n Your spy discovered " + bois + " wood in the opponent city.";
            }
        }
        if (alea == 4) {
            int acier = res.get("acier");
            if (lang == command.Language.fr) {
                messages = String.valueOf(messages) + "\n Votre espion a decouvert " + acier + " argile dans la ville ennemie.";
            }
            if (lang == command.Language.en) {
                messages = String.valueOf(messages) + "\n Your spy discovered " + acier + " clay in the opponent city.";
            }
        }
        if (alea == 5) {
            int brique = res.get("brique");
            if (lang == command.Language.fr) {
                messages = String.valueOf(messages) + "\n Votre espion a decouvert " + brique + " cuir dans la ville ennemie.";
            }
            if (lang == command.Language.en) {
                messages = String.valueOf(messages) + "\n Your spy discovered " + brique + " leather in the opponent city.";
            }
        }
        if (alea == 6) {
            int verre = res.get("verre");
            if (lang == command.Language.fr) {
                messages = String.valueOf(messages) + "\n Votre espion a decouvert " + verre + " pierre dans la ville ennemie.";
            }
            if (lang == command.Language.en) {
                messages = String.valueOf(messages) + "\n Your spy discovered " + verre + " stone in the opponent city.";
            }
        }
        if (alea == 7) {
            int pierre = res.get("pierre");
            if (lang == command.Language.fr) {
                messages = String.valueOf(messages) + "\n Votre espion a decouvert " + pierre + " paille dans la ville ennemie.";
            }
            if (lang == command.Language.en) {
                messages = String.valueOf(messages) + "\n Your spy discovered " + pierre + " straw in the opponent city.";
            }
        }
        if (alea == 8) {
            int petrole = res.get("petrole");
            if (lang == command.Language.fr) {
                messages = String.valueOf(messages) + "\n Votre espion a decouvert " + petrole + " fer dans la ville ennemie.";
            }
            if (lang == command.Language.en) {
                messages = String.valueOf(messages) + "\n Your spy discovered " + petrole + " iron in the opponent city.";
            }
        }
        if (alea == 9) {
            long habitant = habitants.pop(cible);
            if (lang == command.Language.fr) {
                messages = String.valueOf(messages) + "\n Il y a " + habitant + " habitants dans la ville ennemie";
            }
            if (lang == command.Language.en) {
                messages = String.valueOf(messages) + "\n There are " + habitant + "people in the opponent city";
            }
        }
        int reperage = 50;
        if (caserne == 26) {
            reperage = 50;
        } else if (caserne == 27) {
            reperage = 40;
        } else if (caserne == 28) {
            reperage = 30;
        } else if (caserne == 29) {
            reperage = 20;
        } else if (caserne == 30) {
            reperage = 10;
        }
        int alea3 = 1 + (int)(Math.random() * 100.0);
        int espion = data.getProfils().get(user.getId()).getEspion();
        data.getProfils().get(user.getId()).setEspion(++espion);
        command.Language langc = DiscordBot.getData().getProfils().get(cible.getId()).getLanguage();
        if (alea3 <= reperage) {
            if (!cible.hasPrivateChannel()) {
                cible.openPrivateChannel().complete();
            }
            if (langc == command.Language.fr) {
                ((UserImpl)cible).getPrivateChannel().sendMessage("\u26a0 Alerte \u26a0 \n Un espion a été repéré dans votre ville.").queue();
            }
            if (langc == command.Language.en) {
                ((UserImpl)cible).getPrivateChannel().sendMessage("\u26a0 Alert \u26a0 \n A spy has been discovered in your city.").queue();
            }
        }
        if (!user.hasPrivateChannel()) {
            user.openPrivateChannel().complete();
        }
        if (lang == command.Language.fr) {
            ((UserImpl)user).getPrivateChannel().sendMessage("\ud83d\udd75 Rapport d'espionnage \ud83d\udd75 \n Vous venez d'espionnez la ville de " + cible.getName() + ".\n Elle poss\u00e8de entre " + soldierMin + " et " + soldierMax + " soldats." + messages).queue();
        }
        if (lang == command.Language.en) {
            ((UserImpl)user).getPrivateChannel().sendMessage("\ud83d\udd75 Spying report \ud83d\udd75 \n You jsut spy " + cible.getName() + " city.\n there is between " + soldierMin + " and " + soldierMax + " soldiers in this city. " + messages).queue();
        }
        TextFileWriter.write("/home/DiscordBot/Rasberry/données/Users/" + user.getId() + "/spy.txt", Integer.toString(point - 1), 1);
        if (lang == command.Language.fr) {
            channel.sendMessage("Votre mission d'espionnage est en cours... Consulter vos mp pour voir le rapport.").queue();
        }
        if (lang == command.Language.en) {
            channel.sendMessage("You spying mission is in progress... Check your PM to see the report.").queue();
        }
    }
}

