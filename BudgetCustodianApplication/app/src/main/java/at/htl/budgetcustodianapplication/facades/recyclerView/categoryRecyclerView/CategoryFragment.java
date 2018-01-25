package at.htl.budgetcustodianapplication.facades.recyclerView.categoryRecyclerView;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import at.htl.budgetcustodianapplication.R;
import at.htl.budgetcustodianapplication.facades.ApplicationDatabase;
import at.htl.budgetcustodianapplication.facades.entities.ExpensesCategory;


public class CategoryFragment extends Fragment {

    RecyclerView recyclerView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CategoryFragment() {
    }

    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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
        ApplicationDatabase db = ApplicationDatabase.getInstance(getActivity().getApplicationContext());
        db.expensesCategoryDao().insertOneExpenseCategory(new ExpensesCategory("Hotel"));
        db.expensesCategoryDao().insertOneExpenseCategory(new ExpensesCategory("Essen"));
        db.expensesCategoryDao().insertOneExpenseCategory(new ExpensesCategory("Freizeitaktivität"));

        View view = inflater.inflate(R.layout.fragment_recyclerview_category, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.category_recyclerview);
        recyclerView.setHasFixedSize(true);


        CategoryAdapter adapter = new CategoryAdapter(getActivity().getApplicationContext(),db,getLayoutInflater());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        void onFragmentInteraction(Uri uri);
    }
}
