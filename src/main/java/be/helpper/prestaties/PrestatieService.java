package be.helpper.prestaties;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PrestatieService {
    @Inject
    PrestatieRepository prestatieRepository;
    public long createPrestatie(Prestatie prestatie){
        return prestatieRepository.createPrestatie(prestatie);
    }
    public Prestatie findPrestatieById(long id){ return prestatieRepository.findPrestatieById(id);}
}
