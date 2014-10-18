package ee.ttu.service;

import ee.ttu.exception.CoreException;
import ee.ttu.model.classifier.*;
import ee.ttu.repository.classifier.*;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vahur Kaar on 27.09.2014.
 */
@Service
public class ClassifierServiceImpl implements ClassifierService {

    private static final Logger logger = LoggerFactory.getLogger(ClassifierServiceImpl.class);

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CustomerStateTypeRepository customerStateTypeRepository;

    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    @Autowired
    private ContractTypeRepository contractTypeRepository;

    @Autowired
    private ContractStatusTypeRepository contractStatusTypeRepository;

    @Override
    public CustomerType getCustomerType(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("CustomerType id must not be null!");
        }

        try {
            CustomerType customerType = customerTypeRepository.findOne(id);

            if (customerType == null) {
                throw new CoreException(String.format("Failed to find CustomerType with id '%s'", id));
            }

            return customerType;
        } catch (HibernateException e) {
            throw new CoreException(String.format("Failed to find CustomerType with id '%s'", id), e);
        }
    }

    @Override
    public List<CustomerType> getAllCustomerTypes() {
        try {
            return customerTypeRepository.findAll();
        } catch (HibernateException e) {
            logger.warn("Failed to find any customer types!", e);
            return new ArrayList<>();
        }
    }

    @Override
    public CustomerType findCustomerTypeByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("CustomerType name must not be null!");
        }

        try {
            CustomerType customerType = customerTypeRepository.findByName(name);

            if (customerType == null) {
                throw new CoreException(String.format("Failed to find CustomerType with name '%s'", name));
            }

            return customerType;
        } catch (HibernateException e) {
            throw new CoreException(String.format("Failed to find CustomerType with name '%s'", name), e);
        }
    }




    @Override
    public Country getCountry(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Country id must not be null!");
        }

        try {
            Country country = countryRepository.findOne(id);

            if (country == null) {
                throw new CoreException(String.format("Failed to find Country with id '%s'", id));
            }

            return country;
        } catch (HibernateException e) {
            throw new CoreException(String.format("Failed to find Country with id '%s'", id), e);
        }
    }

    @Override
    public List<Country> getAllCountries() {
        try {
            return countryRepository.findAll();
        } catch (HibernateException e) {
            logger.warn("Failed to find any countries!", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Country findCountryByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Country name must not be null!");
        }

        try {
            Country country = countryRepository.findByName(name);

            if (country == null) {
                throw new CoreException(String.format("Failed to find Country with name '%s'", name));
            }

            return country;
        } catch (HibernateException e) {
            throw new CoreException(String.format("Failed to find Country with name '%s'", name), e);
        }
    }




    @Override
    public CustomerStateType getCustomerStateType(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("CustomerStateType id must not be null!");
        }

        try {
            CustomerStateType customerStateType = customerStateTypeRepository.findOne(id);

            if (customerStateType == null) {
                throw new CoreException(String.format("Failed to find CustomerStateType with id '%s'", id));
            }

            return customerStateType;
        } catch (HibernateException e) {
            throw new CoreException(String.format("Failed to find CustomerStateType with id '%s'", id), e);
        }
    }

    @Override
    public List<CustomerStateType> getAllCustomerStateTypes() {
        try {
            return customerStateTypeRepository.findAll();
        } catch (HibernateException e) {
            logger.warn("Failed to find any customer state types!", e);
            return new ArrayList<>();
        }
    }

    @Override
    public CustomerStateType findCustomerStateTypeByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("CustomerStateType name must not be null!");
        }

        try {
            CustomerStateType customerStateType = customerStateTypeRepository.findByName(name);

            if (customerStateType == null) {
                throw new CoreException(String.format("Failed to find CustomerStateType with name '%s'", name));
            }

            return customerStateType;
        } catch (HibernateException e) {
            throw new CoreException(String.format("Failed to find CustomerStateType with name '%s'", name), e);
        }
    }



    @Override
    public ContractType getContractType(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ContractType id must not be null!");
        }

        try {
            ContractType contractType = contractTypeRepository.findOne(id);

            if (contractType == null) {
                throw new CoreException(String.format("Failed to find ContractType with id '%s'", id));
            }

            return contractType;
        } catch (HibernateException e) {
            throw new CoreException(String.format("Failed to find ContractType with id '%s'", id), e);
        }
    }

    @Override
    public List<ContractType> getAllContractTypes() {
        try {
            return contractTypeRepository.findAll();
        } catch (HibernateException e) {
            logger.warn("Failed to find any contract types!", e);
            return new ArrayList<>();
        }
    }

    @Override
    public ContractType findContractTypeByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("ContractType name must not be null!");
        }

        try {
            ContractType contractType = contractTypeRepository.findByName(name);

            if (contractType == null) {
                throw new CoreException(String.format("Failed to find ContractType with name '%s'", name));
            }

            return contractType;
        } catch (HibernateException e) {
            throw new CoreException(String.format("Failed to find ContractType with name '%s'", name), e);
        }
    }



    @Override
    public ContractStatusType getContractStatusType(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ContractStatusType id must not be null!");
        }

        try {
            ContractStatusType contractStatusType = contractStatusTypeRepository.findOne(id);

            if (contractStatusType == null) {
                throw new CoreException(String.format("Failed to find ContractStatusType with id '%s'", id));
            }

            return contractStatusType;
        } catch (HibernateException e) {
            throw new CoreException(String.format("Failed to find ContractStatusType with id '%s'", id), e);
        }
    }

    @Override
    public List<ContractStatusType> getAllContractStatusTypes() {
        try {
            return contractStatusTypeRepository.findAll();
        } catch (HibernateException e) {
            logger.warn("Failed to find any contract status types!", e);
            return new ArrayList<>();
        }
    }

    @Override
    public ContractStatusType findContractStatusTypeByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("ContractStatusType name must not be null!");
        }

        try {
            ContractStatusType contractStatusType = contractStatusTypeRepository.findByName(name);

            if (contractStatusType == null) {
                throw new CoreException(String.format("Failed to find ContractStatusType with name '%s'", name));
            }

            return contractStatusType;
        } catch (HibernateException e) {
            throw new CoreException(String.format("Failed to find ContractStatusType with name '%s'", name), e);
        }
    }
}
