package com.furnitureshop.product.hashmap;

import com.furnitureshop.product.entity.Product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProductHashMap {
    public static Map<String, Object> get(Product product) {
        Map<String, Object> result = new LinkedHashMap<>();
        ArrayList<Map<String, Object>> variants = new ArrayList<>();

        product.getProductVariants().forEach(productVariant -> {
            Map<String, Object> variant = new LinkedHashMap<>();
            ArrayList<Map<String, Object>> options = new ArrayList<>();

            productVariant.getVariantValues().forEach(variantValue -> {
                Map<String, Object> option = new LinkedHashMap<>();
                option.put("option_id", variantValue.getOption().getOptionId());
                option.put("option_name", variantValue.getOption().getOptionName());
                option.put("option_value", variantValue.getOptionValue());
                option.put("option_image", variantValue.getOptionImage());
                options.add(option);
            });

            variant.put("variant_id", productVariant.getVariantId());
            variant.put("sku", productVariant.getSku());
            variant.put("price", productVariant.getPrice());
            variant.put("import_price", 1000);
            variant.put("quantity", productVariant.getQuantity());
            variant.put("image_url", productVariant.getImage());
            variant.put("options", options);
            variants.add(variant);
        });

        result.put("product_id", product.getProductId());
        result.put("product_name", product.getProductName());
        result.put("category_id", product.getCategory().getCategoryId());
        result.put("category_name", product.getCategory().getCategoryName());
        result.put("brand_id", product.getBrand().getBrandId());
        result.put("brand_name", product.getBrand().getBrandName());
        result.put("description", product.getProductDesc());
        result.put("image_url", product.getImage());
        result.put("variants", variants);

        return result;
    }
}