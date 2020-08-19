package com.todotresde.interbanking.stockoption.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(	name = "file_info" )
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 200)
    private String url;

    public FileInfo(){

    }

    /**
     * Instantiates a new File info.
     *
     * @param username the username
     * @param name the name
     * @param url  the url
     */
    public FileInfo(String username, String name, String url) {
        this.username = username;
        this.name = name;
        this.url = url;
    }
}
