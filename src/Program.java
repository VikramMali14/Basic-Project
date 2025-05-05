public class Program {
    public static void main(String[] args) {
       int[] ar={4,4,5,5,1,4,582,4,78,51,12,6,84,21,45};

       for(int i=0;i<ar.length;++i){
           int val=ar[i];
      int count=0;
      if(val!=-1 ) {
          for (int j = i; j < ar.length; ++j) {
              if (val == ar[j]) {
                  count++;
                  ar[j] = -1;
              }
          }

          System.out.println(val + " count -> " + count);
      } }
    }
}
