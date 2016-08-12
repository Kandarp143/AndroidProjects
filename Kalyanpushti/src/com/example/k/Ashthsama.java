package com.example.k;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.shreejienterpriseinc.kalyanpushti.R;

public class Ashthsama extends Activity {
	ImageView img;
	TextView header;
	MediaPlayer mp;
	private Timer myTimer;
	public int i=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.darshanimage);

		img = (ImageView) findViewById(R.id.imageView1);
		header = (TextView) findViewById(R.id.textView1);

		img.setScaleType(ScaleType.FIT_XY);

		Typeface font = Typeface.createFromAsset(this.getAssets(),
				"poorich.TTF");
		header.setTypeface(font);
		
		try{
    		
    		Calendar cal = Calendar.getInstance();

    		int Hrs = cal.get(Calendar.HOUR_OF_DAY);
    		int Min= cal.get(Calendar.MINUTE);

    		String s = "";
    		if(Min < 10)
    		{
    			s="0"+Min;
    		}
    		else
    		{
    			s= ""+Min;
    		}
    		int CurTime = Integer.parseInt(Integer.toString(Hrs) + s);
    			if(CurTime >=530  && CurTime < 800 )
    			{

    				mp = MediaPlayer.create(Ashthsama.this,R.raw.mangla);
    			          mp.start();
    			          mp.setLooping(true);
	
    			} 
    			if(CurTime >= 800 && CurTime < 930 ){

    				mp = MediaPlayer.create(Ashthsama.this,R.raw.shringar);
    			          mp.start();
    			          mp.setLooping(true);
	
    			}
    			if(CurTime >= 930 && CurTime < 1100 ){

    				mp = MediaPlayer.create(Ashthsama.this,R.raw.gwal);
    			          mp.start();
    			          mp.setLooping(true);

    			}
    			if(CurTime >= 1100 && CurTime < 1300 ){

    				mp = MediaPlayer.create(Ashthsama.this,R.raw.rajbhog);
    			          mp.start();
    			          mp.setLooping(true);
	
    			}
    			if(CurTime >= 1300 && CurTime < 1615 ){
 
    				mp = MediaPlayer.create(Ashthsama.this,R.raw.utthapan);
    			          mp.start();
    			          mp.setLooping(true);
	
    			}
    			if(CurTime >= 1615 && CurTime < 1645 ){
    			
    				mp = MediaPlayer.create(Ashthsama.this,R.raw.bhog);
    			          mp.start();
    			          mp.setLooping(true);
                }
    			if(CurTime >= 1645 && CurTime < 1830 ){
    				
    				mp = MediaPlayer.create(Ashthsama.this,R.raw.sandhya);
    			          mp.start();
    			          mp.setLooping(true);	
    			}
    			if(CurTime >= 1830 || CurTime < 530 ){
    				
    					mp = MediaPlayer.create(Ashthsama.this,R.raw.shayan);
    			          mp.start();
    			          mp.setLooping(true);  			      	    	            
    			}

    		}
    		
    		catch (NumberFormatException e) {
    		    Log.e("TAG", "Couldn't parse time");
    		} 
		
		myTimer = new Timer();
	    myTimer.schedule(new TimerTask() {
	        @Override
	        public void run() {
	            TimerMethod();
	        }

	    }, 0, 1000);	    	   
	}

	private void TimerMethod()
	{
	    //This method is called directly by the timer
	    //and runs in the same thread as the timer.

	    //We call the method that will work with the UI
	    //through the runOnUiThread method.
	    this.runOnUiThread(Timer_Tick);
	}

	private Runnable Timer_Tick = new Runnable() {
	    @Override
		public void run() {

	    	i++;
	    	//Log.d(TAG, "i="+i);

	    	try{
	    		
	    		Calendar cal = Calendar.getInstance();

	    		int Hrs = cal.get(Calendar.HOUR_OF_DAY);
	    		int Min= cal.get(Calendar.MINUTE);

	    		String s = "";
	    		if(Min < 10)
	    		{
	    			s="0"+Min;
	    		}
	    		else
	    		{
	    			s= ""+Min;
	    		}
	    		int CurTime = Integer.parseInt(Integer.toString(Hrs) + s);

	    			if(CurTime >=530  && CurTime < 800 )
	    			{
	    				
	    				if(i>9)
	    				{
	    					header.setText("");
	    					img.setImageResource(R.drawable.mangla);
	    				}
	    				else
	    				{
	    				header.setText("Mangla");
	    				img.setImageResource(R.drawable.mangla);	    				
	    	            //Toast.makeText(getBaseContext(), "time : "+CurTime, Toast.LENGTH_LONG).show();
	    				}
	    				
	    			} 
	    			if(CurTime >= 800 && CurTime < 930 ){
	    				
	    				if(i>9)
	    				{
	    					header.setText("");
	    					img.setImageResource(R.drawable.shringar);
	    				}
	    				else
	    				{
	    				header.setText("Shringar");
	    				img.setImageResource(R.drawable.shringar);
	    				}
	    				
	    			}
	    			if(CurTime >= 930 && CurTime < 1100 ){
	    				if(i>9)
	    				{
	    					header.setText("");
	    					img.setImageResource(R.drawable.gwal);
	    				}
	    				else
	    				{
	    				header.setText("Gwal");
	    				img.setImageResource(R.drawable.gwal);	 
	    				}
	    			}
	    			if(CurTime >= 1100 && CurTime < 1300 ){
	    				if(i>9)
	    				{
	    					header.setText("");
	    					img.setImageResource(R.drawable.rajbhog);
	    				}
	    				else
	    				{
	    				header.setText("Rajbhog");
	    				img.setImageResource(R.drawable.rajbhog);
	    				}	    				
	    			}
	    			if(CurTime >= 1300 && CurTime < 1615 ){
	    				if(i>9)
	    				{
	    					header.setText("");
	    					img.setImageResource(R.drawable.uthapan);
	    				}
	    				else
	    				{
	    				header.setText("Uthapan");
	    				img.setImageResource(R.drawable.uthapan);
	    				}
	    			}
	    			if(CurTime >= 1615 && CurTime < 1645 ){
	    				if(i>9)
	    				{
	    					header.setText("");
	    					img.setImageResource(R.drawable.bhog);
	    				}
	    				else
	    				{
	    				header.setText("Bhog");
	    				img.setImageResource(R.drawable.bhog);	    				
	    				}
	    			}
	    			if(CurTime >= 1645 && CurTime < 1830 ){
	    				if(i>9)
	    				{
	    					header.setText("");
	    					img.setImageResource(R.drawable.aarti);
	    				}
	    				else
	    				{
	    				header.setText("Aarti");
	    				img.setImageResource(R.drawable.aarti);
	    				}
	    			}
	    			if(CurTime >= 1830 || CurTime < 530 ){
	    				if(i>9)
	    				{
	    					header.setText("");
	    					img.setImageResource(R.drawable.shayan);
	    				}
	    				else
	    				{
	    				header.setText("Shayan");
	    				img.setImageResource(R.drawable.shayan);	    				
	    				}
	    			}    		
	    		}	    		
	    		catch (NumberFormatException e) {
	    		    Log.e("TAG", "Couldn't parse time");
	    		} 

	    }
	};

	@Override 
	public void onPause(){
	    super.onPause();

	        if(mp.isPlaying()){
	            mp.pause();
	        }

	}
	@Override
	 protected void onResume()
	    {
	       super.onResume();
	      
	      if(!mp.isPlaying()){
	          mp.start();
	      }
			
	    }
	@Override
	public void onDestroy() {
		 super.onDestroy();
		//Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
		//Log.d(TAG, "onDestroy");
		mp.stop();
	}


	
	@Override
	public void onBackPressed() {
		finish();
		startActivity(new Intent(getApplicationContext(), Darshan.class));
			
	}

}