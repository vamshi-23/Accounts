package com.easybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name= "Customer",
        description = "This schema holds Customer & Account details in Easy Bank",
        requiredProperties = {"name", "email", "mobileNumber"})
public class CustomerDTO {

    @Schema(
            description = "Name of the customer",
            example = "John Doe",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "Name cannot be empty or null")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Schema(
            description = "Email address of the customer",
            example = "example@easybank.com")
    @NotEmpty(message = "Email cannot be empty or null")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(
            description = "Mobile number of the customer",
            example = "1234567890",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @Pattern(message = "Mobile number must be 10 digits", regexp = "^[0-9]{10}$")
    @NotEmpty(message = "Mobile number cannot be empty or null")
    private String mobileNumber;

    @Schema(
            description = "Accounts details associated with the customer")
    private AccountsDTO accountsDTO;
}
