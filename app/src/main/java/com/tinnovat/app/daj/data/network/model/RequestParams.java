package com.tinnovat.app.daj.data.network.model;

/**
 * Created by Anjali on 08-07-2018.
 */

public class RequestParams {

    public class LoginReequest {
        private String userName;
        private String password;

        public LoginReequest(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }
    }
}
