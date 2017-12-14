package rememberme.io.rememberme.Photo;

import java.util.Date;

/**
 * Created by JW on 2017-12-10.
 */

public class Photo {

    private Date start;
    private Date end;
    private int image;

    public Photo(Date start, Date end, int image) {
        this.start = start;
        this.end = end;
        this.image = image;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
