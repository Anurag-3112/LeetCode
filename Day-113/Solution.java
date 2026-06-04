// import java.util.*;
// class Solution {
//     public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
//         HashMap<String, int[]> map = new HashMap<>();
//         int[] result = new int[wordsQuery.length];
//         for(int i = 0; i < wordsContainer.length; ++i){
//             int cur_len = wordsContainer[i].length();
//             for(int j = cur_len; j >= 0; --j){
//                 String s = wordsContainer[i].substring(j,cur_len);
//                 if(!map.containsKey(s)){
//                     map.put(s, new int[]{cur_len, i});
//                 }
//                 else{
//                     if(map.get(s)[0] > cur_len){
//                         map.get(s)[0] = cur_len;
//                         map.get(s)[1] = i;
//                     }
//                 }
//             }
//         } 
//         for(int i = 0; i < wordsQuery.length; ++i){  
//             int cur_len = wordsQuery[i].length();
//             for(int j = cur_len; j >= 0; --j){
//                 String s = wordsQuery[i].substring(j,cur_len);
//                 if(map.containsKey(s)){
//                     result[i] = map.get(s)[1];
//                 }
//                 else{
//                     break;
//                 }
//             }
//         }
//         return result;
//     }
// }

class Solution {

    class TrieNode {
        TrieNode[] child = new TrieNode[26];

        int len = Integer.MAX_VALUE;
        int idx = 0;
    }

    TrieNode root = new TrieNode();

    void insert(String s, int index) {

        TrieNode node = root;

        int n = s.length();

        // update root
        if (n < node.len) {
            node.len = n;
            node.idx = index;
        }

        for (int i = n - 1; i >= 0; --i) {

            int c = s.charAt(i) - 'a';

            if (node.child[c] == null) {
                node.child[c] = new TrieNode();
            }

            node = node.child[c];

            if (n < node.len) {
                node.len = n;
                node.idx = index;
            }
        }
    }

    int search(String s) {

        TrieNode node = root;

        int ans = node.idx;

        for (int i = s.length() - 1; i >= 0; --i) {

            int c = s.charAt(i) - 'a';

            if (node.child[c] == null) {
                break;
            }

            node = node.child[c];

            ans = node.idx;
        }

        return ans;
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {

        for (int i = 0; i < wordsContainer.length; ++i) {
            insert(wordsContainer[i], i);
        }

        int[] res = new int[wordsQuery.length];

        for (int i = 0; i < wordsQuery.length; ++i) {
            res[i] = search(wordsQuery[i]);
        }

        return res;
    }
}