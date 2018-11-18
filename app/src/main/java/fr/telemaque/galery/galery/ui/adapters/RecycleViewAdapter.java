package fr.telemaque.galery.galery.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.ArrayList;

import fr.telemaque.galery.galery.models.Picture;
import fr.telemaque.galery.galery.ui.views.PictureView;

/**
 * Created by THINKPAD T450 on 17/11/2018.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.PictureHolder> {

    private ArrayList<Picture> mPicturesList;
    private Context mContext;

    public RecycleViewAdapter(Context context, ArrayList<Picture> picturesList){
        mContext = context;
        mPicturesList = picturesList;
    }

    @NonNull
    @Override
    public PictureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PictureView pictureView = new PictureView(mContext);
        PictureHolder pictureHolder = new PictureHolder(pictureView);
        return pictureHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PictureHolder holder, int position) {
        if (position < getItemCount())
            holder.fillData(mPicturesList.get(position));

    }

    @Override
    public int getItemCount() {
        return mPicturesList.size();
    }


    public class PictureHolder extends RecyclerView.ViewHolder {
        PictureView mPictureView;

        public PictureHolder(PictureView pictureView) {
            super(pictureView);
            mPictureView = pictureView;
        }
        public void fillData(Picture picture){
            mPictureView.fillData(picture);
        }
    }

}
