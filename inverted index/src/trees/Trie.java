package trees;

import dataStructures.LinkedList;
import java.io.File;
import java.util.ArrayList;
import main.StopWords;

public class Trie implements Trees{

	private TrieNode root;
	private StopWords stopWords;


	public Trie(StopWords stopWords) {
		// TODO Auto-generated constructor stub

		this.root = new TrieNode();
		this.stopWords = stopWords;

	}
	
	
	public TrieNode getRoot() {
		return root;
	}
	
	
	private Integer numberOfWords = 0; 
	
	public  Integer getNumberOfWords() {
		return numberOfWords;
	}
	
	
	
	
	public void number_of_words(TrieNode p){
		
		if(p.isEnd()){
		
			numberOfWords +=1 ; 
			
		}
		
		for( int i = 0 ; i < 26 ; i++){

			if(p.link[i] != null )
				number_of_words(p.link[i]) ;
			
		}
		
				
		
		
	}
	
	
	private	ArrayList<String> listOfWords = new ArrayList<>() ;
	
	public ArrayList<String> getListOfWords() {
		return listOfWords;
	}
	
	public void setListOfWords(ArrayList<String> listOfWords) {
		this.listOfWords = listOfWords;
	}
	

	
	public void list_of_all_of_words(TrieNode p , String thisString){
		

		
		if(p.isEnd()){
			
			ArrayList<String> temp =p.getLinkList().traverse() ;
			
			String n =  "" ; 
			
			for( int i =0 ; i< temp.size() ; i++){
				
				n +=temp.get(i); 
				
			}
			
			listOfWords.add(thisString + " -> " + n);
			
			 
			
		}
		
		
		for( int i = 0 ; i < 26 ; i++){
			
			if(p.link[i] != null ){
				String temp = "";
				temp += thisString+(char)(i+'a');
				list_of_all_of_words(p.link[i],temp);
			}
			
		}
		
		
		
		
		
	}
	

	private ArrayList<String> list_of_all_docs = new ArrayList<>();

	public ArrayList<String> getList_of_all_docs() {
		return list_of_all_docs;
	}
	
	public void setList_of_all_docs(ArrayList<String> list_of_all_docs) {
		this.list_of_all_docs = list_of_all_docs;
	}
	
	public void listedDocs(TrieNode p ){
		
		if(p.isEnd()){
			
			ArrayList<String> temp = p.getLinkList().traverse();
			
			for(int i =0 ; i < temp.size() ; i++){
				
				if(!list_of_all_docs.contains(temp.get(i)))
					
					list_of_all_docs.add(temp.get(i));
				
			} 
			
		}
		
		for( int i = 0 ; i < 26 ; i++){

			if(p.link[i] != null )
			listedDocs(p.link[i]) ;
			
		}
	}



	public void add(String word , File file) {
		// TODO Auto-generated method stub
		if(!stopWords.containStopWords(word)){
			
			TrieNode node = root ;

			for(int i =0 ; i< word.length() ; i++){


				char currentChar  = word.toLowerCase().charAt(i);


				if(!node.containsKey(currentChar)){

					node.put(currentChar, new TrieNode());

				}

				node = node.get(currentChar);



			}

			node.addLinkList(file.getName());
			node.setEnd(true);

		}

	}

	@Override
	public void delete(String word , File file) {
		// TODO Auto-generated method stub
		if(!stopWords.containStopWords(word)){

			delete(root, word.toLowerCase(), 0 , file);

		}

	}
	
	private boolean delete( TrieNode current  ,String word , int index  , File file ){
		
		if(index == word.length()   ){
			
			
			
			if(!current.isEnd()){
				
				
				return false ; 
						
			}
			
			if(current.getLinkList().getSize() ==1){
				current.setEnd(false);
			
			}
			
			else{
				
				current.getLinkList().remove(file.getName());
				
			}
			return isChildrenNull(current);
			
		}
		
		char c = word.charAt(index);
		
		TrieNode node = current.get(c);
		
		if(node == null){
			
			return false ; 
			
		}
		
		boolean shouldDeleteNode = delete(node, word, index + 1 , file);
		
		if(shouldDeleteNode){
			
				current.put(c, null);
				return isChildrenNull(current);

			
		}
		
		return false;
		
	}
	
	private boolean isChildrenNull(TrieNode t ){
		
		int index = 0 ; 
		
		for( int i =0 ; i<t.getLink().length ; i++){
			
			if(t.getLink()[i] != null )
				
				index++;
		}
		
		if(index==0)
			return true;
		else 
			return false ; 
		
		
	}

	
	public void update() {
		// TODO Auto-generated method stub

	}

	
	public void list_word() {
		// TODO Auto-generated method stub

	}

	private ArrayList<String> searchedFiles ;
	
	public ArrayList<String> getSearchedFiles() {
		return searchedFiles;
	}
	
	public void setSearchedFiles(ArrayList<String> searchedFiles) {
		this.searchedFiles = searchedFiles;
	}
	
	public boolean search_word(String word) {
		// TODO Auto-generated method stub

		TrieNode node = searchPrefix(word);

		if( (node != null) && (node.isEnd())){
			
			setSearchedFiles(node.getLinkList().traverse());
			return true ;
		}
	
		else
			
			return false ;

	}

	private TrieNode searchPrefix(String word){

		TrieNode node = root; 

		for(int i = 0 ; i< word.length() ; i++){

			char currentChar = word.toLowerCase().charAt(i);

			if(node.containsKey(currentChar)){

				node = node.get(currentChar);

			}
			else{

				return null; 

			}

		}

		return node ; 

	}
	

	
	public void search_phrase() {
		// TODO Auto-generated method stub

	}

	private class TrieNode{

		private TrieNode [] link; 

		private boolean isEnd; 
		
		private LinkedList linkList;

		public TrieNode(){

			this.link = new TrieNode[26];
			this.linkList = new LinkedList();
			
		}
		
		private void addLinkList(String string){
			
			this.linkList.add(string);
			
		}
		
		public LinkedList getLinkList() {
			return linkList;
		}

		public boolean containsKey(char c){

			return link[c-'a'] != null; 

		}

		public TrieNode get(char c ){

			return link[c-'a'];

		}

		public void put(char c , TrieNode node ){

			link[c-'a'] = node;

		}

		public void setEnd(boolean isEnd) {
			this.isEnd = isEnd;
		}

		public boolean isEnd() {
			return isEnd;
		}
		
		public TrieNode[] getLink() {
			return link;
		}


	}








}
