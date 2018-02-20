package at.htl.budgetcustodianapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import at.htl.budgetcustodianapplication.R;
import at.htl.budgetcustodianapplication.facades.FirstFragment;
import at.htl.budgetcustodianapplication.facades.charts.ShowGraph;
import at.htl.budgetcustodianapplication.facades.entities.ExpensesCategory;
import at.htl.budgetcustodianapplication.facades.entities.Holiday;
import at.htl.budgetcustodianapplication.facades.recyclerView.AddHolidayFragment;
import at.htl.budgetcustodianapplication.facades.recyclerView.categoryRecyclerView.AddExpenseFragment;
import at.htl.budgetcustodianapplication.facades.recyclerView.categoryRecyclerView.CategoryFragment;
import at.htl.budgetcustodianapplication.facades.recyclerView.holidayRecyclerView.HolydaysFragment;


public class MainActivity extends AppCompatActivity implements
        HolydaysFragment.OnAddFragmentInteractionListener,
        AddHolidayFragment.OnFragmentInteractionListener,
        FirstFragment.OnFragmentInteractionListener,
        CategoryFragment.OnFragmentInteractionListener,
        AddExpenseFragment.OnFragmentInteractionListener,
        ShowGraph.OnShowGraphFragmentInteractionListener
{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fragment_container, new HolydaysFragment()).commit();
    }

    @Override
    public void fabtn_AddFragmentCall() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new AddHolidayFragment()).addToBackStack("back").commit();
    }

    @Override
    public void onHolidayClicked(Holiday holiday) {
        //FirstFragment firstFragment = FirstFragment.newInstance(holiday);
        //FragmentManager manager = getSupportFragmentManager();
        //manager.beginTransaction().replace(R.id.fragment_container,firstFragment).addToBackStack("back").commit();

        Intent intent = new Intent(this,NavDrawer.class);
        startActivity(intent);
    }

    @Override
    public void OnHolidayIsOver(Holiday holiday) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new ShowGraph()).commit();
    }

    @Override
    public void onCheckListButtonClicked() {
        Intent intent = new Intent(MainActivity.this, CheckListActivity.class);
        startActivity(intent);
    }


    @Override
    public void onAddHolidayFragmentInteraction() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new HolydaysFragment()).commit();
    }

    @Override
    public void onAddExpenseForCategory(ExpensesCategory expensesCategory) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container,new AddExpenseFragment()).addToBackStack("back").commit();
    }

    @Override
    public void onAddExpenseForCategory() {

    }

    @Override
    public void onHolidayOver() {

    }
}
