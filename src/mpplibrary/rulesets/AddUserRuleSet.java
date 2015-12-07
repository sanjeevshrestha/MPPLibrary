/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.rulesets;

import mpplibrary.application.controllers.AddUserController;

/**
 *
 * @author Anish
 */
public class AddUserRuleSet implements RuleSet {

    @Override
    public void applyRules(Object o) throws RuleException {
        AddUserController amc = (AddUserController) o;
        amc.getUsernameTxt().setStyle("");
        amc.getPasswordTxt().setStyle("");
        amc.getFirstNameTxt().setStyle("");
        amc.getLastNameTxt().setStyle("");
        amc.getPhoneTxt().setStyle("");
        amc.getEmailTxt().setStyle("");
        amc.getAddressTxt().setStyle("");
        amc.getCityTxt().setStyle("");
        amc.getStateTxt().setStyle("");
        amc.getZipTxt().setStyle("");

        checkEmptyFields(amc);
        numericRule(amc);

        String stateValue = amc.getStateTxt().getText();
        if (stateValue.length() != 2) {
            throw new RuleException("State field should be of 2 letters.", amc.getStateTxt());
        }

        if (!stateValue.matches("[A-Z]{2}")) {
            throw new RuleException("State field should only contains A-Z.", amc.getStateTxt());
        }
        if (amc.getZipTxt().getText().length() != 5) {
            throw new RuleException("Zip field should be exactly of 5 digits.", amc.getZipTxt());
        }

//        if (amc.getZipComponent().getText().equals(amc.getMemberComponent().getText())) {
//            throw new RuleException("ID and ZIP cannot be same.", amc.getZipComponent());
//        }
    }

    private void checkEmptyFields(AddUserController amc) throws RuleException {
//        if (amc.getMemberComponent().getText().length() == 0) {
//            throw new RuleException("ID cannot be empty.", amc.getMemberComponent());
//        }

        if (amc.getUsernameTxt().getText().length() == 0) {
            throw new RuleException("Username cannot be empty.", amc.getUsernameTxt());
        }

        if (amc.getPasswordTxt().getText().length() == 0) {
            throw new RuleException("Password cannot be empty.", amc.getPasswordTxt());
        }

        if (amc.getFirstNameTxt().getText().length() == 0) {
            throw new RuleException("First Name cannot be empty.", amc.getFirstNameTxt());
        }

        if (amc.getLastNameTxt().getText().length() == 0) {
            throw new RuleException("Last Name cannot be empty.", amc.getLastNameTxt());
        }

        if (amc.getPhoneTxt().getText().length() == 0) {
            throw new RuleException("Phone cannot be empty.", amc.getPhoneTxt());
        }

        if (amc.getEmailTxt().getText().length() == 0) {
            throw new RuleException("Email cannot be empty.", amc.getEmailTxt());
        }

        if (amc.getAddressTxt().getText().length() == 0) {
            throw new RuleException("Address cannot be empty.", amc.getAddressTxt());
        }

        if (amc.getCityTxt().getText().length() == 0) {
            throw new RuleException("City cannot be empty.", amc.getCityTxt());
        }

        if (amc.getStateTxt().getText().length() == 0) {
            throw new RuleException("State cannot be empty.", amc.getStateTxt());
        }

        if (amc.getZipTxt().getText().length() == 0) {
            throw new RuleException("ZIP cannot be empty.", amc.getZipTxt());
        }

        EmailValidator ev = new EmailValidator();
        if (!ev.validate(amc.getEmailTxt().getText())) {
            throw new RuleException("Invalid Email Address.", amc.getEmailTxt());
        }
    }

    private void numericRule(AddUserController amc) throws RuleException {
        String zipCode = amc.getZipTxt().getText();
        //   String id = amc.getMemberComponent().getText();
        try {
            Integer.parseInt(zipCode);
        } catch (NumberFormatException e) {
            throw new RuleException("ZIP must be numeric.", amc.getZipTxt());
        }

//        try {
//            Integer.parseInt(id);
//        } catch (NumberFormatException e) {
//            throw new RuleException("ID must be numeric.", amc.getMemberComponent());
//        }
    }
}
