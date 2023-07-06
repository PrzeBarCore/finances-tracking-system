package io.github.PrzeBarCore.Transaction.Dto;

import io.github.PrzeBarCore.ValueObjects.NameString;

public class TransactionCategoryDto {
    private Integer id;
    private NameString name;
    private TransactionCategoryDto parentCategory;
    private Integer dependencyLevel;

    private TransactionCategoryDto() {}

    public static Builder builder(){
        return new Builder();
    }

    public Integer getId() {
        return id;
    }

    public NameString getName() {
        return name;
    }

    public TransactionCategoryDto getParentCategory() {
        return parentCategory;
    }

    public Integer getDependencyLevel() {
        return dependencyLevel;
    }

    public static class Builder{
        private Integer id;
        private NameString name;
        private TransactionCategoryDto parentCategory;
        private Integer dependencyLevel;

        private Builder() {}

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withName(NameString name) {
            this.name = name;
            return this;
        }

        public Builder withParentCategory(TransactionCategoryDto parentCategory) {
            this.parentCategory = parentCategory;
            return this;
        }

        public Builder withDependencyLevel(Integer dependencyLevel) {
            this.dependencyLevel = dependencyLevel;
            return this;
        }

        public TransactionCategoryDto build(){
            var dto=new TransactionCategoryDto();
            dto.id=this.id;
            dto.name=this.name;
            dto.parentCategory=this.parentCategory;
            dto.dependencyLevel=this.dependencyLevel;
            return dto;
        }
    }
}
