import java.util.Stack;

public class LogicalExpressionSolver implements IFLogicalExpressionSolver {
    
    @Override
    public boolean evaluateExpression(Expression exp) {
        String expression = exp.getPrefex();
        Stack<Boolean> stack = new Stack<Boolean>();

        for (int i = 0; i < expression.length(); i++) {

            if (expression.charAt(i) == 't')
                stack.push(true);

            else if (expression.charAt(i) == 'f')
                stack.push(false);

            else if (expression.charAt(i) == '~')
                stack.push(!(boolean) stack.pop());

            else if (expression.charAt(i) == '^')
                stack.push((boolean) stack.pop() && (boolean) stack.pop());

            else if (expression.charAt(i) == 'v') 
                stack.push((boolean) stack.pop() || (boolean) stack.pop());
            
            else if (expression.charAt(i) == '>')
                stack.push((boolean) stack.pop() || !(boolean) stack.pop());
            
        }

        return (boolean) stack.pop();
    }
}
