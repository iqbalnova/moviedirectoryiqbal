package com.example.utsfilmiqbal;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestAPI {
    private RequestQueue requestQueue;
    private static RequestAPI mInstance;

    private RequestAPI(Context context){
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized RequestAPI getmInstance(Context context){

        if (mInstance == null){
            mInstance = new RequestAPI(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){return requestQueue;}
}
