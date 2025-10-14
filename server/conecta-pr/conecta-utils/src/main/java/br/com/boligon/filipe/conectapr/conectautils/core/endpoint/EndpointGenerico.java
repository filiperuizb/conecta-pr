package br.com.boligon.filipe.conectapr.conectautils.core.endpoint;

import br.com.boligon.filipe.conectapr.conectautils.core.domain.EntidadePadrao;
import br.com.boligon.filipe.conectapr.conectautils.core.service.ServicoGenerico;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public abstract class EndpointGenerico<T extends EntidadePadrao> {
    protected abstract ServicoGenerico<T> getServico();

    @GetMapping
    public ResponseEntity<List<T>> listar() {
        return ResponseEntity.ok(getServico().buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> buscarPorId(@PathVariable Long id) {
        return getServico().buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new
                        ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado"));
    }

    @PostMapping
    public ResponseEntity<T> criar(T entidade) {
        T entidadeSalva = getServico().salvar(entidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(entidadeSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> atualizar(@RequestBody T entidade, @PathVariable Long id) {
        T novaEntidade = getServico().atualizar(entidade, id);
        return  ResponseEntity.ok(novaEntidade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<T> remover(@PathVariable Long id) {
        getServico().excluir(id);
        return ResponseEntity.noContent().build();
    }
}
