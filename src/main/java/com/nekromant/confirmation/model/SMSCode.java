package com.nekromant.confirmation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SMSCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 4)
    private Integer code;

    @Column
    private String phoneNumber;

    @Column
    private LocalDateTime expiredDate;

    public SMSCode(Integer code, String phoneNumber, LocalDateTime expiredDate) {
        this.code = code;
        this.phoneNumber = phoneNumber;
        this.expiredDate = expiredDate;
    }
}
