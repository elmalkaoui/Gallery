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

    public static CarouselGalleryFragment newInstance() {
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

    //call asynchronous for getting data (pictures list) by calling WSManager, on response i fill mPicturesList
    // after we call inislider to init ImageSlider with data in mPicturesList
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

    /*
    *fill mImageSlider with data in mPictures list and configure
    * the way wich the slider will display pictures
    */
    private void initSlider(){
        for(Picture picture : mPicturesList){
            //declaring new slide
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize  slide with title, image and ScaleType (in this case image fit slider)
            textSliderView
                    .description(picture.getTitle())
                    .image(picture.getImagePath())
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            //adding slide to sliderLayout(mPictureSlider)
            mPictureSlider.addSlider(textSliderView);
        }
        /*adding listener to mPictureListener to update mTitleTextView
        * and mDescriptionTextView when page is changed
        * */
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
        //defining animation between slides
        mPictureSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        //defining slide indicator position
        mPictureSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mPictureSlider.setCustomAnimation(new DescriptionAnimation());
        //set duration between slide change automatically
        mPictureSlider.setDuration(4000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //on destroying Activity i cancel all httpclient calls currently enqueued or executing
        WSManager.getInstance().cancelAll();
    }
}
