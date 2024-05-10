package dev.pioruocco.email.service;

import dev.pioruocco.email.domain.User;

public interface UserService {

    User saveAndSendSimpleMail(User user);

    User saveAndSendEmailWithAttachment(User user);

    //salva e invia embedded files
    User saveAndSendEmailEmbeddedFiles(User user);

    //salva e invia una mail con template html
    User saveAndSendHtmlMail(User user);

    //salva e invia mail con template html e files embedded
    User saveAndSendHtmlMailEmbeddedFile(User user);

    Boolean verifyToken(String token);

}
