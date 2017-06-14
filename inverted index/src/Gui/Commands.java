package Gui;

import java.util.ArrayList;

import trees.Bst;
import trees.Tst;
import trees.Trie;

public class Commands {
	
	private Bst BST; 
	private Tst TST;
	private Trie TRIE;
	private java.io.File file;
	
	private ArrayList<String> history; 


	public Commands(Bst BST  , java.io.File file  ) {
		// TODO Auto-generated constructor stub
		
		this.BST = BST ; 
		this.file = file ; 
		this.history = new ArrayList<>();
		
	}
	
	public Commands(Tst TST , java.io.File file ) {
		// TODO Auto-generated constructor stub
		
		this.TST = TST ; 
		this.file = file ;
		this.history = new ArrayList<>();
		
	}
	
	public Commands(Trie TRIE  , java.io.File file ) {
		// TODO Auto-generated constructor stub
		
		this.TRIE = TRIE ; 
		this.file = file ; 
		this.history = new ArrayList<>();
		
	}
	
	public boolean addfile(){
		
		return false ; 
	}
	

	
	public ArrayList<String> getHistory() {
		return history;
	}
	
	

}
