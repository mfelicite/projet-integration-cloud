package com.casino.userservice.dto;

public record RegisterResponseDto(Long id, String username) {
    
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
        
        public RegisterResponseDto build() {
            return new RegisterResponseDto(this.id, this.username);
        }
    }
}
