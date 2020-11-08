/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

public class LevelProfil {
    private String id = "";
    private String name = "";
    private String IconURL = "";
    private int xp = 0;
    private long lastmessage = 0L;
    private int toplevel = 0;

    public LevelProfil() {
    }

    public LevelProfil(String id) {
        this.setId(id);
    }

    public int getXp() {
        return this.xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getLastmessage() {
        return this.lastmessage;
    }

    public void setLastmessage(long lastmessage) {
        this.lastmessage = lastmessage;
    }

    public int getToplevel() {
        return this.toplevel;
    }

    public void setToplevel(int toplevel) {
        this.toplevel = toplevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconURL() {
        return IconURL;
    }

    public void setIconURL(String iconURL) {
        IconURL = iconURL;
    }
}
