package spiritsup;

import java.util.*;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

public class Event {
	public List<Word> words;				//�û�����ľ��ӵķִʺ���
	public String res;						//����������
	public int emotion;					//���������û���ǰ������������ʾ������������ʾ����
	public SituationTree situation;			//���ڼ�¼�û������龰״̬����
	public Person nowperson;				//���ڼ�¼�û��龰״̬
	public PersonInfo waitinfo;				//��ȷ�ϵ��û��龰״̬
	public State state;						//����Ļش�����״̬��ö�٣�
	public Event()
	{
		state=State.advice;
		words = new ArrayList<Word>();
		res="";
		situation = new SituationTree();
		nowperson = new Person();
		emotion=0;
		ToAnalysis.parse(" ");
	}
	public String inputWords(List<Word> inputstr)		
	{
		words=inputstr;
		res="";
		setResult();
		return res;
	}
	public String showSituation()
	{
		String s="��ǰ��"+ situation.getNowSituation();
		s+="\n��Ϣ��";
		if(nowperson.info!=null)
		for(int i=0;i<nowperson.info.size();i++)
		{
			s+=" "+nowperson.info.get(i).name+":"+nowperson.info.get(i).content;
		}
		s+="\n״̬��";
		if(nowperson.situations!=null)
			for(int i=0;i<nowperson.situations.size();i++)
			{
				s+=" "+nowperson.situations.get(i).name;
			}
		if(waitinfo!=null)s+="\n��ȷ����Ϣ��"+waitinfo.name+","+waitinfo.content;
		s+="\nϵͳ��";
		if(state.equals(State.ask))s+="����";
		else if(state.equals(State.confirm))s+="ȷ��";
		else if(state.equals(State.advice))s+="�Ὠ��";
		return s;
	}
	public void clear()
	{
		situation.nowsituation=situation.rootsituation;
	}
	public void ask(String q)
	{
		if(q.length()>0)res=""+q+"��ô����";
	}
	public void confirm(PersonInfo i)
	{
		if(i!=null)
		{
			res="��"+i.name+i.content+"����ô��";
		}
	}
	public void getRVN()
	{
		for(int i=0;i<words.size()-2;i++)
		{
			if(words.get(i).type.toCharArray()[0]=='r')
			{
				for(int ii=i;ii<words.size()-1;ii++)
				{
					if(words.get(ii).type.toCharArray()[0]=='v')
					{
						for(int iii=ii;iii<words.size();iii++)
						{
							if(words.get(iii).type.toCharArray()[0]=='n')
							{
								if(waitinfo==null)waitinfo=new PersonInfo(words.get(ii).word,words.get(iii).word);
								break;
							}
						}
					}
				}
			}
		}
	}
	public void getRNA()
	{
		for(int i=0;i<words.size()-2;i++)
		{
			if(words.get(i).type.toCharArray()[0]=='r')
			{
				for(int ii=i;ii<words.size()-1;ii++)
				{
					if(words.get(ii).type.toCharArray()[0]=='n')
					{
						for(int iii=ii;iii<words.size();iii++)
						{
							if(words.get(iii).type.toCharArray()[0]=='a')
							{
								if(waitinfo==null)waitinfo=new PersonInfo(words.get(ii).word,words.get(iii).word);
								break;
							}
						}
					}
				}
			}
		}
	}
	public void getNA()
	{
			for(int ii=0;ii<words.size()-1;ii++)
			{
					if(words.get(ii).type.toCharArray()[0]=='n')
					{
						for(int iii=ii;iii<words.size();iii++)
						{
							if(words.get(iii).type.toCharArray()[0]=='a')
							{
								if(waitinfo==null)waitinfo=new PersonInfo(words.get(ii).word,words.get(iii).word);
								break;
							}
						}
					}
			}
	}
	public void getVN()
	{
			for(int ii=0;ii<words.size()-1;ii++)
			{
					if(words.get(ii).type.toCharArray()[0]=='v')
					{
						for(int iii=ii;iii<words.size();iii++)
						{
							if(words.get(iii).type.toCharArray()[0]=='n')
							{
								if(waitinfo==null)waitinfo=new PersonInfo(words.get(ii).word,words.get(iii).word);
								break;
							}
						}
					}
			}
	}
	public void getex()
	{
		for(int ii=0;ii<words.size();ii++)
		{
			if(words.get(ii).word.equals("ѧУ"))
			{
				waitinfo=new PersonInfo("ѧУ","�з�����");
				break;
			}
			if(words.get(ii).word.equals("�ɼ�"))
			{
				for(int iii=ii+1;iii<words.size();iii++)
				{
					if(words.get(iii).word.equals("��")
					  ||words.get(iii).word.equals("��") )
					{
						waitinfo=new PersonInfo("�ɼ�","����");
						break;
					}
				}
			}
			if(words.get(ii).word.equals("ͬѧ"))
			{
				for(int iii=ii+1;iii<words.size();iii++)
				{
					if(words.get(iii).word.equals("��Ц")
					  ||words.get(iii).word.equals("��") )
					{
						waitinfo=new PersonInfo("ͬѧ","̬�Ȳ���");
						break;
					}
				}
			}
		}
	}
	public void getInfo()
	{
		waitinfo=null;
		//����˳�������жϵ����ȼ����ߡ����ȴ������������ҽ��
		getNA();				//����-���ݴ� ����
		getVN();				//����-���� ����
		getRVN();				//��ν�� ����
		getRNA();				//��ϵ�� ����
		getex();				//����
	}
	public void judge()
	{
		if(waitinfo==null)return;
		if(situation.nowsituation.name.equals("��"))
		{
			if(waitinfo.name.equals("ѧУ")
			 ||waitinfo.name.equals("�ɼ�"))
			{
				situation.nowsituation=situation.find("ѧУ");
			}
		}
		else if(situation.nowsituation.name.equals("ѧУ"))
		{
			if(waitinfo.name.equals("�ɼ�"))
			{
				situation.nowsituation=situation.find("ѧҵ");
			}
			if(waitinfo.name.equals("ͬѧ"))
			{
				situation.nowsituation=situation.find("ͬѧ��ϵ");
			}
		}
		nowperson.addSituation(situation.nowsituation);
	}
	public void giveadvice()
	{
		PersonInfo s ;
		if(nowperson.info!=null)
		for(int i=0;i<nowperson.info.size();i++)
		{
			//ÿһ���˽⵽�������Ӧһ�佨�飬��������������ǶԸ���������Խ���ĵ��ӡ�
			s=nowperson.info.get(i);
			if(s.name.equals("ѧУ"))
			{
					res+="Ҫ�ú���ӦѧУ�Ļ����ˣ�����ѧϰ��";
			}
			if(s.name.equals("�ɼ�"))
			{
						PersonInfo pi = nowperson.getInfo("��");
						if(pi!=null)
						{
							if(res.length()>0)res+="���ң�";
							res+="��"+pi.content+"���ܻ�Ӱ��ѧϰ�������Ժ���ø�һ�ģ�";
						}
						else
						{
							res+="�ɼ��ҪŬ������Լ��������������ͬѧ����ʦ��";
						}
			}
			else if(s.name.equals("ͬѧ"))
			{
						PersonInfo pi = nowperson.getInfo("��");
						if(pi!=null)
						{
							if(res.length()>0)res+="���ң�";
							res+="��������"+pi.content+"����ͬѧ����̬�Ȳ��á�";
						}
						else
						{
							//res+="";
						}		
			}			
		}
		if(res.length()<=0)res="����Щ����ɣ����һ�㡣���磬��Щ���ɻ����������";
	}
	public void setResult()
	{
		res="";
		//if(situation.nowsituation.equals(situation.rootsituation))res="�٣���ð���������ô���أ���ʲô���ⶼ���Ը�������Ŷ��";
		switch(state)
		{
		case confirm:
			int yes=0;
			for(int i=0;i<words.size();i++)
			{
				if(WordList.isYes(words.get(i).word))
				{
					yes+=1;
					break;
				}
				else if(WordList.isNo(words.get(i).word))
				{
					yes-=1;
					break;
				}
			}
			if(yes>0)
			{
				nowperson.addInfo(waitinfo);
				nowperson.addSituation(situation.nowsituation);
				state=State.advice;
			}
			else if(yes<=0)
			{
				state=State.ask;
				ask(waitinfo.name);
			}
			getInfo();
			if(waitinfo!=null){confirm(waitinfo);state=State.confirm;}
			judge();
			giveadvice();
			if(res.length()>0)state=State.advice;
			break;
		case ask:
			getInfo();
			judge();
			if(waitinfo!=null){confirm(waitinfo);state=State.confirm;}
			else {ask(situation.nowsituation.name);state=State.ask;}
			break;
		case advice:
			int emotionv=0;
			for(int i=0;i<words.size();i++)
			{
				if(WordList.isGoodAdj(words.get(i).word))
				{
					emotionv++;
					break;
				}
				else if(WordList.isBadAdj(words.get(i).word))
				{
					emotionv--;
					break;
				}
			}
			if(emotionv>0)
			{
				res="��������Ŷ��";
				state=State.advice;
				emotion=1;
			}
			else if(emotion<0)
			{
				res="����ʲô�鷳����";
				state=State.advice;
				emotion=-1;
				getInfo();
				judge();
				if(waitinfo!=null){confirm(waitinfo);state=State.confirm;}
			}
			else
			{
				res="����ʲô�鷳����";
				state=State.advice;
				emotion=-1;
			}
			break;
		default:break;
		}
	}
	public List<Word> getWordlist(String str)
	{
		//ʹ��ansj�ִ�ϵͳ�����зִʣ����طֺôʲ����д��Ա�ע�Ĵ���
		List<Term> temp = ToAnalysis.parse(str);
		System.out.println(temp);
		List<Word> res = new ArrayList<Word>();
		for(int i=0;i<temp.size();i++)
		{
			res.add(new Word(temp.get(i).getName(),temp.get(i).getNatureStr()));
		}
		return res;
	}
}
