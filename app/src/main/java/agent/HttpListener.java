package agent;

//import android.graphics.Bitmap;

public interface HttpListener {
	public int onHttpResponse(int code, String msg);
	//public void onHttpImage(Bitmap bitmap,Object object);
	public void onError(int code, String desc);
	public int onHttpAuthorized(int code);
}
