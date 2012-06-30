package com.svgonbatik.forgetcnot;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

public class splashscreen extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Thread splashThread = new Thread(){ 
        	@Override
        	public void run() {
        		try {
        			int waited = 0;
        			while (waited < 5000) {
        				sleep(100);
        				waited += 100;
        				}
        			}
        		catch(InterruptedException e) {
        			// do nothing
        			}
        		finally {
        			System.out.println("In the finally clause");
        			finish();
        			Intent i = new Intent();
        			i.setClassName("com.svgonbatik.forgetcnot", "com.svgonbatik.forgetcnot.GoogleMapsActivity");
        			startActivity(i);
        			}
        		}
        	};
        	splashThread.start();
	}
}
