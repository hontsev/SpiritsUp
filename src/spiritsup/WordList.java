package spiritsup;

public class WordList {
	public static String[] goodadj={"��","����","����","����","��","����"};
	public static String[] badadj={"��","�ѹ�","��ɥ","����","ʹ��","��","���"};
	public static String[] schoolnoun={"ѧУ","��ѧ","����","����","ͬѧ","��ʦ","�Ͽ�","�¿�","�μ�","У԰","�ɼ�"};
	public static String[] familynoun={"�ְ�","����","��","����","ĸ��","��","����","үү","����","����","��ү","��ĸ","��ͥ","��"};
	public static String[] yes={"��","��","��","��","��"};
	public static String[] no={"��","����","����","��","��",};
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
