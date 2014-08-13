package com.example.threeglass.rcrs.threeglass;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import  com.example.threeglass.rcrs.threeglass.models.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 010144 on 14/03/25.
 */
public class MyRecordFragment extends Fragment {

    private ListView lv;
    private View v;
    private Context context;
    public static final String PREFERENCES_FILE_NAME = "preference";

    static List<String> dataList = new ArrayList<String>();
    static ArrayAdapter<String> adapter1,adapter2,adapter3;
   // ListView listView1,listView2,listView3;
    GridView record_grid;

    private RequestQueue mQueue;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_record_view, container, false);
        context = v.getContext();


        //ListViewのインスタンス生成
        findListView();
        //各ListViewにアダプターの設定
        setAdapters();

        //前のリストが残っているときは一度クリア
        if(adapter1.getCount()!=0){
            adapter1.clear();
        }

        //サーバにアクセス
        //リクエストURL
        UserInfo userInfo = UserInfo.getInstance();
        String url = "http://192.168.76.37/apppass/api/recordapi";
        url += "?id="+userInfo.getUserId();

        mQueue = Volley.newRequestQueue(context);
        mQueue.add(new JsonArrayRequest( url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // JSONArrayのパース、List、Viewへの追加等
                        try {
                                JSONObject json = new JSONObject();
                                String SpotName,InTime,OutTime;
                                setData("場所","入室時間","退室時間");
                                //データの抽出
                                for(int i=0;i<response.length(); i++){
                                    json = response.getJSONObject(i);
                                    SpotName = json.get("address").toString();
                                    InTime = json.get("checkined_time").toString();
                                    OutTime = json.get("checkout_time").toString();
                                    setData(SpotName,InTime,OutTime);
                                }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ));

        return v;
    }

    //リストに追加
    protected  void setData(String SpotName, String InTime, String OutTime){

        adapter1.add(SpotName);
        adapter1.add(InTime);
        adapter1.add(OutTime);
       // adapter1.add(SpotName);
       // adapter2.add(InTime);
       // adapter3.add(OutTime);
    }

    protected void findListView(){
        record_grid = (GridView)v.findViewById(R.id.record_grid);
        //listView1 = (ListView)v.findViewById(R.id.listView1);
        //listView2 = (ListView)v.findViewById(R.id.listView2);
        //listView3 = (ListView)v.findViewById(R.id.listView3);
    }

    protected void setAdapters(){

        adapter1 = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_list_item_1,
                dataList);
        record_grid.setAdapter(adapter1);

        /*adapter1 = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_list_item_1,
                dataList);

        listView1.setAdapter(adapter1);

        adapter2 = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_list_item_1,
                dataList);

        listView2.setAdapter(adapter2);

        adapter3 = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_list_item_1,
                dataList);

        listView3.setAdapter(adapter3);*/


    }

    private View.OnClickListener mClickListener = new View.OnClickListener(){
        public void onClick(View v){
            int a = 0;
            a++;
        }
    };



}
