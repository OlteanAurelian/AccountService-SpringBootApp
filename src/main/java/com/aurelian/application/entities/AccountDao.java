package com.aurelian.application.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
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