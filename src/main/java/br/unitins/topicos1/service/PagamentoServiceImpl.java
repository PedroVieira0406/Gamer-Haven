package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.PagamentoDTO;
import br.unitins.topicos1.dto.PagamentoResponseDTO;
import br.unitins.topicos1.model.Cartao;
import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.Compra;
import br.unitins.topicos1.model.Jogo;
import br.unitins.topicos1.model.ModoPagamento;
import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.repository.CartaoRepository;
import br.unitins.topicos1.repository.ClienteRepository;
import br.unitins.topicos1.repository.CompraRepository;
import br.unitins.topicos1.repository.PagamentoRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService {

    @Inject
    public PagamentoRepository PagamentoRepository;

    @Inject
    public CompraRepository CompraRepository;

    @Inject
    public ClienteRepository clienteRepository;
    
    @Inject
    public CartaoRepository CartaoRepository;

    
    @Override
    @Transactional
    public PagamentoResponseDTO create(@Valid PagamentoDTO dto){
        Pagamento entity = new Pagamento();
        Compra compra = CompraRepository.findById(entity.getId());
        
        entity.setCliente(acharPagante(dto.idCliente()));
        entity.setValorCompra(dto.valorCompra());
        entity.setValorPago(dto.valorPago());
        entity.setStatus(dto.status());

        entity.setModoPagamento(ModoPagamento.valueOf(dto.modoPagamento()));
        entity.setCartao(acharCartao(dto.nCartao(),entity.getCliente().getId(),entity.getModoPagamento()));

        if(entity.isStatus()){
            //colocando o jogo na biblioteca do dono

            List<Jogo> jogos = new ArrayList<>();
            if (compra.getDono().getBiblioteca() != null) jogos = compra.getDono().getBiblioteca();
            jogos.add(compra.getJogo());
            compra.getDono().setBiblioteca(jogos);
        }

        PagamentoRepository.persist(entity);
        return PagamentoResponseDTO.valueOf(entity);
    }
    @Override
    @Transactional
    public PagamentoResponseDTO update(Long id, PagamentoDTO dto){
        
        Pagamento entity = PagamentoRepository.findById(id);
        PanacheQuery<Compra> query = CompraRepository.findByPagamento(entity.getId());
        Compra compra = query.firstResult();
        entity.setCliente(acharPagante(dto.idCliente()));
        entity.setStatus(dto.status());
        entity.setValorPago(dto.valorPago());
        entity.setValorPago(dto.valorPago());
        entity.setModoPagamento(ModoPagamento.valueOf(dto.modoPagamento()));
        entity.setCartao(acharCartao(dto.nCartao(),entity.getCliente().getId(),entity.getModoPagamento()));
        if(entity.isStatus()){
            //colocando o jogo na biblioteca do dono

            List<Jogo> jogos = new ArrayList<>();
            if (compra.getDono().getBiblioteca() != null) jogos = compra.getDono().getBiblioteca();
            jogos.add(compra.getJogo());
            compra.getDono().setBiblioteca(jogos);
        }

        return PagamentoResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public PagamentoResponseDTO efetuarPagamento(Long id, PagamentoDTO dto){
        
        Pagamento entity = PagamentoRepository.findById(id);
        PanacheQuery<Compra> query = CompraRepository.findByPagamento(entity.getId());
        Compra compra = query.firstResult();
        
        entity.setValorPago(dto.valorPago());
        if(entity.getValorPago()>= compra.getPreco()){
            entity.setStatus(true);
        }
        entity.setModoPagamento(ModoPagamento.valueOf(dto.modoPagamento()));
        entity.setCartao(acharCartao(dto.nCartao(),entity.getCliente().getId(),entity.getModoPagamento()));
    
        return PagamentoResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        PagamentoRepository.deleteById(id);
    }

    @Override
    public List<PagamentoResponseDTO> getAll() {
    List<Pagamento> pagamentos = PagamentoRepository.findAll().list();
    List<PagamentoResponseDTO> responseDTOs = new ArrayList<>();

    for (Pagamento pagamento : pagamentos) {
        PagamentoResponseDTO dto = PagamentoResponseDTO.valueOf(pagamento);
        responseDTOs.add(dto);
    }

    return responseDTOs;
}

    private Cliente acharPagante(long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente);
        if (cliente == null) {
            throw new NotFoundException("Cliente não encontrada.");
        }
        return cliente;
    }

    private Cartao acharCartao(String numero, long idCliente, ModoPagamento modoPagamento) {
        // Verifica se o modo de pagamento é PIX
        if (modoPagamento == ModoPagamento.PIX) {
            return null; // Retorna null se o modo de pagamento for PIX
        }
    
        // Busca o cartão pelo número e id do cliente
        PanacheQuery<Cartao> query = CartaoRepository.findByNumeroECliente(numero, idCliente);
        Cartao cartao = query.firstResult();
    
        // Verifica se o cartão foi encontrado
        if (cartao == null) {
            throw new NotFoundException("Cartao não encontrado.");
        }
    
        return cartao; // Retorna o cartão encontrado
    }


    @Override
    public PagamentoResponseDTO findById(Long id) {
        return PagamentoResponseDTO.valueOf(PagamentoRepository.findById(id));
    }


    @Override
    public List<PagamentoResponseDTO> findAll(){
        return PagamentoRepository.listAll().stream().map(Pagamento -> PagamentoResponseDTO.valueOf(Pagamento)).toList();
    }
    @Override
    public List<PagamentoResponseDTO> findByCliente(Long idCliente) {
        return PagamentoRepository.findByPagante(idCliente).stream()
        .map(e -> PagamentoResponseDTO.valueOf(e)).toList();

    }
}
