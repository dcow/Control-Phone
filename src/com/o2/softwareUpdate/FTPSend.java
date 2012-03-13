package com.o2.softwareUpdate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;

import android.util.Log;

public class FTPSend extends Thread {

	FTPClient mConnection;
	InputStream mData;
	
	public FTPSend(FTPClient client, InputStream data) {
		super();
		mData = data;
		mConnection = client;
	}
	
	public FTPSend() {
	}
	
	@Override
	public void run() {
		
		try
		{
		    mConnection.connect("FTP_SERVER");
		    if (mConnection.login("USER_NAME", "PASSWORD"))
		    {
		        mConnection.enterLocalPassiveMode();
		        boolean result = mConnection.storeFile("test.txt", mData);
		        mData.close();
		        if (result) Log.v("upload result", "succeeded");
		    }
		    else
		    {
		    	Log.v("upload result", "could not log in to server");
		    }
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}


		try
		{
		    mConnection.logout();
		    mConnection.disconnect();
		}
		catch (IOException e)
		{
		    e.printStackTrace();
		}
		
	}
	
	
	
		
}
