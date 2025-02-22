package ru.ditchsound.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "studio")
@AttributeOverride(name = "id", column = @Column(name = "studio_id"))
public class Studio extends BaseEntity {

    @Column (name = "studio_name")
    private String studioName;

    @Column (name = "studio_place")
    private String studioPlace;

    @Column (name = "social_network_link")
    private String socialNetworkLink;

    @OneToMany(mappedBy = "studio"
            , cascade = CascadeType.ALL
            , orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Instrument> instrumentList;

    @OneToMany(mappedBy = "studio"
            , cascade = CascadeType.ALL
            , orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Guitar> guitarList;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drums_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Drums drums;

    @OneToMany(mappedBy = "studio"
            , cascade = CascadeType.ALL
            , orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Vocal> vocalsList;

}
