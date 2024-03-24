package io.github.PrzeBarCore.Transaction;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.PrzeBarCore.Account.AccountDataConverter;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.TransactionType;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonComponent
public class TransactionDataConverter {
    private static final String ID = "id";
    private static final String ISSUED_ON_DATE_TIME = "issuedOnDateTime";
    private static final String TOTAL_VALUE = "totalValue";
    private static final String TRANSACTION_TYPE = "transactionType";
    private static final String SOURCE_ACCOUNT = "sourceAccount";
    private static final String RECEIPT_ID = "receiptId";

    public static ReceiptTypeSimpleTransactionDto deserializeReceiptTypeTransaction(JsonNode transactionRoot) {
        JsonNode id = transactionRoot.get(ID);
        JsonNode issuedOnDateTime = transactionRoot.get(ISSUED_ON_DATE_TIME);
        JsonNode totalValue = transactionRoot.get(TOTAL_VALUE);
        JsonNode sourceAccount = transactionRoot.get(SOURCE_ACCOUNT);
        JsonNode receiptID = transactionRoot.get(RECEIPT_ID);
        return new ReceiptTypeSimpleTransactionDto(id.asInt(),
                LocalDateTime.parse(issuedOnDateTime.asText(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")),
                MonetaryAmount.of(totalValue.asDouble()),
                AccountDataConverter.deserializeSimpleAccount(sourceAccount),
                receiptID.asInt());
    }

    public static void serializeTransaction(SimpleTransactionDto transactionDto, JsonGenerator gen) {
        try {
            if (transactionDto == null)
                gen.writeNull();
            else {
                if(transactionDto.getTransactionType().equals(TransactionType.RECEIPT))
                    serializeReceiptTypeTransaction((ReceiptTypeSimpleTransactionDto) transactionDto, gen);
                else {
                gen.writeStartObject();
                gen.writeNumberField(ID, transactionDto.getId());
                gen.writeStringField(ISSUED_ON_DATE_TIME, transactionDto.getIssuedOnDateTime().toString());
                gen.writeNumberField(TOTAL_VALUE, transactionDto.getTotalValue().toDouble());
                gen.writeStringField(TRANSACTION_TYPE, transactionDto.getTransactionType().toString());
                gen.writeEndObject();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void serializeReceiptTypeTransaction(ReceiptTypeSimpleTransactionDto transactionDto, JsonGenerator gen) {
        try {
            if (transactionDto == null)
                gen.writeNull();
            else {
                gen.writeStartObject();
                gen.writeNumberField(ID, transactionDto.getId());
                gen.writeStringField(ISSUED_ON_DATE_TIME, transactionDto.getIssuedOnDateTime().toString());
                gen.writeNumberField(TOTAL_VALUE, transactionDto.getTotalValue().toDouble());
                gen.writeStringField(TRANSACTION_TYPE, transactionDto.getTransactionType().toString());
                gen.writeFieldName(SOURCE_ACCOUNT);
                AccountDataConverter.serializeSimpleAccount(transactionDto.getSourceAccount(), gen);
                gen.writeNumberField(RECEIPT_ID, transactionDto.getReceiptId());
                gen.writeEndObject();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static class SimpleTransactionSerializer extends JsonSerializer<SimpleTransactionDto> {
        @Override
        public void serialize(SimpleTransactionDto value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            try {
                if (value != null) {
                    gen.writeStartObject();
                    gen.writeNumberField(ID, value.getId());
                    gen.writeStringField(ISSUED_ON_DATE_TIME, value.getIssuedOnDateTime().toString());
                    gen.writeStringField(TOTAL_VALUE, value.getTotalValue().toString());
                    gen.writeStringField(TRANSACTION_TYPE, value.getTransactionType().toString());
                    gen.writeEndObject();
                } else {
                    gen.writeStartObject();
                    gen.writeNull();
                    gen.writeEndObject();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
