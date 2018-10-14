package br.embrapa.produtor.endpoint;

import br.embrapa.produtor.models.Usuario;
import br.embrapa.produtor.serviceimpl.EmailServiceImpl;
import br.embrapa.produtor.serviceimpl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioEndPoint {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @Autowired
    EmailServiceImpl emailService;


    @GetMapping("/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id){
        Optional<Usuario> usuario = Optional.ofNullable(usuarioService.buscarPorId(id));

        if(usuario.isPresent()){
            return ResponseEntity.ok(usuario.get());
        }

        return ResponseEntity.ok(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity postUsuario(@RequestBody Usuario usuario){
        Usuario user = usuarioService.persistir(usuario);

        if(user != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("{mensagem: não foi possível salvar usuário}");
    }


    @PutMapping
    public ResponseEntity putUsuario(@RequestBody Usuario usuario){
        Usuario user = usuarioService.persistir(usuario);

        if(user != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("{mensagem: não foi alterar usuário}");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsuario(@PathVariable Long id){
        Usuario usuario = usuarioService.buscarPorId(id);

        if(usuario != null){
            usuario.setAtivo(false);
            usuarioService.persistir(usuario);
            return ResponseEntity.status(HttpStatus.GONE).body("{mensagem: usuário desativado");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{mensagem: não foi encontrar o usuário}");
    }


}
