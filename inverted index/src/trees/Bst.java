package trees;
import java.io.File;
import java.util.ArrayList;

import dataStructures.LinkedList;
import main.StopWords;
import dataStructures.Stack;

public class Bst implements Trees{
	
	private  Node root;
	private File file;
	private StopWords stopWords;
	private int number_of_files;
	private Stack stack; 


	

	public Bst( StopWords stopWords) {
		// TODO Auto-generated constructor stub
		this.root = null;
		this.stopWords = stopWords;
		this.stack = new Stack(1000);
		
	}
	
	public File getFile() {
		return file;
	}
	
	public int getNumber_of_files() {

		return number_of_files;
	}

	
	public void increaseNumber_of_files(int increament) {
		this.number_of_files += increament;
	}
	
	
	public Integer depth(Node p ){
		
		if( p == null ) return 0 ; 
		
		return Math.max(depth(p.getLeft()), depth(p.getRight())) + 1 ;
		
	}
	
		
		

	
	/*************************************************************************/
	
	public void add(String string , File file ){ // workhorse function
		
		if(!stopWords.containStopWords(string)){
			
			root = insert(string, file, this.root);

		}
		
		
	}
	
	private int height(Node p ){
		
		if( p == null )
			
			return 0 ; 
		
		return p.getHeight();
	}

	
	private Node insert(String string , File file , Node node ) {
		// TODO Auto-generated method stub
		
		
		if(node == null ){

			Node newNode = new Node(string);
			newNode.addLinkList(file.getName());
			//return (new Node(string));   // now we should add the new node;
			return newNode ; 
		}
		
		if(string.compareToIgnoreCase(node.getData()) < 0 )
			
			node.setLeft(insert(string, file, node.getLeft()));
		
		else if (string.compareToIgnoreCase(node.getData()) > 0 )
			
			node.setRight(insert(string, file, node.getRight()));
		
		else if(string.compareToIgnoreCase(node.getData()) == 0 ){ //duplicate keys not allowed 
			
			node.addLinkList(file.getName());
			return node ;
			
		}
		
		
		// update height 
		
		node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1 );
		
		//get the balancedFactor to check which node should be rotated 
		
		int balancedFactor = getBalancedFactor(node);
		
		
		// LL rotation
		
		if(balancedFactor > 1 && string.compareToIgnoreCase(node.getLeft().getData()) < 0 )
			
			return rotateRight(node);
		
		// LR rotation
		
		if(balancedFactor > 1 && string.compareToIgnoreCase(node.getLeft().getData()) > 0  ){
			
			node.setLeft(rotateLeft(node.getLeft()));
			
			return rotateRight(node);
			
		}
		
		// RR rotation
		
		if(balancedFactor < -1 && string.compareToIgnoreCase(node.getRight().getData()) > 0 )
			
			return rotateLeft(node);
		
		//RL rotation 
		
		if(balancedFactor < -1 && string.compareToIgnoreCase(node.getRight().getData()) < 0 ){
			
			node.setRight(rotateRight(node.getRight()));
			
			return rotateLeft(node);
			
		}
		
		return node ; 
					
	}
	

	
	private Node rotateLeft(Node p ){
		
		Node q = p.getRight();
		Node m = q.getLeft();
		
		//rotate left
		p.setRight(m);
		q.setLeft(p);
		
		//update heights 
		p.setHeight(Math.max(height(p.getLeft()), height(p.getRight())) + 1 );
		
		q.setHeight(Math.max(height(q.getLeft()), height(q.getRight())) + 1 );
		
		return q ; 
		
	}
	
	private Node rotateRight(Node p ){
		
		Node q = p.getLeft(); 
		Node m = q.getRight();
		
		//rotate right 
		p.setLeft(m);
		q.setRight(p);
		
		//update heights 
		p.setHeight(Math.max(height(p.getLeft()), height(p.getRight())) + 1 );

		q.setHeight(Math.max(height(q.getLeft()), height(q.getRight())) + 1 );
		
		return q ; 
		
	}
	
	private int getBalancedFactor(Node p ){
		
		if(p == null )
			return 0 ; 
		
		return height(p.getLeft()) - height(p.getRight());
	}
	/*****************************************************************/
	
	public Integer getNumberOfWords(Node p ){
		if(p == null ) return 0;
			
			return getNumberOfWords(p.getLeft())+getNumberOfWords(p.getRight())+1; 
		
	}
	
	private ArrayList<String> list_of_all_of_words = new ArrayList<>();
	
	public ArrayList<String> getList_of_all_of_words() {
		return list_of_all_of_words;
	}
	
	public void setList_of_all_of_words(ArrayList<String> list_of_all_of_words) {
		this.list_of_all_of_words = list_of_all_of_words;
	}
	
	
	private ArrayList<String> list_of_all_docs = new ArrayList<>();
	
	public ArrayList<String> getList_of_all_docs() {
		return list_of_all_docs;
	}
	
	public void setList_of_all_docs(ArrayList<String> list_of_all_docs) {
		this.list_of_all_docs = list_of_all_docs;
	}
	
	
	public void listedDocs(Node p ){
		
		if(p == null )
			
			return;
		
		
		ArrayList<String> temp = p.getLinkList().traverse();
		
		for(int i =0 ; i < temp.size() ; i++){
			
			if(!list_of_all_docs.contains(temp.get(i)))
				
				list_of_all_docs.add(temp.get(i));
			
		}
		
		
		
		
		if(p.getLeft() != null )
			listedDocs(p.getLeft());
		
		if(p.getRight() != null)
			listedDocs(p.getRight());
		
	}
	
	public void preorder(Node p){
		
		if(p == null ) 
			
			return;
		
		
		ArrayList<String> temp =p.getLinkList().traverse() ;
		
		String n =  "" ; 
		
		for( int i =0 ; i< temp.size() ; i++){
			
			n +=temp.get(i); 
			
		}
		list_of_all_of_words.add(p.getData() + " -> " + n);
		
		
		if(p.getLeft() != null )
			preorder(p.getLeft());
		
		
		if(p.getRight() != null)
			preorder(p.getRight());



	}

	
	/*********************************************************************/
	
	@Override
	public void delete(String word , File file ){ // workhorse
		
		if(!stopWords.containStopWords(word)){
			
		 findWordTodelete(word, file , getRoot() , getRoot()  );
			
			//if(node != null )
				
				//this.root = node; 
			
		}

		
	}
	
	private boolean  isLeftchild =false ;
	
	public Node findWordTodelete(String word , File file , Node current , Node parent  ){
	
		
		if( current == null )
			
			return null ; 
		
		if(word.compareToIgnoreCase(current.getData()) < 0 ){
			
			isLeftchild = true ; 
			current.setLeft(findWordTodelete(word, file, current.getLeft() ,current));

		}
		
		else if (word.compareToIgnoreCase(current.getData()) > 0 ){
		
			isLeftchild = false ; 
			current.setRight(findWordTodelete(word, file , current.getRight() , current));
			
		}
		
		else if(word.compareToIgnoreCase(current.getData()) == 0 ){ 			// Now we are sure that we have found the word 

			
			return deleteFoundWord( file, current ,parent , isLeftchild );
			
			 
			
		}
		
		// update height 

		current.setHeight(Math.max(height(current.getLeft()), height(current.getRight())) + 1 );

		//get the balancedFactor to check which node should be rotated 

		int balancedFactor = getBalancedFactor(current);


		// LL rotation

		if(balancedFactor > 1 && word.compareToIgnoreCase(current.getLeft().getData()) < 0 )

			return rotateRight(current);

		// LR rotation

		if(balancedFactor > 1 && word.compareToIgnoreCase(current.getLeft().getData()) > 0  ){

			current.setLeft(rotateLeft(current.getLeft()));

			return rotateRight(current);

		}

		// RR rotation

		if(balancedFactor < -1 && word.compareToIgnoreCase(current.getRight().getData()) > 0 )

			return rotateLeft(current);

		//RL rotation 

		if(balancedFactor < -1 && word.compareToIgnoreCase(current.getRight().getData()) < 0 ){

			current.setRight(rotateRight(current.getRight()));

			return rotateLeft(current);

		}

		return current ; 



		
		
		
		
	}

	public Node deleteFoundWord( File file, Node current , Node parent  , boolean isLeftChild ) {
		// TODO Auto-generated method stub


			if(current.getLinkList().getSize() >1 ){

				current.getLinkList().remove(file.getName());
				return current; 
				
			}

			else{


				// Case 1 : if the deleted node has no children ::

				if(current.getLeft() == null && current.getRight() == null){


					if(current == root){

						root = null;
						return null;
						
					}

					if(isLeftChild){

						parent.setLeft(null);
						parent.setHeight(height(parent.getRight())+1);
						
						return null; 
						
					}

					else{

						parent.setHeight(height(parent.getLeft())+1);
						parent.setRight(null);
						
						return null; 
						
					}

				}	


				// Case 2 : if the deleted node has one children ::

				else if(current.getLeft() == null){

					if(current == root ){

						
						root = current.getRight();
						
						return root ; 
						

					}

					else if(isLeftChild){

						parent.setLeft(current.getRight());
						parent.setHeight(Math.max(height(parent.getLeft()), height(parent.getRight())) + 1 );
						return current.getRight();

					}

					else{

						parent.setRight(current.getRight());
						parent.setHeight(Math.max(height(parent.getLeft()), height(parent.getRight())) + 1 );
						return current.getRight();
						
					}

				}


				else if(current.getRight() == null){

					if(current == root){

						root = current.getLeft();
						return root; 

					}

					else if (isLeftChild){

						parent.setLeft(current.getLeft());
						parent.setHeight(Math.max(height(parent.getLeft()), height(parent.getRight())) + 1 );
						return current.getLeft();

					}

					else{

						parent.setRight(current.getLeft());
						parent.setHeight(Math.max(height(parent.getLeft()), height(parent.getRight())) + 1 );
						return current.getLeft();

					}
				}


				// Case 3 : if the deleted node has two children ::

				else if (current.getLeft() != null && current.getRight() != null){




					Node min = min_of_right_sub_tree(current);   // min_of_right_sub_tree

					if( current == root ){

						root = min;
						
						root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1 );
						
						return min ; 
						
					}

					else if (isLeftChild){

						parent.setLeft(min);
						parent.setHeight(Math.max(height(parent.getLeft()), height(parent.getRight())) + 1 );

						return min ; 

					}

					else{

						parent.setRight(min);
						parent.setHeight(Math.max(height(parent.getLeft()), height(parent.getRight())) + 1 );

						return min ;
					}	

				}

				return null; 

			}

		

	}
	
	
	
	private Node min_of_right_sub_tree(Node deletedNode){
		
		Node min = null;
		Node parent = null; 
		Node current = deletedNode.getRight();
		
		
		while (current != null){
			
			parent = min ;
			min = 	current;
			current = current.getLeft();
			
			
		}
		
		
		if(min != deletedNode.getRight()  &&  min.getRight() != null ){
			
			parent.setLeft(min.getRight());
			parent.setHeight(height(parent.getRight() ) + 1 );
			min.setRight(deletedNode.getRight());
			min.setLeft(deletedNode.getLeft());
			min.setHeight(Math.max(height(min.getLeft()), height(min.getRight())) + 1 );
			
		}
		
		else if (min != deletedNode.getRight() && min.getRight() == null ){
			
			parent.setLeft(null);
			parent.setHeight(0);
			min.setRight(deletedNode.getRight());
			min.setLeft(deletedNode.getLeft());
			min.setHeight(Math.max(height(min.getLeft()), height(min.getRight())) + 1 );

			
		}
		
		else if(min == deletedNode.getRight()){
			
			min.setLeft(deletedNode.getLeft());
			min.setHeight(Math.max(height(min.getLeft()), height(min.getRight())) + 1 );

			
			
		}

		return min ;
		
	}
		

	 
	public boolean search_word(String word){
		
		return (search_word(word , getRoot()));
 
		
	}
	
	
	private ArrayList<String> searchedFiles ;
	
	public ArrayList<String> getSearchedFiles() {
		return searchedFiles;
	}
	
	
	public void setSearchedFiles(ArrayList<String> searchedFiles) {
		this.searchedFiles = searchedFiles;
	}
	
	public boolean search_word(String word , Node current ) {
		
		
		if(  current == null )
			
			return false ; 
		
		else if (word.compareToIgnoreCase(current.getData()) == 0 ){
			
			setSearchedFiles(current.getLinkList().traverse());
			return true;
			
		}	

		else if(word.compareToIgnoreCase(current.getData()) < 0)

			return search_word(word, current.getLeft());
					
		else 
				
			return search_word(word, current.getRight());
			
			
		
	}
	
	
	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}
	

	
	
	
	
	
	
	
	
	
	/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
	
	
	
	
	
	public class Node{
		
		private String data;
		private int height; 
		private Node left;
		private Node right;
	 	private LinkedList linkList;

		
		public Node(String data){
			
			this.data = data ; 
			this.left = null ; 
			this.right = null; 
			this.linkList = new LinkedList();
			this.height = 1; 
			
		}
		
		
		public void addLinkList(String word){
			
			this.linkList.add(word);
			
		}
		
		public LinkedList getLinkList() {
			return linkList;
		}
		
		
		public String getData() {
			return data;
		}
		
		public void setLeft(Node left) {
			this.left = left;
		}
		
		public void setRight(Node right) {
			this.right = right;
		}
		public Node getLeft() {
			return left;
		}
		public Node getRight() {
			return right;
		}
		
		public int getHeight() {
			return height;
		}
		
		public void setHeight(int height) {
			this.height = height;
		}	
		
		
		
		
	}


}
