package com.rmanda.mobiletechtest;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by RMB on 2/23/18.
 */
public class MainActivityTest {

    @Rule

    public ActivityTestRule<MainActivity>mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    private  MainActivity mainActivity = null;
    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityActivityTestRule.getActivity();

    }

    @Test
    public  void  testLaunch(){

        View view = mainActivity.findViewById(R.id.progress);
        View viewb = mainActivity.findViewById(R.id.rv_list_items);
        View viewc = mainActivity.findViewById(R.id.relativeLayout);

        assertNotNull(view);
        assertNotNull(viewb);
        assertNotNull(viewc);}


       // public void scrollFullList() throws InterruptedException {
         //   ListAdapter adapter = mActivityRule.getActivity().findViewById(android.R.id.li);


    @After
    public void tearDown() throws Exception {

       mainActivity = null;
    }


}