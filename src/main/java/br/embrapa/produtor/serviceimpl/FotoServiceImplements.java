package br.embrapa.produtor.serviceimpl;

import br.embrapa.produtor.models.Foto;
import br.embrapa.produtor.repositories.FotoRepository;
import br.embrapa.produtor.services.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoServiceImplements implements FotoService {

    @Autowired
    FotoRepository fotoRepository;

    @Override
    public Iterable<Foto> listarTodasFotos() {
        return this.fotoRepository.findAll();
    }

    @Override
    public Foto cadastrarFoto(Foto foto) {
        return this.fotoRepository.save(foto);
    }

    @Override
    public Foto buscarFoto(Long id) {
        return this.fotoRepository.findById(id).get();
    }
}
