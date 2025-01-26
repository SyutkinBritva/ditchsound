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
@Table(name = "vocal")
@ToString
public class Vocal {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long vocalId;

    @Column(name = "vocal_type")
    private String vocalType;

    @Column(name = "vocal_technique")
    private String vocalTechnique;

    @Column(name = "vocal_mic")
    private String vocalMic;

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
