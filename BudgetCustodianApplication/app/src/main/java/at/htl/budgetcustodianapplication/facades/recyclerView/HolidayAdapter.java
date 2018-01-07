package at.htl.budgetcustodianapplication.facades.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import at.htl.budgetcustodianapplication.facades.entities.Holiday;

/**
 * Created by juliakajic on 07.01.18.
 */

public class HolidayAdapter extends RecyclerView.Adapter<HolidayViewHolder> {

    private List<Holiday> holidays;

    public HolidayAdapter(List<Holiday> list){
        this.holidays = list;
    }

    @Override
    public HolidayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(HolidayViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
