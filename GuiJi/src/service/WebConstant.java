package service;

public interface WebConstant {
	//用户操作类型：1，发布信息；2，推荐信息；3，评论当前(对日志而言)；4，想参加；5，关注；6，加照片；7，发起活动；8，登录；9，退出；11，发表天气；12，天气赞成；
	//13，天气不赞成,；14，注册;15,删除信息；16，删除活动；17，删除回复;18,转发信息；19，转发活动；20，回复信息；21,推荐活动;22,取消关注；23，评论当前同时也评论原始(对日志而言)
	//24,评论原始(对日志而言);25,回复活动;26,心情原创；27，心情评论；28,删除心情原创；29，删除心情评论；30,增加照片;31,反馈意见
	int OP_MESSAGE =	1;
	int OP_RECOMMENDMSG =	2;
	int OP_COMMENT =	3;
	int OP_WANTJOIN =	4;
	int OP_ATTENTION =	5;
	int OP_ADDPHOTO =	6;
	int OP_ACTIVITY =	7;
	int OP_LOGIN =	8;
	int OP_LOGOUT =	9;
	int OP_ADDWEATHER =	11;
	int OP_APPROVEWEATHER =	12;
	int OP_DISAPPROVEWEATHER =	13;
	int OP_REGISTER =	14;
	int OP_DELETEMSG =	15;
	int OP_DELETEACTIVITY =	16;
	int OP_DELETEREPLY =	17;
	int OP_TRANSMITMSG =	18;
	int OP_TRANSMITACT =	19;
	int OP_REPLYMSG =	20;
	int OP_RECOMMENDACT =	21;
	int OP_DISATTENTION =	22;
	int OP_COMMENTAND =	23;
	int OP_COMMENTORIGNAL =	24;
	int OP_REPLYACT =	25;
	int OP_MOOD =	26;
	int OP_REPLYMOOD =	27;
	int OP_DELETEMOODORIGNAL =	28;
	int OP_DELETEMOODREPLY =	29;
	int OP_UPLOADPICTURE = 30;
	int OP_FEEDBACK	=	31;
	
	//注册时候的状态
	int	RG_CANREGISTER =	15;
	int RG_CANLOGIN	=	16;
	int RG_EMAILSAME =	17;
	int RG_NAMESAME	=	18;
	
	//数据库里信息与活动类型关系类型 msg_msg_relation表 ,实质上R_MSGCOMMENT 和 T_MSGORIGNAL对应；R_MSGTRANSMIT和T_MSGTRANSMIT对应。
	int R_MSGCOMMENT = 1;
	int R_MSGTRANSMIT = 2;
	int R_ACTCOMMENT = 3;
	int R_ACTTRANSMIT = 4;
	int R_MOODCOMMENT = 5;
	
	//数据库里面操作类型 MessageInfo表中信息
	int DB_MSGORIGNAL =	1;
	int DB_MSGCOMMENT =	2;
	int DB_MSGTRANSMIT =3;
	int DB_ACTORIGNAL =	4;
	int DB_ACTCOMMENT = 5;
	int DB_ACTTRANSMIT =6;
	int	DB_MOODORIGNAL =	7;
	int	DB_MOODCOMMENT =	8;
	
	//在页面上显示的信息与活动类型,jsp页面里面涉及到的type的值
	int T_MSGORIGNAL =	1;
	int T_MSGTRANSMIT =	2;
	int T_ACTORIGNAL =	3;
	int T_ACTTRANSMIT =	4;
	int T_MOODORIGNAL =	5;
	
	
	//UserUserRelationship 的用户关系类型
	int UUR_FANS =	1;//只粉丝
	int UUR_FOLLOWER =	2;//只关注
	int UUR_FRIEND =	3;//即粉丝即关注
	
	//性别
	int SEX_MALE = 1;
	int SEX_FEMAL =	2;
	
	//对于邮箱是否重复
	int EMAILEXIST = 1;
	int EMAILNOTEXIST = 2;
	
	//Action 的Result
	String TOLOGIN="toLogin";
	String SETUSERAVATORYES="setUserAvatorYes";
	String SETUSEREMAILYES="setUserEmailYes";
	String SETUPOWNINFOYES="setupOwnInfoYes";
	String SETUPINTERESTYES="setupInterestYes";
	String SETUPFEEDBACK="setupFeedBackYes";
	
	//距离计算使用的百度常量
	double DEF_PI = 3.14159265359; // PI
	double DEF_2PI= 6.28318530712; // 2*PI
	double DEF_PI180= 0.01745329252; // PI/180.0
	double DEF_R =6370693.5; // radius of earth
	
	String BAIDU_MAP_KEY="8522b8ebf0f921d66c943dd82946ae6c";
	
	
	//所有的类别
	int	C_TOTAL =	22;
	String C_SHOP =	"店铺";
	String C_TRAVEL =	"旅游";
	String C_SUPERMARKET =	"超市";
	String C_HOTEL =	"酒店";
	String C_STREET =	"街道";
	String C_CINEMA =	"电影院";
	String C_EXHIBITION =	"展览演出";
	String C_SPORTS =	"运动健身";
	String C_PARLORS =	"洗浴按摩";
	String C_KTVBAR =	"KTV/酒吧";
	String C_BEAUTY =	"丽人";
	String C_PICTORIAL =	"写真摄影";
	String C_HEALTHCARE =	"医疗保健";
	String C_CARSERVICE =	"汽车服务";
	String C_JOBFAIRS =	"招聘会";
	String C_MEETING =	"会议";
	String C_ENVIRONMENTALPROTECTION =	"环保";
	String C_FESTIVAL =	"节庆";
	String C_PARTY =	"派对";
	String C_LECTURE =	"讲座";
	String C_TRAIN =	"培训";
	String C_OTHER =	"其他";
	//类别：心情
	int C_MOOD =	50;
	
	//在新鲜地点热度选择时，日期的选择
	//"#{'今天':'1','昨天':'2','前天':'3','前2天':'4','前3天':'5','前4天':'6','前5天':'7','前6天':'8','前一周':'9',
	//'去年的今天':'11','去年的昨天':'12','去年的前天':'13','去年的前2天':'14',
	//'去年的前3天':'15','去年的前4天':'16','去年的前5天':'17','去年的前6天':'18','去年的前一周':'19'
	int DATE_TODAY = 1;
	int DATE_TOMORROW =	2;
	int DATE_DAYBEFORE =	3;
	int DATE_TWODAYS =	4;    
	int DATE_THREEDAYS =	5;
	int DATE_FOURDAYS =	6;
	int DATE_FIVEDAYS =	7;
	int DATE_SIXDAYS =	8;
	int DATE_SEVENDAYS =	9;
	int DATE_TODAYBEFOREYEAR = 11;
	int DATE_TOMORROWBEFOREYEAR =	12;    
	int DATE_DAYBEFOREBEFOREYEAR =	13;
	int DATE_TWODAYSBEFOREYEAR =	14;    
	int DATE_THREEDAYSBEFOREYEAR =	15;
	int DATE_FOURDAYSBEFOREYEAR =	16;
	int DATE_FIVEDAYSBEFOREYEAR =	17;
	int DATE_SIXDAYSBEFOREYEAR =	18;
	int DATE_SEVENDAYSBEFOREYEAR =	19;
	
	
	//在新鲜地点推荐页面中，全部和关注者类型
	int RECOMMEND_TOTAL =	1;
	int RECOMMEND_FOLLOWER =	2;
	
	//moodDynamic.jsp页面显示的关注者，和同城人
	int MOOD_FOLLOWER =	1;  //关注者
	int MOOD_SAMECITY =	2;  //同城
	
	//当前用户A，和用户B的关系状态
	//1,A,B没有任何关系；2，A为B的粉丝,也就是B关注A；3，B为A的粉丝，也就是A关注B；4，A，B互相关注
	int STATUS_NONE = 1;
	int STATUS_FANS =	2;
	int STATUS_FOLLOWER =	3;
	int STATUS_FRIEND =	4;
	int STATUS_SAME = 5;
	
	
	String WEBADMIN="burncom";
	int WEBADMINID=76;
}
