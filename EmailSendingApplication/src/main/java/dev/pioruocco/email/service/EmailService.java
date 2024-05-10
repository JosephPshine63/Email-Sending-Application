package dev.pioruocco.email.service;

public interface EmailService {

    //invio messaggio semplice
    void sendSimpleMailMessage(String name, String to, String token);

    //invio messaggio con allegato
    void sendMimeMessageWithAttachments(String name, String to, String token);

    //invio messaggio con cartelle(?)
    void sendMimeMessageWithEmbeddedFiles(String name, String to, String token);

    //manda messaggio html
    void sendHtmlEmail(String name, String to, String token);

    //invio messaggo html con file innestrati
    void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token);

}
