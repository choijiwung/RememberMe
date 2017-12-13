package rememberme.io.rememberme.Day;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import rememberme.io.rememberme.Main.LeftMainActivity;
import rememberme.io.rememberme.R;

public class DayActivity extends AppCompatActivity {

//    APINetwork network;
//    ViewPager pager;
//    DayPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        Button btndone = (Button) findViewById(R.id.btndone);
        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLeftMainActivity = new Intent(getApplicationContext(), LeftMainActivity.class);
                startActivity(goToLeftMainActivity);
            }
        });
        LinearLayout dayListContainer =  (LinearLayout) findViewById(R.id.llDayListContainer);
        for(int i = 1 ; i<=3; i++) {
            appendItemView(dayListContainer, i);
            RelativeLayout itemView = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.activity_day_item, null);

        }


//        Intent intent = getIntent();
//        int tid = intent.getExtras().getInt("tid");
//
//        network = ApplicationController.getInstance().getApiNetwork();
//        pager = (ViewPager) findViewById(R.id.day_pager);

        //getDays(tid);

    }
    private void appendItemView(final LinearLayout dayListContainer, int h) {
        //
        RelativeLayout itemView = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.activity_day_item, null);
        // 파라미터로 받은 dayListContainer(레이아웃)에 addView()메소드를 통해 itemView를 넣는다.
        dayListContainer.addView(itemView);
        TextView textView = (TextView) itemView.findViewById(R.id.tvDayInput);
        textView.setText(h+"일차");

        final LinearLayout dayListItemContainer = (LinearLayout) itemView.findViewById(R.id.llDAyListItemContainer);

        //Day별 장소추가버튼
        Button addbtn = (Button) itemView.findViewById(R.id.btnAddSpot);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendSpotView(dayListItemContainer);
            }
        });
        //Day별 장소를 기본 2개씩 생성
        for (int j = 0; j < 1; j++) {
            appendSpotView(dayListItemContainer);
        }
    }

    private void appendSpotView(final LinearLayout dayListItemContainer){
        // spot_item.xml (장소 레이아웃) 인플레이터
        final RelativeLayout spotView = (RelativeLayout)  LayoutInflater.from(this).inflate(R.layout.activity_day_spot_item, null);

        //파라미터로 받은 dayListItemContainer(레이아웃)에 addView() 메소드를 통해 spotView를 넣는다.
        dayListItemContainer.addView(spotView);
        //Day별 장소제거버튼
        Button btnRemove = (Button) spotView.findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayListItemContainer.removeView(spotView);
            }
        });
    }

//    private void getDays(int tid) {
//        Call<DPageResult> dPageResultCall = network.getDPageResult(new Token(this).getKey(), tid, 1);
////        Call<DIndexResult> dIndexResultCall = network.getDIndexResult(new Token(this).getKey(), tid);
////        dIndexResultCall.enqueue(new Callback<DIndexResult>() {
//        dPageResultCall.enqueue(new Callback<DPageResult>() {
//            @Override
//            public void onResponse(Call<DPageResult> call, Response<DPageResult> response) {
//                if (response.isSuccessful()) {
//                    Log.i("Day", "Success / code : " + response.code());
//                    DPageResult dPageResult = response.body();
//                    adapter = new DayPagerAdapter(DayActivity.this, getSupportFragmentManager(), dPageResult.result);
//                    pager.setAdapter(adapter);
//                } else {
//                    Log.i("Day", "Fail / code : " + response.code());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<DPageResult> call, Throwable t) {
//                Log.i("Day", "errors : ".concat(t.getMessage()));
//            }
//        });
//    }
}
