package se.example.Apispring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class FileController {

    FileInterface fileinterface;

    @Autowired
    public FileController(FileService fileinterface) {
        this.fileinterface = fileinterface;
    }

    @PostMapping(value = "folder")
    public ResponseEntity<?> createFolder(@RequestParam String name, @RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient oauthClient) {
        String username = oauthClient.getPrincipalName();
        fileinterface.createFolder(name, username);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "file")
    public ResponseEntity<?> uploadFiles(@RequestParam String name,
                                         @RequestParam byte[] content,
                                         @RequestParam String filetype,
                                         @RequestParam String foldername,
                                         @RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient oauthClient) {
        fileinterface.upploadFile(name, content, filetype, foldername, oauthClient.getPrincipalName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "file")
    public ResponseEntity<?> deleteFile(@RequestParam Long id, @RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient oauthClient) {
        fileinterface.deleteFile(id, oauthClient.getPrincipalName());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "file")
    public ResponseEntity<?> getFile(@RequestParam Long id, @RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient oauthClient) {
        File file = fileinterface.getFile(id, oauthClient.getPrincipalName());
        return ResponseEntity.ok(file);
    }

    @GetMapping(value = "file/name")
    public ResponseEntity<?> getFilesByName(@RequestParam String name, @RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient oauthClient) {
        List<File> files = fileinterface.getFilesByName(name, oauthClient.getPrincipalName());
        return ResponseEntity.ok(files);
    }

}
