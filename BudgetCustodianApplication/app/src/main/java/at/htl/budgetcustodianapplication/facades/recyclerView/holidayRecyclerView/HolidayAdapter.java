package at.htl.budgetcustodianapplication.facades.recyclerView.holidayRecyclerView;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import at.htl.budgetcustodianapplication.R;
import at.htl.budgetcustodianapplication.facades.ApplicationDatabase;
import at.htl.budgetcustodianapplication.facades.FirstFragment;
import at.htl.budgetcustodianapplication.facades.charts.ShowGraph;
import at.htl.budgetcustodianapplication.facades.entities.Holiday;

/**
 * Created by juliakajic on 07.01.18.
 */

public class HolidayAdapter extends RecyclerView.Adapter<HolidayViewHolder> {

    /*
    Prefixe private variables mit "m". Macht Google so, hab ich mir einfach schon angewohnt.
    Ist aber etwas umstritten. Würde man das durchziehen müsste man static sachen mit "s" prefixen.
     */
    private List<Holiday> mHolidays = new LinkedList<Holiday>();
    private Context mContext;
    private ApplicationDatabase mDb;
    private LayoutInflater mInflater;
    private HolydaysFragment.OnAddFragmentInteractionListener mListener = HolydaysFragment.mListener;
    private boolean isHolidayOver = false;


    /**
     * Constructor bekommt Context als param. Wird zur Zeit noch nicht im Adapter benötigt aber in Zukunft
     * vielleicht. Daher würd ichs machen.
     *
     * ApplicationDatabase als param mehr oder weniger optional. Einfach schöner - brauchst nicht getInstance
     * aufrufen, performance gain nicht wirklich da; getInstance returned ja eh einfach nur die Instance.
     *
     * LayoutInflater als param damit der beim onCreateViewHolder nicht jedesmal "geholt" werden muss.
     * Machen serh viele nicht so, ich mach es um die Performance zu verbessern.
     * @param context
     * @param db
     * @param inflater
     */
    public HolidayAdapter(Context context, ApplicationDatabase db, LayoutInflater inflater) {
        mContext = context;
        mDb = db;
        mInflater = inflater;
        mHolidays = mDb.holidayDao().getAllHolidays();
    }


    @Override
    public HolidayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = mInflater.inflate(android.R.layout.simple_list_item_2, parent,false);

        return new HolidayViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final HolidayViewHolder holder, int position) {
        final Holiday holiday = mHolidays.get(position);
        holder.setTitle(holiday);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentDate = new Date();

                if (currentDate.getTime() > holiday.getDateTo().getTime()){
                    isHolidayOver = true;
                }
                if (isHolidayOver){
                    mListener.OnHolidayIsOver(holiday);
                }
                else {
                    mListener.onHolidayClicked(holiday);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mHolidays.size();
    }
}
