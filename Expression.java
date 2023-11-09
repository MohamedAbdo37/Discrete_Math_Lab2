import java.util.Stack;

public class Expression implements IFExpression {

    String[] valuesArray;
    public String expression;
    private String prefex;
    // constractor
    public Expression(String representation, String[] values) {
        this.valuesArray = values;
        this.expression = representation;
        this.prefex = this.getRepresentation();
        this.setRepresentation(this.prefex);
    }

    public String getPrefex(){
        return this.prefex;
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
                this.prefex = representation.replace(replace, 't');
                // representation = this.expression;

            } else if (getValueOfOperand(this.valuesArray, representation.charAt(i)) == 0) {
                replace = representation.charAt(i);
                this.prefex = representation.replace(replace, 'f');
                // representation = this.expression;
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
