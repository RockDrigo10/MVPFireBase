package com.example.admin.mvpfirebase.view.mainactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.admin.mvpfirebase.Injection.mainactivity.DaggerMainActivityComponent;
import com.example.admin.mvpfirebase.R;
import com.example.admin.mvpfirebase.model.Movie;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {
    private static final String TAG = "MainActivity";
    TextView tvText;
    @Inject
    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvText = (TextView) findViewById(R.id.tvText);
        setupDaggerComponent();
        presenter.init();
        presenter.attachView(this);
        presenter.saveDataToCloud("John Snow");
    }

    public void getDataFirebase(View view) {
        presenter.getDataToCloud();
    }

    private void setupDaggerComponent() {
        DaggerMainActivityComponent.create().inject(this);
    }

    @Override
    public void showError(String s) {

    }

    @Override
    public void onDataSaved(boolean isSaved) {
        Log.d(TAG, "onDataSaved: " + isSaved);
    }

    @Override
    public void sendToView(String s) {
        tvText.setText(s);
    }

    @Override
    public void updateMovieList(List<Movie> movieList) {
        for(Movie movie: movieList){
            Log.d(TAG, "updateMovieList: "+ movie.toString());
        }
    }

    public void updateFirebaseDb(View view) {
        switch (view.getId()) {
            case R.id.btnPushMovie:
                Movie movie = new Movie("Avengers", "sdadss", 2015);
                presenter.pushToDb(movie);
                break;
            case R.id.btnGetMovie:
                presenter.getMovies();
                break;
        }
    }
}
