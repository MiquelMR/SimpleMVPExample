package com.simplemvpexample.app.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "evil_characters")
public class EvilCharacter implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String movie;

    private String image;

    public EvilCharacter() {
    }

    public static final Creator<EvilCharacter> CREATOR = new Creator<EvilCharacter>() {
        @Override
        public EvilCharacter createFromParcel(Parcel in) {
            return new EvilCharacter( in );
        }

        @Override
        public EvilCharacter[] newArray(int size) {
            return new EvilCharacter[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt( id );
        dest.writeString( name );
        dest.writeString( movie );

        if (image != null) {
            dest.writeString( image );
        } else {
            dest.writeString( null );
        }
    }

    public EvilCharacter(Parcel input) {

        this.id = input.readInt();
        this.name = input.readString();
        this.movie = input.readString();
        this.image = input.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
