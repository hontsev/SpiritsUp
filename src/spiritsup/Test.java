package spiritsup;


import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

public class Test {
	public static void main(String args[]){
		String words = "�й��������Ĵ������Ź�֮һ�������ƾõ���ʷ�����Լ5000��ǰ������ԭ����Ϊ���Ŀ�ʼ���־�����֯�����ɹ��Һͳ���������������ݱ�ͳ�������������ʱ��ϳ��ĳ������ġ��̡��ܡ����������ơ��Ρ�Ԫ��������ȡ���ԭ������ʷ�ϲ����뱱���������彻������ս���ڶ������ںϳ�Ϊ�л����塣20���ͳ������������й��ľ��������˳���ʷ��̨��ȡ����֮���ǹ������塣1949���л����񹲺͹����������й���½����������������ƶȵ����塣�й����Ŷ�ʵ������Ļ�����ͳ������ʽ��ʫ�ʡ�Ϸ�����鷨�͹����ȣ����ڡ�Ԫ�������������硢������������й���Ҫ�Ĵ�ͳ���ա�"; 
        List<Term> temp = ToAnalysis.parse(words);
		System.out.println(temp.get(0).getName()); 
        System.out.println(temp.get(0).getNatureStr());
        System.out.println(temp.get(0).getOffe());
        System.out.println(temp.get(0).getRealName());
	}
}
