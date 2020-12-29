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
    String name();

    String abbrev() default "donotusethiscommand";

    String descfr() default "sans description";

    String descen() default "without description";

    String use() default "=[command]";

    ExecutorType type() default ExecutorType.ALL;

    Topics topic() default Topics.Util;

    int rank() default 1;

    int power() default 0;

    enum ExecutorType {
        ALL, USER, CONSOLE

    }

    enum Language {
        fr, en

    }

    enum Topics {
        Util, Game, Admin, Social, Stories, Music, Modo

    }

}
