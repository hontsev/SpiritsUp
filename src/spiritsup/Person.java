package spiritsup;

import java.util.ArrayList;
import java.util.List;

public class Person {
	List<PersonInfo> info;			//一个键-值对列表，表示各种获取到的个人信息
	List<Situation> situations;
	public Person()
	{
		info = new ArrayList<PersonInfo>();
		situations = new ArrayList<Situation>();
	}
	public void addSituation(Situation s)
	{
		for(int i=0;i<situations.size();i++)
		{
			if(s.equals(situations.get(i)))return;
		}
		situations.add(s);
	}
	public void addInfo(String infoname,String content)
	{
		PersonInfo tinfo = getInfo(infoname);
		if(tinfo==null)
		{
			info.add(new PersonInfo(infoname,content));
		}
		else
		{
			tinfo.content=content;
		}
	}
	public void addInfo(PersonInfo p)
	{
		PersonInfo tinfo = getInfo(p.name);
		if(tinfo==null)
		{
			info.add(new PersonInfo(p.name,p.content));
		}
		else
		{
			tinfo.content=p.content;
		}
	}
	public void deleteInfo(String infoname)
	{
		for(int i=0;i<info.size();i++)
		{
			if(info.get(i).name.equals(infoname))
			{
				info.remove(i);
				break;
			}
		}
	}
	public PersonInfo getInfo(String infoname)
	{
		for(int i=0;i<info.size();i++)
		{
			if(info.get(i).name.equals(infoname))
			{
				return info.get(i);
			}
		}
		return null;
	}
	public String getInfoContent(String infoname)
	{
		for(int i=0;i<info.size();i++)
		{
			if(info.get(i).name.equals(infoname))
			{
				return info.get(i).content;
			}
		}
		return null;
	}
}
