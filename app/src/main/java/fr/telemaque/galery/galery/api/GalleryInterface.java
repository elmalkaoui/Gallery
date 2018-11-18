package fr.telemaque.galery.galery.api;

import java.util.ArrayList;

import fr.telemaque.galery.galery.models.Picture;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by THINKPAD T450 on 17/11/2018.
 */

public interface GalleryInterface {
    @GET("/rh/pictures.json")
    Call<ArrayList<Picture>> getPictures();
}
