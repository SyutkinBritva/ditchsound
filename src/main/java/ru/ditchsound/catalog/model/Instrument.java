//package ru.ditchsound.catalog.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "instrument")
//public class Instrument {
//    @Id
//    @GeneratedValue (strategy = GenerationType.IDENTITY)
//    private Long instrumentId;
//    @Column (name = "instrument_type")
//    private String instrumentType;
//    @Column (name = "instrument_model")
//    private String instrumentModel;
//    @Column (name = "instrument_tone_stack")
//    private String instrumentToneStack;
//    @Column (name = "instrument_tone_stack_img")
//    private String instrumentToneStackImg;
//
//    @ManyToOne (fetch = FetchType.LAZY)
//    private Release release;
//
//    @ManyToOne (fetch = FetchType.LAZY)
//    private Studio studio;
//}
