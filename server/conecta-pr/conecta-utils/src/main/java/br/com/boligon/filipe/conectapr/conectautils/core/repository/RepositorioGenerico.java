package br.com.boligon.filipe.conectapr.conectautils.core.repository;

import br.com.boligon.filipe.conectapr.conectautils.core.domain.EntidadePadrao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositorioGenerico<T extends EntidadePadrao> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
    default Class<T> getClasseEntidade() {
        throw new UnsupportedOperationException("Método getClasseEntidade() deve ser implementado pela classe concreta");
    }
}
