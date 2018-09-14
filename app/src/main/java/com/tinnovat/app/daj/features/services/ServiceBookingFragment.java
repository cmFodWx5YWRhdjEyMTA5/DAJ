package com.tinnovat.app.daj.features.services;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import com.squareup.picasso.Picasso;
import com.tinnovat.app.daj.BaseFragment;
import com.tinnovat.app.daj.R;
import com.tinnovat.app.daj.data.network.ApiClient;
import com.tinnovat.app.daj.data.network.ApiInterface;
import com.tinnovat.app.daj.data.network.model.RequestParams;
import com.tinnovat.app.daj.data.network.model.Service;
import com.tinnovat.app.daj.data.network.model.ServiceAvailableDate;
import com.tinnovat.app.daj.data.network.model.ServiceSlots;
import com.tinnovat.app.daj.data.network.model.SuccessResponseModel;
import com.tinnovat.app.daj.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ServiceBookingFragment extends BaseFragment implements ChooseDateAdapter.DateAdapterListener {

    private OnFragmentInteractionListener mListener;
    private double lat;
    private double lng;
    private Service mServiceItem;
    private String mSelectedDay;
    private int mCategoryId;

    MaterialCalendarView cal;
    TextView monthTitle;
    TextView locationText;
    ImageView servicesImage;
    Button buttonBook;

    String selectedDate = null;

    private List<Integer> mSelectedTimeSlots = new ArrayList<>();
    private RecyclerView recyclerView;
    private EditText noOfGuest;
    private EditText commentBox;
    private ChooseDateAdapter mAdapter;


    public ServiceBookingFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(Service item, int categoryId,String day) {
        ServiceBookingFragment fragment = new ServiceBookingFragment();
        fragment.mServiceItem = item;
        fragment.mCategoryId = categoryId;
        fragment.mSelectedDay = day;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initialiseViews(View view) {
        cal = view.findViewById(R.id.calendarView);
        monthTitle = view.findViewById(R.id.monthTitle);
        locationText = view.findViewById(R.id.locationText);
        servicesImage = view.findViewById(R.id.servicesImage);
        buttonBook = view.findViewById(R.id.button_book);
        buttonBook.setOnClickListener(this);
        recyclerView = view.findViewById(R.id.recycler_view);

        noOfGuest = view.findViewById(R.id.noOfGuest);
        commentBox = view.findViewById(R.id.commentBox);




        setCalender();

        setData();
    }

    @Override
    public void initialiseEventListners() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_service_booking, container, false);

        initialiseViews(view);
        //mListener.setTitle(getString(R.string.service_booking));
        mListener.setTitle(String.format(getResources().getString(R.string.header_formatter), mServiceItem.getName(), getResources().getString(R.string.booking)));

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void setDateCategory(List<ServiceAvailableDate> serviceAvailableDate) {
        mAdapter = new ChooseDateAdapter(getActivity(), this, serviceAvailableDate);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);

        if (mAdapter.getItemCount() == 0){
            showDilog(getActivity().getResources().getString(R.string.slots_filled),false);

            buttonBook.setEnabled(false);
            buttonBook.setText(getActivity().getResources().getString(R.string.fully_booked));
            buttonBook.setBackgroundColor(getActivity().getResources().getColor(R.color.gray));
        }else {
            buttonBook.setEnabled(true);
            buttonBook.setText(getActivity().getResources().getString(R.string.book));
            buttonBook.setBackgroundColor(getActivity().getResources().getColor(R.color.greenText));
        }
    }

    private void setData() {

        locationText.setText(mServiceItem.getName());
        Picasso.get().load(mServiceItem.getServiceImages()).into(servicesImage);

       // setDateCategory(mServiceItem.getServiceAvailableDates());
        if (mSelectedDay != null){
            fetchServiceAvailableSlots(mSelectedDay);
        }else {
            fetchServiceAvailableSlots(CommonUtils.getInstance().getDate(CalendarDay.today().getCalendar()));
        }


    }

    private void setCalender() {

        cal.state().edit()
                .setMinimumDate(CalendarDay.from(CalendarDay.today().getYear(), CalendarDay.today().getMonth(), CalendarDay.today().getDay()))
                .commit();

        if (mSelectedDay != null){
            cal.setSelectedDate(CommonUtils.getInstance().convertToCalender(mSelectedDay));
            cal.setCurrentDate(CommonUtils.getInstance().convertToCalender(mSelectedDay));

            selectedDate = mSelectedDay;
        }else {
            cal.setSelectedDate(CalendarDay.today());
        }

        cal.setSelected(true);

        if (getLanguage()) {
            cal.setRightArrowMask(ContextCompat.getDrawable(getActivity(), R.drawable.arrow_right));
            cal.setLeftArrowMask(ContextCompat.getDrawable(getActivity(), R.drawable.arrow_left));
        } else {
            cal.setRightArrowMask(ContextCompat.getDrawable(getActivity(), R.drawable.arrow_left));
            cal.setLeftArrowMask(ContextCompat.getDrawable(getActivity(), R.drawable.arrow_right));
        }

        String[] weekDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        cal.setWeekDayLabels(weekDays);

        DayFormatter day = new DayFormatter() {
            @NonNull
            @Override
            public String format(@NonNull CalendarDay day) {
                return "" + day.getDay();
            }
        };
        cal.setTitleMonths(months);
        cal.setDayFormatter(day);
        cal.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                monthTitle.setVisibility(View.INVISIBLE);
                cal.setHeaderTextAppearance(R.style.CalenderViewCustomWeekHeading);
            }
        });

        cal.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                monthTitle.setVisibility(View.VISIBLE);
                cal.setHeaderTextAppearance(R.style.CalenderViewCustomWeekHeading1);
                //  monthTitle.setText(""+getCurrentMonthWeek(date.getMonth())+" "+date.getYear());
                monthTitle.setText(CommonUtils.getInstance().getMonthWithYear(date.getCalendar()));
                selectedDate = CommonUtils.getInstance().getDate(date.getCalendar());

                fetchServiceAvailableSlots(selectedDate);

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_book:

                if (mSelectedTimeSlots.isEmpty()) {
                    showMessage(getResources().getString(R.string.select_time));
                } else {
                    fetchData();
                }

                break;
        }
    }

    private void fetchServiceAvailableSlots(String selectedDate) {
        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        //ApiInterface apiInterface = ApiClient.getAuthClient(appPreferanceStore.getToken()).create(ApiInterface.class);
        Call<ServiceSlots> call = apiInterface.getAvailableTimeSlots(mCategoryId, mServiceItem.getId(), selectedDate);
        call.enqueue(new Callback<ServiceSlots>() {
            @Override
            public void onResponse(@NonNull Call<ServiceSlots> call, Response<ServiceSlots> response) {
                endLoading();
                if (response.body() != null && response.body().getServiceAvailableDates() != null) {

                    setDateCategory(response.body().getServiceAvailableDates());
                }
            }

            @Override
            public void onFailure(Call<ServiceSlots> call, Throwable t) {
                endLoading();
            }
        });
    }

    private void fetchData() {

        if (selectedDate == null) {
            selectedDate = CommonUtils.getInstance().getDate(CalendarDay.today().getCalendar());
        }

        if ((noOfGuest.getText().toString().isEmpty())) {
            doBooking(mCategoryId, mServiceItem.getId(), selectedDate, mSelectedTimeSlots, 0, commentBox.getText().toString());
        } else if (commentBox.getText().toString().isEmpty()) {
            doBooking(mCategoryId, mServiceItem.getId(), selectedDate, mSelectedTimeSlots, Integer.parseInt(noOfGuest.getText().toString()), " ");
        } else if ((noOfGuest.getText().toString().isEmpty()) && commentBox.getText().toString().isEmpty()) {
            doBooking(mCategoryId, mServiceItem.getId(), selectedDate, mSelectedTimeSlots, 0, " ");

        } else {
            doBooking(mCategoryId, mServiceItem.getId(), selectedDate, mSelectedTimeSlots, Integer.parseInt(noOfGuest.getText().toString()), commentBox.getText().toString());
        }
    }

    private void showDilog(String message, final Boolean error){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (error){
                            dialog.cancel();
                            getActivity().onBackPressed();
                        }else {
                            dialog.cancel();
                        }

                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void doBooking(int category_id , int service_id, String date, List<Integer> timeSlots, int guest_no, String comments){

        startLoading();

        ApiInterface apiInterface = ApiClient.getAuthClient(getToken()).create(ApiInterface.class);
        RequestParams.ServiceBookingRequest serviceBookingRequest = new RequestParams().new ServiceBookingRequest(category_id , service_id, date, timeSlots, guest_no, comments);

        Call<SuccessResponseModel> call = apiInterface.serviceBooking(serviceBookingRequest);
        call.enqueue(new Callback<SuccessResponseModel>() {
            @Override
            public void onResponse(Call<SuccessResponseModel> call, Response<SuccessResponseModel> response) {
                endLoading();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        showDilog(response.body().getMessage(),true);
                        mAdapter.notifyDataSetChanged();
                    } else {
                        showDilog(response.body().getMessage(),true);
                    }
                }else {
                }
            }

            @Override
            public void onFailure(Call<SuccessResponseModel> call, Throwable t) {
                endLoading();
                showDilog(getResources().getString(R.string.try_again_later),true);
            }
        });
    }

    @Override
    public void onDateSelected(List<Integer> selectedTimeSlots) {
        mSelectedTimeSlots = selectedTimeSlots;
    }

    public interface OnFragmentInteractionListener {

        void setTitle(String title);
    }
}
