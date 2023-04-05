package br.gov.pe.sismepe.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pe.sismepe.dto.UploadArquivoDTO;
import br.gov.pe.sismepe.dto.UploadArquivoInfoDTO;
import br.gov.pe.sismepe.dto.UrlArquivoDTO;
import br.gov.pe.sismepe.services.MinioStorageService;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.InvalidExpiresRangeException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value = "/minio")
public class MinioResource {

	@Autowired
	private MinioStorageService minioStorageService;

	@PostMapping("/upload")
	public ResponseEntity<UploadArquivoInfoDTO> upload(@Valid @RequestBody UploadArquivoDTO uploadArquivoDTO) throws Exception {
		try {
			UploadArquivoInfoDTO info = minioStorageService.saveFile(uploadArquivoDTO);
			return ResponseEntity.ok(info);
		} catch (InvalidKeyException | ErrorResponseException | IllegalArgumentException | InsufficientDataException
				| InternalException | InvalidBucketNameException | InvalidResponseException | NoSuchAlgorithmException
				| ServerException | XmlParserException | InvalidExpiresRangeException | IOException e) {
			e.printStackTrace();
			throw new Exception("Erro ao salvar foto. Tente novamente");
		}
	}

	@PostMapping("/download-by-url")
	public ResponseEntity<String> downloadByUrl(@RequestBody UrlArquivoDTO dto) throws FileNotFoundException {
		try {
			URL u = new URL(dto.getUrl());
			BufferedImage img = ImageIO.read(u);
			File file = new File(dto.getFilename());
			ImageIO.write(img, dto.getExtension(), file);
			byte[] fileContent = Files.readAllBytes(file.toPath());
	        String base64 = "data:image/"+ dto.getExtension() +";base64," + Base64.getEncoder().encodeToString(fileContent);
	        file.delete();
			return ResponseEntity.ok(base64);
		} catch (Exception e) {
			throw new FileNotFoundException(e.getMessage());
		}
	}

}
