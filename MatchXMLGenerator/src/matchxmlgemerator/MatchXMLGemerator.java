/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matchxmlgemerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martin
 */
public class MatchXMLGemerator {
    private static final String FILENAME = "output.txt";
    
    
    /**
     * Cuts spaces from edges of string.
     * @param item the string to be modified
     * @return item the modified string
     */
    public String cutEdges(String item){
        int index = 0;
        while(item.charAt(index) == ' ' && index < item.length()){
            index++;
        }
        item = item.substring(index, item.length());

        index = item.length();

        while(item.charAt(index-1) == ' ' && index > 0){
            index--;
        }
        item = item.substring(0, index);
        
        if(item.length() > 4){
            if(item.substring(item.length()-4,item.length()).equals(" (C)")){
                item = item.substring(0, item.length()-4);
            }
        }
        
        if(item.length() > 4){
            if(item.substring(item.length()-4,item.length()).equals(" (A)")){
                item = item.substring(0, item.length()-4);
            }
        }
        
        return item;
    }
    
    /**
     * generates list from text divided by – or - or ,
     * @param str source
     * @return list generated list
     */
    public List<String> generateList(String str){
        List<String> list = new ArrayList<>();
        int lastIndex = 0;
        
        
        for(int i=0; i < str.length(); i++){
            if(str.charAt(i)==',' || str.charAt(i)=='–' || str.charAt(i)=='-' || i == str.length()-1){
                if(i == str.length()-1) i++;
                String item = str.substring(lastIndex, i);
                
                item = cutEdges(item);
                
                list.add(item);
                lastIndex = i+1;
            }
        }
        
        return list;
    }
    
    /**
     * generates xml from list of string items of given type,
     * @param list source
     * @param type tag for item
     * @param offset offset of items in result
     * @return generated generated xml
     */
    public String getXMLFromList(List<String> list, String type, String offset){
        String generated = "";
        String beginMark = offset + "<"+type+">";
        String endMark = "</"+type+">";
        for(String item : list){
            generated += beginMark + item + endMark + "\n";
        }
        return generated;
    }
    
    /**
     * generates xml from list of string items of given type,
     * @param str filename
     * @throws java.lang.Exception
     */
    public void writeToFile(String str) throws Exception {
        BufferedWriter bw = null;
	FileWriter fw = null;
        try {
            fw = new FileWriter(FILENAME, true);
            bw = new BufferedWriter(fw);
            bw.write(str);
        } catch (IOException e) {
            //System.out.println("vypis selhal");
            throw new Exception("output failure");
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                //System.out.println("selhala manipulace se souborem");
                throw new Exception("failed to close file");
            }
        }
    }
    
    /**
     * reads string from file
     * @param filename name of the file
     * @return str
     * @throws java.lang.Exception 
     */
    public String readFromFile(String filename) throws Exception{
        String str = "";
        BufferedReader br = null;
	FileReader fr = null;

        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            String line;
            br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                    str+=line + "\n";
            }
        } catch (IOException e) {
            throw new Exception("input failure");

        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                throw new Exception("failed to close file");
            }
        }
        return str;
    }
    
    /**
     * parses match description
     * @param str match description
     * @return list list of parameters
     * @throws java.lang.Exception 
     */
    public List<String> parseMatchDescription(String str) throws Exception{
        List<String> list = new ArrayList<>();
        int j = 0;
        
        String date;
        String homeTeam;
        String awayTeam;
        String homePlayers;
        String awayPlayers;
        String goals;
        String spectators;
        String homeTeamShots;
        String awayTeamShots;
        String description;
        
        //skip day and number
        while(str.charAt(j) != ' '){
            j++;
        }
        j++;
        str = str.substring(j, str.length());
        j=0;
        while(str.charAt(j) != ' '){
            j++;
        }
        j++;
        str = str.substring(j, str.length());
        j=0;
        
        //extract date
        while(str.charAt(j) != '\n'){
            j++;
        }
        date = str.substring(0, j);
        j++;
        list.add(date);//item 0
        str = str.substring(j, str.length());
        j=0;
        
        //extract homeTeam
        while(str.charAt(j)!='–' && str.charAt(j)!='-'){
            j++;
        }
        homeTeam = cutEdges(str.substring(0, j-1));
        j++;
        list.add(homeTeam); //item 1
        str = str.substring(j, str.length());
        j=0;
        
        //extract awayTeam
        while(str.charAt(j)!='\n'){
            j++;
        }
        awayTeam = cutEdges(str.substring(0, j));
        j++;
        list.add(awayTeam); // utem 2
        str = str.substring(j, str.length());
        j=0;
        
        //skip score
        while(str.charAt(j)!='\n'){
            j++;
        }
        j++;
        str = str.substring(j, str.length());
        j=0;
        
        //check and skip homeTeam
        if(!homeTeam.equals(str.substring(0, homeTeam.length()))){
            throw new Exception("parsing error, homeTeam:" +homeTeam
                    +" not equal to team of homePlayers:" 
                    + str.substring(0, homeTeam.length()));
        }
        str = str.substring(homeTeam.length()+2, str.length());
        
        //extract homePlayers and skip coaches
        while(str.charAt(j)!=':'){
            j++;
        }
        if(str.substring(j-6,j).equals("Trenér")){
            homePlayers = str.substring(0, j-8);
            list.add(homePlayers);//item 3
        }
        else if (str.substring(j-7,j).equals("Trenéři")){
            homePlayers = str.substring(0, j-9);
            list.add(homePlayers);//item 3
        }
        else{
            throw new Exception("parsing error, coaches not found or not in standard format:"+str.substring(0, j-9));
        }
        while(str.charAt(j)!='\n'){
            j++;
        }
        j++;
        str = str.substring(j, str.length());
        j=0;
        
        //check and skip awayTeam
        if(!awayTeam.equals(str.substring(0, awayTeam.length()))){
            throw new Exception("parsing error, awayTeam:" +homeTeam
                    +" not equal to team of awayPlayers:" 
                    + str.substring(0, awayTeam.length()));
        }
        str = str.substring(awayTeam.length()+2, str.length());
        
        //extract awayPlayers and skip coaches
        while(str.charAt(j)!=':'){
            j++;
        }
        if(str.substring(j-6,j).equals("Trenér")){
            awayPlayers = str.substring(0, j-8);
            list.add(awayPlayers);//item 4
        }
        else if (str.substring(j-7,j).equals("Trenéři")){
            awayPlayers = str.substring(0, j-9);
            list.add(awayPlayers);//item 4
        }
        else{
            throw new Exception("parsing error, coaches not found or not in standard format:"+str.substring(j-7,j));
        }
        while(str.charAt(j)!='\n'){
            j++;
        }
        j++;
        str = str.substring(j, str.length());
        j=0;
        
        //check goals text format
        if(!"Branky a nahrávky:".equals(str.substring(0, "Branky a nahrávky:".length()))){
            throw new Exception("parsing error, text should be \"Branky a nahrávky:\", but is:"+str.substring(0, "Branky a nahrávky:".length()));
        }
        str = str.substring("Branky a nahrávky:".length()+1, str.length());
        
        //extract goals and skip referees
        while(str.charAt(j)!=':'){
            j++;
        }
        if(str.substring(j-8,j).equals("Rozhodčí")){
            goals = str.substring(0, j-10);
            list.add(goals);//item 5
        }
        else{
            throw new Exception("parsing error, referees not found or not in standard format:"+str.substring(j-8,j));
        }
        
        //skip garbage
        for(int i=0; i<7; i++){
            while(str.charAt(j)!=':'){
                j++;
            }
            j++;
            str = str.substring(j, str.length());
            j=0;
        }
        
        //check spectators text format
        while(str.charAt(j)!='.'){
            j++;
        }
        j++;
        str = str.substring(j+1, str.length());
        j=0;
        
        if(!"Diváci:".equals(str.substring(0, "Diváci:".length()))){
            throw new Exception("parsing error, text should be \"Diváci:\", but is:"+str.substring(0, "Diváci:".length()));
        }
        str = str.substring("Diváci:".length()+1, str.length());
        
        //check goals text format and extract spectators
        while(str.charAt(j)!='.'){
            j++;
        }
        spectators = str.substring(0, j);
        list.add(spectators);
        
        j++;
        str = str.substring(j+1, str.length());
        j=0;
        
        if(!"Střely na branku:".equals(str.substring(0, "Střely na branku:".length()))){
            throw new Exception("parsing error, text should be \"Střely na branku:\", but is:"+str.substring(0, "Střely na branku:".length()));
        }
        str = str.substring("Střely na branku:".length()+1, str.length());
        
        //extract homeTeamShots
        while(str.charAt(j)!=':'){
            j++;
        }
        homeTeamShots = str.substring(0, j);
        list.add(homeTeamShots);
        j++;
        str = str.substring(j, str.length());
        j=0;
        
        //extract awayTeamShots
        while(str.charAt(j)!='.'){
            j++;
        }
        awayTeamShots = str.substring(0, j);
        list.add(awayTeamShots);
        j++;
        str = str.substring(j+1, str.length());
        j=0;
        
        //skip garbage
        for(int i=0; i<2; i++){
            while(str.charAt(j)!='.'){
                j++;
            }
            j++;
            str = str.substring(j+1, str.length());
            j=0;
        }
        description = str;
        list.add(description);
        
        //System.out.println(list.get(0));//date
        //System.out.println(list.get(1));//homeTeam
        //System.out.println(list.get(2));//awayTeam
        //System.out.println(list.get(3));//homePlayers
        //System.out.println(list.get(4));//awayPlayers
        //System.out.println(list.get(5));//goals
        //System.out.println(list.get(6));//spectators
        //System.out.println(list.get(7));//homeTeamShots
        //System.out.println(list.get(8));//awayTeamShots
        //System.out.println(list.get(9));//description
        return list;
    } 
    
    /**
     * generates xml goals
     * @param thisPlayersList list of players of this team
     * @param otherPlayersList list of players of the other team
     * @param str source with goals
     * @param offset1 offset of goal tag
     * @param offset2 offset of goal subtag 
     * @return result the generated xml
     * @throws java.lang.Exception
     */
    public String generateTeamGoals(List<String> thisPlayersList, List<String> otherPlayersList, String str, String offset1, String offset2) throws Exception{
        List<String> list = new ArrayList<>();
        String result = "";
        int lastIndex = 0;
        boolean isInBrackets = false;
        
        for(int i=0; i < str.length(); i++){
            if(str.charAt(i)=='(') isInBrackets = true;
            if(str.charAt(i)==')') isInBrackets = false;
            if(!isInBrackets && (str.charAt(i)==',' || str.charAt(i)=='–' || str.charAt(i)=='-' || i == str.length()-1)){
                if(i == str.length()-1) i++;
                String item = str.substring(lastIndex, i);
                
                item = cutEdges(item);
                
                list.add(item);
                lastIndex = i+1;
            }
        }
       
        for(int i=0; i<list.size();i++){
            String item = "";
            String item2 = "";
            String scorer = "";
            String minute = "";
            List<String> assistList = new ArrayList<>();
            
            item += list.get(i);
            item = cutEdges(item);
            
            int j = 0;
            if(item.contains(".")){
                j = item.indexOf(".");
            }
            
            if(item.charAt(0) == '0'
             ||item.charAt(0) == '1'
             ||item.charAt(0) == '2'
             ||item.charAt(0) == '3'
             ||item.charAt(0) == '4'
             ||item.charAt(0) == '5'
             ||item.charAt(0) == '6'
             ||item.charAt(0) == '7'
             ||item.charAt(0) == '8'
             ||item.charAt(0) == '9'
            )
            {
                minute = item.substring(0, j);
            }
            
            item = item.substring(j+1, item.length());
            int checkThis = -1;
            
            if(item.contains("("))
            {
                item2 += item.substring(item.indexOf("("), item.length());
                item = item.substring(0, item.indexOf("("));
            }
            
            for(String player1 : thisPlayersList){
                if(item.contains(player1)) {
                    checkThis = 1;
                    scorer = player1;
                    
                }
                if(player1.contains(" ")){
                    if(item.contains(player1.substring(player1.indexOf(" ")+1, player1.length()))) {
                        checkThis = 1;
                        scorer = player1;
                        
                    }
                }
                if(item2.contains(player1)) {
                    assistList.add(player1);
                }
                if(player1.contains(" ") && item2.contains(player1)) {
                    if(item2.contains(player1.substring(player1.indexOf(" ")+1, player1.length()))){
                        assistList.add(player1);
                    }
                }
            }
             
            
            int checkOther = -1;
            
            for(String player2 : otherPlayersList){
                if(item.contains(player2)) {
                    checkOther = 1;
                    scorer = player2;
                    
                }
                if(player2.contains(" ")){
                    if(item.contains(player2.substring(player2.indexOf(" ")+1, player2.length()))) {
                        checkOther = 1;
                        scorer = player2;
                        
                    }
                }
                
            }
            
            if(checkThis> -1) {
                result+= 
                    offset1 + "<goal>"+"\n"
                    +offset2+"<minute>"+minute+"</minute>"+"\n"
                    +offset2+"<scorer>"+scorer+"</scorer>"+"\n";
                
                for(String player : assistList){
                    
                    result+= offset2+"<assist>"+player+"</assist>"+"\n";
                    
                }
                result+= offset1 +"</goal>"+"\n";
            }
            if(checkThis< 0 && checkOther<0){
                throw new Exception("unidentified goal:"+item+ ", minute:"+minute);
            }

            if(checkThis> 0 && checkOther > 0){
                throw new Exception("goal assigned to both teams:"+item+ ", minute:"+minute);
            }
        }
        return result;
    }
    
    /**
     * generates xml file describing a match from text file in specific format
     * @param matchNumber number of file with info about the match
     * @throws java.lang.Exception
     */
    public void generateMatch(int matchNumber) throws Exception{
        
        
        String inputBlock = readFromFile("match"+matchNumber+".txt"); 
        
        
        List<String> info = parseMatchDescription(inputBlock);
        
        List homePlayersList = generateList(info.get(3));
        String homePlayersString = getXMLFromList(homePlayersList, "player", "                ");
        
        List awayPlayersList = generateList(info.get(4));
        String awayPlayersString = getXMLFromList(awayPlayersList, "player", "                ");
        
        String homeTeamGoals = generateTeamGoals(homePlayersList, awayPlayersList, info.get(5), "                    ", "                        ");
        
        String awayTeamGoals = generateTeamGoals(awayPlayersList, homePlayersList, info.get(5), "                    ", "                        ");
        
        writeToFile(
                 "        <match>"+"\n"
                +"            <date>"+info.get(0)+"</date>"+"\n"
                +"            <homeTeam>"+info.get(1)+"</homeTeam>"+"\n"
                +"            <awayTeam>"+info.get(2)+"</awayTeam>"+"\n"
                +"            <homePlayers>" + "\n"
                +homePlayersString
                +"            </homePlayers>"+"\n"
                +"            <awayPlayers>" + "\n"
                +awayPlayersString
                +"            </awayPlayers>"+"\n"
                +"            <goals>"+"\n"
                +"                <homeTeamGoals>"+"\n"
                +homeTeamGoals
                +"                </homeTeamGoals>"+"\n"
                +"                <awayTeamGoals>"+"\n"
                +awayTeamGoals
                +"                </awayTeamGoals>"+"\n"
                +"            </goals>"+"\n"
                +"            <homeTeamShots>"+info.get(7)+"</homeTeamShots>" + "\n"
                +"            <awayTeamShots>"+info.get(8)+"</awayTeamShots>" + "\n"
                +"            <spectators>"+info.get(6)+"</spectators>" + "\n"
                +"        </match>\n"
        );
    };
    
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            //System.out.println("jsem program na upravu textu");
            MatchXMLGemerator match = new MatchXMLGemerator();
            for(int i = 1; i<=56; i++){
                match.generateMatch(i);
            }
        } catch (Exception ex) {
            System.out.println("Failure. This exception has been thrown: "+ ex);
        }
    }
    
    
    
}
