package se.example.Apispring;

import java.util.List;

public interface FileInterface {
    void createFolder(String name, String username);
    void upploadFile(String name, byte[] content, String filetype, String foldername, String username);
    void deleteFile(Long id, String username);
    File getFile(Long id, String username);
    List<File> getFilesByName(String name, String username);
}
