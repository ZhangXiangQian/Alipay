# Alipay
##支付宝支付
支付宝支付相关支付申请流程请依据官方申请流程
在这里若使用请将Alipay的mouble直接导入自己工程即可，具体调用如下：
####初始化AlipayHelper类
```java
        AlipayHelper mHelper = AlipayHelper.getHelper();
         /** 配置商户PID */
        mHelper.setPARTNER("");
        /**支付结束之后跳转至指定页面，这里由商户指定 可为空 */
        mHelper.setRETURN_URL("");
        /** 配置商户收款账号 */
        mHelper.setSELLER("");
```
####订单信息生成
 订单信息由AlipayOrderInfo类生成，这里必须调用toString方法
```java
  /**
  * @param outTradeNo  订单号 由商户服务器生成
  * @param subject     订单主题
  * @param body        订单内容
  * @param price       金额
  */
  String orderInfo = new AlipayOrderInfo(System.currentTimeMillis() + "" ,"好商品" ,"欢迎购买我公司的商品" ,"0.01").toString();
```
#### 需要在线程调用支付功能
```java
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
```
