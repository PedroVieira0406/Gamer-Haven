package br.unitins.topicos1.model.converterjpa;

import br.unitins.topicos1.model.CartaoMarca;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CartaoMarcaConverter implements AttributeConverter<CartaoMarca, Integer>{

    @Override
    public Integer convertToDatabaseColumn(CartaoMarca cartao) {
       return cartao.getId();
    }

    @Override
    public CartaoMarca convertToEntityAttribute(Integer id) {
        return CartaoMarca.valueOf(id);
    }
}