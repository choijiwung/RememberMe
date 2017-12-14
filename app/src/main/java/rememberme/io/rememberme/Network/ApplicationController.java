package rememberme.io.rememberme.Network;

import android.app.Application;

import java.io.File;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by samsung on 2017-12-08.
 */

public class ApplicationController extends Application {
    // 나중에 만약 포트 뺀다면, 수정.. 아직은 건들지 말것
    private static ApplicationController instance;
    private final int PORT = 3000;
    //    private final String URL = "http://192.168.43.204".concat(":"+PORT);
//    private final String URL = "http://70.12.50.58".concat(":" + PORT);
    private final String URL = "http://13.230.9.160";

    public static ApplicationController getInstance() {
        return instance;
    }
    public final String rootPath = new File("/storage/self/primary", "RememberMe").getAbsolutePath();
    public APINetwork getApiNetwork() {
        return network;
    }

    private APINetwork network;

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationController.instance = this;
        buildNetwork();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }

    private void buildNetwork() {
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        network = retrofit.create(APINetwork.class);
    }

}
