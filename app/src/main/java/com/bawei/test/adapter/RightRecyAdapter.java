package com.bawei.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.test.R;
import com.bawei.test.bean.Bean;
import com.bawei.test.utils.MyGridView;

/**
 * Created by 荆泽阳 on 2017/3/6.
 * 类的用途: 右边Recyclerview的适配器
 *
 *
 *
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

public class RightRecyAdapter extends RecyclerView.Adapter<RightRecyAdapter.MyViewHolder> {


    private final Bean.RsBean rs;
    private final Context context;
    private RightRecyOnItemClickListener recyOnItemClickListener;

    public RightRecyAdapter(Bean.RsBean rs, Context context) {
        this.rs = rs;
        this.context = context;
    }
    public void setRecyOnItemClickListener(RightRecyOnItemClickListener recyOnItemClickListener) {
        this.recyOnItemClickListener = recyOnItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.right_recyclerview,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.shopBrand.setText(rs.getChildren().get(position).getDirName());
        holder.shopBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"您点击了"+rs.getChildren().get(position).getDirName()+"分类",Toast.LENGTH_SHORT).show();
            }
        });
        MyGridAdapter myGridAdapter = new MyGridAdapter(rs.getChildren().get(position).getChildren(),context);
        holder.right_grid.setAdapter(myGridAdapter);
        if (recyOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    recyOnItemClickListener.RightRecyItemClickListener(layoutPosition);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return rs.getChildren() == null && rs.getChildren().size() > 0 ? 0 : rs.getChildren().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView shopBrand;
        private final MyGridView right_grid;

        public MyViewHolder(View itemView) {
            super(itemView);
            shopBrand = (TextView) itemView.findViewById(R.id.shopBrand);
            right_grid = (MyGridView) itemView.findViewById(R.id.right_grid);
        }
    }
    public interface RightRecyOnItemClickListener {
        void RightRecyItemClickListener(int pos);
    }


}
