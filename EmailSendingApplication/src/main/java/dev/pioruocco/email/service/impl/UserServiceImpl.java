package dev.pioruocco.email.service.impl;

import dev.pioruocco.email.domain.Confirmation;
import dev.pioruocco.email.domain.User;
import dev.pioruocco.email.repository.ConfirmationRepository;
import dev.pioruocco.email.repository.UserRepository;
import dev.pioruocco.email.service.EmailService;
import dev.pioruocco.email.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final static String EMAIL_EXISTS_MESSAGE = "Email already exists.";

    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, ConfirmationRepository confirmationRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.confirmationRepository = confirmationRepository;
        this.emailService = emailService;
    }

    //salva e ivia mail semplice
    @Override
    public User saveAndSendSimpleMail(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException(EMAIL_EXISTS_MESSAGE);
        }

        user.setEnabled(false);
        userRepository.save(user);

        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);

        /*TODO send email to the user*/
        emailService.sendSimpleMailMessage(user.getUsername(), user.getEmail(), confirmation.getToken());
        return user;
    }

    //salva e invia alllegati
    @Override
    public User saveAndSendEmailWithAttachment(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException(EMAIL_EXISTS_MESSAGE);
        }

        user.setEnabled(false);
        userRepository.save(user);

        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);

        /*TODO send email to the user*/
        emailService.sendMimeMessageWithAttachments(user.getUsername(), user.getEmail(), confirmation.getToken());
        return user;
    }

    //salva e invia embedded files
    @Override
    public User saveAndSendEmailEmbeddedFiles(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException(EMAIL_EXISTS_MESSAGE);
        }

        user.setEnabled(false);
        userRepository.save(user);

        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);

        /*TODO send email to the user*/
        emailService.sendMimeMessageWithEmbeddedFiles(user.getUsername(), user.getEmail(), confirmation.getToken());
        return user;
    }

   //salva e invia una mail con template html
   @Override
   public User saveAndSendHtmlMail(User user) {
       if(userRepository.existsByEmail(user.getEmail())){
           throw new RuntimeException(EMAIL_EXISTS_MESSAGE);
       }

       user.setEnabled(false);
       userRepository.save(user);

       Confirmation confirmation = new Confirmation(user);
       confirmationRepository.save(confirmation);

       /*TODO send email to the user*/
       emailService.sendHtmlEmail(user.getUsername(), user.getEmail(), confirmation.getToken());
       return user;
   }

   //salva e invia mail con template html e files embedded
   @Override
   public User saveAndSendHtmlMailEmbeddedFile(User user) {
       if(userRepository.existsByEmail(user.getEmail())){
           throw new RuntimeException(EMAIL_EXISTS_MESSAGE);
       }

       user.setEnabled(false);
       userRepository.save(user);

       Confirmation confirmation = new Confirmation(user);
       confirmationRepository.save(confirmation);

       /*TODO send email to the user*/
       emailService.sendHtmlEmailWithEmbeddedFiles(user.getUsername(), user.getEmail(), confirmation.getToken());
       return user;
   }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken(token);

        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        user.setEnabled(true);
        userRepository.save(user);

        //confirmationRepository.delete(confirmation);

        return Boolean.TRUE;
    }
}
