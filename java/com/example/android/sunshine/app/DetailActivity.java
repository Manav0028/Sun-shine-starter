package com.example.android.sunshine.app;

import android.arch.lifecycle.Lifecycle;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    public static String weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailFragment())
                    .commit();
        }
        setTitle("Detail Activity");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class DetailFragment extends Fragment {

        String weatherFragment;

        public DetailFragment() {
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

            TextView detailTV = rootView.findViewById(R.id.detail_text_view);

            Intent intent = getActivity().getIntent();

            if (intent != null && intent.hasExtra("DETAIL_WEATHER")) {
                weatherFragment = intent.getStringExtra("DETAIL_WEATHER");
                detailTV.setText(weatherFragment);
                weather = weatherFragment;
            }

            return rootView;
        }


        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            // Inflate the menu; this adds items to the action bar if it is present.
            inflater.inflate(R.menu.detailfragment, menu);
            // Retrieve the share menu item
//            MenuItem menuItem = menu.findItem(R.id.action_share);
//            // Get the provider and hold onto it to set/change the share intent.
//            ShareActionProvider mShareActionProvider =
//                    (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
//            // Attach an intent to this ShareActionProvider.  You can update this at any time,
//            // like when the user selects a new piece of com.example.android.sunshine.app.data they might like to share.
//            if (mShareActionProvider != null) {
//                mShareActionProvider.setShareIntent(createShareForecastIntent());
//            } else {
//                Log.d("DA", "Share Action Provider is null?");
//            }
        }

        private void createShareForecastIntent() {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            intent.setType("text/plain");
            Log.i("DETAIL_WEATHER", weather + "\n#SunShine\nBy Manav Sharma");
            intent.putExtra(Intent.EXTRA_TEXT, weather + "\n#SunShine\nBy Manav Sharma");
            startActivity(Intent.createChooser(intent, "Share Via"));
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            int id = item.getItemId();

            if (id == R.id.action_share) {
                createShareForecastIntent();
            }
            return true;
        }
    }
}