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
	
	//按照省份，城市，县/地区，街道等信息返回
	public String[] getPlaceNameByCoorinateByBaiduMap(String coordinate){
		String placeName[]=new String[4];
		//构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		//创建GET方法的实例
		GetMethod getMethod = new GetMethod("http://api.map.baidu.com/geocoder?location="+coordinate+
				  "&output=json&key="+WebConstant.BAIDU_MAP_KEY);
		getMethod.getParams().setParameter("http.protocol.cookie-policy",CookiePolicy.BROWSER_COMPATIBILITY);
		  //使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
		    new DefaultHttpMethodRetryHandler());
		try {
		   //执行getMethod
		   int statusCode = httpClient.executeMethod(getMethod);
		   if (statusCode != HttpStatus.SC_OK) {
		    System.err.println("Method failed: "
		      + getMethod.getStatusLine());
		   }
		 //读取内容 
		 String response=new String(getMethod.getResponseBodyAsString());
		 JSONObject jsonObject=JSONObject.fromObject(response);
		 //处理内容
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
		   //发生致命的异常，可能是协议不对或者返回的内容有问题
		 } catch (IOException e) {
		   //发生网络异常
		 } finally {
		   //释放连接
		   getMethod.releaseConnection();
		 }
		 return placeName;
	}
	
	//如果是计算任意两点的距离，有两种方法：一种利用勾股定理计算，适用于两点距离很近的情况；一种按标准的球面大圆劣弧长度计算，适用于距离较远的情况
	//单位：米
	public double getDistance(double lon1, double lat1, double lon2, double lat2){
		double ew1, ns1, ew2, ns2;
		double dx, dy, dew;
		double distance;
		// 角度转换为弧度
		ew1 = lon1 * WebConstant.DEF_PI180;
		ns1 = lat1 * WebConstant.DEF_PI180;
		ew2 = lon2 * WebConstant.DEF_PI180;
		ns2 = lat2 * WebConstant.DEF_PI180;
		// 经度差
		dew = ew1 - ew2;
		// 若跨东经和西经180 度，进行调整
		if (dew > WebConstant.DEF_PI)
		dew = WebConstant.DEF_2PI - dew;
		else if (dew < -WebConstant.DEF_PI)
		dew = WebConstant.DEF_2PI + dew;
		dx = WebConstant.DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)
		dy = WebConstant.DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)
		// 勾股定理求斜边长
		distance = Math.sqrt(dx * dx + dy * dy);
		return distance;
	}
	//对于相册进行排序，图片序号越大代表时间越靠前，然后按照越近时间来排序
	public int partitionPhoto(ArrayList<PhotosInfo> a,int low,int high)  
	{  
	    int key=a.get(low).getPhoto_id(); //用子表的第一个记录作杻轴记录  
	    while(low < high)   //从表的两端交替地向中间扫描  
	    {  
	        while(low < high && a.get(high).getPhoto_id() <=key)
	            --high;  
	        {                  //将比杻轴记录小的记录交换到低端  
	        	PhotosInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	  
	        while(low < high && a.get(low).getPhoto_id() >= key)  
	            ++low;  
	        {                 //将比杻轴记录大的记录交换到低端  
	        	PhotosInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	    }  
	    return low;   //返回杻轴所在的位置  
	}  
	
	//按照时间最早排序
	public void qsortPhoto(ArrayList<PhotosInfo> a,int b,int e)  
	{  
	    if(b < e)  
	    {  
	        int m=partitionPhoto(a,b,e);  
	        qsortPhoto(a,b,m-1);  
	        qsortPhoto(a,m+1,e);  
	    }  
	}
	//根据msgmsgrelation表中的时间进行排序，按照时间最早
	//1、首先要根据日期，如果是同一天要根据实时时间
	public int partitionMsgMsgRDateTime(ArrayList<MsgMsgRelationship> a,int low,int high)  
	{  
	    int key=a.get(low).getDatetime(); //用子表的第一个记录作杻轴记录  
	    while(low < high)   //从表的两端交替地向中间扫描  
	    {  
	        while(low < high && a.get(high).getDatetime() <=key)
	            --high;  
	        {                  //将比杻轴记录小的记录交换到低端  
	        	MsgMsgRelationship temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	  
	        while(low < high && a.get(low).getDatetime() >= key)  
	            ++low;  
	        {                 //将比杻轴记录大的记录交换到低端  
	        	MsgMsgRelationship temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	    }  
	    return low;   //返回杻轴所在的位置  
	}  
	
	//按照时间最早排序
	public void qsortMsgMsgRDateTime(ArrayList<MsgMsgRelationship> a,int b,int e)  
	{  
	    if(b < e)  
	    {  
	        int m=partitionMsgMsgRDateTime(a,b,e);  
	        qsortMsgMsgRDateTime(a,b,m-1);  
	        qsortMsgMsgRDateTime(a,m+1,e);  
	    }  
	}
	//根据msgmsgrelation表中的时间进行排序，按照时间最早
	//2、如果是同一天要根据实时时间
	public int partitionMsgMsgRRealTime(ArrayList<MsgMsgRelationship> a,int low,int high)  
	{  
	    int key=a.get(low).getRealtime(); //用子表的第一个记录作杻轴记录  
	    while(low < high)   //从表的两端交替地向中间扫描  
	    {  
	        while(low < high && a.get(high).getRealtime() <=key)
	            --high;  
	        {                  //将比杻轴记录小的记录交换到低端  
	        	MsgMsgRelationship temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	  
	        while(low < high && a.get(low).getRealtime() >= key)  
	            ++low;  
	        {                 //将比杻轴记录大的记录交换到低端  
	        	MsgMsgRelationship temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	    }  
	    return low;   //返回杻轴所在的位置  
	}  
	
	//按照时间最早排序
	public void qsortMsgMsgRRealTime(ArrayList<MsgMsgRelationship> a,int b,int e)  
	{  
	    if(b < e)  
	    {  
	        int m=partitionMsgMsgRRealTime(a,b,e);  
	        qsortMsgMsgRRealTime(a,b,m-1);  
	        qsortMsgMsgRRealTime(a,m+1,e);  
	    }  
	}
	
	
	//对于天气数据 的快速排序WeatherInfo
	public int partition(ArrayList<WeatherInfo> a,int low,int high)  
	{  
	    int key=a.get(low).getRealtime(); //用子表的第一个记录作杻轴记录  
	    while(low < high)   //从表的两端交替地向中间扫描  
	    {  
	        while(low < high && a.get(high).getRealtime() <=key)
	            --high;  
	        {                  //将比杻轴记录小的记录交换到低端  
	        	WeatherInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	  
	        while(low < high && a.get(low).getRealtime() >= key)  
	            ++low;  
	        {                 //将比杻轴记录大的记录交换到低端  
	        	WeatherInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	    }  
	    return low;   //返回杻轴所在的位置  
	}  
	
	//按照时间最早排序
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
			return between/3600+"小时前";
		}else if(between/60!=0){
			return between/60+"分钟前";
		}else{
			return between+"秒前";
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
				return betweenYear+"年前";
			}
			else if(betweenYear == 1){
				
				betweenMonth=nowMonth+12-beforeMonth;
				if(betweenMonth >= 2){
					return betweenMonth+"月前";
				}
				else{
					betweenDay=getMonthDay(beforeYear, beforeMonth)+nowDay-beforeDay;
					if(betweenDay>2)
						return betweenDay+"天前";
					else if(betweenDay==2)
						return "前天";
					else return "昨天";
				}
			}
			else{
				betweenMonth=nowMonth-beforeMonth;
				if(betweenMonth >= 2){
					return betweenMonth+"月前";
				}
				else if(betweenMonth ==1){
					betweenDay=getMonthDay(beforeYear, beforeMonth)+nowDay-beforeDay;
					if(betweenDay>2)
						return betweenDay+"天前";
					else if(betweenDay==2)
						return "前天";
					else return "昨天";
				}
				else{
					betweenDay=nowDay-beforeDay;
					if(betweenDay>2)
						return betweenDay+"天前";
					else if(betweenDay==2)
						return "前天";
					else return "昨天";
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
			if(result.equals("昨天")||result.equals("前天")){
				return result+" "+transferTimeStr(time);
			}
			else{
				long ttime=getTotalSeconds(time);
				long tnowTime=getTotalSeconds(nowTime);
				long between=tnowTime-ttime;
				if(between/3600!=0){
					return between/3600+"小时前";
				}else if(between/60!=0){
					return between/60+"分钟前";
				}else{
					return between+"秒前";
				}
			}
		}else
			return result+" "+transferTimeStr(time);
	}
	
	//将int时间转发为2012年12月12日 09:09:09 形式
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
			monthStr="0"+month+"月";
		else
			monthStr=month+"月";
		if(day < 10)
			dayStr="0"+day+"日";
		else 
			dayStr=day+"日";
		return year+"年"+monthStr+dayStr;
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
	
	//通过二分查找算法查找值是否包含于有序序列,目的是否某值在感兴趣数组中
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
	//去掉和用户感兴趣类别不符的 信息或者活动，MessageInfo
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
	
	
	//对将要进行的活动 快速排序，按照时间最早，对于MessageInfo
	//1,按照dateTime排序
	public int partitionMessageByStartDateTime(ArrayList<MessageInfo> a,int low,int high)  
	{  
	    int key=a.get(low).getStart_datetime(); //用子表的第一个记录作杻轴记录  
	    while(low < high)   //从表的两端交替地向中间扫描  
	    {  
	        while(low < high && a.get(high).getStart_datetime() <=key)
	            --high;  
	        {                  //将比杻轴记录小的记录交换到低端  
	        	MessageInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	  
	        while(low < high && a.get(low).getStart_datetime() >= key)  
	            ++low;  
	        {                 //将比杻轴记录大的记录交换到低端  
	        	MessageInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	    }  
	    return low;   //返回杻轴所在的位置  
	}  
	
	//按照时间最早排序
	public void qsortMessageByStartDateTime(ArrayList<MessageInfo> a,int b,int e)  
	{  
	    if(b < e)  
	    {  
	        int m=partitionMessageByStartDateTime(a,b,e);  
	        qsortMessageByStartDateTime(a,b,m-1);  
	        qsortMessageByStartDateTime(a,m+1,e);  
	    }  
	}
	
	////对将要进行的活动 快速排序，按照时间最早，对于MessageInfo，按照startrealTime排序
	public int partitionMessageByStartRealTime(ArrayList<MessageInfo> a,int low,int high)  
	{  
	    int key=a.get(low).getStart_realtime(); //用子表的第一个记录作杻轴记录  
	    while(low < high)   //从表的两端交替地向中间扫描  
	    {  
	        while(low < high && a.get(high).getStart_realtime() >=key)
	            --high;  
	        {                  //将比杻轴记录小的记录交换到低端  
	        	MessageInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	  
	        while(low < high && a.get(low).getStart_realtime() <= key)  
	            ++low;  
	        {                 //将比杻轴记录大的记录交换到低端  
	        	MessageInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	    }  
	    return low;   //返回杻轴所在的位置  
	}  
	
	//按照时间最早排序
	public void qsortMessageByStartRealTime(ArrayList<MessageInfo> a,int b,int e)  
	{  
	    if(b < e)  
	    {  
	        int m=partitionMessageByStartRealTime(a,b,e);  
	        qsortMessageByStartRealTime(a,b,m-1);  
	        qsortMessageByStartRealTime(a,m+1,e);  
	    }  
	}
	
	
	//对信息或者活动 快速排序，按照时间最早，对于MessageInfo
	//1,按照dateTime排序
	public int partitionMessageByDateTime(ArrayList<MessageInfo> a,int low,int high)  
	{  
	    int key=a.get(low).getDatetime(); //用子表的第一个记录作杻轴记录  
	    while(low < high)   //从表的两端交替地向中间扫描  
	    {  
	        while(low < high && a.get(high).getDatetime() <=key)
	            --high;  
	        {                  //将比杻轴记录小的记录交换到低端  
	        	MessageInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	  
	        while(low < high && a.get(low).getDatetime() >= key)  
	            ++low;  
	        {                 //将比杻轴记录大的记录交换到低端  
	        	MessageInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	    }  
	    return low;   //返回杻轴所在的位置  
	}  
	
	//按照时间最早排序
	public void qsortMessageByDateTime(ArrayList<MessageInfo> a,int b,int e)  
	{  
	    if(b < e)  
	    {  
	        int m=partitionMessageByDateTime(a,b,e);  
	        qsortMessageByDateTime(a,b,m-1);  
	        qsortMessageByDateTime(a,m+1,e);  
	    }  
	}
	
	//2,按照realTime排序
	public int partitionMessageByRealTime(ArrayList<MessageInfo> a,int low,int high)  
	{  
	    int key=a.get(low).getRealtime(); //用子表的第一个记录作杻轴记录  
	    while(low < high)   //从表的两端交替地向中间扫描  
	    {  
	        while(low < high && a.get(high).getRealtime() <=key)
	            --high;  
	        {                  //将比杻轴记录小的记录交换到低端  
	        	MessageInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	  
	        while(low < high && a.get(low).getRealtime() >= key)  
	            ++low;  
	        {                 //将比杻轴记录大的记录交换到低端  
	        	MessageInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	    }  
	    return low;   //返回杻轴所在的位置  
	}  
	
	//按照时间最早排序
	public void qsortMessageByRealTime(ArrayList<MessageInfo> a,int b,int e)  
	{  
	    if(b < e)  
	    {  
	        int m=partitionMessageByRealTime(a,b,e);  
	        qsortMessageByRealTime(a,b,m-1);  
	        qsortMessageByRealTime(a,m+1,e);  
	    }  
	}
	
	//如果存在时间上相等的情况，则按照信任值高到低排序
	public void makeSameTimeMaxTrust(ArrayList<MessageInfo> m,int size){
		for(int i=0;i<size;i++){
			MessageInfo temp=m.get(i);
			int index=i;//时间相等的下标
			for(int j=i+1;j<size;j++){
				if(temp.getRealtime()==m.get(j).getRealtime()){//找出发布时间相等的实例
					index=j;
				}
				else
					break;
			}
			if(index != i){
				System.out.println("yes too");
				//对于时间相等根据信任值快速排序
				qsortSameTimeByTrust(m,i,index);
				i=index;//重新设置开始搜索的位置
			}
		}
	}
	
	//按照信任值由高到低排序，MessageInfo
	public int partitionSameTimeByTrust(ArrayList<MessageInfo> a,int low,int high)  
	{  
	    String key=a.get(low).getUserInfo().getTrust_value(); //用子表的第一个记录作杻轴记录  
	    while(low < high)   //从表的两端交替地向中间扫描  
	    {  
	        while(low < high && a.get(high).getUserInfo().getTrust_value().compareTo(key) >=0)
	            --high;  
	        {                  //将比杻轴记录小的记录交换到低端  
	        	MessageInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	  
	        while(low < high && a.get(low).getUserInfo().getTrust_value().compareTo(key) <= 0)  
	            ++low;  
	        {                 //将比杻轴记录大的记录交换到低端  
	        	MessageInfo temp=a.get(low); 
	            a.set(low, a.get(high));
	            a.set(high, temp);
	        }  
	    }  
	    return low;   //返回杻轴所在的位置  
	}  
	
	//按照时间最早排序
	public void qsortSameTimeByTrust(ArrayList<MessageInfo> a,int b,int e)  
	{  
	    if(b < e)  
	    {  
	        int m=partitionSameTimeByTrust(a,b,e);  
	        qsortSameTimeByTrust(a,b,m-1);  
	        qsortSameTimeByTrust(a,m+1,e);  
	    }  
	}
	
	//通过int型感兴趣类别得到中文名
	/*{'店铺':'1','旅游':'2','超市':'3','酒店':'4','街道':'5',
		'电影院':'6','展览演出':'7','运动健身':'8','洗浴按摩':'9','KTV/酒吧':'10','丽人':'11','写真摄影':'12',
		'医疗保健':'13','汽车服务':'14','招聘会':'15','会议':'16','环保':'17','节庆':'18','派对':'19','讲座':'20','培训':'21',
		'其他':'22'}*/
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
	
	//得到日期时间，除掉了-符号
	public String getRealDay(){
		String dutyDays[]=(new java.sql.Date(System.currentTimeMillis()).toString()).split("-");
		StringBuffer dutyDaysSB=new StringBuffer();
		for(String d:dutyDays)
			dutyDaysSB.append(d);
		return dutyDaysSB.toString();
	}
	//得到时分秒时间，除掉了：符号
	public String getRealTime(){
		StringBuffer dutytimesSB=new StringBuffer();
		String dutytimes[]=(new java.sql.Time(System.currentTimeMillis()).toString()).split(":");
		int flag=1;
		for(String t:dutytimes){
			if(flag==1){  //防止服务器时间为tt H:mm:ss型
				dutytimesSB.append(Integer.parseInt(t)+"");
				flag=2;
			}
			else
				dutytimesSB.append(t);
		}
		return dutytimesSB.toString();
	}
	
	//通过间隔的多少year，多少day,现在的日期序列，得到之后的多少年多少天的字符串序列
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
	
	//对MessageInfo的hashmap集合通过快速排序，得到从高到低的顺序
	public int partitionHashMapOfMessageinfo(List<Map.Entry<MessageInfo, Float>> a,int low,int high)
	{
		Float key=a.get(low).getValue(); //用子表的第一个记录作杻轴记录
	    while(low < high)   //从表的两端交替地向中间扫描
	    {
	        while(low < high && a.get(high).getValue() >= key)
	            --high;
	        {                  //将比杻轴记录小的记录交换到低端
	        	Map.Entry<MessageInfo, Float> temp=a.get(low);
	        	a.set(low, a.get(high));
	        	a.set(high, temp);
	        }

	        while(low < high && a.get(low).getValue() <= key)
	            ++low;
	        {                 //将比杻轴记录大的记录交换到低端
	        	Map.Entry<MessageInfo, Float> temp=a.get(low);
	        	a.set(low, a.get(high));
	        	a.set(high, temp);
	        }
	    }
	    return low;   //返回杻轴所在的位置
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
	
	
	//对MessageInfo的hashmap集合通过快速排序，得到从近到远的顺序,double,主要相应距离
	public int partitionHashMapOfMessageinfoDouble(List<Map.Entry<MessageInfo, Double>> a,int low,int high)
	{
		double key=a.get(low).getValue(); //用子表的第一个记录作杻轴记录
	    while(low < high)   //从表的两端交替地向中间扫描
	    {
	        while(low < high && a.get(high).getValue() <= key)
	            --high;
	        {                  //将比杻轴记录小的记录交换到低端
	        	Map.Entry<MessageInfo, Double> temp=a.get(low);
	        	a.set(low, a.get(high));
	        	a.set(high, temp);
	        }

	        while(low < high && a.get(low).getValue() >= key)
	            ++low;
	        {                 //将比杻轴记录大的记录交换到低端
	        	Map.Entry<MessageInfo, Double> temp=a.get(low);
	        	a.set(low, a.get(high));
	        	a.set(high, temp);
	        }
	    }
	    return low;   //返回杻轴所在的位置
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
	
	
	//对距离有低到高
	public int partitionHashMapOfMessageinfoDistance(List<Map.Entry<MessageInfo, Double>> a,int low,int high)
	{
		double key=a.get(low).getValue(); //用子表的第一个记录作杻轴记录
	    while(low < high)   //从表的两端交替地向中间扫描
	    {
	        while(low < high && a.get(high).getValue() <= key)
	            --high;
	        {                  //将比杻轴记录小的记录交换到低端
	        	Map.Entry<MessageInfo, Double> temp=a.get(low);
	        	a.set(low, a.get(high));
	        	a.set(high, temp);
	        }

	        while(low < high && a.get(low).getValue() >= key)
	            ++low;
	        {                 //将比杻轴记录大的记录交换到低端
	        	Map.Entry<MessageInfo, Double> temp=a.get(low);
	        	a.set(low, a.get(high));
	        	a.set(high, temp);
	        }
	    }
	    return low;   //返回杻轴所在的位置
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
	
	
	//对List<Map.Entry<Integer, Float>>的hashmap集合通过快速排序，得到从高到低的顺序
	public int partitionTopList(List<Map.Entry<Integer, Float>> a,int low,int high)
	{
		Float key=a.get(low).getValue(); //用子表的第一个记录作杻轴记录
	    while(low < high)   //从表的两端交替地向中间扫描
	    {
	        while(low < high && a.get(high).getValue() <= key)
	            --high;
	        {                  //将比杻轴记录小的记录交换到低端
	        	Map.Entry<Integer, Float> temp=a.get(low);
	        	a.set(low, a.get(high));
	        	a.set(high, temp);
	        }

	        while(low < high && a.get(low).getValue() >= key)
	            ++low;
	        {                 //将比杻轴记录大的记录交换到低端
	        	Map.Entry<Integer, Float> temp=a.get(low);
	        	a.set(low, a.get(high));
	        	a.set(high, temp);
	        }
	    }
	    return low;   //返回杻轴所在的位置
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
	
	
	//比较两个字符串，得到正序相同的字符数
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
	
	//对查找的用户结果，根据匹配的数量由高到低排序
	public int partitionFindUser(List<Map.Entry<UserInfo, Integer>> a,int low,int high)
	{
		double key=a.get(low).getValue(); //用子表的第一个记录作杻轴记录
	    while(low < high)   //从表的两端交替地向中间扫描
	    {
	        while(low < high && a.get(high).getValue() <= key)
	            --high;
	        {                  //将比杻轴记录小的记录交换到低端
	        	Map.Entry<UserInfo, Integer> temp=a.get(low);
	        	a.set(low, a.get(high));
	        	a.set(high, temp);
	        }

	        while(low < high && a.get(low).getValue() >= key)
	            ++low;
	        {                 //将比杻轴记录大的记录交换到低端
	        	Map.Entry<UserInfo, Integer> temp=a.get(low);
	        	a.set(low, a.get(high));
	        	a.set(high, temp);
	        }
	    }
	    return low;   //返回杻轴所在的位置
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
