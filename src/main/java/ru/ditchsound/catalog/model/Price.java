package ru.ditchsound.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "price")
@ToString
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priceId;

    @Column (name = "mixing")
    private Double mixing;

    @Column (name = "mastering")
    private Double mastering;

    @Column (name = "editing_drums")
    private Double editingDrums;

    @Column (name = "editing_vocals")
    private Double editingVocals;

    @Column (name = "editing_other_instr")
    private Double editingInstrument;

    @Column (name = "producing")
    private Double producing;

    @Column (name = "discount")
    private Double discount;

    @OneToOne()
    @JoinColumn(name = "request_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Request request;

}
