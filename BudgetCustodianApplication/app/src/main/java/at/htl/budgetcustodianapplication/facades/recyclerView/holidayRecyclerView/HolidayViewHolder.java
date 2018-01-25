package at.htl.budgetcustodianapplication.facades.recyclerView.holidayRecyclerView;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import at.htl.budgetcustodianapplication.facades.entities.Holiday;

/**
 * Created by juliakajic on 07.01.18.
 */

public class HolidayViewHolder extends RecyclerView.ViewHolder {

    public TextView tvName;
    public Holiday holiday;




    public HolidayViewHolder(View itemView) {
        super(itemView);

        tvName = (TextView) itemView.findViewById(android.R.id.text1);
        tvName.setTextColor(Color.BLACK);
    }

    public void setTitle(Holiday holiday){
        tvName.setText(holiday.getTitle());
    }


}
