package com.gikdew.facebookhacker;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.SystemClock;

import com.appnext.appnextsdk.Appnext;
import com.gikdew.facebookhacker.MainActivity;

public class AsyncClass extends AsyncTask<Void, String, Void> {
    private Context context;
    ProgressDialog dialog;
    String password;
    Appnext appnext;
    SharedPreferences settings_sh;
    Editor editor_ed;
    int number_in;

        public AsyncClass(Context cxt, String password1, SharedPreferences settings, Editor editor, int number) {
            context = cxt;
            settings_sh = settings;
            editor_ed = editor;
            number_in = number;
            appnext = new Appnext(context);
            dialog = new ProgressDialog(context);
            password = password1;
            
        }

       	@Override
        protected void onPreExecute() {
        	dialog.setCancelable(false);
			dialog.setMessage("Hacking...");
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.setProgress(1);
			dialog.setMax(100);
			dialog.show();
			
        }

        @Override
        protected Void doInBackground(Void... unused) {
            SystemClock.sleep((long) (Math.random()*4000+4000));
            return (null);
        }

        @Override
        protected void onPostExecute(Void unused) {
        	MainActivity.c = 0;
            dialog.cancel();
            Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("The Facebook Account has been hacked successfully." + '\n' + "Password: " + password)
            	   .setTitle("Facebook Password Hacker")
                   .setCancelable(false)
                   .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {
                    	   appnext.setAppID("890958f2-0d54-47b0-9e15-dec4fbbdc9c7");	    
	                	   appnext.showBubble(); 	
                       }
                   });
            AlertDialog alert = builder.create();
            alert.show();
            
        }
    }