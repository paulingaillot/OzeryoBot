/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.CommandMap;
import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.internal.entities.UserImpl;

import java.util.ArrayList;
import java.util.HashMap;

public class Trade {
    public static void give(String c1, Profil user, Profil cible, int c2) {
        try {
            ArrayList<ArrayList<String>> mails;
            int resC;
            boolean mailC;
            boolean mail2;
            ProfilData data = DiscordBot.getData();
            HashMap<String, Integer> res = new HashMap<String, Integer>();
            try {
                res = data.getProfils().get(cible.getId()).getRes();
            } catch (NullPointerException e) {
                res = new HashMap();
                res.put("bois", 0);
                res.put("argile", 0);
                res.put("cuir", 0);
                res.put("pierre", 0);
                res.put("paille", 0);
                res.put("fer", 0);
            }
            if (c1.equals("bois")) {
                int Cible_bois = (Integer) res.get("bois");
                int res_cible = Cible_bois + c2;
                res.put("bois", res_cible);
                try {
                    data.getProfils().get(cible.getId()).setRes(res);
                } catch (NullPointerException e) {
                    data.getProfils().put(cible.getId(), new Profil(cible.getId()));
                    data.getProfils().get(cible.getId()).setRes(res);
                }
            } else if (c1.equals("argile")) {
                int Cargile = (Integer) res.get("argile");
                resC = Cargile + c2;
                res.put("argile", resC);
                try {
                    data.getProfils().get(cible.getId()).setRes(res);
                } catch (NullPointerException e) {
                    data.getProfils().put(cible.getId(), new Profil(cible.getId()));
                    data.getProfils().get(cible.getId()).setRes(res);
                }
            } else if (c1.equals("cuir")) {
                int Ccuir = (Integer) res.get("cuir");
                resC = Ccuir + c2;
                res.put("cuir", resC);
                try {
                    data.getProfils().get(cible.getId()).setRes(res);
                } catch (NullPointerException e) {
                    data.getProfils().put(cible.getId(), new Profil(cible.getId()));
                    data.getProfils().get(cible.getId()).setRes(res);
                }
            } else if (c1.equals("pierre")) {
                int Cpierre = (Integer) res.get("pierre");
                resC = Cpierre + c2;
                res.put("pierre", resC);
                try {
                    data.getProfils().get(cible.getId()).setRes(res);
                } catch (NullPointerException e) {
                    data.getProfils().put(cible.getId(), new Profil(cible.getId()));
                    data.getProfils().get(cible.getId()).setRes(res);
                }
            } else if (c1.equals("paille")) {
                int Cpaille = (Integer) res.get("paille");
                resC = Cpaille + c2;
                res.put("paille", resC);
                try {
                    data.getProfils().get(cible.getId()).setRes(res);
                } catch (NullPointerException e) {
                    data.getProfils().put(cible.getId(), new Profil(cible.getId()));
                    data.getProfils().get(cible.getId()).setRes(res);
                }
            } else if (c1.equals("fer") || c1.equals("fer")) {
                int Cfer = (Integer) res.get("fer");
                resC = Cfer + c2;
                res.put("fer", resC);
                try {
                    data.getProfils().get(cible.getId()).setRes(res);
                } catch (NullPointerException e) {
                    data.getProfils().put(cible.getId(), new Profil(cible.getId()));
                    data.getProfils().get(cible.getId()).setRes(res);
                }
            } else if (c1.equals("money")) {
                long Cmoney = data.getProfils().get(cible.getId()).getMoney();
                long resC2 = Cmoney + (long) c2;
                try {
                    data.getProfils().get(cible.getId()).setMoney(resC2);
                } catch (NullPointerException e) {
                    data.getProfils().put(cible.getId(), new Profil(cible.getId()));
                    data.getProfils().get(cible.getId()).setMoney(resC2);
                }
            }
            try {
                mail2 = data.getProfils().get(user.getId()).isMail();
            } catch (Exception e) {
                mail2 = false;
            }
            try {
                mailC = data.getProfils().get(cible.getId()).isMail();
            } catch (Exception e) {
                mailC = false;
            }
            command.Language lang = DiscordBot.getData().getProfils().get(user.getId()).getLanguage();
            if (!mail2) {
               /* if (!user.hasPrivateChannel()) {
                    user.openPrivateChannel().complete();
                }
                if (lang == command.Language.fr) {
                    ((UserImpl) user).getPrivateChannel()
                            .sendMessage(
                                    "\ud83d\udcb1 **Historique de transactions** \ud83d\udcb1 \n Vous venez de donner "
                                            + c2 + " " + c1 + " a " + cible.getName() + " (" + cible.getId() + ").")
                            .queue();
                }
                if (lang == command.Language.en) {
                    ((UserImpl) user).getPrivateChannel()
                            .sendMessage("\ud83d\udcb1 **transaction report** \ud83d\udcb1 \n You just give " + c2 + " "
                                    + c1 + " to " + cible.getName() + " (" + cible.getId() + ").")
                            .queue();
                }*/
            } else {
                ArrayList<String> mail1 = new ArrayList<String>();
                if (lang == command.Language.fr) {
                    mail1.add("Ressources envoyés a " + cible.getName());
                }
                if (lang == command.Language.en) {
                    mail1.add("Ressources send to " + cible.getName());
                }
                if (lang == command.Language.fr) {
                    mail1.add("\ud83d\udcb1 **Historique de transactions** \ud83d\udcb1 \n Vous venez de donner " + c2
                            + " " + c1 + " a " + cible.getName() + " (" + cible.getId() + ").");
                }
                if (lang == command.Language.en) {
                    mail1.add("\ud83d\udcb1 **transaction report** \ud83d\udcb1 \n You just give " + c2 + " " + c1
                            + " to " + cible.getName() + " (" + cible.getId() + ").");
                }
                mail1.add("false");
                mail1.add("" + System.currentTimeMillis());
                try {
                    ArrayList<ArrayList<String>> mails2 = data.getProfils().get(user.getId()).getListMail();
                    mails2.add(0, mail1);
                    data.getProfils().get(user.getId()).setListMail(mails2);
                } catch (NullPointerException e) {
                    mails = new ArrayList<ArrayList<String>>();
                    mails.add(0, mail1);
                    data.getProfils().get(user.getId()).setListMail(mails);
                }
            }
            command.Language langc = DiscordBot.getData().getProfils().get(cible.getId()).getLanguage();
            /*if (!mailC) {
                if (!cible.hasPrivateChannel()) {
                    cible.openPrivateChannel().complete();
                }
                if (langc == command.Language.fr) {
                    ((UserImpl) cible).getPrivateChannel().sendMessage(
                            "\ud83d\udcb1 **Historique de transactions** \ud83d\udcb1 \n Vous venez de recevoir " + c2
                                    + " " + c1 + " de " + user.getName() + " (" + cible.getId() + ").")
                            .queue();
                }
                if (langc == command.Language.en) {
                    ((UserImpl) cible).getPrivateChannel()
                            .sendMessage("\ud83d\udcb1 **Transaction Report** \ud83d\udcb1 \n You just received " + c2
                                    + " " + c1 + " from " + user.getName() + " (" + cible.getId() + ").")
                            .queue();
                }
            } else {*/
                ArrayList<String> mail1 = new ArrayList<String>();
                if (langc == command.Language.fr) {
                    mail1.add("Ressources re\u00e7us de " + user.getName());
                }
                if (langc == command.Language.en) {
                    mail1.add("Ressources received from " + user.getName());
                }
                if (langc == command.Language.fr) {
                    mail1.add("\ud83d\udcb1 **Historique de transactions** \ud83d\udcb1 \n Vous venez de recevoir " + c2
                            + " " + c1 + " de " + user.getName() + " (" + cible.getId() + ").");
                }
                if (langc == command.Language.en) {
                    mail1.add("\ud83d\udcb1 **Transaction Report** \ud83d\udcb1 \n You just received " + c2 + " " + c1
                            + " from " + user.getName() + " (" + cible.getId() + ").");
                }
                mail1.add("false");
                mail1.add("" + System.currentTimeMillis());
                try {
                    mails = data.getProfils().get(cible.getId()).getListMail();
                    mails.add(0, mail1);
                    data.getProfils().get(cible.getId()).setListMail(mails);
                } catch (NullPointerException e) {
                    ArrayList<ArrayList<String>> mails3 = new ArrayList<ArrayList<String>>();
                    mails3.add(0, mail1);
                    data.getProfils().get(cible.getId()).setListMail(mails3);
                }
          // }
            CommandMap.PublicLog(":currency_exchange:  **Rapport de transaction** :currency_exchange: \n\nle joueur "
                    + user.getName() + " (" + user.getId() + ")  vient de donner " + c2 + " " + c1 + " a "
                    + cible.getName() + " (" + cible.getId() + ")", DiscordBot.getjda());
        } catch (Exception e) {
            e.printStackTrace();
            CommandMap.Log("Give", e.getLocalizedMessage(), DiscordBot.getjda());
        }
    }

    @command(name = "give", abbrev = "g", type = command.ExecutorType.ALL, topic = command.Topics.Game)
    private void give(Message message, Guild guild, String[] args, User user, MessageChannel channel, String arg,
                      JDA jda, command.Language lang, String command2) {
        String c11;
        ProfilData data = DiscordBot.getData();
        try {
            c11 = args[0];
        } catch (IndexOutOfBoundsException e) {
            if (lang == command.Language.fr) {
                channel.sendMessage(
                        "Afin de donner vos ressources veuillez mentionner ou mettre l'id de la personne avec qui vous voulez echanger.")
                        .queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage(
                        "Please mention, type the id or the location of the user with whom you want to trade.").queue();
            }
            return;
        }
        int x = 0;
        int y = 0;
        User cible = null;
        try {
            cible = message.getMentionedUsers().get(0);
        } catch (IndexOutOfBoundsException e) {
            try {
                cible = jda.getUserById(c11);
            } catch (Exception e1) {
                cible = null;
            }
        }
        if (cible == null) {
            try {
                x = Integer.parseInt(args[0]);
                y = Integer.parseInt(args[1]);
            } catch (IndexOutOfBoundsException e) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(
                            "Afin d'echanger veuillez mentionner ou mettre l'id de la personne avec qui vous voulez echanger.")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(
                            "Please mention , type the id or the location of the personne with whom you want to trade.")
                            .queue();
                }
                return;
            } catch (Exception e) {
                x = 0;
                y = 0;
            }
            try {
                cible = jda.getUserById(
                        TextFileWriter.read("/home/DiscordBot/Rasberry/données/bot/Map/" + x + "_" + y + "/name.txt"));
            } catch (NumberFormatException e) {
                // empty catch block
            }
            if (cible == null) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Il ne semble y avoir ici personne pour recuperer vos fabuleuses ressources")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("There is no player in this location").queue();
                }
                return;
            }
        }
        String c1 = "";
        int c2 = 0;
        for (String str : args) {
            try {
                c2 = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                c1 = str;
            }
        }
        System.out.println(c1);
        System.out.println(c2);
        if (c2 < 0) {
            if (lang == command.Language.fr) {
                channel.sendMessage("C'est pas bien d'essayer d'usebug tu sais ...").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("Please type a valide number").queue();
            }
            return;
        }
        if (user.getId().equals(cible.getId())) {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous ne pouvez pas vous donner des ressources a vous meme.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You can't give ressources to yourself.").queue();
            }
            return;
        }
        HashMap<String, Integer> building1 = data.getProfils().get(user.getId()).getBuilding();
        int level = building1.get("transport");
        int temps = 0;
        if (level == 0) {
            temps = 600000;
        } else if (level == 1) {
            temps = 300000;
        } else if (level == 2) {
            temps = 120000;
        } else if (level == 3) {
            temps = 60000;
        } else if (level == 4) {
            temps = 1000;
        }
        String userHome = data.getProfils().get(user.getId()).getHome();
        String[] strU = userHome.split("_");
        int xU = Integer.parseInt(strU[0]);
        int yU = Integer.parseInt(strU[1]);
        String cibleHome = data.getProfils().get(cible.getId()).getHome();
        String[] strC = cibleHome.split("_");
        int xC = Integer.parseInt(strC[0]);
        int yC = Integer.parseInt(strC[1]);
        double operation = Math.pow(xC - xU, 2.0) + Math.pow(yC - yU, 2.0);
        System.out.println(operation);
        double durée = Math.sqrt(operation);
        System.out.println(durée);
        int heure = (int) (durée * (double) temps / 3600000.0);
        int minutes1 = (int) (durée * (double) temps / 3600000.0 * 60.0 % 60.0);
        long DateFin = System.currentTimeMillis() + (long) (durée * (double) temps);
        String pref = TextFileWriter.read("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/prefix.txt");
        if (pref.equals("0")) {
            pref = "=";
        }
        String mess = command2;
        mess = mess.replaceAll("g ", "");
        mess = mess.replaceAll("give ", "");
        mess = mess.replaceAll(String.valueOf(xC) + " " + yC + " ", "");
        mess = mess.replaceAll(String.valueOf(cible.getAsMention()) + " ", "");
        mess = mess.replaceAll("<@!" + cible.getId() + "> ", "");
        mess = mess.replaceAll("<@" + cible.getId() + "> ", "");
        mess = mess.replaceAll(String.valueOf(cible.getId()) + " ", "");
        System.out.println(mess);
        channel.sendMessage(cible.getAsMention()).queue();
        channel.sendMessage(cible.getId()).queue();
        channel.sendMessage(mess).queue();
        args = mess.split(" ");
        System.out.println(cible.getAsMention());
        System.out.println(mess.replace(String.valueOf(cible.getAsMention()) + " ", ""));
        String mess1 = "";
        HashMap<Long, ArrayList<String>> map = new HashMap();
        try {
            map = data.getProfils().get(user.getId()).getGive();
        } catch (NullPointerException e) {
            map = new HashMap();
        }
        HashMap<String, Integer> res = data.getProfils().get(user.getId()).getRes();
        for (int i = 0; i < args.length; i += 2) {
            c1 = args[i];
            c2 = Integer.parseInt(args[i + 1]);
            if (c1.equals("bois")) {
                int User_bois = res.get("bois");
                if (c2 > User_bois) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Vous ne pouvez pas donner plus de ressources que vous n'en avait actuelement.")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You can't give more ressources than you actually have.").queue();
                    }
                    return;
                }
                int res_user = User_bois - c2;
                res.put("bois", res_user);
                try {
                    data.getProfils().get(user.getId()).setRes(res);
                } catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setRes(res);
                }
            } else if (c1.equals("argile")) {
                int Uargile = res.get("argile");
                if (c2 > Uargile) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Vous ne pouvez pas donner plus de ressources que vous n'en avait actuelement.")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You can't give more ressources than you actually have.").queue();
                    }
                    return;
                }
                int res_user = Uargile - c2;
                res.put("argile", res_user);
                try {
                    data.getProfils().get(user.getId()).setRes(res);
                } catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setRes(res);
                }
            } else if (c1.equals("cuir")) {
                int Ucuir = res.get("cuir");
                if (c2 > Ucuir) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Vous ne pouvez pas donner plus de ressources que vous n'en avait actuelement.")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You can't give more ressources than you actually have.").queue();
                    }
                    return;
                }
                int res_user = Ucuir - c2;
                res.put("cuir", res_user);
                try {
                    data.getProfils().get(user.getId()).setRes(res);
                } catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setRes(res);
                }
            } else if (c1.equals("pierre")) {
                int Upierre = res.get("pierre");
                if (c2 > Upierre) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Vous ne pouvez pas donner plus de ressources que vous n'en avait actuelement.")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You can't give more ressources than you actually have.").queue();
                    }
                    return;
                }
                int res_user = Upierre - c2;
                res.put("pierre", res_user);
                try {
                    data.getProfils().get(user.getId()).setRes(res);
                } catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setRes(res);
                }
            } else if (c1.equals("paille")) {
                int Upaille = res.get("paille");
                if (c2 > Upaille) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Vous ne pouvez pas donner plus de ressources que vous n'en avait actuelement.")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You can't give more ressources than you actually have.").queue();
                    }
                    return;
                }
                int res_user = Upaille - c2;
                res.put("paille", res_user);
                try {
                    data.getProfils().get(user.getId()).setRes(res);
                } catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setRes(res);
                }
            } else if (c1.equals("fer") || c1.equals("fer")) {
                int Ufer = res.get("fer");
                if (c2 > Ufer) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(
                                "Vous ne pouvez pas donner plus de ressources que vous n'en avait actuelement.")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You can't give more ressources than you actually have.").queue();
                    }
                    return;
                }
                int res_user = Ufer - c2;
                res.put("fer", res_user);
                try {
                    data.getProfils().get(user.getId()).setRes(res);
                } catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setRes(res);
                }
            } else if (c1.equals("money")) {
                long Umoney = data.getProfils().get(user.getId()).getMoney();
                if ((long) c2 > Umoney) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous ne pouvez pas donner plus d'argent que vous n'en avait actuelement.")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You can't give more OzeCoins than you actually have.").queue();
                    }
                    return;
                }
                long res_user = Umoney - (long) c2;
                try {
                    data.getProfils().get(user.getId()).setMoney(res_user);
                } catch (NullPointerException e) {
                    data.getProfils().put(user.getId(), new Profil(user.getId()));
                    data.getProfils().get(user.getId()).setMoney(res_user);
                }
            } else {
                if (lang == command.Language.fr) {
                    channel.sendMessage(
                            "Ce que vous essayer de donner ne semble pas exister. Veuillez verifier le nom de votre objet.")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("That you try to trade doesn't exist. Please check the name of your object")
                            .queue();
                }
                return;
            }
            ArrayList<String> list = new ArrayList<String>();
            list.add(Long.toString(DateFin + (long) i));
            list.add(cible.getId());
            list.add(c1);
            list.add(Integer.toString(c2));
            try {
                map.put(DateFin + (long) i, list);
            } catch (NullPointerException e) {
                map = new HashMap();
                map.put(DateFin, list);
            }
            mess1 = String.valueOf(mess1) + c2 + " " + c1 + "\n";
        }
        if (lang == command.Language.fr) {
            channel.sendMessage("Vous venez d'echanger avec " + cible.getName() + " : \n" + mess1
                    + " Votre echange sera effectué dans " + heure + "h" + minutes1).queue();
        }
        if (lang == command.Language.en) {
            channel.sendMessage("You jsut trade with " + cible.getName() + " : \n" + mess1
                    + " Your trade will be done in " + heure + "h" + minutes1).queue();
        }
        try {
            data.getProfils().get(user.getId()).setGive(map);
        } catch (NullPointerException e) {
            data.getProfils().put(user.getId(), new Profil(user.getId()));
            data.getProfils().get(user.getId()).setGive(map);
        }
    }
}
