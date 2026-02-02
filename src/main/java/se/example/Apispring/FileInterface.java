package se.example.Apispring;

import java.util.List;

public interface FileInterface {
    void createFolder(String name);
    void upploadFile(String name, byte[] content, String filetype, String foldername);
    void deleteFile(Long id);
    File getFile(Long id);
    List<File> getFilesByName(String name);
}
