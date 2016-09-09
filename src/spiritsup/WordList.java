package spiritsup;

public class WordList {
	public static String[] goodadj={"好","快乐","开心","高兴","棒","优秀"};
	public static String[] badadj={"不","难过","沮丧","悲伤","痛苦","差","落后"};
	public static String[] schoolnoun={"学校","大学","高中","初中","同学","老师","上课","下课","课间","校园","成绩"};
	public static String[] familynoun={"爸爸","父亲","爹","妈妈","母亲","娘","家人","爷爷","奶奶","姥姥","姥爷","父母","家庭","家"};
	public static String[] yes={"是","对","恩","嗯","好"};
	public static String[] no={"不","不是","不对","否","非",};
	public static boolean isGoodAdj(String word)
	{
		for(int i=0;i<goodadj.length;i++)
		{
			if(word.equals(goodadj[i]))return true;
		}
		return false;
	}
	public static boolean isBadAdj(String word)
	{
		for(int i=0;i<badadj.length;i++)
		{
			if(word.equals(badadj[i]))return true;
		}
		return false;
	}
	public static boolean isSchoolNoun(String word)
	{
		for(int i=0;i<schoolnoun.length;i++)
		{
			if(word.equals(schoolnoun[i]))return true;
		}
		return false;
	}
	public static boolean isFamilyNoun(String word)
	{
		for(int i=0;i<familynoun.length;i++)
		{
			if(word.equals(familynoun[i]))return true;
		}
		return false;
	}
	public static boolean isYes(String word)
	{
		for(int i=0;i<yes.length;i++)
		{
			if(word.equals(yes[i]))return true;
		}
		return false;
	}
	public static boolean isNo(String word)
	{
		for(int i=0;i<no.length;i++)
		{
			if(word.equals(no[i]))return true;
		}
		return false;
	}
}
