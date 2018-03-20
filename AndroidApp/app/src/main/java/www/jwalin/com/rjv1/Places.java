package www.jwalin.com.rjv1;

import java.io.Serializable;

/**
 * Created by BRAHMPRAKASHARORA on 3/19/2018.
 */

public class Places implements Serializable{
    private String name,description,type,thumbnail;
   private int RewardPoints=0;
    private float  rating=0;
    public Places(){}
    public Places(String name,String description,String type,String thumbnail,int RewardPoints,float rating){
        this.name=name;
        this.thumbnail=thumbnail;
        this.description=description;
        this.type=type;
        this.RewardPoints=RewardPoints;
        this.rating=rating;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public void setType(String type){
        this.type=type;
    }
    public void setThumbnail(String thumbnail){
        this.thumbnail=thumbnail;
    }
    public void setRewardPoints(int RewardPoints){
        this.RewardPoints=RewardPoints;
    }
    public void setRating(float rating){
        this.rating=rating;
    }
    public String getName(){
        return this.name;
    }
    public String getThumbnail(){
        return thumbnail;
    }
    public String getDescription(){
        return description;
    }
    public String getType(){
        return type;
    }
    public float getRating(){
        return rating;
    }
    public int getRewardPoints(){
        return RewardPoints;
    }



}
