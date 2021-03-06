/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.rulesets;

import mpplibrary.application.controllers.AddBookController;

/**
 *
 * @author Anish
 */
public class AddBookAndAuthorRuleSet implements RuleSet {

    @Override
    public void applyRules(Object o) throws RuleException {
        AddBookController bc = (AddBookController) o;
        bc.getFxLabelError().setText("");
        bc.getFxTxtIsbn().setStyle("");
        bc.getFxTxtTitle().setStyle("");
//        bc.getTxtBookTitle().setStyle("");
//        bc.getTxtFirstName().setStyle("");
//        bc.getTxtLastName().setStyle("");
//        bc.getTxtStreet().setStyle("");
//        bc.getTxtCity().setStyle("");
//        bc.getTxtZip().setStyle("");
//        bc.getTxtPhone().setStyle("");
//        bc.getTxtCredentials().setStyle("");
//        bc.getTxtBio().setStyle("");

        checkEmptyFields(bc);
//        numericRule(bc);

//        String stateValue = bc.getTxtZip().getText();
//        if (stateValue.length() != 2) {
//            throw new RuleException("State field should be of 2 letters.", bc.getTxtState());
//        }
//
//        if (!stateValue.matches("[A-Z]{2}")) {
//            throw new RuleException("State field should only contains A-Z.", bc.getTxtState());
//        }
//        if (bc.getTxtZip().getText().length() != 5) {
//            throw new RuleException("Zip field should be exactly of 5 digits.", bc.getTxtZip());
//        }
    }

    private void checkEmptyFields(AddBookController bc) throws RuleException {
        if (bc.getFxComboPublicationType().getSelectionModel().getSelectedItem() == null) {
            throw new RuleException("Select a publication type", null);
        }

        if (bc.getIsbn().length() == 0) {
            if (((String) bc.getFxComboPublicationType().getSelectionModel().getSelectedItem()).equalsIgnoreCase(AddBookController.PUBLICATION_TYPE_BOOK)) {
                System.out.println("throw isbn error");
                throw new RuleException("ISBN cannot be empty.", bc.getFxTxtIsbn());
            }
        } else if (!bc.isUniqueIsbn()) {
            throw new RuleException("ISBN already exists", bc.getFxTxtIsbn());
        }

        if (bc.getTitle().length() == 0) {
            throw new RuleException("Book title cannot be empty.", bc.getFxTxtTitle());
        }

        if (bc.getLendableCopiesList().isEmpty()) {
            throw new RuleException("Add Lendable copies", null);
        }

//        if (bc.get.length() == 0) {
//            throw new RuleException("Last Name cannot be empty.", bc.getTxtLastName());
//        }
//        if (bc.getTxtStreet().getText().length() == 0) {
//            throw new RuleException("Street cannot be empty.", bc.getTxtStreet());
//        }
//        if (bc.getTxtCity().getText().length() == 0) {
//            throw new RuleException("City cannot be empty.", bc.getTxtCity());
//        }
//
//        if (bc.getTxtState().getText().length() == 0) {
//            throw new RuleException("State cannot be empty.", bc.getTxtState());
//        }
//
//        if (bc.getTxtZip().getText().length() == 0) {
//            throw new RuleException("ZIP cannot be empty.", bc.getTxtZip());
//        }
//
//        if (bc.getTxtPhone().getText().length() == 0) {
//            throw new RuleException("Phone cannot be empty.", bc.getTxtPhone());
//        }
//
//        if (bc.getTxtCredentials().getText().length() == 0) {
//            throw new RuleException("Email cannot be empty.", bc.getTxtCredentials());
//        }
    }

//    private void numericRule(BookController bc) throws RuleException {
//        String zipCode = bc.getTxtZip().getText();
//        try {
//            Integer.parseInt(zipCode);
//        } catch (NumberFormatException e) {
//            throw new RuleException("ZIP must be numeric.", bc.getTxtZip());
//        }
//    }
}
