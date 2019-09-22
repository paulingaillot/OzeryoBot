/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot;

import fr.Ybsi.OzeryoBot.Commands.CommandMap;
import fr.Ybsi.OzeryoBot.Event.BadWords;
import fr.Ybsi.OzeryoBot.Event.BotListener;
import fr.Ybsi.OzeryoBot.Event.GlobalChat;
import fr.Ybsi.OzeryoBot.Event.Log;
import fr.Ybsi.OzeryoBot.Event.count;
import fr.Ybsi.OzeryoBot.Level.EXP;
import fr.Ybsi.OzeryoBot.Level.levelup;
import fr.Ybsi.OzeryoBot.Utils.GuildProfilData;
import fr.Ybsi.OzeryoBot.Utils.HypixelData;
import fr.Ybsi.OzeryoBot.Utils.LevelProfilData;
import fr.Ybsi.OzeryoBot.Utils.ProfilData;
import fr.Ybsi.OzeryoBot.Utils.Scheduler;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.CompletionStage;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import org.discordbots.api.client.DiscordBotListAPI;

public class DiscordBot implements Runnable {
    private static JDA jda;
    private final JDABuilder ShardBuilder;
    private final CommandMap commandMap = new CommandMap(this);
    private final Scanner scanner = new Scanner(System.in);
    private static ProfilData Profildata;
    private static GuildProfilData Guilddata;
    private static LevelProfilData Leveldata;
    private static HypixelData Hypixeldata;
    final int shard = 1;
    private boolean running;

    static {
        Profildata = ProfilData.loadData(new File("/home/DiscordBot/Rasberry/données/bot/profilData.json"));
        Guilddata = GuildProfilData.loadData(new File("/home/DiscordBot/Rasberry/données/bot/guildData.json"));
        Leveldata = LevelProfilData.loadData(new File("/home/DiscordBot/Rasberry/données/bot/levelData.json"));
        Hypixeldata = HypixelData.loadData(new File("/home/DiscordBot/Rasberry/données/bot/hypixelData.json"));
    }

    public DiscordBot() throws LoginException, IllegalArgumentException, RateLimitedException {
        String DiscordKey = TextFileWriter.read("/home/DiscordBot/Rasberry/key/DiscordKey.txt");
        String DiscordBotKey = TextFileWriter.read("/home/DiscordBot/Rasberry/key/DiscordBotKey.txt");
        this.ShardBuilder = new JDABuilder(AccountType.BOT);
        this.ShardBuilder.addEventListener(new BotListener(this.commandMap), new BadWords(), new EXP(), new levelup(),
                new Log(), new GlobalChat(), new count());
        for (int i = 0; i < 1; ++i) {
            jda = this.ShardBuilder.setToken(DiscordKey).useSharding(i, 1)
                    .setGame(Game.playing("Probl\u00e8mes de connexion ?")).build();
        }
        String secondes = new SimpleDateFormat("ss", Locale.FRANCE).format(new Date());
        String minutes = new SimpleDateFormat("mm", Locale.FRANCE).format(new Date());
        String heures = new SimpleDateFormat("HH", Locale.FRANCE).format(new Date());
        String jours = new SimpleDateFormat("dd", Locale.FRANCE).format(new Date());
        String mois = new SimpleDateFormat("MM", Locale.FRANCE).format(new Date());
        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/");
        TextFileWriter.folder("/home/DiscordBot/Rasberry/données/bot/");
        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/secondes.txt", secondes, 1);
        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/minutes.txt", minutes, 1);
        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/heures.txt", heures, 1);
        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/jours.txt", jours, 1);
        TextFileWriter.write("/home/DiscordBot/Rasberry/données/bot/mois.txt", mois, 1);
        System.out.println(
                "----------------------------------------------------------------\n\n           Bot connected              \n          l'uptime commmence le "
                        + jours + "/" + mois + " \ufffd " + heures + ":" + minutes + ":" + secondes
                        + "\n\n----------------------------------------------------------------\n ");
        DiscordBotListAPI api = new DiscordBotListAPI.Builder().token(DiscordBotKey).botId("399115724926484490")
                .build();
        int serverCount = jda.getGuilds().size();
        api.setStats(serverCount);
        Scheduler.Bid(jda);
        Scheduler.OzeryoRoleUpdate(jda);
        Scheduler.GameUpdate(jda);
        Scheduler.Guild_Bonus(jda);
        Scheduler.Quests(jda);
        Scheduler.Shop();
        Scheduler.Ressources(jda);
        Scheduler.Dungeon();
        Scheduler.Map(jda, Profildata);
        Scheduler.Give(jda);
        Scheduler.Save(jda);
        Scheduler.Uptime(jda);
        Scheduler.hypixelStats(jda);
        Scheduler.WebMap(jda);
        Scheduler.WebLeaderBoard(jda);
        Scheduler.Guildtest(jda);
        Scheduler.Pub(jda);
        URL url = null;
        HttpURLConnection con = null;
        try {
            String key = TextFileWriter.read("/home/DiscordBot/Rasberry/key/DiscordBotFrKey.txt");
            url = new URL("https://discordbots.fr/api/v1/bot/399115724926484490?api_key=" + key + "&shard=" + 2
                    + "&serveurs=" + jda.getGuilds().size());
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JDA getjda() {
        return jda;
    }

    public void setrunning(boolean running) {
        this.running = running;
    }

    public JDABuilder getjdaBuilder() {
        return this.ShardBuilder;
    }

    @Override
    public void run() {
        this.running = true;
        while (this.running) {
            if (!this.scanner.hasNextLine())
                continue;
            this.commandMap.commandConsole(this.scanner.nextLine());
        }
        String URL2 = "/home/DiscordBot/Rasberry/données/bot";
        ProfilData data = DiscordBot.getData();
        System.out.println("Enregistrement des données en cours ...");
        data.saveData(URL2);
        System.out.println("Données enregistrées");
        this.scanner.close();
        System.out.println("bot stopped");
        jda.shutdown();
        System.exit(0);
    }

    public static void main(String[] args) {
        try {
            DiscordBot DiscordBot2 = new DiscordBot();
            new Thread((Runnable) DiscordBot2, "Ozeryo").start();
        } catch (IllegalArgumentException | LoginException | RateLimitedException e) {
            e.printStackTrace();
            String txtDate = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss", Locale.FRANCE).format(new Date());
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/log.txt",
                    String.valueOf(txtDate) + " ERROR " + e.getMessage(), 1);
        }
    }

    public static ProfilData getData() {
        return Profildata;
    }

    public static GuildProfilData getGuilddata() {
        return Guilddata;
    }

    public static LevelProfilData getLeveldata() {
        return Leveldata;
    }

    public static HypixelData getHypixeldata() {
        return Hypixeldata;
    }
}
