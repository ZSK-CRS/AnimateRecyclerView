package cn.example.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

import cn.zsk.animaterecycler.AnimateRecyclerView;
import cn.zsk.animaterecycler.ProgressStyle;

public class MainActivity extends AppCompatActivity {
    private MyAdapter mAdapter;
    private ArrayList<String> listData;
    private int refreshTime = 0;
    private int times = 0;
    private AnimateRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        recyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        recyclerView.setLoadingListener(new AnimateRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime ++;
                times = 0;
                new Handler().postDelayed(new Runnable(){
                    public void run() {

                        listData.clear();
                        for(int i = 0; i < 20 ;i++){
                            listData.add("item" + i + "after " + refreshTime + " times of refresh");
                        }
                        mAdapter.notifyDataSetChanged();
                        recyclerView.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                if(times < 2){
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            recyclerView.loadMoreComplete();
                            for(int i = 0; i < 20 ;i++){
                                listData.add("item" + (i + listData.size()) );
                            }
                            recyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for(int i = 0; i < 9 ;i++){
                                listData.add("item" + (i + listData.size()) );
                            }
                            mAdapter.notifyDataSetChanged();
                            recyclerView.setNoMore(true);
                        }
                    }, 1000);
                }
                times ++;
            }
        });

        listData = new ArrayList<String>();
        for(int i = 0; i < 20 ;i++){
            listData.add("item" + i);
        }
        mAdapter = new MyAdapter(listData);

        recyclerView.setAdapter(mAdapter);



    }


}
