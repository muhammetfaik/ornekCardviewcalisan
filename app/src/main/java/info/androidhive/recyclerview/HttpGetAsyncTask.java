package info.androidhive.recyclerview;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

/**
 * Created by Onur Yenigün on 5.4.2015.
 */
public class HttpGetAsyncTask extends AsyncTask<String, Void, Void> {

    OnTaskCompleted onTaskCompleted;
    Type type;
    Boolean isProgressActive = false;
    ProgressDialog dialog;

    public HttpGetAsyncTask(OnTaskCompleted onTaskCompleted, Type type) {
        this.onTaskCompleted = onTaskCompleted;
        this.type = type;
        isProgressActive = false;
    }

    public HttpGetAsyncTask(Context context, OnTaskCompleted onTaskCompleted, Type type, Boolean isProgressActive) {
        this.onTaskCompleted = onTaskCompleted;
        this.type = type;
        this.isProgressActive = isProgressActive;
        dialog = new ProgressDialog(context);
        dialog.setMessage("Lütfen Bekleyiniz..");
        dialog.setCancelable(false);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (isProgressActive && dialog != null) {
            dialog.show();
        }

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (isProgressActive && dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    protected Void doInBackground(String... urls) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(Methods.baseUrl + urls[0]);

        HttpResponse response;
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {

                InputStream instream = entity.getContent();
                String result = convertStreamToString(instream);
                if (urls[0].contains(Methods.GetYearsList)) {
                  //  onTaskCompleted.onTaskCompleted(getYearsFromJson(result));
                } else
                    onTaskCompleted.onTaskCompleted(new Gson().fromJson(result, type));
                instream.close();
            }


        } catch (Exception e) {
            Log.e("Executer", "" + e);
        }
        return null;
    }

    /*private GeneralResponse<List<GeneralContract>> getYearsFromJson(String jsonString) {

        GeneralResponse<List<GeneralContract>> response = new GeneralResponse<>();
        List<GeneralContract> yearList = new ArrayList<>();
        for (int i = 0; i < jsonString.length(); i++) {

            if (jsonString.charAt(i) == '_') {
                GeneralContract contract = new GeneralContract();
                contract.setName(jsonString.substring(i - 4, i + 5));
                yearList.add(contract);
            }
        }
        response.setData(yearList);
        response.setStatus("OK");
        return response;
    }*/

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
