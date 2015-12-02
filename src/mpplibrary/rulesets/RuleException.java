package mpplibrary.rulesets;

@SuppressWarnings("serial")
final public class RuleException extends Exception {

    private Object errorObject;

    public RuleException(Object obj) {
        super();
        this.errorObject = obj;
    }

    public RuleException(String msg, Object obj) {
        super(msg);
        this.errorObject = obj;
    }

    public Object getErrorObject() {
        return errorObject;
    }

    public void setErrorObject(Object errorObject) {
        this.errorObject = errorObject;
    }
}
