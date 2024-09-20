package com.eldar.challenge.DTO;

import org.springframework.http.ResponseEntity;

public class ResponseEntityDTO<T> {
    private boolean success;
    private String message;
    private T data;

    public ResponseEntityDTO(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Getters y Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // Métodos estáticos para generar respuestas comunes
    public static <T> ResponseEntityDTO<T> success(String message, T data) {
        return new ResponseEntityDTO<>(true, message, data);
    }

    public static <T> ResponseEntityDTO<T> error(String message) {
        return new ResponseEntityDTO<>(false, message, null);
    }
}