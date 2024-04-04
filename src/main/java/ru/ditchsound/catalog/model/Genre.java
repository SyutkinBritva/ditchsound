package ru.ditchsound.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "genre")
public class Genre {
    @Id
   @GeneratedValue (strategy = GenerationType.IDENTITY)
   private Long genreId;
   @Column (name = "genre_name")
   private String genreName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "release_id")
    private Release release;
}
