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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mzou
 */
public class ics475_BLAST {

    String input_sequence;

    public ics475_BLAST() {

    }

    public ics475_BLAST(String item) {
        input_sequence = item;
    }

    public static void main(String[] args) {
        ics475_BLAST var = new ics475_BLAST();
        String pwd = "/Users/mzou/NetBeansProjects/ics475_BLAST/src/output.txt";
        List<ArrayList<String>> list = var.getSequences(pwd);
        ArrayList<String> sequences;
        sequences = list.get(0);
        String[] str = new String[sequences.size()];
        String filtered, line = "";
        int distance;
        String output;
        ArrayList<String> compareSequences = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) { //DO NOT DELETE
            //for(int i = 0; i < 1; i++){
            sequences = list.get(i);
            for (int a = 0; a < sequences.size() - 1; a++) {
                for (int b = a + 1; b < sequences.size(); b++) {
                    output = var.compareSequences(sequences.get(a), sequences.get(b));
                    System.out.println(output);
                }
            }

//            for(int j =0; j < sequences.size(); j++){
//                line = sequences.get(j);
//                filtered = var.filterSequences(line);
//                compareSequences.add(filtered);
//            }
//            //System.out.println(compareSequences.size());
//            for(int a = 0; a < compareSequences.size() -1; a++){
//                //System.out.println("a: "+compareSequences.get(a));
//                for(int b = a + 1; b < compareSequences.size(); b++){
//                    //System.out.println("b: " + compareSequences.get(b));
//                    distance = var.compareSequences(compareSequences.get(a), compareSequences.get(b));
//                    //System.out.println(distance);
//                    System.out.println("Comparing: "+compareSequences.get(a) + " with " + compareSequences.get(b)
//                            + ", result distance: "+ distance);
//                }
//            }
            compareSequences = new ArrayList<String>();
            System.out.println();
        }
    }

//    private Map<String, String> getType(String line){
//        char[] ca = line.toCharArray();
//        int i = 0;
//        char currentChar;
//        boolean bool = true;
//        String prefix = "";
//        String sequences = "";
//        Map<String, String> m = new HashMap<String, String>();
//        while(bool){
//            currentChar = line.charAt(i);
//            if(currentChar != ' '){
//                prefix += String.valueOf(line.charAt(i));
//            } if(currentChar == ' '){
//                for(int j = i; j < line.length(); j++){
//                    if(line.charAt(j) == ' '){
//                        j++;
//                    } else{
//                        sequences += String.valueOf(line.charAt(j));
//                    }
//                }
//                bool = false;
//            }
//        }
//        return Map<prefix, sequences>;
//    }
    private String compareSequences(String a, String b) {
        char[] ar_a;
        char[] ar_b;
        int distance = 0;
        char currentChar;
        String prefix_a = "", prefix_b = "", seq_a = "", seq_b = "";
        boolean bool = true;
        int i = 0;
        while (bool) {
            //System.out.println("looping a");
            currentChar = a.charAt(i);
            if (currentChar != ' ') {
                prefix_a += String.valueOf(a.charAt(i));
                i++;
            }
            if (currentChar == ' ') {
                for (int j = i; j < a.length(); j++) {
                    if (a.charAt(j) == ' ') {
                        j++;
                    } else {
                        seq_a += String.valueOf(a.charAt(j));
                    }
                }
                bool = false;
            }
        }
        bool = true;
        i = 0;
        while (bool) {
            //System.out.println("looping b");
            currentChar = b.charAt(i);
            if (currentChar != ' ') {
                prefix_b += String.valueOf(b.charAt(i));
                i++;
            }
            if (currentChar == ' ') {
                for (int j = i; j < b.length(); j++) {
                    if (b.charAt(j) == ' ') {
                        j++;
                    } else {
                        seq_b += String.valueOf(b.charAt(j));
                    }
                }
                bool = false;
            }
        }
        //System.out.println("loop stopped");
        ar_a = seq_a.toCharArray();
        ar_b = seq_b.toCharArray();
        //System.out.println(ar_a);
        //System.out.println(ar_b);
        if(ar_a.length != ar_b.length){
            return "Sequence length is not the same!";
        } else {
            for (int k = 0; k < ar_a.length; k++) {
                if (ar_a[k] != ar_b[k]) {
                    distance += 1;
                } else {
                    distance += 0;
                }
            }
        }
        return "Distance(" + prefix_a + ", " + prefix_b + ") = " + distance;
    }

    private String filterSequences(String line) {
        char[] ca = line.toCharArray();
        int i = 0;
        char currentChar;
        boolean bool = true;
        String returnStr = "";
        while (bool) {
            currentChar = line.charAt(i);
            if (currentChar != ' ') {
                i++;
            }
            if (currentChar == ' ') {
                for (int j = i; j < line.length(); j++) {
                    if (line.charAt(j) == ' ') {
                        j++;
                    } else {
                        returnStr += String.valueOf(line.charAt(j));
                    }
                }
                bool = false;
            }
        }
        return returnStr;
    }

    private List<ArrayList<String>> getSequences(String url) {
        BufferedReader br;
        List<ArrayList<String>> list;
        String line = "";
        ArrayList<String> str_array = new ArrayList<String>();
        char[] char_array;
        try {
            br = new BufferedReader(new FileReader(url));
            list = new ArrayList<ArrayList<String>>();
            while (br.ready()) {
                line = br.readLine();
                char_array = line.toCharArray();
                if (char_array.length > 0) {
                    if (line.contains("tr") || line.contains("sp")) {
                        str_array.add(line);
                    } else if (line.contains("*") || line.contains("x1")) {
                        list.add(str_array);
                        str_array = new ArrayList<String>();
                    }
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
