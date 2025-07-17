package com.easybank.accounts.service;

import com.easybank.accounts.dto.CustomerDTO;

public interface IAccountsService {
    /**
     * Creates a new customer account.
     *
     * @param customerDTO the data transfer object containing customer details
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     * Fetches account details for a given account ID.
     *
     * @param mobileNumber the ID of the account to fetch details for
     * @return a CustomerDTO containing the account details
     */
    CustomerDTO fetchAccountDetails(String mobileNumber);

    /**
     * Updates an existing customer account.
     *
     * @param customerDTO the data transfer object containing updated customer details
     * @return true if the account was updated successfully, false otherwise
     */
    boolean updateAccount(CustomerDTO customerDTO);

    /**
     * Deletes a customer account.
     *
     * @param mobileNumber the mobile number of the customer whose account is to be deleted
     * @return true if the account was deleted successfully, false otherwise
     */
    boolean deleteAccount(String mobileNumber);
}
