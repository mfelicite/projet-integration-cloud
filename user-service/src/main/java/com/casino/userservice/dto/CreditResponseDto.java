package com.casino.userservice.dto;

public record CreditResponseDto(
    Long id, 
    String username,
    long balance
) {
    
    public static class Builder {
        private Long id;
        private String username;
        private long balance;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder username(String username) {
            this.username = username;
            return this;
        }
        
        public Builder balance(long balance) {
            this.balance = balance;
            return this;
        }
        
        public CreditResponseDto build() {
            return new CreditResponseDto(this.id, this.username, this.balance);
        }
    }
}
