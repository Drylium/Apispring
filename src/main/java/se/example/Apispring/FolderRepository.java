package se.example.Apispring;

import org.springframework.data.repository.CrudRepository;

public abstract interface FolderRepository extends CrudRepository<Folder, Long> {

    Folder findByName(String name);

    Folder save(Folder folder);
}
