package com.example.rutunes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "save_albums")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveAlbums {
    public SaveAlbums(User user, Album album){
        this.purchasePrice = album.getPrice();
        this.dateOfBuying = new Date();
        this.user = user;
        this.album = album;
        this.title = album.getTitle();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "purchase_price")
    private Integer purchasePrice;
    @Column(name = "date_of_buying")
    private Date dateOfBuying;
    @Column(name = "title")
    private String title;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private Album album;
}