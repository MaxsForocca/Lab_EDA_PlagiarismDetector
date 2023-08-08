
package Trie;

public class Trie {
    private NodeTrie root;

    public Trie(){
        root = new NodeTrie();
    }

    public void insert(String word){
        NodeTrie aux = root;
        for(char c : word.toCharArray() ){
            if(aux.getChild(c) == null){
                aux.setChild(c, new NodeTrie());
            }
            aux = aux.getChild(c);
        }
        aux.setIsWord(true);
    }

    public boolean search(String word){
        NodeTrie aux = root;
        // recorrer segun los caracteres de word
        for(char c : word.toCharArray()){
            aux = aux.getChild(c);
            if(aux == null)
                return false;
        }
        // si no una palabras
        return aux.isAWord();
    }
}
