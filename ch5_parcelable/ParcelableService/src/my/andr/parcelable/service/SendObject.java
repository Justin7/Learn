package my.andr.parcelable.service;

import android.os.Parcel;
import android.os.Parcelable;

public class SendObject implements Parcelable {
	private int  num;
	private String msg;
	
	public SendObject() {
		super();
	}
	public SendObject(int num, String msg) {
		super();
		this.num = num;
		this.msg = msg;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}	
	@Override
	public String toString() {
		return "SendObject [msg=" + msg + ", num=" + num + "]";
	}
	
	//=====================================================
	private SendObject(Parcel in){
		num=in.readInt();
		msg=in.readString();
	}
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(num);
		dest.writeString(msg);
	}
	public static final Parcelable.Creator<SendObject> CREATOR=
		new Parcelable.Creator<SendObject>(){
			public SendObject createFromParcel(Parcel in){
				return new SendObject(in);
			}
			public SendObject[]  newArray(int size){
				return new SendObject[size];
			}
	};
}
