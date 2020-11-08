/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.api.entities.Guild;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AudioListener extends AudioEventAdapter {
    private final BlockingQueue<AudioTrack> tracks = new LinkedBlockingQueue<AudioTrack>();
    private final MusicPlayer player;
    private final MusicManager manager = new MusicManager();
    private final Guild guild;

    public AudioListener(MusicPlayer player, Guild guild) {
        this.player = player;
        this.guild = guild;
    }

    public BlockingQueue<AudioTrack> getTracks() {
        return this.tracks;
    }

    public int getTrackSize() {
        return this.tracks.size();
    }

    public void nextTrack() {
        if (this.tracks.isEmpty()) {
            return;
        }
        this.player.getAudioPlayer().startTrack((AudioTrack) this.tracks.poll(), false);
    }

    @Override
    public void onTrackEnd(AudioPlayer players, AudioTrack track, AudioTrackEndReason endReason) {
        if (endReason.mayStartNext) {
            this.nextTrack();
        } else {
            this.player.getGuild().getAudioManager().closeAudioConnection();
            players.destroy();
        }
    }

    public void queue(AudioTrack track) {
        if (!this.player.getAudioPlayer().startTrack(track, true)) {
            this.tracks.offer(track);
        }
    }

    public Guild getGuild() {
        return this.guild;
    }
}
