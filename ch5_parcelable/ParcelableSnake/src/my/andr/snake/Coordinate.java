package my.andr.snake;

import android.os.Parcel;
import android.os.Parcelable;


/**  ÁÂÇ¥°ªÀ» ±â¾ïÇÏ´Â °´Ã¼ <br/>
 * Simple class containing two integer values and a comparison function.
 * There's probably something I should use instead, but this was quick and
 * easy to build.
 * 
 */
 class Coordinate implements Parcelable{
    public int x;
    public int y;

    public Coordinate(int newX, int newY) {
        x = newX;
        y = newY;
    }

    public boolean equals(Coordinate other) {
        if (x == other.x && y == other.y) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Coordinate: [" + x + "," + y + "]";
    }
 //======================================================
    private Coordinate(Parcel in){
		x=in.readInt();
		y=in.readInt();
	}
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(x);
		dest.writeInt(y);
	}
	public static final Parcelable.Creator<Coordinate> CREATOR=
		new Parcelable.Creator<Coordinate>(){
			public Coordinate createFromParcel(Parcel in){
				return new Coordinate(in);
			}
			public Coordinate[]  newArray(int size){
				return new Coordinate[size];
			}
	};
    
    
    
    
}