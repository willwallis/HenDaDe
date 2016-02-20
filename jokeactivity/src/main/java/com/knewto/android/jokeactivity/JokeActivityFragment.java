package com.knewto.android.jokeactivity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class JokeActivityFragment extends Fragment {

    public JokeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke, container, false);
        TextView jokeText = (TextView) view.findViewById(R.id.JokeSpace);

        // Getting joke from activity so can retain during rotation
        JokeActivity activity = (JokeActivity) getActivity();
        String activityJoke = activity.getMyJoke();

        jokeText.setText(activityJoke);
        return view;

    }
}
