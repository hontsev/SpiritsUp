package spiritsup;

public class SituationTree {
	public 	Situation nowsituation;
	public Situation rootsituation;
	public void addSituation(String parentname,Situation newsituation)
	{
		Situation e = find(rootsituation,parentname);
		if(e!=null)e.addSituation(newsituation);
	}
	public Situation find(Situation e,String str)
	{
		if(e.name.equals(str))return e;
		else if(e.son.size()<=0)return null;
		else 
		{
			Situation te =null;
			for(int i=0;i<e.son.size();i++)
			{
				te=find(e.son.get(i),str);
				if(te!=null)return te;
			}
			return te;
		}
	}
	public Situation find(String str)
	{
		return find(rootsituation,str);
	}
	public String getNowSituation()
	{
		String res="";
		Situation e = nowsituation;
		while(e.parent!=null)
		{
			res = e.name+"->"+res;
			e=e.parent;
		}
		if(res.length()<=0)return "�޽��";
		res=res.substring(0,res.length()-2);
		
		return res;
	}
	public void  printTree(Situation e)
	{
		if(e.son.size()>0)
		{
			System.out.print(e.name);
			System.out.print(":");
			for(int i=0;i<e.son.size();i++)
			{
				System.out.print(e.son.get(i).name+" ");
				printTree(e.son.get(i));
			}
			System.out.print("\n");
		}
	}
	public SituationTree()
	{
		rootsituation=new Situation("��");
		addSituation("��",new Situation("ѧУ"));
		addSituation("��",new Situation("��ͥ"));
		addSituation("ѧУ",new Situation("ҵ��"));
		addSituation("ѧУ",new Situation("�˼ʹ�ϵ"));
		addSituation("ҵ��",new Situation("ѧҵ"));
		addSituation("�˼ʹ�ϵ",new Situation("ͬѧ��ϵ"));
		printTree(rootsituation);
		nowsituation=rootsituation;
		System.out.println(getNowSituation());
	}
}
