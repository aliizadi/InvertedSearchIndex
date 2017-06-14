package main;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import trees.Bst;



public class StopWords {
	
	private File stopwords; 
	private String [] words;
	private Bst BST;  
	public StopWords() {
		// TODO Auto-generated constructor stub
		
		
		this.BST = new Bst(this);
		
		
		stopwords = new File("D:\\my program\\inverted index\\StopWords.txt");
		
		String string = "" ; 
		
		try {
			
			Scanner input = new Scanner(new FileReader(stopwords));
			DataInputStream dataIn = new DataInputStream(new FileInputStream(stopwords));
			
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
		
		words = string.split("[^a-zA-Z']+");
		
		for(int i =0 ; i < words.length ; i++){

			BST.add(words[i], stopwords);


		} 
		
		System.out.println(BST.depth(BST.getRoot()));
		
		

	}
	
	public void readFromFile(){
		
		
		
		
	}
	
	public boolean containStopWords(String word){
		
		return BST.search_word(word);
		
	}
	
	public String[] getWords() {
		return words;
	}
	
	public static void main(String[] args) {
		
		StopWords a = new StopWords();
	
		for(String i : a.getWords())
			System.out.println(i);
	}
	


}
