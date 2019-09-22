/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class Pray {
    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
   /* @command(name="pray", type=command.ExecutorType.ALL, descfr="usage : [BETA] creer une ville et developpe la au fil de temps", topic=command.Topics.Game)
    private void pray(MessageChannel channel, User user, Guild guild, String[] args, JDA jda, ProfilData data, command.Language lang) {
        String dieu;
		String bonus;
                                            building = data.getProfils().get(user.getId()).getBuilding();
                                            eglise = 0;
                                            try {
                                                eglise = building.get("eglise");
                                            }
                                            catch (NumberFormatException e) {
                                                e.printStackTrace();
                                            }
                                            if (eglise != 1) {
                                                if (lang == command.Language.fr) {
                                                    channel.sendMessage("Vous devez construire le eglise pour avoir acces a cette fonctionalit\u00e9.").queue();
                                                }
                                                if (lang != command.Language.en) return;
                                                channel.sendMessage("You must build your Church to perform this command.").queue();
                                                return;
                                            }
                                            c1 = "";
                                            try {
                                                c1 = args[0];
                                            }
                                            catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            TextFileWriter.folder("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + user.getId() + "/eglise/");
                                            Lday = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + user.getId() + "/eglise/day.txt"));
                                            Lmonth = Integer.parseInt(TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + user.getId() + "/eglise/month.txt"));
                                            day = Integer.parseInt(new SimpleDateFormat("dd", Locale.FRANCE).format(new Date()));
                                            month = Integer.parseInt(new SimpleDateFormat("MM", Locale.FRANCE).format(new Date()));
                                            Nday = day - Lday;
                                            Nmonth = month - Lmonth;
                                            if (Nday < 0) {
                                                Nday += 30;
                                                --Nmonth;
                                            }
                                            if ((Nday += Nmonth * 30) <= 7 && Nday >= 0) break block95;
                                            var17_19 = c1.toLowerCase();
                                            switch (var17_19.hashCode()) {
                                                case -1407612381: {
                                                    if (!var17_19.equals("athena")) {
                                                        ** break;
                                                    }
                                                    break block96;
                                                }
                                                case -1220755802: {
                                                    if (!var17_19.equals("hermes")) {
                                                        ** break;
                                                    }
                                                    break block97;
                                                }
                                                case 0: {
                                                    if (!var17_19.equals("")) {
                                                        ** break;
                                                    }
                                                    break block98;
                                                }
                                                case 3002527: {
                                                    if (!var17_19.equals("ares")) {
                                                        ** break;
                                                    }
                                                    break block99;
                                                }
                                                case 3198956: {
                                                    if (!var17_19.equals("hera")) {
                                                        ** break;
                                                    }
                                                    break block100;
                                                }
                                                case 3735305: {
                                                    if (var17_19.equals("zeus")) break;
                                                    ** break;
                                                }
                                                case 99035257: {
                                                    if (!var17_19.equals("hades")) {
                                                        ** break;
                                                    }
                                                    break block101;
                                                }
                                                case 743778379: {
                                                    if (!var17_19.equals("poseidon")) {
                                                        ** break;
                                                    }
                                                    break block102;
                                                }
                                            }
                                            TextFileWriter.write("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + user.getId() + "/eglise.txt", "Zeus", 1);
                                            if (lang == command.Language.fr) {
                                                channel.sendMessage("Vous venez de prier pour Zeus.").queue();
                                            }
                                            if (lang == command.Language.en) {
                                                channel.sendMessage("You just pray for Zeus.").queue();
                                            }
                                            break block103;
                                        }
                                        TextFileWriter.write("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + user.getId() + "/eglise.txt", "Hera", 1);
                                        if (lang == command.Language.fr) {
                                            channel.sendMessage("Vous venez de prier pour Hera.").queue();
                                        }
                                        if (lang == command.Language.en) {
                                            channel.sendMessage("You just pray for Hera.").queue();
                                        }
                                        break block103;
                                    }
                                    TextFileWriter.write("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + user.getId() + "/eglise.txt", "Athena", 1);
                                    if (lang == command.Language.fr) {
                                        channel.sendMessage("Vous venez de prier pour Athena.").queue();
                                    }
                                    if (lang == command.Language.en) {
                                        channel.sendMessage("You just pray for Athena.").queue();
                                    }
                                    break block103;
                                }
                                TextFileWriter.write("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + user.getId() + "/eglise.txt", "Hermes", 1);
                                if (lang == command.Language.fr) {
                                    channel.sendMessage("Vous venez de prier pour Hermes.").queue();
                                }
                                if (lang == command.Language.en) {
                                    channel.sendMessage("You just pray for Hermes.").queue();
                                }
                                break block103;
                            }
                            TextFileWriter.write("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + user.getId() + "/eglise.txt", "Poseidon", 1);
                            if (lang == command.Language.fr) {
                                channel.sendMessage("Vous venez de prier pour Poseidon.").queue();
                            }
                            if (lang == command.Language.en) {
                                channel.sendMessage("You just pray for Poseidon.").queue();
                            }
                            break block103;
                        }
                        TextFileWriter.write("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + user.getId() + "/eglise.txt", "Ares", 1);
                        if (lang == command.Language.fr) {
                            channel.sendMessage("Vous venez de prier pour Ares.").queue();
                        }
                        if (lang == command.Language.en) {
                            channel.sendMessage("You just pray for Ares.").queue();
                        }
                        break block103;
                    }
                    TextFileWriter.write("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + user.getId() + "/eglise.txt", "Hades", 1);
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous venez de prier pour Hades.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You just pray for Hades.").queue();
                    }
                    break block103;
                }
                dieu = TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + user.getId() + "/eglise.txt");
                bonus = "";
                if (dieu.equals("Zeus")) {
                    if (lang == command.Language.fr) {
                        bonus = "Ce Dieu boost le gain de money";
                    }
                    if (lang == command.Language.en) {
                        bonus = "This deus boost money you win";
                    }
                } else if (dieu.equals("Hera")) {
                    if (lang == command.Language.fr) {
                        bonus = "Ce Dieu boost le gain d'habitants";
                    }
                    if (lang == command.Language.en) {
                        bonus = "This deus boost people you win";
                    }
                } else if (dieu.equals("Athena")) {
                    if (lang == command.Language.fr) {
                        bonus = "Ce Dieu boost votre maximum de mana";
                    }
                    if (lang == command.Language.en) {
                        bonus = "This deus boost your mana Maximum";
                    }
                } else if (dieu.equals("Poseidon")) {
                    if (lang == command.Language.fr) {
                        bonus = "Ce Dieu boost le gain de materiaux";
                    }
                    if (lang == command.Language.en) {
                        bonus = "This deus boost ressources you win";
                    }
                } else if (dieu.equals("Hades")) {
                    if (lang == command.Language.fr) {
                        bonus = "Ce Dieu boost votre maximum d'habitants";
                    }
                    if (lang == command.Language.en) {
                        bonus = "This deus boost your people limit";
                    }
                } else if (dieu.equals("Ares")) {
                    if (lang == command.Language.fr) {
                        bonus = "Ce Dieu boost votre entrainement de soldats";
                    }
                    if (lang == command.Language.en) {
                        bonus = "This deus boost the number of soldiers you train";
                    }
                } else if (dieu.equals("Hermes")) {
                    if (lang == command.Language.fr) {
                        bonus = "Ce Dieu boost votre regeneration de mana";
                    }
                    if (lang == command.Language.en) {
                        bonus = "This deus boost your mana regen";
                    }
                }
                if (lang == command.Language.fr) {
                    channel.sendMessage("Votre dieu actuel est " + dieu + ". " + bonus + ". Vous pouvez changer de dieu.").queue();
                }
                if (lang != command.Language.en) return;
                channel.sendMessage("Your actual deus is " + dieu + ". " + bonus + ". You can change deus now.").queue();
                return;
lbl161: // 9 sources:
                if (lang == command.Language.fr) {
                    channel.sendMessage("Les dieux que vous pouvez prier sont : ``Zeus``, ``Poseidon``, ``Hades``, ``Hermes``, ``Hera``, ``Athena``, ``Ares``").queue();
                }
                if (lang != command.Language.en) return;
                channel.sendMessage("Deus you can pray are : ``Zeus``, ``Poseidon``, ``Hades``, ``Hermes``, ``Hera``, ``Athena``, ``Ares``").queue();
                return;
            }
            TextFileWriter.write("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + user.getId() + "/eglise/day.txt", Integer.toString(day), 1);
            TextFileWriter.write("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + user.getId() + "/eglise/month.txt", Integer.toString(month), 1);
            return;
        }
        dieu = TextFileWriter.read("/home/DiscordBot/Rasberry/donn\u00e9es/Users/" + user.getId() + "/eglise.txt");
        bonus = "";
        if (dieu.equals("Zeus")) {
            if (lang == command.Language.fr) {
                bonus = "Ce Dieu boost le gain de money";
            }
            if (lang == command.Language.en) {
                bonus = "This deus boost money you win";
            }
        } else if (dieu.equals("Hera")) {
            if (lang == command.Language.fr) {
                bonus = "Ce Dieu boost le gain d'habitants";
            }
            if (lang == command.Language.en) {
                bonus = "This deus boost people you win";
            }
        } else if (dieu.equals("Athena")) {
            if (lang == command.Language.fr) {
                bonus = "Ce Dieu boost votre maximum de mana";
            }
            if (lang == command.Language.en) {
                bonus = "This deus boost your mana Maximum";
            }
        } else if (dieu.equals("Poseidon")) {
            if (lang == command.Language.fr) {
                bonus = "Ce Dieu boost le gain de materiaux";
            }
            if (lang == command.Language.en) {
                bonus = "This deus boost ressources you win";
            }
        } else if (dieu.equals("Hades")) {
            if (lang == command.Language.fr) {
                bonus = "Ce Dieu boost votre maximum d'habitants";
            }
            if (lang == command.Language.en) {
                bonus = "This deus boost your people limit";
            }
        } else if (dieu.equals("Ares")) {
            if (lang == command.Language.fr) {
                bonus = "Ce Dieu boost votre entrainement de soldats";
            }
            if (lang == command.Language.en) {
                bonus = "This deus boost the number of soldiers you train";
            }
        } else if (dieu.equals("Hermes")) {
            if (lang == command.Language.fr) {
                bonus = "Ce Dieu boost votre regeneration de mana";
            }
            if (lang == command.Language.en) {
                bonus = "This deus boost your mana regen";
            }
        }
        Nday = 7 - Nday;
        if (lang == command.Language.fr) {
            channel.sendMessage("Votre dieu est " + dieu + ". " + bonus + ". Vous pourez changer de dieu dans " + Nday + " jours.").queue();
        }
        if (lang != command.Language.en) return;
        channel.sendMessage("Your deus is " + dieu + ". " + bonus + ". You will be able to change deus in " + Nday + " days.").queue();
    }*/
}

