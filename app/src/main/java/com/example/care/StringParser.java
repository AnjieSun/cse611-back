package com.example.care;

import java.lang.Character;

public class StringParser {
    public String convert(String input) {
        String num=" ";
        if(input.contains("blood pressure")) {
            int begin=input.indexOf("is")+3;
            num = getBloodPressure(input.substring(begin));
        }
        else if(input.contains("blood sugar")) {
            int begin=input.indexOf("is")+3;
            num = String.valueOf(getNum(input.substring(begin)));
            System.out.println(getNum(input.substring(begin)));
        }
        else if(input.contains("body temperature")) {
            int begin=input.indexOf("is")+3;
            num = String.valueOf(getNum(input.substring(begin)));
        }
        else if(input.contains("add to schedule")||input.contains("add schedule")){
            int begin = input.indexOf("schedule")+8;
            num=getTime(input.substring(begin));
        }
        if(num.equals(" "))
            return "";
        return " "+num;
    }
    public float getNum(String input) {
        Boolean point=false;
        float decCounter=(float) 0.1;
        float res=0;
        int size = input.length();
        for(int i=0;i<size;i++) {
            if(Character.isDigit(input.charAt(i))||input.charAt(i)=='.'){
                if(input.charAt(i)=='.')
                    point=true;
                else if(!point){
                    res*=10;
                    res+=(input.charAt(i)-'0');
                }
                else{
                    res+=(input.charAt(i)-'0')*decCounter;
                    decCounter/=10;
                }
            }
            else if(res!=0)
                break;
        }
        return (float) (Math.round(res*100.0)/100.0);
    }
    public String getBloodPressure(String input){
        int idx = input.indexOf(" + ");
        int upper = 0, lower=0;
        int counter=1;
        for(int i=idx-1;i>=0;i--){
            if(Character.isDigit(input.charAt(i))){
                upper+=counter*(input.charAt(i)-'0');
                counter*=10;
            }
            else
                break;
        }
        for(int i=idx+3;i<input.length();i++){
            if(Character.isDigit(input.charAt(i))){
                lower*=10;
                lower+=(input.charAt(i)-'0');
            }
            else break;
        }
        return String.valueOf(upper)+" and "+String.valueOf(lower);
    }
    public String getTime(String input){
        int index = input.indexOf(':');
        int M = input.indexOf("a.m.");
        String t = "A.M.";
        if(M==-1){
            M = input.indexOf("p.m.");
            t = "P.M.";
        }
        String hour = "";
//        if(index==-1){
//            if()
//        }
        String minute = input.substring(M+1);
        if(Character.isDigit(input.charAt(index-2))){
            hour+=input.charAt(index-2);
        }
        hour+=input.charAt(index-1);
        return hour+":"+minute;
    }
}