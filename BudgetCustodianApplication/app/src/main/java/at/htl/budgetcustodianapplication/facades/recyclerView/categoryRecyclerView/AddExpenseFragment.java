package at.htl.budgetcustodianapplication.facades.recyclerView.categoryRecyclerView;

import android.content.Context;
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
public class AddExpenseFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    private EditText edDescription;
    private EditText edCostInput;
    private TextView tvExpenseCategory;
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);
        edDescription = (EditText) view.findViewById(R.id.etDescription);
        edCostInput = (EditText) view.findViewById(R.id.edCostInput);
        tvExpenseCategory = (TextView) view.findViewById(R.id.tvExpenseCategory);
        //tvExpenseCategory.setText(getArguments().getString("getCategory"));
        btnSaveExpense = (Button) view.findViewById(R.id.btnSaveExpense);



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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onAddExpenseForCategory();
    }
}
