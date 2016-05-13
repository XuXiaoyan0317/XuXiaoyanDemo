package com.lanou3g.liwushuodemo.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;

/**
 * Created by dllo on 16/5/12.
 */
public class RequestGson<T> extends Request<T> {
    private Class<T> mClass;
    private Gson gson;
    private Response.Listener<T> mListener;

    public RequestGson(int method, String url, Response.ErrorListener listener, Class<T> mClass, Response.Listener<T> mListener) {
        super(method, url, listener);
        this.mClass = mClass;
        this.mListener = mListener;
        this.gson = new Gson();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        try {
            String data = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(gson.fromJson(data, mClass),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }

    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);

    }
}
