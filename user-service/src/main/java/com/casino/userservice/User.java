package com.casino.userservice;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity 
@Table(name = "users")
public class User {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;
    
    @NotBlank
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private long balance = 1000;
    
    protected User() {
        
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public long getBalance() {
        return balance;
    }
    
    public void debit(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Le montant à débiter doit être positif");
        if (amount > balance) throw new IllegalArgumentException("Solde insuffisant");
        balance -= amount;
    }
    
    public void credit(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Le montant à créditer doit être positif");
        balance += amount;
    }
}
