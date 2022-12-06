package ru.ditchsound.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn (name = "instrument_id")
    private List<Instrument> instrumentList;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn (name = "guitar_id")
    private List<Guitar> guitarList;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn (name = "drums_id")
    private List<Drums> drumsList;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn (name = "vocal_id")
    private List<Vocal> vocalsList;

}
