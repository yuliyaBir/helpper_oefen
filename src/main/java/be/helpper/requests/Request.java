package be.helpper.requests;

import jakarta.persistence.*;

@Entity
@Table(name = "requests")
public class Request {
    public Request() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long requestId;
    private String requestBody;

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public Request(long requestId, String requestBody) {
        this.requestId = requestId;
        this.requestBody = requestBody;
    }
}
