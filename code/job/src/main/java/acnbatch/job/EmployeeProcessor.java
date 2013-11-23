package acnbatch.job;

import acnbatch.job.domain.EmployeeInputRecord;
import org.apache.commons.validator.EmailValidator;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

/**
 * GLHFDDDFU! Don't forget to commit!
 */
@Named
public class EmployeeProcessor implements ItemProcessor
{
    @Override
    public Object processItem(Object item) throws Exception
    {
        // Cast because we're not dealing with generics...
        EmployeeInputRecord record = (EmployeeInputRecord) item;

        // Filter out if invalid.
        if (!EmployeeProcessor.isValid(record))
        {
            return null;
        }

        return item;
    }

    /**
     * Validates the entire record. TODO: Only email is currently validated.
     */
    private static boolean isValid(EmployeeInputRecord record)
    {
        final String email = record.getEmail();

        return EmailValidator.getInstance().isValid(email)
                && email.matches(".*\\..*@.*");
    }
}
