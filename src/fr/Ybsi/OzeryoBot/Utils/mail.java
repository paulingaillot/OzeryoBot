/*
 * Decompiled with CFR 0.145.
 */
package fr.Ybsi.OzeryoBot.Utils;

import fr.Ybsi.OzeryoBot.Utils.TextFileWriter;
import java.io.PrintStream;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mail {
    static String passwd = TextFileWriter.read("/home/DiscordBot/Rasberry/key/OutlookKey.txt");

    public static void main(String objet, String mess) {
        try {
            String smtpHost = "smtp-mail.outlook.com";
            String from = "paulingaillot@outlook.com";
            String to = "paulingaillot@gmail.com";
            String username = "paulingaillot@outlook.com";
            String password = passwd;
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", smtpHost);
            props.put("mail.smtp.port", "587");
            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(objet);
            message.setText(mess);
            Transport tr = session.getTransport("smtp");
            tr.connect(smtpHost, username, password);
            message.saveChanges();
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
            System.out.println("message envoyé");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
