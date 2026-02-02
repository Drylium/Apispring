package se.example.Apispring;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends CrudRepository<File, Long> {
    default void createFile(String filename, byte[] content, String filetype, Folder folder) {
        // insert
        save(new File(filename, content.length, filetype, content, folder));
    }

    default void deleteFileById(Long id) {
        Optional<File> file = findById(id);
        file.ifPresent(this::delete);
    }

    File findFileById(Long id);

    List<File> findFilesByName(String name);
}
