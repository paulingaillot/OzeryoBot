/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Default;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.Utils.Premium;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class Cosmetics {
    @command(name = "firework", abbrev = "fw", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void firework(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            command.Language lang) {
        message.delete().queue();
        channel.sendMessage(":tada:").complete();
    }

    @command(name = "hi", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void hi(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda) {
        message.delete().queue();
        channel.sendMessage(":wave:").complete();
    }

    @command(name = "hearts", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void heart(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 70) {
            Message mess = (Message) channel.sendMessage(":broken_heart:").complete();
            mess.editMessage(":heart:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":sparkling_heart: ").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 70 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 70 rep to use this animated emoji").queue();
            }
        }
    }

    @command(name = "lol", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void lol(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 60) {
            Message mess = (Message) channel.sendMessage(":neutral_face:").complete();
            mess.editMessage(":grinning:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":laughing:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":joy:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 60 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 60 rep to use this animated emoji").queue();
            }
        }
    }

    @command(name = "lf", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void lf(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 100) {
            Message mess = (Message) channel.sendMessage(
                    ":white_check_mark::black_large_square::black_large_square::black_large_square::black_large_square:")
                    .complete();
            mess.editMessage(
                    ":white_check_mark::white_check_mark::black_large_square::black_large_square::black_large_square:")
                    .completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(
                    ":white_check_mark::white_check_mark::white_check_mark::black_large_square::black_large_square:")
                    .completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(
                    ":white_check_mark::white_check_mark::white_check_mark::white_check_mark::black_large_square:")
                    .completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":middle_finger:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 100 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 100 rep to use this animated emoji").queue();
            }
        }
    }

    @command(name = "travel", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void travel(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 80) {
            Message mess = (Message) channel.sendMessage(":cruise_ship:").complete();
            mess.editMessage(":island:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":cruise_ship:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":sunrise:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":cruise_ship:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":beach:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 80 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 80 rep to use this animated emoji").queue();
            }
        }
    }

    @command(name = "rain", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void rain(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 90) {
            Message mess = (Message) channel.sendMessage(":cloud:").complete();
            mess.editMessage(":umbrella2:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":cloud_rain:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":umbrella:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 90 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 90 rep to use this animated emoji").queue();
            }
        }
    }

    @command(name = "surf", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void surf(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 120) {
            Message mess = (Message) channel.sendMessage(":park:").complete();
            mess.editMessage(":ocean:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":surfer:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 120 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 120 rep to use this animated emoji").queue();
            }
        }
    }

    @command(name = "travelplane", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void travelplane(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 140) {
            Message mess = (Message) channel.sendMessage(":airplane_departure:").complete();
            mess.editMessage(":foggy:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":japanese_castle:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":european_castle:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":classical_building:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":foggy:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":airplane_arriving:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 140 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 140 rep to use this animated emoji").queue();
            }
        }
    }

    @command(name = "thug", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void thug(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 160) {
            Message mess = (Message) channel.sendMessage(":no_mobile_phones:").complete();
            mess.editMessage(":iphone:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":no_smoking:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":smoking:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":non_potable_water:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":sweat_drops:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":underage:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":baby:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":no_bicycles:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":mountain_bicyclist:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":no_pedestrians:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":man_dancing:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 160 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 160 rep to use this animated emoji").queue();
            }
        }
    }

    @command(name = "ghost", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void ghost(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 180) {
            Message mess = (Message) channel.sendMessage(":slight_smile:").complete();
            mess.editMessage(":ghost:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":scream:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 180 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 180 rep to use this animated emoji").queue();
            }
        }
    }

    @command(name = "singe", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void singe(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 200) {
            Message mess = (Message) channel.sendMessage(":monkey_face:").complete();
            mess.editMessage(":see_no_evil:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":hear_no_evil:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":speak_no_evil:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 200 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 200 rep to use this animated emoji").queue();
            }
        }
    }

    @command(name = "moon", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void moon(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 240) {
            Message mess = (Message) channel.sendMessage(":full_moon:").complete();
            mess.editMessage(":waning_gibbous_moon:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":last_quarter_moon:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":waning_crescent_moon:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":new_moon:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":waxing_crescent_moon:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":first_quarter_moon:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":waxing_gibbous_moon:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 240 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 240 rep to use this animated emoji").queue();
            }
        }
    }

    @command(name = "fight", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void fight(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 280) {
            Message mess = (Message) channel.sendMessage(":nerd:").complete();
            mess.editMessage(":punch:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":boom:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":knife:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":bomb:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":hammer:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":skull_crossbones:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":dizzy_face:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 280 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 280 rep to use this animated emoji").queue();
            }
        }
    }

    @command(name = "noel", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void noel(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda) {
        message.delete().queue();
        Message mess = (Message) channel.sendMessage(":tada:").complete();
        mess.editMessage(":mrs_claus:").completeAfter(1L, TimeUnit.SECONDS);
        mess.editMessage(":gift:").completeAfter(1L, TimeUnit.SECONDS);
    }

    @command(name = "halloween", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void halloween(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda) {
        message.delete().queue();
        Message mess = (Message) channel.sendMessage(":bat:").complete();
        mess.editMessage(":spider_web:").completeAfter(1L, TimeUnit.SECONDS);
        mess.editMessage(":alien:").completeAfter(1L, TimeUnit.SECONDS);
        mess.editMessage(":ghost:").completeAfter(1L, TimeUnit.SECONDS);
        mess.editMessage(":japanese_goblin:").completeAfter(1L, TimeUnit.SECONDS);
    }

    @command(name = "happybirthday", abbrev = "ap", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void happybirthday(MessageChannel channel, User user, String[] args, Message message, Guild guild,
            JDA jda) {
        message.delete().queue();
        Message mess = (Message) channel.sendMessage(":champagne:").complete();
        mess.editMessage(":champagne:").completeAfter(1L, TimeUnit.SECONDS);
    }

    @command(name = "halloween2", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void halloween2(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda) {
        message.delete().queue();
        Message mess = (Message) channel.sendMessage(":lollipop:").complete();
        mess.editMessage(":candy:").completeAfter(1L, TimeUnit.SECONDS);
        mess.editMessage(":dango:").completeAfter(1L, TimeUnit.SECONDS);
    }

    @command(name = "cfez", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void cfez(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        HashMap<String, Integer> classement = new HashMap<String, Integer>();
        for (Profil profil : data.getProfils().values()) {
            Object member;
            int rep = profil.getCf();
            try {
                member = jda.getUserById(profil.getId()).getName();
            } catch (NullPointerException e) {
                member = "Une personne discr\u00e8te";
            }
            classement.put((String) member, rep);
        }
        ArrayList<Entry<String, Integer>> entries = new ArrayList(classement.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> e2, Map.Entry<String, Integer> e1) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        int topOzr = 0;
        for (Map.Entry entry : entries) {
            ++topOzr;
            if (((String) entry.getKey()).equals(user.getName()))
                break;
        }
        if (topOzr <= 5) {
            Message mess = (Message) channel.sendMessage(":lock:").complete();
            mess.editMessage(":unlock:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez etre dans le top 5 des coffres fort pour utiliser cet emoji animé")
                        .queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must be at top 5 on the Cf LeaderBoard to use this animated emoji").queue();
            }
        }
    }

    @command(name = "soundover", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void soundover(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 300) {
            Message mess = (Message) channel.sendMessage(":loud_sound:").complete();
            mess.editMessage(":sound:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":speaker:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":mute:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 300 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 300 rep to use this animated emoji").queue();
            }
        }
    }

    @command(name = "timeislong", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void timeislong(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 500) {
            Message mess = (Message) channel.sendMessage(":clock1:").complete();
            mess.editMessage(":clock2:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":clock3:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":clock4:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":clock5:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":clock6:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":clock7:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":clock8:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":clock9:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":clock10:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":clock11:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":clock12:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 500 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 500 rep to use this animated emoji").queue();
            }
        }
    }

    @command(name = "prem", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void prem(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            command.Language lang) {
        message.delete().queue();
        if (Premium.Premium(user)) {
            Message mess = (Message) channel.sendMessage(":trophy:").complete();
            mess.editMessage(":regional_indicator_p:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":regional_indicator_r:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":regional_indicator_e:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":regional_indicator_m:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":regional_indicator_i:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":regional_indicator_u:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":regional_indicator_m:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":trophy:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez etre premium pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must be premium to use this animated emoji").queue();
            }
        }
    }

    @command(name = "topreprec", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void topreprec(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        HashMap<String, Integer> classement = new HashMap<String, Integer>();
        for (Profil profil : data.getProfils().values()) {
            Object member;
            int rep = profil.getRep();
            try {
                member = jda.getUserById(profil.getId()).getName();
            } catch (NullPointerException e) {
                member = "Une personne discr\u00e8te";
            }
            classement.put((String) member, rep);
        }
        ArrayList<Entry<String, Integer>> entries = new ArrayList(classement.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> e2, Map.Entry<String, Integer> e1) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        int topOzr = 0;
        for (Map.Entry entry : entries) {
            ++topOzr;
            if (((String) entry.getKey()).equals(user.getName()))
                break;
        }
        if (topOzr <= 5) {
            Message mess = (Message) channel.sendMessage(":bed:").complete();
            mess.editMessage(":runner:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":sleeping_accommodation:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez etre top 5 dans le toprep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must be top 5 on the Rep LeaderBoard to use this animated emoji").queue();
            }
        }
    }

    @command(name = "hiver", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void hiver(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 350) {
            Message mess = (Message) channel.sendMessage(":snowman:").complete();
            mess.editMessage(":cloud_snow:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":snowman2:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 350 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 350 rep to use this animated emoji.").queue();
            }
        }
    }

    @command(name = "valentins", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void valentins(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda) {
        message.delete().queue();
        Message mess = (Message) channel.sendMessage(":revolving_hearts:").complete();
        mess.editMessage(":heart_eyes:").completeAfter(1L, TimeUnit.SECONDS);
        mess.editMessage(":angel:").completeAfter(1L, TimeUnit.SECONDS);
    }

    @command(name = "woman", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void woman(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 400) {
            Message mess = (Message) channel.sendMessage(":bride_with_veil:").complete();
            mess.editMessage(":lipstick:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":kiss:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 400 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 400 rep to use this animated emoji.").queue();
            }
        }
    }

    @command(name = "vulcain", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void vulcain(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 10) {
            Message mess = (Message) channel.sendMessage(":mount_fuji:").complete();
            mess.editMessage(":mountain_snow:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":mountain:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":volcano:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 10 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 10 rep to use this animated emoji.").queue();
            }
        }
    }

    @command(name = "rage", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void rage(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 30) {
            Message mess = (Message) channel.sendMessage(":slight_smile:").complete();
            mess.editMessage(":neutral_face:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":angry:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":rage:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 30 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 30 rep to use this animated emoji.").queue();
            }
        }
    }

    @command(name = "cry", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void cry(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 20) {
            Message mess = (Message) channel.sendMessage(":confused:").complete();
            mess.editMessage(":pensive:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":disappointed_relieved:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":sob:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 20 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 20 rep to use this animated emoji.").queue();
            }
        }
    }

    @command(name = "grow", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void grow(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 40) {
            Message mess = (Message) channel.sendMessage(":baby:").complete();
            mess.editMessage(":boy:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":man:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":older_man:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":skull_crossbones:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 40 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 40 rep to use this animated emoji.").queue();
            }
        }
    }

    @command(name = "nop", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void nop(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            ProfilData data, command.Language lang) {
        message.delete().queue();
        int rep = data.getProfils().get(user.getId()).getRep();
        if (rep >= 50) {
            Message mess = (Message) channel.sendMessage(":sunny:").complete();
            mess.editMessage(":white_sun_small_cloud:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":partly_sunny:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":white_sun_cloud:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":white_sun_rain_cloud:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":cloud_rain:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":thunder_cloud_rain:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez avoir 50 rep pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 50 rep to use this animated emoji.").queue();
            }
        }
    }

    @command(name = "fck", type = command.ExecutorType.ALL, descfr = "Affiche le level d'un joueur", topic = command.Topics.Stories)
    private void fck(MessageChannel channel, User user, String[] args, Message message, Guild guild, JDA jda,
            command.Language lang) {
        message.delete().queue();
        if (user.getId().equals("102108573298851840") || user.getId().equals("502535486691082279")
                || user.getId().equals("249987060365000704")) {
            Message mess = (Message) channel.sendMessage(":ok_hand:").complete();
            mess.editMessage(":point_left:").completeAfter(1L, TimeUnit.SECONDS);
            mess.editMessage(":sweat_drops:").completeAfter(1L, TimeUnit.SECONDS);
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous devez etre admin pour utiliser cet emoji animé").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You must have 50 rep to use this animated emoji.").queue();
            }
        }
    }

}
