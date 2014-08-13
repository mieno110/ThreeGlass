package com.example.threeglass.rcrs.threeglass;

/**
 * Created by 010322 on 2014/07/01.
 */
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyRecordActivity extends Activity {


    static List<String> dataList = new ArrayList<String>();
    static ArrayAdapter<String> adapter1,adapter2,adapter3;

    ListView listView1,listView2,listView3;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_view);

        //ListViewのインスタンス生成
        findListView();
        //各ListViewにアダプターの設定
        setAdapters();

        //サーバにアクセス






        //--------------------------
        //サーバから受け取ったデータをリストに追加
        int num = 10;
        for(int i=0;i<num;i++){
            if(i==0){
                setData("場所","入室時間","退室時間");
            }
            else setData("大分","2014/06/30/08:50","2014/06/30/17:50");
        }


    }

    //リストに追加
    protected  void setData(String SpotName, String InTime, String OutTime){

        /*adapter1.add(SpotName);
        adapter2.add(InTime);
        adapter3.add(OutTime);*/
    }

    protected void findListView(){
        //listView1 = (ListView)findViewById(R.id.listView1);
       // listView2 = (ListView)findViewById(R.id.listView2);
       // listView3 = (ListView)findViewById(R.id.listView3);

    }

    protected void setAdapters(){
        /*adapter1 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                dataList);

        listView1.setAdapter(adapter1);

        adapter2 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                dataList);

        listView2.setAdapter(adapter2);

        adapter3 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                dataList);

        listView3.setAdapter(adapter3);
*/

    }
}
