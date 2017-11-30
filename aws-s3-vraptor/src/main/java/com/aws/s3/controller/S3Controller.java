package com.aws.s3.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.inject.Inject;

import com.aws.s3.model.BucketsSession;
import com.aws.s3.service.S3Service;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.download.InputStreamDownload;
import br.com.caelum.vraptor.observer.upload.UploadedFile;

@Controller
public class S3Controller {

	@Inject
	private BucketsSession session;

	@Inject
	private Result result;

	@Inject
	private S3Service service;
	
	@Path("/")
	public void index() {
		if (null == session.getBuckets()) {
			session.setBuckets(service.getBuckets());
		}
		result.include("bucketsList", session.getBuckets());
	}

	@Path("/listBucketFiles/{bucketName}")
	public void listBucketFiles(String bucketName) {
		session.setBucketName(bucketName);
		result.include("bucketFiles", service.getBucketObjectListing(bucketName));
		result.include("bucketName", session.getBucketName());
		result.redirectTo(S3Controller.class).index();
	}

	@Path("/upload/{bucketName}")
	public void upload(String bucketName) {
		session.setBucketName(bucketName);
		result.include("bucketsList", session.getBuckets());
		result.include("bucketName", session.getBucketName());
	}

	public void bucketForm() {
		result.include("bucketsList", session.getBuckets());
	}

	@Path("/savebucket")
	public void savebucket(String bucketName) {
		try {
			service.createBucket(bucketName);
			session.setBuckets(service.getBuckets());
			result.include("msg", "Bucket save successful!");
			result.redirectTo(S3Controller.class).index();
		} catch (Exception e) {
			result.include("msgErro", erroMsg(e));
			result.redirectTo(S3Controller.class).bucketForm();
		}
	}

	@Path("/deletebucket/{bucketName}")
	public void deleteBucket(String bucketName) {
		try {
			service.deleteBucket(bucketName);
			session.setBuckets(service.getBuckets());
			result.include("msg", "Bucket delete successful!");
			result.redirectTo(S3Controller.class).index();
		} catch (Exception e) {
			result.include("msgErro", erroMsg(e));
			result.redirectTo(S3Controller.class).index();
		}
	}

	@Path("/uploadFile")
	public void uploadFile(UploadedFile file) {
		try {
			service.uploadFile(session.getBucketName(), file);
			result.include("msg", "File Upload successful");
			result.redirectTo(S3Controller.class).listBucketFiles(session.getBucketName());
		} catch (Exception e) {
			result.include("msgErro", erroMsg(e));
			result.redirectTo(S3Controller.class).upload(session.getBucketName());
		}
	}

	@Path("/download/{key}")
	public Download download(String key) {
		try {
			String contentType = Files.probeContentType(Paths.get(key));
			InputStream stream = service.download(session.getBucketName(), key);
			return new InputStreamDownload(stream, contentType, key);
		} catch (Exception e) {
			result.include("msgErro", erroMsg(e));
			result.redirectTo(S3Controller.class).listBucketFiles(session.getBucketName());
			return null;
		}
	}

	@Path("/deleteFile/{key}")
	public void deleteFile(String key) {
		try {
			service.deleteFile(session.getBucketName(), key);
			result.include("msg", "File delete successful!");
			result.redirectTo(S3Controller.class).listBucketFiles(session.getBucketName());
		} catch (Exception e) {
			result.include("msgErro", erroMsg(e));
			result.redirectTo(S3Controller.class).listBucketFiles(session.getBucketName());
		}
	}

	private String erroMsg(Exception e) {
		return e.getMessage().replaceAll("\n", "");
	}

}