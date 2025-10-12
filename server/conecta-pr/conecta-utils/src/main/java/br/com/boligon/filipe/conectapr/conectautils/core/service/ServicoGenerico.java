package br.com.boligon.filipe.conectapr.conectautils.core.service;

import br.com.boligon.filipe.conectapr.conectautils.core.domain.EntidadePadrao;
import br.com.boligon.filipe.conectapr.conectautils.core.repository.RepositorioGenerico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public abstract class ServicoGenerico<T extends EntidadePadrao> {
    protected abstract RepositorioGenerico<T> getRepositorio();

    @Transactional(readOnly = true)
    public List<T> buscarTodos() {
        return getRepositorio().findAll();
    }

    @Transactional(readOnly = true)
    public Page<T> buscarTodos(Pageable paginacao) {
        return getRepositorio().findAll(paginacao);
    }

    @Transactional(readOnly = true)
    public List<T> buscar(Specification<T> spec) {
        return getRepositorio().findAll(spec);
    }

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

    @Transactional(readOnly = true)
    public T salvar(T entidade) {
        T resultado = getRepositorio().save(entidade);
        return resultado;
    }

    @Transactional(readOnly = true)
    public List<T> salvarTodos(List<T> entidades) {
        List<T> resultados = getRepositorio().saveAll(entidades);
        return resultados;
    }

    @Transactional(readOnly = true)
    public void excluir(T entidade) {
        getRepositorio().delete(entidade);
    }

    @Transactional(readOnly = true)
    public void excluir(Long id) {
        getRepositorio().deleteById(id);
    }


}
