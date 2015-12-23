package com.bionic.edu.customer;


import com.bionic.edu.merchant.Merchant;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String email;
    private String ccno;
    private String cctype;
    private Date maturity;

    @ManyToMany
    @JoinTable(name = "Payment", joinColumns = @JoinColumn(name = "customerId"), inverseJoinColumns = @JoinColumn(name = "merchantId"))
    private Collection<Merchant> merchants;

    public Collection<Merchant> getMerchants(){
        return this.merchants;
    }

    public void setMerchants(Collection<Merchant> merchants){
        this.merchants = merchants;
    }

    public String getCctype() {
        return cctype;
    }

    public void setCctype(String cctype) {
        this.cctype = cctype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCcno() {
        return ccno;
    }

    public void setCcno(String ccno) {
        this.ccno = ccno;
    }

    public Date getMaturity() {
        return maturity;
    }

    public void setMaturity(Date maturity) {
        this.maturity = maturity;
    }

}