package fr.telemaque.galery.galery.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fr.telemaque.galery.galery.R;
import fr.telemaque.galery.galery.managers.WSManager;
import fr.telemaque.galery.galery.models.Picture;
import fr.telemaque.galery.galery.ui.adapters.RecycleViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
* this fragment represent a list of pictures in form of list
* using RecycleView component
*/
public class ListGalleryFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecycleViewAdapter mRecycleViewAdapter;
    private ArrayList<Picture> mPicturesList;

    public ListGalleryFragment() {
    }

    public static ListGalleryFragment newInstance() {
        ListGalleryFragment fragment = new ListGalleryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_gallery, container, false);
        initViews(rootView);
        loadData();
        return rootView;
    }

    private void initViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycle_view);
        mPicturesList = new ArrayList<Picture>();
        mRecycleViewAdapter = new RecycleViewAdapter(getContext(), mPicturesList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mRecycleViewAdapter);
    }

    //getting data (pictures list) by calling WSManager, on response the we fill mPicturesList
    // and notify RecycleView adapter that the list is updated by calling notifyDatasetchanged()
    private void loadData() {
        WSManager.getInstance().getPictures(new Callback<ArrayList<Picture>>() {
            @Override
            public void onResponse(Call<ArrayList<Picture>> call, Response<ArrayList<Picture>> response) {
                if (response.body()!=null){
                    mPicturesList.addAll(response.body());
                    mRecycleViewAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Picture>> call, Throwable t) {
                Log.e("DATA_ERROR",t.getMessage());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //on destroying Activity i cancel all httpclient calls currently enqueued or executing
        WSManager.getInstance().cancelAll();
    }
}
