package com.example.shubham.app1;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    public ArrayList<Learn> learnArrayList;

    Context context;
    public RecyclerAdapter(ArrayList<Learn> learnArrayList, Context context){
        this.learnArrayList = learnArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerlayout, parent, false);
            ImageViewHolder imageViewHolder = new ImageViewHolder(view);
            return imageViewHolder;
        } else if (viewType == TYPE_FOOTER) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_footer, parent, false);
            return new FooterViewHolder(itemView);
        }else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ImageViewHolder) {
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
            final Learn learn = learnArrayList.get(position);
            imageViewHolder.image.setImageResource(learn.image);

            imageViewHolder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        String imageName = learn.name;
                        int resourceId = view.getResources().getIdentifier(imageName, "raw", "com.example.shubham.app1");
                        MediaPlayer mplayer = MediaPlayer.create(context, resourceId);
                        mplayer.start();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

            imageViewHolder.name.setText(learn.name);
        }else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == learnArrayList.size()) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return learnArrayList.size()+1;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;

        public ImageViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.animal);
            name = itemView.findViewById(R.id.animalName);
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {
        Button footerButton;

        public FooterViewHolder(View view) {
            super(view);
            footerButton = view.findViewById(R.id.quizButtonFooter);
        }
    }

}
