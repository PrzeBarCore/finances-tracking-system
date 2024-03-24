package io.github.PrzeBarCore.Transaction;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import io.github.PrzeBarCore.Account.AccountDataConverter;
import io.github.PrzeBarCore.Category.CategoryDataConverter;
import io.github.PrzeBarCore.Product.ProductDataConverter;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;
import io.github.PrzeBarCore.ValueObjects.TransactionType;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

@JsonComponent
public class TransactionDataConverter {
    private static final String ID = "id";
    private static final String ISSUED_ON_DATE_TIME = "issuedOnDateTime";
    private static final String TOTAL_VALUE = "totalValue";
    private static final String TRANSACTION_TYPE = "transactionType";
    private static final String SOURCE_ACCOUNT = "sourceAccount";
    private static final String RECEIPT = "receipt";

    private static final String TOTAL_DISCOUNT = "totalDiscount";
    private static final String TRANSACTION = "transaction";
    private static final String ITEMS = "items";

    private static final String NAME = "name";
    private static final String QUANTITY = "quantity";
    private static final String REGULAR_PRICE = "regularPrice";
    private static final String DISCOUNT = "discount";
    private static final String CATEGORY = "category";
    private static final String PRODUCT = "product";


    public static class ReceiptDeserializer extends JsonDeserializer<ReceiptDto> {
        @Override
        public ReceiptDto deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
            try {
                if (jsonParser.getText() == null)
                    return null;
                else
                    return deserializeReceipt(jsonParser.getCodec().readTree(jsonParser));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }

    public static class ReceiptSerializer extends JsonSerializer<ReceiptDto> {
        @Override
        public void serialize(ReceiptDto value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value == null)
                gen.writeNull();
            else {
                try {
                    serializeReceipt(value, gen);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private static void serializeReceipt(ReceiptDto receipt, JsonGenerator gen) throws IOException {
        if (receipt == null)
            gen.writeNull();
        else {
            gen.writeStartObject();
            gen.writeNumberField(ID, receipt.getId());
            gen.writeNumberField(TOTAL_DISCOUNT, receipt.getTotalDiscount().toDouble());
            gen.writeArrayFieldStart(ITEMS);
            receipt.getItems().forEach(item -> {
                try {
                    serializeReceiptItem(item, gen);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            gen.writeEndArray();
            gen.writeEndObject();
        }
    }


    private static void serializeReceiptItem(ReceiptDto.DtoItem receiptItem, JsonGenerator gen) throws IOException {
        if (receiptItem == null)
            gen.writeNull();
        else {
            gen.writeStartObject();
            gen.writeStringField(NAME, receiptItem.getName().getText());
            gen.writeNumberField(QUANTITY, receiptItem.getQuantity());
            gen.writeNumberField(REGULAR_PRICE, receiptItem.getRegularPrice().toDouble());
            gen.writeNumberField(DISCOUNT, receiptItem.getDiscount().toDouble());
            gen.writeFieldName(CATEGORY);
            CategoryDataConverter.serializeSimpleCategory(receiptItem.getExpenseCategory(), gen);
            gen.writeFieldName(PRODUCT);
            if (receiptItem.getProduct().isPresent()) {
                ProductDataConverter.serializeSimpleDto(receiptItem.getProduct().get(), gen);
                gen.writeEndObject();
            } else {
                gen.writeNull();
            }
        }
    }

    private static ReceiptDto deserializeReceipt(JsonNode receiptRoot) {
        JsonNode id = receiptRoot.get(ID);
        JsonNode totalDiscount = receiptRoot.get(TOTAL_DISCOUNT);
        JsonNode items = receiptRoot.get(ITEMS);

        ArrayList<ReceiptDto.DtoItem> convertedItems = new ArrayList<>();
        items.forEach(jsonNode -> convertedItems.add(deserializeReceiptItem(jsonNode)));


        return new ReceiptDto(id.asInt(),
                MonetaryAmount.of(totalDiscount.asDouble()),
                convertedItems);
    }

    private static ReceiptDto.DtoItem deserializeReceiptItem(JsonNode receiptItem) {
        JsonNode id = receiptItem.get(ID);
        JsonNode name = receiptItem.get(NAME);
        JsonNode quantity = receiptItem.get(QUANTITY);
        JsonNode regularPrice = receiptItem.get(REGULAR_PRICE);
        JsonNode discount = receiptItem.get(DISCOUNT);
        JsonNode category = receiptItem.get(CATEGORY);
        JsonNode product = receiptItem.get(PRODUCT);
        return new ReceiptDto.DtoItem(id.asInt(),
                NameString.of(name.asText()),
                quantity.asDouble(),
                MonetaryAmount.of(regularPrice.asDouble()),
                MonetaryAmount.of(discount.asDouble()),
                Optional.of(ProductDataConverter.deserializeSimpleProduct(product)),
                CategoryDataConverter.deserializeSimpleCategory(category));
    }

    public static ReceiptTypeTransaction deserializeReceiptTypeTransaction(JsonNode transactionRoot) {
        JsonNode id = transactionRoot.get(ID);
        JsonNode issuedOnDateTime = transactionRoot.get(ISSUED_ON_DATE_TIME);
        JsonNode totalValue = transactionRoot.get(TOTAL_VALUE);
        JsonNode sourceAccount = transactionRoot.get(SOURCE_ACCOUNT);
        JsonNode receipt = transactionRoot.get(RECEIPT);
        return new ReceiptTypeTransaction(id.asInt(),
                LocalDateTime.parse(issuedOnDateTime.asText(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")),
                MonetaryAmount.of(totalValue.asDouble()),
                AccountDataConverter.deserializeSimpleAccount(sourceAccount),
                deserializeReceipt(receipt));
    }

    public static void serializeTransaction(SimpleTransactionDto transactionDto, JsonGenerator gen) {
        try {
            if (transactionDto == null)
                gen.writeNull();
            else {
                if(transactionDto.getTransactionType().equals(TransactionType.RECEIPT))
                    serializeReceiptTypeTransaction((ReceiptTypeTransaction) transactionDto, gen);
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

    public static void serializeReceiptTypeTransaction(ReceiptTypeTransaction transactionDto, JsonGenerator gen) {
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
                gen.writeFieldName(RECEIPT);
                serializeReceipt(transactionDto.getReceipt(), gen);
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
