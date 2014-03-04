package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import domain.MessageInfo;
import domain.MsgMsgRelationship;
import domain.PhotosInfo;
import domain.UserInfo;
import domain.WeatherInfo;

public class WebAlgo {
	
	//����ʡ�ݣ����У���/�������ֵ�����Ϣ����
	public String[] getPlaceNameByCoorinateByBaiduMap(String coordinate){
		String placeName[]=new String[4];
		//����HttpClient��ʵ��
		HttpClient httpClient = new HttpClient();
		//����GET������ʵ��
		GetMethod getMethod = new GetMethod("http://api.map.baidu.com/geocoder?location="+coordinate+
				  "&output=json&key="+WebConstant.BAIDU_MAP_KEY);
		getMethod.getParams().setParameter("http.protocol.cookie-policy",CookiePolicy.BROWSER_COMPATIBILITY);
		  //ʹ��ϵͳ�ṩ��Ĭ�ϵĻָ�����
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
		    new DefaultHttpMethodRetryHandler());
		try {
		   //ִ��getMethod
		   int statusCode = httpClient.executeMethod(getMethod);
		   if (statusCode != HttpStatus.SC_OK) {
		    System.err.println("Method failed: "
		      + getMethod.getStatusLine());
		   }
		 //��ȡ���� 
		 String response=new String(getMethod.getResponseBodyAsString());
		 JSONObject jsonObject=JSONObject.fromObject(response);
		 //��������
		 JSONObject object=(JSONObject)((JSONObject) jsonObject.get("result")).get("addressComponent");
		 placeName[0]=object.get("province").toString();
		 placeName[1]=object.get("city").toString();
		 placeName[2]=object.get("district").toString();
		 String other=object.get("street").toString();
		 if(!other.isEmpty()&&other.length()!=0)
			 placeName[3]=other;
		 else 
			 placeName[3]="";
		   
		 } catch (HttpException e) {
		   //�����������쳣��������Э�鲻�Ի��߷��ص�����������
		 } catch (IOException e) {
		   //���������쳣
		 } finally {
		   //�ͷ�����
		   getMethod.releaseConnection();
		 }
		 return placeName;
	}
	
	//����Ǽ�����������ľ��룬�����ַ�����һ�����ù��ɶ�����㣬�������������ܽ��������һ�ְ���׼�������Բ�ӻ����ȼ��㣬�����ھ����Զ�����
	//��λ����
	public double getDistance(double lon1, double lat1, double lon2, double lat2){
		double ew1, ns1, ew2, ns2;
		double dx, dy, dew;
		double distance;
		// �Ƕ�ת��Ϊ����
		ew1 = lon1 * WebConstant.DEF_PI180;
		ns1 = lat1 * WebConstant.DEF_PI180;
		ew2 = lon2 * WebConstant.DEF_PI180;
		ns2 = lat2 * WebConstant.DEF_PI180;
		// ���Ȳ�
		dew = ew1 - ew2;
		// ���綫��������180 �ȣ����е���
		if (dew > WebConstant.DEF_PI)
		dew = WebConstant.DEF_2PI - dew;
		else if (dew < -WebConstant.DEF_PI)
		dew = WebConstant.DEF_2PI + dew;
		dx = WebConstant.DEF_R * Math.cos(ns1) * dew; // �������򳤶�(��γ��Ȧ�ϵ�ͶӰ����)
		dy = WebConstant.DEF_R * (ns1 - ns2); // �ϱ����򳤶�(�ھ���Ȧ�ϵ�ͶӰ����)
		// ���ɶ�����б�߳�
		distance = Math.sqrt(dx * dx + dy * dy);
		return distance;
	}
	//��������������ͼƬ���Խ�����ʱ��Խ��ǰ��Ȼ����Խ��ʱ��������
	public int partitionPhoto(ArrayList<PhotosInfo> a,int low,int high)  
	{  
	    int key=a.get(low).getPhoto_id(); //���ӱ�ĵ�һ����¼�������¼  
	    while(low < high)   //�ӱ�����˽�������м�ɨ��  
	    {  
	        while(low < high && a.get(high).getPhoto_id() <=key)
	            --high;  
	        {                  //���Ȗ����¼С�ļ�¼�������Ͷ�  
	        	PhotosInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	  
	        while(low < high && a.get(low).getPhoto_id() >= key)  
	            ++low;  
	        {                 //���Ȗ����¼��ļ�¼�������Ͷ�  
	        	PhotosInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	    }  
	    return low;   //���ؖ������ڵ�λ��  
	}  
	
	//����ʱ����������
	public void qsortPhoto(ArrayList<PhotosInfo> a,int b,int e)  
	{  
	    if(b < e)  
	    {  
	        int m=partitionPhoto(a,b,e);  
	        qsortPhoto(a,b,m-1);  
	        qsortPhoto(a,m+1,e);  
	    }  
	}
	//����msgmsgrelation���е�ʱ��������򣬰���ʱ������
	//1������Ҫ�������ڣ������ͬһ��Ҫ����ʵʱʱ��
	public int partitionMsgMsgRDateTime(ArrayList<MsgMsgRelationship> a,int low,int high)  
	{  
	    int key=a.get(low).getDatetime(); //���ӱ�ĵ�һ����¼�������¼  
	    while(low < high)   //�ӱ�����˽�������м�ɨ��  
	    {  
	        while(low < high && a.get(high).getDatetime() <=key)
	            --high;  
	        {                  //���Ȗ����¼С�ļ�¼�������Ͷ�  
	        	MsgMsgRelationship temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	  
	        while(low < high && a.get(low).getDatetime() >= key)  
	            ++low;  
	        {                 //���Ȗ����¼��ļ�¼�������Ͷ�  
	        	MsgMsgRelationship temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	    }  
	    return low;   //���ؖ������ڵ�λ��  
	}  
	
	//����ʱ����������
	public void qsortMsgMsgRDateTime(ArrayList<MsgMsgRelationship> a,int b,int e)  
	{  
	    if(b < e)  
	    {  
	        int m=partitionMsgMsgRDateTime(a,b,e);  
	        qsortMsgMsgRDateTime(a,b,m-1);  
	        qsortMsgMsgRDateTime(a,m+1,e);  
	    }  
	}
	//����msgmsgrelation���е�ʱ��������򣬰���ʱ������
	//2�������ͬһ��Ҫ����ʵʱʱ��
	public int partitionMsgMsgRRealTime(ArrayList<MsgMsgRelationship> a,int low,int high)  
	{  
	    int key=a.get(low).getRealtime(); //���ӱ�ĵ�һ����¼�������¼  
	    while(low < high)   //�ӱ�����˽�������м�ɨ��  
	    {  
	        while(low < high && a.get(high).getRealtime() <=key)
	            --high;  
	        {                  //���Ȗ����¼С�ļ�¼�������Ͷ�  
	        	MsgMsgRelationship temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	  
	        while(low < high && a.get(low).getRealtime() >= key)  
	            ++low;  
	        {                 //���Ȗ����¼��ļ�¼�������Ͷ�  
	        	MsgMsgRelationship temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	    }  
	    return low;   //���ؖ������ڵ�λ��  
	}  
	
	//����ʱ����������
	public void qsortMsgMsgRRealTime(ArrayList<MsgMsgRelationship> a,int b,int e)  
	{  
	    if(b < e)  
	    {  
	        int m=partitionMsgMsgRRealTime(a,b,e);  
	        qsortMsgMsgRRealTime(a,b,m-1);  
	        qsortMsgMsgRRealTime(a,m+1,e);  
	    }  
	}
	
	
	//������������ �Ŀ�������WeatherInfo
	public int partition(ArrayList<WeatherInfo> a,int low,int high)  
	{  
	    int key=a.get(low).getRealtime(); //���ӱ�ĵ�һ����¼�������¼  
	    while(low < high)   //�ӱ�����˽�������м�ɨ��  
	    {  
	        while(low < high && a.get(high).getRealtime() <=key)
	            --high;  
	        {                  //���Ȗ����¼С�ļ�¼�������Ͷ�  
	        	WeatherInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	  
	        while(low < high && a.get(low).getRealtime() >= key)  
	            ++low;  
	        {                 //���Ȗ����¼��ļ�¼�������Ͷ�  
	        	WeatherInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	    }  
	    return low;   //���ؖ������ڵ�λ��  
	}  
	
	//����ʱ����������
	public void qsort(ArrayList<WeatherInfo> a,int b,int e)  
	{  
	    if(b < e)  
	    {  
	        int m=partition(a,b,e);  
	        qsort(a,b,m-1);  
	        qsort(a,m+1,e);  
	    }  
	}
	
	public String transferVirtualTime(int time,int nowTime){
		long ttime=getTotalSeconds(time);
		long tnowTime=getTotalSeconds(nowTime);
		long between=tnowTime-ttime;
		if(between/3600!=0){
			return between/3600+"Сʱǰ";
		}else if(between/60!=0){
			return between/60+"����ǰ";
		}else{
			return between+"��ǰ";
		}
	}
	
	public long getTotalSeconds(int time){
		int temp=0;
		long total=0;
		temp =time%100;
		time /= 100;
		total=temp;
		
		temp=time % 100;
		time /= 100;
		total += temp*60;
			
		total += time * 3600;
		return total;
	}
	
	public String getTotalDays(int beforeDaySequence,int nowDaySequence){
		if(beforeDaySequence!=nowDaySequence){
			int beforeTemp=beforeDaySequence % 10000;
			int nowTemp=nowDaySequence % 10000;
			
			int beforeYear=beforeDaySequence / 10000;
			int nowYear=nowDaySequence / 10000;
			
			int beforeMonth=beforeTemp /100;
			int nowMonth=nowTemp /100;
			
			int beforeDay= beforeTemp % 100;
			int nowDay= nowTemp % 100;
			
			int betweenYear=nowYear-beforeYear;
			int betweenMonth=0;
			int betweenDay=0;
			if( betweenYear>=2){
				return betweenYear+"��ǰ";
			}
			else if(betweenYear == 1){
				
				betweenMonth=nowMonth+12-beforeMonth;
				if(betweenMonth >= 2){
					return betweenMonth+"��ǰ";
				}
				else{
					betweenDay=getMonthDay(beforeYear, beforeMonth)+nowDay-beforeDay;
					if(betweenDay>2)
						return betweenDay+"��ǰ";
					else if(betweenDay==2)
						return "ǰ��";
					else return "����";
				}
			}
			else{
				betweenMonth=nowMonth-beforeMonth;
				if(betweenMonth >= 2){
					return betweenMonth+"��ǰ";
				}
				else if(betweenMonth ==1){
					betweenDay=getMonthDay(beforeYear, beforeMonth)+nowDay-beforeDay;
					if(betweenDay>2)
						return betweenDay+"��ǰ";
					else if(betweenDay==2)
						return "ǰ��";
					else return "����";
				}
				else{
					betweenDay=nowDay-beforeDay;
					if(betweenDay>2)
						return betweenDay+"��ǰ";
					else if(betweenDay==2)
						return "ǰ��";
					else return "����";
				}
			}
		}
		else{
			return "1";
		}
		
	}
	
	public int getMonthDay(int year,int month){
		int monthDay=0;
		switch(month){
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:monthDay=31;break;
		case 4:
		case 6:
		case 9:
		case 11:monthDay=30;break;
		case 2:monthDay=(((year % 4 == 0) &&( year % 100 != 0)) || year % 400 == 0) ? 28 : 29;break;
		}
		return monthDay;
	}
	public String transferTimeStr(int time){
		String strTime="";
		time /= 100;
		int minute=time % 100;
		int hour=time / 100;
		
		if(hour<10)
			strTime+="0"+hour+":";
		else
			strTime +=hour+":";
		
		if(minute < 10)
			strTime+="0"+minute;
		else
			strTime+=minute;
		
		return strTime;
			
	}
	public String transferVirtualTime(int day,int time,int nowDay,int nowTime){
		String result=getTotalDays(day,nowDay);
		if(result.equals("1")){
			if(result.equals("����")||result.equals("ǰ��")){
				return result+" "+transferTimeStr(time);
			}
			else{
				long ttime=getTotalSeconds(time);
				long tnowTime=getTotalSeconds(nowTime);
				long between=tnowTime-ttime;
				if(between/3600!=0){
					return between/3600+"Сʱǰ";
				}else if(between/60!=0){
					return between/60+"����ǰ";
				}else{
					return between+"��ǰ";
				}
			}
		}else
			return result+" "+transferTimeStr(time);
	}
	
	//��intʱ��ת��Ϊ2012��12��12�� 09:09:09 ��ʽ
	public String transferTimeIStr(int datetime,int realtime){
		return transferDateIS(datetime)+"  "+transferRealTimeIS(realtime);
	}
	public String transferDateIS(int datetime){
		int year=datetime / 10000;
		datetime %= 10000;
		int month=datetime / 100;
		int day = datetime % 100;
		String monthStr="";
		String dayStr="";
		if(month < 10)
			monthStr="0"+month+"��";
		else
			monthStr=month+"��";
		if(day < 10)
			dayStr="0"+day+"��";
		else 
			dayStr=day+"��";
		return year+"��"+monthStr+dayStr;
	}
	public String transferRealTimeIS(int realTime){
		int hour=realTime / 10000;
		realTime %= 10000;
		int minute= realTime / 100;
		int seconds=realTime % 100;
		String hourStr="",minuteStr="",secondsStr="";
		if(hour< 10)
			hourStr="0"+hour;
		else 
			hourStr=hour+"";
		
		if(minute < 10)
			minuteStr="0"+minute;
		else 
			minuteStr=minute+"";
		
		if(seconds < 10)
			secondsStr="0"+seconds;
		else
			secondsStr=seconds+"";
		return hourStr+":"+minuteStr+":"+secondsStr;
	}
	
	//ͨ�����ֲ����㷨����ֵ�Ƿ��������������,Ŀ���Ƿ�ĳֵ�ڸ���Ȥ������
	public int isContain(int num,int cNum[],int size){
		int low=0;
		int high=size-1;
		int middle=(low+high)/2; 
		while(num!=cNum[middle] && low<=high){
            if(num<=cNum[middle])  
                high=middle-1;  
            else  
                low=middle+1; 
            middle=(low+high)/2;
		}
		
		if(cNum[middle]==num)
			return 1;
		else
			return 0;
	}
	//ȥ�����û�����Ȥ��𲻷��� ��Ϣ���߻��MessageInfo
	public void deleteDisInterest(ArrayList<MessageInfo> messages,String category){
		String cat[]=category.split(",");
		int len=cat.length;
		int catNum[]=new int[len];
		
		for(int i=0;i<len;i++){
			catNum[i]=Integer.parseInt(cat[i].trim());
		}
		List<MessageInfo> delList=new ArrayList<MessageInfo>();
		for(MessageInfo m:messages){
			if(isContain(m.getCategory(),catNum,len)==0)
				delList.add(m);
		}
		messages.removeAll(delList);
	}
	
	
	//�Խ�Ҫ���еĻ �������򣬰���ʱ�����磬����MessageInfo
	//1,����dateTime����
	public int partitionMessageByStartDateTime(ArrayList<MessageInfo> a,int low,int high)  
	{  
	    int key=a.get(low).getStart_datetime(); //���ӱ�ĵ�һ����¼�������¼  
	    while(low < high)   //�ӱ�����˽�������м�ɨ��  
	    {  
	        while(low < high && a.get(high).getStart_datetime() <=key)
	            --high;  
	        {                  //���Ȗ����¼С�ļ�¼�������Ͷ�  
	        	MessageInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	  
	        while(low < high && a.get(low).getStart_datetime() >= key)  
	            ++low;  
	        {                 //���Ȗ����¼��ļ�¼�������Ͷ�  
	        	MessageInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	    }  
	    return low;   //���ؖ������ڵ�λ��  
	}  
	
	//����ʱ����������
	public void qsortMessageByStartDateTime(ArrayList<MessageInfo> a,int b,int e)  
	{  
	    if(b < e)  
	    {  
	        int m=partitionMessageByStartDateTime(a,b,e);  
	        qsortMessageByStartDateTime(a,b,m-1);  
	        qsortMessageByStartDateTime(a,m+1,e);  
	    }  
	}
	
	////�Խ�Ҫ���еĻ �������򣬰���ʱ�����磬����MessageInfo������startrealTime����
	public int partitionMessageByStartRealTime(ArrayList<MessageInfo> a,int low,int high)  
	{  
	    int key=a.get(low).getStart_realtime(); //���ӱ�ĵ�һ����¼�������¼  
	    while(low < high)   //�ӱ�����˽�������м�ɨ��  
	    {  
	        while(low < high && a.get(high).getStart_realtime() >=key)
	            --high;  
	        {                  //���Ȗ����¼С�ļ�¼�������Ͷ�  
	        	MessageInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	  
	        while(low < high && a.get(low).getStart_realtime() <= key)  
	            ++low;  
	        {                 //���Ȗ����¼��ļ�¼�������Ͷ�  
	        	MessageInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	    }  
	    return low;   //���ؖ������ڵ�λ��  
	}  
	
	//����ʱ����������
	public void qsortMessageByStartRealTime(ArrayList<MessageInfo> a,int b,int e)  
	{  
	    if(b < e)  
	    {  
	        int m=partitionMessageByStartRealTime(a,b,e);  
	        qsortMessageByStartRealTime(a,b,m-1);  
	        qsortMessageByStartRealTime(a,m+1,e);  
	    }  
	}
	
	
	//����Ϣ���߻ �������򣬰���ʱ�����磬����MessageInfo
	//1,����dateTime����
	public int partitionMessageByDateTime(ArrayList<MessageInfo> a,int low,int high)  
	{  
	    int key=a.get(low).getDatetime(); //���ӱ�ĵ�һ����¼�������¼  
	    while(low < high)   //�ӱ�����˽�������м�ɨ��  
	    {  
	        while(low < high && a.get(high).getDatetime() <=key)
	            --high;  
	        {                  //���Ȗ����¼С�ļ�¼�������Ͷ�  
	        	MessageInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	  
	        while(low < high && a.get(low).getDatetime() >= key)  
	            ++low;  
	        {                 //���Ȗ����¼��ļ�¼�������Ͷ�  
	        	MessageInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	    }  
	    return low;   //���ؖ������ڵ�λ��  
	}  
	
	//����ʱ����������
	public void qsortMessageByDateTime(ArrayList<MessageInfo> a,int b,int e)  
	{  
	    if(b < e)  
	    {  
	        int m=partitionMessageByDateTime(a,b,e);  
	        qsortMessageByDateTime(a,b,m-1);  
	        qsortMessageByDateTime(a,m+1,e);  
	    }  
	}
	
	//2,����realTime����
	public int partitionMessageByRealTime(ArrayList<MessageInfo> a,int low,int high)  
	{  
	    int key=a.get(low).getRealtime(); //���ӱ�ĵ�һ����¼�������¼  
	    while(low < high)   //�ӱ�����˽�������м�ɨ��  
	    {  
	        while(low < high && a.get(high).getRealtime() <=key)
	            --high;  
	        {                  //���Ȗ����¼С�ļ�¼�������Ͷ�  
	        	MessageInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	  
	        while(low < high && a.get(low).getRealtime() >= key)  
	            ++low;  
	        {                 //���Ȗ����¼��ļ�¼�������Ͷ�  
	        	MessageInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	    }  
	    return low;   //���ؖ������ڵ�λ��  
	}  
	
	//����ʱ����������
	public void qsortMessageByRealTime(ArrayList<MessageInfo> a,int b,int e)  
	{  
	    if(b < e)  
	    {  
	        int m=partitionMessageByRealTime(a,b,e);  
	        qsortMessageByRealTime(a,b,m-1);  
	        qsortMessageByRealTime(a,m+1,e);  
	    }  
	}
	
	//�������ʱ������ȵ��������������ֵ�ߵ�������
	public void makeSameTimeMaxTrust(ArrayList<MessageInfo> m,int size){
		for(int i=0;i<size;i++){
			MessageInfo temp=m.get(i);
			int index=i;//ʱ����ȵ��±�
			for(int j=i+1;j<size;j++){
				if(temp.getRealtime()==m.get(j).getRealtime()){//�ҳ�����ʱ����ȵ�ʵ��
					index=j;
				}
				else
					break;
			}
			if(index != i){
				System.out.println("yes too");
				//����ʱ����ȸ�������ֵ��������
				qsortSameTimeByTrust(m,i,index);
				i=index;//�������ÿ�ʼ������λ��
			}
		}
	}
	
	//��������ֵ�ɸߵ�������MessageInfo
	public int partitionSameTimeByTrust(ArrayList<MessageInfo> a,int low,int high)  
	{  
	    String key=a.get(low).getUserInfo().getTrust_value(); //���ӱ�ĵ�һ����¼�������¼  
	    while(low < high)   //�ӱ�����˽�������м�ɨ��  
	    {  
	        while(low < high && a.get(high).getUserInfo().getTrust_value().compareTo(key) >=0)
	            --high;  
	        {                  //���Ȗ����¼С�ļ�¼�������Ͷ�  
	        	MessageInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	  
	        while(low < high && a.get(low).getUserInfo().getTrust_value().compareTo(key) <= 0)  
	            ++low;  
	        {                 //���Ȗ����¼��ļ�¼�������Ͷ�  
	        	MessageInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	    }  
	    return low;   //���ؖ������ڵ�λ��  
	}  
	
	//����ʱ����������
	public void qsortSameTimeByTrust(ArrayList<MessageInfo> a,int b,int e)  
	{  
	    if(b < e)  
	    {  
	        int m=partitionSameTimeByTrust(a,b,e);  
	        qsortSameTimeByTrust(a,b,m-1);  
	        qsortSameTimeByTrust(a,m+1,e);  
	    }  
	}
	
	//ͨ��int�͸���Ȥ���õ�������
	/*{'����':'1','����':'2','����':'3','�Ƶ�':'4','�ֵ�':'5',
		'��ӰԺ':'6','չ���ݳ�':'7','�˶�����':'8','ϴԡ��Ħ':'9','KTV/�ư�':'10','����':'11','д����Ӱ':'12',
		'ҽ�Ʊ���':'13','��������':'14','��Ƹ��':'15','����':'16','����':'17','����':'18','�ɶ�':'19','����':'20','��ѵ':'21',
		'����':'22'}*/
	public String getCategoryIS(int category){
		String result = null;
		switch(category){
		case 1:result=WebConstant.C_SHOP;break;
		case 2:result=WebConstant.C_TRAVEL;break;
		case 3:result=WebConstant.C_SUPERMARKET;break;
		case 4:result=WebConstant.C_HOTEL;break;
		case 5:result=WebConstant.C_STREET;break;
		case 6:result=WebConstant.C_CINEMA;break;
		case 7:result=WebConstant.C_EXHIBITION;break;
		case 8:result=WebConstant.C_SPORTS;break;
		case 9:result=WebConstant.C_PARLORS;break;
		case 10:result=WebConstant.C_KTVBAR;break;
		case 11:result=WebConstant.C_BEAUTY;break;
		case 12:result=WebConstant.C_PICTORIAL;break;
		case 13:result=WebConstant.C_HEALTHCARE;break;
		case 14:result=WebConstant.C_CARSERVICE;break;
		case 15:result=WebConstant.C_JOBFAIRS;break;
		case 16:result=WebConstant.C_MEETING;break;
		case 17:result=WebConstant.C_ENVIRONMENTALPROTECTION;break;
		case 18:result=WebConstant.C_FESTIVAL;break;
		case 19:result=WebConstant.C_PARTY;break;
		case 20:result=WebConstant.C_LECTURE;break;
		case 21:result=WebConstant.C_TRAIN;break;
		case 22:result=WebConstant.C_OTHER;break;
		}
		
		return result;
	}
	
	//�õ�����ʱ�䣬������-����
	public String getRealDay(){
		String dutyDays[]=(new java.sql.Date(System.currentTimeMillis()).toString()).split("-");
		StringBuffer dutyDaysSB=new StringBuffer();
		for(String d:dutyDays)
			dutyDaysSB.append(d);
		return dutyDaysSB.toString();
	}
	//�õ�ʱ����ʱ�䣬�����ˣ�����
	public String getRealTime(){
		StringBuffer dutytimesSB=new StringBuffer();
		String dutytimes[]=(new java.sql.Time(System.currentTimeMillis()).toString()).split(":");
		int flag=1;
		for(String t:dutytimes){
			if(flag==1){  //��ֹ������ʱ��Ϊtt H:mm:ss��
				dutytimesSB.append(Integer.parseInt(t)+"");
				flag=2;
			}
			else
				dutytimesSB.append(t);
		}
		return dutytimesSB.toString();
	}
	
	//ͨ������Ķ���year������day,���ڵ��������У��õ�֮��Ķ������������ַ�������
	public String getDaySequenceByBetween(int daySequence,int year,int day){
		int nowTemp=daySequence % 10000;
		int nowYear=daySequence / 10000;
		int nowMonth=nowTemp /100;
		int nowDay= nowTemp % 100;
		
		int yearStr=nowYear-year;
		int monthStr=0;
		int dayStr=0;
		if(nowDay-day<=0){
			if(nowMonth-1 > 0){
				yearStr=nowYear-year;
				monthStr=nowMonth-1;
				dayStr=getMonthDay(nowYear-year, nowMonth-1)+nowDay-day;
			}
			else {
					yearStr=nowYear-1-year;
					monthStr=12;
					dayStr=getMonthDay(nowYear-1-year, 12)+nowDay-day;
			}
		}
		else{
			yearStr=nowYear-year;
			monthStr=nowMonth;
			dayStr=nowDay-day;
		}
		String monthString="";
		String dayString="";
		if(monthStr<10)
			monthString="0"+monthStr;
		else
			monthString=monthStr+"";
		
		if(dayStr < 10)
			dayString="0"+dayStr;
		else
			dayString=dayStr+"";
		
		return yearStr+monthString+dayString;
	}
	
	
	public int[] getStartEndByDayType(int chooseDayType){
		int []sequence=new int[2];
		int startday=Integer.parseInt(getRealDay());
		int start=0;
		int end=0;
		
		switch(chooseDayType){
		case WebConstant.DATE_TODAY:	start=startday;break;
		case WebConstant.DATE_TOMORROW:	start=Integer.parseInt(getDaySequenceByBetween(startday, 0, 1));break;
		case WebConstant.DATE_DAYBEFORE:	start=Integer.parseInt(getDaySequenceByBetween(startday, 0, 2));break;
		case WebConstant.DATE_TWODAYS:	{start=Integer.parseInt(getDaySequenceByBetween(startday, 0, 2));end=startday;}break;
		case WebConstant.DATE_THREEDAYS:	{start=Integer.parseInt(getDaySequenceByBetween(startday, 0, 3));end=startday;}break;
		case WebConstant.DATE_FOURDAYS: 	{start=Integer.parseInt(getDaySequenceByBetween(startday, 0, 4));end=startday;}break;
		case WebConstant.DATE_FIVEDAYS:	{start=Integer.parseInt(getDaySequenceByBetween(startday, 0, 5));end=startday;}break;
		case WebConstant.DATE_SIXDAYS:	{start=Integer.parseInt(getDaySequenceByBetween(startday, 0, 6));end=startday;}break;
		case WebConstant.DATE_SEVENDAYS:	{start=Integer.parseInt(getDaySequenceByBetween(startday, 0, 7));end=startday;}break;
		case WebConstant.DATE_TODAYBEFOREYEAR: start=Integer.parseInt(getDaySequenceByBetween(startday, 1, 0));break;
		case WebConstant.DATE_TOMORROWBEFOREYEAR:start=Integer.parseInt(getDaySequenceByBetween(startday, 1, 1));break;
		case WebConstant.DATE_DAYBEFOREBEFOREYEAR:start=Integer.parseInt(getDaySequenceByBetween(startday, 1, 2));break;
		case WebConstant.DATE_TWODAYSBEFOREYEAR:{start=Integer.parseInt(getDaySequenceByBetween(startday, 1, 2));end=Integer.parseInt(getDaySequenceByBetween(startday, 1, 0));}break;
		case WebConstant.DATE_THREEDAYSBEFOREYEAR:{start=Integer.parseInt(getDaySequenceByBetween(startday, 1, 3));end=Integer.parseInt(getDaySequenceByBetween(startday, 1, 0));}break;
		case WebConstant.DATE_FOURDAYSBEFOREYEAR:{start=Integer.parseInt(getDaySequenceByBetween(startday, 1, 4));end=Integer.parseInt(getDaySequenceByBetween(startday, 1, 0));}break;
		case WebConstant.DATE_FIVEDAYSBEFOREYEAR:{start=Integer.parseInt(getDaySequenceByBetween(startday, 1, 5));end=Integer.parseInt(getDaySequenceByBetween(startday, 1, 0));}break;
		case WebConstant.DATE_SIXDAYSBEFOREYEAR:{start=Integer.parseInt(getDaySequenceByBetween(startday, 1, 6));end=Integer.parseInt(getDaySequenceByBetween(startday, 1, 0));}break;
		case WebConstant.DATE_SEVENDAYSBEFOREYEAR:{start=Integer.parseInt(getDaySequenceByBetween(startday, 1, 7));end=Integer.parseInt(getDaySequenceByBetween(startday, 1, 0));}break;
		}
		sequence[0]=start;
		sequence[1]=end;
		return sequence;
	}
	
	//��MessageInfo��hashmap����ͨ���������򣬵õ��Ӹߵ��͵�˳��
	public int partitionHashMapOfMessageinfo(List<Map.Entry<MessageInfo, Float>> a,int low,int high)
	{
		Float key=a.get(low).getValue(); //���ӱ�ĵ�һ����¼�������¼
	    while(low < high)   //�ӱ�����˽�������м�ɨ��
	    {
	        while(low < high && a.get(high).getValue() >= key)
	            --high;
	        {                  //���Ȗ����¼С�ļ�¼�������Ͷ�
	        	Map.Entry<MessageInfo, Float> temp=a.get(low);
	        	a.set(low, a.get(high));
	        	a.set(high, temp);
	        }

	        while(low < high && a.get(low).getValue() <= key)
	            ++low;
	        {                 //���Ȗ����¼��ļ�¼�������Ͷ�
	        	Map.Entry<MessageInfo, Float> temp=a.get(low);
	        	a.set(low, a.get(high));
	        	a.set(high, temp);
	        }
	    }
	    return low;   //���ؖ������ڵ�λ��
	}

	public void qsortHashMapOfMessageinfo(List<Map.Entry<MessageInfo, Float>> a,int b,int e)
	{
	    if(b < e)
	    {
	        int m=partitionHashMapOfMessageinfo(a,b,e);
	        qsortHashMapOfMessageinfo(a,b,m-1);
	        qsortHashMapOfMessageinfo(a,m+1,e);
	    }
	}
	
	
	//��MessageInfo��hashmap����ͨ���������򣬵õ��ӽ���Զ��˳��,double,��Ҫ��Ӧ����
	public int partitionHashMapOfMessageinfoDouble(List<Map.Entry<MessageInfo, Double>> a,int low,int high)
	{
		double key=a.get(low).getValue(); //���ӱ�ĵ�һ����¼�������¼
	    while(low < high)   //�ӱ�����˽�������м�ɨ��
	    {
	        while(low < high && a.get(high).getValue() <= key)
	            --high;
	        {                  //���Ȗ����¼С�ļ�¼�������Ͷ�
	        	Map.Entry<MessageInfo, Double> temp=a.get(low);
	        	a.set(low, a.get(high));
	        	a.set(high, temp);
	        }

	        while(low < high && a.get(low).getValue() >= key)
	            ++low;
	        {                 //���Ȗ����¼��ļ�¼�������Ͷ�
	        	Map.Entry<MessageInfo, Double> temp=a.get(low);
	        	a.set(low, a.get(high));
	        	a.set(high, temp);
	        }
	    }
	    return low;   //���ؖ������ڵ�λ��
	}

	public void qsortHashMapOfMessageinfoDouble(List<Map.Entry<MessageInfo, Double>> a,int b,int e)
	{
	    if(b < e)
	    {
	        int m=partitionHashMapOfMessageinfoDouble(a,b,e);
	        qsortHashMapOfMessageinfoDouble(a,b,m-1);
	        qsortHashMapOfMessageinfoDouble(a,m+1,e);
	    }
	}
	
	
	//�Ծ����е͵���
	public int partitionHashMapOfMessageinfoDistance(List<Map.Entry<MessageInfo, Double>> a,int low,int high)
	{
		double key=a.get(low).getValue(); //���ӱ�ĵ�һ����¼�������¼
	    while(low < high)   //�ӱ�����˽�������м�ɨ��
	    {
	        while(low < high && a.get(high).getValue() <= key)
	            --high;
	        {                  //���Ȗ����¼С�ļ�¼�������Ͷ�
	        	Map.Entry<MessageInfo, Double> temp=a.get(low);
	        	a.set(low, a.get(high));
	        	a.set(high, temp);
	        }

	        while(low < high && a.get(low).getValue() >= key)
	            ++low;
	        {                 //���Ȗ����¼��ļ�¼�������Ͷ�
	        	Map.Entry<MessageInfo, Double> temp=a.get(low);
	        	a.set(low, a.get(high));
	        	a.set(high, temp);
	        }
	    }
	    return low;   //���ؖ������ڵ�λ��
	}

	public void qsortHashMapOfMessageinfoDistance(List<Map.Entry<MessageInfo, Double>> a,int b,int e)
	{
	    if(b < e)
	    {
	        int m=partitionHashMapOfMessageinfoDistance(a,b,e);
	        qsortHashMapOfMessageinfoDistance(a,b,m-1);
	        qsortHashMapOfMessageinfoDistance(a,m+1,e);
	    }
	}
	
	
	//��List<Map.Entry<Integer, Float>>��hashmap����ͨ���������򣬵õ��Ӹߵ��͵�˳��
	public int partitionTopList(List<Map.Entry<Integer, Float>> a,int low,int high)
	{
		Float key=a.get(low).getValue(); //���ӱ�ĵ�һ����¼�������¼
	    while(low < high)   //�ӱ�����˽�������м�ɨ��
	    {
	        while(low < high && a.get(high).getValue() <= key)
	            --high;
	        {                  //���Ȗ����¼С�ļ�¼�������Ͷ�
	        	Map.Entry<Integer, Float> temp=a.get(low);
	        	a.set(low, a.get(high));
	        	a.set(high, temp);
	        }

	        while(low < high && a.get(low).getValue() >= key)
	            ++low;
	        {                 //���Ȗ����¼��ļ�¼�������Ͷ�
	        	Map.Entry<Integer, Float> temp=a.get(low);
	        	a.set(low, a.get(high));
	        	a.set(high, temp);
	        }
	    }
	    return low;   //���ؖ������ڵ�λ��
	}

	public void qsortTopList(List<Map.Entry<Integer, Float>> a,int b,int e)
	{
	    if(b < e)
	    {
	        int m=partitionTopList(a,b,e);
	        qsortTopList(a,b,m-1);
	        qsortTopList(a,m+1,e);
	    }
	}
	
	
	//�Ƚ������ַ������õ�������ͬ���ַ���
	public int searchUser(String findUserName,String user){
		int count=0,index=0,j=index;
		for(int i=0;i<findUserName.length();i++){
			for(;j<user.length();j++){
				if(findUserName.charAt(i)==user.charAt(j)){
					count++;
					index=j;
					break;
				}
			}
			j=index;
		}
		return count;
	}
	
	//�Բ��ҵ��û����������ƥ��������ɸߵ�������
	public int partitionFindUser(List<Map.Entry<UserInfo, Integer>> a,int low,int high)
	{
		double key=a.get(low).getValue(); //���ӱ�ĵ�һ����¼�������¼
	    while(low < high)   //�ӱ�����˽�������м�ɨ��
	    {
	        while(low < high && a.get(high).getValue() <= key)
	            --high;
	        {                  //���Ȗ����¼С�ļ�¼�������Ͷ�
	        	Map.Entry<UserInfo, Integer> temp=a.get(low);
	        	a.set(low, a.get(high));
	        	a.set(high, temp);
	        }

	        while(low < high && a.get(low).getValue() >= key)
	            ++low;
	        {                 //���Ȗ����¼��ļ�¼�������Ͷ�
	        	Map.Entry<UserInfo, Integer> temp=a.get(low);
	        	a.set(low, a.get(high));
	        	a.set(high, temp);
	        }
	    }
	    return low;   //���ؖ������ڵ�λ��
	}

	public void qsortFindUser(List<Map.Entry<UserInfo, Integer>> a,int b,int e)
	{
	    if(b < e)
	    {
	        int m=partitionFindUser(a,b,e);
	        qsortFindUser(a,b,m-1);
	        qsortFindUser(a,m+1,e);
	    }
	}
}
