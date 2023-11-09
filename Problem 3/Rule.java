import java.util.ArrayList;
// import java.util.Stack;

public class Rule extends Inference {
    Expression firstOp;
    Expression secondOp;
    Expression result;

    private static Expression imples = new Expression("P>Q");
    private static Expression direct = new Expression("P");
    private static Expression invers = new Expression("~Q");
    private static Expression or = new Expression("PvQ");
    private static Expression and = new Expression("P^Q");

    static ArrayList<Rule> rules = new ArrayList<>();
    // Stack<Rule> exicut = new Stack<>();

    public Rule(Expression first, Expression second, Expression result){
        this.firstOp = first;
        this.secondOp= second;
        this.result = result;
    }

    public Rule(){
        rules.add(new Rule(imples,direct, direct));
        rules.add(new Rule(imples,invers, invers));
        rules.add(new Rule(imples,imples,imples));
        rules.add(new Rule(or,invers,direct));
        rules.add(new Rule(or,imples,or));

    }

    protected static Expression imples(char[] ops){
        return new Expression(imples,ops);
    }

    protected static Expression invers(char[] ops){
        return new Expression(invers, ops);
    }

    protected static Expression direct(char[] ops){
        return new Expression(direct, ops);
    }

    protected static Expression Or(char[] ops){
        return new Expression(or, ops);
    }
    protected static Expression And(char[] ops){
        return new Expression(and, ops);
    }

    @Override
    public void addRule(IFInferenceRule rule) {
        rules.add((Rule) rule);
    }

    @Override
    public void addExpression(Expression exp) {
        
    }

    @Override
    public Expression applyRules() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyRules'");
    }

    public static Expression getResult(Expression r) {
        Inference i = new Rule();
        if(i.matches(r, imples))
            return imples(r.operands);
        if(i.matches(r, direct))
            return direct(r.operands);
        if(i.matches(r, invers))
            return invers(r.operands);
        if(i.matches(r, or))
            return Or(r.operands);
        if(i.matches(r, and))
            return And(r.operands);

        return null;
    }
}
