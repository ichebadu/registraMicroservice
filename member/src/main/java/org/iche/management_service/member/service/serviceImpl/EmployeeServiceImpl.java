package org.iche.management_service.member.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.iche.management_service.member.dto.request.EmployeeRegistration;
import org.iche.management_service.member.dto.response.AddressResponse;
import org.iche.management_service.member.dto.response.ApiResponse;
import org.iche.management_service.member.dto.response.EmployeeId;
import org.iche.management_service.member.dto.response.EmployeeResponse;
import org.iche.management_service.member.entity.Employee;
import org.iche.management_service.member.repository.EmployeeRepository;
import org.iche.management_service.member.service.AddressClient;
import org.iche.management_service.member.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    private final WebClient webClient;
//    private final DiscoveryClient discoveryClient;
    private final LoadBalancerClient loadBalancerClient;

    private AddressClient addressClient;


    @Value("${addressservice.base.url}")
    private String restAddressBaseUrl;

//    Alternative way of using restTemplate
//    public EmployeeServiceImpl(@Value("${addressservice.base.url}") String restAddressBaseUrl, RestTemplateBuilder builder){
//        this.restTemplate = builder
//                .rootUri(restAddressBaseUrl)
//                .build();
//    }

    @Override
    public EmployeeResponse employeeRegistration(EmployeeRegistration employeeRegistration) {
        Employee employee = employeeRegistrationToEmployee(employeeRegistration);
        employeeRepository.save(employee);

        return EmployeeResponse.builder()
                .name(employeeRegistration.getEmail())
                .email(employeeRegistration.getEmail())
                .bloodGroup(employeeRegistration.getBloodGroup())
                .build();
    }

    @Override
    public Employee employeeRegistrationToEmployee(EmployeeRegistration employeeRegistration) {
        return modelMapper.map(employeeRegistration, Employee.class);
    }

    @Override
    public EmployeeId getEmployeeById(Long id) {
        EmployeeId employeeId = new EmployeeId();
        employeeId.setId(id);
        return employeeId;
    }

    @Override
    public EmployeeResponse getEmployeeDetails(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));




        // FOR WEBCLINET AND RESTTEMPLATE
        AddressResponse addressResponse = callToAddressServiceUsingWebClient(id);

        //FOR FEIGNCLIENT
  //      AddressResponse addressResponse = addressClient.getEmployeeById(id).getBody();


        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);


        System.out.println("AddressResponse : " + addressResponse);
        employeeResponse.setAddressResponse(addressResponse);

        System.out.println(employee);
        System.out.println("addressResponse :" + addressResponse);


        return employeeResponse;
    }

//    public AddressResponse callToAddressServiceUsingWebClient(Long id){
//        return webClient
//                .get()
//                .uri("/address/"+id)
//                .retrieve()
//                .bodyToMono(AddressResponse.class)
//                .block();
//
//    }

    // for restTemplate calls
    @Override
    public AddressResponse callToAddressServiceUsingWebClient(Long id){

    //get me the details for the ip and port number for address service when using discovery
//    List<ServiceInstance> instance = discoveryClient.getInstances("address-service");
//    ServiceInstance serviceInstance = instance.get(0);
//    String uri = serviceInstance.getUri().toString();


        //get me the details for the ip and port number for address service when using loadbalancer
        ServiceInstance serviceInstance = loadBalancerClient.choose("address-service");
        String uri = serviceInstance.getMetadata().toString();
        String contextRoot = serviceInstance.getMetadata().get("configPath");



        System.out.println("uri +  >>>>>>>>>>>>>>>" + uri+contextRoot);
        return restTemplate.getForObject(uri+contextRoot,AddressResponse.class,id);

    }

}
