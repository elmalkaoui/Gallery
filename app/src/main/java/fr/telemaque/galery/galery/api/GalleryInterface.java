package fr.telemaque.galery.galery.api;

import java.util.ArrayList;

import fr.telemaque.galery.galery.models.Picture;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by THINKPAD T450 on 17/11/2018.
 */

/*
*this interface describe a webservice of one method that is getPictures()
 */
public interface GalleryInterface {
    /*
    defining method of call that is GET and the URL of this method
    retrofit will convert automatically result into list of pictures
    */
    @GET("/rh/pictures.json")
    Call<ArrayList<Picture>> getPictures();
}
