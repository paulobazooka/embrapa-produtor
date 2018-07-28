package br.embrapa.produtor.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 *
 * Classe abstrata para aplicar a serialização
 *   do objeto quando for transmitido em forma de bytes
 *
 *
 * */
@MappedSuperclass  // notação para não criar a tabela no banco de dados
public abstract class Abstract implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
