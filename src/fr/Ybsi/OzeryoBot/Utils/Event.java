/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

import java.util.Date;

public class Event {
    public static boolean Summer() {
        Date dateA = new Date();
        Date dateB = new Date(119, 6, 7, 23, 59, 59);
        Date dateC = new Date(119, 8, 14, 23, 59, 59);
        if (dateA.equals(dateB)) {
            return true;
        }
        if (dateA.after(dateC)) {
            return false;
        }
        if (dateA.before(dateB)) {
            return false;
        }
        return dateA.before(dateC) && dateA.after(dateB);
    }
}
