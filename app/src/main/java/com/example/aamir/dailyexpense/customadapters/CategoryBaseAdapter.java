package com.example.aamir.dailyexpense.customadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aamir.dailyexpense.R;
import com.example.aamir.dailyexpense.myobjects.CategoryItem;

import java.util.ArrayList;

public class CategoryBaseAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<CategoryItem> categoryList;
    Integer[] icons = {
            R.drawable.bills,
            R.drawable.commute,
            R.drawable.food,
            R.drawable.fruits,
            R.drawable.fuel,
            R.drawable.gift,
            R.drawable.grocery,
            R.drawable.health,
            R.drawable.office,
            R.drawable.shopping,
            R.drawable.sports,
            R.drawable.getables,
            R.drawable.home,
            R.drawable.pets,
            R.drawable.clothing,
            R.drawable.baby,
    };
    String[] names = {
            "Bills","Commute","Food","Fruits","Fuel","Gifts","Grocery","Health",
            "Office","Shopping","Sports","Vegetables","Home","Pets","Clothing","Baby"
    };

    public interface OnItemClickListener
    {
        void onItemClick();
    }

    public CategoryBaseAdapter(Context context) {

        this.mContext = context;
        CategoryItem c;
        categoryList = new ArrayList<>();
        for (int i=0; i<=15;i++)
        {
            c = new CategoryItem();
            c.setIcon(i);
            c.setIconName(names[i]);
            categoryList.add(c);
        }
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.category_grid_item, parent, false);
        }
        ImageView iv = (ImageView) convertView.findViewById(R.id.gridImage);
        TextView tv = (TextView) convertView.findViewById(R.id.iconName);
        tv.setText(names[position]);
        iv.setImageResource(icons[position]);

        return convertView;
    }
}
