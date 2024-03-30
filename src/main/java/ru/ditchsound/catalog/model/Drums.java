package ru.ditchsound.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "drums")
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

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "release_id")
    private Release release;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id")
    private Studio studio;
}
