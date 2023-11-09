import java.util.Scanner;

public class Main{
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter the inference:");
        String inference = scan.nextLine();
        inference = inference.replaceAll(" ", "");
        String[] exps = inference.split(",");
        Expression exp1, exp2;
        if(exps.length == 1){
            exp1 = new Expression(exps[0]);
            exp2 = null;
        }else{
            exp1 = new Expression(exps[0]);
            exp2 = new Expression(exps[1]);
        }

        System.out.println("Result:");
        System.out.println(new Inference().apply(exp1, exp2).expression);

        scan.close();
    }
}