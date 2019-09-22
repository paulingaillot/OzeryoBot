/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

import java.util.Comparator;
import java.util.Map;

public class ValueComparator
implements Comparator<String> {
    Map<String, Integer> base;

    public ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }

    @Override
    public int compare(String a, String b) {
        if (this.base.get(a) >= this.base.get(b)) {
            return -1;
        }
        return 1;
    }
}

