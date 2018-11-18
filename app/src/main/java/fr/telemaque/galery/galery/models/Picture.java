package fr.telemaque.galery.galery.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by THINKPAD T450 on 17/11/2018.
 */

public class Picture {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("imagePath")
    @Expose
    private String imagePath;

    @SerializedName("description")
    @Expose
    private String description;


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }
}
