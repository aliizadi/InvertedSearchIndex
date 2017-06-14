package dataStructures;

import java.util.ArrayList;

public class LinkedList {
	
	private Node head;
	
	private int size; 
	
	public LinkedList() {
		// TODO Auto-generated constructor stub
		
		this.head = new Node(null);
		this.size = 0;
		
	}
	
	public void add(String data){
		
	
		Node temp = new Node(data);
		Node current = head;
		
		int index = -1 ; 
		
		while(current.getLink() != null){
			if(current.getLink().getData().compareToIgnoreCase(data) == 0 ){
				index = 0; 
				break;
			}
			current = current.getLink();
		}
		
		if(index == -1 ){
			
			current.setLink(temp);
			increaseSize(1);
			
			
		}
		
		
	}
	
	
	public boolean remove(String name){
		

		
		Node current = head; 
		
		for(int i =1 ; i < getSize() ; i++){
			
			if(current.getLink() == null)
				return false ;
			
			
			current = current.getLink();
			if(current.getData().compareTo(name) == 0 )
				break;
			
		}
		
		current.setLink(current.getLink().getLink());
		
		increaseSize(-1);
		
		return true;

	}
	
	public ArrayList<String>  traverse(){
		
		Node current = head;
		
		ArrayList<String> string  = new ArrayList<>(); ; 
		

		while(current.getLink() != null){
			
			
			
			string.add(current.getLink().getData());
			current = current.getLink();
			
		}
		
		
		return string;


		
		
	}
	
	
	
	/*public boolean contain(String data){

		Node current = head;

		int index = -1 ; 
		while(current.getLink() != null){
			
			if(current.getLink().getData() .equals(data)){
				index = 0 ;
				break ;
			}
			
			
			
		}

		if(index == -1)
			
			return false ; 
		
		else 
			
			return true;
		
		
		
	}*/
	
	
	public void setHead(Node head) {
		this.head = head;
	}
	public void increaseSize(int increament) {
		this.size += increament;
	}
	public Node getHead() {
		return head;
	}
	public int getSize() {
		return size;
	}
	
	
	
	
	
	private class Node{
		
		private Node link;
		private String data;
		
		public Node(String data){
			
			this.link = null; 
			this.data = data; 
			
			
		}
		
		
		public Node(String data , Node link){    // another constructor if we want a node to point to.
			
			this.link = link ; 
			this.data = data; 
			
		}
		
		
		public void setData(String data) {
			this.data = data;
		}
		
		public void setLink(Node link) {
			this.link = link;
		}
		
		public String getData() {
			return data;
		}
		
		public Node getLink() {
			return link;
		}
		
		
	}

}
