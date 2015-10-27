package marcosambrosi.mmovies.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import marcosambrosi.mmovies.MoviesApplication;
import marcosambrosi.mmovies.R;
import marcosambrosi.mmovies.model.Movie;
import marcosambrosi.mmovies.util.Constants;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Movie movie = Movie.fromJsonString(getIntent().getStringExtra(Constants.EXTRA.MOVIE));

        ImageView movieImage = (ImageView) findViewById(R.id.movie_image);

        String posterUrl = MoviesApplication.getInstance().getConfiguration().image.baseUrl
                .concat("w1280")
                .concat(movie.backdropPath);

        Picasso.with(this)
                .load(posterUrl)
                .placeholder(R.color.darker_color)
                .error(R.color.darker_color)
                .into(movieImage);


        ((CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout)).
                setTitle(movie.title);

//        ((TextView) findViewById(R.id.movie_title)).setText(movie.title);
//        ((TextView) findViewById(R.id.movie_overview)).setText(movie.overview);

    }

}