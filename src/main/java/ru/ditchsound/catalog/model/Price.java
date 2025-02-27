package ru.ditchsound.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "price")
@AttributeOverride(name = "id", column = @Column(name = "price_id"))
public class Price extends  BaseEntity {

    @Column (name = "mixing")
    private Double mixing;

    @Column (name = "mastering")
    private Double mastering;

    @Column (name = "editing")
    private Double editing;

    @Column (name = "producing")
    private Double producing;

    @Column (name = "discount")
    private Double discount;

    @Column (name = "number_of_songs")
    private Integer numberOfSongs;

    @OneToOne()
    @JoinColumn(name = "request_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Request request;

}
