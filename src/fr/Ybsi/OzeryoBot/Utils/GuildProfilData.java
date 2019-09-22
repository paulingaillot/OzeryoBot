/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

import fr.Ybsi.OzeryoBot.Utils.DataSerializerManager;
import fr.Ybsi.OzeryoBot.Utils.FileUtils;
import fr.Ybsi.OzeryoBot.Utils.GuildProfil;
import java.io.File;
import java.util.HashMap;

public class GuildProfilData {
    private HashMap<String, GuildProfil> GuildProfil = new HashMap();

    public static GuildProfilData loadData(File file) {
        DataSerializerManager hsm = new DataSerializerManager();
        String json = FileUtils.loadContant(file);
        GuildProfilData homeM = hsm.deserializeGuildData(json);
        return homeM;
    }

    public void saveData(String path) {
        File file = GuildProfilData.getFile(path);
        DataSerializerManager hsm = new DataSerializerManager();
        String json = hsm.serializeGuildData(this);
        FileUtils.save(file, json);
    }

    public static File getFile(String path) {
        return new File(path, "/guildData.json");
    }

    public HashMap<String, GuildProfil> getGuildProfil() {
        return this.GuildProfil;
    }

    public void setGuildProfil(HashMap<String, GuildProfil> guildProfil) {
        this.GuildProfil = guildProfil;
    }
}
