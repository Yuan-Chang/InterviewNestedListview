package intuit.com.interviewnestedlistview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ychang3 on 7/23/18.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    private ArrayList<Album> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public RelativeLayout mRootView;
        public ViewHolder(RelativeLayout v) {
            super(v);
            mTextView = v.findViewById(R.id.name);
            mRootView = v;
        }
    }

    public AlbumAdapter(ArrayList<Album> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_list_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String name = mDataset.get(position).getName();
        holder.mTextView.setText(mDataset.get(position).getName());
        holder.mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClickListener != null)
                    onClickListener.onClickListener(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    OnClickListener onClickListener;
    public interface OnClickListener {
        void onClickListener(int position);
    }
    public void setOnClickListener(OnClickListener listener){
        onClickListener = listener;
    }


}
