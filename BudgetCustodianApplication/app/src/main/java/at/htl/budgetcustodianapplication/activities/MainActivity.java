package at.htl.budgetcustodianapplication.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import at.htl.budgetcustodianapplication.R;
import at.htl.budgetcustodianapplication.facades.ApplicationDatabase;
import at.htl.budgetcustodianapplication.facades.FirstFragment;
import at.htl.budgetcustodianapplication.facades.costPointsFragments.FoodFragment;
import at.htl.budgetcustodianapplication.facades.costPointsFragments.FuelFragment;
import at.htl.budgetcustodianapplication.facades.dao.ExpensesCategoryDao;
import at.htl.budgetcustodianapplication.facades.entities.ExpensesCategory;
import at.htl.budgetcustodianapplication.facades.entities.Holiday;
import at.htl.budgetcustodianapplication.facades.featureFragments.GoogleMapsFragment;
import at.htl.budgetcustodianapplication.facades.costPointsFragments.HotelFragment;
import at.htl.budgetcustodianapplication.facades.MainFragment;
import at.htl.budgetcustodianapplication.facades.costPointsFragments.OthersFragment;
import at.htl.budgetcustodianapplication.facades.recyclerView.AddHolidayFragment;
import at.htl.budgetcustodianapplication.facades.recyclerView.categoryRecyclerView.AddExpenseFragment;
import at.htl.budgetcustodianapplication.facades.recyclerView.categoryRecyclerView.CategoryFragment;
import at.htl.budgetcustodianapplication.facades.recyclerView.holidayRecyclerView.HolydaysFragment;


public class MainActivity extends AppCompatActivity implements MainFragment.buttonFragmentCall,
        HotelFragment.OnFragmentInteractionListener,
        FoodFragment.OnFragmentFoodInteractionListener,
        OthersFragment.OnFragmentInteractionListener,
        GoogleMapsFragment.OnFragmentInteractionListener,
        FuelFragment.OnFragmentInteractionListener,
        HolydaysFragment.OnAddFragmentInteractionListener,
        AddHolidayFragment.OnFragmentInteractionListener,
        FirstFragment.OnFragmentInteractionListener,
        CategoryFragment.OnFragmentInteractionListener,
        AddExpenseFragment.OnFragmentInteractionListener
{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fragment_container, new HolydaysFragment()).commit();
    }



    @Override
    public void btn_hotelFragmentCall() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new HotelFragment()).addToBackStack("back").commit();
    }

    @Override
    public void btn_foodFragmentCall() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new FoodFragment()).addToBackStack("back").commit();
    }

    @Override
    public void btn_otherFragmentCall() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new OthersFragment()).addToBackStack("back").commit();
    }

    @Override
    public void btn_googleMapsFragmentCall() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new GoogleMapsFragment()).addToBackStack("back").commit();
    }

    @Override
    public void btn_fuelOnClickListener() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new FuelFragment()).addToBackStack("back").commit();
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
    public void onAddHolidayFragmentInteraction() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new HolydaysFragment()).commit();
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void onAddExpenseForCategory(ExpensesCategory expensesCategory) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container,new AddExpenseFragment()).addToBackStack("back").commit();
    }

    @Override
    public void onAddExpenseForCategory() {

    }
}
