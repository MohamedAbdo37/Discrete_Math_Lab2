public interface IFInferenceEngine {
    void addRule(IFInferenceRule rule);
    void addExpression(Expression exp);
    Expression applyRules();
}
