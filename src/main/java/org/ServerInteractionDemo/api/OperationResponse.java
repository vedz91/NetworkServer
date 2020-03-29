package org.ServerInteractionDemo.api;

import org.ServerInteractionDemo.core.OperationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperationResponse {
    OperationType type;
    Double        result;
}