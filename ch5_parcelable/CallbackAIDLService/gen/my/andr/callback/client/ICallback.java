/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\android\\workspace\\CallbackAIDLService\\src\\my\\andr\\callback\\client\\ICallback.aidl
 */
package my.andr.callback.client;
public interface ICallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements my.andr.callback.client.ICallback
{
private static final java.lang.String DESCRIPTOR = "my.andr.callback.client.ICallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an my.andr.callback.client.ICallback interface,
 * generating a proxy if needed.
 */
public static my.andr.callback.client.ICallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof my.andr.callback.client.ICallback))) {
return ((my.andr.callback.client.ICallback)iin);
}
return new my.andr.callback.client.ICallback.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_valueChanged:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.valueChanged(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_receiveMessage:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.receiveMessage(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements my.andr.callback.client.ICallback
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public void valueChanged(int value) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(value);
mRemote.transact(Stub.TRANSACTION_valueChanged, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void receiveMessage(java.lang.String msg) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(msg);
mRemote.transact(Stub.TRANSACTION_receiveMessage, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_valueChanged = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_receiveMessage = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public void valueChanged(int value) throws android.os.RemoteException;
public void receiveMessage(java.lang.String msg) throws android.os.RemoteException;
}
