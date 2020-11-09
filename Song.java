import java.io.*;

public class Song {

 public class Node {

  private Node left; //left child of tree
  private Node right; //right child off tree
  private String artistName;
  private String songTitle;
  private String streamCount;
  

  
  //constructor for Node
  public Node(String songTitle, String artistName, String streamCount) {
   this.artistName = artistName;
   this.songTitle = songTitle;
   this.streamCount = streamCount;
  }
  
  //method converts to String
  public String toString() {
   String line = "Title: " + songTitle + "\n" 
                     + "Artist: " + artistName + "\n" 
                     + "Stream Count: " + streamCount + "\n\n";
   return line;
  }
  
  //method prints the line above
  public void printLine() {
    System.out.println(toString());
  }
 }
 

 private Node songRoot;
 
 //constructor method
 public Song() {
  songRoot = null;
 }
 
 //search method
 public Node search(String keyword) { 
  
  Node str = songRoot; //
  
  //loop searches for keyword match
  while(str.songTitle != keyword) { 
    if(keyword.toLowerCase().charAt(0) < str.songTitle.toLowerCase().charAt(0)){ 
    str = str.left; //smaller is redirected to left node
    }
    else {
      str = str.right; //greater is redirected to right node
    }
    if(str == null) { 
    return null; //when no match found, null is returned
    }
  }
  return str; //returns str when the keyword matches
 }
 
 //add method
 public void add(String songTitle, String artistName, String streamCount) {
  Node addNode = new Node(songTitle, artistName, streamCount); //instantiate a new node
  
  if(songRoot == null) {//if the songRoot is null (meaning tree is empty, set songRoot to addNode
   songRoot = addNode;
  }
  
  else { 
   Node str = songRoot; //begins at the root
   Node parent;
   
   while(true) { 
    parent = str;
    if(songTitle.toLowerCase().charAt(0) < str.songTitle.toLowerCase().charAt(0)) { //compares which is smaller
     str = str.left; //if smaller, goes to the left
     if(str == null) { 
      parent.left = addNode; //if null, adds to the left
      return;
     }
    }
    
    else { 
     str = str.right; //if bigger, goes to right
     
     if(str == null) { 
      parent.right = addNode; //if null, adds to right
      return;
     }
    }
   } 
  } 
 } 
 
 public void displayTree(PrintWriter output) {
   displayTree(output, songRoot);
 }
 
 //this displayTree method is called by the one before it, it takes the songRoot as an argument, then does inorder traversal to recursively
 //traverse the entire tree in ascending alphabetical order, every time it visits a node it calls its toString method to print to a 
 //printwriter
 public void displayTree(PrintWriter output, Node localRoot) {
  if(localRoot != null) {
   displayTree(output, localRoot.left);
   
   output.println(localRoot.toString());
   displayTree(output, localRoot.right);
  } 
 } 
} 