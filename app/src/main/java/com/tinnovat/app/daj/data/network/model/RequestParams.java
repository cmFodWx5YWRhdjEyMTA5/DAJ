package com.tinnovat.app.daj.data.network.model;

import java.util.List;

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

    public class ResetPasswordRequest {
        String userName;
        String email;

        public ResetPasswordRequest(String userName, String email) {
            this.userName = userName;
            this.email = email;
        }
    }

    public class ChangePasswordRequest {
        private String Oldpassword;
        private String Newpassword;

        public ChangePasswordRequest(String oldpassword, String newpassword) {
            this.Oldpassword = oldpassword;
            this.Newpassword = newpassword;
        }
    }

    public class ServiceBookingRequest {
        private int category_id;
        private int service_id;
        private String date;
        private List<Integer> TimeSlots;
        private int guest_no;
        private String comments;

        public ServiceBookingRequest(int category_id, int service_id, String date, List<Integer> timeSlots, int guest_no, String comments) {
            this.category_id = category_id;
            this.service_id = service_id;
            this.date = date;
            this.TimeSlots = timeSlots;
            this.guest_no = guest_no;
            this.comments = comments;
        }
    }

    public class GuestRegistrationRequest {
        private String name;
        private String date;
        private String time;
        private int purpose;
        private String vehicleNo;
        public GuestRegistrationRequest(String name,String date,String time, int purpose , String vehicleNo ){
            this.name = name;
            this.date = date;
            this.time = time;
            this.purpose = purpose;
            this.vehicleNo = vehicleNo;

        }
    }
}
