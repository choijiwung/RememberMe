package rememberme.io.rememberme.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;

import rememberme.io.rememberme.Photo.PhotoActivity;
import rememberme.io.rememberme.R;

import static rememberme.io.rememberme.R.id.txtFirstLastName;

/**
 * Created by samsung on 2017-11-17.
 */
// 현재여행 탭 뷰
public class LeftMainFragment extends Fragment {
    private ListView listView;
    private CustomAdapter adapter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_spot);


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.activity_spot, container, false);

        listView = (ListView) view.findViewById(R.id.listView);



        final ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("Image", R.drawable.day1_1);
        map1.put("FirstLastName", "포지타노");
        //map1.put("Descriptions", "레몬 샤베트 짱맛있음");
        map1.put("Params1", "Day1");

        list.add(map1);

        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("Image", R.drawable.day1_2);
        map2.put("FirstLastName", "콜로세움");
        //map2.put("Descriptions", "나랑 싸우고시펑? ");
        map2.put("Params1", "Day1");

        list.add(map2);

        HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("Image", R.drawable.day2_1);
        map3.put("FirstLastName", "베니스강");
       // map3.put("Descriptions", "베니스강을 너와 함께 걷고 싶다.");
        map3.put("Params1", "Day2");
        list.add(map3);

        HashMap<String, Object> map4 = new HashMap<String, Object>();
        map4.put("Image", R.drawable.day2_2);
        map4.put("FirstLastName", "에펠탑");
       // map4.put("Descriptions", "에펠탑 저 높은 곳으로 가볼까?.");
        map4.put("Params1", "Day2");
        list.add(map4);

        HashMap<String, Object> map5 = new HashMap<String, Object>();
        map5.put("Image", R.drawable.day2_3);
        map5.put("FirstLastName", "몽마르뜨");
       // map5.put("Descriptions", "저기서 저녁먹으면 쩔겠는데....");
        map5.put("Params1", "Day2");
        list.add(map5);

        HashMap<String, Object> map6 = new HashMap<String, Object>();
        map6.put("Image", R.drawable.day3_1);
        map6.put("FirstLastName", "노틀담");
        //map6.put("Descriptions", "이 곳이 그 유명한 노틀담인가.");
        map6.put("Params1", "Day3");
        list.add(map6);

        HashMap<String, Object> map7 = new HashMap<String, Object>();
        map7.put("Image", R.drawable.day3_2);
        map7.put("FirstLastName", "루브르");
       // map7.put("Descriptions", "루브르 박물관ㅎ.");
        map7.put("Params1", "Day3");
        list.add(map7);



        adapter = new CustomAdapter(getContext(), list);
        listView.setAdapter(adapter);
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext().this, list.get(position).get("FirstLastName").toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return  view;
    }



    //}

    class CustomAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<HashMap<String,Object>> data;

        public CustomAdapter(Context context,ArrayList<HashMap<String,Object>> data){
            this.context = context;
            this.data = data;
        }

        @Override
        public int getCount(){
            return data.size();
        }

        @Override
        public Object getItem(int position){
            return data.get(position);
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent){
            final ViewHolder holder;
            if(convertView == null){
                holder =new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
                holder.imgIcon = (ImageView)convertView.findViewById(R.id.imgIcon);
                holder.txtFirstLastName = (TextView)convertView.findViewById(txtFirstLastName);
                holder.txtDescription = (TextView)convertView.findViewById(R.id.txtDescription);
                holder.txtParams1 = (TextView)convertView.findViewById(R.id.txtParams1);
                holder.pinbtn = (ToggleButton)convertView.findViewById(R.id.pinbtn);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }
            holder.imgIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), PhotoActivity.class).putExtra("id", position));
                }
            });
            holder.imgIcon.setImageResource((Integer) data.get(position).get("Image"));
            holder.txtFirstLastName.setText(data.get(position).get("FirstLastName").toString());
            //holder.txtDescription.setText(data.get(position).get("Descriptions").toString());
            holder.txtParams1.setText(data.get(position).get("Params1").toString());
            holder.pinbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked ==true){


                    }else{
                    }
                }
            });


            return convertView;
        }

        class ViewHolder{
            ImageView imgIcon;
            ToggleButton pinbtn;

            TextView txtFirstLastName;
            TextView txtDescription;
            TextView txtParams1;

        }
    }
}
