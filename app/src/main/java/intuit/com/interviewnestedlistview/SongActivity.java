package intuit.com.interviewnestedlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.Arrays;

import intuit.com.interviewnestedlistview.Web.WebServiceGenerator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SongActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SongAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        WebServiceGenerator.getWebClient(MainActivity.SERVICE_URL).loadSongs(SongsManager.getInstance().getSelectedAlbum().id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<Song[]>() {
                    @Override
                    public void accept(Song[] responses) throws Exception {
                        Log.d("Msg", responses.toString());
                        SongsManager.getInstance().getSelectedAlbum().setSongs(responses);
                        mAdapter = new SongAdapter(Arrays.asList(SongsManager.getInstance().getSelectedAlbum().getSongs()));
                        mRecyclerView.setAdapter(mAdapter);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("Error", throwable.toString());
                    }
                });

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        // specify an adapter (see also next example)
        Album album = SongsManager.getInstance().getSelectedAlbum();
        mAdapter = new SongAdapter(Arrays.asList(album.getSongs()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.scrollToPosition(SongsManager.getInstance().getSelectedIndex());
    }
}
