package local.gershpenst.pizzaristoranteproject.exceptions.response;

import java.util.HashMap;
import java.util.Map;


public class ErrorResponse implements ResponseFormat {

    private Boolean success;
    private Map<String, String> errors = new HashMap<>();
    
    public ErrorResponse(Boolean success, Map<String, String> errors) {
        this.success = success;
        this.errors.putAll(errors);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public void addErrors(String key, String val) {
        this.errors.put(key, val);
    }

    // @Override
    // public void setErrors(Object key, Object val) {
    //     // TODO Auto-generated method stub
        
    // }

    
    
}