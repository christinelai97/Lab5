import java.io.*;

public class Song {

 public class Node {

  private Node left, right; //left and right child of tree
  private String artistName, songTitle, streamCount;
  
  //constructor for Node
  public Node(String songTitle, String artistName, String streamCount) {
   this.artistName = artistName;
   this.songTitle = songTitle;
   this.streamCount = streamCount;
  }
  
  //method converts to String
  public String toString() {
   String line;
   return line = "Title: " + songTitle + "\n" 
                     + "Artist: " + artistName + "\n" 
                     + "Stream Count: " + streamCount + "\n\n";
  }
  
  //method prints the line above
  public void printLine() {
    System.out.println(toString());
  }
 }
 

 private Node root;
 
 //constructor method
 public Song() {
  root = null;
 }
 
 //search method
 public Node search(String keyword) { 
  
  Node str = root; //
  
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
  
  if(root == null) {//if the root is null (meaning tree is empty, set root to addNode
   root = addNode;
  }
  
  else { 
   Node str = root; //begins at the root
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
 
 //prints output file when called in main method
 public void printOutput(PrintWriter output) {
   printOutput(output, root);
 }
 public void printOutput(PrintWriter output, Node localRoot) {
  if(localRoot != null) {
   printOutput(output, localRoot.left);
   
   output.println(localRoot.toString());
   printOutput(output, localRoot.right);
  } 
 } 
} 
