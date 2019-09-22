/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class FileUtils {
    public static void createFile(File file) throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    public static void save(File file, String text) {
        try {
            FileUtils.createFile(file);
            FileWriter fw = new FileWriter(file);
            fw.write(text);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadContant(File file) {
        if (file.exists()) {
            try {
                String line;
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder text = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    text.append(line);
                }
                reader.close();
                return text.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
