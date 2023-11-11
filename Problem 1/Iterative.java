import java.util.Scanner;

public class Iterative {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter Set Elements: ");
        System.out.println("ex:{1,2,A,#}");
        String input = scan.nextLine();
        
        if( input.length() <= 2 ){
            System.out.println("Wrong input");
            System.exit(0);
        }

        if(input.charAt(0) != '{'){
            System.out.println("Wrong input");
            System.exit(0);
        }else{
            char[] c = input.toCharArray();
            c[0] = 0;
            input = String.valueOf(c);
        }

        if(input.charAt(input.length()-1) != '}'){
            System.out.println("Wrong input");
            System.exit(0);
        }
        else{
            char[] c = input.toCharArray();
            c[c.length-1] = 0;
            input = String.valueOf(c);
        }

        for(char c : input.toCharArray() ){
            if(c == '{'){
                System.out.println("Wrong input");
                System.exit(0);
            }
            if(c == '}'){
                System.out.println("Wrong input");
                System.exit(0);
            }
        }

        String[] ele = input.replace(" ", "").split(",");
        
        System.out.print("Power function = \n{ ");
        System.out.println(Power(ele) + " }");
        scan.close();

    }

    private static String Power(String[] set) {
        
        if(set.length == 0 )
            return "{ }" ;

        if(set.length == 1 & set[0] == "")
            return "{ }" ;
            
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

