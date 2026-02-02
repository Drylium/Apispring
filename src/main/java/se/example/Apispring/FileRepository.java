package se.example.Apispring;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FileRepository extends CrudRepository<File, Long> {
    File save(File file);

    void delete(File file);

    File findFileById(Long id);

    List<File> findFilesByName(String name);
}
