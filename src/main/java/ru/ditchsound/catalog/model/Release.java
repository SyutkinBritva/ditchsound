package ru.ditchsound.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table (name = "release")
public class Release {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long releaseId;
    @Column (name = "band_name")
    private String bandName;
    @Column (name = "work_description")
    private String workDescription;
    @Column (name = "album_cover_link")
    private String albumCoverLink;
    @Column (name = "social_network_link")
    private String socialNetworkLink;
    @Column (name = "count_of_track")
    private Integer countOfTrack;
    @Column (name = "hours_of_work")
    private Integer hoursOfWork;
    @Column (name = "release_length")
    private Double releaseLength;
    @Column (name = "start_of_work")
    private LocalDate startOfWork;
    @Column (name = "end_of_work")
    private LocalDate endOfWork;
    @Column (name = "release_dttm")
    private LocalDate releaseDttm;
    @Column (name = "release_status")
    private String releaseStatus;
    @Column (name = "multitrack_link")
    private String multitrackLink;
    @Column (name = "music_label")
    private String musicLabel;

    @OneToMany(mappedBy = "release", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Drums> drumsList;
    @OneToMany(mappedBy = "release", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Guitar> guitarList;
    @OneToMany(mappedBy = "release", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Instrument> instrumentList;
    @OneToMany(mappedBy = "release", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vocal> vocalList;

    @OneToOne (
            mappedBy = "release",
            cascade = CascadeType.ALL,
           fetch = FetchType.LAZY,
           optional = false
    )
    private Price price;

    @OneToOne (
            mappedBy = "release",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    private Genre genre;

}
