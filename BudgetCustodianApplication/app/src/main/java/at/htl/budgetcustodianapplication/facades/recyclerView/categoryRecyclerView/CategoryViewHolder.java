package at.htl.budgetcustodianapplication.facades.recyclerView.categoryRecyclerView;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import at.htl.budgetcustodianapplication.facades.entities.ExpensesCategory;

/**
 * Created by Sasa on 18.01.2018.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder {


    public TextView tvName;
    public ExpensesCategory expensesCategory;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(android.R.id.text1);
        tvName.setTextColor(Color.BLACK);
    }

    public void setTitle(ExpensesCategory expensesCategory){
        tvName.setText(expensesCategory.getCategory());
    }

}
