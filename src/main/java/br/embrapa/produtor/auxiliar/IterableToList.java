package br.embrapa.produtor.auxiliar;

import br.embrapa.produtor.models.Solicitacao;

import java.util.ArrayList;
import java.util.List;

/**
 *  Classe auxiliar para transformar Iterable em List
 *
 * */
public class IterableToList {


    public static <E> List<E> toList(Iterable<E> iterable) {

        if(iterable instanceof List) {
            return (List<E>) iterable;
        }

        ArrayList<E> list = new ArrayList<E>();

        if(iterable != null) {
            for(E e: iterable) {
                list.add(e);
            }
        }

        return list;
    }
}
