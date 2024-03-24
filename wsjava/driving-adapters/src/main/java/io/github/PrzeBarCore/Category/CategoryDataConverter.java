package io.github.PrzeBarCore.Category;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.PrzeBarCore.Product.SimpleProductDto;
import io.github.PrzeBarCore.ValueObjects.CategoryType;
import io.github.PrzeBarCore.ValueObjects.Company;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.Set;
@JsonComponent
public class CategoryDataConverter {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CATEGORY_TYPE = "categoryType";
    private static final String CHILD_CATEGORIES = "childCategories";
    private static final String PARENT_CATEGORY = "parentCategory";
    private static final String HAS_ANY_CHILD = "hasAnyChild";

    public static SimpleCategoryDto deserializeSimpleCategory(JsonNode categoryRoot) {
        JsonNode id = categoryRoot.get(ID);
        JsonNode name = categoryRoot.get(NAME);
        JsonNode categoryType = categoryRoot.get(CATEGORY_TYPE);
        return new SimpleCategoryDto(id.asInt(), NameString.of(name.asText()), CategoryType.valueOf(categoryType.asText()));
    }

    public static void serializeSimpleCategory(SimpleCategoryDto simpleCategoryDto, JsonGenerator gen) throws IOException {
        if (simpleCategoryDto == null)
            gen.writeNull();
        else {
            gen.writeStartObject();
            serializeSimpleDtoFields(simpleCategoryDto, gen);
            gen.writeEndObject();
        }
    }

    static class SimpleCategorySerializer extends JsonSerializer<SimpleCategoryDto> {
        @Override
        public void serialize(SimpleCategoryDto value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            try {
                if (value == null)
                    gen.writeNull();
                else
                    serializeSimpleCategory(value, gen);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    static class CategorySerializer extends JsonSerializer<CategoryDto> {
        @Override
        public void serialize(CategoryDto value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            try {
                if (value == null)
                    gen.writeNull();
                else
                    serializeCategory(value, gen);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    private static void serializeCategory(CategoryDto categoryDto, JsonGenerator gen) throws IOException {
        if (categoryDto == null)
            gen.writeNull();
        else {
            gen.writeStartObject();
            serializeSimpleDtoFields(categoryDto, gen);
            gen.writeArrayFieldStart(CHILD_CATEGORIES);
            categoryDto.getChildCategories().forEach(category -> {
                try {
                    serializeCategory(category, gen);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            gen.writeEndArray();
            gen.writeFieldName(PARENT_CATEGORY);
            try {
                serializeCategory(categoryDto.getParentCategory(), gen);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            gen.writeBooleanField(HAS_ANY_CHILD, categoryDto.getHasAnyChild());
            gen.writeEndObject();
        }
    }

    private static void serializeSimpleDtoFields(SimpleCategoryDto simpleCategoryDto, JsonGenerator gen) throws IOException {
        gen.writeNumberField(ID, simpleCategoryDto.getId());
        gen.writeStringField(NAME, simpleCategoryDto.getName().getText());
        gen.writeStringField(CATEGORY_TYPE, simpleCategoryDto.getCategoryType().toString());

    }
}
