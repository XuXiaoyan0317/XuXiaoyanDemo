package com.lanou3g.liwushuodemo.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lanou3g.liwushuodemo.Base.MyApplication;

/**
 * Created by dllo on 16/5/16.
 */
public class VolleySingle {
    private static Context mContext;
    private RequestQueue queue;
    private static VolleySingle ourInstance = new VolleySingle(MyApplication.context);
    //获取单例的对象
    public static VolleySingle getInstance() {
        return ourInstance;
    }

    private VolleySingle(Context context) {
        mContext = context;
        queue = getQueue();//初始化我的请求队列
    }
    //初始化队列的方法,调用后会添加到请求队列
    public RequestQueue getQueue() {
        if (queue==null){
            queue = Volley.newRequestQueue(mContext);
        }
        return queue;
    }
    public static final String TAG = "VolleySingleton";
    public String tag;
    //用于字符串解析的方法
    // 调用后会添加到请求队列
    public void _addRequest(String url, Response.Listener<String> listener,//这里为成功时的泛型
                            Response.ErrorListener errorListener){
        StringRequest stringRequest = new StringRequest(url,listener,errorListener);
        _addRequest(stringRequest);
    }
    //用于自己写的Gson工具类解析的方法(不添加TAG)
    // 调用后自动添加到请求队列中
    public <T> void _addRequest(String url,Response.ErrorListener errorListener ,Class<T> mClass, Response.Listener<T> listener) {
    RequestGson requestGson = new RequestGson(Request.Method.GET,url,errorListener,mClass,listener);
        _addRequest(requestGson);
    }
    //用于自己写的Gson工具类解析的方法(添加TAG)
    // 调用后自动添加到请求队列中
    public <T> void _addRequest(String url,Response.ErrorListener errorListener ,Class<T> mClass, Response.Listener<T> listener,String tag) {
        RequestGson requestGson = new RequestGson(Request.Method.GET,url,errorListener,mClass,listener);
        this.tag=tag;
        _addRequest(requestGson,tag);
    }

    //添加请求
    public <T>  void _addRequest(Request<T> request){
        request.setTag(TAG);//为我们的请求添加标签,方便管理
        queue.add(request);//将请求添加到队列当中
    }
    public <T> void _addRequest(Request<T> request,String tag){
        request.setTag(tag);
        queue.add(request);
    }
    //将请求队列移除
    public void removeRequest(Object tag){
        queue.cancelAll(tag);//根据不同的tag移除出队列
    }
    /*
    先不添加到请求队列
     */
//    public static void addRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener){
//        //获得单例的对象,调用添加请求方法(这个是StringRequest的请求)
//        getInstance()._addRequest(url,listener,errorListener);
//    }
//    public static <T> void addRequest(String url, Response.ErrorListener errorListener, Class<T> mClass, Response.Listener<T>listener){
//        getInstance()._addRequest(url,errorListener,mClass,listener);
//    }

}
