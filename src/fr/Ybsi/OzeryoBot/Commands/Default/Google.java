/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Default;

import fr.Ybsi.OzeryoBot.Commands.command;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class Google {
    @command(name = "google", type = command.ExecutorType.ALL)
    private void google(String[] arg, MessageChannel channel, User user, command.Language lang) {
        StringBuilder builder = new StringBuilder();
        for (String str : arg) {
            if (builder.length() > 0) {
                builder.append("+");
            }
            builder.append(str);
        }
        if (lang == command.Language.fr) {
            channel.sendMessage("**Recherche Google** : https://www.google.fr/search?gXS7ayYCw&q=" + builder.toString())
                    .queue();
        }
        if (lang == command.Language.en) {
            channel.sendMessage("**Google Search** : https://www.google.fr/search?gXS7ayYCw&q=" + builder.toString())
                    .queue();
        }
    }
}
