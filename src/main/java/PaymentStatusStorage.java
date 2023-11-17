import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//Хранилище для статуса, обычная оплата
public class PaymentStatusStorage {
    public Status status;
}
