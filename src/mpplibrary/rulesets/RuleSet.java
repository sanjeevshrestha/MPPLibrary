package mpplibrary.rulesets;

import mpplibrary.application.controllers.AddMemberController;

public interface RuleSet {

    public void applyRules(AddMemberController amc) throws RuleException;
}
