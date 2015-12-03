package mpplibrary.rulesets;

import java.util.HashMap;
import mpplibrary.application.controllers.AddBookController;
import mpplibrary.application.controllers.AddMemberController;

final public class RuleSetFactory {

    private RuleSetFactory() {
    }
    static HashMap<Class<? extends Object>, RuleSet> map = new HashMap<>();

    static {
        map.put(AddMemberController.class, new AddMemberRuleSet());
        map.put(AddBookController.class, new AddBookAndAuthorRuleSet());
        
    }

    public static RuleSet getRuleSet(Object amc) {
        Class<? extends Object> cl = amc.getClass();
        if (!map.containsKey(cl)) {
            throw new IllegalArgumentException("No RuleSet found for this Component");
        }
        return map.get(cl);
    }
}
