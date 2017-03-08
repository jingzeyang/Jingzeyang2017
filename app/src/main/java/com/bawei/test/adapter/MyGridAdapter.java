package com.bawei.test.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.test.R;
import com.bawei.test.bean.Bean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 荆泽阳 on 2017/3/6.
 *
 * 右边的RecyclerView里面的Grid的适配器
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

public class MyGridAdapter extends BaseAdapter {
    private List<Bean.RsBean.ChildrenBeanX.ChildrenBean> children;
    private final Context context;

    public MyGridAdapter(List<Bean.RsBean.ChildrenBeanX.ChildrenBean> children, Context context) {
        this.children = children;
        this.context = context;
    }

    @Override
    public int getCount() {
        return children == null && children.size() > 0 ? 0 : children.size();
    }

    @Override
    public Object getItem(int position) {
        return children.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.grid_item, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //Glide加载图片
        Glide.with(context).load(children.get(position).getImgApp()).into(holder.imageView);
        holder.textView.setText(children.get(position).getDirName());
        //给图片加上监听
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"您点击了"+children.get(position).getDirName()+"商品",Toast.LENGTH_SHORT).show();
            }
        });

        notifyDataSetChanged();
        return convertView;
    }
    //刷新
    class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}
