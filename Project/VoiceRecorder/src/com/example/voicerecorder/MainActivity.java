package com.example.voicerecorder;

import java.io.File;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	private MediaPlayer mediaPlayer;
	private MediaRecorder recorder;
    private String OUTPUT_FILE;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        OUTPUT_FILE=Environment.getExternalStorageDirectory()+"/audiorecorder.3gpp";
    }
    public void buttonTapped(View view){
    	switch(view.getId()){
    	
    	case R.id.StartRecord:
    		try {
    			beginRecording();
    			}
    		catch (Exception e){
    				e.printStackTrace();
    			}
    	break;
    	
    	case R.id.StopRecord:
    		try {
    			stopRecording();
    			}
    			catch (Exception e){
    				e.printStackTrace();
    			}
    	break;
    	case R.id.StartPlay:
    		try{
    		playRecording();
    		}
    		catch (Exception e){
				e.printStackTrace();
			}
	break;
    	case R.id.StopPlay:
    		try{
    			stopPlayback();
    		}
    		catch (Exception e){
				e.printStackTrace();
			}
	break;
    	
    	}
    }
	private void stopPlayback() {
		if(mediaPlayer != null)
			mediaPlayer.stop();
		
	}
	private void playRecording() throws Exception{
		
		ditchMediaRecorder();
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setDataSource(OUTPUT_FILE);
		mediaPlayer.prepare();
		mediaPlayer.start();
		
		}
	private void ditchMediaPlayer(){
		if(mediaPlayer != null)
		{ try{
			mediaPlayer.release();
		} catch(Exception e){
			
		e.printStackTrace();
		}
		
	}
		
	}
	private void stopRecording() {
		if (recorder != null)
			recorder.stop();		
	}
	private void beginRecording() throws Exception{
		
		ditchMediaRecorder();
		File outFile = new File(OUTPUT_FILE);
		
		if(outFile.exists())
			outFile.delete();
		
		recorder = new MediaRecorder();
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);  
		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		recorder.setOutputFile(OUTPUT_FILE);
		recorder.prepare();
		recorder.start();
		
			
	}
		private void ditchMediaRecorder(){
			if(recorder != null)
			     recorder.release();
		
			
		}
		
	}

