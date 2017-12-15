package rememberme.io.rememberme.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import rememberme.io.rememberme.Day.DayActivity;
import rememberme.io.rememberme.Network.APINetwork;
import rememberme.io.rememberme.Network.ApplicationController;
import rememberme.io.rememberme.Network.Token;
import rememberme.io.rememberme.R;
import rememberme.io.rememberme.Trip.Results.TIndexResult;
import rememberme.io.rememberme.Trip.Trip;
import rememberme.io.rememberme.Trip.TripActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by samsung on 2017-11-17.
 */
// 여행목록 탭
public class RightMainFragment extends Fragment {

    APINetwork network;

    RecyclerView recyclerView;
    RightMainAdapter adapter;
    FloatingActionButton fab;

    ArrayList<Trip> arrayList = new ArrayList<>();

    public RightMainFragment() {
    }

    public RightMainFragment(ArrayList<Trip> arrayList) {
        arrayList.addAll(arrayList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { //레이아웃을 인플레이터 할수 있게하는 메소드
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_right_main, container, false); // false는 바로 붙이지 않고, 동작할때만 붙일 수있게

        network = ApplicationController.getInstance().getApiNetwork();

//        fab = (FloatingActionButton) view.findViewById(R.id.right_main_fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Toast.makeText(view.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getActivity(), TripActivity.class));
//            }
//        });
//
//        recyclerView = (RecyclerView) view.findViewById(R.id.right_main_recycler);
        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        // 이미지 눌렀을 때 장소 스팟입력
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ToDayActivity = new Intent(getContext(), DayActivity.class);
                startActivity(ToDayActivity);
            }
        });
       // recyclerView.setLayoutManager(manager);

//        adapter = new RightMainAdapter(getContext(), arrayList);
//        recyclerView.setAdapter(adapter);
        getTrips();

        ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TripActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }

    private void getTrips() {
        Call<TIndexResult> tIndexResultCall = network.getTIndexResult(new Token(getContext()).getKey());

        tIndexResultCall.enqueue(new Callback<TIndexResult>() {
            @Override
            public void onResponse(Call<TIndexResult> call, Response<TIndexResult> response) {

                if (response.isSuccessful()) {
                    Log.i("Trip", "Success / code : " + response.code());
                    TIndexResult indexResult = response.body();

                    adapter = new RightMainAdapter(getContext(), indexResult.result);
                   //recyclerView.setAdapter(adapter);
                } else {
                    Log.i("Trip", "Fail / code : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TIndexResult> call, Throwable t) {
                Log.i("Trip", "errors : ".concat(t.getMessage()));
            }
        });
    }
}