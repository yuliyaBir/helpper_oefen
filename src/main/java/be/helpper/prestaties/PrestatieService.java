package be.helpper.prestaties;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PrestatieService {
    @Inject
    PrestatieRepository prestatieRepository;
    public void createPrestatie(Prestatie prestatie){
        prestatieRepository.createPrestatie(prestatie);
    }
    public Optional<Prestatie> findPrestatieById(long id){ return prestatieRepository.findPrestatieById(id);}
    public List<Prestatie> findPrestatiesZonderGoedkeuring(){
        return prestatieRepository.lijstVanPrestatiesZonderGoedkeuring();
    }
    public int aantalPristatiesZonderGoedkeuring(){
        return prestatieRepository.aantalPristatiesZonderGoedkeuring();
    }
    public List<Prestatie> lijstVanPrestatiesMetGoedkeuring(){
        return prestatieRepository.lijstVanPrestatiesMetGoedkeuring();
    }
    public void deletePrestatie(long id){
        prestatieRepository.deletePrestatie(id);
    }
}
