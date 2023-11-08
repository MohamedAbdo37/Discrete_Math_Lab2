import java.util.Stack;

public class LogicalExpressionSolver implements IFLogicalExpressionSolver {

    @Override
    public boolean evaluateExpression(Expression exp) {

        Stack<Boolean> stack = new Stack<Boolean>();

        for (int i = 0; i < exp.expression.length(); i++) {

            if (exp.expression.charAt(i) == 't')
                stack.push(true);

            else if (exp.expression.charAt(i) == 'f')
                stack.push(false);

            else if (exp.expression.charAt(i) == '~')
                stack.push(!(boolean) stack.pop());

            else if (exp.expression.charAt(i) == '^')
                stack.push((boolean) stack.pop() && (boolean) stack.pop());

            else if (exp.expression.charAt(i) == 'v') 
                stack.push((boolean) stack.pop() || (boolean) stack.pop());
            
            else if (exp.expression.charAt(i) == '>')
                stack.push((boolean) stack.pop() || !(boolean) stack.pop());
            
        }

        return (boolean) stack.pop();
    }
}
