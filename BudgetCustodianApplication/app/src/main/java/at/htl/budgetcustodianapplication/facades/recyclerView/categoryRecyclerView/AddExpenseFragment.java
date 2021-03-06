package at.htl.budgetcustodianapplication.facades.recyclerView.categoryRecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import at.htl.budgetcustodianapplication.R;
import at.htl.budgetcustodianapplication.facades.entities.ExpensesCategory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddExpenseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddExpenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddExpenseFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private EditText edDescription;
    private EditText edAmount;
    private ExpensesCategory expensesCategoryToShow;
    private Button btnSaveExpense;

    public static OnFragmentInteractionListener mListener;

    public AddExpenseFragment() {
        // Required empty public constructor
    }


    public static AddExpenseFragment newInstance(ExpensesCategory param1) {
        AddExpenseFragment fragment = new AddExpenseFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            expensesCategoryToShow = (ExpensesCategory) getArguments().getSerializable(ARG_PARAM1);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);

        edDescription = (EditText) view.findViewById(R.id.edDescription);
        edAmount = (EditText) view.findViewById(R.id.edAmount);
        btnSaveExpense = (Button) view.findViewById(R.id.btnSaveExpense);

        btnSaveExpense.setOnClickListener(this);

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
        if (view.getId() == btnSaveExpense.getId()){
            //region Description Validation
            if (String.valueOf(edDescription.getText()).equals(null) || String.valueOf(edDescription.getText()).equals("")) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setMessage("Description cannot be empty!!");
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

            //region Amount Validation
            else if (String.valueOf(edAmount.getText()).equals(null) || String.valueOf(edAmount.getText()).equals("")) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setMessage("Amount cannot be empty!");
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
            } else if (Integer.parseInt(String.valueOf(edAmount.getText())) <= 0) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setMessage("Amount has to be bigger than 0!");
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
        }
    }

    public interface OnFragmentInteractionListener {
        void onAddExpenseForCategory();
    }
}
