package service;

import java.util.ArrayList;
import java.util.List;

public class WebControl {
	/**
	 * 伟大的单例模式
	 */
	private WebControl(){}
	private static WebControl single=null;
	//用户列表
	private static List<String> webUserList=null;
	public static WebControl getWebControlInstance(){
		if(single==null){
			single=new WebControl();
		}
		return single;	
	}
	public static List<String> getWebUserList() {
		if(webUserList==null){
			webUserList=new ArrayList<String>();
		}
		return webUserList;
	}
	public static void printWebUserList(List<String> webUserList){
		for(String member:webUserList)
			System.out.println(member);
	}
}
