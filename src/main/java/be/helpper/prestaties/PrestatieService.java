package be.helpper.prestaties;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PrestatieService {
    @Inject
    PrestatieRepository prestatieRepository;
    public long createPrestatie(Prestatie prestatie){
        return prestatieRepository.createPrestatie(prestatie);
    }
    public Optional<Prestatie> findPrestatieById(long id){ return prestatieRepository.findPrestatieById(id);}
    public List<Prestatie> lijstVanPrestatiesZonderGoedkeuringVoorBepaaldeAssistent(long assistentId){
        return prestatieRepository.lijstVanPrestatiesZonderGoedkeuringVoorBepaaldeAssistent(assistentId);
    }
    public List<Prestatie> lijstVanPrestatiesMetGoedkeuringVoorBepaaldeBudgethouder(long budgethouderId){
        return prestatieRepository.lijstVanPrestatiesMetGoedkeuringVoorBepaaldeBudgethouder(budgethouderId);
    }
    public List<Prestatie> lijstVanPrestatiesZonderGoedkeuringVoorBepaaldeBudgethouder(long budgethouderId){
        return prestatieRepository.lijstVanPrestatiesZonderGoedkeuringVoorBepaaldeBudgethouder(budgethouderId);
    }
    public List<Prestatie> lijstVanPrestatiesMetGoedkeuringVoorBepaaldeAssistent(long assistentId){
        return prestatieRepository.lijstVanPrestatiesMetGoedkeuringVoorBepaaldeAssistent(assistentId);
    }
    public void deletePrestatie(long id){
        prestatieRepository.deletePrestatie(id);
    }
}
