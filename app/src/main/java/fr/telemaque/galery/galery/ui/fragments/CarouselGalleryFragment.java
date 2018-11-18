package fr.telemaque.galery.galery.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.List;

import fr.telemaque.galery.galery.R;
import fr.telemaque.galery.galery.managers.WSManager;
import fr.telemaque.galery.galery.models.Picture;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
this fragment represent the view with carousel image slider
using SliderLayout, title and description TextView to display
current Image title and description
 */
public class CarouselGalleryFragment extends Fragment {

    private SliderLayout mPictureSlider;
    private TextView mTitleTextView, mDescriptionTextView;
    private List<Picture>  mPicturesList;

    public CarouselGalleryFragment() {
    }

    public static CarouselGalleryFragment newInstance(String param1, String param2) {
        CarouselGalleryFragment fragment = new CarouselGalleryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_carousel_gallery, container, false);
        initViews(rootView);
        loadData();
        return rootView;
    }

    private void initViews(View rootView) {
        //init views
        mPictureSlider = rootView.findViewById(R.id.viewPager);
        mTitleTextView = rootView.findViewById(R.id.title);
        mDescriptionTextView = rootView.findViewById(R.id.description);
        mPicturesList = new ArrayList<Picture>();

    }

    private void loadData() {
        WSManager.getInstance().getPictures(new Callback<ArrayList<Picture>>() {
            @Override
            public void onResponse(Call<ArrayList<Picture>> call, Response<ArrayList<Picture>> response) {
                if (response.body()!=null){
                    mPicturesList.addAll(response.body());
                    initSlider();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Picture>> call, Throwable t) {
                Log.e("DATA_ERROR",t.getMessage());
            }
        });
    }


    private void initSlider(){
        for(Picture picture : mPicturesList){
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(picture.getTitle())
                    .image(picture.getImagePath())
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            mPictureSlider.addSlider(textSliderView);
        }
        mPictureSlider.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position < mPicturesList.size()) {
                    mTitleTextView.setText(mPicturesList.get(position).getTitle());
                    mDescriptionTextView.setText(mPicturesList.get(position).getDescription());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mPictureSlider.setPresetTransformer(SliderLayout.Transformer.Foreground2Background);
        mPictureSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Top);
        mPictureSlider.setCustomAnimation(new DescriptionAnimation());
        mPictureSlider.setDuration(4000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        WSManager.getInstance().cancelAll();
    }
}
