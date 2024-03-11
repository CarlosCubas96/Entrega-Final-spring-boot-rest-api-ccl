package com.proyectdwes.api.proyect.dto;


public class JwtAuthenticationResponseDTO {
    private String token;

	public JwtAuthenticationResponseDTO(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

    public static JwtAuthenticationResponseBuilder builder() {
        return new JwtAuthenticationResponseBuilder();
    }

    public static class JwtAuthenticationResponseBuilder {
        private String token;

        public JwtAuthenticationResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public JwtAuthenticationResponseDTO build() {
            return new JwtAuthenticationResponseDTO(token);
        }
    }
    
}
