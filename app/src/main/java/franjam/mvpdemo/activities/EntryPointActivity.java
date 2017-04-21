package franjam.mvpdemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import javax.inject.Inject;

import franjam.flickrapi.R;
import franjam.mvpdemo.App;
import franjam.mvpdemo.adapters.GiphyAdapter;
import franjam.mvpdemo.mvp.model.GiphyData;
import franjam.mvpdemo.mvp.presenter.EntryPointPresenterImplementation;
import franjam.mvpdemo.mvp.view.EntryPointView;

public class EntryPointActivity extends AppCompatActivity implements EntryPointView, GiphyAdapter.GiphyListener {
    @Inject
    EntryPointPresenterImplementation presenter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_entry_point_activity);

        injectView();

        recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.initialize();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void updateRecyclerView(GiphyData giphyData) {
        GiphyAdapter adapter = new GiphyAdapter(this, giphyData, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClickImage(String url) {
        displayImageDetails(url);
    }

    @Override
    public void displayMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void displayImageDetails(String url) {
        displayMessage(url);
    }

    /**
     * Dagger 2 injection.
     *
     * Injects EntryPointActivity into ApplicationComponent
     */
    private void injectView() {
        App app = (App) getApplication();
        app.getComponent().inject(this);
    }
}