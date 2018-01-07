package at.htl.budgetcustodianapplication.facades.recyclerView;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import at.htl.budgetcustodianapplication.R;
import at.htl.budgetcustodianapplication.facades.entities.Holiday;

public class HolydaysFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton b_addHoliday;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnAddFragmentInteractionListener mListener;

    public HolydaysFragment() {
    }

    public static HolydaysFragment newInstance(String param1, String param2) {
        HolydaysFragment fragment = new HolydaysFragment();
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

        View view = inflater.inflate(R.layout.fragment_holydays, container, false);

        List<Holiday> holidayList = new LinkedList<Holiday>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        HolidayAdapter adapter = new HolidayAdapter(holidayList);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        b_addHoliday = (FloatingActionButton) view.findViewById(R.id.fab_addHoliday);

        b_addHoliday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.fabtn_AddFragmentCall();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddFragmentInteractionListener) {
            mListener = (OnAddFragmentInteractionListener) context;
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

    public interface OnAddFragmentInteractionListener {
        void fabtn_AddFragmentCall();
    }
}
