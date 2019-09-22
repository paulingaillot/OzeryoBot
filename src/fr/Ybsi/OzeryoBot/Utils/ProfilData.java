/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

import fr.Ybsi.OzeryoBot.Utils.DataSerializerManager;
import fr.Ybsi.OzeryoBot.Utils.FileUtils;
import fr.Ybsi.OzeryoBot.Utils.Profil;
import java.io.File;
import java.util.HashMap;

public class ProfilData {
    private HashMap<String, Profil> profils = new HashMap();
    private int LbPremiumWeek = 30;
    private int registers = 0;
    private long nextcf = 0L;

    public HashMap<String, Profil> getProfils() {
        return this.profils;
    }

    public static ProfilData loadData(File file) {
        DataSerializerManager hsm = new DataSerializerManager();
        String json = FileUtils.loadContant(file);
        ProfilData homeM = hsm.deserialize(json);
        return homeM;
    }

    public void saveData(String path) {
        File file = ProfilData.getFile(path);
        DataSerializerManager hsm = new DataSerializerManager();
        String json = hsm.serialize(this);
        FileUtils.save(file, json);
    }

    public static File getFile(String path) {
        return new File(path, "/profilData.json");
    }

    public int getLbPremiumWeek() {
        return this.LbPremiumWeek;
    }

    public void setLbPremiumWeek(int lbPremiumWeek) {
        this.LbPremiumWeek = lbPremiumWeek;
    }

    public int getRegisters() {
        return this.registers;
    }

    public void setRegisters(int registers) {
        this.registers = registers;
    }

    public long getNextcf() {
        return this.nextcf;
    }

    public void setNextcf(long nextcf) {
        this.nextcf = nextcf;
    }
}

