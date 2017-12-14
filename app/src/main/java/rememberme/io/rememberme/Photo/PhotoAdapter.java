package rememberme.io.rememberme.Photo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import rememberme.io.rememberme.R;

/**
 * Created by jongbong on 2017. 12. 14..
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoVH> {

    Context context;
    ArrayList<Photo> photos = new ArrayList<>();

    public PhotoAdapter(Context context, ArrayList<Photo> photos) {
        this.context = context;
        this.photos.addAll(photos);
    }

    @Override
    public PhotoVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.photo_item, parent, false);
        return new PhotoVH(view);
    }

    @Override
    public void onBindViewHolder(PhotoVH holder, int position) {
        holder.image.setImageResource(photos.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }
}
