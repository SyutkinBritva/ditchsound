package ru.ditchsound.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.ditchsound.catalog.enums.InstrumentPropertyEnum;

import javax.persistence.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "instrument")
public abstract class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "instrument_property")
    @Enumerated(EnumType.STRING)
    private InstrumentPropertyEnum instrumentProperty;

    @Column(name = "type")
    private String instrumentType;

    @Column (name = "instrument_model")
    private String instrumentModel;

    @Column (name = "instrument_img")
    private String instrumentImg;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "release_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Release release;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Studio studio;
}
