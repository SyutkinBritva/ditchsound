package ru.ditchsound.catalog.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "guitar")
public class Guitar {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long guitarId;
    @Column (name = "guitar_type")
    private String guitarType;
    @Column (name = "guitar_model")
    private String guitarModel;
    @Column (name = "tone_stack")
    private String toneStack;
    @Column (name = "tone_stack_img")
    private String toneStackImg;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "release_id")
    private Release release;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "studio_id")
    private Studio studio;
}
