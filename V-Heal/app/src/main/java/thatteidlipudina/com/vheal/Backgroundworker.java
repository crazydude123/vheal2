package thatteidlipudina.com.vheal;
/*
Thatte Idli Pudina Chutney
Coded by members of ThatteIdliPudina Chutney for CodeFundo : Oct 26, 2018
 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
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
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
//This class connects to the backend php files
public class Backgroundworker extends AsyncTask<String,Void,String> {

    String dataParsed = "";
    String singleParsed = "";

    Context context;

    Backgroundworker(Context ctx) {
        this.context = ctx;
    }

    @Override
    protected String doInBackground(String... voids) {

        String login_url = "https://vheal12.azurewebsites.net/login.php";
        String register_url = "https://vheal12.azurewebsites.net/register.php";
        String disease_URL = "https://vheal12.azurewebsites.net/disease.php";
        String upload_url = "https://vheal12.azurewebsites.net/alert.php";
        String search_url = "https://vheal12.azurewebsites.net/search.php";
        String patientupdate_URL= "https://vheal12.azurewebsites.net/patientdetails.php";

        String type = voids[0];
        if (type.equals("login")) {
            try {
                String username = voids[1];
                String pass = voids[2];

                URL url = new URL(login_url);
                HttpURLConnection htc = (HttpURLConnection) url.openConnection();
                if (htc == null) {
                    Toast.makeText(context, "connection failed", Toast.LENGTH_SHORT).show();
                }
                htc.setRequestMethod("POST");
                htc.setDoOutput(true);
                htc.setDoInput(true);
                OutputStream os = htc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                        + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
                bw.write(post_data);
                bw.flush();
                bw.close();
                os.close();
                InputStream is = htc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = br.readLine()) != null) {
                    result = result + line;
                }
                br.close();
                is.close();
                htc.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        else if (type.equals("register")) {
            try {
                String username = voids[1];
                String pass = voids[2];
                String name = voids[3];
                String phone = voids[4];

                URL url = new URL(register_url);
                HttpURLConnection htc = (HttpURLConnection) url.openConnection();
                if (htc == null) {
                    Toast.makeText(context, "connection failed", Toast.LENGTH_SHORT).show();
                }
                htc.setRequestMethod("POST");
                htc.setDoOutput(true);
                htc.setDoInput(true);
                OutputStream os = htc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                        + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8") + "&"
                        + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8");
                bw.write(post_data);
                bw.flush();
                bw.close();
                os.close();
                InputStream is = htc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = br.readLine()) != null) {
                    result = result + line;
                }
                br.close();
                is.close();
                htc.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        else if (type.equals("disease")) {
            try {
                String disease = voids[1];
                String pincode = voids[2];
                String email = voids[3];
                URL url = new URL(disease_URL);
                HttpURLConnection htc = (HttpURLConnection) url.openConnection();
                if (htc == null) {
                    Toast.makeText(context, "connection failed", Toast.LENGTH_SHORT).show();
                }
                htc.setRequestMethod("POST");
                htc.setDoOutput(true);
                htc.setDoInput(true);
                OutputStream os = htc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String post_data = URLEncoder.encode("disease", "UTF-8") + "=" + URLEncoder.encode(disease, "UTF-8") + "&"
                        + URLEncoder.encode("pincode", "UTF-8") + "=" + URLEncoder.encode(pincode, "UTF-8") + "&"
                        + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                bw.write(post_data);
                bw.flush();
                bw.close();
                os.close();
                InputStream is = htc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = br.readLine()) != null) {
                    result = result + line;
                }
                br.close();
                is.close();
                htc.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }



        else if (type.equals("Upload")) {
            try {
                String reporttext = voids[1];
                String reportname= voids[2];
                URL url = new URL(upload_url);
                HttpURLConnection htc = (HttpURLConnection) url.openConnection();
                if (htc == null) {
                    Toast.makeText(context, "connection failed", Toast.LENGTH_SHORT).show();
                }
                htc.setRequestMethod("POST");
                htc.setDoOutput(true);
                htc.setDoInput(true);
                OutputStream os = htc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String post_data = URLEncoder.encode("reporttext", "UTF-8") + "=" + URLEncoder.encode(reporttext, "UTF-8")+ "&"
                        + URLEncoder.encode("reportname", "UTF-8") + "=" + URLEncoder.encode(reportname, "UTF-8");
                bw.write(post_data);
                bw.flush();
                bw.close();
                os.close();
                InputStream is = htc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = br.readLine()) != null) {
                    result = result + line;
                }
                br.close();
                is.close();
                htc.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        else if (type.equals("Search")) {
            try {
                String pincodeSearch = voids[1];
                URL url = new URL(search_url);
                HttpURLConnection htc = (HttpURLConnection) url.openConnection();
                if (htc == null) {
                    Toast.makeText(context, "connection failed", Toast.LENGTH_SHORT).show();
                }
                htc.setRequestMethod("POST");
                htc.setDoOutput(true);
                htc.setDoInput(true);
                OutputStream os = htc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String post_data = URLEncoder.encode("pincodeSearch", "UTF-8") + "=" + URLEncoder.encode(pincodeSearch, "UTF-8");
                bw.write(post_data);
                bw.flush();
                bw.close();
                os.close();
                InputStream is = htc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String data = "";
                String line = "";
                while ((line = br.readLine()) != null) {
                    data = data + line;
                }
                String result = "No details in this area yet";
                if(isJSONValid(data)) {
                    JSONArray JA = new JSONArray(data);
                    for (int i = 0; i < JA.length(); i++) {
                        JSONObject JO = (JSONObject) JA.get(i);
                        Log.i("diseasetable: ", "Disease: " + JO.getString("disease") +
                                ", Count: " + JO.getString("count"));
                        singleParsed = "Disease:" + JO.get("disease") + "\n" +
                                "Count:" + JO.get("count") + "\n";
                        dataParsed = dataParsed + singleParsed + "\n";


                    }
                    result = dataParsed;
                }
                else{
                    result = data;
                }
                br.close();
                is.close();
                htc.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


        else if (type.equals("patient update")) {
            try {
                String patientPhone = voids[1];
                String patientName = voids[2];
                String patientAge = voids[3];
                String patientPincode = voids[4];
                String patientDoctoryes = voids[5];
                String patientDisease = voids[6];
                String username= voids[7];

                URL url = new URL(patientupdate_URL);
                HttpURLConnection htc = (HttpURLConnection) url.openConnection();
                if (htc == null) {
                    Toast.makeText(context, "connection failed", Toast.LENGTH_SHORT).show();
                }
                htc.setRequestMethod("POST");
                htc.setDoOutput(true);
                htc.setDoInput(true);
                OutputStream os = htc.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String post_data = URLEncoder.encode("patientPhone", "UTF-8") + "=" + URLEncoder.encode(patientPhone, "UTF-8") + "&"
                        + URLEncoder.encode("patientName", "UTF-8") + "=" + URLEncoder.encode(patientName, "UTF-8") + "&"
                        + URLEncoder.encode("patientAge", "UTF-8") + "=" + URLEncoder.encode(patientAge, "UTF-8") + "&"
                        + URLEncoder.encode("patientPincode", "UTF-8") + "=" + URLEncoder.encode(patientPincode, "UTF-8") + "&"
                        + URLEncoder.encode("patientDoctoryes", "UTF-8") + "=" + URLEncoder.encode(patientDoctoryes, "UTF-8") + "&"
                        + URLEncoder.encode("patientDisease", "UTF-8") + "=" + URLEncoder.encode(patientDisease, "UTF-8") + "&"
                        + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                bw.write(post_data);
                bw.flush();
                bw.close();
                os.close();
                InputStream is = htc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = br.readLine()) != null) {
                    result = result + line;
                }
                br.close();
                is.close();
                htc.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        return null;

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        if(s == null && s.equals("")){
            Toast.makeText(context, "Server error", Toast.LENGTH_SHORT).show();
        }
        else if (s.equals("Login Successful")) {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
        else if(s.equals("Registration Successful")){
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }
        else if(s.equals("Update Successful")){
            Intent intent = new Intent(context, Credit.class);
            context.startActivity(intent);
        }
        else if(s.equals(dataParsed)){
            System.out.println(dataParsed + "dataPased" );
            if (dataParsed != null) {
                Search.data1static.setText(dataParsed);
            }
        }
        else if(s.equals("Patient details uploaded")){
            Intent intent= new Intent(context, doyouhaveapres.class);
            context.startActivity(intent);
        }
        else if(s.equals("Valid")){
            return;
        }




    }





    public boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}