package br.com.webflux.domain.event;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvent {

	private Long eventId;
	
	private String eventType;
	
	
	

	@Override
	public String toString() {
		return "ProductEvent [eventId=" + eventId + ", eventType=" + eventType + "]";
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductEvent)) return false;
        ProductEvent that = (ProductEvent) o;
        return Objects.equals(eventId, that.eventId) &&
                Objects.equals(eventType, that.eventType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventType);
    }
	
}
