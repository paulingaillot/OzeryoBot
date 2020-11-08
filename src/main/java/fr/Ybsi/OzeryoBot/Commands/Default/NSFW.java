/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Commands.Default;

import fr.Ybsi.OzeryoBot.Commands.command;
import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import fr.Ybsi.OzeryoBot.Utils.color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;

public class NSFW {
    @command(name = "nsfw", type = command.ExecutorType.ALL)
    private void NSFW(User user, TextChannel channel1, JDA jda, Guild guild, String[] args, command.Language lang,
                      MessageChannel channel) {
        String c1;
        StringBuilder builder = new StringBuilder();
        for (String str : args) {
            if (str.equals(args[0]))
                continue;
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(str);
        }
        try {
            c1 = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            c1 = "hentai";
        }
        if (channel.getId().contentEquals(
                TextFileWriter.read("/home/DiscordBot/Rasberry/données/Guild/" + guild.getId() + "/NSFW.txt"))
                || channel1.isNSFW()) {
            URL url;
            int page;
            if (c1.equals("yaoi")) {
                int image = 1 + (int) (Math.random() * 50.0);
                image *= 2;
                page = 1 + (int) (Math.random() * 5.0);
                try {
                    url = new URL(
                            "https://rule34.xxx/index.php?page=dapi&s=post&q=index&tags=yaoi&limit=100&pid=" + page);
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    try {
                        Element personne;
                        DocumentBuilder buider = factory.newDocumentBuilder();
                        Document document = buider.parse(url.toString());
                        Element racine = document.getDocumentElement();
                        NodeList racineNoeuds = racine.getChildNodes();
                        try {
                            personne = (Element) racineNoeuds.item(image);
                        } catch (ClassCastException e) {
                            personne = (Element) racineNoeuds.item(1);
                        }
                        System.out.println(personne.getNodeName());
                        System.out.println("file_url : " + personne.getAttribute("file_url"));
                        String photo = personne.getAttribute("file_url");
                        EmbedBuilder builder1 = new EmbedBuilder();
                        builder1.setImage(photo);
                        builder1.setColor(color.couleurAleatoire(user));
                        builder1.setTimestamp(Instant.now());
                        builder1.setFooter(guild.getName(), guild.getIconUrl());
                        builder1.setAuthor(user.getName(), null, user.getAvatarUrl());
                        channel.sendMessage(builder1.build()).queue();
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }
            } else if (c1.equals("hentai") || c1.equals("")) {
                int image = 1 + (int) (Math.random() * 50.0);
                image *= 2;
                page = 1 + (int) (Math.random() * 5.0);
                try {
                    url = new URL("https://rule34.xxx/index.php?page=dapi&s=post&q=index&limit=100&pid=" + page);
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    try {
                        Element personne;
                        DocumentBuilder buider = factory.newDocumentBuilder();
                        Document document = buider.parse(url.toString());
                        Element racine = document.getDocumentElement();
                        NodeList racineNoeuds = racine.getChildNodes();
                        try {
                            personne = (Element) racineNoeuds.item(image);
                        } catch (ClassCastException e) {
                            personne = (Element) racineNoeuds.item(1);
                        }
                        System.out.println(personne.getNodeName());
                        System.out.println("file_url : " + personne.getAttribute("file_url"));
                        String photo = personne.getAttribute("file_url");
                        EmbedBuilder builder1 = new EmbedBuilder();
                        builder1.setImage(photo);
                        builder1.setColor(color.couleurAleatoire(user));
                        builder1.setTimestamp(Instant.now());
                        builder1.setFooter(String.valueOf(guild.getName()) + " | " + image, guild.getIconUrl());
                        builder1.setAuthor(user.getName(), null, user.getAvatarUrl());
                        channel.sendMessage(builder1.build()).queue();
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }
            } else if (c1.equals("yuri")) {
                int image = 1 + (int) (Math.random() * 50.0);
                image *= 2;
                page = 1 + (int) (Math.random() * 5.0);
                try {
                    url = new URL(
                            "https://rule34.xxx/index.php?page=dapi&s=post&q=index&tags=yuri&limit=100&pid=" + page);
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    try {
                        Element personne;
                        DocumentBuilder buider = factory.newDocumentBuilder();
                        Document document = buider.parse(url.toString());
                        Element racine = document.getDocumentElement();
                        NodeList racineNoeuds = racine.getChildNodes();
                        try {
                            personne = (Element) racineNoeuds.item(image);
                        } catch (ClassCastException e) {
                            personne = (Element) racineNoeuds.item(1);
                        }
                        System.out.println(personne.getNodeName());
                        System.out.println("file_url : " + personne.getAttribute("file_url"));
                        String photo = personne.getAttribute("file_url");
                        EmbedBuilder builder1 = new EmbedBuilder();
                        builder1.setImage(photo);
                        builder1.setColor(color.couleurAleatoire(user));
                        builder1.setTimestamp(Instant.now());
                        builder1.setFooter(guild.getName(), guild.getIconUrl());
                        builder1.setAuthor(user.getName(), null, user.getAvatarUrl());
                        channel.sendMessage(builder1.build()).queue();
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }
            } else {
                int image = 1 + (int) (Math.random() * 50.0);
                image *= 2;
                page = 1 + (int) (Math.random() * 5.0);
                try {
                    url = new URL("https://rule34.xxx/index.php?page=dapi&s=post&q=index&tags=" + c1 + "&limit=100&pid="
                            + page);
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    try {
                        Element personne;
                        DocumentBuilder buider = factory.newDocumentBuilder();
                        Document document = buider.parse(url.toString());
                        Element racine = document.getDocumentElement();
                        NodeList racineNoeuds = racine.getChildNodes();
                        try {
                            personne = (Element) racineNoeuds.item(image);
                        } catch (ClassCastException e) {
                            personne = (Element) racineNoeuds.item(1);
                        }
                        System.out.println(personne.getNodeName());
                        System.out.println("file_url : " + personne.getAttribute("file_url"));
                        String photo = personne.getAttribute("file_url");
                        EmbedBuilder builder1 = new EmbedBuilder();
                        builder1.setImage(photo);
                        builder1.setColor(color.couleurAleatoire(user));
                        builder1.setTimestamp(Instant.now());
                        builder1.setFooter(guild.getName(), guild.getIconUrl());
                        builder1.setAuthor(user.getName(), null, user.getAvatarUrl());
                        channel.sendMessage(builder1.build()).queue();
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }
            }
        } else {
            if (lang == command.Language.fr) {
                channel.sendMessage(
                        "Désolé mais vous ne pouvez utiliser cette commande que sur un channel NSFW (=set NSFW)")
                        .queue();
            }
            if (lang == command.Language.en) {
                channel.sendMessage(
                        "Sorry but you can't use thsi commande on this channel (type =set NSFW to be able to use this command here)")
                        .queue();
            }
        }
    }
}
