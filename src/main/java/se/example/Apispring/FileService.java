package se.example.Apispring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class FileService implements FileInterface {

    FileRepository repository;
    FolderRepository folderRepository;

    @Autowired
    public FileService(FileRepository repository, FolderRepository folderRepository) {
        this.repository = repository;
        this.folderRepository = folderRepository;
    }

    @Override
    public void createFolder(String name, String username) {
        Folder folder = new Folder(name);
        folder.setUsername(username);
        folderRepository.save(folder);
    }

    @Override
    public void upploadFile(String name, byte[] content, String filetype, String foldername, String username) {
        Folder folder = folderRepository.findByNameAndUsername(foldername, username);
        if (folder == null) {
            folder = new Folder(foldername);
            folder = folderRepository.save(folder);
        }
        repository.save(new File(name, content.length, filetype, content, folder));
        //repository.createFile(name, content, filetype, folder);
    }

    @Override
    public void deleteFile(Long id, String username) {
        Optional<File> file = repository.findFileByIdAndUsername(id, username); // Hämta filen
        // Kolla om den finns
        // Om den finns, hämta ut filen ur din optional och gör en delete
        file.ifPresent(value -> repository.delete(value));
    }

        @Override
        public File getFile (Long id, String username){
            return repository.findFileByIdAndUsername(id, username).orElseThrow();
        }

    @Override
    public List<File> getFilesByName(String name, String username) {
        return repository.findFilesByNameAndUserName(name, username);
    }

    /*
    public getFileAsString(Long id) {
        File file = getFile(id);
        return new String(file.getContent(), StandardCharsets.UTF_8);
    }
     */
}
