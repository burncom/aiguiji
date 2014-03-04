package service;

public interface WebConstant {
	//�û��������ͣ�1��������Ϣ��2���Ƽ���Ϣ��3�����۵�ǰ(����־����)��4����μӣ�5����ע��6������Ƭ��7��������8����¼��9���˳���11������������12�������޳ɣ�
	//13���������޳�,��14��ע��;15,ɾ����Ϣ��16��ɾ�����17��ɾ���ظ�;18,ת����Ϣ��19��ת�����20���ظ���Ϣ��21,�Ƽ��;22,ȡ����ע��23�����۵�ǰͬʱҲ����ԭʼ(����־����)
	//24,����ԭʼ(����־����);25,�ظ��;26,����ԭ����27���������ۣ�28,ɾ������ԭ����29��ɾ���������ۣ�30,������Ƭ;31,�������
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
	
	//ע��ʱ���״̬
	int	RG_CANREGISTER =	15;
	int RG_CANLOGIN	=	16;
	int RG_EMAILSAME =	17;
	int RG_NAMESAME	=	18;
	
	//���ݿ�����Ϣ�����͹�ϵ���� msg_msg_relation�� ,ʵ����R_MSGCOMMENT �� T_MSGORIGNAL��Ӧ��R_MSGTRANSMIT��T_MSGTRANSMIT��Ӧ��
	int R_MSGCOMMENT = 1;
	int R_MSGTRANSMIT = 2;
	int R_ACTCOMMENT = 3;
	int R_ACTTRANSMIT = 4;
	int R_MOODCOMMENT = 5;
	
	//���ݿ������������ MessageInfo������Ϣ
	int DB_MSGORIGNAL =	1;
	int DB_MSGCOMMENT =	2;
	int DB_MSGTRANSMIT =3;
	int DB_ACTORIGNAL =	4;
	int DB_ACTCOMMENT = 5;
	int DB_ACTTRANSMIT =6;
	int	DB_MOODORIGNAL =	7;
	int	DB_MOODCOMMENT =	8;
	
	//��ҳ������ʾ����Ϣ������,jspҳ�������漰����type��ֵ
	int T_MSGORIGNAL =	1;
	int T_MSGTRANSMIT =	2;
	int T_ACTORIGNAL =	3;
	int T_ACTTRANSMIT =	4;
	int T_MOODORIGNAL =	5;
	
	
	//UserUserRelationship ���û���ϵ����
	int UUR_FANS =	1;//ֻ��˿
	int UUR_FOLLOWER =	2;//ֻ��ע
	int UUR_FRIEND =	3;//����˿����ע
	
	//�Ա�
	int SEX_MALE = 1;
	int SEX_FEMAL =	2;
	
	//���������Ƿ��ظ�
	int EMAILEXIST = 1;
	int EMAILNOTEXIST = 2;
	
	//Action ��Result
	String TOLOGIN="toLogin";
	String SETUSERAVATORYES="setUserAvatorYes";
	String SETUSEREMAILYES="setUserEmailYes";
	String SETUPOWNINFOYES="setupOwnInfoYes";
	String SETUPINTERESTYES="setupInterestYes";
	String SETUPFEEDBACK="setupFeedBackYes";
	
	//�������ʹ�õİٶȳ���
	double DEF_PI = 3.14159265359; // PI
	double DEF_2PI= 6.28318530712; // 2*PI
	double DEF_PI180= 0.01745329252; // PI/180.0
	double DEF_R =6370693.5; // radius of earth
	
	String BAIDU_MAP_KEY="8522b8ebf0f921d66c943dd82946ae6c";
	
	
	//���е����
	int	C_TOTAL =	22;
	String C_SHOP =	"����";
	String C_TRAVEL =	"����";
	String C_SUPERMARKET =	"����";
	String C_HOTEL =	"�Ƶ�";
	String C_STREET =	"�ֵ�";
	String C_CINEMA =	"��ӰԺ";
	String C_EXHIBITION =	"չ���ݳ�";
	String C_SPORTS =	"�˶�����";
	String C_PARLORS =	"ϴԡ��Ħ";
	String C_KTVBAR =	"KTV/�ư�";
	String C_BEAUTY =	"����";
	String C_PICTORIAL =	"д����Ӱ";
	String C_HEALTHCARE =	"ҽ�Ʊ���";
	String C_CARSERVICE =	"��������";
	String C_JOBFAIRS =	"��Ƹ��";
	String C_MEETING =	"����";
	String C_ENVIRONMENTALPROTECTION =	"����";
	String C_FESTIVAL =	"����";
	String C_PARTY =	"�ɶ�";
	String C_LECTURE =	"����";
	String C_TRAIN =	"��ѵ";
	String C_OTHER =	"����";
	//�������
	int C_MOOD =	50;
	
	//�����ʵص��ȶ�ѡ��ʱ�����ڵ�ѡ��
	//"#{'����':'1','����':'2','ǰ��':'3','ǰ2��':'4','ǰ3��':'5','ǰ4��':'6','ǰ5��':'7','ǰ6��':'8','ǰһ��':'9',
	//'ȥ��Ľ���':'11','ȥ�������':'12','ȥ���ǰ��':'13','ȥ���ǰ2��':'14',
	//'ȥ���ǰ3��':'15','ȥ���ǰ4��':'16','ȥ���ǰ5��':'17','ȥ���ǰ6��':'18','ȥ���ǰһ��':'19'
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
	
	
	//�����ʵص��Ƽ�ҳ���У�ȫ���͹�ע������
	int RECOMMEND_TOTAL =	1;
	int RECOMMEND_FOLLOWER =	2;
	
	//moodDynamic.jspҳ����ʾ�Ĺ�ע�ߣ���ͬ����
	int MOOD_FOLLOWER =	1;  //��ע��
	int MOOD_SAMECITY =	2;  //ͬ��
	
	//��ǰ�û�A�����û�B�Ĺ�ϵ״̬
	//1,A,Bû���κι�ϵ��2��AΪB�ķ�˿,Ҳ����B��עA��3��BΪA�ķ�˿��Ҳ����A��עB��4��A��B�����ע
	int STATUS_NONE = 1;
	int STATUS_FANS =	2;
	int STATUS_FOLLOWER =	3;
	int STATUS_FRIEND =	4;
	int STATUS_SAME = 5;
	
	
	String WEBADMIN="burncom";
	int WEBADMINID=76;
}
