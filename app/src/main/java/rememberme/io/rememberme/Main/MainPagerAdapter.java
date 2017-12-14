package rememberme.io.rememberme.Main;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import rememberme.io.rememberme.Network.APINetwork;
import rememberme.io.rememberme.Network.ApplicationController;
import rememberme.io.rememberme.Network.Token;
import rememberme.io.rememberme.Trip.Results.TIndexResult;
import rememberme.io.rememberme.Trip.Trip;
import retrofit2.Call;

/**
 * Created by jongbong on 2017. 12. 11..
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {
    int size;
    int tabCount;
    APINetwork network;
    Context context;

    LeftMainNoFragment leftMainNoFragment;
    RightMainFragment rightMainFragment;
    RightMainNoFragment rightMainNoFragment;

    public MainPagerAdapter(Context context, FragmentManager fm, int tabCount) {
        super(fm);
        this.context = context;
        this.tabCount = tabCount;
        this.network = ApplicationController.getInstance().getApiNetwork();
    }

    @Override
    public synchronized Fragment getItem(int position) {
        Log.i("Main", "start inflate tab fragment");
        switch (position) {
            case 0:
//                Log.i("Main", "LeftPage inflate");
//                updateLeftPage();
                rightMainNoFragment = new RightMainNoFragment();
                return rightMainNoFragment;

            case 1:
//                Log.i("Main", "RightPage inflate");
//                updateRightPage();
//                Log.i("Main", "before check size : " + size);
//                return updateRightPage();
//                if (size != 0) {
//                    rightMainFragment = new RightMainFragment();
//                    return rightMainFragment;
//                } else {
                rightMainNoFragment = new RightMainNoFragment();
                return rightMainNoFragment;

//                }
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public int getItemPosition(Object object) {
//        if(object instanceof LeftMainFragment || object instanceof RightMainFragment || object instanceof  RightMainNoFragment) {
//            return POSITION_NONE;
//        }
        return super.getItemPosition(object);
//        return POSITION_NONE;
    }

    public void updateLeftPage() {

    }

    public void updateRightPage() {
        Log.i("Main", "start updateRightPage");
        Token token = new Token(context);
        final Call<TIndexResult> tIndexResultCall = network.getTIndexResult(token.getKey()); //가져온 토큰을 네트워크에 보내서 다시 받는다.
        Log.i("Main", "start sync network");

        try {
            ArrayList<Trip> newTrips = new Network().execute(tIndexResultCall).get().result;
            size = newTrips.size();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Log.i("Main", "end of network");
    }


    private class Network extends AsyncTask<Call, Void, TIndexResult> {
        @Override
        protected TIndexResult doInBackground(Call... calls) {
            try {
                Call<TIndexResult> tIndexResultCall = calls[0];
                return tIndexResultCall.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(TIndexResult tIndexResult) {
            Log.i("Main", "before end network size : " + size);
        }
    }

}


/*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TIndexResult tIndexResult = tIndexResultCall.execute().body();
                    size = tIndexResult.result.size();
                    Log.i("Main", "thread size : " + size);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });*/

        /*tIndexResultCall.enqueue(new Callback<TIndexResult>() {
            @Override
            public void onResponse(Call<TIndexResult> call, Response<TIndexResult> response) {
                Log.i("Main", "Success");
                TIndexResult tIndexResult = response.body();
                size = tIndexResult.result.size();
                Log.i("Main", "thread size : " + size);
            }

            @Override
            public void onFailure(Call<TIndexResult> call, Throwable t) {
                Log.i("Main", "fail : " + t.getMessage());
            }
        });*/