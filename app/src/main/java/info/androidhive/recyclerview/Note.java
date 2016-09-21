package info.androidhive.recyclerview;

/**
 * Created by MUHAMMET on 12.07.2016.
 */
public class Note {

    public String id;
    public String name;
    public String slug;
    public String average;
    public String price;
    public String university_id;

    public Note(String id,/*String name,String slug,String average,*/String price,String university_id)
    {
        this.id = id;
       /* this.name = name;
        this.slug = slug;
        this.average = average;*/
        this.price = price;
        this.university_id = university_id;
    }


    public String  getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getAverage() {
        return average;
    }
    public void setAverage(String average) {
        this.average = average;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getUniversity_id() { return university_id; }
    public void setUniversity_id(String university_id){ this.university_id = university_id; }






}
