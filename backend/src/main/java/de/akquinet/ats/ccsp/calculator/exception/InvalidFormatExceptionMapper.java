package de.akquinet.ats.ccsp.calculator.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import de.akquinet.ats.ccsp.calculator.model.Operator;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Provider
public class InvalidFormatExceptionMapper implements ExceptionMapper<InvalidFormatException> {

    private static final String ALLOWED_VALUES =
            Stream.of(Operator.values())
                    .map(Operator::name)
                    .collect(Collectors.joining(", "));

    @Override
    public Response toResponse(final InvalidFormatException exception) {
        final Class<?> targetType = exception.getTargetType();

        if (targetType == Operator.class) {
            final String allowedValues = ALLOWED_VALUES;
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("The provided operator '" + exception.getValue()
                            + "' is not supported. This API supports the following operators: " + allowedValues)
                    .build();
        }else if (targetType == Double.class) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("The provided operand '" + exception.getValue()
                            + "' cannot be processed. This API supports numeric values only.")
                    .build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Unknown deserialization error").build();
        }
    }
}
