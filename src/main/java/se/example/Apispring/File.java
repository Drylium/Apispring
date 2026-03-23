package se.example.Apispring;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "T_FILE")
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int filesize;
    private String filetype;
    private byte[] content;
    private String username;


    @ManyToOne
    @JoinColumn(name = "id_folder")
    private Folder folder;

    public File(String name, int filesize, String filetype, byte[] content, Folder folder, String username) {
        this.name = name;
        this.filesize = filesize;
        this.filetype = filetype;
        this.content = content;
        this.folder = folder;
        this.username = username;
    }

}
