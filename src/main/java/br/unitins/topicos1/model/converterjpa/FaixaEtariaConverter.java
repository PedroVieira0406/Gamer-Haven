package br.unitins.topicos1.model.converterjpa;

import br.unitins.topicos1.model.FaixaEtaria;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FaixaEtariaConverter implements AttributeConverter<FaixaEtaria, Integer>{

    @Override
    public Integer convertToDatabaseColumn(FaixaEtaria faixaEtaria) {
       return faixaEtaria.getId();
    }

    @Override
    public FaixaEtaria convertToEntityAttribute(Integer id) {
        return FaixaEtaria.valueOf(id);
    }
}