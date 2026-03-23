package se.example.Apispring;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_FOLDER")
@NoArgsConstructor
@Getter
@Setter
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String username;

    public Folder(String name, String username) {
        this.name = name;
        this.username = username;
    }
}
