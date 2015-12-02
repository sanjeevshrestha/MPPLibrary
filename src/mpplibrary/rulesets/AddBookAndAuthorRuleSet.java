/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.rulesets;

import mpplibrary.application.controllers.AddMemberController;

/**
 *
 * @author Anish
 */
public class AddBookAndAuthorRuleSet implements RuleSet {

    @Override
    public void applyRules(AddMemberController amc) throws RuleException {
        amc.getErrorLabel().setText("");
        amc.getMemberComponent().setStyle("");
        amc.getFirstNameComponent().setStyle("");
        amc.getLastNameComponent().setStyle("");
        amc.getAddressComponent().setStyle("");
        amc.getCityComponent().setStyle("");
        amc.getStateComponent().setStyle("");
        amc.getZipComponent().setStyle("");
        amc.getPhoneComponent().setStyle("");
        amc.getEmailComponent().setStyle("");

        checkEmptyFields(amc);
        numericRule(amc);

        String stateValue = amc.getStateComponent().getText();
        if (stateValue.length() != 2) {
            throw new RuleException("State field should be of 2 letters.", amc.getStateComponent());
        }

        if (!stateValue.matches("[A-Z]{2}")) {
            throw new RuleException("State field should only contains A-Z.", amc.getStateComponent());
        }
        if (amc.getZipComponent().getText().length() != 5) {
            throw new RuleException("Zip field should be exactly of 5 digits.", amc.getZipComponent());
        }

        if (amc.getZipComponent().getText().equals(amc.getMemberComponent().getText())) {
            throw new RuleException("ID and ZIP cannot be same.", amc.getZipComponent());
        }
    }

    private void checkEmptyFields(AddMemberController amc) throws RuleException {
        if (amc.getMemberComponent().getText().length() == 0) {
            throw new RuleException("ID cannot be empty.", amc.getMemberComponent());
        }

        if (amc.getFirstNameComponent().getText().length() == 0) {
            throw new RuleException("First Name cannot be empty.", amc.getFirstNameComponent());
        }

        if (amc.getLastNameComponent().getText().length() == 0) {
            throw new RuleException("Last Name cannot be empty.", amc.getLastNameComponent());
        }

        if (amc.getAddressComponent().getText().length() == 0) {
            throw new RuleException("Address cannot be empty.", amc.getAddressComponent());
        }

        if (amc.getCityComponent().getText().length() == 0) {
            throw new RuleException("City cannot be empty.", amc.getCityComponent());
        }

        if (amc.getStateComponent().getText().length() == 0) {
            throw new RuleException("State cannot be empty.", amc.getStateComponent());
        }

        if (amc.getZipComponent().getText().length() == 0) {
            throw new RuleException("ZIP cannot be empty.", amc.getZipComponent());
        }

        if (amc.getPhoneComponent().getText().length() == 0) {
            throw new RuleException("Phone cannot be empty.", amc.getPhoneComponent());
        }

        if (amc.getEmailComponent().getText().length() == 0) {
            throw new RuleException("Email cannot be empty.", amc.getEmailComponent());
        }

        EmailValidator ev = new EmailValidator();
        if (!ev.validate(amc.getEmailComponent().getText())) {
            throw new RuleException("Invalid Email Address.", amc.getEmailComponent());
        }
    }

    private void numericRule(AddMemberController amc) throws RuleException {
        String zipCode = amc.getZipComponent().getText();
        String id = amc.getMemberComponent().getText();
        try {
            Integer.parseInt(zipCode);
        } catch (NumberFormatException e) {
            throw new RuleException("ZIP must be numeric.", amc.getZipComponent());
        }

        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new RuleException("ID must be numeric.", amc.getMemberComponent());
        }
    }
}
