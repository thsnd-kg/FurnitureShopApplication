package com.furnitureshop.order.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.order.dto.voucher.CreateVoucherDto;
import com.furnitureshop.order.dto.voucher.GetVoucherDto;
import com.furnitureshop.order.dto.voucher.UpdateVoucherDto;
import com.furnitureshop.order.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/vouchers")
public class VoucherController {
    private final VoucherService service;

    @Autowired
    public VoucherController(VoucherService service) {
        this.service = service;
    }

    @GetMapping
    public Object getVouchers(@RequestParam(value = "onlyActive") Boolean isActive) {
        try {
            if (isActive) {
                List<GetVoucherDto> vouchers = service.getVoucherActive()
                        .stream().map(GetVoucherDto::new)
                        .sorted(Comparator.comparing(GetVoucherDto::getVoucherId))
                        .collect(Collectors.toList());
                return ResponseHandler.getResponse(vouchers, HttpStatus.OK);
            }

            List<GetVoucherDto> vouchers = service.getVouchers()
                    .stream().map(GetVoucherDto::new)
                    .sorted(Comparator.comparing(GetVoucherDto::getVoucherId))
                    .collect(Collectors.toList());
            return ResponseHandler.getResponse(vouchers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{voucher-id}")
    public Object getVoucher(@PathVariable("voucher-id") Long voucherId) {
        try {
            GetVoucherDto voucher = new GetVoucherDto(service.getVoucherById(voucherId));
            return ResponseHandler.getResponse(voucher, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{voucher-name}")
    public Object getVoucherByName(@PathVariable("voucher-name") String voucherName) {
        try {
            GetVoucherDto voucher = new GetVoucherDto(service.getVoucherByName(voucherName));
            return ResponseHandler.getResponse(voucher, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public Object createVoucher(@Valid @RequestBody CreateVoucherDto dto, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            GetVoucherDto voucher = new GetVoucherDto(service.createVoucher(dto));
            return ResponseHandler.getResponse(voucher, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public Object updateVoucher(@Valid @RequestBody UpdateVoucherDto dto, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            GetVoucherDto product = new GetVoucherDto(service.updateVoucher(dto));
            return ResponseHandler.getResponse(product, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{voucher-id}")
    public Object deleteVoucher(@PathVariable("voucher-id") Long voucherId) {
        try {
            return ResponseHandler.getResponse(service.deleteVoucher(voucherId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }
}
