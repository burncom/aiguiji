package service;

import java.util.ArrayList;
import java.util.List;

public class WebControl {
	/**
	 * ΰ��ĵ���ģʽ
	 */
	private WebControl(){}
	private static WebControl single=null;
	//�û��б�
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
