package com.bochi.kito.timbernotes.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "t_notes")
public class Notes implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String content;

    private int color;

    public Notes(String title, String content, int color) {
        this.title = title;
        this.content = content;
        this.color = color;
    }


    public Notes() {
    }


    public int getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
//        @Override
//        public Notes createFromParcel(Parcel in) {
//            return new Notes(in);
//        }
//
//        @Override
//        public Notes[] newArray(int size) {
//            return new Notes[size];
//        }
//    };
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(@NonNull Parcel dest, int flags) {
//        dest.writeInt(id);
//        dest.writeString(title);
//        dest.writeString(content);
//        dest.writeInt(color);
//    }


}
