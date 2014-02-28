package com.gikdew.facebookhacker;

import com.appnext.appnextsdk.Appnext;
import com.appnext.appnextsdk.PopupClickedInterface;
import com.appnext.appnextsdk.PopupClosedInterface;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

public class MainActivity extends Activity {

	String passwords[] = {"1Kitty","jelly22fish","AllBlacks!","JackBauer","Salamanca",
			   "housedoctor","IloveMypiano","likelike","iEatPizza", "lesson24", "cstfttt", "pusou", "itsfwi",
			   "1234sex1234", "7964monkey", "letmein", "trustno1", "dragonBorn", "baseballteam", "fuckme", 
			   "5cheese", "grey50sex", "mynameisUH", "wtFoxsay?", "savethemoon23", "moneymoney", 
			   "jabs532", "mobil3phone", "bioFrutas", "samsungS6"};
	public static int c=1;
	String email = "";
	private EmailValidator emailvalidator = new EmailValidator();
	private randomString randomString = new randomString();
	EditText email_et;
	ProgressDialog progressBar;
	CountDownTimer countDown;
	String buttonText = "", buttonText1 = "";
	Drawable icon, icon1;
	Appnext appnext;
	int number = 0;
	SharedPreferences settings;
	Editor editor;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);		
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD){
		    requestWindowFeature(Window.FEATURE_NO_TITLE); 
		    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}

		setContentView(R.layout.activity_main);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
		    android.app.ActionBar actionBar = getActionBar();
		    actionBar.hide();

		}
		

		DisplayMetrics metrics = new DisplayMetrics();
		((WindowManager) getBaseContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);    
	    final Double h = (double) metrics.heightPixels;
	    final Double w = (double) metrics.widthPixels;
	    
	    settings = getSharedPreferences("settings", Context.MODE_PRIVATE);
	    editor = settings.edit();	
	    
	    
        if(Math.random()>0.5){
        	number = 0;
        }else{
        	number = 1;
        }
	    
	    final Typeface font = Typeface.createFromAsset(getAssets(), "fonts/facebook.otf");
	    
	   if(!settings.contains("notClicked")){
        	editor.putInt("notClicked", number);
        	editor.commit();
	    }
	    
	    appnext = new Appnext(this);
		appnext.setPopupClickedCallback(new PopupClickedInterface() { 
			 @Override 
			 public void popupClicked() { 
				 Log.v("appnext", "popup clicked"); 
				 editor.putInt("notClicked", 1);
		         editor.commit();
		         
			 
			 } 
			 }); 

	    appnext.setAppID("890958f2-0d54-47b0-9e15-dec4fbbdc9c7");	    
		appnext.showBubble();    
	    
		checkAdClosed();
		
		RelativeLayout.LayoutParams params;
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.Relativ);
        rl.setBackgroundResource(R.drawable.background);       

       
        email_et = new EditText(this);
        email_et.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        email_et.setText("example@gmail.com");
        email_et.setTextColor(Color.WHITE);
        
        params = new RelativeLayout.LayoutParams((int)(w * 0.80), (int)(h * 0.08));
        params.leftMargin = (int)(w*0.1);
        params.topMargin = (int)(h*0.42);
        rl.addView(email_et, params);
        
        final ImageView skull_iv = new ImageView(this);
        skull_iv.setImageResource(R.drawable.skull_icon);
        params = new RelativeLayout.LayoutParams((int)(w * 0.90), (int)(h * 0.20));
        params.leftMargin = (int)(w*0.05);
        params.topMargin = (int)(h*0.10);
        rl.addView(skull_iv, params);
        
        final Button title_bt = new Button(this);      
        title_bt.setText("facebook hacker");     
        title_bt.setTextSize((float) (h/25));
        title_bt.setTextColor(Color.WHITE);
        title_bt.setTypeface(font);
        title_bt.getBackground().setAlpha(0);
        params = new RelativeLayout.LayoutParams((int)(w * 0.90), (int)(h * 0.20));
        params.leftMargin = (int)(w*0.05);
        params.topMargin = (int)(h*0.25);
        rl.addView(title_bt, params);
        
        final Button hack_bt = new Button(this);      
        hack_bt.setText("Hack Account");        
        icon = getApplicationContext().getResources().getDrawable( R.drawable.ic_action_skull_icon);
        icon1 = getApplicationContext().getResources().getDrawable( R.drawable.ic_action_skull_icon);
        icon.setBounds(15, 0, 60, 45);
        icon1.setBounds(-15,0,30,45);
        hack_bt.setCompoundDrawables(icon, null, icon1, null);
        hack_bt.setTypeface(font);
        hack_bt.setTextColor(Color.WHITE);
        hack_bt.setBackgroundResource(R.drawable.button_flat_c);
        params = new RelativeLayout.LayoutParams((int)(w * 0.80), (int)(h * 0.08));
        params.leftMargin = (int)(w*0.1);
        params.topMargin = (int)(h*0.52);
        rl.addView(hack_bt, params);
        
        final TextView progress_bt = new TextView(this);      
        progress_bt.setText("");
        progress_bt.setTextSize((float) (h*0.05));
        progress_bt.setTextColor(Color.WHITE);
        progress_bt.setSingleLine();
        progress_bt.setClickable(false);
        progress_bt.setTypeface(font);
        params = new RelativeLayout.LayoutParams((int)(w * 1.2), (int)(h * 0.10));
        params.leftMargin = (int)(-w*0.1);
        params.topMargin = (int)(h*0.62);
        rl.addView(progress_bt, params);
        
        final TextView progress_bt1 = new TextView(this);     
        progress_bt1.setText("");
        progress_bt1.setTextSize((float) (h*0.05));
        progress_bt1.setTextColor(Color.WHITE);
        progress_bt1.setSingleLine();
        progress_bt1.setClickable(false);
        progress_bt1.setTypeface(font);
        params = new RelativeLayout.LayoutParams((int)(w * 1.5), (int)(h * 0.10));
        params.leftMargin = (int)(-w*0.5);
        params.topMargin = (int)(h*0.72);
        rl.addView(progress_bt1, params);
        
        final Button share_bt = new Button(this);
        share_bt.setText("Share");
        share_bt.setTextColor(Color.WHITE);
        share_bt.setTypeface(font);
        share_bt.setBackgroundResource(R.drawable.button_flat_c);
        icon = getApplicationContext().getResources().getDrawable( R.drawable.ic_action_share);
        icon.setBounds(15, 0, 60, 45);
        share_bt.setCompoundDrawables(icon, null, null, null);
        params = new RelativeLayout.LayoutParams((int)(w * 0.78/2), (int)(h * 0.08));
        params.leftMargin = (int)(w*0.1);
        params.topMargin = (int)(h*0.85);
        rl.addView(share_bt, params);
        
        final Button other_bt = new Button(this);
        other_bt.setText("Apps");
        other_bt.setTextColor(Color.WHITE);
        other_bt.setTypeface(font);
        other_bt.setBackgroundResource(R.drawable.button_flat_c);
        icon = getApplicationContext().getResources().getDrawable( R.drawable.ic_action_view_as_grid);
        icon.setBounds(15, 0, 60, 45);
        other_bt.setCompoundDrawables(icon, null, null, null);
        params = new RelativeLayout.LayoutParams((int)(w * 0.78/2), (int)(h * 0.08));
        params.leftMargin = (int)(w*0.12+(w*0.78/2));
        params.topMargin = (int)(h*0.85);
        rl.addView(other_bt, params);
        
        final Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("You have to rate this app with 5 stars in Google Play Store to work properly." + '\n' + "Please rate 5 stars now.")
        	   .setCancelable(false)
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                        editor.putInt("notRated", 1);
                        editor.commit();
                        Uri uri = Uri.parse("market://details?id=" + getPackageName());
                        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
                        try {
                            startActivity(myAppLinkToMarket);
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(getApplicationContext(), "Unable to find Google Play app.", Toast.LENGTH_LONG).show();
                        }
                   }
               });
       
        
        if(!settings.contains("notRated")){
        	editor.putInt("notRated", 0);
        	editor.commit();
        }
	    
	    if(settings.getInt("notRated", 0)==0){
	    	 AlertDialog rateDialog = builder.create();
	         rateDialog.show();
	    }
	    
	    
        
        rl.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				hideKeyboard();				
			}
		});
        
        hack_bt.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				buttonText = randomString.getRandomString(60);
				c = 1;
				email = email_et.getText().toString();
				hideKeyboard();					
				String password = passwords[(int) Math.floor(Math.random()*passwords.length)];
				if(emailvalidator.validate(email)){
					new CountDownTimer(9000,30) {
						
						@Override
						public void onTick(long millisUntilFinished) {
							if(c==1){							    
							   	buttonText = buttonText.substring(1);
						    	buttonText += randomString.getRandomString(1);
							    
						    	if(Math.random()>0.5){
							    progress_bt.setText(buttonText);
							    }else if(Math.random()>0.2){	
							    progress_bt1.setText(buttonText);
							    }
							}else{
								progress_bt.setText(" ");
								progress_bt1.setText(" ");
							}						
						}
						
						@Override
						public void onFinish() {
							c=1;
							
						}
					}.start();
					new AsyncClass(MainActivity.this, password, settings, editor, number).execute();					
				}else{
					Toast.makeText(getApplicationContext(), "The email is not valid.", Toast.LENGTH_SHORT).show();
				}			
			}       
       
        });
        
        share_bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT, "Get facebook passwords of your friends with this app: " + '\n' +"https://play.google.com/store/apps/details?id=" + getPackageName());
				sendIntent.setType("text/plain");
				startActivity(Intent.createChooser(sendIntent, "Share to..."));
				
			}
		});
        
        other_bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("market://search?q=pub:Gikdew");
                Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(myAppLinkToMarket);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Unable to find Google Play app.", Toast.LENGTH_LONG).show();
                }
				
			}
		});
        
	}
	
	private void checkAdClosed() {
		appnext.setPopupClosedCallback(new PopupClosedInterface() { 
			@Override  
			public void popupClosed() {  
				Log.v("appnext", "popup closed");
				Builder builder1 = new AlertDialog.Builder(MainActivity.this);
		        builder1.setMessage("You have to click 'Install' in one Ad onder to get the app fully working." + '\n' + "Please rate 5 stars now.")
		        	   .setCancelable(false)
		               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		                   public void onClick(DialogInterface dialog, int id) {
		                	   if(settings.getInt("notClicked", 0)==0){
			                	   	appnext = new Appnext(MainActivity.this);
			                	   	appnext.setPopupClickedCallback(new PopupClickedInterface() { 
			               			 @Override 
			               			 public void popupClicked() { 
			               				 Log.v("appnext", "popup clicked"); 
			               				 editor.putInt("notClicked", 1);
			               		         editor.commit();
			               		         
			               			 
			               			 } 
			               			 }); 
			                	    appnext.setAppID("890958f2-0d54-47b0-9e15-dec4fbbdc9c7");	    
			                		appnext.showBubble(); 		                		
			                		checkAdClosed();
		                		}
		                      }
		               });   
		        
			    
			    
			    if(settings.getInt("notClicked", 0)==0){
			    	 AlertDialog clickDialog = builder1.create();
			         clickDialog.show();
			    }
				} 
			});
		
	}

	protected void hideKeyboard() {
		InputMethodManager imm = (InputMethodManager)getSystemService(
			      Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(email_et.getWindowToken(), 0);		
	}	
	
	@Override
	public void onBackPressed() {	
		new AlertDialog.Builder(this)
        .setMessage("Are you sure you want to exit?")
        .setCancelable(false)
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {            	   
                MainActivity.this.finish();
                
            }
        })
        .setNegativeButton("No", null)
        .show();
    	
	}
		
}




