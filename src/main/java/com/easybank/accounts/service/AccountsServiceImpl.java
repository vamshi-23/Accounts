package com.easybank.accounts.service;

import com.easybank.accounts.dto.AccountsDTO;
import com.easybank.accounts.dto.CustomerDTO;
import com.easybank.accounts.entity.Accounts;
import com.easybank.accounts.entity.Customer;
import com.easybank.accounts.exception.CustomerAlreadyExistsException;
import com.easybank.accounts.exception.ResourceNotFoundException;
import com.easybank.accounts.mapper.AccountMapper;
import com.easybank.accounts.mapper.CustomerMapper;
import com.easybank.accounts.repository.AccountsRepository;
import com.easybank.accounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

import static com.easybank.accounts.constants.AccountsConstants.ADDRESS;
import static com.easybank.accounts.constants.AccountsConstants.SAVINGS;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService{

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDTO customerDTO) {

        Customer customer = CustomerMapper.mapToCustomerEntity(customerDTO, new Customer());
        Optional<Customer> optionalCustomer= customerRepository.findByMobileNumber(customer.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with mobile number " + customer.getMobileNumber() + " already registered.");
        }
        Customer savedCust = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCust));

    }

    /**
     * @param mobileNumber the ID of the account to fetch details for
     * @return
     */
    @Override
    public CustomerDTO fetchAccountDetails(String mobileNumber) {
        Customer customer= customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        CustomerDTO customerDTO =CustomerMapper.mapToCustomerDTO(new CustomerDTO(), customer);
        customerDTO.setAccountsDTO(AccountMapper.mapToAccountsDTO(account, new AccountsDTO()));
        return customerDTO;
    }

    /**
     * @param customerDTO
     * @return
     */
    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountsDTO accountsDTO = customerDTO.getAccountsDTO();
        if (accountsDTO != null && accountsDTO.getAccountNumber() != null) {
            Accounts account = accountsRepository.findById(accountsDTO.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Account", "accountNumber", accountsDTO.getAccountNumber().toString()));

            AccountMapper.mapToAccountsEntity( accountsDTO, account);
            account = accountsRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));
            CustomerMapper.mapToCustomerEntity( customerDTO, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * @param mobileNumber the mobile number of the customer whose account is to be deleted
     * @return
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById( customer.getCustomerId());

        return true;
    }


    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();

        newAccount.setCustomerId(customer.getCustomerId());
        long accountNumber = 100000000L+ new Random().nextInt(9000000);

        newAccount.setAccountNumber(accountNumber);
        newAccount.setAccountType(SAVINGS);
        newAccount.setBranchAddress(ADDRESS);
        return newAccount;
    }
}
