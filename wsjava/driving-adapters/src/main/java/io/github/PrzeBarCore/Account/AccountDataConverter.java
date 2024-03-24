package io.github.PrzeBarCore.Account;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import io.github.PrzeBarCore.Transaction.TransactionDataConverter;
import io.github.PrzeBarCore.ValueObjects.NameString;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class AccountDataConverter {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String BALANCE = "balance";
    private static final String CURRENCY = "currency";
    private static final String TRANSACTION_LIST = "transactionList";

    public static SimpleAccountDto deserializeSimpleAccount(JsonNode accountRoot) {
        JsonNode id = accountRoot.get(ID);
        JsonNode name = accountRoot.get(NAME);
        return new SimpleAccountDto(id.asInt(), NameString.of(name.asText()));
    }

    public static void serializeSimpleAccount(SimpleAccountDto value, JsonGenerator gen) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField(ID, value.getId());
        gen.writeStringField(NAME, value.getName().getText());
        gen.writeEndObject();
    }

    static class AccountSerializer extends JsonSerializer<AccountDto> {
        @Override
        public void serialize(AccountDto value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            try {
                if (value == null)
                    gen.writeNull();
                else {
                    gen.writeStartObject();
                    gen.writeNumberField(ID, value.getId());
                    gen.writeStringField(NAME, value.getName().getText());
                    gen.writeNumberField(BALANCE, value.getBalance().toDouble());
                    gen.writeStringField(CURRENCY, value.getCurrency());
                    gen.writeArrayFieldStart(TRANSACTION_LIST);
                    if (value.transactionList != null)
                        value.transactionList.forEach(transaction -> TransactionDataConverter.serializeTransaction(transaction, gen));
                    gen.writeEndArray();
                    gen.writeEndObject();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
