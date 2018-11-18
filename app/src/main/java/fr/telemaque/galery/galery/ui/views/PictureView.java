package fr.telemaque.galery.galery.ui.views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.telemaque.galery.galery.R;
import fr.telemaque.galery.galery.models.Picture;
import okhttp3.internal.Util;

/**
 * Created by THINKPAD T450 on 17/11/2018.
 */
/*
*PictureView represent a custom component to display image and title of a picture object
*/
public class PictureView extends ConstraintLayout {

    private TextView mTitleTextView;
    private ImageView mPictureImageView;

    private Context mContext;


    public PictureView(@NonNull Context context) {
        super(context);
        mContext = context;
        initViews();
    }


    private void initViews() {
        inflate(getContext(), R.layout.view_picture, this);
        mTitleTextView = findViewById(R.id.title);
        mPictureImageView = findViewById(R.id.imageView);
    }

    //fill views (mTitleTextView and mPictureImageView) by information within picture passed in argument
    public void fillData(Picture picture){
        if(picture != null){
            mTitleTextView.setText(picture.getTitle());
            //Picasso load image with the specific path in mPictureImageView
            Picasso.with(mContext).load(picture.getImagePath()).into(mPictureImageView);
        }
    }


}
