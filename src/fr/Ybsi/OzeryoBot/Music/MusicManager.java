/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import fr.Ybsi.OzeryoBot.Music.AudioHandler;
import fr.Ybsi.OzeryoBot.Music.MusicPlayer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import net.dv8tion.jda.core.audio.AudioSendHandler;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.managers.AudioManager;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class MusicManager {
    private final AudioPlayerManager manager = new DefaultAudioPlayerManager();
    private final Map<String, MusicPlayer> players = new HashMap<String, MusicPlayer>();

    public MusicManager() {
        AudioSourceManagers.registerRemoteSources(this.manager);
        AudioSourceManagers.registerLocalSource(this.manager);
    }

    public synchronized MusicPlayer getPlayer(Guild guild) {
        if (!this.players.containsKey(guild.getId())) {
            this.players.put(guild.getId(), new MusicPlayer(this.manager.createPlayer(), guild));
        }
        return this.players.get(guild.getId());
    }

    public void loadTrack(final MessageChannel channel, final String source, Guild guild) {
        final MusicPlayer player = this.getPlayer(guild);
        guild.getAudioManager().setSendingHandler(player.getAudioHandler());
        this.manager.loadItemOrdered(player, source, new AudioLoadResultHandler() {

            @Override
            public void trackLoaded(AudioTrack track) {
                try {
                    channel.sendMessage("Adding the track " + track.getInfo().title + ".").queue();
                } catch (NullPointerException nullPointerException) {
                    // empty catch block
                }
                player.playTrack(track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                StringBuilder builder = new StringBuilder();
                builder.append("Adding the playlist playlist **").append(playlist.getName()).append("**\n");
                for (int i = 0; i < playlist.getTracks().size(); ++i) {
                    AudioTrack track = playlist.getTracks().get(i);
                    if (i <= 5) {
                        builder.append("\n  **->** ").append(track.getInfo().title);
                    }
                    if (i == 6) {
                        builder.append("**->** **.....**");
                    }
                    player.playTrack(track);
                }
                try {
                    channel.sendMessage(builder.toString()).queue();
                } catch (NullPointerException i) {
                    // empty catch block
                }
            }

            @Override
            public void noMatches() {
                try {
                    channel.sendMessage("The song" + source + " was not found.").queue();
                } catch (NullPointerException nullPointerException) {
                    // empty catch block
                }
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                try {
                    channel.sendMessage("Impossible to play the track (reason:" + exception.getMessage() + ")").queue();
                } catch (NullPointerException nullPointerException) {
                    // empty catch block
                }
            }
        });
    }

}
