package com.example.nhattruong.financialmanager.interactor.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpRequest {
    @SerializedName("firstName")
    @Expose
    private String firstName;

    @SerializedName("lastName")
    @Expose
    private String lastName;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;

    private SignUpRequest(SignUpRequestBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.username = builder.userName;
        this.password = builder.password;
    }

    public static class SignUpRequestBuilder {

        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String userName;
        private String password;

        public SignUpRequestBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public SignUpRequestBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public SignUpRequestBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public SignUpRequestBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public SignUpRequestBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public SignUpRequestBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public SignUpRequest build() {
            return new SignUpRequest(this);
        }
    }
}
