//Christine Lai
/**This program takes in CSV files from spotify global regional weekly and prints it out in an output file
  * 
 **/

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Lab5 {
 
 public static void main(String[] args) throws Exception {
   
  ArrayList<String> artistNames = new ArrayList<>(800);  //arraylist for artist names
  ArrayList<String> songTitles = new ArrayList<>(800); //arraylist for song titles
  ArrayList<String> streamCount = new ArrayList<>(800); //arraylist for stream counts
   
  Scanner[] SpotifyList = {
    new Scanner(new File("regional-global-weekly-2020-10-02--2020-10-09.csv"), StandardCharsets.UTF_8),
    new Scanner(new File("regional-global-weekly-2020-10-09--2020-10-16.csv"), StandardCharsets.UTF_8),
    new Scanner(new File("regional-global-weekly-2020-10-16--2020-10-23.csv"), StandardCharsets.UTF_8),
   };
  
  //enhanced for loop goes through Scanner
  for (Scanner scanner : SpotifyList) {
   String skip = scanner.nextLine(); //skips line in file
   skip = scanner.nextLine();

   //this while loop goes through each line in list
   while (scanner.hasNext()) {
    String[] fileLine = scanner.nextLine().split(","); //splits line in file by commas

    //5 accounts for position, song title, artist, streams, URL
    if (fileLine.length != 5) { //more than 5 means extra commas in title
  
     StringBuilder newTitle = new StringBuilder(); //String builder to fix songTitle

     for (int i = 1; i < fileLine.length - 3; i++)
      newTitle.append(fileLine[i]); //adds fileLine[j] to songTitle that were separated by commas

     songTitles.add(newTitle.toString());
     artistNames.add(fileLine[fileLine.length - 3]); //-3 is location of artist name 
     streamCount.add(fileLine[fileLine.length - 2]);//-2 is location of stream counts
    } 
    else { // 5 lines means no extra commas, locations can be assigned normally
     songTitles.add(fileLine[1]);
     artistNames.add(fileLine[2]);
     streamCount.add(fileLine[3]);
    }
   }
  } 
  
  //for loop searches for duplicates
  for (int i = 0; i < songTitles.size(); i++) 
   for (int j = i+1; j < songTitles.size()-1; j++) 
    if (songTitles.get(i).equals(songTitles.get(j))) {
     artistNames.remove(j); //removes duplucate fron artistNames
     songTitles.remove(j); //removes duplicate from songTitle
     
     //divides stream by two to get average
     int intStreams = (Integer.parseInt(streamCount.get(i)) + Integer.parseInt(streamCount.get(j)))/2; //converts to int to get average
     //converts back to String in order to stay compatible with arraylist type
     String newTotalStreams = String.valueOf(intStreams); 
     streamCount.set(i, newTotalStreams);
     streamCount.remove(j);
     j--;
    } 
  
  //for loop sorts the songTitles alphabelically
  for (int i = 0; i < songTitles.size(); i++) {
   for (int j = i+1; j < songTitles.size(); j++) {
    if (songTitles.get(i).toLowerCase().charAt(0) > (songTitles.get(j).toLowerCase()).charAt(0)) {
     String temp = artistNames.get(i);
     artistNames.set(i, artistNames.get(j));
     artistNames.set(j, temp);
     
     temp = songTitles.get(i);
     songTitles.set(i, songTitles.get(j));
     songTitles.set(j, temp);
     
     temp = streamCount.get(i);
     streamCount.set(i, streamCount.get(j));
     streamCount.set(j, temp);
    }
   }
  }
  
  //creates new playlist using Song class
  Song playlist = new Song();
  
  //for loop adds new node as it goes through arraylist
  for (int i = 0; i < songTitles.size(); i++) 
   playlist.add(songTitles.get(i), artistNames.get(i), streamCount.get(i));
  
  //calls printOut method from Song tree class
  PrintWriter output = new PrintWriter("ArtistsSorted-WeekOf10022020.txt");
  playlist.printOutput(output);
  output.close();
 }
}