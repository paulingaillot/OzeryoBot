/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.HashMap;

public class Pray {
    /*
     * Unable to fully structure code Enabled aggressive block sorting Enabled
     * unnecessary exception pruning Enabled aggressive exception aggregation Lifted
     * jumps to return sites
     */
    @command(name = "pray", type = command.ExecutorType.ALL, descfr = "usage : [BETA] creer une ville et developpe la au fil de temps", topic = command.Topics.Game)
    private void pray(MessageChannel channel, User user, Guild guild, String[] args, JDA jda, ProfilData data,
                      command.Language lang) {

        String bonus;
        HashMap<String, Integer> building = data.getProfils().get(user.getId()).getBuilding();
        int eglise = 0;
        try {
            eglise = building.get("eglise");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (eglise != 1) {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez construire le eglise pour avoir acces a cette fonctionalité.").queue();
            }
            if (lang != command.Language.en)
                return;
            channel.sendMessage("You must build your Church to perform this command.").queue();
            return;
        }
        String c1 = "";
        try {
            c1 = args[0];
        } catch (Exception e) {
            e.printStackTrace();
        }

        long lastPray = data.getProfils().get(user.getId()).getLastPray();
        String dieu = c1.toLowerCase();
        String lastdieu = data.getProfils().get(user.getId()).getDeus();

        if (dieu.equals("")) {

            dieu = data.getProfils().get(user.getId()).getDeus();
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
            int Nday = (int) (7 - (System.currentTimeMillis() - lastPray) / 86400000);
            if (Nday <= 0) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(
                            "Votre dieu est " + dieu + ". " + bonus + ". Vous pouvez desormais changer de dieu.")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(
                            "Your deus is " + dieu + ". " + bonus + ".You can now change you deus.")
                            .queue();
                }

            } else {
                if (lang == command.Language.fr) {
                    channel.sendMessage(
                            "Votre dieu est " + dieu + ". " + bonus + ". Vous pourez changer de dieu dans " + Nday + " jours.")
                            .queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(
                            "Your deus is " + dieu + ". " + bonus + ". You will be able to change deus in " + Nday + " days.")
                            .queue();
                }
            }
            return;
        }


        if ((System.currentTimeMillis() - lastPray) < 604800000) {
            channel.sendMessage("Vous ne pouvez changer de dieu que tout les 7 jours").queue();
            return;
        }


        switch (dieu) {
            case "athena": {
                if (!lastdieu.equals("athena")) {
                    data.getProfils().get(user.getId()).setDeus("Athena");
                    data.getProfils().get(user.getId()).setLastPray(System.currentTimeMillis());
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous venez de prier pour Athena.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You just pray for Athena.").queue();
                    }

                }
                return;
            }

            case "hermes": {
                if (!lastdieu.equals("hermes")) {
                    data.getProfils().get(user.getId()).setDeus("Hermes");
                    data.getProfils().get(user.getId()).setLastPray(System.currentTimeMillis());
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous venez de prier pour Hermes.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You just pray for Hermes.").queue();
                    }

                }
                return;
            }
            case "ares": {
                if (!lastdieu.equals("ares")) {
                    data.getProfils().get(user.getId()).setDeus("Ares");
                    data.getProfils().get(user.getId()).setLastPray(System.currentTimeMillis());
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous venez de prier pour Ares.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You just pray for Ares.").queue();
                    }

                }
                return;
            }

            case "hera": {
                if (!lastdieu.equals("hera")) {
                    data.getProfils().get(user.getId()).setDeus("Hera");
                    data.getProfils().get(user.getId()).setLastPray(System.currentTimeMillis());
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous venez de prier pour Hera.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You just pray for Hera.").queue();
                    }
                }
                return;
            }

            case "zeus": {
                if (!lastdieu.equals("zeus")) {
                    data.getProfils().get(user.getId()).setDeus("Zeus");
                    data.getProfils().get(user.getId()).setLastPray(System.currentTimeMillis());
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous venez de prier pour Zeus.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You just pray for Zeus.").queue();
                    }
                }
                return;
            }
            case "hades": {
                if (!lastdieu.equals("hades")) {
                    data.getProfils().get(user.getId()).setDeus("Hades");
                    data.getProfils().get(user.getId()).setLastPray(System.currentTimeMillis());
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous venez de prier pour Hades.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You just pray for Hades.").queue();
                    }

                }
                return;
            }

            case "poseidon": {
                if (!lastdieu.equals("poseidon")) {
                    data.getProfils().get(user.getId()).setDeus("Poseidon");
                    data.getProfils().get(user.getId()).setLastPray(System.currentTimeMillis());
                    if (lang == command.Language.fr) {
                        channel.sendMessage("Vous venez de prier pour Poseidon.").queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage("You just pray for Poseidon.").queue();
                    }

                }
                return;
            }

        }
    }
}
