package se.example.Apispring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createFolder(@RequestParam String name){
        fileinterface.createFolder(name);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "file")
    public ResponseEntity<?> uploadFiles(@RequestParam String name,
                             @RequestParam byte[] content,
                             @RequestParam String filetype,
                             @RequestParam String foldername){
        fileinterface.upploadFile(name, content, filetype, foldername);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "file")
    public ResponseEntity<?> deleteFile(@RequestParam Long id){
        fileinterface.deleteFile(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "file")
    public ResponseEntity<?> getFile(@RequestParam Long id){
        File file = fileinterface.getFile(id);
        return ResponseEntity.ok(file);
    }

    @GetMapping(value = "file/name")
    public ResponseEntity<?> getFilesByName(@RequestParam String name){
        List<File> files = fileinterface.getFilesByName(name);
        return ResponseEntity.ok(files);
    }

}
