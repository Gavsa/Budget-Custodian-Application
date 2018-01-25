package at.htl.budgetcustodianapplication.facades;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import at.htl.budgetcustodianapplication.R;
import at.htl.budgetcustodianapplication.activities.MapsActivity;
import at.htl.budgetcustodianapplication.facades.entities.Holiday;

public class FirstFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";

    private Holiday holidayToShow;
    private View mVMaps;

    private OnFragmentInteractionListener mListener;

    public FirstFragment() {
    }

    /**
     * Bezüglich entitäten. I würd halt ane fia holiday machen, ane fia categories.
     * bei holiday da gibts halt den namen und an ort und vu wann bis wann.
     * und halt a verweis auf a kategorien. kategorien würd i unique nachm namen machen.
     *
     * du brauchast dann eig a many to many beziehung zw kategorie und holiday und um de aufzulösen
     * a dritte tabelle. CategoryHoliday oder so. de beinhaltet dann immer de kosten für a kategorie und
     * den jeweiligen urlaub.
     *
     * sprich:
     *
     * Holiday -> Long id, String name, String city (vllt a String country), Date start, Date end, List<CategoryHoliday> categoriesWithCosts
     * CategoryHoliday -> Long id, Long holidayId, Category category, Double costs
     * Category -> String name
     *
     * I glaub so würds passen bin ma aber ned sicher^^ I was ned vllt gehts a einfacher und schöner. Da i mit room nu ned so viel erfahrung
     * hab leider kann i dir de many to many ned jz ausm stehgreif sogn :(. I würd da aba des empfehlen:
     *
     * https://android.jlelse.eu/android-architecture-components-room-relationships-bf473510c14a
     *
     * Graphen ohne daten puuh I don't think so aber hab de library erst einmal verwendet^^
     *
     * Zu deina Datumswahlfrage: https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
     * @param param1
     * @return
     */

    public static FirstFragment newInstance(Holiday param1) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            holidayToShow = (Holiday) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        mVMaps = rootView.findViewById(R.id.view_maps);
        mVMaps.setOnClickListener(this);
        return rootView;
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
        //id is ja de unique id de im R.java file steht
        //des umleiten auf de chart detail ansicht überlass i dir dann ^^ sollte aber relativ analog sein.
        if (view.getId() == mVMaps.getId()) {
            Intent intent = new Intent(getActivity(), MapsActivity.class);
            startActivity(intent);
        }
    }


    public interface OnFragmentInteractionListener {
        void onHolidayClicked(Holiday holiday);
    }
}
