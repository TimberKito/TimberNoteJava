package com.bochi.kito.timbernotejava.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.bochi.kito.timbernotejava.tools.DateTools;

import lombok.Data;

@Entity(tableName = "t_task")
@Data
public class Task implements Parcelable {
    private int id;
    private String content;
    private Long dateLong;
    private String date = DateTools.convertMillisToDate(dateLong);
    private Boolean isOpen = true;
    private Integer hour;
    private Integer minute;
    private Long systemTimeL;

    protected Task(Parcel in) {
        id = in.readInt();
        content = in.readString();
        if (in.readByte() == 0) {
            dateLong = null;
        } else {
            dateLong = in.readLong();
        }
        date = in.readString();
        byte tmpIsOpen = in.readByte();
        isOpen = tmpIsOpen == 0 ? null : tmpIsOpen == 1;
        if (in.readByte() == 0) {
            hour = null;
        } else {
            hour = in.readInt();
        }
        if (in.readByte() == 0) {
            minute = null;
        } else {
            minute = in.readInt();
        }
        if (in.readByte() == 0) {
            systemTimeL = null;
        } else {
            systemTimeL = in.readLong();
        }
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(content);
        if (dateLong == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(dateLong);
        }
        dest.writeString(date);
        dest.writeByte((byte) (isOpen == null ? 0 : isOpen ? 1 : 2));
        if (hour == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(hour);
        }
        if (minute == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(minute);
        }
        if (systemTimeL == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(systemTimeL);
        }
    }
}