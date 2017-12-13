package rememberme.io.rememberme.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import rememberme.io.rememberme.R;

/**
 * Created by samsung on 2017-12-13.
 */

public class LeftMainNoFragment extends Fragment {
    TextView daySeq;
    TextView spot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { //레이아웃을 인플레이터 할수 있게하는 메소드
            ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_left_no_main, container, false);

            daySeq = (TextView) view.findViewById(R.id.left_main_day_seq);
            spot = (TextView) view.findViewById(R.id.left_main_spot);


        return view;
    }
}
