package be.helpper.requests;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class RequestRepository {
    @Inject
    EntityManager em;
    public long createRequest(Request request){
        em.persist(request);
        return request.getRequestId();
    }
}
