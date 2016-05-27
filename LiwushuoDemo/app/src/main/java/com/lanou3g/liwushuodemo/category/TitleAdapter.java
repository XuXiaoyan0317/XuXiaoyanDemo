package com.lanou3g.liwushuodemo.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lanou3g.liwushuodemo.Bean.GiftBean;
import com.lanou3g.liwushuodemo.R;

import java.util.List;

/**
 * Created by dllo on 16/5/25.
 */
public class TitleAdapter extends BaseAdapter {
    private Context context;
    private List<GiftBean.DataBean.CategoriesBean> titleBean;

    public void setTitleBean(List<GiftBean.DataBean.CategoriesBean> titleBean) {
        this.titleBean = titleBean;
        notifyDataSetChanged();
    }

    public TitleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return titleBean ==null?0: titleBean.size();
    }

    @Override
    public Object getItem(int position) {
        return titleBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;//定义一个viewholder
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_category_gift_title,parent,false);
            //进行findviewbyid操作 并把操作玩的组件保存起来
            //初始化hodler,将holder内部的组件都完成findviewbyid操作
            holder = new ViewHolder(convertView);
            //把初始化好的ViewHolder放到converView里
            convertView.setTag(holder);


        }else{
            //证明convertView不是新的
            //它已经注入布局,并且有holder
            //把convertView里的holder取出来
            holder = (ViewHolder) convertView.getTag();
        }
        //设置数据
        //从数据集合里渠道对应的position位置的数据

        holder.nameTv.setText(titleBean.get(position).getName());
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(context,"哈哈",Toast.LENGTH_SHORT);
//                Intent intent = new Intent();
//                intent.putExtra("位置",position);
//                Log.d("哈哈哈","---"+position);
//            }
//        });

        return  convertView;


    }
    //内部类用来保存item中的各个组件
    class ViewHolder{

        TextView nameTv;

        //在这个ViewHolder的对象创建的时候对他内部保存的各个组件进行findviewbyid的操作
        //之后这个对象内部的各个组件就是初始化完成的
        //省略了不必要的findviewbyid操作
        public ViewHolder(View itemView){

            nameTv = (TextView) itemView.findViewById(R.id.tv);
        }

    }
}
