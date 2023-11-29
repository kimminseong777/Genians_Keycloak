// package io.security.oauth2.springsecurityoauth2soap;

// import io.security.oauth2.DeleteProductRequest;
// import io.security.oauth2.DeleteProductResponse;
// import io.security.oauth2.GetProductDetailsRequest;
// import io.security.oauth2.GetProductDetailsResponse;
// import io.security.oauth2.Product;
// import org.springframework.ws.server.endpoint.annotation.Endpoint;
// import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
// import org.springframework.ws.server.endpoint.annotation.RequestPayload;
// import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

// import javax.annotation.security.RolesAllowed;
// import java.util.Map;

// @Endpoint
// public class ProductsEndpoint {

//     private final Map<String, Product> productMap;

//     public ProductsEndpoint(Map<String, Product> productMap) {
//         this.productMap = productMap;
//     }

//     @RolesAllowed("user")
//     @PayloadRoot(namespace = "https://sdevtest.genians.kr/springbootsoap/keycloak", localPart = "getProductDetailsRequest")
//     @ResponsePayload
//     public GetProductDetailsResponse getProductDetails(@RequestPayload GetProductDetailsRequest request) {
//         GetProductDetailsResponse response = new GetProductDetailsResponse();
//         response.setProduct(productMap.get(request.getId()));
//         return response;
//     }

//     @RolesAllowed("admin")
//     @PayloadRoot(namespace = "https://sdevtest.genians.kr/springbootsoap/keycloak", localPart = "deleteProductRequest")
//     @ResponsePayload
//     public DeleteProductResponse deleteProduct(@RequestPayload DeleteProductRequest request) {
//         DeleteProductResponse response = new DeleteProductResponse();
//         response.setMessage("Success! Deleted the product with the id - "+request.getId());
//         return response;
//     }
// }
