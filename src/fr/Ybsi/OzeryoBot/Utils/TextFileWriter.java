/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;

public class TextFileWriter {
    public static void write(String filename, String text, int boucle) {
        BufferedWriter bw = null;
        FileWriter fileWriter = null;
        try {
            try {
                fileWriter = new FileWriter(filename, true);
                bw = new BufferedWriter(fileWriter);
                for (int i = 0; i < boucle; ++i) {
                    bw.newLine();
                }
                bw.write(text);
                bw.close();
            }
            catch (IOException i) {
                try {
                    bw.close();
                    fileWriter.close();
                }
                catch (IOException iOException) {}
            }
        }
        finally {
            try {
                bw.close();
                fileWriter.close();
            }
            catch (IOException iOException) {}
        }
    }

    public static void folder(String folder) {
        File Fodler = new File(folder);
        if (!Fodler.exists()) {
            Fodler.mkdir();
        }
    }

    public static void CreateFile(String file) {
        BufferedWriter bw = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, true);
            bw = new BufferedWriter(fileWriter);
            bw.close();
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    public static int folderlength(String folder) {
        int FolderLength;
        File Folder2 = new File(folder);
        if (!Folder2.exists()) {
            Folder2.mkdir();
            FolderLength = 0;
        } else {
            FolderLength = Folder2.listFiles().length;
        }
        return FolderLength;
    }

    public static File[] folderlist(String folder) {
        File[] FolderLength;
        File Folder2 = new File(folder);
        if (!Folder2.exists()) {
            Folder2.mkdir();
            FolderLength = null;
        } else {
            FolderLength = Folder2.listFiles();
        }
        return FolderLength;
    }

    public static String read(String file) {
        String text = "0";
        try {
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader((InputStream)new FileInputStream(file), "UTF-8"));
            text = line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                if (line == null) continue;
                text = line;
            }
            reader.close();
        }
        catch (IOException reader) {
            // empty catch block
        }
        return text;
    }

    public static String read1(String file, int line) {
        String text = "0";
        try {
            String content;
            BufferedReader reader = new BufferedReader(new InputStreamReader((InputStream)new FileInputStream(file), "UTF-8"));
            text = content = reader.readLine();
            for (int i = 1; i < line; ++i) {
                text = content = reader.readLine();
            }
            reader.close();
        }
        catch (IOException reader) {
            // empty catch block
        }
        return text;
    }

    public static int readint(String file) {
        int text = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader((InputStream)new FileInputStream(file), "UTF-8"));
            String line = reader.readLine();
            text = Integer.parseInt(line);
            while (line != null) {
                line = reader.readLine();
                if (line == null) continue;
                text = Integer.parseInt(line);
            }
            reader.close();
        }
        catch (IOException reader) {
            // empty catch block
        }
        return text;
    }

    public static void delete(String file) {
        File file_delete = new File(file);
        file_delete.delete();
    }

    public static void folder_delete(String file) {
        File file_delete = new File(file);
        try {
            TextFileWriter.recursifDelete(file_delete);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void recursifDelete(File path) throws IOException {
        if (!path.exists()) {
            throw new IOException("File not found '" + path.getAbsolutePath() + "'");
        }
        if (path.isDirectory()) {
            File[] children = path.listFiles();
            for (int i = 0; children != null && i < children.length; ++i) {
                TextFileWriter.recursifDelete(children[i]);
            }
            if (!path.delete()) {
                throw new IOException("No delete path '" + path.getAbsolutePath() + "'");
            }
        } else if (!path.delete()) {
            throw new IOException("No delete file '" + path.getAbsolutePath() + "'");
        }
    }
}

