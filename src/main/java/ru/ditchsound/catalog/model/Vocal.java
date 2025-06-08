package ru.ditchsound.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vocal")
public class Vocal extends Instrument {

    @Column(name = "vocal_technique")
    private String vocalTechnique;

    @Column(name = "vocal_mic")
    private String vocalMic;

}
