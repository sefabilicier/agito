package intern.customer.agitoo.Configuration;

import intern.customer.agitoo.Common.Exception.BusinessException;
import intern.customer.agitoo.Common.Exception.ProblemDetails;
import intern.customer.agitoo.Common.Exception.ValidationProblemDetails;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
@Configuration
public class ExceptionConfiguration {

    @ExceptionHandler //exceptionda artık gerekli mesaj tek verilecek
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetails handleBusinessException (BusinessException exception) { //businessexception aldığında çalış
        ProblemDetails problemDetails = new ProblemDetails ();
        problemDetails.setMessage (exception.getMessage ());

        return problemDetails;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetails handleValidationException (MethodArgumentNotValidException exception) {//ekranda çıkan exception hatası için yazıldı
        ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails ();

        validationProblemDetails.setMessage ("Validation exception");
        validationProblemDetails.setValidationErrors (new HashMap<String, String> ());

        for (FieldError fieldError : exception.getBindingResult ().getFieldErrors ()) {
            validationProblemDetails.getValidationErrors ()
                    .put (fieldError.getField (), fieldError.getDefaultMessage ());
        }

        return validationProblemDetails;
    }
}