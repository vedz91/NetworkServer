package org.ServerInteractionDemo.api;

import org.ServerInteractionDemo.api.exception.InvalidOperandException;
import org.ServerInteractionDemo.core.OperationType;
import org.ServerInteractionDemo.literal.ExceptionLiterals;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.Builder;

@Builder
public class OperationRequest {
    Optional<OperationType> type;
    Double valA;
    Double valB;

    public void validate() {
        if(this.valA == null || this.valB == null) {
            throw new InvalidOperandException(ExceptionLiterals.E_MISSING_OPERAND);
        }
    }

    public Map<String, String> toQueryMap() {
        final Map<String, String> queryparam = new HashMap<String, String>() {{
            put("operand1", String.valueOf(valA));
            put("operand2", String.valueOf(valB));
        }};
        if(type.isPresent()){
            queryparam.put("type", type.get().toString());
        }
        return queryparam;
    }
}
