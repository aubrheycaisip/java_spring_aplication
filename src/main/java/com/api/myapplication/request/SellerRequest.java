package com.api.myapplication.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerRequest extends UserSignupRequest{

	private List<ProductRequest> products;
}
