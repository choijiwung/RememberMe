package rememberme.io.rememberme.Photo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import rememberme.io.rememberme.R;

/**
 * Created by jongbong on 2017. 12. 14..
 */

public class PhotoVH extends RecyclerView.ViewHolder {

    ImageView image;

    public PhotoVH(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.photo_image);
    }
}
