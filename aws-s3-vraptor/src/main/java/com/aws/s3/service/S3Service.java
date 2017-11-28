package com.aws.s3.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ListVersionsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.S3VersionSummary;
import com.amazonaws.services.s3.model.VersionListing;

import br.com.caelum.vraptor.observer.upload.UploadedFile;

public class S3Service {

	private static AWSCredentials credentials;
	
	private AmazonS3 s3;

	public S3Service() throws Exception {
		final Properties properties = new Properties();
		try {
			properties.load(this.getClass().getResourceAsStream("/aws.properties"));
			String acessKey = properties.getProperty("acessKey");
			String secretKey = properties.getProperty("secretKey");
			credentials = new BasicAWSCredentials(acessKey, secretKey);
			s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
					.withRegion(Regions.SA_EAST_1).build();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}

	
	}

	public List<Bucket> getBuckets() {
		return s3.listBuckets();
	}

	public List<S3ObjectSummary> getBucketObjectListing(String bucketName) {
		return s3.listObjects(new ListObjectsRequest().withBucketName(bucketName)).getObjectSummaries();
	}

	public Bucket createBucket(String bucket_name) {
		Bucket b = null;
		if (s3.doesBucketExistV2(bucket_name)) {
			throw new AmazonS3Exception(String.format("Bucket %s already exists.\n", bucket_name));
		} else {
			try {
				b = s3.createBucket(bucket_name);
			} catch (AmazonS3Exception e) {
				e.printStackTrace();
			}
		}
		return b;
	}
	
	public void uploadFile(String bucketName, UploadedFile uploadedFile) throws IOException {
		try {			
			s3.putObject(bucketName, uploadedFile.getFileName(), uploadedFile.getContentType());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public InputStream  download(String bucket, String key) throws IOException {
		S3Object o = s3.getObject(bucket, key);				
		return o.getObjectContent();
	}
	
	public void  deleteFile(String bucket, String key){
		s3.deleteObject(bucket, key);
	}
	
	public void  deleteBucket(String bucketName){
		try {
            System.out.println(" - removing objects from bucket");
            ObjectListing object_listing = s3.listObjects(bucketName);
            while (true) {
                for (Iterator<?> iterator =
                        object_listing.getObjectSummaries().iterator();
                        iterator.hasNext();) {
                    S3ObjectSummary summary = (S3ObjectSummary)iterator.next();
                    s3.deleteObject(bucketName, summary.getKey());
                }

                // more object_listing to retrieve?
                if (object_listing.isTruncated()) {
                    object_listing = s3.listNextBatchOfObjects(object_listing);
                } else {
                    break;
                }
            };

            System.out.println(" - removing versions from bucket");
            VersionListing version_listing = s3.listVersions(
                    new ListVersionsRequest().withBucketName(bucketName));
            while (true) {
                for (Iterator<?> iterator =
                        version_listing.getVersionSummaries().iterator();
                        iterator.hasNext();) {
                    S3VersionSummary vs = (S3VersionSummary)iterator.next();
                    s3.deleteVersion(
                            bucketName, vs.getKey(), vs.getVersionId());
                }

                if (version_listing.isTruncated()) {
                    version_listing = s3.listNextBatchOfVersions(
                            version_listing);
                } else {
                    break;
                }
            }
            
            s3.deleteBucket(bucketName);
        } catch (AmazonServiceException e) {
            e.printStackTrace();
            throw new AmazonServiceException(e.getErrorMessage());
        }
	}
	
	public static File stream2file (UploadedFile file) throws IOException {
        final File tempFile = File.createTempFile(file.getFileName().split("\\.")[0], "."+file.getFileName().split("\\.")[1]);
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(file.getFile(), out);
        }
        return tempFile;
    }
	
	
	public URL getUrl(String bucket, String key){
		java.util.Date expiration = new java.util.Date();
		long msec = expiration.getTime();
		msec += 1000 * 60 * 60; // 1 hour.
		expiration.setTime(msec);
		GeneratePresignedUrlRequest generatePresignedUrlRequest = 
		              new GeneratePresignedUrlRequest(bucket, key);
		generatePresignedUrlRequest.setMethod(HttpMethod.GET); // Default.
		generatePresignedUrlRequest.setExpiration(expiration);
		             
		URL s = s3.generatePresignedUrl(generatePresignedUrlRequest); 
		return s;
		
	}


}
