package com.zerobase.account3.domain;

import com.zerobase.account3.type.AccountStatus;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//JPA, 테이블과 연결
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
}
