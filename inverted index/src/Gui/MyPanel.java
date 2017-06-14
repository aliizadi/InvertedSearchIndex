package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.*;
import java.io.File;
import java.net.StandardProtocolFamily;
import java.util.ArrayList;

import main.Files;
import trees.Bst;
import trees.Tst;
import trees.Trie;
import trees.HashTable;
import main.StopWords;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.Document;


public class MyPanel extends JPanel implements MouseListener , KeyListener {

	private JLabel JLable1;
	private JLabel JLable2;
	private JFileChooser jFileChooser;

	private MyFrame myFrame;
	private MyPanel myPanel ; 

	private java.io.File file;

	private JButton showFileDialogButton;
	private JButton enter;
	private JTextField textfield;
	private JButton build , reset , help  , exit ; 
	private JTextArea jTextArea , jTextArea1;
	private JRadioButton bst , tst , trie , hash;

	private int chosenTree;

	private Files files; 

	private Bst BST;
	private Tst TST;
	private Trie TRIE;
	private HashTable HASHTABLE;

	private JScrollPane jScrollBar;
	
	private ArrayList<String> history  = new ArrayList<String>(); 
	private int indexOfHistory = 0 ; 
	
	private StopWords stopWords ; 






	public MyPanel(MyFrame myFrame) {
		// TODO Auto-generated constructor stub

		setFocusable(true);
		setLayout(null);
		setSize(615 , 785 );
		setLocation(0 , 0);
		setVisible(true);
		addKeyListener(this);
		
		
		this.stopWords = new StopWords() ; 


		chosenTree = 1 ; //bst

		JLable1 = new JLabel("Please enter folder address or use browse button");
		JLable1.setSize(500,30);
		JLable1.setLocation(10 ,20 );
		JLable1.setForeground(Color.black);
		JLable1.setFont(new Font(getFont().getName(),getFont().getStyle(),17) );
		add(JLable1);

		JLable2 = new JLabel("Please enter your command : ");
		JLable2.setSize(300,30);
		JLable2.setLocation(10 ,550 );
		JLable2.setForeground(Color.black);
		JLable2.setFont(new Font(getFont().getName(),getFont().getStyle(),17) );
		add(JLable2);



		jTextArea = new JTextArea("");
		jTextArea.setEditable(false);
		jTextArea.setLocation(20, 80);
		jTextArea.setSize(400, 30);
		jTextArea.setFont(new Font(getFont().getName(),getFont().getStyle(),17));

		add(jTextArea);


		jFileChooser = new JFileChooser();
		showFileDialogButton = new JButton("Open File");
		showFileDialogButton.setLocation(450, 80);
		showFileDialogButton.setSize(100, 30);

		setfile();

		textfield = new JTextField(1);
		textfield.setLocation(95, 600);
		textfield.setSize(500 , 30);
		textfield.setFont(new Font(getFont().getName(),getFont().getStyle(),18) );

		add(textfield);


		enter = new JButton("Enter");
		enter.setLocation(10, 600);
		enter.setSize(70, 28);

		add(enter);
		enter.addMouseListener(this);

		build = new JButton("Build");
		build.setLocation(40, 670);
		build.setSize(100, 30);

		add(build);
		build.addMouseListener(this);



		reset = new JButton("Reset");
		reset.setLocation(180, 670);
		reset.setSize(100, 30);

		add(reset);
		reset.addMouseListener(this);

		help = new JButton("Help");
		help.setLocation(320, 670);
		help.setSize(100,30);

		add(help);
		help.addMouseListener(this);

		exit = new JButton("Exit");
		exit.setLocation(460, 670);
		exit.setSize(100, 30);

		add(exit);
		exit.addMouseListener(this);

		bst = new JRadioButton("BST", true);
		bst.setLocation(340 , 555 );
		bst.setSize(50 , 20);

		tst = new JRadioButton("TST");
		tst.setLocation(390 , 555);
		tst.setSize(50 ,20);


		trie = new JRadioButton("Trie");
		trie.setLocation(440 , 555);
		trie.setSize(50 ,20);
		
		hash = new JRadioButton("HASH");
		hash.setLocation(490 , 555 );
		hash.setSize(80 , 20);

		jRadioButtons();


		jTextArea1 = new JTextArea("" , 100 , 100 );
		jTextArea1.setEditable(false);
		jTextArea1.setLineWrap(true);
		jTextArea1.setFont(new Font(getFont().getName(),getFont().getStyle(),16));

		jScrollBar = new JScrollPane(jTextArea1);
		jScrollBar.setLocation( 20, 150);
		jScrollBar.setSize(565, 380);

		add(jScrollBar);

	}



	public void setfile(){

		showFileDialogButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = jFileChooser.showOpenDialog(myFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = jFileChooser.getCurrentDirectory();					 
					jTextArea.setText("  " + jFileChooser.getCurrentDirectory().toString());	 

				}      
			}
		});

		add(showFileDialogButton);

	}

	public void jRadioButtons(){

		bst.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {         

				setChosenTree(1); //bst


			}           
		});

		tst.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {         

				setChosenTree(2); //tst
			}           
		});

		trie.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {         

				setChosenTree(3); //trie
			}           
		});
		
		hash.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {         

				setChosenTree(4); //hashTable
			}           
		});

		ButtonGroup group = new ButtonGroup();

		group.add(bst);
		group.add(tst);
		group.add(trie);
		group.add(hash);

		add(bst);
		add(tst);
		add(trie);
		add(hash);

	}


	public java.io.File getFile() {
		return file;
	}

	public MyPanel getMyPanel() {
		return myPanel;
	}

	public void setChosenTree(int chosenTree) {
		this.chosenTree = chosenTree;
	}

	public int getChosenTree() {
		return chosenTree;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

		if(arg0.getSource()==build){

			if(file == null){

				JOptionPane.showMessageDialog(null, "Please Enter the Diretory");

			}

			else if( getChosenTree() ==1 ){
				
				
				long n = System.currentTimeMillis();
				
				BST = new Bst(stopWords);
				Files a = new Files(BST);
				a.readFromFile(file ,1);
			
				long m = System.currentTimeMillis();
				
				JOptionPane.showMessageDialog(null, "BST Have Been Built...");
				jTextArea1.append("depth : " + BST.depth(BST.getRoot()).toString()+"\n");
				jTextArea1.append("number of nodes : "+BST.getNumberOfWords(BST.getRoot()).toString()+"\n");
				jTextArea1.append("number of files : "+a.getNumber_of_files().toString()+"\n");
				jTextArea1.append("Time : " + (m-n) + " mili second");
				

			}

			else if (getChosenTree() == 2 ){

				long n = System.currentTimeMillis();
				
				TST = new Tst(stopWords);
				Files a = new Files(TST);
				a.readFromFile(file ,2);
				
				long m = System.currentTimeMillis();
				
				JOptionPane.showMessageDialog(null, "TST Have Been Built...");
				jTextArea1.append("depth : " + TST.depth(TST.getRoot()).toString()+"\n");
				TST.number_of_words(TST.getRoot());
				jTextArea1.append("number of nodes : "+TST.getNumberOfWords().toString() +"\n");
				jTextArea1.append("number of files : "+a.getNumber_of_files().toString()+"\n");
				jTextArea1.append("Time : " + (m-n) + " mili second");


			}

			else if (getChosenTree() ==3 ){

				long n = System.currentTimeMillis();
				
				TRIE = new Trie(stopWords);
				Files a = new Files(TRIE);
				a.readFromFile(file ,3);
				
				long m = System.currentTimeMillis();

				JOptionPane.showMessageDialog(null, "TRIE Have Been Built...");
				TRIE.number_of_words(TRIE.getRoot());
				jTextArea1.append("number of nodes : "+TRIE.getNumberOfWords().toString() +"\n");
				jTextArea1.append("number of files : "+a.getNumber_of_files().toString()+"\n");
				jTextArea1.append("Time : " + (m-n) + " mili second"+"\n");


			}
			
			else if (getChosenTree() ==4 ){
				

				long n = System.currentTimeMillis();
				
				HASHTABLE = new HashTable(64, stopWords);
				Files a = new Files(HASHTABLE);
				a.readFromFile(file ,4);
				
				long m = System.currentTimeMillis();

				JOptionPane.showMessageDialog(null, "HASH Have Been Built...");
				HASHTABLE.number_of_words();
				jTextArea1.append("number of nodes : "+HASHTABLE.getNumberOfWords().toString() +"\n");
				jTextArea1.append("number of files : "+a.getNumber_of_files().toString()+"\n");
				jTextArea1.append("Time : " + (m-n) + " mili second"+"\n");
			
			

			}





		}

		if(arg0.getSource()==reset){


		}

		if(arg0.getSource()==help){
			
			JOptionPane.showMessageDialog(null,
					
						"add <document_name_in_current_folder>" + "\n" +
						"del <document_name>" + "\n" +
						"update <document_name>" + "\n" +
						"list –w  : list of all of words" + "\n" + 
						"list –l : list of all of files in the tree" + "\n" + 
						"list –f : list of all of files in the directory" + "\n" + "\n" + 
						"developed by Ali Izadi");

			
			


		}

		if(arg0.getSource()==exit){

			System.exit(0);

		}


		if(arg0.getSource()==enter){

			jTextArea1.setText("");
			
			history.add(jTextArea1.getText());
			indexOfHistory = history.size() -1; 

			String [] s = textfield.getText().split("[ ]");





			if(s[0].compareTo("list") == 0 && s[1].compareTo("-w")==0 ){


				if(getChosenTree() ==1 ){ //BST

					BST.preorder(BST.getRoot());
					
					
					ArrayList<String> temp = BST.getList_of_all_of_words();


					for(int i =0 ; i< temp.size() ; i++){

						jTextArea1.append(temp.get(i)+"\n");

					}
					
					jTextArea1.append("Number of words : " + temp.size()+"\n");
					
					//jTextArea1.append("depth : " + BST.depth(BST.getRoot()).toString()+"\n");

					//for(int i =0 ; i < BST.getList_of_all_of_words().size() ; i++)
						
						//BST.getList_of_all_of_words().remove(i);
					
					BST.setList_of_all_of_words(new ArrayList<>());
					
					

				}

				else if (getChosenTree() == 2 ){ //TST
					
		
					
					TST.list_of_all_of_words(TST.getRoot(),"" );
					
					ArrayList<String> temp = TST.getListOfWords();

					for(int i =0 ; i< temp.size() ; i++){

						jTextArea1.append(temp.get(i)+"\n");

					}

					jTextArea1.append("Number of words : " + temp.size()+"\n");
					
					TST.setListOfWords(new ArrayList<>());
					
				}

				else if (getChosenTree() == 3){ // Trie

					
					TRIE.list_of_all_of_words(TRIE.getRoot(), "");
					
					ArrayList<String> temp = TRIE.getListOfWords();

					for(int i =0 ; i< temp.size() ; i++){

						jTextArea1.append(temp.get(i)+"\n");

					}

					jTextArea1.append("Number of words : " + temp.size()+"\n");

					
					TRIE.setListOfWords(new ArrayList<>());


				}

			}
			
			/*****************************************************************************/
			
			if(s[0].compareTo("update") == 0 ){


				if(getChosenTree() ==1 ){ //BST
					
					int index =0 ; 
					
					File newFile = null ; 
					for(File i : file.listFiles()){

						if(i.getName().compareTo(s[1]) == 0 ){

							newFile = i ; 
							index = 1;
							
						}

					}

					if(index == 0 ){

						jTextArea1.append(" document not found " + "\n");

					}

					else{

						
						Files a = new Files(BST);
						a.update(newFile, getChosenTree());
						jTextArea1.append("successfully updated"+"\n");

					}

				}
				
				else if (getChosenTree() == 2){ //TST
					
					int index =0 ; 
					File newFile = null ; 

					for(File i : file.listFiles()){

						if(i.getName().compareTo(s[1]) == 0 ){
						
							newFile = i; 
							index = 1;
							
						}

					}

					if(index == 0 ){

						jTextArea1.append(" document not found " + "\n");

					}

					else{

						
						Files a = new Files(TST);
						a.update(newFile, getChosenTree());
						jTextArea1.append("successfully updated"+"\n");

					}
				}
				
				else if (getChosenTree() ==3 ){ //TRIE
					
					int index =0 ; 
					File newFile = null ; 

					for(File i : file.listFiles()){

						if(i.getName().compareTo(s[1]) == 0 ){
							
							newFile = i ; 
							index = 1;
							
						}

					}

					if(index == 0 ){

						jTextArea1.append(" document not found " + "\n");

					}

					else{

						Files a = new Files(TRIE);
						a.update(newFile, getChosenTree());
						jTextArea1.append("successfully updated"+"\n");

					}
					
				}
				
				
				else if (getChosenTree() ==4 ){ //hash 
					
					
					
					
				}
					
					
			}
			
			/******************************************************************************/
			
			if(s[0].compareTo("list") == 0 && s[1].compareTo("-l")==0){

				if(getChosenTree() ==1 ){ //BST

					BST.listedDocs(BST.getRoot());
					

					ArrayList<String> result = BST.getList_of_all_docs();

					int number_of_all_listed_docs = 0; 

					for(int i =0 ; i < result.size() ; i++ ){

						jTextArea1.append(result.get(i)+"\n");
						number_of_all_listed_docs++;

					}

					jTextArea1.append("number of all listed files : " + number_of_all_listed_docs );
					BST.setList_of_all_docs(new ArrayList<>());

				}	
				
				else if (getChosenTree() ==2 ){ //TST
					
					TST.listedDocs(TST.getRoot());
					

					ArrayList<String> result = TST.getList_of_all_docs();

					int number_of_all_listed_docs = 0; 

					for(int i =0 ; i < result.size() ; i++ ){

						jTextArea1.append(result.get(i)+"\n");
						number_of_all_listed_docs++;

					}

					jTextArea1.append("number of all listed files : " + number_of_all_listed_docs );

					TST.setList_of_all_docs(new ArrayList<>());
					
					
				}
				
				else if (getChosenTree() == 3 ){ //Trie
					
					TRIE.listedDocs(TRIE.getRoot());
					

					ArrayList<String> result = TRIE.getList_of_all_docs();

					int number_of_all_listed_docs = 0; 

					for(int i =0 ; i < result.size() ; i++ ){

						jTextArea1.append(result.get(i)+"\n");
						number_of_all_listed_docs++;

					}

					jTextArea1.append("number of all listed files : " + number_of_all_listed_docs );
					TRIE.setList_of_all_docs(new ArrayList<>());

					
				}
				
				else if( getChosenTree() == 4 ){ // hashTable
					
					HASHTABLE .listedDocs();
					
					ArrayList<String> result = HASHTABLE.getList_of_all_docs();
					
					int number_of_all_listed_docs = 0; 

					for(int i =0 ; i < result.size() ; i++ ){

						jTextArea1.append(result.get(i)+"\n");
						number_of_all_listed_docs++;

					}

					jTextArea1.append("number of all listed files : " + number_of_all_listed_docs );
					
					HASHTABLE.setList_of_all_docs(new ArrayList<>());
					
				}

			}

			/**************************************************************************/
			
			
			if(s[0].compareTo("list") == 0 && s[1].compareTo("-f")==0){

				int number_of_all_docs =0; 
				for(File f : file.listFiles()){
					jTextArea1.append(f.getName()+"\n");
					number_of_all_docs++;
				}
				
				jTextArea1.append("Number of all docs :  " + number_of_all_docs + "\n");
			
			}
			
			
			/*****************************************************************************/
			

			if(s[0].compareTo("add") == 0 ){


				if(getChosenTree() ==1 ){ //BST
					
					int index =0 ; 
					
					File newFile = null ; 
					for(File i : file.listFiles()){

						if(i.getName().compareTo(s[1]) == 0 ){

							newFile = i ; 
							index = 1;
							
						}

					}

					if(index == 0 ){

						jTextArea1.append(" document not found " + "\n");

					}

					else{

						Files a = new Files(BST);
						a.addFile(newFile, 1);

						jTextArea1.append("successfully added"+"\n");

					}

				}
				
				else if (getChosenTree() == 2){ //TST
					
					int index =0 ; 
					File newFile = null ; 

					for(File i : file.listFiles()){

						if(i.getName().compareTo(s[1]) == 0 ){
						
							newFile = i; 
							index = 1;
							
						}

					}

					if(index == 0 ){

						jTextArea1.append(" document not found " + "\n");

					}

					else{

						Files a = new Files(TST);
						a.addFile(newFile, 2);

						jTextArea1.append("successfully added"+"\n");

					}
				}
				
				else if (getChosenTree() ==3 ){ //TRIE
					
					int index =0 ; 
					File newFile = null ; 

					for(File i : file.listFiles()){

						if(i.getName().compareTo(s[1]) == 0 ){
							
							newFile = i ; 
							index = 1;
							
						}

					}

					if(index == 0 ){

						jTextArea1.append(" document not found " + "\n");

					}

					else{

						Files a = new Files(TRIE);
						a.addFile(newFile, 3);

						jTextArea1.append("successfully added"+"\n");

					}
					
				}
				
				else if (getChosenTree() == 4 ){
					
					int index =0 ; 
					File newFile = null ; 

					for(File i : file.listFiles()){

						if(i.getName().compareTo(s[1]) == 0 ){
							
							newFile = i ; 
							index = 1;
							
						}

					}

					if(index == 0 ){

						jTextArea1.append(" document not found " + "\n");

					}

					else{

						Files a = new Files(HASHTABLE);
						a.addFile(newFile, 4);

						jTextArea1.append("successfully added"+"\n");

					}

					
					
					
				}

			}

			/**************************************************************************/


			if(s[0].compareTo("del") == 0 ){


				if(getChosenTree() ==1 ){ //BST

					int index =0 ; 

					File newFile = null ; 
					for(File i : file.listFiles()){

						if(i.getName().compareTo(s[1]) == 0 ){

							newFile = i ; 
							index = 1;

						}

					}

					if(index == 0 ){

						jTextArea1.append(" document not found " + "\n");

					}

					else{

						Files a = new Files(BST);

						
						a.deleteFile(newFile, 1);
						jTextArea1.append("successfully deleted"+"\n");

					}
				}

				else if(getChosenTree() ==2 ){ //TST

					int index =0 ; 

					File newFile = null ; 
					for(File i : file.listFiles()){

						if(i.getName().compareTo(s[1]) == 0 ){

							newFile = i ; 
							index = 1;

						}

					}

					if(index == 0 ){

						jTextArea1.append(" document not found " + "\n");

					}

					else{


						Files a = new Files(TST);
						a.deleteFile(newFile, 2);

						jTextArea1.append("successfully deleted"+"\n");

					}
				}

				else if(getChosenTree() == 3  ){ //TRIE

					int index =0 ; 

					File newFile = null ; 
					for(File i : file.listFiles()){

						if(i.getName().compareTo(s[1]) == 0 ){

							newFile = i ; 
							index = 1;

						}

					}

					if(index == 0 ){

						jTextArea1.append(" document not found " + "\n");

					}

					else{


						Files a = new Files(TRIE);
						
						a.deleteFile(newFile, 3);
						jTextArea1.append("successfully deleted"+"\n");

					}
				}
				
				else if (getChosenTree() == 4 ){ // hashTable 
					
					int index =0 ; 

					File newFile = null ; 
					for(File i : file.listFiles()){

						if(i.getName().compareTo(s[1]) == 0 ){

							newFile = i ; 
							index = 1;

						}

					}

					if(index == 0 ){

						jTextArea1.append(" document not found " + "\n");

					}

					else{


						Files a = new Files(HASHTABLE);
						
						a.deleteFile(newFile, 4);
						jTextArea1.append("successfully deleted"+"\n");
						
					}
					
					
				}
				
				


			}
			
			
			
			/**************************************************************************/


			if(s[0].compareTo("search") == 0  && s[1].compareTo("-w") == 0 ){
				
				if(getChosenTree() ==1 ){ //BST

					if(BST.search_word(s[2])){

						ArrayList<String> x =  BST.getSearchedFiles();
						for(int i =0 ; i<  x.size() ; i++ )

							jTextArea1.append(x.get(i)+"\n");
						
						

					}

					else 
						jTextArea1.append("not found"+"\n");

					BST.setSearchedFiles(new ArrayList<>());
				}
				
				else if (getChosenTree() ==2 ){ // TST
					

					
					if(TST.search_word(s[2])){

						ArrayList<String> x =  TST.getSearchedFiles();
						for(int i =0 ; i<  x.size() ; i++ )

							jTextArea1.append(x.get(i)+"\n");
						

					}

					else{
						
						jTextArea1.append("not found"+"\n");
						
						
					}	
					
					TST.setSearchedFiles(new ArrayList<>());
					
					
				}

				else if (getChosenTree() ==3 ){ // Trie
					
					if(TRIE.search_word(s[2])){
						
						
						ArrayList<String> x =  TRIE.getSearchedFiles();
						
						for(int i =0 ; i<  x.size() ; i++ )

							jTextArea1.append(x.get(i)+"\n");

					}
					
					else
						
						jTextArea1.append("not found"+"\n");

					TRIE.setSearchedFiles(new ArrayList<>());
						
					
					
				}
				
				else if (getChosenTree() == 4 ){ // hashTable
					
					if(HASHTABLE.search_word(s[2])){


						ArrayList<String> x =  HASHTABLE.getSearchedFiles();

						for(int i =0 ; i<  x.size() ; i++ )

							jTextArea1.append(x.get(i)+"\n");

					}

					else

						jTextArea1.append("not found"+"\n");

					HASHTABLE.setSearchedFiles(new ArrayList<>());



					
					
					
				}

			}


			/****************************************************************************/

			if(s[0].compareTo("search") == 0  && s[1].compareTo("-s") == 0){


				if(getChosenTree() == 1){ //BST

					ArrayList<String> result = new ArrayList<>();

					BST.search_word(s[2]);

					for( int i =0 ; i< BST.getSearchedFiles().size() ; i++){

						result.add(BST.getSearchedFiles().get(i));

					}




					for( int i = 3 ; i< s.length ; i++){

						if(BST.search_word(s[i])){

							ArrayList<String> temp = BST.getSearchedFiles();

							for(int j =0 ; j< result.size() ; j++){

								if(!temp.contains(result.get(i)))

									result.remove(i);

							}


						}


					}

					for( int i =0 ; i<result.size() ; i++){
						jTextArea1.append(result.get(i)+"\n");

					}
					
					BST.setSearchedFiles(new ArrayList<>());

				}
				
				/*********************************/
				
				
				else if (getChosenTree() ==2 ){ //TST
					

					ArrayList<String> result = new ArrayList<>();

					TST.search_word(s[2]);

					for( int i =0 ; i< TST.getSearchedFiles().size() ; i++){

						result.add(TST.getSearchedFiles().get(i));

					}




					for( int i = 3 ; i< s.length ; i++){

						if(TST.search_word(s[i])){

							ArrayList<String> temp = TST.getSearchedFiles();

							for(int j =0 ; j< result.size() ; j++){

								if(!temp.contains(result.get(i)))

									result.remove(i);

							}


						}


					}

					for( int i =0 ; i<result.size() ; i++){
						jTextArea1.append(result.get(i)+"\n");

					}

					
					TST.setSearchedFiles(new ArrayList<>());
					
				}
				
				/*********************************/
				
				else if (getChosenTree() == 3 ){ // Trie
					ArrayList<String> result = new ArrayList<>();

					TRIE.search_word(s[2]);

					for( int i =0 ; i< TRIE.getSearchedFiles().size() ; i++){

						result.add(TRIE.getSearchedFiles().get(i));

					}




					for( int i = 3 ; i< s.length ; i++){

						if(TRIE.search_word(s[i])){

							ArrayList<String> temp = TRIE.getSearchedFiles();

							for(int j =0 ; j< result.size() ; j++){

								if(!temp.contains(result.get(i)))

									result.remove(i);

							}


						}


					}

					for( int i =0 ; i<result.size() ; i++){
						jTextArea1.append(result.get(i)+"\n");

					}
					
					TRIE.setSearchedFiles(new ArrayList<>());

					
				}
				
				else if ( getChosenTree() == 4 ){ // hashTable
					
					ArrayList<String> result = new ArrayList<>();

					HASHTABLE.search_word(s[2]);

					for( int i =0 ; i< HASHTABLE.getSearchedFiles().size() ; i++){

						result.add(HASHTABLE.getSearchedFiles().get(i));

					}




					for( int i = 3 ; i< s.length ; i++){

						if(HASHTABLE.search_word(s[i])){

							ArrayList<String> temp = HASHTABLE.getSearchedFiles();

							for(int j =0 ; j< result.size() ; j++){

								if(!temp.contains(result.get(i)))

									result.remove(i);

							}


						}


					}

					for( int i =0 ; i<result.size() ; i++){
						jTextArea1.append(result.get(i)+"\n");

					}
					
					HASHTABLE.setSearchedFiles(new ArrayList<>());

					
				}









			}

			


		}





	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getKeyCode() == KeyEvent.VK_UP){
			
			if(indexOfHistory>0)
				indexOfHistory -- ;
			
			jTextArea1.setText(history.get(indexOfHistory));
			
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN){
			
			if(indexOfHistory<history.size()-1)
				indexOfHistory++;
			
			jTextArea1.setText(history.get(indexOfHistory));
			
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}





}
