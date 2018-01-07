package at.htl.budgetcustodianapplication.facades.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import at.htl.budgetcustodianapplication.R;
import at.htl.budgetcustodianapplication.facades.entities.Holiday;

/**
 * Created by juliakajic on 07.01.18.
 */

public class HolidayViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;
    public Holiday holiday;

    public HolidayViewHolder(View itemView, Holiday holiday) {
        super(itemView);

        this.textView = (TextView) itemView.findViewById(R.id.textViewHoliday);
        this.holiday = holiday;
    }

    public void setRecyclerHolidayText(){

    }
}
