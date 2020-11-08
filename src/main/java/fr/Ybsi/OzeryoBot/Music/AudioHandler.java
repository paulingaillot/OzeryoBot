/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.api.audio.AudioSendHandler;

import java.nio.ByteBuffer;

public class AudioHandler implements AudioSendHandler {
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
    public ByteBuffer provide20MsAudio() {
        ByteBuffer data = this.canProvide() ? ByteBuffer.wrap(this.lastFrame.getData()) : null;
        this.lastFrame = null;
        return data;
    }

    @Override
    public boolean isOpus() {
        return true;
    }
}
