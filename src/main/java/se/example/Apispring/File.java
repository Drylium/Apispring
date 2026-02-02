package se.example.Apispring;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "T_FILE")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int filesize;
    private String filetype;
    private byte[] content;

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

    public int getFilesize() {
        return filesize;
    }

    public void setFilesize(int filesize) {
        this.filesize = filesize;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }
}
