package at.htl.budgetcustodianapplication.facades.charts;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;

import at.htl.budgetcustodianapplication.R;
import at.htl.budgetcustodianapplication.facades.dao.ExpensesValueDao;
import at.htl.budgetcustodianapplication.facades.entities.ExpensesValue;
import at.htl.budgetcustodianapplication.facades.entities.Holiday;

public class ShowGraph extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public static OnShowGraphFragmentInteractionListener graphListener;

    public ShowGraph() {
    }

    public static ShowGraph newInstance(String param1, String param2) {
        ShowGraph fragment = new ShowGraph();
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
        View view = inflater.inflate(R.layout.fragment_show_graph, container, false);

        PieChart chart = (PieChart) view.findViewById(R.id.chart);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnShowGraphFragmentInteractionListener) {
            graphListener = (OnShowGraphFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        graphListener = null;
    }

    public interface OnShowGraphFragmentInteractionListener {
        void onHolidayOver(Holiday holiday);
    }
}
