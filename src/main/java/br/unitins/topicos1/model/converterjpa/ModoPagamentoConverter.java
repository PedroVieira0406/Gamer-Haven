package br.unitins.topicos1.model.converterjpa;

import br.unitins.topicos1.model.ModoPagamento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ModoPagamentoConverter implements AttributeConverter<ModoPagamento, Integer>{

    @Override
    public Integer convertToDatabaseColumn(ModoPagamento cartao) {
       return cartao.getId();
    }

    @Override
    public ModoPagamento convertToEntityAttribute(Integer id) {
        return ModoPagamento.valueOf(id);
    }
}