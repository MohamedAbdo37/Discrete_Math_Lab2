import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // reading expression
        System.out.println("Enter expression: ");
        String input = scanner.nextLine();
        String res = new String();

        // Reading values
        String values = scanner.nextLine().replace(" ", "");// reading values string and removing spaces
        values = values.replace("=", "");// removing "=" signs
        values = values.replace("and", ",");// remplacing "and" with ","
        String[] valuesArray = values.split(",");
        // creating an expression object
        Expression expression = new Expression(input, valuesArray);

        // getting representation
        // try {
        //     res = expression.getRepresentation();
        // } catch (Exception e) {
        //     System.out.println("Wrong expression");
        //     return;
        // }
        // // parsing opperand values in the expression representation
        // try {
        //     expression.setRepresentation(res);
        // } catch (Exception e) {
        //     System.out.println("Wrong expression");
        //     return;
        // }

        LogicalExpressionSolver logicStatement = new LogicalExpressionSolver();
        // estimamting result
        boolean boolResult = true;
        try {
            boolResult = logicStatement.evaluateExpression(expression);
        } catch (Exception e) {
            System.out.println("Wrong expression");
            return;
        }
        System.out.println(boolResult);
    }
}
