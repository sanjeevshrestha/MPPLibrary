package mpplibrary.rulesets;

import java.awt.Component;
import java.util.HashMap;
import mpplibrary.application.controllers.AddMemberController;

final public class RuleSetFactory {

    private RuleSetFactory() {
    }
    static HashMap<Class<? extends AddMemberController>, RuleSet> map = new HashMap<>();

    static {
        map.put(AddMemberController.class, new AddMemberRuleSet());
//        map.put(ProfileWindow.class, new ProfileRuleSet());
    }

    public static RuleSet getRuleSet(AddMemberController amc) {
        Class<? extends AddMemberController> cl = amc.getClass();
        if (!map.containsKey(cl)) {
            throw new IllegalArgumentException("No RuleSet found for this Component");
        }
        return map.get(cl);
    }
}
