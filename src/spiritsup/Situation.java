package spiritsup;

import java.util.*;

public class Situation {
	public String name;
	public Situation parent;
	public List<Situation> son; 
	public void addSituation(Situation e)
	{
		e.parent=this;
		son.add(e);
	}
	public Situation()
	{
		name="";
		parent=null;
		son=new ArrayList<Situation>();
	}
	public Situation(String str)
	{
		name=str;
		parent=null;
		son=new ArrayList<Situation>();
	}
}
