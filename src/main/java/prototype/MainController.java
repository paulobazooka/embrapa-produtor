package prototype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    PessoaRepository pessoaRepository;


    @GetMapping
    public String bemVindo(){
        return "Olá Mundo!";
    }

    @GetMapping("/listar")
    public Iterable<Pessoa> listarPessoas(){
        return pessoaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pessoa buscarPessoa(@PathVariable Long id){
        return pessoaRepository.findById(id).get();
    }

    @PostMapping
    public Pessoa cadastrarPessoa(@Valid @RequestBody Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    @PutMapping
    public Pessoa alterarPessoa(@Valid @RequestBody Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePessoa(@PathVariable Long id){
        pessoaRepository.deleteById(id);
        return ResponseEntity.ok().body("Pessoa removida do banco de dados");
    }

}
