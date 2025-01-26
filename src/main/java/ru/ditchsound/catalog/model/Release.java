package ru.ditchsound.catalog.model;

import io.hypersistence.utils.hibernate.type.array.EnumArrayType;
import io.hypersistence.utils.hibernate.type.array.internal.AbstractArrayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import ru.ditchsound.catalog.enums.WorkDescription;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
        parameters = {@Parameter(
                name = AbstractArrayType.SQL_ARRAY_TYPE,
                value = "work_description"
        )
        }
)
@ToString
public class Release {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long releaseId;

    @Column(name = "band_name")
    private String bandName; // надо и тут и в заявке

    @Column(name = "album_cover_link")
    private String albumCoverLink;

    @Column(name = "social_network_link")
    private String socialNetworkLink;

    @Column(name = "count_of_track")
    private Integer countOfTrack; // и тут и в поле заявки

    @Column(name = "release_dttm")
    private LocalDate releaseDttm;

    @Column(name = "music_label")
    private String musicLabel;

    @Column(name = "total_amount_with_discount")
    private double totalAmountWithDiscount; // нужно удалить

    @Column(name = "genre")
    private String genre;


    @OneToMany(mappedBy = "release", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Drums> drumsList;

    @OneToMany(mappedBy = "release", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Guitar> guitarList;

    @OneToMany(mappedBy = "release", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Instrument> instrumentList;

    @OneToMany(mappedBy = "release", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Vocal> vocalList;

    @Column(
            name = "work_description",
            columnDefinition = "work_description[]"
    )
    private WorkDescription[] workDescription;

    @OneToOne
    @JoinColumn(name = "request_id", nullable = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Request request;

}
