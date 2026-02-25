package se.example.Apispring;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_FOLDER")
@NoArgsConstructor
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    long id;

    String name;

    public Folder(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
