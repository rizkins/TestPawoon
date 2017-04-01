package agent;

import android.app.Dialog;

public class HttpRequest {

	
	
	private static HttpRequest instance;
	
	static{
		if (instance == null) {
			instance = new HttpRequest();
		}
	}
	
	public static HttpRequest instance(){
		return instance;
	}
	
	public void request(Dialog progress,HttpListener listen,String url,Object param,int type){
		if(progress!=null && !progress.isShowing()){
			progress.show();
		}
		new HttpAgent().openUrl(listen,url, param,type);		
		
	}
		
	
}
