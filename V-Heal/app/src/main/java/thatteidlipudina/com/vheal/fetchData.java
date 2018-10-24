package thatteidlipudina.com.vheal;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static thatteidlipudina.com.vheal.Search.pincodestatic1;


public class fetchData extends AsyncTask<String,Void,String> {
    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    Context context;

    fetchData(Context ctx) {
        this.context = ctx;
    }

    @Override
    protected String doInBackground(String... voids) {
        try {
            URL url = new URL("https://vheal12.azurewebsites.net/search.php");
            HttpURLConnection htc = (HttpURLConnection) url.openConnection();



            if (htc == null) {
                Toast.makeText(context, "connection failed", Toast.LENGTH_SHORT).show();
            }
            htc.setRequestMethod("POST");
            htc.setDoOutput(true);
            htc.setDoInput(true);
            OutputStream os = htc.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            String post_data = URLEncoder.encode("pincode", "UTF-8") + "=" + URLEncoder.encode(pincodestatic1, "UTF-8");
            bw.write(post_data);
            bw.flush();
            bw.close();
            os.close();

            InputStream inputStream = htc.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONArray JA = new JSONArray(data);
            for (int i = 0; i < JA.length(); i++) {
                JSONObject JO = (JSONObject) JA.get(i);
                singleParsed = "Disease:" + JO.get("disease") + "\n" +
                        "Count:" + JO.get("count") + "\n";
                dataParsed = dataParsed + singleParsed + "\n";


            }
            return dataParsed;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String a) {
        super.onPostExecute(a);

        Search.data1static.setText(this.dataParsed);

    }
}