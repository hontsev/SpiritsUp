package spiritsup;

import java.util.*;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

public class Event {
	public List<Word> words;				//用户输入的句子的分词后结果
	public String res;						//程序输出语句
	public int emotion;					//整数储存用户当前情绪，正数表示积极，负数表示消极
	public SituationTree situation;			//用于记录用户所处情景状态的树
	public Person nowperson;				//用于记录用户情景状态
	public PersonInfo waitinfo;				//待确认的用户情景状态
	public State state;						//程序的回答语句的状态（枚举）
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
		String s="当前："+ situation.getNowSituation();
		s+="\n信息：";
		if(nowperson.info!=null)
		for(int i=0;i<nowperson.info.size();i++)
		{
			s+=" "+nowperson.info.get(i).name+":"+nowperson.info.get(i).content;
		}
		s+="\n状态：";
		if(nowperson.situations!=null)
			for(int i=0;i<nowperson.situations.size();i++)
			{
				s+=" "+nowperson.situations.get(i).name;
			}
		if(waitinfo!=null)s+="\n待确认信息："+waitinfo.name+","+waitinfo.content;
		s+="\n系统：";
		if(state.equals(State.ask))s+="提问";
		else if(state.equals(State.confirm))s+="确认";
		else if(state.equals(State.advice))s+="提建议";
		return s;
	}
	public void clear()
	{
		situation.nowsituation=situation.rootsituation;
	}
	public void ask(String q)
	{
		if(q.length()>0)res=""+q+"怎么样？";
	}
	public void confirm(PersonInfo i)
	{
		if(i!=null)
		{
			res="你"+i.name+i.content+"，是么？";
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
			if(words.get(ii).word.equals("学校"))
			{
				waitinfo=new PersonInfo("学校","有烦心事");
				break;
			}
			if(words.get(ii).word.equals("成绩"))
			{
				for(int iii=ii+1;iii<words.size();iii++)
				{
					if(words.get(iii).word.equals("不")
					  ||words.get(iii).word.equals("差") )
					{
						waitinfo=new PersonInfo("成绩","不好");
						break;
					}
				}
			}
			if(words.get(ii).word.equals("同学"))
			{
				for(int iii=ii+1;iii<words.size();iii++)
				{
					if(words.get(iii).word.equals("嘲笑")
					  ||words.get(iii).word.equals("骂") )
					{
						waitinfo=new PersonInfo("同学","态度不好");
						break;
					}
				}
			}
		}
	}
	public void getInfo()
	{
		waitinfo=null;
		//以下顺序是晚判断的优先级更高。即先从特例词组中找结果
		getNA();				//名词-形容词 词组
		getVN();				//动词-名词 词组
		getRVN();				//主谓宾 词组
		getRNA();				//主系表 词组
		getex();				//特例
	}
	public void judge()
	{
		if(waitinfo==null)return;
		if(situation.nowsituation.name.equals("无"))
		{
			if(waitinfo.name.equals("学校")
			 ||waitinfo.name.equals("成绩"))
			{
				situation.nowsituation=situation.find("学校");
			}
		}
		else if(situation.nowsituation.name.equals("学校"))
		{
			if(waitinfo.name.equals("成绩"))
			{
				situation.nowsituation=situation.find("学业");
			}
			if(waitinfo.name.equals("同学"))
			{
				situation.nowsituation=situation.find("同学关系");
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
			//每一个了解到的情况对应一句建议，最后输出结果等于是对各个情况各自建议的叠加。
			s=nowperson.info.get(i);
			if(s.name.equals("学校"))
			{
					res+="要好好适应学校的环境了，认真学习。";
			}
			if(s.name.equals("成绩"))
			{
						PersonInfo pi = nowperson.getInfo("玩");
						if(pi!=null)
						{
							if(res.length()>0)res+="而且，";
							res+="玩"+pi.content+"可能会影响学习，所以以后最好改一改！";
						}
						else
						{
							res+="成绩差，要努力提高自己，有问题多问问同学、老师。";
						}
			}
			else if(s.name.equals("同学"))
			{
						PersonInfo pi = nowperson.getInfo("玩");
						if(pi!=null)
						{
							if(res.length()>0)res+="而且，";
							res+="你总是玩"+pi.content+"导致同学对你态度不好。";
						}
						else
						{
							//res+="";
						}		
			}			
		}
		if(res.length()<=0)res="放松些心情吧，会好一点。比如，听些轻松欢快的乐曲？";
	}
	public void setResult()
	{
		res="";
		//if(situation.nowsituation.equals(situation.rootsituation))res="嘿，你好啊！心情怎么样呢？有什么问题都可以跟我倾诉哦。";
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
				res="恩，加油哦！";
				state=State.advice;
				emotion=1;
			}
			else if(emotion<0)
			{
				res="遇到什么麻烦了吗？";
				state=State.advice;
				emotion=-1;
				getInfo();
				judge();
				if(waitinfo!=null){confirm(waitinfo);state=State.confirm;}
			}
			else
			{
				res="遇到什么麻烦了吗？";
				state=State.advice;
				emotion=-1;
			}
			break;
		default:break;
		}
	}
	public List<Word> getWordlist(String str)
	{
		//使用ansj分词系统来进行分词，返回分好词并带有词性标注的词组
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
