package rememberme.io.rememberme.Photo;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import rememberme.io.rememberme.R;

public class PhotoActivity extends AppCompatActivity {

    TextView textView;
    ListView listView;
    PictureAdapter adapter;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    int pictureCount = 0;

    ArrayList<ImageInfo> pictures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        textView = (TextView) findViewById(R.id.textView);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new PictureAdapter();

        ArrayList<ImageInfo> result = queryAllPictures();
        adapter.setItems(result);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ImageInfo item = (ImageInfo) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), " : " + item.getDisplayName(), Toast.LENGTH_LONG).show();
            }
        });

//        recyclerView = (RecyclerView) findViewById(R.id.photo_bucket_recycler);
//        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(manager);
//
//        File root = new File("/storage/self/primary", "RememberMe");
//
//        ArrayList<Photo> photos = new ArrayList<Photo>();
//        for (int j = 0; j < 20; j++) {
//            photos.add(new Photo(new Date(), new Date(), R.drawable.logo));
//        }
//        PhotoAdapter adapter = new PhotoAdapter(getApplicationContext(), photos);
//        recyclerView.setAdapter(adapter);
    }
    private ArrayList<ImageInfo> queryAllPictures() {
        ArrayList<ImageInfo> result = new ArrayList<>();
        Uri uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME, MediaStore.MediaColumns.DATE_ADDED };

        //Cursor cursor = getContentResolver().query(uri, projection, null, null, MediaStore.MediaColumns.DATE_ADDED + " desc");
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null,"bucket_display_name='RememberMe'",null,null);

        int columnDataIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        int columnNameIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);
        // int columnDateIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_ADDED);

        while (cursor.moveToNext()) {
            String path = cursor.getString(columnDataIndex);
            String displayName = cursor.getString(columnNameIndex);
            // String outDate = cursor.getString(columnDateIndex);
            //String addedDate = dateFormat.format(new Date(new Long(outDate).longValue() * 1000L));

            if (!TextUtils.isEmpty(path)) {
                ImageInfo info = new ImageInfo(path, displayName);//, addedDate);
                result.add(info);
            }

            pictureCount++;
        }

        textView.setText(pictureCount + " 개");
        Log.d("MainActivity", "Picture count : " + pictureCount);

        for (ImageInfo info : result) {
            Log.d("MainActivity", info.toString());
        }

        return result;
    }

    class PictureAdapter extends BaseAdapter {
        ArrayList<ImageInfo> items = new ArrayList<ImageInfo>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(ImageInfo item) {
            items.add(item);
        }

        public void setItems(ArrayList<ImageInfo> items) {
            this.items = items;
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            PictureItemView view = null;
            if (convertView == null) {
                view = new PictureItemView(getApplicationContext());
            } else {
                view = (PictureItemView) convertView;
            }

            ImageInfo item = items.get(position);
            view.setName(item.getDisplayName());
            // view.setDate(item.getDateAdded());
            view.setImage(item.getPath());

            return view;
        }
    }
}
