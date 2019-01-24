package com.example.mediaapp.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediaapp.R;
import com.example.mediaapp.activity.model.Row;
import com.squareup.picasso.Picasso;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<Row> mNews;
    private Context mContext;

    public NewsAdapter(Context applicationContext, List<Row> newsList){
        this.mContext = applicationContext;
        this.mNews = newsList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_news,viewGroup, false );
        return new NewsViewHolder(view);
    }

    /*
      bind view holder
     */
    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.mTitle.setText(mNews.get(position).getTitle());
        holder.mDetail.setText(mNews.get(position).getDescription());

        //picasso to load image from internet
        Picasso.with(mContext)
                .load(mNews.get(position).getImageHref())
                .placeholder(R.drawable.ic_image_black_24dp)
                .into(holder.mImageView);
    }
    @Override
    public int getItemCount() {
        return mNews.size();
    }

    /*
      Newsholder class to set data
     */

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        private TextView mTitle, mDetail;
        private ImageView mImageView;

        public NewsViewHolder(View view){
            super(view);
            mTitle = (TextView)view.findViewById(R.id.mTitle);
            mDetail = (TextView)view.findViewById(R.id.detail);
            mImageView = (ImageView)view.findViewById(R.id.cover);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    //check if item stills exists
                    if(pos != RecyclerView.NO_POSITION){
                        Row clickDataItem = mNews.get(pos);
                        Toast.makeText(v.getContext(), "clicked "+clickDataItem.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}
























