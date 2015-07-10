package com.aws.s3.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;

import com.aws.s3.service.AwsS3Service;

@Controller
public class S3Controller {

	@Inject
	private Result result;

	@Inject
	private AwsS3Service s3;

	@Path("/")
	public void list() {
		result.include("bucketsList", s3.getBuckets());
	}

	@Path("/bucketListFiles/{nome}")
	public void bucketListFiles(String nome) {
		result.include("bucketFiles", s3.getBucketObjectListing(nome));
		result.redirectTo(S3Controller.class).list();
	}

}