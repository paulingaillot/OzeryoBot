/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface command {
    public String name();

    public String abbrev() default "donotusethiscommand";

    public String descfr() default "sans description";

    public String descen() default "without description";

    public String use() default "=[command]";

    public ExecutorType type() default ExecutorType.ALL;

    public Topics topic() default Topics.Util;

    public int rank() default 1;

    public int power() default 0;

    public static enum ExecutorType {
        ALL, USER, CONSOLE;

    }

    public static enum Language {
        fr, en;

    }

    public static enum Topics {
        Util, Game, Admin, Social, Stories, Music, Modo;

    }

}
