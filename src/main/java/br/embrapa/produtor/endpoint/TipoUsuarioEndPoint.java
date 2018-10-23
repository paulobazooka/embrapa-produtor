package br.embrapa.produtor.endpoint;


import br.embrapa.produtor.constants.TipoUsuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tipousuario")
public class TipoUsuarioEndPoint {

    @GetMapping
    public List<Tipo> getTipoUsuario(){

        Tipo adm = new Tipo(TipoUsuario.ADMINISTRADOR.toString());
        Tipo pesq = new Tipo(TipoUsuario.PESQUISADOR.toString());
        Tipo prod = new Tipo(TipoUsuario.PRODUTOR.toString());

        List<Tipo> listaTiposUsuarios = new ArrayList<>();
        listaTiposUsuarios.add(adm);
        listaTiposUsuarios.add(pesq);
        listaTiposUsuarios.add(prod);

        return listaTiposUsuarios;
    }




    private class Tipo {
        private String tipo;

        public Tipo(String tipo) {
            this.tipo = tipo;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }
    }
}
