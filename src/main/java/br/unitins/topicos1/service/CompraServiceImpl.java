package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.CompraDTO;
import br.unitins.topicos1.dto.CompraResponseDTO;
import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.Compra;
import br.unitins.topicos1.model.Jogo;
import br.unitins.topicos1.model.ModoPagamento;
import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.repository.ClienteRepository;
import br.unitins.topicos1.repository.CompraRepository;
import br.unitins.topicos1.repository.JogoRepository;
import br.unitins.topicos1.repository.PagamentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CompraServiceImpl implements CompraService {


    @Inject
    public CompraRepository compraRepository;

    @Inject
    public JogoRepository jogoRepository;

    @Inject
    public ClienteRepository clienteRepository;

    @Inject
    public PagamentoRepository pagamentoRepository;

    @Override
    @Transactional
    public CompraResponseDTO create(@Valid CompraDTO dto){
        Compra compra = new Compra();

        compra.setPagante(acharPagante(dto.idPagante()));
        compra.setDono(acharDono(dto.idDono()));
        compra.setJogo(acharJogo(dto.idJogo()));

        //pegando valor automaticamente
        float valor = compra.getJogo().getPreco();
        valor = valor - compra.getJogo().getDesconto();
        compra.setPreco(valor);

        //verificando se já tem o jogo na biblioteca de quem receberá o jogo
        if (jogoNaBiblioteca(compra.getDono(), compra.getJogo().getId())) {
            throw new IllegalStateException("O jogo já está na biblioteca do dono.");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setClienteId(compra.getPagante().getId());
        pagamento.setValorCompra(valor);
        pagamento.setValorPago(0.0f);
        pagamento.setCartao(null);
        pagamento.setModoPagamento(ModoPagamento.valueOf(1));
        pagamento.setStatus(false);
        if(valor == 0.0f) pagamento.setStatus(true);

        pagamentoRepository.persist(pagamento);
        compra.setPagamento(pagamento);

        compraRepository.persist(compra);

        //caso o valor seja 0 a compra vai ser efetiva automaticamente
        if(pagamento.isStatus())ativandoJogo(pagamento.getId());

        return CompraResponseDTO.valueOf(compra);
    }

    @Override
    @Transactional
    public CompraResponseDTO update(@Valid CompraDTO dto, Long id){
        Compra compra = compraRepository.findById(id);
        compra.setPagante(acharPagante(dto.idPagante()));
        compra.setDono(acharDono(dto.idDono()));
        compra.setJogo(acharJogo(dto.idJogo()));

        //pegando valor automaticamente
        float valor = compra.getJogo().getPreco();
        valor = valor - compra.getJogo().getDesconto();
        compra.setPreco(valor);

        //verificando se já tem o jogo na biblioteca de quem receberá o jogo
        if (jogoNaBiblioteca(compra.getDono(), compra.getJogo().getId())) {
            throw new IllegalStateException("O jogo já está na biblioteca do dono.");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setClienteId(compra.getPagante().getId());
        pagamento.setValorCompra(valor);
        pagamento.setValorPago(0.0f);
        pagamento.setCartao(null);
        pagamento.setModoPagamento(ModoPagamento.valueOf(1));
        pagamento.setStatus(false);
        if(valor == 0.0f) pagamento.setStatus(true);

        compra.setPagamento(pagamento);

        //caso o valor seja 0 a compra vai ser efetiva automaticamente
        if(pagamento.isStatus())ativandoJogo(pagamento.getId());

        return CompraResponseDTO.valueOf(compra);
    }

    @Transactional
    public void ativandoJogo(Long idPagamento) {
        Pagamento pagamento = acharPagamento(idPagamento);
    // Verifica se o pagamento foi bem-sucedido
        if (pagamento.isStatus()) {
        // Obtém a compra associada ao pagamento
        Compra compra = compraRepository.findByPagamento(pagamento.getId());
        Cliente cliente = compra.getDono();
        List<Jogo> biblioteca = cliente.getBiblioteca();

        // Verifica se a biblioteca é nula e inicializa se necessário
        if (biblioteca == null) {
            biblioteca = new ArrayList<>();
        }

        // Adiciona o jogo à biblioteca do cliente
        biblioteca.add(compra.getJogo());
        cliente.setBiblioteca(biblioteca);

        } else {
            throw new NotFoundException("Pagamento não encontrado para o pagamento fornecido.");
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        compraRepository.deleteById(id);
    }

    private boolean jogoNaBiblioteca(Cliente dono, Long idJogo) {
        if (dono.getBiblioteca() != null) {
            for (Jogo jogo : dono.getBiblioteca()) {
                if (jogo.getId().equals(idJogo)) {
                    return true; // Jogo já está na biblioteca do dono
                }
            }
        }
        return false; // Caso chegue aqui o jogo não existe na biblioteca
    }

    private Cliente acharPagante(long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente);
        if (cliente == null) {
            throw new NotFoundException("cliente não encontrada.");
        }
        return cliente;
    }

    private Pagamento acharPagamento(long idPagamento) {
        Pagamento pagamento = pagamentoRepository.findById(idPagamento);
        if (pagamento == null) {
            throw new NotFoundException("cliente não encontrada.");
        }
        return pagamento;
    }

    private Cliente acharDono(long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente);
        if (cliente == null) {
            throw new NotFoundException("cliente não encontrada.");
        }
        return cliente;
    }

    private Jogo acharJogo(long idJogo) {
        Jogo jogo = jogoRepository.findById(idJogo);
        if (jogo == null) {
            throw new NotFoundException("jogo não encontrada.");
        }
        return jogo;
    }

    @Override
    public CompraResponseDTO findById(Long id) {
        return CompraResponseDTO.valueOf(compraRepository.findById(id));
    }

    @Override
    public List<CompraResponseDTO> getAll() {

        List<Compra> list = compraRepository.findAll().list();
        return list.stream().map(e -> CompraResponseDTO.valueOf(e)).collect(Collectors.toList());
    }
    @Override
    public List<CompraResponseDTO> findAll(){
        return compraRepository.listAll().stream().map(Compra -> CompraResponseDTO.valueOf(Compra)).toList();
    }
    @Override
    public List<CompraResponseDTO> findByPagante(Long idCliente) {
        return compraRepository.findByPagante(idCliente).stream()
        .map(e -> CompraResponseDTO.valueOf(e)).toList();

    }
    @Override
    public List<CompraResponseDTO> findByDono(Long idDono) {
        return compraRepository.findByDono(idDono).stream()
        .map(e -> CompraResponseDTO.valueOf(e)).toList();
    }
    @Override
    public List<CompraResponseDTO> findByJogo(Long idJogo) {
        return compraRepository.findByJogo(idJogo).stream()
        .map(e -> CompraResponseDTO.valueOf(e)).toList();
    }

}