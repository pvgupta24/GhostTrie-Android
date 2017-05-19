package com.google.engedu.ghost;

import java.util.HashMap;
import java.util.HashSet;


public class TrieNode {
     HashMap<String, TrieNode> children;
    private boolean isWord;

    boolean isLeaf;
    char c;
  //  private HashSet<String> wordSet = new HashSet<String>();


    public TrieNode() {
        children = new HashMap<String, TrieNode>();
        this.c=c;
        //isWord = false;
    }

    public void add(String s) {
      //  wordSet.add(s);
        //  children.put(s,TrieNode);
    }

    public boolean isWord(String s) {
        char node = s.charAt(0);

        if (!children.containsKey(node)) {

            return false;
        } else {
            if (node == '\0') {
                return true;
            }
            else
            {


            }
        }
        return false;
    }

    public String getAnyWordStartingWith(String s) {
        return null;
    }

    public String getGoodWordStartingWith(String s) {
        return null;
    }
}
