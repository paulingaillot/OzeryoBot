/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Game;

public class Structure {
    public static double entreprise(int level) {
        double bonus = 1.0;
        switch (level) {
            case 0: {
                bonus = 1.0;
                break;
            }
            case 1: {
                bonus = 1.125;
                break;
            }
            case 2: {
                bonus = 1.25;
                break;
            }
            case 3: {
                bonus = 1.5;
                break;
            }
            case 4: {
                bonus = 2.0;
                break;
            }
            case 5: {
                bonus = 2.5;
                break;
            }
            case 6: {
                bonus = 3.0;
                break;
            }
            case 7: {
                bonus = 3.5;
                break;
            }
            case 8: {
                bonus = 4.0;
                break;
            }
            case 9: {
                bonus = 4.5;
                break;
            }
            case 10: {
                bonus = 5.0;
                break;
            }
            case 11: {
                bonus = 5.5;
                break;
            }
            case 12: {
                bonus = 6.0;
                break;
            }
            case 13: {
                bonus = 6.5;
                break;
            }
            case 14: {
                bonus = 7.0;
                break;
            }
            case 15: {
                bonus = 7.5;
                break;
            }
            case 16: {
                bonus = 8.0;
                break;
            }
            case 17: {
                bonus = 8.5;
                break;
            }
            case 18: {
                bonus = 9.0;
                break;
            }
            case 19: {
                bonus = 9.5;
                break;
            }
            case 20: {
                bonus = 10.0;
                break;
            }
            case 21: {
                bonus = 11.0;
                break;
            }
            case 22: {
                bonus = 12.0;
                break;
            }
            case 23: {
                bonus = 13.0;
                break;
            }
            case 24: {
                bonus = 14.0;
                break;
            }
            case 25: {
                bonus = 15.0;
                break;
            }
            default: {
                bonus = level - 10;
            }
        }
        return bonus;
    }
}

