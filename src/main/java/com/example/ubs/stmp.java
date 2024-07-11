package com.example.ubs;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javax.net.ssl.*;

public class stmp {

    public static void main(String[] args) {
        final String username = "milesbrain280@gmail.com";
        final String password = "twircsrekcnyubin";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.properties.mail.smtp.starttls.required",true);
        props.put("mail.properties.mail.smtp.auth",true);
        props.put("mail.properties.mail.smtp.ssl.trust",true);

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        public void checkClientTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }
                        public void checkServerTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }
                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ubsbanking@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("milesbrain280@gmail.com"));
            message.setSubject("Test Mail");
            message.setText("Hello, this is a test email.");

            Transport.send(message);

            System.out.println("Email sent successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
