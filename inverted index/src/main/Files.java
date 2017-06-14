package main;

import trees.Bst;
import trees.Trees;
import trees.Tst;
import trees.Trie;
import trees.HashTable;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Files {
	
	private File myDirection ;
	String string ; 
	private Trees []  tree;
	private int  number_of_files;

	

	public Files(Bst Bst ) {
		// TODO Auto-generated constructor stub
		
		this.tree = new Trees [4];
		this.tree [0] = Bst ;
		
	}
	public Files(Tst TST  ) {
		// TODO Auto-generated constructor stub
		
		this.tree = new Trees [4];

		this.tree [1] = TST ;
		
	}
	public Files(Trie TRIE ) {
		// TODO Auto-generated constructor stub
		
		this.tree = new Trees [4];
		this.tree [2] = TRIE ;
		
	}
	
	public Files(HashTable hashTable ) {
		// TODO Auto-generated constructor stub
		
		this.tree = new Trees [4];
		this.tree [3] = hashTable ;
		
	}
	
	
	
	
	

	
	public File getMyDirection() {
		return myDirection;
	}
	
	public Integer getNumber_of_files() {
		return number_of_files;
	}
	
	public void increaseNumber_of_files(int increament) {
		this.number_of_files += increament ;
	}


	
	public void setMyDirection(File myDirection) {
		this.myDirection = myDirection;
	}



	
	public  ArrayList<String> readFromFile(File myDir , int chosenTree){
		
		ArrayList<String> split  = new ArrayList<>();
		
		for (File f: myDir.listFiles()){
			
				
				//String string = "";   // string for all word in a specific file
				string = "";
				
				try {
					
					Scanner input = new Scanner(new FileReader(f));
					DataInputStream dataIn = new DataInputStream(new FileInputStream(f));
					
					while(dataIn.available()>0){
						
						string += dataIn.readLine();
						string += " ";

					}
					
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				
				
				String [] x = string.split("[^a-zA-Z]+");
				 
				
				 
				 increaseNumber_of_files(1);

				 for(int i =1 ; i < x.length ; i++){
					 
					 tree[chosenTree-1].add(x[i] , f);
					 
					 
				 } 

				
		}
		

		
		
		return split;
		
		
	}
	
	
	
	public void addFile(File file , int chosenTree){
		
		string = "";
		
		try {
			
			Scanner input = new Scanner(new FileReader(file) );
			DataInputStream dataIn = new DataInputStream(new FileInputStream(file));
			
			while(dataIn.available()>0){
				
				string += dataIn.readLine();
				string += " ";

			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		 String [] x = string.split("[^a-zA-Z]+");
		 
		 
		 increaseNumber_of_files(1);

		 for(int i =1 ; i < x.length ; i++){
			 
			 tree[chosenTree-1].add(x[i] , file );
			 
			 
		 } 
		 
		 
		 
		 

		
		
		
		
		
		
		
		
	}
	
	
	
	
	public void deleteFile(File file , int chosenTree){

		string = "";

		try {

			Scanner input = new Scanner(new FileReader(file) );
			DataInputStream dataIn = new DataInputStream(new FileInputStream(file));

			while(dataIn.available()>0){

				string += dataIn.readLine();
				string += " ";

			}


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		String [] x = string.split("[^a-zA-Z]+");


		increaseNumber_of_files(-1);
		

		for(int i =1 ; i < x.length ; i++){

			tree[chosenTree-1].delete(x[i], file);


		} 

		
		
		
		
		
		
		
	}
	
	
	
	public void  update (File file , int chosenTree){
		
		
		deleteFile(file, chosenTree);
		
		addFile(file, chosenTree);
		
		
	}



	
	


	
	

}
