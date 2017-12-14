package rememberme.io.rememberme.Photo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import rememberme.io.rememberme.R;

public class PhotoActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        recyclerView = (RecyclerView) findViewById(R.id.photo_bucket_recycler);
        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        File root = new File("/storage/self/primary", "RememberMe");

        ArrayList<Photo> photos = new ArrayList<Photo>();
        for (int j = 0; j < 20; j++) {
            photos.add(new Photo(new Date(), new Date(), R.drawable.logo));
        }
        PhotoAdapter adapter = new PhotoAdapter(getApplicationContext(), photos);
        recyclerView.setAdapter(adapter);
    }
}
