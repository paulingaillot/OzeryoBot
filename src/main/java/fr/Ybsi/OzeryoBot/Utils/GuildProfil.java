/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

import java.util.ArrayList;

public class GuildProfil {
    private String id = "";
    private String name = "";
    private int xp = 0;
    private boolean silence = true;
    private ArrayList<String> Gplaylist = new ArrayList();
    private String Tmusique = "";
    private String general = "";
    private String WelcomeMessage = "\ud83c\udf89\ud83c\udf89 Tu es desormais en compagnie de {players} users.\n Passe un bon moment ici. \ud83c\udf89\ud83c\udf89";
    private String pubchannel = "";
    private String Pub = "";
    private int pub_sent = 0;
    private int pub_liked = 0;
    private int pub_reported = 0;
    private long last_update = 0L;

    public GuildProfil() {
    }

    public GuildProfil(String id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getXp() {
        return this.xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public boolean isSilence() {
        return this.silence;
    }

    public void setSilence(boolean silence) {
        this.silence = silence;
    }

    public ArrayList<String> getGplaylist() {
        return this.Gplaylist;
    }

    public void setGplaylist(ArrayList<String> gplaylist) {
        this.Gplaylist = gplaylist;
    }

    public String getWelcomeMessage() {
        return this.WelcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.WelcomeMessage = welcomeMessage;
    }

    public String getTmusique() {
        return this.Tmusique;
    }

    public void setTmusique(String tmusique) {
        this.Tmusique = tmusique;
    }

    public String getGeneral() {
        return this.general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public String getPubchannel() {
        return this.pubchannel;
    }

    public void setPubchannel(String pubchannel) {
        this.pubchannel = pubchannel;
    }

    public String getPub() {
        return this.Pub;
    }

    public void setPub(String pub) {
        this.Pub = pub;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPub_liked() {
        return this.pub_liked;
    }

    public void setPub_liked(int pub_liked) {
        this.pub_liked = pub_liked;
    }

    public int getPub_sent() {
        return this.pub_sent;
    }

    public void setPub_sent(int pub_sent) {
        this.pub_sent = pub_sent;
    }

    public int getPub_reported() {
        return this.pub_reported;
    }

    public void setPub_reported(int pub_reported) {
        this.pub_reported = pub_reported;
    }

    public long getLast_update() {
        return this.last_update;
    }

    public void setLast_update(long last_update) {
        this.last_update = last_update;
    }
}
