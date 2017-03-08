package com.bawei.test.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.test.R;
import com.bawei.test.utils.MyApp;

import java.util.List;

/**
 * Created by 荆泽阳 on 2017/3/6.
 *
 *
 * 类的用途: 左边Recyclerview的适配器
 * .::::.
 * .::::::::.
 * :::::::::::  Goddess bless, never bug
 * ..:::::::::::'
 * '::::::::::::'
 * .::::::::::
 * '::::::::::::::..
 * ..::::::::::::.
 * ``::::::::::::::::
 * ::::``:::::::::'        .:::.
 * ::::'   ':::::'       .::::::::.
 * .::::'      ::::     .:::::::'::::.
 * .:::'       :::::  .:::::::::' ':::::.
 * .::'        :::::.:::::::::'      ':::::.
 * .::'         ::::::::::::::'         ``::::.
 * ...:::           ::::::::::::'              ``::.
 * ```` ':.          ':::::::::'                  ::::..
 * '.:::::'                    ':'````..
 */

public class LeftRecyAdapter extends RecyclerView.Adapter<LeftRecyAdapter.MyViewHolder> {
    private final List<String> mList;
    private final Context context;
    private LeftRecyOnItemClickListener recyOnItemClickListener;

    public void setRecyOnItemClickListener(LeftRecyOnItemClickListener recyOnItemClickListener) {
        this.recyOnItemClickListener = recyOnItemClickListener;
    }

    public LeftRecyAdapter(List<String> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //获取布局
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.textView.setText(mList.get(position).toString());
        //判断定义的ID是否等于当前的ID
        if (position == MyApp.pos) {
            //如果等于,改变字体的颜色
            holder.textView.setBackgroundColor(Color.WHITE);
        } else {
            //如果不等于,变回原来的颜色
            holder.textView.setBackgroundColor(context.getResources().getColor(R.color.defaultColor));
        }
        if (recyOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    recyOnItemClickListener.leftRecyItemClickListener(layoutPosition);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }

    //定义item监听接口
    public interface LeftRecyOnItemClickListener {
        void leftRecyItemClickListener(int pos);
    }
}
