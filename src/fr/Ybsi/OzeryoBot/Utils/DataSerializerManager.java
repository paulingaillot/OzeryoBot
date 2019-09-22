/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.Ybsi.OzeryoBot.Utils.GuildProfilData;
import fr.Ybsi.OzeryoBot.Utils.HypixelData;
import fr.Ybsi.OzeryoBot.Utils.LevelProfilData;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;

public class DataSerializerManager {
    private Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setLenient().create();

    public String serialize(ProfilData s) {
        return this.gson.toJson(s);
    }

    public ProfilData deserialize(String json) {
        return this.gson.fromJson(json, ProfilData.class);
    }

    public HypixelData deserializeHypixelData(String json) {
        return this.gson.fromJson(json, HypixelData.class);
    }

    public GuildProfilData deserializeGuildData(String json) {
        return this.gson.fromJson(json, GuildProfilData.class);
    }

    public LevelProfilData deserializeLevelData(String json) {
        return this.gson.fromJson(json, LevelProfilData.class);
    }

    public String serializeHypixelData(HypixelData s) {
        return this.gson.toJson(s);
    }

    public String serializeGuildData(GuildProfilData s) {
        return this.gson.toJson(s);
    }

    public String serializeLevelData(LevelProfilData s) {
        return this.gson.toJson(s);
    }
}

