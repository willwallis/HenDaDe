package com.knewto.android.jokes;

public class WillJokes {
    public String tellJoke(){
        double chooser = Math.random() * 10;
        String theJoke;

        if(chooser <5) {
            theJoke = "Can a kangaroo jump higher than a house? Of course, a house doesn’t jump at all.";
        }
        else {
            theJoke = "No joke for you!";
        }

        return theJoke;
    }
}