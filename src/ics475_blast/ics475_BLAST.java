/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ics475_blast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mzou
 */
public class ics475_BLAST {
    String input_sequence;
    
    public ics475_BLAST(){
        
    }

    public ics475_BLAST(String item){
        input_sequence = item;
    }
    
    public static void main(String[] args) {
       
        ics475_BLAST var = new ics475_BLAST();
        String pwd = "/Users/mzou/NetBeansProjects/ics475_BLAST/src/output.txt";
        List<ArrayList<String>> list = var.getSequences(pwd);
        System.out.println(list.size());
        ArrayList<String> sequences;
        for(int i = 0; i < list.size(); i++){
            sequences = list.get(i);
            for(int j =0; j < sequences.size(); j++){
                System.out.println(sequences.get(j));
            }
            System.out.println();
        }
    }
    
    //change return type
    private List<ArrayList<String>> getSequences(String url){
        BufferedReader br = null; 
        List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        String line = "";
        ArrayList<String> str_array = new ArrayList<String>();
        char[] char_array;
        try {
            br = new BufferedReader(new FileReader(url));
            while((line = br.readLine()) != null){
                char_array = null;
                char_array = line.toCharArray();
                if(line.contains("tr") || line.contains("sp")){
                    str_array.add(line);
                    for(int k = 0; k < str_array.size(); k++){
                        System.out.println(str_array.get(k));
                    }
                } else if (line.contains("*") && line.contains(":")){
                    list.add(str_array);
                    str_array.clear();
                }
               
            }
            br.close();
            return list;
        } catch (IOException e) {
            System.out.println("ERROR: File path does not exist!");
        }
        return null;
    }
  
    
}
