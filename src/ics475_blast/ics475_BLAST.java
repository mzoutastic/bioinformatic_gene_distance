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

    public static void main(String[] args) {
        ics475_BLAST var = new ics475_BLAST();
        String pwd = "/Users/mzou/NetBeansProjects/ics475_BLAST/src/ics475_a2.txt";
        List<ArrayList<String>> list = var.getSequences(pwd);
        int countLines = var.getSequencesCount(pwd);
        //System.out.println(countLines);
        ArrayList<String> sequences;
        sequences = list.get(0);
        String[] str = new String[sequences.size()];
        String filtered, line = "";
        int distance = -1;
        String output;
        String ignore = "";
        ArrayList<String> as = new ArrayList<String>();
        String tempp = "";
        char[] cr;
        ArrayList<String> results = new ArrayList<String>();
        ArrayList<String> compareSequences = new ArrayList<String>();
        List<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
        String[] full = new String[countLines];
        sequences = list.get(0);
        for(int j = 0; j < countLines; j++){
            full[j]=sequences.get(j);
        }
        
        for(int i = 1; i < list.size() ;i++){
            sequences = list.get(i);
            for(int j = 0; j < sequences.size() ; j++){
                full[j] += var.filterSequences(sequences.get(j));
            }
        }
        
        for(int j = 0; j < countLines ; j++){
            //System.out.println(full[j]);
        }
        
        for(int i = 0; i < full.length -1; i++){
            for(int j = i + 1; j < full.length; j++){
                output = var.compareSequences(full[i], full[j]);
                System.out.println(output);
                results.add(output);
            }
        }
        //var.fixUp(arr);
        var.answers(results);
        
//        for (int i = 0; i < list.size(); i++) { 
//            sequences = list.get(i);
//            for (int a = 0; a < sequences.size() - 1; a++) {
//                for (int b = a + 1; b < sequences.size(); b++) {
//                    output = var.compareSequences(sequences.get(a), sequences.get(b));
//                    results.add(output);
//                    //System.out.println(output);
//                }
//                arr.add(results);
//            }
//            compareSequences = new ArrayList<String>();
//            //System.out.println();
//        }
        //var.fixUp(arr);
//        var.answers(results);
    }
    
    private void fixUp(List<ArrayList<String>> arr){
        //System.out.println(result.size());
        //System.out.println(arr.get(0).size());
        
    }
    
    private void answers(ArrayList<String> al){
        boolean bool;
        String temp;
        char[] ca;
        String result_str = "";
        ArrayList<Integer> list = new ArrayList<Integer>();
        int distance = 0;
        int smallest_distance;
        String ignore = "";
        for(int i = 0; i < al.size(); i++){
            temp = al.get(i);
            ca = temp.toCharArray();
            bool = true;
            while(bool){
                for(int j = 0; j < ca.length; j++){
                    if(ca[j] != '='){
                        ignore += ca[j];
                    } else{
                        for(int k = j; k < ca.length; k++){
                            if(ca[k] == ' ' || ca[k] == '='){
                                ignore += ca[k];
                            } else{
                                result_str += ca[k];
                                distance = Integer.parseInt(result_str);
                            }
                        }
                        list.add(distance);
                        result_str = "";
                        bool = false;
                    }
                }
            } 
        }
        smallest_distance = list.get(0);
        for(int i = 1; i < list.size() - 1; i++){
            if(list.get(i) < smallest_distance){
                smallest_distance = list.get(i);
            }
        }
        ArrayList<String> filtered = new ArrayList<String>();
        ArrayList<Integer> human_distance2 = new ArrayList<Integer>();
        int human_distance = -1;
        int human_smallest_distance = -1;
        int index;
        String temp3;
        String[] organism = new String[2];
        int temp4 = 0;
        char[] ca2;
        char[] ca3;
        for(int i = 0; i < al.size(); i++){
            if(al.get(i).toLowerCase().contains("human")){
                filtered.add(al.get(i));
            }
        }
        for(int i = 0; i < filtered.size(); i++){
            temp = filtered.get(i);
            ca = temp.toCharArray();
            bool = true;
            while(bool){
                for(int j = 0; j < ca.length; j++){
                    if(ca[j] != '='){
                        ignore += ca[j];
                    } else{
                        for(int k = j; k < ca.length; k++){
                            if(ca[k] == ' ' || ca[k] == '='){
                                ignore += ca[k];
                            } else{
                                result_str += ca[k];
                                human_distance = Integer.parseInt(result_str);
                            }
                        }
                        human_distance2.add(human_distance);
                        result_str = "";
                        bool = false;
                    }
                }
            } 
        }
        human_smallest_distance = human_distance2.get(0);
        index = 0;
        for(int i = 1; i < human_distance2.size() - 1; i++){
            if(human_distance2.get(i) < human_smallest_distance){
                human_smallest_distance = human_distance2.get(i);
                index = i;
            }
        }
        temp3 = filtered.get(index);
        organism[0] = "";
        organism[1] = "";
        ca2 = temp3.toCharArray();
        for(int i = 0; i < ca2.length; i++){
            if(ca2[i] != '_'){
                ignore += ca2[i];
            } else {
                for(int j = i + 1; j < ca2.length; j++){
                    if(ca2[j] != ','){
                        organism[temp4] += ca2[j];
                    } else {
                        temp4 += 1;
                        break;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        String output_org = "";
        sb.append(organism[1]);
        if(organism[1].contains(")")){
            ca3 = organism[1].toCharArray();
            for(int i = organism[1].indexOf(")"); i < ca3.length; i++){
                sb.deleteCharAt(organism[1].indexOf(")"));
            }
            organism[1] = sb.toString();
        }
        if(!organism[0].toLowerCase().contains("human")){
            output_org = organism[0];
        } 
         if(!organism[1].toLowerCase().contains("human")){
            output_org = organism[1];
        } 
        System.out.println("Smallest Distancse: "+smallest_distance);
        System.out.println("Closest to Human is " + output_org);
    }
    
    private String compareSequences(String a, String b) {
        char[] ar_a;
        char[] ar_b;
        int distance = 0;
        char currentChar;
        String prefix_a = "", prefix_b = "", seq_a = "", seq_b = "";
        boolean bool = true;
        int i = 0;
        while (bool) {
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
        ar_a = seq_a.toCharArray();
        ar_b = seq_b.toCharArray();
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
            list.add(str_array);
            br.close();
            return list;
        } catch (IOException e) {
            System.out.println("ERROR: File path does not exist!");
        }
        return null;
    }
    
    private int getSequencesCount(String url) {
        BufferedReader br;
        List<ArrayList<String>> list;
        String line = "";
        ArrayList<String> str_array = new ArrayList<String>();
        char[] char_array;
        boolean bool = true;
        int count = 0;
        try {
            br = new BufferedReader(new FileReader(url));
            list = new ArrayList<ArrayList<String>>();
            while (bool) {
                line = br.readLine();
                if(!line.contains("*")){
                    count++;
                } else{
                    bool = false;
                }
            }
            return count-3;
        } catch (IOException e) {
            System.out.println("ERROR: File path does not exist!");
        }
        return -1;
    }
    
}
