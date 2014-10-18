package ee.ttu.service;

import ee.ttu.exception.CoreException;
import ee.ttu.model.classifier.Country;
import ee.ttu.repository.classifier.CountryRepository;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClassifierServiceImplCountryTest {

    @InjectMocks
    private ClassifierService classifierService = new ClassifierServiceImpl();

    @Mock
    private CountryRepository countryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCountryHandlesException() throws Exception {
        when(countryRepository.findOne(anyLong())).thenThrow(HibernateException.class);

        try {
            classifierService.getCountry(1L);
        } catch (CoreException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Failed to find Country with id '1'", e.getMessage());

            verify(countryRepository).findOne(1L);
            return;
        }

        Assert.fail();
    }

    @Test
    public void testGetCountryDoesNotFindCountry() throws Exception {
        try {
            classifierService.getCountry(1L);
        } catch (CoreException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Failed to find Country with id '1'", e.getMessage());

            verify(countryRepository).findOne(1L);
            return;
        }

        Assert.fail();
    }

    @Test
    public void testGetCountryReturnsValidCountry() throws Exception {
        Country country = new Country();
        country.setId(1L);
        country.setName("Name");
        when(countryRepository.findOne(1L)).thenReturn(country);

        Assert.assertEquals(country, classifierService.getCountry(1L));
        verify(countryRepository).findOne(1L);
    }

    @Test
    public void testGetCountryIdMustNotBeNull() throws Exception {
        try {
            classifierService.getCountry(null);
        } catch (IllegalArgumentException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Country id must not be null!", e.getMessage());
            return;
        }

        Assert.fail();
    }

    @Test
    public void testGetAllCountriesHandlesException() throws Exception {
        when(countryRepository.findAll()).thenThrow(HibernateException.class);

        List<Country> countries = classifierService.getAllCountries();
        Assert.assertNotNull(countries);
        Assert.assertEquals(0, countries.size());
        verify(countryRepository).findAll();
    }

    @Test
    public void testGetAllCountriesFindsNoResults() throws Exception {
        when(countryRepository.findAll()).thenReturn(new ArrayList<Country>());

        List<Country> countries = classifierService.getAllCountries();
        Assert.assertNotNull(countries);
        Assert.assertEquals(0, countries.size());
        verify(countryRepository).findAll();
    }

    @Test
    public void testGetAllCountriesFindsResults() throws Exception {
        Country country = new Country();
        country.setId(1L);
        country.setName("Name");
        when(countryRepository.findAll()).thenReturn(Arrays.asList(country));

        List<Country> countries = classifierService.getAllCountries();
        Assert.assertNotNull(countries);
        Assert.assertEquals(1, countries.size());
        Assert.assertEquals(country, countries.get(0));
        verify(countryRepository).findAll();
    }

    @Test
    public void testFindByNameHandlesException() throws Exception {
        when(countryRepository.findByName(anyString())).thenThrow(HibernateException.class);

        try {
            classifierService.findCountryByName("name");
        } catch (CoreException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Failed to find Country with name 'name'", e.getMessage());

            verify(countryRepository).findByName("name");
            return;
        }

        Assert.fail();
    }

    @Test
    public void testFindByNameDoesNotFindAResult() throws Exception {
        try {
            classifierService.findCountryByName("name");
        } catch (CoreException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Failed to find Country with name 'name'", e.getMessage());

            verify(countryRepository).findByName("name");
            return;
        }

        Assert.fail();
    }

    @Test
    public void testFindByNameFindsResult() throws Exception {
        Country country = new Country();
        country.setId(1L);
        country.setName("Name");
        when(countryRepository.findByName("Name")).thenReturn(country);

        Assert.assertEquals(country, classifierService.findCountryByName("Name"));
        verify(countryRepository).findByName("Name");
    }

    @Test
    public void testFindByNameNameMustNotBeNull() throws Exception {
        try {
            classifierService.findCountryByName(null);
        } catch (IllegalArgumentException e) {
            Assert.assertNotNull(e);
            Assert.assertEquals("Country name must not be null!", e.getMessage());
            return;
        }

        Assert.fail();
    }
}