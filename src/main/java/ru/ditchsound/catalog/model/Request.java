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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "request")
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
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @Column(name = "band_name")
    private String bandName;

    @Column(name = "count_of_track")
    private Integer countOfTrack;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "multitrack_link")
    private String multitrackLink;

    @Column(name = "band_email")
    private String bandEmail;

    @Column(
            name = "work_description",
            columnDefinition = "work_description[]"
    )
    private WorkDescription[] workDescription;

    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ReleaseStatus releaseStatus;

    @OneToOne(mappedBy = "request", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Price price;

    @OneToOne(mappedBy = "request", optional = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Release release;

}