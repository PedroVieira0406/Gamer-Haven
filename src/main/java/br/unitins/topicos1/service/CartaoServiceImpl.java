package br.unitins.topicos1.service;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.CartaoDTO;
import br.unitins.topicos1.dto.CartaoResponseDTO;
import br.unitins.topicos1.model.Cartao;
import br.unitins.topicos1.model.CartaoMarca;
import br.unitins.topicos1.repository.CartaoRepository;
import br.unitins.topicos1.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.Validator;

@ApplicationScoped
public class CartaoServiceImpl implements CartaoService {

    @Inject
    CartaoRepository CartaoRepository;

    @Inject
    ClienteRepository ClienteRepository;

    @Inject
    Validator validator;

    @Inject
    HashService hashService;

    @Override
    @Transactional
    public CartaoResponseDTO create(@Valid CartaoDTO dto) {

        Cartao entity = new Cartao();
        entity.setCartaoMarca(CartaoMarca.valueOf(dto.idCartaoMarca()));
        entity.setnCartao(dto.nCartao());
        entity.setMesValid(dto.mesValid());
        entity.setAnoValid(dto.anoValid());
        entity.setNumSeguranca(hashService.getHashSenha(dto.numSeguranca()));
        entity.setNome(dto.nome());
        entity.setSobrenome(dto.sobrenome());
        entity.setCep(dto.cep());
        entity.setCidade(dto.cidade());
        entity.setPais(dto.pais());
        entity.setCliente(ClienteRepository.findById(dto.dono()));
        CartaoRepository.persist(entity);

        return CartaoResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public CartaoResponseDTO update(Long idCartao, CartaoDTO dto){
    
        Cartao entity = CartaoRepository.findById(idCartao);
            entity.setCartaoMarca(CartaoMarca.valueOf(dto.idCartaoMarca()));
            entity.setnCartao(dto.nCartao());
            entity.setMesValid(dto.mesValid());
            entity.setAnoValid(dto.anoValid());
            entity.setNumSeguranca(hashService.getHashSenha(dto.numSeguranca()));
            entity.setNome(dto.nome());
            entity.setSobrenome(dto.sobrenome());
            entity.setCep(dto.cep());
            entity.setCidade(dto.cidade());
            entity.setPais(dto.pais());
            entity.setCliente(ClienteRepository.findById(dto.dono()));

            CartaoRepository.persist(entity);
        return CartaoResponseDTO.valueOf(entity);
    }
    @Override
    public List<CartaoResponseDTO> getAll() {

        List<Cartao> list = CartaoRepository.findAll().list();
        return list.stream().map(e -> CartaoResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        CartaoRepository.deleteById(id);
    }

    @Override
    public CartaoResponseDTO findById(Long id){
        return CartaoResponseDTO.valueOf(CartaoRepository.findById(id));
    }

    @Override
    public List<CartaoResponseDTO> findByDono(Long dono) {
        List<Cartao> list = CartaoRepository.findByDono(dono).list();
        return list.stream().map(e -> CartaoResponseDTO.valueOf(e)).collect(Collectors.toList());
    }
}
