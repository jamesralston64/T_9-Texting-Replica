//James Ralston
//CSCD320 Midterm Assignment

package Trie;
import java.util.HashMap;
import java.util.Map;

public class TrieTree
{
    class TrieNode
    {

        private char firstLetter;
        HashMap<Character, TrieNode> childNode = new HashMap<>();
        boolean isLeaf;

        public TrieNode()
        {}

        public TrieNode(char firstLetter)
        {
            this.firstLetter = firstLetter;
        }//end TrieNode

    }//en TrieNode

    private TrieNode root;

    public TrieTree()
    {
        root = new TrieNode();
    }//end ctor

    public boolean search(String word)
    {
        TrieNode node = searchNode(word);

        if(node != null && node.isLeaf)
        {
            return true;
        }//end if

        else
        {
            return false;
        }//end else

    }//end search

    public TrieNode searchNode(String data)
    {
        Map<Character, TrieNode> childNode = root.childNode;
        TrieNode node = null;

        for(int i = 0; i < data.length(); i++)
        {
            char firstLetter = data.charAt(i);

            if(childNode.containsKey(firstLetter))
            {
                node = childNode.get(firstLetter);
                childNode = node.childNode;
            }//end if

            else
            {
                return null;
            }//end else

        }//end for

        return node;
    }//end searchNode

    
    public void insert(String word)
    {
        HashMap<Character, TrieNode> childNode = root.childNode;

        for(int i = 0; i < word.length(); i++)
        {
            char firstLetter = word.charAt(i);
            TrieNode node;
            
            if(childNode.containsKey(firstLetter))
            {
                node = childNode.get(firstLetter);
            }//end if

            else
            {
                node = new TrieNode(firstLetter);
                childNode.put(firstLetter, node);
            }//end else

            childNode = node.childNode;

            if(i == word.length() - 1)
            {
                node.isLeaf = true;
            }//end if

        }//end for

    }//end insert

}//end of TrieTree