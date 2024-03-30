package ru.ditchsound.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "genre")
public class Genre {
    @Id
   @GeneratedValue (strategy = GenerationType.IDENTITY)
   private Long genreId;
   @Column (name = "genre_name")
   private String genreName;

    @ManyToOne (fetch = FetchType.LAZY)
    private Release release;
}
