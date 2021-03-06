package BTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


//List = interface 
//List를 구현한 클래스 = ArrayList,Vector,LinkedList
//Set에서는 중복 X  


@SuppressWarnings("unused")
public class FiveWayBTreeNode {

	private FiveWayBTreeNode parent;
	private List<Integer> keyList;
	private List<FiveWayBTreeNode> children;
	
	private int size=0;
	

	
	
	
	//생성자 
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
	//keyList 접근 
	public List getkeyList() {
		return keyList;
	}
	
	//keyList에서 해당 값이 몇번째에 위치해있는지 확인 
	public int keyListindex(Integer e) {
		int index=0;
		for(int i=0;i<getkeyListsize();i++) {
			if(keyList.get(i)==e) {
				index=i;
			}
		}
		return index;
	}
	
	//children 접근
	public List getchilderen() {
		return children;
	}
	//children 세팅
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
	
	
	//parent 접근 
	public FiveWayBTreeNode getparent() {
		return parent;
	}
	//parent 세팅 
	public void setparent(FiveWayBTreeNode p) {
		parent=p;
	}
	
	
	
	//keyList의 크기 반환 
	public int getkeyListsize() {
		return keyList.size();
	}
	
	//key가 5개일 경우(분리해야할 경우) true, 아닐 시 false 반환 함수 
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
		
		//key가 5미만 일때  
		keyList.add(e);
		Collections.sort(keyList); //정렬
			
		
	}
	
	
	
	//해당 노드의 key 중에서 e 없을 경우 e보다 큰 수 중 가장 작은 수의 인덱스 값 반환 
	public int returnindex(Integer e) {
		int jud=0;
		int index=-1;
		int i;
		for(i=0;i<keyList.size();i++) {
			if(((Integer)keyList.get(i))>e) {
				//key중에서 큰 값 중 가장 작은 수의 인덱스 값 반환 
				jud=1;
				index=i;
				break;
			}
			
			
		}
		if(jud==0) {
			//e보다 큰 값이 없을 경우 작은 값들 중 가장 큰 수의 인덱스 값+1 반환 
			index=keyList.size();
		}
		
		
		return index;
	}
	
	//children List 확인해서 모두 null이면 true, 하나라도 존재하면 false 반환 
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