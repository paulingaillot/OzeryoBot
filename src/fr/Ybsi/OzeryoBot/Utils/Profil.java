/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

import fr.Ybsi.OzeryoBot.Commands.command;
import java.util.ArrayList;
import java.util.HashMap;

public class Profil {
    private String id = "";
    private String name = "";
    private String IconURL = "";
    private int tuto = 0;
    private command.Language language = command.Language.en;
    private boolean register = false;
    private int xp = 0;
    private int idh = 0;
    private int trophy = 0;
    private int cf = 0;
    private HashMap<Long, ArrayList<String>> Attack = new HashMap();
    private HashMap<Long, ArrayList<String>> give = new HashMap();
    private HashMap<Long, ArrayList<String>> RMD = new HashMap();
    private long lastRep = 0L;
    private int rep = 0;
    private long LastTrain = 0L;
    private long Train = 0L;
    private String Description = "Aucune Description";
    private int vote = 0;
    private HashMap<String, Long> achievement = new HashMap();
    private int ap = 0;
    private long lastDaily = 0L;
    private long lastHourly = 0L;
    private long money = 0L;
    private long habitants = 0L;
    private HashMap<String, Integer> res = new HashMap();
    private HashMap<String, Integer> building = new HashMap();
    private long soldiers = 0L;
    private String country = "";
    private String home = "0_0";
    private int mana = 0;
    private long lastMana = 0L;
    private HashMap<String, ArrayList<String>> pet = new HashMap();
    private HashMap<String, ArrayList<String>> houses = new HashMap();
    private long lastPerte = 0L;
    private int tokens = 0;
    private boolean game = false;
    private HashMap<String, ArrayList<String>> heroe = new HashMap();
    private String activeHeroe = "";
    private long lastPV = 0L;
    private int lootbox = 0;
    private boolean lootboxPremium = false;
    private HashMap<String, ArrayList<Integer>> weapons = new HashMap();
    private HashMap<String, ArrayList<Integer>> armor = new HashMap();
    private boolean mail = true;
    private ArrayList<ArrayList<String>> listMail = new ArrayList();
    private int filleuls = 0;
    private boolean parrain = false;
    private int ozPassXp = 0;
    private int pallier = 0;
    private int bonus = 0;
    private int useless = 0;
    private long money_r\u00e9colt\u00e9 = 0L;
    private int Conquerant = 0;
    private int Defenseur = 0;
    private int espion = 0;
    private int Parieur = 0;
    private int Daily_r\u00e9colt\u00e9 = 0;
    private int hourly_r\u00e9colt\u00e9 = 0;
    private int jetons_r\u00e9colt\u00e9 = 0;
    private int looser = 0;
    private int Investisseur = 0;
    private int Avant_poste = 0;
    private int Pigeon = 0;
    private int Scientifique = 0;
    private int Melting = 0;
    private int Collector = 0;
    private HashMap<String, Integer> competence = new HashMap();
    private long premium = 1L;
    private int topIdh = 0;
    private int topTrophy = 0;
    private int topCf = 0;
    private int toprRep = 0;
    private int topAp = 0;
    private int topXp = 0;
    private long inscription = System.currentTimeMillis();
    private long temps_de_jeu = 0L;
    private int commands = 0;
    private long lastCommand = 0L;
    int topLevel = 0;

    public Profil() {
    }

    public Profil(String id) {
        this.id = id;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getXp() {
        return this.xp;
    }

    public int getIdh() {
        return this.idh;
    }

    public void setIdh(int idh) {
        this.idh = idh;
    }

    public int getTrophy() {
        return this.trophy;
    }

    public void setTrophy(int trophy) {
        this.trophy = trophy;
    }

    public int getCf() {
        return this.cf;
    }

    public void setCf(int cf) {
        this.cf = cf;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<Long, ArrayList<String>> getGive() {
        return this.give;
    }

    public void setGive(HashMap<Long, ArrayList<String>> give) {
        this.give = give;
    }

    public HashMap<Long, ArrayList<String>> getRMD() {
        return this.RMD;
    }

    public void setRMD(HashMap<Long, ArrayList<String>> rMD) {
        this.RMD = rMD;
    }

    public HashMap<Long, ArrayList<String>> getAttack() {
        return this.Attack;
    }

    public void setAttack(HashMap<Long, ArrayList<String>> attack) {
        this.Attack = attack;
    }

    public int getRep() {
        return this.rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }

    public long getLastTrain() {
        return this.LastTrain;
    }

    public void setLastTrain(long lastTrain) {
        this.LastTrain = lastTrain;
    }

    public long getTrain() {
        return this.Train;
    }

    public void setTrain(long train) {
        this.Train = train;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public int getVote() {
        return this.vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public HashMap<String, Long> getAchievement() {
        return this.achievement;
    }

    public void setAchievement(HashMap<String, Long> achievement) {
        this.achievement = achievement;
    }

    public int getAp() {
        return this.ap;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public long getLastRep() {
        return this.lastRep;
    }

    public void setLastRep(long lastRep) {
        this.lastRep = lastRep;
    }

    public long getLastDaily() {
        return this.lastDaily;
    }

    public void setLastDaily(long lastDaily) {
        this.lastDaily = lastDaily;
    }

    public long getLastHourly() {
        return this.lastHourly;
    }

    public void setLastHourly(long lastHourly) {
        this.lastHourly = lastHourly;
    }

    public long getMoney() {
        return this.money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public long getHabitants() {
        return this.habitants;
    }

    public void setHabitants(long habitants2) {
        this.habitants = habitants2;
    }

    public HashMap<String, Integer> getRes() {
        return this.res;
    }

    public void setRes(HashMap<String, Integer> res) {
        this.res = res;
    }

    public HashMap<String, Integer> getBuilding() {
        return this.building;
    }

    public void setBuilding(HashMap<String, Integer> building) {
        this.building = building;
    }

    public long getSoldiers() {
        return this.soldiers;
    }

    public void setSoldiers(long soldiers) {
        this.soldiers = soldiers;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHome() {
        return this.home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public int getMana() {
        return this.mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public long getLastMana() {
        return this.lastMana;
    }

    public void setLastMana(long lastMana) {
        this.lastMana = lastMana;
    }

    public HashMap<String, ArrayList<String>> getPet() {
        return this.pet;
    }

    public void setPet(HashMap<String, ArrayList<String>> pet) {
        this.pet = pet;
    }

    public HashMap<String, ArrayList<String>> getHouses() {
        return this.houses;
    }

    public void setHouses(HashMap<String, ArrayList<String>> houses) {
        this.houses = houses;
    }

    public long getLastPerte() {
        return this.lastPerte;
    }

    public void setLastPerte(long lastPerte) {
        this.lastPerte = lastPerte;
    }

    public int getTokens() {
        return this.tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public boolean isGame() {
        return this.game;
    }

    public void setGame(boolean game) {
        this.game = game;
    }

    public HashMap<String, ArrayList<String>> getHeroe() {
        return this.heroe;
    }

    public void setHeroe(HashMap<String, ArrayList<String>> heroe) {
        this.heroe = heroe;
    }

    public int getLootbox() {
        return this.lootbox;
    }

    public void setLootbox(int lootbox) {
        this.lootbox = lootbox;
    }

    public boolean isLootboxPremium() {
        return this.lootboxPremium;
    }

    public void setLootboxPremium(boolean lootboxPremium) {
        this.lootboxPremium = lootboxPremium;
    }

    public String getActiveHeroe() {
        return this.activeHeroe;
    }

    public void setActiveHeroe(String activeHeroe) {
        this.activeHeroe = activeHeroe;
    }

    public long getLastPV() {
        return this.lastPV;
    }

    public void setLastPV(long lastPV) {
        this.lastPV = lastPV;
    }

    public HashMap<String, ArrayList<Integer>> getWeapons() {
        return this.weapons;
    }

    public void setWeapons(HashMap<String, ArrayList<Integer>> weapons) {
        this.weapons = weapons;
    }

    public HashMap<String, ArrayList<Integer>> getArmor() {
        return this.armor;
    }

    public void setArmor(HashMap<String, ArrayList<Integer>> armor) {
        this.armor = armor;
    }

    public boolean isMail() {
        return this.mail;
    }

    public void setMail(boolean mail2) {
        this.mail = mail2;
    }

    public ArrayList<ArrayList<String>> getListMail() {
        return this.listMail;
    }

    public void setListMail(ArrayList<ArrayList<String>> listMail) {
        this.listMail = listMail;
    }

    public int getOzPassXp() {
        return this.ozPassXp;
    }

    public void setOzPassXp(int ozPassXp) {
        this.ozPassXp = ozPassXp;
    }

    public int getPallier() {
        return this.pallier;
    }

    public void setPallier(int pallier) {
        this.pallier = pallier;
    }

    public int getBonus() {
        return this.bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getUseless() {
        return this.useless;
    }

    public void setUseless(int useless) {
        this.useless = useless;
    }

    public long getMoney_r\u00e9colt\u00e9() {
        return this.money_r\u00e9colt\u00e9;
    }

    public void setMoney_r\u00e9colt\u00e9(long money_r\u00e9colt\u00e9) {
        this.money_r\u00e9colt\u00e9 = money_r\u00e9colt\u00e9;
    }

    public int getConquerant() {
        return this.Conquerant;
    }

    public void setConquerant(int conquerant) {
        this.Conquerant = conquerant;
    }

    public int getDefenseur() {
        return this.Defenseur;
    }

    public void setDefenseur(int defenseur) {
        this.Defenseur = defenseur;
    }

    public int getEspion() {
        return this.espion;
    }

    public void setEspion(int espion) {
        this.espion = espion;
    }

    public int getParieur() {
        return this.Parieur;
    }

    public void setParieur(int parieur) {
        this.Parieur = parieur;
    }

    public boolean isParrain() {
        return this.parrain;
    }

    public void setParrain(boolean parrain) {
        this.parrain = parrain;
    }

    public int getFilleuls() {
        return this.filleuls;
    }

    public void setFilleuls(int filleuls) {
        this.filleuls = filleuls;
    }

    public int getDaily_r\u00e9colt\u00e9() {
        return this.Daily_r\u00e9colt\u00e9;
    }

    public void setDaily_r\u00e9colt\u00e9(int daily_r\u00e9colt\u00e9) {
        this.Daily_r\u00e9colt\u00e9 = daily_r\u00e9colt\u00e9;
    }

    public int getHourly_r\u00e9colt\u00e9() {
        return this.hourly_r\u00e9colt\u00e9;
    }

    public void setHourly_r\u00e9colt\u00e9(int hourly_r\u00e9colt\u00e9) {
        this.hourly_r\u00e9colt\u00e9 = hourly_r\u00e9colt\u00e9;
    }

    public int getJetons_r\u00e9colt\u00e9() {
        return this.jetons_r\u00e9colt\u00e9;
    }

    public void setJetons_r\u00e9colt\u00e9(int jetons_r\u00e9colt\u00e9) {
        this.jetons_r\u00e9colt\u00e9 = jetons_r\u00e9colt\u00e9;
    }

    public int getLooser() {
        return this.looser;
    }

    public void setLooser(int looser) {
        this.looser = looser;
    }

    public int getInvestisseur() {
        return this.Investisseur;
    }

    public void setInvestisseur(int investisseur) {
        this.Investisseur = investisseur;
    }

    public int getAvant_poste() {
        return this.Avant_poste;
    }

    public void setAvant_poste(int avant_poste) {
        this.Avant_poste = avant_poste;
    }

    public int getPigeon() {
        return this.Pigeon;
    }

    public void setPigeon(int pigeon) {
        this.Pigeon = pigeon;
    }

    public int getScientifique() {
        return this.Scientifique;
    }

    public void setScientifique(int scientifique) {
        this.Scientifique = scientifique;
    }

    public int getMelting() {
        return this.Melting;
    }

    public void setMelting(int melting) {
        this.Melting = melting;
    }

    public int getCollector() {
        return this.Collector;
    }

    public void setCollector(int collector) {
        this.Collector = collector;
    }

    public boolean isRegister() {
        return this.register;
    }

    public void setRegister(boolean register) {
        this.register = register;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTuto() {
        return this.tuto;
    }

    public void setTuto(int tuto) {
        this.tuto = tuto;
    }

    public HashMap<String, Integer> getCompetence() {
        return this.competence;
    }

    public void setCompetence(HashMap<String, Integer> competence) {
        this.competence = competence;
    }

    public command.Language getLanguage() {
        return this.language;
    }

    public void setLanguage(command.Language language) {
        this.language = language;
    }

    public long getPremium() {
        return this.premium;
    }

    public void setPremium(long premium) {
        this.premium = premium;
    }

    public String getIconURL() {
        return this.IconURL;
    }

    public void setIconURL(String iconURL) {
        this.IconURL = iconURL;
    }

    public int getTopLevel() {
        return this.topLevel;
    }

    public void setTopLevel(int topLevel) {
        this.topLevel = topLevel;
    }

    public int getTopIdh() {
        return this.topIdh;
    }

    public void setTopIdh(int topIdh) {
        this.topIdh = topIdh;
    }

    public int getTopTrophy() {
        return this.topTrophy;
    }

    public void setTopTrophy(int topTrophy) {
        this.topTrophy = topTrophy;
    }

    public int getTopCf() {
        return this.topCf;
    }

    public void setTopCf(int topCf) {
        this.topCf = topCf;
    }

    public int getToprRep() {
        return this.toprRep;
    }

    public void setToprRep(int toprRep) {
        this.toprRep = toprRep;
    }

    public int getTopAp() {
        return this.topAp;
    }

    public void setTopAp(int topAp) {
        this.topAp = topAp;
    }

    public int getTopXp() {
        return this.topXp;
    }

    public void setTopXp(int topXp) {
        this.topXp = topXp;
    }

    public long getInscription() {
        return this.inscription;
    }

    public void setInscription(long inscription) {
        this.inscription = inscription;
    }

    public long getTemps_de_jeu() {
        return this.temps_de_jeu;
    }

    public void setTemps_de_jeu(long temps_de_jeu) {
        this.temps_de_jeu = temps_de_jeu;
    }

    public int getCommands() {
        return this.commands;
    }

    public void setCommands(int commands) {
        this.commands = commands;
    }

    public long getLastCommand() {
        return this.lastCommand;
    }

    public void setLastCommand(long lastCommand) {
        this.lastCommand = lastCommand;
    }
}

