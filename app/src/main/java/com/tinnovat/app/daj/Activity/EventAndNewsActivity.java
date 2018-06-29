package com.tinnovat.app.daj.Activity;

        import java.lang.reflect.Field;
        import java.util.Locale;
        import java.util.Objects;

        import android.content.Intent;
        import android.content.res.Configuration;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AbsListView;
        import android.widget.AdapterView;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.AbsListView.OnScrollListener;
        import android.widget.RelativeLayout;
        import android.widget.TextView;

        import com.tinnovat.app.daj.Activity.EventNewsActivity;
        import com.tinnovat.app.daj.BaseActivity;
        import com.tinnovat.app.daj.Circlelist.MatrixView;
        import com.tinnovat.app.daj.R;

public class EventAndNewsActivity extends BaseActivity {

    RelativeLayout row1;
    RelativeLayout row2;
    RelativeLayout row3;
    RelativeLayout row4;
    RelativeLayout row5;
    RelativeLayout row6;

    private ListView listview;
    private int[] images = new int[]{R.drawable.cooking, R.drawable.family, R.drawable.festival, R.drawable.play, R.drawable.tennis_bat, R.drawable.wedding};
    private String[] text = new String[]{"Cooking", "Family", "Festival", "Play", "Tennis", "Wedding"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String languageToLoad  = "en"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        setContentView(R.layout.activity_event_and_news);
        Objects.requireNonNull(getSupportActionBar()).setTitle("EVENT AND NEWS");

        row1 = findViewById(R.id.row1);
        row2 = findViewById(R.id.row2);
        row3 = findViewById(R.id.row3);
        row4= findViewById(R.id.row4);
        row5 = findViewById(R.id.row5);
        row6 = findViewById(R.id.row6);

        row1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callEventNews();
            }
        });row2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callEventNews();
            }
        });row3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callEventNews();
            }
        });row4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callEventNews();
            }
        });row5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callEventNews();
            }
        });row6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callEventNews();
            }
        });

        listview = (ListView) findViewById(R.id.lv);
        listview.setAdapter(new EventAndNewsActivity.MyAdapter());
        listview.setClipToPadding(false);
        listview.setClipChildren(false);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callEventNews();
            }
        });


        listview.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                for (int i = 0; i < listview.getChildCount(); i++) {
                    listview.getChildAt(i).invalidate();
                }
            }
        });
    }

    public void callEventNews(){
        Intent i = new Intent(EventAndNewsActivity.this, EventNewsActivity.class);
        startActivity(i);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return 12;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                MatrixView m = (MatrixView) LayoutInflater.from(EventAndNewsActivity.this).inflate(R.layout.view_list_item, null);
                m.setParentHeight(listview.getHeight());
                convertView = m;
            }
            ImageView imageView = convertView.findViewById(R.id.image);
            imageView.setImageResource(images[position % images.length]);
            TextView textView = convertView.findViewById(R.id.text);
            textView.setText(text[position % text.length]);

            return convertView;
        }

    }

    @Deprecated
    public void changeGroupFlag(Object obj) throws Exception {
        Field[] f = obj.getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredFields();
        for (Field tem : f) {
            if (tem.getName().equals("mGroupFlags")) {
                tem.setAccessible(true);
                Integer mGroupFlags = (Integer) tem.get(obj);
                int newGroupFlags = mGroupFlags & 0xfffff8;
                tem.set(obj, newGroupFlags);
            }
        }
    }
}
