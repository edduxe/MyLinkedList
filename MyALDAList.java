/* Authors:
 * Ali Nazar // alna0203  // alna0203@student.su.se // 
 * Mehdi Rahimi // mera9128 // mera9128@student.su.se // 
 * Michel Nickbon // mile3894 // mile3894@student.su.se // 
 * 
 * */

package alda.linear;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyALDAList<T> implements ALDAList<T> {

	private static class Node<T>{
		T data;
		Node<T> next;

		public Node(T element){
			this.data=element;

		}
		public void setNext(Node<T> next){
			this.next = next;
		}
		public void setData(T element){
			this.data = element;
		}
		public Node<T> getNext(){
			return next;
		}
		public String toString(){
			return  data.toString();
		}
	}

	private Node<T> first;
	private Node<T> last;
	private int indexcounter=0;
	private MyALDAList<T> alda = this;


	@Override
	public Iterator<T> iterator() {
		return new ListIterator<T>(alda);
		// TODO Auto-generated method stub
	}

	@Override
	public void add(T element) {
		if(first == null){
			first = new Node<T>(element);
			last = first;
			indexcounter++;
		}else{
			indexcounter++;
			last.setNext(new Node<T>(element));
			last = last.next;

		}

	}

	@Override
	public void add(int index, T element) {
		// TODO Auto-generated method stub
		if(index < 0 || index > size() ){
			throw new IndexOutOfBoundsException();

		}else{

			Node<T> newNode = new Node<T>(element);
			Node<T> current = first;
			Node<T> previous = null;

			if(index==0){
				newNode.next = first;
				first = newNode;
				indexcounter++;
			}else if(index == size()){
				add(element);
			}else{
				for(int i=0; i<index; i++){

					previous = current;
					
					current = current.getNext();
				}
				previous.next = newNode;
				newNode.next = current;
				indexcounter++;
			}
		}
	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub

		if(index < 0 || index >= size() )
			throw new IndexOutOfBoundsException();

		int counter=0;
		Node<T> previous=null;
		T removeddata= null;
		for(Node<T> temp=first; temp!=null; temp=temp.next){

			if(counter == index){
				if(temp == first || temp.equals(first) && temp.getNext()!=null){
					removeddata = first.data;
					if(temp.next != null){
					first.setData(temp.getNext().data);
					first.setNext(temp.getNext().next); // this work
					indexcounter--;
					return removeddata;
					}else{
					}
				}else if(temp == last || temp.equals(last)){
					removeddata = previous.next.data;
					previous.next=null;
					temp=previous;
					last = previous;
					indexcounter--;
					return removeddata;
				}else{
					previous.next = null;
					Node<T> pointTo= temp.next;
					removeddata = temp.data;
					temp = previous;
					temp.next =pointTo;
					indexcounter--;
					return removeddata;
				}
			}else{
				counter++;
			}
			previous = temp;

		}
		return null;
	}

	@Override
	public boolean remove(T element) {
		// TODO Auto-generated method stub
		if(element!=null){
			Node<T> previous=null;
			for(Node<T> temp=first; temp!=null; temp=temp.next){
				if((element == first.data) && (element == temp.data)){
					if(temp.next != null){
					first.data = temp.getNext().data;
					first.next = temp.getNext().next;
					indexcounter--;
					return true;
					}else{
						
					}
				}else if((element == last) && (element == temp.data)){
					previous.next=null;
					temp=previous;
					last = previous;
					indexcounter--;
					return true;
				}else if(element == temp.data || element.equals(temp.data)){
					previous.next = null;
					Node<T> pointTo= temp.next;
					temp = previous;
					temp.next =pointTo;
					indexcounter--;
					return true;
				}
				previous = temp;
			}
		}
		return false;
	}


	@Override
	public T get(int index) {
		if(index < 0 || index >= size() || size()==0&&index==0)
			throw new IndexOutOfBoundsException();

		int counter=0;
		// TODO Auto-generated method stub
		for(Node<T> temp=first; temp!=null; temp=temp.next){
			if(counter == index){
				return temp.data;
			}else{
				counter++;
			}
		}
		return null;
	}

	@Override
	public boolean contains(T element) {
		// TODO Auto-generated method stub
		for(Node<T> temp=first; temp!=null; temp=temp.next){
			if(temp.data==element || temp.data.equals(element)){
				return true;
			}
		}
		return false;
	}

	@Override
	public int indexOf(T element) {
		// TODO Auto-generated method stub
		int counter=0;
		for(Node<T> temp=first; temp!=null; temp=temp.next){
			if(element == temp.data){
				return counter;
			}else{
				counter++;
			}
		}
		return -1;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		Node<T> current = first;
		while(current != first){
			Node<T> next = current.next;
			current.next = null;
			current.data = null;
			current = next;
		}
		first = null;
		indexcounter = 0;
	}

	@Override
	public int size() {
		return indexcounter;
	}

	public String toString(){
		Node<T> current = first;
		String str ="";
		if(current==null)
			return "[]";
		while (current != null) {
			if(current.next!=null)
				str += current.toString() + ", ";
			if(current.next==null)
				str += current.toString();

			current = current.next;
		}
		return "[" + str +"]";
	}

	@SuppressWarnings("unchecked")
	private class ListIterator<T> implements Iterator<T>{

		Node<T> current = (Node<T>) first;
		T resultat;
		private MyALDAList<T> list;

		public ListIterator(MyALDAList<T> myALDAList) {
			// TODO Auto-generated constructor stub
			list = myALDAList;
		}

		@Override
		public boolean hasNext() {
			if(current==null){
				return false;
			}
			Node<T> temp = current;
			//current = current.next;
			return (temp!=null);
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			if(current==null){
				throw new NoSuchElementException();
			}
			resultat = (T) current.data;
			current = current.next;
			return resultat;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

			if(!list.remove(resultat))
				throw new IllegalStateException();
			else
				list.remove(resultat);

		}
		public String toString(){
			return current.toString();
		}
	}
}
