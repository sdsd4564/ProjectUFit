package kr.co.team.LKLH.ufit;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ccei on 2016-08-03.
 */
public class UFitJSONParseHandler {
    public static ArrayList<UFitEntityObject> getMemberList(StringBuilder buf){
        ArrayList<UFitEntityObject> json_main_list = null;
        JSONObject mainobj = null;
        JSONArray mainlist = null;

        try{
            mainobj = new JSONObject(buf.toString());
            mainlist = mainobj.getJSONArray("data");
            json_main_list = new ArrayList<UFitEntityObject>(mainlist.length());

            for(int i = 0;  i < mainlist.length(); i++){
                Log.i("array 길이", "" + mainlist.length());
                Log.i("파스핸들러 mainlist", "" + mainlist);
                UFitEntityObject entity = new UFitEntityObject();
                JSONObject listOB = mainlist.getJSONObject(i);
                Log.i("파스핸들러&listOB", "" + listOB);

                entity._mid = listOB.getString("_mid");
                Log.i("파스핸들러&listOB&mid", "" + entity._mid);

                entity._name = listOB.getString("_name");
                Log.i("파스핸들러&listOB&name", "" + entity._name);

                entity._time = listOB.getString("_time");
                Log.i("파스핸들러&listOB&time", "" + entity._time);

                json_main_list.add(entity);

            }

        } catch(Exception e){
            Log.e("ParseDataParseHandler", e.toString());
        }
        Log.i("파스핸들러& json_main_list", "" + json_main_list);

        return json_main_list;
    }
}
