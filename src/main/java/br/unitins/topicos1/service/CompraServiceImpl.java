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
import io.quarkus.hibernate.orm.panache.PanacheQuery;
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
        if (jogoJaNaBiblioteca(compra.getDono(), compra.getJogo().getId())) {
            throw new IllegalStateException("O jogo já está na biblioteca do dono.");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setCliente(compra.getPagante());
        pagamento.setValorCompra(valor);
        pagamento.setValorPago(0.0f);
        pagamento.setCartao(null);
        pagamento.setModoPagamento(ModoPagamento.valueOf(1));
        

        compraRepository.persist(compra);
        return CompraResponseDTO.valueOf(compra);
    }

    @Transactional
    public void ativandoJogo(Pagamento pagamento) {
        // Verifica se o pagamento foi bem-sucedido
        if (pagamento.isStatus()) {
            // Obtém a compra associada ao pagamento
            PanacheQuery<Compra> query = compraRepository.findByPagamento(pagamento.getId());
            Compra compra = query.firstResult();

            // Verifica se a compra existe
            if (compra != null) {
                // Obtém a biblioteca do dono da compra
                List<Jogo> jogos = compra.getDono().getBiblioteca();

                // Verifica se a biblioteca é nula e inicializa se necessário
                if (jogos == null) {
                    jogos = new ArrayList<>();
                }

                // Adiciona o jogo à biblioteca do dono
                jogos.add(compra.getJogo());
                compra.getDono().setBiblioteca(jogos);

                // Atualiza a compra no repositório (ou o dono, se necessário)
                compraRepository.persist(compra);
            } else {
                throw new NotFoundException("Compra não encontrada para o pagamento fornecido.");
            }
        }
    }

    private boolean jogoJaNaBiblioteca(Cliente dono, Long idJogo) {
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