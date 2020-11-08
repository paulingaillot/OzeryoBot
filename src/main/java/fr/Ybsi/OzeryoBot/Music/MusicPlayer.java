/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MusicPlayer {
    private final AudioPlayer audioPlayer;
    private final AudioListener listener;
    private final Guild guild;
    private final BlockingQueue<AudioTrack> tracks = new LinkedBlockingQueue<AudioTrack>();

    public MusicPlayer(AudioPlayer audioPlayer, Guild guild) {
        this.audioPlayer = audioPlayer;
        this.guild = guild;
        this.listener = new AudioListener(this, guild);
        audioPlayer.addListener(this.listener);
    }

    public AudioPlayer getAudioPlayer() {
        return this.audioPlayer;
    }

    public Guild getGuild() {
        return this.guild;
    }

    public AudioListener getListener() {
        return this.listener;
    }

    public AudioHandler getAudioHandler() {
        return new AudioHandler(this.audioPlayer);
    }

    public synchronized void playTrack(AudioTrack track) {
        this.listener.queue(track);
    }

    public synchronized void skipTrack() {
        this.listener.nextTrack();
    }
}
