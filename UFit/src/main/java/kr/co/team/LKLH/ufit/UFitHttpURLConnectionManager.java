package kr.co.team.LKLH.ufit;

import android.util.Log;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ccei on 2016-08-03.
 */

public class UFitHttpURLConnectionManager {
    public static HttpURLConnection getHttpURLConnection(String targetURL, String reqMethod){
        HttpURLConnection httpConnection = null;
        try{
            URL url = new URL(targetURL);

            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setDoInput(true);
            httpConnection.setConnectTimeout(15000);

        }catch(Exception e){
            Log.e("그르르", "getHttpURLConnection() fail! ", e);
        }
        return httpConnection;
    }

    public  static void setDismissConnection(HttpURLConnection returnedConn, Reader inR, Writer outW){

        if( inR != null){
            try{
                inR.close();
            }catch(IOException ioe){

            }
        }
        if( outW != null){
            try{
                outW.close();
            }catch(IOException ioe){

            }
        }
        if( returnedConn != null){

            returnedConn.disconnect();
        }
    }

}