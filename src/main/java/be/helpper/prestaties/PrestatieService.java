package be.helpper.prestaties;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class PrestatieService {
    @Inject
    PrestatieRepository prestatieRepository;
    public long createPrestatie(Prestatie prestatie){
        return prestatieRepository.createPrestatie(prestatie);
    }
    public Prestatie findPrestatieById(long id){ return prestatieRepository.findPrestatieById(id);}
    public List<Prestatie> findPrestatiesZonderGoedkeuring(){
        return prestatieRepository.lijstVanPrestatiesZonderGoedkeuring();
    }
    public List<Prestatie> lijstVanPrestatiesMetGoedkeuring(){
        return prestatieRepository.lijstVanPrestatiesMetGoedkeuring();
    }
}
