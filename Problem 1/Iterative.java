import java.util.Scanner;

public class Iterative {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter Set Elements: ");
        System.out.println("ex:{1,2,A,#}");
        String[] ele = scan.nextLine().replace( "{", "").replace( "}", "").split(",");
        
        System.out.print("Power function = \n{ ");
        System.out.println(Power(ele) + " }");

        scan.close();

    }

    private static String Power(String[] set) {
        String result = "";
        for(int i : sequnce(set.length)){
            result += "{";
            for(int j : indexes(i))
                result += set[j] + ", ";
            if(indexes(i).length != 0)
                result += "\b\b}, ";
            else
                result += " }, ";
        }
        result += "\b\b" ;
        return result;
    }

    private static int[] indexes(int i) {
        int[] res = new int[getOnes(i)];
        int j = 0, v = 0;
        while (i > 0) {
            if((i & 1) == 1)
                res[j++] = v; 
            v ++ ;
            i = i >> 1;
        }
        
        return res;
    }

    private static int getOnes(int f) {
        int n = 0;
        while (f > 0) {
            if((f & 1) == 1) n++;
            f = f >> 1;
        }
        return n;
    }

    private static int[] sequnce(int l){
        int[] seq = new int[(int) Math.pow(2, l)];
        for(int i  = 0 ; i < seq.length;i++)
            seq[i] = i;
        return seq;
    }

}

