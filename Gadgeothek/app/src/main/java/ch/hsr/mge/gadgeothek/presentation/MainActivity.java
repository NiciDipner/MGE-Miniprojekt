package ch.hsr.mge.gadgeothek.presentation;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ch.hsr.mge.gadgeothek.R;

public class MainActivity extends AppCompatActivity implements ItemSelectionListener {

    PagerAdapter mainViewPagerAdapter;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(mainViewPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onItemSelected(int position) {

    }
}
