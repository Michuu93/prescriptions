package pl.michuu93.prescriptions.drug;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class DrugsUpdateResponse {
    private int inactivated;
    private int active;
}