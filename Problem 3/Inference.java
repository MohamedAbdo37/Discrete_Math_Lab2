import java.util.ArrayList;

public class Inference implements IFInferenceEngine, IFInferenceRule {

    ArrayList<Expression> exps = new ArrayList<>() ;

    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        LogicalExpressionSolver logic = new LogicalExpressionSolver();
        int l = 0;
        if ( exp1.operands.length != exp2.operands.length) 
            l = exp1.operands.length + exp2.operands.length;
        else
            l = exp1.operands.length;
        for(int i = 0 ; i < l; i++){
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
                    match = true;
                }
        }
        
        if(match == false)
            return null;
        else {
            r = Rule.getResult(r);
            Expression o = new Expression("~PvP");
            Expression s = new Expression("(("+exp1.expression+")^"+exp2.expression+")>"+r.expression);
            match = matches(o,s);
        }

        if(match == true)
            return r;
        else
            return null;
    }

    private char[] getOp(Expression exp1, Expression exp2) {
        ArrayList<Character> ops = new ArrayList<>();

        for (int index = 0; index < exp1.operands.length; index++) {
            ops.add(exp1.operands[index]);
        }
        
        for (int index = 0; index < exp2.operands.length; index++) {
            if(ops.contains(exp2.operands[index])) ops.remove(Character.valueOf(exp2.operands[index]));
            else ops.add(exp2.operands[index]);
        }
        
        char[] res = new char[ops.size()];
        for (int i = 0; i < res.length; i++)
            res[i] = ops.get(i);
        
        return res;
    }

    @Override
    public void addRule(IFInferenceRule rule) {
        Rule.rules.add((Rule) rule);
    }

    @Override
    public void addExpression(Expression exp) {
        this.exps.add(exp);
    }

    @Override
    public Expression applyRules() {
        Expression res = this.exps.get(0);
        for(int i = 1; i < Rule.rules.size(); i++){
            Expression exp = this.exps.get(i);
            res = apply(res, exp);
        }

        return res;
    }
    
}
