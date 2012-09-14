package my.andr.hcgallery;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

public class DirectoryEntry {
    private String name;
    private int resID;

    public DirectoryEntry(String name, int resID) {
        this.name = name;
        this.resID = resID;
    }

    public String getName() {
        return name;
    }
    public Drawable getDrawable(Resources res) {
        return res.getDrawable(resID);
    }
    public Bitmap getBitmap(Resources res) {
        return BitmapFactory.decodeResource(res, resID);
    }
}
