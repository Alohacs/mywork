package com.gx.web.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class KeyWordUtil {
    private Map<String, Object> dictionaryMap;
 
    public KeyWordUtil(Set<String> wordSet) {
        this.dictionaryMap = handleToMap(wordSet);
    }
 
    public Map<String, Object> getDictionaryMap() {
        return dictionaryMap;
    }
 
    public void setDictionaryMap(Map<String, Object> dictionaryMap) {
        this.dictionaryMap = dictionaryMap;
    }
 
    @SuppressWarnings("unchecked")
    private Map<String, Object> handleToMap(Set<String> wordSet) {
        if (wordSet == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>(wordSet.size());
        Map<String, Object> curMap = null;
        Iterator<String> ite = wordSet.iterator();
        while (ite.hasNext()) {
            String word = ite.next();
            curMap = map;
            int len = word.length();
            for (int i = 0; i < len; i++) {
                String key = String.valueOf(word.charAt(i));
                Map<String, Object> wordMap = (Map<String, Object>) curMap
                        .get(key);
                if (wordMap == null) {
                    wordMap = new HashMap<String, Object>();
                    wordMap.put("isEnd", "0");
                    curMap.put(key, wordMap);
                    curMap = wordMap;
                }
                else {
                    curMap = wordMap;
                }
                if (i == len - 1) {
                    curMap.put("isEnd", "1");
                }
            }
        }
        return map;
    }
 
    @SuppressWarnings("unchecked")
    public int checkWord(String text, int beginIndex) {
        if (dictionaryMap == null) {
            throw new RuntimeException("字典不能为空！");
        }
        boolean isEnd = false;
        int wordLength = 0;
        Map<String, Object> curMap = dictionaryMap;
        int len = text.length();
        for (int i = beginIndex; i < len; i++) {
            String key = String.valueOf(text.charAt(i));
            curMap = (Map<String, Object>) curMap.get(key);
            if (curMap == null) {
                break;
            }
            else {
                wordLength++;
                if ("1".equals(curMap.get("isEnd"))) {
                    isEnd = true;
                }
            }
        }
        if (!isEnd) {
            wordLength = 0;
        }
        return wordLength;
    }
 
    public Set<String> getWords(String text) {
        Set<String> wordSet = new HashSet<String>();
        int len = text.length();
        for (int i = 0; i < len; i++) {
            int wordLength = checkWord(text, i);
            if (wordLength > 0) {
                String word = text.substring(i, i + wordLength);
                wordSet.add(word);
                i = i + wordLength - 1;
            }
        }
        return wordSet;
    }
 
    public static void main(String[] args) {
        Set<String> wordSet = new HashSet<String>();
        wordSet.add("叶良辰");
        wordSet.add("叶良辰的");
        wordSet.add("叶良辰的人");
        wordSet.add("叶良辰的兄弟");
        wordSet.add("良辰");
        wordSet.add("良辰不");
        wordSet.add("呵呵");
        KeyWordUtil keyWordUtil = new KeyWordUtil(wordSet);
//        Map<String, Object> map = keyWordUtil.getDictionaryMap();
//        System.out.println(map);
        String text = "不错，我就是叶良辰。你的行为实在欺人太甚，你若是感觉有实力跟我玩，良辰不介意奉陪到底。呵呵，我会让你明白，良辰从不说空话。";
        int beginIndex = 12;
        int wordLength = keyWordUtil.checkWord(text, beginIndex);
        System.out.println(wordLength);
        
        //查找一句话中的  KeyWord
        System.out.println(keyWordUtil.getWords(text));
    }
 
}
