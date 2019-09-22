/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

import fr.Ybsi.OzeryoBot.Utils.DataSerializerManager;
import fr.Ybsi.OzeryoBot.Utils.FileUtils;
import fr.Ybsi.OzeryoBot.Utils.LevelProfil;
import java.io.File;
import java.util.HashMap;

public class LevelProfilData {
    private HashMap<String, LevelProfil> levelProfil = new HashMap();

    public static LevelProfilData loadData(File file) {
        DataSerializerManager hsm = new DataSerializerManager();
        String json = FileUtils.loadContant(file);
        LevelProfilData homeM = hsm.deserializeLevelData(json);
        return homeM;
    }

    public void saveData(String path) {
        File file = LevelProfilData.getFile(path);
        DataSerializerManager hsm = new DataSerializerManager();
        String json = hsm.serializeLevelData(this);
        FileUtils.save(file, json);
    }

    public static File getFile(String path) {
        return new File(path, "/levelData.json");
    }

    public HashMap<String, LevelProfil> getLevelProfil() {
        return this.levelProfil;
    }
}
