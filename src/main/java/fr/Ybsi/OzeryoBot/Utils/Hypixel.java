/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

public class Hypixel {
    private String ID = "inconnu";
    private Integer pitStats = 0;
    private Integer pitRank = 0;
    private Integer legacyStats = 0;
    private Integer legacyRank = 0;

    public Hypixel() {
    }

    public Hypixel(String ID) {
        this.ID = ID;
    }

    public Integer getPitStats() {
        return this.pitStats;
    }

    public void setPitStats(Integer pitStats) {
        this.pitStats = pitStats;
    }

    public Integer getPitRank() {
        return this.pitRank;
    }

    public void setPitRank(Integer pitRank) {
        this.pitRank = pitRank;
    }

    public Integer getLegacyStats() {
        return this.legacyStats;
    }

    public void setLegacyStats(Integer legacyStats) {
        this.legacyStats = legacyStats;
    }

    public Integer getLegacyRank() {
        return this.legacyRank;
    }

    public void setLegacyRank(Integer legacyRank) {
        this.legacyRank = legacyRank;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String iD) {
        this.ID = iD;
    }
}
