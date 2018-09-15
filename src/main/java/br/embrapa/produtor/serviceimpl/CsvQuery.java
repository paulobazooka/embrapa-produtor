package br.embrapa.produtor.serviceimpl;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class CsvQuery {

    @PersistenceContext
    private EntityManager em;

    public List<Object[]> listaInteligencia(){

        String sql = "SELECT u.id as produtor_id, " +
                "u.nome as produtor, " +
                "s.data_requisicao as data_requisicao, " +
                "f.caminho_foto as caminho, " +
                "cl.nome as cultura, " +
                "d.doenca as doenca, " +
                "p.id as pesquisador_id, " +
                "p.nome as pesquisador " +
                "FROM usuario u " +
                "INNER JOIN solicitacao s ON u.id = s.produtor_id " +
                "INNER JOIN solicitacao_fotos sf ON s.id = sf.solicitacao_id " +
                "INNER JOIN foto f ON sf.fotos_id = f.id " +
                "INNER JOIN foto_comentarios fc ON f.id = fc.foto_id " +
                "INNER JOIN comentario c ON fc.comentarios_id = c.id " +
                "INNER JOIN doenca d ON c.doenca_id = d.id " +
                "INNER JOIN usuario p ON c.pesquisador_id = p.id " +
                "INNER JOIN cultura cl ON s.cultura_id = cl.id";

        Query query = em.createNativeQuery(sql);

        List<Object[]> rows = query.getResultList();

        return rows;
    }

}
