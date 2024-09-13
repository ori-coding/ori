import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int i =  0; i < 3; i++) {
            bw.write(getMinumum(arr) + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int getMinumum(int[] arr) {
        int min = 1000000;
        int index = 0;
        for(int i = 0; i < arr.length; i++) {
            int elem = arr[i];
            if(elem == 0) continue;
            if(elem < min) {
                min = elem;
                index = i;
            }
        }
        arr[index] = 0;
        return min;
    }
}