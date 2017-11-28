package com.aws.s3.model;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import com.amazonaws.services.s3.model.Bucket;

@SessionScoped
public class BucketsSession implements Serializable {
	private static final long serialVersionUID = 1L;

	private String bucketName;
	private List<Bucket> buckets;

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public List<Bucket> getBuckets() {
		return buckets;
	}

	public void setBuckets(List<Bucket> buckets) {
		this.buckets = buckets;
	}

}