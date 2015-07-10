package com.aws.s3.service;

import java.util.List;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class AwsS3Service {

	private AmazonS3 s3;

	public AwsS3Service() {
		AWSCredentialsProvider credentialsProvider = new ClasspathPropertiesFileCredentialsProvider();
		s3 = new AmazonS3Client(credentialsProvider);
	}

	public List<Bucket> getBuckets() {
		return s3.listBuckets();
	}
	
	public List<S3ObjectSummary> getBucketObjectListing(String bucketName) {
		return s3.listObjects(new ListObjectsRequest().withBucketName(bucketName)).getObjectSummaries();
	}

}
