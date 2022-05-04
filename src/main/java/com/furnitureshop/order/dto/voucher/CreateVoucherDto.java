package com.furnitureshop.order.dto.voucher;

import lombok.Getter;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
public class CreateVoucherDto {
    @Size(max = 100, message = "Category description must be less than 100 characters")
    private String voucherDesc;

    @PositiveOrZero
    private Integer amount;

    @FutureOrPresent
    private LocalDateTime validDate;

    @Future
    private LocalDateTime expirationDate;

    @PositiveOrZero
    private Integer voucherValue;

    @PositiveOrZero
    private Integer cappedAt;
}
