package pl.coderslab.charity.Service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.Model.Institution;
import pl.coderslab.charity.Repository.InstitutionRepository;

import java.util.List;

@Service
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public List<Institution> listAll() {
        return (List<Institution>) institutionRepository.findAll();
    }
}
