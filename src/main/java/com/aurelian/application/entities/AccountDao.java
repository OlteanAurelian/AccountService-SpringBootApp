package com.aurelian.application.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AccountDao {
    @Id
    @GeneratedValue
    private Long id;
    private String iban;
    private String currency;
    private double balance;
    private Date lastUpdate;
}