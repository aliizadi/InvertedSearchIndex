package dataStructures;

public class Stack {
	
	private Object [] stackArray;
	private int top; 
	private int maxSize;

	public Stack(int maxSize) {
		// TODO Auto-generated constructor stub'
		this.maxSize = maxSize;
		stackArray = new Object[maxSize];
		this.top = -1; 
		
	}
	
	public void push(Object object){
		stackArray[++top] = object;
	}
	
	public Object pop(){
		return stackArray[top--];
	}
	
	public boolean isEmpty(){
		return ( top == -1 ) ; 
	}
	
	public boolean isFull(){
		return ( top == maxSize-1 );
	}
	
	
	
	

}
