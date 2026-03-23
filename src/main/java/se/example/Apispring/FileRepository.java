package se.example.Apispring;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends CrudRepository<File, Long> {
    File save(File file);

    void delete(File file);

    Optional<File> findFileByIdAndUsername(Long id, String username);

    List<File> findFilesByNameAndUserName(String name, String username);
}
