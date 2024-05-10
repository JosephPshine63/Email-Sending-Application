package dev.pioruocco.email.resource;

import dev.pioruocco.email.domain.RispostaHttp;
import dev.pioruocco.email.domain.User;
import dev.pioruocco.email.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping(path="api/users/")
public class UserResource {

    public static final String ACCOUNT_CREATED = "Account Created!!!";

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    //creating a user
    @PostMapping(path = "simple-mail")
    public ResponseEntity<RispostaHttp> createUserSimpleMail(@RequestBody User user) {
        User user1 = userService.saveAndSendSimpleMail(user);

        return ResponseEntity.created(URI.create("")).body(
                RispostaHttp.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("user", user1))
                        .status(HttpStatus.CREATED)
                        .message(ACCOUNT_CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    //attachment
    @PostMapping(path = "attachment-mail")
    public ResponseEntity<RispostaHttp> createUserAttachementMail(@RequestBody User user) {
        User user1 = userService.saveAndSendEmailWithAttachment(user);

        return ResponseEntity.created(URI.create("")).body(
                RispostaHttp.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("user", user1))
                        .status(HttpStatus.CREATED)
                        .message(ACCOUNT_CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    //embedded files
    @PostMapping(path = "embedded-mail")
    public ResponseEntity<RispostaHttp> createUSerEmbeddedFiles(@RequestBody User user) {
        User user1 = userService.saveAndSendEmailEmbeddedFiles(user);

        return ResponseEntity.created(URI.create("")).body(
                RispostaHttp.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("user", user1))
                        .status(HttpStatus.CREATED)
                        .message(ACCOUNT_CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    //html mail
    @PostMapping(path = "html-mail")
    public ResponseEntity<RispostaHttp> createUserHtmlMail(@RequestBody User user) {
        User user1 = userService.saveAndSendHtmlMail(user);

        return ResponseEntity.created(URI.create("")).body(
                RispostaHttp.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("user", user1))
                        .status(HttpStatus.CREATED)
                        .message(ACCOUNT_CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    //html mail
    @PostMapping(path = "html-mail-embedded")
    public ResponseEntity<RispostaHttp> createUserHtmlMailEmbeddedFiles(@RequestBody User user) {
        User user1 = userService.saveAndSendHtmlMailEmbeddedFile(user);

        return ResponseEntity.created(URI.create("")).body(
                RispostaHttp.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("user", user1))
                        .status(HttpStatus.CREATED)
                        .message(ACCOUNT_CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    //ritorno di un utente per token
    @GetMapping
    public ResponseEntity<RispostaHttp> confirmUserAccount(@RequestParam("token") String token) {
        Boolean isSuccess = userService.verifyToken(token);
        return ResponseEntity.ok().body(
                RispostaHttp.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Success", isSuccess))
                        .message("Account Verified")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
