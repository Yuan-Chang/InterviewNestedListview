package intuit.com.interviewnestedlistview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ychang3 on 7/23/18.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private List<Song> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public RelativeLayout mRootView;
        public ViewHolder(RelativeLayout v) {
            super(v);
            mTextView = v.findViewById(R.id.name);
            mRootView = v;
        }
    }

    public SongAdapter(List myDataset) {
        mDataset = myDataset;
    }

    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_list_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
