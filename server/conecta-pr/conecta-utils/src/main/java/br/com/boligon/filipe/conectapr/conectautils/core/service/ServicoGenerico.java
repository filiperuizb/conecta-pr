package br.com.boligon.filipe.conectapr.conectautils.core.service;

import br.com.boligon.filipe.conectapr.conectautils.core.domain.EntidadePadrao;
import br.com.boligon.filipe.conectapr.conectautils.core.repository.RepositorioGenerico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class ServicoGenerico<T extends EntidadePadrao> {
    protected abstract RepositorioGenerico<T> getRepositorio();

    @Transactional(readOnly = true)
    public List<T> buscarTodos() {
        return getRepositorio().findAll();
    }

    // TO-DO - Implementar paginação
    @Transactional(readOnly = true)
    public Page<T> buscarTodos(Pageable paginacao) {
        return getRepositorio().findAll(paginacao);
    }

    // TO-DO - Implementar paginação com Specification
    @Transactional(readOnly = true)
    public List<T> buscar(Specification<T> spec) {
        return getRepositorio().findAll(spec);
    }

    // TO-DO - Implementar paginação com Specification e Pageable
    @Transactional(readOnly = true)
    public Page<T> buscar(Specification<T> spec, Pageable paginacao) {
        return getRepositorio().findAll(spec, paginacao);
    }

    @Transactional(readOnly = true)
    public Optional<T> buscarPorId(Long id) {
        return getRepositorio().findById(id);
    }

    @Transactional(readOnly = true)
    public Long contarRegistros(Specification<T> spec) {
        return getRepositorio().count(spec);
    }

    @Transactional
    public T salvar(T entidade) {
        return getRepositorio().save(entidade);
    }

    @Transactional
    public void excluir(Long id) {
        if(getRepositorio().existsById(id)) {
            getRepositorio().deleteById(id);
        } else {
            throw new RuntimeException("Registro não encontrado");
        }

    }

    @Transactional
    public T atualizar(T entidade, Long id) {
        return getRepositorio().findById(id)
                .map(entidadeExistente -> {
                    entidade.setId(id);
                    return getRepositorio().save(entidade);
                })
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));
    }
}
