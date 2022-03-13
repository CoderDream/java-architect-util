package com.shanghai.bean;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by lijm on 2017-11-06.
 */
@Entity
@Table(name = "t_bank_account")
public class BankAccount extends JpaRepositoriesAutoConfiguration{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String cardno;

    private String name;

    private Long id;

    private BigDecimal money;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
