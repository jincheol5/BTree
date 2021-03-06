package BTree;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Random;
import java.util.TreeSet;

public class App {

	public static void main(String[] args) {
		
		
		
		//0~9999수를 100개 랜덤하게 NavigableSet에다가 넣었다
		ArrayList<Integer> list = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i <100; i++) {
			
			list.add(r.nextInt(10000));
			//list.add(i);
			
		}
		
		//yourBTree와 비교하기 위해 만든것 
		NavigableSet<Integer> treeSet = new TreeSet<Integer>();
		for (Integer val : list) {
			
			treeSet.add(val);
			
		}
		
		//NavigableSet 인터페이스를  5way BTree 클래스로 구현하는게 이번 과제 
		//22번째 라인 : 이미 만들어진 클래스로 객체 생성  
		//NavigableSet<Integer> yourBTree = new TreeSet<Integer>();
		
		//24번째 Line을 주석처리하고 22번째 Line의 주석을 제거하면 옳은 결과가 나옴 
		//24번째 라인 : 내가 직접 구현 후 이 라인으로 객체 생성
		
		
		NavigableSet<Integer> yourBTree = new FiveWayBTree();
		for (Integer val : list) {
			yourBTree.add(val);
			
		}
		
		
		
		
		
		
		System.out.println("size test: " + (treeSet.size() == yourBTree.size()));
		System.out.println("first test: " + treeSet.first().equals(yourBTree.first()));
		System.out.println("last test: " + treeSet.last().equals(yourBTree.last()));
		
		
		
		
		
	
		
		
		Iterator<Integer> treeIterator = treeSet.iterator();
		Iterator<Integer> yourBTreeIterator = yourBTree.iterator();
		boolean isPass = true;
		while (treeIterator.hasNext() && yourBTreeIterator.hasNext()) {
			if (!treeIterator.next().equals(yourBTreeIterator.next())) {
				isPass = false;
				break;
			}
		}
		System.out.println("iterator test: " + isPass);
		
		
		
		int pivot = r.nextInt(10000);
		
		try {
			
			System.out.println("floor test: " + treeSet.floor(pivot).equals(yourBTree.floor(pivot)));
			
		} catch (NullPointerException e) {
			
			if (treeSet.floor(pivot) == null && yourBTree.floor(pivot) == null)
				System.out.println("floor test: true");
			
		}
		
		
		
		
		
		
		
		
		try {
			
			System.out.println("ceiling test: " + treeSet.ceiling(pivot).equals(yourBTree.ceiling(pivot)));
			
		} catch (NullPointerException e) {
			
			if (treeSet.ceiling(pivot) == null && yourBTree.ceiling(pivot) == null)
				System.out.println("ceiling test: true");
			
		}
		
	
		
		
		
		
		
		
		
		
		Iterator<Integer> treeHeadIterator = treeSet.headSet(pivot).iterator();
		Iterator<Integer> yourBTtreeHeadIterator = yourBTree.headSet(pivot).iterator();
		isPass = true;
		while (treeHeadIterator.hasNext() && yourBTtreeHeadIterator.hasNext()) {
			
			if (!treeHeadIterator.next().equals(yourBTtreeHeadIterator.next())) {
				
				isPass = false;
				break;
				
			}
			
		}
		System.out.println("headSet test: " + isPass);
		
		
		
		
		Iterator<Integer> treeTailIterator = treeSet.tailSet(pivot).iterator();
		Iterator<Integer> yourBTtreeTailIterator = yourBTree.tailSet(pivot).iterator();
		
		isPass = true;
		
		
		
		
		
		
		
		while (treeTailIterator.hasNext() && yourBTtreeTailIterator.hasNext()) {
			
			
			
			if (!treeTailIterator.next().equals(yourBTtreeTailIterator.next())) {
				
				isPass = false;
				break;
				
			}
			
			
		}
		
		System.out.println("tailSet test: " + isPass);
		
		
		
		
		for (int i = 0; i < list.size() / 2; i++) {
			treeSet.remove(list.get(i));
			yourBTree.remove(list.get(i));
		}
		treeIterator = treeSet.iterator();
		yourBTreeIterator = yourBTree.iterator();
		isPass = true;
		while (treeIterator.hasNext() && yourBTreeIterator.hasNext()) {
			
			if (!treeIterator.next().equals(yourBTreeIterator.next())) {
				
				isPass = false;
				break;
				
			}
			
		}
		System.out.println("remove test: " + isPass);
		
	}
}