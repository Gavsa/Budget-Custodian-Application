package at.htl.budgetcustodianapplication.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import at.htl.budgetcustodianapplication.R;
import at.htl.budgetcustodianapplication.facades.HotelFragment;
import at.htl.budgetcustodianapplication.facades.MainFragment;


public class MainActivity extends AppCompatActivity implements MainFragment.InitialFragmentCall, HotelFragment.OnFragmentInteractionListener {

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
    public void initialFragmentCall() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new HotelFragment()).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
