package com.easybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "This schema holds account details in Easy Bank",
        requiredProperties = {"accountNumber", "accountType", "branchAddress"})
public class AccountsDTO {

    @Schema(
            description = "Account number of the customer")
    @Pattern( message = "Account number must be 10 digits", regexp = "^[0-9]{10}$")
    @NotEmpty(message = "Account number cannot be empty or null")
    @NotBlank(message = "Account number cannot be blank")
    private Long accountNumber;

    @Schema(
            description = "Type of the account",
            example = "Savings")
    @NotEmpty(message = "Account type cannot be empty or null")
    @NotBlank(message = "Account type cannot be blank")
    private String accountType;

    @Schema(
            description = "Address of the branch where the account is held",
            example = "123 Easy Bank Street, City, Country")
    @NotEmpty(message = "Branch address cannot be empty or null")
    @NotBlank(message = "Branch address cannot be blank")
    private String branchAddress;
}
