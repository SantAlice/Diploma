
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//Хранилище для статуса, оплата в кредит
public class CreditStatusStorage {
    public Status status;
}