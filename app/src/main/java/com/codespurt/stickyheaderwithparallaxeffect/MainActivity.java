package com.codespurt.stickyheaderwithparallaxeffect;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private View image;
    private ListView listView;
    private TextView text;

    private View stickyView;

    private int NUMBER_OF_ROWS = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.iv_image);
        listView = (ListView) findViewById(R.id.list_view);
        text = (TextView) findViewById(R.id.tv_sticky_view);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listHeader = inflater.inflate(R.layout.list_header, null);
        stickyView = listHeader.findViewById(R.id.sticky_view_placeholder);

        listView.addHeaderView(listHeader);

        // set dummy data
        List<String> list = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            list.add("List item " + i);
        }
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item, list);
        listView.setAdapter(adapter);

        setListener();
    }

    private void setListener() {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (listView.getFirstVisiblePosition() == 0) {
                    View firstChild = listView.getChildAt(0);
                    int topY = 0;
                    if (firstChild != null) {
                        topY = firstChild.getTop();
                    }
                    int imageTopY = stickyView.getTop();
                    text.setY(Math.max(0, imageTopY + topY));
                    image.setY(topY * 0.5f);
                }
            }
        });
    }
}
