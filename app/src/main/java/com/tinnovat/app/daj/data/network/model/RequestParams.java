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
        private String device;
        private String device_token;

        public LoginReequest(String userName, String password, String language,String device,String device_token) {
            UserName = userName;
            Password = password;
            this.language = language;
            this.device = device;
            this.device_token = device_token;
        }
    }

    public class ResetPasswordRequest {
        String UserName;
        String email;

        public ResetPasswordRequest(String userName, String email) {
            this.UserName = userName;
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

    public class DeleteServiceBookingRequest {
        private List<Integer> booking_id;

        public DeleteServiceBookingRequest(List<Integer> booking_id) {
            this.booking_id = booking_id;
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
        private List<String> name;
        private List<String> date;
        private List<String> time;
        private List<Integer> purpose;
        private List<String> vehicleNo;
        public GuestRegistrationRequest(List<String> name,List<String> date,List<String> time, List<Integer> purpose , List<String> vehicleNo ){
            this.name = name;
            this.date = date;
            this.time = time;
            this.purpose = purpose;
            this.vehicleNo = vehicleNo;

        }
    }

    public class ComplaintRequest {
        private String language;
        private int category;
        private String description;
        private List<String> base64_image;
        private String locationCoordinates;

        public ComplaintRequest(String language, int category, String description, List<String> base64_image, String locationCoordinates) {
            this.language = language;
            this.category = category;
            this.description = description;
            this.base64_image = base64_image;
            this.locationCoordinates = locationCoordinates;
        }
    }
}
