package at.htl.budgetcustodianapplication.facades.recyclerView.categoryRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import at.htl.budgetcustodianapplication.facades.ApplicationDatabase;
import at.htl.budgetcustodianapplication.facades.entities.ExpensesCategory;
import at.htl.budgetcustodianapplication.facades.entities.Holiday;

/**
 * Created by Sasa on 18.01.2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private List<ExpensesCategory> mExpensesCategory = new LinkedList<ExpensesCategory>();
    private Context mContext;
    private ApplicationDatabase mDb;
    private LayoutInflater mInflater;

    public CategoryAdapter (Context context, ApplicationDatabase db, LayoutInflater inflater){
        mContext = context;
        mDb = db;
        mInflater = inflater;
        mExpensesCategory = mDb.expensesCategoryDao().getAllExpensesCategories();

    }


    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = mInflater.inflate(android.R.layout.simple_list_item_1, parent,false);
        return new CategoryViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        final ExpensesCategory expensesCategory = mExpensesCategory.get(position);
        holder.setTitle(expensesCategory);
    }

    @Override
    public int getItemCount() {
        return mExpensesCategory.size();
    }
}
