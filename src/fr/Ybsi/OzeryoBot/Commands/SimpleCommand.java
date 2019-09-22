/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands;

import fr.Ybsi.OzeryoBot.Commands.command;
import java.lang.reflect.Method;

public final class SimpleCommand {
    private final String name;
    private final command.ExecutorType executorType;
    private Object object;
    private final Method method;
    private final int power;
    private String abbrev;
    private command.Topics topic;
    private String descfr;
    private String descen;
    private String use;

    public SimpleCommand(String name, String abbrev, command.Topics topic, String descfr, String descen,
            command.ExecutorType executorType, Object object, Method method, int power, String use) {
        this.name = name;
        this.descfr = descfr;
        this.descen = descen;
        this.executorType = executorType;
        this.object = object;
        this.method = method;
        this.power = power;
        this.abbrev = abbrev;
        this.topic = topic;
        this.use = use;
    }

    public Object getObject() {
        return this.object;
    }

    public int getPower() {
        return this.power;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getAbbrev() {
        return this.abbrev;
    }

    public String getName() {
        return this.name;
    }

    public command.ExecutorType getExecutorType() {
        return this.executorType;
    }

    public Method getMethod() {
        return this.method;
    }

    public command.Topics getTopic() {
        return this.topic;
    }

    public void setTopic(command.Topics topic) {
        this.topic = topic;
    }

    public String getDescen() {
        return this.descen;
    }

    public String getDescfr() {
        return this.descfr;
    }

    public String getUse() {
        return this.use;
    }
}
