class Solution {
    public int minOperations(String s) {
        int n= s.length();
        boolean[] b= new boolean[n];
        int count=0;
        char c= s.charAt(0);
        for(int i=1; i<n;i++){
            if(s.charAt(i)==c){
                if(!b[i-1]){
                    b[i]=true;
                    count+=1;
                }
            }
            else{
                if(b[i-1]){
                    b[i]=true;
                    count+=1;
                }
            }
            c= s.charAt(i);
        }
        return Math.min(count,n-count);
    }
}