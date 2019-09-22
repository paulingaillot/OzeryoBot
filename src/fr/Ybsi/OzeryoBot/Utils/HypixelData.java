/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

import fr.Ybsi.OzeryoBot.Utils.DataSerializerManager;
import fr.Ybsi.OzeryoBot.Utils.FileUtils;
import fr.Ybsi.OzeryoBot.Utils.Hypixel;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class HypixelData {
    private ArrayList<String> HypixelQueue = new ArrayList();
    private HashMap<String, Hypixel> HypixelStats = new HashMap();

    public static HypixelData loadData(File file) {
        DataSerializerManager hsm = new DataSerializerManager();
        String json = FileUtils.loadContant(file);
        HypixelData homeM = hsm.deserializeHypixelData(json);
        return homeM;
    }

    public void saveData(String path) {
        File file = HypixelData.getFile(path);
        DataSerializerManager hsm = new DataSerializerManager();
        String json = hsm.serializeHypixelData(this);
        FileUtils.save(file, json);
    }

    public static File getFile(String path) {
        return new File(path, "/hypixeData.json");
    }

    public HashMap<String, Hypixel> getHypixelStats() {
        return this.HypixelStats;
    }

    public ArrayList<String> getHypixelQueue() {
        return this.HypixelQueue;
    }

    public void setHypixelQueue(ArrayList<String> hypixelQueue) {
        this.HypixelQueue = hypixelQueue;
    }
}

