package io.github.PrzeBarCore.Product;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.PrzeBarCore.Category.CategoryDataConverter;
import io.github.PrzeBarCore.ValueObjects.Company;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class ProductDataConverter {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PRODUCER = "producer";
    private static final String DEFAULT_PRICE = "defaultPrice";
    private static final String DEFAULT_RECEIPT_TRANSACTION_CATEGORY = "defaultReceiptTransactionCategory";
    private static final String QUANTITY = "quantity";
    private static final String UNIT = "unit";
    private static final String PRODUCT_CATEGORY = "productCategory";

    public static SimpleProductDto deserializeSimpleProduct(JsonNode productRoot) {
        JsonNode id = productRoot.get(ID);
        JsonNode name = productRoot.get(NAME);
        JsonNode producer = productRoot.get(PRODUCER);
        JsonNode defaultPrice = productRoot.get(DEFAULT_PRICE);
        JsonNode defaultReceiptTransactionCategory = productRoot.get(DEFAULT_RECEIPT_TRANSACTION_CATEGORY);

        return new SimpleProductDto(id.asInt(),
                NameString.of(name.asText()),
                Company.of(producer.asText()),
                MonetaryAmount.of(defaultPrice.asDouble()),
                CategoryDataConverter.deserializeSimpleCategory(defaultReceiptTransactionCategory));
    }

    public static void serializeSimpleDto(SimpleProductDto simpleProductDto, JsonGenerator gen) throws IOException {
        gen.writeStartObject();
        serializeSimpleDtoFields(simpleProductDto, gen);
        gen.writeEndObject();
    }

    static class SimpleProductSerializer extends JsonSerializer<SimpleProductDto> {
        @Override
        public void serialize(SimpleProductDto value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            try {
                if (value == null)
                    gen.writeNull();
                else {
                    serializeSimpleDto(value, gen);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    static class ProductSerializer extends JsonSerializer<ProductDto> {
        @Override
        public void serialize(ProductDto value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            try {
                if (value == null)
                    gen.writeNull();
                else {
                    gen.writeStartObject();
                    serializeSimpleDtoFields(value, gen);
                    gen.writeNumberField(QUANTITY, value.getQuantity());
                    gen.writeStringField(UNIT, value.getUnit().toString());
                    gen.writeFieldName(PRODUCT_CATEGORY);
                    CategoryDataConverter.serializeSimpleCategory(value.getProductCategory(), gen);
                    gen.writeEndObject();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void serializeSimpleDtoFields(SimpleProductDto simpleProductDto, JsonGenerator gen) throws IOException {
        gen.writeNumberField(ID, simpleProductDto.getId());
        gen.writeStringField(NAME, simpleProductDto.getName().getText());
        gen.writeStringField(PRODUCER, simpleProductDto.getProducer().getText());
        gen.writeNumberField(DEFAULT_PRICE, simpleProductDto.getDefaultPrice().toDouble());
        gen.writeFieldName(DEFAULT_RECEIPT_TRANSACTION_CATEGORY);
        CategoryDataConverter.serializeSimpleCategory(simpleProductDto.getDefaultReceiptTransactionCategory(), gen);
    }
}
