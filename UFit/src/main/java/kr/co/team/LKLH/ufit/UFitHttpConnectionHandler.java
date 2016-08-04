package kr.co.team.LKLH.ufit;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by ccei on 2016-08-03.
 */
public class UFitHttpConnectionHandler {


    public static ArrayList<UFitEntityObject> mainlist(){
        HttpURLConnection urlCon = null;
        BufferedReader jsonStreamData = null;
        ArrayList<UFitEntityObject> MainList = null;

        try{
            urlCon = UFitHttpURLConnectionManager.getHttpURLConnection(UFitNetworkConstantDefinition.SERVER_URL_UFit_Trainer_Selected_Day_Schedule, "GET");
            jsonStreamData = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String line = "";
            StringBuilder buf = new StringBuilder();


            while ((line = jsonStreamData.readLine()) != null) {
                buf.append(line);
            }

            MainList = UFitJSONParseHandler.getMemberList(buf);
            Log.i("메인리스트 in COnnHandler", MainList + "");
//            return MainList;

        } catch (IOException ioe) {
            Log.e("8888888888888888888", ioe.toString());
        }finally {
            UFitHttpURLConnectionManager.setDismissConnection(urlCon, jsonStreamData, null);
        }

        return MainList;

    }
}