package intuit.com.interviewnestedlistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

import intuit.com.interviewnestedlistview.Web.WebServiceGenerator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    static final String SERVICE_URL = "http://52.8.111.8:10301";
    static final int LIMIT = 20;

    private RecyclerView mRecyclerView;
    private AlbumAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private AlbumAdapter.OnClickListener onClickListener = new AlbumAdapter.OnClickListener() {
        @Override
        public void onClickListener(int position) {
            SongsManager.getInstance().setSelectedAlbumIndex(position);
            Intent intent = new Intent(MainActivity.this,SongActivity.class);
            startActivity(intent);
        }
    };

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            int pos = mLayoutManager.findFirstVisibleItemPosition();
            SongsManager.getInstance().setScrollPos(pos);


            int lastPos = mLayoutManager.findLastVisibleItemPosition();
            Log.d("Yuan",lastPos+"");

            final int total = SongsManager.getInstance().getAlbumList().size();
            if(lastPos > total-10)
            {
                WebServiceGenerator.getWebClient(SERVICE_URL).loadAlbums(total, LIMIT)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(new Consumer<Album[]>() {
                            @Override
                            public void accept(Album[] responses) throws Exception {
                                Log.d("Msg", responses.toString());
                                SongsManager.getInstance().addAlbumList(responses, total+LIMIT);
                                ArrayList<Album> list = SongsManager.getInstance().getAlbumList();
                                mAdapter = new AlbumAdapter(list);
                                mAdapter.setOnClickListener(onClickListener);
                                mRecyclerView.setAdapter(mAdapter);
                                mRecyclerView.setOnScrollListener(onScrollListener);
                                mRecyclerView.scrollToPosition(SongsManager.getInstance().getScrollPos());

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d("Error", throwable.toString());
                            }
                        });

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Yuan","onCreate");

        WebServiceGenerator.getWebClient(SERVICE_URL).loadAlbums(0, LIMIT)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<Album[]>() {
                    @Override
                    public void accept(Album[] responses) throws Exception {
                        Log.d("Msg", responses.toString());
                        SongsManager.getInstance().addAlbumList(responses, LIMIT);
                        ArrayList<Album> list = SongsManager.getInstance().getAlbumList();
                        mAdapter = new AlbumAdapter(list);
                        mAdapter.setOnClickListener(onClickListener);
                        mRecyclerView.setAdapter(mAdapter);
                        mRecyclerView.setOnScrollListener(onScrollListener);

                        mRecyclerView.scrollToPosition(SongsManager.getInstance().getScrollPos());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("Error", throwable.toString());
                    }
                });

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new AlbumAdapter(SongsManager.getInstance().getAlbumList());
        mAdapter.setOnClickListener(onClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnScrollListener(onScrollListener);
        mRecyclerView.scrollToPosition(SongsManager.getInstance().getScrollPos());
    }
}
