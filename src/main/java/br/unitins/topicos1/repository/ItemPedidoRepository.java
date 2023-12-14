package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.ItemPedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemPedidoRepository implements PanacheRepository<ItemPedido>{
    
}
