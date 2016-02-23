package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.knewto.android.jokeactivity.JokeActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by willwallis on 2/20/16.
 */
public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    static final String NEW_JOKE = "newJoke";

    @Override
    protected String doInBackground(Void... voids) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
//                     options for running against local devappserver
//                     - 10.0.2.2 is localhost's IP address in Android emulator
//                     - turn off compression when running against local devappserver


// START LOCAL VERSION
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
// END LOCAL VERSION

            // end options for devappserver

                    // Server version - commented out for final task of testing against local GCE

// START SERVER VERSION
//                    .setRootUrl("https://nanodegree-jokester.appspot.com/_ah/api/");
// END SERVER VERSION

            myApiService = builder.build();
        }

//        context = params[0].first;
//        String name = params[0].second;

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

}