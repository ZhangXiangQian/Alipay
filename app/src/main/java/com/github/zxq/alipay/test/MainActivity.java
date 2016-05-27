package com.github.zxq.alipay.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.github.zxq.alipay.AlipayOrderInfo;
import com.github.zxq.alipay.PayResult;

public class MainActivity extends AppCompatActivity {
    private TextView txtPayResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("支付宝测试");
        setSupportActionBar(toolbar);

        txtPayResult = (TextView) findViewById(R.id.txtPayResult);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 pay(new AlipayOrderInfo(System.currentTimeMillis() + "" ,"好商品" ,"欢迎购买我公司的商品" ,"0.01").toString(),true);
            }
        });
    }

    private void pay(final String orderInfo, final boolean isShowDialog) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                PayTask payTask = new PayTask(MainActivity.this);
                String result = payTask.pay(orderInfo, isShowDialog);
                PayResult mPayResult = new PayResult(result);
                txtPayResult.setText("payResultCode:" + mPayResult.getResultStatus()
                        + "\n订单号：" + mPayResult.getMemo()
                        + "\n订单结果：" + mPayResult.getResult());
            }
        });
    }
}
