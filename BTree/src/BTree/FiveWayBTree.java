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
	
	//������
	newiterator(FiveWayBTreeNode p){
		root=p;
		myq=new LinkedList<Integer>();
		q=showtreeup(root,myq);
		
		
	}
	
	
	
	public Queue showtreeup(FiveWayBTreeNode p,Queue q) {
		//�������� ������� ť�� ���� 
		if(p.childrencheck()) {
			//�ڽ��� ������ 
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
	
	
	//������
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
			//�ߺ� ���� ��� add ���� 
			
			return false;
		}
		else {
			//�ش� ���� Ʈ�� �ȿ� ���� ��� ����
			
			FiveWayBTreeNode f=findnode(root,e); //���� �� ���
			
			
			f.addkeyList(e);
			
			
			
			if(f.judkeyListsize()==true) {
				separation(f);
			}
			
			root.sizeplus();
				
			return true;
		}
		
		
		
		
		
		
	}
	
	//key�� 5�� �� �� �и� �Լ� 
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
				//�ڽ� ��尡 ���� ���   
				c1.setparent(p);
				c2.setparent(p);
				
				p.setchilderen(c1,0);
				p.setchilderen(c2,1);
				
				
				
				
				p.setsize(root.getsize()); //tree ũ�� �ѱ��
				
				root=null;
				root=p;
				
				
				
				
			}
			else {
				//�ڽ� ��尡 ���� ���
				for(int i=0;i<3;i++) {
					//root�� ���� �ڽĵ��� ��������
					if(((FiveWayBTreeNode)f.getchilderen().get(i))!=null) {
						c1.setchilderen(((FiveWayBTreeNode)f.getchilderen().get(i)), i);
						((FiveWayBTreeNode)f.getchilderen().get(i)).setparent(c1);
					}
					
				}
				
				for(int i=3;i<6;i++) {
					//root�� ������ �ڽĵ��� �������� 
					if(((FiveWayBTreeNode)f.getchilderen().get(i))!=null) {
						c2.setchilderen(((FiveWayBTreeNode)f.getchilderen().get(i)), i-3);
						((FiveWayBTreeNode)f.getchilderen().get(i)).setparent(c2);
					}
					
				}
				
				c1.setparent(p);
				c2.setparent(p);
				
				p.setchilderen(c1,0);
				p.setchilderen(c2,1);
				
				p.setsize(root.getsize()); //tree ũ�� �ѱ��
				
				root=null;
				root=p;
				
				
			}
		}
		else {
			//root�� �ƴ� ��� 
			
			reseparation(f);
		}
		
		
		
	}
	
	//�и��ؾ��� ��尡 root�� �ƴ� ��� �и� ��� �Լ� 
	public void reseparation(FiveWayBTreeNode f) {
		
		if(f.getkeyListsize()!=5) {
			
			return;
		}
		if(f==root) {
			//root ���� �ö�Դٸ�
			
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
			//�ڽ� ��尡 ���� ���
			f.getparent().addkeyList(vm);
			
			f.setparent(sortedchildrennode(f.getparent(),vm));
			
			e1=(Integer)f.getkeyList().get(0);
			e2=(Integer)f.getkeyList().get(3);
			
			//�ش� ���� ���� children ���� ��� ��ġ�� ���� index �ޱ�
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
			//�ڽ� ��尡 ���� ���
			
			
			for(int i=0;i<3;i++) {
				//f�� ���� �ڽĵ��� �������� 
				if(((FiveWayBTreeNode)f.getchilderen().get(i))!=null) {
					c1.setchilderen(((FiveWayBTreeNode)f.getchilderen().get(i)), i);
					((FiveWayBTreeNode)f.getchilderen().get(i)).setparent(c1);
				}
				
			}
			
			for(int i=3;i<6;i++) {
				//f�� ������ �ڽĵ��� �������� 
				if(((FiveWayBTreeNode)f.getchilderen().get(i))!=null) {
					c2.setchilderen(((FiveWayBTreeNode)f.getchilderen().get(i)), i-3);
					((FiveWayBTreeNode)f.getchilderen().get(i)).setparent(c2);
				}
				
			}
			
			f.getparent().addkeyList(vm);
			
			f.setparent(sortedchildrennode(f.getparent(),vm));
			
			e1=(Integer)f.getkeyList().get(0);
			e2=(Integer)f.getkeyList().get(3);
			
			//�ش� ���� ���� children ���� ��� ��ġ�� ���� index �ޱ� 
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
	
	//���� �߰��� ����� children ������ �Լ�  
	public FiveWayBTreeNode sortedchildrennode(FiveWayBTreeNode next,Integer newvalue) {
		
		int k=next.keyListindex(newvalue)+1;
		
		FiveWayBTreeNode ch;
		ch=(FiveWayBTreeNode)next.getchilderen().get(5);
		for(int i=5;i>k;i--) {
			
			next.setchilderen((FiveWayBTreeNode)next.getchilderen().get(i-1), i);
		}
		
		
		
		
		
		return next;
	}
	
	
	
	
	
	//�ش� ���� Ʈ�� �ȿ� ���� ��� �߰� ���� �� ��带 ã�� ����Լ� 
	public FiveWayBTreeNode findnode(FiveWayBTreeNode f,Integer e) {
		
		
		
		
		if(f==root&&root.getkeyList().size()==0) {
			
			return root;
		}
		
		int index;
		
		
		if(f.childrencheck()==true) {
			//children�� ��� null�� ��� 
			
			return f;
		}
		else {
			
			index=f.returnindex(e);
			
			
			if(((FiveWayBTreeNode)f.getchilderen().get(index))==null) {
				//�ش簪�� �ش� ��忡 ������ ���� ��尡 null�� ��쿡 
				
				return f;
			}
			return findnode(((FiveWayBTreeNode)f.getchilderen().get(index)),e);
		}
		
		
		
		
	}
	
	
	public void showbtree(FiveWayBTreeNode r) {
		//btree ����ؼ� �����ִ� �Լ� 
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
		//�ش� ���� �ִ��� ������ Ȯ�� 
		if(search(root,o,0)==1) {
			return true;
		}
		return false;
		
	}
	//Ž�� ����Լ�
	public int search(FiveWayBTreeNode r,Object o,int sum) {
		//DFS�� Ž���ϸ鼭 �ش� �� ã�ƺ���
		
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
	
	//�ش� ���� �ִ� ��� ��ȯ 
	public FiveWayBTreeNode searchnode(FiveWayBTreeNode r,Object o,FiveWayBTreeNode f) {
		//�ش� �� ���� ��� ó�� f ��ȯ 
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
	
	//Ʈ������ �ִ밪 ã�� �Լ� 
	public Object findmax(FiveWayBTreeNode f) {
		int ksize=f.getkeyListsize();
		int i,index=0;
		if(f.childrencheck()==true) {
			//�ڽ� ��尡 ������ 
			
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
	
	//Ʈ������ �ּҰ� ã�� �Լ� 
	public Object findmin(FiveWayBTreeNode f) {
		int ksize=f.getkeyListsize();
		int i,index=0;
		
		if(f.childrencheck()==true) {
			//�ڽ� ��尡 ������ 
			
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
		//ù��° ���� ��� �ּҰ�
		return (Integer)findmin(root);
	}

	@Override
	public Integer last() {
		// TODO Auto-generated method stub
		//*
		//������ ���� ��� �ִ밪 
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
		//BTree ������� true, �ƴϸ� false return
		if (root.getkeyList()==null) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	//�������� Ʈ�� ��� 
	public void upshowtree(FiveWayBTreeNode p) {
		
		if(p.childrencheck()) {
			//�ڽ��� ������ 
			for(int i=0;i<p.getkeyListsize();i++) {
				System.out.println(p.getkeyList().get(i)+" ");
			}
		}
		else {
			//�ڽ��� ������   
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
		//����ִ� ���� �ϳ��ϳ��� Ȯ�� 
		if(root==null) {
			return null;
		}
		return new newiterator(root);
	}
	
	@Override
	public Integer floor(Integer e) {
		// TODO Auto-generated method stub
		//�ش� ������ ���� ���� �� ���� ū �� 
		//�ش� ���� ��Ʈ���� ���� �� �ִ� 
		//�����ϴ� �� ������ �� ���� ���� 
		
		FiveWayBTreeNode f=null;
		if(contains(e)) {
			
			return e;
		}
		else {
			return refloor(root,e,-1);
		}
		
		
		
		
		
	}
	
	
	//����ϸ鼭 floor �� ã�� �Լ� 
	public Integer refloor(FiveWayBTreeNode p,Integer e,int beindex) {
		
		int jud=0; //keyList���� �ش� ������ ū �� ã���� 1,�� ���� ���� e ���� ũ�� 2
		int index=-1;
		int beforeindex=-1;
		beforeindex=beindex;
		
		
		
		
		
		for(int i=p.getkeyListsize()-1;i>=0;i--) {
			if((Integer)p.getkeyList().get(i)<e) {
				//e���� ���� �� ã�´� 
				index=i;
				jud=1;
				break;
			}
			if(i==0) {
				//������ ���µ��� �� ���� ���� ������
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
			//ù��°���� ���� �ش� ������ Ŭ �� 
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
		//�ش� ������ ū ���� �� ���� ���� �� 
		//�����ϴ� �� ������ �� ���� ���� 
		FiveWayBTreeNode f=null;
		if(contains(e)) {
			
			return e;
		}
		else {
			return receiling(root,e,-1);
		}
		
		
		
	}
	
	//�ش� ���� ��Ʈ���� ���� �� ����ϸ鼭 ceiling �� ã�� �Լ� 
	public Integer receiling(FiveWayBTreeNode p,Integer e,int beindex) {
		
		int jud=0; //keyList���� �ش� ������ ū �� ã���� 1
		int index=-1;
		int beforeindex=-1;
		beforeindex=beindex;
		
		for(int i=0;i<p.getkeyListsize();i++) {
			//keyList ���鼭 
			if((Integer)p.getkeyList().get(i)>e) {
				
				index=i;
				jud=1;
				break;
			}
			if(i==p.getkeyListsize()-1) {
				//������ ���µ��� �� ū ���� ������
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
			//������ ���µ��� �� ū ���� ������ 
			
			
			if((Integer)findmax(p)<e) {
				//������ Ʈ������ �ִ밪�� e ���� �۴ٸ�
				if(p.getparent()==null) {
					return null;
				}
				else {
					return (Integer)p.getparent().getkeyList().get(beforeindex);
				}
				
			}
			else {
				//������ �ٽ� �� ������ �ڽ�Ʈ���� �̵� 
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
		node=searchnode(head,e,head); //�ش� ���� �ִ� ��� 
		index=node.keyListindex(e);
		
		//key ����
		for(int i=0;i<node.getkeyListsize();i++) {
			if(i>index) {
				node.getkeyList().remove(i);
				
			}
			
		}
		//�ڽ� ���� 
		for(int i=0;i<node.getkeyListsize();i++) {
			if(i>=index) {
				node.getchilderen().remove(i+1);
				
			}
			
		}
		for(int i=0;i<node.getkeyListsize();i++) {
			//�����Ѹ�ŭ null�� �ڽ� ä���� 
			if(i>=index) {
				node.getchilderen().add(null);
				
			}
			
		}
			
		
		
		
		
		
		return headtree;
	}

	@Override
	public SortedSet<Integer> tailSet(Integer fromElement) {
		// TODO Auto-generated method stub
		//�ش� �� ���� �Ʒ� �����
		
		
				
		return null;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////
	@Override
	public NavigableSet<Integer> headSet(Integer toElement, boolean inclusive) {
		// TODO Auto-generated method stub
		//�ش� �� ���� �� ����� 
		
		
		
		
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
		//set�� ��� ��Ҹ� �� �迭�� ���� 
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
		//ó�� ���� ��� �ش� ���� ����  
		return null;
	}

	@Override
	public Integer pollLast() {
		// TODO Auto-generated method stub
		//������ ���� ��� �ش� ���� ����
		return null;
	}

	

	@Override
	public NavigableSet<Integer> descendingSet() {
		// TODO Auto-generated method stub
		//�������� ���ĵ� ���ο� set���� ���� 
		return null;
	}

	@Override
	public Iterator<Integer> descendingIterator() {
		// TODO Auto-generated method stub
		//������ ���ͷ����� ��ȯ 
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