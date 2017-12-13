package rememberme.io.rememberme.Main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import rememberme.io.rememberme.R;

/**
 * Created by samsung on 2017-12-13.
 */

public class LeftMainActivity extends AppCompatActivity {
    RightMainFragment rightMainFragment;
    LeftMainFragment leftMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_main);
        LinearLayout actionbar_linear = (LinearLayout) findViewById(R.id.actionbar_linear);
        rightMainFragment = new RightMainFragment();
        leftMainFragment = new LeftMainFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_container, rightMainFragment).commit();

        // 이제 탭버튼을 추가

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("여행목록"));
        tabs.addTab(tabs.newTab().setText("현재여행"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {  // 탭버튼을 하나 선택했을 때 자동으로 선택되는 콜벡메소드
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                if(position==0){
                    selected = rightMainFragment;

                }else if (position == 1) {
                    selected = leftMainFragment;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container,selected).commit(); // 이미 선택되었기 때문에, replace를 사용하며, 뜻은 container 안에 선택된 selected를 넣어주라
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
