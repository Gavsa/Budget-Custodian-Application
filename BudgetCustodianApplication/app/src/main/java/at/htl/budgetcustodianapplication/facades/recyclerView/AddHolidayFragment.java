package at.htl.budgetcustodianapplication.facades.recyclerView;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
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
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

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
    private EditText amount;
    private Button saveBtn;
    private Button dateFromBtn;
    private Button dateToBtn;
    private TextView dateFrom;
    private TextView dateTo;


    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;

    private Date currentDate = new Date();
    Date date = new GregorianCalendar(currentDate.getYear(), currentDate.getMonth(), currentDate.getDay()-1).getTime();


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
        amount = (EditText) view.findViewById(R.id.amount);
        saveBtn = (Button) view.findViewById(R.id.btn_saveHoliday);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        dateFromBtn = (Button) view.findViewById(R.id.fromDateBtn);
        dateToBtn = (Button) view.findViewById(R.id.toDateBtn);
        dateFrom = (TextView) view.findViewById(R.id.dateFromTextView);
        dateTo = (TextView) view.findViewById(R.id.dateToTextView);


        dateFromBtn.setOnClickListener(this);
        InputMethodManager inputManager = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(dateFromBtn.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        saveBtn.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this.getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                dateFrom.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        dateToBtn.setOnClickListener(this);

        InputMethodManager inputManager2 = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager2.hideSoftInputFromWindow(dateToBtn.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        Calendar newCalendar2 = Calendar.getInstance();
        toDatePickerDialog = new DatePickerDialog(this.getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                dateTo.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar2.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

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
        try {
            if (view == this.dateFromBtn) {
                fromDatePickerDialog.show();

            } else if (view == this.dateToBtn) {

                toDatePickerDialog.show();

            } else if (view == this.saveBtn) {
                ApplicationDatabase db = ApplicationDatabase.getInstance(getActivity().getApplicationContext());
                Holiday holiday = new Holiday();

                //region Holiday Validation
                if (String.valueOf(nameOfHoliday.getText()).equals(null) || String.valueOf(nameOfHoliday.getText()).equals("")) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                    builder1.setMessage("Holidayname cannot be empty!!");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
                //endregion

                //region Budget Validation
                else if (String.valueOf(amount.getText()).equals(null) || String.valueOf(amount.getText()).equals("")) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                    builder1.setMessage("Budget cannot be empty!");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                } else if (Integer.parseInt(String.valueOf(amount.getText())) <= 0) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                    builder1.setMessage("Budget has to be bigger than 0!");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
                //endregion

                //region Dates Validation
                else if (String.valueOf(dateFrom.getText()).equals(null) || String.valueOf(dateFrom.getText()).equals("") || String.valueOf(dateTo.getText()).equals(null) || String.valueOf(dateTo.getText()).equals("")) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                    builder1.setMessage("Datefields cannot be empty!");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
                if(dateFormatter.parse(dateFrom.getText().toString()).getTime() < date.getTime()){
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                    builder2.setMessage("The From-Date can't be smaller than the Current-Date!");
                    builder2.setCancelable(true);

                    builder2.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener()

                            {
                                public void onClick (DialogInterface dialog,int id){
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert12 = builder2.create();
                    alert12.show();
                }
                else if (dateFormatter.parse(dateTo.getText().toString()).getTime() < date.getTime()){
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                    builder2.setMessage("The To-Date can't be smaller than the Current-Date!");
                    builder2.setCancelable(true);

                    builder2.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener()

                            {
                                public void onClick (DialogInterface dialog,int id){
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert12 = builder2.create();
                    alert12.show();
                }
                else if(dateFormatter.parse(dateTo.getText().toString()).getTime() < dateFormatter.parse(dateFrom.getText().toString()).getTime()){
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                    builder2.setMessage("The To-Date can't be smaller than the From-Date!");
                    builder2.setCancelable(true);

                    builder2.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener()

                            {
                                public void onClick (DialogInterface dialog,int id){
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert12 = builder2.create();
                    alert12.show();
                }

                //endregion

                else {
                    try {
                        holiday.setDateFrom(dateFormatter.parse(String.valueOf(this.dateFrom.getText())));
                        holiday.setDateTo(dateFormatter.parse(String.valueOf(this.dateTo.getText())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    holiday.setBudget(Double.parseDouble(String.valueOf(this.amount.getText())));
                    holiday.setTitle(String.valueOf(this.nameOfHoliday.getText()));
                    db.holidayDao().insertOneHoliday(holiday);

                    mListener.onAddHolidayFragmentInteraction();
                }
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    public interface OnFragmentInteractionListener {
        void onAddHolidayFragmentInteraction();
    }
}
