package com.fyang21117.rdiot1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class test2Activity extends AppCompatActivity implements View.OnClickListener{
    public static void actionStart(Context context) {
        Intent intent = new Intent(context,test2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
    }


    //***************************点击增加
    private String TAG = this.getClass().getSimpleName();//装在所有动态添加的Item的LinearLayout容器
    private LinearLayout addCPNameItem;
    private TextView t2;
    private TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        //***************************点击增加
        addCPNameItem = findViewById(R.id.addView);
        //默认添加一个Item
        addViewItem(null);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home: {
                finish();
            }break;
            default :break;
        }
        return super.onOptionsItemSelected(item);
    }

    //***************************点击增加
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addCP://点击添加按钮就动态添加Item

                addViewItem(v);
                break;
        }
    }

    /**
     * Item排序
     */
    private void sortCPViewItem() {

        String string;
        //获取LinearLayout里面所有的view
        for (int i = 0; i < addCPNameItem.getChildCount(); i++) {
            final View childAt = addCPNameItem.getChildAt(i);
            final Button btn_remove = childAt.findViewById(R.id.btn_addCP);
            btn_remove.setText("删除");
            btn_remove.setTag("remove");//设置删除标记
            btn_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addCPNameItem.removeView(childAt);//从LinearLayout容器中删除当前点击到的ViewItem
                }
            });
            //如果是最后一个ViewItem，就设置为添加
            if (i == (addCPNameItem.getChildCount() - 1)) {
                Button btn_add = childAt.findViewById(R.id.btn_addCP);
                btn_add.setText("+新增");
                btn_add.setTag("add");
                t2 = findViewById(R.id.cp_name);
               t1 = findViewById(R.id.cp_num);
                t1.setText(String.valueOf(addCPNameItem.getChildCount()));
                t2.setText("设备:"+ String.valueOf(addCPNameItem.getChildCount()));

                btn_add.setOnClickListener(this);
            }
        }
    }

    //添加ViewItem
    private void addViewItem(View view) {
        if (addCPNameItem.getChildCount() == 0) {
            View cpEvaluateView = View.inflate(this, R.layout.item_add, null);
            Button btn_add =  cpEvaluateView.findViewById(R.id.btn_addCP);
            btn_add.setText("+新增");
            btn_add.setTag("add");
            btn_add.setOnClickListener(this);
            addCPNameItem.addView(cpEvaluateView);
            //sortCPViewItem();
        } else if (( view.getTag()).equals("add")) {//如果有一个以上的Item,点击为添加的Item则添加
            View cpEvaluateView = View.inflate(this, R.layout.item_add, null);
            addCPNameItem.addView(cpEvaluateView);
            sortCPViewItem();
        }
    }
}

