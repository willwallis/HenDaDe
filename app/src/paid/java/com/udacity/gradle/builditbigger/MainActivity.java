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
import com.knewto.android.jokes.WillJokes;
import com.knewto.android.jokeactivity.JokeActivity;


public class MainActivity extends ActionBarActivity {

    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create Loading indicator and hide
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
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

    public void tellJoke(View view){
        spinner.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask().execute();

        new EndpointsAsyncTask() {
            @Override
            protected void onPostExecute(String result) {
                if (result != null) {
                    Intent myIntent = new Intent(MainActivity.this, JokeActivity.class);
                    myIntent.putExtra(NEW_JOKE, result);

                    startActivity(myIntent);
                   // startActivity(JokePresenterActivity.launchIntent(MainActivity.this, s));
                } else {
                    Toast.makeText(MainActivity.this, "No Jokes Returned", Toast.LENGTH_LONG).show();
                }
            }
        }.execute();

    }

}
