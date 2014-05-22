package com.tchart.apptag;

//import android.util.Log;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.DependsOn;
import anywheresoftware.b4a.BA.Permissions;

import co.spark.core.AppTagControlConnection;
import co.spark.core.AppTagControlListener;
import co.spark.core.ConnectionStartedException;

@BA.Author("Trevor Hart")
@BA.ShortName("AppTagControlConnection")
@BA.Version(1.0F)

@DependsOn(values = {"apptagsdk"})
@Permissions(values = {"android.permission.RECORD_AUDIO","android.permission.WRITE_EXTERNAL_STORAGE"})

public class AppTagControlConnectionWrapper extends AbsObjectWrapper<AppTagControlConnection>
{
	private AppTagControlConnection connection;
	
	/**
	* AppTag converts your Smartphone into a Gaming Peripheral.
	*
	* See http://www.hex3.co/products/apptag for more detail.
	*
	*/
	public static void LIBRARY_DOC()
	{
	}	 
	
	/**
	* Initializes the AppTagControlConnection and creates the listener from the event name.
	*/
	public void Initialize(final BA ba, final String EventName)
	{	
		System.loadLibrary("gdx-audio");
		
		this.connection = new AppTagControlConnection();
		
		final String eventName = EventName.toLowerCase(BA.cul);
		
		connection.setListener(new AppTagControlListener()
		{
			/**
			* Invoked after pressing or releasing the trigger.
			*/
			public void triggerStateChangedTo(boolean isPressed, boolean isOutdoor) {                
				ba.raiseEventFromUI(this, eventName + "_triggerstatechangedto", new Object[] { isPressed,isOutdoor });
				//Log.i("B4A", eventName + "_triggerStateChangedTo");
			}		
				
			/**
			* Invoked after changing id.
			*/
			public void setPlayerId(int playerId) {
				ba.raiseEventFromUI(null, eventName + "_setplayerid", new Object[] { playerId });
			}
			
			/**
			* Invoked after player was hit by another player with id 'playerId'.
			*/
			public void hitByAction(int playerId) {
				ba.raiseEventFromUI(null, eventName + "_hitbyaction", new Object[] { playerId });
			}
            
			/**
			* Invoked after pressing or releasing the reload button.
			*/
			public void reloadStateChangedTo(boolean isPressed) {
				ba.raiseEventFromUI(null, eventName + "_reloadstatechangedto", new Object[] { isPressed });
			}
            
			/**
			* Invoked after pressing or releasing the red button.
			*/
			public void redButtonStateChangedTo(boolean isPressed) {
				ba.raiseEventFromUI(null, eventName + "_redbuttonstatechangedto", new Object[] { isPressed });
			}			
            
			/**
			* Invoked after pressing or releasing the green button.
			*/
			public void greenButtonStateChangedTo(boolean isPressed) {
				ba.raiseEventFromUI(null, eventName + "_greenbuttonstatechangedto", new Object[] { isPressed });                
			}
            
			/**
			* Invoked after pressing or releasing the blue button.
			*/
			public void blueButtonStateChangedTo(boolean isPressed) {
				ba.raiseEventFromUI(null, eventName + "_bluebuttonstatechangedto", new Object[] { isPressed });                
			}
			
			/**
			* Invoked after pressing or releasing the yellow button.
			*/			
			public void yellowButtonStateChangedTo(boolean isPressed) {
				ba.raiseEventFromUI(null, eventName + "_yellowbuttonstatechangedto", new Object[] { isPressed });
			}

			public void appTagControlError() {				
				//initConnection();
			}
		});
	}
	
	/**
	* 
	*/
    public boolean IsInitialized()
    {
      return (this.connection != null);
    } 
    
    /**
	* Stops the signal receiver.
	*/
    public void Stop()
    {
    	if (this.connection != null) this.connection.stop();    	
    }
    
    /**
	* A Boolean value that indicates whether the Sound Detector is running or not
	* (means that the framework is currently listening to the sound and trying to
	* detect the signal received from the device)
	*/
    public boolean IsStarted()
    {
    	return this.connection.isStarted();
    }
    
    /**
	* Starts the signal receiver.
	*/    
    public boolean Start()
    {
    	if (this.connection != null && this.connection.isStarted() == false)
    	{
			try
			{
				this.connection.start();
				return true;
			}
			catch (ConnectionStartedException e)
			{
				return false;
			}
    	}
		return false;    	
    }
    
    /**
	* Indicates whether the blue button pressed or not.
	*/
    public boolean isBlueButtonPressed()
    {
    	return this.connection.isBlueButtonPressed();
    }
    
    /**
	* Indicates whether the green button pressed or not.
	*/
    public boolean isGreenButtonPressed()
    {
    	return this.connection.isGreenButtonPressed();
    }
    
    /**
	* Indicates whether the red button pressed or not..
	*/
    public boolean isRedButtonPressed()
    {
    	return this.connection.isRedButtonPressed();
    }
    
    /**
	* Indicates whether the reload button pressed or not.
	*/
    public boolean isReloadButtonPressed()
    {
    	return this.connection.isReloadButtonPressed();
    }
    
    /**
	* Indicates whether the trigger button pressed or not.
	*/
    public boolean isTriggerButtonPressed()
    {
    	return this.connection.isTriggerButtonPressed();
    }
    
    /**
	* Indicates whether the yellow button pressed or not.
	*/
    public boolean isYellowButtonPressed()
    {
    	return this.connection.isYellowButtonPressed();
    }    
}
