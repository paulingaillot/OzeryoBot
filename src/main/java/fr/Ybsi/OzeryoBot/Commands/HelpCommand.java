/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands;

import fr.Ybsi.OzeryoBot.Utils.Scheduler;
import fr.Ybsi.OzeryoBot.Utils.color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.internal.entities.UserImpl;

import java.time.Instant;

public class HelpCommand {
    private final CommandMap commandMap;

    public HelpCommand(CommandMap commandMap) {
        this.commandMap = commandMap;
    }

    @command(name = "help", type = command.ExecutorType.USER, descfr = "affiche la liste des commandes")
    private void help(User user, TextChannel channel, Guild guild, String[] args, Message message, JDA jda,
                      command.Language lang) {
        EmbedBuilder builder1;
        StringBuilder buider = new StringBuilder();
        String[] arrstring = args;
        int n = arrstring.length;
        for (int i = 0; i < n; ++i) {
            String str = arrstring[i];
            if (str.equals(args[0]))
                continue;
            if (buider.length() > 0) {
                buider.append(" ");
            }
            buider.append(str);
        }
        StringBuilder buder = new StringBuilder();
        for (String str : args) {
            if (buder.length() > 0) {
                buder.append(" ");
            }
            buder.append(str);
        }
        String c1 = buder.toString();
        String c2 = buider.toString();
        if (c1.equals("")) {
            EmbedBuilder builder12 = new EmbedBuilder();
            builder12.setTitle("Commands");
            builder12.addField("\ud83d\udc51 Admin help", "**=help admin**", true);
            builder12.addField(":video_game:Game help", "**=help game**", true);
            builder12.addField(":musical_note: Music help", "**=help music**", true);
            builder12.addField(":fire: Social help", "**=help social**", true);
            builder12.addField(":zap: Util help", "**=help util**", true);
            builder12.addField(":unicorn: Stories help", "**=help stories**", true);
            builder12.addField("\ud83c\udf0e General help", "**=help all**", true);
            channel.sendMessage(builder12.build()).queue();
        } else if (c1.equals("admin")) {
            String admin = "";
            for (SimpleCommand command2 : CommandMap.getCommands()) {
                if (command2.getExecutorType() == command.ExecutorType.CONSOLE
                        || command2.getTopic() != command.Topics.Modo)
                    continue;
                admin = admin + "``" + command2.getName() + "``, ";
            }
            builder1 = new EmbedBuilder();
            builder1.setTitle("\u2139 | Commands | \u2139");
            builder1.setColor(color.couleurAleatoire(user));
            builder1.setAuthor(user.getName());
            builder1.setFooter(guild.getName(), guild.getIconUrl());
            builder1.setTimestamp(Instant.now());
            builder1.addField("\ud83d\udc6e **Moderation** \ud83d\udc6e",
                    new StringBuilder(admin).deleteCharAt(admin.length() - 2).toString(), false);
            if (!user.hasPrivateChannel()) {
                user.openPrivateChannel().complete();
            }
            ((UserImpl) user).getPrivateChannel().sendMessage(builder1.build()).queue();
            if (guild.getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(user.getAsMention() + " regarde tes messages privés").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(user.getAsMention() + " see your private messages").queue();
                }
            }
        } else if (c1.equals("util")) {
            String util = "";
            for (SimpleCommand command2 : CommandMap.getCommands()) {
                if (command2.getExecutorType() == command.ExecutorType.CONSOLE
                        || command2.getTopic() != command.Topics.Util)
                    continue;
                util = util + "``" + command2.getName() + "``, ";
            }
            builder1 = new EmbedBuilder();
            builder1.setTitle("\u2139 | Commands | \u2139");
            builder1.setColor(color.couleurAleatoire(user));
            builder1.setAuthor(user.getName());
            builder1.setFooter(guild.getName(), guild.getIconUrl());
            builder1.setTimestamp(Instant.now());
            builder1.addField("\u26a1  **Util** \u26a1",
                    new StringBuilder(util).deleteCharAt(util.length() - 2).toString(), false);
            if (!user.hasPrivateChannel()) {
                user.openPrivateChannel().complete();
            }
            ((UserImpl) user).getPrivateChannel().sendMessage(builder1.build()).queue();
            if (guild.getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(user.getAsMention() + " regarde tes messages privés").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(user.getAsMention() + " see your private messages").queue();
                }
            }
        } else if (c1.equals("music")) {
            String music = "";
            for (SimpleCommand command2 : CommandMap.getCommands()) {
                if (command2.getExecutorType() == command.ExecutorType.CONSOLE
                        || command2.getTopic() != command.Topics.Music)
                    continue;
                music = music + "``" + command2.getName() + "``, ";
            }
            builder1 = new EmbedBuilder();
            builder1.setTitle("\u2139 | Commands | \u2139");
            builder1.setColor(color.couleurAleatoire(user));
            builder1.setAuthor(user.getName());
            builder1.setFooter(guild.getName(), guild.getIconUrl());
            builder1.setTimestamp(Instant.now());
            builder1.addField("\ud83c\udfb5 **Music** \ud83c\udfb5",
                    new StringBuilder(music).deleteCharAt(music.length() - 2).toString(), false);
            if (!user.hasPrivateChannel()) {
                user.openPrivateChannel().complete();
            }
            ((UserImpl) user).getPrivateChannel().sendMessage(builder1.build()).queue();
            if (guild.getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(user.getAsMention() + " regarde tes messages privés").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(user.getAsMention() + " see your private messages").queue();
                }
            }
        } else if (c1.equals("game")) {
            String game = "";
            for (SimpleCommand command2 : CommandMap.getCommands()) {
                if (command2.getExecutorType() == command.ExecutorType.CONSOLE || game.contains(command2.getName())
                        || command2.getTopic() != command.Topics.Game)
                    continue;
                game = game + "``" + command2.getName() + "``, ";
            }
            builder1 = new EmbedBuilder();
            builder1.setTitle("\u2139 | Commands | \u2139");
            builder1.setColor(color.couleurAleatoire(user));
            builder1.setAuthor(user.getName());
            builder1.setFooter(guild.getName(), guild.getIconUrl());
            builder1.setTimestamp(Instant.now());
            builder1.addField("\ud83c\udfae **Game ** \ud83c\udfae",
                    new StringBuilder(game).deleteCharAt(game.length() - 2).toString(), true);
            if (!user.hasPrivateChannel()) {
                user.openPrivateChannel().complete();
            }
            ((UserImpl) user).getPrivateChannel().sendMessage(builder1.build()).queue();
            if (guild.getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(user.getAsMention() + " regarde tes messages privés").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(user.getAsMention() + " see your private messages").queue();
                }
            }
        } else if (c1.equals("social")) {
            String social = "";
            for (SimpleCommand command2 : CommandMap.getCommands()) {
                if (command2.getExecutorType() == command.ExecutorType.CONSOLE
                        || command2.getTopic() != command.Topics.Social)
                    continue;
                social = social + "``" + command2.getName() + "``, ";
            }
            builder1 = new EmbedBuilder();
            builder1.setTitle("\u2139 | Commands | \u2139");
            builder1.setColor(color.couleurAleatoire(user));
            builder1.setAuthor(user.getName());
            builder1.setFooter(guild.getName(), guild.getIconUrl());
            builder1.setTimestamp(Instant.now());
            builder1.addField("\ud83d\udd25 **Social** \ud83d\udd25",
                    new StringBuilder(social).deleteCharAt(social.length() - 2).toString(), true);
            if (!user.hasPrivateChannel()) {
                user.openPrivateChannel().complete();
            }
            ((UserImpl) user).getPrivateChannel().sendMessage(builder1.build()).queue();
            if (guild.getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(user.getAsMention() + " regarde tes messages privés").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(user.getAsMention() + " see your private messages").queue();
                }
            }
        } else if (c1.equals("stories")) {
            String stories = "";
            for (SimpleCommand command2 : CommandMap.getCommands()) {
                if (command2.getExecutorType() == command.ExecutorType.CONSOLE
                        || command2.getTopic() != command.Topics.Stories)
                    continue;
                stories = stories + "``" + command2.getName() + "``, ";
            }
            builder1 = new EmbedBuilder();
            builder1.setTitle("\u2139 | Commands | \u2139");
            builder1.setColor(color.couleurAleatoire(user));
            builder1.setAuthor(user.getName());
            builder1.setFooter(guild.getName(), guild.getIconUrl());
            builder1.setTimestamp(Instant.now());
            builder1.addField("\ud83e\udd84 **Stories** \ud83e\udd84",
                    new StringBuilder(stories).deleteCharAt(stories.length() - 2).toString(), true);
            if (!user.hasPrivateChannel()) {
                user.openPrivateChannel().complete();
            }
            ((UserImpl) user).getPrivateChannel().sendMessage(builder1.build()).queue();
            if (guild.getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(user.getAsMention() + " regarde tes messages privés").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(user.getAsMention() + " see your private messages").queue();
                }
            }
        } else if (c1.equals("all")) {
            String game = "";
            Object admin = "";
            String util = "";
            String music = "";
            String social = "";
            String stories = "";
            for (SimpleCommand command3 : CommandMap.getCommands()) {
                if (command3.getExecutorType() == command.ExecutorType.CONSOLE)
                    continue;
                if (command3.getTopic() == command.Topics.Game) {
                    game = game + "``" + command3.getName() + "``, ";
                    continue;
                }
                if (command3.getTopic() == command.Topics.Modo) {
                    admin = admin + "``" + command3.getName() + "``, ";
                    continue;
                }
                if (command3.getTopic() == command.Topics.Util) {
                    util = util + "``" + command3.getName() + "``, ";
                    continue;
                }
                if (command3.getTopic() == command.Topics.Music) {
                    music = music + "``" + command3.getName() + "``, ";
                    continue;
                }
                if (command3.getTopic() == command.Topics.Social) {
                    social = social + "``" + command3.getName() + "``, ";
                    continue;
                }
                if (command3.getTopic() != command.Topics.Stories)
                    continue;
                stories = stories + "``" + command3.getName() + "``, ";
            }
            EmbedBuilder builder13 = new EmbedBuilder();
            builder13.setTitle("\u2139 | Commands | \u2139");
            builder13.setColor(color.couleurAleatoire(user));
            builder13.setAuthor(user.getName());
            builder13.setFooter(guild.getName(), guild.getIconUrl());
            builder13.setTimestamp(Instant.now());
            builder13.addField("\ud83c\udfae **Game** \ud83c\udfae",
                    new StringBuilder(game).deleteCharAt(game.length() - 2).toString(), true);
            builder13.addField("\ud83d\udc6e **Moderation** \ud83d\udc6e",
                    new StringBuilder((String) admin).deleteCharAt(((String) admin).length() - 2).toString(), false);
            builder13.addField("\ud83c\udfb5 **Music** \ud83c\udfb5",
                    new StringBuilder(music).deleteCharAt(music.length() - 2).toString(), false);
            builder13.addField("\u26a1  **Util** \u26a1",
                    new StringBuilder(util).deleteCharAt(util.length() - 2).toString(), false);
            builder13.addField("\ud83e\udd84 **Stories** \ud83e\udd84",
                    new StringBuilder(stories).deleteCharAt(stories.length() - 2).toString(), true);
            builder13.addField("\ud83d\udd25 **Social** \ud83d\udd25",
                    new StringBuilder(social).deleteCharAt(social.length() - 2).toString(), true);
            if (!user.hasPrivateChannel()) {
                user.openPrivateChannel().complete();
            }
            ((UserImpl) user).getPrivateChannel().sendMessage(builder13.build()).queue();
            if (guild.getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                if (lang == command.Language.fr) {
                    channel.sendMessage(user.getAsMention() + " regarde tes messages privés").queue();
                }
                if (lang == command.Language.en) {
                    channel.sendMessage(user.getAsMention() + " see your private messages").queue();
                }
            }
        } else {
            for (SimpleCommand command4 : this.commandMap.getCommands2()) {
                if (!command4.getName().equals(c1))
                    continue;
                String name = command4.getName();
                String abbrev = command4.getAbbrev();
                if (abbrev.equals("donotusethiscommand")) {
                    abbrev = "Aucune abbreviation";
                }
                String descfr = command4.getDescfr();
                String descen = command4.getDescen();
                String use = command4.getUse();
                EmbedBuilder builder = new EmbedBuilder();
                builder.setColor(color.couleurAleatoire(user));
                builder.setAuthor(user.getName());
                builder.setThumbnail(
                        "https://vignette.wikia.nocookie.net/mixedmartialarts/images/8/89/Help_logo.png/revision/latest?cb=20100314171646");
                builder.setFooter(guild.getName(), guild.getIconUrl());
                builder.addField("**Name**", name, true);
                builder.addField("**Categorie**", command4.getTopic().toString(), true);
                builder.addField("**Alias**", abbrev, true);
                if (lang == command.Language.fr) {
                    builder.addField("**Description**", descfr, true);
                }
                if (lang == command.Language.en) {
                    builder.addField("**Description**", descen, true);
                }
                if (lang == command.Language.fr) {
                    builder.addField("**Usage**", use, true);
                }
                if (lang == command.Language.en) {
                    builder.addField("**Use**", use, true);
                }
                if (!user.hasPrivateChannel()) {
                    user.openPrivateChannel().complete();
                }
                ((UserImpl) user).getPrivateChannel().sendMessage(builder.build()).queue();
                if (guild.getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                    if (lang == command.Language.fr) {
                        channel.sendMessage(user.getAsMention() + " regarde tes messages privés")
                                .queue();
                    }
                    if (lang == command.Language.en) {
                        channel.sendMessage(user.getAsMention() + " see your private messages").queue();
                    }
                }
                return;
            }
            if (lang == command.Language.fr) {
                channel.sendMessage("La commande recherché ne semble pas exister").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("This command doesn't exist.").queue();
            }
        }
    }

    @command(name = "hh", type = command.ExecutorType.USER, descfr = "affiche la liste des commandes")
    private void hh(User user, MessageChannel channel, Guild guild, String[] args, command.Language lang) {
        EmbedBuilder builder1;
        StringBuilder buider = new StringBuilder();
        String[] arrstring = args;
        int n = arrstring.length;
        for (int i = 0; i < n; ++i) {
            String str = arrstring[i];
            if (str.equals(args[0]))
                continue;
            if (buider.length() > 0) {
                buider.append(" ");
            }
            buider.append(str);
        }
        StringBuilder buder = new StringBuilder();
        for (String str : args) {
            if (buder.length() > 0) {
                buder.append(" ");
            }
            buder.append(str);
        }
        String c1 = buder.toString();
        String c2 = buider.toString();
        if (c1.equals("")) {
            EmbedBuilder builder12 = new EmbedBuilder();
            builder12.setTitle("Commands");
            builder12.addField("\ud83d\udc51 Admin help", "**=hh admin**", true);
            builder12.addField(":video_game:Game help", "**=hh game**", true);
            builder12.addField(":musical_note: Music help", "**=hh music**", true);
            builder12.addField(":fire: Social help", "**=hh social**", true);
            builder12.addField(":zap: Util help", "**=hh util**", true);
            builder12.addField(":unicorn: Stories help", "**=hh stories**", true);
            builder12.addField("\ud83c\udf0e General help", "**=hh all**", true);
            channel.sendMessage(builder12.build()).complete();
        } else if (c1.equals("admin")) {
            String admin = "";
            for (SimpleCommand command2 : CommandMap.getCommands()) {
                if (command2.getExecutorType() == command.ExecutorType.CONSOLE
                        || command2.getTopic() != command.Topics.Modo)
                    continue;
                admin = admin + "``" + command2.getName() + "``, ";
            }
            builder1 = new EmbedBuilder();
            builder1.setTitle("\u2139 | Commands | \u2139");
            builder1.setColor(color.couleurAleatoire(user));
            builder1.setAuthor(user.getName());
            builder1.setFooter(guild.getName(), guild.getIconUrl());
            builder1.setTimestamp(Instant.now());
            builder1.addField("\ud83d\udc6e **Moderation** \ud83d\udc6e",
                    new StringBuilder(admin).deleteCharAt(admin.length() - 2).toString(), false);
            channel.sendMessage(builder1.build()).queue();
        } else if (c1.equals("util")) {
            String util = "";
            for (SimpleCommand command2 : CommandMap.getCommands()) {
                if (command2.getExecutorType() == command.ExecutorType.CONSOLE
                        || command2.getTopic() != command.Topics.Util)
                    continue;
                util = util + "``" + command2.getName() + "``, ";
            }
            builder1 = new EmbedBuilder();
            builder1.setTitle("\u2139 | Commands | \u2139");
            builder1.setColor(color.couleurAleatoire(user));
            builder1.setAuthor(user.getName());
            builder1.setFooter(guild.getName(), guild.getIconUrl());
            builder1.setTimestamp(Instant.now());
            builder1.addField("\u26a1  **Utilitaire** \u26a1",
                    new StringBuilder(util).deleteCharAt(util.length() - 2).toString(), false);
            channel.sendMessage(builder1.build()).queue();
        } else if (c1.equals("music")) {
            String music = "";
            for (SimpleCommand command2 : CommandMap.getCommands()) {
                if (command2.getExecutorType() == command.ExecutorType.CONSOLE
                        || command2.getTopic() != command.Topics.Music)
                    continue;
                music = music + "``" + command2.getName() + "``, ";
            }
            builder1 = new EmbedBuilder();
            builder1.setTitle("\u2139 | Commands | \u2139");
            builder1.setColor(color.couleurAleatoire(user));
            builder1.setAuthor(user.getName());
            builder1.setFooter(guild.getName(), guild.getIconUrl());
            builder1.setTimestamp(Instant.now());
            builder1.addField("\ud83c\udfb5 **Musique** \ud83c\udfb5",
                    new StringBuilder(music).deleteCharAt(music.length() - 2).toString(), false);
            channel.sendMessage(builder1.build()).queue();
        } else if (c1.equals("game")) {
            String game = "";
            for (SimpleCommand command2 : CommandMap.getCommands()) {
                if (command2.getExecutorType() == command.ExecutorType.CONSOLE || game.contains(command2.getName())
                        || command2.getTopic() != command.Topics.Game)
                    continue;
                game = game + "``" + command2.getName() + "``, ";
            }
            builder1 = new EmbedBuilder();
            builder1.setTitle("\u2139 | Commands | \u2139");
            builder1.setColor(color.couleurAleatoire(user));
            builder1.setAuthor(user.getName());
            builder1.setFooter(guild.getName(), guild.getIconUrl());
            builder1.setTimestamp(Instant.now());
            builder1.addField("\ud83c\udfae **Jeu** \ud83c\udfae",
                    new StringBuilder(game).deleteCharAt(game.length() - 2).toString(), true);
            channel.sendMessage(builder1.build()).queue();
        } else if (c1.equals("social")) {
            String social = "";
            for (SimpleCommand command2 : CommandMap.getCommands()) {
                if (command2.getExecutorType() == command.ExecutorType.CONSOLE
                        || command2.getTopic() != command.Topics.Social)
                    continue;
                social = social + "``" + command2.getName() + "``, ";
            }
            builder1 = new EmbedBuilder();
            builder1.setTitle("\u2139 | Commands | \u2139");
            builder1.setColor(color.couleurAleatoire(user));
            builder1.setAuthor(user.getName());
            builder1.setFooter(guild.getName(), guild.getIconUrl());
            builder1.setTimestamp(Instant.now());
            builder1.addField("\ud83d\udd25 **Social** \ud83d\udd25",
                    new StringBuilder(social).deleteCharAt(social.length() - 2).toString(), true);
            channel.sendMessage(builder1.build()).queue();
        } else if (c1.equals("stories")) {
            String stories = "";
            for (SimpleCommand command2 : CommandMap.getCommands()) {
                if (command2.getExecutorType() == command.ExecutorType.CONSOLE
                        || command2.getTopic() != command.Topics.Stories)
                    continue;
                stories = stories + "``" + command2.getName() + "``, ";
            }
            builder1 = new EmbedBuilder();
            builder1.setTitle("\u2139 | Commands | \u2139");
            builder1.setColor(color.couleurAleatoire(user));
            builder1.setAuthor(user.getName());
            builder1.setFooter(guild.getName(), guild.getIconUrl());
            builder1.setTimestamp(Instant.now());
            builder1.addField("\ud83e\udd84 **Stories** \ud83e\udd84",
                    new StringBuilder(stories).deleteCharAt(stories.length() - 2).toString(), true);
            channel.sendMessage(builder1.build()).queue();
        } else if (c1.equals("all")) {
            String game = "";
            Object admin = "";
            String util = "";
            String music = "";
            String social = "";
            String stories = "";
            for (SimpleCommand command3 : CommandMap.getCommands()) {
                if (command3.getExecutorType() == command.ExecutorType.CONSOLE)
                    continue;
                if (command3.getTopic() == command.Topics.Game) {
                    game = game + "``" + command3.getName() + "``, ";
                    continue;
                }
                if (command3.getTopic() == command.Topics.Modo) {
                    admin = admin + "``" + command3.getName() + "``, ";
                    continue;
                }
                if (command3.getTopic() == command.Topics.Util) {
                    util = util + "``" + command3.getName() + "``, ";
                    continue;
                }
                if (command3.getTopic() == command.Topics.Music) {
                    music = music + "``" + command3.getName() + "``, ";
                    continue;
                }
                if (command3.getTopic() == command.Topics.Social) {
                    social = social + "``" + command3.getName() + "``, ";
                    continue;
                }
                if (command3.getTopic() != command.Topics.Stories)
                    continue;
                stories = stories + "``" + command3.getName() + "``, ";
            }
            EmbedBuilder builder13 = new EmbedBuilder();
            builder13.setTitle("\u2139 | Commands | \u2139");
            builder13.setColor(color.couleurAleatoire(user));
            builder13.setAuthor(user.getName());
            builder13.setFooter(guild.getName(), guild.getIconUrl());
            builder13.setTimestamp(Instant.now());
            builder13.addField("\ud83c\udfae ***Game** \ud83c\udfae",
                    new StringBuilder(game).deleteCharAt(game.length() - 2).toString(), true);
            builder13.addField("\ud83d\udc6e **Moderation** \ud83d\udc6e",
                    new StringBuilder((String) admin).deleteCharAt(((String) admin).length() - 2).toString(), false);
            builder13.addField("\ud83c\udfb5 **Music** \ud83c\udfb5",
                    new StringBuilder(music).deleteCharAt(music.length() - 2).toString(), false);
            builder13.addField("\u26a1  **Util** \u26a1",
                    new StringBuilder(util).deleteCharAt(util.length() - 2).toString(), false);
            builder13.addField("\ud83e\udd84 **Stories** \ud83e\udd84",
                    new StringBuilder(stories).deleteCharAt(stories.length() - 2).toString(), true);
            builder13.addField("\ud83d\udd25 **Social** \ud83d\udd25",
                    new StringBuilder(social).deleteCharAt(social.length() - 2).toString(), true);
            channel.sendMessage(builder13.build()).queue();
        } else {
            for (SimpleCommand command4 : CommandMap.getCommands()) {
                if (!command4.getName().equals(c1))
                    continue;
                String name = command4.getName();
                String abbrev = command4.getAbbrev();
                if (abbrev.equals("donotusethiscommand")) {
                    abbrev = "Aucune abbreviation";
                }
                String descfr = command4.getDescfr();
                String descen = command4.getDescen();
                String use = command4.getUse();
                EmbedBuilder builder = new EmbedBuilder();
                builder.setColor(color.couleurAleatoire(user));
                builder.setAuthor(user.getName());
                builder.setThumbnail(
                        "https://vignette.wikia.nocookie.net/mixedmartialarts/images/8/89/Help_logo.png/revision/latest?cb=20100314171646");
                builder.setFooter(guild.getName(), guild.getIconUrl());
                builder.addField("**Name**", name, true);
                builder.addField("**Categorie**", command4.getTopic().toString(), true);
                builder.addField("**Alias**", abbrev, true);
                if (lang == command.Language.fr) {
                    builder.addField("**Description**", descfr, true);
                }
                if (lang == command.Language.en) {
                    builder.addField("**Description**", descen, true);
                }
                if (lang == command.Language.fr) {
                    builder.addField("**Usage**", use, true);
                }
                if (lang == command.Language.en) {
                    builder.addField("**Use**", use, true);
                }
                channel.sendMessage(builder.build()).queue();
                return;
            }
            if (lang == command.Language.fr) {
                channel.sendMessage("La commande recherché ne semble pas exister").queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage("This commands doesn't exist").queue();
            }
        }
    }
}
