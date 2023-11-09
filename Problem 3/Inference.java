import java.util.ArrayList;

public class Inference implements IFInferenceEngine, IFInferenceRule {

    ArrayList<Expression> exps = new ArrayList<>() ;
    ArrayList<Rule> rules = new ArrayList<>() ;

    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        LogicalExpressionSolver logic = new LogicalExpressionSolver();
        if ( exp1.operands.length != exp2.operands.length) return false;
        for(int i = 0 ; i < exp1.operands.length; i++){
            exp1.addValues(i);
            exp2.addValues(i);
            if(logic.evaluateExpression(exp1) != logic.evaluateExpression(exp2))
                return false;
        }
        
        if(logic.evaluateExpression(exp1) != logic.evaluateExpression(exp2))
            return false;
        return true;
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2) {
        new Rule();
        boolean match = false;
        Expression r = new Expression("P");
        for (int i = 0; i <Rule.rules.size(); i++) {
            if(matches(exp1, Rule.rules.get(i).firstOp))
                if(matches(exp2, Rule.rules.get(i).secondOp)){
                    r = new Expression(Rule.rules.get(i).result, getOp(exp1,exp2));
                    match = true;
                    break;
                }
        }
        for (int i = 0; i <Rule.rules.size() && !match; i++) {
            if(matches(exp2, Rule.rules.get(i).firstOp))
                if(matches(exp1, Rule.rules.get(i).secondOp)){
                    r = new Expression(Rule.rules.get(i).result, getOp(exp2,exp1));
                }
        }

        return Rule.getResult(r);
    }

    private char[] getOp(Expression exp1, Expression exp2) {
        ArrayList<Character> ops = new ArrayList<>();
        for (char c : exp1.operands)
            ops.add(c);
        for (char c : exp2.operands)
            if(ops.contains(c)) ops.remove(c);
            else ops.add(c);
        
        char[] res = new char[ops.size()];
        for (int i = 0; i < res.length; i++)
            res[i] = ops.get(i);
        
        return res;
    }

    @Override
    public void addRule(IFInferenceRule rule) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addRule'");
    }

    @Override
    public void addExpression(Expression exp) {
        this.exps.add(exp);
    }

    @Override
    public Expression applyRules() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyRules'");
    }
    
}
