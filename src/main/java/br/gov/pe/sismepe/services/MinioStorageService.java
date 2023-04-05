package br.gov.pe.sismepe.services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.gov.pe.sismepe.dto.UploadArquivoDTO;
import br.gov.pe.sismepe.dto.UploadArquivoInfoDTO;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.InvalidExpiresRangeException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.http.Method;

@Service
public class MinioStorageService {

	@Value("${minio.server-image.bucket}")
	private String fileServerBucket;

	@Value("${minio.server-image.url}")
	private String fileServerUrl;

	@Value("${minio.server-image.access-key}")
	private String fileServerAccessKey;

	@Value("${minio.server-image.secret-key}")
	private String fileServerSecretKey;

	public UploadArquivoInfoDTO saveFile(UploadArquivoDTO dto)
			throws IOException, InvalidKeyException, ErrorResponseException, IllegalArgumentException,
			InsufficientDataException, InternalException, InvalidBucketNameException, InvalidResponseException,
			NoSuchAlgorithmException, ServerException, XmlParserException, InvalidExpiresRangeException {
		Decoder decoder = Base64.getDecoder();

		String uuid = UUID.randomUUID().toString();

		byte[] bytes = decoder.decode(dto.getBase64());
		
		File f = new File(uuid + "." + dto.getExtensao());
		FileOutputStream fos = new FileOutputStream(f);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(bytes);
		baos.writeTo(fos);
		
		MinioClient minioClient = MinioClient.builder().endpoint(fileServerUrl)
				.credentials(fileServerAccessKey, fileServerSecretKey).build();

		minioClient.uploadObject(UploadObjectArgs.builder().bucket(fileServerBucket)
				.object(uuid + "." + dto.getExtensao()).filename(f.getAbsolutePath()).build());
		
		String url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(fileServerBucket)
				.object(uuid + "." + dto.getExtensao()).expiry(60 * 60 * 24).method(Method.GET).build());
		
		f.delete();
		fos.close();
		baos.close();
		
		UploadArquivoInfoDTO info = new UploadArquivoInfoDTO(fileServerBucket, f.getName(), url);

		return info;
	}
	
	public UploadArquivoInfoDTO getFile(String bucket, String filename) {
		UploadArquivoInfoDTO info = new UploadArquivoInfoDTO();
		
		MinioClient minioClient = MinioClient.builder().endpoint(fileServerUrl)
				.credentials(fileServerAccessKey, fileServerSecretKey).build();

		try {
			String url = minioClient
					.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(bucket)
					.object(filename)
					.expiry(60 * 60 * 24)
					.method(Method.GET)
					.build());
			
			info.setBucket(bucket);
			info.setFilename(filename);
			info.setUrl(url);
			
			return info;
		} catch (InvalidKeyException | ErrorResponseException | IllegalArgumentException | InsufficientDataException
				| InternalException | InvalidBucketNameException | InvalidExpiresRangeException
				| InvalidResponseException | NoSuchAlgorithmException | XmlParserException | ServerException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return info;
	}
	
	public void deleteFile(String bucket, String filename) throws InvalidKeyException, ErrorResponseException, IllegalArgumentException, InsufficientDataException, InternalException, InvalidBucketNameException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException {
		MinioClient minioClient = MinioClient.builder().endpoint(fileServerUrl)
				.credentials(fileServerAccessKey, fileServerSecretKey).build();
		
		minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(filename).build());
	}

}
