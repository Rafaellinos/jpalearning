package br.com.rafaellinos.core.usecase;

import br.com.rafaellinos.core.domain.PageableDomain;
import br.com.rafaellinos.core.domain.Pessoa;
import br.com.rafaellinos.core.repository.PessoaRepository;
import br.com.rafaellinos.core.specification.PessoaSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("PessoaUseCaseImpl - Testes Unitários")
class PessoaUseCaseImplTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaUseCaseImpl pessoaUseCase;

    private Pessoa pessoa;
    private PessoaSpecification specification;

    @BeforeEach
    void setUp() {
        pessoa = new Pessoa();
        pessoa.setPessoaId(UUID.randomUUID());
        pessoa.setNome("João");
        pessoa.setSobrenome("Silva");
        pessoa.setIdade(30);

        specification = PessoaSpecification.builder()
                .withNome("João")
                .withPageNumber(0)
                .withPageSize(10)
                .build();
    }

    @Test
    @DisplayName("Deve salvar uma pessoa com sucesso")
    void deveSalvarPessoaComSucesso() {
        // Given
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        // When
        Pessoa pessoaSalva = pessoaUseCase.save(pessoa);

        // Then
        assertThat(pessoaSalva).isNotNull();
        assertThat(pessoaSalva.getPessoaId()).isEqualTo(pessoa.getPessoaId());
        assertThat(pessoaSalva.getNome()).isEqualTo("João");
        assertThat(pessoaSalva.getSobrenome()).isEqualTo("Silva");
        assertThat(pessoaSalva.getIdade()).isEqualTo(30);

        verify(pessoaRepository, times(1)).save(pessoa);
    }

    @Test
    @DisplayName("Deve passar o objeto pessoa corretamente para o repository ao salvar")
    void devePassarObjetoPessoaCorretamenteParaRepository() {
        // Given
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        // When
        pessoaUseCase.save(pessoa);

        // Then
        verify(pessoaRepository, times(1)).save(pessoa);
        verifyNoMoreInteractions(pessoaRepository);
    }

    @Test
    @DisplayName("Deve retornar o mesmo objeto retornado pelo repository ao salvar")
    void deveRetornarMesmoObjetoRetornadoPeloRepository() {
        // Given
        Pessoa pessoaEsperada = new Pessoa();
        pessoaEsperada.setPessoaId(UUID.randomUUID());
        pessoaEsperada.setNome("Maria");
        pessoaEsperada.setSobrenome("Santos");

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoaEsperada);

        // When
        Pessoa resultado = pessoaUseCase.save(pessoa);

        // Then
        assertThat(resultado).isEqualTo(pessoaEsperada);
        assertThat(resultado.getNome()).isEqualTo("Maria");
        assertThat(resultado.getSobrenome()).isEqualTo("Santos");
    }

    @Test
    @DisplayName("Deve buscar pessoas com sucesso usando specification")
    void deveBuscarPessoasComSucesso() {
        // Given
        PageableDomain<Pessoa> pageableDomain = new PageableDomain<>();
        pageableDomain.setContent(List.of(pessoa));
        pageableDomain.setTotalPages(1);
        pageableDomain.setTotalElements(1);

        when(pessoaRepository.get(any(PessoaSpecification.class))).thenReturn(pageableDomain);

        // When
        PageableDomain<Pessoa> resultado = pessoaUseCase.getPessoa(specification);

        // Then
        assertThat(resultado).isNotNull();
        assertThat(resultado.getContent()).hasSize(1);
        assertThat(resultado.getContent().get(0).getNome()).isEqualTo("João");
        assertThat(resultado.getTotalPages()).isEqualTo(1);
        assertThat(resultado.getTotalElements()).isEqualTo(1);

        verify(pessoaRepository, times(1)).get(specification);
    }

    @Test
    @DisplayName("Deve passar a specification corretamente para o repository")
    void devePassarSpecificationCorretamenteParaRepository() {
        // Given
        PageableDomain<Pessoa> pageableDomain = new PageableDomain<>();
        pageableDomain.setContent(List.of());
        pageableDomain.setTotalPages(0);
        pageableDomain.setTotalElements(0);

        when(pessoaRepository.get(any(PessoaSpecification.class))).thenReturn(pageableDomain);

        // When
        pessoaUseCase.getPessoa(specification);

        // Then
        verify(pessoaRepository, times(1)).get(specification);
        verifyNoMoreInteractions(pessoaRepository);
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não há pessoas")
    void deveRetornarListaVaziaQuandoNaoHaPessoas() {
        // Given
        PageableDomain<Pessoa> pageableDomain = new PageableDomain<>();
        pageableDomain.setContent(List.of());
        pageableDomain.setTotalPages(0);
        pageableDomain.setTotalElements(0);

        when(pessoaRepository.get(any(PessoaSpecification.class))).thenReturn(pageableDomain);

        // When
        PageableDomain<Pessoa> resultado = pessoaUseCase.getPessoa(specification);

        // Then
        assertThat(resultado).isNotNull();
        assertThat(resultado.getContent()).isEmpty();
        assertThat(resultado.getTotalPages()).isZero();
        assertThat(resultado.getTotalElements()).isZero();
    }

    @Test
    @DisplayName("Deve retornar múltiplas pessoas quando existem")
    void deveRetornarMultiplasPessoas() {
        // Given
        Pessoa pessoa2 = new Pessoa();
        pessoa2.setPessoaId(UUID.randomUUID());
        pessoa2.setNome("Maria");
        pessoa2.setSobrenome("Oliveira");
        pessoa2.setIdade(25);

        Pessoa pessoa3 = new Pessoa();
        pessoa3.setPessoaId(UUID.randomUUID());
        pessoa3.setNome("José");
        pessoa3.setSobrenome("Santos");
        pessoa3.setIdade(35);

        PageableDomain<Pessoa> pageableDomain = new PageableDomain<>();
        pageableDomain.setContent(List.of(pessoa, pessoa2, pessoa3));
        pageableDomain.setTotalPages(1);
        pageableDomain.setTotalElements(3);

        when(pessoaRepository.get(any(PessoaSpecification.class))).thenReturn(pageableDomain);

        // When
        PageableDomain<Pessoa> resultado = pessoaUseCase.getPessoa(specification);

        // Then
        assertThat(resultado).isNotNull();
        assertThat(resultado.getContent()).hasSize(3);
        assertThat(resultado.getContent())
                .extracting(Pessoa::getNome)
                .containsExactly("João", "Maria", "José");
        assertThat(resultado.getTotalPages()).isEqualTo(1);
        assertThat(resultado.getTotalElements()).isEqualTo(3);
    }

    @Test
    @DisplayName("Deve lidar com paginação corretamente")
    void deveLidarComPaginacaoCorretamente() {
        // Given
        PessoaSpecification specPagina2 = PessoaSpecification.builder()
                .withNome("João")
                .withPageNumber(1)
                .withPageSize(5)
                .build();

        PageableDomain<Pessoa> pageableDomain = new PageableDomain<>();
        pageableDomain.setContent(List.of(pessoa));
        pageableDomain.setTotalPages(3);
        pageableDomain.setTotalElements(1);

        when(pessoaRepository.get(any(PessoaSpecification.class))).thenReturn(pageableDomain);

        // When
        PageableDomain<Pessoa> resultado = pessoaUseCase.getPessoa(specPagina2);

        // Then
        assertThat(resultado).isNotNull();
        assertThat(resultado.getTotalPages()).isEqualTo(3);
        assertThat(resultado.getContent()).hasSize(1);

        verify(pessoaRepository, times(1)).get(specPagina2);
    }

    @Test
    @DisplayName("Deve buscar pessoas com specification complexa")
    void deveBuscarPessoasComSpecificationComplexa() {
        // Given
        PessoaSpecification specCompleta = PessoaSpecification.builder()
                .withId(UUID.randomUUID())
                .withNome("João")
                .withSobrenome("Silva")
                .withPageNumber(0)
                .withPageSize(10)
                .withSearchEmail(true)
                .withSearchTelefone(true)
                .withSearchEndereco(true)
                .build();

        PageableDomain<Pessoa> pageableDomain = new PageableDomain<>();
        pageableDomain.setContent(List.of(pessoa));
        pageableDomain.setTotalPages(1);
        pageableDomain.setTotalElements(1);

        when(pessoaRepository.get(any(PessoaSpecification.class))).thenReturn(pageableDomain);

        // When
        PageableDomain<Pessoa> resultado = pessoaUseCase.getPessoa(specCompleta);

        // Then
        assertThat(resultado).isNotNull();
        assertThat(resultado.getContent()).hasSize(1);

        verify(pessoaRepository, times(1)).get(specCompleta);
    }

    @Test
    @DisplayName("Deve salvar pessoa com dados mínimos")
    void deveSalvarPessoaComDadosMinimos() {
        // Given
        Pessoa pessoaMinima = new Pessoa();
        pessoaMinima.setNome("Ana");

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoaMinima);

        // When
        Pessoa resultado = pessoaUseCase.save(pessoaMinima);

        // Then
        assertThat(resultado).isNotNull();
        assertThat(resultado.getNome()).isEqualTo("Ana");

        verify(pessoaRepository, times(1)).save(pessoaMinima);
    }

    @Test
    @DisplayName("Deve retornar o mesmo objeto PageableDomain do repository")
    void deveRetornarMesmoPageableDomainDoRepository() {
        // Given
        PageableDomain<Pessoa> pageableEsperado = new PageableDomain<>();
        pageableEsperado.setContent(List.of(pessoa));
        pageableEsperado.setTotalPages(5);
        pageableEsperado.setTotalElements(2);

        when(pessoaRepository.get(any(PessoaSpecification.class))).thenReturn(pageableEsperado);

        // When
        PageableDomain<Pessoa> resultado = pessoaUseCase.getPessoa(specification);

        // Then
        assertThat(resultado).isSameAs(pageableEsperado);
        assertThat(resultado.getTotalPages()).isEqualTo(5);
        assertThat(resultado.getTotalElements()).isEqualTo(2);
    }
}