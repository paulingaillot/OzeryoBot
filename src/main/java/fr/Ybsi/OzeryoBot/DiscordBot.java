/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot;

import fr.Ybsi.OzeryoBot.Commands.CommandMap;
import fr.Ybsi.OzeryoBot.Event.*;
import fr.Ybsi.OzeryoBot.Level.EXP;
import fr.Ybsi.OzeryoBot.Level.levelup;
import fr.Ybsi.OzeryoBot.Utils.*;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import org.discordbots.api.client.DiscordBotListAPI;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


public class DiscordBot implements Runnable {
    private static JDA jda;
    private static ProfilData Profildata;
    private static GuildProfilData Guilddata;
    private static LevelProfilData Leveldata;
    private static HypixelData Hypixeldata;

    static {
        Profildata = ProfilData.loadData(new File("/home/DiscordBot/Rasberry/données/bot/profilData.json"));
        Guilddata = GuildProfilData.loadData(new File("/home/DiscordBot/Rasberry/données/bot/guildData.json"));
        Leveldata = LevelProfilData.loadData(new File("/home/DiscordBot/Rasberry/données/bot/levelData.json"));
        Hypixeldata = HypixelData.loadData(new File("/home/DiscordBot/Rasberry/données/bot/hypixelData.json"));
    }

    final int shard = 1;
    private final CommandMap commandMap = new CommandMap(this);
    private final Scanner scanner = new Scanner(System.in);
    private boolean running;

    public DiscordBot() throws LoginException, IllegalArgumentException, RateLimitedException, InterruptedException {

        if(Profildata == null) Profildata = new ProfilData();
        if(Guilddata == null) Guilddata = new GuildProfilData();
        if(Leveldata == null) Leveldata = new LevelProfilData();
        if(Hypixeldata == null) Hypixeldata = new HypixelData();

        String DiscordKey = "NzY4MTIyNjk2MjEwMTg2Mjky.X474AQ.JTjNmy-AavaitxZxrtOXD412Y0w";
        String DiscordBotKey = TextFileWriter.read("/home/DiscordBot/Rasberry/key/DiscordBotKey.txt");
        jda = JDABuilder.createDefault(DiscordKey).build();
        jda.awaitReady();
        jda.addEventListener(new BotListener(this.commandMap), new BadWords(), new EXP(), new levelup(),
                new Log(), new GlobalChat(), new count());

        /*DiscordBotListAPI api = new DiscordBotListAPI.Builder()
                .token("token")
                .botId("768122696210186292")
                .build();*/

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

        Scheduler.Save(jda);

        Scheduler.Bid(jda);
        Scheduler.GameUpdate(jda);
        Scheduler.Guild_Bonus(jda);
        Scheduler.Quests(jda);
        Scheduler.Shop();
        Scheduler.Ressources(jda);
        Scheduler.Dungeon();
        Scheduler.Map(jda);
        Scheduler.Give(jda);

        Scheduler.Uptime(jda);
        Scheduler.hypixelStats(jda);
        Scheduler.WebMap(jda);
        Scheduler.WebLeaderBoard(jda);
        Scheduler.Guildtest(jda);
        Scheduler.Pub(jda);
    }

    public static JDA getjda() {
        return jda;
    }

    public static void main(String[] args) throws Exception {
        try {
            DiscordBot DiscordBot2 = new DiscordBot();
            new Thread(DiscordBot2, "Ozeryo").start();
        } catch (IllegalArgumentException | LoginException | RateLimitedException e) {
            e.printStackTrace();
            String txtDate = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss", Locale.FRANCE).format(new Date());
            TextFileWriter.write("/home/DiscordBot/Rasberry/données/log.txt",
                    txtDate + " ERROR " + e.getMessage(), 1);
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

    public void setrunning(boolean running) {
        this.running = running;
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
}
