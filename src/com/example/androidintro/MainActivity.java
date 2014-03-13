package com.example.androidintro;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

   private static final int DIALOG_ABOUT = 0;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      if (savedInstanceState == null) {
         getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
      }

      getActionBar().setDisplayHomeAsUpEnabled(true);
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {

      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }

   @Override
   protected Dialog onCreateDialog(int arg0) {
      if (arg0 == DIALOG_ABOUT) {
         return new AlertDialog.Builder(this)
            .setTitle("Android Intro")
            .setMessage("This is a demo app")
            .create();
      }
      
      return super.onCreateDialog(arg0);
   }
   
   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      int id = item.getItemId();
      
      if (id == R.id.action_settings) { return true; }
      
      if (id == R.id.action_second) {
         onSecondClick(null);
         return true;
      }
      
      if (id == android.R.id.home) {
         finish(); // close if up arrow clicked
         return true;
      }

      if (id == R.id.action_about) {
         showDialog(DIALOG_ABOUT);
         return true;
      }
      
      return super.onOptionsItemSelected(item);
   }

   /**
    * A placeholder fragment containing a simple view.
    */
   public static class PlaceholderFragment extends Fragment {

      public PlaceholderFragment() {
      }

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.fragment_main, container, false);
         return rootView;
      }
   }

   public void onButtonClick(View v) {
      // if (true) throw new IllegalStateException("oh no!"); // demo force
      // close
      Toast.makeText(this, "Hello, world!", Toast.LENGTH_LONG).show();
      // Log.d("main", "Hello from onButtonClick!"); // demo logging
   }

   public void onWebClick(View v) {
      Intent i = new Intent();
      i.setAction(Intent.ACTION_VIEW);
      i.setData(Uri.parse("http://developer.android.com"));
      startActivity(i);
   }

   public void onDialClick(View v) {
      Intent i = new Intent();
      i.setAction(Intent.ACTION_DIAL);
      i.setData(Uri.parse("tel:1234567"));
      startActivity(i);
   }

   public void onSecondClick(View v) {
      Intent i = new Intent(this, SecondActivity.class);
      startActivity(i);
   }
}
