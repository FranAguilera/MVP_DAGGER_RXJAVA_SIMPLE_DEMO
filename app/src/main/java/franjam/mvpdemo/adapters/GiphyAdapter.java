package franjam.mvpdemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import franjam.flickrapi.R;
import franjam.mvpdemo.mvp.model.GiphyData;

public class GiphyAdapter extends RecyclerView.Adapter<GiphyAdapter.GiphyViewHolder> {
    private GiphyData giphyData;
    private GiphyListener listener;
    private Context context;

    public GiphyAdapter(Context context, GiphyListener listener) {
        this.giphyData = new GiphyData();
        this.listener = listener;
        this.context = context;
    }

    @Override
    public GiphyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new GiphyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GiphyViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return giphyData.getSize();
    }

    public void updateGiphyData(GiphyData giphyData) {
        this.giphyData = giphyData;
        notifyDataSetChanged();
    }

    public interface GiphyListener {
        void onClickImage(final String url);
    }

    class GiphyViewHolder extends RecyclerView.ViewHolder {
        private View itemView;
        private ImageView thumbNail;

        public GiphyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            thumbNail = (ImageView) itemView.findViewById(R.id.grid_item_image_view);
        }

        public void bind(final int position) {
            final String thumbUrl = giphyData.getUrl(position);
            Glide.with(context).load(thumbUrl).into(thumbNail);

            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickImage(thumbUrl);
                }
            });
        }
    }
}
