package intern.customer.agitoo.Core.Utilities;

import jakarta.persistence.AttributeConverter;

public class BooleanToYesNoConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn (Boolean attribute) {
        return (attribute != null && attribute) ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute (String dbData) {
        return "Y".equals(dbData);
    }
}
