package br.com.boligon.filipe.conectapr.conectautils.core.endpoint;

import br.com.boligon.filipe.conectapr.conectautils.core.domain.EntidadePadrao;
import br.com.boligon.filipe.conectapr.conectautils.core.service.ServicoGenerico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

public abstract class EndpointGenerico<T extends EntidadePadrao> {
    protected abstract ServicoGenerico<T> getServico();

}
