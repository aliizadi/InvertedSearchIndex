package trees;

import java.io.File;
import java.util.ArrayList;

import dataStructures.LinkedList;
import main.StopWords;

public class HashTable implements Trees {

	private ListNode [] table ; 
	private int counter ; // number of pairs in the hash table
	private StopWords stopWords; 




	public HashTable(int size , StopWords stopWords) {
		// TODO Auto-generated constructor stub

		this.table = new ListNode[size];

		this.stopWords = stopWords ; 

	}

	@Override
	public void add ( String word   , File file ){
		

		if(!stopWords.containStopWords(word)){
			
			

			int bucket = find_hash_code(word);

			ListNode list = table [bucket] ; 


			while( list != null ){

				if(list.word.compareToIgnoreCase(word) == 0 )

					break ;  

				list = list.next ; 
			}

			if(list != null ){


				list.addLinkList(file.getName());


			}

			else {

				if(counter >= 0.75 * table.length ){

					resize(); 

				}

				ListNode newNode = new ListNode() ; 

				newNode.word = word ; 

				newNode.addLinkList(file.getName());

				newNode.next = table[bucket] ; // be sare list ezafe mikonim

				table[bucket] = newNode ; 

				counter ++ ;


			}

		}

	}

	@Override
	public void delete ( String word  , File file ){
		


		if(!stopWords.containStopWords(word)){

			int bucket = find_hash_code(word);

			if(table[bucket] == null)

				return ; 

			if (table[bucket].word.compareToIgnoreCase(word) == 0 ){

				if(table[bucket].linkList.getSize() >1 ){

					table[bucket].linkList.remove(file.getName());

				}

				else{

					table[bucket] = table[bucket].next;

					counter -- ; 

					return ; 
				}
			}

			ListNode prev = table[bucket] ;  // the node that precedes current in the list 

			ListNode current = prev.next ; 

			while(current != null && current.word.compareToIgnoreCase(word) != 0 ){

				prev = current ; 
				current = current.next ;


			}

			if(current != null){


				if(table[bucket].linkList.getSize() >1 ){

					table[bucket].linkList.remove(file.getName());

				}

				else{
					prev.next = current.next;

					counter -- ;
				}
			}

		}



	}

	private ArrayList<String> searchedFiles ;

	public ArrayList<String> getSearchedFiles() {
		return searchedFiles;
	}

	public void setSearchedFiles(ArrayList<String> searchedFiles) {
		this.searchedFiles = searchedFiles;
	}

	public boolean search_word(String word){

		int bucket = find_hash_code(word);

		ListNode list = table[bucket]; 

		while(list != null ){

			if(list.word.compareToIgnoreCase(word) == 0 ){

				setSearchedFiles(list.getLinkList().traverse());
				return true ; 
			
			}

			list = list.next ; 
		}

		return false ; 

	}


	private int find_hash_code(String word ) {

		return (Math.abs(word.toLowerCase().hashCode())) % table.length ; 

	}

	private void resize(){ // increasing the size(double it ) of the table when it becomes too full. reach the counter at 3/4 of table length

		ListNode [] newTable = new ListNode[table.length * 2 ]; 

		for(int i = 0 ; i< table.length ; i++ ){	

			ListNode list  = table [i] ; 

			while (list != null ){ 

				ListNode next  = list.next ; 

				int hash = (Math.abs(list.word.toLowerCase().hashCode())) % newTable.length  ;

				// add onto the head of linked list 

				list.next = newTable[hash];

				newTable[hash] = list ; 

				list = next ; 

			}	
		}

		table = newTable ; 


	}
	
	
	/***************************************************************************/

	private Integer numberOfWords = 0; 

	public  Integer getNumberOfWords() {
		return numberOfWords;
	}
	

	public void number_of_words(){

		for(int i = 0 ; i< table.length ; i++ ){	

			ListNode list  = table [i] ; 

			while (list != null ){ 

				 list   = list.next ;
				 numberOfWords ++ ;
				
			}
			
		}
	}
	
	

	public int getCounter() {
		return counter;
	}
	
	
	private ArrayList<String> list_of_all_docs = new ArrayList<>();

	public ArrayList<String> getList_of_all_docs() {
		return list_of_all_docs;
	}
	
	public void setList_of_all_docs(ArrayList<String> list_of_all_docs) {
		this.list_of_all_docs = list_of_all_docs;
	}
	
	public void listedDocs(){
		
		for(int i = 0 ; i< table.length ; i++ ){	

			ListNode list  = table [i] ; 

			while (list != null ){ 

				ArrayList<String> temp = list.getLinkList().traverse();
				
				for(int j =0 ; j < temp.size() ; j++){
					
					if(!list_of_all_docs.contains(temp.get(j)))
						
						list_of_all_docs.add(temp.get(j));
					
				}

					
				list   = list.next ;
				
			}
			
		}
		
		
		
		
		
	}







	private class ListNode{

		String word ; 
		ListNode next ;
		private LinkedList linkList; 

		public ListNode() {
			// TODO Auto-generated constructor stub

			this.linkList = new LinkedList();

		}

		public void addLinkList(String fileName){

			this.linkList.add(fileName);

		}

		public LinkedList getLinkList() {
			return linkList;
		}

	}




}
