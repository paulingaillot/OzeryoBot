/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.core.audio.AudioSendHandler;

public class AudioHandler
implements AudioSendHandler {
    private final AudioPlayer audioPlayer;
    private AudioFrame lastFrame;

    public AudioHandler(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public boolean canProvide() {
        if (this.lastFrame == null) {
            this.lastFrame = this.audioPlayer.provide();
        }
        return this.lastFrame != null;
    }

    @Override
    public byte[] provide20MsAudio() {
        byte[] data = this.canProvide() ? this.lastFrame.getData() : null;
        this.lastFrame = null;
        return data;
    }

    @Override
    public boolean isOpus() {
        return true;
    }
}

