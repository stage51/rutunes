package com.example.rutunes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_basket")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBasket {
    public UserBasket(User user, Album album){
        this.dateOfAdding = new Date();
        this.user = user;
        this.album = album;
        this.title = album.getTitle();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "date_of_adding")
    private Date dateOfAdding;
    @Column(name = "title")
    private String title;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private Album album;
}