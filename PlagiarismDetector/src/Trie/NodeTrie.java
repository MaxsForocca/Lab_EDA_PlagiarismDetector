
package Trie;

public class NodeTrie {
    private NodeTrie[] children;
    private boolean isWord;

    public NodeTrie(){
        children = new NodeTrie[26];
        isWord = false;
    }

    public NodeTrie[] getChildren(){
        return children;
    } 

    public boolean isAWord(){
        return isWord;
    }
    
    public void setIsWord(boolean isWord){
        this.isWord = isWord;
    }

    public NodeTrie getChild(char c){
        return children[c - 'a'];
    }
    
    public void setChild(char c, NodeTrie child){
        this.children[c - 'a'] = child;
    }
}
