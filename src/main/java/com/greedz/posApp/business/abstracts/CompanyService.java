package com.greedz.posApp.business.abstracts;

import com.greedz.posApp.business.requests.companyRequests.CreateCompanyRequest;
import com.greedz.posApp.business.requests.companyRequests.DeleteCompanyRequest;
import com.greedz.posApp.business.requests.companyRequests.UpdateCompanyRequest;
import com.greedz.posApp.business.requests.productRequests.CreateProductRequest;
import com.greedz.posApp.business.requests.productRequests.DeleteProductRequest;
import com.greedz.posApp.business.requests.productRequests.UpdateProductRequest;
import com.greedz.posApp.business.responses.companyResponse.ListCompanyDto;
import com.greedz.posApp.business.responses.productResponses.ListProductDto;
import com.greedz.posApp.core.utilities.results.DataResult;
import com.greedz.posApp.core.utilities.results.Result;

import java.util.List;

public interface CompanyService {

    DataResult<List<ListCompanyDto>> getAll();
    DataResult<List<ListCompanyDto>> getAllPaged(int pageNo, int pageSize);

    Result add(CreateCompanyRequest createCompanyRequest);
    Result update(UpdateCompanyRequest updateCompanyRequest);
    Result delete(DeleteCompanyRequest deleteCompanyRequest);
}
