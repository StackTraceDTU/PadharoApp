package www.jwalin.com.rjv1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
//import com.facebook.CallbackManager;
//import com.facebook.FacebookCallback;
//import com.facebook.FacebookException;
//import com.facebook.FacebookSdk;
//import com.facebook.GraphRequest;
//import com.facebook.GraphResponse;
//import com.facebook.appevents.AppEventsLogger;
//import com.facebook.login.LoginResult;
//import com.facebook.login.widget.LoginButton;


import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
   // CallbackManager callbackManager;
    String url = "http://192.168.43.169:4000/";
    private AdapterForPlaces adapter;
   public ArrayList<Places> placesList=new ArrayList<Places>();

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    // private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     //     */
//    private static final String[] DUMMY_CREDENTIALS = new String[]{
//            "foo@example.com:hello", "bar@example.com:world"
//    };

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    // private UserLoginTask mAuthTask = null;

//    // UI references.
//    private AutoCompleteTextView mEmailView;
//    private EditText mPasswordView;
//    private View mProgressView;
//    private View mLoginFormView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getUserDetails(loginResult);
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });*/
    }

//    protected void getUserDetails(LoginResult loginResult) {
//        GraphRequest data_request = GraphRequest.newMeRequest(
//                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(
//                            JSONObject json_object,
//                            GraphResponse response) {
//                        //String url=http://192.168.43.169:4000/
//                        final Intent intent = new Intent(LoginActivity.this, Home.class);
//                        String s=json_object.toString();
//                        JSONArray jsonArray=new JSONArray();
//                        try {
//                            jsonArray = new JSONArray(s);
//                        }
//                        catch (JSONException e){
//                            e.printStackTrace();
//                        }
//                        intent.putExtra("userProfile", json_object.toString());
//
//                        JsonArrayRequest placesreq = new JsonArrayRequest(Request.Method.POST, url,jsonArray,
//                                new Response.Listener<JSONArray>() {
//                                    @Override
//                                    public void onResponse(JSONArray response) {
//                                        // Log.d(TAG, response.toString());
//                                        //hidePDialog();
//
//                                        // Parsing json
//                                        for (int i = 0; i < response.length(); i++) {
//                                            try {
//
//                                                JSONObject obj = response.getJSONObject(i);
//                                                Places places = new Places();
//                                                places.setName(obj.getString("title"));
//                                                places.setThumbnail(obj.getString("image"));
//                                                places.setRating(((Number) obj.get("rating"))
//                                                        .floatValue());
//
//
//                                                places.setType(obj.getString("type"));
//
//
//                                                placesList.add(places);
//
//                                            } catch (JSONException e) {
//                                                e.printStackTrace();
//                                            }
//
//                                        }
//
//                                        intent.putExtra("Listofplaces",placesList);
//                                        // notifying list adapter about data changes
//                                        // so that it renders the list view with updated data
//                                        //adapter.notifyDataSetChanged();
//                                    }
//                                }, new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //VolleyLog.d(TAG, "Error: " + error.getMessage());
//                                //hidePDialog();
//
//                            }
//                        });
//
//                        // Adding request to request queue
//                        AppController.getInstance().addToRequestQueue(placesreq);
//
//                        startActivity(intent);
//                    }
//
//                });
//        Bundle permission_param = new Bundle();
//        permission_param.putString("fields", "id,name,email,picture.width(120).height(120)");
//        data_request.setParameters(permission_param);
//        data_request.executeAsync();
//
//    }
//
//
//
//
//        protected void onResume() {
//            super.onResume();
//            // Logs 'install' and 'app activate' App Events.
//            AppEventsLogger.activateApp(this);
//        }
//        @Override
//        protected void onPause() {
//            super.onPause();
//            // Logs 'app deactivate' App Event.
//            AppEventsLogger.deactivateApp(this);
//        }

        public void getdata(View view) {

            JSONArray jsonArray=new JSONArray();
            String s="[{id:shivam}]";
            Log.v("button","Sahi se press kiya hai");
            JSONObject jsonObject=new JSONObject();
            try{
                jsonArray=new JSONArray(s);
               // jsonObject=new JSONObject(s);
            }
            catch (JSONException e){
                e.printStackTrace();
            }
            Intent intent=new Intent(LoginActivity.this,Home.class);

            JsonArrayRequest placesreq = new JsonArrayRequest(Request.Method.POST, url+"users",jsonArray,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                             Log.d("Array", "LOL chal gaya");
                            //hidePDialog();

//                            JSONArray response=new JSONArray();
//                            try{
//                                response=new JSONArray(iresponse);
//                            }
//                            catch (JSONException e){
//                                e.printStackTrace();
//                            }
                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    Places places = new Places();
                                    places.setName(obj.getString("name"));
                                    places.setThumbnail(obj.getString("image"));
                                    places.setRating(((Number) obj.get("rating"))
                                            .floatValue());
                                    places.setDescription(obj.getString("description"));
                                   // places.setRewardPoints();


                                    places.setType(obj.getString("class_name"));
                                    //

                                    placesList.add(places);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            //intent.putExtra("Listofplaces",placesList);
                            // notifying list adapter about data changes
                            // so that it renders the list view with updated data
                            //adapter.notifyDataSetChanged();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d("LOL", "Error: " + error.getMessage());
                    //hidePDialog();
                }
               });//{
//
//                        @Override
//                        protected Map<String,String> getParams(){
//                        Map<String,String> params = new HashMap<String, String>();
//                        params.put("id","Shivam");
//
//
//                        return params;
//
//                }


            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(placesreq);
           //
            // Toast.makeText(getApplicationContext(),placesList.get(0).getName(),Toast.LENGTH_SHORT);
            Integer i=placesList.size();
            String S;
            S="0";
            if(i!=0)S="1";
            Log.v("Size og places list",S);
            intent.putExtra("placeslist",placesList);
            startActivity(intent);
        }


}


//public class Requestfunction{
//    JsonArrayRequest placeslist=new JsonArrayRequest(url,new Response.Listener<JSONArray>(){
//        @Override
//        public void onResponse(JSONArray){
//
//        }
//        })
//        }