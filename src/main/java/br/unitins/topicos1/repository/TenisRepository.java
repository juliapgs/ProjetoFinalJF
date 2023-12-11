package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Tenis;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TenisRepository implements PanacheRepository<Tenis> {
    public List<Tenis> findByModelo(String modelo) {
        return find("UPPER(modelo) LIKE UPPER(?1)", "%" + modelo + "%").list();
    }
}
