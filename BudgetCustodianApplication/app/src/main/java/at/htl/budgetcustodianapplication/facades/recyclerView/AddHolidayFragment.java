package at.htl.budgetcustodianapplication.facades.recyclerView;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;

import at.htl.budgetcustodianapplication.R;
import at.htl.budgetcustodianapplication.facades.ApplicationDatabase;
import at.htl.budgetcustodianapplication.facades.dao.HolidayDao;
import at.htl.budgetcustodianapplication.facades.entities.Holiday;


public class AddHolidayFragment extends Fragment implements View.OnClickListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText nameOfHoliday;
    private TextView dateFrom;
    private TextView dateTo;
    private EditText amount;
    private Button saveBtn;

    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;

    private OnFragmentInteractionListener mListener;



    public AddHolidayFragment() {
    }

    public static AddHolidayFragment newInstance(String param1, String param2) {
        AddHolidayFragment fragment = new AddHolidayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_add_holiday, container, false);

        nameOfHoliday = (EditText) view.findViewById(R.id.nameOfHoliday);
        dateFrom = (TextView) view.findViewById(R.id.dateFrom);
        dateTo = (TextView) view.findViewById(R.id.dateTo);
        amount = (EditText) view.findViewById(R.id.amount);
        saveBtn = (Button) view.findViewById(R.id.btn_saveHoliday);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

        dateFrom.setOnClickListener(this);
        InputMethodManager inputManager = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(dateFrom.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        saveBtn.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this.getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                dateFrom.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        dateTo.setOnClickListener(this);
        Calendar newCalendar2 = Calendar.getInstance();
        toDatePickerDialog = new DatePickerDialog(this.getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                dateTo.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        InputMethodManager inputManager2 = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(dateTo.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

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

    @Override
    public void onClick(View view) {
        if (view == this.dateFrom)
        {
            fromDatePickerDialog.show();

        } else if (view == this.dateTo){

            toDatePickerDialog.show();

        } else if(view == this.saveBtn)
        {
            ApplicationDatabase db = ApplicationDatabase.getInstance(getActivity().getApplicationContext());
            Holiday holiday = new Holiday();
            if ((this.amount.getContext() != null) && (this.dateFrom.getContext() != null) && (this.dateTo.getContext() != null) && (this.nameOfHoliday.getContext() != null))
            {
                holiday.setBudget(Double.parseDouble(String.valueOf(this.amount.getText())));

                try {
                    Date temp1 = dateFormatter.parse(String.valueOf(this.dateFrom.getText()));
                    Date temp2 = dateFormatter.parse(String.valueOf(this.dateTo.getText()));

                    do{
                        if(temp1.getTime() < temp2.getTime()){
                            try {
                                holiday.setDateFrom(dateFormatter.parse(String.valueOf(this.dateFrom.getText())));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                holiday.setDateFrom(dateFormatter.parse(String.valueOf(this.dateTo.getText())));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(this.getContext());
                            builder1.setMessage("Your Last Day of the Vacation is smaller than your first day!");
                            builder1.setCancelable(true);
                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                        }
                    } while(temp1.getTime() < temp2.getTime());

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                holiday.setTitle(String.valueOf(this.nameOfHoliday.getText()));
                db.holidayDao().insertOneHoliday(holiday);
                mListener.onAddHolidayFragmentInteraction();

            } else{
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this.getContext());
                builder1.setMessage("You have to fill out all fields!");
                builder1.setCancelable(true);
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        }
    }

    public interface OnFragmentInteractionListener {
        void onAddHolidayFragmentInteraction();
    }
}
