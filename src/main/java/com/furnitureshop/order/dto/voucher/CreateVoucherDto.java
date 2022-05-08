package com.furnitureshop.order.dto.voucher;

import lombok.Getter;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
public class CreateVoucherDto {
    @Size(max = 100, message = "Category description must be less than 100 characters")
    private String voucherDesc;

    @NotNull(message = "Amount must not be null")
    @Positive(message = "Amount must greater than 0")
    private Integer amount;

    @NotNull(message = "Valid date must not be null")
    @FutureOrPresent
    private LocalDateTime validDate;

    @NotNull(message = "Expiration date must not be null")
    @Future
    private LocalDateTime expirationDate;

    @NotNull(message = "Voucher value must not be null")
    @Positive(message = "Voucher value must greater than 0")
    private Integer voucherValue;

    @NotNull(message = "Capped at must not be null")
    @PositiveOrZero
    private Integer cappedAt;
}
