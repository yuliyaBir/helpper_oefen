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
    public List<Prestatie> prestatiesWithoutGoedkeuringForASpecificAssistent(long assistentId){
        return prestatieRepository.prestatiesWithoutGoedkeuringForASpecificAssistent(assistentId);
    }
    public List<Prestatie> prestatiesWithGoedkeuringForASpecificBudgethouder(long budgethouderId){
        return prestatieRepository.prestatiesWithGoedkeuringForASpecificBudgethouder(budgethouderId);
    }
    public List<Prestatie> prestatiesWithoutGoedkeuringForASpecificBudgethouder(long budgethouderId){
        return prestatieRepository.prestatiesWithoutGoedkeuringForASpecificBudgethouder(budgethouderId);
    }
    public List<Prestatie> prestatiesWithGoedkeuringForASpecificAssistent(long assistentId){
        return prestatieRepository.prestatiesWithGoedkeuringForASpecificAssistent(assistentId);
    }
    public void deletePrestatie(long id){
        prestatieRepository.deletePrestatie(id);
    }
}
