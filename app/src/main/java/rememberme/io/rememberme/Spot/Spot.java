package rememberme.io.rememberme.Spot;

import java.util.ArrayList;
import java.util.Date;

import rememberme.io.rememberme.Photo.Photo;

/**
 * Created by JW on 2017-12-10.
 */

public class Spot {
    private int uid;
    private int subSeq;
    private String name;
    private String destination;
    private String memo;
    private Date start;
    private Date end;
    private ArrayList<Photo> photos;

    public Spot(int uid, int subSeq, String name, String destination, String memo, Date start, Date end, ArrayList<Photo> photos) {
        this.uid = uid;
        this.subSeq = subSeq;
        this.name = name;
        this.destination = destination;
        this.memo = memo;
        this.start = start;
        this.end = end;
        this.photos = photos;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getSubSeq() {
        return subSeq;
    }

    public void setSubSeq(int subSeq) {
        this.subSeq = subSeq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }
}
