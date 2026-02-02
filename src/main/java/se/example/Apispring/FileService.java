package se.example.Apispring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void createFolder(String name) {
        folderRepository.save(new Folder(name));
    }

    @Override
    public void upploadFile(String name, byte[] content, String filetype, String foldername) {
        Folder folder = folderRepository.findByName(foldername);
        if (folder == null) {
            folder = new Folder(foldername);
            folder = folderRepository.save(folder);
        }
        repository.createFile(name, content, filetype, folder);
    }

    @Override
    public void deleteFile(Long id) {
       repository.deleteFileById(id);
    }

    @Override
    public File getFile(Long id) {
        return repository.findFileById(id);
    }

    @Override
    public List<File> getFilesByName(String name) {
        return repository.findFilesByName(name);
    }
}
