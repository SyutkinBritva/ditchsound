package ru.ditchsound.catalog.model;

import io.hypersistence.utils.hibernate.type.array.EnumArrayType;
import io.hypersistence.utils.hibernate.type.array.internal.AbstractArrayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import ru.ditchsound.catalog.enums.GenreEnum;
import ru.ditchsound.catalog.enums.ReleaseStatus;
import ru.ditchsound.catalog.enums.WorkDescription;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "release")
@TypeDef(
        typeClass = EnumArrayType.class,
        defaultForType = WorkDescription[].class,
        parameters = { @Parameter(
                        name = AbstractArrayType.SQL_ARRAY_TYPE,
                        value = "work_description"
                )
        }
)
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long releaseId;

    @Column(name = "band_name")
    private String bandName;

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

    @Column (name = "multitrack_link")
    private String multitrackLink;

    @Column (name = "music_label")
    private String musicLabel;

    @Column (name = "total_amount")
    private double totalAmount;

    @Column (name = "total_amount_with_discount")
    private double totalAmountWithDiscount;

    @OneToMany(mappedBy = "release", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Drums> drumsList;

    @OneToMany(mappedBy = "release", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Guitar> guitarList;

    @OneToMany(mappedBy = "release", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Instrument> instrumentList;

    @OneToMany(mappedBy = "release", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vocal> vocalList;

    @OneToOne(mappedBy = "release", cascade = CascadeType.ALL, orphanRemoval = true)
    private Price price;

    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

    @Column(
            name = "work_description",
            columnDefinition = "work_description[]"
    )
    private WorkDescription[] workDescription;

    @Enumerated(EnumType.STRING)
    private ReleaseStatus releaseStatus;
}
