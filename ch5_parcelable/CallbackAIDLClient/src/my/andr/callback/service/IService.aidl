package my.andr.callback.service;

import my.andr.callback.client.ICallback;

interface IService{
	void registerCallback(ICallback cb);
    void unregisterCallback(ICallback cb);
	void setValue(int value);
	int getValue(); 
	void sendMessage(String msg);
}