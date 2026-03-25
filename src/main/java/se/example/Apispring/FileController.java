package se.example.Apispring;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping("/")
public class FileController {

    private final FileInterface fileinterface;

    @Autowired
    public FileController(FileService fileinterface) {
        this.fileinterface = fileinterface;
    }

    // 📁 Create folder
    @PostMapping("folder")
    public ResponseEntity<EntityModel<String>> createFolder(
            @RequestParam String name,
            @RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient oauthClient) {

        String username = oauthClient.getPrincipalName();
        fileinterface.createFolder(name, username);

        EntityModel<String> model = EntityModel.of("Folder created: " + name);

        model.add(
                linkTo(methodOn(FileController.class).createFolder(name, null)).withSelfRel(),
                linkTo(methodOn(FileController.class).getFilesByName(name, null)).withRel("files-in-folder"),
                linkTo(methodOn(FileController.class).uploadFiles(null, null, null, name, null)).withRel("upload-file")
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    // 📄 Upload file
    @PostMapping("file")
    public ResponseEntity<EntityModel<String>> uploadFiles(
            @RequestParam String name,
            @RequestParam byte[] content,
            @RequestParam String filetype,
            @RequestParam String foldername,
            @RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient oauthClient) {

        fileinterface.upploadFile(name, content, filetype, foldername, oauthClient.getPrincipalName());

        EntityModel<String> model = EntityModel.of("File uploaded: " + name);

        model.add(
                linkTo(methodOn(FileController.class).uploadFiles(null, null, null, foldername, null)).withSelfRel(),
                linkTo(methodOn(FileController.class).getFilesByName(foldername, null)).withRel("files-in-folder")
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    // 🔍 Get file by ID
    @GetMapping("file")
    public ResponseEntity<EntityModel<File>> getFile(
            @RequestParam Long id,
            @RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient oauthClient) {

        File file = fileinterface.getFile(id, oauthClient.getPrincipalName());

        EntityModel<File> model = EntityModel.of(file);

        model.add(
                linkTo(methodOn(FileController.class).getFile(id, null)).withSelfRel(),
                linkTo(methodOn(FileController.class).deleteFile(id, null)).withRel("delete"),
                linkTo(methodOn(FileController.class).getFilesByName(file.getName(), null)).withRel("search-by-name")
        );

        return ResponseEntity.ok(model);
    }

    // 🗑 Delete file
    @DeleteMapping("file")
    public ResponseEntity<EntityModel<String>> deleteFile(
            @RequestParam Long id,
            @RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient oauthClient) {

        fileinterface.deleteFile(id, oauthClient.getPrincipalName());

        EntityModel<String> model = EntityModel.of("File deleted");

        model.add(
                linkTo(methodOn(FileController.class).getFilesByName("", null)).withRel("search-files"),
                linkTo(methodOn(FileController.class).uploadFiles(null, null, null, null, null)).withRel("upload-file")
        );

        return ResponseEntity.ok(model);
    }

    // 🔎 Search files by name
    @GetMapping("file/name")
    public ResponseEntity<CollectionModel<EntityModel<File>>> getFilesByName(
            @RequestParam String name,
            @RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient oauthClient) {

        List<File> files = fileinterface.getFilesByName(name, oauthClient.getPrincipalName());

        List<EntityModel<File>> fileModels = files.stream()
                .map(f -> EntityModel.of(
                        f,
                        linkTo(methodOn(FileController.class).getFile(f.getId(), null)).withSelfRel()
                ))
                .toList();

        CollectionModel<EntityModel<File>> collectionModel = CollectionModel.of(
                fileModels,
                linkTo(methodOn(FileController.class).getFilesByName(name, null)).withSelfRel()
        );

        return ResponseEntity.ok(collectionModel);
    }
}