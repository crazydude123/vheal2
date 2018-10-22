package thatteidlipudina.com.vheal;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Bitmap;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;
import android.net.Uri;
import android.content.ActivityNotFoundException;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;

import thatteidlipudina.com.vheal.models.PlaceInfo;

public class Search extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    String TAG = "Search";
    static String pincodestatic1;
    String redPincodeSearch;
    ListView listView;
    public static EditText data1static;
    AutoCompleteTextView textPincode;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    ImageView mSearch;

    private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;
    private GoogleApiClient mGoogleApiClient;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40, -168), new LatLng(71, 136));
    private PlaceInfo mPlace;
    private Marker mMarker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textPincode = (AutoCompleteTextView) findViewById(R.id.searchView);


        init();
        mSearch = (ImageView) findViewById(R.id.fab);




        list = new ArrayList<>();

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pincodeWorks = textPincode.getText().toString();
                pincodeWorks = redPincodeSearch;
                if(pincodeWorks!=null && !pincodeWorks.equals("")) {
                    System.out.println(pincodeWorks + "pincodeWorks");
                    Backgroundworker b = new Backgroundworker(Search.this);

                    data1static = (EditText) findViewById(R.id.data1);
                    b.execute("Search", pincodeWorks);
                    redPincodeSearch = "";
                }
                else{
                    Toast.makeText(Search.this, "Enter a location, please",Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

        /*FloatingActionButton mFloat= (FloatingActionButton) findViewById(R.id.fab);
        mFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Search.this, MainActivity.class);
                startActivity(intent);
            }
        });
        */
        //Example list
        list.add("Apple");
        list.add("Banana");
        list.add("Pineapple");
        list.add("Orange");
        list.add("Lychee");
        list.add("Gavava");
        list.add("Peech");
        list.add("Melon");
        list.add("Watermelon");
        list.add("Papaya");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
//        listView.setAdapter(adapter);

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                if(list.contains(query)){
//                    adapter.getFilter().filter(query);
//                }else{
//                    Toast.makeText(Search.this, "No Match found",Toast.LENGTH_LONG).show();
//                }
//                return false;
//            }

//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //    adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//    }


        /*Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }*/

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }*/

//        @Override
//        public void onBackPressed () {
//            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//            if (drawer.isDrawerOpen(GravityCompat.START)) {
//                drawer.closeDrawer(GravityCompat.START);
//            } else {
//                super.onBackPressed();
//            }
//        }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }*/

        //@Override
    /*public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

//        @SuppressWarnings("StatementWithEmptyBody")
//
//        public static Bitmap getScreenShot (View view){
//            View screenView = view.getRootView();
//            screenView.setDrawingCacheEnabled(true);
//            Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
//            screenView.setDrawingCacheEnabled(false);
//            return bitmap;
//        }
//
//        public static File store (Bitmap bm, String fileName){
//            final String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Screenshots";
//            File dir = new File(dirPath);
//            if (!dir.exists())
//                dir.mkdirs();
//            File file = new File(dirPath, fileName);
//            try {
//                FileOutputStream fOut = new FileOutputStream(file);
//                bm.compress(Bitmap.CompressFormat.PNG, 85, fOut);
//                fOut.flush();
//                fOut.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return dir;
//        }
//
//        private void shareImage (File file){
//            Uri uri = Uri.fromFile(file);
//            Intent intent = new Intent();
//            intent.setAction(Intent.ACTION_SEND);
//            intent.setType("image/*");
//
//            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
//            intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
//            intent.putExtra(Intent.EXTRA_STREAM, uri);
//            try {
//                startActivity(Intent.createChooser(intent, "Share Screenshot"));
//            } catch (ActivityNotFoundException e) {
//                Toast.makeText(getApplicationContext(), "No App Available", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        @Override
//        public boolean onNavigationItemSelected (MenuItem item){
//            // Handle navigation view item clicks here.
//            int id = item.getItemId();
//
//            if (id == R.id.nav_camera) {
//                // Handle the camera action
//            } else if (id == R.id.nav_gallery) {
//
//            } else if (id == R.id.nav_slideshow) {
//
//            } else if (id == R.id.nav_manage) {
//
//            } else if (id == R.id.nav_share) {
//                final View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
//                Button share = (Button) findViewById(R.id.nav_share);
//                share.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Bitmap bitmap = getScreenShot(rootView);
//                        File f = store(bitmap, "Screenshot");
//                        shareImage(f);
//                    }
//                });
//
//            } else if (id == R.id.nav_report) {
//
//
//            }
//
//            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//            drawer.closeDrawer(GravityCompat.START);
//            return true;
//        }
    }



private void init(){

    mGoogleApiClient = new GoogleApiClient
            .Builder(this)
            .addApi(Places.GEO_DATA_API)
            .addApi(Places.PLACE_DETECTION_API)
            .enableAutoManage(this, this)
            .build();

    textPincode.setOnItemClickListener(mAutocompleteClickListener);

    mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(this, mGoogleApiClient,
            LAT_LNG_BOUNDS, null);

    textPincode.setAdapter(mPlaceAutocompleteAdapter);

    textPincode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
            if(actionId == EditorInfo.IME_ACTION_SEARCH
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                    || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER){

                //execute our method for searching

                geoLocate();

            }

            return false;
        }
    });


}

    private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            hideSoftKeyboard();

            final AutocompletePrediction item = mPlaceAutocompleteAdapter.getItem(i);
            final String placeId = item.getPlaceId();

            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
        }
    };

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(@NonNull PlaceBuffer places) {
            if(!places.getStatus().isSuccess()){
                Log.d(TAG, "onResult: Place query did not complete successfully: " + places.getStatus().toString());
                places.release();
                return;
            }
            final Place place = places.get(0);

            try{
                mPlace = new PlaceInfo();
                mPlace.setName(place.getName().toString());
                Log.d(TAG, "onResult: name: " + place.getName());
                mPlace.setAddress(place.getAddress().toString());

                /*
                 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@SEND RESIDENCE to firebase@@@@@@@@@@@@@@@@
                 *
                 * send
                 * String s= place.getAddress().toString() ---- send
                 * String r= place.getLatLng() ----- send
                 *
                 * */
                Log.d(TAG, "onResult: address: " + place.getAddress());
//                mPlace.setAttributions(place.getAttributions().toString());
//                Log.d(TAG, "onResult: attributions: " + place.getAttributions());
                mPlace.setId(place.getId());
                Log.d(TAG, "onResult: id:" + place.getId());
                mPlace.setLatlng(place.getLatLng());
                Log.d(TAG, "onResult: latlng: " + place.getLatLng());
                mPlace.setRating(place.getRating());
                Log.d(TAG, "onResult: rating: " + place.getRating());
                mPlace.setPhoneNumber(place.getPhoneNumber().toString());
                Log.d(TAG, "onResult: phone number: " + place.getPhoneNumber());
                mPlace.setWebsiteUri(place.getWebsiteUri());
                Log.d(TAG, "onResult: website uri: " + place.getWebsiteUri());

                Geocoder geocoder = new Geocoder(Search.this);
                List<Address> list = new ArrayList<>();
                try{
                    list = geocoder.getFromLocationName(place.getName().toString(), 1);
                }catch (IOException e){
                    Log.e(TAG, "geoLocate: IOException: " + e.getMessage() );
                }

                if(list.size() > 0){
                    Address address = list.get(0);

                    Log.d(TAG, "geoLocate: found a location: " + address.toString());
                    //Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();

                    if(!address.getPostalCode().equals("null")){
                        redPincodeSearch = address.getPostalCode();
                    }
                }




                Log.d(TAG, "onResult: place: " + mPlace.toString());
            }catch (NullPointerException e){
                Log.e(TAG, "onResult: NullPointerException: " + e.getMessage() );
            }

            places.release();
        }
    };

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void geoLocate(){
        Log.d(TAG, "geoLocate: geolocating");

        String searchString = textPincode.getText().toString();

        Geocoder geocoder = new Geocoder(Search.this);
        List<Address> list = new ArrayList<>();
        try{
            list = geocoder.getFromLocationName(searchString, 1);
        }catch (IOException e){
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage() );
        }

        if(list.size() > 0){
            Address address = list.get(0);

            Log.d(TAG, "geoLocate: found a location: " + address.toString());
            //Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();

            if(!address.getPostalCode().equals("null")){
                redPincodeSearch=address.getPostalCode();
            }
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
