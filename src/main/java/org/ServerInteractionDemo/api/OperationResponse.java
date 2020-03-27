package org.ServerInteractionDemo.api;

import org.ServerInteractionDemo.core.OperationType;

import lombok.Data;


@Data
public class OperationResponse {
    OperationType type;
    Double        result;
}