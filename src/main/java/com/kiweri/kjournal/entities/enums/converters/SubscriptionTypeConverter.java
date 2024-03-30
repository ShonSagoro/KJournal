package com.kiweri.kjournal.entities.enums.converters;

import com.kiweri.kjournal.entities.enums.SubscriptionType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@Converter(autoApply = true)
public class SubscriptionTypeConverter implements AttributeConverter<SubscriptionType, String> {

    @Override
    public String convertToDatabaseColumn(SubscriptionType paymentMethodType) {
        if (paymentMethodType == null)
            return null;
        return paymentMethodType.getValue();
    }

    @Override
    public SubscriptionType convertToEntityAttribute(String type) {
        if (type == null)
            return null;
        return Stream.of(SubscriptionType.values())
                .filter(t -> t.getValue().equals(type))
                .findFirst().orElseThrow(RuntimeException::new);
    }
}
