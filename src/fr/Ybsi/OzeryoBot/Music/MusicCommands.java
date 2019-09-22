/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.DiscordBot;
import fr.Ybsi.OzeryoBot.Music.AudioListener;
import fr.Ybsi.OzeryoBot.Music.MusicManager;
import fr.Ybsi.OzeryoBot.Music.MusicPlayer;
import fr.Ybsi.OzeryoBot.Utils.GuildProfil;
import fr.Ybsi.OzeryoBot.Utils.GuildProfilData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.GuildVoiceState;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.managers.AudioManager;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class MusicCommands {
    private static final MusicManager manager = new MusicManager();

    public static void MusicStop(Guild guild) {
        MusicPlayer player = manager.getPlayer(guild);
        player.getListener().getTracks().clear();
        player.getAudioPlayer().stopTrack();
        player.getAudioPlayer().destroy();
    }

    public static void Gplaylist(VoiceChannel voicechannel, Guild guild) {
        guild.getAudioManager().openAudioConnection(voicechannel);
        ArrayList<String> list = DiscordBot.getGuilddata().getGuildProfil().get(guild.getId()).getGplaylist();
        try {
            for (String track : list) {
                if (DiscordBot.getGuilddata().getGuildProfil().get(guild.getId()).getTmusique() == "") {
                    manager.loadTrack(null, track, guild);
                    continue;
                }
                if (DiscordBot.getGuilddata().getGuildProfil().get(guild.getId()).getTmusique() == "false") {
                    manager.loadTrack(null, track, guild);
                    continue;
                }
                TextChannel channel2 = guild.getTextChannelById(DiscordBot.getGuilddata().getGuildProfil().get(guild.getId()).getTmusique());
                manager.loadTrack(guild.getTextChannelById(channel2.getId()), track, guild);
            }
        }
        catch (NullPointerException e) {
            manager.loadTrack(null, "https://www.youtube.com/watch?v=pMarN41uJqE&list=PLRBp0Fe2GpgnIh0AiYKh7o7HnYAej-5ph", guild);
            return;
        }
    }

    @command(name="play", type=command.ExecutorType.USER, topic=command.Topics.Music)
    private void play(Guild guild, TextChannel textChannel, User user, String command2, command.Language lang) {
        if (guild == null) {
            return;
        }
        if (!guild.getAudioManager().isConnected() && !guild.getAudioManager().isAttemptingToConnect()) {
            VoiceChannel voiceChannel = guild.getMember(user).getVoiceState().getChannel();
            if (voiceChannel == null) {
                if (lang == command.Language.fr) {
                    textChannel.sendMessage("Vous devez \u00eatre connect\u00e9 \u00e0 un salon vocal.").queue();
                }
                if (lang == command.Language.en) {
                    textChannel.sendMessage("You must be connected on a vocal channel.").queue();
                }
                return;
            }
            guild.getAudioManager().openAudioConnection(voiceChannel);
        }
        manager.loadTrack(textChannel, command2.replaceFirst("play ", ""), guild);
    }

    @command(name="skip", type=command.ExecutorType.USER, topic=command.Topics.Music)
    private void skip(Guild guild, TextChannel textChannel, command.Language lang) {
        if (!guild.getAudioManager().isConnected() && !guild.getAudioManager().isAttemptingToConnect()) {
            if (lang == command.Language.fr) {
                textChannel.sendMessage("Le player n'as pas de piste en cours.").queue();
            }
            if (lang == command.Language.en) {
                textChannel.sendMessage("The player doesn't have any track in progress.").queue();
            }
            return;
        }
        manager.getPlayer(guild).skipTrack();
        textChannel.sendMessage("La lecture est pass\u00e9 \u00e0 la piste suivante.").queue();
    }

    @command(name="Gplaylist", type=command.ExecutorType.USER, topic=command.Topics.Music)
    private void Gplaylist(MessageChannel channel, String[] args, Guild guild, User user, String command2, command.Language lang) {
        String message;
        String c2;
        ArrayList<String> list;
        String c1;
        GuildProfilData data = DiscordBot.getGuilddata();
        StringBuilder builder = new StringBuilder();
        for (String str : args) {
            if (str.equals(args[0])) continue;
            builder.append(String.valueOf(str));
        }
        try {
            c1 = args[0];
        }
        catch (Exception e) {
            if (lang == command.Language.fr) {
                channel.sendMessage("Syntaxe : ``=Gplaylist [play/add] [URL]``.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("Syntax : ``=Gplaylist [play/add] [URL]``.").queue();
            }
            return;
        }
        try {
            c2 = builder.toString();
        }
        catch (NullPointerException e) {
            c2 = "";
        }
        if (c1.equals("add")) {
            ArrayList<String> playlist = data.getGuildProfil().get(guild.getId()).getGplaylist();
            playlist.add(c2);
            data.getGuildProfil().get(guild.getId()).setGplaylist(playlist);
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous venez d'ajouter la musique : " + c2 + " a la playlist du serveur.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You just add the song : " + c2 + " To the guild playlist.").queue();
            }
            return;
        }
        if (c1.equals("list")) {
            list = data.getGuildProfil().get(guild.getId()).getGplaylist();
            message = "**Guild Playlist** \n";
            int i = 1;
            for (String track : list) {
                message = String.valueOf(message) + "**" + i + "** - " + track + "\n";
                ++i;
            }
            channel.sendMessage(message).queue();
        } else if (c1.equals("remove")) {
            if (c2.equals("")) {
                list = data.getGuildProfil().get(guild.getId()).getGplaylist();
                message = "**Guild Playlist** \n";
                int i = 1;
                for (String track : list) {
                    message = String.valueOf(message) + "**" + i + "** - " + track + "\n";
                    ++i;
                }
                if (lang == command.Language.fr) {
                    message = String.valueOf(message) + " **Veuillez indiquer le numero de la musique a supprimer de la playlist.**";
                }
                if (lang == command.Language.en) {
                    message = String.valueOf(message) + " **You must indicate the number of the song you want to remove from the playlist.**";
                }
                channel.sendMessage(message).queue();
            }
        } else if (c1.equals("play")) {
            VoiceChannel voiceChannel = guild.getMember(user).getVoiceState().getChannel();
            guild.getAudioManager().openAudioConnection(voiceChannel);
            for (String track : data.getGuildProfil().get(guild.getId()).getGplaylist()) {
                manager.loadTrack(channel, track, guild);
            }
        } else if (c1.equals("clear")) {
            data.getGuildProfil().get(guild.getId()).setGplaylist(new ArrayList<String>());
            if (lang == command.Language.fr) {
                channel.sendMessage("La playlist de votre guilde a \u00e9t\u00e9 nettoy\u00e9e.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("Your Guild Playlist has been clear.").queue();
            }
        }
    }

    @command(name="volume", type=command.ExecutorType.USER, topic=command.Topics.Music)
    private void volume(TextChannel textChannel, MessageChannel channel, String[] args, command.Language lang) {
        StringBuilder builder = new StringBuilder();
        for (String str : args) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(str);
        }
        if (Integer.parseInt(builder.toString()) <= 200) {
            MusicPlayer player = null;
            try {
                player = manager.getPlayer(textChannel.getGuild());
            }
            catch (Exception e) {
                if (lang == command.Language.fr) {
                    channel.sendMessage("Vous devez etre en train d'ecouter de la musique avec le bot pour pouvoir utiliser cet commande.").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage("You must be listening music with the bot to use this command.").queue();
                }
                return;
            }
            player.getAudioPlayer().setVolume(Integer.parseInt(builder.toString()));
            if (lang == command.Language.fr) {
                channel.sendMessage("le volume a \u00e9t\u00e9 set a :" + builder.toString()).queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("The volume is set on :" + builder.toString()).queue();
            }
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage("le volume ne peut pas exceder 200 !").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("The volume can't be above 200 !").queue();
            }
        }
    }

    @command(name="stop", type=command.ExecutorType.USER, topic=command.Topics.Music)
    private void stop(TextChannel textChannel, MessageChannel channel, String[] args, VoiceChannel voice, Guild guild, User user, command.Language lang) {
        MusicPlayer player = manager.getPlayer(textChannel.getGuild());
        player.getListener().getTracks().clear();
        player.getAudioPlayer().stopTrack();
        player.getAudioPlayer().destroy();
        guild.getAudioManager().closeAudioConnection();
        if (lang == command.Language.fr) {
            channel.sendMessage("La musique a \u00e9t\u00e9 arret\u00e9e").queue();
        }
        if (lang == command.Language.en) {
            channel.sendMessage("The song stopped").queue();
        }
    }

    @command(name="song", type=command.ExecutorType.USER, topic=command.Topics.Music)
    private void song(TextChannel textChannel, MessageChannel channel, User user, command.Language lang) {
        MusicPlayer player;
        try {
            player = manager.getPlayer(textChannel.getGuild());
        }
        catch (Exception e) {
            if (lang == command.Language.fr) {
                channel.sendMessage("Vous ne pouvez pas utiliser cet commande si vous n'ecoutez pas de musique avec le bot actuelement.").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("You can't use this command if you'renot listening music with the bot actually.").queue();
            }
            return;
        }
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(user.getName(), null, user.getAvatarUrl());
        builder.setThumbnail("http://ozeryo.sytes.net/images/note_de_musique.jpg");
        if (lang == command.Language.fr) {
            builder.addField("Auteur", player.getAudioPlayer().getPlayingTrack().getInfo().author, true);
        }
        if (lang == command.Language.en) {
            builder.addField("Author", player.getAudioPlayer().getPlayingTrack().getInfo().author, true);
        }
        if (lang == command.Language.fr) {
            builder.addField("Titre", player.getAudioPlayer().getPlayingTrack().getInfo().title, true);
        }
        if (lang == command.Language.en) {
            builder.addField("Title", player.getAudioPlayer().getPlayingTrack().getInfo().title, true);
        }
        if (lang == command.Language.fr) {
            builder.addField("Dur\u00e9e", String.valueOf((double)player.getAudioPlayer().getPlayingTrack().getInfo().length / 60000.0) + " minutes", true);
        }
        if (lang == command.Language.en) {
            builder.addField("Duration", String.valueOf((double)player.getAudioPlayer().getPlayingTrack().getInfo().length / 60000.0) + " minutes", true);
        }
        builder.addField("URL", player.getAudioPlayer().getPlayingTrack().getInfo().uri, true);
        channel.sendMessage(builder.build()).queue();
    }

    @command(name="pause", type=command.ExecutorType.USER, topic=command.Topics.Music)
    private void pause(TextChannel textChannel, MessageChannel channel, String[] args, VoiceChannel voice, Guild guild, User user, command.Language lang) {
        MusicPlayer player = manager.getPlayer(textChannel.getGuild());
        if (player.getAudioPlayer().isPaused()) {
            player.getAudioPlayer().setPaused(false);
            if (lang == command.Language.fr) {
                channel.sendMessage("La musique n'est plus en pause").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("The song is no longer paused").queue();
            }
        } else {
            player.getAudioPlayer().setPaused(true);
            if (lang == command.Language.fr) {
                channel.sendMessage("La musique a \u00e9t\u00e9 mise en pause").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("The song is now paused").queue();
            }
        }
    }

    @command(name="shuffle", type=command.ExecutorType.USER, topic=command.Topics.Music)
    private void shuffle(TextChannel textChannel, MessageChannel channel, String[] args, VoiceChannel voice, Guild guild, User user, JDA jda, command.Language lang) {
        MusicPlayer player = manager.getPlayer(textChannel.getGuild());
        BlockingQueue<AudioTrack> tracks = player.getListener().getTracks();
        for (AudioTrack Track2 : tracks) {
            ArrayList<String> list = new ArrayList<String>();
            String url = Track2.getInfo().uri;
            int alea = 0 + (int)(Math.random() * (double)(list.size() - 1 - 0 + 1));
            list.add(alea, url);
            player.getListener().getTracks().clear();
            player.getAudioPlayer().stopTrack();
            player.getAudioPlayer().destroy();
            for (String track : list) {
                manager.loadTrack(channel, track, guild);
            }
        }
        if (lang == command.Language.fr) {
            channel.sendMessage("La Liste de lecture a \u00e9t\u00e9 m\u00e9lang\u00e9.").queue();
        }
        if (lang == command.Language.en) {
            channel.sendMessage("The music list has been shuffled.").queue();
        }
    }
}

