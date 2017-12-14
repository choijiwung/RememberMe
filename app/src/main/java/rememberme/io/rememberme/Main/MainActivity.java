package rememberme.io.rememberme.Main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rememberme.io.rememberme.Network.APINetwork;
import rememberme.io.rememberme.Network.ApplicationController;
import rememberme.io.rememberme.R;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    MainPagerAdapter adapter;
    APINetwork network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        network = ApplicationController.getInstance().getApiNetwork();

        tabLayout = (TabLayout) findViewById(R.id.main_tab);
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);

        tabLayout.addTab(tabLayout.newTab().setText("여행목록"));
        tabLayout.addTab(tabLayout.newTab().setText("현재여행"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        adapter = new MainPagerAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Toast.makeText(this, "onResume Start", Toast.LENGTH_SHORT).show();
//        adapter.updateLeftPage();
        adapter.updateRightPage();
        adapter.notifyDataSetChanged();
        Log.i("Main", "resume check size : " + adapter.size);
    }

}
