package be.helpper.requests;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class RequestService {
    @Inject
    RequestRepository requestRepository;
    @Transactional
    public long createRequest(Request request){
        return requestRepository.createRequest(request);
    }
}
