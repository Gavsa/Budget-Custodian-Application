package at.htl.budgetcustodianapplication.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import at.htl.budgetcustodianapplication.R;
import at.htl.budgetcustodianapplication.facades.FoodFragment;
import at.htl.budgetcustodianapplication.facades.FuelFragment;
import at.htl.budgetcustodianapplication.facades.GoogleMapsFragment;
import at.htl.budgetcustodianapplication.facades.HotelFragment;
import at.htl.budgetcustodianapplication.facades.MainFragment;
import at.htl.budgetcustodianapplication.facades.OthersFragment;


public class MainActivity extends AppCompatActivity implements MainFragment.buttonFragmentCall,
        HotelFragment.OnFragmentInteractionListener,
        FoodFragment.OnFragmentFoodInteractionListener,
        OthersFragment.OnFragmentInteractionListener,
        GoogleMapsFragment.OnFragmentInteractionListener,
        FuelFragment.OnFragmentInteractionListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fragment_container, new MainFragment()).commit();

        /*FirebaseAuth auth = FirebaseAuth.getInstance();
        Intent intent = new Intent(this, GoogleSignInActivity.class);
        auth.signOut();
        if (auth.getCurrentUser() == null) {
            startActivity(intent);
            onPause();
        } else if (auth.getCurrentUser() != null)
            Toast.makeText(this, "Already authenticated", Toast.LENGTH_LONG).show();

        Log.e("Startup", "Im here now");*/
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
    public void onFragmentInteraction(Uri uri) {

    }
}
