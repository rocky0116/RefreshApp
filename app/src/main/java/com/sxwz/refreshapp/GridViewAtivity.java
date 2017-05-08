
package com.sxwz.refreshapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.sxwz.refreshlib.BaseRefreshView;
import com.sxwz.refreshlib.DefaultHandler;
import com.sxwz.refreshlib.RefreshView;
import com.sxwz.refreshlib.loadmore.GridViewWithHeaderAndFooter;
import com.sxwz.refreshlib.loadmore.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;


public class GridViewAtivity extends AppCompatActivity {
    RefreshView refreshView;
    GridViewWithHeaderAndFooter mGridView;
    GridViewAdapter mAdapter;
    private List<String> mData = new ArrayList<>();
    Handler handler = new Handler();

    int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.girdview_layout);
        refreshView = (RefreshView) this.findViewById(R.id.test_grid_view_frame);
        mGridView = (GridViewWithHeaderAndFooter) this.findViewById(R.id.test_grid_view);
        initData();
    }

    private void initData() {
        mAdapter = new GridViewAdapter(this, mData);
        mGridView.setAdapter(mAdapter);
        refreshView.postDelayed(new Runnable() {

            @Override
            public void run() {
                refreshView.autoRefresh(true);
            }
        }, 150);

        refreshView.setPtrHandler(new DefaultHandler() {

            @Override
            public void onRefreshBegin(BaseRefreshView frame) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 0;
                        mData.clear();
                        for (int i = 0; i < 40; i++) {
                            mData.add(new String("GridView item  -" + i));
                        }
                        mAdapter.notifyDataSetChanged();
                        refreshView.refreshComplete();
                        refreshView.setLoadMoreEnable(true);
                    }
                }, 1500);
            }
        });

        refreshView.setOnLoadMoreListener(new OnLoadMoreListener() {

            @Override
            public void loadMore() {
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        for (int i = 0; i < 4; i++) {
                            mData.add(new String("GridView item -- add" + page));
                        }
                        mAdapter.notifyDataSetChanged();
                        refreshView.loadMoreComplete(true);
                        page++;
                        Toast.makeText(GridViewAtivity.this, "load more complete", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);
            }
        });
    }


    public class GridViewAdapter extends BaseAdapter {
        private List<String> datas;
        private LayoutInflater inflater;

        public GridViewAdapter(Context context, List<String> data) {
            super();
            inflater = LayoutInflater.from(context);
            datas = data;
        }

        @Override
        public int getCount() {
            return datas.size();
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
                convertView = inflater.inflate(R.layout.listitem_layout, parent, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(datas.get(position));
            return convertView;
        }

        public List<String> getData() {
            return datas;
        }

    }
}
