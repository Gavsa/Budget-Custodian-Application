package at.htl.budgetcustodianapplication.facades;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import at.htl.budgetcustodianapplication.R;

public class MainFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ImageButton btn_hotel;
    private ImageButton btn_food;
    private ImageButton btn_fuel;
    private ImageButton btn_other;
    private ImageButton btn_create;
    private ImageButton btn_googleMaps;

    private buttonFragmentCall mListener;

    public MainFragment() {
    }

    public static MainFragment newInstance(/*String param1, String param2*/) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        btn_hotel = (ImageButton) view.findViewById(R.id.btn_hotel);
        btn_fuel = (ImageButton) view.findViewById(R.id.btn_fuel);
        btn_other = (ImageButton) view.findViewById(R.id.btn_other);
        btn_food = (ImageButton) view.findViewById(R.id.btn_food);
        //btn_create = (ImageButton) view.findViewById(R.id.btn_create);
        btn_googleMaps = (ImageButton) view.findViewById(R.id.btn_GoogleMaps);

        btn_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.btn_hotelFragmentCall();
            }
        });
        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.btn_foodFragmentCall();
            }
        });
        btn_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.btn_otherFragmentCall();
            }
        });
        btn_googleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.btn_googleMapsFragmentCall();
            }
        });
        btn_fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.btn_fuelOnClickListener();
            }
        });
        

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof buttonFragmentCall) {
            mListener = (buttonFragmentCall) context;
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

    public interface buttonFragmentCall {
        void btn_hotelFragmentCall();
        void btn_foodFragmentCall();
        void btn_otherFragmentCall();
        void btn_googleMapsFragmentCall();
        void btn_fuelOnClickListener();
    }
}
