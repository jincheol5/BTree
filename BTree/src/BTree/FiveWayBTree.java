package BTree;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.Queue;
class newiterator implements Iterator<Integer>{
	
	public FiveWayBTreeNode root;
	Queue<Integer> myq;
	Queue q;
	
	//생성자
	newiterator(FiveWayBTreeNode p){
		root=p;
		myq=new LinkedList<Integer>();
		q=showtreeup(root,myq);
		
		
	}
	
	
	
	public Queue showtreeup(FiveWayBTreeNode p,Queue q) {
		//오름차순 순서대로 큐에 삽입 
		if(p.childrencheck()) {
			//자식이 없으면 
			for(int i=0;i<p.getkeyListsize();i++) {
				
				q.offer((Integer)p.getkeyList().get(i));
				return q;
			}
		}
		else {
			
			for(int i=0;i<p.getkeyListsize();i++) {
				if(i==p.getkeyListsize()-1) {
					if(p.getchilderen().get(i)!=null) {
						return showtreeup((FiveWayBTreeNode)p.getchilderen().get(i),q);
					}
					System.out.println(p.getkeyList().get(i));
					if(p.getchilderen().get(i+1)!=null) {
						return showtreeup((FiveWayBTreeNode)p.getchilderen().get(i+1),q);
					}
				}
				else {
					if(p.getchilderen().get(i)!=null) {
						return showtreeup((FiveWayBTreeNode)p.getchilderen().get(i),q);
					}
					q.offer((Integer)p.getkeyList().get(i));
					return q;
				}
				
				
			}
			
			
		}
		return null;
		
		
		
	}
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if(q==null) {
			return false;
		}
		if(q.peek()!=null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		
		return (Integer)q.poll();
	}
	
}


public class FiveWayBTree implements NavigableSet<Integer> {

	
	private FiveWayBTreeNode root;
	
	
	//생성자
	public FiveWayBTree()
	{
		root=new FiveWayBTreeNode();
		
		
	}
	public void setroot(FiveWayBTreeNode p) {
		root=p;
	}
	
	public FiveWayBTreeNode getroot() {
		return root;
	}
	
	@Override
	public boolean add(Integer e) {
		// TODO Auto-generated method stub
		
		
		if(contains(e)==true) {
			//중복 값일 경우 add 종료 
			
			return false;
		}
		else {
			//해당 값이 트리 안에 없을 경우 실행
			
			FiveWayBTreeNode f=findnode(root,e); //값이 들어갈 노드
			
			
			f.addkeyList(e);
			
			
			
			if(f.judkeyListsize()==true) {
				separation(f);
			}
			
			root.sizeplus();
				
			return true;
		}
		
		
		
		
		
		
	}
	
	//key가 5개 일 때 분리 함수 
	public void separation(FiveWayBTreeNode f) {
		
		if(f==root) {
			
			FiveWayBTreeNode p=new FiveWayBTreeNode();
			FiveWayBTreeNode c1=new FiveWayBTreeNode();
			FiveWayBTreeNode c2=new FiveWayBTreeNode();
			
			Integer vl1,vl2,vm,vr1,vr2;
			vm=(Integer)f.getkeyList().get(2);
			
			vl1=(Integer)f.getkeyList().get(0);
			vl2=(Integer)f.getkeyList().get(1);
			vr1=(Integer)f.getkeyList().get(3);
			vr2=(Integer)f.getkeyList().get(4);
			
			p.addkeyList(vm);
			
			c1.addkeyList(vl1);
			c1.addkeyList(vl2);
			
			c2.addkeyList(vr1);
			c2.addkeyList(vr2);
			
			
			
			if(f.childrencheck()==true) {
				//자식 노드가 없을 경우   
				c1.setparent(p);
				c2.setparent(p);
				
				p.setchilderen(c1,0);
				p.setchilderen(c2,1);
				
				
				
				
				p.setsize(root.getsize()); //tree 크기 넘기기
				
				root=null;
				root=p;
				
				
				
				
			}
			else {
				//자식 노드가 있을 경우
				for(int i=0;i<3;i++) {
					//root의 왼쪽 자식들을 가져간다
					if(((FiveWayBTreeNode)f.getchilderen().get(i))!=null) {
						c1.setchilderen(((FiveWayBTreeNode)f.getchilderen().get(i)), i);
						((FiveWayBTreeNode)f.getchilderen().get(i)).setparent(c1);
					}
					
				}
				
				for(int i=3;i<6;i++) {
					//root의 오른쪽 자식들을 가져간다 
					if(((FiveWayBTreeNode)f.getchilderen().get(i))!=null) {
						c2.setchilderen(((FiveWayBTreeNode)f.getchilderen().get(i)), i-3);
						((FiveWayBTreeNode)f.getchilderen().get(i)).setparent(c2);
					}
					
				}
				
				c1.setparent(p);
				c2.setparent(p);
				
				p.setchilderen(c1,0);
				p.setchilderen(c2,1);
				
				p.setsize(root.getsize()); //tree 크기 넘기기
				
				root=null;
				root=p;
				
				
			}
		}
		else {
			//root가 아닐 경우 
			
			reseparation(f);
		}
		
		
		
	}
	
	//분리해야할 노드가 root가 아닐 경우 분리 재귀 함수 
	public void reseparation(FiveWayBTreeNode f) {
		
		if(f.getkeyListsize()!=5) {
			
			return;
		}
		if(f==root) {
			//root 까지 올라왔다면
			
			separation(f);
			return;
		}
		
		
		Integer e1,e2;
		FiveWayBTreeNode c1=new FiveWayBTreeNode();
		FiveWayBTreeNode c2=new FiveWayBTreeNode();
		
		int index1,index2;
		
		Integer vl1,vl2,vm,vr1,vr2;
		vm=(Integer)f.getkeyList().get(2);
		
		vl1=(Integer)f.getkeyList().get(0);
		vl2=(Integer)f.getkeyList().get(1);
		vr1=(Integer)f.getkeyList().get(3);
		vr2=(Integer)f.getkeyList().get(4);
		
		c1.addkeyList(vl1);
		c1.addkeyList(vl2);
		
		c2.addkeyList(vr1);
		c2.addkeyList(vr2);
		
		if(f.childrencheck()==true) {
			//자식 노드가 없을 경우
			f.getparent().addkeyList(vm);
			
			f.setparent(sortedchildrennode(f.getparent(),vm));
			
			e1=(Integer)f.getkeyList().get(0);
			e2=(Integer)f.getkeyList().get(3);
			
			//해당 값을 통해 children 에서 어디 위치로 들어갈지 index 받기
			index1=f.getparent().returnindex(e1);
			index2=f.getparent().returnindex(e2);
			
			
			
			f.getparent().setchilderen(c1, index1);
			f.getparent().setchilderen(c2, index2);
			
			c1.setparent(f.getparent());
			c2.setparent(f.getparent());
			
			f=null;
			
			reseparation(c1.getparent());
			
			
			
			
		}
		else {
			//자식 노드가 있을 경우
			
			
			for(int i=0;i<3;i++) {
				//f의 왼쪽 자식들을 가져간다 
				if(((FiveWayBTreeNode)f.getchilderen().get(i))!=null) {
					c1.setchilderen(((FiveWayBTreeNode)f.getchilderen().get(i)), i);
					((FiveWayBTreeNode)f.getchilderen().get(i)).setparent(c1);
				}
				
			}
			
			for(int i=3;i<6;i++) {
				//f의 오른쪽 자식들을 가져간다 
				if(((FiveWayBTreeNode)f.getchilderen().get(i))!=null) {
					c2.setchilderen(((FiveWayBTreeNode)f.getchilderen().get(i)), i-3);
					((FiveWayBTreeNode)f.getchilderen().get(i)).setparent(c2);
				}
				
			}
			
			f.getparent().addkeyList(vm);
			
			f.setparent(sortedchildrennode(f.getparent(),vm));
			
			e1=(Integer)f.getkeyList().get(0);
			e2=(Integer)f.getkeyList().get(3);
			
			//해당 값을 통해 children 에서 어디 위치로 들어갈지 index 받기 
			index1=f.getparent().returnindex(e1);
			index2=f.getparent().returnindex(e2);
			
			
			f.getparent().setchilderen(c1, index1);
			f.getparent().setchilderen(c2, index2);
			
			c1.setparent(f.getparent());
			c2.setparent(f.getparent());
			
			f=null;
			
			reseparation(c1.getparent());
		}
		
		
		
	}
	
	//새로 추가된 노드의 children 재정렬 함수  
	public FiveWayBTreeNode sortedchildrennode(FiveWayBTreeNode next,Integer newvalue) {
		
		int k=next.keyListindex(newvalue)+1;
		
		FiveWayBTreeNode ch;
		ch=(FiveWayBTreeNode)next.getchilderen().get(5);
		for(int i=5;i>k;i--) {
			
			next.setchilderen((FiveWayBTreeNode)next.getchilderen().get(i-1), i);
		}
		
		
		
		
		
		return next;
	}
	
	
	
	
	
	//해당 값이 트리 안에 없을 경우 추가 값이 들어갈 노드를 찾는 재귀함수 
	public FiveWayBTreeNode findnode(FiveWayBTreeNode f,Integer e) {
		
		
		
		
		if(f==root&&root.getkeyList().size()==0) {
			
			return root;
		}
		
		int index;
		
		
		if(f.childrencheck()==true) {
			//children이 모두 null일 경우 
			
			return f;
		}
		else {
			
			index=f.returnindex(e);
			
			
			if(((FiveWayBTreeNode)f.getchilderen().get(index))==null) {
				//해당값이 해당 노드에 없지만 다음 노드가 null일 경우에 
				
				return f;
			}
			return findnode(((FiveWayBTreeNode)f.getchilderen().get(index)),e);
		}
		
		
		
		
	}
	
	
	public void showbtree(FiveWayBTreeNode r) {
		//btree 출력해서 보여주는 함수 
		//BFS 
		
		if(r==null) {
			return;
		}
		for(int i=0;i<r.getchilderen().size();i++) {
			showbtree((FiveWayBTreeNode)r.getchilderen().get(i));
		}
		System.out.println(r.getkeyList());
		
	}
	
	
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		//해당 값이 있는지 없는지 확인 
		if(search(root,o,0)==1) {
			return true;
		}
		return false;
		
	}
	//탐색 재귀함수
	public int search(FiveWayBTreeNode r,Object o,int sum) {
		//DFS로 탐색하면서 해당 값 찾아본다
		
		if(r==null) {
			return sum;
		}
		for(int i=0;i<r.getchilderen().size();i++) {
			 sum=search((FiveWayBTreeNode)r.getchilderen().get(i),o,sum);
		}
		
		if(r.getkeyList().contains(o)==true) {	
			
			sum=1;
		}
		
		return sum;
		
	
		
	}
	
	//해당 값이 있는 노드 반환 
	public FiveWayBTreeNode searchnode(FiveWayBTreeNode r,Object o,FiveWayBTreeNode f) {
		//해당 값 없을 경우 처음 f 반환 
		if(r==null) {
			return f;
		}
		for(int i=0;i<r.getchilderen().size();i++) {
			 f=searchnode((FiveWayBTreeNode)r.getchilderen().get(i),o,f);
		}
		
		if(r.getkeyList().contains(o)==true) {	
			
			f=r;
		}
		
		return f;
		
		
			
	}
	
	//트리에서 최대값 찾는 함수 
	public Object findmax(FiveWayBTreeNode f) {
		int ksize=f.getkeyListsize();
		int i,index=0;
		if(f.childrencheck()==true) {
			//자식 노드가 없으면 
			
			return f.getkeyList().get(ksize-1);
		}
		else {
			for(i=ksize;i>0;i--) {
				
				if(f.getchilderen().get(i)!=null) {
					index=i;
					break;
					
				}
			}
			return findmax((FiveWayBTreeNode)f.getchilderen().get(index));
			
		}
		
		
		
	}
	
	//트리에서 최소값 찾는 함수 
	public Object findmin(FiveWayBTreeNode f) {
		int ksize=f.getkeyListsize();
		int i,index=0;
		
		if(f.childrencheck()==true) {
			//자식 노드가 없으면 
			
			return f.getkeyList().get(0);
		}
		else {
			for(i=0;i<ksize;i++) {
				
				if(f.getchilderen().get(i)!=null) {
					index=i;
					break;
					
				}
			}
			return findmin((FiveWayBTreeNode)f.getchilderen().get(index));
			
		}
		
		
		
	}

	@Override
	public Integer first() {
		// TODO Auto-generated method stub
		//*
		//첫번째 원소 얻기 최소값
		return (Integer)findmin(root);
	}

	@Override
	public Integer last() {
		// TODO Auto-generated method stub
		//*
		//마지막 원소 얻기 최대값 
		return (Integer)findmax(root);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		//*
		//tree size return
		
		
		return root.getsize();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		//BTree 비었으면 true, 아니면 false return
		if (root.getkeyList()==null) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	//오름차순 트리 출력 
	public void upshowtree(FiveWayBTreeNode p) {
		
		if(p.childrencheck()) {
			//자식이 없으면 
			for(int i=0;i<p.getkeyListsize();i++) {
				System.out.println(p.getkeyList().get(i)+" ");
			}
		}
		else {
			//자식이 있으면   
			for(int i=0;i<p.getkeyListsize();i++) {
				if(i==p.getkeyListsize()-1) {
					if(p.getchilderen().get(i)!=null) {
						upshowtree((FiveWayBTreeNode)p.getchilderen().get(i));
					}
					System.out.println(p.getkeyList().get(i));
					if(p.getchilderen().get(i+1)!=null) {
						upshowtree((FiveWayBTreeNode)p.getchilderen().get(i+1));
					}
				}
				else {
					if(p.getchilderen().get(i)!=null) {
						upshowtree((FiveWayBTreeNode)p.getchilderen().get(i));
					}
					System.out.println(p.getkeyList().get(i)+" ");
				}
				
				
			}
			
			
		}
		
		
	}
	
	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		//*
		//담겨있는 원소 하나하나를 확인 
		if(root==null) {
			return null;
		}
		return new newiterator(root);
	}
	
	@Override
	public Integer floor(Integer e) {
		// TODO Auto-generated method stub
		//해당 값보다 작은 값들 중 가장 큰 값 
		//해당 값이 비트리에 없을 수 있다 
		//존재하는 값 넣으면 그 값이 나옴 
		
		FiveWayBTreeNode f=null;
		if(contains(e)) {
			
			return e;
		}
		else {
			return refloor(root,e,-1);
		}
		
		
		
		
		
	}
	
	
	//재귀하면서 floor 값 찾는 함수 
	public Integer refloor(FiveWayBTreeNode p,Integer e,int beindex) {
		
		int jud=0; //keyList에서 해당 값보다 큰 값 찾으면 1,젤 작은 값도 e 보다 크면 2
		int index=-1;
		int beforeindex=-1;
		beforeindex=beindex;
		
		
		
		
		
		for(int i=p.getkeyListsize()-1;i>=0;i--) {
			if((Integer)p.getkeyList().get(i)<e) {
				//e보다 작은 수 찾는다 
				index=i;
				jud=1;
				break;
			}
			if(i==0) {
				//끝까지 갔는데도 더 작은 수가 없으면
				index=0;
			}
		}
		
		if(jud==1) {
			if(p.getchilderen().get(index+1)==null) {
				
				return (Integer)p.getkeyList().get(index);
				
				
			}
			else {
				return (Integer)refloor((FiveWayBTreeNode)p.getchilderen().get(index+1),e,index);
			}
		}
		else {
			//첫번째까지 가도 해당 값보다 클 때 
			if((Integer)findmin(p)>e) {
				if(p.getparent()==null) {
					return null;
				}
				else {
					return (Integer)p.getparent().getkeyList().get(beforeindex);
				}
			}
			else {
				return refloor((FiveWayBTreeNode)p.getchilderen().get(0),e,index);
			}
			
		}
		
			
		
		
	}

	@Override
	public Integer ceiling(Integer e) {
		// TODO Auto-generated method stub
		//해당 값보다 큰 값들 중 가장 작은 값 
		//존재하는 값 넣으면 그 값이 나옴 
		FiveWayBTreeNode f=null;
		if(contains(e)) {
			
			return e;
		}
		else {
			return receiling(root,e,-1);
		}
		
		
		
	}
	
	//해당 값이 비트리에 없을 시 재귀하면서 ceiling 값 찾는 함수 
	public Integer receiling(FiveWayBTreeNode p,Integer e,int beindex) {
		
		int jud=0; //keyList에서 해당 값보다 큰 값 찾으면 1
		int index=-1;
		int beforeindex=-1;
		beforeindex=beindex;
		
		for(int i=0;i<p.getkeyListsize();i++) {
			//keyList 돌면서 
			if((Integer)p.getkeyList().get(i)>e) {
				
				index=i;
				jud=1;
				break;
			}
			if(i==p.getkeyListsize()-1) {
				//끝까지 갔는데도 더 큰 수가 없으면
				index=p.getkeyListsize()-1;
			}
				
		}
		
		if(jud==1) {
			if(p.getchilderen().get(index)==null) {
				return (Integer)p.getkeyList().get(index);
			}
			else {
				
				return receiling((FiveWayBTreeNode)p.getchilderen().get(index),e,index);
				
			}
		}
		else {
			//끝까지 갔는데도 더 큰 수가 없으면 
			
			
			if((Integer)findmax(p)<e) {
				//안쪽의 트리에서 최대값이 e 보다 작다면
				if(p.getparent()==null) {
					return null;
				}
				else {
					return (Integer)p.getparent().getkeyList().get(beforeindex);
				}
				
			}
			else {
				//있으면 다시 맨 오른쪽 자식트리로 이동 
				return receiling((FiveWayBTreeNode)p.getchilderen().get(index+1),e,index);
			}
			
		}
		
		
			
			
	}
	
	
	
	@Override
	public SortedSet<Integer> headSet(Integer toElement) {
		// TODO Auto-generated method stub
		FiveWayBTree headtree = new FiveWayBTree();
		FiveWayBTreeNode node,head;
		int index=0;
		
		headtree.setroot(root);
		head=headtree.getroot();
		
		
		
		Integer e=floor(toElement);
		node=searchnode(head,e,head); //해당 값이 있는 노드 
		index=node.keyListindex(e);
		
		//key 삭제
		for(int i=0;i<node.getkeyListsize();i++) {
			if(i>index) {
				node.getkeyList().remove(i);
				
			}
			
		}
		//자식 삭제 
		for(int i=0;i<node.getkeyListsize();i++) {
			if(i>=index) {
				node.getchilderen().remove(i+1);
				
			}
			
		}
		for(int i=0;i<node.getkeyListsize();i++) {
			//삭제한만큼 null로 자식 채워줌 
			if(i>=index) {
				node.getchilderen().add(null);
				
			}
			
		}
			
		
		
		
		
		
		return headtree;
	}

	@Override
	public SortedSet<Integer> tailSet(Integer fromElement) {
		// TODO Auto-generated method stub
		//해당 값 포함 아래 서브셋
		
		
				
		return null;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////
	@Override
	public NavigableSet<Integer> headSet(Integer toElement, boolean inclusive) {
		// TODO Auto-generated method stub
		//해당 값 포함 위 서브셋 
		
		
		
		
		return null;
	}
	
	
	@Override
	public NavigableSet<Integer> tailSet(Integer fromElement, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Comparator<? super Integer> comparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		//set의 모든 요소를 새 배열로 복사 
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer lower(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Integer higher(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer pollFirst() {
		// TODO Auto-generated method stub
		//처음 원소 얻고 해당 원소 삭제  
		return null;
	}

	@Override
	public Integer pollLast() {
		// TODO Auto-generated method stub
		//마지막 원소 얻고 해당 원소 삭제
		return null;
	}

	

	@Override
	public NavigableSet<Integer> descendingSet() {
		// TODO Auto-generated method stub
		//역순으로 정렬된 새로운 set으로 정리 
		return null;
	}

	@Override
	public Iterator<Integer> descendingIterator() {
		// TODO Auto-generated method stub
		//역방향 이터레이터 반환 
		return null;
	}

	@Override
	public NavigableSet<Integer> subSet(Integer fromElement, boolean fromInclusive, Integer toElement,
			boolean toInclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public SortedSet<Integer> subSet(Integer fromElement, Integer toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	

}