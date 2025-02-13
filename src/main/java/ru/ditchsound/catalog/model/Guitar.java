package ru.ditchsound.catalog.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "guitar")
@AttributeOverride(name = "id", column = @Column(name = "guitar_id"))
public class Guitar extends BaseEntity {

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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Release release;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "studio_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Studio studio;
}
