package trees;

import java.io.File;
import java.util.ArrayList;

import dataStructures.LinkedList;
import main.StopWords;
import trees.Bst.Node;

public class Tst implements Trees{
	
	private TSTNodes root;
	private StopWords stopWords;

	public Tst(StopWords stopWords) {
		// TODO Auto-generated constructor stub
		
		this.root = null; 
		this.stopWords = stopWords;
		
	}
	
	
	public TSTNodes getRoot() {
		return root;
	}

	
	public void add(String word , File file) {
		// TODO Auto-generated method stub
		if(!stopWords.containStopWords(word)){

		root = add(root, word, 0 , file );
	
		}
	}
	
	private TSTNodes add(TSTNodes node , String word , int index , File file ){
		
		char c = word.toLowerCase().charAt(index);     
		
		if(node == null)
			
			node = new TSTNodes(c);
		
		if(c < node.getData())
			
			node.setLeft(add(node.getLeft(), word, index , file));
		
		else if (c > node.getData())
			
			node.setRight(add(node.getRight(), word, index , file));

		else{

			if (index < word.length() - 1)

				node.setMid(add(node.getMid(), word, index+1 , file));

			else{

				node.addLinkList(file.getName());
				node.setEnd(true);

			}
		}


		node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1 );

		//get the balancedFactor to check which node should be rotated 

		int balancedFactor = getBalancedFactor(node);


		// LL rotation

		if(balancedFactor > 1 && c < node.getLeft().getData() )

			return rotateRight(node);

		// LR rotation

		if(balancedFactor > 1 && c > node.getLeft().getData()  ){

			node.setLeft(rotateLeft(node.getLeft()));

			return rotateRight(node);

		}

		// RR rotation

		if(balancedFactor < -1 && c > node.getRight().getData() )

			return rotateLeft(node);

		//RL rotation 

		if(balancedFactor < -1 && c < node.getRight().getData() ){

			node.setRight(rotateRight(node.getRight()));

			return rotateLeft(node);

		}

		
		return node; 
			
	}
	
	/**************/
	
	public Integer depth(TSTNodes p ){
		
		if( p == null ) return 0 ;   
		
		return Math.max(depth(p.getLeft()) , Math.max(depth(p.getRight()), depth(p.getMid()))) + 1 ; 
	}

	private int height(TSTNodes p ){

		if( p == null )

			return 0 ; 

		return p.getHeight();
	}
	
	private TSTNodes rotateLeft(TSTNodes p ){

		TSTNodes q = p.getRight();
		TSTNodes m = q.getLeft();

		//rotate left
		p.setRight(m);
		q.setLeft(p);

		//update heights 
		p.setHeight(Math.max(height(p.getLeft()), height(p.getRight())) + 1 );
		
		q.setHeight(Math.max(height(q.getLeft()), height(q.getRight())) + 1 );
		
		return q ; 
		
	}
	
	private TSTNodes rotateRight(TSTNodes p ){
		
		TSTNodes q = p.getLeft(); 
		TSTNodes m = q.getRight();
		
		//rotate right 
		p.setLeft(m);
		q.setRight(p);
		
		//update heights 
		p.setHeight(Math.max(height(p.getLeft()), height(p.getRight())) + 1 );

		q.setHeight(Math.max(height(q.getLeft()), height(q.getRight())) + 1 );
		
		return q ; 
		
	}
	
	private int getBalancedFactor(TSTNodes p ){
		
		if(p == null )
			return 0 ; 
		
		return height(p.getLeft()) - height(p.getRight());
	}

	
	/*****************************************************************************************/

	
	@Override
	public void delete(String word , File file) {
		// TODO Auto-generated method stub
		
		this.root = delete(root	,root,  word.toLowerCase().toCharArray(), 0 , file);
		
	}
	
	private boolean isLeftChild ; 
	private TSTNodes delete(TSTNodes current , TSTNodes parent, char [] word , int index , File file ){
		
		if(current == null){
			
			return null;
			
		}

		if(word[index] < current.getData()){

			isLeftChild = true ; 
			current.setLeft(delete(current.getLeft(), current , word , index , file));
			
		}

		else if (word[index] > current.getData()){

			isLeftChild = false ; 
			current.setRight(delete(current.getRight(), current , word , index , file ));
			
		}
		
		else{
			
			if(current.isEnd() && index == word.length - 1){
			
				if(current.getLinkedList().getSize() ==1 ){ 
				
					if(current.getMid() == null ){ // the node should be deleted ... 
						
						return deleteFoundWord(file , current , parent , isLeftChild);
						
					}
					
					else { // isEnd just should be set false ... 
						
						current.setEnd(false);
						
					}			
							
				}
				else
					
					current.getLinkedList().remove(file.getName());
				
				
			}
			
			else if(index  < word.length - 1 )
				
				current.setMid(delete(current.getMid(),current,  word, index + 1 , file));
			
			
		}
		

		// update height 

		current.setHeight(Math.max(height(current.getLeft()), height(current.getRight())) + 1 );

		//get the balancedFactor to check which node should be rotated 

		int balancedFactor = getBalancedFactor(current);


		// LL rotation

		if(balancedFactor > 1 && word[index] < (current.getLeft().getData())   )

			return rotateRight(current);

		// LR rotation

		if(balancedFactor > 1 && word [index] > (current.getLeft().getData())   ){

			current.setLeft(rotateLeft(current.getLeft()));

			return rotateRight(current);
		

		}

		// RR rotation

		if(balancedFactor < -1 && word[index] > (current.getRight().getData()) )

			return rotateLeft(current);

		//RL rotation 

		if(balancedFactor < -1 && word[index] < (current.getRight().getData()) ){

			current.setRight(rotateRight(current.getRight()));

			return rotateLeft(current);

		}

		return current ; 

		
		
		

	}
	
	public TSTNodes deleteFoundWord(File file , TSTNodes current , TSTNodes parent , boolean isLeftChild){
		
		if(current.getLinkedList().getSize() >1 ){

			current.getLinkedList().remove(file.getName());
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




				TSTNodes min = min_of_right_sub_tree(current);   // min_of_right_sub_tree

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

	private TSTNodes min_of_right_sub_tree(TSTNodes deletedNode){

		TSTNodes min = null;
		TSTNodes parent = null; 
		TSTNodes current = deletedNode.getRight();


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







	
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void list_word() {
		// TODO Auto-generated method stub
		
	}
	
	private ArrayList<String> searchedFiles  = new ArrayList<>();
	
	public ArrayList<String> getSearchedFiles() {
		return searchedFiles;
	}
	
	public void setSearchedFiles(ArrayList<String> searchedFiles) {
		this.searchedFiles = searchedFiles;
	}

	
	public boolean search_word(String word) {
		// TODO Auto-generated method stub
		
		return search(root, word, 0);
		
	}
	
	private boolean search(TSTNodes node , String word , int index){
		
		
		
		if(node.isEnd() && index == word.length()-1){
			
			setSearchedFiles(node.getLinkedList().traverse());
			return true ; 
			
		}
		
		
		
		char c = word.toLowerCase().charAt(index);
		
		if(c < node.getData() && node.getLeft()!=null)
			
			return search(node.getLeft(), word, index);
		
		else if(c > node.getData() && node.getRight() != null )
			
			return search(node.getRight(), word, index);
		
		else if (index < word.length() -1 && node.getMid() != null )
			
			return search(node.getMid(), word, index + 1 );
		
		else 
			
			return false ; 
	}
	
	
	private	ArrayList<String> listOfWords = new ArrayList<>() ;
	
	public ArrayList<String> getListOfWords() {
		return listOfWords;
	}
	
	public void setListOfWords(ArrayList<String> listOfWords) {
		this.listOfWords = listOfWords;
	}
	

	
	public void list_of_all_of_words(TSTNodes p , String thisString){
		
		
		String temp = "";
		temp += thisString+ p.getData();

		if(p.isEnd()){

			ArrayList<String> t =p.getLinkedList().traverse() ;
			
			String n =  "" ; 
			
			for( int i =0 ; i< t.size() ; i++){
				
				n +=t.get(i); 
				
			}
			listOfWords.add(temp + " -> " + t );
			 

		}
		


		if( p.getLeft()!=null){
				
			list_of_all_of_words(p.getLeft(), temp.substring(0 , temp.length()-1)  );
			 
		}

		if( p.getRight() != null ){

			list_of_all_of_words(p.getRight() , temp.substring(0, temp.length()-1) );
			 
		}

		if (p.getMid() != null ){

			list_of_all_of_words(p.getMid() , temp );
			
		}

		else 

			return;
		
		
	}
	
	
	private ArrayList<String> list_of_all_docs = new ArrayList<>();

	public ArrayList<String> getList_of_all_docs() {
		return list_of_all_docs;
	}
	
	public void setList_of_all_docs(ArrayList<String> list_of_all_docs) {
		this.list_of_all_docs = list_of_all_docs;
	}
	
	public void listedDocs(TSTNodes p ){
		
		if(p.isEnd()){


			ArrayList<String> temp = p.getLinkedList().traverse();

			for(int i =0 ; i < temp.size() ; i++){

				if(!list_of_all_docs.contains(temp.get(i)))

					list_of_all_docs.add(temp.get(i));

			} 

		}


		if( p.getLeft()!=null)

			number_of_words(p.getLeft());

		if( p.getRight() != null )

			number_of_words(p.getRight());

		if (p.getMid() != null )

			number_of_words(p.getMid());

		else 

			return;

		
	}
	
	private Integer numberOfWords = 0; 
	
	public  Integer getNumberOfWords() {
		return numberOfWords;
	}
	
	
	
	
	public void number_of_words(TSTNodes p){

		if(p.isEnd()){

			
			numberOfWords++;
			 

		}


		if( p.getLeft()!=null)

			 number_of_words(p.getLeft());

		if( p.getRight() != null )

			 number_of_words(p.getRight());

		if (p.getMid() != null )

			number_of_words(p.getMid());

		else 

			return;
		
	}
		
	public void search_phrase() {
		// TODO Auto-generated method stub
		
	}
	
	public class TSTNodes{
		
		private char data ; 
		private boolean isEnd;
		private TSTNodes left , mid , right ; 
		private LinkedList linkedList;
		private int height; 
		
		public TSTNodes(char data ) {
			// TODO Auto-generated constructor stub
			
			this.data = data ;
			this.isEnd = false ; 
			this.left = null;
			this.mid = null; 
			this.right = null; 
			this.linkedList = new LinkedList();
			this.height = 1 ; 

		}
		
		public void setLeft(TSTNodes left) {
			this.left = left;
		}
		
		public void setRight(TSTNodes right) {
			this.right = right;
		}
		
		public void setMid(TSTNodes mid) {
			this.mid = mid;
		}
		
		public char getData() {
			return data;
		}
		
		public TSTNodes getLeft() {
			return left;
		}
		public TSTNodes getRight() {
			return right;
		}
		public TSTNodes getMid() {
			return mid;
		}
		
		public void setEnd(boolean isEnd) {
			this.isEnd = isEnd;
		}
		
		public boolean isEnd() {
			return isEnd;
		}
		
		public LinkedList getLinkedList() {
			return linkedList;
		}

		private void addLinkList(String string){

			this.linkedList.add(string);

		}
		
		public void setHeight(int height) {
			this.height = height;
		}
		public int getHeight() {
			return height;
		}
	}

}
