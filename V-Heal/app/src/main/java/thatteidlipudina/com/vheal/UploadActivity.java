package thatteidlipudina.com.vheal;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;


import thatteidlipudina.com.vheal.R;

import static thatteidlipudina.com.vheal.Ailments_or_Report.diseaseStaticAilments;
import static thatteidlipudina.com.vheal.Ailments_or_Report.patientNamestatic;
import static thatteidlipudina.com.vheal.Constants.UPLOAD_URL;
import static thatteidlipudina.com.vheal.LoginActivity.usernamestatic;
import static thatteidlipudina.com.vheal.MapActivity.pincodestatic;

/*
Thatte Idli Pudina Chutney
Coded by members of ThatteIdliPudina Chutney for CodeFundo : Oct 26, 2018
 */

public class UploadActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "UploadActivity";
    //Declaring views
    private Button buttonChoose, buttonProceed;
    private Button buttonUpload;
    private ImageView imageView;
    private EditText editText, diseaseText;

    //request code
    private int PICK_IMAGE_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;

    //Bitmap to get image from gallery
    private Bitmap bitmap;
    Button btnProcess;
    TextView txtView;

    //Uri to store the image uri
    private Uri filePath;
    static String diseasestatic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        //Requesting storage permission
        requestStoragePermission();

        //Initializing views
        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        buttonProceed=(Button) findViewById(R.id.buttonProceed);
        imageView = (ImageView) findViewById(R.id.imageView);
        editText = (EditText) findViewById(R.id.editTextName);
        diseaseText=(EditText) findViewById(R.id.diseaseText);

        //Setting clicklistener
        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);

        btnProcess = findViewById(R.id.btnProcess);
        txtView = findViewById(R.id.txtView);


        buttonProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((diseaseText.getText().toString().equals("null"))) {
                    diseasestatic = diseaseStaticAilments;

                }
                else{
                    System.out.println(diseaseStaticAilments + "hi");
                    diseasestatic = diseaseText.getText().toString();
                }
                String type=  "disease";
                Toast.makeText(UploadActivity.this,diseasestatic, Toast.LENGTH_SHORT).show();
                Backgroundworker b= new Backgroundworker(UploadActivity.this);
                b.execute(type, diseasestatic, pincodestatic, usernamestatic);
            }
        });

        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextRecognizer txtRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
                if (!txtRecognizer.isOperational()) {
                    txtView.setText(R.string.error_prompt);
                } else {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray items = txtRecognizer.detect(frame);
                    StringBuilder strBuilder = new StringBuilder();
                    for (int i = 0; i < items.size(); i++) {
                        TextBlock item = (TextBlock) items.valueAt(i);
                        strBuilder.append(item.getValue());
                        strBuilder.append("/");
                        for (Text line : item.getComponents()) {
                            //extract scanned text lines here
                            Log.v("lines", line.getValue());
                            for (Text element : line.getComponents()) {
                                //extract scanned text words here
                                Log.v("element", element.getValue());

                            }
                        }
                    }
                    String type="Upload";
                    txtView.setText(strBuilder.toString().substring(0, strBuilder.toString().length() - 1));
                    System.out.println(patientNamestatic + "hi");
                    Backgroundworker b= new Backgroundworker(UploadActivity.this);
                    b.execute(type,strBuilder.toString(), patientNamestatic);

                }
            }
        });
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private void uploadImage() {
        class UploadImage extends AsyncTask<Bitmap, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UploadActivity.this, "Uploading...", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);




                    try {
                        URL url = new URL(UPLOAD_URL);
                        HttpURLConnection htc = (HttpURLConnection) url.openConnection();
                        if (htc == null) {
                            Toast.makeText(UploadActivity.this, "connection failed", Toast.LENGTH_SHORT).show();
                        }
                        htc.setRequestMethod("POST");
                        htc.setDoOutput(true);
                        htc.setDoInput(true);
                        OutputStream os = htc.getOutputStream();
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                        String post_data = URLEncoder.encode("patientname", "UTF-8") + "=" + URLEncoder.encode(patientNamestatic, "UTF-8") + "&"
                                + URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(uploadImage, "UTF-8");
                        bw.write(post_data);
                        bw.flush();
                        bw.close();
                        os.close();
                        InputStream is = htc.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                        String result = "";
                        String line = "";
                        while ((line = br.readLine()) != null) {
                            result += line;
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


                return null;
                }
            }

            UploadImage ui = new UploadImage();
        ui.execute(bitmap);
        }


    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //method to get the file path from uri
    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }


    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
        //Ask user for permission(tellhim why to trust us)

        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onClick(View v) {
        if (v == buttonChoose) {
            showFileChooser();
        }
        if (v == buttonUpload) {
            uploadImage();
        }
    }


}