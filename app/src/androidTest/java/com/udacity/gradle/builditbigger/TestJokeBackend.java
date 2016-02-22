package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.TimeUnit;

/**
 * Created by willwallis on 2/21/16.
 */
public class TestJokeBackend extends AndroidTestCase {

    public void testJokeDownload() {

        try {
            EndpointsAsyncTask task = new EndpointsAsyncTask();
            task.execute();
            String result = task.get(30, TimeUnit.SECONDS);

            assertTrue(result.length() > 0);

        } catch (Exception e) {
            fail("No response from server");
        }
    }

}
