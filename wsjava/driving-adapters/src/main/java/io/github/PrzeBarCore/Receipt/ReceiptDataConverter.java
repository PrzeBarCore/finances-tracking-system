package io.github.PrzeBarCore.Receipt;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import io.github.PrzeBarCore.Category.CategoryDataConverter;
import io.github.PrzeBarCore.Product.ProductDataConverter;
import io.github.PrzeBarCore.Transaction.TransactionDataConverter;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@JsonComponent
public class ReceiptDataConverter {
    private static final String ID = "id";
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
            gen.writeFieldName(TRANSACTION);
            TransactionDataConverter.serializeReceiptTypeTransaction(receipt.getRelatedTransaction(), gen);
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
        JsonNode transaction = receiptRoot.get(TRANSACTION);
        JsonNode items = receiptRoot.get(ITEMS);

        ArrayList<ReceiptDto.DtoItem> convertedItems = new ArrayList<>();
        items.forEach(jsonNode -> convertedItems.add(deserializeReceiptItem(jsonNode)));


        return new ReceiptDto(id.asInt(),
                MonetaryAmount.of(totalDiscount.asDouble()),
                TransactionDataConverter.deserializeReceiptTypeTransaction(transaction),
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

}
