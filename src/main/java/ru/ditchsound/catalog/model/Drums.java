package ru.ditchsound.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "drums")
@ToString
public class Drums {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long drumsId;

    @Column(name = "drums_type")
    private String drumsType;

    @Column(name = "drums_model")
    private String drumsModel;

    @Column(name = "drums_mics")
    private String drumsMics;

    @Column(name = "drums_img")
    private String drumsImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "release_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Release release;

    @OneToOne(mappedBy = "drums", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Studio studio;
}
