package ru.ditchsound.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "studio")
public class Studio {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long studioId;

    @Column (name = "studio_name")
    private String studioName;

    @Column (name = "studio_place")
    private String studioPlace;

    @Column (name = "social_network_link")
    private String socialNetworkLink;

    @OneToMany(mappedBy = "studio"
            , cascade = CascadeType.ALL
            , orphanRemoval = true)
    private List<Instrument> instrumentList;

    @OneToMany(mappedBy = "studio"
            , cascade = CascadeType.ALL
            , orphanRemoval = true)
    private List<Guitar> guitarList;

    @OneToMany(mappedBy = "studio"
            , cascade = CascadeType.ALL
            , orphanRemoval = true)
    private List<Drums> drumsList;

    @OneToMany(mappedBy = "studio"
            , cascade = CascadeType.ALL
            , orphanRemoval = true)
    private List<Vocal> vocalsList;

}
