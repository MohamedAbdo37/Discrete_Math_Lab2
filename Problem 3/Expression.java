import java.util.ArrayList;
import java.util.Stack;

public class Expression implements IFExpression {

    private String[] valuesArray;
    public String expression;
    protected char[] operands;

    // constractor
    public Expression(String representation, String[] values) {
        this.valuesArray = values;
        this.expression = representation;
        this.operands = this.getOperands();
    }

    public Expression(String representation) {
        this.expression = representation;
        this.operands = this.getOperands();
        this.valuesArray = this.addValues(0);
    }

    public Expression(Expression exp, char[] ops){
        this.expression = exp.expression;

        for (int i = 0; i < ops.length; i++) 
            this.expression.replaceAll(String.valueOf(ops[i]), String.valueOf(exp.operands[i]));
        this.operands = ops;
        this.valuesArray = addValues(0);
    }
    

    public String[] addValues(int i) {
        String[] values = new String[this.operands.length];
        for (int j = 0 ; j < this.operands.length; j++) {
            values[j] = this.operands[j] + String.valueOf(((i & 1) == 1));
            i = i >> 1;
        }
        return values;
    }

    private char[] getOperands() {

        ArrayList<Character> ops = new ArrayList<>();
        for (char c : this.expression.toCharArray()) {
            switch(c){
                case '~':
                    break;
                case '^':
                    break;
                case 'v':
                    break;
                case '>':
                    break;
                case ')':
                    break;
                case '(':
                    break;
                default:
                    if(!ops.contains(c))
                        ops.add(c);
            }
        }

        char[] res = new char[ops.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = ops.get(i);
        }
        return res;
    }

    @Override
    public String getRepresentation() {

        String res = new String();
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < this.expression.length(); i++) {
            switch (this.expression.charAt(i)) {
                case '~':
                    if (stack.size() != 0) {
                        if ((char) stack.peek() == '~') {
                            res += stack.pop();
                            i--;
                            continue;
                        }
                    }
                    stack.push(this.expression.charAt(i));
                    break;

                case '>':
                    if (stack.size() != 0) {
                        if ((char) stack.peek() == '>' || (char) stack.peek() == '~') {
                            res += stack.pop();
                            i--;
                            continue;
                        }
                    }
                    stack.push(this.expression.charAt(i));
                    break;

                case '^':
                    if (stack.size() != 0) {
                        if ((char) stack.peek() == '^' || (char) stack.peek() == '~'
                                || (char) stack.peek() == '>') {
                            res += stack.pop();
                            i--;
                            continue;
                        }
                    }
                    stack.push(this.expression.charAt(i));
                    break;
        
                case 'v':
                    if (stack.size() != 0) {
                        if ((char) stack.peek() == 'v' || (char) stack.peek() == '^' || (char) stack.peek() == '~'
                                || (char) stack.peek() == '>') {
                            res += stack.pop();
                            i--;
                            continue;
                        }
                    }

                    stack.push(this.expression.charAt(i));
                    break;

                case '(':
                    stack.push(this.expression.charAt(i));
                    break;

                case ')':
                    boolean check = true;
                    while (check) {
                        if (stack.size() != 0) {
                            if ((char) stack.peek() != '(') {
                                res += stack.pop();
                                continue;
                            } else
                                stack.pop();
                                check = false;
                            } else {
                                System.out.println("Error!");
                                return null;
                            }
                        }
                        break;

                    default:
                        res += this.expression.charAt(i);
                        break;
            }
        }

        int size = stack.size();

        for (int j = 0; j < size; j++) {
            res += stack.pop();
        }

        return res;
    }

    @Override
    public void setRepresentation(String representation) {
        char replace;
        for (int i = 0; i < representation.length(); i++) {
            if (getValueOfOperand(this.valuesArray, representation.charAt(i)) == 1) {
                replace = representation.charAt(i);
                this.expression = representation.replace(replace, 't');
                representation = this.expression;

            } else if (getValueOfOperand(this.valuesArray, representation.charAt(i)) == 0) {
                replace = representation.charAt(i);
                this.expression = representation.replace(replace, 'f');
                representation = this.expression;
            }
        }
    }


    int getValueOfOperand(String[] values, char x) {
        for (int i = 0; i < values.length; i++) {
            if (x == values[i].charAt(0)) {
                if (values[i].charAt(1) == 't') {
                    return 1;
                } else if (values[i].charAt(1) == 'f')
                    return 0;
            }
        }
        return -1;
    }
    
}