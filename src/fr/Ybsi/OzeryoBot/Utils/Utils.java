/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

import java.text.DecimalFormat;

public class Utils {
    public static double arrondi(int arrondi, double nombre) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(arrondi);
        df.setMinimumFractionDigits(arrondi);
        df.setGroupingSize(0);
        double result = new Double(df.format(nombre));
        return result;
    }

    public static String format(long value) {
        double operation;
        String nombre = "" + value;
        if (value > 1000L) {
            operation = (double)value / 1000.0;
            nombre = Utils.arrondi(3, operation) + "k";
        }
        if (value > 1000000L) {
            operation = (double)value / 1000000.0;
            nombre = Utils.arrondi(3, operation) + "M";
        }
        if (value > 1000000000L) {
            operation = (double)value / 1.0E9;
            nombre = Utils.arrondi(3, operation) + "G";
        }
        if (value > 1000000000000L) {
            operation = (double)value / 1.0E12;
            nombre = Utils.arrondi(3, operation) + "T";
        }
        return nombre;
    }
}

