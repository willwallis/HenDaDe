package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.knewto.android.jokes.WillJokes;
import com.knewto.android.jokeactivity.JokeActivity;


public class MainActivity extends ActionBarActivity {

    InterstitialAd mInterstitialAd;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Code to load interstitial ad
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                goToJoke();
            }
        });

        // Create Loading indicator and hide
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);

        requestNewInterstitial();
    }

    // Required to hide loading indicator if user navigates from joke using back button
    @Override
    protected void onResume() {
        super.onResume();
        spinner.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            goToJoke();
        }
    }

    public void goToJoke(){
        spinner.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask().execute();

        new EndpointsAsyncTask() {
            @Override
            protected void onPostExecute(String result) {
                if (result != null) {
                    Intent myIntent = new Intent(MainActivity.this, JokeActivity.class);
                    myIntent.putExtra(NEW_JOKE, result);
                    startActivity(myIntent);
                } else {
                    Toast.makeText(MainActivity.this, "No Jokes Returned", Toast.LENGTH_LONG).show();
                }
            }
        }.execute();


    }

    // See https://developers.google.com/admob/android/interstitial
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

}
