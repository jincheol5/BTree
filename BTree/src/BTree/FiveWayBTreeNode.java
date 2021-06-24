package BTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


//List = interface 
//List�� ������ Ŭ���� = ArrayList,Vector,LinkedList
//Set������ �ߺ� X  


@SuppressWarnings("unused")
public class FiveWayBTreeNode {

	private FiveWayBTreeNode parent;
	private List<Integer> keyList;
	private List<FiveWayBTreeNode> children;
	
	private int size=0;
	

	
	
	
	//������ 
	public FiveWayBTreeNode() {
		
		keyList=new ArrayList<Integer>();
		parent=null;
		children=new ArrayList<FiveWayBTreeNode>();
		for(int i=0;i<6;i++) {
			children.add(null);
			
		}
		
	}
	
	public int getsize() {
		return size;
	}
	//size plus
	public void sizeplus() {
		size++;
	}
	
	//set size
	public void setsize(int s) {
		size=s;
	}
	//keyList ���� 
	public List getkeyList() {
		return keyList;
	}
	
	//keyList���� �ش� ���� ���°�� ��ġ���ִ��� Ȯ�� 
	public int keyListindex(Integer e) {
		int index=0;
		for(int i=0;i<getkeyListsize();i++) {
			if(keyList.get(i)==e) {
				index=i;
			}
		}
		return index;
	}
	
	//children ����
	public List getchilderen() {
		return children;
	}
	//children ����
	public void setchilderen(FiveWayBTreeNode c,int index) {
		children.set(index, c);
	}
	public int findchilderen(FiveWayBTreeNode p) {
		int index=-1;
		for(int i=0;i<6;i++) {
			if(children.get(i)==p) {
				index=i;
				break;
			}
		}
		return index;
	}
	
	
	//parent ���� 
	public FiveWayBTreeNode getparent() {
		return parent;
	}
	//parent ���� 
	public void setparent(FiveWayBTreeNode p) {
		parent=p;
	}
	
	
	
	//keyList�� ũ�� ��ȯ 
	public int getkeyListsize() {
		return keyList.size();
	}
	
	//key�� 5���� ���(�и��ؾ��� ���) true, �ƴ� �� false ��ȯ �Լ� 
	public boolean judkeyListsize() {
		if(getkeyListsize()==5) {
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void addkeyList(Integer e) {
		
		//key�� 5�̸� �϶�  
		keyList.add(e);
		Collections.sort(keyList); //����
			
		
	}
	
	
	
	//�ش� ����� key �߿��� e ���� ��� e���� ū �� �� ���� ���� ���� �ε��� �� ��ȯ 
	public int returnindex(Integer e) {
		int jud=0;
		int index=-1;
		int i;
		for(i=0;i<keyList.size();i++) {
			if(((Integer)keyList.get(i))>e) {
				//key�߿��� ū �� �� ���� ���� ���� �ε��� �� ��ȯ 
				jud=1;
				index=i;
				break;
			}
			
			
		}
		if(jud==0) {
			//e���� ū ���� ���� ��� ���� ���� �� ���� ū ���� �ε��� ��+1 ��ȯ 
			index=keyList.size();
		}
		
		
		return index;
	}
	
	//children List Ȯ���ؼ� ��� null�̸� true, �ϳ��� �����ϸ� false ��ȯ 
	public boolean childrencheck() {
		int count=0;
		for(int i=0;i<6;i++) {
			if(children.get(i)==null) {
				count++;
				
			}
		}
		if(count==6) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	
	
}