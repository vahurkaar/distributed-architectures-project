import ee.ttu.CustomerUserDetailsService

// Place your Spring DSL code here
beans = {

    userDetailsService(CustomerUserDetailsService) {
        soapClient = ref('soapClient')
    }

    httpClient(wslite.http.HTTPClient) {
        connectTimeout = 5000
        readTimeout = 10000
        useCaches = false
        followRedirects = false
        sslTrustAllCerts = true
        // authorization = ref('clientBasicAuth')
        // proxy = myproxy
    }

    soapClient(wslite.soap.SOAPClient) {
        serviceURL = "http://localhost:8088/core/customerService/"
        httpClient = ref('httpClient')
        // authorization = ref('clientBasicAuth')
    }

}
