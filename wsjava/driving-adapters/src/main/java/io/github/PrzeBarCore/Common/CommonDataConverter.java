package io.github.PrzeBarCore.Common;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import io.github.PrzeBarCore.ValueObjects.*;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.math.BigDecimal;

@JsonComponent
public class CommonDataConverter {
    public static class DescriptionSerializer extends JsonSerializer<Description>{

        private static final String ID="id";
        private static final String ISSUED_ON_DATE_TIME="issuedOnDateTime";
        private static final String TOTAL_VALUE="totalValue";
        private static final String RECEIPT_ITEM="receiptItem";

        private static final String NAME="name";
        private static final String QUANTITY="quantity";
        private static final String REGULAR_PRICE="regularPrice";
        private static final String DISCOUNT="discount";
        private static final String CATEGORY="category";
        private static final String PRODUCT="product";


        @Override
        public void serialize(Description value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            try{
                if(value == null)
                    gen.writeNull();
                else
                    gen.writeString(value.getText());
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
//    public static class DescriptionDeserializer extends JsonDeserializer<Description> {
//        @Override
//        public Description deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
//            try{
//                JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
//                if(p.getText() == null)
//                    return null;
//                else
//                    return Description.of(p.getText());
//            } catch (Exception ex){
//                ex.printStackTrace();
//            }
//            return null;
//        }
//    }

    public static class NameStringSerializer extends JsonSerializer<NameString>{
        @Override
        public void serialize(NameString value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            try{
                if(value == null)
                    gen.writeNull();
                else
                    gen.writeString(value.getText());
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    public static class NameStringDeserializer extends JsonDeserializer<NameString> {
        @Override
        public NameString deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            try{
                if(p.getText() == null)
                    return null;
                else
                    return NameString.of(p.getText());
            } catch (Exception ex){
                ex.printStackTrace();
            }
            return null;
        }
    }

    public static class CategoryTypeSerializer extends JsonSerializer<CategoryType>{
        @Override
        public void serialize(CategoryType value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            try{
                if(value == null)
                    gen.writeNull();
                else
                    gen.writeString(value.toString());
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    public static class CategoryTypeDeserializer extends JsonDeserializer<CategoryType> {
        @Override
        public CategoryType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            try{
                if(p.getText() == null)
                    return null;
                else
                    return CategoryType.valueOf((p.getText()));
            } catch (Exception ex){
                ex.printStackTrace();
            }
            return null;
        }
    }

    public static class TransactionTypeSerializer extends JsonSerializer<TransactionType>{
        @Override
        public void serialize(TransactionType value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            try{
                if(value == null)
                    gen.writeNull();
                else
                    gen.writeString(value.toString());
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    public static class TransactionTypeDeserializer extends JsonDeserializer<TransactionType> {
        @Override
        public TransactionType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            try{
                if(p.getText() == null)
                    return null;
                else
                    return TransactionType.valueOf((p.getText()));
            } catch (Exception ex){
                ex.printStackTrace();
            }
            return null;
        }
    }

    public static class CompanySerializer extends JsonSerializer<Company>{
        @Override
        public void serialize(Company value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            try{
                if(value == null)
                    gen.writeNull();
                else
                    gen.writeString(value.getText());
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    public static class CompanyDeserializer extends JsonDeserializer<Company> {
        @Override
        public Company deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            try{
                if(p.getText() == null)
                    return null;
                else
                    return Company.of((p.getText()));
            } catch (Exception ex){
                ex.printStackTrace();
            }
            return null;
        }
    }

    public static class MonetaryAmountSerializer extends JsonSerializer<MonetaryAmount>{
        @Override
        public void serialize(MonetaryAmount value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            try{
                if(value == null)
                    gen.writeNull();
                else
                    gen.writeString(value.toString());
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public static class MonetaryAmountDeserializer extends JsonDeserializer<MonetaryAmount> {
        @Override
        public MonetaryAmount deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            try{
                if(p.getText() == null)
                    return null;
                else
                    return MonetaryAmount.of(BigDecimal.valueOf(p.getDoubleValue()));
            } catch (Exception ex){
                ex.printStackTrace();
            }
            return null;
        }
    }

}
