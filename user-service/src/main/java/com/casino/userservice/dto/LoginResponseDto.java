package com.casino.userservice.dto;

public record LoginResponseDto(Long id, String username) {
    
    public static class Builder {
        private Long id;
        private String username;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder username(String username) {
            this.username = username;
            return this;
        }
        
        public LoginResponseDto build() {
            return new LoginResponseDto(this.id, this.username);
        }
    }
}
