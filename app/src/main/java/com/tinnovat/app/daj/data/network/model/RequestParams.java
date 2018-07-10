package com.tinnovat.app.daj.data.network.model;

/**
 * Created by Rahul on 08-07-2018.
 */

public class RequestParams {

    public class LoginReequest {
        private String UserName;
        private String Password;
        private String language;

        public LoginReequest(String userName, String password, String language) {
            UserName = userName;
            Password = password;
            this.language = language;
        }
    }
}
