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

    private ImageButton btnHotel;
    private ImageButton btnVerpflegung;
    private ImageButton btnTreibstoff;
    private ImageButton btnSonstiges;
    private ImageButton btnCreate;
    private ImageButton btnGoogleMaps;

    private InitialFragmentCall mListener;

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
        btnHotel = (ImageButton) view.findViewById(R.id.btnHotel);
        btnTreibstoff = (ImageButton) view.findViewById(R.id.btnTreibstoff);
        btnSonstiges = (ImageButton) view.findViewById(R.id.btnTreibstoff);
        btnVerpflegung = (ImageButton) view.findViewById(R.id.btnVerpflegung);
        btnCreate = (ImageButton) view.findViewById(R.id.btnCreate);
        btnGoogleMaps = (ImageButton) view.findViewById(R.id.btnGoogleMaps);

        btnHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.initialFragmentCall();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InitialFragmentCall) {
            mListener = (InitialFragmentCall) context;
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

    public interface InitialFragmentCall {
        void initialFragmentCall();
    }
}
