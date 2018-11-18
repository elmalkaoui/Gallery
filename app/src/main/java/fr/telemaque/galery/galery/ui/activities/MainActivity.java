package fr.telemaque.galery.galery.ui.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import fr.telemaque.galery.galery.R;
import fr.telemaque.galery.galery.ui.fragments.CarouselGalleryFragment;
import fr.telemaque.galery.galery.ui.fragments.ListGalleryFragment;


/*
*MainActivity is lunching point of the App, within there is
*BottomNavigationView for navigate between fragments
*ListGalleryFragment and CarouselFGalleryFragment
*/
public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;
    private ListGalleryFragment mListGalleryFragment;
    private CarouselGalleryFragment mCarouselGalleryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar();
        initViews();
        initFragments();
        initListeners();
        showFragment(mListGalleryFragment);

    }

    private void initFragments() {
        mListGalleryFragment = ListGalleryFragment.newInstance();
        mCarouselGalleryFragment = CarouselGalleryFragment.newInstance();
    }

    private void initViews() {
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
    }

    /*
    *adding listener to BottomNavigationView to handle displaying appropriate fragment
    *based on user interaction
    */
    private void initListeners() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.list) {
                    showFragment(mListGalleryFragment);
                    return true;
                } else {
                    showFragment(mCarouselGalleryFragment);
                    return true;
                }
            }
        });
    }

    //display fragment passed in argument
    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }


}