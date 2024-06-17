package com.example.springboot.modules.report;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity(name = "report")
@Table(name = "report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "lob")
    private String lob;

    @Column(name = "cause_of_claim")
    private String causeOfClaim;

    @Column(name = "claims_burden")
    private Long claimsBurden;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
}
