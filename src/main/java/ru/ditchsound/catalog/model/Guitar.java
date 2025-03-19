package ru.ditchsound.catalog.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "guitar")

public class Guitar extends Instrument {

    @Column (name = "signal_chain")
    private String signalChain;

}
