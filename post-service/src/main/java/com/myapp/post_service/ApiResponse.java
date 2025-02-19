package com.myapp.post_service;

public class ApiResponse<T> {
        private boolean hasError;
        private T data;
        private int status;
        private String message;

        public ApiResponse(boolean hasError, T data, int status, String message) {
            this.hasError = hasError;
            this.data = data;
            this.status = status;
            this.message = message;
        }

        // Getters & Setters

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
