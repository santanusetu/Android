package com.santanu.sjsu.android.googleplusmini;

import java.io.IOException;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.AccountPicker;

import android.support.v7.app.ActionBarActivity;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener
{

	 static final int REQUEST_CODE_PICK_ACCOUNT = 1000;
     static final int REQUEST_CODE_RECOVER_FROM_AUTH_ERROR = 1001;
     static final int REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR = 1002; 
    
	 private static final String TAG = "Main Activity";
	 private static final int REQ_SIGN_IN_REQUIRED = 55664;
	 public static final String SCOPE = "https://www.googleapis.com/auth/plus.login";
	 
	 public static final String SCOPE_PLUSME = "https://www.googleapis.com/auth/plus.me";
	 public static final String SCOPE_PROFILE_READ = "https://www.googleapis.com/auth/plus.profiles.read";
	 public static final String SCOPE_EMAIL = "https://www.googleapis.com/auth/userinfo.email";
	 public static final String SCOPE_USERINFO_PROFILE = "https://www.googleapis.com/auth/userinfo.profile";
	 
	 public static final String SCOPE_CIRCLE_READ = "https://www.googleapis.com/auth/plus.circles.read";
	 
	 public static String mAccountName;
	 public static String accountName;
	 TextView helloButton;
	 TextView nameDisplay;
	 String mEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try{
			
		findViewById(R.id.button_token).setOnClickListener(this);
		nameDisplay = (TextView)findViewById(R.id.nameDisplay);
		
		Intent intent = AccountPicker.newChooseAccountIntent(null, null,
				new String[] { "com.google" }, true, null, null, null, null);
		startActivityForResult(intent, REQUEST_CODE_PICK_ACCOUNT);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
    public void onClick(View view)
	{   
		try
		{
		Log.i(TAG, "account name "+accountName + "  mAccountName  "+mAccountName);

		if (view.getId() == R.id.button_token) 
        {
			if((mEmail != null) && !mEmail.isEmpty())
			{
				new RetrieveTokenTask().execute(mEmail);
			}
			else{
				Toast.makeText(this, "Email not retrived. You need to go back and start again...", Toast.LENGTH_SHORT).show();
			}
        }

		}catch(Exception e)
		{
			e.printStackTrace();
		}
    }
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		try{
	    if (requestCode == REQUEST_CODE_PICK_ACCOUNT) {
	        // Receiving a result from the AccountPicker
	        if (resultCode == RESULT_OK) {
	            mEmail = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
	            // With the account name acquired, get the auth token
	            new RetrieveTokenTask().execute(mEmail);
	        } else if (resultCode == RESULT_CANCELED) {
	            // The account picker dialog closed without selecting an account.
	            // Notify users that they must pick an account to proceed.
	            Toast.makeText(this, "Please pick an account...", Toast.LENGTH_SHORT).show();
	        }
	    }
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	private class RetrieveTokenTask extends AsyncTask<String, Void, String>
	{
        @Override
        protected String doInBackground(String... params)
        {
            String accountName = params[0];

            String scopes = "oauth2:" + SCOPE_PLUSME +" "+SCOPE_PROFILE_READ+" "+SCOPE_USERINFO_PROFILE+" "+SCOPE_EMAIL+" "+SCOPE_CIRCLE_READ;
            
            String token = null;
            try {
                token = GoogleAuthUtil.getToken(getApplicationContext(), accountName, scopes);
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            } catch (UserRecoverableAuthException e) {
                startActivityForResult(e.getIntent(), REQ_SIGN_IN_REQUIRED);
            } catch (GoogleAuthException e) {
                Log.e(TAG, e.getMessage());
            }catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
            return token;
        }
 
        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);

            nameDisplay.setText("\n Hi You have logged in with \n"+ mEmail);
           ((TextView) findViewById(R.id.token_value)).setText("\n The generted access Token for your account is: \n" + s+ "\n");
            
            Intent myIntent = new Intent(MainActivity.this, TabViewerActivity.class);
            myIntent.putExtra("token", s);
            myIntent.putExtra("accountName", mEmail);
    		//myIntent.putExtra("key", value); //Optional parameters
    		MainActivity.this.startActivity(myIntent);
    		
        }
    } // end of Asynctask
	
}
