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
//@Table(name = "price")
//public class Price {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long priceId;
//    @Column (name = "mixing")
//    private Double mixing;
//    @Column (name = "mastering")
//    private Double mastering;
//    @Column (name = "editing_drums")
//    private Double editingDrums;
//    @Column (name = "editing_vocals")
//    private Double editingVocals;
//    @Column (name = "editing_other_instr")
//    private Double editingInstrument;
//    @Column (name = "producing")
//    private Double producing;
//    @Column (name = "discount")
//    private Double discount;
//    @Column (name = "total_amount")
//    private Double totalAmount;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "release_id")
//    private Release release;
//}
