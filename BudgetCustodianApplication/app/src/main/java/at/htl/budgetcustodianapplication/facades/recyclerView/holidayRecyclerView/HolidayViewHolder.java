package at.htl.budgetcustodianapplication.facades.recyclerView.holidayRecyclerView;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import at.htl.budgetcustodianapplication.facades.entities.Holiday;

/**
 * Created by josipkajic on 07.01.18.
 */

public class HolidayViewHolder extends RecyclerView.ViewHolder {

    public TextView tvName;
    public TextView tvEndDate;
    public Holiday holiday;

    public HolidayViewHolder(View itemView) {
        super(itemView);

        tvName = (TextView) itemView.findViewById(android.R.id.text1);
        tvEndDate = (TextView) itemView.findViewById(android.R.id.text2);
        tvName.setTextSize(24);
        tvName.setPadding(5,5,5,5);
        tvEndDate.setPadding(5,5,5,5);
        tvEndDate.setTextSize(20);
        tvName.setTextColor(Color.BLACK);
        tvEndDate.setTextColor(Color.GRAY);
    }

    public void setTitle(Holiday holiday){
        Date currentDate = new Date();
        Date date = new GregorianCalendar(currentDate.getYear(), currentDate.getMonth(), currentDate.getDay()-1).getTime();
        if (date.getTime() > holiday.getDateTo().getTime()){
            tvName.setTextColor(Color.RED);
            tvEndDate.setText("Holiday is over");
        }
        else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            String check1 = dateFormat.format(holiday.getDateFrom());
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd.MM.yyyy");
            String check2 = dateFormat2.format(holiday.getDateTo());
            tvEndDate.setText("From " + check1 + " to " + check2);
        }
        tvName.setText(holiday.getTitle());

    }


}
