package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.TransactionType;
import org.example.validation.AccountValidator;

import java.math.BigDecimal;
@Getter
@Setter
public class BusinessAccount extends CheckingAccount{
    private String companyName;
    private static final  double FEE = 25;
    public BusinessAccount( String ownerName, BigDecimal balance,String companyName) {
        super( ownerName, balance,2000);
        this.companyName = companyName;
    }

    public void chargeFee(){
        super.withdraw(FEE);
        log(TransactionType.FEE, FEE);
    }

}
