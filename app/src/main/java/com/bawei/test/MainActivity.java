package com.bawei.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bawei.test.adapter.LeftRecyAdapter;
import com.bawei.test.adapter.RightRecyAdapter;
import com.bawei.test.bean.Bean;
import com.bawei.test.utils.MyApp;
import com.bawei.test.utils.OkHttputils;

import java.util.ArrayList;
import java.util.List;

/**
 * 荆泽阳20170306
 * 类的用途: 主界面
 *
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView leftRecyclerView;
    private RecyclerView rightRecyclerView;
    private String path="http://mock.eoapi.cn/success/4q69ckcRaBdxhdHySqp2Mnxdju5Z8Yr4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //赋值
        getData();


    }

    private void getData() {
        //调用Okhttp来请求玩网络
        OkHttputils  httpUtils = OkHttputils .getHttpUtils();
        httpUtils.loadDataFromNet(path, Bean.class, new OkHttputils.CallBackListener<Bean>() {
           //请求成功
            @Override
            public void onSuccess(Bean result) {
                //实例化集合
                List<String> titleList = new ArrayList<String>();
                //得到请求解析出来的数据
                List<Bean.RsBean> rs = result.getRs();
                //循环遍历得到的数据
                if (rs != null && rs.size() > 0) {

                    for (int i = 0; i < rs.size(); i++) {
                        //把请求解析出来的数据放到集合中
                        titleList.add(rs.get(i).getDirName());
                    }
                }
                //左边标题列表
                leftRecy(titleList,rs);
            }

            @Override
            public void onFail() {

            }
        });


    }
    private void leftRecy(List<String> mList, final List<Bean.RsBean> rs) {
        //给左边的RecyclerView设置布局模式
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //实例化左边的RecyclerView的适配器
        final LeftRecyAdapter  leftRecy =  new LeftRecyAdapter(mList,MainActivity.this);
        //给左边的RecyclerView加上适配器
        leftRecyclerView.setAdapter(leftRecy);
        //默认显示第一页数据
        rightRecyclerView(rs.get(0));
        //左边的RecyclerView的监听方法
        leftRecy.setRecyOnItemClickListener(new LeftRecyAdapter.LeftRecyOnItemClickListener() {
            @Override
            public void leftRecyItemClickListener(int pos) {

                MyApp.pos = pos;
                //右边RecyclerView
                rightRecyclerView(rs.get(pos));
                //刷新左边的适配器
                leftRecy.notifyDataSetChanged();
            }
        });
    }
    //右边RecyclerView
    private void rightRecyclerView(Bean.RsBean rs) {
        //设置布局模式
        rightRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //实例化适配器
        RightRecyAdapter rightRecyAdapter = new RightRecyAdapter(rs,MainActivity.this);
        rightRecyclerView.setAdapter(rightRecyAdapter);
    }


    private void initView() {
        //获取资源ID
        leftRecyclerView= (RecyclerView) findViewById(R.id.leftRecyclerView);
        rightRecyclerView= (RecyclerView) findViewById(R.id.rightRecyclerView);

    }



}
