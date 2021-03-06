/*
 *
 *
 *  *
 *  *  * Copyright (C)  2015 Marcos Ambrosi marcosambrosi@gmail.com
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *  *
 *
 */

package marcosambrosi.mmovies.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import marcosambrosi.mmovies.MoviesApplication;
import marcosambrosi.mmovies.R;
import marcosambrosi.mmovies.fragment.MovieListFragment;
import marcosambrosi.mmovies.model.Configuration;
import marcosambrosi.mmovies.network.ServiceController;
import marcosambrosi.mmovies.util.Constants;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.splash_screen);


        getWindow().setBackgroundDrawable(getDrawable(R.drawable.window_background));

        if (MoviesApplication.getInstance().hasConfiguration()) {

            String configString = MoviesApplication.getInstance().
                    getPreference(Constants.PREFERENCES.CONFIG);

            Configuration configuration = Configuration.fromJsonString(configString);

            MoviesApplication.getInstance().setConfiguration(configuration);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    listOnCreate();
                }
            }, 2000);

        } else {


            ServiceController.getInstance().configuration(new Callback<Configuration>() {
                @Override
                public void success(Configuration configuration, Response response) {
                    MoviesApplication.getInstance().addPreference(Constants.PREFERENCES.CONFIG,
                            configuration.toJsonString());
                    MoviesApplication.getInstance().setConfiguration(configuration);
                    listOnCreate();
                }

                @Override
                public void failure(RetrofitError error) {
                    //Handle this
                }
            });
        }


    }

    private void listOnCreate() {
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        viewPager.setAdapter(new SectionPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        getWindow().setBackgroundDrawable(null);
    }

    public class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return MovieListFragment.newInstance(MovieListFragment.TYPE_DISCOVER);
                case 1:
                    return MovieListFragment.newInstance(MovieListFragment.TYPE_LATEST);
                default:
                    return MovieListFragment.newInstance(MovieListFragment.TYPE_DISCOVER);
            }

        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Discover";
                case 1:
                default:
                    return "Latest";
            }
        }
    }

}
