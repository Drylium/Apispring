package se.example.Apispring;

import org.springframework.data.repository.CrudRepository;

public abstract interface FolderRepository extends CrudRepository<Folder, Long> {

    Folder findByNameAndUsername(String name, String username);

    Folder save(Folder folder);
}
