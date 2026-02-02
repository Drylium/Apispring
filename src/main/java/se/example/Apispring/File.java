package se.example.Apispring;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "T_FILE")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    final String name;
    final int filesize;
    final String filetype;
    final byte[] content;


    @ManyToOne
    @JoinColumn(name = "id_folder")
    private Folder folder;

    public File(String name, int filesize, String filetype, byte[] content, Folder folder) {
        this.name = name;
        this.filesize = filesize;
        this.filetype = filetype;
        this.content = content;
        this.folder = folder;
    }

}
