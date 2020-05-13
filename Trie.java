// TRIE IS ALSO KNOWN AS PREFIX TREE

import java.util.*;

// A node of trie
class TrieNode{
    
     // A hashmap to store the characters and address of its next trie node
    HashMap<Character,TrieNode> chars;
    
     // a boolean flag to indicate the end of the word
    boolean endOfWord;
    
    // creating a new TrieNode
    TrieNode(){
        this.chars = new HashMap<>();
        this.endOfWord = false;
    }
}

public class Trie {
    
    // the root node of trie
    TrieNode root = new TrieNode();
    
    // function to insert a word into Trie
    // Time Complexity - O(n) , Space Complexity - O(n)
    public void insertWord(String word){
        
        // check if word is empty
        if(word.isEmpty()){
            System.out.println("Not able to insert into Trie, Word can't be empty");
            return;
        }
        
        // start from the root node of Trie
        TrieNode curr = root;
        
        // Check for each character, if it is present in current node  
        //if yes, go to next node
        // else make a new node, insert the current char into current node and point current node to nextNode
        // if last character is encountered, make endOfWord of nextNode as true
        for(int i=0;i<word.length();i++){
            Character ch = word.charAt(i);
            if(curr.chars.containsKey(ch)){
                // move current to next node
                curr = curr.chars.get(ch);
            }
            else{
                TrieNode nextNode = new TrieNode();
                curr.chars.put(ch,nextNode);
                curr = nextNode;
            }
            
            if(i==word.length()-1){
                curr.endOfWord = true;
            }
        }
        System.out.println("Successfully inserted: "+word+" in Trie");
    }
    
    
    // function to search a word in a trie
    // Time Complexity - 0(n),  Space Complexity - O(1)
    // Start with root node.
    // If the current char is in  current node, move to next node
    // if the current char is not in current node, return not found
    // if last char is reahced, check if the next node has endOfWord marked as true
    // If yes, return found, else return not found
    public void searchWord(String word){
        if(word.isEmpty()){
            System.out.println("Not able to search into Trie, Word can't be empty");
            return;
        }
        TrieNode curr = root;
        for(int i=0;i<word.length();i++){
            Character ch = word.charAt(i);
            // check if current node has current char
            if(!curr.chars.containsKey(ch)){
                System.out.println("Given word: "+word+" NOT FOUND in Trie");
                return;
            }
            else{
                // move to next node
                curr = curr.chars.get(ch);
            }
            
            // If last character node is reached
            if(i==word.length()-1 && curr.endOfWord==true){
                System.out.println("Given word: "+word+" FOUND in trie");
            }
            else if(i==word.length()-1 && curr.endOfWord==false){
                System.out.println("Given word: "+word+" NOT FOUND in Trie");
            }
        }
    }
    
    
    // function to delete a word from a Trie
    // Time Complexity - O(n) , Space Complexity - O(n)
    public void deleteWord(String word){
        if(word.isEmpty()){
            System.out.println("Not able to delete from Trie, Word can't be empty");
            return;
        }
        // set current node to root node
        TrieNode curr = root;
        
        // call overloaded method to delete as we will be using recursion to delete a word.
        // Reason to use recursion is that, in a Trie we always delete a word starting from bottom to up
        // Reason for bottom-up deletion is that a node of trie may be used for storing some other string too.
        // For example - bcde, bcdef and we want to delete bcde. But we also have nodes b,c,d,e also used to store string bcdef
        
        // An alternate to reursion here can be using stack explicitly and add nodes one by one from top to botton. 
        // Then pop stack till it is empty to ensure bottom-up deletion.
        
        //Recursion does this by using internal stack and it is easy to implement here . so I am going with recursive approach to delete a word.
        
        // First I will move to the endOfWord node recursively.
        // Remember, for each step we have to check if deleting this node will cause any problem ?
        // How to know if deleting a TrieNode will cause a problem ? 
        
        // 1. for endOfWord node of word to delete, if the node has more than 0 character, we can't delete that node.
        // 2. for other nodes except endOfWord of word to delete, we check if the current node has no. of characters gretaer than 1 (more chars including current char)
        //                                          if yes, we can't delete this node. We just remove this char from current node.
        // 3. also, for other nodes except endOfWord , we check if the currentnode's endOfWord is true. If yes, we can't delete this node.
        
        // KNOWING WHEN TO STOP DELETING IS VERY IMPORTANT.
        // So when to stop deleting ? 
        // If we find our first node which can't be deleted while moving bottom-up in recusrion, WE WILL STOP and then we will return curr node to subsequent recursive calls
        
        curr = deleteWord(curr,0,word);
        
        // if the root becomes null after deletion , we make a empty TrieNode and set it as new root.
        if(curr==null){
            root = new TrieNode();
        }
        
    }
    
    // Overloaded method to delete a word. This method is a recursive method.
    // After successfull completion it returns root node. 
    // If root node also gets deleted , it returns null
    
    // we will be passing current node , index -  to indicate current index of word and word to delete as parameters
    private TrieNode deleteWord(TrieNode curr, int index, String word){
        // this means when end of word node is reached
        if(index==word.length()){
            
            // if endOfWord node is reached and we get endOfWord as false, 
            // this means the given word is not present in Trie
            if(curr.endOfWord==false){
               System.out.println("Given word: "+word+" is not present in Trie, so can't delete it");
                return curr; 
            }
            
            // If word is present in trie
            else if(curr.endOfWord==true){
                // first we check if the endOfWord has 0 charcaters. That means safe to delete. 
                // If yes we return null its preceding recursive call.
                if(curr.chars.size()==0){
                    return null;
                }
                
                // if endOfWord has more than 0 chars, node is not safe to delete.
                // so we just mark endOfWord flag as false and return curr node which is endOfWord node to its preceding recursive call
                else{
                    curr.endOfWord = false;
                    return curr;
                }
            }
            
        }
        
        // IF endOfWord node is not reached yet
        
        // find the curr char
        Character ch = word.charAt(index); 
        
        // find the nextNode
        TrieNode nextNode = curr.chars.get(ch); 
        
        // if the nextnode is null and remember this is the case of not reaching endOfWord node yet.
        // hence this is the case where given word is not present in Trie
        if(nextNode==null){
            System.out.println("Given word: "+word+" is not present in Trie, so can't delete it");
            return curr; 
        }
        
        // Recursively moving to next node if nextnode is present
        nextNode = deleteWord(nextNode, index+1, word);
        
        // Updating the nextnode in currentnode.
        // the new nextNode's value will be coming from previous recursive call.
        curr.chars.put(ch,nextNode);
        
        
        // Remember the case where we talked about knowing when to stop deleting.
        // Here it is. If we don't get null value of nextNode from previous recursive call, that menas
        // the nextNode is not deleted, so we return currentnode as it is.
        
        // Receiving null in nextNode means, the nextNode was safe to delete and hence was deleted
        if(nextNode!=null){
            return curr;
        }
        
        // if we get nextNode as null, so iindicates nextNode was safe to delete. 
        // good, then let's check current node now. Because it is the nextNode of some node. Right?
        
        // if we find more than 1 character in current node or 
        // if we find currentnode's endOfWord flag as true, it means current node is being used and it is not safe to delete
        
        // we just remove the current char from current node and return currentnode as it is to the recursive function
        if(curr.chars.size()>1 || curr.endOfWord==true){
            curr.chars.remove(ch);
            return curr;
        }
        
        // when currentnode do not contains any other character except the character to delete and it's endOfWord is false,
        // it means it is not being used by any other word and it is safe to delete, so we just return null to the recursive call.
        else if(curr.chars.size()==1 && curr.endOfWord==false){
            return null;
        }
        
        else return curr;
        
    }
    

    public static void main(String[] args) throws Exception {
        Trie trie = new Trie();
        trie.insertWord("bcde");
        trie.insertWord("bckg");
        trie.insertWord("bcd");
        trie.insertWord("bcdef");
        trie.insertWord("bc");
        System.out.println();

        trie.searchWord("bc");
        trie.deleteWord("bc");
        trie.searchWord("bc");
        System.out.println();

        trie.searchWord("bcde");
        trie.deleteWord("bcde");
        trie.searchWord("bcde");
        System.out.println();

        trie.searchWord("bcd");
        System.out.println();
  
        trie.searchWord("bcdef");
        trie.deleteWord("bcdef");
        trie.searchWord("bcdef");
        System.out.println();
  
        trie.searchWord("bcd");
        trie.deleteWord("bcd");
        trie.searchWord("bcd");
        System.out.println();
  
        trie.searchWord("bckg");
        trie.deleteWord("bckg");
        trie.searchWord("bckg");
        System.out.println();
  
        trie.searchWord("b");
        trie.insertWord("b");
        trie.searchWord("b");
        trie.searchWord("bc");
        trie.insertWord("bc");
        trie.searchWord("bc");
        trie.searchWord("b");
        System.out.println();
        
        trie.searchWord("batman");
        trie.insertWord("batman");
        trie.searchWord("batman");
    
    }
}

/*============================= OUTPUT ================================

Successfully inserted: bcde in Trie
Successfully inserted: bckg in Trie
Successfully inserted: bcd in Trie
Successfully inserted: bcdef in Trie
Successfully inserted: bc in Trie

Given word: bc FOUND in trie
Given word: bc NOT FOUND in Trie

Given word: bcde FOUND in trie
Given word: bcde NOT FOUND in Trie

Given word: bcd FOUND in trie

Given word: bcdef FOUND in trie
Given word: bcdef NOT FOUND in Trie

Given word: bcd FOUND in trie
Given word: bcd NOT FOUND in Trie

Given word: bckg FOUND in trie
Given word: bckg NOT FOUND in Trie

Given word: b NOT FOUND in Trie
Successfully inserted: b in Trie
Given word: b FOUND in trie
Given word: bc NOT FOUND in Trie
Successfully inserted: bc in Trie
Given word: bc FOUND in trie
Given word: b FOUND in trie

Given word: batman NOT FOUND in Trie
Successfully inserted: batman in Trie
Given word: batman FOUND in trie

================================================================================*/
